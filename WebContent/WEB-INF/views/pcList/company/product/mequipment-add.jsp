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
  <span>设备设施管理</span>
  <span class="c-gray en">&gt;</span>
  <span>添加主要设备</span>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
    <i class="Hui-iconfont">&#xe68f;</i>
  </a>
</nav>
<div class="page-container">
  <form class="form form-horizontal" id="form">
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">名称：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="name" name=""  style="width:350px" class="input-text required"  >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">型号规格：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="size" name=""  style="width:350px" class="input-text required"  >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">工艺参数：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <textarea id="parameter" class="textarea txtarea_sq"></textarea>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">数量：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" onkeyup="value=value.replace(/[^\d]/g,'')" id="amount" name=""  style="width:350px" class="input-text required ll-numberbox" data-options="min:0">
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">备注：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <textarea id="remark" class="textarea txtarea_sq"></textarea>
      </div> 
    </div>
    <div class="row cl">
      <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
        <button onClick="save()" class="btn btn-primary radius" type="button" style="padding: 0 70px;">
          <i class="Hui-iconfont">&#xe632;</i>保存
        </button>
      </div>
    </div>
  </form>
  <script type="text/javascript">
  function save() {
    if($("#name").val() == '') {
      layer.alert("名称不能为空",function(i) {
        layer.close(i);
        $("#name").focus();
      })
      return false;
    }
    /* if($("#size").val() == '') {
      layer.alert("型号规格不能为空",function(i) {
        layer.close(i);
        $("#size").focus();
      })
      return false;
    }
    if($("#parameter").val() == '') {
      layer.alert("工艺参数不能为空",function(i) {
        layer.close(i);
        $("#parameter").focus();
      })
      return false;
    }
    if($("#amount").val() == '') {
      layer.alert("数量不能为空",function(i) {
        layer.close(i);
        $("#amount").focus();
      })
      return false;
    } */
    
    $.post(getRootPath() + "/company/product/mequipment-save",{
 		equipmentName:$("#name").val(),
 		size : $("#size").val(),
 		processParameters : $("#parameter").val(),
 		amount:$("#amount").val(),
 		remark:$("#remark").val()
    }, function (result) {
         show_tab("主要设备清单",  getRootPath() +'/company/product/mequipment-list');
    })
  }
  </script>
  
</div>
</body>
</html>