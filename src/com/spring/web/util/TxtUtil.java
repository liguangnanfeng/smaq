package com.spring.web.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * @Title: TxtUtil
 * @Description: txt文件操作工具类
 * @author FL
 * @date 2016年1月13日 下午3:28:36
 * @version V1.0
 */
public class TxtUtil {
    private Logger log = Logger.getLogger(TxtUtil.class);
    /**
     * 路径
     */
    private String path;
    /**
     * 系统属性文件路径
     */
    private final String SYSTEM_PROPERTIES = "system.properties";

    public TxtUtil() {
    };

    /**
     * 指定路径生成TXT文件
     * 
     * @param fileName 待生成文件的名称
     */
    public TxtUtil(String fileName) {
        // 获取系统限制行数
        Properties prop = new Properties();
        try {
            prop.load(this.getClass().getClassLoader().getResourceAsStream(SYSTEM_PROPERTIES));
            path = prop.getProperty("txt_path");
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            path = path + fileName + ".txt";
        } catch (IOException e) {
            log.error(StackTraceUtil.getStackTrace(e));
        }
    }

    /**
     * TXT文件写入
     * 
     * @param content 待写入的内容
     */
    public void writeTxt(String content) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(content);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            log.error(StackTraceUtil.getStackTrace(e));
        }
    }
}
