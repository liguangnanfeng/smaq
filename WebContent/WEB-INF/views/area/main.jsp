<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="apple-mobile-web-app-capable" content="yes">  
<meta name="mobile-web-app-capable" content="yes"> 
<meta name="viewport" content="width=1400, initial-scale=1, maximum-scale=1, user-scalable=yes, minimal-ui">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<%@ include file="/WEB-INF/inc/back-header.inc"%>
<title>安全生产信息智能化管理平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style type="text/css">
dt a:hover{text-decoration: none;}
.Hui-iconfont{font-size:15px;}
</style>
</head>
<body>
  <%@ include file="top.jsp"%>
  <%@ include file="left.jsp"%>
  <!-- 中间主页 -->
  <div class="dislpayArrow hidden-xs">
    <a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a>
  </div>
  <section class="Hui-article-box">
    <div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
      <div class="Hui-tabNav-wp">
        <ul id="min_title_list" class="acrossTab cl">
          <li class="active">
            <span title="首页" data-href="${ly }/district/welcome">首页</span>
            <em></em>
          </li>
        </ul>
      </div>
      <div class="Hui-tabNav-more btn-group">
        <a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;">
          <i class="Hui-iconfont">&#xe6d4;</i>
        </a>
        <a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;">
          <i class="Hui-iconfont">&#xe6d7;</i>
        </a>
      </div>
    </div>
    <div id="iframe_box" class="Hui-article">
      <div class="show_iframe">
        <div style="display:none" class="loading"></div>
        <iframe scrolling="yes" frameborder="0" src="${ly }/district/welcome"></iframe>
      </div>
    </div>
  </section>
  <div class="contextMenu" id="Huiadminmenu">
    <ul>
      <li id="closethis">关闭当前</li>
      <li id="closeothers">关闭其他</li>
      <li id="closeall">关闭全部</li>
    </ul>
  </div>
</body>
</html>