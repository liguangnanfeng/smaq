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
    <span class="c-gray en">&gt;</span> <span>消防设备设施</span> 
    <span class="c-gray en">&gt;</span> <span>${flag == 1 ? '消防' : '应急'}设备设施</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:history.back(-1)" title="返回">返回</a>
  </nav>
  <div class="page-container">
    <div class="text-c">
      <form action="${ly }/company/facilities/facilities-list" method="post">
        <div class="dis-ib">
          <span>种类：</span>
          <input type="text" value="${faType }" id="model" name="faType" class="input-text mb-5 mt-5" style="width:150px;">
        </div>
<!--         <div class="dis-ib"> -->
<!--           <span>到期时间：</span> -->
<!--           <span class="select-box inline"> -->
<!--           <select name="isTime" class="select"> -->
<!--             <option value="">全部</option> -->
<!--             <option value="1">1个月内到期</option> -->
<!--             <option value="2">2个月内到期</option> -->
<!--             <option value="3">3个月内到期</option> -->
<!--           </select> -->
<!--           </span> -->
<!--         </div> -->
        <button class="btn btn-success" type="submit">
          <i class="Hui-iconfont">&#xe665;</i> 查询
        </button>
      </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l">
        <a class="btn btn-primary radius" data-title="添加${flag == 1 ? '消防' : '应急'}设备设施" data-href="${ly }/company/facilities/facilities-edit?flag=${flag}" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 添加${flag == 1 ? '消防' : '应急'}设施配备</a>
      </span>
      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span> 
    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="15%">种类</th>
            <th width="15%">型号</th>
            <th width="15%">配备位置</th>
            <th width="15%">数量</th>
            <th width="10%">有效期</th>
            <th width="15%">备注</th>
            <th width="10%">操作</th>
          </tr>
        </thead>
        <tbody>
        <c:forEach items="${list }" varStatus="index" var="fac">
          <tr class="text-c">
            <td>${index.index+1 }</td>
            <td>${fac.type }</td>
            <td>${fac.model }</td>
            <td>${fac.equippedPosition }</td>
            <td>${fac.amount }</td>
            <td>${fac.expiryDate }</td>
            <td>${fac.remark }</td>
            <td>
              <a style="text-decoration:none" onClick="admin_edit(${fac.id})" href="javascript:;" title="编辑">查看/编辑</a>
              <a style="text-decoration:none" onClick="del(${fac.id})" href="javascript:;" title="删除">删除</a>
            </td>
          </tr>
          </c:forEach>
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
var flag = '${flag}'
/*编辑*/
function admin_edit(id){
  show_tab("编辑" + (flag=='1'?'消防':'应急') + "设备设施", getRootPath() + "/company/facilities/facilities-edit?id=" + id + "&flag=" + flag);
}

/*删除*/
function del(id){
  $.post(getRootPath()+"/company/facilities/deleteFa",{
    id:id,
  },function(reuslt){
    location.reload()
  })
}
</script> 
</body>
</html>