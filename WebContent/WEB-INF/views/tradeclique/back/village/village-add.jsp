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
<link rel="stylesheet" href="https://cache.amap.com/lbs/static/main.css"/>
<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.3&key=441462b46b736497a5f2a377aafb5b69"></script>
<title>后台首页</title>
<meta name="keywords" content="后台首页">
<meta name="description" content="后台首页">
<style type="text/css">
body .amap-sug-result{z-index:9999} 
body .txtarea_sq{width:350px;}
</style>
<script type="text/javascript">
function openmap(){
  $(".win2").height($(document).height());
  $(".win2").show();
  $(".win1").show();
}
function closemap(){
  $(".win2").hide();
  $(".win1").hide();
}
</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>村庄管理</span> 
    <span class="c-gray en">&gt;</span> <span>添加村庄</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <form class="form form-horizontal">
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">登录账号：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="text" id="userName" name="userName" placeholder="" style="width:350px" class="input-text required" maxlength="50" >
        </div> 
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">登录密码：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="password" id="userPsw" name="userPsw" placeholder="" style="width:350px" class="input-text required" maxlength="50" >
        </div> 
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">确认密码：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="password" id="userPsw2" name="userPsw2" placeholder="" style="width:350px" class="input-text required" maxlength="50" >
        </div> 
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">村庄名称：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="text" id="villageName" name="villageName" placeholder="" style="width:350px" class="input-text required" maxlength="50" >
        </div> 
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">所在地区：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <select id="province" class="sel_area"></select>
          <select id="city" class="sel_area"></select>
          <select id="region" class="sel_area"></select>
        </div> 
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">详细地址：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="hidden" id="globalRegionId" value="">
          <input type="hidden" id="jw" value="">
          <textarea id="address" class="textarea txtarea_sq" readonly="readonly" onclick="openmap()"></textarea>
        </div> 
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">村庄简介：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <textarea id="accountNum" class="textarea txtarea_sq"></textarea>
        </div> 
      </div>
      <div class="row cl">
        <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
          <button onClick="article_save_submit();" class="btn btn-primary radius" type="button" style="padding: 0 70px;"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
        </div>
      </div>
    </form>
    
    <!-- 地图弹窗 -->
    <div class="win1">
      <div class="tcall" style="min-height: 540px;">
        <div class="close_win" onclick="closemap()" style="border: 0px;">
          <label>选择地址</label> <img src="${ly }/images/fore/gb.jpg" />
        </div>
        <div class="allsq" style="width:300px;margin-left:15px;">
          <span style="float:left;line-height:29px;width: 77px;text-align:right;">详细地址：</span>
          <input type="text" class="txt_sq" id="search" value="" style="width:210px;"/>
        </div>
        <div class="allsq" style="width:500px;">
          <span style="float:left;line-height:29px;width: 65px;text-align:right;">经纬度：</span>
          <input type="text" class="txt_sq" id="addressMap1" placeholder="请在地图上标记您的详细地址" value="" style="width:310px;" readonly="readonly"/>
          <input type="button" class="btn_dzx" onclick="jw_()" value="确定"/>
        </div>
        <div class="div_map" id="map_div1"  style="width: 96%;height:425px;margin-left:2%;"></div>
      </div>
    </div>
    <div class="win2"></div>
  </div>
  <script type="text/javascript" src="${ly }/js/map.js"></script>
<script type="text/javascript">
function jw_() {
  $("#jw").val($("#addressMap1").val());
  $("#address").val($("#search").val());
  closemap();
}  
function article_save_submit() {
  if($("#userName").val() == '') {
    layer.alert("请输入登录账号",'',function(index){
      layer.close(index);
      $("#userName").focus();
    })
    return false;
  }
  if($("#userPsw").val() == '') {
    layer.alert("请输入账号密码",'',function(index){
      layer.close(index);
      $("#userPsw").focus();
    })
    return false;
  }
    if($("#confirmPsw").val() == '') {
      layer.alert("请确认密码",'',function(index){
        layer.close(index);
        $("#confirmPsw").focus();
      })
      return false;
  }
    if($("#confirmPsw").val() == $("#userPsw").val) {
      layer.alert("两次输入密码不一致",'',function(index){
        layer.close(index);
        $("#confirmPsw").focus();
      })
      return false;
  }
  if($("#userPsw").val() == '') {
    layer.alert("请输入登录密码",'',function(index){
      layer.close(index);
      $("#userPsw").focus();
    })
    return false;
  }
  if($("#userPsw2").val() == '') {
    layer.alert("请确认登录密码",'',function(index){
      layer.close(index);
      $("#userPsw2").focus();
    })
    return false;
  }
  if($("#userPsw2").val() !=$("#userPsw").val()) {
    layer.alert("登录密码不一致",'',function(index){
      layer.close(index);
      $("#userPsw2").focus();
    })
    return false;
  }
  
  if($("#villageName").val() == '') {
    layer.alert("请输入村庄名称",'',function(index){
      layer.close(index);
      $("#villageName").focus();
    })
    return false;
  }
  
  if($("#region").val() == '') {
    layer.alert("请选择所在地区")
    return false;
  }
  if($("#address").val() == '') {
    layer.alert("请选择详细地址")
    return false;
  }
  $.post(getRootPath() + "/back/village/village-save",{
    vName : $("#userName").val(),
    vPsw : $("#userPsw").val(),
    name : $("#villageName").val(),
    regionId : $("#region").val(),
    address : $("#address").val(),
    jw : $("#jw").val(),
    accountnum : $("#accountNum").val()
   },function(result) {
     if(result.status == '1') {
       layer.alert(result.map.message);
       return false;
     }
     layer.alert("保存成功","",function(index){
         show_tab('村庄列表', getRootPath() + '/back/village/village-list');
       /* parent.close_tab(function(){
         show_tab('村庄列表', getRootPath() + '/back/village/village-list');
       }) */
     })
  })
}
</script>
</body>
<script type="text/javascript" src="${ly }/js/util/globalRegion.js"></script>
<script type="text/javascript" src="https://webapi.amap.com/demos/js/liteToolbar.js"></script>
</html>