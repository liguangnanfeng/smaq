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
.title_xw{width:100%;font-weight:bold;font-size:15px;line-height: 32px;}
.one_notice{width:100%;margin-top:0px;}
.text-overflow{display: inline-block;width:70%;line-height:25px;color:#7C7C7C;margin-right:0px;}
.text-overflow i{height:3px;width:3px;background:#7C7C7C;border-radius:50%;display:inline-block;vertical-align: middle;margin-right:5px;}
.text-overflow:hover a{color:#5889D8;text-decoration: underline;}
.text-overflow:hover i{background:#5889D8;}
.text-riqi{float:right;color:#AEAEAE;line-height:25px;}
body .lianj_com img{margin-right:6px;margin-left:6px;}
.clearfix:after{content:"\20";display:block;height:0;clear:both;visibility:hidden}
.clearfix{zoom:1}
.tabBar {border-bottom: 2px solid #F6F6F6}
.tabBar span {background-color: #fff;cursor: pointer;font-size:15px;display: inline-block;float: left;font-weight: bold;height: 40px;line-height: 40px;padding: 0 35px}
.tabBar span.current{background-color: #fff;color: #FF9900;border:0px;border-bottom:2px solid #FF9900}
.tabCon {display: none;border:0px;padding:0;}
.one_tx{margin-top:20px;width:31%;margin-right:2%}
.bt_zd{background:#DF2E30;padding:0 9px;color:#fff;font-size:12px;margin-left:10px;border-radius:5px;height:25px;line-height:25px;display: inline-block;}
.div_zhe{width:100%;}
.change_zhe{width:100%;height:35px;position: relative;}
body .iconxw{transform: rotate(-90deg);float:right;margin-right:-5px;margin-top: -3px;}
</style>
<script type="text/javascript">
</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>企业信息库</span> 
    <span class="c-gray en">&gt;</span> <span>企业统计</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
      <div class="div_tx text-c div-chart">
        <div style="display: inline-block;width:100%;text-align:center;">
          <div id="container" style="float:left;width:850px;height:500px"></div>
        </div>
        <div style="display: inline-block;width:100%;height:15px;background:#EBEEF3"></div>
        <div style="display: inline-block;width:100%;text-align:center;">
          <div id="container4" style="float:left;width:950px;margin-top:40px"></div>
        </div>
        <div style="display: inline-block;width:100%;height:15px;background:#EBEEF3"></div>
        <div style="display: inline-block;width:100%;text-align:center;">
          <div id="container2" style="width:900px;height:500px;display: inline-block;margin-top:40px"></div>
        </div>
      </div>
</body>
<script type="text/javascript" src="/js/fxgk/industry.js?v=180920"></script>
<script type="text/javascript" src="/js/fxgk/industry2-pie.js"></script>
</html>