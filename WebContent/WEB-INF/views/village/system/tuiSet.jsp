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
<title>后台首页</title>
<meta name="keywords" content="后台首页">
<meta name="description" content="后台首页">
<style type="text/css">
.win1{top:50px;width:94%;left:3%}
.dis-b{float:left;margin-bottom:15px;width:33%}
.dis-b input{margin-left:10px;margin-right:5px;}
.allsq{width:94%;margin-left:3%;}
.list-btn{float:left;width:100%;margin-top:5px;}
</style>
</head>
<body>
<nav class="breadcrumb">
  <i class="Hui-iconfont">&#xe67f;</i>
  <span>首页</span>
  <span class="c-gray en">&gt;</span>
  <span>系统管理</span>
  <span class="c-gray en">&gt;</span>
  <span>推送设置</span>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
    <i class="Hui-iconfont">&#xe68f;</i>
  </a>
</nav>
<div class="page-container">
  <form class="form form-horizontal" id="form">
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">选择用户：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <div class="list-btn">
          <c:set var="ut" value="${session_user.userType }"/>
          <button onClick="chosearea(1)" class="btn btn-success radius mr-15 mb-10" type="button">选择企业</button>
          <c:if test="${ut == 3 or ut == 6 or ut == 9}">
          <button onClick="chosearea(2)" class="btn btn-success radius mr-15 mb-10" type="button">选择村</button>
          </c:if>
          <c:if test="${ut == 6 or ut == 9}">
          <button onClick="chosearea(3)" class="btn btn-success radius mr-15 mb-10" type="button">选择镇</button>
          </c:if>
          <c:if test="${ut == 9}">
          <button onClick="chosearea(4)" class="btn btn-success radius mr-15 mb-10" type="button">选择区</button>
          <button onClick="chosearea(5)" class="btn btn-success radius mr-15 mb-10" type="button">选择行业</button>
          </c:if>
        </div>
        <textarea id="names" placeholder="请选择推送用户" class="textarea txtarea_sq" readonly="readonly" style="width:500px"></textarea>
        <input type="hidden" id="ids"/>
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">上传文档：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <span class="btn-upload form-group">
          <input class="input-text upload-url radius" type="text" name="uploadfile" id="uploadfile" readonly><a href="javascript:void();" class="btn btn-success radius"><i class="Hui-iconfont">&#xe642;</i> 浏览文件</a>
          <input type="file" multiple name="file" id="file" onchange="a_load()" class="input-file">
        </span>
      </div>
    </div>
    <div class="row cl">
      <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
        <button onClick="article_save_submit()" class="btn btn-primary radius mt-20" type="button" style="padding: 0 70px;">
          <i class="Hui-iconfont">&#xe632;</i>保存
        </button>
      </div>
    </div>
  </form>
</div>
</body>
<script type="text/javascript">
//1 发送给企业 2发送给村 3发送给镇 4 区  5行业
function chosearea(t){
  window.type = t;
  var title = "选择企业";
  if(t == 3) title = "选择镇";
  if(t == 4) title = "选择村";
  if(t == 6) title = "选择区";
  if(t == 10) title = "选择行业";
  show_dialog(title, getRootPath() + "/village/tuiSetChoose?t=" + t);
}
var fileUrl = "", fileName = "";
function savetownChoose(ids, names) {
  $("#ids").val(ids);
  $("#names").val(names);
}
function a_load() {
  var i = layer.load();
  $.ajaxFileUpload({
    url: getRootPath() + '/ajaxUploadFile', //用于文件上传的服务器端请求地址
    secureuri: false, //一般设置为false
    fileElementId: 'file', //文件上传空间的id属性  <input type="file" id="file" name="file" />
    dataType: 'json', //返回值类型 一般设置为json
    async: false,
    success: function (result) { //服务器成功响应处理函数
      layer.close(i);
      if (status == '1') {
        layer.alert(map.message);
        return false;
      } else {
        fileUrl = result.map.fileName;
        fileName = result.map.oldName;
        $("#uploadfile").val(fileName);
      }
    },
    error: function (data, status, e) {//服务器响应失败处理函数
      var result = new Object();
      var map = new Object();
      map.message = "图片上传失败";
      result.status = "1";
      result.map = map;
    }
})
}

function article_save_submit() {
  if($("#ids").val() == '') {
    layer.alert("请选择消息接受用户");
    return false;
  }
  if(fileUrl == '') {
    layer.alert("请上传推送的文件");
    return false;
  }
  var i = layer.load();
  $.post(getRootPath() + "/village/pushMessageSave", {
    ids : $("#ids").val(),
    fileName : fileName,
    fileUrl : fileUrl,
    type : type
  },function(result) {
    layer.close(i);
    if(result.status == '1') {
      layer.alert(result.map.message);
    } else {
      layer.alert("推送成功");
    }
  })
}
</script>
</html>