package com.spring.web.util;

/**
 * @Title: EncodingUtil
 * @Description: 转码工具类
 * @author FL
 * @date 2016年1月26日 上午11:30:56
 * @version V1.0
 */
public class EncodingUtil {
    /**
     * @param arrays
     * @return
     */
    public static byte[] concatenate(byte[]... arrays) {
        int length = 0;
        for (byte[] array : arrays) {
            length += array.length;
        }
        byte[] newArray = new byte[length];
        int destPos = 0;
        for (byte[] array : arrays) {
            System.arraycopy(array, 0, newArray, destPos, array.length);
            destPos += array.length;
        }
        return newArray;
    }

    /**
     * @param array
     * @param beginIndex
     * @param endIndex
     * @return
     */
    public static byte[] subArray(byte[] array, int beginIndex, int endIndex) {
        int length = endIndex - beginIndex;
        byte[] subarray = new byte[length];
        System.arraycopy(array, beginIndex, subarray, 0, length);
        return subarray;
    }

    private EncodingUtil() {
    }
}
