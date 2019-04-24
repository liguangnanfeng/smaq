<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<%-- <%@ include file="/WEB-INF/inc/back-header.inc"%> --%>
<%@ include file="/WEB-INF/inc/print.inc"%>
<script src="${ly }/js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="${ly}/js/H-ui.admin_v3.0/lib/layer/2.4/layer.js"></script>
<title>风险分级管控 隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style type="text/css">
body .dis-ib {margin-right: 15px; }
.btn-group{width:100%;text-align: center;margin-bottom:30px;}
.btn-group .btn{height:38px;line-height:38px;padding:0 25px;font-size:14px;}
</style>
<script type="text/javascript">

$(function(){
	  $(".btn-group .btn").click(function(){
	    if($(this).hasClass("btn-default")){
	      $(this).removeClass("btn-default");
	      $(this).addClass("btn-primary");
	    }
	  });
	});
</script>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 
		<span>首页</span> <span class="c-gray en">&gt;</span> 
		<span>重大危险源源长制</span> <span class="c-gray en">&gt;</span>
		<span>管控措施</span> <span class="c-gray en">&gt;</span> 
		<span>设备设施管理</span> 
		<a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新"> 
			<i class="Hui-iconfont">&#xe68f;</i>
		</a>
	</nav>
	<div class="page-container">
		<div id="spTab" class="btn-group" style="text-align: center;margin-bottom: 20px;margin-top:20px;">
	        <a class="btn default ${type==1 ? 'btn-primary' : 'radius'}" href="${ly }/company/source/equipment?type=1">主要设备</a>
	        <a class="btn default ${type==2 ? 'btn-primary' : 'radius'}" href="${ly }/company/source/equipment?type=2">特种设备</a>
	        <a class="btn default ${type==3 ? 'btn-primary' : 'radius'}" href="${ly }/company/source/equipment?type=3">安全设施</a>
		</div>
		<c:if test="${type == 1 || type == 3 }">
		<div class="mt-20">
			<table class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th width="5%">序号</th>
						<th width="20%">名称</th>
						<th width="15%">维护保养检测时间</th>
						<th width="20%">设备状态</th>
						<th width="15%">主体</th>
						<th width="15%">责任人</th>
						<th width="10%">操作</th>
					</tr>
				</thead>
				<tbody>
					<!-- 循环-->
					<c:forEach items="${showList }" varStatus="index" var="equip">
						<tr class="text-c">
							<td>${index.index + 1 }</td>
							<td>${equip.name }</td>
							<td>${equip.maintenance}</td>
							<td id="${equip.id }" class="text-c">
								<c:if test="${equip.equipmentStatus == 1}">正常</c:if>
								<c:if test="${equip.equipmentStatus == 0}">不正常</c:if>
								<c:if test="${equip.equipmentStatus == null}"></c:if>
							</td>
							<td>${equip.dpname }</td>
							<td>${equip.responsible }</td>
							<td><a style="text-decoration: none"
								onClick="show_tab('编辑设备', '${ly }/company/source/equipment-edit?type=${type }&id=${equip.id}')"
								href="javascript:;" title="编辑">查看/编辑</a> 
								<%--<a style="text-decoration: none" onClick="del(${equip.id})"
								href="javascript:;" title="删除">删除</a> --%>
							</td>
						</tr>
					</c:forEach>
					<!-- 循环结束 -->
				</tbody>
			</table>
		</div>
		</c:if>
		<c:if test="${type == 2 }">
		<div class="mt-20">
			<table class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th width="5%">序号</th>
						<th width="15%">名称</th>
						<th width="10%">检测时间</th>
						<th width="15%">维护保养检测时间</th>
						<th width="20%">设备状态</th>
						<th width="15%">主体</th>
						<th width="10%">责任人</th>
						<th width="10%">操作</th>
					</tr>
				</thead>
				<tbody>
					<!-- 循环-->
					<c:forEach items="${showList }" varStatus="index" var="equip">
						<tr class="text-c">
							<td>${index.index + 1 }</td>
							<td>${equip.name }</td>
							<td>${equip.detectiontime }</td>
							<td>${equip.maintenance}</td>
							<td id="${equip.id }" class="text-c">
								<c:if test="${equip.equipmentStatus == 1}">正常</c:if>
								<c:if test="${equip.equipmentStatus == 0}">不正常</c:if>
								<c:if test="${equip.equipmentStatus == null}"></c:if>
							</td>
							<td>${equip.dpname }</td>
							<td>${equip.responsible }</td>
							<td><a style="text-decoration: none"
								onClick="show_tab('编辑设备', '${ly }/company/source/equipment-edit?type=${type }&id=${equip.id}')"
								href="javascript:;" title="编辑">查看/编辑</a> 
								<%--<a style="text-decoration: none" onClick="del(${equip.id})"
								href="javascript:;" title="删除">删除</a> --%>
							</td>
						</tr>
					</c:forEach>
					<!-- 循环结束 -->
				</tbody>
			</table>
		</div>
		</c:if>
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
/*删除*/
var type = ${type};
function del(id){
  $.post(getRootPath()+"/company/source/deleteEquipment",{
    id:id,
    type:type
  },function(reuslt){
    location.reload()
  })
}



</script>
</body>
</html>