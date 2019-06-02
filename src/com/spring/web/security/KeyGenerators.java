package com.spring.web.security;

import java.security.SecureRandom;

/**
 * @Title: KeyGenerators
 * @Description: KEY生成
 * @author FL
 * @date 2016年1月26日 上午11:41:19
 * @version V1.0
 */
public class KeyGenerators {
    /**
     * Create a {@link BytesKeyGenerator} that uses a {@link SecureRandom} to generate keys of 8 bytes in length.
     */
    public static BytesKeyGenerator secureRandom() {
        return new SecureRandomBytesKeyGenerator();
    }

    /**
     * @param keyLength
     * @return
     */
    public static BytesKeyGenerator secureRandom(int keyLength) {
        return new SecureRandomBytesKeyGenerator(keyLength);
    }

    /**
     * @param keyLength
     * @return
     */
    public static BytesKeyGenerator shared(int keyLength) {
        return new SharedKeyGenerator(secureRandom(keyLength).generateKey());
    }

    /**
     * Creates a {@link StringKeyGenerator} that hex-encodes {@link SecureRandom} keys of 8 bytes in length. The hex-encoded string is keyLength * 2 characters in length.
     */
    public static StringKeyGenerator string() {
        return new HexEncodingStringKeyGenerator(secureRandom());
    }

    // internal helpers
    private KeyGenerators() {
    }
}
