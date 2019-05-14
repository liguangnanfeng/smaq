package com.spring.web.dao;

import com.spring.web.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AppControllerMessageMapper {

    List<Messages>  findMessageList(@Param("userId") String userId, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    TCheck findCheckById(String checkId);

    Messages findMessageDetail(String id);

    List<Map>  findTCheckList(@Param("cid") String userId,@Param("pageNo") Integer pageNo,@Param("pageSize") Integer pageSize);

    ZzjgPersonnel findPersonnel(String userId);

    List<Map> findRectificationList(String checkId);

    TRectification findTRectification(String checkId);

    // 复查
    List<Map<String, Object>> selectRecheckByCheckId(@Param("checkId")Integer checkId);

    // 复查 （主界面进入）
    List<Map<String, Object>> selectRecheckByCheckId2(@Param("userId")Integer userId);

    // 政府端检查表
    List<Map> selectCheckByFlag();

}
