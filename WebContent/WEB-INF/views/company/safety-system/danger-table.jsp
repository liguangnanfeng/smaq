<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <%@ include file="/WEB-INF/inc/back-header.inc" %>
    <title>风险分级管控 隐患排查治理智能化平台</title>
    <meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
    <meta name="description" content="风险分级管控   隐患排查治理智能化平台">
    <style type="text/css">
        body .dis-ib {
            margin-right: 15px;
        }

        .wid1 {
            width: 30%
        }

        .wid2 {
            width: 30%
        }

        .wid3 {
            width: 30%
        }

        .wid4 {
            width: 10%
        }

        .wid5 {
            width: 70%
        }

        .btn-cz {
            margin: auto;
            background: #eee;
            border: 1px solid #ddd;
            padding: 0 5px;
            height: 20px;
            line-height: 20px;
            border-radius: 5px;
            margin-left: 15px
        }

        .btn-cz:hover {
            border: 1px solid #06c;
            color: #06c;
            text-decoration: none;
        }

        body .check-box label {
            margin-left: 5px;
        }

        .one_danger .list_odan {
            margin-left: 20px
        }

        .two_danger .list_twdan {
            margin-left: 45px;
        }

        .one_danger .Hui-iconfont {
            margin-top: 2px
        }

        .one_danger, .list_danger {
            display: inline-block
        }

        .one_danger, .one_danger .list_odan, .two_danger, .two_danger .list_twdan, .list_danger {
            float: none;
        }

        .check-box {
            width: 30%;
            margin-bottom: 15px;
        }

        .btn-group .btn {
            height: 34px;
            line-height: 34px;
            padding: 0 25px;
        }

        .one-danrisk {
            float: left;
            width: 100%;
            border-bottom: 1px solid #ddd;
            height: auto;
        }

        .one-danrisk p {
            float: left;
            width: 80%;
            padding: 8px;
            height: auto;
        }

        .table tbody tr td .one-danrisk a {
            float: right;
            margin-top: 8px;
            margin-right: 8px;
        }

        .my_text {
            text-align: center !important;
            font-size: 25px;
            font-weight: bold;
        }

        .my_red {
            display: inline-block !important;
            background-color: red;
        }

        .my_o {
            display: inline-block !important;
            background-color: orange;
        }

        .my_y {
            display: inline-block !important;
            background-color: yellow;
        }

        .my_b {
            display: inline-block !important;
            background-color: blue;
        }

    </style>
    <%--    <script src="/js/jquery.jqprint-0.3.js"></script>--%>
</head>
<body>
<%--<nav class="breadcrumb">--%>
<%--    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>--%>
<%--    <span class="c-gray en">&gt;</span> <span>系统设置</span>--%>
<%--    <span class="c-gray en">&gt;</span> <span>风险辨识</span>--%>
<%--    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="parent.close_tab(function(){})">返回</a>--%>
<%--</nav>--%>
<div class="page-container">

    <div class="page-container" id="div_container">
        <div class="div-print">

            <h3 class="text-c">工贸行业重大生产安全事故隐患判定标准</h3>
            <h3 class="text-c">（2017版） </h3>
            <h4>本判定标准适用于判定工贸行业的重大生产安全事故隐患（以下简称重大事故隐患），危险化学品、消防（火灾）、特种设备等有关行业领域对重大事故隐患判定标准另有规定的，适用其规定。
                工贸行业重大事故隐患分为专项类重大事故隐患和行业类重大事故隐患，专项类重大事故隐患适用于所有相关的工贸行业，行业类重大事故隐患仅适用于对应的行业。</h4>
            <div class="mt-20">
                <table class="table table-border table-bordered table-bg table-hover table-sort tab-ndan">
<%--                    <thead>--%>
<%--                    <tr class="text-c">--%>
<%--                        <th style="width:20%">类别</th>--%>
<%--                        <th style="width:20%">项目（分值）</th>--%>
<%--                        <th style="width:50%">评估内容</th>--%>
<%--                        <th style="width:10%">扣分值</th>--%>

<%--                    </thead>--%>
                    <tbody id="tableaaa">



                    <tr>
                        <td colspan="2" style="font-size: 20px;font-weight: bold">一、专项类重大事故隐患</td>
                    </tr>
                    <tr>
                        <td colspan="2" style="font-size: 15px;font-weight: bold">（一）存在粉尘爆炸危险的行业领域。</td>
                    </tr>
                    <tr>
                        <td>1.粉尘爆炸危险场所设置在非框架结构的多层建构筑物内，或与居民区、员工宿舍、会议室等人员密集场所安全距离不足。</td>
                        <td width="10%" style="text-align: center"><input type="checkbox"  class= "item1" value="item1" onchange="dagou(this)"></td>
                    </tr>
                    <tr>
                        <td>2.可燃性粉尘与可燃气体等易加剧爆炸危险的介质共用一套除尘系统，不同防火分区的除尘系统互联互通。</td>
                        <td width="10%" style="text-align: center"><input type="checkbox" class= "item2" value="item2" onchange="dagou(this)"></td>
                    </tr>

                    <tr>
                        <td>3.干式除尘系统未规范采用泄爆、隔爆、惰化、抑爆等任一种控爆措施。</td>
                        <td width="10%" style="text-align: center"><input type="checkbox" class= "item3" value="item3" onchange="dagou(this)"></td>
                    </tr>

                    <tr>
                        <td>4.除尘系统采用正压吹送粉尘，且未采取可靠的防范点燃源的措施。</td>
                        <td width="10%" style="text-align: center"><input type="checkbox"  class= "item4" value="item4" onchange="dagou(this)"></td>
                    </tr>

                    <tr>
                        <td>5.除尘系统采用粉尘沉降室除尘，或者采用干式巷道式构筑物作为除尘风道。</td>
                        <td width="10%" style="text-align: center"><input type="checkbox"  class= "item5" value="item5" onchange="dagou(this)"></td>
                    </tr>

                    <tr>
                        <td>6.铝镁等金属粉尘及木质粉尘的干式除尘系统未规范设置锁气卸灰装置。</td>
                        <td width="10%" style="text-align: center"><input type="checkbox"  class= "item6" value="item6" onchange="dagou(this)"></td>
                    </tr>

                    <tr>
                        <td>7.粉尘爆炸危险场所的20区未使用防爆电气设备设施。</td>
                        <td width="10%" style="text-align: center"><input type="checkbox"  class= "item7" value="item7" onchange="dagou(this)"></td>
                    </tr>

                    <tr>
                        <td>8.在粉碎、研磨、造粒等易于产生机械点火源的工艺设备前，未按规范设置去除铁、石等异物的装置。</td>
                        <td width="10%" style="text-align: center"><input type="checkbox"  class= "item8" value="item8" onchange="dagou(this)"></td>
                    </tr>

                    <tr>
                        <td>9.木制品加工企业，与砂光机连接的风管未规范设置火花探测报警装置。</td>
                        <td width="10%" style="text-align: center"><input type="checkbox"  class= "item9" value="item9" onchange="dagou(this)"></td>
                    </tr>
                    <tr>
                        <td>10.未制定粉尘清扫制度，作业现场积尘未及时规范清理。</td>
                        <td width="10%" style="text-align: center"><input type="checkbox"  class= "item10" value="item10" onchange="dagou(this)"></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="font-size: 15px;font-weight: bold">（二）使用液氨制冷的行业领域。</td>
                    </tr>
                    <tr>
                        <td>1.包装间、分割间、产品整理间等人员较多生产场所的空调系统采用氨直接蒸发制冷系统。</td>
                        <td width="10%" style="text-align: center"><input type="checkbox"  class= "item11" value="item11" onchange="dagou(this)"></td>
                    </tr>
                    <tr>
                        <td>2.快速冻结装置未设置在单独的作业间内，且作业间内作业人员数量超过9人。</td>
                        <td width="10%" style="text-align: center"><input type="checkbox"  class= "item12" value="item12" onchange="dagou(this)"></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="font-size: 15px;font-weight: bold">（三）有限空间作业相关的行业领域。</td>
                    </tr>
                    <tr>
                        <td>1.未对有限空间作业场所进行辨识，并设置明显安全警示标志。</td>
                        <td width="10%" style="text-align: center"><input type="checkbox"  class= "item13" value="item13" onchange="dagou(this)"></td>
                    </tr>
                    <tr>
                        <td>2.未落实作业审批制度，擅自进入有限空间作业。</td>
                        <td width="10%" style="text-align: center"><input type="checkbox"  class= "item14" value="item14" onchange="dagou(this)"></td>
                    </tr>
<%--```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````--%>
 <%--```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````--%>
                    <tr>
                        <td colspan="2" style="font-size: 20px;font-weight: bold">二、行业类重大事故隐患</td>
                    </tr>

                    <c:if test="${industry eq '冶金行业'}">

                        <tr>
                            <td colspan="2" style="font-size: 15px;font-weight: bold">冶金行业。</td>
                        </tr>
                        <tr>
                            <td>1.会议室、活动室、休息室、更衣室等场所设置在铁水、钢水与液渣吊运影响的范围内。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item15" value="item15" onchange="dagou(this)"></td>
                        </tr>
                        <tr>
                            <td>2.吊运铁水、钢水与液渣起重机不符合冶金起重机的相关要求；炼钢厂在吊运重罐铁水、钢水或液渣时，未使用固定式龙门钩的铸造起重机，龙门钩横梁、耳轴销和吊钩、钢丝绳及其端头固定零件，未进行定期检查，发现问题未及时整改。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item16" value="item16" onchange="dagou(this)"></td>
                        </tr>

                        <tr>
                            <td>3.盛装铁水、钢水与液渣的罐（包、盆）等容器耳轴未按国家标准规定要求定期进行探伤检测。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item17" value="item17" onchange="dagou(this)"></td>
                        </tr>

                        <tr>
                            <td>4.冶炼、熔炼、精炼生产区域的安全坑内及熔体泄漏、喷溅影响范围内存在积水，放置有易燃易爆物品。金属铸造、连铸、浇铸流程未设置铁水罐、钢水罐、溢流槽、中间溢流罐等高温熔融金属紧急排放和应急储存设施。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item18" value="item18" onchange="dagou(this)"></td>
                        </tr>

                        <tr>
                            <td>5.炉、窑、槽、罐类设备本体及附属设施未定期检查，出现严重焊缝开裂、腐蚀、破损、衬砖损坏、壳体发红及明显弯曲变形等未报修或报废，仍继续使用。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item19" value="item19" onchange="dagou(this)"></td>
                        </tr>
                        <tr>
                            <td>6.氧枪等水冷元件未配置出水温度与进出水流量差检测、报警装置及温度监测，未与炉体倾动、氧气开闭等联锁。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item20" value="item20" onchange="dagou(this)"></td>
                        </tr>

                        <tr>
                            <td>7.煤气柜建设在居民稠密区，未远离大型建筑、仓库、通信和交通枢纽等重要设施；附属设备设施未按防火防爆要求配置防爆型设备；柜顶未设置防雷装置。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item21" value="item21" onchange="dagou(this)"></td>
                        </tr>

                        <tr>
                            <td>8.煤气区域的值班室、操作室等人员较集中的地方，未设置固定式一氧化碳监测报警装置。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item22" value="item22" onchange="dagou(this)"></td>
                        </tr>

                        <tr>
                            <td>9.高炉、转炉、加热炉、煤气柜、除尘器等设施的煤气管道未设置可靠隔离装置和吹扫设施。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item23" value="item23" onchange="dagou(this)"></td>
                        </tr>
                        <tr>
                            <td>10.煤气分配主管上支管引接处，未设置可靠的切断装置；车间内各类燃气管线，在车间入口未设置总管切断阀。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item24" value="item24" onchange="dagou(this)"></td>
                        </tr>
                        <tr>
                            <td>11.金属冶炼企业主要负责人和安全生产管理人员未依法经考核合格。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item25" value="item25" onchange="dagou(this)"></td>
                        </tr>
                    </c:if>

                    <c:if test="${industry eq '有色行业'}">
                        <tr>
                            <td colspan="2" style="font-size: 15px;font-weight: bold">有色行业。</td>
                        </tr>
                        <tr>
                            <td>1.吊运铜水等熔融有色金属及渣的起重机不符合冶金起重机的相关要求；横梁、耳轴销和吊钩、钢丝绳及其端头固定零件，未进行定期检查，发现问题未及时处理。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item26" value="item26" onchange="dagou(this)"></td>
                        </tr>
                        <tr>
                            <td>2.会议室、活动室、休息室、更衣室等场所设置在铜水等熔融有色金属及渣的吊运影响范围内。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item27" value="item27" onchange="dagou(this)"></td>
                        </tr>

                        <tr>
                            <td>3.盛装铜水等熔融有色金属及渣的罐（包、盆）等容器耳轴未定期进行检测。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item28" value="item28" onchange="dagou(this)"></td>
                        </tr>

                        <tr>
                            <td>4.铜水等高温熔融有色金属冶炼、精炼、铸造生产区域的安全坑内及熔体泄漏、喷溅影响范围内存在非生产性积水；熔体容易喷溅到的区域，放置有易燃易爆物品。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item29" value="item29" onchange="dagou(this)"></td>
                        </tr>

                        <tr>
                            <td>5.铜水等熔融有色金属铸造、浇铸流程未设置紧急排放和应急储存设施。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item30" value="item30" onchange="dagou(this)"></td>
                        </tr>
                        <tr>
                            <td>6.高温工作的熔融有色金属冶炼炉窑、铸造机、加热炉及水冷元件未设置应急冷却水源等冷却应急处置措施。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item31" value="item31" onchange="dagou(this)"></td>
                        </tr>

                        <tr>
                            <td>7.冶炼炉窑的水冷元件未配置温度、进出水流量差检测及报警装置；未设置防止冷却水大量进入炉内的安全设施（如：快速切断阀等）。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item32" value="item32" onchange="dagou(this)"></td>
                        </tr>

                        <tr>
                            <td>8.炉、窑、槽、罐类设备本体及附属设施未定期检查，出现严重焊缝开裂、腐蚀、破损、衬砖损坏、壳体发红及明显弯曲变形等未报修或报废，仍继续使用。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item33" value="item33" onchange="dagou(this)"></td>
                        </tr>

                        <tr>
                            <td>9.使用煤气（天然气）的烧嘴等燃烧装置，未设置防突然熄火或点火失败的快速切断阀，以切断煤气（天然气）。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item34" value="item34" onchange="dagou(this)"></td>
                        </tr>
                        <tr>
                            <td>10.金属冶炼企业主要负责人和安全生产管理人员未依法经考核合格。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item35" value="item35" onchange="dagou(this)"></td>
                        </tr>
                    </c:if>



                    <c:if test="${industry eq '建材行业'}">
                        <tr>
                            <td colspan="2" style="font-size: 15px;font-weight: bold">建材行业。</td>
                        </tr>
                        <tr>
                            <td>1.水泥工厂煤磨袋式收尘器（或煤粉仓）未设置温度和一氧化碳监测，或未设置气体灭火装置。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item36" value="item36" onchange="dagou(this)"></td>
                        </tr>
                        <tr>
                            <td>2.水泥工厂筒型储存库人工清库作业外包给不具备高空作业工程专业承包资质的承包方且作业前未进行风险分析。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item37" value="item37" onchange="dagou(this)"></td>
                        </tr>

                        <tr>
                            <td>3.燃气窑炉未设置燃气低压警报器和快速切断阀，或易燃易爆气体聚集区域未设置监测报警装置。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item38" value="item38" onchange="dagou(this)"></td>
                        </tr>

                        <tr>
                            <td>4.纤维制品三相电弧炉、电熔制品电炉，水冷构件泄漏。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item39" value="item39" onchange="dagou(this)"></td>
                        </tr>

                        <tr>
                            <td>5.进入筒型储库、磨机、破碎机、篦冷机、各种焙烧窑等有限空间作业时，未采取有效的防止电气设备意外启动、热气涌入等隔离防护措施。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item40" value="item40" onchange="dagou(this)"></td>
                        </tr>
                        <tr>
                            <td>6.玻璃窑炉、玻璃锡槽，水冷、风冷保护系统存在漏水、漏气，未设置监测报警装置。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item41" value="item41" onchange="dagou(this)"></td>
                        </tr>
                    </c:if>


                    <c:if test="${industry eq '机械行业'}">
                        <tr>
                            <td colspan="2" style="font-size: 15px;font-weight: bold">机械行业。</td>
                        </tr>
                        <tr>
                            <td>1.会议室、活动室、休息室、更衣室等场所设置在熔炼炉、熔融金属吊运和浇注影响范围内。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item42" value="item42" onchange="dagou(this)"></td>
                        </tr>
                        <tr>
                            <td>2.吊运熔融金属的起重机不符合冶金铸造起重机技术条件,或驱动装置中未设置两套制动器。吊运浇注包的龙门钩横梁、耳轴销和吊钩等零件，未进行定期探伤检查。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item43" value="item43" onchange="dagou(this)"></td>
                        </tr>

                        <tr>
                            <td>3.铸造熔炼炉炉底、炉坑及浇注坑等作业坑存在潮湿、积水状况，或存放易燃易爆物品。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item44" value="item44" onchange="dagou(this)"></td>
                        </tr>

                        <tr>
                            <td>4.铸造熔炼炉冷却水系统未配置温度、进出水流量检测报警装置，没有设置防止冷却水进入炉内的安全设施。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item45" value="item45" onchange="dagou(this)"></td>
                        </tr>

                        <tr>
                            <td>5.天然气（煤气）加热炉燃烧器操作部位未设置可燃气体泄漏报警装置，或燃烧系统未设置防突然熄火或点火失败的安全装置。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item46" value="item46" onchange="dagou(this)"></td>
                        </tr>
                        <tr>
                            <td>6.使用易燃易爆稀释剂（如天拿水）清洗设备设施，未采取有效措施及时清除集聚在地沟、地坑等有限空间内的可燃气体。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item47" value="item47" onchange="dagou(this)"></td>
                        </tr>
                        <tr>
                            <td>7.涂装调漆间和喷漆室未规范设置可燃气体报警装置和防爆电气设备设施。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item48" value="item48" onchange="dagou(this)"></td>
                        </tr>
                    </c:if>

                    <c:if test="${industry eq '轻工行业'}">
                        <tr>
                            <td colspan="2" style="font-size: 15px;font-weight: bold">轻工行业。</td>
                        </tr>
                        <tr>
                            <td>1.食品制造企业涉及烘制、油炸等设施设备，未采取防过热自动报警切断装置和隔热防护措施。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item49" value="item49" onchange="dagou(this)"></td>
                        </tr>
                        <tr>
                            <td>2.白酒储存、勾兑场所未规范设置乙醇浓度检测报警装置。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item50" value="item50" onchange="dagou(this)"></td>
                        </tr>

                        <tr>
                            <td>3.纸浆制造、造纸企业使用水蒸气或明火直接加热钢瓶汽化液氯。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item51" value="item51" onchange="dagou(this)"></td>
                        </tr>

                        <tr>
                            <td>4.日用玻璃、陶瓷制造企业燃气窑炉未设燃气低压警报器和快速切断阀，或易燃易爆气体聚集区域未设置监测报警装置。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item52" value="item52" onchange="dagou(this)"></td>
                        </tr>

                        <tr>
                            <td>5.日用玻璃制造企业炉、窑类设备本体及附属设施出现开裂、腐蚀、破损、衬砖损坏、壳体发红及明显弯曲变形。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item53" value="item53" onchange="dagou(this)"></td>
                        </tr>
                        <tr>
                            <td>6.喷涂车间、调漆间未规范设置通风装置和防爆电气设备设施。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item54" value="item54" onchange="dagou(this)"></td>
                        </tr>
                    </c:if>

                    <c:if test="${industry eq '纺织行业'}">
                        <tr>
                            <td colspan="2" style="font-size: 15px;font-weight: bold">纺织行业。</td>
                        </tr>
                        <tr>
                            <td>1.纱、线、织物加工的烧毛、开幅、烘干等热定型工艺的汽化室、燃气贮罐、储油罐、热媒炉等未与生产加工、人员密集场所明确分开或单独设置。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item55" value="item55" onchange="dagou(this)"></td>
                        </tr>
                        <tr>
                            <td>2.保险粉、双氧水、亚氯酸钠、雕白粉（吊白块）等危险品与禁忌物料混合贮存的；保险粉露天堆放，或储存场所未采取防水、防潮等措施。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item56" value="item56" onchange="dagou(this)"></td>
                        </tr>

                    </c:if>

                    <c:if test="${industry eq '烟草行业'}">
                        <tr>
                            <td colspan="2" style="font-size: 15px;font-weight: bold">烟草行业。</td>
                        </tr>
                        <tr>
                            <td>1.熏蒸杀虫作业前，未确认无关人员全部撤离仓库，且作业人员未配置防毒面具。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item57" value="item57" onchange="dagou(this)"></td>
                        </tr>
                        <tr>
                            <td>2.使用液态二氧化碳制造膨胀烟丝的生产线和场所，未设置二氧化碳浓度报警仪、燃气浓度报警仪、紧急联动排风装置。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item58" value="item58" onchange="dagou(this)"></td>
                        </tr>
                    </c:if>

                    <c:if test="${industry eq '烟草行业'}">
                        <tr>
                            <td colspan="2" style="font-size: 15px;font-weight: bold">商贸行业。</td>
                        </tr>
                        <tr>
                            <td>1.在房式仓、筒仓及简易仓囤进行粮食进出仓作业时，未按照作业标准步骤或未采取有效防护措施作业。</td>
                            <td width="10%" style="text-align: center"><input type="checkbox"  class= "item59" value="item59" onchange="dagou(this)"></td>
                        </tr>
                    </c:if>



                    </tbody>
                </table>




            </div>
        </div>
        <div class="row" style="margin-top: 20px;margin-bottom:50px">
            <div class="col-xs-3 col-sm-3">
                <span style="display:inline-block;line-height: 32px;font-size: 16px">当前风险评级</span>
                <div style="width:50px;height: 20px;margin-left: 10px;border-radius: 5px;position: relative;top:5px"
                     class='my_b' id="sekuai"></div>
            </div>
            <div class="col-xs-6 col-sm-6 col-md-offset-3 col-xs-offset-3">
                <button class="btn btn-primary radius"  onClick="saveCoordinate()" style="float: right">保存</button>
            </div>
        </div>





    </div>
</div>
</body>
<script>
   $(function(){
       var data= '${comFlag}';
       if(data){
           data=data.split(',');
           $('#sekuai').removeClass('my_red my_o my_y my_b');
           $('#sekuai').addClass('my_red');
           data.map(function(item){
             $('.'+item).attr("checked",'true')
           })
       }
   })


    function dagou(obj) {
        var dom = $(obj);
        var isCheck = dom.is(':checked');
        if (isCheck) {
            $('#sekuai').removeClass('my_red my_o my_y my_b');
            $('#sekuai').addClass('my_red');
        } else {
          var checks = $("input:checkbox:checked");
          if(checks.length==0){
              $('#sekuai').removeClass('my_red my_o my_y my_b');
              $('#sekuai').addClass('my_b');
          }

        }
    }




    /*
    * 确定按钮！！！
    * */
    function saveCoordinate() {
        var checks = $("input:checkbox:checked");
        var arr = [];
        var comFlag='';
        if(checks.length>0){
            checks.each(function(){
                arr.push($(this).val());
            })

            comFlag=arr.join(",")
        }
        $.ajax({ //post也可
            type: "POST",
            url: getRootPath() + "/company/safety-system/save-commerce-table",
            data: {comFlag:comFlag},
            success: function (result) {
               layer.msg(result.mess);

            }
        });

    }
</script>

</html>