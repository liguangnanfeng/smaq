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
<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.3&key=441462b46b736497a5f2a377aafb5b69"></script>
<style type="text/css">
.ter1{width:15%}
.tab_tmr1{width:35%}
</style>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>安全生产标准化</span> 
    <span class="c-gray en">&gt;</span> <span>安全生产责任制</span>
    <span class="c-gray en">&gt;</span> <span>安全生产管理网络图</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:history.back(-1)" title="返回">返回</a>
  </nav>
  <div class="page-container">
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">安全生产管理网络图：</label>
      <div class="formControls col-xs-8 col-sm-9">
            <img id="pic1" src="${ly }/images/zwtp.jpg" url="" style="width:300px;min-height:300px;"/>
        <a href="javascript:void(0);" onclick="img_upload('pic1', null)" class="btn btn-primary radius upload-btn"><i class="Hui-iconfont">&#xe642;</i> 添加图片</a>
      </div> 
    </div>
    <div class="row cl mt-20">
      <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
        <button onclick="save_submit()" class="btn btn-primary radius" type="button" style="padding: 0 70px;">
          <i class="Hui-iconfont">&#xe632;</i>保存
        </button>
      </div>
    </div>
  </div>
</body>
<script type="text/javascript">
var pic1='${user.pic1}'
$(function(){
  if(null!= pic1 && ""!=pic1){
  $("#pic1").attr("src",pic1);
  }
})

function save_submit(){
  $.post(getRootPath()+"/company/information/save-pic",{
    code : 1,
    pic:$("#pic1").attr("src"),
  },function(reuslt){
    layer.alert("保存成功");
  })
}
</script>
</html>