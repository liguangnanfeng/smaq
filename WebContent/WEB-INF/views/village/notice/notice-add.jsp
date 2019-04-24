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
<%@ include file="/WEB-INF/inc/kindeditor.inc"%>
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<script type="text/javascript">
var id = '${notice.id}';
var editor;
KindEditor.ready(function(K) {
  editor = K.create('textarea[name="content"]', {
    width : "900px",
    height : "500px",
    resizeType : 1,//编辑器只能调整高度
    allowFileManager : true,
    allowImageRemote : false,//取消网络图片tab
    uploadJson : getRootPath() + '/UploadServlet',
  });
}); 
  function save() {
    if($("#title").val() == '') {
      layer.alert("公告标题不能为空",function(i) {
        layer.close(i);
        $("#title").focus();
      })
      return false;
    }
    if(editor.html() == '') {
      layer.alert("请输入详细内容");
      return false;
    }
    $.post(getRootPath() + "/village/notice/notice-save",{
      id : id,
      type : 1,
      title : $("#title").val(),
      source : $("#source").val(),
      content : editor.html(),
    }, function (result) {
         show_tab("公告列表", getRootPath() + '/village/notice/notice-list?type=1');
      /*  parent.close_tab(function() {
         show_tab("公告列表", getRootPath() + '/village/notice/notice-list?type=1');
       }); */
    })
  }
</script>
</head>

<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>监管动态</span> 
    <span class="c-gray en">&gt;</span> <span>添加公告</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <form class="form form-horizontal" id="form">
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">公告标题：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="text" id="title" value="${notice.title }"  style="width:350px" class="input-text required" >
        </div> 
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">来源：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="text" id="source" value="${notice.source }" style="width:350px" class="input-text required" >
        </div> 
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">详细内容：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <textarea id="content" name="content" style="width:100%;height:500px;border:1px solid #ccc">${notice.content }</textarea>
        </div> 
      </div>
      <div class="row cl">
        <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
          <button onClick="save()" class="btn btn-primary radius" type="button" style="padding: 0 70px;"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
        </div>
      </div>
    </form>
  </div>
</body>
</html>