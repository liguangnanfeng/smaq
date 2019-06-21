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
.btn-group .btn{height: 34px;line-height: 34px;padding: 0 25px;}
</style>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>${flag!=2 ? '隐患排查治理系统' : '行政执法系统'}</span> 
    <c:if test="${flag != 2}">
      <span class="c-gray en">&gt;</span> <span>隐患治理复查</span> 
    </c:if>
    <c:if test="${flag == 2}">
      <span class="c-gray en">&gt;</span> <span>执法复查</span> 
    </c:if>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <c:if test="${flag != 2}">
    <div id="spTab" class="btn-group" style="text-align: center;margin-bottom: 20px;">
        <a class="btn default ${flag == 1 ? 'btn-primary' : 'radius'}" href="${ly }/village/rectification-list?flag=1">企业自查</a>
        <a class="btn default ${flag == 4 ? 'btn-primary' : 'radius'}" href="${ly }/village/rectification-list?flag=4">行政检查</a>
        <a class="btn default ${flag == 3 ? 'btn-primary' : 'radius'}" href="${ly }/village/rectification-list?flag=3">部门抽查</a>
        <%-- <a class="btn default ${flag == 2 ? 'btn-primary' : 'radius'}" href="${ly }/village/rectification-list?flag=2">执法检查</a> --%>
    </div>
    </c:if>
    <div class="text-c">
      <form action="${ly }/village/rectification-list?flag=${flag}" method="post">
        <div class="dis-ib">
          <span>检查表名称：</span>
          <input type="text" value="${title }" name="${title }" class="input-text mb-5 mt-5" style="width:150px;">
          <span style="display: none;">整改状态：</span>
            <select class="input-text mb-5 mt-5" style="width:150px;display: none;" name="c">
                <option value="">全部</option>
                <option value="0" <c:if test="${c == 0}"> selected</c:if>>未整改</option>
                <option value="1" <c:if test="${c == 1}"> selected</c:if>>已整改</option>
            </select>
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
            <th width="5%">编号</th>
            <th width="25%">检查表名称</th>
            <th width="15%">${flag == 1 ? '检查' : '录入'}时间</th>
            <th width="10%">隐患数量</th>
            <th width="10%">已整改</th>
            <th width="10%">未整改</th>
            <th width="15%">受检部门</th>
            <th width="10%">操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环-->
          <c:forEach items="${list }" var="be" varStatus="index">
            <tr class="text-c">
            <td>${index.index + 1}</td>
            <td>${be.title }</td>
            <td><fmt:formatDate value="${be.realTime }" pattern="yyyy-MM-dd"/></td>
            <td>${be.a }</td>
            <td>${be.b }</td>
            <td>${be.a - be.b}</td>
            <td>${be.depart }</td>
            <td>
              <c:if test="${be.c > 0}">
                <a style="text-decoration:none" onClick="show_dialog('复查详情_${be.checkId}', '${ly }/company/recheck-detail?checkId=${be.checkId }')" href="javascript:;">查看详情</a><br>
              </c:if>
              <c:if test="${be.a - be.b > 0}">
                <a style="text-decoration:none" onClick="show_dialog('整改复查_${be.checkId}', '${ly }/company/recheck-add?checkId=${be.checkId }')" href="javascript:;">整改复查</a><br>
              </c:if>
              <a style="text-decoration:none" onClick="del_(${be.id })" href="javascript:;">删除复查</a><br>
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
    "aoColumnDefs": [
    ]
    });
});
function del_(id) {
  layer.confirm("确定删除？", function(i) {
    layer.close(i);
    $.post(getRootPath() + "/company/fu-del",{
      id : id
    }, function(result) {
      $("form").submit();
    })
  })
}
</script> 
</body>
</html>