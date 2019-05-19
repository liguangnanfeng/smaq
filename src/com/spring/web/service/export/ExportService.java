/**  
* Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
* All right reserved. 
*/ 

package com.spring.web.service.export;

import com.spring.web.result.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/** 
 * @Title: exportService 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author FL   
 * @date 2017年7月25日 上午10:16:04 
 * @version V1.0   
 */
public interface ExportService extends Serializable {

    /** 
    * @param file
    * @param request
    * @param response 
    */
   public Result companyImport(MultipartFile file, Integer villageId, HttpServletRequest request) throws Exception;

    /** 
    * @param file
    * @param id
    * @param isType
     * @param isType2 
    * @param request
    * @return 
     * @throws Exception 
    */
    public Result tableImport(MultipartFile file, Integer userId, Integer id, Integer isType, HttpServletRequest request) throws Exception;

    /** 
    * @param file
    * @param id
    * @param id2
    * @param isType
    * @param request
    * @return 
    */
    public Result hImport(MultipartFile file, Integer id, Integer id2, Integer isType, HttpServletRequest request) throws Exception;

    /** 
    * @param file
    * @param id
    * @param request
    * @return 
    */
    public Result pImport(MultipartFile file, Integer userId, HttpServletRequest request)throws Exception;
}
