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
            <a class="current" href="javascript:void(0)">现场检查记录</a>
            <c:if test="${empty check}">
            <a href="javascript:void(0)" onclick="layer.alert('请先保存现场检查记录')">责令限期整改意见书</a>
            <a href="javascript:void(0)" onclick="layer.alert('请先保存现场检查记录')">整改复查意见书</a>
            </c:if>
            <c:if test="${!empty check}">
            <a href="${ly }/api/village/xlb/A024?checkId=${check.id}&flag=1">责令限期整改意见书</a>
            <a href="${ly }/api/village/xlb/A024?checkId=${check.id}&flag=2">整改复查意见书</a>
            </c:if>
          </div>
        </div>
        <div id="container" style="width:150mm;margin:auto;padding:50px 0 0px 0;">
        <p class="dant1  mt-20" style="text-align:center;border-bottom:1px solid #000;margin-bottom:3px;" align="center">
            <b><span style="font-family:华文中宋;font-weight:bold;font-size:18pt;"><span>安全生产行政执法文书</span></span></b>
        </p>
        <p class="dant1" style="text-align:center;border-top:1px solid #000;margin-bottom:3px;" align="center">
            <span></span><b><span style="font-family:华文中宋;font-weight:bold;font-size:18pt;"><span>现场检查记录</span></span></b><b><span style="font-family:华文中宋;font-weight:bold;font-size:18.0000pt;"></span></b>
        </p>
        <p class="MsoNormal" style="text-align:center;margin-top:10px;" align="center">
            <span style="font-size:14.7pt;"><span contenteditable="true">（&nbsp;&nbsp;&nbsp;）</span><span>安监检记</span><span contenteditable="true">〔&nbsp;&nbsp;&nbsp;〕</span> <span>号</span></span>
        </p>
        <p class="MsoNormal" style="text-align:left;margin-top:30px">
            <span style="vertical-align: bottom;"><span>被检查单位</span></span>
            <u><span style="text-align:center;vertical-align: text-bottom;border-bottom:1px solid #333; width:81%;display:inline-block;" contenteditable="true">&nbsp;${company.name }</span></u>
        </p>
        <p class="MsoNormal" style="text-align:left;">
            <span style="vertical-align: bottom;"><span>地&emsp;&emsp;&emsp;址</span></span>
            <u><span style="text-align:center;vertical-align: text-bottom;border-bottom:1px solid #333; width:81%;display:inline-block;" contenteditable="true">&nbsp;${company.address }</span></u>
        </p>
        <p class="MsoNormal" style="text-align:left;">
            <span style="vertical-align: bottom;"><span>法定代表人（负责人）</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom; width:15%;display:inline-block;" contenteditable="true">&nbsp;${company.legal }</span></u>
            <span style="vertical-align: bottom;"><span>职务</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom; width:15%;display:inline-block;" contenteditable="true">&nbsp;</span></u>
            <span style="vertical-align: bottom;"><span>联系电话</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom; width:18%;display:inline-block;" contenteditable="true">&nbsp;${company.legalContact }</span></u>
        </p>
        <p class="MsoNormal" style="text-align:left;">
            <span style=""><span>检查场所</span></span>
            <u><span style="border-bottom:1px solid #333; width:83%;display:inline-block;" contenteditable="true">&nbsp;</span></u>
        </p>
        <p class="MsoNormal" style="text-align:left;">
            <span style="vertical-align: bottom;"><span>检查时间</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom; width:14%;display:inline-block;text-align:center;" contenteditable="true">&nbsp;<fmt:formatDate value="${timenow }" pattern="yyyy"/></span></u>
            <span style="vertical-align: bottom;"><span>年</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom; width:9%;display:inline-block;text-align:center;" contenteditable="true">&nbsp;<fmt:formatDate value="${timenow }" pattern="MM"/></span></u>
            <span style="vertical-align: bottom;"><span>月</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom; width:9%;display:inline-block;text-align:center;" contenteditable="true">&nbsp;<fmt:formatDate value="${timenow }" pattern="dd"/></span></u>
            <span style="vertical-align: bottom;"><span>日</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom; width:9%;display:inline-block;text-align:center;" contenteditable="true">&nbsp;</span></u>
            <span style="vertical-align: bottom;"><span>时</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom; width:9%;display:inline-block;text-align:center;" contenteditable="true">&nbsp;</span></u>
            <span style="vertical-align: bottom;"><span>分至</span></span>
            
        </p>
        <p class="MsoNormal" style="text-align:left;">
          <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom; width:14%;display:inline-block;text-align:center;" contenteditable="true">&nbsp;<fmt:formatDate value="${timenow }" pattern="yyyy"/></span></u>
          <span style="vertical-align: bottom;"><span>年</span></span>
          <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom; width:9%;display:inline-block;text-align:center;" contenteditable="true">&nbsp;<fmt:formatDate value="${timenow }" pattern="MM"/></span></u>
          <span style="vertical-align: bottom;"><span>月</span></span>
          <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom; width:9%;display:inline-block;text-align:center;" contenteditable="true">&nbsp;<fmt:formatDate value="${timenow }" pattern="dd"/></span></u>
          <span style="vertical-align: bottom;"><span>日</span></span>
          <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom; width:9%;display:inline-block;text-align:center;" contenteditable="true">&nbsp;</span></u>
          <span style="vertical-align: bottom;"><span>时</span></span>
          <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom; width:9%;display:inline-block;text-align:center;" contenteditable="true">&nbsp;</span></u>
          <span style="vertical-align: bottom;"><span>分</span></span>
        </p>
        <p class="MsoNormal" style="text-indent:23.0000pt;text-align:left;">
            <span style="vertical-align: bottom;"><span>我们是</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom; width:11%;display:inline-block;text-indent: 0;" contenteditable="true">&nbsp;</span></u>
            <span style="vertical-align: bottom;"><span>安全生产监督管理局行政执法人员</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom; width:11%;display:inline-block;text-indent: 0;" contenteditable="true">&nbsp;</span></u>
            <span style="vertical-align: bottom;"><span>、</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom; width:11%;display:inline-block;text-indent: 0;" contenteditable="true">&nbsp;</span></u>
            <span style="vertical-align: bottom;"><span>，</span></span>
        </p>
        <p class="MsoNormal" style="text-align:left;">
            <span style="vertical-align: bottom;"><span>证件号码为</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom; width:18%;display:inline-block;" contenteditable="true">&nbsp;</span></u>
            <span style="vertical-align: bottom;"><span>、</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom; width:18%;display:inline-block;" contenteditable="true">&nbsp;</span></u>
            <span style="vertical-align: bottom;"><span>，这是我们的证件（出示证件）。</span></span>
        </p>
        <p class="MsoNormal" style="text-align:left;">
          <span style="vertical-align: bottom;"><span>现依法对你单位进行现场检查，请予以配合。</span></span>
        </p>
    
        <p class="MsoNormal" style="text-indent:23.0000pt;text-align:left;">
            <span style=""><span>检查情况：</span></span>
    </p>
    <div id="div_dangerm">

        <c:forEach items="${itemL }" var="be" varStatus="index">
        <p class="MsoNormal" style="text-indent:21.0000pt;" >
            <span class="num_dan" style="">${index.index + 1}</span><span style="">．</span><u><span class="yh_span" style="text-decoration:underline;text-indent: 0;">&nbsp;${be.memo }&nbsp;&nbsp;</span></u>
        </p>
        </c:forEach>
    </div>
    <p class="MsoNormal" style="text-align:left;">
        <span style="vertical-align: bottom;"><span>检查人员（签名）：</span></span>
        <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom; width:20%;display:inline-block;" contenteditable="true">&nbsp;</span></u>
        <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom; width:20%;display:inline-block;margin-left:10px" contenteditable="true">&nbsp;</span></u>
    </p>
    <p class="MsoNormal" style="text-align:left;margin-top:15px">
        <span style="vertical-align: bottom;"><span>被检查单位现场负责人（签名）：</span></span>
        <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:20%;display:inline-block;" contenteditable="true">&nbsp;</span></u>
    </p>
    <p class="MsoNormal" style="text-align:right;margin-top:15px">
        <span style=""><span contenteditable="true">&nbsp;<fmt:formatDate value="${timenow }" pattern="yyyy"/></span> 年&nbsp;&nbsp;<span contenteditable="true">&nbsp;<fmt:formatDate value="${timenow }" pattern="MM"/></span> 月&nbsp;&nbsp;<span contenteditable="true">&nbsp;<fmt:formatDate value="${timenow }" pattern="dd"/></span>日</span><span style=""></span>
    </p>
    <p class="MsoNormal" style="border-top:1px solid #000;text-align:right;">
        <span style="">共<span contenteditable="true">&nbsp;&nbsp;</span>页&nbsp;&nbsp; 第<span contenteditable="true">&nbsp;&nbsp;</span>页</span>
    </p>
    <p class="MsoNormal" style="text-align:right;" align="right">
        <span style=""><span></span><span></span></span><span style=""></span>
    </p>
    <br />
    </div>
    <!-- 添加检查情况 -->
        <div id="div_model">
            <p class="MsoNormal" style="text-indent:21.0000pt;" >
                <span class="num_dan" style="">1</span><span style="">．</span><u><span style="text-decoration:underline;text-indent: 0;" class="yh_span" contenteditable="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u>
                <i class="Hui-iconfont mr-10 dustbin" style="cursor:pointer;font-size:20px" onclick="removeDan(this)">&#xe609;</i>
            </p>
        </div>
     <!-- 底部操作 -->
     <div class="allh1"></div>
     <div class="sjfoot">
      <a href="javascript:void(0)" class="btn_tjyh" onClick="addDan()">添加检查情况</a>
      <a href="javascript:void(0)" class="btn_yhbc" onClick="save_()">保存</a>
    </div>
</div>

</body>
<script type="text/javascript">
var checkId = '${check.id}';
var userId = ${userId};
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
  var list = new Array();
  $("#div_dangerm .MsoNormal").each(function() {
    var x = $(this).find(".yh_span").text().trim();
    if(x != '') {
      list.push(x);
    }
  })
  if(list.length == 0) {
    layer.alert("请输入检查情况");
    return false;
  }
  var itms = "";
  if(checkId == '') {
    itms = list.join("!@#");
  }
  $(".dustbin").hide();
  var access_token = "";
  if(window.androidandiosJS){
    access_token = window.androidandiosJS.getAppToken();
  }else{
    layer.alert("获取AppToken失败");
    return;
  }
  $.post(getRootPath() + "/api/village/xlb/check-document-save8", {
    access_token : access_token,
    checkId : checkId,
    userId : userId,
    flag : 8,
    copy: $("#container").html().trim(),
    itms : itms,
    flag2 : 2
  }, function(reuslt) {
    layer.alert("保存成功！");
    /* layer.confirm('保存成功，是否继续添加责令限期整改意见书?', {
      btn: ['确认', '取消'] //可以无限个按钮
    }, function(index, layero){
      //按钮【按钮一】的回调
      window.location.href = getRootPath() + "/api/village/xlb/A024?flag=1&checkId=" + checkId;
    }, function(index){
      //按钮【按钮二】的回调
      
    }); */
    window.location.href = getRootPath() + "/api/village/xlb/A024?flag=1&checkId=" + checkId;
  })
}

if($("#div_dangerm .MsoNormal").length == 0) {
  addDan();
  addDan();
  addDan();
}
</script>
</html>
