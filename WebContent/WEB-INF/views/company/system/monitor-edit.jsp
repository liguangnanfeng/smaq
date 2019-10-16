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
<title>安全分级管控   隐患排查治理智能化信息平台</title>
<meta name="keywords" content="安全分级管控   隐患排查治理智能化信息平台">
<meta name="description" content="安全分级管控   隐患排查治理智能化信息平台">
<link rel="stylesheet" type="text/css" href="${ly }/js/plugins/webuploader/webuploader.css">
<style type="text/css">
.input-text{position:relative}
.txtarea_sq{height:100px;width:350px;}
.webuploader-pick {
    position: relative;
    display: inline-block;
    cursor: pointer;
    background: #ccc;
    padding: 7px 15px;
    color: #fff;
    text-align: center;
    border-radius: 3px;
    overflow: hidden;
}
</style>
<script type="text/javascript">

</script>
</head>
<body>
<nav class="breadcrumb">
  <i class="Hui-iconfont">&#xe67f;</i>
  <span>首页</span>
  <span class="c-gray en">&gt;</span>
  <span>实时监控</span>
  <span class="c-gray en">&gt;</span>
  <span>添加摄像头</span>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
    <i class="Hui-iconfont">&#xe68f;</i>
  </a>
</nav>
<div class="page-container">
  <form class="form form-horizontal" id="form">
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2"><em style="color:#FF0000;margin-right: 5px;">*</em>重点部位/关键装置：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <!--<input type="text" id="name" value="${m.name }" style="width:350px" class="input-text required"  >-->
      	<input type="text" id="name" value="${m.name}" style="width:350px" readonly="readonly" class="input-text required"  ><!--zhangcl 2018.12.26-->
      </div> 
    </div>
    <div class="row cl" style="display: none;"><!--zhangcl 2018.12.26-->
      <label class="form-label col-xs-4 col-sm-2"><em style="color:#FF0000;margin-right: 5px;">*</em>视频地址：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="code" value="${m.code }" style="width:350px" class="input-text required" placeholder="如：http://www.xxx.m3u8">
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">风险等级：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <%-- <input type="text" id="dangerLevel" value="${m.dangerLevel }" style="width:350px" class="input-text required" > --%>
        <select style="width:350px" class="input-text required" id="dangerLevel">
        	<option <c:if test="${(not empty m.dangerLevel) and (m.dangerLevel == '1')}">selected</c:if> value="1">重大风险</option>
        	<option <c:if test="${(not empty m.dangerLevel) and (m.dangerLevel == '2')}">selected</c:if> value="2">高风险</option>
        	<option <c:if test="${(not empty m.dangerLevel) and (m.dangerLevel == '3')}">selected</c:if> value="3">较大风险</option>
        	<option <c:if test="${(not empty m.dangerLevel) and (m.dangerLevel == '4')}">selected</c:if> value="4">一般风险</option>
        </select>
      </div> 
    </div>
    <%-- <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">用户名：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="user" value="${m.user }" style="width:350px" class="input-text required"  >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">密码：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="psw" value="${m.psw }" style="width:350px" class="input-text required"  >
      </div> 
    </div> --%>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">事故类型：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="accidentType" value="${m.accidentType }" style="width:350px" class="input-text required" >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">编号：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <textarea id="memo" class="textarea txtarea_sq">${m.memo }</textarea>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">应急&nbsp;&nbsp;处置方式：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <textarea id="descr" class="textarea txtarea_sq">${m.descr }</textarea>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">责任人：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="dutyPeople" value="${m.dutyPeople }" style="width:350px" class="input-text required" >
      </div> 
    </div>
    
    <div class="row cl" style="display:none;">
      <label class="form-label col-xs-4 col-sm-2">监控封面上传：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="thumbnail_val" disabled  value="${m.img }" style="width:300px;margin-top: -24px;" class="input-text required" ><a href="javascript:void(0);" id="to_uploader">上传</a>
		<span style="margin-top: -24px;">*请上传宽高比1.2:1的图片   </span>
		   
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">视频设备编号：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="puid" value="${m.puid}" style="width:350px" class="input-text required" >
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">视频通道编号：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="channelId" value="${m.channelId}" style="width:350px" class="input-text required" >
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
</div>
<script type="text/javascript" src="${ly }/js/plugins/webuploader/webuploader.js"></script>
<script type="text/javascript" src="${ly }/js/modules/userGroup/monitorUpload.js"></script>
  <script type="text/javascript">
  var id_ = '${m.id}';
  function save() {
    if($("#code").val() == '') {
      layer.alert("视频地址不能为空",function(i) {
        layer.close(i);
        $("#code").focus();
      })
      return false;
    }
    if($("#accidentType").val() == '') {
      layer.alert("事故类型不能为空",function(i) {
        layer.close(i);
        $("#accidentType").focus();
      })

      return false;
    }
    if($("#memo").val() == '') {
      layer.alert("编号不能为空",function(i) {
        layer.close(i);
        $("#memo").focus();
      })

      return false;
    }
    if($("#descr").val() == '') {
      layer.alert("应急处置方式不能为空",function(i) {
        layer.close(i);
        $("#descr").focus();
      })

      return false;
    }
    if($("#dutyPeople").val() == '') {
      layer.alert("责任人不能为空",function(i) {
        layer.close(i);
        $("#dutyPeople").focus();
      })

      return false;
    }
    if($("#img").val() == '') {
      layer.alert("监控封面不能为空",function(i) {
        layer.close(i);
        $("#img").focus();
      })

      return false;
    }
    if($("#puid").val() == '') {
      layer.alert("视频设备编号不能为空",function(i) {
        layer.close(i);
        $("#puid").focus();
      })

      return false;
    }
    if($("#channelId").val() == '') {
      layer.alert("视频通道编号不能为空",function(i) {
        layer.close(i);
        $("#channelId").focus();
      })

      return false;
    }
//  if($("#code").val() == '') {
//    layer.alert("视频地址不能为空",function(i) {
//      layer.close(i);
//      $("#code").focus();
//    })
//    return false;
//  }
    /* if($("#user").val() == '') {
      layer.alert("用户名不能为空",function(i) {
        layer.close(i);
        $("#user").focus();
      })
      return false;
    } */
   /*  if($("#psw").val() == '') {
      layer.alert("密码不能为空",function(i) {
        layer.close(i);
        $("#psw").focus();
      })
      return false;
    } */
    
    $.post(getRootPath() + "/company/monitor-update",{
      id : id_,
      name : $("#name").val(),
      code : $("#code").val(),
      memo : $("#memo").val(),
      dangerLevel : $("#dangerLevel").children("option:selected").val(),
      descr : $("#descr").val(),
      accidentType : $("#accidentType").val(),
      dutyPeople : $("#dutyPeople").val(),
      img : $("#img").val(),
      puid : $("#puid").val(),
      channelId : $("#channelId").val()
    }, function (result) {
         top.show_tab("实时监控",  getRootPath() +'/village/to_monitor-list?flag=1');//zhangcl 2018.12.26
    })
  }
  </script>
</body>
</html>