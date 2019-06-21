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
  function save_submit() {
    $.post(getRootPath()+"/company/rules-institution-change-save",{
      id:'${r.id}',
      managementSystem:$("#managementSystem").val(),
      misInforce:$("#misInforce").val(),
      regulations:$("#regulations").val(),
      risInforce:$("#risInforce").val(),
      safety:$("#safety").val(),
      sisInforce:$("#sisInforce").val(),
      implementation:$("#implementation").val(),
      contingency:$("#contingency").val(),
      cisInforce:$("#cisInforce").val(),
    },function(result){
      layer.msg("保存成功！", {
        icon : 6,
        time : 1000
      }, function() {
          show_tab('规章制度', getRootPath() +"/company/rules-institution");
      });
    });
  }
</script>
</head>
<body>
<nav class="breadcrumb">
  <i class="Hui-iconfont">&#xe67f;</i>
  <span>首页</span>
  <span class="c-gray en">&gt;</span>
  <span>安全生产标准化</span>
  <span class="c-gray en">&gt;</span>
  <span>安全生产管理制度</span>
  <span class="c-gray en">&gt;</span>
  <span>编辑规章制度</span>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
    <i class="Hui-iconfont">&#xe68f;</i>
  </a>
</nav>
<div class="page-container">
  <form class="form form-horizontal" id="form">
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">管理制度：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <select class="sel_area" id="managementSystem" style="float:left;">
          <option value=""></option>
          <option value="1" <c:if test="${r.managementSystem==1 }">selected="selected"</c:if>>有</option>
          <option value="0" <c:if test="${r.managementSystem==0 }">selected="selected"</c:if>>无</option>
        </select>
        <label class="form-label col-xs-4 col-sm-2">是否在有效期内：</label>
        <select class="sel_area" id="misInforce">
          <option value=""></option>
          <option value="1" <c:if test="${r.misInforce==1 }">selected="selected"</c:if>>是</option>
          <option value="0" <c:if test="${r.misInforce==0 }">selected="selected"</c:if>>否</option>
        </select>
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">操作规程：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <select class="sel_area" id="regulations" style="float:left;">
          <option value=""></option>
          <option value="1" <c:if test="${r.regulations==1 }">selected="selected"</c:if>>有</option>
          <option value="0" <c:if test="${r.regulations==0 }">selected="selected"</c:if>>无</option>
        </select>
        <label class="form-label col-xs-4 col-sm-2">是否在有效期内：</label>
        <select class="sel_area" id="risInforce">
          <option value=""></option>
          <option value="1" <c:if test="${r.risInforce==1 }">selected="selected"</c:if>>是</option>
          <option value="0" <c:if test="${r.risInforce==0 }">selected="selected"</c:if>>否</option>
        </select>
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">安全生产责任制：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <select class="sel_area" id="safety" style="float:left;">
          <option value=""></option>
          <option value="1" <c:if test="${r.safety==1 }">selected="selected"</c:if>>有</option>
          <option value="0" <c:if test="${r.safety==0 }">selected="selected"</c:if>>无</option>
        </select>
        <label class="form-label col-xs-4 col-sm-2">是否在有效期内：</label>
        <select class="sel_area" id="sisInforce">
          <option value=""></option>
          <option value="1" <c:if test="${r.sisInforce==1 }">selected="selected"</c:if>>是</option>
          <option value="0" <c:if test="${r.sisInforce==0 }">selected="selected"</c:if>>否</option>
        </select>
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">责任制落实情况：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <select class="sel_area" id="implementation" style="float:left;">
          <option value=""></option>
          <option value="1" <c:if test="${r.implementation==1 }">selected="selected"</c:if>>是</option>
          <option value="0" <c:if test="${r.implementation==0 }">selected="selected"</c:if>>否</option>
        </select>
        <label class="form-label col-xs-4 col-sm-2">应急预案：</label>
        <select class="sel_area" id="contingency">
          <option value=""></option>
          <option value="1" <c:if test="${r.contingency==1 }">selected="selected"</c:if>>有</option>
          <option value="0" <c:if test="${r.contingency==0 }">selected="selected"</c:if>>无</option>
        </select>
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">是否在有效期内：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <select class="sel_area" id="cisInforce" style="float:left;">
          <option value=""></option>
          <option value="1" <c:if test="${r.cisInforce==1 }">selected="selected"</c:if>>是</option>
          <option value="0" <c:if test="${r.cisInforce==0 }">selected="selected"</c:if>>否</option>
        </select>
      </div>
    </div>
    <div class="row cl">
      <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
        <button onClick="save_submit()" class="btn btn-primary radius" type="button" style="padding: 0 70px;">
          <i class="Hui-iconfont">&#xe632;</i>保存
        </button>
      </div>
    </div>
  </form>
</div>
</body>
</html>