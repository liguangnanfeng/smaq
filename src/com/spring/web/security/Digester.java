package com.spring.web.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Title: Digester
 * @Description:
 * @author FL
 * @date 2016年1月13日 下午6:28:30
 * @version V1.0
 */
final class Digester {
    private final MessageDigest messageDigest;
    private final int iterations;

    /**
     * Create a new Digester.
     * 
     * @param algorithm the digest algorithm; for example, "SHA-1" or "SHA-256".
     * @param iterations the number of times to apply the digest algorithm to the input
     */
    public Digester(String algorithm, int iterations) {
        try {
            messageDigest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No such hashing algorithm", e);
        }
        this.iterations = iterations;
    }

    public byte[] digest(byte[] value) {
        synchronized (messageDigest) {
            for (int i = 0; i < iterations; i++) {
                value = messageDigest.digest(value);
            }
            return value;
        }
    }
}
