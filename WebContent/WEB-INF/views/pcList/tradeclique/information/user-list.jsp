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
<script type="text/javascript">
/* 弹窗管控信息添加 */
function add(){
  $("#win-add").modal("show")
}
var villageId = '${m.villageId}';
$(function() {
  if($("#townId").length > 0) {
    $("#townId").change(function() {
      $("#villageId").empty().append("<option value=''>全部</option>");
      if($(this).val() != '') {
        $.post(getRootPath() + "/village/getVillageByTown", {
          townId : $("#townId").val()
        },function(result) {
          var list = result.map.list;
          $.each(list, function(i, item) {
            $("#villageId").append("<option value='"+item.userId+"'>"+item.name+"</option>");
          })
          if(villageId != '') {
            $("#villageId").val(villageId);
            villageId = '';
          }
        })
      }
    })
    
    $("#townId").change();
  }
})
</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>企业数据库</span> 
    <span class="c-gray en">&gt;</span> <span>组织架构</span> 
    <span class="c-gray en">&gt;</span> <span>部门人员</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="text-c">
      <form action="${ly }/tradeclique/information/user-list" method="post">
        <div class="dis-ib">
          <span>企业名称：</span>
          <input type="text" name="companyName" value="${companyName }" class="input-text mb-5 mt-5" style="width:150px;">
        </div>
        <button class="btn btn-success" type="submit">
          <i class="Hui-iconfont">&#xe665;</i> 查询
        </button>
      </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="r">共有数据：<strong>${total }</strong> 条</span>
    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort tab-ndan">
        <thead>
          <tr class="text-c">
          	<th width="10%">序号</th>
            <th width="40%">企业名称</th>
            <th width="20%">人员数量</th>
            <th width="40%">操作</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${branches }" var="br" varStatus="index">
          <tr class="text-c">
          	<td>${index.index+1 }</td>
            <td>${br.name }</td>
            <td>${br.count }</td>
			<td>
       			<a href="${ly }/tradeclique/information/user-details?userId=${br.userId}">查看详情</a>
			</td>
          </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</body>
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

</script> 
</html>
