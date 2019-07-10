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
</style>
<script type="text/javascript">

var id = '${id}';
var ac = '${type}';
var list = ac.split(",");

$(function() {
  $.each(list, function(i,item) {
    $(":checkbox[value='"+item+"']").prop("checked", true);
  })

});



function save() {
  var v = [];

  $(":checkbox:checked").each(function() {
    v.push($(this).val());
  })
  var s = v.join(",");

  var obj = {
      id:id ,
      dangers:s
  };

  $.post("/company/safety-system/update-danger_type",obj,function(result) {
      if(result.status==0){
          parent.location.reload();
          var index = parent.layer.getFrameIndex(window.name);
          parent.layer.close(index);
      }else{
          layer.msg(result.mess);
      }

  })
}
</script>
</head>
<body>
  <%--<div style="display: none;" id="material">${ac.material}</div>--%>
  <div class="page-container">
    <div class="div-set">



        \

      <div class="div-redit" style="display: block;">
        <div class="all-risk">
          <c:if test="${empty list}">
          <p>没有可选因素</p>
          </c:if>
          <!-- 循环 -->
          <c:forEach items="${list }" var="be">
          <div class="check-risk">
            <input type="checkbox" name="che-risk"  value="${be.name}">
            <p data-toggle="tooltip" data-placement="top" title="${be.name}">${be.name}</p>
          </div>
          </c:forEach>
          <!-- 循环结束 -->
        </div>
      </div>
    <button onClick="save()" class="btn btn-primary radius mt-20" type="button" style="padding: 0 70px;">
      <i class="Hui-iconfont mr-10">&#xe632;</i>保存
    </button>
  </div>
  </div>
</body>
</html>