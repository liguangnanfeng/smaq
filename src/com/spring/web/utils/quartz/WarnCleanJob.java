

package com.spring.web.utils.quartz;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import com.spring.web.dao.TCleanWarnSettingMapper;
import com.spring.web.dao.TWarnmsgMapper;
import com.spring.web.model.TCleanWarnSetting;


public class WarnCleanJob implements Job{

    @Autowired
    private TWarnmsgMapper tWarnmsgMapper;
    @Autowired
    private TCleanWarnSettingMapper tCleanWarnSettingMapper;
    
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this); 
        TCleanWarnSetting setting = null;
        List<TCleanWarnSetting> settings = tCleanWarnSettingMapper.getAllCleanSettings();
        if(null != settings && !settings.isEmpty()){
            setting = settings.get(0);
        }
        if(null != setting){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(calendar.MONTH, -setting.getMaxTimes());
            tWarnmsgMapper.updateCleanTimeByMonth(calendar.getTime());
        }
    }
    
}
