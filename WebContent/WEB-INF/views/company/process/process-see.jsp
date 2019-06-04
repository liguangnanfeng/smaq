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
</head>

<body>
<nav class="breadcrumb">
  <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
  <span class="c-gray en">&gt;</span> <span>企业基础信息</span>
  <span class="c-gray en">&gt;</span> <span>生产工艺流程图</span>
  <span class="c-gray en">&gt;</span> <span>查看文档</span>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href=" " title="刷新">
    <i class="Hui-iconfont">&#xe68f;</i>
  </a>
</nav>
<p>${list}</p>
  <iframe src="${list}" width='100%' height='100%' frameborder='1'>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    </head>
  </iframe>
</body>
</html>