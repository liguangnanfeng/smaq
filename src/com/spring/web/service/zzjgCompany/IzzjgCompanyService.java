package com.spring.web.service.zzjgCompany;

import com.google.gson.JsonElement;
import com.spring.web.ibatis.LlHashMap;
import com.spring.web.model.User;
import com.spring.web.model.ZzjgCompany;
import com.spring.web.model.ZzjgDepartment;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * zzigCompanyService对应的接口
 */
public interface IzzjgCompanyService {

    List<ZzjgCompany> selectAll(Integer id);

    List<LlHashMap<Object, Object>> selectByMap(LlHashMap<Object, Object> m);

    List<ZzjgDepartment> selectByCompanyId(ZzjgDepartment dep);
}
