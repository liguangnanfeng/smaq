package com.spring.web.security;

/**
 * @Title: BytesKeyGenerator
 * @Description: 字节KEY生产
 * @author FL
 * @date 2016年1月26日 上午11:26:41
 * @version V1.0
 */
public interface BytesKeyGenerator {
    /**
     * Get the length, in bytes, of keys created by this generator. Most unique keys are at least 8 bytes in length.
     */
    int getKeyLength();

    /**
     * Generate a new key.
     */
    byte[] generateKey();

}
