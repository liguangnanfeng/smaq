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
    <span class="c-gray en">&gt;</span> <span>危险有害因素检索</span> 
    <span class="c-gray en">&gt;</span> <span>危化品目录</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="text-c">
      <form action="${ly }/global/danger/danger-list" method="post">
        <div class="dis-ib">
          <span>品名：</span>
          <input type="text" value="${dangerName }" id="dangerName" name="dangerName" class="input-text mb-5 mt-5" style="width:150px;">
        </div>
        <button class="btn btn-success" type="submit">
          <i class="Hui-iconfont">&#xe665;</i> 查询
        </button>
      </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l">
        <a class="btn btn-primary radius" href="javascript:show_dialog('查看危险信息说明','${ly }/global/danger/danger-show')"><i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 查看危险信息说明</a>
        <a class="btn btn-primary radius ml-10" href="${ly}/common/download?filename=危险化学品目录.pdf&fileurl=${ly}/upload/trouble/25.pdf" title="查看危化品目录"><i class="Hui-iconfont" style="font-size:15px;">&#xe640;</i> 查看危化品目录</a>
      </span>
      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span> 
    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="35%">品名</th>
            <th width="30%">别名</th>
            <th width="15%">CAS号</th>
            <th width="15%">备注</th>
          </tr>
        </thead>
        <tbody>
        <c:forEach items="${list }" varStatus="index" var="d">
        <tr class="text-c">
          <td>${index.index+1 }</td>
          <td>${d.name}</td>
          <td>${d.name2 }</td>
          <td>${d.cas }</td>
          <td>${d.remark }</td>
        </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
    <div class="mt-40">
    <img alt="" src="${ly }/images/d3.jpg" style="margin-top:30px;"/>
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