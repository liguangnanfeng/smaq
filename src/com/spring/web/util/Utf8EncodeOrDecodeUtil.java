package com.spring.web.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;

/**
 * @Title: Utf8EncodeOrDecodeUtil
 * @Description: UTF8转码工具类
 * @author FL
 * @date 2016年1月26日 上午11:49:51
 * @version V1.0
 */
public final class Utf8EncodeOrDecodeUtil {
    private static final Charset CHARSET = Charset.forName("UTF-8");

    /**
     * 编码
     * 
     * @param string
     * @return
     */
    public static byte[] encode(CharSequence string) {
        try {
            ByteBuffer bytes = CHARSET.newEncoder().encode(CharBuffer.wrap(string));
            byte[] bytesCopy = new byte[bytes.limit()];
            System.arraycopy(bytes.array(), 0, bytesCopy, 0, bytes.limit());
            return bytesCopy;
        } catch (CharacterCodingException e) {
            throw new IllegalArgumentException("Encoding failed", e);
        }
    }

    /**
     * 解码
     * 
     * @param bytes
     * @return
     */
    public static String decode(byte[] bytes) {
        try {
            return CHARSET.newDecoder().decode(ByteBuffer.wrap(bytes)).toString();
        } catch (CharacterCodingException e) {
            throw new IllegalArgumentException("Decoding failed", e);
        }
    }
}
