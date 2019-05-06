package com.spring.web.service.CheckCompany;

import com.spring.web.model.TCheckItem;
import com.spring.web.model.ZzjgPersonnel;
import com.spring.web.model.request.SaveDataMessage;
import com.spring.web.model.request.SaveDataMessageItem;

import java.util.List;
import java.util.Map;

/**
 * @Author: 桃红梨白
 * @Date: 2019/05/05 18:32
 * 保存合格不合格信息,并进行复查的分发
 */
public interface SaveMessageService {
    /**
     * 保存检查结果
     * @param saveDataMessageItem
     * @param zzjg
     * @return
     */
    String saveCheckMessage(SaveDataMessageItem saveDataMessageItem, ZzjgPersonnel zzjg);

    /**
     * 根据checkId获取不合格项
     * @param checkId
     * @return
     */
    List findItemByCheckId(Integer checkId);

    /**
     * 很据复查信息进行保存
     * @param saveDataMessageItem
     * @param zzjg
     * @return
     */
    String saveReviewData(SaveDataMessageItem saveDataMessageItem, ZzjgPersonnel zzjg);

    /**
     * 根据当前的登陆用户,查询检查表
     * @param zzjg
     */
    List<Map> findCheckItemById(ZzjgPersonnel zzjg);
}
