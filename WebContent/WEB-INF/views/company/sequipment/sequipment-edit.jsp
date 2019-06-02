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
  <span>编辑特种设备</span>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
    <i class="Hui-iconfont">&#xe68f;</i>
  </a>
</nav>
<div class="page-container">
  <form class="form form-horizontal" id="form">
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">特种设备名称：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="a" name="" value="${se.seName }" style="width:350px" class="input-text required"  >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">型号：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="type" name="" value="${se.type }" style="width:350px" class="input-text required"  >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">设备档案号：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="b" name="" value="${se.fileNumber }" style="width:350px" class="input-text required"  >
      </div> 
    </div>
    <%-- <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">数量：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input  onkeyup="value=value.replace(/[^\d]/g,'')" type="text" id="c" name="" value="${se.amount }"  style="width:350px" class="input-text required ll-numberbox"  >
      </div> 
    </div> --%>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">上次检测时间 ：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text"  value="${se.detectionTime}" onfocus="WdatePicker({dateFmt:'yyyy-MM',maxDate:'#F{$dp.$D(\'safeTime3\')}'})" id="safeTime2" name="safeTime2" class="input-text Wdate mb-5 mt-5" style="width:350px;">
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">到期时间：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text"  value="${se.expirationTime }" onfocus="WdatePicker({dateFmt:'yyyy-MM',minDate:'#F{$dp.$D(\'safeTime2\')}'})" id="safeTime3" name="safeTime3" class="input-text Wdate mb-5 mt-5" style="width:350px;">
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">备注：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <textarea id="f"  class="textarea txtarea_sq">${se.remark }</textarea>
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
  var id = '${se.id}';
  function save() {
    if($("#a").val() == '') {
      layer.alert("特种设备名称不能为空",function(i) {
        layer.close(i);
        $("#a").focus();
      })
      return false;
    }
    /* if($("#type").val() == '') {
      layer.alert("型号不能为空",function(i) {
        layer.close(i);
        $("#type").focus();
      })
      return false;
    }
    if($("#b").val() == '') {
      layer.alert("设备档案号不能为空",function(i) {
        layer.close(i);
        $("#b").focus();
      })
      return false;
    } */
    /* if($("#c").val() == '') {
      layer.alert("数量不能为空",function(i) {
        layer.close(i);
        $("#c").focus();
      })
      return false;
    } */
    if($("#safeTime2").val() == '') {
      layer.alert("检测时间不能为空",function(i) {
        layer.close(i);
        $("#safeTime2").focus();
      })
      return false;
    }
    if($("#safeTime3").val() == '') {
      layer.alert("到期时间不能为空",function(i) {
        layer.close(i);
        $("#safeTime3").focus();
      })
      return false;
    }
    
    $.post(getRootPath() + "/company/sequipment/sequipment-save",{
      	id:id,
      	seName:$("#a").val(),
      	type:$("#type").val(),
      	fileNumber : $("#b").val(),
      	amount : $("#c").val(),
      	detectionTime:$("#safeTime2").val(),
      	expirationTime:$("#safeTime3").val(),
      	remark:$("#f").val()
    }, function (result) {
         show_tab("特种设备清单", getRootPath()+ '/company/sequipment/sequipment-list');
      /*  parent.close_tab(function() {
         show_tab("特种设备清单", getRootPath()+ '/company/sequipment/sequipment-list');
       }); */
    })
  }
  </script>
  
</div>
</body>
</html>