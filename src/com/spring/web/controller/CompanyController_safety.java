/**
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd.
 * All right reserved.
 */
package com.spring.web.controller;

import com.spring.web.BaseController;
import com.spring.web.ibatis.LlHashMap;
import com.spring.web.model.*;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.service.cgf.CgfService;
import com.spring.web.service.trouble.TroubleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Title: CompanyController_cd
 * @Description: TODO(用一句话描述该文件做什么)
 * @author CGF
 * @date 2017年7月27日 上午9:49:51
 * @version V1.0
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
    *  高危风险一键导入
    * */
    @RequestMapping(value = "risk-list-add1")
    public @ResponseBody Result riskList(HttpServletRequest request){
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

        if ( null != company.getIndustry()){
            if (company.getIndustry().contains("化工企业（危险化学品生产、经营、使用）、加油站")){
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

            }else if (company.getIndustry().contains("烟花爆竹经营(批发)企业")) {

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


        if ( null != company.getDangers() && company.getDangers().contains("粉尘涉爆")) {

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
    public @ResponseBody Result adddel1(HttpServletRequest request,Model model,Integer type){

        Result result = new ResultImpl();
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        // 根据企业ID 查询当前企业中所有的高危风险的数据 ID
        List<ACompanyManual> aCompanyManualList = aCompanyManualMapper.selectIds(company.getUserId());
        // 根据 ids 修改删除标记为 1（已经删除）
        for (int i = 0; i < aCompanyManualList.size(); i++) {
            aCompanyManualMapper.updateAll(aCompanyManualList.get(i).getId());
        }

        return  result;
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


    @RequestMapping({"risk-list"})
    public String riskList(Model model, HttpServletRequest request, Integer type) throws Exception {
        User user = this.getLoginUser(request);
        Company company = this.companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI());
            return "company/safety-system/type";
        } else {
            model.addAttribute("company", company);
            Map<String, Object> m = new HashMap();
            m.put("type", type);
            m.put("flag", "1,3,4,5");
            m.put("uid", user.getId());
            List<Map<Object, Object>> zzjg = this.zzjgDepartmentMapper.selectLevel2ByUid(user.getId());
            List acL;
            if (type == null) {
                acL = this.aCompanyManualMapper.selectByMap(m);
                model.addAttribute("zzjgDep", zzjg);
                model.addAttribute("acL", acL);
                return "company/safety-system/risk-list1";
            } else {
                acL = this.aCompanyManualMapper.selectByMapGroupByLevel1Level2(m);
                Iterator it = acL.iterator();

                while(it.hasNext()) {
                    Map<String, Object> ac = (Map)it.next();
                    String level1 = ac.get("level1").toString();
                    String level2 = ac.get("level2").toString();
                    boolean has = false;
                    Iterator var15 = zzjg.iterator();

                    while(var15.hasNext()) {
                        Map<Object, Object> zz = (Map)var15.next();
                        String p = zz.get("parName").toString();
                        String name = zz.get("name").toString();
                        if (p.equals(level1) && name.equals(level2)) {
                            has = true;
                            break;
                        }
                    }

                    if (!has) {
                        it.remove();
                    }
                }
                model.addAttribute("dL", acL);
                model.addAttribute("type", type);
                return "company/safety-system/risk-list1";
            }
        }
    }



    /*
    * 风险点辨识表
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
       导入所有较大风险数据
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
        ACompanyManual aCompanyManual1 ;
        // 根据 行业 查询该行业中所有的较大风险信息
        List<ADangerManual> aDangerManualList = aDangerManualMapper.selectFactors("1",company.getIndustry()) ;
        if (aDangerManualList.size() != 0){
            for (int i = 0; i < aDangerManualList.size(); i++) {

                aCompanyManual1 = new ACompanyManual();
                aCompanyManual1.setUid(user.getId());
                aCompanyManual1.setLevel3(aDangerManualList.get(i).getLevel1()+"/"+aDangerManualList.get(i).getLevel2()+"/"+aDangerManualList.get(i).getLevel3());
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
        }else {
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
    * 导入现场管理数据
    * */
    @RequestMapping({"risk-list-add2"})
    @ResponseBody
    public Result riskListAdd2(Model model, HttpServletRequest request, Integer type) throws Exception {
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

        ACompanyManual aCompanyManual1 ;

        // 根据 行业 查询该行业中所有的较大风险信息
        List<ADangerManual> aDangerManualList = aDangerManualMapper.selectFactors("3",company.getIndustry()) ;

        if (aDangerManualList.size() != 0){
            for (int i = 0; i < aDangerManualList.size(); i++) {

                aCompanyManual1 = new ACompanyManual();
                aCompanyManual1.setUid(user.getId());
                aCompanyManual1.setLevel3(aDangerManualList.get(i).getLevel()+"/"+aDangerManualList.get(i).getLevel2()+"/"+aDangerManualList.get(i).getLevel3());
                aCompanyManual1.setFactors(aDangerManualList.get(i).getFactors());
                aCompanyManual1.setType(aDangerManualList.get(i).getType());
                aCompanyManual1.setMeasures(aDangerManualList.get(i).getMeasures());
                aCompanyManual1.setReference(aDangerManualList.get(i).getReference());
                aCompanyManual1.setFlag("3");
                aCompanyManual1.setLevel("橙色");
                aCompanyManual1.setDel(0);
                aCompanyManual1.setCtime(new Date());

                // 添加数据到表中
                aCompanyManualMapper.save(aCompanyManual1);

            }
            return result;
        }else {
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
            /*if ("化工企业（危险化学品生产、经营、使用）、加油站".equals(company.getIndustry())) {
                m.put("flag", "1,3,4");
            } else {
                m.put("flag", "1,3,4,5");
            }*/
            m.put("flag", "1");
            List<Map<String, Object>> acL = this.aCompanyManualMapper.selectByMap(m);
            model.addAttribute("list", acL);
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
            model.addAttribute("url",  request.getRequestURI() + "?id=" + id);
            return "company/safety-system/type";
        }
        model.addAttribute("company", company);
        model.addAttribute("ac", aCompanyManualMapper.selectByPrimaryKey(id));
        return "company/safety-system/set-risk";
    }

    /**
     * 设置风险因素-保存
     */
    @RequestMapping(value = "set-risk-edit")
    public @ResponseBody Result setRiskEdit(Model model, ACompanyManual ac,
                                            HttpServletRequest request,
                                            String gzkhxys, String gzkwlys) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        ACompanyManual a = aCompanyManualMapper.selectByPrimaryKey(ac.getId());

        ac.setUid(a.getUid());
        ac.setLevel1(a.getLevel1());
        ac.setLevel2(a.getLevel2());
        aCompanyManualMapper.updateByL1L2(ac);

        aCompanyManualMapper.updateCompanyDlevel(user.getId());

        if(null != gzkhxys || null != gzkwlys) {
            AGzk gzk = aGzkMapper.selectByPrimaryKey(ac.getId());
            if(null == gzk) {
                gzk = new AGzk(ac.getId(), gzkhxys, gzkwlys);
                aGzkMapper.insertSelective(gzk);
            } else {
                gzk.setHxys(gzkhxys);
                gzk.setWlys(gzkwlys);
                aGzkMapper.updateByPrimaryKeySelective(gzk);
            }
        }

        //处理两重点一重大的
        if(null != ac.getGy()) {
            Regulation r = regulationGet(user.getId());
            if(null == r) {
                r = new Regulation();
                r.setUserId(user.getId());
                if("".equals(ac.getGy())) {
                    r.setProcess(0);
                } else {
                    r.setProcess(1);
                }
                r.setProcessName(ac.getGy());
                regulationMapper.insertSelective(r);
            } else {
                if(!"".equals(ac.getGy())) {
                    r.setProcess(1);
                    String process_name = r.getProcessName();
                    if(StringUtils.isBlank(process_name)) {
                        r.setProcessName(ac.getGy());
                        regulationMapper.updateByPrimaryKeySelective(r);
                    } else {
                        String[] gys = ac.getGy().split("!@#");
                        for(String gy : gys) {
                            if(process_name.indexOf(gy) == -1) {
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
        model.addAttribute("ac", aCompanyManualMapper.selectByPrimaryKey(id));
        model.addAttribute("aGzk", aGzkMapper.selectByPrimaryKey(id));
        model.addAttribute("type", type);
        if(type.intValue() == 1) {//物理因素
            model.addAttribute("list", aHxysgzkMapper.selectAll());
        }
        if(type.intValue() == 2) {//化学因素
            List<AMaterial> list = aMaterialMapper.selectAll();//化学因素
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("userId", user.getId());
            List<Material> mL = materialMapper.selectByUserId(m);//原辅材料
            List<Product> pL = productMapper.selectByUserId(m);//生产产品

            List<AMaterial> list2 = new ArrayList<AMaterial>();
            for(AMaterial ama : list) {
                Boolean x = false;
                for(Material ma : mL) {
                    if(ma.getMaterial().indexOf(ama.getName()) > -1 ||
                            ama.getName().indexOf(ma.getMaterial()) > -1) {
                        list2.add(ama);
                        x = true;
                        break;
                    }
                }
                if(x) {
                    continue;
                }
                for(Product p : pL) {
                    if(p.getProductName().indexOf(ama.getName()) > -1 ||
                            ama.getName().indexOf(p.getProductName()) > -1) {
                        list2.add(ama);
                        break;
                    }
                }
            }

            model.addAttribute("list", list2);
        }
        if(type.intValue() == 3) {//工艺
            model.addAttribute("list", aGyMapper.selectAll());
        }
        if(type.intValue() == 4) {//物料
            Set<String> list = new HashSet<String>();

            List<AMaterial> amL = aMaterialMapper.selectAll();//化学因素
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("userId", getLoginUser(request).getId());
            List<Material> mL = materialMapper.selectByUserId(m);//原辅材料
            List<Product> pL = productMapper.selectByUserId(m);//生产产品

            for(Material ma : mL) {
                for(AMaterial ama : amL) {
                    if(ma.getMaterial().indexOf(ama.getName()) > -1 ||
                            ama.getName().indexOf(ma.getMaterial()) > -1) {
                        list.add(ma.getMaterial());
                        break;
                    }
                }
            }
            for(Product p : pL) {
                for(AMaterial ama : amL) {
                    if(p.getProductName().indexOf(ama.getName()) > -1 ||
                            ama.getName().indexOf(p.getProductName()) > -1) {
                        list.add(p.getProductName());
                        break;
                    }
                }
            }

            model.addAttribute("list", list);
        }
        if(type.intValue() == 5) {//高危作业
            Company company = companyMapper.selectByPrimaryKey(user.getId());
            if(StringUtils.isBlank(company.getDangers())) {
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
            model.addAttribute("url",  request.getRequestURI());
            return "company/safety-system/type";
        }
        AGzk gzk = aGzkMapper.selectByPrimaryKey(user.getId());
        if(null != gzk) {
            if(StringUtils.isNotBlank(gzk.getWlys())) {//物理因素
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
        if(null != gzk) {
            if(StringUtils.isNotBlank(gzk.getHxys())) {//化学因素
                model.addAttribute("list", aMaterialMapper.selectByIds(gzk.getHxys()));
            }
        }
        model.addAttribute("hxysL", aMaterialMapper.selectAll());
        model.addAttribute("gzk", gzk);
        return "company/safety-system/risk-list3";
    }

    /**
     * 告知卡
     */
    @RequestMapping(value = "harm-list")
    public String gzk(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        List<AGzk> gzk = aGzkMapper.selectByUid(user.getId());
        Set<String> s = new HashSet<String>();
        for(AGzk a : gzk) {
            if(StringUtils.isNotBlank(a.getWlys())) {
                s.addAll(Arrays.asList(a.getWlys().split(",")));
            }
        }
        if(s.size() > 0) {//物理因素
            model.addAttribute("wlysL", aHxysgzkMapper.selectByIds(StringUtils.join(s, ",")));
        }
        return "company/safety-system/harm-list";
    }

    /**
     * 主要物质理化、危险、有害特性表
     */
    @RequestMapping(value = "risk-information-list2")
    public String riskInformationList2(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        List<AGzk> gzk = aGzkMapper.selectByUid(user.getId());
        Set<String> s = new HashSet<String>();
        for(AGzk a : gzk) {
            if(StringUtils.isNotBlank(a.getHxys())) {
                s.addAll(Arrays.asList(a.getHxys().split(",")));
            }
        }
        if(s.size() > 0) {//物理因素
            model.addAttribute("list", aMaterialMapper.selectByIds(StringUtils.join(s, ",")));
        }
        return "company/safety-system/risk-information-list2";
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
     * 一般和较小实施方案
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
            List<Map<String, Object>> acL = this.aCompanyManualMapper.selectByMap(m);
            model.addAttribute("list", acL);
            return "company/safety-system/assess6";
        }
    }

    /**
     * 风险告知牌
     */
    @RequestMapping(value = "risk-information-list")
    public String riskInformationList(Model model, HttpServletRequest request, Integer flag) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI() + "flag=" + flag);
            return "company/safety-system/type";
        }

        //所有风险
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        //m.put("flag2", 1);
        m.put("levels", new String[]{"红色","橙色","黄色","蓝色"});
        List<Map<String, Object>> acL = aCompanyManualMapper.selectByMap(m);
        //所有重大较大风险
        List<Map<String, Object>> acL_f = new ArrayList<Map<String,Object>>();
        for(Map<String, Object> ac : acL) {
            String level = getStringFromMap(ac, "level");
            if(level.equals("红色") || level.equals("橙色")) {
                acL_f.add(ac);
            }
        }

        if(acL_f.size() == 0) {//没有重大较大隐患
            model.addAttribute("flag", flag);
            return "company/safety-system/risk-information-list_0";
        }

        //事故类型列表，用于处理其中的风险
        List<AGwyjSh> shList = aGwyjShMapper.selectAll();
        //List<String> adanerTypeList = aDangerTypeMapper.selectAll();

        //公司级公告牌
        if(flag.intValue() == 1) {
            AFxgzp ggp = aFxgzpMapper.selectFlag1(user.getId());
            if(null == ggp || ggp.getIsedit().intValue() == 0) {//没有公告牌或没有修改过，重新生成
                if(null == ggp) {
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
                for(AGwyjSh s : shL) {
                    yjczcs.append("<b>").append(s.getName()).append("：</b>").append(s.getMeasures()).append("<br>");
                }
                ggp.setYjczcs(yjczcs.toString());
                if(null == ggp.getId()) {
                    aFxgzpMapper.insertSelective(ggp);
                } else {
                    aFxgzpMapper.updateByPrimaryKeySelective(ggp);
                }

            }
            model.addAttribute("be", ggp);
            return "company/safety-system/risk-information-list_1";
        }

        //车间级
        if(flag.intValue() == 2) {
            Set<String> nameL = new HashSet<String>();//二级分类作为名称
            for(Map<String, Object> ac : acL_f) {
                String level1 = getStringFromMap(ac, "level1");
//                String level2 = getStringFromMap(ac, "level2");
//                String gkzt = getStringFromMap(ac, "gkzt");
//                if(StringUtils.isBlank(gkzt)) {
//                    continue;
//                }
//                nameL.add(gkzt);
                if(StringUtils.isBlank(level1)) {
                    continue;
                }
                nameL.add(level1);
            }

            if(nameL.size() == 0) {//没有重大较大隐患
                model.addAttribute("flag", flag);
                return "company/safety-system/risk-information-list_0";
            }
            model.addAttribute("nameL", nameL);
            return "company/safety-system/risk-information-list_2";
        }

        //岗位级
        if(flag.intValue() == 3) {
            Set<String> nameL = new HashSet<String>();//二级分类作为名称
            for(Map<String, Object> ac : acL_f) {
                String level1 = getStringFromMap(ac, "level1");
                String level2 = getStringFromMap(ac, "level2");
//                String level3 = getStringFromMap(ac, "level3");
                if(StringUtils.isBlank(level1)) {
                    continue;
                }
                nameL.add(level1 + "/" + level2);
//                if(StringUtils.isBlank(level1)) {
//                    nameL.add(level3);
//                } else {
//                    nameL.add(level1 + "/" + level2 + "/" + level3);
//                }
            }

            if(nameL.size() == 0) {//没有重大较大隐患
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
        for(Map<String, Object> ac : acL) {
            //风险等级
            String level = getStringFromMap(ac, "level");
            if(fxdj.toString().indexOf(level.replace("色", "")) == -1) {
                if(!"".equals(fxdj.toString())) {
                    fxdj.append(",");
                }
                fxdj.append(level.replace("色", ""));
            }

            //事故类型
            String type = getStringFromMap(ac, "type");
            if(StringUtils.isNotBlank(type)) {
                for(AGwyjSh e : shList) {
                    if(type.indexOf(e.getName()) > -1) {
                        if(sglx.add(e.getName())) {
                            shL.add(e);
                        };
                    }
                }
            }

            //风险因素
            String factors = getStringFromMap(ac, "factors");
            if(StringUtils.isNotBlank(factors)) {
                if(level.equals("红色")) {
                    fxys_zd.add(factors);
                } else {
                    fxys.add(factors);
                }
            }

            //措施
            String measures = getStringFromMap(ac, "measures");
            if(StringUtils.isNotBlank(measures)) {
                ffcs.add(measures);
            }
        }

        ggp.setFxdj(fxdj.toString());//风险等级
        if(sglx.size() > 0) {//事故类型
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
        if(null == be || be.getIsedit().intValue() == 0) {//没有公告牌或没有修改过，重新生成
            if(null == be) {
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
            m.put("levels", new String[]{"红色","橙色"});
            List<Map<String, Object>> acL = aCompanyManualMapper.selectByMap(m);
            //所有重大较大风险
            List<Map<String, Object>> acL_f = new ArrayList<Map<String,Object>>();
            for(Map<String, Object> ac : acL) {
                String level1 = getStringFromMap(ac, "level1");
                if(StringUtils.isBlank(level1)) {
                    continue;
                }
                if((level1).equals(name)) {
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

            if(null == be.getId()) {
                aFxgzpMapper.insertSelective(be);
            } else {
                aFxgzpMapper.updateByPrimaryKeySelective(be);
            }

        }
        model.addAttribute("be", be);
        return "company/safety-system/risk-information-list-load";
    }

    /**
     * 风险告知牌
     */
    @RequestMapping(value = "fxggp-load3")
    public String fxggpLoad3(Model model, HttpServletRequest request, String name) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        m.put("name", name);
        //m.put("flag2", 1);
        AFxgzp be = aFxgzpMapper.selectByName(m);
        //事故类型列表，用于处理其中的风险
        List<AGwyjSh> adanerTypeList = aGwyjShMapper.selectAll();
        //List<String> adanerTypeList = aDangerTypeMapper.selectAll();
        if(null == be || be.getIsedit().intValue() == 0) {//没有公告牌或没有修改过，重新生成
            if(null == be) {
                be = new AFxgzp();
            }
            be.setFlag(3);
            be.setUid(user.getId());
            be.setCtime(new Date());
            be.setDel(0);
            be.setIsedit(0);
            be.setName(name);

            m.put("uid", user.getId());
            m.put("levels", new String[]{"红色","橙色"});
            List<Map<String, Object>> acL = aCompanyManualMapper.selectByMap(m);
            //所有重大较大风险
            List<Map<String, Object>> acL_f = new ArrayList<Map<String,Object>>();
            for(Map<String, Object> ac : acL) {
                String level1 = getStringFromMap(ac, "level1");
                String level2 = getStringFromMap(ac, "level2");
//                String level3 = getStringFromMap(ac, "level3");
                if(StringUtils.isBlank(level1)) {
                    continue;
                }
                if((level1 + "/" + level2).equals(name)) {
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

            be.setGlzrr((String)acL_f.get(0).get("fjgkfzr"));//责任人

            setGgpXx(acL_f, be, adanerTypeList);
            if(null == be.getId()) {
                aFxgzpMapper.insertSelective(be);
            } else {
                aFxgzpMapper.updateByPrimaryKeySelective(be);
            }

        }
        model.addAttribute("be", be);
        return "company/safety-system/risk-information-list-load";
    }


    String getStringFromMap(Map<String, Object> m, String key) {
        Object o = m.get(key);
        if(null != o) {
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

    /**
     * 实施方案
     */
    @RequestMapping(value = "risk-list-load")//确认风险操作
    public String riskListLoad(Model model, HttpServletRequest request, String industry,
                               Integer depId) throws Exception {
        User user = getLoginUser(request);
        if(StringUtils.isNotBlank(industry)) {
            industry = utf8Str(industry);
        }
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        model.addAttribute("company", company);
        if(StringUtils.isBlank(industry)) {
            industry = company.getIndustry();
            if(industry.equals("化工企业（危险化学品生产、经营、使用）、加油站")) {
                if(company.getName().contains("油")) {
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
        model.addAttribute("depId", depId);
        log.error("risk-list-load depId:"+depId);
        return "company/safety-system/risk-list1-load";
    }

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
        if(null != im) {
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
    public String assess5(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI());
            return "company/safety-system/type";
        }
        List<ACompanyManual> aCompanyManualList = aCompanyManualMapper.findALLsss("2",user.getId());
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

    /**
     * 分级管控
     */
    @RequestMapping(value = "control-list")
    public String controlList(Model model, HttpServletRequest request, Integer type) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        m.put("order", 1);

        List<Map<String, Object>> list = aCompanyManualMapper.selectByMap(m);
        model.addAttribute("list", list);
        Map<String, LinkedHashSet<String>> levmap = new HashMap<String, LinkedHashSet<String>>();

        for(Map<String, Object> m1 : list) {
            String level1 = null == m1.get("level1") ? "" : m1.get("level1").toString();
            String level2 = null == m1.get("level2") ? "" : m1.get("level2").toString();
            LinkedHashSet<String> l2s = levmap.get(level1);
            if(null == l2s) {
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





    /**
     * 分级管控
     */
    @RequestMapping(value = "control-list-one")
    public @ResponseBody
    List<ZzjgDepartment> controlLists(Model model, HttpServletRequest request, String name,Integer pid) throws Exception {

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
        if(null != im && StringUtils.isNotBlank(im.getTextz1())) {
            model.addAttribute("per", im.getTextz1().split("\\|"));
        } else {
            model.addAttribute("per", new String[]{"","",""});
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
        if(null != im && StringUtils.isNotBlank(im.getTextz1())) {
            model.addAttribute("per", im.getTextz1().split("\\|"));
        } else {
            model.addAttribute("per", new String[]{"","","",""});
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
     * 评估结果
     */
//    @RequestMapping(value = "control-list2")
//    public String controlList2(Model model, HttpServletRequest request, String flag) throws Exception {
//        User user = getLoginUser(request);
//        Map<String, Object> m = new HashMap<String, Object>();
//        m.put("uid", user.getId());
//        m.put("order", 1);
//        model.addAttribute("list", aCompanyManualMapper.selectByMap(m));
//        model.addAttribute("company", companyMapper.selectByPrimaryKey(user.getId()));
//        model.addAttribute("flag", flag);
//        return "company/safety-system/control-list2";
//    }
    @RequestMapping(value = "control-list2")//zhangcl 2018.10.13
    public String controlList2(Model model, HttpServletRequest request, String flag) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
//        m.put("order", 1);

        model.addAttribute("company", company);
        model.addAttribute("user", userMapper.selectByPrimaryKey(company.getUserId()));
        model.addAttribute("v", userMapper.selectByPrimaryKey(company.getVillageId()));
        List<Map<String, Object>> list = aCompanyManualMapper.selectByMap(m);
        model.addAttribute("list", list);

        Map<String, LinkedHashSet<String>> levmap = new HashMap<String, LinkedHashSet<String>>();
        for(Map<String, Object> m1 : list) {
            String level1 = null == m1.get("level1") ? "" : m1.get("level1").toString();
            //log.error("level1：-----------start------------"+level1);
            String level2 = null == m1.get("level2") ? "" : m1.get("level2").toString();
            //log.error("level2："+level2);
            LinkedHashSet<String> l2s = levmap.get(level1);
            if(null == l2s) {
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

        if(flag.equals("2")){
            //log.error("zhangcl 2018.10.18 controlList3,area_range="+company.getAreaRange());
            return "company/safety-system/control-list3";
        }
        else{
            return "company/safety-system/control-list2";
        }

    }

    /**
     * 处理风险标点
     */
    @RequestMapping(value = "control-list3")//zhangcl 2018.10.13
    public String controlList3(Model model, HttpServletRequest request, Integer id, String lnglat) throws Exception {
        //log.error("zhangcl 2018.10.13 controlList3,id="+id+",lnglat="+lnglat);
        aCompanyManualMapper.updateLnglat(id,lnglat);
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
    public @ResponseBody Result companySave(HttpServletRequest request, AImplementation i) throws Exception {
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
    public @ResponseBody Result companyIndustry(HttpServletRequest request, String industry) throws Exception {
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
    public @ResponseBody Result aCompanyManualSave(HttpServletRequest request, ACompanyManual be,Integer ids) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        be.setUid(user.getId());
        be.setDmid(ids);
        be.setLevel1(be.getGkzt());
        be.setLevel2(be.getLevel2());
        be.setType("高危");
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


    /**
     * 企业风险辨识-保存,从指导书中选择的
     * TODO 保存风险
     */
    @RequestMapping(value = "aCompanyManual-save1")
    public @ResponseBody Result aCompanyManualSave1(HttpServletRequest request, Integer[] ids,
                                                    Integer depId) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        ZzjgDepartment dep = zzjgDepartmentMapper.selectByPrimaryKey(depId);
        ZzjgDepartment parDep = zzjgDepartmentMapper.selectByPrimaryKey(dep.getPid());
        String level1 = parDep.getName();
        String level2 = dep.getName();

        LlHashMap<Object, Object> lm = new LlHashMap<Object, Object>();
        lm.put("dpid", dep.getPid());
        List<LlHashMap<Object, Object>> personL = zzjgPersonnelMapper.selectByMap(lm);
        String fjgkfzr = "";//分级管控负责人
        if(personL.size() > 0) {
            fjgkfzr = personL.get(0).getString("name");
        }
        List<ACompanyManual> list = aDangerManualMapper.selectByIds(ids);
        for(ACompanyManual a : list) {
            a.setLevel3(a.getLevel1() + "/" + a.getLevel2() + "/" + a.getLevel3());
            a.setLevel1(level1);
            a.setLevel2(level2);
            a.setGkzt(level1);//管控主体
            a.setFjgkfzr(fjgkfzr);
        }
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        m.put("issys", 0);
        m.put("del", 0);
        m.put("ctime", new Date());
        m.put("del", 0);
        m.put("list", list);
        //m.put("flag", 1);
        aCompanyManualMapper.insertBath(m);
        aCompanyManualMapper.updateCompanyDlevel(user.getId());
        return result;
    }

    /**
     * 危险化学品生产储存企业安全风险评估诊断分级指南（试行）-保存
     */
    @RequestMapping(value = "assess1-save")
    public @ResponseBody Result assess1Save(HttpServletRequest request, AWhp be) throws Exception {
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
    public @ResponseBody Result assess2Save(HttpServletRequest request, AZytjfxcdpj be) throws Exception {
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
        if(null != af) {
            level = getDanger(af.getLev(), level);
        }
        ACompanyManualZp zp = aCompanyManualZpMapper.selectByPrimaryKey(id);
        if(null != zp) {
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
        if(StringUtils.isBlank(danger)) {
            return danger1;
        }
        if(StringUtils.isBlank(danger1)) {
            return danger;
        }
        Map<String, Integer> mm = new HashMap<String, Integer>();
        mm.put("红色", 4);
        mm.put("橙色", 3);
        mm.put("黄色", 2);
        mm.put("蓝色", 1);
        if(mm.get(danger1).compareTo(mm.get(danger)) > 0) {
            return danger1;
        }
        return danger;
    }

    /**
     * 风险举证-保存
     */
    @RequestMapping(value = "assess3-save")
    public @ResponseBody Result assess3Save(HttpServletRequest request, AFxjz be) throws Exception {
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
        if(null != aZytjfxcdpj) {
            level = getDanger(aZytjfxcdpj.getLev(), level);
        }
        ACompanyManualZp zp = aCompanyManualZpMapper.selectByPrimaryKey(id);
        if(null != zp) {
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
        ACompanyManual acm = new ACompanyManual();
        acm.setId(id);
        acm.setLevel(level);
        if (level != null && level.equals("红色")) {
            acm.setFlag("2");
        }

        if (level != null && level.equals("橙色")) {
            acm.setFlag("1");
        }

        if (level != null && level.equals("黄色")) {
            acm.setFlag("3");
        }

        if (level != null && level.equals("蓝色")) {
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
    public @ResponseBody Result riskInformationSave(HttpServletRequest request, AFxgzp be) throws Exception {
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
    public @ResponseBody Result gzkSave(HttpServletRequest request, AGzk be) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        be.setUid(user.getId());
        AGzk g = aGzkMapper.selectByPrimaryKey(user.getId());
        if(null == g) {
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
    public @ResponseBody Result assess5Save2(HttpServletRequest request, String json) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        aCompanyManualMapper.deleteFlag2(user.getId());
        if(StringUtils.isBlank(json)) {
            return result;
        }
        List<LinkedHashMap<String, Object>> l = gson.fromJson(json, List.class);
        for(Map<String, Object> l1 : l) {
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
    public @ResponseBody Result assess5Save(HttpServletRequest request, String zdfx) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        List<ACompanyManual> list = new ArrayList<ACompanyManual>();
        aCompanyManualMapper.deleteFlag2(user.getId());

        if(StringUtils.isBlank(zdfx)) {
            return result;
        }
        String[] ll = zdfx.split("!@#");
        for(String l : ll) {
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
        for(ACompanyManual l1 : list) {
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
        if(null == yj) {
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
    public @ResponseBody Result yingUpdate(HttpServletRequest request, AGwyj be) throws Exception {
        Result result = new ResultImpl();
        aGwyjMapper.updateByPrimaryKeySelective(be);
        return result;
    }
}
