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
.tabBar {border-bottom:0px;text-align:center;background:#fff;height:42px;line-height:42px;margin-top:28px;}
.tabBar a {height:42px;line-height:42px;background-color:#E5F3F7;border:0px;font-size:14px;color:#687E99;cursor: pointer;display: inline-block;float: none;font-weight: bold;padding: 0 25px}
.tabBar a.current{background-color:#008DFF;color: #fff;border:0px;}
.tabCon {display: none;padding:0;border:0px;}
</style>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>职业健康管理</span> 
    <span class="c-gray en">&gt;</span> <span>劳动者个人职业健康监护</span>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:history.back(-1)" title="返回">返回</a>
  </nav>
    <div class="page-container">
      <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
          <a class="btn btn-primary radius" data-title="劳动者个人职业健康监护-添加" data-href="${ly }/company/cyryjkjh-add" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 添加劳动者个人职业健康监护</a>
        </span>
        <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span>
      </div>
      <div class="mt-20">
        <table class="table table-border table-bordered table-bg table-hover table-sort">
          <thead>
            <tr class="text-c">
                <th width="5%">序号</th>
                <th>劳动者姓名</th>
                <th>检查日期</th>
                <th>检查项目摘要</th>
                <th>检查结果</th>
                <th>检查单位</th>
                <th>接触的职业病危害因素名称</th>
                <th>体检类别</th>
                <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <!-- 循环-->
            <c:forEach items="${list }" varStatus="index" var="de">
            <tr class="text-c">
                <td>${index.index+1 }</td>
                <td>${de.name }</td>
                <td>${de.time }</td>
                <td>${de.jcxmzy }</td>
                <td>${de.jcjg }</td>
                <td>${de.jcdw }</td>
                <td>${de.zybwhys }</td>
                <td>${de.tjlb }</td>
                 <td>
                  <a style="text-decoration:none" href="javascript:void(0)" onclick="top.show_tab('职业健康体检-编辑', '/company/cyryjkjh-add?id=${de.id}')" title="编辑">编辑</a>
                  <a style="text-decoration:none" href="javascript:void(0)" onclick="del_(${de.id})" title="删除">删除</a>
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
function del_(id) {
  $.post("/company/cyryjkjh-del", {
    id : id
  },function(result) {
    location.reload();
  })
}
</script> 
</body>
</html>