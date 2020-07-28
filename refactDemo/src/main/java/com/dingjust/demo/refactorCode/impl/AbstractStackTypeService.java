package com.dingjust.demo.refactorCode.impl;

import com.dingjust.demo.entity.EcuItfNo;
import com.dingjust.demo.entity.Line;
import com.dingjust.demo.entity.PreviousStackConfig;
import com.dingjust.demo.entity.StackComponent;
import com.dingjust.demo.pojo.dto.EcuRfidTbDTO;
import com.dingjust.demo.pojo.dto.StackConfigureDTO;
import com.dingjust.demo.pojo.dto.WorkStationDTO;
import com.dingjust.demo.refactorCode.IStackTypeStrategy;

import java.util.List;
import java.util.Map;

/**
 * 抽象策略
 *
 * @author zhenghui
 * @date 2019-11-4
 */
public abstract class AbstractStackTypeService implements IStackTypeStrategy {

    protected Map<Integer, EcuItfNo> ecuItfNoMap;

    protected Map<String, StackComponent> stackComponentMap;

    protected Map<Integer, WorkStationDTO> workStationMap;

    protected List<WorkStationDTO> workStationDTOList;

    protected Map<Integer, EcuRfidTbDTO> ecuRfidTbMap;

    protected Map<Long, StackConfigureDTO> stackConfigureDTOMap;

    protected Map<Integer, Line> lineMap;

    protected Map<Integer, List<PreviousStackConfig>> previousStackConfigMap;
    protected Map<Integer, List<PreviousStackConfig>> nextStackConfigMap;

}
