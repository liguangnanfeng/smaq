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
    <span class="c-gray en">&gt;</span> <span>预警报警</span> 
    <span class="c-gray en">&gt;</span> <span>评价评估</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:history.back(-1)" title="返回">返回</a>
  </nav>
  <div class="page-container">
    <div class="text-c">
      <form action="${ly }/global/evaluate/evaluation-list" method="post">
        <div class="dis-ib">
          <span>评价报告名称：</span>
          <input type="text" value="${reportName }" id="evaName" name="reportName" class="input-text mb-5 mt-5" style="width:150px;">
        </div>
        <div class="dis-ib">
          <span>企业名称：</span>
          <input type="text" value="${companyName }" id="companyName" name="companyName" class="input-text mb-5 mt-5" style="width:150px;">
        </div>
        <div class="dis-ib">
          <span>评价类型：</span>
          <span class="select-box inline">
            <select name="flag" class="select">
              <option value="">全部</option>
              <option value="1" <c:if test="${1==flag}">selected</c:if>>安全生产类</option>
              <option value="2" <c:if test="${2==flag}">selected</c:if>>职业健康类</option>
            </select>
          </span>
        </div>
        <div class="dis-ib">
          <span>到期时间：</span>
          <span class="select-box inline">
          <select name="isTime" class="select">
            <option value="">全部</option>
            <option value="6" <c:if test="${6==isTime}">selected</c:if>>6个月内到期</option>
            <option value="0" <c:if test="${0==isTime}">selected</c:if>>已过期</option>
          </select>
          </span>
        </div>
        <button class="btn btn-success" type="submit">
          <i class="Hui-iconfont">&#xe665;</i> 查询
        </button>
      </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span>
	</div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="10%">所属企业</th>
            <th width="10%">评价类型</th>
            <th width="20%">评价项目名称</th>
            <th width="10%">编制日期</th>
            <th width="10%">有效期</th>
            <th width="10%">编制单位</th>
            <th width="15%">报告文档</th>
            <th width="10%">操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环-->
          <c:forEach items="${list }" varStatus="index" var="eva">
          <tr class="text-c">
            <td>${index.index+1 }</td>
            <td>${eva.companyName }</td>
            <td>
            	<c:if test="${eva.flag==1 && eva.type==1}">安全生产类--预评价</c:if>
            	<c:if test="${eva.flag==1 && eva.type==2}">安全生产类--验收评价</c:if>
            	<c:if test="${eva.flag==1 && eva.type==3}">安全生产类--现状评价</c:if>
            	<c:if test="${eva.flag==1 && eva.type==4}">安全生产类--危化品经营证评价</c:if>
            	<c:if test="${eva.flag==1 && eva.type==5}">安全生产类--危化品生产证评价</c:if>
            	<c:if test="${eva.flag==1 && eva.type==6}">安全生产类--危化品使用证评价</c:if>
            	<c:if test="${eva.flag==1 && eva.type==7}">安全生产类--重大危险源评估</c:if>
            	<c:if test="${eva.flag==1 && eva.type==8}">安全生产类--安全条件论证报告</c:if>
            	<c:if test="${eva.flag==1 && eva.type==9}">安全生产类--安全条件综合分析报告</c:if>
            	<c:if test="${eva.flag==2 && eva.type==1}">职业健康类--预评价</c:if>
            	<c:if test="${eva.flag==2 && eva.type==2}">职业健康类--控效评价</c:if>
            	<c:if test="${eva.flag==2 && eva.type==3}">职业健康类--现状评价</c:if>
           	</td>
            <td>${eva.reportName }</td>
            <td>${eva.reportTime }</td>
            <td>${eva.expirationTime }</td>
            <td>${eva.unit }</td>
            <td>${eva.docName }</td>
            <td>
            <c:if test="${!empty eva.docUrl}">
              <a style="text-decoration:none" href="${ly}/common/download?filename=${eva.docName}&fileurl=${eva.docUrl}" title="查看文档" target="_blank">查看文档</a>
            </c:if>
            </td>
          </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
  </div>
<script type="text/javascript">
$(function() {
  $('.table-sort').dataTable({
    "aaSorting": [[0, "asc"]],//默认第几个排序
    "bStateSave": false,//状态保存
    searching: false,
    ordering:  false,
    "aoColumnDefs": [
    ]
    });
});

$(function() {

})
</script>
</body>
</html>