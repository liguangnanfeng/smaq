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
  console.log('${ac}');
var type = '${type}';
var id = '${id}';
var ac = '${ac}';
var list = ac.split("!@#");

<%--var hxys = '${ac.hxys}';--%>
<%--var gy = '${ac.gy}';--%>
<%--var wlbs = '${ac.wlbs}';--%>
<%--var gwzy = '${ac.gwzy}';--%>

$(function() {
  <%--var material = $("#material").text();--%>

  <%--var list;--%>
  <%--if(type == '1') {--%>
    <%--list = hxys.split("!@#");--%>
  <%--}--%>
  <%--if(type == '2') {--%>
    <%--list = material.split("!@#");--%>
  <%--}--%>
  <%--if(type == '3') {--%>
    <%--list = gy.split("!@#");--%>
  <%--}--%>
  <%--if(type == '4') {--%>
    <%--list = wlbs.split("!@#");--%>
  <%--}--%>
  <%--if(type == '5') {--%>
    <%--list = gwzy.split("!@#");--%>
  <%--}--%>
  $.each(list, function(i,item) {
    $(":checkbox[value='"+item+"']").prop("checked", true);
  })

});

function ser() {
  var v = $(":text").val();
  if(v == '') {
    $(".check-risk").show();
  } else {
    $(":checkbox").each(function(i, item) {
      if($(this).val().indexOf(v) != -1) {
        $(this).parent().show();
      } else {
        $(this).parent().hide();
      }
    })
  }
}

function save() {
  var v = [];
  var ids = [];
  $(":checkbox:checked").each(function() {
    v.push($(this).val());
    if(type != '4' && type != '5') {
      ids.push($(this).attr("data-id"));
    }
  })
  var obj = {id : id,type:type};
  if(type == '1') {
    obj.hxys = v.join("!@#");
    obj.gzkwlys = ids.join(",");
  }
  if(type == '2') {
    obj.material = v.join("!@#");
    obj.gzkhxys = ids.join(",");
  }
  if(type == '3') {
    obj.gy = v.join("!@#");
  }
  if(type == '4') {
    obj.wlbs = v.join("!@#");
  }
  if(type == '5') {
    obj.gwzy = v.join("!@#");
  }
  $.post("/company/safety-system/set-risk-edit",obj,function(result) {
    parent.location.reload();
  })
}
</script>
</head>
<body>
  <%--<div style="display: none;" id="material">${ac.material}</div>--%>
  <div class="page-container">
    <div class="div-set">
      <div class="div-redit" style="display: block;">
        <div class="dis-ib">
          <span>名称：</span>
          <input type="text" name="ser" class="input-text mb-5 mt-5" style="width:150px;">
        </div>
        <button class="btn btn-success" onclick="ser()" type="button">
          <i class="Hui-iconfont" >&#xe665;</i> 查询
        </button>
        <div class="all-risk">
          <c:if test="${empty list}">
          <p>没有可选因素</p>
          </c:if>
          <!-- 循环 -->
          <c:forEach items="${list }" var="be">
          <div class="check-risk">
            <input type="checkbox" name="che-risk" <c:if test="${type != 4 && type != 5}">data-id="${be.id }"</c:if> value="${type == 4 || type == 5 ? be : be.name}">
            <p data-toggle="tooltip" data-placement="top" title="${type == 4 || type == 5 ? be : be.name}">${type == 4 || type == 5 ? be : be.name}</p>
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