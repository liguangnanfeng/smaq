package com.spring.web.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Title: LlExceptionHandler
 * @Description: 异常处理器
 * @author FL
 * @date 2016年1月26日 上午10:29:05
 * @version V1.0
 */
@Component
public class LlExceptionHandler implements HandlerExceptionResolver {
    @Autowired
    private MessageSource messageSource;
    private Logger log = Logger.getLogger(LlExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handle,
            Exception exception) {
        log.error("handle exception start...");
        if (!(request.getHeader("accept").indexOf("application/json") > -1)) {
            if (exception instanceof LlSystemException) {
                LlSystemException ex = (LlSystemException) exception;
                log.error(messageSource.getMessage(ex.getMessageId(), ex.getParams(), null));
                return new ModelAndView("error");
            } else if (exception instanceof LlSQLException) {
                LlSQLException ex = (LlSQLException) exception;
                log.error(messageSource.getMessage(ex.getMessageId(), ex.getParams(), null));
                return new ModelAndView("error");
            } else {
                log.error(this.getStackTrace(exception));
                return new ModelAndView("error");
            }
        } else {
            try {
                log.error(this.getStackTrace(exception));
                response.sendError(404);
            } catch (IOException e) {
                log.error(this.getStackTrace(e));
            }
            return new ModelAndView();
        }
    }

    /**
     * 获取异常信息。
     * 
     * @param aThrowable
     * @return 异常信息
     */
    private String getStackTrace(Throwable aThrowable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }
}
