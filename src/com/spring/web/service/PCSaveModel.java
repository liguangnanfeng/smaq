package com.spring.web.service;

import java.util.List;

/**
 * @Author: 桃红梨白
 * @Date: 2019/05/11 08:50
 */
public interface PCSaveModel {

    public Integer saveModel(String title, Integer depId, String sName, String[] checkVal,
                             String cycle, String nextTime, String checkType, String checkNature ,
                             Integer userId);

    public Integer saveTIndustry(Integer userId,String checkNature);

    public List saveTlevel(Integer industyId, String[] checkVal );

}
