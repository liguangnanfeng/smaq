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
.tabBar {border-bottom:0px;text-align:center;background:#fff;height:42px;line-height:42px;margin-top:28px;}
.tabBar a {height:42px;line-height:42px;background-color:#E5F3F7;border:0px;font-size:14px;color:#687E99;cursor: pointer;display: inline-block;float: none;font-weight: bold;padding: 0 25px}
.tabBar a.current{background-color:#008DFF;color: #fff;border:0px;}
.tabCon {display: none;padding:0;border:0px;}
</style>
<script type="text/javascript">
$(function(){
  $.Huitab("#tab_demo .tabBar span","#tab_demo .tabCon","current","click","0")
});
</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>企业信息库</span> 
    <span class="c-gray en">&gt;</span> <span>企业信息汇总</span> 
    <span class="c-gray en">&gt;</span> <span>职业卫生检测</span>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="tabBar clearfix">
    <a class="current" style="border-radius:5px 0 0 5px;" href="JavaScript:void(0)">职业卫生检测</a>
    <a style="border-radius:0 5px 5px 0;" href="${ly}/global/warning/examination-list?isTime=${isTime}">职业健康体检</a>
  </div>
    <div class="page-container">
      <div class="text-c">
        <form action="${ly}/global/warning/detection-list" method="post">
          <div class="dis-ib">
            <span>所属企业：</span>
            <input type="text" value="${companyName }" name="companyName" class="input-text mb-5 mt-5" style="width:230px;">
          </div>
          <div class="dis-ib">
            <span>检测类别：</span>
            <select name="deType" class="sel_area">
              <option value="">全部</option>
              <option value="定期检测"  <c:if test="${'定期检测'==deType}">selected</c:if>>定期检测</option>
              <option value="委托检测"  <c:if test="${'委托检测'==deType}">selected</c:if>>委托检测</option>
              <option value="监督检测"  <c:if test="${'监督检测'==deType}">selected</c:if>>监督检测</option>
            </select>
          </div> 
          <div class="dis-ib">
            <span>到期时间：</span>
            <span class="select-box inline">
            <select name="isTime" class="select">
              <option value="">全部</option>
              <option value="1"  <c:if test="${1==isTime}">selected</c:if>>1个月内到期</option>
              <option value="2"  <c:if test="${2==isTime}">selected</c:if>>2个月内到期</option>
              <option value="3"  <c:if test="${3==isTime}">selected</c:if>>3个月内到期</option>
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
		<span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span>
	  </div>
      <div class="mt-20">
        <table class="table table-border table-bordered table-bg table-hover table-sort">
          <thead>
            <tr class="text-c">
              <th>序号</th>
              <th>所属企业</th>
              <th>检测类别</th>
              <th>上次检测时间 </th>
              <th>到期时间</th>
              <th>监测点</th>
              <th>检测职业病危害因素</th>
              <th>检测结果</th>
              <th>检测单位</th>
              <th>检测编号</th>
              <th>备案情况及备案表</th>
              <th>备注</th>
            </tr>
          </thead>
          <tbody>
            <!-- 循环-->
            <c:forEach items="${list }" varStatus="index" var="de">
            <tr class="text-c">
              <td>${index.index+1 }</td>
              <td>${de.companyName }</td>
              <td>${de.type }</td>
              <td>${de.reportTime }</td>
              <td>${de.expirationTime}</td>
              <td>${de.jcd}</td>
              <td>${de.whys}</td>
              <td>${de.jcjg}</td>
              <td>${de.jcdw}</td>
              <td>${de.jcbh}</td>
              <td>${de.ba}</td>
              <td>${de.remark}</td>
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
    searching: false,
    ordering:  false,
    "aoColumnDefs": [
    ]
    });
});
</script> 
</body>
</html>