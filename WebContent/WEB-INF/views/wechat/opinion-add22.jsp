<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="format-detection" content="telephone=no" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/inc/back-header.inc"%>
<link type="text/css" href="${ly }/styles/all.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${ly}/styles/print.css" />
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style type="text/css">
.tabBar{margin-top:5px;}
.tabBar a{width:32%;overflow: hidden;padding:0;font-size:0.9em;}
.row{position: fixed;top:45px;left:0;width:100%;background:#fff;padding-bottom:5px;}
</style>
</head>
<body>
<div class="container">
  <div class="top_all">
    <div class="top_main">
      <a onclick="window.androidandiosJS.finishButton()" class="back_top"><img alt="" src="${ly }/images/weixin/fanhuib.png"/></a>
      <font class="top_title">文书检查</font>
    </div>
  </div>
  <div class="allh1"></div>
  <div class="allh1"></div>
  <!-- 原文件信息 -->
  <div class="row cl mb-20">
          <div class="tabBar clearfix">
            <a href="${ly }/api/village/xlb/A024?checkId=${check.id}&flag=8">现场检查记录</a>
            <a href="${ly }/api/village/xlb/A024?checkId=${check.id}&flag=1">责令限期整改意见书</a>
            <a class="current" href="JavaScript:void(0)">整改复查意见书</a>
          </div>
        </div>
        <div id="container" style="width:150mm;margin:auto;padding:50px 0 0px 0;">
        <p class="dant1  mt-20" style="text-align:center;border-bottom:1px solid #000;margin-bottom:3px;" align="center">
            <b><span style="font-family:华文中宋;font-weight:bold;font-size:18pt;"><span>安全生产行政执法文书</span></span></b>
        </p>
        <p class="dant1" style="text-align:center;border-top:1px solid #000;margin-bottom:3px;" align="center">
            <span></span><b><span style="font-family:华文中宋;font-weight:bold;font-size:18pt;"><span>整改复查意见书</span></span></b><b><span style="font-family:华文中宋;font-weight:bold;font-size:18.0000pt;"></span></b>
        </p>
        <p class="MsoNormal" style="text-align:center;margin-top:10px;" align="center">
            <span style="font-size:14.7pt;"><span contenteditable="true">（&nbsp;&nbsp;&nbsp;）</span><span>安监复查</span><span contenteditable="true">〔&nbsp;&nbsp;&nbsp;〕</span> <span>号</span></span>
        </p>
        <p class="MsoNormal" style="margin-top:30px;">
            <u><span style="float:left;text-decoration:underline;" contenteditable="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${company.name }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u><span style=""><span>：</span></span><span style=""></span>
        </p>
        <p class="MsoNormal" style="text-indent:23.0000pt;text-align:left;">
            <span style="vertical-align: bottom;"><span>本机关于</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;;width:13%;display:inline-block;text-indent: 0;" contenteditable="true">&nbsp;<fmt:formatDate value="${timenow }" pattern="yyyy"/></span></u>
            <span style="vertical-align: bottom;"><span>年</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;;width:7%;display:inline-block;text-indent: 0;" contenteditable="true">&nbsp;<fmt:formatDate value="${timenow }" pattern="MM"/></span></u>
            <span style="vertical-align: bottom;"><span>月</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;;width:7%;display:inline-block;text-indent: 0;" contenteditable="true">&nbsp;<fmt:formatDate value="${timenow }" pattern="dd"/></span></u>
            <span style="vertical-align: bottom;"><span>日作出了</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;;width:30%;display:inline-block;text-indent: 0;" contenteditable="true">&nbsp;</span></u>
            <span style=""></span>
        </p>
        <p class="MsoNormal" style="margin-bottom:0px;">
            <span style=""><span>的决定[(&nbsp;)安监</span> 
            <u><span style="text-decoration:underline;" contenteditable="true">&nbsp;&nbsp;</span></u>
            <span>(&nbsp;)(&nbsp;)</span><span>号]，经对你单位整改情况进行复查，提出如下意见：</span></span>
        </p>
        <div id="div_dangerm">
            <c:forEach items="${itemL }" var="be" varStatus="index">
                <p class="MsoNormal" style="text-indent:21.0000pt;">
                    <span style="font-family:'\4EFF\5B8B_GB2312';">${index.index + 1}．</span><u><span style="font-family:'\4EFF\5B8B_GB2312';text-decoration:underline;text-indent: 0;" contenteditable="true">&nbsp;${be.memo }</span></u><u><span style="font-family:'\4EFF\5B8B_GB2312';text-decoration:underline;text-indent: 0;"></span></u> 
                </p>
            </c:forEach>
        </div>
        <p class="MsoNormal" style="margin-top:30px">
            <span style=""><span>被复查单位负责人（签名）：</span></span>
            <u><span style="text-decoration:underline;" contenteditable="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u>
            <u><span style="text-decoration:underline;"></span></u>
        </p>
        <p class="MsoNormal"style="margin-top:15px">
            <span style=""><span>安全生产监管行政执法人员（签名）：</span> </span>
            <u><span style="text-decoration:underline;" contenteditable="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u>
            <span style=""><span>证号：</span></span>
            <u><span style="text-decoration:underline;" contenteditable="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u><span style="">&nbsp;</span><span style=""></span>
        </p>
        <p class="MsoNormal">
            <span style="visibility:hidden"><span>安全生产监管行政执法人员（签名）：</span> </span>
            <u><span style="text-decoration:underline;text-indent: 0;" contenteditable="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u>
            <span style=""><span>证号：</span></span>
            <u><span style="text-decoration:underline;text-indent: 0;" contenteditable="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u>
            <span style="">&nbsp;</span>
            <span style=""></span>
        </p>
        <p class="MsoNormal" style="text-align:right;margin-top:40px">
            <span style=""><span>安全生产监督管理部门（印章）</span></span><span style=""></span>
        </p>
        <p class="MsoNormal" style="text-align:right">
            <span style=""><span contenteditable="true">&nbsp;<fmt:formatDate value="${timenow }" pattern="yyyy"/></span> 年&nbsp;&nbsp;<span contenteditable="true">&nbsp;<fmt:formatDate value="${timenow }" pattern="MM"/></span> 月&nbsp;&nbsp;<span contenteditable="true">&nbsp;<fmt:formatDate value="${timenow }" pattern="dd"/></span>日</span><span style=""></span>
        </p>
        <p class="MsoNormal" style="border-top:1px solid #000;">
            <span style=""><span>本文书一式两份，一份由检查单位备案，一份交被复查单位</span> </span><span style=""></span>
        </p>
        <p class="MsoNormal" style="text-align:right;" align="right">
        </div>
  <!-- 添加-->
        <div id="div_model">
            <p class="MsoNormal" style="text-indent:21.0000pt;">
                <span class="num_dan" style="">1</span><span style="">．</span><u><span style="text-decoration:underline;text-indent: 0;" contenteditable="true" class="yh_span">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u><u><span style="text-decoration:underline;text-indent: 0;"></span></u>
                <i class="Hui-iconfont mr-10 dustbin" style="cursor:pointer;font-size:20px" onclick="removeDan(this)">&#xe609;</i>
            </p>
        </div>
     <!-- 底部操作 -->
     <div class="allh1"></div>
     <div class="sjfoot">
      <a href="javascript:void(0)" class="btn_tjyh" onClick="addDan()">添加意见</a>
      <a href="javascript:void(0)" class="btn_yhbc" onClick="save_()">保存</a>
    </div>

<script type="text/javascript">
  var checkId = ${checkId};
  var userId = ${company.userId };
  function addDan() {
    var html = $("#div_model").html();
    $("#div_dangerm").append(html);
    init_index();
  }

  function init_index() {
    $("#div_dangerm .num_dan").each(function(i,e){
      $(this).text(i+1);
    });
  }

  function removeDan(e) {
      $(e).parent().remove();
      init_index();
  }
  function pr_(e) {
      layer.alert("打印之前，请先保存！");
  }
  
function save_() {
  $(".dustbin").hide();
  var access_token = "";
  if(window.androidandiosJS){
    access_token = window.androidandiosJS.getAppToken();
  }else{
    layer.alert("获取AppToken失败");
    return;
  }
  $.post(getRootPath() + "/api/village/xlb/danger/opinion-save", {
      access_token : access_token,
        userId: userId,
        checkId : checkId,
        copy: $("#container").html(),
        flag: 2,
        flag2 : 2
    }, function(reuslt) {
      layer.alert("保存成功！");
        location.reload();
    })
}
$(function () {
  if($("#div_dangerm .MsoNormal").length == 0) {
    addDan();
    addDan();
    addDan();
  }
})
</script>
</body>
</html>
