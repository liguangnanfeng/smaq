<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<%@ include file="/WEB-INF/inc/header.inc"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>kindEditor</title>
<link type="text/css" rel="stylesheet" href="${ly }/js/kindeditor/themes/default/default.css" />
<script type="text/javascript" src="${ly }/js/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" src="${ly }/js/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript">
 /* 1.编辑器  */
  KindEditor.ready(function(K) {
     var editor = K.create('textarea[name="content"]', {
         width: "750px",
         height: "400px",
         resizeMode : 1 ,//编辑器只能调整高度
         allowFileManager : true,
         uploadJson : getRootPath() + '/UploadServlet',
         allowFileManager : true
     });
 
  /* 2. 图片上传*/
   var editor1 = K.editor({
       allowFileManager : true,
       uploadJson : getRootPath() + '/UploadServlet',
   });
  
   K('#image1').click(function() {
       editor1.loadPlugin('image', function() {
           editor1.plugin.imageDialog({
               imageUrl : K('#url1').val(),
               clickFn : function(url, title, width, height, border, align) {
                   K('#url1').attr("src",url);
                   editor1.hideDialog();
               }
           });
       });
   });

 
   K('#image2').click(function() {
       editor1.loadPlugin('image', function() {
           editor1.plugin.imageDialog({
               showLocal : false,
               imageUrl : K('#url2').val(),
               clickFn : function(url, title, width, height, border, align) {
                   K('#url2').val(url);
                   editor1.hideDialog();
               }
           });
       });
   });
   K('#image3').click(function() {
       editor1.loadPlugin('image', function() {
           editor1.plugin.imageDialog({
               showRemote : false,
               imageUrl : K('#url3').val(),
               clickFn : function(url, title, width, height, border, align) {
                   K('#url3').val(url);
                   editor1.hideDialog();
               }
           });
       });
   });
   
   /* 批量上传 */
     K('#J_selectImage').click(function() {
         editor1.loadPlugin('multiimage', function() {
             editor1.plugin.multiImageDialog({
                 clickFn : function(urlList) {
                     var div = K('#J_imageView');
                     div.html('');
                     K.each(urlList, function(i, data) {
                         div.append('<img src="' + data.url + '">');
                     });
                     editor1.hideDialog();
                 }
             });
         });
     });
 
   K('#insertfile').click(function() {
       editor1.loadPlugin('insertfile', function() {
           editor1.plugin.fileDialog({
               fileUrl : K('#url').val(),
               clickFn : function(url, title) {
                   K('#url').val(url);
                   editor1.hideDialog();
               }
           });
       });
   });
})
</script>
</head>
<body>
  <span>1.</span>
  <textarea id="content_1" name="content" style="width:700px;height:300px;"></textarea>
  <span>2.</span>
  <p>
    <img id="url1" src="" width="200px" height="200px"/>
    <span class="ke-outline"  title="上传图片" id="image1">
      <span class="ke-toolbar-icon ke-toolbar-icon-url ke-icon-image" style="cursor: pointer;"></span>
    </span>
  </p>

  <p>
    <input type="text" id="url2" value="" /> 
    <input type="button" id="image2" value="选择图片" />
  </p>
  <p>
    <input type="text" id="url3" value="" /> 
    <input type="button" id="image3" value="选择图片" />
  </p>
  <span>3.</span>
  <input type="button" id="J_selectImage" value="批量上传" />
  <div id="J_imageView"></div>
  <span>4.</span>
  <input type="text" id="url" value="" /> 
  <input type="button" id="insertfile" value="选择文件" />
</body>
</html>