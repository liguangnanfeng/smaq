package com.spring.web.exception;

import java.util.Collection;
import com.spring.web.result.Result;

/**
 * @Title: LlApplicationException
 * @Description: 应用异常
 * @author FL
 * @date 2016年1月26日 上午10:24:57
 * @version V1.0
 */
public class LlApplicationException extends Exception {
    /**
     * @Fields serialVersionUID : 序列ID
     */
    private static final long serialVersionUID = -228940273905131641L;
    private String messageId;
    private String[] params;
    private Collection<?> messages;
    private Result result;

    public LlApplicationException(String messageId, String[] params) {
        this.messageId = messageId;
        if (params == null) {
            this.params = null;
        } else {
            this.params = new String[params.length];
            System.arraycopy(params, 0, this.params, 0, params.length);
        }
    }

    public LlApplicationException(String messageId, String params) {
        this.messageId = messageId;
        this.params = (new String[]{params});
    }

    public LlApplicationException(String messageId) {
        this.messageId = messageId;
    }

    public LlApplicationException(Collection<?> messages) {
        this.messages = messages;
    }

    public LlApplicationException(Result result) {
        this.result = result;
    }

    public String getMessageId() {
        return messageId;
    }

    public Result getResult() {
        return this.result;
    }

    public String[] getParams() {
        if (params == null) {
            return null;
        } else {
            String[] results = new String[params.length];
            System.arraycopy(params, 0, results, 0, params.length);
            return results;
        }
    }

    public Collection<?> getMessages() {
        return messages;
    }
}
