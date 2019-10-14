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
<%@ include file="/WEB-INF/inc/print.inc"%>
<link rel="stylesheet" type="text/css" href="${ly}/styles/print.css"/>
<link rel="stylesheet" type="text/css" href="${ly}/styles/print.css" media="print" />
<title>安全分级管控 隐患排查治理智能化信息平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style type="text/css">
</style>
<script type="text/javascript">
function pr_() {
  $("#div_container").jqprint();
}
    var searchURL = window.location.search;
    searchURL = searchURL.substring(1, searchURL.length);
    var f = searchURL.split("&")[0].split("=")[1];
    console.log('document.flag2:${document.flag2}')
</script>
</head>
<body>
    <div class="page-container" style="width:150mm;margin:auto;padding:10px 0 0 0;">
        <div class="row cl row_ac">
            <button onClick="pr_()" class="btn radius" type="button">
                <i class="Hui-iconfont mr-10">&#xe652;</i>打印
            </button>
            <c:choose>
                <c:when test="${document.flag >2 && document.flag < 8}">
                <button onClick="top.show_tab('行政处罚', '/village/danger/opinion-list?type=3&flag2=2')" class="btn radius" type="button">
                    <i class="Hui-iconfont mr-10">&#xe66b;</i>返回列表
                </button>
                <%-- <button onClick="top.show_tab('检查文书', '${ly }/village/opinion-show?flag2=2')" class="btn radius" type="button" >
                  <i class="Hui-iconfont mr-10">&#xe66b;</i>返回文书
                </button> --%>
                </c:when>

                <c:otherwise>
                <button onClick="top.show_tab('检查文书', '${ly }/village/check-list?flag=${document.flag2}')" class="btn radius" type="button" >
                    <i class="Hui-iconfont mr-10">&#xe66b;</i>返回列表
                </button>
                <c:if test="${document.flag2==2 }">
                  <%-- <button onClick="top.show_tab('检查文书', '${ly }/village/opinion-show?flag2=2')" class="btn radius" type="button" >
                      <i class="Hui-iconfont mr-10">&#xe66b;</i>返回文书
                  </button> --%>
                </c:if>
                <c:if test="${document.flag2!=2 }">
                  <%-- <button onClick="top.show_tab('检查文书', '${ly }/village/danger/opinion-list?type=1&flag2=${document.flag2}')" class="btn radius" type="button" >
                    <i class="Hui-iconfont mr-10">&#xe66b;</i>返回文书
                  </button> --%>
                </c:if>
                </c:otherwise>
            </c:choose>
            
        </div>
        <script>
    console.log('document.flag: ${document.flag}')
    console.log('opinion-detail2')
    </script>
        <c:if test="${document.flag == 1 || document.flag == 2 || document.flag == 8}">
        
            <div class="row cl mb-20">
              <div class="tabBar clearfix">
              <c:choose>
                <c:when test="${document.flag == 8}">
                  <a class="current" href='/village/check-document?checkId=${document.checkId}&flag=8&f=${document.flag2}'><%-- ${document.flag2 == 3 ? '隐患整改意见书' : '现场检查记录'} --%>现场检查记录</a>
              </c:when>
                  <c:when test="${document.flag == 1}">

                      <a  href='/village/check-document?checkId=${document.checkId}&flag=8&f=${document.flag2}'><%-- ${document.flag2 == 3 ? '隐患整改意见书' : '现场检查记录'} --%>现场检查记录</a>

                  </c:when>
                <c:otherwise>

                    <a href='/village/check-document?checkId=${document.checkId}&flag=8&f=${document.flag2}'><%-- ${document.flag2 == 3 ? '隐患整改意见书' : '现场检查记录'} --%>现场检查记录</a>

                </c:otherwise>
               </c:choose>
                <%-- <c:if test="${document.flag2 != 3}"> --%>
                <c:choose>
                    <c:when test="${empty itemL}">
                    <a href="javascript:void(0)" onclick="layer.alert('本次检查没有隐患，无需整改')">${document.flag2 == 3 ? '隐患整改意见书' : '责令限期整改意见书'}</a>
                    </c:when>
                    <c:otherwise>
                    <a ${document.flag == 1 ? 'class="current"' : ''} href='/village/check-document?checkId=${document.checkId}&flag=1&f=${document.flag2}'>${document.flag2 == 3 ? '隐患整改意见书' : '责令限期整改意见书'}</a>
                    </c:otherwise>
                </c:choose>
                <%-- </c:if> --%>
                
                <c:choose>
                    <c:when test="${empty itemL}">
                    <a href="javascript:void(0)" onclick="layer.alert('本次检查没有隐患，无需整改')">整改复查意见书</a>
                    </c:when>
                    <c:otherwise>
                    <a ${document.flag == 2 ? 'class="current"' : ''} href='/village/check-document?checkId=${document.checkId}&flag=2&f=${document.flag2}'>整改复查意见书</a>
                    </c:otherwise>
                </c:choose>
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
<script type="text/javascript">
$("span[contenteditable='true']").removeAttr("contenteditable");
</script>

</html>