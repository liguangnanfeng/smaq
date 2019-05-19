package com.spring.web.service.CheckCompany;

import com.spring.web.model.ACompanyManual;
import com.spring.web.model.Officials;
import com.spring.web.model.ZzjgPersonnel;
import com.spring.web.model.request.CheckItem;
import com.spring.web.model.request.CheckLevel;
import com.spring.web.model.request.SaveDataMessageItem;
import com.spring.web.model.response.CheckItemS;
import com.spring.web.model.response.MeasuresBean;
import com.sun.javafx.collections.MappingChange;

import java.util.List;
import java.util.Map;

/**
 * @Author: 桃红梨白
 * @Date: 2019/04/25 11:52
 */
public interface ICheckManual {
    Map<String,List> selectDangerAndManual(Integer cid, List<String> names);

    List<Map> selectLevel3AndId(List<CheckLevel> checkLevel);

    /**
     * 根据levle1 level2 检查类型  查询level3
     * @param checkLevel
     * @return
     */
    List<Map<String,Object>> selectLevel4AndId(CheckLevel checkLevel);

    List<Map> selectLevel5AndId(CheckLevel checkLevel);

    /**
     * 保存自定义模版的信息
     * @param checkItem
     * @param zzjg
     * @return
     */
    Integer saveCheck(CheckItem checkItem,ZzjgPersonnel zzjg);

    List<Map<Integer, String>> findUserByIdAndStatus(ZzjgPersonnel zzjg);

    /**
     * 企业端查询所有模版
     * @param uid 公司id
     * @param dpName 部门名称
     * @return
     */
    List<Map<Integer, String>> findModelByUid(Integer uid,String dpName );

    /**
     * 政府端查询所有的模版
     * @param uid
     * @return
     */
    List<Map<Integer, String>> findCountryModelByUid(Integer uid);

    /**
     * 根据检查人员id查询部门对应的level2
     * @param personnelId
     * @param uId
     * @return
     */
    Map<String, List> findLevel2ByPersonnelId(Integer personnelId,Integer uId);

    /**
     * 获取所有的高危检查项
     * @return
     */
    List<Map> checkGaoWei(Integer Uid);

    /**
     * 基础检查 查询
     * @param uid
     */
    Map checkJiChu(Integer uid);

    /**
     * 获取高危的检查选项
     * @param industryId
     */
    Map checkGaoWeiItem(Integer industryId);

    /**
     * 基础 高危
     * 获取 level3  and level4
     * @param checkLevel
     */
    List checkGaoWeiAndJiChu(CheckLevel checkLevel);

    /**
     *  TODO 查询高危选项的level1
     * @param industryId
     */
    List<Map<String, Object>> checkGaoWei2(Integer industryId);
}
