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
    <span class="c-gray en">&gt;</span> <span>企业基础信息</span> 
    <span class="c-gray en">&gt;</span> <span>主要原辅材料</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="text-c">
      <form action="${ly }/company/product/material-list" method="post">
        <div class="dis-ib">
          <span>原辅材料名称：</span>
          <input type="text" value="${materialName }" id="maName" name="materialName" class="input-text mb-5 mt-5" style="width:150px;">
          </div>
        <button class="btn btn-success" type="submit">
          <i class="Hui-iconfont">&#xe665;</i> 查询
        </button>
      </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l">
        <a class="btn btn-primary radius" href="javascript:show_dialog('添加主要原辅材料','${ly }/company/product/material-add')"<%-- data-title="添加主要原辅材料" data-href="${ly }/company/product/material-add" onclick="Hui_admin_tab(this)" href="javascript:;" --%>><i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 添加主要原辅材料</a>
      </span>
      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span> 
    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="15%">原辅材料名称</th>
            <th width="10%">年用量</th>
            <th width="10%">最大储存量</th>
            <th width="5%">物态</th>
            <th width="15%">储存地点</th>
            <th width="15%">储存方式</th>
            <th width="15%">备注</th>
            <th width="10%">操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环-->
          <c:forEach items="${list }" varStatus="index" var="mat">
          <tr class="text-c">
            <td>${index.index+1 }</td>
            <td>${mat.material }</td>
            <td>${mat.annualConsumption }</td>
            <td>${mat.maximumStorage }</td>
            <td>${mat.state }</td>
            <td>${mat.storageLocation }</td>
            <td>${mat.storageMethod }</td>
            <td>${mat.remark }</td>
            <td>
              <a style="text-decoration:none" onClick="admin_edit(${mat.id})" href="javascript:;" title="编辑">查看/编辑</a>
              <a style="text-decoration:none" onClick="del(${mat.id})" href="javascript:;" title="删除">删除</a>
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
function admin_edit(id){
  show_tab("编辑主要原辅材料", getRootPath() + "/company/product/material-edit?id=" + id);
}

/*删除*/
function del(id){
  $.post(getRootPath()+"/company/product/deleteMaterial",{
    id:id,
  },function(reuslt){
    location.reload()
  })
}
// /*查询*/
//  function inquriy(){
//    $.post(getRootPath()+"/company/product/ma-inquriy",{
//      material:$("#maName").val(),
//    },function(reuslt){
//    })
// }
</script> 
</body>
</html>