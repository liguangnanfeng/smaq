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
    <span class="c-gray en">&gt;</span> <span>村庄管理</span> 
    <span class="c-gray en">&gt;</span> <span>村庄列表</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="text-c">
      <form action="${ly }/back/village/village-list" method="post">
        <div class="dis-ib"><span>村庄名称：</span><input type="text" value="${name }" name="name" class="input-text mb-5 mt-5" style="width:150px;"></div>
        <button class="btn btn-success inline" type="submit">
          <i class="Hui-iconfont">&#xe665;</i> 查询
        </button>
      </form>
    </div>
    
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l">
        <a class="btn btn-primary radius" data-title="添加村庄" data-href="${ly }/back/village/village-add" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加村庄</a>
      </span>
      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span> 
    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="10%">登录账号</th>
            <th width="15%">村庄名称</th>
            <th width="10%">所属乡镇</th>
            <th width="15%">所在地区</th>
            <th width="20%">详细地址</th>
            <th width="10%">运行状态</th>
            <th width="20%">操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环村 -->
          <c:forEach items="${list }" varStatus="index" var="vi">
          <tr class="text-c">
            <td>${index.index + 1}</td>
            <td>${vi.userName}</td>
            <td>${vi.name}</td>
            <td>${vi.townName}</td>
            <td>${vi.regionName}</td>
            <td>${vi.address}</td>
            <td>${vi.isFreeze eq '1' ? '冻结' : '正常'}</td>
            <td>
              <a style="text-decoration:none" onClick="show_dialog('村庄信息', '${ly }/back/village/village-edit?userId=${vi.userId }')" href="javascript:;" title="编辑">查看/编辑</a>
              <c:if test="${vi.isFreeze == '0'}">
              <a style="text-decoration:none" onClick="user_freeze(${vi.userId})" href="javascript:;" title="冻结">冻结</a>
              </c:if>
              <c:if test="${vi.isFreeze == '1'}">
              <a style="text-decoration:none" onClick="user_unfreeze(${vi.userId})" href="javascript:;" title="解冻">解冻</a>
              </c:if>
              <a style="text-decoration:none" onclick="psw(${vi.userId})" href="javascript:;" title="重置密码">重置密码</a>
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