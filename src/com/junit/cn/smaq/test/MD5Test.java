package com.junit.cn.smaq.test;

import com.spring.web.util.MyMD5Util;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: 桃红梨白
 * @Date: 2019/04/27 21:41
 * MD5加密解密测试
 *
 * 8EC3E9FF327D6772D0E280EC2935C7F2DAC566F0D8433D26970C1278
 * 62065F54D05351EF36EEBA05C337A84F5B8CA0FB8A21CD3F37787960
 * 118A13946763A57CF2FCE6B1EA4B70FFE99929ECAC88D6809ECFB537
 * 72D22D68998B66D4125B824669BA2725D0A566E8DBF9203EC63BEB5A
 *
 * 56位的字符集
 */
public class MD5Test {

    @Test
    public void  Test01(){
        try {
            String encryptedPwd = MyMD5Util.getEncryptedPwd("123456");
            System.out.println(encryptedPwd);

            String encryptedPwd1 = MyMD5Util.getEncryptedPwd("123456");
            System.out.println(encryptedPwd1);

            int length = encryptedPwd.length();
            System.out.println(length);

        } catch (NoSuchAlgorithmException e) {
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解密
     */
    @Test
    public void  Test02() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        boolean b1 = MyMD5Util.validPassword("111222", "3f3d5b144e75f4203e26d4a955767f67f1edbda20dc125d4588a1cf7aa2469fa8c04f39a377feb05");
        boolean b2 = MyMD5Util.validPassword("123", "62065F54D05351EF36EEBA05C337A84F5B8CA0FB8A21CD3F37787960");
        boolean b3 = MyMD5Util.validPassword("123456", "118A13946763A57CF2FCE6B1EA4B70FFE99929ECAC88D6809ECFB537");
        boolean b4 = MyMD5Util.validPassword("123456", "9F1CD051AA0FF412826C8C840B05FFEDEC98478EEA5F702D0B98A11B");
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
        System.out.println(b4);
    }


}
