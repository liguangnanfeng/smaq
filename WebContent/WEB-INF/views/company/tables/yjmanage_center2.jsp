        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%@ include file="/WEB-INF/views/taglibs.jsp"%>
    <!DOCTYPE html>
        <html>
        <head>
        <meta charset="utf-8">
        <%@ include file="/WEB-INF/inc/back-header.inc"%>
        <title>应急管理中心</title>
        </head>

        <style>
        body {
        width: 100%;
        height: 100vh;
        margin: 0;
        padding: 0;
        }
        .func>section {
        height: 77px;
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: 18px;
        margin-right: 31px;
        font-weight: bold;
        }
            <%--        background-color: #f95b26;--%>
            <%--        background-image: linear-gradient(180deg, #f95b26 10%, #fb801b 100%);--%>
        .func {
            position: relative;
            display: flex;
            justify-content: center;
        }

        a {
        text-decoration: none;
        }
            a:hover {
            text-decoration: none;
            color:#FF3939;
            }
        span.time {
            font-size: 25px;
            position: relative;
        }



        span.time2 {
        font-size: 45px;
        color: #fff;
        }
        .com{
            display: flex;
            justify-content: center;
            position: relative;
            top: 10%;
        } main{
            width:100%;
            height: 100vw;
            background: url(/images/companyImg/newimg/yj.jpg) no-repeat center;
            background-size: 100% 100%;
<%--            margin-top: 22px;--%>
            position: relative;
            }
            #topleft {
            display: flex;
            position: relative;
            justify-content: space-between;
            padding: 0 70px;
            width: 100%;

            }

            .topleftmod {
            display: flex;
<%--            margin-left: 9%;--%>
            }

            .antai {
            color: #19365F;
            font-size: 24px;
            font-weight: bold;
            }

            .logo {
            margin-left: 10px;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            }
            header{
            height:86px;
            display:flex;
            align-items: center;
            }
        </style>

        <body>
            <header>
            <div id="topleft">
            <div class="topleftmod">
            <img style="display: flex;" width="60" height="60" src="/images/companyImg/newimg/logo.png" />
            <span class="logo" style="">
            <span class="antai">应急管理中心</span>
            <span class="webadd">www.antai.com</span>
            </span>
            </div>

            <div style="display: flex; align-items: center;">
            <span id="time" class="time"></span>
            </div>

            </div>

            </header>

        <main>
            <div class="com"><span class="time2" style="color:#64FFFF">${userName}</span></div>

            </main>
            <script>
                var w=$('main').height();
                $('main').height(w*0.4015625)

            </script>
            <div class="func">
                <section><a href="/steel/searchPage2">应急管理</a></section>
                <section><a href="/global/command">指挥中心</a></section>
                <section><a href="/village/to_monitor-list?flag=2" data-title="实时监控">实时监控</a></section>
                <section><a  onClick="showz()"  data-title="在线监测">在线监测</a></section>
                <section><a  onClick="showp()"   data-title="动态预警">预警动态</a></section>
                <section><a target="_blank" href="http://www.emc.gov.cn/">应急信息</a></section>
            </div>



            <style>
            .t{
            margin-top:20px
            }
            .one_tx{
            margin-right:2%;
            width:30%;
            }
            </style>

            <%--预警动态弹出框--%>
            <div id="yujin" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" style="width:800px">
            <div class="modal-content radius">

            <div class="modal-header">
            <h3 class="modal-title">预警动态(${count[0] + count[1] + count[2] + count[3] + count[4] + count[5] + count[6] + zdsgyh + count[8] + count[9]})</h3>
            <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>

            <div class="modal-body">

            <div>
            <a class="one_tx radius pos-r" onClick="showping()"  href="javascript:;" style="margin-left:2%;background:#FFA160;">
            <img alt="" src="${ly }/images/httb4.png"/>
            <font class="font_tx1 pos-a <c:if test="${count[4]+ count[5]+ count[8] >0}">blink</c:if>">${count[4]+ count[5]+ count[8] }</font>
            <font class="font_tx2 pos-a">安全许可</font>
            </a>

            <a class="one_tx radius pos-r" data-title="安全培训" data-href="${ly }/company/train/special-list?spFlag=0&isTime=3" onclick="Hui_admin_tab(this)" href="javascript:;" style="margin-left:1%;background:#46D1E5;">
            <img alt="" src="${ly }/images/httb1.png"/>
            <font class="font_tx1 pos-a <c:if test="${count[0] >0}">blink</c:if>">${count[0] }</font>
            <font class="font_tx2 pos-a">安全培训</font>
            </a>

            <a class="one_tx radius pos-r"  onClick="showping2()" href="javascript:;" style="background:#60D1FF;">
            <img alt="" src="${ly }/images/httb2.png"/>
            <font class="font_tx1 pos-a <c:if test="${count[1]+count[2]+count[3] >0}">blink</c:if>">${count[1]+count[2]+count[3] }</font>
            <font class="font_tx2 pos-a">检测检验</font>
            </a>


            <a class="one_tx radius pos-r t" href="javascript:;"   style="background:#ea4f21;    margin-left: 2%;;margin-right:0">
            <img alt="" src="${ly }/images/httb9.png"/>
            <font class="font_tx1 pos-a"></font>
            <font class="font_tx2 pos-a">隐患排查</font>
            <%--  onclick="show_dialog('隐患排查', '${ly }/village/check-item3?status=2')"--%>
            </a>


            <a class="one_tx radius pos-r t" href="javascript:;"   style="background:#ea4f21;margin-left: 3%;">
            <img alt="" src="${ly }/images/httb9.png"/>
            <font class="font_tx1 pos-a "></font>
            <font class="font_tx2 pos-a">隐患治理</font>
            <%--  onclick="show_dialog('隐患治理', '${ly }/village/check-item3?status=2')"--%>
            </a>

            <a class="one_tx radius pos-r t" href="javascript:;" onclick="show_dialog('重大隐患', '${ly }/village/check-item3?status=2')"  style="background:#ea4f21;margin-right:0">
            <img alt="" src="${ly }/images/httb9.png"/>
            <font class="font_tx1 pos-a <c:if test="${count[7] >0}">blink</c:if>">${count[7]}</font>
            <font class="font_tx2 pos-a">重大隐患</font>
            </a>
            </div>
            </div>
            </div>
            </div>


            </div>

            <!-- 评价评估弹窗 -->

            <div id="win-ping" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" style="width:800px">
            <div class="modal-content radius">
            <div class="modal-header">
            <h3 class="modal-title">评价评估</h3>
            <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body">
            <a class="one_tx radius pos-r" data-dismiss="modal" aria-hidden="true" data-title="各类评价评估" data-href="${ly }/company/evaluate/evaluation-list?flag=0&isTime=6" onclick="Hui_admin_tab(this)" href="javascript:;" style="background:#FFA160;">
            <img alt="" src="${ly }/images/httb4.png"/>
            <font class="font_tx1 pos-a <c:if test="${count[4] >0}">blink</c:if>">${count[4] }</font>
            <font class="font_tx2 pos-a">评价评估</font>
            </a>
            <a class="one_tx radius pos-r" data-dismiss="modal" aria-hidden="true" data-title="各类许可证" data-href="${ly }/company/evaluate/zheng-list?isTime=6" onclick="Hui_admin_tab(this)" href="javascript:;" style="background:#FF9346;">
            <img alt="" src="${ly }/images/httb6.png"/>
            <font class="font_tx1 pos-a <c:if test="${count[5] >0}">blink</c:if>">${count[5] }</font>
            <font class="font_tx2 pos-a">各类许可证</font>
            </a>
            <a class="one_tx radius pos-r" data-dismiss="modal" aria-hidden="true" data-title="应急预案" data-href="${ly }/company/tables/tab-yjlist?isTime=6" onclick="Hui_admin_tab(this)" href="javascript:;" style="margin-right:0;background:#ff8048;">
            <img alt="" src="${ly }/images/httb10.png"/>
            <font class="font_tx1 pos-a <c:if test="${count[8] >0}">blink</c:if>">${count[8] }</font>
            <font class="font_tx2 pos-a">应急预案</font>
            </a>
            <a class="one_tx radius pos-r" data-dismiss="modal" aria-hidden="true" data-title="各类评价评估" href="javascript:;" style="background:#FFA160;    margin-top: 20px;">
            <img alt="" src="${ly }/images/httb4.png"/>
            <%--  data-href="" onclick="Hui_admin_tab(this)"--%>
            <font class="font_tx1 pos-a <c:if test="${count[4] >0}">blink</c:if>">${count[4] }</font>
            <font class="font_tx2 pos-a">备案块</font>
            </a>
            </div>
            </div>
            </div>
            </div>

            <!-- 检验检测弹窗 -->
            <div id="win-ping2" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" style="width:540px">
            <div class="modal-content radius">
            <div class="modal-header">
            <h3 class="modal-title">评价评估</h3>
            <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <style>.w{width:48.5%}</style>
            <div class="modal-body">
            <a class="one_tx radius pos-r w"  data-title="特种设备检测" data-href="${ly }/company/sequipment/sequipment-list?isTime=3" onclick="Hui_admin_tab(this)" href="javascript:;" style="background:#60D1FF;">
            <img alt="" src="${ly }/images/httb2.png"/>
            <font class="font_tx1 pos-a <c:if test="${count[1] >0}">blink</c:if>">${count[1] }</font>
            <font class="font_tx2 pos-a">特种设备检测</font>
            </a>
            <a class="one_tx radius pos-r w" data-title="职卫检测、体检" data-href="${ly }/company/evaluate/detection-list?isTime=3" onclick="Hui_admin_tab(this)" href="javascript:;" style="background:#34B5ED;margin-right:0;">
            <img alt="" src="${ly }/images/httb3.png"/>
            <font class="font_tx1 pos-a <c:if test="${count[2] + count[3] >0}">blink</c:if>">${count[2] + count[3]}</font>
            <font class="font_tx2 pos-a">职卫检测、体检</font>
            </a>
            </div>
            </div>
            </div>
            </div>
            <div id="jianche" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" style="width:600px">
            <div class="modal-content radius">
            <div class="modal-header">
            <h3 class="modal-title" style="color:red">在线监测预警.报警</h3>
            <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body">
            <style>
                .aa{
                   width:48%;
                   margin:3px;
                }

            </style>
            <c:choose>
                <c:when test="${c.hazard eq '1'}">
                    <a class="one_tx radius pos-r aa"  data-title="重大危险源" onclick="Hui_admin_tab(this)" href="javascript:;" data-href="${ly }/village/company/zdlist?hazard=1" style="background:#1450DD;" >
                    <font style="width: 95%;text-align: center;left:auto;" id="zd_font" class="font_tx1 pos-a "></font>
                    <font style="left:5px;width:88%;text-align:center;" class="font_tx2 pos-a" title="重大危险源">重大危险源</font>
                    </a>
                </c:when>
                <c:otherwise>
                    <a class="one_tx radius pos-r aa"  data-title="重点部位关键装置实时监控" onclick="Hui_admin_tab(this)" href="javascript:;" data-href="/village/to_monitor-list?flag=1" style="background:#1450DD;" >
                    <font style="width: 95%;text-align: center;left:auto;" class="font_tx1 pos-a "></font>
                    <font style="left:5px;width:88%;text-align:center;" class="font_tx2 pos-a" title="重点部位">重点部位</font>
                    </a>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${c.danger eq '1'}">
                    <a class="one_tx radius pos-r aa"  data-title="有限（受限）空间作业" onclick="Hui_admin_tab(this)" href="javascript:;" data-href="${ly }/village/company/gwlist?danger=1" style="background:#1450DD;" >
                    <font id="gw_font" style="width: 95%;text-align: center;left:auto;" class="font_tx1 pos-a "></font>
                    <font style="left:13px;width:88%;text-align:center;" class="font_tx2 pos-a" title="有限（受限）空间作业">有限（受限）空间作业</font>
                    </a>
                </c:when>
                <c:otherwise>
                    <a class="one_tx radius pos-r aa"  data-title="重点部位关键装置实时监控" onclick="Hui_admin_tab(this)" href="javascript:;" data-href="/village/to_monitor-list?flag=2" style="background:#1450DD;" >
                    <font style="width: 95%;text-align: center;left:auto;" class="font_tx1 pos-a "></font>
                    <font style="left:13px;width:88%;text-align:center;" class="font_tx2 pos-a" title="关键装置">关键装置</font>
                    </a>
                </c:otherwise>
            </c:choose>
            <%-- <a class="one_tx radius pos-r" data-title="河道污染" onclick="Hui_admin_tab(this)" href="javascript:void(0)" data-href="${ly }/village/company/wwlist?danger=1" style="width:100%;margin-right:0;background:#DF3030;">
            <img alt="" src="${ly }/images/httb8.png"/>
            <font class="font_tx1 pos-a "></font>
            <font class="font_tx2 pos-a" title="河道污染">河道污染</font>
          </a> --%>

            </div>



            </div>
            </div>
            </div>

            </body>
            <script>
            function showp(){
            $("#yujin").modal("show")
            }
            function showz(){
            $("#jianche").modal("show")
            }
            function showping(){
            $("#win-ping").modal("show")}
            function showping2(){
            $("#win-ping2").modal("show")}

            var endTime = new Date(new Date().getTime());
            console.log(endTime);
            var myDate = new Date();
            var mytime=myDate.toLocaleTimeString();     //获取当前时间
            var a=myDate.toLocaleDateString();      //获取日期与时间
            console.log(a);
            $("#time").text('日期：'+a);
            </script>
        </html>
