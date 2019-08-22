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
var id_ = '${e.id}';
function save_() {
  $.post(getRootPath() + "/company/evaluate/health-save", {
    id : id_,
    type : $("#type").val(),
    time : $("#time").val(),
    project : $("#project").val(),
    whys : $("#whys").val(),
    tjjg : $("#tjjg").val(),
    healthcheckMember : $("#healthcheckMember").val(),
    endTime : $("#endTime").val(),
    remark : $("#remark").val(),
  },function(result){
    if(result.status == '1') {
      layer.alert(result.map.message);
    } else {
        show_tab('职卫检测、体检', '/company/warning/examination-list')
    }
  })
}
</script>
</head>
<body>
<nav class="breadcrumb">
  <i class="Hui-iconfont">&#xe67f;</i>
  <span>首页</span>
  <span class="c-gray en">&gt;</span>
  <span>职业健康管理</span>
  <span class="c-gray en">&gt;</span>
  <span>职业健康管理台账</span>
  <span class="c-gray en">&gt;</span>
  <span>添加职业健康检测</span>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
    <i class="Hui-iconfont">&#xe68f;</i>
  </a>
</nav>
<div class="page-container">
  <form class="form form-horizontal" id="form" action="${ly }/company/evaluate/health-save" method="post">
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">体检类别：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <select id="type" name="contactCondition"  class="sel_area">
          <option>在岗</option>
          <option>岗前</option>
          <option>离岗</option>
          <option>诊断</option>
          <option>复查</option>
        </select>
        <script type="text/javascript">
        var type = '${e.type}';
        if(type != '') {
          $("#type").val(type);
        }
        </script>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">体检时间：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="time" value="${e.time }" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" class="input-text Wdate mb-5 mt-5" style="width:350px;">
      </div>
    </div>
    <div class="row cl" style="display:none">
      <label class="form-label col-xs-4 col-sm-2">体检项目：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="project" value="${e.project }" class="input-text mb-5 mt-5" style="width:350px;" maxlength="200">
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">体检人数：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="healthcheckMember" value="${e.healthcheckMember }" style="width:350px" class="input-text required" >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">职业病危害因素：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="whys" value="${e.whys }" style="width:350px" class="input-text required" maxlength="200">
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">体检机构：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="tjjg" value="${e.tjjg }" style="width:350px" class="input-text required" maxlength="200">
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">到期时间：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" value="${e.endTime }" id="endTime" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" class="input-text Wdate mb-5 mt-5" style="width:350px;">
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">备注：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <textarea id="remark" class="textarea txtarea_sq">${e.remark }</textarea>
      </div> 
    </div>
    <div class="row cl">
      <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
        <button  class="btn btn-primary btn-success radius" type="button" style="padding: 0 70px;" onclick="save_()">
          <i class="Hui-iconfont">&#xe632;</i>保存
        </button>
      </div>
    </div>
  </form>
  </div>
</body>
</html>