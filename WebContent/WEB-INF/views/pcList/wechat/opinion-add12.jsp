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
            <a class="current" href="JavaScript:void(0)">责令限期整改意见书</a>
            <a href="${ly }/api/village/xlb/A024?checkId=${check.id}&flag=2">整改复查意见书</a>
          </div>
        </div>
        <div id="container" style="width:150mm;margin:auto;padding:50px 0 0px 0;">
        <p class="dant1  mt-20" style="text-align:center;border-bottom:1px solid #000;margin-bottom:3px;" align="center">
            <b><span style="font-family:华文中宋;font-weight:bold;font-size:18pt;"><span>安全生产行政执法文书</span></span></b>
        </p>
        <p class="dant1" style="text-align:center;border-top:1px solid #000;margin-bottom:3px;" align="center">
            <span></span><b><span style="font-family:华文中宋;font-weight:bold;font-size:18pt;"><span>责令限期整改指令书</span></span></b><b><span style="font-family:华文中宋;font-weight:bold;font-size:18.0000pt;"></span></b>
        </p>
        <p class="MsoNormal" style="text-align:center;margin-top:10px;" align="center">
            <span style="font-size:14.7pt;"><span contenteditable="true">（&nbsp;&nbsp;&nbsp;）</span><span>安监责改</span><span contenteditable="true">〔&nbsp;&nbsp;&nbsp;〕</span> <span>号</span></span>
        </p>
        <p class="MsoNormal" style="margin-top:30px;">
            <u><span style="float:left;text-decoration:underline;" contenteditable="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${company.name }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u><span style=""><span>：</span></span><span style=""></span>
        </p>
        <p class="MsoNormal" style="text-indent:27.7500pt;">
            <span style=""><span>经查，你单位存在下列问题：</span></span><span style=""></span>
        </p>
        <div id="div_dangerm">
        <c:forEach items="${itemL }" var="be" varStatus="index">
            <c:if test="${lanlin:constains(rectification.item2,be.id,',') }">
              <c:choose>
              <c:when test="${xx == ''}">
                <c:set value="${index.index + 1}" var="xx"/>
              </c:when>
              <c:otherwise>
                <c:set value="${xx },${index.index + 1}" var="xx"/>
              </c:otherwise>
              </c:choose>
            </c:if>
            <p class="MsoNormal" style="text-indent:21.0000pt;">
                <span>${index.index + 1}．</span><u><span style="text-decoration:underline;text-indent: 0;" contenteditable="true">&nbsp;${be.memo }</span></u>
            </p>
        </c:forEach>
        </div>
        <p class="MsoNormal" style="text-indent:27.7500pt;">
            <span style="vertical-align: bottom;"><span>其中事故隐患</span></span>
            <u><span style="border-bottom:1px solid #333;width:30%;display:inline-block;vertical-align: text-bottom;text-indent: 0;" contenteditable="true">&nbsp;&nbsp;</span></u>
            <span style="vertical-align: bottom;"><span>项属于重大隐患。</span></span>
        </p>
        <p class="MsoNormal" style="text-indent:23.0000pt;text-align:left;line-height:28px;">
            <span style=""><span>现责令</span></span><span style=""><span>你单位</span></span><span style=""><span>对上述第</span></span>
            <u><span style="text-decoration:underline;text-indent: 0;" contenteditable="true">&nbsp;${empty xx ? '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' : xx}</span></u>
            <span style=""><span>项问题</span></span>
            <span style=""><span>于</span></span>
            <u><span style="text-decoration:underline;text-indent: 0;" contenteditable="true">&nbsp;<fmt:formatDate value="${!empty rectification ? rectification.deadline : timenow }" pattern="yyyy"/></span></u>
            <span style=""><span>年</span></span>
            <u><span style="text-decoration:underline;text-indent: 0;" contenteditable="true">&nbsp;<fmt:formatDate value="${!empty rectification ? rectification.deadline : timenow }" pattern="MM"/></span></u>
            <span style=""><span>月</span></span>
            <u><span style="text-decoration:underline;text-indent: 0;" contenteditable="true">&nbsp;<fmt:formatDate value="${!empty rectification ? rectification.deadline : timenow }" pattern="dd"/></span></u>
            <span style=""><span>日前整改完毕，达到有关法律法规规章和标准规定的要求。由此造成事故的，依法追究有关人员的责任。</span></span>
            <span style=""></span>
        </p>
        <p class="MsoNormal" style="text-indent:21.0000pt;line-height:28px;">
            <span style=""><span>整改期间，你单位应当采取措施，确保安全生产。对安全生产违法行为，将依法予以行政处罚。</span></span><span style=""></span>
        </p>
        <p class="MsoNormal" style="text-indent:21.0000pt;line-height:28px;">
            <span style=""><span>如果不服本指令，可以依法在</span>60日内向 </span>
            <u><span style="text-decoration:underline;text-indent: 0;" contenteditable="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u>
            <span style="">&nbsp;<span>人民政府或者</span></span>
            <u><span style="text-decoration:underline;text-indent: 0;word-wrap:break-word;word-break:break-all;white-space:normal;overflow:hidden;" contenteditable="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u>
            <span style=""><span>申请行政复议，或者在</span>6个月内依法向</span>
            <u><span style="text-decoration:underline;text-indent: 0;" contenteditable="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u>
            <span style=""><span>人民法院提起行政诉讼，但本指令不停止执行，法律另有规定的除外。</span></span>
        </p>
        
        <p class="MsoNormal" style="margin-top:15px;">
            <span style=""><span>安全生产监管行政执法人员（签名）：</span> </span>
            <u><span style="text-decoration:underline;" contenteditable="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u>
            <span style=""><span>证号：</span></span>
            <u><span style="text-decoration:underline;" contenteditable="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u><span style="">&nbsp;</span><span style=""></span>
        </p>
        <p class="MsoNormal" >
            <span style="visibility:hidden"><span>安全生产监管行政执法人员（签名）：</span> </span>
            <u><span style="text-decoration:underline;text-indent: 0;" contenteditable="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u>
            <span style=""><span>证号：</span></span>
            <u><span style="text-decoration:underline;text-indent: 0;" contenteditable="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u>
            <span style="">&nbsp;</span>
            <span style=""></span>
        </p>
        <p class="MsoNormal" style="margin-top:15px;">
            <span style=""><span>被检查单位负责人（签名）：</span></span>
            <u><span style="text-decoration:underline;" contenteditable="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u>
            <u><span style="text-decoration:underline;"></span></u>
        </p>
        
        <p class="MsoNormal" style="text-align:right;margin-top:50px">
            <span style=""><span>安全生产监督管理部门（印章）</span></span><span style=""></span>
        </p>
        <p class="MsoNormal" style="text-align:right">
            <span style=""><span contenteditable="true">&nbsp;<fmt:formatDate value="${timenow }" pattern="yyyy"/></span> 年&nbsp;&nbsp;<span contenteditable="true">&nbsp;<fmt:formatDate value="${timenow }" pattern="MM"/></span> 月&nbsp;&nbsp;<span contenteditable="true">&nbsp;<fmt:formatDate value="${timenow }" pattern="dd"/></span>日</span><span style=""></span>
        </p>
        <p class="MsoNormal" style="border-top:1px solid #000;">
            <span style=""><span>本文书一式两份：一份由安全生产监督管理部门备案，一份交被检查单位。</span> </span><span style=""></span>
        </p>
        <p class="MsoNormal" style="text-align:right;" align="right">
            <span style=""><span></span><span></span></span><span style=""></span>
        </p>
        <p class="MsoNormal" style="text-align:right">
            <span style="">共<span contenteditable="true">&nbsp;&nbsp;</span>页&nbsp;&nbsp; 第<span contenteditable="true">&nbsp;&nbsp;</span>页
        </p>
        <br />
        </div>
        <!-- 添加隐患模块 -->
        <div id="div_model">
            <p class="MsoNormal" style="text-indent:21.0000pt;" contenteditable="true">
                <span class="num_dan" style="">1</span><span style="">．</span><u><span style="text-decoration:underline;text-indent: 0;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u><u><span style="text-decoration:underline;"></span></u>
                <i class="Hui-iconfont mr-10 dustbin" style="cursor:pointer;font-size:20px" onclick="removeDan(this)">&#xe609;</i>
            </p>
        </div>
     <!-- 底部操作 -->
     <div class="allh1"></div>
     <div class="sjfoot">
      <a href="javascript:void(0)" class="btn_tjyh" onClick="addDan()">添加隐患</a>
      <a href="javascript:void(0)" class="btn_yhbc" onClick="save_()">保存</a>
    </div>
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
      flag: 1,
      flag2 : 2
    }, function(reuslt) {
      layer.alert("保存成功！");
      location.reload();
    })
}

function pr_(e) {
  layer.alert("打印之前，请先保存！");
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
