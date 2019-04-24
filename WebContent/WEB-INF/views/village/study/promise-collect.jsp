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
<%@ include file="/WEB-INF/inc/print.inc"%>
<script type="text/javascript">
function pr_() {
		$("#div_container").jqprint();
}
</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>风险研判与承诺公告</span> 
    <span class="c-gray en">&gt;</span> <span>安全承诺公告</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
    <div class="page-container" id="div_container" >
	    <div class="cl pd-5 bg-1 bk-gray mt-20">
	      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span>
	    </div>
	    <div class="bd">
	        <ul>
                <li>
  <div class="mt-20">
    <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="15%">编号</th>
            <th width="30%">企业名称</th>
            <th width="20%">本月承诺公告数</th>
            <th width="20%">承诺公告公示总数</th>
            <th width="15%">操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环-->
          <c:forEach items="${list }" varStatus="index" var="be">
            <tr class="text-c">
            	<td>${index.index + 1 }</td>
            	<td>${be.name }</td>
            	<td><a href="${ly }/villageAdd/study/promise-list?uid=${be.uid }&type=1">${be.MonthNum }</a></td>
            	<td>${be.TotalNum }</td>
            	<td><a href="${ly }/villageAdd/study/promise-list?uid=${be.uid }&type=0">查看详情</a></td>
            </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
    </table>
  </div>
                </li>
            </ul>
	    </div>
  </div>
</body>
<script type="text/javascript">
$(function() {
  $('.table-sort').dataTable({
    "aaSorting": [[0, "asc"]],//默认第几个排序
    "bStateSave": false,//状态保存
    "aoColumnDefs": [
    ]
    });
});
</script> 
<script type="text/javascript" src="/js/fxgk/tbl-rowspan-reset.js"></script>
</html>