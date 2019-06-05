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
<%@ include file="/WEB-INF/inc/print.inc"%>
<link rel="stylesheet" type="text/css" media="print"  href="${ly}/js/H-ui.admin_v3.0/static/h-ui/css/H-ui.css" />
<link rel="stylesheet" type="text/css" href="${ly}/styles/common.css" media="print"  />
<style type="text/css" media="print">
body .dis-ib{margin-right:15px;}
.col-b,.col-a,.col-c,.col-d{margin:0;padding:0 15px;height:25px;line-height:25px;}
.div-print{width:90%;margin:auto;padding:20px 0 0 0;}
h3{display:block;width:100%;margin-bottom:15px;}
</style>
<style type="text/css">
body .dis-ib{margin-right:15px;}
.col-b,.col-a,.col-c,.col-d{margin:0;padding:0 15px;height:25px;line-height:25px;}
.div-print{width:90%;margin:auto;padding:20px 0 0 0;}
/* h3{display:none;} */
</style>
<script type="text/javascript">
function pr_() {
  $("#div_container").jqprint();
}
</script>
</head>
<body>
 <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>风险分级管控</span> 
    <span class="c-gray en">&gt;</span> <span>风险评估</span> 
  <c:if test="${flag == 1}">
    <span class="c-gray en">&gt;</span> <span>评估结果</span> 
  </c:if>
  <c:if test="${flag == 3}">
    <span class="c-gray en">&gt;</span> <span>风险分布表</span> 
  </c:if>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="text-c mt-20">
    <button onClick="pr_()" class="btn btn-primary radius" type="button">
        <i class="Hui-iconfont">&#xe652;</i>打印
    </button>
  </div>
  <div class="page-container" id="div_container" >
    <div class="div-print">
      <c:if test="${flag == 1}"><h3 class="text-c">${company.name}评估结果表</h3></c:if>
      <c:if test="${flag == 3}"><h3 class="text-c">${company.name}风险分布表</h3></c:if>
        <div class="cl pd-5 bg-1 bk-gray mt-20">
     		<span class="r">共有数据：<strong>${fn:length(list) }</strong> 条；重大风险：<strong>${fn:length(list11) }</strong> 条；较大风险：<strong>${fn:length(list22) }</strong> 条
             一般风险：<strong>${fn:length(list33) }</strong> 条；
             较小风险：<strong>${fn:length(list44) }</strong> 条；
             未辨识风险：<strong>${fn:length(list55) }</strong> 条；
            </span>

    	</div>
      <table class="table table-border table-bordered table-bg table-hover table-sort">  
        <%-- <c:if test="${flag == 1}">
        <thead>
          <tr class="text-c">
            <th style="width:40px">序号</th>
            <th style="min-width:100px">岗位/部位</th>
            <th style="min-width:100px">风险等级</th>
            <th style="min-width:150px">风险类型</th>
            <th style="min-width:200px">风险因素</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${list }" var="be" varStatus="index">
          <tr>
            <td class="text-c">${index.index + 1}</td>
            <td><c:if test="${empty be.level1}">公司</c:if><c:if test="${not empty be.level1}">${be.level1 } > ${be.level2 }</c:if></td>
            <td class="text-c">
               <c:choose>
                <c:when test="${be.level eq '红色'}"><font class="col-a">${be.level}</font></c:when>
                <c:when test="${be.level eq '橙色'}"><font class="col-b">${be.level}</font></c:when>
                <c:when test="${be.level eq '黄色'}"><font class="col-c">${be.level}</font></c:when>
                <c:when test="${be.level eq '蓝色'}"><font class="col-d">${be.level}</font></c:when>
               </c:choose>
            </td>
            <td>${be.type }</td>
            <td>${be.factors }</td>
          </tr>
          </c:forEach>
        </tbody>
        </c:if> --%>
        
        <%-- <c:if test="${flag == 2}"> --%>
        <thead>
          <tr class="text-c">
            <th style="width:40px">车间</th>
            <th style="min-width:100px">岗位/部位</th>
            <th style="min-width:100px">风险点</th>
            <th style="min-width:100px">风险等级</th>
            <th style="min-width:150px">风险类型</th>
            <th style="min-width:200px">风险因素</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${treeMap }" var="be1" varStatus="index">
          <c:forEach items="${be1.value }" var="be2">
          <c:forEach items="${list }" var="be">
          <c:if test="${(be.level1 eq be1.key && be.level2 eq be2) || (empty be1.key && empty be.level1)}">
          <tr>
            <td class="text-c">${empty be1.key ? '公司' : be1.key}</td>
            <td class="text-c">${be2}</td>
            
            <td class="text-c">
            	<c:set value="${fn:split(be.level3,'/')}" var="ls"></c:set>
              		<c:set value="0" var="y"/>
              		<c:forEach items="${ls}" var="l">
              			<c:set value="${y+1}" var="y"/>
                  			<c:if test="${y==3}">
                  			<c:set value="${l}" var="point"/>
                  		</c:if>
            	</c:forEach>
            	<p>${point}</p>
            </td>
            <td class="text-c">
               <c:choose>
                <c:when test="${be.level eq '红色'}"><font class="col-a">${be.level}</font></c:when>
                <c:when test="${be.level eq '橙色'}"><font class="col-b">${be.level}</font></c:when>
                <c:when test="${be.level eq '黄色'}"><font class="col-c">${be.level}</font></c:when>
                <c:when test="${be.level eq '蓝色'}"><font class="col-d">${be.level}</font></c:when>
               </c:choose>
            </td>
            <td class="text-c">${be.type }</td>
            <td class="text-c">${be.factors }</td>
          </tr>
          </c:if>
          </c:forEach>
          </c:forEach>
          </c:forEach>
        </tbody>
        <%-- </c:if> --%>
      </table>
    </div>
  </div>
</body>
<script type="text/javascript" src="/js/fxgk/tbl-rowspan-reset_top3col.js"></script>
</html>