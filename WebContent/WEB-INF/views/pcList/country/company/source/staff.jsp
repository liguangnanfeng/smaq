<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<%@ include file="/WEB-INF/inc/back-header.inc"%>
<title>风险分级管控 隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<script type="text/javascript">
$(function() {
  $('.table-sort').dataTable({
    "retrieve" : true,
    "bSort" : false,
    'ordering' : false,
    "bStateSave" : false,// 状态保存
    "searching" : false,
    "aoColumnDefs" : [{
      "orderable" : false,
    }]
  });
});
</script>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 
		<span class="c-gray en">&gt;</span> 重大危险源源长制 
		<span class="c-gray en">&gt;</span> 管控措施 
		<span class="c-gray en">&gt;</span> 人员管理 
		<a class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"> 
			<i class="Hui-iconfont">&#xe68f;</i>
		</a>
	<a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:history.back(-1)" title="返回">返回</a>
	</nav>
	<div class="page-container">
		<div class="text-c div-search">
			<form action="" method="post">
				<span>姓名：</span><input type="text" name="name" value="" placeholder="姓名" style="width: 150px" class="input-text"> 
				<span>所在公司：</span> 
				<span class="select-box inline"> 
					<select name="cid" class="select" id="cid">
						<option value=""> 所在公司 </option>
						<c:forEach items="${companyL }" var="be">
							<option value="${be.id }">${be.name } </option>
						</c:forEach>
					</select>
				</span> 
				<span class="select-box inline"> 
					<select name="dpid" class="select" id="dpid">
						<option value=""> 所在车间</option>
					</select>
				</span>
				<span class="select-box inline"> 
					<select name="post" class="select" id="post">
						<option value="">所在岗位/部位</option>
					</select>
				</span>
				<button name="" id="" class="btn btn-success" type="submit">
					<i class="Hui-iconfont">&#xe665;</i> 查询
				</button>
			</form>
		</div>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l">
				<button class="btn btn-primary radius" onclick="showadd('')">
					<i class="Hui-iconfont">&#xe600;</i> 添加人员
				</button>
			</span> 
			<span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span>
		</div>
		<div class="mt-20">
			<table class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th>序号</th>
						<th>姓名</th>
						<th>所属部位/车间</th>
						<th>职务</th>
						<th>联系方式</th>
						<th>备注</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list }" var="be" varStatus="index">
						<tr class="text-c">
							<td>${index.index + 1}</td>
							<td>${be.name }</td>
							<td>${be.dpname }${be.post }</td>
							<td>${be.position }</td>
							<td>${be.mobile }</td>
							<td>${be.remark }</td>
							<td><a style="text-decoration: none" class="ml-5"
								onClick="showadd(${be.id})" href="javascript:;" title="编辑">编辑</a>
								<a style="text-decoration: none" class="ml-5"
								onClick="del_(${be.id})" href="javascript:;" title="删除">删除</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
<script type="text/javascript">
var dep1= ${dep1};
var dep2= ${dep2};
$(function() {
  $("#cid").change(function() {
    loadDep1($(this).val());
  })
  $("#dpid").change(function() {
    loadDep2($(this).val());
  })
})

function loadDep1(c) {
  $("#dpid").empty().append('<option value="">所在车间</option>');
  if(c == '') {
    return false;
  }
  $.each(dep1, function(i, item) {
    if(item.cid == c) {
      $("#dpid").append('<option value="' + item.id + '">' + item.name + '</option>');
    }
  })
}

function loadDep2(dp) {
  $("#post").empty().append('<option value="">所在岗位/部位</option>');
  if(dp == '') {
	return false;
  }
  $.each(dep2, function(i, item) {
    if(item.pid == dp) {
	  $("#post").append('<option value="' + item.name + '">' + item.name + '</option>');
	}
  })
}

function showadd(id){
  show_dialog("部门人员", '/company/source/user-add?id=' + id);
}

function del_(id) {
  layer.confirm("确认要删除该数据么？", function(index) {
    $.post("/company/source/user-save", {
      id : id,
      del : 1
    },function(result) {
      layer.close(index);
      location.reload();
    })
  });
}
</script>
</html>