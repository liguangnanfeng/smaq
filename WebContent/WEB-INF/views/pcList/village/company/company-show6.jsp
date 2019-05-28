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
body .dis-ib{margin-right:15px;}
</style>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>职业安全卫生</span>
    <span class="c-gray en">&gt;</span> <span>职业卫生管理台账</span> 
    <span class="c-gray en">&gt;</span> <span>职业健康检查</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  
  <div class="page-container">
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="20%">职业病危害因素</th>
            <th width="20%">接触情况</th>
            <th width="15%">接触人数</th>
            <th width="15%">体检人数</th>
            <th width="25%">备注</th>
          </tr>
        </thead>
        <tbody>
        <c:forEach items="${list }" varStatus="index" var="e">
          <tr class="text-c">
            <td>${index.index+1 }</td>
            <td>${e.type }</td>
            <td>
            <c:if test="${0==e.contactCondition }">无</c:if>
            <c:if test="${1==e.contactCondition }">有</c:if>
            </td>
            <td>${e.contactMembers }</td>
            <td>${e.healthcheckMember }</td>
            <td>${e.remark }</td>
          </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</body>
</html>