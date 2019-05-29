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
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>领导关注</span> 
    <span class="c-gray en">&gt;</span> <span>一周监管动态</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <%-- <span class="l">
        <a class="btn btn-primary radius" data-title="添加公告" data-href="${ly }/village/notice/notice-add" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加公告</a>
      </span> --%>
      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span> 
    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="20%">标题</th>
            <th width="20%">公告类型</th>
            <th width="40%">详情</th>
            <th width="15%">操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环公告 -->
          <c:forEach items="${list }" varStatus="index" var="no">
          <tr class="text-c">
            <td>${index.index + 1 }</td>
            <td>${no.title }</td>
            <td>一周监管动态</td>
            <td><lanlin:htmlToText length="50" value="${no.content }"/></td><!-- 显示50字 -->
            <td>
              <a style="text-decoration:none" onClick="admin_show(${no.id})"  href="javascript:;" title="查看详情">查看详情</a>
            </td>
          </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
  </div>

<script type="text/javascript">
/*编辑*/
function admin_show(id){
  show_dialog("详情", getRootPath() + "/village/notice/notice-show?id=" + id);
}
</script>
</body>
</html>