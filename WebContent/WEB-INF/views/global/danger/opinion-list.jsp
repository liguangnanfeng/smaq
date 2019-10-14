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
body .cl .l .btn-primary{width:240px}
</style>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>行政执法检查</span>
    <span class="c-gray en">&gt;</span> <span>行政处罚</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <c:if test="${session_user.userType != 5 }">
    <div class="text-c">
      <form action="" method="post">
        <div class="dis-ib">
          <span>企业：</span>
          <input type="text" value="${companyName}" name="companyName" class="input-text mb-5 mt-5" style="width:150px;">
          </div>
        <button class="btn btn-success" type="submit">
          <i class="Hui-iconfont">&#xe665;</i> 查询
        </button>
      </form>
    </div>
    </c:if>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l">
      	<c:if test="${session_user.userType >= 6 || session_user.userName eq '港口' || session_user.userType == 5}">
      	  <a class="btn btn-primary radius ml-10" onclick="show_dialog('添加行政处罚告知书', '${ly }/global/danger/opinion-add?flag=3&flag2=2')" href="javascript:;">
            <i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 添加行政处罚告知书
          </a>
	      <a class="btn btn-primary radius  mt-5" onclick="show_dialog('添加行政（当场）处罚决定书（单位）', '${ly }/global/danger/opinion-add?flag=4&flag2=2')" href="javascript:;">
	         <i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 添加行政（当场）处罚决定书（单位）
	      </a>
	      <a class="btn btn-primary radius ml-10 mt-5" onclick="show_dialog('添加行政（当场）处罚决定书（个人）', '${ly }/global/danger/opinion-add?flag=5&flag2=2')" href="javascript:;">
	         <i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 添加行政（当场）处罚决定书（个人）
          </a>
	      <a class="btn btn-primary radius ml-10 mt-5" onclick="show_dialog('添加行政处罚决定书（单位）', '${ly }/global/danger/opinion-add?flag=6&flag2=2')" href="javascript:;">
	        <i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 添加行政处罚决定书（单位）
	      </a>
	      <a class="btn btn-primary radius ml-10 mt-5" onclick="show_dialog('添加行政处罚决定书（个人）', '${ly }/global/danger/opinion-add?flag=7&flag2=2')" href="javascript:;">
	        <i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 添加行政处罚决定书（个人）
	      </a>
      	</c:if>
        <%-- </c:if> --%>
      	
      </span>
      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span>
    </div>
    <div class="mt-20">
      <c:set var="sw" value="${fn:split('责令限期整改指令书/整改复查意见书/行政处罚告知书/行政（当场）处罚决定书（单位）/行政（当场）处罚决定书（个人）/行政处罚决定书（单位）/行政处罚决定书（个人）/现场检查记录','/') }"></c:set>
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">编号</th>
            <th width="20%">企业名称</th>
            <th width="20%">类型</th>
            <th width="20%">摘要</th>
            <th width="10%">创建时间</th>
            <th width="10%">操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环-->
          <c:forEach items="${list }" varStatus="index" var="list">
            <tr class="text-c">
            <td>${index.index+1 }</td>
            <td>${list.companyName }</td>
            <td>${sw[list.flag - 1] }</td>
            <td><lanlin:htmlToText length="200" value="${list.copy }"/></td>
            <td><fmt:formatDate value="${list.createTime }" pattern="yyyy-MM-dd"/></td>
            <td>
              <a style="text-decoration:none" onClick="detail(${list.id })" href="javascript:;">查看详情</a><br>
              <c:if test="${session_user.userType >= 6 || session_user.userName eq '港口' || session_user.userType == 5}">
              <a style="text-decoration:none" onClick="del(${list.id })" href="javascript:;">删除文书</a>
              </c:if>
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

/*详情*/
function detail(id){
  show_dialog("文书详情", getRootPath() + "/global/danger/opinion-detail?id=" + id);
}

/*删除*/
function del(id){
  $.post(getRootPath()+"/village/danger/opinion-del",{
    id:id,
  },function(reuslt){
    location.reload()
  })
}
</script> 
</body>
</html>