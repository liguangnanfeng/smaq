package com.spring.web.controller;

import com.spring.web.BaseController;
import com.spring.web.dao.TSafetyMapper;
import com.spring.web.model.*;
import com.spring.web.result.AppResult;
import com.spring.web.result.AppResultImpl;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * TODO (安全生产标准话实体类)
 *
 * @author 桃红梨白
 * @Date 2019/6/26 16:02
 */
@Controller
@RequestMapping("api/safety_Standard")
public class TSafetyStandardController extends BaseController {

    private static final long serialVersionUID = 6784473267577273720L;

    @Autowired
    private TSafetyMapper tSafetyMapper;

    /**
     * 根据条件查询安全生产标准化数据
     *
     * @param parentId 父级id
     * @param flag     1: A级  2: B级
     * @return
     */
    @RequestMapping(value = "/findAll")
    public String findAll(Integer parentId, Integer flag, HttpServletRequest request, Model model, Integer sort) {
        User user = getLoginUser(request);
        if (null == sort) {
            sort = 1;
        }
        HashMap<String, Object> map = new HashMap<>();

        map.put("userId", user.getId());
        map.put("parentId", parentId);
        map.put("flag", flag);

        List<TSafetyStandard> TSafetyStandardlist = tSafetyStandardMapper.findAll(map);

        // 判断是否有顺序,有书序就按照顺序来,没有就是倒序
        model.addAttribute("sort", sort);
        model.addAttribute("companyName", user.getUserName());
        model.addAttribute("list", TSafetyStandardlist);
         return "company/tables/tab-biaozhun2";

    }

    /**
     * 根据A级元素id查询B级元素
     *
     * @return
     */
    @RequestMapping(value = "/findByParentId")
    public String findByParentId(Integer safetyStandardlistId, Model model, Integer sort) {
        if (null == sort) {
            sort = 1;
        }
        List<TSafetyStandard> TSafetyStandard = tSafetyStandardMapper.findByparentId(safetyStandardlistId, sort);
        model.addAttribute("list", TSafetyStandard);
        model.addAttribute("parentId", safetyStandardlistId);
        model.addAttribute("sort", sort);
        return "company/tables/tab-biaozhunB";
    }

    /**
     * 根据 id查询项目信息 并调转动修改的页面
     * 普通的跳转页面,没有请求
     *
     * @param safetyStandardlistId
     * @return
     */
    @RequestMapping(value = "/findOne")
    public String findOne(Integer safetyStandardlistId, Model model, HttpServletRequest request) {

        TSafetyStandard TSafetyStandard = tSafetyStandardMapper.findOne(safetyStandardlistId);
        model.addAttribute("item", TSafetyStandard);
        return "company/tables/tab-detail";
    }

    /**
     * 根据id查询详细信息并返回页面
     *
     * @param safetyStandardlistId @id
     * @param model                model
     * @return html
     */

    @RequestMapping(value = "/tab-biaozhunC")
    public String findOneTwo(Integer safetyStandardlistId, Integer sort, Model model, HttpServletRequest request) {
        if (null == sort) {
            sort = 1;
        }
        List<TSafetyStandard> tSafetyStandard = tSafetyStandardMapper.findByparentId(safetyStandardlistId, sort);
        TSafetyStandard one = tSafetyStandardMapper.findOne(safetyStandardlistId);
        model.addAttribute("fuId", one.getParentId());
        model.addAttribute("list", tSafetyStandard);
        model.addAttribute("parentId", safetyStandardlistId);
        model.addAttribute("sort", sort);
        return "company/tables/tab-biaozhunC";
    }

    /**
     * 保存插入数据
     *
     * @param tSafetyStandard
     * @return
     */
    @RequestMapping(value = "/save")
    public @ResponseBody
    Result save(@RequestBody TSafetyStandard tSafetyStandard, HttpServletRequest request) {
        Result result = new ResultImpl();
        User loginUser = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(loginUser.getId());
        try {

            String industry = company.getIndustry();
            if (industry.indexOf("化工") != -1) {
                tSafetyStandard.setIndustryId(1);
            } else {
                tSafetyStandard.setIndustryId(2);
            }
            tSafetyStandard.setUserId(loginUser.getId());
            if (tSafetyStandard.getParentId() == 0) {
                tSafetyStandard.setFlag(1);
            } else {
                tSafetyStandard.setFlag(2);
            }

            if (null == tSafetyStandard.getOder()) {
                tSafetyStandard.setOder(0);
            }
            tSafetyStandard.setDel(0);
            tSafetyStandardMapper.insertSelective(tSafetyStandard);

            result.setMess("保存成功");
            result.setStatus("0");
            result.setObject(tSafetyStandard.getId());
            return result;
        } catch (Exception e) {
            result.setMess("保存失败");
            result.setStatus("1");
            result.setObject(tSafetyStandard.getId());
            return result;
        }

    }

    /**
     * 修改数据
     *
     * @param tSafetyStandard
     * @return
     */
    @RequestMapping(value = "/update-tSafetyStandard", method = RequestMethod.POST)
    public @ResponseBody
    Result updateTSafetyStandard(@RequestBody TSafetyStandard tSafetyStandard) {
        Result result = new ResultImpl();

        try {
            if (null == tSafetyStandard.getOder()) {
                tSafetyStandard.setOder(0);
            }
            tSafetyStandardMapper.updateTSafetyStandard(tSafetyStandard);
            result.setStatus("0");
            result.setMess("修改成功");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMess("修改失败");
            return result;
        }
    }

    /**
     * 删除要素,要判断是否有这个要素点,第二这个要素点的子id有没有数据,有的话让其将子要素删除,然后才能删除
     *
     * @param safetyStandardlistId
     * @return
     */
    @RequestMapping(value = "/delete-tSafetyStandard", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteTSafetyStandard(Integer safetyStandardlistId) {
        Result result = new ResultImpl();
        try {
            List<TSafetyStandard> TSafetyStandard = tSafetyStandardMapper.findByparentId(safetyStandardlistId, null);
            if (null != TSafetyStandard && TSafetyStandard.size() > 0) {
                result.setStatus("1");
                result.setMess("该数据存在下级要素点,请删除完后在进行删除");
                return result;
            }
            tSafetyStandardMapper.deleteTSafetyStandard(safetyStandardlistId);
            result.setStatus("0");
            result.setMess("删除成功");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMess("未成功删除,请重试!");
            return result;
        }
    }


    /**
     * 富文本内容
     *
     * @param safetyStandardlistId
     * @param type
     * @param model
     * @return
     */
    @RequestMapping(value = "tab-richtext")
    public String jumpTabRichtext(Integer safetyStandardlistId, Integer type, Model model) {
        TSafetyStandard TSafetyStandard = tSafetyStandardMapper.findOne(safetyStandardlistId);
        model.addAttribute("type", type);
        model.addAttribute("safetyStandardlistId", safetyStandardlistId);
        model.addAttribute("item", TSafetyStandard);
        return "company/tables/tab-richtext";
    }

    /**
     * 自动导入功能
     * 首先判断这个公司类型
     * <p>
     * 先根据名称进行判断,是否包含化工企业,
     * 两种企业, 工贸/化工
     *
     * @return
     */
    @RequestMapping("/Automatic-import")
    @SuppressWarnings("all")
    @ResponseBody
    public Result automaticImport(HttpServletRequest request) {
        Result result = new ResultImpl();
        try {
            User user = getLoginUser(request);// 获取公司名称
            Company company = companyMapper.selectByPrimaryKey(user.getId());

            String industry ="";
            if(null==company){
                TradeClique tradeClique = tradeCliqueMapper.selectByPrimaryKey(user.getId());
                if(null==tradeClique){
                    industry=tradeClique.getIndustry();
                }
            }else{
                industry= company.getIndustry();
            }

            Integer industryType = null;
            if (industry.indexOf("化工") != -1) {
                industryType = 1; // 危化企业
            } else {
                industryType = 2; // 工贸企业
            }
            // 1. 查询关于该行业的A级要素
            List<TSafety> tSafetyList = tSafetyMapper.selectAByIndustryType(industryType, 0);
            for (TSafety tSafety : tSafetyList) {
                //A级要素
                TSafetyStandard tSafetyStandard = new TSafetyStandard();
                tSafetyStandard.setUserId(user.getId());   // 公司id
                tSafetyStandard.setName(tSafety.getName());// 要素名称
                tSafetyStandard.setDel(tSafety.getDel()); //表示未删除
                tSafetyStandard.setIndustryId(industryType); // 行业类型 1. 危化  2. 工贸
                tSafetyStandard.setFlag(tSafety.getFlag()); // A级要素
                tSafetyStandard.setParentId(0);// 父id
                tSafetyStandard.setOder(tSafety.getOder());
                tSafetyStandard.setType(tSafety.getType());
                tSafetyStandard.setContent(tSafety.getContent());
                tSafetyStandard.setFileAddress(tSafety.getFileAddress());
                tSafetyStandard.setFiles(tSafety.getFiles());
                tSafetyStandardMapper.insertSelective(tSafetyStandard);
                Integer tSafetyStandardId = tSafetyStandard.getId(); // 获取插入的A级要素id
                List<TSafety> list = tSafetyMapper.selectBBytSafetyStandardId(tSafety.getId());
                if (list.size() > 0) {
                    for (TSafety safety : list) {
                        // B级要素
                        TSafetyStandard tSafetyStandard1 = new TSafetyStandard();
                        tSafetyStandard1.setUserId(user.getId());   // 公司id
                        tSafetyStandard1.setName(safety.getName()); // 要素名称
                        tSafetyStandard1.setIndustryId(industryType); // 行业类型 1. 危化 2 工贸
                        tSafetyStandard1.setParentId(tSafetyStandardId); // 对应的A类要素id
                        tSafetyStandard1.setFlag(safety.getFlag()); // b级要素
                        tSafetyStandard1.setDel(safety.getDel()); //表示未删除
                        tSafetyStandard1.setOder(safety.getOder());
                        tSafetyStandard1.setType(safety.getType());
                        tSafetyStandard1.setFiles(safety.getFiles());
                        tSafetyStandard1.setFileAddress(safety.getFileAddress());
                        tSafetyStandard1.setContent(safety.getContent());
                        tSafetyStandardMapper.insertSelective(tSafetyStandard1);
                        Integer id = tSafetyStandard1.getId();
                        List<TSafety> tSafetyList2 = tSafetyMapper.selectBBytSafetyStandardId(safety.getId());
                        if (tSafetyList2.size() > 0) {
                            for (TSafety tSafety1 : tSafetyList2) {
                                // C级要素
                                TSafetyStandard tSafetyStandard2 = new TSafetyStandard();
                                tSafetyStandard2.setUserId(user.getId());   // 公司id
                                tSafetyStandard2.setName(tSafety1.getName()); // 要素名称
                                tSafetyStandard2.setIndustryId(industryType); // 行业类型 1. 危化 2 工贸
                                tSafetyStandard2.setParentId(id); // 对应的B类要素id
                                tSafetyStandard2.setFlag(tSafety1.getFlag()); // C级要素
                                tSafetyStandard2.setDel(tSafety1.getDel()); //表示未删除
                                tSafetyStandard2.setOder(tSafety1.getOder());
                                tSafetyStandard2.setType(tSafety1.getType());
                                tSafetyStandard2.setContent(tSafety1.getContent());
                                tSafetyStandard2.setFileAddress(tSafety1.getFileAddress());
                                tSafetyStandard2.setFiles(tSafety1.getFiles());
                                tSafetyStandardMapper.insertSelective(tSafetyStandard2);

                            }
                        }


                    }
                }

            }
            result.setStatus("0");
            result.setMess("插入成功");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMess("插入失败");
            return result;
        }
    }

    /**
     * 自动导入文本文件数据进行保存
     */
    @RequestMapping("/import-table")
    @ResponseBody
    public Result importTable(@RequestBody TSafety tSafety) {
        Result result = new ResultImpl();
        tSafetyMapper.insertTSafety(tSafety);
        //判断该公司类型
        result.setObject(tSafety.getId());
        return result;
    }

    /*
     *
     * 根据传递的字符串进行切割,然后进行保存
     * */
    @RequestMapping("/import-table-string")
    @ResponseBody
    public Result importTableString(@RequestBody Map<String, Object> map) {
        Integer parentId = (Integer) map.get("parentId");         // 父id
        Integer industryType = (Integer) map.get("industryType");  // 行业id
        String array = (String) map.get("StrArray");
        String[] split = array.split(",");
        for (int i = 0; i < split.length; i++) {
            if (!"".equals(split[i])) {
                TSafety tSafety = new TSafety();
                tSafety.setName(split[i]);
                tSafety.setDel(0);
                tSafety.setParentId(parentId);
                tSafety.setIndustryType(industryType);

                tSafetyMapper.insertTSafety(tSafety);
            }
        }

        Result result = new ResultImpl();

        //判断该公司类型
        result.setStatus("0");
        result.setMess("保存成功");
        return result;
    }

    /**
     * 治理方案文件上传的接口
     */
    @RequestMapping("B005")
    @SuppressWarnings("all")
    public @ResponseBody
    AppResult uploadFiles(@RequestParam(value = "file", required = false) MultipartFile file, Integer safetyStandardlistId, Integer type, HttpServletRequest request) throws IOException {
        AppResult result = new AppResultImpl();

        request.setCharacterEncoding("UTF-8");

        String realPath1 = "/images/upload/";
        String path = null;
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();

            String name = null;
            name = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            if (name != null) {
                if ("PDF".equals(name.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    String realPath = request.getSession().getServletContext().getRealPath("/");
                    String realPath2 = realPath.replaceAll("\\\\", "/");

                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                    String oldname = file.getOriginalFilename();
                    String fileExt = oldname.substring(oldname.lastIndexOf(".") + 1).toLowerCase();
                    String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
                    // 设置存放图片文件的路径
                    path = realPath2 + "images/upload/" + newFileName;
                    // 判断是否存在
                    File file1 = new File(realPath2 + "images/upload/");
                    if (!file1.exists() && !file1.isDirectory()) {
                        file1.mkdir();
                    }
                    realPath1 += newFileName;
                    file.transferTo(new File(path));
                    TSafetyStandard one = tSafetyStandardMapper.findOne(safetyStandardlistId);
                    one.setType(type);
                    String url = "";

                    if (8080 == request.getServerPort()) {
                        url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
                    } else {
                        url = request.getScheme() + "://" + request.getServerName();
                    }
                    one.setFiles(url + realPath1);
                    tSafetyStandardMapper.updateTSafetyStandard(one);
                } else {
                    result.setStatus("1");
                    result.setMessage("请上传pdf格式的文件。");
                    return result;
                }
            } else {
                result.setStatus("1");
                result.setMessage("文件类型为空");
                return result;

            }
        } else {

            result.setStatus("1");
            result.setMessage("没有找到相对应的文件");

            return result;

        }
        result.setStatus("0");
        result.setMessage("保存成功");
        realPath1.replace("\\", "/");
        result.setData(realPath1);
        return result;

    }

    @RequestMapping("daoru")
    public Result daoru() {
        Result result = new ResultImpl();

        try {
            List<TSafetyStandard> list1 = tSafetyStandardMapper.findAllByUserId(789, 0); //获取原始的a级要素
            for (TSafetyStandard tSafetyStandard : list1) {
                //然后再进行插入
                TSafety tSafety1 = new TSafety();
                tSafety1.setName(tSafetyStandard.getName());  // 要素名称
                tSafety1.setIndustryType(tSafetyStandard.getIndustryId());//行业类型
                tSafety1.setParentId(tSafetyStandard.getParentId());   // 父id
                tSafety1.setDel(tSafetyStandard.getDel());//是否删除
                tSafety1.setContent(tSafetyStandard.getContent());// 内容
                tSafety1.setFileAddress(tSafetyStandard.getFileAddress());
                tSafety1.setFiles(tSafetyStandard.getFiles());
                tSafety1.setOder(tSafetyStandard.getOder());
                tSafety1.setType(tSafetyStandard.getType());
                tSafety1.setFlag(tSafetyStandard.getFlag());
                tSafetyMapper.insertTSafety(tSafety1);
                Integer id1 = tSafety1.getId(); //获取A级要素id
                List<TSafetyStandard> list2 = tSafetyStandardMapper.findAllByUserId(789, tSafetyStandard.getId());//获取原始的B级要素
                if (list2.size() > 0) {
                    for (TSafetyStandard safetyStandard : list2) {
                        TSafety tSafety2 = new TSafety();
                        tSafety2.setName(safetyStandard.getName());  // 要素名称
                        tSafety2.setIndustryType(safetyStandard.getIndustryId());//行业类型
                        tSafety2.setParentId(id1);   // 父id
                        tSafety2.setDel(safetyStandard.getDel());//是否删除
                        tSafety2.setContent(safetyStandard.getContent());// 内容
                        tSafety2.setFileAddress(safetyStandard.getFileAddress());
                        tSafety2.setFiles(safetyStandard.getFiles());
                        tSafety2.setOder(safetyStandard.getOder());
                        tSafety2.setType(safetyStandard.getType());
                        tSafety2.setFlag(safetyStandard.getFlag());
                        tSafetyMapper.insertTSafety(tSafety2);
                        Integer id2 = tSafety2.getId(); //获取A级要素id
                        List<TSafetyStandard> list3 = tSafetyStandardMapper.findAllByUserId(789, safetyStandard.getId());//获取原始的c级要素
                        if (list3.size() > 0) {
                            for (TSafetyStandard standard : list3) {
                                TSafety tSafety3 = new TSafety();
                                tSafety3.setName(standard.getName());  // 要素名称
                                tSafety3.setIndustryType(standard.getIndustryId());//行业类型
                                tSafety3.setParentId(id2);   // 父id
                                tSafety3.setDel(standard.getDel());//是否删除
                                tSafety3.setContent(standard.getContent());// 内容
                                tSafety3.setFileAddress(standard.getFileAddress());
                                tSafety3.setFiles(standard.getFiles());
                                tSafety3.setOder(standard.getOder());
                                tSafety3.setType(standard.getType());
                                tSafety3.setFlag(standard.getFlag());
                                tSafetyMapper.insertTSafety(tSafety3);
                            }
                        }

                    }
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
            result.setStatus("1");
            return result;
        }
        result.setStatus("0");
        return result;
    }


    /**
     * 查询所有的文件
     *
     * @param
     * @param model
     * @return
     */
    @SuppressWarnings("all")
    @RequestMapping("tab-biaozhun-my")
    public String selectSafety(Integer parentId, Model model) {
        //在这里把数据给进行插入

        List<TSafety> list = tSafetyMapper.selectBBytSafetyStandardId(parentId);
        model.addAttribute("list", list);
        model.addAttribute("parentId", parentId);
        return "company/tables/tab-biaozhun-my";
    }

    /**
     * 保存插入数据
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/save-safety")
    public @ResponseBody
    Result saveSafety(@RequestBody TSafety tSafety, HttpServletRequest request) {
        Result result = new ResultImpl();
        User loginUser = getLoginUser(request);

        try {
            tSafety.setDel(0);
            tSafetyMapper.insertTSafety(tSafety);

            result.setMess("保存成功");
            result.setStatus("0");
            result.setObject(tSafety.getId());
            return result;
        } catch (Exception e) {
            result.setMess("保存失败");
            result.setStatus("1");
            result.setObject(tSafety.getId());
            return result;
        }

    }

    /**
     * 修改数据
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/update-tSafety", method = RequestMethod.POST)
    public @ResponseBody
    Result updateTSafety(@RequestBody TSafety tSafety) {
        Result result = new ResultImpl();
        try {
            String name = tSafety.getName();
            tSafety.setName(name);
            tSafetyMapper.updateTSafety(tSafety);
            result.setStatus("0");
            result.setMess("修改成功");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMess("修改失败");
            return result;
        }
    }


}
