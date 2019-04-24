package com.spring.web.result;

/**
 * @Title: Message
 * @Description: message信息实体
 * @author FL
 * @date 2016年1月26日 上午10:32:24
 * @version V1.0
 */
public class Message {
    private String messageId;
    private String[] params;

    public Message(String messageId, String[] params) {
        this.messageId = messageId;
        this.params = params;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }
}
