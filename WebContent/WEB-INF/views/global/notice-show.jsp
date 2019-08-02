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
.panel-default{display: inline-block;border:0px;}
body,html{background:#EBEEF3}
.title_xw{width:100%;border-bottom:0px solid #F6F6F6;font-weight:bold;font-size:14px;}
.one_notice{width:100%;margin-top:0px;}
.text-overflow{display: inline-block;width:70%;line-height:25px;color:#7C7C7C;margin-right:0px;}
.text-overflow i{height:3px;width:3px;background:#7C7C7C;border-radius:50%;display:inline-block;vertical-align: middle;margin-right:5px;}
.text-overflow:hover a{color:#5889D8;text-decoration: underline;}
.text-overflow:hover i{background:#5889D8;}
.text-riqi{float:right;color:#AEAEAE;line-height:25px;}
body .iconxw{transform: rotate(-90deg);float:right;margin-right:-5px;margin-top: -3px;}
</style>
<script type="text/javascript">
$(function(){
  
  });
  
</script>
</head>
<body>
  <div class="panel panel-default" style="width:95%;margin-left:10px;float:left;padding:15px;margin-top:20px;">
    <div class="panel-body" style="padding:0px;">
      <div class="page-container">
        <div class="titi_gg">${n.title }</div>
        <div class="titi_gg2">发布时间：${n.time }&nbsp;&nbsp;来源：${n.source }</div>
        <div class="notice-detail">${n.content}</div>
      </div>
    </div>
  </div>
</body>
</html>