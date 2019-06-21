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
.clearfix:after{content:"\20";display:block;height:0;clear:both;visibility:hidden}
.clearfix{zoom:1}
.tabBar {border-bottom: 2px solid #eee}
.tabBar span {background-color: #fff;cursor: pointer;display: inline-block;float: left;font-weight: bold;height: 35px;line-height: 35px;padding: 0 15px}
.tabBar span.current{background-color:#0a6999;color: #fff}
.tabCon {display: none}
</style>
<script type="text/javascript">
$(function() {
  $.Huitab("#tab_demo .tabBar span","#tab_demo .tabCon","current","click","0");
});
</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>隐患排查治理系统</span> 
    <span class="c-gray en">&gt;</span> <span>较大危险因素辨识与防范指导手册</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="mt-20">
      <div id="tab_demo" class="HuiTab">
        <div class="tabCon">
          <table class="table table-border table-bordered table-bg table-hover table-sort">
            <thead>
              <tr class="text-c">
                <th width="5%">序号</th>
                <th width="60%">资料名称</th>
                <th width="35%">操作</th>
              </tr>
            </thead>
            <tbody>
              <!-- 循环-->
              <c:forEach items="${list }" varStatus="index" var="t1">
              <tr class="text-c">
                <td>${index.index+1 }</td>
                <td>${t1.name }</td>
                <td>
                  <c:if test="${!empty t1.url}">
                  <a style="text-decoration:none"  href="${ly}/common/download?filename=${t1.name}&fileurl=${t1.url}" title="查看资料">查看资料</a>
                  </c:if>
                </td>
              </tr>
              </c:forEach>
              <!-- 循环结束 -->
            </tbody>
          </table>
        </div>
        
      </div>
      
    </div>
  </div>
  
</body>
</html>