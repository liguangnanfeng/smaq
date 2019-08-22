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
</style>
<script type="text/javascript">

</script>
</head>
<body>
<nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>安全生产标准化</span> 
    <span class="c-gray en">&gt;</span><span>安全生产管理档案</span>
    <span class="c-gray en">&gt;</span> <span>事故报告和处理</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
<div class="page-container">
  <form class="form form-horizontal" id="form">
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">所在部门：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="" name=""  style="width:350px" class="input-text required"  >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">事故时间：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" value="<fmt:formatDate value="" pattern="yyyy-MM-dd"/>" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd', minDate:'%y-%M-{%d+1}'})" id="" class="input-text Wdate mb-5 mt-5" style="width:350px;">
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">事故类别：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input  type="text" id="" name=""  style="width:350px" class="input-text"  >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">死亡人数：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input  type="text" id="" name=""  style="width:350px" class="input-text"  >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">失踪人数：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="" value="" class="input-text" style="width:350px;">
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">受伤人数：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="" value="" class="input-text" style="width:350px;">
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">经济损失：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="" value="" class="input-text" style="width:350px;">
      </div>
    </div>
    <div class="row cl">
      <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
        <button onClick="article_save_submit()" class="btn btn-primary radius" type="button" style="padding: 0 70px;">
          <i class="Hui-iconfont">&#xe632;</i>保存
        </button>
      </div>
    </div>
  </form>
  <script type="text/javascript">
  
  </script>
  
</div>
</body>
</html>