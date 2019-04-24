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
    <span class="c-gray en">&gt;</span> <span>${flag2 == 2 ? '行政执法系统' : '隐患排查治理系统'}</span> 
    <span class="c-gray en">&gt;</span> <span>检查文书</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <c:if test="${flag2 != 2}">
    <div id="spTab" class="btn-group" style="text-align: center;margin-bottom: 20px;margin-top:30px;">
        <a class="btn default ${flag2 == 1 ? 'btn-primary' : 'radius'}" href="${ly }/village/check-list?type=1&flag2=1">企业自查</a>
        <a class="btn default ${flag2 == 4 ? 'btn-primary' : 'radius'}" href="${ly }/village/danger/opinion-list?type=1&flag2=4">行政检查</a>
        <a class="btn default ${flag2 == 3 ? 'btn-primary' : 'radius'}" href="${ly }/village/danger/opinion-list?type=1&flag2=3">部门抽查</a>
        <%-- <a class="btn default ${flag2 == 2 ? 'btn-primary' : 'radius'}" href="${ly }/village/danger/opinion-list?type=1&flag2=2">执法检查</a> --%>
    </div>
    </c:if>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <%-- <c:if test="${session_user.userType == 5 || ((session_user.userType >=6 || session_user.userName eq '港口') && flag2 == 2) || flag2 == 3 || (session_user.userType < 6 && flag2 == 4)}">
      <span class="l">
          <a class="btn btn-primary radius ml-10" onclick='show_dialog("添加现场检查记录", "${ly }/village/danger/opinion-add?flag=8&flag2=${flag2 }")' href="javascript:;">
         	<i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 添加现场检查记录
       	  </a>   
      </span>
      </c:if> --%>
      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span>
    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">编号</th>
            <th>企业名称</th>
            <th>摘要</th>
            <th>创建时间</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环-->
          <c:forEach items="${list }" varStatus="index" var="list">
            <tr class="text-c">
            <td>${index.index+1 }</td>
            <td>${list.companyName }</td>
            <td><lanlin:htmlToText length="200" value="${list.copy }"/></td>
            <td><fmt:formatDate value="${list.createTime }" pattern="yyyy-MM-dd"/></td>
            <td>
              <c:if test="${flag2 == 3}">
                <a style="text-decoration:none" onClick='show_dialog("隐患整改意见书", "/village/check-document?checkId=${list.checkId }&flag=8");' href="javascript:;">隐患整改意见书</a><br>
              </c:if>
              <c:if test="${flag2 != 3}">
              <a style="text-decoration:none" onClick='show_dialog("现场检查记录", "/village/check-document?checkId=${list.checkId }&flag=8");' href="javascript:;">现场检查记录</a><br>
              <a style="text-decoration:none" onClick='show_dialog("整改指令书", "/village/check-document?checkId=${list.checkId }&flag=1");' href="javascript:;">整改指令书</a><br>
              </c:if>
              <a style="text-decoration:none" onClick='show_dialog("复查意见书", "/village/check-document?checkId=${list.checkId }&flag=2");' href="javascript:;">复查意见书</a><br>
              
              <c:if test="${flag2 == 3 || (session_user.userType >=6 && flag2 == 2) || (session_user.userType < 6 && flag2 == 4)}">
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
function detail(checkId,flag){
    show_dialog("行政文书详情", getRootPath() + "/village/check-document?checkId="+checkId+"&flag=" +flag);
}

/*删除*/
function del(id){
  $.post(getRootPath()+"/village/danger/opinion-del",{
    id:id
  },function(reuslt){
    location.reload()
  })
}
</script> 
</body>
</html>