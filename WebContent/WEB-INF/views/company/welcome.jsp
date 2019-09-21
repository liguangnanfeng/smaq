  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/taglibs.jsp"%>
    <!DOCTYPE HTML>
    <html>
    <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="mobile-web-app-capable" content="yes">
    <meta name="viewport" content="width=1400, initial-scale=1, maximum-scale=1, user-scalable=yes, minimal-ui">
    <%@ include file="/WEB-INF/inc/back-header.inc"%>
    <title>风险分级管控   隐患排查治理智能化平台</title>
    <meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
    <meta name="description" content="风险分级管控   隐患排查治理智能化平台">
    <style type="text/css">
    .panel-default{display: inline-block;border:0px;}
    body,html{background:#EBEEF3;min-width:1200px;}
    .title_xw{width:100%;font-weight:bold;font-size:15px;}
    .one_notice{width:100%;margin-top:0px;}
    .list_aq{margin-top:10px;}
    .text-overflow{display: inline-block;width:70%;line-height:25px;color:#7C7C7C;margin-right:0px;}
    .text-overflow i{height:3px;width:3px;background:#7C7C7C;border-radius:50%;display:inline-block;vertical-align: middle;margin-right:5px;}
    .text-overflow:hover a{color:#5889D8;text-decoration: underline;}
    .text-overflow:hover i{background:#5889D8;}
    .text-riqi{float:right;color:#AEAEAE;line-height:25px;}
    .one_tx{margin-top:20px;width:30%;margin-right:2%}
    body .div_company2 span{margin-top:10px;}
    .clearfix:after{content:"\20";display:block;height:0;clear:both;visibility:hidden}
    .clearfix{zoom:1}
    .tabBar {border-bottom: 2px solid #F6F6F6}
    .tabBar span {background-color: #fff;cursor: pointer;font-size:15px;display: inline-block;float: left;font-weight: bold;height: 40px;line-height: 40px;padding: 0 35px}
    .tabBar span.current{background-color: #fff;color: #FF9900;border:0px;border-bottom:2px solid #FF9900}
    .tabCon {display: none;border:0px;padding:0;}
    .bt_zd{background:#DF2E30;padding:0 9px;color:#fff;font-size:12px;margin-left:10px;border-radius:5px;height:25px;line-height:25px;display: inline-block;}
    .div_zhe{width:100%;}
    .change_zhe{width:100%;height:35px;position: relative;}
    .btn-group{position: absolute;right:0;top:14px;}
    .btnyh{color: #333;background: #e6e6e6;}
    .btnyhxz{color: #fff;background: #5a98de;}
    body .iconxw{transform: rotate(-90deg);float:right;margin-right:-5px;margin-top: -3px;}
    </style>
    <script type="text/javascript">
    $(function(){
    $(".title_danger").on("click",function(){
    if($(this).parent().hasClass("listdxz")){
    $(this).parent().removeClass("listdxz");
    $(this).find("i").html("&#xe6d5;");
    }else{
    $(this).parent().addClass("listdxz");
    $(this).find("i").html("&#xe6d7;");
    }
    });
    $.Huitab("#tab_yj .tabBar span","#tab_yj .tabCon","current","click","0")
    });
    function openzl(){
    $("#upload-zl").modal("show")}


    function showping(){
    $("#win-ping").modal("show")}
    function showping2(){
    $("#win-ping2").modal("show")}
    function showping3(){
    $("#win-ping3").modal("show")}
    function showping4(){
    $("#win-ping4").modal("show")}

    </script>
    </head>
    <body>
    <c:set var="c1_2" value="0"/>
    <c:set var="c1_3" value="0"/>
    <c:set var="c2_2" value="0"/>
    <c:set var="c2_3" value="0"/>
    <c:set var="c3_2" value="0"/>
    <c:set var="c3_3" value="0"/>
    <c:set var="c4_2" value="0"/>
    <c:set var="c4_3" value="0"/>
    <c:forEach items="${dangerC }" var="be">
      <c:if test="${be.flag == 1 and be.status == 2}"><c:set var="c1_2" value="${be.c }"/></c:if>
      <c:if test="${be.flag == 1 and be.status == 3}"><c:set var="c1_3" value="${be.c }"/></c:if>
      <c:if test="${be.flag == 2 and be.status == 2}"><c:set var="c2_2" value="${be.c }"/></c:if>
      <c:if test="${be.flag == 2 and be.status == 3}"><c:set var="c2_3" value="${be.c }"/></c:if>
      <c:if test="${be.flag == 3 and be.status == 2}"><c:set var="c3_2" value="${be.c }"/></c:if>
      <c:if test="${be.flag == 3 and be.status == 3}"><c:set var="c3_3" value="${be.c }"/></c:if>
      <c:if test="${be.flag == 4 and be.status == 2}"><c:set var="c4_2" value="${be.c }"/></c:if>
      <c:if test="${be.flag == 4 and be.status == 3}"><c:set var="c4_3" value="${be.c }"/></c:if>
    </c:forEach>

    <c:set var="d1_2" value="0"/>
    <c:set var="d1_3" value="0"/>
    <c:set var="d2_2" value="0"/>
    <c:set var="d2_3" value="0"/>
    <c:set var="d3_2" value="0"/>
    <c:set var="d3_3" value="0"/>
    <c:set var="d4_2" value="0"/>
    <c:set var="d4_3" value="0"/>
    <c:forEach items="${dangerC2 }" var="be">
      <c:if test="${be.flag == 1 and be.status == 2}"><c:set var="d1_2" value="${be.c }"/></c:if>
      <c:if test="${be.flag == 1 and be.status == 3}"><c:set var="d1_3" value="${be.c }"/></c:if>
      <c:if test="${be.flag == 2 and be.status == 2}"><c:set var="d2_2" value="${be.c }"/></c:if>
      <c:if test="${be.flag == 2 and be.status == 3}"><c:set var="d2_3" value="${be.c }"/></c:if>
      <c:if test="${be.flag == 3 and be.status == 2}"><c:set var="d3_2" value="${be.c }"/></c:if>
      <c:if test="${be.flag == 3 and be.status == 3}"><c:set var="d3_3" value="${be.c }"/></c:if>
      <c:if test="${be.flag == 4 and be.status == 2}"><c:set var="d4_2" value="${be.c }"/></c:if>
      <c:if test="${be.flag == 4 and be.status == 3}"><c:set var="d4_3" value="${be.c }"/></c:if>
    </c:forEach>
    <c:set var="zdsgyh" value="${d1_2 + d2_2 + d3_2 + d4_2}"/>
    <div class="panel panel-default" style="width:83%;margin-left:10px;float:left;background:none;">
    <div class="panel-body">
    <div class="div_tx">
    <div class="div_img">
    <img alt="" src="${ly }/images/mrqy.jpg"/>
    </div>
    <div class="div_company">
    <p>
    ${c.name }
    <c:if test="${c.isKey == 1}">
      <label class="bt_zd">重点企业</label>
    </c:if>
    <c:if test="${moveCompany == 1 }">
      <c:if test="${flage == 2}">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="${ly }/steel/back/steel" target="_parent">
        <strong style="color:red;">${nameBefore }</strong>
        </a>
      </c:if>
      <c:if test="${flage == 1}">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="${ly }/global/back/global" target="_parent">
        <strong style="color:red;">${nameBefore }</strong>
        </a>
      </c:if>
    </c:if>
    </p>
    <span>统一社会信用代码：${c.license }</span>
    <span>入驻时间：<fmt:formatDate value="${session_user.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
    <span>
    <a onclick="show_dialog('企业定位', '${ly }/company/information/information4')" href="javascript:;" class="lianj_com"><img alt="" src="${ly }/images/fore/dt.jpg"/>企业定位</a>
    </span>
    </div>
    <div class="div_company2" style="padding-left:30px;">
    <p style="margin-top:5px">风险等级：<font id="level-font">${c.dlevel }</font>
    <a style="margin-left:10px;" onclick="openzl()" href="javascript:;" class="lianj_com"><img alt="" src="${ly }/images/fore/bj.jpg"/></a>
    </p>
    <span>
    <a data-href="Zheng=2" onclick="show_tab('重点部位/装置实时监控', '/village/to_monitor-list?flag=2')" href="javascript:;" class="lianj_com"><img alt="" src="${ly }/images/fore/jko.jpg"/>重点部位/装置实时监控</a>
    </span>
    <span>
    <a data-href="${ly }/company/tables/tab-yjlist" data-title="应急救援" onclick="Hui_admin_tab(this)" href="javascript:;" class="lianj_com"><img alt="" src="${ly }/images/fore/yj.jpg"/>应急救援</a>
    </span>
    </div>
    </div>
    <div class="mt-15 pos-r">
    <div class="div_tx div_yj" style="padding-top:15px;">
    <div class="title_xw pos-r" style="border-bottom:1px solid #F6F6F6;color:#FB0005;"><div class="bt_yuj">预警栏(${count[0] + count[1] + count[2] + count[3] + count[4] + count[5] + count[6] + zdsgyh + count[8] + count[9]})</div></div>

    <%--  ①安全许可--%>
    <%--  ②安全培训--%>
    <%--  ③检测检验--%>
    <%--  ④隐患排查--%>
    <%--  ⑤隐患治理--%>
    <%--  ⑥重大隐患--%>
    <%--  --%>


    <a class="one_tx radius pos-r" onClick="showping()"  href="javascript:;" style="margin-left:1%;background:#FFA160;">
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



    <a class="one_tx radius pos-r" href="${ly}/village/hidden-danger-list?flag=1&status=1"   style="background:#ea4f21;    margin-left: 1%;;margin-right:0">
    <img alt="" src="${ly }/images/httb9.png"/>
      <font class="font_tx1 pos-a <c:if test="${counts >0}">blink</c:if>">${counts}</font>
    <font class="font_tx2 pos-a">隐患排查</font>
    <%--  onclick="show_dialog('隐患排查', '${ly }/village/check-item3?status=2')"--%>
    </a>


    <a class="one_tx radius pos-r" href="/village/hidden-danger-list?flag=1&status=1"   style="background:#ea4f21;margin-left: 3%;">
    <img alt="" src="${ly }/images/httb9.png"/>
      <font class="font_tx1 pos-a <c:if test="${counts1 >0}">blink</c:if>">${counts1}</font>

      <font class="font_tx2 pos-a">隐患治理</font>
    <%--  onclick="show_dialog('隐患治理', '${ly }/village/check-item3?status=2')"--%>
    </a>

    <a class="one_tx radius pos-r" href="javascript:;" onclick="show_dialog('重大隐患', '${ly }/village/check-item3?status=2')"  style="background:#ea4f21;margin-right:0">
    <img alt="" src="${ly }/images/httb9.png"/>
    <font class="font_tx1 pos-a <c:if test="${count[7] >0}">blink</c:if>">${count[7]}</font>
    <font class="font_tx2 pos-a">重大隐患</font>
    </a>






    <%--              <a class="one_tx radius pos-r" data-title="定期检查" data-href="${ly }/tradeclique/check-list?flag=1&status=1" onclick="Hui_admin_tab(this)" href="javascript:;" style="background:#ED6840;">--%>
    <%--                <img alt="" src="${ly }/images/httb5.png"/>--%>
    <%--                <font class="font_tx1 pos-a <c:if test="${count[9] >0}">blink</c:if>">${count[9] }</font>--%>
    <%--                <font class="font_tx2 pos-a">定期检查</font>--%>
    <%--              </a>--%>
    </div>
    <div class="div_tx div_yh pos-a" style="height:313px;padding: 13px 20px 0px;">
    <div class="title_xw pos-r" style="border-bottom:1px solid #F6F6F6;color:#FB0005;"><div class="bt_yuj" style="width:180px">在线监测预警.报警</div></div>
    <div class="list_zd" >
    <%-- <a class="one_tx radius pos-r" id="toOpenDanger"  href="javascript:;"  style="width:100%;margin-right:0;background:#1450DD;">
       <img alt="" src="${ly }/images/httb7.png"/>
       <font class="font_tx1 pos-a <c:if test="${totalCount >0}">blink</c:if>">${totalCount }</font>
       <font class="font_tx2 pos-a" title="重大危险源/高危作业">重大危险源/高危作业</font>
     </a> --%>
    <c:choose>
      <c:when test="${c.hazard eq '1'}">
        <%--onclick="Hui_admin_tab(this)"--%>
        <%--data-href="${ly }/village/company/zdlist?hazard=1"--%>
        <a class="one_tx radius pos-r"  data-title="重大危险源" onClick="showping3()" href="javascript:;"  style="width:100%;margin-right:0;background:#1450DD;" >
        <font style="width: 95%;text-align: center;left:auto;" id="zd_font" class="font_tx1 pos-a "></font>
        <font style="left:5px;width:88%;text-align:center;" class="font_tx2 pos-a" title="重大危险源">重大危险源</font>
        </a>
      </c:when>
      <c:otherwise>
        <%--data-href="/village/to_monitor-list"--%>
        <a class="one_tx radius pos-r"  data-title="重点部位关键装置实时监控" onclick="showping3()" href="javascript:;" style="width:100%;margin-right:0;background:#1450DD;" >
        <font style="width: 95%;text-align: center;left:auto;" class="font_tx1 pos-a "></font>
        <font style="left:5px;width:88%;text-align:center;" class="font_tx2 pos-a" title="重点部位">重点部位</font>
        </a>
      </c:otherwise>
    </c:choose>
    <c:choose>
      <c:when test="${c.danger eq '1'}">
        <%--data-href="${ly }/village/company/gwlist?danger=1"--%>
        <a class="one_tx radius pos-r"  data-title="易燃易爆有毒作业" onclick="showping4()"  href="javascript:;"  style="width:100%;margin-right:0;background:#1450DD;" >
        <font id="gw_font" style="width: 95%;text-align: center;left:auto;" class="font_tx1 pos-a "></font>
        <font style="left:13px;width:88%;text-align:center;" class="font_tx2 pos-a" title="易燃易爆有毒作业">易燃易爆、有毒作业</font>
        </a>
      </c:when>
      <c:otherwise>
        <%--data-href="/village/to_monitor-list"--%>
        <a class="one_tx radius pos-r"  data-title="重点部位关键装置实时监控" onclick="showping4()" href="javascript:;" style="width:100%;margin-right:0;background:#1450DD;" >
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
    <a class="one_tx radius pos-r" data-dismiss="modal" aria-hidden="true" data-title="各类评价评估" href="javascript:;" style="background:#FFA160;">
    <img alt="" src="${ly }/images/httb4.png"/>
    <%--  data-href="" onclick="Hui_admin_tab(this)"--%>
    <font class="font_tx1 pos-a <c:if test="${count[4] >0}">blink</c:if>">${count[4] }</font>
    <font class="font_tx2 pos-a">备案块</font>
    </a>
    </div>
    </div>
    </div>
    </div>
    <style>
    .fal{
    font-size:22px;
    text-align:center
    }
    </style>
    <!-- 检验检测弹窗 -->
    <div id="win-ping2" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:800px">
    <div class="modal-content radius">
    <div class="modal-header">
    <h3 class="modal-title">评价评估</h3>
    <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
    </div>
    <div class="modal-body">
    <a class="one_tx radius pos-r" data-title="特种设备检测" data-href="${ly }/company/sequipment/sequipment-list?isTime=3" onclick="Hui_admin_tab(this)" href="javascript:;" style="background:#60D1FF;">
    <img alt="" src="${ly }/images/httb2.png"/>
    <font class="font_tx1 pos-a <c:if test="${count[1] >0}">blink</c:if>">${count[1] }</font>
    <font class="font_tx2 pos-a">特种设备检测</font>
    </a>
    <a class="one_tx radius pos-r" data-title="职卫检测、体检" data-href="${ly }/company/evaluate/detection-list?isTime=3" onclick="Hui_admin_tab(this)" href="javascript:;" style="background:#34B5ED;margin-right:0;">
    <img alt="" src="${ly }/images/httb3.png"/>
    <font class="font_tx1 pos-a <c:if test="${count[2] + count[3] >0}">blink</c:if>">${count[2] + count[3]}</font>
    <font class="font_tx2 pos-a">职卫检测、体检</font>
    </a>
    </div>
    </div>
    </div>
    </div>

    <%--重点部位--%>
    <div id="win-ping3" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:400px">
    <div class="modal-content radius">
    <div class="modal-header">
    <h3 class="modal-title">在线监测预警.报警</h3>
    <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
    </div>
    <div class="modal-body fal">
    <c:choose>
      <c:when test="${c.hazard eq '1'}">
        重大危险源无报警
      </c:when>
      <c:otherwise>
        重点部位无报警
      </c:otherwise>
    </c:choose>
    </div>
    </div>
    </div>
    </div>

    <%--关键设备--%>
    <div id="win-ping4" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:400px">
    <div class="modal-content radius">
    <div class="modal-header">
    <h3 class="modal-title">在线监测预警.报警</h3>
    <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
    </div>
    <div class="modal-body fal">
    <c:choose>
      <c:when test="${c.danger eq '1'}">
          易燃易爆、有毒作业无故障
      </c:when>
      <c:otherwise>
        关键装置无故障
      </c:otherwise>
    </c:choose>

    </div>
    </div>
    </div>
    </div>



    <div class="div_tx mt-15">
    <div class="title_xw pb-5 pos-r" style="border-bottom:1px solid #F6F6F6">隐患治理数据</div>
    <div class="div_hz">
    <span>企业自查</span>
    <div class="div_hui">
    <a class="one_huiz" data-title="企业自查-隐患排查记录" data-href="${ly }/village/danger-index-list?flag=1" onclick="Hui_admin_tab(this)" href="javascript:;">
    <font>${c1_2 + c1_3}</font>
    <label>全部隐患</label>
    </a>
    <a class="one_huiz" data-title="企业自查-隐患排查记录" data-href="${ly }/village/danger-index-list?flag=1" onclick="Hui_admin_tab(this)" href="javascript:;">
    <font style="color:#FF9345"><fmt:formatNumber value="${(c1_2 + c1_3) == 0 ? 1 : c1_3 / (c1_2 + c1_3) }" pattern="0.0%"/></font>
    <label>整改率</label>
    </a>
    <a class="one_huiz" style="width:40%;border:0px;" onclick="show_dialog('重大事故隐患', '${ly }/village/check-item3?flag=1')" href="javascript:;" style="border:0px;">
    <font style="color:#E13031"><fmt:formatNumber value="${(d1_2 + d1_3) == 0 ? 1 : d1_3 / (d1_2 + d1_3) }" pattern="0.0%"/></font>
    <label>重大隐患整改率</label>
    </a>
    </div>
    </div>
    <div class="div_hz">
    <span>第三方检查</span>
    <div class="div_hui">
    <a class="one_huiz" data-title="第三方检查-隐患排查记录" data-href="${ly }/village/danger-index-list?flag=3" onclick="Hui_admin_tab(this)" href="javascript:;">
    <font>${c3_2 + c3_3}</font>
    <label>全部隐患</label>
    </a>
    <a class="one_huiz" data-title="第三方检查-隐患排查记录" data-href="${ly }/village/danger-index-list?flag=3" onclick="Hui_admin_tab(this)" href="javascript:;">
    <font style="color:#FF9345"><fmt:formatNumber value="${(c3_2 + c3_3) == 0 ? 1 : c3_3 / (c3_2 + c3_3) }" pattern="0.0%"/></font>
    <label>整改率</label>
    </a>
    <a class="one_huiz" style="width:40%;border:0px;" onclick="show_dialog('重大事故隐患', '${ly }/village/check-item3?flag=3')" href="javascript:;" style="border:0px;">
    <font style="color:#E13031"><fmt:formatNumber value="${(d3_2 + d3_3) == 0 ? 1 : d3_3 / (d3_2 + d3_3) }" pattern="0.0%"/></font>
    <label>重大隐患整改率</label>
    </a>
    </div>
    </div>
    <div class="div_hz" style="margin-right:0px;">
    <span>行政检查</span>
    <div class="div_hui">
    <a class="one_huiz" data-title="行政检查-隐患排查记录" data-href="${ly }/village/danger-index-list?flag=4" onclick="Hui_admin_tab(this)" href="javascript:;">
    <font>${c4_2 + c4_3}</font>
    <label>全部隐患</label>
    </a>
    <a class="one_huiz" data-title="行政检查-隐患排查记录" data-href="${ly }/village/danger-index-list?flag=4" onclick="Hui_admin_tab(this)" href="javascript:;">
    <font style="color:#FF9345"><fmt:formatNumber value="${(c4_2 + c4_3) == 0 ? 1 : c4_3 / (c4_2 + c4_3) }" pattern="0.0%"/></font>
    <label>整改率</label>
    </a>
    <a class="one_huiz" style="width:40%;border:0px;" onclick="show_dialog('重大事故隐患', '${ly }/village/check-item3?flag=4')" href="javascript:;" style="border:0px;">
    <font style="color:#E13031"><fmt:formatNumber value="${(d4_2 + d4_3) == 0 ? 1 : d4_3 / (d4_2 + d4_3) }" pattern="0.0%"/></font>
    <label>重大隐患整改率</label>
    </a>
    </div>
    </div>
    </div>
    <div class="div_tx mt-15" style="padding-top:15px">
    <div id="tab_yj" class="HuiTab">
    <div class="tabBar clearfix"><span>公共服务</span><span>安全许可服务</span></div>
    <div class="tabCon">
    <a class="one_tx2 radius" data-title="无锡特种设备检验" data-href="http://at.lbypmall.com/" onclick="Hui_admin_tab(this)" href="javascript:;" style="margin-left:1%">
    <img alt="" src="${ly }/images/fore/atpx3.jpg"/>
    <font class="font_txs2">无锡特种设备检验</font>
    </a>
    <a class="one_tx2 radius" data-title="劳保用品商城" data-href="http://shop.lbypmall.com/" onclick="Hui_admin_tab(this)" href="javascript:;">
    <img alt="" src="${ly }/images/fore/atpx2.jpg"/>
    <font class="font_txs2">劳保用品商城</font>
    </a>
    <a class="one_tx2 radius" data-title="安泰培训系统"  data-href="http://www.antaisafety.com/" onclick="Hui_admin_tab(this)" href="javascript:;">
    <img alt="" src="${ly }/images/fore/atpx.jpg"/>
    <font class="font_txs2">安泰培训系统</font>
    </a>
    <a class="one_tx2 radius" data-title="慈铭华康体检"  data-href="http://www.wxhktj.cn/Default.aspx " onclick="Hui_admin_tab(this)" href="javascript:;">
    <img alt="" src="${ly }/images/fore/hpw.jpg"/>
    <font class="font_txs2">慈铭华康体检</font>
    </a>
    <a class="one_tx2 radius" data-title="易安商城"  data-href="http://www.esafety.cn/shop/" onclick="Hui_admin_tab(this)" href="javascript:;">
    <img alt="" src="${ly }/images/fore/atpx1.jpg"/>
    <font class="font_txs2">易安商城</font>
    </a>
    <a class="one_tx2 radius" data-title="普天防雷检测"  data-href="http://www.ptfljt.com/" style="margin-right:0" onclick="Hui_admin_tab(this)" href="javascript:;">
    <img alt="" src="${ly }/images/fore/atpx4.jpg"/>
    <font class="font_txs2">普天防雷检测</font>
    </a>

    </div>
    <div class="tabCon">
    <a class="one_tx2 radius" style="width:23%;margin-left:1%"  data-title="行政许可" data-href="http://223.111.68.8:8008/waoswh1/main1.aspx" onclick="Hui_admin_tab(this)" href="javascript:;" style="margin-left:1%">
    <img alt="" src="${ly }/images/fore/atpx5.jpg"/>
    <font class="font_txs2">行政许可</font>
    </a>
    <a class="one_tx2 radius" style="width:23%" data-title="标准化" href="http://aqbzh.chinasafety.gov.cn/fw/base/login" target="_blank">
    <img alt="" src="${ly }/images/fore/atpx6.jpg"/>
    <font class="font_txs2">标准化</font>
    </a>
    <a class="one_tx2 radius" style="width:23%;" data-title="危化登记"  data-href="http://211.100.28.219/ " onclick="Hui_admin_tab(this)" href="javascript:;">
    <img alt="" src="${ly }/images/fore/atpx7.jpg"/>
    <font class="font_txs2">危化登记</font>
    </a>
    <a class="one_tx2 radius" style="width:23%;margin-right:0" data-title="职业危害申报"  data-href="http://211.100.47.109/zywsmain/index.asp" onclick="Hui_admin_tab(this)" href="javascript:;">
    <img alt="" src="${ly }/images/fore/atpx8.jpg"/>
    <font class="font_txs2">职业危害申报</font>
    </a>
    </div>
    </div>
    </div>
    <%--      <div class="div_tx mt-15">--%>
    <%--        <div class="title_xw pb-5 pos-r" style="border-bottom:1px solid #F6F6F6">排查治理统计分析--%>
    <%--          <!-- 判断并提示至少选择3个月 -->--%>
    <%--          <div class="search_rq pos-a">--%>
    <%--            <font>查询时间段：</font>--%>
    <%--            <input type="text" class="Wdate input-text" id="sT" style="width:150px;" onfocus="WdatePicker({dateFmt:'yyyy-MM',maxDate:'#F{$dp.$D(\'eT\',{M:-3}) || \'%y-{%M-3}\'}'})"/>--%>
    <%--            <font>-</font>--%>
    <%--            <input type="text" class="Wdate input-text" id="eT" style="width:150px;" onfocus="WdatePicker({dateFmt:'yyyy-MM',minDate:'#F{$dp.$D(\'sT\',{M:+3})}', maxDate : '%y-%M'})"/>--%>
    <%--            <button class="btn btn-success" type="button" onclick="reload()">--%>
    <%--              <i class="Hui-iconfont">&#xe665;</i> 查询--%>
    <%--            </button>--%>
    <%--          </div>--%>
    <%--        </div>--%>
    <%--        <div class="div_zhe">--%>
    <%--          <div class="change_zhe">--%>
    <%--            <div class="btn-group">--%>
    <%--              <span class="btn btnyh btnyhxz radius yhlx">隐患类型</span>--%>
    <%--              <span class="btn btnyh radius yhdj">隐患等级</span>--%>
    <%--              <span class="btn btnyh radius yhzg">整改率</span>--%>
    <%--              <span class="btn btnyh radius yhly">隐患来源</span>--%>
    <%--            </div>--%>
    <%--          </div>--%>
    <%--          <div id="container3" style="width:100%;height:450px;display: inline-block;margin-top:25px;"></div>--%>
    <%--        </div>--%>
    <%--        <div id="upload-zl" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">--%>
    <%--        <div class="modal-dialog">--%>
    <%--        <div class="modal-content radius">--%>
    <%--            <div class="modal-header">--%>
    <%--                <h3 class="modal-title">风险等级</h3>--%>
    <%--                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>--%>
    <%--            </div>--%>
    <%--            <div class="modal-body">--%>
    <%--              <div class="row cl">--%>
    <%--                <div class="formControls col-xs-8 col-sm-9" style="width:70%;">--%>
    <%--                  <select id="b"  name="isTab" class="select" style="height:31px;">--%>
    <%--                    <c:forEach items="${lib }" varStatus="index" var="l">--%>
    <%--					<option <c:if test="${c.dlevel == l.name}"> selected</c:if>>${l.name }</option>--%>
    <%--			 		 </c:forEach>--%>
    <%--                  </select>--%>
    <%--                </div> --%>
    <%--              </div>--%>
    <%--            </div>--%>
    <%--            <div class="modal-footer">--%>
    <%--                <button class="btn btn-primary" onclick="change($('#b').val())" >确定</button>--%>
    <%--                <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>--%>
    <%--            </div>--%>
    <%--        </div>--%>
    <%--    </div>--%>
    <%--</div>--%>
    <%--      </div>--%>
    <%--    --%>
    </div>
    </div>
    <div style="position: fixed;right:10px;top:0px;width:15%">
    <div class="panel panel-default" style="width:100%;margin-right:10px;float:right;margin-top:15px;">
    <div class="panel-body" style="padding:8px">
    <a class="title_xw div_xinw" data-href="${ly }/fore/notice-list"  data-title="监管动态" onclick="Hui_admin_tab(this)" href="javascript:;">
    <font>监管动态</font>
    <i class="Hui-iconfont iconxw">&#xe6d5;</i>
    </a>
    </div>
    </div>

    <div class="panel panel-default" style="width:100%;margin-right:10px;float:right;margin-top:15px;">
    <div class="panel-body" style="padding:8px">
    <a class="title_xw div_xinw" data-href="${ly }/fore/notice-list1"  data-title="法律法规标准规范" onclick="Hui_admin_tab(this)" href="javascript:;">
    <font>法律法规标准规范</font>
    <i class="Hui-iconfont iconxw">&#xe6d5;</i>
    </a>
    </div>
    </div>
    <div class="panel panel-default" style="width:100%;margin-right:10px;float:right;margin-top:15px;">
    <div class="panel-body" style="padding:8px">
    <a class="title_xw div_xinw" data-href="${ly }/fore/notice-list2"  data-title="安全员之家" onclick="Hui_admin_tab(this)" href="javascript:;">
    <font>安全员之家</font>
    <i class="Hui-iconfont iconxw">&#xe6d5;</i>
    </a>
    </div>
    </div>
    <div class="panel panel-default" style="width:100%;margin-right:10px;float:right;margin-top:15px;">
    <div class="panel-body listd" style="padding:8px">
    <div class="title_xw div_xinw title_danger">
    <font>风险因素检索</font>
    <i class="Hui-iconfont iconxw">&#xe6d5;</i>
    </div>
    <div class="list_gg">
    <div class="one_notice">
    <div class="text-overflow">
    <i class="Hui-iconfont">&nbsp;</i>
    <a data-href="${ly }/html/danger.html" data-title="重大事故隐患判定标准" onclick="Hui_admin_tab(this)" href="javascript:;">重大事故隐患判定标准</a>
    </div>
    </div>
    <div class="one_notice">
    <div class="text-overflow">
    <i class="Hui-iconfont">&nbsp;</i>
    <a data-href="${ly }/fore/notice-show?id=543" data-title="重大火灾隐患判定方法" onclick="Hui_admin_tab(this)" href="javascript:;">重大火灾隐患判定方法</a>
    </div>
    </div>
    <div class="one_notice">
    <div class="text-overflow">
    <i class="Hui-iconfont">&nbsp;</i>
    <a data-href="${ly }/fore/danger/wei-list" data-title="重大危险源辨识目录" onclick="Hui_admin_tab(this)" href="javascript:;">重大危险源辨识目录</a>
    </div>
    </div>
    <div class="one_notice">
    <div class="text-overflow">
    <i class="Hui-iconfont">&nbsp;</i>
    <a data-href="${ly }/fore/danger/danger-list" data-title="危化品目录" onclick="Hui_admin_tab(this)" href="javascript:;">危化品目录</a>
    </div>
    </div>
    <div class="one_notice">
    <div class="text-overflow">
    <i class="Hui-iconfont">&nbsp;</i>
    <a data-href="${ly }/fore/danger/bao-list" data-title="易制爆危险化学品" onclick="Hui_admin_tab(this)" href="javascript:;">易制爆危险化学品</a>
    </div>
    </div>
    <div class="one_notice">
    <div class="text-overflow">
    <i class="Hui-iconfont">&nbsp;</i>
    <a data-href="${ly }/fore/danger/du-list" data-title="易制毒化学品" onclick="Hui_admin_tab(this)" href="javascript:;">易制毒化学品</a>
    </div>
    </div>
    <div class="one_notice">
    <div class="text-overflow">
    <i class="Hui-iconfont">&nbsp;</i>
    <a data-href="${ly }/fore/danger/zhi-list" data-title="职业病有害因素" onclick="Hui_admin_tab(this)" href="javascript:;">职业病有害因素</a>
    </div>
    </div>
    <%-- <div class="one_notice">
      <div class="text-overflow">
        <i class="Hui-iconfont">&nbsp;</i>
        <a data-href="${ly }/company/trouble/trouble-information" data-title="隐患排查治理标准信息库" onclick="Hui_admin_tab(this)" href="javascript:;">隐患排查治理标准</a>
      </div>
    </div>
    <div class="one_notice">
      <div class="text-overflow">
        <i class="Hui-iconfont">&nbsp;</i>
        <a data-href="${ly }/company/trouble/trouble-information-list" data-title="较大危险因素辨识与防范指导手册" onclick="Hui_admin_tab(this)" href="javascript:;">较大危险因素辨识与防范指导手册</a>
      </div>
    </div> --%>
    <div class="one_notice">
    <div class="text-overflow">
    <i class="Hui-iconfont">&nbsp;</i>
    <a data-href="${ly }/fore/safety-system/risk-information-list2" data-title="常用危险化学品特性表" onclick="Hui_admin_tab(this)" href="javascript:;">常用危险化学品特性表</a>
    </div>
    </div>
    <div class="one_notice">
    <div class="text-overflow">
    <i class="Hui-iconfont">&nbsp;</i>
    <a data-href="${ly }/html/picture-danger.html" data-title="危化品生产分级指南" onclick="Hui_admin_tab(this)" href="javascript:;">危化品生产分级指南</a>
    </div>
    </div>
    </div>
    </div>
    </div>
    <div class="panel panel-default" style="width:100%;margin-right:10px;float:right;margin-top:15px;">
    <%--<div class="panel-body" style="padding:8px">--%>
    <%--<a class="title_xw div_xinw" data-href="http://www.hupinwang.com/info" data-title="护品网" onclick="Hui_admin_tab(this)" href="javascript:;">--%>
    <%--<font>获取更多信息</font>--%>
    <%--<i class="Hui-iconfont iconxw">&#xe6d5;</i>--%>
    <%--</a>--%>
    <%--</div>--%>
    </div>
    </div>
    </body>
    <script type="text/javascript" src="${ly }/js/c-chart.js"></script>
    <script type="text/javascript">
    function change(dlevel){
    $.post(getRootPath() + "/company/changedlvl",{
    dlevel : dlevel
    }, function(result){
    $("#upload-zl").modal("hide");
    $("#level-font").text(dlevel);
    })
    }
    </script>

    <script type="text/javascript" src="${ly }/js/plugins/websocket/swfobject.js"></script>
    <script type="text/javascript" src="${ly }/js/plugins/websocket/web_socket.js"></script>
    <script type="text/javascript">
    function getCountData(){
    $.ajax({
    url : "${ly}/village/getDangerCount",
    type : 'post',
    success : function(data){
    if(undefined != data.zdTotal){
    if(data.zdTotal > 0){
    if(!$("#zd_font").hasClass("blink")){
    $("#zd_font").addClass("blink");
    }
    $("#zd_font").html(data.zdTotal);
    }else{
    $("#zd_font").removeClass("blink");
    $("#zd_font").html(0);
    }
    }
    if(undefined != data.gwTotal){
    if(data.gwTotal > 0){
    if(!$("#gw_font").hasClass("blink")){
    $("#gw_font").addClass("blink");
    }
    $("#gw_font").html(data.gwTotal);
    }else{
    $("#gw_font").removeClass("blink");
    $("#gw_font").html(0);
    }
    }
    }
    });
    }

    getCountData();

    var intervalIndex = setInterval(function(){
    getCountData();
    },1000*60);

    var websocket = null;
    var userno="${loginUserId}";
    //判断当前浏览器是否支持WebSocket
    WEB_SOCKET_SWF_LOCATION = "${ly }/js/plugins/websocket/WebSocketMain.swf";
    if(window.WebSocket){
    websocket = new WebSocket("wss://"+location.hostname+":"+location.port+"/village/websocket/danger/"+userno);
    }
    else {
    //alert("当前版本不支持");
    }


    //连接发生错误的回调方法
    websocket.onerror = function () {
    //alert("error");
    //setMessageInnerHTML("WebSocket连接发生错误");
    };


    //连接成功建立的回调方法
    websocket.onopen = function () {
    clearInterval(intervalIndex);
    }


    //接收到消息的回调方法
    websocket.onmessage = function (event) {
    //tips_pop();
    //setMessageInnerHTML(event.data);
    //alert(event.data);
    //tips_pop(event.data);
    if("" != $.trim(event.data)){

    }else{
    getCountData();
    }

    }


    //连接关闭的回调方法
    websocket.onclose = function () {
    //setMessageInnerHTML("WebSocket连接关闭");
    //alert("close");
    }


    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
    //closeWebSocket();
    }
    //关闭WebSocket连接
    function closeWebSocket() {
    websocket.close();
    }

    </script>
    </html>