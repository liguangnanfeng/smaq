package com.spring.web.service;

import com.alibaba.fastjson.JSON;
import com.spring.web.dao.*;
import com.spring.web.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: 桃红梨白
 * @Date: 2019/05/10 20:35
 *保存方法的工具类
 */
@Service
@SuppressWarnings("all")
@Transactional
public class SaveModelService implements PCSaveModel {


    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private TIndustryMapper tIndustryMapper;

    @Autowired
    private ACompanyManualMapper aCompanyManualMapper;

    @Autowired
    private TLevelMapper tLevelMapper;

    @Autowired
    private TItemMapper tItemMapper;

    @Autowired
    private ZzjgDepartmentMapper zzjgDepartmentMapper;

    @Autowired
    private TModelMapper tModelMapper;

    @Autowired
    private TModelPartMapper tModelPartMapper;


    /**
     * 保存model数据到数据库并返回一个modelId
     * @return
     */
    public Integer saveModel(String title, Integer depId, String sName, String[] checkVal,
                             String cycle, String nextTime, String checkType, String checkNature ,
                             Integer userId){


        Integer tIndustryId = saveTIndustry(userId, checkNature);  //获取等级id

        // 创建model方法
        TModel model = new TModel();
        model.setTitle(title);   // 标题
        model.setUserId(userId); // 用户id
        model.setFlag(1); //自查
        model.setType(Integer.parseInt(checkType)); // 日常, 定期, 临时
        //根据部门id查询部门
        ZzjgDepartment zzjgDepartment = zzjgDepartmentMapper.selectByPrimaryKey(depId);
        model.setPart(zzjgDepartment.getName());

        // TODO 表示是定期检查 要确定检查时间
        if (null != cycle ) {
            int day = Integer.parseInt(cycle);
            model.setCycle(day); //定期时间
            Date d = new Date();
            long time = d.getTime();

            int i = day * 24 * 60 * 60 * 1000;
            long l = time + i;
            Date t = new Date(l);

            int a = (day - 1) * 24 * 60 * 60 * 1000;
            long b = time + a;
            Date c = new Date(b);
            model.setMemo("");      // 备注null
            model.setNextTime(c);     // 下次生成的时间
            model.setNextCheckTime(t); // 定期检查的时间
            model.setOpen(1);          // 定期生成
        }

        model.setCreateTime(new Date()); //创建时间
        model.setIndustryId(tIndustryId); // 被检查的行业id
        model.setIndustryType(2);         // TODO没有上传

        int i = tModelMapper.insertSelective(model);
        Integer ModelId = model.getId();   // 获取生成的id
        List tlevelList = saveTlevel(tIndustryId, checkVal);       // 获取企业的每一条检查项的等级的数据

        // 保存
        String s = JSON.toJSONString(tlevelList);

        //保存数据到数据库
        saveModelPath(ModelId,s,zzjgDepartment.getName());

        return ModelId;


    }


    /**
     * 根据用户的id查询公司的等级和规格 返回  行业id表
     * @return
     */
    public Integer saveTIndustry(Integer userId,String checkNature){
        Company company = companyMapper.selectByPrimaryKey(userId); // 获取公司的详细信息
        TIndustry industry = new TIndustry();

        industry.setName(company.getRegType()); //企业类型
        industry.setType(Integer.parseInt(checkNature)); //等级 基础 高危
        int i = tIndustryMapper.insertSelective(industry);
        return industry.getId();
    }

    /**
     * 保存
     * TLevel   自查级别分类表
     * TItem   检查项目表
     * TItemSerious  检查项目对应的问题表
     *
     * @return  list 自查级别分类表id集合
     * @param  industyId 行业id
     * @param checkVal  // 企业安全风险管控系统 -企业 危险因素辨识与防范
     *
     */

    public List saveTlevel(Integer industyId, String[] checkVal ){
        List<Integer> list = new ArrayList<>();

        for (String s : checkVal) {
            ACompanyManual companyManual = aCompanyManualMapper.selectByPrimaryKey(Integer.parseInt(s)); //获取所有的数据
            TLevel tLevel = new TLevel(); // 检查等级表
            TItem tItem = new TItem();  // 检查项目
            TItemSerious tItemSerious = new TItemSerious();
            String level3 = companyManual.getLevel3();
            if(level3!=null){
                String[] split = level3.split("/");

                tLevel.setLevel1(split[0]);
                tLevel.setLevel2(split[1]);
                tLevel.setLevel3(split[2]);
            }
            tLevel.setIndustryId(industyId);
            int i = tLevelMapper.insertSelective(tLevel);
            tItem.setLevelId(tLevel.getId()); // 等级id
            tItem.setReference(companyManual.getReference()); // 指导准则
            tItem.setContent(companyManual.getMeasures());                               // 查询内容
            int i1 = tItemMapper.insertSelective(tItem);

            tItemSerious.setKeywords(companyManual.getFactors()); //检查点出现问题
            tItemSerious.setLevelid(tLevel.getId());    //检查的等级id
            list.add(tLevel.getId());

        }

        return list ;
    }

    /**
     *
     * @param ModelId 模版id
     * @param tlevelList
     * @param name   部门名称
     */
    public void saveModelPath(Integer ModelId,String tlevelList,String name){
        TModelPart tModelPart = new TModelPart();
        tModelPart.setName(name); //检查部门只有一个
        tModelPart.setModelId(ModelId);
        tModelPart.setLevels(tlevelList);

        int tModelPartId = tModelPartMapper.insertSelective(tModelPart);

    }

}
