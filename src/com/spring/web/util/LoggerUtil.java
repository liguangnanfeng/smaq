package com.spring.web.util;

import org.apache.log4j.Logger;

/**
 * @Title: LoggerUtil
 * @Description:日志工具类
 * @author FL
 * @date 2017年7月10日 下午1:32:09
 * @version V1.0
 */
public class LoggerUtil {
    public static Logger cLog = Logger.getLogger("R");
    public static Logger sLog = Logger.getLogger("SQL");

    public static boolean isDebuggedC() {
        return cLog.isDebugEnabled();
    }

    public static boolean isInfoC() {
        return cLog.isInfoEnabled();
    }

    public static boolean isDebuggedS() {
        return sLog.isDebugEnabled();
    }

    public static boolean isInfoS() {
        return sLog.isInfoEnabled();
    }

    public static <T> Logger log(Class<T> t) {
        return Logger.getLogger(t);
    }
}
