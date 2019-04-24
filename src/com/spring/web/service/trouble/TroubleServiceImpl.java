/**  
* Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
* All right reserved. 
*/ 

package com.spring.web.service.trouble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.spring.web.dao.TCheckItemMapper;
import com.spring.web.dao.TCheckMapper;
import com.spring.web.dao.TItemMapper;
import com.spring.web.model.TCheck;
import com.spring.web.result.Result;

/** 
 * @Title: TroubleServiceImpl 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author CGF   
 * @date 2017年9月4日 下午2:44:36 
 * @version V1.0   
 */
@Service(value="troubleService")
public class TroubleServiceImpl implements TroubleService {
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    
    private static final long serialVersionUID = -7071867241405150262L;
    @Autowired
    private TCheckMapper tCheckMapper;
    @Autowired
    private TItemMapper tItemMapper;
    @Autowired
    private TCheckItemMapper tCheckItemMapper;

    /** (非 Javadoc) 
     *  自查第一步 保存本次检查的分类
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = RuntimeException.class)
    public Result tCheckSave(TCheck tCheck, Result result) throws Exception {
//        tCheckMapper.insertSelective(tCheck);
//        List<TItem> list = tItemMapper.selectByLevelIds(tCheck.getLevelIds());
//        tCheckItemMapper.insertBath(list, tCheck.getId());
//        result.setMap("id", tCheck.getId());
        return result;
    }
}
