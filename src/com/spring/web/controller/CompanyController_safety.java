/**
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd.
 * All right reserved.
 */
package com.spring.web.controller;

import com.spring.web.BaseController;
import com.spring.web.dao.CommerceMapper;
import com.spring.web.dao.DangerCoordinateMapper;
import com.spring.web.dao.DistinguishTypeMapper;
import com.spring.web.dao.ImportPhotoMapper;
import com.spring.web.ibatis.LlHashMap;
import com.spring.web.model.*;
import com.spring.web.model.request.ImportPhoto;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.service.cgf.CgfService;
import com.spring.web.service.trouble.TroubleService;
import com.spring.web.util.OutPrintUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * @author CGF
 * @version V1.0
 * @Title: CompanyController_cd
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2017年7月27日 上午9:49:51
 */
@Controller
@RequestMapping("company/safety-system")
public class CompanyController_safety extends BaseController {
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 6784473257577263720L;
    @Autowired
    private TroubleService troubleService;
    @Autowired
    private CgfService cgfService;
    @Autowired
    private ImportPhotoMapper importPhotoMapper;
    @Autowired
    private DangerCoordinateMapper dangerCoordinateMapper;
    @Autowired
    private DistinguishTypeMapper distinguishTypeMapper;
    @Autowired
    private CommerceMapper commerceMapper;

    public CompanyController_safety() {
    }

    /** 安全风险管控系统 开始 */
    /**
     * 判断安全风险管控 是否存在
     */
    @RequestMapping({"check"})
    @ResponseBody
    public Result check(HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = this.getLoginUser(request);
        Company company = this.companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            result.setMap("c", Integer.valueOf(-1));
            result.setMap("ind", "");
        } else {
            List<ADangerManual> dL = this.aDangerManualMapper.selectByIndustry(company.getIndustry());
            result.setMap("c", dL.size());
            result.setMap("ind", company.getIndustry());
        }

        return result;
    }

    /**
     * 企业风险辨识-删除
     */
  /*  @RequestMapping(value = "aCompanyManual-dels")
    public @ResponseBody Result aCompanyManualDel(HttpServletRequest request,Integer id) throws Exception {
        Result result = new ResultImpl();

        // 根据ID修改数据信息达到删除的目的
        aCompanyManualMapper.updateDeltess(id);
        return result;
    }*/


    /*
     * 多选框删除
     * */
    @RequestMapping({"aCompanyManual-deletes"})
    @ResponseBody
    public Result aCompanyManualDel(HttpServletRequest request, String ids) throws Exception {
        Result result = new ResultImpl();
        User user = this.getLoginUser(request);
        this.aCompanyManualMapper.updateDelByIds(ids);
        this.aCompanyManualMapper.updateCompanyDlevel(user.getId());
        return result;
    }


    /*
     * 较大风险删除
     * */
    @RequestMapping({"aCompanyManual-del"})
    @ResponseBody
    public Result aCompanyManualDel(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        aCompanyManualMapper.updateAll(id);
        return result;
    }


    /*
     *  重大风险一键导入 2
     * */
    @RequestMapping(value = "risk-list-add1")
    public @ResponseBody
    Result riskList(HttpServletRequest request) {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        // 根据企业ID 查询当前企业中所有的高危风险的数据 ID
        List<ACompanyManual> aCompanyManualList = aCompanyManualMapper.selectIds(company.getUserId());
        // 根据 ids 修改删除标记为 1（已经删除）
        for (int i = 0; i < aCompanyManualList.size(); i++) {
            aCompanyManualMapper.updateAll(aCompanyManualList.get(i).getId());
        }
        ACompanyManual aCompanyManuals;
        List<String> list = null;

        if (null != company.getIndustry()) {
            if (company.getIndustry().contains("化工企业（危化生产、使用）")) {
                list = new ArrayList<>();
                list.add("危险化学品生产、经营单位主要负责人和安全生产管理人员未依法经考核合格。");
                list.add("特种作业人员未持证上岗。");
                list.add("涉及“两重点一重大”的生产装置、储存设施外部安全防护距离不符合国家标准要求。");
                list.add("涉及重点监管危险化工工艺的装置未实现自动化控制，系统未实现紧急停车功能，装备的自动化控制系统、紧急停车系统未投入使用。");
                list.add("构成一级、二级重大危险源的危险化学品罐区未实现紧急切断功能；涉及毒性气体、液化气体、剧毒液体的一级、二级重大危险源的危险化学品罐区未配备独立的安全仪表系统。");
                list.add("全压力式液化烃储罐未按国家标准设置注水措施。");
                list.add("液化烃、液氨、液氯等易燃易爆、有毒有害液化气体的充装未使用万向管道充装系统。");
                list.add("光气、氯气等剧毒气体及硫化氢气体管道穿越除厂区(包括化工园区、工业园区）外的公共区域。");
                list.add("地区架空电力线路穿越生产区且不符合国家标准要求。");
                list.add("在役化工装置未经正规设计且未进行安全设计诊断。");
                list.add("使用淘汰落后安全技术工艺、设备目录列出的工艺、设备。");
                list.add("涉及可燃和有毒有害气体泄漏的场所未按国家标准设置检测报警装置，爆炸危险场所未按国家标准安装使用防爆电气设备。");
                list.add("控制室或机柜间面向具有火灾、爆炸危险性装置一侧不满足国家标准关于防火防爆的要求。");
                list.add("化工生产装置未按国家标准要求设置双重电源供电，自动化控制系统未设置不间断电源。");
                list.add("安全阀、爆破片等安全附件未正常投用。");
                list.add("未建立与岗位相匹配的全员安全生产责任制或者未制定实施生产安全事故隐患排查治理制度。");
                list.add("未制定操作规程和工艺控制指标。");
                list.add("未按照国家标准制定动火、进入受限空间等特殊作业管理制度，或者制度未有效执行。");
                list.add("新开发的危险化学品生产工艺未经小试、中试、工业化试验直接进行工业化生产；国内首次使用的化工工艺未经过省级人民政府有关部门组织的安全可靠性论证；新建装置未制定试生产方案投料开车；精细化工企业未按规范性文件要求开展反应安全风险评估。");
                list.add("未按国家标准分区分类储存危险化学品，超量、超品种储存危险化学品，相互禁配物质混放混存。");

            } else if (company.getIndustry().contains("烟花爆竹经营(批发)企业")) {

                list = new ArrayList<>();
                list.add("主要负责人、安全生产管理人员未依法经考核合格。");
                list.add("特种作业人员未持证上岗，作业人员带药检维修设备设施。");
                list.add("职工自行携带工器具、机器设备进厂进行涉药作业。");
                list.add("工（库）房实际作业人员数量超过核定人数。");
                list.add("工（库）房实际滞留、存储药量超过核定药量。");
                list.add("工（库）房内、外部安全距离不足，防护屏障缺失或者不符合要求。");
                list.add("防静电、防火、防雷设备设施缺失或者失效。");
                list.add("擅自改变工（库）房用途或者违规私搭乱建。");
                list.add("工厂围墙缺失或者分区设置不符合国家标准。");
                list.add("将氧化剂、还原剂同库储存、违规预混或者在同一工房内粉碎、称量。");
                list.add("在用涉药机械设备未经安全性论证或者擅自更改、改变用途。");
                list.add("中转库、药物总库和成品总库的存储能力与设计产能不匹配。");
                list.add("未建立与岗位相匹配的全员安全生产责任制或者未制定实施生产安全事故隐患排查治理制度。");
                list.add("出租、出借、转让、买卖、冒用或者伪造许可证。");
                list.add("生产经营的产品种类、危险等级超许可范围或者生产使用违禁药物。");
                list.add("分包转包生产线、工房、库房组织生产经营。");
                list.add("一证多厂或者多股东各自独立组织生产经营。");
                list.add("许可证过期、整顿改造、恶劣天气等停产停业期间组织生产经营。");
                list.add("烟花爆竹仓库存放其它爆炸物等危险物品或者生产经营违禁超标产品。");
                list.add("零售点与居民居住场所设置在同一建筑物内或者在零售场所使用明火。");
            } else if (company.getIndustry().equals("冶金行业")) {

                list = new ArrayList<>();
                list.add("会议室、活动室、休息室、更衣室等场所设置在铁水、钢水与液渣吊运影响的范围内。");
                list.add("吊运铁水、钢水与液渣起重机不符合冶金起重机的相关要求；炼钢厂在吊运重罐铁水、钢水或液渣时，未使用固定式龙门钩的铸造起重机，龙门钩横梁、耳轴销和吊钩、钢丝绳及其端头固定零件，未进行定期检查，发现问题未及时整改。");
                list.add("盛装铁水、钢水与液渣的罐（包、盆）等容器耳轴未按国家标准规定要求定期进行探伤检测。");
                list.add("冶炼、熔炼、精炼生产区域的安全坑内及熔体泄漏、喷溅影响范围内存在积水，放置有易燃易爆物品。金属铸造、连铸、浇铸流程未设置铁水罐、钢水罐、溢流槽、中间溢流罐等高温熔融金属紧急排放和应急储存设施。");
                list.add("炉、窑、槽、罐类设备本体及附属设施未定期检查，出现严重焊缝开裂、腐蚀、破损、衬砖损坏、壳体发红及明显弯曲变形等未报修或报废，仍继续使用。");
                list.add("氧枪等水冷元件未配置出水温度与进出水流量差检测、报警装置及温度监测，未与炉体倾动、氧气开闭等联锁。");
                list.add("煤气柜建设在居民稠密区，未远离大型建筑、仓库、通信和交通枢纽等重要设施；附属设备设施未按防火防爆要求配置防爆型设备；柜顶未设置防雷装置。");
                list.add("煤气区域的值班室、操作室等人员较集中的地方，未设置固定式一氧化碳监测报警装置。");
                list.add("高炉、转炉、加热炉、煤气柜、除尘器等设施的煤气管道未设置可靠隔离装置和吹扫设施。");
                list.add("煤气分配主管上支管引接处，未设置可靠的切断装置；车间内各类燃气管线，在车间入口未设置总管切断阀。");
                list.add("金属冶炼企业主要负责人和安全生产管理人员未依法经考核合格。");
            } else if (company.getIndustry().contains("有色行业")) {

                list = new ArrayList<>();
                list.add("吊运铜水等熔融有色金属及渣的起重机不符合冶金起重机的相关要求；横梁、耳轴销和吊钩、钢丝绳及其端头固定零件，未进行定期检查，发现问题未及时处理。");
                list.add("会议室、活动室、休息室、更衣室等场所设置在铜水等熔融有色金属及渣的吊运影响范围内。");
                list.add("盛装铜水等熔融有色金属及渣的罐（包、盆）等容器耳轴未定期进行检测。");
                list.add("铜水等高温熔融有色金属冶炼、精炼、铸造生产区域的安全坑内及熔体泄漏、喷溅影响范围内存在非生产性积水；熔体容易喷溅到的区域，放置有易燃易爆物品。");
                list.add("铜水等熔融有色金属铸造、浇铸流程未设置紧急排放和应急储存设施。");
                list.add("高温工作的熔融有色金属冶炼炉窑、铸造机、加热炉及水冷元件未设置应急冷却水源等冷却应急处置措施。");
                list.add("冶炼炉窑的水冷元件未配置温度、进出水流量差检测及报警装置；未设置防止冷却水大量进入炉内的安全设施（如：快速切断阀等）。");
                list.add("炉、窑、槽、罐类设备本体及附属设施未定期检查，出现严重焊缝开裂、腐蚀、破损、衬砖损坏、壳体发红及明显弯曲变形等未报修或报废，仍继续使用。");
                list.add("使用煤气（天然气）的烧嘴等燃烧装置，未设置防突然熄火或点火失败的快速切断阀，以切断煤气（天然气）。");
                list.add("煤气分配主管上支管引接处，未设置可靠的切断装置；车间内各类燃气管线，在车间入口未设置总管切断阀。");
                list.add("金属冶炼企业主要负责人和安全生产管理人员未依法经考核合格。");
            } else if (company.getIndustry().contains("建材行业")) {
                list = new ArrayList<>();
                list.add("水泥工厂煤磨袋式收尘器（或煤粉仓）未设置温度和一氧化碳监测，或未设置气体灭火装置。");
                list.add("水泥工厂筒型储存库人工清库作业外包给不具备高空作业工程专业承包资质的承包方且作业前未进行风险分析。");
                list.add("燃气窑炉未设置燃气低压警报器和快速切断阀，或易燃易爆气体聚集区域未设置监测报警装置。");
                list.add("纤维制品三相电弧炉、电熔制品电炉，水冷构件泄漏。");
                list.add("进入筒型储库、磨机、破碎机、篦冷机、各种焙烧窑等有限空间作业时，未采取有效的防止电气设备意外启动、热气涌入等隔离防护措施。");
                list.add("玻璃窑炉、玻璃锡槽，水冷、风冷保护系统存在漏水、漏气，未设置监测报警装置。");
            } else if (company.getIndustry().contains("机械行业")) {
                list = new ArrayList<>();
                list.add("会议室、活动室、休息室、更衣室等场所设置在熔炼炉、熔融金属吊运和浇注影响范围内。");
                list.add("吊运熔融金属的起重机不符合冶金铸造起重机技术条件,或驱动装置中未设置两套制动器。吊运浇注包的龙门钩横梁、耳轴销和吊钩等零件，未进行定期探伤检查。");
                list.add("铸造熔炼炉炉底、炉坑及浇注坑等作业坑存在潮湿、积水状况，或存放易燃易爆物品。");
                list.add("铸造熔炼炉冷却水系统未配置温度、进出水流量检测报警装置，没有设置防止冷却水进入炉内的安全设施。");
                list.add("天然气（煤气）加热炉燃烧器操作部位未设置可燃气体泄漏报警装置，或燃烧系统未设置防突然熄火或点火失败的安全装置。");
                list.add("使用易燃易爆稀释剂（如天拿水）清洗设备设施，未采取有效措施及时清除集聚在地沟、地坑等有限空间内的可燃气体。");
                list.add("涂装调漆间和喷漆室未规范设置可燃气体报警装置和防爆电气设备设施。");
            } else if (company.getIndustry().contains("轻工行业")) {
                list = new ArrayList<>();
                list.add("食品制造企业涉及烘制、油炸等设施设备，未采取防过热自动报警切断装置和隔热防护措施。");
                list.add("白酒储存、勾兑场所未规范设置乙醇浓度检测报警装置。");
                list.add("纸浆制造、造纸企业使用水蒸气或明火直接加热钢瓶汽化液氯。");
                list.add("日用玻璃、陶瓷制造企业燃气窑炉未设燃气低压警报器和快速切断阀，或易燃易爆气体聚集区域未设置监测报警装置。");
                list.add("日用玻璃制造企业炉、窑类设备本体及附属设施出现开裂、腐蚀、破损、衬砖损坏、壳体发红及明显弯曲变形。");
                list.add("喷涂车间、调漆间未规范设置通风装置和防爆电气设备设施。");
            } else if (company.getIndustry().contains("纺织行业")) {
                list = new ArrayList<>();
                list.add("纱、线、织物加工的烧毛、开幅、烘干等热定型工艺的汽化室、燃气贮罐、储油罐、热媒炉等未与生产加工、人员密集场所明确分开或单独设置。");
                list.add("保险粉、双氧水、亚氯酸钠、雕白粉（吊白块）等危险品与禁忌物料混合贮存的；保险粉露天堆放，或储存场所未采取防水、防潮等措施。");
            } else if (company.getIndustry().contains("烟草行业")) {
                list = new ArrayList<>();
                list.add("熏蒸杀虫作业前，未确认无关人员全部撤离仓库，且作业人员未配置防毒面具。");
                list.add("使用液态二氧化碳制造膨胀烟丝的生产线和场所，未设置二氧化碳浓度报警仪、燃气浓度报警仪、紧急联动排风装置。");
            } else if (company.getIndustry().contains("商贸行业")) {
                list = new ArrayList<>();
                list.add("在房式仓、筒仓及简易仓囤进行粮食进出仓作业时，未按照作业标准步骤或未采取有效防护措施作业。");
            }
        }


        if (null != company.getDangers() && company.getDangers().contains("粉尘涉爆")) {

            list.add("粉尘爆炸危险场所设置在非框架结构的多层建构筑物内，或与居民区、员工宿舍、会议室等人员密集场所安全距离不足。");
            list.add("可燃性粉尘与可燃气体等易加剧爆炸危险的介质共用一套除尘系统，不同防火分区的除尘系统互联互通。");
            list.add("干式除尘系统未规范采用泄爆、隔爆、惰化、抑爆等任一种控爆措施。");
            list.add("除尘系统采用正压吹送粉尘，且未采取可靠的防范点燃源的措施。");
            list.add("除尘系统采用粉尘沉降室除尘，或者采用干式巷道式构筑物作为除尘风道。");
            list.add("铝镁等金属粉尘及木质粉尘的干式除尘系统未规范设置锁气卸灰装置。");
            list.add("粉尘爆炸危险场所的20区未使用防爆电气设备设施。");
            list.add("在粉碎、研磨、造粒等易于产生机械点火源的工艺设备前，未按规范设置去除铁、石等异物的装置。");
            list.add("木制品加工企业，与砂光机连接的风管未规范设置火花探测报警装置。");
            list.add("未制定粉尘清扫制度，作业现场积尘未及时规范清理。");
        }
        if (null != company.getDangers() && company.getDangers().contains("液氨制冷")) {

            list.add("包装间、分割间、产品整理间等人员较多生产场所的空调系统采用氨直接蒸发制冷系统。");
            list.add("快速冻结装置未设置在单独的作业间内，且作业间内作业人员数量超过9人。");
        }
        if (null != company.getDangers() && company.getDangers().contains("空间安全作业")) {


            list.add("未对有限空间作业场所进行辨识，并设置明显安全警示标志。");

            list.add("未落实作业审批制度，擅自进入有限空间作业。");
        }

        for (int i = 0; i < list.size(); i++) {
            aCompanyManuals = new ACompanyManual();
            aCompanyManuals.setUid(user.getId());
            aCompanyManuals.setLevel3("高危");
            aCompanyManuals.setFactors(list.get(i));
            aCompanyManuals.setFlag("2");
            aCompanyManuals.setLevel("红色");
            aCompanyManuals.setIssys(0);
            aCompanyManuals.setDel(0);
            aCompanyManuals.setCtime(new Date());
            aCompanyManuals.setGwzy(company.getDangers());
            aCompanyManualMapper.add(aCompanyManuals);
        }

        return result;
    }

   /* @RequestMapping({"risk-list-adddel"})
    @ResponseBody
    public Result riskListDel(Model model, HttpServletRequest request, Integer type) throws Exception {
        Result result = new ResultImpl();
        User user = this.getLoginUser(request);
        this.aCompanyManualMapper.updateDelByUserIddel(user.getId());
        return result;
    }*/

    /*
     *  清空高危风险数据
     * */
    @RequestMapping(value = "risk-list-adddel1")
    public @ResponseBody
    Result adddel1(HttpServletRequest request, Model model, Integer type) {

        Result result = new ResultImpl();
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        // 根据企业ID 查询当前企业中所有的高危风险的数据 ID
        List<ACompanyManual> aCompanyManualList = aCompanyManualMapper.selectIds(company.getUserId());
        // 根据 ids 修改删除标记为 1（已经删除）
        for (int i = 0; i < aCompanyManualList.size(); i++) {
            aCompanyManualMapper.updateAll(aCompanyManualList.get(i).getId());
        }

        return result;
    }


    /**
     * 风险辨识
     */
    /*@RequestMapping(value = "risk-list")
    public String riskList(Model model, HttpServletRequest request, Integer type) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI());
            return "company/safety-system/type";
        }

        model.addAttribute("company", company);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("type", type);
        m.put("flag", "1,3");
        m.put("uid", user.getId());
        List<Map<Object, Object>> zzjg = zzjgDepartmentMapper.selectLevel2ByUid(user.getId());
        if(null == type) {
//        	m.put("order", 1);
            List<ACompanyManual> aCompanyManualList = aCompanyManualMapper.selectAll(company.getUserId());

            ACompanyManual aCompanyManual = new ACompanyManual();

            if (aCompanyManualList.size() == 0){
                aCompanyManual.setLevel3("无数据信息");
                aCompanyManual.setFactors("无数据信息");

                aCompanyManualList.add(aCompanyManual);

                model.addAttribute("aCompanyManualList", aCompanyManualList);

                return "company/safety-system/risk-list1";
            }

            model.addAttribute("zzjgDep", zzjg);
            model.addAttribute("aCompanyManualList", aCompanyManualList);
            return "company/safety-system/risk-list1";
        }

        List<Map<String, Object>> acL = aCompanyManualMapper.selectByMapGroupByLevel1Level2(m);
        Iterator <Map<String, Object>> it = acL.iterator();
        while(it.hasNext()){
            Map<String, Object> ac = it.next();
            String level1 = ac.get("level1").toString();
            String level2 = ac.get("level2").toString();
            boolean has = false;
            for(Map<Object, Object> zz : zzjg) {
                String p = zz.get("parName").toString();
                String name = zz.get("name").toString();
                if(p.equals(level1) && name.equals(level2)) {
                    has = true;
                    break;
                }
            }

            if(!has) {
                it.remove();
            }
        }

//        Map<String, Set<String>> list = new LinkedHashMap<String, Set<String>>();
//        for (Map<String, Object> ad : acL) {
//            String l1 = String.valueOf(ad.get("level1"));
//            String l2 = String.valueOf(ad.get("level2"));
//            if (null == list.get(l1)) {
//                list.put(l1, new LinkedHashSet<String>());
//            }
//            Set<String> s = list.get(l1);
//            s.add(l2);
//        }
//        model.addAttribute("list", list);
        model.addAttribute("dL", acL);
        model.addAttribute("type", type);
        return "company/safety-system/risk-list1";
    }*/

    /*
     * 风险辨识,其他因素 !!!
     * @param model
     * @param request
     * @param type
     * @return
     * @throws Exception
     **/
    @RequestMapping({"risk-list"})
    public String riskList(Model model, HttpServletRequest request, Integer type, Integer number,Integer dmid,
                           Integer flag,String types,String id, String buttons) throws Exception {
        User user = this.getLoginUser(request);
        Company company = this.companyMapper.selectByPrimaryKey(user.getId());

        if (null == buttons){
            buttons = "2";
        }
        if (null != buttons && Integer.parseInt(buttons) == 1){
            // 根据 uid 修改 dangerId 为 null
            Integer a = zzjgDepartmentMapper.deleteAll(null,user.getId(),new Date());
            model.addAttribute("buttons", 1);
        }
        if (null != buttons && Integer.parseInt(buttons) == 2){
            model.addAttribute("buttons", 2);
        }
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI());
            return "company/safety-system/type";
        } else {
            model.addAttribute("company", company);
            Map<String, Object> m = new HashMap();
            if (null != types){
                m.put("type", Integer.parseInt(types));
            }
            m.put("flag", "1,2,3,4,5");
            m.put("uid", user.getId());
            if (null != dmid){
                m.put("dmid", Integer.parseInt(id));
            }
            List<Map<Object, Object>> zzjg = null;
            List<Map<Object, Object>> zzjg1 = null;
            List acL = null;
            /*if (type == null) {*/
                /*if (null == number){ // 设置
                    String dangerIds = "1"; // 现场
                    zzjg = this.zzjgDepartmentMapper.selectLevel1All(user.getId(),dangerIds);
                    if (null == zzjg || zzjg.size() == 0){
                        dangerIds = "2";
                        zzjg = this.zzjgDepartmentMapper.selectLevel1All(user.getId(),dangerIds);
                        if (null == zzjg || zzjg.size() == 0){
                           number = 3;
                        }else if (null != zzjg || zzjg.size() != 0){
                            number = 2;
                        }
                    }else if (null != zzjg){
                        number = 1;
                    }
                }*/
            if (null == types || Integer.parseInt(types) == 1 || Integer.parseInt(types) == 2) {
                if (null == number && null == types){
                    number = 3;
                }else if (null != types && types.equals("1")){ // 现场
                    number = 1;
                }else if (null != types && types.equals("2")){ // 基础
                    number = 2;
                }
                if (number == 1) { // 现场
                    String dangerIds = "1";

                    zzjg1 = this.zzjgDepartmentMapper.selectLevel1All(user.getId(),dangerIds);

                    acL = this.aCompanyManualMapper.selectByAll(m);
                    if (null == id){
                        zzjg = this.zzjgDepartmentMapper.selectLevel1All(user.getId(),dangerIds);
                    }else {
                        zzjg = this.zzjgDepartmentMapper.selectLevel1One(user.getId(),dangerIds,Integer.parseInt(id));
                    }
                } else if (number == 2) { // 基础
                    String dangerIds = "2";
                    acL = this.aCompanyManualMapper.selectBase(m);
                    zzjg1 = this.zzjgDepartmentMapper.selectLevel1All(user.getId(),dangerIds);
                    if (null == id){
                        zzjg = this.zzjgDepartmentMapper.selectLevel1All(user.getId(),dangerIds);
                    }else {
                        zzjg = this.zzjgDepartmentMapper.selectLevel1One(user.getId(),dangerIds,Integer.parseInt(id));
                    }
                }else if (number == 3) { // 设置
                    zzjg = this.zzjgDepartmentMapper.selectLevel1ByUid(user.getId());
                }

                Integer indus = 0;
                if (company.getIndustry().contains("化工")){
                    indus = 1;
                }else {
                    indus = 2;
                }
                model.addAttribute("buttons",buttons);
                model.addAttribute("indus",indus);
                model.addAttribute("ids",id);
                model.addAttribute("zzjgDep1", zzjg1);
                model.addAttribute("number", number);
                model.addAttribute("zzjgDep", zzjg);
                model.addAttribute("acL", acL);

                return "company/safety-system/risk-list1";
            } else {
                /*String dangerId = null;
                if (type == 1){ // 职业病风险物理因素
                    dangerId = "4";
                }else if(type == 2){ // 职业病风险化学因素
                    dangerId = "5";
                }else if(type == 3){ // 高危工艺辨识
                    dangerId = "6";
                }else if(type == 4){ // 物料风险辨识
                    dangerId = "7";
                }else if(type == 5){ // 高危作业辨识
                    dangerId = "8";
                }*/
                String dangerId = null;
                if (Integer.parseInt(types) == 4){ // 职业病风险物理因素
                    dangerId = "4";
                    type = 1;
                }else if(Integer.parseInt(types) == 5){ // 职业病风险化学因素
                    dangerId = "5";
                    type = 2;
                }else if(Integer.parseInt(types) == 6){ // 高危工艺辨识
                    dangerId = "6";
                    type = 3;
                }else if(Integer.parseInt(types) == 7){ // 物料风险辨识
                    dangerId = "7";
                    type = 4;
                }else if(Integer.parseInt(types) == 8){ // 高危作业辨识
                    dangerId = "8";
                    type = 5;
                }
                List<ZzjgDepartment> zzjgDepartment = zzjgDepartmentMapper.selectDangerIds(user.getId(),dangerId);
                if (null != zzjgDepartment && zzjgDepartment.size() != 0){
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < zzjgDepartment.size(); i++) {
                        if (i == zzjgDepartment.size()-1){
                            sb.append(zzjgDepartment.get(i).getId());
                        }else {
                            sb.append(zzjgDepartment.get(i).getId()).append(",");
                        }
                    }
                    if (sb.toString().length() != 0){
                        zzjg = this.zzjgDepartmentMapper.selectAllName(user.getId(),type,sb.toString());
                        zzjg1 = this.zzjgDepartmentMapper.selectAllName(user.getId(),type,sb.toString());
                        if (null == dmid){
                            zzjg = this.zzjgDepartmentMapper.selectAllName(user.getId(),type,sb.toString());
                        }else {
                            zzjg = this.zzjgDepartmentMapper.selectAllName(user.getId(),type,dmid.toString());
                        }
                    }
                }
                Integer indus = 0;
                if (company.getIndustry().contains("化工")){
                    indus = 1;
                }else {
                    indus = 2;
                }

                model.addAttribute("indus",indus);
                model.addAttribute("zzjgDep1", zzjgDepartment);
                model.addAttribute("zzjg",zzjg);
                model.addAttribute("type", type);
                return "company/safety-system/risk-list1";
                /* if (null == flag){
                    flag = 2;
                }
                if (flag == 1){
                    zzjg = this.zzjgDepartmentMapper.selectAllName(user.getId(),type);
                    flag = 1;
                }else if (flag == 2){
                    zzjg = this.zzjgDepartmentMapper.selectNotEmpy(user.getId(),type);
                    flag = 2;
                }*/
                /*acL = this.aCompanyManualMapper.selectByMapGroupByLevel1Level2(m);*/
                /*model.addAttribute("dL", acL);*/
                /*model.addAttribute("flags",flag);*/
            }
        }
    }

    /*
    * 风险辨识 ：设置按钮！！！
    * */
    @RequestMapping({"risk-set"})
    @ResponseBody
    public Result riskSet(Model model, String dpId, String number1, String number2, String number3, String number4,
                          String number5, String number6, String number7, String number8, Boolean flag){
        Result result = new ResultImpl();
        ZzjgDepartment zzjgDepartment = zzjgDepartmentMapper.selectByPrimaryKey(Integer.parseInt(dpId));

        String  dangerId = null;
        if (flag){
            if (null != number1){
                if (null == zzjgDepartment.getDangerId() || zzjgDepartment.getDangerId().length() == 0){
                    dangerId = "1";
                }else if (null != zzjgDepartment.getDangerId() && zzjgDepartment.getDangerId().length() != 0){
                    dangerId = zzjgDepartment.getDangerId() + "," + "1"; // 现场
                }
            }
            if (null != number2){
                if (null == zzjgDepartment.getDangerId() || zzjgDepartment.getDangerId().length() == 0){
                    dangerId = "2";
                }else if (null != zzjgDepartment.getDangerId() && zzjgDepartment.getDangerId().length() != 0){
                    dangerId = zzjgDepartment.getDangerId() + "," + "2"; // 基础
                }
            }
            if (null != number3){
                if (null == zzjgDepartment.getDangerId() || zzjgDepartment.getDangerId().length() == 0){
                    dangerId = "3";
                }else if (null != zzjgDepartment.getDangerId() && zzjgDepartment.getDangerId().length() != 0){
                    dangerId = zzjgDepartment.getDangerId() + "," + "3"; // 安全风险
                }
            }
            if (null != number4){
                if (null == zzjgDepartment.getDangerId() || zzjgDepartment.getDangerId().length() == 0){
                    dangerId = "4";
                }else if (null != zzjgDepartment.getDangerId() && zzjgDepartment.getDangerId().length() != 0){
                    dangerId = zzjgDepartment.getDangerId() + "," + "4";  // 职业病物理因素
                }
            }
            if (null != number5){
                if (null == zzjgDepartment.getDangerId() || zzjgDepartment.getDangerId().length() == 0){
                    dangerId = "5";
                }else if (null != zzjgDepartment.getDangerId() && zzjgDepartment.getDangerId().length() != 0) {
                    dangerId = zzjgDepartment.getDangerId() + "," + "5"; // 职业病化学因素
                }
            }
            if (null != number6){
                if (null == zzjgDepartment.getDangerId() || zzjgDepartment.getDangerId().length() == 0){
                    dangerId = "4";
                }else if (null != zzjgDepartment.getDangerId() && zzjgDepartment.getDangerId().length() != 0) {
                    dangerId = zzjgDepartment.getDangerId() + "," + "6"; // 高危工艺
                }
            }
            if (null != number7){
                if (null == zzjgDepartment.getDangerId() || zzjgDepartment.getDangerId().length() == 0){
                    dangerId = "4";
                }else if (null != zzjgDepartment.getDangerId() && zzjgDepartment.getDangerId().length() != 0) {
                    dangerId = zzjgDepartment.getDangerId() + "," + "7"; // 物料
                }
            }
            if (null != number8){
                if (null == zzjgDepartment.getDangerId() || zzjgDepartment.getDangerId().length() == 0){
                    dangerId = "4";
                }else if (null != zzjgDepartment.getDangerId() && zzjgDepartment.getDangerId().length() != 0) {
                    dangerId = zzjgDepartment.getDangerId() + "," + "8"; // 高危作业
                }
            }
        }else if (flag == false && null != zzjgDepartment.getDangerId() && zzjgDepartment.getDangerId().length() != 0){
            String[] str = zzjgDepartment.getDangerId().split(",");
            String[]  test = null;
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < str.length; i++) {
                if (null != number1 && number1.equals(str[i])){
                    test =  ArrayUtils.remove(str,i);
                }else if (null != number2 && number2.equals(str[i])){
                    test =  ArrayUtils.remove(str,i);
                }else if (null != number3 && number3.equals(str[i])){
                    test =  ArrayUtils.remove(str,i);
                }else if (null != number4 && number4.equals(str[i])){
                    test =  ArrayUtils.remove(str,i);
                }else if (null != number5 && number5.equals(str[i])){
                    test =  ArrayUtils.remove(str,i);
                }else if (null != number6 && number6.equals(str[i])){
                    test =  ArrayUtils.remove(str,i);
                }else if (null != number7 && number7.equals(str[i])){
                    test =  ArrayUtils.remove(str,i);
                }else if (null != number8 && number8.equals(str[i])){
                    test =  ArrayUtils.remove(str,i);
                }
            }
            for (int i = 0; i < test.length; i++) {
                sb.append(test[i]).append(",");
            }
            dangerId = sb.toString();
        }

        boolean b = zzjgDepartmentMapper.updateDangerId(Integer.parseInt(dpId),dangerId);
        if (b){
            result.setStatus("0");
        }else {
            result.setStatus("1");
        }

       /* // 根据 ID 修改对应的数据信息在 zzjg_department_tbl 表中
        Integer  dangerId = null;
        if (null != xc && null != jc){
            if (xc == 1 && jc == 1){ // 现场/基础 都有
                dangerId = 3;
            }else if (xc == 1 && jc == 0){// 现场 ：有   基础 ：没有
                dangerId = 1;
            }else if (xc == 0 && jc == 1){// 现场 ：没有   基础 ：有
                dangerId = 2;
            }else if (xc == 0 && jc == 0){// 现场/基础 都没有
                dangerId = 4;
            }
            boolean b = zzjgDepartmentMapper.updateDangerId(id,dangerId);
            if (b){
                result.setStatus("0");
            }else {
                result.setStatus("1");
            }
        }else {
            result.setStatus("1");
        }*/
        return result;
    }

    /**
     * create by  : 小明！！！
     * description: 工贸评分页面
     * create time: 16:21 2019/6/20
     */
    @RequestMapping({"danger-table"})
    public String dangerTable(Model model, HttpServletRequest request) throws Exception {
        User user = this.getLoginUser(request);
        // 根据公司 user_id  查询数据 将数据返回
        Commerce commerce = commerceMapper.selectComFlag(user.getId());
        Company company = companyMapper.selectByPrimaryKey(user.getId());

        if (null == commerce){
            model.addAttribute("comFlag","");
        }else {
            model.addAttribute("comFlag",commerce.getCom_flag());
        }
        model.addAttribute("industry",company.getIndustry());
        return "company/safety-system/danger-table";
    }


    /**
     * create by  : 小明！！！
     * description: 工贸行业数据保存
     * create time: 10:13 2019/6/21
     */
    @RequestMapping({"save-commerce-table"})
    @ResponseBody
    public Result commerce(Model model, HttpServletRequest request,String comFlag) throws Exception {
        User user = this.getLoginUser(request);
        Result result = new ResultImpl();
        if (null != comFlag && "" != comFlag){
           // 根据公司 user_id  查询数据 将数据返回
           Commerce commerce = commerceMapper.selectComFlag(user.getId());
           Commerce commerce1 = null;
           int a ;
           int b;
            if (null == commerce){
               commerce1 = new Commerce();
               commerce1.setUser_id(user.getId());
               commerce1.setCom_flag(comFlag);
               commerce1.setCtime(new Date());
               commerce1.setUtime(new Date());
               a = commerceMapper.insert(commerce1);
           }else {
               commerce1 = new Commerce();
               commerce1.setId(commerce.getId());
               commerce1.setCom_flag(comFlag);
               commerce1.setUtime(new Date());
               a = commerceMapper.updateByPrimaryKey(commerce1);
           }
           if (a == 0){
               result.setStatus("1");
               result.setMess("请选择风险项后再保存");
           }else {
               Company company = new Company();
               company.setDlevel("红色");
               b = companyMapper.updateByPrimaryKeySelective(company);
               result.setStatus("0");
               result.setMess("保存成功。");
           }
        }else {
           result.setStatus("1");
           result.setMess("数据异常请重新选择！！！");
        }
        return result;
    }






    /*
   * 化工评分页面！！！
   * */
    @RequestMapping({"grade-table"})
    public String grade(Model model, HttpServletRequest request) throws Exception {
        return "company/safety-system/grade-table";
    }


    /*
     * 化工评分页面！！！
     * */
    @RequestMapping({"grade-tables-data"})
    @ResponseBody
    public Result grades(Model model, HttpServletRequest request){
        User user = this.getLoginUser(request);
        Result result = new ResultImpl();
        try {
            // 根据 user_id 查询数据库 是否有数据
            List<DangerCoordinate> list = dangerCoordinateMapper.selectOne(user.getId());
            if (list.size() == 0){
                DangerCoordinate dangerCoordinate1 = new DangerCoordinate();
                dangerCoordinate1.setUser_id(user.getId());
                dangerCoordinate1.setDanger1(0.00);
                dangerCoordinate1.setDanger2(0.00);
                dangerCoordinate1.setDanger3(0.00);
                dangerCoordinate1.setDanger4(0.00);
                dangerCoordinate1.setDanger5(0.00);
                dangerCoordinate1.setDanger6(0.00);
                dangerCoordinate1.setDanger7(0.00);
                dangerCoordinate1.setDanger8(0.00);
                dangerCoordinate1.setDanger9(0.00);
                dangerCoordinate1.setDanger10(0.00);
                dangerCoordinate1.setDanger11(0.00);
                dangerCoordinate1.setDanger12(0.00);
                dangerCoordinate1.setDanger13(0.00);
                dangerCoordinate1.setDanger14(0.00);
                dangerCoordinate1.setDanger15(0.00);
                dangerCoordinate1.setDanger16(0.00);
                dangerCoordinate1.setDanger17(0.00);
                dangerCoordinate1.setDanger18(0.00);
                dangerCoordinate1.setDanger19(0.00);
                dangerCoordinate1.setDanger20(0.00);
                dangerCoordinate1.setDanger21(0.00);
                dangerCoordinate1.setDanger22(0.00);
                dangerCoordinate1.setDanger23(0.00);
                dangerCoordinate1.setDanger24(0.00);
                dangerCoordinate1.setDanger25(0.00);
                dangerCoordinate1.setDanger26(0.00);
                dangerCoordinate1.setDanger27(0.00);
                dangerCoordinate1.setDanger28(0.00);
                dangerCoordinate1.setDanger29(0.00);
                dangerCoordinate1.setDanger30(0.00);
                dangerCoordinate1.setDanger31(0.00);
                dangerCoordinate1.setDanger32(0.00);
                dangerCoordinate1.setDanger33(0.00);
                dangerCoordinate1.setDanger34(0.00);
                dangerCoordinate1.setDanger35(0.00);
                dangerCoordinate1.setDanger36(0.00);
                dangerCoordinate1.setDanger37(0.00);
                dangerCoordinate1.setDanger38(0.00);
                dangerCoordinate1.setDanger39(0.00);
                dangerCoordinate1.setDanger40(0.00);
                dangerCoordinate1.setDanger41(0.00);
                dangerCoordinate1.setCounts(100.00);
                dangerCoordinate1.setCtime(new Date());
                dangerCoordinate1.setUtime(new Date());
                dangerCoordinate1.setImportant1(0);
                dangerCoordinate1.setImportant2(0);
                dangerCoordinate1.setImportant3(0);
                dangerCoordinate1.setImportant4(0);
                dangerCoordinateMapper.insert(dangerCoordinate1);
                list = dangerCoordinateMapper.selectOne(user.getId());
            }
            result.setObject(list.get(0));
            result.setMess("查询成功");
            result.setStatus("0");
            return  result;
        }catch (Exception e) {
            log.error("出错了");
            result.setStatus("1");
            result.setMess("查询失败");
            return result;
        }
    }


    /*
     * 化工评分，重大风险评分表数据添加！！！
     * */
    @RequestMapping({"save-danger-coordinate"})
    @ResponseBody
    public Result coordinate(Model model, HttpServletRequest request,Integer id, Double danger1, Double danger2, Double danger3,
                             Double danger4, Double danger5,Double danger6, Double danger7, Double danger8, Double danger9,
                             Double danger10,Double danger11, Double danger12, Double danger13, Double danger14, Double danger15, Double danger16, Double danger17, Double danger18,
                             Double danger19, Double danger20, Double danger21, Double danger22, Double danger23, Double danger24, Double danger25, Double danger26, Double danger27,
                             Double danger28, Double danger29, Double danger30, Double danger31, Double danger32, Double danger33, Double danger34, Double danger35, Double danger36,
                             Double danger37, Double danger38, Double danger39, Double danger40, Double danger41, Double counts,
                             Integer important1,Integer important2,Integer important3,Integer important4) throws Exception {
        User user = this.getLoginUser(request);
        Result result = new ResultImpl();
        DangerCoordinate dangerCoordinate = new DangerCoordinate();
        Company company = new Company();
        company.setUserId(user.getId());
        // 根据 user_id  查询 id 信息
        List<DangerCoordinate> list = dangerCoordinateMapper.selectOne(user.getId());
        dangerCoordinate.setId(list.get(0).getId());
        dangerCoordinate.setUser_id(user.getId());
        dangerCoordinate.setImportant1(important1);
        dangerCoordinate.setImportant2(important2);
        dangerCoordinate.setImportant3(important3);
        dangerCoordinate.setImportant4(important4);
        if (null == danger1) {
            dangerCoordinate.setDanger1(0.00);
        } else {
            dangerCoordinate.setDanger1(danger1*1.00);
        }
        if (null == danger2) {
            dangerCoordinate.setDanger2(0.00);
        } else {
            dangerCoordinate.setDanger2(danger2*1.00);
        }
        if (null == danger3) {
            dangerCoordinate.setDanger3(0.00);
        } else {
            dangerCoordinate.setDanger3(danger3*1.00);
        }
        if (null == danger4) {
            dangerCoordinate.setDanger4(0.00);
        } else {
            dangerCoordinate.setDanger4(danger4*1.00);
        }
        if (null == danger5) {
            dangerCoordinate.setDanger5(0.00);
        } else {
            dangerCoordinate.setDanger5(danger5*1.00);
        }
        if (null == danger6) {
            dangerCoordinate.setDanger6(0.00);
        } else {
            dangerCoordinate.setDanger6(danger6*1.00);
        }
        if (null == danger7) {
            dangerCoordinate.setDanger7(0.00);
        } else {
            dangerCoordinate.setDanger7(danger7*1.00);
        }
        if (null == danger8) {
            dangerCoordinate.setDanger8(0.00);
        } else {
            dangerCoordinate.setDanger8(danger8*1.00);
        }
        if (null == danger9) {
            dangerCoordinate.setDanger9(0.00);
        } else {
            dangerCoordinate.setDanger9(danger9*1.00);
        }
        if (null == danger10) {
            dangerCoordinate.setDanger10(0.00);
        } else {
            dangerCoordinate.setDanger10(danger10*1.00);
        }
        if (null == danger11) {
            dangerCoordinate.setDanger11(0.00);
        } else {
            dangerCoordinate.setDanger11(danger11*1.00);
        }
        if (null == danger12) {
            dangerCoordinate.setDanger12(0.00);
        } else {
            dangerCoordinate.setDanger12(danger12*1.00);
        }
        if (null == danger13) {
            dangerCoordinate.setDanger13(0.00);
        } else {
            dangerCoordinate.setDanger13(danger13*1.00);
        }
        if (null == danger14) {
            dangerCoordinate.setDanger14(0.00);
        } else {
            dangerCoordinate.setDanger14(danger14*1.00);
        }
        if (null == danger15) {
            dangerCoordinate.setDanger15(0.00);
        } else {
            dangerCoordinate.setDanger15(danger15*1.00);
        }
        if (null == danger16) {
            dangerCoordinate.setDanger16(0.00);
        } else {
            dangerCoordinate.setDanger16(danger16*1.00);
        }
        if (null == danger17) {
            dangerCoordinate.setDanger17(0.00);
        } else {
            dangerCoordinate.setDanger17(danger17*1.00);
        }
        if (null == danger18) {
            dangerCoordinate.setDanger18(0.00);
        } else {
            dangerCoordinate.setDanger18(danger18*1.00);
        }
        if (null == danger19) {
            dangerCoordinate.setDanger19(0.00);
        } else {
            dangerCoordinate.setDanger19(danger19*1.00);
        }
        if (null == danger20) {
            dangerCoordinate.setDanger20(0.00);
        } else {
            dangerCoordinate.setDanger20(danger20*1.00);
        }
        if (null == danger21) {
            dangerCoordinate.setDanger21(0.00);
        } else {
            dangerCoordinate.setDanger21(danger21*1.00);
        }
        if (null == danger22) {
            dangerCoordinate.setDanger22(0.00);
        } else {
            dangerCoordinate.setDanger22(danger22*1.00);
        }
        if (null == danger23) {
            dangerCoordinate.setDanger23(0.00);
        } else {
            dangerCoordinate.setDanger23(danger23*1.00);
        }
        if (null == danger24) {
            dangerCoordinate.setDanger24(0.00);
        } else {
            dangerCoordinate.setDanger24(danger24*1.00);
        }
        if (null == danger25) {
            dangerCoordinate.setDanger25(0.00);
        } else {
            dangerCoordinate.setDanger25(danger25*1.00);
        }
        if (null == danger26) {
            dangerCoordinate.setDanger26(0.00);
        } else {
            dangerCoordinate.setDanger26(danger26*1.00);
        }
        if (null == danger27) {
            dangerCoordinate.setDanger27(0.00);
        } else {
            dangerCoordinate.setDanger27(danger27*1.00);
        }
        if (null == danger28) {
            dangerCoordinate.setDanger28(0.00);
        } else {
            dangerCoordinate.setDanger28(danger28*1.00);
        }
        if (null == danger29) {
            dangerCoordinate.setDanger29(0.00);
        } else {
            dangerCoordinate.setDanger29(danger29*1.00);
        }
        if (null == danger30) {
            dangerCoordinate.setDanger30(0.00);
        } else {
            dangerCoordinate.setDanger30(danger30*1.00);
        }
        if (null == danger31) {
            dangerCoordinate.setDanger31(0.00);
        } else {
            dangerCoordinate.setDanger31(danger31*1.00);
        }
        if (null == danger32) {
            dangerCoordinate.setDanger32(0.00);
        } else {
            dangerCoordinate.setDanger32(danger32*1.00);
        }
        if (null == danger33) {
            dangerCoordinate.setDanger33(0.00);
        } else {
            dangerCoordinate.setDanger33(danger33*1.00);
        }
        if (null == danger34) {
            dangerCoordinate.setDanger34(0.00);
        } else {
            dangerCoordinate.setDanger34(danger34*1.00);
        }
        if (null == danger35) {
            dangerCoordinate.setDanger35(0.00);
        } else {
            dangerCoordinate.setDanger35(danger35*1.00);
        }
        if (null == danger36) {
            dangerCoordinate.setDanger36(0.00);
        } else {
            dangerCoordinate.setDanger36(danger36*1.00);
        }
        if (null == danger37) {
            dangerCoordinate.setDanger37(0.00);
        } else {
            dangerCoordinate.setDanger37(danger37*1.00);
        }
        if (null == danger38) {
            dangerCoordinate.setDanger38(0.00);
        } else {
            dangerCoordinate.setDanger38(danger38*1.00);
        }
        if (null == danger39) {
            dangerCoordinate.setDanger39(0.00);
        } else {
            dangerCoordinate.setDanger39(danger39*1.00);
        }
        if (null == danger40) {
            dangerCoordinate.setDanger40(0.00);
        } else {
            dangerCoordinate.setDanger40(danger40*1.00);
        }
        if (null == danger41) {
            dangerCoordinate.setDanger41(0.00);
        } else {
            dangerCoordinate.setDanger41(danger41*1.00);
        }
        if (null == counts) {
            dangerCoordinate.setCounts(0.00);
        } else {
            dangerCoordinate.setCounts(counts*1.00);
        }
        if (important1 == 0 && important2 == 0 && important3 == 0 && important4 == 0){ // 0 未选中
            if (counts <= 100 && counts >= 90){
                company.setDlevel("蓝色");
            }else if (counts < 90 && counts >= 75){
                company.setDlevel("黄色");
            }else if (counts < 75 && counts >= 60){
                company.setDlevel("橙色");
            }else if (counts < 60 && counts >= 0){
                company.setDlevel("红色");
            }
        }else if (important1 == 1 || important2 == 1 || important3 == 1 || important4 == 1){// 选中
            company.setDlevel("红色");
        }
        dangerCoordinate.setUtime(new Date());
        boolean b = dangerCoordinateMapper.updateByPrimaryKey(dangerCoordinate);
        int a = companyMapper.updateByPrimaryKeySelective(company);
        if (b && a != 0 ) {
            result.setStatus("0");
            result.setMess("评估完成。");
        } else {
            result.setStatus("1");
            result.setMess("评估异常，请重新评估。");
        }
        return result;
    }


    /*
     * TODO 风险点辨识表
     * */
    @RequestMapping({"risk-list-update"})

    public String riskList1(Model model, HttpServletRequest request, Integer type) throws Exception {
        User user = this.getLoginUser(request);
        Company company = this.companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI());
            return "company/safety-system/type";
        } else {
            model.addAttribute("company", company);
            Map<String, Object> m = new HashMap();
            m.put("type", type);
            m.put("uid", user.getId());
            m.put("flag2", Integer.valueOf(1));
            List<Map<String, Object>> acL = this.aCompanyManualMapper.selectByMap(m);
            model.addAttribute("list", acL);
            return "company/safety-system/risk-list-update";
        }
    }

    /*
       导入所有较大风险数据 1
    */
    @RequestMapping({"risk-list-addssss"})
    @ResponseBody
    public Result riskList2(Model model, HttpServletRequest request, Integer type) throws Exception {
        Result result = new ResultImpl();
        User user = this.getLoginUser(request);
        // 根据 ID 查询公司所对应的行业信息
        Company company = this.companyMapper.selectByPrimaryKey(user.getId());
        // 根据企业ID 查询当前企业中所有的高危风险的数据 ID
        List<ACompanyManual> aCompanyManualList = aCompanyManualMapper.selectIdss(company.getUserId());
        // 根据 ids 修改删除标记为 1（已经删除）
        for (int i = 0; i < aCompanyManualList.size(); i++) {
            aCompanyManualMapper.updateAll(aCompanyManualList.get(i).getId());
        }
        ACompanyManual aCompanyManual1;
        // 根据 行业 查询该行业中所有的较大风险信息
        List<ADangerManual> aDangerManualList = aDangerManualMapper.selectFactors("1", company.getIndustry());
        if (aDangerManualList.size() != 0) {
            for (int i = 0; i < aDangerManualList.size(); i++) {
                aCompanyManual1 = new ACompanyManual();
                aCompanyManual1.setUid(user.getId());
                aCompanyManual1.setLevel3(aDangerManualList.get(i).getLevel1() + "/" + aDangerManualList.get(i).getLevel2() + "/" + aDangerManualList.get(i).getLevel3());
                aCompanyManual1.setFactors(aDangerManualList.get(i).getFactors());
                aCompanyManual1.setType(aDangerManualList.get(i).getType());
                aCompanyManual1.setMeasures(aDangerManualList.get(i).getMeasures());
                aCompanyManual1.setReference(aDangerManualList.get(i).getReference());
                aCompanyManual1.setFlag("1");
                aCompanyManual1.setLevel("橙色");
                aCompanyManual1.setDel(0);
                aCompanyManual1.setCtime(new Date());
                // 添加数据到表中
                aCompanyManualMapper.save(aCompanyManual1);
            }
            return result;
        } else {
            result.setStatus("1");
            result.setMess("该行业暂无较大风险！");
            return result;
        }
    }


    /*
     * 清空较大风险
     * */
    @RequestMapping({"risk-list-adddel"})
    @ResponseBody
    public Result riskListDel(Model model, HttpServletRequest request, Integer type) throws Exception {
        Result result = new ResultImpl();
        User user = this.getLoginUser(request);
        Company company = this.companyMapper.selectByPrimaryKey(user.getId());
        // 根据企业ID 查询当前企业中所有的高危风险的数据 ID
        List<ACompanyManual> aCompanyManualList = aCompanyManualMapper.selectIdss(company.getUserId());
        // 根据 ids 修改删除标记为 1（已经删除）
        for (int i = 0; i < aCompanyManualList.size(); i++) {
            aCompanyManualMapper.updateAll(aCompanyManualList.get(i).getId());
        }
        return result;
    }


    /*
     * 导入一般和较小风险   3
     * */
    @RequestMapping({"risk-list-add2"})
    @ResponseBody
    public Result riskListAdd2(Model model, HttpServletRequest request, Integer type) throws Exception {
        Result result = new ResultImpl();
        User user = this.getLoginUser(request);

        // 根据 ID 查询公司所对应的行业信息
        Company company = this.companyMapper.selectByPrimaryKey(user.getId());

        // 根据企业ID 查询当前企业中所有的高危风险的数据 ID
        List<ACompanyManual> aCompanyManualList = aCompanyManualMapper.selectAlls(company.getUserId());
        // 根据 ids 修改删除标记为 1（已经删除）
        for (int i = 0; i < aCompanyManualList.size(); i++) {
            aCompanyManualMapper.updateAll(aCompanyManualList.get(i).getId());
        }
        ACompanyManual aCompanyManual1;
        // 根据 行业 查询该行业中所有的较大风险信息
        List<ADangerManual> aDangerManualList = aDangerManualMapper.selectFactors("3", company.getIndustry());
        if (aDangerManualList.size() != 0) {
            for (int i = 0; i < aDangerManualList.size(); i++) {
                aCompanyManual1 = new ACompanyManual();
                aCompanyManual1.setUid(user.getId());
                aCompanyManual1.setLevel3(aDangerManualList.get(i).getLevel() + "/" + aDangerManualList.get(i).getLevel2() + "/" + aDangerManualList.get(i).getLevel3());
                aCompanyManual1.setFactors(aDangerManualList.get(i).getFactors());
                aCompanyManual1.setType(aDangerManualList.get(i).getType());
                aCompanyManual1.setMeasures(aDangerManualList.get(i).getMeasures());
                aCompanyManual1.setReference(aDangerManualList.get(i).getReference());
                aCompanyManual1.setFlag("3");
                aCompanyManual1.setDel(0);
                aCompanyManual1.setCtime(new Date());
                // 添加数据到表中
                aCompanyManualMapper.save(aCompanyManual1);
            }
            return result;
        } else {
            result.setStatus("1");
            result.setMess("该行业暂无风险！");
            return result;
        }
    }


    /*
     * 清空其它数据信息
     * */
    @RequestMapping({"risk-list-adddels"})
    @ResponseBody
    public Result adddels(Model model, HttpServletRequest request, Integer type) throws Exception {
        Result result = new ResultImpl();
        User user = this.getLoginUser(request);
        Company company = this.companyMapper.selectByPrimaryKey(user.getId());
        // 根据企业ID 查询当前企业中所有的高危风险的数据 ID
        List<ACompanyManual> aCompanyManualList = aCompanyManualMapper.selectAlls(company.getUserId());
        // 根据 id 修改删除标记为 1（已经删除）
        for (int i = 0; i < aCompanyManualList.size(); i++) {
            aCompanyManualMapper.updateAll(aCompanyManualList.get(i).getId());
        }
        return result;
    }


//        Map<String, Set<String>> list = new LinkedHashMap<String, Set<String>>();
//        for (Map<String, Object> ad : acL) {
//            String l1 = String.valueOf(ad.get("level1"));
//            String l2 = String.valueOf(ad.get("level2"));
//            if (null == list.get(l1)) {
//                list.put(l1, new LinkedHashSet<String>());
//            }
//            Set<String> s = list.get(l1);
//            s.add(l2);
//        }
//        model.addAttribute("list", list);


    /**
     * 风险辨识确定危害因素
     */
    @RequestMapping(value = "risk-list-load2")
    public String riskListLoad2(Model model, HttpServletRequest request, Integer type) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        m.put("flag", "1");
        List<Map<String, Object>> acL = aCompanyManualMapper.selectByMapGroupByLevel1Level2(m);
//        Map<String, Set<String>> list = new LinkedHashMap<String, Set<String>>();
//        for (Map<String, Object> ad : acL) {
//            String l1 = String.valueOf(ad.get("level1"));
//            String l2 = String.valueOf(ad.get("level2"));
//            if (null == list.get(l1)) {
//                list.put(l1, new LinkedHashSet<String>());
//            }
//            Set<String> s = list.get(l1);
//            s.add(l2);
//        }
//        model.addAttribute("list", list);
        model.addAttribute("dL", acL);
        model.addAttribute("type", type);
        return "company/safety-system/risk-list1-load2";
    }

    /**
     * 较大风险判定
     */
    @RequestMapping({"assess-list"})
    public String assessList(Model model, HttpServletRequest request) throws Exception {
        User user = this.getLoginUser(request);
        Company company = this.companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI());
            return "company/safety-system/type";
        } else {
            model.addAttribute("company", company);
            Map<String, Object> m = new HashMap();
            m.put("uid", user.getId());
            /*if ("化工企业（危化生产、使用）".equals(company.getIndustry())) {
                m.put("flag", "1,3,4");
            } else {
                m.put("flag", "1,3,4,5");
            }*/
            m.put("flag", "1");
            List<Map<String, Object>> acL = this.aCompanyManualMapper.selectByMap(m);
            Integer indus = 0;
            if (company.getIndustry().contains("化工")){
                indus = 1;
            }else {
                indus = 2;
            }

            model.addAttribute("indus",indus);
            model.addAttribute("list", acL);
            model.addAttribute("industry",company.getIndustry());
            model.addAttribute("depL", this.zzjgDepartmentMapper.selectLevel2ByUid(user.getId()));
            model.addAttribute("shL", this.aGwyjShMapper.selectAll());
            return "company/safety-system/assess-list";
        }
    }


    /**
     * 设置风险因素
     */
    @RequestMapping(value = "set-risk")
    public String setRisk(Model model, Integer id, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI() + "?id=" + id);
            return "company/safety-system/type";
        }
        model.addAttribute("company", company);
        model.addAttribute("ac", aCompanyManualMapper.selectByPrimaryKey(id));
        return "company/safety-system/set-risk";
    }

    /**
     * create by  : 小明！！！
     * description: 设置风险保存
     * create time: 15:22 2019/6/21
     */
    @RequestMapping(value = "set-risk-edit")
    public @ResponseBody
    Result setRiskEdit(Model model, ACompanyManual ac,
                       HttpServletRequest request,
                       String gzkhxys, String gzkwlys) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        // 根据 dep_id 去数据库查询数据
        DistinguishType distinguishType1 = distinguishTypeMapper.selectNum(ac.getId(),Integer.parseInt(ac.getType()),user.getId());
        DistinguishType distinguishType;
        if (null == distinguishType1){
            distinguishType = new DistinguishType();
            distinguishType.setCtime(new Date());
            distinguishType.setUtime(new Date());
            if (Integer.parseInt(ac.getType()) == 1){
                distinguishType.setDanger_point(ac.getHxys());
            }else if (Integer.parseInt(ac.getType()) == 2){
                distinguishType.setDanger_point(ac.getMaterial());
            }else if (Integer.parseInt(ac.getType()) == 3){
                distinguishType.setDanger_point(ac.getGy());
            }else if (Integer.parseInt(ac.getType()) == 4){
                distinguishType.setDanger_point(ac.getWlbs());
            }else if (Integer.parseInt(ac.getType()) == 5){
                distinguishType.setDanger_point(ac.getGwzy());
            }
            distinguishType.setDep_id(ac.getId());
            distinguishType.setUser_id(user.getId());
            distinguishType.setDel(0);
            distinguishType.setFlag(Integer.parseInt(ac.getType()));

            int num = distinguishTypeMapper.insert(distinguishType);

            if (0 == num){
                result.setMess("保存失败，请重新添加！！！");
            }else {
                result.setMess("保存成功。");
            }
        }else {
            distinguishType = new DistinguishType();
            distinguishType.setId(distinguishType1.getId());
            distinguishType.setUtime(new Date());
            if (Integer.parseInt(ac.getType()) == 1){
                distinguishType.setDanger_point(ac.getHxys());
            }else if (Integer.parseInt(ac.getType()) == 2){
                distinguishType.setDanger_point(ac.getMaterial());
            }else if (Integer.parseInt(ac.getType()) == 3){
                distinguishType.setDanger_point(ac.getGy());
            }else if (Integer.parseInt(ac.getType()) == 4){
                distinguishType.setDanger_point(ac.getWlbs());
            }else if (Integer.parseInt(ac.getType()) == 5){
                distinguishType.setDanger_point(ac.getGwzy());
            }
            boolean a = distinguishTypeMapper.updatePoint(distinguishType);

            if (a){
                result.setMess("保存失败，请重新添加！！！");
            }else {
                result.setMess("保存成功。");
            }
        }

       /* ACompanyManual a = aCompanyManualMapper.selectByPrimaryKey(ac.getId());

        ac.setUid(a.getUid());
        ac.setLevel1(a.getLevel1());
        ac.setLevel2(a.getLevel2());
        aCompanyManualMapper.updateByL1L2(ac);

        aCompanyManualMapper.updateCompanyDlevel(user.getId());*/


        if (null != gzkhxys || null != gzkwlys) {
            AGzk gzk = aGzkMapper.selectByPrimaryKey(user.getId());

            if (null == gzk) {
                gzk = new AGzk(user.getId(), gzkhxys, gzkwlys);
                aGzkMapper.insertSelective(gzk);
            } else {
                if ("" != gzkhxys && null != gzkhxys ){
                    if (null != gzk.getHxys() && "" != gzk.getHxys()){
                        gzk.setHxys(gzkhxys+","+gzk.getHxys());
                    }else {
                        gzk.setHxys(gzkhxys);
                    }
                }

                if ("" != gzkwlys && null != gzkwlys ){
                    if (null != gzk.getWlys() && "" != gzk.getWlys()){
                        gzk.setWlys(gzkwlys+","+gzk.getWlys());
                    }else {
                        gzk.setWlys(gzkwlys);
                    }
                }

                aGzkMapper.updateByPrimaryKeySelective(gzk);
            }
        }

        //处理两重点一重大的
        if (null != ac.getGy()) {
            Regulation r = regulationGet(user.getId());
            if (null == r) {
                r = new Regulation();
                r.setUserId(user.getId());
                if ("".equals(ac.getGy())) {
                    r.setProcess(0);
                } else {
                    r.setProcess(1);
                }
                r.setProcessName(ac.getGy());
                regulationMapper.insertSelective(r);
            } else {
                if (!"".equals(ac.getGy())) {
                    r.setProcess(1);
                    String process_name = r.getProcessName();
                    if (StringUtils.isBlank(process_name)) {
                        r.setProcessName(ac.getGy());
                        regulationMapper.updateByPrimaryKeySelective(r);
                    } else {
                        String[] gys = ac.getGy().split("!@#");
                        for (String gy : gys) {
                            if (process_name.indexOf(gy) == -1) {
                                process_name = process_name + "," + gy;
                            }
                        }
                        r.setProcessName(process_name);
                        regulationMapper.updateByPrimaryKeySelective(r);
                    }
                }
            }
        }
        return result;
    }

    /**
     * 设置风险因素
     */
    @RequestMapping(value = "set-choose")
    public String setChoose(Model model, Integer id, Integer type, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        model.addAttribute("id",id);
        DistinguishType distinguishType = distinguishTypeMapper.selectNums(id,type,user.getId());
        if (null != distinguishType){
            model.addAttribute("ac",distinguishType.getDanger_point());
        }

        /*model.addAttribute("ac", aCompanyManualMapper.selectByPrimaryKey(id));*/
        model.addAttribute("aGzk", aGzkMapper.selectByPrimaryKey(id));
        model.addAttribute("type", type);
        if (type.intValue() == 1) {//物理因素
            model.addAttribute("list", aHxysgzkMapper.selectAll());
        }
        if (type.intValue() == 2) {//化学因素
            List<AMaterial> list = aMaterialMapper.selectAll();//化学因素
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("userId", user.getId());
            List<Material> mL = materialMapper.selectByUserId(m);//原辅材料
            List<Product> pL = productMapper.selectByUserId(m);//生产产品

            List<AMaterial> list2 = new ArrayList<AMaterial>();
            for (AMaterial ama : list) {
                Boolean x = false;
                for (Material ma : mL) {
                    if (ma.getMaterial().indexOf(ama.getName()) > -1 ||
                            ama.getName().indexOf(ma.getMaterial()) > -1) {
                        list2.add(ama);
                        x = true;
                        break;
                    }
                }
                if (x) {
                    continue;
                }
                for (Product p : pL) {
                    if (p.getProductName().indexOf(ama.getName()) > -1 ||
                            ama.getName().indexOf(p.getProductName()) > -1) {
                        list2.add(ama);
                        break;
                    }
                }
            }

            model.addAttribute("list", list2);
        }
        if (type.intValue() == 3) {//工艺
            model.addAttribute("list", aGyMapper.selectAll());
        }
        if (type.intValue() == 4) {//物料
            Set<String> list = new HashSet<String>();

            List<AMaterial> amL = aMaterialMapper.selectAll();//化学因素
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("userId", getLoginUser(request).getId());
            List<Material> mL = materialMapper.selectByUserId(m);//原辅材料
            List<Product> pL = productMapper.selectByUserId(m);//生产产品

            for (Material ma : mL) {
                for (AMaterial ama : amL) {
                    if (ma.getMaterial().indexOf(ama.getName()) > -1 ||
                            ama.getName().indexOf(ma.getMaterial()) > -1) {
                        list.add(ma.getMaterial());
                        break;
                    }
                }
            }
            for (Product p : pL) {
                for (AMaterial ama : amL) {
                    if (p.getProductName().indexOf(ama.getName()) > -1 ||
                            ama.getName().indexOf(p.getProductName()) > -1) {
                        list.add(p.getProductName());
                        break;
                    }
                }
            }

            model.addAttribute("list", list);
        }
        if (type.intValue() == 5) {//高危作业
            Company company = companyMapper.selectByPrimaryKey(user.getId());
            if (StringUtils.isBlank(company.getDangers())) {
                model.addAttribute("list", new ArrayList<String>());
            } else {
                model.addAttribute("list", company.getDangers().split(","));
            }
        }
        return "company/safety-system/set-risk-load";
    }

    /**
     * 职业危害物理因素分布表
     */
    @RequestMapping(value = "risk-list2")
    public String riskList2(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI());
            return "company/safety-system/type";
        }
        AGzk gzk = aGzkMapper.selectByPrimaryKey(user.getId());
        if (null != gzk) {
            if (StringUtils.isNotBlank(gzk.getWlys())) {//物理因素
                model.addAttribute("list", aHxysgzkMapper.selectByIds(gzk.getWlys()));
            }
        }
        model.addAttribute("wlysL", aHxysgzkMapper.selectAll());
        model.addAttribute("hxysL", aMaterialMapper.selectAll());
        model.addAttribute("gzk", gzk);
        return "company/safety-system/risk-list2";
    }

    /**
     * 职业危害化学因素分布表
     */
    @RequestMapping(value = "risk-list3")
    public String riskList3(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI());
            return "company/safety-system/type";
        }
        AGzk gzk = aGzkMapper.selectByPrimaryKey(user.getId());
        if (null != gzk) {
            if (StringUtils.isNotBlank(gzk.getHxys())) {//化学因素
                model.addAttribute("list", aMaterialMapper.selectByIds(gzk.getHxys()));
            }
        }
        model.addAttribute("hxysL", aMaterialMapper.selectAll());
        model.addAttribute("gzk", gzk);
        return "company/safety-system/risk-list3";
    }

    /**
     * create by  : 小明！！！
     * description: 物理因素告知牌
     * create time: 16:38 2019/6/22
     */
    @RequestMapping(value = "harm-list")
    public String gzk(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        List<AGzk> gzk = aGzkMapper.selectByUids(user.getId());
        Set<String> s = new HashSet<String>();
        for (AGzk a : gzk) {
            if (StringUtils.isNotBlank(a.getWlys())) {
                s.addAll(Arrays.asList(a.getWlys().split(",")));
            }
        }
        if (s.size() > 0) {//物理因素
            model.addAttribute("wlysL", aHxysgzkMapper.selectByIds(StringUtils.join(s, ",")));
        }
        return "company/safety-system/harm-list";
    }

    /**
     * create by  : 小明！！！
     * description: 化学因素告知牌
     * create time: 16:38 2019/6/22
     */
    @RequestMapping(value = "risk-information-list2")
    public String riskInformationList2(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        List<AGzk> gzk = aGzkMapper.selectByUids(user.getId());
        Set<String> s = new HashSet<String>();
        for (AGzk a : gzk) {
            if (StringUtils.isNotBlank(a.getHxys())) {
                s.addAll(Arrays.asList(a.getHxys().split(",")));
            }
        }
        if (s.size() > 0) {//物理因素
            model.addAttribute("list", aMaterialMapper.selectByIds(StringUtils.join(s, ",")));
        }
        return "company/safety-system/risk-information-list2";
    }

    /**
     * 根据公司id查询所有的 车间/岗位风险分布图
     */
   /* @RequestMapping(value = "control-photo")
    @ResponseBody
    public Result selectPhoto(HttpServletRequest request,Model model ){
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        List<ImportPhoto> importPhotos = importPhotoMapper.selectPhoto(user.getId());
        if(null == importPhotos || importPhotos.size()==0 ){
            return result;
        }

        result.setMess("查询成功");
        result.setStatus("0");
        result.setObject(importPhotos);

        return result;
    }*/
    @RequestMapping(value = "control-photo")
    public String selectPhoto(HttpServletRequest request, Model model) {

        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());

        List<ImportPhoto> importPhotos = importPhotoMapper.selectPhoto(user.getId());
        if(null== importPhotos || importPhotos.size()==0){
            model.addAttribute("list", importPhotos);
            model.addAttribute("compangName",company.getName());
            return "company/safety-system/control-photo";
        }




        // 不是就表示有数据
       /* for (ImportPhoto importPhoto : importPhotos) {
            String coordinate = importPhoto.getCoordinate();
            if (null!=coordinate&&coordinate!=""){
               List<Map> list = new ArrayList<>();
                // 第一步根据 /进行切割
                String[] array = coordinate.split("/");
                for (String s1 : array) {
                    Map<String,String> map = new LinkedHashMap<String,String>();
                    String[] split1 = s1.split(",");
                    for (String s : split1) {
                        String[] split = s.split(":");
                        map.put(split[0],split[1]);
                    }
                    list.add(map);
                }
                importPhoto.setObject(list);
            }
        }*/
        model.addAttribute("list", importPhotos);
        return "company/safety-system/control-photo";

    }

    @RequestMapping(value = "modify-photo")
    @ResponseBody
    public Result modifyPhoto(HttpServletRequest request, Integer id) {

        ImportPhoto importPhoto = importPhotoMapper.selectAllById(id);
        // 判断图片是否为空
        if (null == importPhoto) {

            return null;
        }

        Result result = new ResultImpl();
        result.setObject(importPhoto);

        return result;
    }

    /**
     * TODO 保存图片坐标位置 ！！！
     * 通过id找到对应的记录,然后进行更新操作
     *
     * @param request
     * @return
     * @throws Exception images
     */
    @RequestMapping(value = "control-addCoordinate", method = RequestMethod.POST)
    @ResponseBody
    public Result selectCoordinate(HttpServletRequest request, Map<String, Object> map, Integer id, String images, String coordinate) throws Exception {

        String s ;

        // 数据解析
        if(null!=coordinate){
            StringBuffer stringBuilder = new StringBuffer(coordinate);
            stringBuilder.replace(0,1,"");
            stringBuilder.replace(stringBuilder.length()-2,stringBuilder.length(),"");


            String str = stringBuilder.toString();
            String[] split = str.split("\"");
            String strAll = "";
            for (int i = 0; i < split.length; i++) {
                if(!",".equals(split[i])){
                    // 表示是数组
                    if(i==split.length-1){
                        strAll+=split[i];
                    }else{
                        strAll+=split[i]+"/";
                    }
                }
            }

            StringBuffer stringBuilder1 = new StringBuffer(strAll);
            stringBuilder1.replace(0,1,"");
             s = stringBuilder1.toString().replaceAll("\'", "");
        }else{
            s="";
        }


        User user = getLoginUser(request);

        Result result = new ResultImpl();

        try {
            ImportPhoto importPhoto = importPhotoMapper.selectAllById(id);
            // 判断图片是否为空
            if (null == images || null == importPhoto) {
                return null;
            }

            // 设置base64数据解析
            BASE64Decoder decoder = new BASE64Decoder();
            images = images.replaceAll(" ", "+");
            byte[] bytes = decoder.decodeBuffer(images.substring(images.indexOf(",") + 1));
            images = images.replace("base64", "");
            for (int i = 0; i < bytes.length; i++) {
                if (bytes[i] < 0) {
                    //调整异常数据
                    bytes[i] += 256;
                }
            }
            // 获取绝对路径
            String realPath = request.getSession().getServletContext().getRealPath("/");
            // 获取保存的相对路径
            String path = "/images/upload/";
            // 生成图片的名称
            String photoName = UUID.randomUUID().toString().replaceAll("-", "");

            // 判断是否存在
            File file = new File(realPath + path);
            if (!file.exists() && !file.isDirectory()) {
                System.out.println("不存在");
                file.mkdir();
            }

            // 生成jpeg图片
            String imgFilePath = realPath + path + photoName + ".jpg";

            // 数据库图片路径
            String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + photoName + ".jpg";

            OutputStream ops = new FileOutputStream(imgFilePath);
            ops.write(bytes);
            ops.flush();
            ops.close();

            importPhoto.setUrl(filePath);
            importPhoto.setCoordinate(s);
            importPhotoMapper.updateByInportPhoto(importPhoto);

            result.setMess("编辑成功。");

        } catch (IOException e) {
            e.printStackTrace();
            result.setMess("编辑异常，请重新操作。");

        }
        return result;
    }

    /*
     *  TODO 车间/岗位 文件上传！！！
     */
    @RequestMapping(value = "save-photo", method = RequestMethod.POST)
    public void companyLeadin(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response,String name) throws Exception {
        Result result = exportService.photoImport(file, getLoginUser(request).getId(), request,name);
        OutPrintUtil.OutPrint(response, result);
    }


    /*
     *  TODO 车间/岗位 文件删除！！！
     */
    @RequestMapping(value = "delete-photo")
    @ResponseBody
    public Result deletePhoto(Model model, HttpServletRequest request, Integer id) throws Exception {
        User user = getLoginUser(request);
        Result result = new ResultImpl();
        Boolean aBoolean = importPhotoMapper.deletePhoto(id);
        if (aBoolean == true) {
            result.setMess("删除成功。");
            return result;
        } else {
            result.setMess("删除异常！！！");
            return result;
        }
    }


    /**
     * 主要物质理化、危险、有害特性表
     */
    @RequestMapping(value = "risk-information-detail2")
    public String riskInformationDetail2(Model model, Integer id) throws Exception {
        model.addAttribute("be", aMaterialMapper.selectByPrimaryKey(id));
        return "company/safety-system/risk-information-detail2";
    }

    /**
     * 一般和较小判定
     */
    @RequestMapping({"assess6"})
    public String assess6(Model model, HttpServletRequest request) throws Exception {
        User user = this.getLoginUser(request);
        Company company = this.companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI());
            return "company/safety-system/type";
        } else {
            model.addAttribute("company", company);
            Map<String, Object> m = new HashMap();
            m.put("uid", user.getId());
            m.put("flag", "3");
            List<Map<String, Object>> acL = this.aCompanyManualMapper.selectByFlag3(m);
            model.addAttribute("list", acL);
            Integer indus = 0;
            if (company.getIndustry().contains("化工")){
                indus = 1;
            }else {
                indus = 2;
            }
            model.addAttribute("industry",company.getIndustry());
            model.addAttribute("indus",indus);
            return "company/safety-system/assess6";
        }
    }


    /**
     * @author     ：小明！！！
     * @description：未辨识风险展示
     */
    /**
     * 一般和较小判定
     */
    @RequestMapping({"assess7"})
    public String assess7(Model model, HttpServletRequest request) throws Exception {
        User user = this.getLoginUser(request);
        Company company = this.companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI());
            return "company/safety-system/type";
        } else {
            model.addAttribute("company", company);
            Map<String, Object> m = new HashMap();
            m.put("uid", user.getId());
            m.put("flag", "3");
            List<Map<String, Object>> acL = this.aCompanyManualMapper.selectByAssess7(m);
            model.addAttribute("list", acL);
            Integer indus = 0;
            if (company.getIndustry().contains("化工")){
                indus = 1;
            }else {
                indus = 2;
            }
            model.addAttribute("industry",company.getIndustry());
            model.addAttribute("indus",indus);
            return "company/safety-system/assess7";
        }
    }




    /**
     * TODO 岗位风险公告牌 风险告知牌
     */
    @RequestMapping(value = "risk-information-list")
    public String riskInformationList(Model model, HttpServletRequest request, Integer flag) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        //加上岗位集合
       List<String> list =   zzjgDepartmentMapper.selectAllByUserId(user.getId());

       model.addAttribute("list2",list);

        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI() + "flag=" + flag);
            return "company/safety-system/type";
        }

        //所有风险
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        //m.put("flag2", 1);
        m.put("levels", new String[]{"红色", "橙色", "黄色", "蓝色"});
        List<Map<String, Object>> acL = aCompanyManualMapper.selectByMap(m);
        //所有重大较大风险
        List<Map<String, Object>> acL_f = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> ac : acL) {
            String level = getStringFromMap(ac, "level");
            if (level.equals("红色") || level.equals("橙色")) {
                acL_f.add(ac);
            }
        }

        if (acL_f.size() == 0) {//没有重大较大隐患
            model.addAttribute("flag", flag);
            return "company/safety-system/risk-information-list_0";
        }

        //事故类型列表，用于处理其中的风险
        List<AGwyjSh> shList = aGwyjShMapper.selectAll();
        //List<String> adanerTypeList = aDangerTypeMapper.selectAll();

        //公司级公告牌
        if (flag.intValue() == 1) {
            AFxgzp ggp = aFxgzpMapper.selectFlag1(user.getId());
            if (null == ggp || ggp.getIsedit().intValue() == 0) {//没有公告牌或没有修改过，重新生成
                if (null == ggp) {
                    ggp = new AFxgzp();
                }
                ggp.setFlag(1);
                ggp.setUid(user.getId());
                ggp.setGlzrr(company.getLegal());//责任人默认为法人
                ggp.setCtime(new Date());
                ggp.setDel(0);
                ggp.setIsedit(0);
                ggp.setName(company.getName());
                List<AGwyjSh> shL = setGgpXx(acL_f, ggp, shList);

                //公司的应急处置措施按照危险来
                StringBuilder yjczcs = new StringBuilder();
                for (AGwyjSh s : shL) {
                    yjczcs.append("<b>").append(s.getName()).append("：</b>").append(s.getMeasures()).append("<br>");
                }
                ggp.setYjczcs(yjczcs.toString());
                if (null == ggp.getId()) {
                    aFxgzpMapper.insertSelective(ggp);
                } else {
                    aFxgzpMapper.updateByPrimaryKeySelective(ggp);
                }

            }
            model.addAttribute("be", ggp);
            return "company/safety-system/risk-information-list_1";
        }

        //车间级
        if (flag.intValue() == 2) {
            Set<String> nameL = new HashSet<String>();//二级分类作为名称
            for (Map<String, Object> ac : acL_f) {
                String level1 = getStringFromMap(ac, "level1");
//                String level2 = getStringFromMap(ac, "level2");
//                String gkzt = getStringFromMap(ac, "gkzt");
//                if(StringUtils.isBlank(gkzt)) {
//                    continue;
//                }
//                nameL.add(gkzt);
                if (StringUtils.isBlank(level1)) {
                    continue;
                }
                nameL.add(level1);
            }

            if (nameL.size() == 0) {//没有重大较大隐患
                model.addAttribute("flag", flag);
                return "company/safety-system/risk-information-list_0";
            }
            model.addAttribute("nameL", nameL);
            return "company/safety-system/risk-information-list_2";
        }

        //岗位级
        if (flag.intValue() == 3) {
            Set<String> nameL = new HashSet<String>();//二级分类作为名称
            for (Map<String, Object> ac : acL_f) {
                String level1 = getStringFromMap(ac, "level1");
                String level2 = getStringFromMap(ac, "level2");
//                String level3 = getStringFromMap(ac, "level3");
                if (StringUtils.isBlank(level1)) {
                    continue;
                }
                nameL.add(level1 + "/" + level2);
//                if(StringUtils.isBlank(level1)) {
//                    nameL.add(level3);
//                } else {
//                    nameL.add(level1 + "/" + level2 + "/" + level3);
//                }
            }

            if (nameL.size() == 0) {//没有重大较大隐患
                model.addAttribute("flag", flag);
                return "company/safety-system/risk-information-list_0";
            }
            model.addAttribute("nameL", nameL);
            return "company/safety-system/risk-information-list_3";
        }

        m.put("flag", flag);
        model.addAttribute("flag", flag);
        model.addAttribute("list", aFxgzpMapper.selectByMap(m));
        return "company/safety-system/risk-information-list1";
    }

    //设置风险等级，事故类型，风险因素，措施
    List<AGwyjSh> setGgpXx(List<Map<String, Object>> acL, AFxgzp ggp, List<AGwyjSh> shList) {
        StringBuilder fxdj = new StringBuilder();//风险等级
        Set<String> sglx = new HashSet<String>();//事故类型
        List<AGwyjSh> shL = new LinkedList<AGwyjSh>();
        Set<String> fxys = new HashSet<String>();//风险因素
        Set<String> fxys_zd = new HashSet<String>();//重大风险因素
        Set<String> ffcs = new HashSet<String>();//防范措施
        for (Map<String, Object> ac : acL) {
            //风险等级
            String level = getStringFromMap(ac, "level");
            if (fxdj.toString().indexOf(level.replace("色", "")) == -1) {
                if (!"".equals(fxdj.toString())) {
                    fxdj.append(",");
                }
                fxdj.append(level.replace("色", ""));
            }

            //事故类型
            String type = getStringFromMap(ac, "type");
            if (StringUtils.isNotBlank(type)) {
                for (AGwyjSh e : shList) {
                    if (type.indexOf(e.getName()) > -1) {
                        if (sglx.add(e.getName())) {
                            shL.add(e);
                        }
                        ;
                    }
                }
            }

            //风险因素
            String factors = getStringFromMap(ac, "factors");
            if (StringUtils.isNotBlank(factors)) {
                if (level.equals("红色")) {
                    fxys_zd.add(factors);
                } else {
                    fxys.add(factors);
                }
            }

            //措施
            String measures = getStringFromMap(ac, "measures");
            if (StringUtils.isNotBlank(measures)) {
                ffcs.add(measures);
            }
        }

        ggp.setFxdj(fxdj.toString());//风险等级
        if (sglx.size() > 0) {//事故类型
            ggp.setSglx(StringUtils.join(sglx, "、") + "等");
        }
        ggp.setFxys(StringUtils.join(fxys, "\n"));
        ggp.setFxysZd(StringUtils.join(fxys_zd, "\n"));
        ggp.setYjczcs(StringUtils.join(ffcs, "\n"));
        return shL;
    }

    /**
     * 风险告知牌
     */
    @RequestMapping(value = "fxggp-load")
    public String fxggpLoad(Model model, HttpServletRequest request, String name) throws Exception {
        User user = getLoginUser(request);
        //事故类型列表，用于处理其中的风险
        List<AGwyjSh> adanerTypeList = aGwyjShMapper.selectAll();
        //List<String> adanerTypeList = aDangerTypeMapper.selectAll();
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        m.put("name", name);
        //m.put("flag2", 1);
        AFxgzp be = aFxgzpMapper.selectByName(m);
        if (null == be || be.getIsedit().intValue() == 0) {//没有公告牌或没有修改过，重新生成
            if (null == be) {
                be = new AFxgzp();
            }
            be.setFlag(2);
            be.setUid(user.getId());
            be.setGlzrr(company.getSafetyM());//责任人默认为安全管理员
            be.setCtime(new Date());
            be.setDel(0);
            be.setIsedit(0);
            be.setName(name);


            m.put("uid", user.getId());
            m.put("levels", new String[]{"红色", "橙色"});
            List<Map<String, Object>> acL = aCompanyManualMapper.selectByMap(m);
            //所有重大较大风险
            List<Map<String, Object>> acL_f = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> ac : acL) {
                String level1 = getStringFromMap(ac, "level1");
                if (StringUtils.isBlank(level1)) {
                    continue;
                }
                if ((level1).equals(name)) {
                    acL_f.add(ac);
                }
//                String level1 = getStringFromMap(ac, "level1");
//                String level2 = getStringFromMap(ac, "level2");
//                if(StringUtils.isBlank(level1)) {
//                    continue;
//                }
//                if((level1 + "/" + level2).equals(name)) {
//                    acL_f.add(ac);
//                }
//                String gkzt = getStringFromMap(ac, "gkzt");
//                if(StringUtils.isBlank(gkzt)) {
//                    continue;
//                }
//                if(gkzt.equals(name)) {
//                    acL_f.add(ac);
//                }
            }

            setGgpXx(acL_f, be, adanerTypeList);

            if (null == be.getId()) {
                aFxgzpMapper.insertSelective(be);
            } else {
                aFxgzpMapper.updateByPrimaryKeySelective(be);
            }

        }
        Integer load3 = 1;
        model.addAttribute("be", be);
        model.addAttribute("load3",load3);
        return "company/safety-system/risk-information-list-load";
    }

    /**
     * 风险告知牌
     */
    @RequestMapping(value = "fxggp-load3")
    public String fxggpLoad3(Model model, HttpServletRequest request, String name,String name1) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        m.put("name", name);
        //m.put("flag2", 1);
        AFxgzp be = aFxgzpMapper.selectByName(m);
        //事故类型列表，用于处理其中的风险
        List<AGwyjSh> adanerTypeList = aGwyjShMapper.selectAll();
        //List<String> adanerTypeList = aDangerTypeMapper.selectAll();
        if (null == be || be.getIsedit().intValue() == 0) {//没有公告牌或没有修改过，重新生成
            if (null == be) {
                be = new AFxgzp();
            }
            be.setFlag(3);
            be.setUid(user.getId());
            be.setCtime(new Date());
            be.setDel(0);
            be.setIsedit(0);
            be.setName(name);

            m.put("uid", user.getId());
            m.put("levels", new String[]{"红色", "橙色"});
            List<Map<String, Object>> acL = aCompanyManualMapper.selectByMap(m);
            //所有重大较大风险
            List<Map<String, Object>> acL_f = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> ac : acL) {
                String level1 = getStringFromMap(ac, "level1");
                String level2 = getStringFromMap(ac, "level2");
//                String level3 = getStringFromMap(ac, "level3");
                if (StringUtils.isBlank(level1)) {
                    continue;
                }
                if ((level1 + "/" + level2).equals(name)) {
                    acL_f.add(ac);
                    continue;
                }
//                if(StringUtils.isBlank(level1)) {
//                    if(level3.equals(name)) {
//                        acL_f.add(ac);
//                    }
//                } else {
//                    if((level1 + "/" + level2 + "/" + level3).equals(name)) {
//                        acL_f.add(ac);
//                        break;
//                    }
//                }

            }

            be.setGlzrr((String) acL_f.get(0).get("fjgkfzr"));//责任人

            setGgpXx(acL_f, be, adanerTypeList);
            if (null == be.getId()) {
                aFxgzpMapper.insertSelective(be);
            } else {
                aFxgzpMapper.updateByPrimaryKeySelective(be);
            }

        }
        Integer load3 = 2;
        model.addAttribute("name1",name1);
        model.addAttribute("be", be);
        model.addAttribute("load3",load3);
        return "company/safety-system/risk-information-list-load";
    }


    String getStringFromMap(Map<String, Object> m, String key) {
        Object o = m.get(key);
        if (null != o) {
            return o.toString();
        }
        return null;
    }

    /**
     * 风险告知牌
     */
    @RequestMapping(value = "risk-information-detail")
    public String riskInformationDetail(Model model, HttpServletRequest request, Integer id) throws Exception {
        model.addAttribute("be", aFxgzpMapper.selectByPrimaryKey(id));
        return "company/safety-system/risk-information-detail";
    }

    /**
     * 风险告知牌
     */
    @RequestMapping(value = "risk-information-add")
    public String riskInformationAdd(Model model, HttpServletRequest request, Integer id) throws Exception {
        model.addAttribute("be", aFxgzpMapper.selectByPrimaryKey(id));
        return "company/safety-system/risk-information-add";
    }

    /*
     * 风险辨识 : 现场风险操作!!!
     *
     * */
    @RequestMapping(value = "risk-list-load")//确认风险操作
    public String riskListLoad(Model model, HttpServletRequest request, String industry,
                               Integer depId) throws Exception {
        User user = getLoginUser(request);
        if (StringUtils.isNotBlank(industry)) {
            industry = utf8Str(industry);
        }
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        model.addAttribute("company", company);
        if (StringUtils.isBlank(industry)) {
            industry = company.getIndustry();
        }
        model.addAttribute("industry", industry);
        List<ADangerManual> dL = aDangerManualMapper.selectByIndustry(industry);
        Map<String, Set<String>> list = new LinkedHashMap<String, Set<String>>();
        for (ADangerManual ad : dL) {
            String l1 = ad.getLevel1();
            String l2 = ad.getLevel2();
            if (null == list.get(l1)) {
                list.put(l1, new LinkedHashSet<String>());
            }
            Set<String> s = list.get(l1);
            s.add(l2);
        }
        model.addAttribute("list", list);
        model.addAttribute("dL", dL);
        model.addAttribute("depId", depId);
        log.error("risk-list-load depId:" + depId);
        return "company/safety-system/risk-list1-load";
    }

    /*
     *  确定基础风险操作!!!
     * */
    @RequestMapping(value = "risk-list-loads")//确认风险操作
    public String riskListLoads(Model model, HttpServletRequest request, String industry,
                                Integer depId) throws Exception {
        User user = getLoginUser(request);
        if (StringUtils.isNotBlank(industry)) {
            industry = utf8Str(industry);
        }
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        model.addAttribute("company", company);
        if (StringUtils.isBlank(industry)) {
            industry = company.getIndustry();
        }

        List<TLevel> dL = tLevelMapper.selectAll();
        Map<String, Set<String>> list = new LinkedHashMap<String, Set<String>>();
        for (TLevel ad : dL) {
            String l1 = ad.getLevel1();
            String l2 = ad.getLevel2();
            if (null == list.get(l1)) {
                list.put(l1, new LinkedHashSet<String>());
            }
            Set<String> s = list.get(l1);
            s.add(l2);
        }
        model.addAttribute("list", list);
        model.addAttribute("dL", dL);
        model.addAttribute("depId", depId);
        log.error("risk-list-loads depId:" + depId);
        return "company/safety-system/risk-list1-loads";
    }





    /*
     * 风险辨识 : 确定风险操作!!!
     * */
    /*@RequestMapping(value = "risk-list-load")//确认风险操作
    public String riskListLoad(Model model, HttpServletRequest request, String industry,
                               String name) throws Exception {
        User user = getLoginUser(request);

        if(StringUtils.isNotBlank(industry)) {
        if (StringUtils.isNotBlank(industry)) {
            industry = utf8Str(industry);
        }
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        model.addAttribute("company", company);
        if (StringUtils.isBlank(industry)) {
            industry = company.getIndustry();
            if (industry.equals("化工企业（危化生产、使用）")) {
                if (company.getName().contains("油")) {
                    industry = "加油站";
                }
            }
        }
        model.addAttribute("industry", industry);
        List<ADangerManual> dL = aDangerManualMapper.selectByIndustry(industry);
        Map<String, Set<String>> list = new LinkedHashMap<String, Set<String>>();
        for (ADangerManual ad : dL) {
            String l1 = ad.getLevel1();
            String l2 = ad.getLevel2();
            if (null == list.get(l1)) {
                list.put(l1, new LinkedHashSet<String>());
            }
            Set<String> s = list.get(l1);
            s.add(l2);
        }
        model.addAttribute("list", list);
        model.addAttribute("dL", dL);
        *//*model.addAttribute("depId", depId);
        log.error("risk-list-load depId:"+depId);*//*
        return "company/safety-system/risk-list1-load";
    }
*/


    /**
     * 实施方案
     */
    @RequestMapping(value = "plan-add")
    public String companyList(Model model, HttpServletRequest request, Integer type) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI());
            return "company/safety-system/type";
        }
        AImplementation im = aImplementationMapper.selectByPrimaryKey(user.getId());
        if (null != im) {
            model.addAttribute("im", gson.toJson(im));
        } else {
            model.addAttribute("im", gson.toJson(new AImplementation()));
        }
        model.addAttribute("type", type);
        return "company/safety-system/plan-add";
    }

    /**
     * 化工企业-危险化学品生产储存企业安全风险评估诊断分级指南
     */
    @RequestMapping(value = "assess1")
    public String assess1(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI());
            return "company/safety-system/type";
        }
        model.addAttribute("bean", aWhpMapper.selectByPrimaryKey(user.getId()));
        return "company/safety-system/assess1";
    }

    /**
     * 作业条件风险程度评价
     */
    @RequestMapping(value = "assess2")
    public String assess2(Model model, Integer id) throws Exception {
//        model.addAttribute("bean", aZytjfxcdpjMapper.selectByPrimaryKey(id));
//        model.addAttribute("id", id);
        return "company/safety-system/assess2";
    }

    /**
     * 风险矩阵法
     */
    @RequestMapping(value = "assess3")
    public String assess3(Model model, Integer id) throws Exception {
//        model.addAttribute("bean", aFxjzMapper.selectByPrimaryKey(id));
//        model.addAttribute("id", id);
        return "company/safety-system/assess3";
    }

    /**
     * 危险化学品生产储存企业安全风险评估诊断分级指南
     */
    @RequestMapping(value = "assess-bei")
    public String assessBei(Model model, String industry, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI());
            return "company/safety-system/type";
        }
        if (StringUtils.isEmpty(industry)) {
            industry = company.getIndustry();
        }

        //industry = utf8Str(industry);
        List<ADangerManual> dL = aDangerManualMapper.selectByIndustry(industry);
        Map<String, Set<String>> list = new LinkedHashMap<String, Set<String>>();
        for (ADangerManual ad : dL) {
            String l1 = ad.getLevel1();
            String l2 = ad.getLevel2();
            if (null == list.get(l1)) {
                list.put(l1, new LinkedHashSet<String>());
            }
            Set<String> s = list.get(l1);
            s.add(l2);
        }
        model.addAttribute("list", list);
        model.addAttribute("industry", industry);
        return "company/safety-system/assess-bei";
    }

    /**
     * 风险矩阵法
     */
    @RequestMapping(value = "assess4")
    public String assess4(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI());
            return "company/safety-system/type";
        }
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        model.addAttribute("list", aCompanyManualMapper.selectByMap(m));
        return "company/safety-system/assess4";
    }

    /**
     * 重大风险判定
     */
    @RequestMapping(value = "assess5")
    public String assess5(Model model, HttpServletRequest request, String number) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI());
            return "company/safety-system/type";
        }
        List<ACompanyManual> aCompanyManualList = aCompanyManualMapper.findALLsss("2", user.getId());
        Integer indus = 0;
        if (company.getIndustry().contains("化工")){
            indus = 1;
        }else {
            indus = 2;
        }
        model.addAttribute("indus",indus);
        model.addAttribute("industry",company.getIndustry());
        model.addAttribute("company", company);
        model.addAttribute("list", aCompanyManualList);
        return "company/safety-system/assess5-2";
    }

    /**
     * 重大风险 化工企业选择
     */
    @RequestMapping(value = "assess5-2-choose")
    public String assess52Choose(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        m.put("flag", 2);
        model.addAttribute("list", gson.toJson(aCompanyManualMapper.selectByMap(m)));
        return "company/safety-system/assess5-2-choose";
    }

    /*
     * 分级管控页面展示！！！
     * */
    @RequestMapping(value = "control-list")
    public String controlList(Model model, HttpServletRequest request, Integer type) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        m.put("order", 1);

        List<Map<String, Object>> list = aCompanyManualMapper.selectByAcs(m);
        model.addAttribute("list", list);
       /* List<ZzjgPersonnel> zzjgPersonnelList = null;
        List<Company> companyList = null;
        for(Map<String, Object> name : list){
            String names = null;
            if (null == (String)name.get("flag").toString() || Integer.parseInt((String)name.get("flag").toString()) != 2){ // 较大
                names = (String)name.get("gkzt");
                model.addAttribute("gkzs",name.get("gkzt"));
                // 根据 aCompanyManual 中 dmid  查询 对应的 公司负责人的名称
                zzjgPersonnelList = zzjgPersonnelMapper.selectName((Integer) name.get("dmid"),user.getId());
            }else if(Integer.parseInt((String)name.get("flag").toString()) == 2){ // 重大
                companyList = companyMapper.selectByPrimaryKeys(user.getId());
            }
        }
        
        model.addAttribute("zzjgPersonnelList", zzjgPersonnelList);
        model.addAttribute("companyList", companyList);*/

        Map<String, LinkedHashSet<String>> levmap = new HashMap<String, LinkedHashSet<String>>();

        for (Map<String, Object> m1 : list) {
            String level1 = null == m1.get("level1") ? "" : m1.get("level1").toString();
            String level2 = null == m1.get("level2") ? "" : m1.get("level2").toString();
            LinkedHashSet<String> l2s = levmap.get(level1);
            if (null == l2s) {
                l2s = new LinkedHashSet<String>();
                levmap.put(level1, l2s);
            }
            l2s.add(level2);
        }
        model.addAttribute("treeMap", levmap);

        model.addAttribute("type", type);

        model.addAttribute("company", companyMapper.selectByPrimaryKey(user.getId()));

        model.addAttribute("departL", zzjgDepartmentMapper.selectLevel1ByUid(user.getId()));//组织架构部门班组

        LlHashMap<Object, Object> llm = getLlMap();
        llm.put("uid", user.getId());
        model.addAttribute("perL", zzjgPersonnelMapper.selectByMap(llm));//组织架构部门班组
        return "company/safety-system/control-list";
    }


    /*
     * 根据车间查询对应的责任人信息！！！
     */
    @RequestMapping(value = "control-list-person")
    public @ResponseBody
    List<Map<String, Object>> controlListsPerson(Model model, HttpServletRequest request, String name, Integer pid) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        List<Map<String, Object>> llHashMaps = zzjgPersonnelMapper.selectHashMap(user.getId(), pid);//组织架构部门班组
        return llHashMaps;
    }


    /*
     * 查询相应管控主体下的部门信息！！！
     */
    @RequestMapping(value = "control-list-one")
    public @ResponseBody
    List<ZzjgDepartment> controlLists(Model model, HttpServletRequest request, String name, Integer pid) throws Exception {

        User user = getLoginUser(request);
        List<ZzjgDepartment> zzjgDepartmentList1 = zzjgDepartmentMapper.selectOnes(pid);

        return zzjgDepartmentList1;
    }


    /**
     * 体系文件 组织机构及职责
     */
    @RequestMapping(value = "plan-add2")
    public String planAdd2(Model model, HttpServletRequest request, Integer type) throws Exception {
        User user = getLoginUser(request);
        AImplementation im = aImplementationMapper.selectByPrimaryKey(user.getId());
        model.addAttribute("im", im);
        model.addAttribute("now", new Date());
        if (null != im && StringUtils.isNotBlank(im.getTextz1())) {
            model.addAttribute("per", im.getTextz1().split("\\|"));
        } else {
            model.addAttribute("per", new String[]{"", "", ""});
        }
        return "company/safety-system/plan-add2";
    }

    /**
     * 体系文件 组织机构及职责
     */
    @RequestMapping(value = "plan-add1")
    public String planAdd1(Model model, HttpServletRequest request, Integer type) throws Exception {
        User user = getLoginUser(request);
        LlHashMap<Object, Object> llm = getLlMap();
        llm.put("uid", user.getId());
        model.addAttribute("perL", zzjgPersonnelMapper.selectByMap(llm));//组织架构部门班组
        AImplementation im = aImplementationMapper.selectByPrimaryKey(user.getId());
        model.addAttribute("im", im);
        model.addAttribute("company", companyMapper.selectByPrimaryKey(user.getId()));
        model.addAttribute("now", new Date());
        if (null != im && StringUtils.isNotBlank(im.getTextz1())) {
            model.addAttribute("per", im.getTextz1().split("\\|"));
        } else {
            model.addAttribute("per", new String[]{"", "", "", ""});
        }
        return "company/safety-system/plan-add1";
    }

    /**
     * 体系文件 颁布令
     */
    @RequestMapping(value = "plan-add0")
    public String planAdd0(Model model, HttpServletRequest request, Integer type) throws Exception {
        User user = getLoginUser(request);
        LlHashMap<Object, Object> llm = getLlMap();
        llm.put("uid", user.getId());
        AImplementation im = aImplementationMapper.selectByPrimaryKey(user.getId());
        model.addAttribute("im", im);
        model.addAttribute("company", companyMapper.selectByPrimaryKey(user.getId()));
        model.addAttribute("now", new Date());
        return "company/safety-system/plan-add0";
    }


    /**
     * TODO 评估结果
     * @param model
     * @param request
     * @param flag
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "control-list2")//zhangcl 2018.10.13
    public String controlList2(Model model, HttpServletRequest request, String flag) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        m.put("order", 1);

        model.addAttribute("company", company);
        model.addAttribute("user", userMapper.selectByPrimaryKey(company.getUserId()));
        model.addAttribute("v", userMapper.selectByPrimaryKey(company.getVillageId()));
        List<Map<String, Object>> list = aCompanyManualMapper.selectByMap(m);
        List<Map<String, Object>> list2 = aCompanyManualMapper.selectByMapTwo(m);
        model.addAttribute("list", list);
        model.addAttribute("list2", list2);

        Map<String, LinkedHashSet<String>> levmap = new HashMap<String, LinkedHashSet<String>>();
        for (Map<String, Object> m1 : list) {
            String level1 = null == m1.get("level1") ? "" : m1.get("level1").toString();
            //log.error("level1：-----------start------------"+level1);
            String level2 = null == m1.get("level2") ? "" : m1.get("level2").toString();
            //log.error("level2："+level2);
            LinkedHashSet<String> l2s = levmap.get(level1);
            if (null == l2s) {
                l2s = new LinkedHashSet<String>();
                //log.error("level1："+level1);
                levmap.put(level1, l2s);
                //log.error("levmap："+levmap.toString());
            }
            l2s.add(level2);
            //log.error("l2s：------------end-----------"+l2s.toString());
        }
        //log.error("levmap："+levmap.toString());
        model.addAttribute("treeMap", levmap);
        model.addAttribute("flag", flag);

        /*
         * 添加根据风险等级查询，查询结果可能会受到其他修改风险等级功能的影响	wz_20181116
         */
        m.put("level", "红色");
        List<Map<String, Object>> list11 = aCompanyManualMapper.selectByMap(m);
        model.addAttribute("list11", list11);

        m.put("level", "橙色");
        List<Map<String, Object>> list22 = aCompanyManualMapper.selectByMap(m);
        model.addAttribute("list22", list22);


        m.put("level", "黄色");
        List<Map<String, Object>> list33 = aCompanyManualMapper.selectByMap(m);
        model.addAttribute("list33", list33);

        m.put("level", "蓝色");
        List<Map<String, Object>> list44 = aCompanyManualMapper.selectByMap(m);
        model.addAttribute("list44", list44);

        m.put("level", "");
        List<Map<String, Object>> list55 = aCompanyManualMapper.selectNum(m);
        model.addAttribute("list55", list55);

        if (flag.equals("2")) {
            //log.error("zhangcl 2018.10.18 controlList3,area_range="+company.getAreaRange());
            return "company/safety-system/control-list3";
        } else {
            return "company/safety-system/control-list2";
        }

    }

    /**
     * 处理风险标点
     */
    @RequestMapping(value = "control-list3")//zhangcl 2018.10.13
    public String controlList3(Model model, HttpServletRequest request, Integer id, String lnglat) throws Exception {
        //log.error("zhangcl 2018.10.13 controlList3,id="+id+",lnglat="+lnglat);
        aCompanyManualMapper.updateLnglat(id, lnglat);
        return controlList2(model, request, "2");
    }

    @RequestMapping(value = "area-range-save")
    public String AreaRangeSave(Model model, HttpServletRequest request, String arearange) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        company.setAreaRange(arearange);
        companyMapper.updateByPrimaryKeySelective(company);
        return controlList2(model, request, "2");
    }

    /**
     * 实施方案-保存
     */
    @RequestMapping(value = "plan-save")
    public @ResponseBody
    Result companySave(HttpServletRequest request, AImplementation i) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        AImplementation im = aImplementationMapper.selectByPrimaryKey(user.getId());
        i.setUid(user.getId());
        if (null == im) {
            aImplementationMapper.insertSelective(i);
        } else {
            aImplementationMapper.updateByPrimaryKeySelective(i);
        }
        return result;
    }

    /**
     * 企业所属行业-保存
     */
    @RequestMapping(value = "company-industry")
    public @ResponseBody
    Result companyIndustry(HttpServletRequest request, String industry) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        Company c = new Company();
        c.setUserId(user.getId());
        c.setIndustry(industry);
        companyMapper.updateByPrimaryKeySelective(c);
        return result;
    }

    /**
     * 企业风险辨识-保存
     */
    @RequestMapping(value = "aCompanyManual-save")
    public @ResponseBody
    Result aCompanyManualSave(Model model,HttpServletRequest request, ACompanyManual be, Integer ids, String dianhuas,String dataId) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        StringBuffer sb = new StringBuffer();

        if (null != dataId && "" != dataId){
            String[] str = dataId.split(",");

            for (int i = 0; i < str.length ; i++) {
                // 根据员工 ID 查询对应的数据 信息
                ZzjgPersonnel zzjgPersonnel = zzjgPersonnelMapper.selectByPrimaryKey(Integer.parseInt(str[i]));
                if (null != zzjgPersonnel){
                    if (i == str.length-1){
                        sb.append(zzjgPersonnel.getName()+"-"+zzjgPersonnel.getMobile());
                    }else {
                        sb.append(zzjgPersonnel.getName()+"-"+zzjgPersonnel.getMobile()).append(",");
                    }

                }
            }
        }else {
            sb.append("");
        }
        be.setUid(user.getId());
        be.setDmid(ids);
        be.setLevel1(be.getGkzt());
        be.setLevel2(be.getLevel2());
        be.setFjgkfzr(sb.toString());
        be.setType(be.getType());
        if (null == be.getId()) {
            be.setDel(0);
            be.setCtime(new Date());
            aCompanyManualMapper.insertSelective(be);
        } else {
            aCompanyManualMapper.updateByPrimaryKeySelective(be);
        }
        aCompanyManualMapper.updateCompanyDlevel(user.getId());
        return result;
    }


    /*
     * 现场管理数据的添加 ！！！
     * */
    @RequestMapping(value = "aCompanyManual-save1")
    public @ResponseBody
    Result aCompanyManualSave1(HttpServletRequest request, Integer[] ids, Integer depId) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        // 根据公司 ID 查询对应的 CID 信息
        List<ZzjgCompany> zzjgCompanyList = zzjgCompanyMapper.selectAll(user.getId());
        // 根据 ID 查询对应的车间名称
        ZzjgDepartment dep = zzjgDepartmentMapper.selectByPrimaryKey(depId);
        String level1 = dep.getName();
        List<ADangerManual> list = aDangerManualMapper.selectByAllIds(ids);
        ACompanyManual aCompanyManual;
        for (ADangerManual a : list) {
            aCompanyManual = new ACompanyManual();
            aCompanyManual.setUid(user.getId());
            aCompanyManual.setLevel1(level1);
            aCompanyManual.setLevel2(a.getName());
            aCompanyManual.setLevel3(a.getLevel1() + "/" + a.getLevel2() + "/" + a.getLevel3());
            aCompanyManual.setFactors(a.getFactors());
            aCompanyManual.setReference(a.getReference());
            aCompanyManual.setCtime(new Date());
            aCompanyManual.setDel(0);
            aCompanyManual.setDmid(depId);
            aCompanyManual.setLevel(a.getLevel());
            aCompanyManual.setFlag(a.getFlag());
            aCompanyManual.setType(a.getType());
            aCompanyManual.setMeasures(a.getMeasures());
            aCompanyManual.setRiskId(a.getId());
            String fjgkfzr = null;
            List<ZzjgDepartment> zzjgDepartment1 = zzjgDepartmentMapper.selectNameLevel2(user.getId(),depId,"负责人",2);
            if (zzjgDepartment1.size() != 0){
                // 根据 ID 查询 name
                List<ZzjgPersonnel> zzjgPersonnel1 = zzjgPersonnelMapper.selectDpidAndDid(zzjgDepartment1.get(0).getId(),depId);
                if (null != zzjgPersonnel1 && zzjgPersonnel1.size() != 0){
                    fjgkfzr = zzjgPersonnel1.get(0).getName()+"-"+zzjgPersonnel1.get(0).getMobile();
                }
            }

            // 根据公司 ID  和 车间 ID 查询部门名称为：负责人的名称
            if (Integer.parseInt(a.getFlag()) == 2 && a.getLevel().equals("红色")){
                List<Company> companyList = companyMapper.selectByPrimaryKeys(user.getId());
                aCompanyManual.setFjgkfzr(fjgkfzr+","+companyList.get(0).getCharge()+"-"+companyList.get(0).getChargeContact());
                aCompanyManual.setGkzt("公司");
            }else if (null == a.getFlag() || Integer.parseInt(a.getFlag()) != 2){
                aCompanyManual.setFjgkfzr(fjgkfzr);
                aCompanyManual.setGkzt(level1);
            }

            aCompanyManualMapper.insert(aCompanyManual);
            // 给 zzjg_department_tbl 添加数据信息
            List<ZzjgDepartment> zzjgDepartmentList = zzjgDepartmentMapper.selectCount(depId, a.getName());
            if (zzjgDepartmentList.size() != 0) {
                for (int i = 0; i < zzjgDepartmentList.size(); i++) {
                    zzjgDepartmentMapper.updateAll(new Date(), zzjgDepartmentList.get(i).getId());
                }
            } else {
                ZzjgDepartment zzjgDepartment = new ZzjgDepartment();
                zzjgDepartment.setCtime(new Date());
                zzjgDepartment.setUtime(new Date());
                zzjgDepartment.setDel(0);
                zzjgDepartment.setName(a.getName());
                for (int i = 0; i < zzjgCompanyList.size(); i++) {
                    zzjgDepartment.setCid(zzjgCompanyList.get(i).getId());
                }
                zzjgDepartment.setPid(depId);
                zzjgDepartment.setLevel(2);
                zzjgDepartment.setUid(user.getId());
                zzjgDepartment.setDangerId("1");
                zzjgDepartment.setFlag(2);
                zzjgDepartmentMapper.add(zzjgDepartment);
            }
        }
        return result;
    }


    /*
     * 基础风险保存数据的添加 !!!
     * */
    @RequestMapping(value = "aCompanyManual-save2")
    public @ResponseBody
    Result aCompanyManualSave1s(HttpServletRequest request, Integer[] ids, Integer depId) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        // 根据公司 ID 查询对应的 CID 信息
        List<ZzjgCompany> zzjgCompanyList = zzjgCompanyMapper.selectAll(user.getId());
        ZzjgDepartment dep = zzjgDepartmentMapper.selectByPrimaryKey(depId);
        String level1 = dep.getName();
        List<TLevel> tLevelList = tLevelMapper.selectAllIds(ids);
        ACompanyManual aCompanyManual;
        for (TLevel a : tLevelList) {
            aCompanyManual = new ACompanyManual();
            aCompanyManual.setReference(a.getReference());
            aCompanyManual.setUid(user.getId());
            aCompanyManual.setLevel1(level1);
            aCompanyManual.setLevel2(a.getName());
            aCompanyManual.setLevel3(a.getLevel1() + "/" + a.getLevel2() + "/" + a.getLevel3());
            aCompanyManual.setFactors(a.getFactors());
            aCompanyManual.setGkzt(level1);
            aCompanyManual.setCtime(new Date());
            aCompanyManual.setDel(0);
            aCompanyManual.setDmid(depId);
            if (null != a.getFlag()){
                aCompanyManual.setFlag(Integer.toString(a.getFlag()));
            }else {
                aCompanyManual.setFlag("");
            }
            if (null != a.getLevel()){
                aCompanyManual.setLevel(a.getLevel());
            }else {
                aCompanyManual.setLevel("");
            }
            aCompanyManual.setType(a.getType());
            aCompanyManual.setMeasures(a.getMeasures());
            aCompanyManual.setRiskId(a.getId());


            String fjgkfzr = null;
            List<ZzjgDepartment> zzjgDepartment1 = zzjgDepartmentMapper.selectNameLevel2(user.getId(),depId,"负责人",2);
            if (zzjgDepartment1.size() != 0){
                // 根据 ID 查询 name
                List<ZzjgPersonnel> zzjgPersonnel1 = zzjgPersonnelMapper.selectDpidAndDid(zzjgDepartment1.get(0).getId(),depId);
                if (null != zzjgPersonnel1 && zzjgPersonnel1.size() != 0){
                    fjgkfzr = zzjgPersonnel1.get(0).getName()+"-"+zzjgPersonnel1.get(0).getMobile();
                }
            }
            // 根据公司 ID  和 车间 ID 查询部门名称为：负责人的名称
            if (null == a.getFlag() || a.getFlag() != 2){
                aCompanyManual.setFjgkfzr(fjgkfzr);
                aCompanyManual.setGkzt(level1);
            }else if (null != a.getFlag() && a.getFlag() == 2 && a.getLevel().equals("红色")){
                List<Company> companyList = companyMapper.selectByPrimaryKeys(user.getId());
                aCompanyManual.setFjgkfzr(fjgkfzr+","+companyList.get(0).getCharge()+"-"+companyList.get(0).getChargeContact());
                aCompanyManual.setGkzt("公司");
            }
            aCompanyManualMapper.insertAdd(aCompanyManual);
            // 给 zzjg_department_tbl 添加数据信息
            List<ZzjgDepartment> zzjgDepartmentList = zzjgDepartmentMapper.selectCount(depId, a.getName());
            if (zzjgDepartmentList.size() != 0) {
                for (int i = 0; i < zzjgDepartmentList.size(); i++) {
                    zzjgDepartmentMapper.updateAll(new Date(), zzjgDepartmentList.get(i).getId());
                }
            } else {
                ZzjgDepartment zzjgDepartment = new ZzjgDepartment();
                zzjgDepartment.setCtime(new Date());
                zzjgDepartment.setUtime(new Date());
                zzjgDepartment.setDel(0);
                zzjgDepartment.setName(a.getName());
                for (int i = 0; i < zzjgCompanyList.size(); i++) {
                    zzjgDepartment.setCid(zzjgCompanyList.get(i).getId());
                }
                zzjgDepartment.setPid(depId);
                zzjgDepartment.setLevel(2);
                zzjgDepartment.setUid(user.getId());
                zzjgDepartment.setDangerId("2");
                zzjgDepartment.setFlag(2);
                zzjgDepartmentMapper.add(zzjgDepartment);
            }
        }
        return result;
    }


    /**
     * 危险化学品生产储存企业安全风险评估诊断分级指南（试行）-保存
     */
    @RequestMapping(value = "assess1-save")
    public @ResponseBody
    Result assess1Save(HttpServletRequest request, AWhp be) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        be.setUid(user.getId());
        AWhp a = aWhpMapper.selectByPrimaryKey(user.getId());
        if (null != a) {
            aWhpMapper.updateByPrimaryKeySelective(be);
        } else {
            aWhpMapper.insertSelective(be);
        }
        return result;
    }

    /**
     * 企业风险辨识-保存
     */
    @RequestMapping(value = "assess2-save")
    public @ResponseBody
    Result assess2Save(HttpServletRequest request, AZytjfxcdpj be) throws Exception {
        Result result = new ResultImpl();
        AZytjfxcdpj a = aZytjfxcdpjMapper.selectByPrimaryKey(be.getUid());
        if (null != a) {
            aZytjfxcdpjMapper.updateByPrimaryKeySelective(be);
        } else {
            aZytjfxcdpjMapper.insertSelective(be);
        }

        String level = be.getLev();
        Integer id = be.getUid();
        AFxjz af = aFxjzMapper.selectByPrimaryKey(id);
        if (null != af) {
            level = getDanger(af.getLev(), level);
        }
        ACompanyManualZp zp = aCompanyManualZpMapper.selectByPrimaryKey(id);
        if (null != zp) {
            level = getDanger(zp.getLevel(), level);
        }

        ACompanyManual acm = new ACompanyManual();
        acm.setId(id);
        acm.setLevel(level);
        aCompanyManualMapper.updateByPrimaryKeySelective(acm);
        aCompanyManualMapper.updateCompanyDlevel(getLoginUser(request).getId());
        return result;
    }

    String getDanger(String danger, String danger1) {
        if (StringUtils.isBlank(danger)) {
            return danger1;
        }
        if (StringUtils.isBlank(danger1)) {
            return danger;
        }
        Map<String, Integer> mm = new HashMap<String, Integer>();
        mm.put("红色", 4);
        mm.put("橙色", 3);
        mm.put("黄色", 2);
        mm.put("蓝色", 1);
        if (mm.get(danger1).compareTo(mm.get(danger)) > 0) {
            return danger1;
        }
        return danger;
    }

    /**
     * 风险举证-保存
     */
    @RequestMapping(value = "assess3-save")
    public @ResponseBody
    Result assess3Save(HttpServletRequest request, AFxjz be) throws Exception {
        Result result = new ResultImpl();
        AFxjz a = aFxjzMapper.selectByPrimaryKey(be.getUid());
        if (null != a) {
            aFxjzMapper.updateByPrimaryKeySelective(be);
        } else {
            aFxjzMapper.insertSelective(be);
        }
        String level = be.getLev();
        Integer id = be.getUid();
        AZytjfxcdpj aZytjfxcdpj = aZytjfxcdpjMapper.selectByPrimaryKey(id);
        if (null != aZytjfxcdpj) {
            level = getDanger(aZytjfxcdpj.getLev(), level);
        }
        ACompanyManualZp zp = aCompanyManualZpMapper.selectByPrimaryKey(id);
        if (null != zp) {
            level = getDanger(zp.getLevel(), level);
        }
        ACompanyManual acm = new ACompanyManual();
        acm.setId(id);
        acm.setLevel(level);
        aCompanyManualMapper.updateByPrimaryKeySelective(acm);
        aCompanyManualMapper.updateCompanyDlevel(getLoginUser(request).getId());
        return result;
    }

    /**
     * 一般与较小自评
     */
    @RequestMapping({"zp-save"})
    @ResponseBody
    public Result zpSave(Integer id, String level, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        ACompanyManual acm = new ACompanyManual();
        acm.setId(id);
        acm.setLevel(level);

        ACompanyManual aCompanyManual = aCompanyManualMapper.selectByPrimaryKey(id);
        String fjgkfzr = null;
        List<ZzjgDepartment> zzjgDepartment1 = zzjgDepartmentMapper.selectNameLevel2(user.getId(),aCompanyManual.getDmid(),"负责人",2);
        if (null != zzjgDepartment1 && zzjgDepartment1.size() != 0){
            // 根据 ID 查询 name
            ZzjgPersonnel zzjgPersonnel1 = zzjgPersonnelMapper.selectNameDid(zzjgDepartment1.get(0).getId());

            if (null != zzjgPersonnel1){
                fjgkfzr = zzjgPersonnel1.getName()+"-"+zzjgPersonnel1.getMobile();
            }else {
                fjgkfzr = "";
            }
        }

        if (level != null && level.equals("红色")) {
            List<Company> companyList = companyMapper.selectByPrimaryKeys(user.getId());
            if (companyList.size() != 0){
                acm.setFjgkfzr(fjgkfzr+","+companyList.get(0).getCharge()+"-"+companyList.get(0).getChargeContact());
            }else{
                acm.setFjgkfzr("");
            }
            acm.setGkzt("公司");
            acm.setFlag("2");
        }

        if (level != null && level.equals("橙色")) {
            if (zzjgDepartment1.size() != 0){
                acm.setFjgkfzr(fjgkfzr);
            }else {
                acm.setFjgkfzr("");
            }
            acm.setGkzt(aCompanyManual.getLevel1());
            acm.setFlag("1");
        }

        if (level != null && level.equals("黄色")) {
            if (zzjgDepartment1.size() != 0){
                acm.setFjgkfzr(fjgkfzr);
            }else {
                acm.setFjgkfzr("");
            }
            acm.setGkzt(aCompanyManual.getLevel1());
            acm.setFlag("3");
        }

        if (level != null && level.equals("蓝色")) {
            if (zzjgDepartment1.size() != 0){
                acm.setFjgkfzr(fjgkfzr);
            }else {
                acm.setFjgkfzr("");
            }
            acm.setGkzt(aCompanyManual.getLevel1());
            acm.setFlag("3");
        }

        this.aCompanyManualMapper.updateByPrimaryKeySelective(acm);
        this.aCompanyManualMapper.updateCompanyDlevel(this.getLoginUser(request).getId());
        return result;
    }

    /**
     * 告知牌-保存
     */
    @RequestMapping(value = "risk-information-save")
    public @ResponseBody
    Result riskInformationSave(HttpServletRequest request, AFxgzp be) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        be.setUid(user.getId());
        if (null != be.getId()) {
            aFxgzpMapper.updateByPrimaryKeySelective(be);
        } else {
            be.setCtime(new Date());
            be.setDel(0);
            aFxgzpMapper.insertSelective(be);
        }
        return result;
    }

    /**
     * 告知卡-保存
     */
    @RequestMapping(value = "gzk-save")
    public @ResponseBody
    Result gzkSave(HttpServletRequest request, AGzk be) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        be.setUid(user.getId());
        AGzk g = aGzkMapper.selectByPrimaryKey(user.getId());
        if (null == g) {
            aGzkMapper.insertSelective(be);
        } else {
            aGzkMapper.updateByPrimaryKeySelective(be);
        }
        return result;
    }


    /**
     * 重大风险-保存
     */
    @RequestMapping(value = "a-zdfxys-save2")
    public @ResponseBody
    Result assess5Save2(HttpServletRequest request, String json) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        aCompanyManualMapper.deleteFlag2(user.getId());
        if (StringUtils.isBlank(json)) {
            return result;
        }
        List<LinkedHashMap<String, Object>> l = gson.fromJson(json, List.class);
        for (Map<String, Object> l1 : l) {
            l1.put("level", "红色");
        }
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        m.put("issys", 0);
        m.put("del", 0);
        m.put("ctime", new Date());
        m.put("del", 0);
        m.put("list", l);
        m.put("flag", 2);
        aCompanyManualMapper.insertBath(m);
        aCompanyManualMapper.updateCompanyDlevel(user.getId());
        return result;
    }

    /**
     * 重大风险-保存
     */
    @RequestMapping(value = "a-zdfxys-save")
    public @ResponseBody
    Result assess5Save(HttpServletRequest request, String zdfx) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        List<ACompanyManual> list = new ArrayList<ACompanyManual>();
        aCompanyManualMapper.deleteFlag2(user.getId());

        if (StringUtils.isBlank(zdfx)) {
            return result;
        }
        String[] ll = zdfx.split("!@#");
        for (String l : ll) {
            ACompanyManual c = new ACompanyManual();
            //c.setLevel3(l);
            c.setFactors(l);
            list.add(c);
        }
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        m.put("issys", 0);
        m.put("del", 0);
        m.put("ctime", new Date());
        m.put("del", 0);
        for (ACompanyManual l1 : list) {
            l1.setLevel("红色");
        }
        m.put("list", list);
        m.put("flag", 2);
        aCompanyManualMapper.insertBath(m);
        aCompanyManualMapper.updateCompanyDlevel(user.getId());
        return result;
    }
    /** 安全风险管控系统 结束 */

    /**
     * 岗位应急处置卡
     */
    @RequestMapping(value = "ying-add")
    public String yingAdd(Model model, HttpServletRequest request, Integer type) throws Exception {
        User user = getLoginUser(request);
        List<Map<Object, Object>> depL = zzjgDepartmentMapper.selectLevel2ByUid(user.getId());
        model.addAttribute("depL", depL);
        LlHashMap<Object, Object> llm = getLlMap();
        llm.put("uid", user.getId());
        model.addAttribute("perL", zzjgPersonnelMapper.selectByMap(llm));//组织架构部门班组
        model.addAttribute("shL", aGwyjShMapper.selectAll());//组织架构部门班组
        return "company/safety-system/ying-add";
    }

    /**
     * 岗位应急处置卡
     */
    @RequestMapping(value = "ying-load")
    public String yingLoad(Model model, HttpServletRequest request, Integer departId) throws Exception {
        ZzjgDepartment depat = zzjgDepartmentMapper.selectByPrimaryKey(departId);
        AGwyj yj = aGwyjMapper.selectByDepartId(departId);
        if (null == yj) {
            yj = new AGwyj(null, getLoginUser(request).getId(), departId, "", "", "", "");
            aGwyjMapper.insertSelective(yj);
        }
        model.addAttribute("yj", yj);
        model.addAttribute("depat", depat);
        return "company/safety-system/ying-load";
    }

    /**
     * 岗位应急处置卡-更新
     */
    @RequestMapping(value = "ying-update")
    public @ResponseBody
    Result yingUpdate(HttpServletRequest request, AGwyj be) throws Exception {
        Result result = new ResultImpl();
        aGwyjMapper.updateByPrimaryKeySelective(be);
        return result;
    }
}
