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
.div-print{width:60%;margin:auto;padding:30px 0 0 0;}
</style>
<style type="text/css" media="print">
.div-print{width:90%;margin:auto;padding:30px 0 0 0;}
</style>
<script type="text/javascript">
$(function(){
  $("select").change(function() {
    load();
  })
  
  load();
})

function load() {
  if($(".sel_area2").val() !=  null && $(".sel_area2").val() != '') {
  $('.titleName').html($(".sel_area1").val()+'职业病危害告知牌')
    $(".div-set").load($(".sel_area2").val() + " .div-print");
  }else {
    $(".div-set").html("<p>没有涉及的物理因素</p>")
  }
}
function pr_() {
  $("#div_container").jqprint();
}
</script>
</head>
<body>
  <nav class="breadcrumb text-c">	
 	<a class="btn-page"  data-href="/company/safety-system/risk-information-list2"  data-title="职业危害（化学因素）告知牌" onclick="Hui_admin_tab(this)" href="javascript:;">职业危害（化学因素）告知牌</a>
    <a class="btn-page pagexz" href="javascript:;">职业危害（物理因素）告知牌</a>
    <%--<a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="parent.close_tab(function(){})">返回</a>--%>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;" href="javascript:history.back(-1)" title="返回">返回</a>
  </nav>
  <div class="page-container">
    <div class="text-l">
      <div class="dis-ib">

        <span>车间：</span>
        <select name="" class="sel_area sel_area1">
          <c:forEach items="${list }" var="bes" varStatus="index">
            <option value="${bes.name}">${bes.name }</option>
          </c:forEach>
        </select>

        <span>物理因素：</span>
        <select name="" class="sel_area sel_area2">
          <c:forEach items="${wlysL }" var="be" varStatus="index">
          <option value="${be.url}">${be.name }</option>
          </c:forEach>
        </select>
      </div> 
      <button onClick="pr_()" class="btn btn-primary radius" type="button">
        <i class="Hui-iconfont">&#xe652;</i>打印
      </button>
    </div>
    <div class="mt-20">
  <h3 class="titleName" style="border: 1px solid;width: 59.8%;margin: 0 auto -30px;border-bottom: none;"></h3>
      <div class="div-set" id="div_container" style="border:0">
        <!-- <div class="top-set">
          <font>职业危害告知牌（物理因素）</font>
          <a class="btn_rbj"  onClick="show_dialog('打印', '/html/harm/harm1.html')" href="javascript:;" title="打印">打印</a>
        </div> -->
      </div>
      
      <!--<table class="table table-border table-bordered table-bg table-hover table-sort tab-ndan">
        <thead>
          <tr class="text-c">
            <th style="width:40px">序号</th>
            <th>职业危害告知牌（物理因素）</th>
            <th style="width:150px">操作</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${wlysL }" var="be" varStatus="index">
          <tr>
            <td class="text-c">${index.index + 1}</td>
            <td>${be.name }危害告知牌</td>
            <td class="text-c">
              <a style="text-decoration:none" onClick="show_dialog('查看告知牌', '${be.url}')" href="javascript:;" title="查看告知牌">查看告知牌</a>
            </td>
          </tr>
          </c:forEach>
        </tbody>
      </table>-->
    </div>
  </div>
</body>
</html>