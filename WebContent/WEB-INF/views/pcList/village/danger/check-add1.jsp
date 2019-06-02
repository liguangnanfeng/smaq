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
.input-text{position:relative}
.txtarea_sq{height:100px;width:350px;}
body .check-box, .radio-box{line-height:30px;}
</style>
</head>
<body>
<div class="page-container">
  <form class="form form-horizontal" id="form">
    <div class="row cl mt-20">
      <img alt="" src="${ly }/images/danger.jpg" style="margin-left:10%;height:120px"/>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">请选择检查类型：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <div class="radio-box">
          <input type="radio" name="demo-radio1" value="${ind1.id }" data-t="1">
          <label for="radio-1">基础管理</label>
        </div>
        <c:forEach items="${ind2L }" var="be">
        <div class="radio-box">
          <input type="radio" name="demo-radio1" value="${be.id }" data-t="2">
          <label>现场管理-${be.name }</label>
        </div>
        </c:forEach>
        <c:forEach items="${ind3L }" var="be">
        <div class="radio-box">
          <input type="radio" name="demo-radio1" value="${be.id }" data-t="3">
          <label>高危作业-${be.name }</label>
        </div>
        </c:forEach>
        
      </div> 
    </div>
    <div class="row cl">
      <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2  mt-20">
        <button onClick="article_save_submit()" class="btn btn-primary radius" type="button" style="padding: 0 70px;">
          <i class="Hui-iconfont">&#xe632;</i>下一步
        </button>
      </div>
    </div>
  </form>
  
  
</div>
</body>
<script type="text/javascript">
var type = 3, flag = ${flag}, userId = ${company.userId };
function article_save_submit() {
  if($(":radio:checked").length == 0) {
    layer.alert("请选择隐患检查类型");
    return false;
  }
  window.location.href = getRootPath() + "/village/check-add?flag=" + flag + "&userId=" + userId + "&industryId=" + $(":radio:checked").val();
}
</script>
</html>