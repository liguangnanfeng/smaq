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

</script>
</head>
<body>
<nav class="breadcrumb">
  <i class="Hui-iconfont">&#xe67f;</i>
  <span>首页</span>
  <span class="c-gray en">&gt;</span>
  <span>隐患排查治理系统</span>
  <span class="c-gray en">&gt;</span>
  <span>编辑检查表</span>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
    <i class="Hui-iconfont">&#xe68f;</i>
  </a>
</nav>
<div class="page-container">
  <form class="form form-horizontal" id="form">
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">标题：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="title" value="${check.title }" style="width:350px" class="input-text" maxlength="50"/>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">${check.flag == 1 ? '部门' : '单位'}：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="depart" value="${check.depart }" style="width:350px" class="input-text" maxlength="100"/>
      </div> 
    </div>
   <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">检测类型：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <c:set var="x" value="${fn:split('基础管理/现场管理/高危作业','/') }"/>
        <input type="text" value="${x[model.industryType - 1] }" style="width:350px" class="input-text required" disabled="disabled"/>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">检查表类型：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <c:set var="x" value="${fn:split('日常/定期/临时','/') }"/>
        <input type="text" value="${x[model.type - 1] }" style="width:350px" class="input-text required" disabled="disabled"/>
        <a class="btn btn-primary radius ml-20" data-title="查看检查表_${check.modelId }" data-href="${ly }/company/model-show/${check.modelId}" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont" style="font-size:15px;">&#xe695;</i> 查看检查表</a>
      </div> 
    </div>
    
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">预计${flag == 1 ? '检查' : '录入'}时间：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" value="<fmt:formatDate value="${check.expectTime }" pattern="yyyy-MM-dd"/>" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd', minDate:'%y-%M-%d'})" id="expectTime" class="input-text Wdate mb-5 mt-5" style="width:350px;">
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">检查人：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="cheker" value="${check.cheker }" style="width:350px" class="input-text" maxlength="50"/>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">受检负责人：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="dapartContact" value="${check.dapartContact }" style="width:350px" class="input-text" maxlength="50"/>
      </div> 
    </div>
    <div class="row cl">
      <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2  mt-20">
        <button onClick="article_save_submit()" class="btn btn-primary radius" type="button" style="padding: 0 70px;">
          <i class="Hui-iconfont">&#xe632;</i>保存
        </button>
      </div>
    </div>
  </form>
  
  
</div>
</body>
<script type="text/javascript">
var id = ${check.id};
function article_save_submit() {
  if($("#title").val() == '') {
    layer.alert("检查表标题不能为空");
    return false;
  }
  if($("#depart").val() == '') {
    layer.alert("检查表受检部门不能为空");
    return false;
  }
  
  var i = layer.load();
  $.post(getRootPath() + "/company/plan-edit-save", {
    id : id,
    title : $("#title").val(),
    depart : $("#depart").val(),
    expectTime : $("#expectTime").val(),
    cheker : $("#cheker").val(),
    dapartContact : $("#dapartContact").val()
  },function(result){
    layer.close(i);
    if(result.status == '1') {
      layer.alert(result.map.message);
    } else {
        show_tab('企业自查', '/village/check-list');
    }
  })
}
</script>
</html>