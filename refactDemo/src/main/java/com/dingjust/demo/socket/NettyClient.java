package com.dingjust.demo.socket;

import com.dingjust.c2m.mcs.api.support.*;
import com.dingjust.c2m.mcs.core.*;
import com.dingjust.c2m.mcs.core.domain.*;
import com.dingjust.c2m.mcs.web.dto.*;
import com.dingjust.c2m.mcs.web.facade.*;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by dingjust on 2018/8/6.
 */
@Component("nettyClient")
public class NettyClient {
    @Autowired
    private InBoundSupport inBoundSupport;
    @Autowired
    private RfidStatusSupport rfidStatusSupport;
    @Autowired
    private OutBoundSupport outBoundSupport;
    @Autowired
    private ComponentStatusSupport componentStatusSupport;
    @Autowired
    private LineRfidStatusSupport lineRfidStatusSupport;
    @Autowired
    private StackConfigureFacade stackConfigureFacade;

    @Autowired
    private WorkStationFacade workStationFacade;
    @Autowired
    private EcuItfNoService itfNoService;
    @Autowired
    private StackComponentService stackComponentService;
    @Autowired
    private EcuRfidTbFacade ecuRfidTbFacade;

    @Autowired
    private LineService lineService;
    @Autowired
    private PreviousStackConfigService previousStackConfigService;

    public static NettyClient nettyClient;

    private static ChannelFuture future;
    private static Bootstrap b;

    private Logger logger = LoggerFactory.getLogger(getClass());


    private final static int SIZE_MAX = 1024 * 100;

    private Thread parseThread;
    private EcuDataParser dataParser;

    private Thread ecuDataProcessThread;
    private EcuDataProcessor dataProcessor;

    private Map<Integer, EcuItfNo> ecuItfNoMap;

    private Map<String, StackComponent> stackComponentMap;

    private Map<Integer, WorkStationDTO> workStationMap;

    private List<WorkStationDTO> workStationDTOList;

    private Map<Integer, EcuRfidTbDTO> ecuRfidTbMap;

    private Map<Long, StackConfigureDTO> stackConfigureDTOMap;

    private Map<Integer, Line> lineMap;

    private Map<Integer, List<PreviousStackConfig>> previousStackConfigMap;
    private Map<Integer, List<PreviousStackConfig>> nextStackConfigMap;

    @PostConstruct
    public void startUp() throws InterruptedException {
        nettyClient = this;
    }


    class EcuDataParser implements Runnable {
        private byte[] parseBuffer = new byte[SIZE_MAX];
        private int dataSize = 0;
        private List<EcuData> parsedDataList = new ArrayList<>();

        public synchronized void putBuffer(byte[] buffer) {
            int bufferSize = buffer.length;
            int tempDataSize = dataSize + bufferSize;
            int giveUpSize = tempDataSize - SIZE_MAX;

            if (giveUpSize > 0) {
                String giveUpString = EcuData.bytes2HexString(buffer, bufferSize - giveUpSize, giveUpSize);
                logger.warn("数据量超过缓冲区最大量，将会丢弃" + giveUpSize + "个字节,被丢弃的数据为：" + giveUpString);

                bufferSize -= giveUpSize;
            }

            System.arraycopy(buffer, 0, parseBuffer, dataSize, bufferSize);
            dataSize += bufferSize;
        }

        @Override
        public void run() {
            while (true) {
                sleep(30);

                synchronized (this) {
                    while (dataSize >= EcuData.MIN_DATA_SIZE) {
                        EcuData ecuData = new EcuData();
                        int offset = EcuData.parse(parseBuffer, dataSize, ecuData);
                        if (offset >= EcuData.MIN_DATA_SIZE) {
                            dataSize -= offset;
                            System.arraycopy(parseBuffer, offset, parseBuffer, 0, dataSize);

                            parsedDataList.add(ecuData);
                        } else if (offset == EcuData.ERROR_CODE_WAIT) {
                            break;
                        } else if (offset == EcuData.ERROR_CODE_ERROR_MESSAGE) {
                            dataSize -= 1;
                            System.arraycopy(parseBuffer, 1, parseBuffer, 0, dataSize);

                            continue;
                        }
                    }
                }
                if (parsedDataList.size() > 0) {
                    logger.info("共计收到" + parsedDataList.size() + "个数据包.");
                }
                for (EcuData ecuData : parsedDataList) {
                    dataProcessor.putEcuData(ecuData);
                }
                parsedDataList.clear();
            }
        }
    }

    class EcuDataProcessor implements Runnable {
        private List<EcuData> dataList = new ArrayList<>();

        public synchronized void putEcuData(EcuData ecuData) {
            dataList.add(ecuData);
        }

        @Override
        public void run() {
            while (true) {
                sleep(30);

                if (dataList.size() == 0) {
                    continue;
                }

                Object[] dataArray = dataList.toArray();
                synchronized (this) {
                    dataList.clear();
                }

                for (Object ecuData : dataArray) {
                    dealData((EcuData) ecuData);
                }
            }
        }
    }


    void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void connect(String host, int port) throws InterruptedException {
        dataParser = new EcuDataParser();
        parseThread = new Thread(dataParser);
        parseThread.setName("Thread-EcuParser");
        parseThread.start();

        dataProcessor = new EcuDataProcessor();
        ecuDataProcessThread = new Thread(dataProcessor);
        ecuDataProcessThread.setName("Thread-EcuDataProcess");
        ecuDataProcessThread.start();

        //初始获取接口数据，元器件信息

        if (ecuItfNoMap == null) {
            ecuItfNoMap = getEcuItfNoMap();
        }
        if (stackComponentMap == null) {
            stackComponentMap = getStackComponentMap();
        }
        if (workStationMap == null) {
            workStationMap = getWorkStationMap();
        }
        if (ecuRfidTbMap == null) {
            ecuRfidTbMap = getEcuRfidTbMap();
        }
        if (stackConfigureDTOMap == null) {
            stackConfigureDTOMap = getStackConfigureDTOMap();
        }
        if (CollectionUtils.isEmpty(workStationDTOList)) {
            workStationDTOList = getWorkStationListByType();
        }
        if (lineMap == null) {
            lineMap = getLineMap();
        }
        if (previousStackConfigMap == null) {
            previousStackConfigMap = getPreviousStackConfigMap();
        }
        if (nextStackConfigMap == null) {
            nextStackConfigMap = getNextStackConfigMap();
        }

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new IdleStateHandler(10, 0, 0, TimeUnit.SECONDS));
                            ch.pipeline().addLast(new ClientHandler());
                        }
                    });

            future = b.connect(host, port).addListeners(new ConnectionListener(this)).sync();
            future.channel().closeFuture().sync();
        } finally {
            Thread.sleep(5500);
            group.shutdownGracefully();
        }
    }

    class ClientHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            byte[] msg = new byte[]{0x0f, 0x0f};
            logger.info("send message:{}", msg);
            ctx.writeAndFlush(msg);
        }

        // 失败计数器：未收到client端发送的ping请求
        private int unRecPingTimes = 0;
        // 定义服务端没有收到心跳消息的最大次数
        private static final int MAX_UN_REC_PING_TIMES = 3;

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            logger.info("判断链接是否超时：" + unRecPingTimes);
            if (evt instanceof IdleStateEvent) {
                IdleState state = ((IdleStateEvent) evt).state();
                if (state == IdleState.READER_IDLE) {
                    if (unRecPingTimes >= MAX_UN_REC_PING_TIMES) {
                        logger.info("写超时，关闭chanel");
                        throw new Exception("idle exception");
                    } else {
                        unRecPingTimes++;
                    }
                }
            } else {
                super.userEventTriggered(ctx, evt);
            }
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg)
                throws Exception {
            try {
                unRecPingTimes = 0;

                ByteBuf byteBuf = (ByteBuf) msg;
                byte[] req = new byte[byteBuf.readableBytes()];
                byteBuf.readBytes(req);

                String rawData = EcuData.bytes2HexString(req, 0, req.length);
                logger.debug("收到原始数据：" + rawData);

                dataParser.putBuffer(req);
            } catch (Exception e) {
                logger.info("信息处理错误错误" + e);
            }
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("--- Server is inactive ---");
            // 10s 之后尝试重新连接服务器
            System.out.println("5s 之后尝试重新连接服务器...");
            Thread.sleep(5 * 1000);
            nettyClient.connect(NettyConfig.getIp(), NettyConfig.getPort());
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
                throws Exception {
            cause.printStackTrace();
            ctx.close();
        }

    }

    public void inBound(StackComponent stackComponent, WorkStationDTO workStationDTO, EcuRfidTbDTO rfidTb) {
        if (workStationDTO != null) {
            nettyClient.inBoundSupport.process(stackComponent, workStationDTO, rfidTb, stackConfigureDTOMap);
        }
    }

    public void outBound(WorkStationDTO workStationDTO, int data) {
        if (workStationDTO != null) {
            nettyClient.outBoundSupport.process(workStationDTO, data);
        }
    }

    public void saveStatusLog(EcuData ecuData) {
        EcuStatusLogDTO logDTO = new EcuStatusLogDTO();
        logDTO.setOriginalData(ecuData.getRawData());
        logDTO.setVersionNo(String.valueOf(ecuData.getVer()));
        logDTO.setStackNo(String.valueOf(ecuData.getStackNo()));
        logDTO.setLineNo(String.valueOf(ecuData.getLineNo()));
        logDTO.setItfNo(String.valueOf(ecuData.getItfNo()));
        logDTO.setItfData(String.valueOf(ecuData.getData()));
        logDTO.setAcceptTime(LocalDateTime.now());
        return;
        //nettyClient.ecuStatusLogFacade.create(logDTO);
    }

    public void dealData(EcuData ecuData) {
        try {
            //保存日志
            //saveStatusLog(ecuData);
            int itfNo = ecuData.getItfNo();
            int stackNo = ecuData.getStackNo();
            int data = ecuData.getData();
            //根据元器件类型确定是进站还是出站，再根据编号确定是否是读卡器，然后在做入站或出站操作

            EcuItfNo ecuItfNo = ecuItfNoMap.get(itfNo);
            StackComponent stackComponent = stackComponentMap.get(itfNo + "-" + stackNo);

            if (ecuItfNo != null && stackComponent != null && stackComponent.getStack() != null
                    && stackComponent.getComponentType() != null && stackComponent.getIconType() != null) {
                if (StringUtils.equals(ecuItfNo.getType(), McsConstants.CARD_READER)) { //接口类型读卡器
                    logger.info("栈号：" + stackNo + ",读卡器:" + itfNo + ",读取到的rfid:" + data);
                    dealCardReader(data, stackComponent);
                } else if (StringUtils.equals(ecuItfNo.getType(), McsConstants.SWITCHING_VALUE)
                        && StringUtils.equals(stackComponent.getIconType().name(), IconType.BUTTON.name())) { //接口类型开关量，手动下放按钮
                    //点击按钮动作且出站不满站时3S之内有气缸动作认为为连续动作，
                    //点击按钮并气缸动作，8S之内再次点击按钮并气缸动作，为一次操作，
                    //点击按钮并气缸动作，超过8S，为两次动作，
                    //点击按钮时出站满站时，出站光电为3且出站气缸和下放气缸都动作，为一次动作，
                    StackComponent buttonComponent = nettyClient.stackComponentService.findByComponentTypeAndIconType(ComponentType.HANDDOWNPASS.name(), IconType.BUTTON.name(), stackComponent.getStack().getId());
                    if (buttonComponent != null) {
                        LocalDateTime nowTime = LocalDateTime.now();
                        java.time.Duration buttonDuration = java.time.Duration.between(buttonComponent.getUpdateTime(), nowTime);
                        if (buttonDuration.getSeconds() > 8) {
                            nettyClient.rfidStatusSupport.changeOrder(stackComponent, nowTime);
                        }
                    }
                } else if (StringUtils.equals(ecuItfNo.getType(), McsConstants.SOLENOID_VALVE) && StringUtils.equals(stackComponent.getIconType().name(), IconType.POWER.name())) {
                    nettyClient.componentStatusSupport.updateLineStatus(data, stackComponent);
                }
                //修改元器件状态
                nettyClient.componentStatusSupport.process(ecuItfNo, stackComponent, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dealCardReader(int data, StackComponent stackComponent) {
        Stack stack = stackComponent.getStack();
        EcuRfidTbDTO rfidTb = ecuRfidTbMap.get(data);
        if (stack.getStackType() != null && StringUtils.equals(stack.getStackType().name(), StackType.WORKSTATION.name())) {
            WorkStationDTO workStationDTO = workStationMap.get(stack.getSequenceNumber());
            if (rfidTb != null) {
                inOutStation(stackComponent, workStationDTO, rfidTb, data);
                if (workStationDTO != null && StringUtils.equals(workStationDTO.getType(), WorkStationType.HANGING.name())
                        && StringUtils.equals(stackComponent.getComponentType().name(), ComponentType.AUTODOWNPASS.name())) {
                    //回收站读卡器读取到衣架，删除该衣架历史记录
                    nettyClient.inBoundSupport.deleteHistoryDispatching(rfidTb);
                }
            } else {
                //如果rfid没有维护，需要初始维护rfid到mcs和ecu中
                if (workStationDTO != null && StringUtils.equals(workStationDTO.getType(), WorkStationType.HANGING.name())
                        && StringUtils.equals(stackComponent.getComponentType().name(), ComponentType.PULLIN.name())) {
                    saveRfid(workStationDTO, data);
                }
            }
        } else if (StringUtils.equals(stack.getStackType().name(), StackType.NODE.name())) {
            //通过节点判断产线wip值，节点出站代表进入某一条产线
            if (StringUtils.equals(stackComponent.getComponentType().name(), ComponentType.OUTBOUND.name())) {
                //获取本节点的上一条产线
                List<PreviousStackConfig> previousStackConfigList = previousStackConfigMap.get(stack.getSequenceNumber());
                Line previousLine = lineMap.get(previousStackConfigList.get(0).getPreviousStack().getSequenceNumber());
                //获取本节点的下一条产线
                List<PreviousStackConfig> nextStackConfigList = nextStackConfigMap.get(stack.getSequenceNumber());
                Line nextLine = lineMap.get(nextStackConfigList.get(0).getCurrentStack().getSequenceNumber());
                nettyClient.lineRfidStatusSupport.process(previousLine, nextLine, rfidTb, stack);
            } else if (StringUtils.equals(stackComponent.getComponentType().name(), ComponentType.PULLIN.name())) {
                List<PreviousStackConfig> previousStackConfigList = previousStackConfigMap.get(stack.getSequenceNumber());
                Line previousLine = lineMap.get(previousStackConfigList.get(0).getPreviousStack().getSequenceNumber());

                List<PreviousStackConfig> nextStackConfigList = nextStackConfigMap.get(stack.getSequenceNumber());
                Line nextLine = lineMap.get(nextStackConfigList.get(0).getCurrentStack().getSequenceNumber());
                nettyClient.lineRfidStatusSupport.NodePullIn(previousLine, rfidTb, nextLine, stack);
                //判断下一个节点是否可以下发
                nettyClient.lineRfidStatusSupport.issueNextNodes(stack, rfidTb, nextStackConfigMap, lineMap, previousStackConfigMap);

            }
        } else if (stack.getStackType() != null && StringUtils.equals(stack.getStackType().name(), StackType.LINE.name())) {
            //产线上编号为的12的接口推送的读卡器信息是用来绑定作业单的
            if (StringUtils.equals(stackComponent.getComponentType().name(), ComponentType.BINDINGORDER.name())) {
                if (CollectionUtils.isNotEmpty(workStationDTOList)) {
                    nettyClient.outBoundSupport.pushRfid(workStationDTOList.get(0), data);
                }
            } else {
                //产线读卡器信息推送过来的时候，去查询mes，是否有相应的作业单和该rfid绑定，如果没有，说明为空衣架，需要回收
                logger.info("检测是否存在未绑定作业单的rfid");
                nettyClient.outBoundSupport.checkRfid(stack.getSequenceNumber(), data);
                logger.info("检测该衣架是否在正常位置");
                nettyClient.rfidStatusSupport.process(stack, data);
                //如果是主轨读卡器
                Line line = lineMap.get(stack.getSequenceNumber());
                if (StringUtils.equals(line.getType().name(), ProductionLineType.PRINCIPALAXIS.name())) {
                    //判断下一个节点是否可以下发
                    nettyClient.lineRfidStatusSupport.issueNextNodes(stack, rfidTb, nextStackConfigMap, lineMap, previousStackConfigMap);
                }
            }
            //保存衣架当前位置
            nettyClient.inBoundSupport.saveHangerPlace(stack, rfidTb, stackConfigureDTOMap);
        }
    }

    public Map<Integer, EcuItfNo> getEcuItfNoMap() {
        List<EcuItfNo> itfNoList = nettyClient.itfNoService.findAll();
        Map<Integer, EcuItfNo> ecuItfNoMap = new HashMap<>();
        for (EcuItfNo ecuItfNo : itfNoList) {
            ecuItfNoMap.put(ecuItfNo.getItfNo(), ecuItfNo);
        }
        return ecuItfNoMap;
    }

    public Map<String, StackComponent> getStackComponentMap() {
        List<StackComponent> stackComponentList = nettyClient.stackComponentService.findAll();
        Map<String, StackComponent> stackComponentMap = new HashMap<>();
        for (StackComponent stackComponent : stackComponentList) {
            stackComponentMap.put(stackComponent.getItfNo().getItfNo() + "-" + stackComponent.getStack().getSequenceNumber(), stackComponent);
        }
        return stackComponentMap;
    }

    public Map<Integer, WorkStationDTO> getWorkStationMap() {
        List<WorkStationDTO> workStationList = nettyClient.workStationFacade.findAll();
        Map<Integer, WorkStationDTO> workStationMap = new HashMap<>();
        for (WorkStationDTO workStation : workStationList) {
            workStationMap.put(workStation.getStack().getSequenceNumber(), workStation);
        }
        return workStationMap;
    }

    public List<WorkStationDTO> getWorkStationListByType() {
        List<WorkStationDTO> workStationList = nettyClient.workStationFacade.findByType(WorkStationType.HANGING.name());
        return workStationList;
    }

    public Map<Integer, EcuRfidTbDTO> getEcuRfidTbMap() {
        List<EcuRfidTbDTO> ecuRfidTbList = nettyClient.ecuRfidTbFacade.findAll();
        Map<Integer, EcuRfidTbDTO> ecuRfidTbMap = new HashMap<>();
        for (EcuRfidTbDTO ecuRfidTb : ecuRfidTbList) {
            ecuRfidTbMap.put(ecuRfidTb.getRfidNo(), ecuRfidTb);
        }
        return ecuRfidTbMap;
    }

    public Map<Long, StackConfigureDTO> getStackConfigureDTOMap() {
        List<StackConfigureDTO> stackConfigureDTOList = nettyClient.stackConfigureFacade.findAll();
        Map<Long, StackConfigureDTO> stackConfigureDTOMap = new HashMap<>();
        for (StackConfigureDTO stackConfigureDTO : stackConfigureDTOList) {
            stackConfigureDTOMap.put(stackConfigureDTO.getStack().getId(), stackConfigureDTO);
        }
        return stackConfigureDTOMap;
    }

    public String[] removeArray(String[] content, int index) {
        String[] holdContent = content;
        int j = 0;
        for (int i = index; i < holdContent.length; i++) {
            content[j] = holdContent[i];
            j++;
        }
        return content;
    }

    public void inOutStation(StackComponent stackComponent, WorkStationDTO workStationDTO, EcuRfidTbDTO rfidTb, int data) {
        if (StringUtils.equals(stackComponent.getComponentType().name(), ComponentType.PULLIN.name())) {
            inBound(stackComponent, workStationDTO, rfidTb);
        } else if (StringUtils.equals(stackComponent.getComponentType().name(), ComponentType.OUTBOUND.name()) && !StringUtils.equals(workStationDTO.getType(), WorkStationType.HANGING.name())) {
            outBound(workStationDTO, data);
        }
    }

    public Map<Integer, Line> getLineMap() {
        List<Line> lineList = nettyClient.lineService.findAll();
        Map<Integer, Line> lineMap = new HashMap<>();
        for (Line line : lineList) {
            lineMap.put(line.getStack().getSequenceNumber(), line);
        }
        return lineMap;
    }

    public Map<Integer, List<PreviousStackConfig>> getPreviousStackConfigMap() {
        List<PreviousStackConfig> previousStackConfigList = nettyClient.previousStackConfigService.findAll();
        List<Integer> stackList = new ArrayList<>();
        for (PreviousStackConfig previousStackConfig : previousStackConfigList) {
            if (!stackList.contains(previousStackConfig.getCurrentStack().getSequenceNumber())) {
                stackList.add(previousStackConfig.getCurrentStack().getSequenceNumber());
            }
        }
        Map<Integer, List<PreviousStackConfig>> previousStackConfigMap = new HashMap<>();
        for (Integer stackNumber : stackList) {
            List<PreviousStackConfig> previousStackConfigs = new ArrayList();
            for (PreviousStackConfig previousStackConfig : previousStackConfigList) {
                if (stackNumber.intValue() == previousStackConfig.getCurrentStack().getSequenceNumber().intValue()
                        && previousStackConfig.getPreviousStack() != null && StringUtils.equals(previousStackConfig.getPreviousStack().getStackType().name(), StackType.LINE.name())) {
                    previousStackConfigs.add(previousStackConfig);
                }
            }
            previousStackConfigMap.put(stackNumber, previousStackConfigs);
        }
        return previousStackConfigMap;
    }

    public Map<Integer, List<PreviousStackConfig>> getNextStackConfigMap() {
        List<PreviousStackConfig> previousStackConfigList = nettyClient.previousStackConfigService.findAll();
        List<Integer> stackList = new ArrayList<>();
        for (PreviousStackConfig previousStackConfig : previousStackConfigList) {
            if (previousStackConfig.getPreviousStack() != null && !stackList.contains(previousStackConfig.getPreviousStack().getSequenceNumber())) {
                stackList.add(previousStackConfig.getPreviousStack().getSequenceNumber());
            }
        }
        Map<Integer, List<PreviousStackConfig>> nextStackConfigMap = new HashMap<>();
        for (Integer stackNumber : stackList) {
            List<PreviousStackConfig> nextStackConfigs = new ArrayList();
            for (PreviousStackConfig previousStackConfig : previousStackConfigList) {
                if (previousStackConfig.getPreviousStack() != null && stackNumber.intValue() == previousStackConfig.getPreviousStack().getSequenceNumber().intValue()
                        && StringUtils.equals(previousStackConfig.getCurrentStack().getStackType().name(), StackType.LINE.name())) {
                    nextStackConfigs.add(previousStackConfig);
                }
            }
            nextStackConfigMap.put(stackNumber, nextStackConfigs);
        }
        return nextStackConfigMap;
    }


    @Transactional
    public void saveRfid(WorkStationDTO workStationDTO, int data) {
        if (workStationDTO != null && StringUtils.equals(workStationDTO.getType(), WorkStationType.HANGING.name())) {
            EcuRfidTbDTO rfid = new EcuRfidTbDTO();
            rfid.setRfidNo(data);
            rfid.setRfidName(String.valueOf(data));
            rfid.setRfidTime(LocalDateTime.now());
            nettyClient.ecuRfidTbFacade.create(rfid);
        }
    }

}
