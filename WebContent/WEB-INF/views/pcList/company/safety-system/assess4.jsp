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
<style type="text/css">
body .table{float:left;margin-right:2%}
</style>
<script type="text/javascript">
$(function() {
});


</script> 
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>风险分级管控</span> 
    <span class="c-gray en">&gt;</span> <span>风险评估</span> 
    <span class="c-gray en">&gt;</span> <span>安全风险空间分布图</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="parent.close_tab(function(){})">返回</a>
  </nav>
  <div class="page-container">
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg">
        <thead>
          <tr>
            <th class="text-c">重大风险点</th>
          </tr>
        </thead>
        <c:forEach items="${list }" var="be">
        <c:if test="${be.level eq '红色'}">
        <tr>
          <td class="text-c"><font class="col-a"><c:if test="${not empty be.level1}">${be.level1 } > ${be.level2 } > </c:if>${be.level3 }</font></td>
        </tr>
        </c:if>
        </c:forEach>
      </table>
      <table class="table table-border table-bordered table-bg mt-20">
        <thead>
          <tr>
            <th class="text-c">较大风险点</th>
          </tr>
        </thead>
        <c:forEach items="${list }" var="be">
        <c:if test="${be.level eq '橙色'}">
        <tr>
          <td class="text-c"><font class="col-b"><c:if test="${not empty be.level1}">${be.level1 } > ${be.level2 } > </c:if>${be.level3 }</font></td>
        </tr>
        </c:if>
        </c:forEach>
      </table>
    </div>
  </div>
</body>
</html>