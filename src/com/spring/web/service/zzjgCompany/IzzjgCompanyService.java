package com.spring.web.service.zzjgCompany;

import com.spring.web.ibatis.LlHashMap;
import com.spring.web.model.ZzjgCompany;
import com.spring.web.model.ZzjgDepartment;

import java.util.List;

/**
 * zzigCompanyService对应的接口
 */
public interface IzzjgCompanyService {

    List<ZzjgCompany> selectAll(Integer id);

    List<LlHashMap<Object, Object>> selectByMap(LlHashMap<Object, Object> m);

    List<ZzjgDepartment> selectByCompanyId(ZzjgDepartment dep);
}
