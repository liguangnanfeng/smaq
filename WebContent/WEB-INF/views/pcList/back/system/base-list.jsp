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
<script type="text/javascript">
var Id = null,Type = '${type}';
function openwin(){
  $("#winbase").modal("show");
}
function check(id,name,cycle,type){
  openwin(id);
  $('#baseName').val(name);
  Id=id;
  Type=type;
  $('#cycle').val(cycle);
}
</script>
</head>
<body>
<c:set var="lis" value="${fn:split('所属行业基库,风险分级基库,安全管理人员类别基库,特种工作人员工种基库,评价类型基库,检测类别基库',',') }"/>
<c:set var="dictTitle" value="${lis[type-1]}"/>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>系统管理</span> 
    <!-- 判断基库名称 -->
    <span class="c-gray en">&gt;</span> <span>${lis[type-1]}基库</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <!-- 判断基库名称 -->
    <h2 style="text-align:center;">${dictTitle}基库</h2>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <!-- <span class="l">
        <button class="btn btn-primary radius" onclick="openwin()"><i class="Hui-iconfont">&#xe600;</i> 添加数据</button>
      </span> -->
      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span> 
    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="60%">名称</th>
            <c:if test="${type >= 5}">
            <th width="15%">周期（年）</th>
            </c:if>
           <!--  <th width="20%">操作</th> -->
          </tr>
        </thead>
        <tbody>
          <!-- 循环 -->
          <c:forEach items="${list }" varStatus="index" var="be">
          <tr class="text-c">
            <td>${index.index + 1}</td>
            <td>${be.name}</td>
            <c:if test="${type >= 5}">
            <td>${be.cycle}</td>
            </c:if>
            <%-- <td>
              <a style="text-decoration:none" onclick="check(${be.id},'${be.name }','${be.cycle }',${be.type })" href="javascript:;" title="编辑">查看/编辑</a>
              <a style="text-decoration:none" onclick="del(${be.id})" href="javascript:;" title="删除">删除</a>
            </td> --%>
          </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
    <!-- 弹窗修改添加基库数据 -->
    <div id="winbase" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header">
                <h3 class="modal-title">添加基库数据</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body">
              <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2" style="width:70px;">名称：</label>
                <div class="formControls col-xs-8 col-sm-9">
                  <textarea id="baseName" class="textarea txtarea_sq" style="width:350px;"></textarea>
                </div> 
              </div>
              <c:if test="${type >= 5}">
              <div id="rowCl" class="row cl" style="margin-top:15px;">
                <label class="form-label col-xs-4 col-sm-2" style="width:70px;">周期（年）：</label>
                <div class="formControls col-xs-8 col-sm-9">
                  <select id="cycle" name="isCycle" class="select" style="width:150px;">
                  <option value="">请选择</option>
                  <option value="1">1</option>
                  <option value="2">2</option>
                  <option value="2">3</option>
                </select>
                </div> 
              </div>
              </c:if>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" onclick="addTolibrary()">保存</button>
                <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
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

function addTolibrary(){
  	if($("#baseName").val() == '') {
    	layer.alert("请输入基库内容")
    	return false;
  	}
  	var cycle = "";
  	if(Type >= 5) {
  	  if($("#cycle").val() == '') {
      	layer.alert("请选择周期")
      	return false;
  	  }
  	}
    $.post(getRootPath() + "/back/system/addToLibrary",{
      id : Id,
      type : Type,
      name : $("#baseName").val(),
      cycle : $("#cycle").val(),
    },function(result) {
      if(result.status == '1') {
        layer.alert(result.map.message);
      } else {
        location.reload();
      }
    })
}

function del(id){
  $.post(getRootPath()+"/back/system/deleteFromLibrary",{
    id:id,
  },function(reuslt){
    location.reload()
  })
}
</script> 
</body>
</html>