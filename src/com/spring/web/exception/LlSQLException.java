package com.spring.web.exception;

import java.sql.SQLException;
import java.util.Collection;

/**
 * @Title: LlSQLException
 * @Description: SQL异常
 * @author FL
 * @date 2016年1月26日 上午10:29:51
 * @version V1.0
 */
public class LlSQLException extends SQLException {
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = -228940273905131641L;
    private String messageId;
    private String[] params;
    private Collection<?> messages;

    public LlSQLException(String messageId, String[] params) {
        this.messageId = messageId;
        if (params == null) {
            this.params = null;
        } else {
            this.params = new String[params.length];
            System.arraycopy(params, 0, this.params, 0, params.length);
        }
    }

    public LlSQLException(String messageId, String params) {
        this.messageId = messageId;
        this.params = (new String[]{params});
    }

    public LlSQLException(String messageId) {
        this.messageId = messageId;
    }

    public LlSQLException(Collection<?> messages) {
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
