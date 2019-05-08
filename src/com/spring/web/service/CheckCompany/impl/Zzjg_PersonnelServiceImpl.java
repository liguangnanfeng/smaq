package com.spring.web.service.CheckCompany.impl;


import com.spring.web.dao.ZzjgPersonnelMapper;
import com.spring.web.model.ZzjgPersonnel;
import com.spring.web.service.CheckCompany.Zzjg_PersonnelService;
import com.spring.web.util.EncryptUtil;
import com.spring.web.util.MailUtil;
import com.spring.web.util.MyMD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author 桃红梨白
 * TODO 进行登陆
 * TODO 根据用户Id进行响应的数据的查询
 */
@Service
public class Zzjg_PersonnelServiceImpl implements Zzjg_PersonnelService {

    @Autowired
    private ZzjgPersonnelMapper zzjgPersonnelMapper;


    /**
     * 进行登陆的的验证工作
     * 使用工具类进行验证
     * @param username
     * @param password
     * @return
     */
    @Override
    public ZzjgPersonnel selectPersonnelByNameAndPwd(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        ZzjgPersonnel zzjgPersonnel = zzjgPersonnelMapper.selectPersonnelByNameAndPwd(username);

        if(null == zzjgPersonnel || "".equals(zzjgPersonnel.getPassword()) || zzjgPersonnel.getPassword()== null){
            // 表示没有查询的数据,并返回为空 表示没有数据
            return null;
        }
        // 1 : 加密后的密码   2: 未加密的密码
        boolean flag = EncryptUtil.match(zzjgPersonnel.getPassword(), password);

        if(flag){
            return zzjgPersonnel;
        }else{
            throw  new RuntimeException("密码错误");
        }

    }

    /**
     * 根据用户id查询对应的分公司的id
     * @param userId
     * @return
     */
    @Override
    public Integer selectCompanyIdByuserId(Integer userId) {

        ZzjgPersonnel zzjgPersonnel = zzjgPersonnelMapper.selectById(userId);
        return zzjgPersonnel.getCid();
    }
}
