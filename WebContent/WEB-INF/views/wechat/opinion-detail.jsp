<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="format-detection" content="telephone=no" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/inc/back-header.inc"%>
<link type="text/css" href="${ly }/styles/all.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${ly}/styles/print.css" />
<title>安全分级管控 隐患排查治理智能化信息平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style type="text/css">
.tabBar {
  margin-top: 5px;
}

.tabBar a {
  width: 32%;
  overflow: hidden;
  padding: 0;
  font-size: 0.9em;
}

.row {
  position: fixed;
  top: 45px;
  left: 0;
  width: 100%;
  background: #fff;
  padding-bottom: 5px;
}
</style>
</head>
<body>
  <div class="container">
    <div class="top_all">
      <div class="top_main">
        <a onclick="window.androidandiosJS.finishButton()" class="back_top"><img alt="" src="${ly }/images/weixin/fanhuib.png" /></a> <font class="top_title">文书详情</font>
      </div>
    </div>
    <div class="allh1"></div>
    <!-- 原文件信息 -->
    <c:if test="${document.flag == 1 || document.flag == 2 || document.flag == 8}">
      <div class="allh1"></div>
      <div class="row cl mb-20">
        <div class="tabBar clearfix">
          <c:if test="${document.flag == 8}">
            <a class="current" href='${ly }/api/village/xlb/A024?checkId=${document.checkId}&flag=8'>${document.flag2 == 3 ? '隐患整改意见书' : '现场检查记录'}</a>
          </c:if>
          <c:if test="${document.flag != 8}">
            <a href='${ly }/api/village/xlb/A024?checkId=${document.checkId}&flag=8'>现场检查记录</a>
          </c:if>
          <c:if test="${document.flag2 != 3}">
            <a ${document.flag == 1 ? 'class="current"' : ''} href='${ly }/api/village/xlb/A024?checkId=${document.checkId}&flag=1'>责令限期整改意见书</a>
          </c:if>
          <a ${document.flag == 2 ? 'class="current"' : ''} href='${ly }/api/village/xlb/A024?checkId=${document.checkId}&flag=2'>整改复查意见书</a>
        </div>
      </div>

    </c:if>
    <div id="div_container">
      <div style="width: 150mm; margin: auto; padding: 50px 0 0 0;">${document.copy}</div>
    </div>
    <!-- 底部操作 -->
    <div class="allh1"></div>
  </div>
</body>
</html>
