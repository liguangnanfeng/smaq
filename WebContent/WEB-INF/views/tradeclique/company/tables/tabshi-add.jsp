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
<link type="text/css" rel="stylesheet" href="${ly }/js/kindeditor/themes/default/default.css" />
<script type="text/javascript" src="${ly }/js/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" src="${ly }/js/kindeditor/lang/zh_CN.js"></script>
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
        <input type="text" id="department" value="${be.department }"  style="width:350px" class="input-text required"  >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">事故时间：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="time" value="<fmt:formatDate value="${be.time }" pattern="yyyy-MM-dd"/>" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd', minDate:'%y-%M-{%d+1}'})" class="input-text Wdate mb-5 mt-5" style="width:350px;">
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">事故类别：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input  type="text" id="type" value="${be.type }"  style="width:350px" class="input-text"  >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">死亡人数：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input  type="text" id="personDeath" value="${be.personDeath }"  style="width:350px" class="input-text"  >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">失踪人数：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="personMiss" value="${be.personMiss }" class="input-text" style="width:350px;">
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">受伤人数：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="personInjured" value="${be.personInjured }" class="input-text" style="width:350px;">
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">经济损失：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="economicLoss" value="${be.economicLoss }" class="input-text" style="width:350px;">
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">调查报告：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="docName" value="${be.docName }" style="width: 200px" class="input-text required" readonly="readonly" />
        <input type="hidden" id="docUrl" value="${be.docUrl }" />
        <button id="insertfile" class="btn btn-warning radius ml-10" type="button">
            <i class="Hui-iconfont" style="font-size: 15px;">&#xe642;</i> 上传资料
        </button>
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
    if ($("#department").val() == '') {
      layer.alert("所在部门不能为空", function(i) {
        layer.close(i);
        $("#department").focus();
      })
      return false;
    }
    if ($("#time").val() == '') {
      layer.alert("事故时间不能为空", function(i) {
        layer.close(i);
        $("#time").focus();
      })
      return false;
    }
    if ($("#type").val() == '') {
      layer.alert("事故类别不能为空", function(i) {
        layer.close(i);
        $("#type").focus();
      })
      return false;
    }
    if ($("#personDeath").val() == '') {
      layer.alert("死亡人数不能为空", function(i) {
        layer.close(i);
        $("#personDeath").focus();
      })
      return false;
    }
    if ($("#personMiss").val() == '') {
      layer.alert("失踪人数不能为空", function(i) {
        layer.close(i);
        $("#personMiss").focus();
      })
      return false;
    }
    if ($("#personInjured").val() == '') {
      layer.alert("受伤人数不能为空", function(i) {
        layer.close(i);
        $("#personInjured").focus();
      })
      return false;
    }
    if ($("#economicLoss").val() == '') {
      layer.alert("经济损失不能为空", function(i) {
        layer.close(i);
        $("#economicLoss").focus();
      })
      return false;
    }
    if ($("#docName").val() == '') {
      layer.alert("调查报告不能为空", function(i) {
        layer.close(i);
        $("#docName").focus();
      })
      return false;
    }
    if ($("#docUrl").val() == '') {
      layer.alert("调查报告不能为空", function(i) {
        layer.close(i);
        $("#docUrl").focus();
      })
      return false;
    }

    $.post(getRootPath() + "/company/tables/tabshi-save", {
      id : '${be.id }',
      department : $("#department").val(),
      time : $("#time").val(),
      type : $("#type").val(),
      personDeath : $("#personDeath").val(),
      personMiss : $("#personMiss").val(),
      personInjured : $("#personInjured").val(),
      economicLoss : $("#economicLoss").val(),
      docName : $("#docName").val(),
      docUrl : $("#docUrl").val()
    }, function(result) {
        show_tab("事故报告和处理", getRootPath() + '/company/tables/tab-shilist');
    })
  }
  
  KindEditor.ready(function(K) {
    var editor = K.editor({
      uploadJson : getRootPath() + '/UploadServlet'
    });
    K('#insertfile').click(function() {
      editor.loadPlugin('insertfile', function() {
        editor.plugin.fileDialog({
          fileUrl : K('#docName').val(),
          clickFn : function(url, title) {
            docUrl = url;
            K('#docName').val(title);
            K('#docUrl').val(url);
            editor.hideDialog();
          }
        });
      });
    });
  });
  </script>
  
</div>
</body>
</html>