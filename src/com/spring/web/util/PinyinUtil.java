package com.spring.web.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * @Title: PinyinUtil
 * @Description: 拼音工具类
 * @author FL
 * @date 2016年1月13日 下午3:04:05
 * @version V1.0
 */
public class PinyinUtil {
    private final static int[] li_SecPosValue = {1601, 1637, 1833, 2078, 2274, 2302, 2433, 2594, 2787, 3106, 3212,
            3472, 3635, 3722, 3730, 3858, 4027, 4086, 4390, 4558, 4684, 4925, 5249, 5590};
    private final static String[] lc_FirstLetter = {"a", "b", "c", "d", "e", "f", "g", "h", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "w", "x", "y", "z"};

    /**
     * 取得给定汉字串的首字母串,即声母串
     * 
     * @param str 给定汉字串
     * @return 声母串
     */
    public static String getAllFirstLetter(String str) {
        if (str == null || str.trim().length() == 0) {
            return "";
        }
        String _str = "";
        for (int i = 0; i < str.length(); i++) {
            _str = _str + getFirstLetter(str.substring(i, i + 1));
        }
        return _str;
    }

    /**
     * 取得给定汉字的首字母,即声母
     * 
     * @param chinese 给定的汉字
     * @return 给定汉字的声母
     */
    public static String getFirstLetter(String chinese) {
        if (chinese == null || chinese.trim().length() == 0) {
            return "";
        }
        chinese = conversionStr(chinese, "GBK", "ISO8859-1");
        if (chinese.length() > 1) { // 判断是不是汉字
            int li_SectorCode = (int) chinese.charAt(0); // 汉字区码
            int li_PositionCode = (int) chinese.charAt(1); // 汉字位码
            li_SectorCode = li_SectorCode - 160;
            li_PositionCode = li_PositionCode - 160;
            int li_SecPosCode = li_SectorCode * 100 + li_PositionCode; // 汉字区位码
            if (li_SecPosCode > 1600 && li_SecPosCode < 5590) {
                for (int i = 0; i < 23; i++) {
                    if (li_SecPosCode >= li_SecPosValue[i] && li_SecPosCode < li_SecPosValue[i + 1]) {
                        chinese = lc_FirstLetter[i];
                        break;
                    }
                }
            } else { // 非汉字字符,如图形符号或ASCII码
                chinese = conversionStr(chinese, "ISO8859-1", "GBK");
                chinese = chinese.substring(0, 1);
            }
        }
        return chinese;
    }

    /**
     * 字符串编码转换
     * 
     * @param str 要转换编码的字符串
     * @param charsetName 原来的编码
     * @param toCharsetName 转换后的编码
     * @return 经过编码转换后的字符串
     */
    private static String conversionStr(String str, String charsetName, String toCharsetName) {
        try {
            str = new String(str.getBytes(charsetName), toCharsetName);
        } catch (UnsupportedEncodingException ex) {
            System.out.println("字符串编码转换异常：" + ex.getMessage());
        }
        return str;
    }

    /**
     * 将汉字转换为全拼
     * 
     * @param src
     * @return String
     */
    public static String getPinYin(String src) {
        char[] t1 = null;
        t1 = src.toCharArray();
        // System.out.println(t1.length);
        String[] t2 = new String[t1.length];
        // System.out.println(t2.length);
        // 设置汉字拼音输出的格式
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                // System.out.println(t1[i]);
                if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);// 将汉字的几种全拼都存到t2数组中
                    t4 += t2[0];// 取出该汉字全拼的第一种读音并连接到字符串t4后
                } else {
                    // 如果不是汉字字符，直接取出字符并连接到字符串t4后
                    t4 += Character.toString(t1[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return t4;
    }

    /**
     * 提取每个汉字的首字母
     * 
     * @param str
     * @return String
     */
    public static String getPinYinHeadChar(String str) {
        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            // 提取汉字的首字母
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }

    /**
     * 将字符串转换成ASCII码
     * 
     * @param cnStr
     * @return String
     */
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        // 将字符串转换成字节序列
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            // System.out.println(Integer.toHexString(bGBK[i] & 0xff));
            // 将每个字符转换成ASCII码
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return strBuf.toString();
    }

    /**
     * 提取第一个汉字的第一大写字母
     * 
     * @param str
     * @return String
     */
    public static String getPinYinFirstHeadChar(String str) {
        String convert = "";
        for (int j = 0; j < str.length();) {
            char word = str.charAt(j);
            // 提取汉字的首字母
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
            convert = StringUtils.upperCase(convert);
            break;
        }
        return convert;
    }

    public static void main(String[] args) {
        String cnStr = "陈国富";
        // String cnStr = "wqeqw";
        System.out.println(getPinYin(cnStr));
        System.out.println(getPinYinFirstHeadChar(cnStr));
        System.out.println(getCnASCII(cnStr));
    }
}
