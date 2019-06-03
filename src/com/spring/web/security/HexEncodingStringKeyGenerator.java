package com.spring.web.security;

/**
 * @Title: HexEncodingStringKeyGenerator
 * @Description: Intel Hex编码字符KEY生产
 * @author FL
 * @date 2016年1月26日 上午11:40:13
 * @version V1.0
 */
final class HexEncodingStringKeyGenerator implements StringKeyGenerator {
    private final BytesKeyGenerator keyGenerator;

    public HexEncodingStringKeyGenerator(BytesKeyGenerator keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

    public String generateKey() {
        return new String(Hex.encode(keyGenerator.generateKey()));
    }
}
