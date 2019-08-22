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
    <span class="c-gray en">&gt;</span> <span>安全生产标准化</span> 
    <span class="c-gray en">&gt;</span> <span>防雷防静电</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="text-c">
      <form action="${ly}/tradeclique/clique/lightning-list" method="post">
        <div class="dis-ib">
          <span>企业名称：</span>
          <input type="text" value="${companyName }" id="companyName" name="companyName" class="input-text mb-5 mt-5" style="width:150px;">
        </div>
        <div class="dis-ib">
          <span>检测项目：</span>
          <input type="text" value="${project }" id="project" name="project" class="input-text mb-5 mt-5" style="width:150px;">
        </div>
        <button class="btn btn-success" type="submit">
          <i class="Hui-iconfont">&#xe665;</i> 查询
        </button>
      </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l">
        <a class="btn btn-primary radius" data-title="添加防雷防电" data-href="${ly }/tradeclique/clique/lightning-edit" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 添加防雷防静电</a>
      </span>
      <span class="r">共有数据：<strong>${fn:length(cliqueList)+fn:length(list) }</strong> 条</span> 
    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
          	<th width="5%">序号</th>
          	<th width="10%">企业名称</th>
            <th width="10%">检测项目</th>
            <th width="10%">检测内容</th>
            <th width="7.5%">上次检测时间</th>
            <th width="7.5%">到期时间</th>
            <th width="10%">检测结果</th>
            <th width="10%">检测单位</th>
            <th width="10%">检测报告编号</th>
            <th width="10%">备注</th>
            <th width="10%">操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环开始 -->
          <c:forEach items="${cliqueList }" varStatus="index" var="list">
          <tr class="text-c">
            <td>${index.index+1 }</td>
            <td>${cliqueName }</td>
            <td>${list.project }</td>
            <td>${list.content }</td>
            <td><fmt:formatDate value="${list.lastTestTime }" pattern="yyyy-MM-dd"/></td>
            <td><fmt:formatDate value="${list.expirationTime }" pattern="yyyy-MM-dd"/></td>
            <td>${list.testResults }</td>
            <td>${list.detectionUnit }</td>
            <td>${list.test_report_number }</td>
            <td>${list.remark }</td>
            <td>
              <a style="text-decoration:none" onClick="edit(${list.id })" href="javascript:;" title="编辑">编辑</a>
              <a style="text-decoration:none" onClick="del(${list.id })" href="javascript:;" title="删除">删除</a>
            </td>
          </tr>
          </c:forEach>
		  <c:forEach items="${list }" varStatus="index" var="list">
          <tr class="text-c">
            <td>${fn:length(cliqueList)+index.index+1 }</td>
            <td>${list.companyName }</td>
            <td>${list.project }</td>
            <td>${list.content }</td>
            <td><fmt:formatDate value="${list.lastTestTime }" pattern="yyyy-MM-dd"/></td>
            <td><fmt:formatDate value="${list.expirationTime }" pattern="yyyy-MM-dd"/></td>
            <td>${list.testResults }</td>
            <td>${list.detectionUnit }</td>
            <td>${list.test_report_number }</td>
            <td>${list.remark }</td>
            <td>
              <a style="text-decoration:none" onClick="edit(${list.id })" href="javascript:;" title="编辑">编辑</a>
              <a style="text-decoration:none" onClick="del(${list.id })" href="javascript:;" title="删除">删除</a>
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

/*编辑*/
function edit(id){
  show_tab("编辑防雷防静电", getRootPath() + "/tradeclique/clique/lightning-edit?id=" + id);
}

/*删除*/
function del(id){
  layer.confirm("确认要删除吗？",function(r){
    $.post(getRootPath() + "/tradeclique/clique/lightning-del", {
      id : id,
    },function(result) {
      $("form").submit();
    });
  });
}
</script> 
</body>
</html>