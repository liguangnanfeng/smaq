package com.spring.web.security;

/**
 * @Title: SharedKeyGenerator
 * @Description: 共享KEY生成
 * @author FL
 * @date 2016年1月26日 上午11:44:54
 * @version V1.0
 */
final class SharedKeyGenerator implements BytesKeyGenerator {
    private byte[] sharedKey;

    public SharedKeyGenerator(byte[] sharedKey) {
        this.sharedKey = sharedKey;
    }

    public int getKeyLength() {
        return sharedKey.length;
    }

    public byte[] generateKey() {
        return sharedKey;
    }
}