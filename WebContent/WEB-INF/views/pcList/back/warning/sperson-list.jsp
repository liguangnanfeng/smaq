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
    <span class="c-gray en">&gt;</span> <span>预警提醒</span> 
    <span class="c-gray en">&gt;</span> <span>特种作业人员</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="text-c">
      <form action="${ly }/back/warning/sperson-list" method="post">
        <div class="dis-ib"><span>村庄名称：</span><input type="text" value="${villageName }" name="villageName" class="input-text mb-5 mt-5" style="width:150px;"></div>
        <div class="dis-ib"><span>企业名称：</span><input type="text" value="${companyname }" name="companyname" class="input-text mb-5 mt-5" style="width:150px;"></div>
        <div class="dis-ib">
          <span>到期时间：</span>
          <span class="select-box inline">
          <select name="isTime" class="select">
            <option value="">全部</option>
            <option value="1"<c:if test="${isTime == 1}"> selected</c:if>>1个月内到期</option>
            <option value="2"<c:if test="${isTime == 2}"> selected</c:if>>2个月内到期</option>
            <option value="3"<c:if test="${isTime == 3}"> selected</c:if>>3个月内到期</option>
            <option value="0" <c:if test="${0==isTime}">selected</c:if>>已过期</option>
          </select>
          </span>
        </div>
        <button class="btn btn-success inline" type="submit">
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
            <th width="10%">所属村庄</th>
            <th width="10%">所属企业</th>
            <th width="10%">姓名</th>
            <th width="10%">工种</th>
            <th width="15%">证书编号</th>
            <th width="15%">取证时间</th>
            <th width="15%">下次复审时间</th>
            <th width="10%">备注</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环-->
          <c:forEach items="${list }" varStatus="index" var="sp">
          <tr class="text-c">
            <td>${index.index + 1 }</td>
            <td>${sp.villageName }</td>
            <td>${sp.companyName }</td>
            <td>${sp.name }</td>
            <td>${sp.type }</td>
            <td>${sp.certificateNumber }</td>
            <td>${sp.forensicTime }</td>
            <td>${sp.reviewTime }</td>
            <td>${sp.remark }</td>
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
</script> 
</body>
</html>