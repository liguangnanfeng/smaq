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
<title>安全分级管控 隐患排查治理智能化信息平台</title>
<link rel="stylesheet" type="text/css" href="${ly}/styles/print.css"/>
<link rel="stylesheet" type="text/css" href="${ly}/styles/print.css" media="print" />
<link rel="stylesheet" type="text/css" href="${ly}/js/H-ui.admin_v3.0/static/h-ui/css/H-ui.css"  media="print" />
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style type="text/css">

</style>
<script type="text/javascript">
function showpicture(src){
  $(".div_imgp img").attr("src", src);
  $("#modal-plan").modal("show")}
function pr_() {
  $("#div_container").jqprint();
}
</script>
</head>
<body>
  <div class="page-container print-tp">
  <div class="row cl row_ac text-c">
            <button onClick="pr_()" class="btn radius" type="button">
                <i class="Hui-iconfont mr-10">&#xe652;</i>打印
            </button>
        </div>
  <div>
    <div id="div_container">
    <div style="width:180mm;margin:auto;padding:30px 0 0 0;">
    <h2 class="text-c mb-10">隐患描述与图片</h2>
    <div class="div_pdetail" style="display:inline-block;width:100%">
      <font style="width:310px">检查部门：${check.checkCompany }</font> 
      <label>检查日期：<fmt:formatDate value="${check.realTime }" pattern="yyyy年MM月dd日" /> 
      <c:if test="${empty check.realTime}">&nbsp;&nbsp;年&nbsp;月&nbsp;日</c:if>
      </label>
    </div>
    <table class="table table-border table-bordered table-bg table-hover table-sort" style="width:99%;margin-top:20px;">
      <thead>
        <tr class="text-c">
          <th width="20%">隐患部位</th>
          <th width="20%">隐患描述</th>
          <th width="20%">隐患图片</th>
          <th width="20%">检查依据</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${itemL }" var="ch">
          <tr>
          <c:if test="${ch.status > 1}">
         		  <td class="text-l ">${ch.name}</td>
                <td class="text-l ">${empty ch.memo ? "此隐患没有描述" : ch.memo }</td>
                <td class="text-l ">
                <c:if test="${!empty ch.files }">
                <img src="${ch.files } " style="width:200px; height:200px;" />
                </c:if>
                </td>
                <td class="text-l ">${ch.content}</td>
                </c:if>
        </c:forEach>
      </tbody>
    </table>
    </div>
    </div>
  </div>
  </div>
</body>
</html>