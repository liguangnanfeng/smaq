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
<link type="text/css" rel="stylesheet" href="${ly }/js/kindeditor/themes/default/default.css" />
<script type="text/javascript" src="${ly }/js/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" src="${ly }/js/kindeditor/lang/zh_CN.js"></script>
</head>
<body>
<nav class="breadcrumb">
  <!-- <i class="Hui-iconfont">&#xe67f;</i>
  <span>首页</span>
  <span class="c-gray en">&gt;</span>
  <span>企业基础信息</span>
  <span class="c-gray en">&gt;</span>
  <span>编辑生产工艺流程图</span> -->
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
    <i class="Hui-iconfont">&#xe68f;</i>
  </a>
</nav>
<div class="page-container">
  <form class="form form-horizontal" id="form">
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">项目名称：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="project" value="${be.project }" style="width:350px" class="input-text required"  >
      </div> 
    </div>
    <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">报告文档：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="hidden" id="docUrl" value="${be.docUrl }" />
          <input type="text" id="docName" style="width: 200px" value="${be.docName }" class="input-text" readonly="readonly" />
          <button id="insertfile" class="btn btn-warning radius ml-10" type="button">
            <i class="Hui-iconfont" style="font-size: 15px;">&#xe642;</i> 上传资料
          </button>
        </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">工艺流程简述：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <textarea id="description" class="textarea txtarea_sq" style="width: 557px;">${be.description }</textarea>
      </div> 
    </div>
    <%-- <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">制程描述：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <textarea id="description" class="textarea txtarea_sq">${be.description }</textarea>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">材料名称：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="material" value="${be.material }" style="width:350px" class="input-text required"  >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">材料规格：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="materialSpecifications" value="${be.materialSpecifications }"  style="width:350px" class="input-text required"  >
      </div> 
    </div>
    
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">人员要求：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <textarea id="personnelRequirements" class="textarea txtarea_sq">${be.personnelRequirements }</textarea>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">检查标准：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <textarea id="inspectionStandards" class="textarea txtarea_sq">${be.inspectionStandards }</textarea>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">操作规定：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <textarea id="operatingRules" class="textarea txtarea_sq">${be.operatingRules }</textarea>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">设备/工具：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <textarea id="equipment" class="textarea txtarea_sq">${be.equipment }</textarea>
      </div> 
    </div> --%>
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
      layer.alert("项目名称不能为空", function(i) {
        layer.close(i);
        $("#project").focus();
      })
      return false;
    }
    /* if ($("#docUrl").val() == '') {
      layer.alert("工艺流程文档地址不能为空", function(i) {
        layer.close(i);
        $("#docUrl").focus();
      })
      return false;
    } 
     if ($("#docName").val() == '') {
      layer.alert("工艺流程文档名称不能为空", function(i) {
        layer.close(i);
        $("#docName").focus();
      })
      return false;
    } 
    if ($("#description").val() == '') {
      layer.alert("备注描述不能为空", function(i) {
        layer.close(i);
        $("#description").focus();
      })
      return false;
    }*/
    $.post(getRootPath() + "/steel/process/process-save", {
      id : '${be.id }',
      project : $("#project").val(),
      docUrl : $("#docUrl").val(),
      description : $("#description").val(),
      docName : $("#docName").val()
/*       description : $("#description").val(),
      material : $("#material").val(),
      materialSpecifications : $("#materialSpecifications").val(),
      personnelRequirements : $("#personnelRequirements").val(),
      inspectionStandards : $("#inspectionStandards").val(),
      operatingRules : $("#operatingRules").val(),
      equipment : $("#equipment").val() */
    }, function(result) {
        show_tab("生产工艺流程图", getRootPath() + '/steel/process/process-list');
    })
  }
  
  KindEditor.ready(function(K) {
    var editor = K.editor({
      uploadJson : getRootPath() + '/UploadServlet'
    });
    K('#insertfile').click(function() {
      editor.loadPlugin('insertfile', function() {
        editor.plugin.fileDialog({
          fileUrl : K('#url').val(),
          clickFn : function(url, title) {
            K('#docUrl').val(url);
            K('#docName').val(title);
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