package com.spring.web.schedule;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.spring.web.dao.TModelMapper;
import com.spring.web.model.TCheck;
import com.spring.web.model.TModel;
import com.spring.web.service.cgf.CgfService;
import com.spring.web.util.DateConvertUtil;

/**
 * 定时任务 => 主要是做企业自动检查表,
 */
@Component
public class Scheduled {
    @Autowired
    private CgfService cgfService;
    @Autowired
    private TModelMapper tModelMapper;

    /*
     * 时间大小由小到大排列，从秒开始，顺序为 秒，分，时，天，月，年 *为任意 ？为无限制。 具体如下： "0/10 * * * * ?" 每10秒触发 "0 0 12 * * ?" 每天中午12点触发 "0 15 10 ? * *" 每天上午10:15触发 "0 15 10 * * ?" 每天上午10:15触发 "0 15 10 * * ? *"
     * 每天上午10:15触发 "0 15 10 * * ? 2005" 2005年的每天上午10:15触发 "0 * 14 * * ?" 在每天下午2点到下午2:59期间的每1分钟触发 "0 0/5 14 * * ?" 在每天下午2点到下午2:55期间的每5分钟触发 "0 0/5 14,18 * * ?"
     * 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发 "0 0-5 14 * * ?" 在每天下午2点到下午2:05期间的每1分钟触发 "0 10,44 14 ? 3 WED" 每年三月的星期三的下午2:10和2:44触发 "0 15 10 ? * MON-FRI" 周一至周五的上午10:15触发 "0 15 10 15 * ?"
     * 每月15日上午10:15触发 "0 15 10 L * ?" 每月最后一日的上午10:15触发 "0 15 10 ? * 6L" 每月的最后一个星期五上午10:15触发 "0 15 10 ? * 6L 2002-2005" 2002年至2005年的每月的最后一个星期五上午10:15触发 "0 15 10 ? * 6#3"
     * 每月的第三个星期五上午10:15触发
     */
    /**
     * 定时器
     *  早上1点启动 自动创建企业自查检查表
     * @throws Exception
     */
    @org.springframework.scheduling.annotation.Scheduled(cron = "0 0 1 * * *")
    public void doUpdateIndexData() {
        List<TModel> list = tModelMapper.selectAuto();
        for(TModel model : list) {
            TCheck check = new TCheck();
            
            check.setFlag(model.getFlag());
            check.setDel(0);
            check.setCreateTime(new Date());
            
            check.setTitle(model.getTitle());
            check.setDepart(model.getPart());
            check.setUserId(model.getUserId());
            check.setCreateUser(model.getUserId());
            check.setModelId(model.getId());
            check.setType(model.getType());
            check.setIndustryId(model.getIndustryId());
            check.setIndustryType(model.getIndustryType());
            check.setExpectTime(model.getNextTime());
            try {
                cgfService.checkSave(check);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            model.setNextTime(model.getNextCheckTime());
            model.setUseTime(check.getCreateTime());
            model.setNextCheckTime(DateConvertUtil.addDays(model.getNextCheckTime(), model.getCycle()));
            tModelMapper.updateByPrimaryKeySelective(model);
        }
    }
    
}
