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

<link type="text/css" rel="stylesheet" href="${ly }/js/kindeditor/themes/default/default.css" />
    <script type="text/javascript" src="${ly }/js/kindeditor/kindeditor-min.js"></script>
    <script type="text/javascript" src="${ly }/js/kindeditor/lang/zh_CN.js"></script>
<style type="text/css">
body .dis-ib{margin-right:15px;}
.btn-upload{position: relative; display:inline-block;height:36px; *display:inline;overflow:hidden;vertical-align:middle;cursor:pointer}
.upload-url{cursor: pointer}
.input-file{position:absolute; right:0; top:0; cursor: pointer; z-index:1; font-size:30em; *font-size:30px;opacity:0;filter: alpha(opacity=0)}
.btn-upload .input-text{ width:auto}
.form-group .upload-btn{ margin-left:-1px}
</style>
<script type="text/javascript">
var id;
function openzl(){
  id=Id
  $("#upload-zl").modal("show")}
</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>隐患排查治理系统</span> 
    <span class="c-gray en">&gt;</span> <span>隐患排查模板</span>  
    <span class="c-gray en">&gt;</span> <span>隐患排查计划</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="text-c">
    <form action="" method="post">
      <div class="dis-ib">
        <span>资料名称：</span>
        <input type="text" value="${seName}" id="seName" name="seName" class="input-text mb-5 mt-5" style="width:150px;">
      </div>
      <button class="btn btn-success" type="submit">
        <i class="Hui-iconfont">&#xe665;</i> 查询
      </button>
    </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <div class="l">
        <button id="insertfile" class="btn btn-warning radius ml-10" type="button"><i class="Hui-iconfont" style="font-size:15px;">&#xe642;</i> 上传计划文档</button>
      </div>
    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="55%">资料名称</th>
            <th width="20%">上传时间</th>
            <th width="20%">操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环-->
          <c:forEach items="${list }" varStatus="index" var="list">
          <tr class="text-c">
              <td>${list.id}</td>
              <td>${list.name}</td>
              <td><fmt:formatDate value="${list.createTime}" pattern="yyyy-MM-dd"/></td>
              <td>
                <c:if test="${!empty list.url}">
                  <a style="text-decoration:none" href="${ly}/common/download?filename=${list.name}&fileurl=${list.url}" title="查看资料"  target="_blank">查看资料</a>
                </c:if>
                  <a style="text-decoration:none" onclick="del(${list.id})" href="javascript:;" title="删除">删除</a>
              </td>
          </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
  </div>
  <!-- 弹窗上传资料 -->
  <div id="upload-zl" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header">
                <h3 class="modal-title">上传资料</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body">
              <div class="row cl mt-15">
                <label class="form-label col-xs-4 col-sm-2" style="width:20%;text-align:right;">资料上传：</label>
                <div class="formControls col-xs-8 col-sm-9" style="width:70%;">
                  <span class="btn-upload form-group">
                    <input class="input-text upload-url" type="text" name="uploadfile-1" id="uploadfile-1" readonly><a href="javascript:void();" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe642;</i> 浏览文件</a>
                    <input type="file" multiple name="file" id="file"  class="input-file">
                  </span>
                </div> 
              </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" onclick="upload()" >确定</button>
                <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>
  <script type="text/javascript">
$(function() {
  $('.table-sort').dataTable({
    "aaSorting": [[0, "asc"]],//默认第几个排序
    "bStateSave": false,//状态保存
    searching: false,
    ordering:  false,
    "aoColumnDefs": [
    ]
    });
});

/*删除*/
function del(id) {
    $.post(getRootPath() + "/company/tables/h1-del", {
        id: id,
    }, function(reuslt) {
        location.reload()
    })
}

KindEditor.ready(function(K) {

  var editor = K.editor({
      uploadJson: getRootPath() + '/UploadServlet'
  });

K('#insertfile').click(function() {
      editor.loadPlugin('insertfile', function() {
                editor.plugin.fileDialog({
                    fileUrl: K('#url').val(),
                    clickFn: function(url, title) {
                      $.post(getRootPath() + "/company/evaluate/tab-she-add", {
                    name: title,
                    isType: 51,
                    url: url,
                    type: 1
                }, function(result) {
                        show_tab("隐患排查计划", getRootPath() + '/company/danger/tab-plan');
                })
              }
          });
      });
  });
})
</script>
</body>
</html>