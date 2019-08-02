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
.input-text{position:relative}
</style>

</head>
<body>
<nav class="breadcrumb">
  <i class="Hui-iconfont">&#xe67f;</i>
  <span>首页</span>
  <span class="c-gray en">&gt;</span>
  <span>系统管理</span>
  <span class="c-gray en">&gt;</span>
  <span>清除报警时间间隔</span>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
    <i class="Hui-iconfont">&#xe68f;</i>
  </a>
</nav>
<div class="page-container">
  <form class="form form-horizontal" id="form">
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>自动清除时间：</label>
      <div class="formControls col-xs-8 col-sm-9" style="padding-top:3px">
        <select id="timeCleanSelect" style="width:200px">
        	<c:choose>
        		<c:when test="${null != setting }">
        			<option <c:if test="${setting.maxTimes == 1 }">selected</c:if> value="1">一个月前</option>
		        	<option <c:if test="${setting.maxTimes == 3 }">selected</c:if> value="3">一季度前</option>
		        	<option <c:if test="${setting.maxTimes == 6 }">selected</c:if> value="6">半年前</option>
		        	<option <c:if test="${setting.maxTimes == 12 }">selected</c:if> value="12">一年前</option>
        		</c:when>
        		<c:otherwise>
        			<option value="1">近一个月</option>
		        	<option value="3">近一季度</option>
		        	<option selected value="6">近半年</option>
		        	<option value="12">近一年</option>
        		</c:otherwise>
        	</c:choose>
        	
        </select>
      </div>
    </div>
  </form>
</div>
<script type="text/javascript" src="${ly }/js/modules/system/timeSetting.js"></script>
</body>
</html>