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
<title>后台首页</title>
<meta name="keywords" content="后台首页">
<meta name="description" content="后台首页">
<style type="text/css">
body .dis-ib{margin-right:15px;}
</style>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>乡镇管理</span> 
    <span class="c-gray en">&gt;</span> <span>乡镇列表</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="text-c">
      <form action="${ly }/back/town/town-list" method="post">
        <div class="dis-ib"><span>登录账号：</span><input type="text" value="${userName }" name="userName" class="input-text mb-5 mt-5" style="width:150px;"></div>
        <div class="dis-ib"><span>乡镇名称：</span><input type="text" value="${name }" name="name" class="input-text mb-5 mt-5" style="width:150px;"></div>
        <div class="dis-ib">
          <span>运行状态：</span>
          <span class="select-box inline">
          <select name="isFreeze" class="select">
            <option value="">全部</option>
            <option value="1"<c:if test="${isFreeze == '1'}"> selected</c:if>>已冻结</option>
            <option value="0"<c:if test="${isFreeze == '0'}"> selected</c:if>>未冻结</option>
          </select>
          </span>
        </div>
        <button class="btn btn-success" type="submit">
          <i class="Hui-iconfont">&#xe665;</i> 查询
        </button>
      </form>
    </div>
    
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l">
        <a class="btn btn-primary radius" data-title="添加乡镇" data-href="${ly }/back/town/town-add" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加乡镇</a>
      </span>
      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span> 
    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="15%">登录账号</th>
            <th width="15%">乡镇名称</th>
            <th width="10%">乡镇地址</th>
            <th width="20%">下属村</th>
            <th width="15%">运行状态</th>
            <th width="15%">操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环镇 -->
          <c:forEach items="${list }" varStatus="index" var="to">
          <tr class="text-c">
            <td>${index.index + 1}</td>
            <td>${to.userName}</td>
            <td>${to.name}</td>
            <td>${to.regionName}, ${to.address}</td>
            <td>${to.c }</td>
            <td>${to.isFreeze eq '1' ? '冻结' : '正常'}</td>
            <td>
              <a style="text-decoration:none" onClick="show_dialog('乡镇信息', '${ly}/back/town/town-edit?userId=${to.userId }');" href="javascript:;" title="编辑">查看/编辑</a>
              <c:if test="${to.isFreeze == '0'}">
              <a style="text-decoration:none" onClick="user_freeze(${to.userId})" href="javascript:;" title="冻结">冻结</a>
              </c:if>
              <c:if test="${to.isFreeze == '1'}">
              <a style="text-decoration:none" onClick="user_unfreeze(${to.userId})" href="javascript:;" title="解冻">解冻</a>
              </c:if>
              <a style="text-decoration:none" onclick="psw(${to.userId})" href="javascript:;" title="重置密码">重置密码</a>
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

//冻结
function user_freeze(userId){
layer.confirm("确认要冻结该村庄吗？",function(){
  var index = layer.load();
  $.post(getRootPath() + "/back/user-freeze",{
    id : userId,
    isFreeze : 1
  },function(result){
    layer.close(index);
    $("form").submit();
  })
});
}

//解冻
function user_unfreeze(userId){
layer.confirm("确认要解冻该村庄吗？",function(){
  var index = layer.load();
  $.post(getRootPath() + "/back/user-freeze",{
    id : userId,
    isFreeze : 0
  },function(result){
    layer.close(index);
    $("form").submit();
  })
});
}

//密码重置
function psw(userId){
layer.confirm("确认要重置密码么？",function(index){
$.post(getRootPath() + "/back/user-psw",{
  id : userId
},function(result){
  layer.alert("密码重置为‘123456’");
});
});
}
</script> 
</body>
</html>