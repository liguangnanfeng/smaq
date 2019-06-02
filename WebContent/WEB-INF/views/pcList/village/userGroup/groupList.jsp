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
<title>安全分级管控   隐患排查治理智能化信息平台</title>
<meta name="keywords" content="安全分级管控   隐患排查治理智能化信息平台">
<meta name="description" content="安全分级管控   隐患排查治理智能化信息平台">
<style type="text/css">
body .dis-ib{margin-right:15px;}
.listem>* {
    float: left;
}
.listem em {
    font-size: 12px;
    padding: 0 5px;
    margin: 0 5px 5px 0;
    border-radius: 1px;
    background-color: #f7f7f7;
    height: 22px;
    line-height: 21px;
    box-sizing: border-box;
    font-style: unset;
}
</style>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>群组列表</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="text-c">
      <!-- <form action="" method="post"> -->
        <div class="dis-ib">
          <span>群组名称：</span>
          <input type="text" id="searchGroupNameInput" value="${groupName }" class="input-text mb-5 mt-5" style="width:150px;">
        </div>
        <button class="btn btn-success" type="button" id="toSearchGroupName">
          <i class="Hui-iconfont">&#xe665;</i> 查询
        </button>
        
        <button class="btn btn-success" id="toAddGroupUser" type="button">
          <i class="">&#xe665;</i> 新建群组
        </button>
      <!-- </form> -->
    </div>
  <div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span> 
    </div>
    <div class="mt-20" id="showList">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="40%">群组名称</th>
            <th width="20%">创建时间</th>
            <th width="10%">操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环-->
          <c:choose>
          	<c:when test="${fn:length(list)>0 }">
          		<c:forEach items="${list }" varStatus="index" var="be">
		          <tr class="text-c">
		            <td>${index.index+1 }</td>
		            <td>${be.groupName }</td>
		            <td><fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${be.createTime}" /></td>
		            <td>
		              <a style="" class="todetail"  href="javascript:;" core-id="${be.id }" core-name="${be.groupName }" core-time='<fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${be.createTime}" />'  title="查看详情">查看详情</a>
		              
		              <a style="margin-left:10px;" class="toedit" core-id="${be.id }"  href="javascript:;" title="编辑">编辑</a>
		            </td>
		          </tr>
		          </c:forEach>
          	</c:when>
          	<c:otherwise>
          		<tr class="text-c">
		            <td colspan="4">暂未设置群组</td>
		          </tr>
          	</c:otherwise>
          </c:choose>
          
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
  </div>
  <input type="hidden" id="pagesType" value="0"/>
<script type="text/javascript" src="${ly }/js/plugins/juicer/juicer.js"></script>
<script type="text/javascript" src="${ly }/js/modules/common/common.js"></script>
<script type="text/javascript" src="${ly }/js/modules/userGroup/addUserGroup.js"></script>
<script type="text/javascript">
</script> 
</body>
</html>