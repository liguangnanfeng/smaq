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
<title>安全分级管控   隐患排查治理智能化信息平台</title>
<meta name="keywords" content="安全分级管控   隐患排查治理智能化信息平台">
<meta name="description" content="安全分级管控   隐患排查治理智能化信息平台">
<style type="text/css">
body .dis-ib{margin-right:15px;}
p {
	font-family:"Microsoft YaHei";
	font-size: 160%;
	letter-spacing: 5px;
	text-indent: 2em;
}
</style>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>信息列表</span> 
    <span class="c-gray en">&gt;</span> <span>信息详情</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  
  <div class="page-container">
  	<div class="row cl">
      <!-- <label class="form-label col-xs-4 col-sm-2">标题：</label> -->
      <c:choose>
      	<c:when test="${from == 1 }">
      		<a href="<c:url value='/village/reviceMessageList'/>" class="" style="float:right;margin-right:20px;">返回</a>
      	</c:when>
      	<c:when test="${from == 0 }">
      		<a href="<c:url value='/village/sendMessageList'/>" class="btn btn-success radius r" style="float:right;margin-right:20px;">返回</a>	
      	</c:when>
      </c:choose>
    </div>
    <div class="row cl">
      <!-- <label class="form-label col-xs-4 col-sm-2">标题：</label> -->
      <div class="formControls" style="text-align:center;">
        <h1>${message.title }</h1>
      </div>
    </div>
    <div class="row cl">
      <!-- <label class="form-label col-xs-4 col-sm-2">创建时间：</label> -->
      <div class="formControls" style="text-align:center;">
       创建时间： <fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${message.createTime}" />
      </div>
    </div>
    <div class="row cl">
      <!-- <label class="form-label col-xs-4 col-sm-2">描述：</label> -->
      <div class="formControls" style="text-align:center;">
        简介：${message.descr }
      </div>
    </div>
    <div class="row cl">
      <!-- <label class="form-label col-xs-4 col-sm-2">正文：</label> -->
      <div class="formControls col-xs-8 col-sm-9" style="max-height:700px;overfow:auto;">
      <h5>正文：</h5>
      <p>
      <c:choose>
      	<c:when test="${empty message.content }">
      		暂无
      	</c:when>
      	<c:otherwise>
      		${message.content }
      	</c:otherwise>
      </c:choose>
      </p>
      </div>
      <div class="row cl">
      <!-- <label class="form-label col-xs-4 col-sm-2">附件：</label> -->
      <div class="formControls col-xs-8 col-sm-9">
      <h5>
	      附件：<c:choose>
	      	<c:when test="${fn:length(files) >0}">
	      		<c:forEach items="${files }" var="f">
		        	<a href="${f.url }" style="margin-left:10px;">${f.name }</a>
		        </c:forEach>
	      	</c:when>
	      	<c:otherwise>
	      		暂无
	      	</c:otherwise>
	      </c:choose>
        </h5>
      </div>
    </div>
  </div>
</div>
</body>
</html>