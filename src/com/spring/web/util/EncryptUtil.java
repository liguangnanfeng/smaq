package com.spring.web.util;

import com.spring.web.security.StandardPasswordEncoder;

import java.util.UUID;

/**
 * @Title: EncryptUtil
 * @Description: 加密工具类
 * @author FL
 * @date 2016年1月12日 下午4:47:09
 * @version V1.0
 */

public class EncryptUtil {
    private static final StandardPasswordEncoder encoder = new StandardPasswordEncoder();

    /**
     * 加密
     * 
     * @param rawPassword 待加密的密码
     * @return
     */
    public static String encrypt(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    /**
     * 验证加密结果
     * 
     * @param encodedPassword 加密后的密码
     * @param rawPassword 未加密的密码
     * @return
     */
    public static boolean match(String encodedPassword, String rawPassword) {
        return encoder.matches(encodedPassword, rawPassword);
    }

    public static void main(String[] args) throws Exception {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid.length());
        System.out.println(uuid.replace("-", ""));
        System.out.println("Successfully loaded XPath factory. Things look good.");
        System.out.println(EncryptUtil.encrypt("123456"));
        System.out.println(EncryptUtil.match(
                "37dcd8eb9d6a571598bc592f410426fe2ee0a2e81ff0296345336a578dcb5a9d8bb20a167e1fca60", "123456"));
    }
}