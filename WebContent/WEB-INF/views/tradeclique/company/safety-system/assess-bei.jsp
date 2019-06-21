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
.wid1{width:10%}
.wid2{width:10%}
.wid3{width:10%}
.wid4{width:10%}
.wid10{float:left;width:60%}
.one-ysd{float:left;}
.four-lb p{padding:0 5px;margin-bottom:5px;}
</style>
<script type="text/javascript">
$(function() {
 $(".btn-page").click(function(){
   $(".btn-page").removeClass("btn-pagexz");
   $(this).addClass("btn-pagexz");
 })
});


</script> 
</head>
<body>
  <div class="page-container">
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort tab-ndan">
        <thead>
          <tr class="text-c">
            <th>系统/车间</th>
            <th>工段/班组</th>
            <th>岗位/部位</th>
            <th>较大危险因素</th>
            <th>事故类型</th>
            <th>防范措施</th>
            <!-- <th>依据</th> -->
          </tr>
        </thead>
        <tbody>
          <!-- 循环 -->
          <c:forEach items="${list }" var="be">
          <c:forEach items="${be.value }" var="be2">
          <c:forEach items="${dL }" var="be3">
              <c:if test="${be3.level1 eq be.key and be3.level2 eq be2}">
              <tr>
                <td class="text-c" rowspan="1" width="100px;">${be.key }</td>
                <td class="text-c" width="100px;">${be2 }</td>
                <td class="text-c" width="100px;">${be3.level3 }</td>
                <td class="text-c" width="300px;">${be3.factors }</td>
                <td class="text-c" width="100px;">${be3.type }</td>
                <td class="text-c">${be3.measures }</td>
                <%-- <td class="text-c">${be3.reference }</td> --%>
              </tr>
              </c:if>
          </c:forEach>
          </c:forEach>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
  </div>
</body>
<script type="text/javascript" src="/js/fxgk/tbl-rowspan-reset.js"></script>
</html>