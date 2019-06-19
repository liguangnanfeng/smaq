/*
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.result;

import com.spring.web.tobject.DTO;

import java.util.List;
import java.util.Map;

/**
 * @Title: Result
 * @Description: 结果接口类
 * @author FL
 * @date 2014年6月22日 上午4:00:36
 * @version V1.0
 */
public interface Result {
    public List<Mess> getList();

    public void setList(Mess mess);

    public DTO getResponseDTO();

    public void setResponseDTO(DTO dto);

    public String getStatus();

    public void setStatus(String status);

    public Map<String, String[]> getMessage();

    public void setMessage(String s, String[] parma);

    public List<Message> getMessageList();

    public void setMessageList(Message message);

    public DTO getDto();

    public void setDto(DTO dto);

    public Map<String, Object> getMap();

    public void setMap(String string, Object obj);

    public Object getObject();

    public void setObject(Object obj);
    
    public String getMess();
    
    public void setMess(String message);
}