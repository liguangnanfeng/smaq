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
</head>
<script type="text/javascript">
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
  $("#type2").hide();
  $("#type3").hide();
  $("#type4").hide();
  if($("#type").val() == '2'){
    $("#type4").show();
  }
  if($("#type").val() == '3'){
    $("#type2").show();
    if($("#type2").val() == '3' || $("#type2").val() == '2'){
      $("#type3").show();
    }
  }

$("#type").change(function(){
  if($(this).val() == '2') {
    $("#type2").hide();
    $("#type3").hide();
    $("#type4").show();
  } 
  if($(this).val() == '1') {
    $("#type2").hide();
    $("#type3").hide();
    $("#type4").hide();
  } 
  if($(this).val() == '3'){
    $("#type2").show();
    $("#type3").hide();
    $("#type4").hide();
  }
})

$("#type2").change(function(){
  if($(this).val() == '1') {
    $("#type3").hide();
  } else {
    $("#type3").show();
  }
})
});
function save(id) {
  if($("#title").val() == '') {
    layer.alert("标题不能为空",function(i) {
      layer.close(i);
      $("#title").focus();
    })
    return false;
  }
  if(editor.html() == '') {
    layer.alert("请输入详细内容");
    return false;
  }
  
  var type2 = null;;
  if($("#type").val() == '3') {
    if($("#type2").val() == '1') {
      type2 = 10;
    } else {
      type2 = parseInt($("#type2").val() + $("#type3").val());
    }
  }
  if($("#type").val() == '2'){
    type2=parseInt($("#type4").val());
  }
  if($("#sort").val() == '') {
    $("#sort").val(0);
  }
  $.post(getRootPath() + "/back/notice-save",{
    id : id,
    title : $("#title").val(),
    type : $("#type").val(),
    source : $("#source").val(),
    content : editor.html(),
    sort : $("#sort").val(),
    type2 : type2
  }, function (result) {
       show_tab("公告列表", getRootPath() + '/back/notice-list');
     /* parent.close_tab(function() {
       show_tab("公告列表", getRootPath() + '/back/notice-list');
     }); */
  })
}
</script>
<body>
  <nav class="breadcrumb">
    <!-- <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>网站公告</span> 
    <span class="c-gray en">&gt;</span> <span>编辑公告</span>  -->
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <form class="form form-horizontal" id="form">
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">标题：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="text" data-id=""  id="title" value="${notice.title }"  style="width:350px" class="input-text required" >
        </div> 
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">公告类型：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <select id="type" class="sel_area">
            <option value="1" <c:if test="${notice.type == 1}"> selected</c:if>>监管动态</option>
            <option value="2" <c:if test="${notice.type == 2}"> selected</c:if>>安全员之家</option>
            <option value="3" <c:if test="${notice.type == 3}"> selected</c:if>>法律法规标准规范</option>
          </select>
          <!-- 判断选择法律法规标准规范显示 -->
          <select class="sel_area" id="type2" style="display: none;">
            <option value="1" <c:if test="${notice.type2 == 10}"> selected</c:if>>法律</option>
            <option value="2" <c:if test="${notice.type2 > 20 && notice.type2 < 30}"> selected</c:if>>法规</option>
            <option value="3" <c:if test="${notice.type2 > 30}"> selected</c:if>>标准规范</option>
          </select>
          <select class="sel_area" id="type3" style="display: none;">
            <option value="1" <c:if test="${notice.type2 == 21 ||notice.type2 == 31}"> selected</c:if>>危化</option>
            <option value="2" <c:if test="${notice.type2 == 22 ||notice.type2 == 32}"> selected</c:if>>工贸</option>
            <%-- <option value="3" <c:if test="${notice.type2 == 23 ||notice.type2 == 33}"> selected</c:if>>煤矿</option> --%>
            <option value="4" <c:if test="${notice.type2 == 24 ||notice.type2 == 34}"> selected</c:if>>消防</option>
            <option value="5" <c:if test="${notice.type2 == 25 ||notice.type2 == 35}"> selected</c:if>>职卫</option>
          </select>
          <select class="sel_area" id="type4" style="display: none;">
            <option value="1" <c:if test="${notice.type2 == 1}"> selected</c:if>>怎样当好安全员</option>
            <option value="3" <c:if test="${notice.type2 == 3}"> selected</c:if>>一周事故警示</option>          
            <option value="5" <c:if test="${notice.type2 == 5}"> selected</c:if>>安全常识</option>
          </select>
          <!-- 判断结束 -->
        </div> 
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">来源：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="text" id="source" value="${notice.source }" style="width:350px" class="input-text required" >
        </div> 
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">排序（根据大小倒序）：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="text" id="sort" value="${notice.sort}" style="width:350px" class="input-text required ll-numberbox" data-options="min:0,max:99999">
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
          <button onClick="save(${notice.id})" class="btn btn-primary radius" type="button" style="padding: 0 70px;"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
        </div>
      </div>
    </form>
  </div>

</body>
</html>