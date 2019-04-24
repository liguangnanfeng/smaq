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
  <i class="Hui-iconfont">&#xe67f;</i>
  <span>首页</span>
  <span class="c-gray en">&gt;</span>
  <span>企业基础信息</span>
  <span class="c-gray en">&gt;</span>
  <span>添加生产工艺流程图</span>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
    <i class="Hui-iconfont">&#xe68f;</i>
  </a>
</nav>
<div class="page-container">
  <form class="form form-horizontal" id="form">
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">项目名称：</label>
      <div class="formControls col-xs-8 col-sm-9">
      	<input type="hidden" id="id" value="id">
        <input type="text" id="project" value="project" style="width:350px" class="input-text required"  >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">制程描述：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <textarea id="description" value="description" class="textarea txtarea_sq">description</textarea>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">材料名称：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="" name=""  style="width:350px" class="input-text required"  >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">材料规格：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="" name=""  style="width:350px" class="input-text required"  >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">人员要求：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <textarea id="" class="textarea txtarea_sq"></textarea>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">检查标准：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <textarea id="" class="textarea txtarea_sq"></textarea>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">操作规定：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <textarea id="" class="textarea txtarea_sq"></textarea>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">设备/工具：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <textarea id="" class="textarea txtarea_sq"></textarea>
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
  function article_save_submit() {
  }
  </script>
  
</div>
</body>
</html>