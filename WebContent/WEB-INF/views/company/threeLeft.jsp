    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <%@ include file="/WEB-INF/views/taglibs.jsp" %>
        <!doctype html>
        <html lang="en">
        <head>
        <meta charset="UTF-8">
        <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
        <title>Document</title>
        <style>
        ul{
        text-align:center;
        margin:0;
        padding:0;
        margin-top:1%;
        margin-bottom:1%;
        display:flex;
        justify-content: center;
        <%--        background: #efefef;--%>
        }
        ul li{
        display: inline-block;
        list-style-type: none;
        text-align: center;
        padding: 3px 0px;
        margin-left: 12px;
        height: 2.2vw;
        line-height: 2.2vw;
        width: 10%;
        background: #5A98DE;
        color: #fff;
        border-radius: 3px;
        }
        ul li:hover{
        background:#3275BF;
        }
        <%--        ul li:last-child{--%>
        <%--        border:none;--%>
        <%--        }--%>
        ul li a{
        text-decoration: none;
        display: block;
        text-align: center;
        width: 100%;
        padding-left: 0 !important;
        color: #111;
        font-size: 15px;
        }
        ul li a img{
        display:block;
        }
        ul li a{
        color:#fff;
        }
        .children_div{
        display:none;
        height: 2.8vw!important;
        }
        .children_div ul{
        background:#fff;
        text-align:center;
        }
        .children_div ul li{
        border:none;
        }
        .children_div ul li a{
        color:#999;
        }
        .children_div ul li:hover{
        border-bottom:1px solid rgb(35,126,255);
        color:#333;
        }
        .children_div ul li a:hover{
        color:#333;
        }
        <%--        .three>ul:nth-child(2){--%>
        <%--        margin-top:1%;--%>
        <%--        }--%>
        .fanhui{
        text-decoration: none;
        padding-left: 20px;
        color: #333;
        }
        .fanhui:hover{
        color:rgb(35,126,255);
        }
        </style>
        </head>
        <body style="margin:0;padding:0;">
        <script>
        console.log("threeleft!");
        </script>
        <div class="three">
        <!--组织架构-->
        <c:if test="${leftBasic == 1}">
            <ul >
            <li><a onclick="sub1()" data-title="组织架构" href="javascript:void(0)">组织架构</a></li>
            <li><a onclick="sub2()" data-title="人员在岗在位" href="javascript:void(0)">人员在岗在位</a>
            </li>
            </ul>
            <iframe scrolling="no" id="iframe" frameborder="0" width="100%" height="700px" src="${ly}
            /company/system/department-list"></iframe>
            <script>
            function sub1(){
            $("#iframe").attr("src", "${ly}/company/system/department-list");
            }
            function sub2(){
            $("#iframe").attr("src", "${ly }/company/system/user-list");
            }
            </script>


        </c:if>
        <!-- 风险评估 -->
        <c:if test="${leftBasic== 2}">
            <ul id="startnav">
            <li><a onclick="sub11()" data-title="评估操作" href="/company/safety-system/assess7">评估操作</a></li>
            <li><a onclick="sub2()" data-title="评估结果" href="javascript:void(0)">评估结果</a></li>
            <li><a onclick="sub3()" data-title="风险分布表" href="javascript:void(0)">风险分布表</a></li>
            <li><a data-title="风险分布图" href="javascript:void(0)" onclick="firstAcitive(this)">风险分布图</a></li>
            </ul>
            <ul class="children_div">
            <li><a onclick="sub4()" data-title="公司/厂级风险分布图"
            href="javascript:void(0)">公司/厂级风险分布图</a></li>
            <li><a onclick="sub5()" data-title="车间/岗位风险分布图"
            href="javascript:void(0)">车间/岗位风险分布图</a></li>
            </ul>
            <iframe onload="loadFram3()" scrolling="no" id="iframe" frameborder="0" width="100%" height="700px"
            src="${ly }/company/safety-system/control-list2?flag=1"></iframe>
            <script>
            let x=null;
            function loadFram3(){
            x=false;
            $("#iframe").contents().find("#aback").attr("href","javascript:void(0)")
            $("#iframe").contents().find("#aback").on("click",function(){
            console.log("click");
            $(".children_div").hide();
            $("#startnav").show();
            $("#iframe").attr("src", "${ly }/company/safety-system/control-list2?flag=1");
<%--            x=true;--%>
            console.log('x:',x)
            });
           // $("#iframe").contents().find("#bback").hide();

            <%--            if(x===false) {--%>
<%--            $("#iframe").contents().find("nav>a:eq(2)").hide();--%>
<%--            console.log('hide button')--%>
<%--            }--%>

            }
            function firstAcitive(v){
            $("#iframe").attr("src", "${ly }/api/map/control-list4");
            var str = document.querySelector('.children_div')
            v.parentNode.parentNode.style.display="none"
            if(str.style.display=="block"){
            str.style.display="none"
            }else{{
            str.style.display="block"
            }
            }
            }

            function sub11(){
            $("#iframe").attr("src", "${ly}/company/safety-system/assess7");
            }
            function sub2(){
            $("#iframe").attr("src", "${ly }/company/safety-system/control-list2?flag=2");

            }
            function sub3(){
            $("#iframe").attr("src", "${ly }/company/safety-system/control-list2?flag=3");

            }
            function sub4(){
            $("#iframe").attr("src", "${ly }/api/map/control-list4");
            }
            function sub5(){
            $("#iframe").attr("src", "${ly }/company/safety-system/control-photo");
            }
            </script>
        </c:if>
        <!--公告警示-->
        <c:if test="${leftBasic == 3}">
            <ul>
            <li><a onclick="sub1()" data-title="公司风险公告牌" href="javascript:void(0)">公司风险公告牌</a></li>
            <li><a onclick="sub2()" data-title="车间风险公告牌" href="javascript:void(0)">车间风险公告牌</a></li>
            <li><a onclick="sub3()" data-title="岗位风险公告牌" href="javascript:void(0)">岗位风险公告牌</a></li>
            <li><a onclick="sub4()" data-title="岗位应急处置卡" href="javascript:void(0)">岗位应急处置卡</a></li>
            <li><a onclick="sub5()" data-title="告知牌" href="javascript:void(0)">告知牌</a></li>
            </ul>
            <iframe onload="loadFrame()" scrolling="no" id="iframe" frameborder="0" width="100%" height="700px"
            src="${ly }/company/safety-system/risk-information-list?flag=1"></iframe>
            <script>
            </script>
            <script>
            function sub1(){
            $("#iframe").attr("src", "${ly }/company/safety-system/risk-information-list?flag=1");
            }
            function sub2(){
            $("#iframe").attr("src", "${ly }/company/safety-system/risk-information-list?flag=2");
            }
            function sub3(){
            $("#iframe").attr("src", "${ly }/company/safety-system/risk-information-list?flag=3");
            }
            function sub4(){
            $("#iframe").attr("src", "${ly }/company/safety-system/ying-add");
            }
            function sub5(){
            $("#iframe").attr("src", "${ly }/company/safety-system/harm-list");
            }
            </script>
        </c:if>


        <!--检查模版设置-->
        <c:if test="${leftBasic == 4}">
            <ul>
            <li><a data-title="添加检查模版" href="javascript:void(0)">添加检查模版</a>
            </li>
            <li><a data-title="查看检查模版" href="javascript:void(0)">查看检查模版</a>
            </li>
            </ul>
            <iframe onload="loadFrame()" scrolling="no" id="iframe" frameborder="0" width="100%" height="700px"
            src="${ly }/village/addCheckModel"></iframe>
            <script>
            </script>
            <script>
            function sub1(){
            $("#iframe").attr("src", "${ly }/village/addCheckModel");
            }
            function sub2(){
            $("#iframe").attr("src", "${ly }/company/model-list-cx?flag=1&type=1");
            }
            </script>
        </c:if>
        <!--体系管理-->
        <c:if test="${leftBasic == 5}">
            <ul class="oul">
            <li><a data-title="管理手册"
            href="javascript:void(0)">管理手册</a></li>
            </ul>
            <iframe onload="loadFrame()" scrolling="no" id="iframe" frameborder="0" width="100%" height="700px"
            src="${ly }/company/study/management-guide"></iframe>
        </c:if>
        <!--流程管理-->
        <c:if test="${leftBasic == 6}">
            <ul>
            <li><a onclick="sub1()" data-title="特殊作业流程" href="javascript:void(0)">特殊作业流程</a></li>
            <li><a onclick="sub2()" data-title="检维修作业流程" href="javascript:void(0)">检维修作业流程</a></li>
            <li><a onclick="sub3()" data-title="承包商作业流程" href="javascript:void(0)">承包商作业流程</a></li>
            </ul>
            <iframe src="${ly }/company/study/select?type=1" onload="loadFrame()" scrolling="no" id="iframe"
            frameborder="0" width="100%" height="700px" ></iframe>
            <script>
            function sub1(){
            $("#iframe").attr("src", "${ly }/company/study/select?type=1");
            }
            function sub2(){
            $("#iframe").attr("src", "${ly }/company/study/select?type=2");
            }
            function sub3(){
            $("#iframe").attr("src", "${ly }/company/study/select?type=3");
            }
            </script>
        </c:if>
        <!--运行管理-->
        <c:if test="${leftBasic == 7}">
            <ul>
            <li><a onclick="sub1()" data-title="固有风险研判"
            href="javascript:void(0)">固有风险研判</a></li>
            <li><a onclick="sub2()" data-title="临时风险研判"
            href="javascript:void(0)">临时风险研判</a></li>
            <li><a onclick="sub3()" data-title="重大/较大隐患治理风险研判"
            href="javascript:void(0)">重大/较大隐患治理风险研判</a></li>
            </ul>
            <iframe src="${ly }/company/study/naturaldanger-list" onload="loadFrame()" scrolling="no" id="iframe"
            frameborder="0" width="100%" height="700px" ></iframe>
            <script>
            function sub1(){
            $("#iframe").attr("src", "${ly }/company/study/naturaldanger-list");
            }
            function sub2(){
            $("#iframe").attr("src", "${ly }/company/study/temporaryrisk-list");
            }
            function sub3(){
            $("#iframe").attr("src", "${ly }/company/study/greatdanger-list");
            }
            </script>


        </c:if>
        <!--责任制-->
        <c:if test="${leftBasic == 8}">
            <ul>
            <li><a onclick="sub1()" data-title="安全生产责任制" href="javascript:void(0)">责任制</a></li>
            <li><a onclick="sub2()" data-title="安全生产管理网络图" href="javascript:void(0)">网络图</a></li>
            <li><a onclick="sub3()" data-title="安全生产规章制度" href="javascript:void(0)">安全生产规章制度</a></li>
            </ul>
            <iframe src="${ly }/company/tables/tab-list?isType=0" onload="loadFrame()" scrolling="no" id="iframe"
            frameborder="0" width="100%" height="700px" ></iframe>
            <script>
            function sub1(){
            $("#iframe").attr("src", "${ly }/company/tables/tab-list?isType=0");
            }
            function sub2(){
            $("#iframe").attr("src", "${ly }/company/information/information6");
            }
            function sub3(){
            $("#iframe").attr("src", "${ly }/company/tables/tab-list?isType=1");
            }
            </script>
        </c:if>
        <!--管理档案-->
        <c:if test="${leftBasic == 9}">
            <ul>
            <li><a onclick="sub1()" data-title="安全工作台账"
            href="javascript:void(0)">安全工作台账</a></li>
            <li><a onclick="sub2()" data-title="安全档案"
            href="javascript:void(0)">安全档案</a></li>
            <iframe src="${ly }/company/tables/tab-taizhang" onload="loadFrame()" scrolling="no" id="iframe"
            frameborder="0" width="100%" height="700px" ></iframe>
            <script>
            function sub1(){
            $("#iframe").attr("src", "${ly }/company/tables/tab-taizhang");
            }
            function sub2(){
            $("#iframe").attr("src", "${ly }/company/tables/tab-dangan");
            }
            </script>
            </ul>


            <%-- <ul>
             <li><a data-title="教育培训" href="javascript:void(0)" onclick="threeActive(this)"> <img alt="" src="${ly }/images/companyImg/icon/jiaoyupeixun.png"/>教育培训</a></li>
             <li><a data-title="设备设施管理" href="javascript:void(0)" onclick="threeActive(this)"> <img alt="" src="${ly }/images/companyImg/icon/guyoufengxian.png"/>设备设施管理</a></li>
             <li><a href="${ly }/company/evaluate/evaluation-list?flag=0" data-title="评价评估档案"
             href="javascript:void(0)">  <img alt="" src="${ly }/images/companyImg/icon/pingjiapinggu.png"/>评价评估档案</a></li>
             <li><a href="${ly }/company/evaluate/zheng-list" data-title="各类许可证档案"
             href="javascript:void(0)">  <img alt="" src="${ly }/images/companyImg/icon/geleixukezheng.png"/>各类许可证档案</a></li>
             </ul>
             <ul>
             <li><a href="${ly }/company/information/information3" data-title="两重点一重大"
             href="javascript:void(0)"> <img alt="" src="${ly }/images/companyImg/icon/laodongzhe.png"/>两重点一重大</a></li>
             <li><a href="${ly }/company/tables/tab-weilist" data-title="危险作业证"
             href="javascript:void(0)">  <img alt="" src="${ly }/images/companyImg/icon/gongsifengxian.png"/>危险作业证</a></li>
             <li><a href="${ly }/company/tables/tab-jianglist" data-title="安全奖惩"
             href="javascript:void(0)"> <img alt="" src="${ly }/images/companyImg/icon/anquanjiangcheng.png"/>安全奖惩</a></li>
             <li><a href="${ly }/company/tables/tab-shilist" data-title="事故报告和处理"
             href="javascript:void(0)"> <img alt="" src="${ly }/images/companyImg/icon/shigubaogao.png"/>事故报告和处理</a></li>
             </ul>
             <div class="children_div">
             <ul>
             <li><a href="${ly }/company/train/special-list?spFlag=0"
             data-title="持证上岗" href="javascript:void(0)"> <img alt="" src="${ly }/images/companyImg/icon/chizhengshanggang.png"/>持证上岗</a></li>
             <li><a href="${ly }/company/tables/tab-anlist"
             data-title="安全生产教育台账" href="javascript:void(0)"> <img alt="" src="${ly }/images/companyImg/icon/jiaoyutaizhang.png"/>教育台账</a></li>
             </ul>
             </div>
             <div class="children_div">
             <ul>
             <li><a href="${ly }/company/product/mequipment-list"
             data-title="主要设备清单" href="javascript:void(0)"> <img alt="" src="${ly }/images/companyImg/icon/zhuyaoshebei.png"/>主要设备清单</a></li>
             <li><a href="${ly }/company/sequipment/sequipment-list"
             data-title="特种设备检测" href="javascript:void(0)"> <img alt="" src="${ly }/images/companyImg/icon/tezhongshebei.png"/>特种设备检测</a></li>
             <li><a href="${ly }/company/product/lightning-list"
             data-title="防雷防静电" href="javascript:void(0)"> <img alt="" src="${ly }/images/companyImg/icon/fanglei.png"/>防雷防静电</a></li>
             <li><a href="${ly }/company/safety-facilities"
             data-title="安全设施登记台账" href="javascript:void(0)"> <img alt="" src="${ly }/images/companyImg/icon/anquansheshi.png"/>安全设施登记台账</a></li>
             </ul>--%>
            <%--</div>--%>
            <%--<div class="div_omenu">--%>
            <%--<div class="div_otop">教育培训<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>--%>
            <%--<ul>--%>
            <%--<li><a data-href="${ly }/company/train/special-list?spFlag=0"--%>
            <%--data-title="持证上岗" href="javascript:void(0)">持证上岗</a></li>--%>
            <%--<li><a data-href="${ly }/company/tables/tab-anlist"--%>
            <%--data-title="安全生产教育台账" href="javascript:void(0)">教育台账</a></li>--%>
            <%--</ul>--%>
            <%--</div>--%>
            <%--<div class="div_omenu">--%>
            <%--<div class="div_otop">设备设施管理<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>--%>
            <%--<ul>--%>
            <%--<li><a data-href="${ly }/company/product/mequipment-list"--%>
            <%--data-title="主要设备清单" href="javascript:void(0)">主要设备清单</a></li>--%>
            <%--<li><a data-href="${ly }/company/sequipment/sequipment-list"--%>
            <%--data-title="特种设备检测" href="javascript:void(0)">特种设备检测</a></li>--%>
            <%--<li><a data-href="${ly }/company/product/lightning-list"--%>
            <%--data-title="防雷防静电" href="javascript:void(0)">防雷防静电</a></li>--%>
            <%--<li><a data-href="${ly }/company/safety-facilities"--%>
            <%--data-title="安全设施登记台账" href="javascript:void(0)">安全设施登记台账</a></li>--%>
            <%--</ul>--%>
            <%--</div>--%>
            <%--<ul class="oul">--%>
            <%--<li><a data-href="${ly }/company/evaluate/evaluation-list?flag=0" data-title="评价评估档案"--%>
            <%--href="javascript:void(0)">评价评估档案</a></li>--%>
            <%--<li><a data-href="${ly }/company/evaluate/zheng-list" data-title="各类许可证档案"--%>
            <%--href="javascript:void(0)">各类许可证档案</a></li>--%>
            <%--<li><a data-href="${ly }/company/information/information3" data-title="两重点一重大"--%>
            <%--href="javascript:void(0)">两重点一重大</a></li>--%>
            <%--<li><a data-href="${ly }/company/tables/tab-weilist" data-title="危险作业证"--%>
            <%--href="javascript:void(0)">危险作业证</a></li>--%>
            <%--<li><a data-href="${ly }/company/tables/tab-jianglist" data-title="安全奖惩"--%>
            <%--href="javascript:void(0)">安全奖惩</a></li>--%>
            <%--<li><a data-href="${ly }/company/tables/tab-shilist" data-title="事故报告和处理"--%>
            <%--href="javascript:void(0)">事故报告和处理</a></li>--%>
            <%--</ul>--%>
        </c:if>
        <!--职业健康管理 管理档案-->
        <c:if test="${leftBasic == 10}">
            <ul>
            <li><a onclick="sub1()" data-title="职卫检测、体检" href="javascript:void(0)">职卫检测、体检</a></li>
            <li><a onclick="sub2()" data-title="职业卫生管理" href="javascript:void(0)">职业卫生管理</a></li>
            <li><a onclick="sub3()" data-title="职业卫生宣传培训" href="javascript:void(0)">职业卫生宣传培训</a></li>
            <li><a onclick="sub4()" data-title="职业病危害因素监测与检测评价" href="javascript:void(0)">职业病危害因素检测评价</a></li>
            <li><a onclick="sub5()" data-title="用人单位职业健康监护管理" href="javascript:void(0)">职业健康监护管理</a></li>
            <li><a onclick="sub6()" data-title="劳动者个人职业健康监护" href="javascript:void(0)">劳动者个人职业健康监护</a> </li>
            <li><a onclick="sub7()" data-title="其他" href="javascript:void(0)">其他</a>
            </li>
            </ul>
            <iframe src="${ly }/company/evaluate/detection-list" onload="loadFrame()" scrolling="no" id="iframe"
            frameborder="0" width="100%" height="700px" ></iframe>
            <script>
            function sub1(){
            $("#iframe").attr("src", "${ly }/company/tables/tab-taizhang");
            }
            function sub2(){
            $("#iframe").attr("src", "${ly }/company/tables/tab-health/2");
            }
            function sub3(){
            $("#iframe").attr("src", "${ly }/company/tables/tab-health/3");
            }
            function sub4(){
            $("#iframe").attr("src", "${ly }/company/tables/tab-health/4");
            }
            function sub5(){
            $("#iframe").attr("src", "${ly }/company/tables/tab-health/5");
            }
            function sub6(){
            $("#iframe").attr("src", "${ly }/company/cyryjkjh");
            }
            function sub7(){
            $("#iframe").attr("src", "${ly }/company/tables/tab-health/7");
            }
            </script>
        </c:if>
        <!--在线监测设置-->
        <c:if test="${leftBasic == 11}">
            <ul>
            <li><a onclick="sub1()" data-title="传感器列表" href="javascript:void(0)">传感器列表</a> </li>
            <li><a onclick="sub2()" data-title="传感器设置" href="javascript:void(0)">传感器设置</a></li>
            </ul>
            <iframe src="${ly }/village/toSensorList" onload="loadFrame()" scrolling="no" id="iframe" frameborder="0"
            width="100%" height="700px" ></iframe>
            <script>
            function sub1(){
            $("#iframe").attr("src", "${ly }/village/toSensorList");
            }
            function sub2(){
            $("#iframe").attr("src", "${ly }/village/toAddSensor");
            }
            </script>


        </c:if>
        <c:if test="${leftBasic == 12}">
            <ul>
            <li><a onclick="sub1()" data-title="实时监控" href="javascript:void(0)">实时监控</a></li>
            <li><a onclick="sub2()" data-title="报警设备" href="javascript:void(0)">报警设备</a></li>
            </ul>
            <iframe src="${ly}/village/to_monitor-list?flag=2" onload="loadFrame()" scrolling="no" id="iframe"
            frameborder="0" width="100%" height="700px" ></iframe>
            <script>
            function sub1(){
            $("#iframe").attr("src","${ly}/village/to_monitor-list?flag=2");
            }
            function sub2(){
            $("#iframe").attr("src", "${ly}/village/company/zdlist?hazard=1&yzz=1");
            }
            </script>
        </c:if>
        <c:if test="${leftBasic == 13}">
            <ul>
            <li><a onclick="sub1()" data-title="管控架构" href="javascript:void(0)">管控架构</a></li>
            <li><a onclick="sub2()" data-title="机制文件" href="javascript:void(0)">机制文件</a></li>
            </ul>

            <iframe src="${ly }/company/study/department-list?sign=2" onload="loadFrame()" scrolling="no" id="iframe"
            frameborder="0" width="100%" height="700px" ></iframe>
            <script>
            function sub1(){
            $("#iframe").attr("src","${ly }/company/study/department-list?sign=2");
            }
            function sub2(){
            $("#iframe").attr("src", "${ly }/company/source/management-guide");
            }
            </script>
        </c:if>
        <c:if test="${leftBasic == 14}">
            <ul>
            <li><a onclick="sub0()" data-title="重大危险源" href="javascript:void(0)">重大危险源</a></li>
            <li><a onclick="sub1()" data-title="设备设施管理" href="javascript:void(0)">设备设施管理</a></li>
            <li><a onclick="sub2()" data-title="人员管理" href="javascript:void(0)">人员管理</a></li>
            <li><a onclick="sub6()" data-title="实时监控" href="javascript:void(0)">实时监控</a></li>
            <li><a onclick="sub3()" data-title="在线检测" href="javascript:void(0)">在线检测</a></li>
            <li><a onclick="sub4()" data-title="风险分布图" href="javascript:void(0)">风险分布图</a></li>
            <li><a onclick="sub5()" data-title="公示" href="javascript:void(0)">公示</a></li>
            </ul>

            <iframe src="${ly }/company/source/equipment?type=1" onload="loadFrame()" scrolling="no" id="iframe"
            frameborder="0" width="100%" height="700px" ></iframe>
            <script>
            function sub0(){
            $("#iframe").attr("src", "${ly }/company/information/information3" );
            }
            function sub1(){
            $("#iframe").attr("src", "${ly }/company/source/equipment?type=1" );
            }
            function sub2(){
            $("#iframe").attr("src", "${ly }/company/source/staff" );
            }
            function sub3(){
            $("#iframe").attr("src", "${ly }/company/threeLeft?leftBasic=12");
            }
            function sub4(){
            $("#iframe").attr("src", "${ly }/company/source/distribution");
            }
            function sub5(){
            $("#iframe").attr("src", "${ly }/company/source/publicity?flag=1");
            }
            function sub6(){
            $("#iframe").attr("src", "${ly }/village/to_monitor-list?flag=1");
            }
            </script>
        </c:if>
        <c:if test="${leftBasic == 15}">
            <ul>
            <li><a onclick="sub1()" data-title="非化工液氨使用企业" href="javascript:void(0)">非化工液氨使用企业</a></li>
            <li><a onclick="sub2()" data-title="粉尘涉爆企业" href="javascript:void(0)">粉尘涉爆企业</a></li>
            <li><a onclick="sub3()" data-title="高温熔融金属现场运输" href="javascript:void(0)">高温熔融金属现场运输</a></li>
            <li><a onclick="sub4()" data-title="工贸行业有限空间安全作业" href="javascript:void(0)">工贸行业有限空间安全作业</a></li>
            <li><a onclick="sub5()" data-title="冶金企业煤气" href="javascript:void(0)">冶金企业煤气</a></li>
            </ul>

            <iframe src="${ly }/company/source/specialWork?type=1" onload="loadFrame()" scrolling="no" id="iframe"
            frameborder="0" width="100%" height="700px" ></iframe>
            <script>
            function sub1(){
            $("#iframe").attr("src", "${ly }/company/source/specialWork?type=1" );
            }
            function sub2(){
            $("#iframe").attr("src", "${ly }/company/source/specialWork?type=2" );
            }
            function sub3(){
            $("#iframe").attr("src","${ly }/company/source/specialWork?type=3");
            }
            function sub4(){
            $("#iframe").attr("src", "${ly }/company/source/specialWork?type=4");
            }
            function sub5(){
            $("#iframe").attr("src", "${ly }/company/source/specialWork?type=5");
            }
            </script>
        </c:if>
        <c:if test="${leftBasic == 16}">
            <ul>
            <li><a onclick="sub1()" data-title="应急预案" href="javascript:void(0)">应急预案</a>
            </li>
            <li><a onclick="sub2()" data-title="消防设备设施" href="javascript:void(0)">消防设备设施</a></li>

            <li><a onclick="sub3()" data-title="应急设备设施" href="javascript:void(0)">应急设备设施</a></li>
            </ul>
            <iframe src="${ly }/company/tables/tab-yjlist" onload="loadFrame()" scrolling="no" id="iframe"
            frameborder="0" width="100%" height="700px" ></iframe>
            <script>
            function sub1(){
            $("#iframe").attr("src", "${ly }/company/tables/tab-yjlist" );
            }
            function sub2(){
            $("#iframe").attr("src", "${ly }/company/facilities/facilities-list?flag=1" );
            }
            function sub3(){
            $("#iframe").attr("src","${ly }/company/facilities/facilities-list?flag=2");
            }
            </script>
        </c:if>
        <c:if test="${leftBasic == 17}">
            <ul>
            <li><a onclick="sub1()" style="padding-left:55px;" data-title="持证上岗" href="javascript:void(0)">持证上岗</a></li>
            <li><a onclick="sub2()" style="padding-left:55px;" data-title="安全生产教育台账"
            href="javascript:void(0)">教育台账</a></li>
            <li><a onclick="sub3()" target="_blank" href="javascript:void(0)" data-title="安全培训" >安全培训</a></li>
            </ul>
            <iframe src="${ly }/company/tables/tab-yjlist" onload="loadFrame()" scrolling="no" id="iframe"
            frameborder="0" width="100%" height="700px" ></iframe>
            <script>
            function sub1(){
            $("#iframe").attr("src", "${ly }/company/train/special-list?spFlag=0" );
            }
            function sub2(){
            $("#iframe").attr("src", "${ly }/company/tables/tab-anlist" );
            }
            function sub3(){
            $("#iframe").attr("src","http://exam.dicarl.com/index.php?user-app-login&mobile=${userName}");
            }
            </script>
        </c:if>
        <c:if test="${leftBasic == 18}">
            <ul>
            <li><a onclick="sub1()" data-title="主要设备清单" href="javascript:void(0)">主要设备清单</a></li>
            <li><a onclick="sub2()" data-title="特种设备检测" href="javascript:void(0)">特种设备检测</a></li>
            <li><a onclick="sub3()" data-title="防雷防静电" href="javascript:void(0)">防雷防静电</a></li>
            <li><a onclick="sub4()" data-title="安全设施登记台账" href="javascript:void(0)">安全设施登记台账</a></li>
            </ul>

            <iframe src="${ly }/company/product/mequipment-list" onload="loadFrame()" scrolling="no" id="iframe"
            frameborder="0" width="100%" height="700px" ></iframe>
            <script>
            function sub1(){
            $("#iframe").attr("src", "${ly }/company/product/mequipment-list" );
            }
            function sub2(){
            $("#iframe").attr("src","${ly }/company/sequipment/sequipment-list" );
            }
            function sub3(){
            $("#iframe").attr("src","${ly }/company/product/lightning-list");
            }
            function sub4(){
            $("#iframe").attr("src","${ly }/company/safety-facilities");
            }
            </script>
        </c:if>
        </div>

        </body>
        </html>
        <script>
        function loadFrame(){
        $("#iframe").contents().find("nav").hide();
        console.log("onload")
        }
        function loadFrame2(){

        }


        localStorage.setItem('areaType', 2);
        <%--function threeActive(v){--%>
        <%--var str = document.querySelectorAll('.children_div')--%>
        <%--v.classList.add('active')--%>
        <%--var arr = v.parentNode.parentNode--%>
        <%--arr.style.display = "none"--%>
        <%--if(v.innerText=='教育培训'){--%>
        <%--str[0].style.display = 'block'--%>
        <%--str[1].style.display = 'none'--%>
        <%--}else if(v.innerText=='设备设施管理'){--%>
        <%--str[0].style.display = 'none'--%>
        <%--str[1].style.display = 'block'--%>
        <%--}--%>

        <%--}--%>
        </script>
