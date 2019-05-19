package com.spring.web.model.request;

import lombok.Data;

import java.util.List;

/**
 * @Author: 桃红梨白
 * @Date: 2019/04/27 17:01
 * 组合实体类
 * 封装前端传递过来的模版表信息
 */
@Data
public class CheckItem {

    /**公司id*/
    public Integer uid;


    public String template;


    /**检查危险程度  定期检查,日常检查 随机检查*/
    public Integer title;

    /**检查类型    现场 危险   */
    public Integer checkType;


    /**检查时间*/
    public Integer cycle;


    /**对应的负责人id*/
<<<<<<< .mine



=======
    /**
     * 对应的负责人id
     */
>>>>>>> .theirs
    public Integer departmentId;

    /**sessionId*/
    public String SessionId;



    public String access_token;

    /**封装的危险点信息*/
    public List<CheckLevel> checkLevels;

    public Integer getId() {
        return id;
}

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Integer getTitle() {
        return title;
    }

    public void setTitle(Integer title) {
        this.title = title;
    }

    public Integer getCheckType() {
        return checkType;
    }

    public void setCheckType(Integer checkType) {
        this.checkType = checkType;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getSessionId() {
        return SessionId;
    }

    public void setSessionId(String sessionId) {
        SessionId = sessionId;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public List<CheckLevel> getCheckLevels() {
        return checkLevels;
    }

    public void setCheckLevels(List<CheckLevel> checkLevels) {
        this.checkLevels = checkLevels;
    }
}
