package com.spring.web.exception;

import java.util.Collection;

/**
 * @Title: LlSystemException
 * @Description: 系统异常
 * @author FL
 * @date 2016年1月26日 上午10:30:36
 * @version V1.0
 */
public class LlSystemException extends Exception {
    /**
     * @Fields serialVersionUID : 序列ID
     */
    private static final long serialVersionUID = -228940273905131641L;
    private String messageId;
    private String[] params;
    private Collection<?> messages;

    public LlSystemException(String messageId, String[] params) {
        this.messageId = messageId;
        if (params == null) {
            this.params = null;
        } else {
            this.params = new String[params.length];
            System.arraycopy(params, 0, this.params, 0, params.length);
        }
    }

    public LlSystemException(String messageId, String params) {
        this.messageId = messageId;
        this.params = (new String[]{params});
    }

    public LlSystemException(String messageId) {
        this.messageId = messageId;
    }

    public LlSystemException(Collection<?> messages) {
        this.messages = messages;
    }

    public String getMessageId() {
        return messageId;
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
