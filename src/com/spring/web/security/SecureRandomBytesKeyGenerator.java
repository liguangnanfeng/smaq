package com.spring.web.security;

import java.security.SecureRandom;

/**
 * @Title: SecureRandomBytesKeyGenerator
 * @Description: 安全随机字节KEY生成
 * @author FL
 * @date 2016年1月26日 上午11:43:41
 * @version V1.0
 */
final class SecureRandomBytesKeyGenerator implements BytesKeyGenerator {
    private final SecureRandom random;
    private final int keyLength;

    /**
     * Creates a secure random key generator using the defaults.
     */
    public SecureRandomBytesKeyGenerator() {
        this(DEFAULT_KEY_LENGTH);
    }

    /**
     * Creates a secure random key generator with a custom key length.
     */
    public SecureRandomBytesKeyGenerator(int keyLength) {
        this.random = new SecureRandom();
        this.keyLength = keyLength;
    }

    public int getKeyLength() {
        return keyLength;
    }

    public byte[] generateKey() {
        byte[] bytes = new byte[keyLength];
        random.nextBytes(bytes);
        return bytes;
    }

    private static final int DEFAULT_KEY_LENGTH = 8;
}
