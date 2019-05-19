package com.spring.web.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.Date;
import java.util.Properties;

/**
 * @Title: SqlBackUpUtil
 * @Description: 数据库备份工具类
 * @author FL
 * @date 2016年1月13日 下午3:46:23
 * @version V1.0
 */
public class SqlBackUpUtil {
    /**
     * 系统属性文件路径
     */
    private static final String SYSTEM_PROPERTIES = "system.properties";
    private static final Logger log = Logger.getLogger(SqlBackUpUtil.class);

    public static void backup(String address, String baseName, String user, String psw, String file) {
        Properties prop = new Properties();
        Process pc = null;
        try {
            prop.load(SqlBackUpUtil.class.getClassLoader().getResourceAsStream(SYSTEM_PROPERTIES));
            // String mysqlpaths = prop.getProperty("mysql_path");
            String path = prop.getProperty("sql_backup_path");
            file = (file == null || file.equals("")) ? path : file;
            File dirPath = new File(file);
            if (!dirPath.exists()) {
                dirPath.mkdirs();
            }
            String time = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
            String filepath = file.substring(file.length() - 1, file.length()).equals("/") ? (file + time + ".sql")
                    : (file + "/" + time + ".sql"); // 备份文件名
            StringBuffer stmt1 = new StringBuffer();
            // stmt1.append(mysqlpaths);
            stmt1.append("c:\\mysqldump ");
            stmt1.append("-u").append(user).append(" -p").append(psw).append(" -h").append(address).append(" ")
                    .append(" --opt ").append(baseName).append(" --default-character-set=utf8");
            pc = Runtime.getRuntime().exec("cmd /c" + stmt1 + ">" + filepath);
        } catch (Exception e) {
            log.error(StackTraceUtil.getStackTrace(e));
            if (pc != null) {
                pc.destroy();
            }
        }
    }

    public static void main(String[] args) {
        backup("localhost", "datastar", "root", "root", "");
    }
}
