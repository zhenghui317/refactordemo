package com.dingjust.demo.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class EcuData {
    private static Logger logger = LoggerFactory.getLogger(EcuData.class);
    private static final String formatter = "{\"header\":%d, "
            + "\"len\":%d, "
            + "\"ver\":%d, "
            + "\"stackNo\":%d,"
            + "\"lineNo\":%d, "
            + "\"itfNo\":%d,"
            + "\"data\":%d, "
            + "\"crc\":%d, "
            + "\"rawData\":\"%s\"}";

    public final static int MIN_DATA_SIZE = 14;
    public final static int ERROR_CODE_WAIT = 0;
    public final static int ERROR_CODE_ERROR_MESSAGE = -1;
    public final static int ERROR_CODE_ERROR_HEAD = -1;
    public final static int ERROR_CODE_ERROR_LENGTH = -1;
    public final static int ERROR_CODE_ERROR_CRC = -1;

    private final static short HEAD_TAG = 0x5a5a;

    private int header = HEAD_TAG;
    private byte len;
    private byte ver;
    private int stackNo;
    private int lineNo;
    private int itfNo;
    private int data;
    private int crc;
    private String rawData;

    @Override
    public String toString() {
        return String.format(formatter, this.header, this.len, this.ver, this.stackNo, this.lineNo, this.itfNo, this.data, this.crc, this.rawData);
    }

    public static void main(String[] args) {
        //String rawString ="5a5a0e0000220001000b000070d52a5c";
        String rawString = "33343536375a5a0e00000c0001000f0000037f04dd5a5a0c000013000100110001fc3e5a5a0c00003b000100140002fec65a5a0e00002a0001000b00004205162f5a5a0e0000100001000b0000621d7d1c5a5a0c0000210001001100013f2d5a5a0c000029000100110001ffa45a5a0e0000070001000f00001e0982265a5a0e0000050001000f000038e90a245a5a0e0000130001000f000011cde1dd5a5a0c000002000100110001fd3f5a5a0c000020000100110001ff3d5a5a0c0000030001001100013d2f5a5a0c00000f0001001100013de35a5a0c00000e000100110001fdf35a5a0e0000040001000b00003d4f705b5a5a0c0000240001001100013f785a5a0c00001d0001001100013cd15a5a0e00000c0001000b00008074330c5a5a0e0000420001000b00000eaf6b545a5a0e00000f0001000f0000621d8d615a5a0c0000280001001100013fb45a5a0c000008000100110001fd955a5a0c0000090001001100013d855a5a0c00000b000100110001fda65a5a0e0000240001000b00004f8a42265a5a0c0000120001001100013c2e5a5a0c000023000100110001ff0e5a5a0e0000030001000f00003d4f808c5a5a0c0000060001001100013d7a5a5a0c000025000100110001ff685a5a0c00001e0001001100013ce25a5a0e0000230001000b0000485119425a5a0e00000b0001000f00008074c3db5a5a0c00000c0001001100013dd05a5a0c00001f000100110001fcf25a5a0e0000080001000b0000733f343a5a5a0c000004000100140002fd095a5a0c00000a0001001100013db65a5a0c0000220001001100013f1e5a5a0e0000060001000b0000576c692d5a5a0e00001e0001000b000064aacb135a5a0c00002a000100110001ff975a5a0c0000270001001100013f4b5a5a0c000007000100110001fd6a5a5a0e00001f0001000b00000b1bdff35a5a0c00001c000100110001fcc15a5a0e0000220001000b000070d52a5c5a5a0c000026000100110001ff5b5a5a0c0000050001001100013d495a5a0c00003b000100110001fe965a5a0c000013000100110001fc3e5a5a0e0000270001000b00003e3b96d65a5a0e0000100001000b000076b100135a5a0e00003e0001000b00000c18bf255a5a0e0000130001000f0000171ddddf5a5a0c0000210001001100013f2d5a5a0c000029000100110001ffa45a5a0e0000070001000f0000733f048a5a5a0e0000050001000f0000576c59c85a5a0c000002000100110001fd3f5a5a0e00001c0001000b0000288b73fe5a5a0e00000a0001000b00000371a0865a5a0c000020000100110001ff3d5a5a0c0000030001001100013d2f5a5a0c00000f0001001100013de35a5a0e0000040001000b00002069fad35a5a0c00000e000100110001fdf35a5a0c0000240001001100013f785a5a";
        byte[] parseBuffer = string2Bytes(rawString); //new byte[]{0x5a, 0x5a, 0x0e, 0x00, 0x00, 0x22, 0x00, 0x01, 0x00, 0x0b, 0x00, 0x00, 0x70, (byte) 0xd5, 0x2a, 0x5c};
        int dataSize = parseBuffer.length;
        List<EcuData> parsedDataList = new ArrayList<>();

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

        logger.info("共计收到" + parsedDataList.size() + "个数据包.");
    }

    public static int parse(byte[] buffer, int maxCapacity, EcuData ecuData) {
        //标头校验
        if (buffer[0] != 0x5a || buffer[1] != 0x5a) {
            logger.error("标头错误,header:" + bytes2HexString(buffer, 0, 2));
            return EcuData.ERROR_CODE_ERROR_HEAD;
        }

        int index = 2;
        ecuData.len = buffer[index];
        index += 1;

        //长度不够
        if (ecuData.len > maxCapacity) {
            logger.error("长度不够，等待。maxCapacity:" + maxCapacity + ",ecuData.len:" + ecuData.len);
            return EcuData.ERROR_CODE_WAIT;
        }
        //长度错误
        if (ecuData.len > buffer.length) {
            logger.error("长度错误。ecuData.len:" + ecuData.len + ",buffer.length:" + buffer.length);
            return EcuData.ERROR_CODE_ERROR_LENGTH;
        }
        //原始数据
        ecuData.rawData = bytes2HexString(buffer, 0, ecuData.len + 2);

        // crc 校验
        ecuData.crc = convertBytes2Int(buffer, ecuData.len);
        int crc = Crc.crc16Calculate(0xffff, buffer, 2, ecuData.len);
        if (crc != ecuData.crc) {
            return EcuData.ERROR_CODE_ERROR_CRC;
        }

        ecuData.ver = buffer[index];  //版本号:0表示是正常数据，如果是128（-1）则表示是开始的数据
        index += 1;

        ecuData.stackNo = convertBytes2Int(buffer, index);
        index += 2;

        ecuData.lineNo = convertBytes2Int(buffer, index);
        index += 2;

        ecuData.itfNo = convertBytes2Int(buffer, index);
        index += 2;

        if (ecuData.len == 0x0c) {
            ecuData.data = convertBytes2Int(buffer, index);
        } else {
            ecuData.data = convertBytes4Int(buffer, index);
        }
        index = ecuData.len + 2; //要加上标头的长度

        logger.info("收到ecu数据：" + ecuData);

        return index;
    }


    public byte getLen() {
        return len;
    }

    public void setLen(byte len) {
        this.len = len;
    }

    public int getHeader() {
        return header;
    }

    public byte getVer() {
        return ver;
    }


    public int getCrc() {
        return crc;
    }

    public int getItfNo() {
        return itfNo;
    }

    public int getLineNo() {
        return lineNo;
    }

    public int getStackNo() {
        return stackNo;
    }

    public void setCrc(int crc) {
        this.crc = crc;
    }

    public void setItfNo(int itfNo) {
        this.itfNo = itfNo;
    }

    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }

    public void setStackNo(int stackNo) {
        this.stackNo = stackNo;
    }

    public void setVer(byte ver) {
        this.ver = ver;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public static int convertBytes4Int(byte[] buffer, int index) {
        return ((buffer[index] & 0xff) << 24) | ((buffer[index + 1] & 0xff) << 16) | ((buffer[index + 2] & 0xff) << 8) | (buffer[index + 3] & 0xff);
    }

    public static int convertBytes3Int(byte[] buffer, int index) {
        return ((buffer[index] & 0xff) << 16) | ((buffer[index + 1] & 0xff) << 8) | (buffer[index + 2] & 0xff);
    }

    public static int convertBytes2Int(byte[] buffer, int index) {
        return ((buffer[index] & 0xff) << 8) | (buffer[index + 1] & 0xff);
    }

    public static String bytes2HexString(byte[] bytes, int start, int end) {
        return bytes2HexString(bytes, start, end, " ");
    }

    public static String bytes2HexString(byte[] bytes, int start, int end, String delimieter) {
        StringBuilder sb = new StringBuilder();
        int a = 0;
        for (; start < end; start++) { // 使用除与取余进行转换
            byte b = bytes[start];
            if (b < 0) {
                a = 256 + b;
            } else {
                a = b;
            }
            sb.append(HEX_CHAR[a / 16]);
            sb.append(HEX_CHAR[a % 16]);

            sb.append(delimieter);
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    public static byte[] string2Bytes(String str) {
        if (str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }

    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};


}

class Crc {
    public static int crc16Calculate(int crc, byte[] buffer, int start, int len) {
        for (int i = start; i < len; i++) {
            crc = crc16Byte(crc, buffer[i]);
        }
        return crc;
    }

    public static int crc16Calculate(int crc, byte[] buffer, int len) {
        return crc16Calculate(crc, buffer, 0, len);
    }

    public static int crc16Byte(int crc, int data) {
        return (crc >> 8) ^ CRC_TB[(crc ^ data) & 0xff];
    }

    static final int[] CRC_TB = {0x0000, 0xC0C1, 0xC181, 0x0140, 0xC301, 0x03C0, 0x0280, 0xC241, 0xC601, 0x06C0,
            0x0780, 0xC741, 0x0500, 0xC5C1, 0xC481, 0x0440, 0xCC01, 0x0CC0, 0x0D80, 0xCD41, 0x0F00, 0xCFC1, 0xCE81,
            0x0E40, 0x0A00, 0xCAC1, 0xCB81, 0x0B40, 0xC901, 0x09C0, 0x0880, 0xC841, 0xD801, 0x18C0, 0x1980, 0xD941,
            0x1B00, 0xDBC1, 0xDA81, 0x1A40, 0x1E00, 0xDEC1, 0xDF81, 0x1F40, 0xDD01, 0x1DC0, 0x1C80, 0xDC41, 0x1400,
            0xD4C1, 0xD581, 0x1540, 0xD701, 0x17C0, 0x1680, 0xD641, 0xD201, 0x12C0, 0x1380, 0xD341, 0x1100, 0xD1C1,
            0xD081, 0x1040, 0xF001, 0x30C0, 0x3180, 0xF141, 0x3300, 0xF3C1, 0xF281, 0x3240, 0x3600, 0xF6C1, 0xF781,
            0x3740, 0xF501, 0x35C0, 0x3480, 0xF441, 0x3C00, 0xFCC1, 0xFD81, 0x3D40, 0xFF01, 0x3FC0, 0x3E80, 0xFE41,
            0xFA01, 0x3AC0, 0x3B80, 0xFB41, 0x3900, 0xF9C1, 0xF881, 0x3840, 0x2800, 0xE8C1, 0xE981, 0x2940, 0xEB01,
            0x2BC0, 0x2A80, 0xEA41, 0xEE01, 0x2EC0, 0x2F80, 0xEF41, 0x2D00, 0xEDC1, 0xEC81, 0x2C40, 0xE401, 0x24C0,
            0x2580, 0xE541, 0x2700, 0xE7C1, 0xE681, 0x2640, 0x2200, 0xE2C1, 0xE381, 0x2340, 0xE101, 0x21C0, 0x2080,
            0xE041, 0xA001, 0x60C0, 0x6180, 0xA141, 0x6300, 0xA3C1, 0xA281, 0x6240, 0x6600, 0xA6C1, 0xA781, 0x6740,
            0xA501, 0x65C0, 0x6480, 0xA441, 0x6C00, 0xACC1, 0xAD81, 0x6D40, 0xAF01, 0x6FC0, 0x6E80, 0xAE41, 0xAA01,
            0x6AC0, 0x6B80, 0xAB41, 0x6900, 0xA9C1, 0xA881, 0x6840, 0x7800, 0xB8C1, 0xB981, 0x7940, 0xBB01, 0x7BC0,
            0x7A80, 0xBA41, 0xBE01, 0x7EC0, 0x7F80, 0xBF41, 0x7D00, 0xBDC1, 0xBC81, 0x7C40, 0xB401, 0x74C0, 0x7580,
            0xB541, 0x7700, 0xB7C1, 0xB681, 0x7640, 0x7200, 0xB2C1, 0xB381, 0x7340, 0xB101, 0x71C0, 0x7080, 0xB041,
            0x5000, 0x90C1, 0x9181, 0x5140, 0x9301, 0x53C0, 0x5280, 0x9241, 0x9601, 0x56C0, 0x5780, 0x9741, 0x5500,
            0x95C1, 0x9481, 0x5440, 0x9C01, 0x5CC0, 0x5D80, 0x9D41, 0x5F00, 0x9FC1, 0x9E81, 0x5E40, 0x5A00, 0x9AC1,
            0x9B81, 0x5B40, 0x9901, 0x59C0, 0x5880, 0x9841, 0x8801, 0x48C0, 0x4980, 0x8941, 0x4B00, 0x8BC1, 0x8A81,
            0x4A40, 0x4E00, 0x8EC1, 0x8F81, 0x4F40, 0x8D01, 0x4DC0, 0x4C80, 0x8C41, 0x4400, 0x84C1, 0x8581, 0x4540,
            0x8701, 0x47C0, 0x4680, 0x8641, 0x8201, 0x42C0, 0x4380, 0x8341, 0x4100, 0x81C1, 0x8081, 0x4040
    };
}
