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
.btn-group .btn{height: 34px;line-height: 34px;padding: 0 25px;}
</style>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>评价评估系统</span> 
    <span class="c-gray en">&gt;</span> <span>评价评估档案</span>
    <span class="c-gray en">&gt;</span> <span>各类评价评估</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div id="spTab" class="btn-group" style="text-align: center;margin-bottom: 20px;">
        <a class="btn default radius" href="${ly }/company/evaluate/evaluation-list?flag=0">全部</a>
        <a class="btn default radius" href="${ly }/company/evaluate/evaluation-list?flag=1">安全生产类</a>
        <a class="btn default radius" href="${ly }/company/evaluate/evaluation-list?flag=2">职业健康类</a>
    </div>
    <!-- 判断全部显示 -->
    <%-- <div class="list_ping">
      <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="10%">所属分类</th>
            <th width="10%">评价类型</th>
            <th width="20%">评价项目名称</th>
            <th width="10%">编制日期</th>
            <th width="10%">有效期</th>
            <th width="10%">编制单位</th>
            <th width="25%">报告文档</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环-->
          <c:forEach items="${list }" varStatus="index" var="eva">
          <tr class="text-c">
            <td>${index.index+1 }</td>
            <td>xx</td>
            <td>
                <c:if test="${eva.flag==1 && eva.type==1}">预评价</c:if>
                <c:if test="${eva.flag==1 && eva.type==2}">验收评价</c:if>
                <c:if test="${eva.flag==1 && eva.type==3}">现状评价</c:if>
                <c:if test="${eva.flag==1 && eva.type==4}">危化品经营证评价</c:if>
                <c:if test="${eva.flag==1 && eva.type==5}">危化品生产证评价</c:if>
                <c:if test="${eva.flag==1 && eva.type==6}">危化品使用证评价</c:if>
                <c:if test="${eva.flag==1 && eva.type==7}">重大危险源评估</c:if>
                <c:if test="${eva.flag==1 && eva.type==8}">安全条件论证报告</c:if>
                <c:if test="${eva.flag==1 && eva.type==9}">安全条件综合分析报告</c:if>
                <c:if test="${eva.flag==2 && eva.type==1}">预评价</c:if>
                <c:if test="${eva.flag==2 && eva.type==2}">控效评价</c:if>
                <c:if test="${eva.flag==2 && eva.type==3}">现状评价</c:if>
            </td>
            <td>${eva.reportName }</td>
            <td>${eva.reportTime }</td>
            <td>${eva.expirationTime }</td>
            <td>${eva.unit }</td>
            <td>${eva.docName }</td>
          </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
    
    </div> --%>
    
    
    <!-- 判断评价评估显示 -->
    <div class="list_other">
    <div class="text-c">
      <form action="${ly }/company/evaluate/evaluation-list?flag=${flag}" method="post">
        <div class="dis-ib">
          <span>评价报告名称：</span>
          <input type="text" value="${reportName }" id="evaName" name="reportName" class="input-text mb-5 mt-5" style="width:150px;">
        </div>
        <div class="dis-ib">
          <span>评价类型：</span>
          <span class="select-box inline">
            <select id="type" class="select" name="evaType" style="width:170px;" ></select>
          </span>
        </div>
        <div class="dis-ib">
          <span>到期时间：</span>
          <span class="select-box inline">
          <select name="isTime" class="select">
            <option value="">全部</option>
            <option value="6" <c:if test="${6==isTime}">selected</c:if>>6个月内到期</option>
            <option value="0" <c:if test="${0==isTime}">selected</c:if>>已过期</option>
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
        <c:if test="${flag != 0 }">
        <a class="btn btn-primary radius" data-title="添加评价评估" data-href="${ly }/company/evaluate/evaluation-add?flag=${flag}" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 添加评价评估</a>
        </c:if>
      </span>
      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span> 
    </div>
    <c:set var="wid1" value="${fn:split('8/10/10','/') }" />
    <c:set var="wid2" value="${fn:split('22/25/25','/') }" />
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <c:if test="${flag == 0 }">
            <th width="7%">所属分类</th>
            </c:if>
            <th width="10%">评价类型</th>
            <th width="20%">评价项目名称</th>
            <th width="${wid1[flag]}%">编制日期</th>
            <th width="${wid1[flag]}%">有效期</th>
            <th width="10%">编制单位</th>
            <th width="${wid2[flag]}%">报告文档</th>
            <th width="10%">操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环-->
          <c:forEach items="${list }" varStatus="index" var="eva">
          <tr class="text-c">
            <td>${index.index+1 }</td>
            <c:if test="${flag == 0 }">
            <td>
              <c:if test="${eva.flag==1}">安全生产类</c:if>
              <c:if test="${eva.flag==2}">职业健康类</c:if>
            </td>
            </c:if>
            <td>
            	<c:if test="${eva.flag==1 && eva.type==1}">预评价</c:if>
            	<c:if test="${eva.flag==1 && eva.type==2}">验收评价</c:if>
            	<c:if test="${eva.flag==1 && eva.type==3}">现状评价</c:if>
            	<c:if test="${eva.flag==1 && eva.type==4}">危化品经营证评价</c:if>
            	<c:if test="${eva.flag==1 && eva.type==5}">危化品生产证评价</c:if>
            	<c:if test="${eva.flag==1 && eva.type==6}">危化品使用证评价</c:if>
            	<c:if test="${eva.flag==1 && eva.type==7}">重大危险源评估</c:if>
            	<c:if test="${eva.flag==1 && eva.type==8}">安全条件论证报告</c:if>
            	<c:if test="${eva.flag==1 && eva.type==9}">安全条件综合分析报告</c:if>
            	<c:if test="${eva.flag==2 && eva.type==1}">预评价</c:if>
            	<c:if test="${eva.flag==2 && eva.type==2}">控效评价</c:if>
            	<c:if test="${eva.flag==2 && eva.type==3}">现状评价</c:if>
           	</td>
            <td>${eva.reportName }</td>
            <td>${eva.reportTime }</td>
            <td>${eva.expirationTime }</td>
            <td>${eva.unit }</td>
            <td>${eva.docName }</td>
            <td>
              <a style="text-decoration:none" onClick="admin_edit(${eva.id})" href="javascript:;" title="编辑">查看/编辑</a>
              <c:if test="${eva.docUrl!=''}">
              	<a style="text-decoration:none" href="${ly}/common/download?filename=${eva.docName}&fileurl=${eva.docUrl}" title="查看文档" target="_blank">查看文档</a>
              </c:if>
              <a style="text-decoration:none" onClick="del(${eva.id})" href="javascript:;" title="删除">删除</a>
            </td>
          </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
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
/*编辑*/
function admin_edit(id){
  show_tab("编辑评价评估", getRootPath() + "/company/evaluate/evaluation-edit?flag=${flag}&id=" + id);
}

/*删除*/
function del(id){
  layer.confirm("确认要删除吗？",function(r){
    $.post(getRootPath() + "/company/evaluate/deleteEva", {
      id : id,
    },function(result) {
      $("form").submit();
    });
  });
}

function getUrlParam(name) {
  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
  var r = window.location.search.substr(1).match(reg); //匹配目标参数
  if (r != null) return unescape(r[2]);
  return null; //返回参数值
}

$(function() {
  var i = getUrlParam('flag');
  if (i == undefined || i == null) {
      i = '0';
  }
  if (i == '0' || i == '1') {
      $("#type").append("<option value=''>" + "请选择" + "</option>");
      $("#type").append("<option value='" + 1 + "'>" + "预评价" + "</option>");
      $("#type").append("<option value='" + 2 + "'>" + "验收评价" + "</option>");
      $("#type").append("<option value='" + 3 + "'>" + "现状评价" + "</option>");
      $("#type").append("<option value='" + 4 + "'>" + "危化品经营证评价" + "</option>");
      $("#type").append("<option value='" + 5 + "'>" + "危化品生产证评价" + "</option>");
      $("#type").append("<option value='" + 6 + "'>" + "危化品使用证评价" + "</option>");
      $("#type").append("<option value='" + 7 + "'>" + "重大危险源评估" + "</option>");
      $("#type").append("<option value='" + 8 + "'>" + "安全条件论证报告" + "</option>");
      $("#type").append("<option value='" + 9 + "'>" + "安全条件综合分析报告" + "</option>");
  } else {
   	  $("#type").append("<option value=''>" + "请选择" + "</option>");
	  $("#type").append("<option value='" + 1 + "'>" + "预评价" + "</option>");
      $("#type").append("<option value='" + 2 + "'>" + "控效评价" + "</option>");
      $("#type").append("<option value='" + 3 + "'>" + "现状评价" + "</option>");
  }
  $("#type").val('${evaType}');
  $("#spTab").find('a').eq(i).addClass("btn-primary").removeClass("default");
});
</script>
</body>
</html>