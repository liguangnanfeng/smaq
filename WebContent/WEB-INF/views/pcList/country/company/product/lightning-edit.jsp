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
  <span>安全生产标准化</span>
  <span class="c-gray en">&gt;</span>
  <span>编辑防雷防静电</span>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
    <i class="Hui-iconfont">&#xe68f;</i>
  </a>
</nav>
<div class="page-container">
  <form class="form form-horizontal" id="form">
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">检测项目：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="project" value="${be.project }" style="width:350px" class="input-text required"  >
      </div> 
    </div>
    <div class="row cl" style="display:none">
      <label class="form-label col-xs-4 col-sm-2">检测内容：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="content" value="${be.content }" style="width:350px" class="input-text required"  >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">上次检测时间：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="lastTestTime" class="input-text Wdate mb-5 mt-5" value="<fmt:formatDate value="${be.lastTestTime }" pattern="yyyy-MM-dd"/>" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:350px;">
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">到期时间：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="expirationTime" class="input-text Wdate mb-5 mt-5" value="<fmt:formatDate value="${be.expirationTime }" pattern="yyyy-MM-dd"/>" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:350px;">
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">检测结果：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <textarea id="testResults" class="textarea txtarea_sq">${be.testResults }</textarea>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">检测单位：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <textarea id="detectionUnit" class="textarea txtarea_sq">${be.detectionUnit }</textarea>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">检测报告编号：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <textarea id="testReportNumber" class="textarea txtarea_sq">${be.testReportNumber }</textarea>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">备注：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <textarea id="remark" class="textarea txtarea_sq">${be.remark }</textarea>
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
    if ($("#project").val() == '') {
      layer.alert("检测项目不能为空", function(i) {
        layer.close(i);
        $("#project").focus();
      })
      return false;
    }

    $.post(getRootPath() + "/company/product/lightning-save", {
      id : '${be.id }',
      project : $("#project").val(),
      content : $("#content").val(),
      lastTestTime : $("#lastTestTime").val(),
      expirationTime : $("#expirationTime").val(),
      testResults : $("#testResults").val(),
      detectionUnit : $("#detectionUnit").val(),
      testReportNumber : $("#testReportNumber").val(),
      remark : $("#remark").val()
    }, function(result) {
        show_tab("防雷防静电", getRootPath() + '/company/product/lightning-list');
      /* parent.close_tab(function() {
        show_tab("防雷防静电", getRootPath() + '/company/product/lightning-list');
      }); */
    })
  }
  </script>
  
</div>
</body>
</html>