package com.spring.web.service.user;

import com.spring.web.model.TCleanWarnSetting;
import com.spring.web.model.User;
import com.spring.web.result.Result;
import com.spring.web.tobject.cgf.CompanyListReqDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

/**
 * @Title: UserService
 * @Description: 用户服务接口类
 * @author FL
 * @date 2016年1月21日 上午10:49:04
 * @version V1.0
 */
public interface UserService extends Serializable {

    public void registar(User user, Result result, User user_d, Integer villageId) throws Exception;
    
    public void companyImportSave(Result result, MultipartFile file, Integer villageId) throws Exception;
    
    public void spersonImportSave(Result result, Integer flag, MultipartFile file) throws Exception;
    
    public void spersonImportSaveForClique(Result result, Integer flag, MultipartFile file) throws Exception;

    public void sequipmentImportSave(Result result, MultipartFile file) throws Exception;
    
    public void sequipmentImportSaveForClique(Result result, MultipartFile file) throws Exception;
    
    public void editName(User user, Result result, User user2, Integer id, String userName) throws Exception;
    
    public List<Integer> selectCount(CompanyListReqDTO dto, User user);

    //myadd 获取用户设置的时间间隔
    public TCleanWarnSetting geTCleanWarnSetting(Integer creator);
    
    //myadd 修改时间间隔
    public boolean updateCleanWarnSetting(TCleanWarnSetting setting);
    
    /**
     * (非 Javadoc) 批量导入特种设备
     */
    public void importSequipmentExcel(Result result, Integer userId, MultipartFile file) throws Exception;
    
    /**
     * (非 Javadoc) 批量导入主要设备
     */
    public void importMajorequipmentExcel(Result result, Integer userId, MultipartFile file) throws Exception;

    /*
    * 批量导入人员！！！
    * */
    public void importPersonExcel(Result result, Integer userId, MultipartFile file) throws Exception;
}
