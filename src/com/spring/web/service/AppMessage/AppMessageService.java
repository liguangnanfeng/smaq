package com.spring.web.service.AppMessage;

import com.spring.web.model.*;

import java.util.List;
import java.util.Map;

public interface AppMessageService {

    List<Messages> findMessageList(String userId,Integer pageNo,Integer pageSize);

    Messages findMessageDetail(String id);

    // 检查表，同一个企业可见
    List<TCheck>  findTCheckList(String cid,Integer pageNo,Integer pageSize);

    // 根据checkId获取单条检查记录
    TCheck findCheckById(String checkId);

    // 根据员工id查询所属部门，公司
    ZzjgPersonnel findPersonnel(String userId);

    // 根据checkId获取整改意见详情列表
    List<Map> findRectificationList(String checkId);


    // 根据checkId获取整改意见时限
    TRectification findTRectification(String checkId);

}
