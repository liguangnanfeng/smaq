/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.spring.web.tobject.DTO;

/**
 * @Title: ResultImpl
 * @Description: 结果接口实现类
 * @author FL
 * @date 2014年6月22日 上午4:01:13
 * @version V1.0
 */
public class ResultImpl implements Result {
    private DTO dto;
    private Object object;
    private Map<String, Object> map;
    private Map<String, String[]> message = new LinkedHashMap<String, String[]>();
    private List<Message> messageList = new ArrayList<Message>();
    private String status = "0";
    private List<Mess> messList = new ArrayList<Mess>();
    private String mess = "SUCCESS";



    /** 
     * @return mess 
     */
    public String getMess() {
        return mess;
    }

    /** 
     * @param mess 要设置的 mess 
     */
    public void setMess(String mess) {
        this.mess = mess;
    }

    public void setResponseDTO(DTO dto) {
        this.dto = dto;
    }

    public DTO getResponseDTO() {
        return this.dto;
    }

    public void setMessage(String s, String[] parma) {
        this.message.put(s, parma);
    }

    public Map<String, String[]> getMessage() {
        return message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public DTO getDto() {
        return dto;
    }

    public void setDto(DTO dto) {
        this.dto = dto;
    }

    public void setList(Mess mess) {
        if (this.messList == null) {
            this.messList = new ArrayList<Mess>();
        }
        this.messList.add(mess);
    }

    public List<Mess> getList() {
        return this.messList;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(Message message) {
        this.messageList.add(message);
    }

    public void setMap(String string, Object obj) {
        if (null == map)
            map = new HashMap<String, Object>();
        this.map.put(string, obj);
    }

    public Map<String, Object> getMap() {
        return map;
    }
}