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
<title>后台首页</title>
<meta name="keywords" content="后台首页">
<meta name="description" content="后台首页">
<style type="text/css">
body .amap-sug-result{z-index:9999} 
body .check-box label{line-height:26px;}
.listem>* {
    float: left;
}
.listem em {
    font-size: 12px;
    padding: 0 5px;
    margin: 0 5px 5px 0;
    border-radius: 1px;
    background-color: #f7f7f7;
    height: 22px;
    line-height: 21px;
    box-sizing: border-box;
    font-style: unset;
}
.row {
    box-sizing: border-box;
    margin-left: -15px;
    margin-right: -15px;
    margin-top: 12px;
}
</style>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>设备管理</span> 
    <span class="c-gray en">&gt;</span> <span>添加设备</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <!-- <form class="form form-horizontal"> -->
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2"><em style="color:#ff0000;">*</em>设备名称：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="text" id="deviceName"  value="${device.name }" placeholder="" style="width:350px" class="input-text required" maxlength="20" >
        </div> 
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2"><em style="color:#ff0000;">*</em>设备序列号：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="text" id="deviceSn" value="${device.deviceSn }" placeholder="" style="width:350px" class="input-text required" maxlength="5" >
        </div> 
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2"><em style="color:#ff0000;">*</em>单位名称：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <span id="companySpan"><c:out value="${companyName }" default="暂未设置"/></span><a id="toSearchCompany" style="color: #5a98de;" href="javascript:void(0);" class=""  >更换企业</a>
        </div> 
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2"><em style="color:#ff0000;">*</em>放置场所：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="text" id="site" value="${device.site }" placeholder="" style="width:350px" class="input-text required" maxlength="80" >
        </div> 
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">4G号码：</label>
        <div class="formControls col-xs-8 col-sm-9">
         <input type="text" id="phonename" value="${device.phonename }" placeholder="" style="width:350px" class="input-text required" maxlength="11" >
        </div> 
      </div>
     
    <!-- </form> -->
    <div class="row cl" style="margin-top:50px;">
	    <a class="btn btn-primary radius" href="javascript:void(0);" id="addDeviceButton" type="button" style="margin-left:200px;padding: 0 30px;height: 30px;line-height: 30px;">保存</a>
	    <a  class="btn btn-default radius" href="javascript:location.reload()" type="button" style="margin: 0 20px;padding: 0 30px;height: 30px;line-height: 30px;">取消</a>
    </div>
    
  </div>
  <input type="hidden" id="deviceId" value="${device.id }"/>
  <input type="hidden" id="companyIdVal" value="${device.companyId }"/>
  <input type="hidden" id="companyNameVal" value="${companyName }"/>
  <script type="text/javascript" src="${ly }/js/plugins/juicer/juicer.js"></script>
  <script type="text/javascript" src="${ly }/js/modules/common/common.js"></script>
  <script type="text/javascript" src="${ly }/js/modules/device/addEquipment.js"></script>
</body>
</html>