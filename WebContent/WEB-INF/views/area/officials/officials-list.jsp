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
    <span class="c-gray en">&gt;</span> <span>${(session_user.userType == 3 || session_user.userType == 4) ? '隐患排查治理系统' : '、行政执法系统'}</span> 
    <span class="c-gray en">&gt;</span> <span>${(session_user.userType == 3 || session_user.userType == 4) ? '检查人员信息库' : '执法人员管理'}</span>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l">
        <a class="btn btn-primary radius" href="javascript:show_dialog('添加${(session_user.userType == 3 || session_user.userType == 4) ? '检查人员' : '执法人员'}','${ly }/district/off-add')"><i class="Hui-iconfont">&#xe600;</i> 添加${(session_user.userType == 3 || session_user.userType == 4) ? '检查人员' : '执法人员'}</a>
      </span>
      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span> 
    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th>${(session_user.userType == 3 || session_user.userType == 4) ? '检查' : '执法'}部门</th>
            <th>${(session_user.userType == 3 || session_user.userType == 4) ? '检查' : '执法'}人员</th>
            <th>人员编号</th>
            <th>创建时间</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环公告 -->
          <c:forEach items="${list }" varStatus="index" var="be">
          <tr class="text-c">
            <td>${index.index + 1 }</td>
            <td>${be.depart }</td>
            <td>${be.name }</td>
            <td>${be.code }</td>
            <td><fmt:formatDate value="${be.time }"/></td>
            <td>
              <a style="text-decoration:none" onClick='show_tab("编辑人员", "/district/off-add?id=${be.id}");'  href="javascript:;" title="编辑">查看/编辑</a>
              <a style="text-decoration:none"  href="javascript:;" title="删除" onclick="del_(${be.id})">删除</a>
            </td>
          </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
  </div>

<script type="text/javascript">
function del_(id) {
  layer.confirm("确定删除么？",function(i) {
    $.post(getRootPath() + "/district/off-del",{
      id : id,
      del : 1
    },function(result) {
      location.reload();
    })
  })
}
</script> 
</body>
</html>