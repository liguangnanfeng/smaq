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
  <span>消防设备设施</span>
  <span class="c-gray en">&gt;</span>
  <span>编辑${flag == 1 ? '消防' : '应急'}设备设施</span>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
    <i class="Hui-iconfont">&#xe68f;</i>
  </a>
</nav>
<div class="page-container">
  <form class="form form-horizontal" id="form">
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">种类：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="t" value="${f.type }" name=""  style="width:350px" class="input-text required"  >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">型号：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="a" value="${f.model }" name=""  style="width:350px" class="input-text"  >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">配备位置：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <textarea id="b" class="textarea txtarea_sq">${f.equippedPosition }</textarea>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">数量：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="c" value="${f.amount }"  name=""  style="width:350px" class="input-text"  >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">有效期：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="d" value="${f.expiryDate}"   class="input-text" style="width:350px;">
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">备注：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <textarea id="e"  class="textarea txtarea_sq">${f.remark }</textarea>
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
  var id='${f.id}';
  var flag = '${flag}';
  function article_save_submit() {
    if($("#t").val() == '') {
      layer.alert("种类不能为空",function(i) {
        layer.close(i);
        $("#t").focus();
      })
      return false;
    }
    $.post(getRootPath() + "/company/facilities/facilities-save",{
      	id:id,
      	flag : flag,
      	type:$("#t").val(),
      	model:$("#a").val(),
      	equippedPosition : $("#b").val(),
      	amount : $("#c").val(),
      	expiryDate:$("#d").val(),
      	remark:$("#e").val(),
    }, function (result) {
         show_tab((flag=='1'?'消防':'应急') + "设备设施", getRootPath() +'/company/facilities/facilities-list?flag=' + flag);
    })
  }
  </script>
  
</div>
</body>
</html>