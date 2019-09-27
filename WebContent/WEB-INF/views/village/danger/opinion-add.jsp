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
<script type="text/javascript">
function search() {
  if($("#companyName").val() == '') {
    layer.alert("请输入企业名称进行查询");
    return false;
  }
  $("#load").load("/village/companyLoad", {
    companyName : $("#companyName").val()
  })
}
</script>
</head>
<body>
<div class="page-container">
  <form class="form form-horizontal" id="form">
    <div class="row cl mt-20">
      <label class="form-label col-xs-4 col-sm-2">受检企业搜索：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="search" id="companyName" class="input-text mb-5 mt-5" style="width: 500px;" placeholder="请输入企业名称进行过滤">
        <button type="button" class="btn btn-primary radius" onclick="search()">搜索</button>
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">确定受检企业：</label>
      <div class="formControls col-xs-8 col-sm-9" style="max-height: 250px;overflow: scroll;" id="load">
        <p>请输入企业名称搜索企业</p>
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
var flag = ${flag}, flag2 = ${flag2};
function article_save_submit() {
  if($(":radio:checked").length == 0) {
    layer.alert("请选择企业");
    return false;
  }
  window.location.href = getRootPath() + "/village/danger/opinion-add?flag=" + flag + "&flag2="+flag2+"&userId=" + $(":radio:checked").val();
}
</script>
</html>