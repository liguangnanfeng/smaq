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
  <span>编辑主要原辅材料</span>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
    <i class="Hui-iconfont">&#xe68f;</i>
  </a>
</nav>
<div class="page-container">
  <form class="form form-horizontal" id="form">
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">原辅材料名称：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="a" name="" value="${ma.material }" style="width:350px" class="input-text required"  >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">年用量：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="b" name="" value="${ma.annualConsumption }" style="width:350px" class="input-text required"  >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">最大储存量：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="c" name="" value="${ma.maximumStorage }" style="width:350px" class="input-text required"  >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">物态：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="d" name="" value="${ma.state }" style="width:350px" class="input-text required"  >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">储存地点：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <textarea id="e" class="textarea txtarea_sq">${ma.storageLocation }</textarea>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">储存方式：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="f" name="" value="${ma.storageMethod }" style="width:350px" class="input-text required"  >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">备注：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <textarea id="g" ${ma.remark } class="textarea txtarea_sq">${ma.remark }</textarea>
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
  var id = '${ma.id}';
  function save() {
      if($("#a").val() == '') {
        layer.alert("原辅材料名称不能为空",function(i) {
          layer.close(i);
          $("#a").focus();
        })
        return false;
      }
      /* if($("#b").val() == '') {
        layer.alert("年用量不能为空",function(i) {
          layer.close(i);
          $("#b").focus();
        })
        return false;
      }
      if($("#c").val() == '') {
        layer.alert("最大储存量不能为空",function(i) {
          layer.close(i);
          $("#c").focus();
        })
        return false;
      }
      if($("#d").val() == '') {
        layer.alert("物态不能为空",function(i) {
          layer.close(i);
          $("#d").focus();
        })
        return false;
      }
      if($("#e").val() == '') {
        layer.alert("储存地点不能为空",function(i) {
          layer.close(i);
          $("#e").focus();
        })
        return false;
      }
      if($("#f").val() == '') {
        layer.alert("储存方式不能为空",function(i) {
          layer.close(i);
          $("#f").focus();
        })
        return false;
      } */
      
      $.post(getRootPath() + "/company/product/material-save",{
        	id:id,
        	material:$("#a").val(),
        	annualConsumption : $("#b").val(),
        	maximumStorage : $("#c").val(),
        	state:$("#d").val(),
        	storageLocation:$("#e").val(),
        	storageMethod:$("#f").val(),
        	remark:$("#g").val()
      }, function (result) {
           show_tab("主要原辅材料",  getRootPath() +'/company/product/material-list');
      })
  }
  </script>
  
</div>
</body>
</html>