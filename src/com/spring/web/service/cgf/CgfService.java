/**  
 * Copyright(c)2013 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */

package com.spring.web.service.cgf;

import com.spring.web.model.*;
import com.spring.web.result.AppResult;
import com.spring.web.result.Result;
import com.spring.web.tobject.cgf.*;
import org.springframework.ui.Model;

import java.io.Serializable;
import java.util.List;

public interface CgfService extends Serializable {
    
    /** 
     * @param dto 行政 委托检查表保存
     * @param result
     * @throws Exception 
     */
    public void checkSaveDo(CheckSaveReqDTO dto) throws Exception;

    /** 
    * @param dto 自查计划保存
    * @param result
    * @throws Exception 
    */
    public void modelSave(ModelSaveReqDTO dto, Result result) throws Exception;
    
    /** 
    * @param check 检查表保存
    * @param result
    * @throws Exception 
    */
    public void checkSave(TCheck check) throws Exception;
    
    /** 
     * @param check 检查表保存
     * @param result
     * @throws Exception 
     */
     public void checkSave1(TCheck check) throws Exception;//add by zhangcl 2018.10.28
    
    /** 
     * @param check 检查表自动化设置保存
     * @param result
     * @throws Exception 
     */
    public void modelAuto(TModel model, Result result) throws Exception;
    
    /** 
     * @param check 检查检查保存
     * @param result
     * @throws Exception 
     */
    //public void checkNestSave(TCheck t, Integer[] partIds, Integer[] statusp,Integer[] suggest, String[] time, Result result) throws Exception;
   
    public void checkNestSave(TCheck t) throws Exception;
    
    /** 
    * @param tr 整改意见保存
    * @throws Exception 
    */
    public void rectificationSave(TRectification tr) throws Exception;

    /** 
    * @param tr 复查意见保存
    * @throws Exception 
    */
    public void recheckSave(RecheckSaveReqDTO dto) throws Exception;
    
    /** 
     * @param tr 委托排查保存
     * @throws Exception 
     */
    public void threeSave(ThreeSaveReqDTO dto) throws Exception;
    
    /** 
    * @param 保存村
    */
    public void villageSave(Integer region, Village village, String vName, String vPsw, String jw, Result result) throws Exception;

    /**
     * @param 保存镇
     */
    public void townSave(Town town, String tName, String tPsw, String jw, String villageids, Result result) throws Exception;

    /**
     * @param 保存区县
     */
    public void districtSave(District d, String tName, String tPsw, String jw, String villageids, Result result) throws Exception;

    /**
     * @param 保存行业
     */
    public void tradeSave(Integer userId, String userName, String tradeName, String userPsw, String companyArea, Result result) throws Exception;

    /**
     * @param 排查治理记录添加
     */
    public void checkListAdd(Integer _userId, TCheck tCheck, String items, AppResult result) throws Exception;

    /**
    * @param 现场记录保存
    */
    public Integer checkDocumentSave8(Integer checkId, Integer userId, String copy, Integer flag2, String itms, Integer loginUserId, String checkCompany) throws Exception;

    /**
    * @param p
    * @param ids 推送消息保存
    * @throws Exception
    */
    public void pushMessageSave(PushMessage p, String ids) throws Exception;

    /**
    * @param id
    * @throws Exception 检查表删除
    */
    public void checkDel(Integer id) throws Exception;

    /**
    * @param dto 企业查询分页
    * @param model
    */
    public void selectCompanyWithPage(CompanyListReqDTO dto, User user, Model model);

    /**
    * @param dto
    * @param object
    * @return
    */
    public List<Integer> getCompanyUserIds(CompanyListReqDTO dto, User user);

    public void naturaldangerSave(NaturaldangerSaveReqDTO dto) throws Exception;

	public void targetAssessmentSave(FcTargetAssessment fcTargetAssessment, User user)throws Exception;

	public void fcTemporaryDangerCensusSave(FcTemporaryDangerCensus fcTemporaryDangerCensus, User user)throws Exception;

	public void greatdangerSave(GreatdangerSaveReqDTO dto) throws Exception;

	public void temporarydangerSave(TemporaryDangerSaveReqDTO dto) throws Exception;

	public void monitorUpdate(String puids, String ids, String names, Integer userId)throws Exception;
    
}
