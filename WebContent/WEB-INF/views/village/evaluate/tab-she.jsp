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

<link type="text/css" rel="stylesheet" href="${ly }/js/kindeditor/themes/default/default.css" />
	<script type="text/javascript" src="${ly }/js/kindeditor/kindeditor-min.js"></script>
	<script type="text/javascript" src="${ly }/js/kindeditor/lang/zh_CN.js"></script>
<style type="text/css">
body .dis-ib{margin-right:15px;}
.div_btzl{width:100%;font-size:16px;font-weight:bold;margin-bottom:10px;}
.p_zl{width:100%;text-indent:2em;margin:0;padding:0;margin-bottom:8px;}
</style>
</head>
<body>
  <nav class="breadcrumb">
        <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
        <span class="c-gray en">&gt;</span> <span>安全评价档案</span>
        <span class="c-gray en">&gt;</span> <span>安全设施“三同时”</span>
        <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
    </nav>
    <div class="page-container">
        <div class="text-c">
          <form action="${ly }/company/evaluate/tab-she" method="post">
            <div class="dis-ib">
              <span>资料名称：</span>
              <input type="text" value="${seName}" id="seName" name="seName" class="input-text mb-5 mt-5" style="width:150px;">
            </div>
            <div class="dis-ib">
              <span>企业名称：</span>
              <input type="text" value="${companyName}" id="companyName" name="companyName" class="input-text mb-5 mt-5" style="width:150px;">
            </div>
            <button class="btn btn-success" type="submit">
              <i class="Hui-iconfont">&#xe665;</i> 查询
            </button>
          </form>
        </div>
        <div class="mt-20">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
                <thead>
                    <tr class="text-c">
                        <th>序号</th>
                        <th>资料名称</th>
                        <th>上传时间</th>
                        <th>企业名称</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- 循环-->
                   	<c:forEach items="${list }" varStatus="index" var="list">
                    <tr class="text-c">
                        <td>${index.index+1 }</td>
                        <td>${list.name}</td>
                        <td><fmt:formatDate value="${list.createTime}" pattern="yyyy-MM-dd"/></td>
                        <td>${list.companyName }</td>
                        <td>
                        <c:if test="${!empty list.url}">
                          <a style="text-decoration:none" href="${ly}/common/download?filename=${list.name}&fileurl=${list.url}" title="查看资料" target="_blank">查看资料</a>
                        </c:if>
                        </td>
                    </tr>
                    </c:forEach>
                    <!-- 循环结束 -->
                </tbody>
            </table>
        </div>
        <div class="mt-40">
            <img alt="" src="${ly }/images/yan.jpg" />
        </div>
  </div>
  <script type="text/javascript">
    $(function() {
        $('.table-sort').dataTable({
            "aaSorting": [
                [0, "asc"]
            ], //默认第几个排序
            "bStateSave": false, //状态保存
            searching: false,
            ordering: false,
            "aoColumnDefs": []
        });
    });
    </script>
</body>
</html>