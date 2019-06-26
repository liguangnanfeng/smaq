package com.spring.web.controller;

import com.spring.web.BaseController;
import com.spring.web.model.TSafetyStandard;
import com.spring.web.model.User;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * TODO (要标注的类的作用)
 *
 * @author 桃红梨白
 * @Date 2019/6/26 16:02
 */
@Controller
@RequestMapping("api/safety_Standard")
public class TSafetyStandardController extends BaseController {

    private static final long serialVersionUID = 6784473267577273720L;

    /**
     * 保存插入数据
     *
     * @param tSafetyStandard
     * @return
     */
    @RequestMapping(value = "/save")
    public Result save(@RequestBody TSafetyStandard tSafetyStandard) {
        Result result = new ResultImpl();
        tSafetyStandardMapper.insertSelective(tSafetyStandard);

        result.setMess("插入成功");
        result.setStatus("0");
        result.setObject(tSafetyStandard.getId());
        return result;

    }

    /**
     * 根据条件查询安全生产标准化数据
     *
     * @param parendId 父级id
     * @param flag     1: A级  2: B级
     * @return
     */
    @RequestMapping(value = "/findAll")
    public String findAll(Integer parendId, Integer flag, HttpServletRequest request, Model model,Integer  sort) {
        User user = getLoginUser(request);
        if(null==sort){
            sort=2;
        }
        HashMap<String, Object> map = new HashMap<>();

        map.put("userId",user.getId());
        map.put("parentId",parendId);
        map.put("flag",flag);
        map.put("sort",sort);

        List<TSafetyStandard> TSafetyStandardlist = tSafetyStandardMapper.findAll(map);

        // 判断是否有顺序,有书序就按照顺序来,没有就是倒序
        model.addAttribute("sort",sort);
        model.addAttribute("list",TSafetyStandardlist);
        return "company/tables/tab-biaozhun2";

    }

}
