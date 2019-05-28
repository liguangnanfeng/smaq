<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<%@ include file="/WEB-INF/inc/back-header.inc"%>
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<link rel="stylesheet" type="text/css" href="${ly}/styles/print-tab.css"/>
<link rel="stylesheet" type="text/css" href="${ly}/styles/print-tab.css" media="print" />
<link rel="stylesheet" type="text/css" media="print"  href="${ly}/js/H-ui.admin_v3.0/static/h-ui/css/H-ui.css" />
<script src="/js/jquery.jqprint-0.3.js"></script>
<style type="text/css">
body .dis-ib{margin-right:15px;}
.div-print{width:60%;margin:auto;padding:30px 0 0 0;}
.tab-harm p{color:#000}
</style>
<style type="text/css" media="print">
.div-print{width:90%;margin:auto;padding:30px 0 0 0;}
.tab-harm p{color:#000}
</style>
<script type="text/javascript">
$(function(){
  $("select").change(function() {
    load();
  })
  
  load();
})

function load() {
  if($("select").val() !=  null && $("select").val() != '') {
    $(".mt-20").load("/company/safety-system/risk-information-detail2?id=" + $("select").val() + " .div-print");
  } else {
    $(".mt-20").html("<p>没有涉及的化学因素</p>")
  }
}

function pr_() {
  $("#div_container").jqprint();
}
</script>
</head>
<body>
  <nav class="breadcrumb text-c">
    <a class="btn-page pagexz" href="javascript:;">职业危害（化学因素）告知牌</a>
    <a class="btn-page"  data-href="${ly }/company/safety-system/harm-list"  data-title=" 职业危害（物理因素）告知牌" onclick="Hui_admin_tab(this)" href="javascript:;">职业危害（物理因素）告知牌</a>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="parent.close_tab(function(){})">返回</a>
  </nav>
  <div class="page-container">
    <div class="text-l">
      <div class="dis-ib">
        <span>物质名称：</span>
        <select name="" class="sel_area">
         <c:forEach items="${list }" var="be" varStatus="index">
          <option value="${be.id}">${be.name }</option>
         </c:forEach>
        </select>
      </div> 
      <button onClick="pr_()" class="btn btn-primary radius" type="button">
        <i class="Hui-iconfont">&#xe652;</i>打印
      </button>
    </div>
    <div class="mt-20" id="div_container">

    </div>
  </div>
</body>
</html>