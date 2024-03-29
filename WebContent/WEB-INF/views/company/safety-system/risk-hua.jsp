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
<script type="text/javascript">
  $(function(){
    $('.table-sort').dataTable({
      "aaSorting": [[0, "asc"]],//默认第几个排序
      "bStateSave": false,//状态保存
      ordering:  false,
      "aoColumnDefs": [
      ]
      });
  })
</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>风险分级管控</span> 
    <span class="c-gray en">&gt;</span> <span>常用危险化学品特性表</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort tab-ndan">
        <thead>
          <tr class="text-c">
            <th style="width:40px">序号</th>
            <th style="min-width:100px">物质名称</th>
            <th style="min-width:100px">外观与性状</th>
            <th style="min-width:100px">溶解性</th>
            <th style="min-width:100px">爆炸下限</th>
            <th style="min-width:100px">爆炸上限</th>
            <th style="width:30%">健康危害</th>
            <th style="min-width:100px">操作</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${list }" var="be" varStatus="index">
          <tr class="text-c">
            <td>${index.index + 1}</td>
            <td><p>${be.name }</p></td>
            <td><p>${be.whWg }</p></td>
            <td><p>${be.whRjx }</p></td>
            <td><p>${be.rsBzMin }</p></td>
            <td><p>${be.rsBzMax }</p></td>
            <td><p>${be.dxWh }</p></td>
            <td>
              <a style="text-decoration:none" onClick="show_dialog('查看详情', '/fore/safety-system/risk-information-detail2?id=${be.id}')" href="javascript:;" title="查看详情">查看详情</a>
            </td>
          </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</body>
</html>