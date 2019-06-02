<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<%@ include file="/WEB-INF/inc/print.inc"%>
<link rel="stylesheet" type="text/css" href="${ly}/styles/print.css" />
<title>安全分级管控 隐患排查治理智能化信息平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style type="text/css">
</style>
<script type="text/javascript">
function pr_() {
  $("#div_container").jqprint();
}
</script>
</head>
<body>
    <div class="page-container" style="width:150mm;margin:auto;padding:10px 0 0 0;">
        <div class="row cl row_ac">
            <c:choose>
                <c:when test="${document.flag >2 && document.flag < 8}">
                <button class="btn radius" type="button">
                    <i class="Hui-iconfont mr-10">&#xe66b;</i>返回列表
                </button>
                <!-- <button class="btn radius" type="button" >
                  <i class="Hui-iconfont mr-10">&#xe66b;</i>返回文书
                </button> -->
                </c:when>
                <c:otherwise>
                <button class="btn radius" type="button" >
                    <i class="Hui-iconfont mr-10">&#xe66b;</i>返回列表
                </button>
                <%-- <c:if test="${document.flag2==2 }">
                  <button class="btn radius" type="button" >
                      <i class="Hui-iconfont mr-10">&#xe66b;</i>返回文书
                  </button>
                </c:if>
                <c:if test="${document.flag2!=2 }">
                  <button class="btn radius" type="button" >
                    <i class="Hui-iconfont mr-10">&#xe66b;</i>返回文书
                  </button>
                </c:if> --%>
                </c:otherwise>
            </c:choose>
            
        </div>
        
        <c:if test="${document.flag == 1 || document.flag == 2 || document.flag == 8}">
            <div class="row cl mb-20">
              <div class="tabBar clearfix">
                <c:if test="${document.flag == 8}">
                <a class="current" href='/api/village/xlb/A024?checkId=${document.checkId}&flag=8'>${document.flag2 == 3 ? '隐患整改意见书' : '现场检查记录'}</a>
                </c:if>
                <c:if test="${document.flag != 8}">
                <a href='/api/village/xlb/A024?checkId=${document.checkId}&flag=8'>现场检查记录</a>
                </c:if>
                <c:if test="${document.flag2 != 3}">
                <a ${document.flag == 1 ? 'class="current"' : ''} href='/api/village/xlb/A024?checkId=${document.checkId}&flag=1'>责令限期整改意见书</a>
                </c:if>
                <a ${document.flag == 2 ? 'class="current"' : ''} href='/api/village/xlb/A024?checkId=${document.checkId}&flag=2'>整改复查意见书</a>
              </div>
            </div>
            
        </c:if>
        
        <div id="div_container">
          <div style="width:150mm;margin:auto;padding:50px 0 0 0;">
        ${document.copy}
        </div>
        </div>
        <!-- 添加隐患模块 -->
    </div>
</body>

</html>