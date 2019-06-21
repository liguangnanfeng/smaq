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
body .dis-ib{margin-right:15px;}
</style>
</head>
<body>
<%-- <div id="leadin" class="page-container" style="display:none">
  <form action="${ly }/village/company/company-import" method="post" class="form form-horizontal" id="upload_form" enctype="multipart/form-data">
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-3">上传文件：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <span class="btn-upload form-group">
        <input id="filename"class="input-text upload-url" type="text" readonly nullmsg="请添加附件！" style="width:200px">
        <a href="javascript:void();" class="btn btn-primary radius upload-btn"><i class="Hui-iconfont">&#xe642;</i> 浏览文件</a>
        <input type="file" multiple name="file" id="file" accept="application/msexcel" class="input-file">
        </span> 
      </div>
    </div>
    <div class="row cl">
      <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
        <button onClick="upload();" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 确定</button>
        <button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
      </div>
    </div>
  </form>
</div> --%>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>自评列表</span> 
    <!-- 判断结束 -->
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l">
        <a class="btn btn-primary radius" data-title="上传文件"  onclick="openwin()" href="javascript:;"><i class="Hui-iconfont" style="font-size:15px;">&#xe642;</i> 上传文件</a>
      </span>
      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span> 
    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="70%">文件名称</th>
            <th width="25%">上传时间</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环企业 -->
          <c:forEach items="${list }" varStatus="index" var="z">
          <tr class="text-c">
            <td>${index.index + 1}</td>
            <td><a href="${ly }${z.url}" target="_blank">${z.name }</a></td>
            <td><fmt:formatDate value="${z.create_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
          </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
    <div id="leadin" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
          <div class="modal-content radius">
              <div class="modal-header">
                  <h3 class="modal-title">上传自评</h3>
                  <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
              </div>
              <div class="modal-body">
                <form action="${ly }/village/company/company-import" method="post" class="form form-horizontal" id="upload_form" enctype="multipart/form-data">
                <div class="row cl">
                  <label class="form-label col-xs-4 col-sm-3">上传文件：</label>
                  <div class="formControls col-xs-8 col-sm-9">
                    <span class="btn-upload form-group">
                      <input id="filename"class="input-text upload-url" type="text" readonly nullmsg="请添加附件！" style="width:200px">
                      <a href="javascript:void();" class="btn btn-primary radius upload-btn"><i class="Hui-iconfont">&#xe642;</i> 浏览文件</a>
                      <input type="file" multiple name="file" id="file" accept="application/msexcel" class="input-file">
                    </span> 
                  </div>
                </div>
                </form>
              </div>
              <div class="modal-footer">
                  <button class="btn btn-primary" onClick="upload();">确定</button>
                  <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
              </div>
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
    ordering:  true,
    "aoColumnDefs": [
    ]
    });
});

function openwin(){
  $("#leadin").modal("show")
}

$(function(){
  $("#filename").click(function(){
    $(this).val("");
  })
})
function upload() {
  $.ajaxFileUpload({
      url: getRootPath() + '/company/ping', //用于文件上传的服务器端请求地址
      secureuri: false, //一般设置为false
      fileElementId: 'file', //文件上传空间的id属性  <input type="file" id="file" name="file" />
      dataType: 'json', //返回值类型 一般设置为json
      async: false,
      success: function (result) { //服务器成功响应处理函数
        $(".upload-url").val("");
        if(result.status == '0') {
          layer.alert("导入成功","",function() {
            location.reload();
          })
        } else {
          if(null != result.map.message) {
            layer.alert(result.map.message);
          } else {
            var list = result.map.list;
            layer.alert(list.join("<br>"));
          }
        }
      },
      error: function (data, status, e) {//服务器响应失败处理函数
        alert("文件上传失败");
      }
  })
}
function layer_close(){
  $("#leadin").hide()
}

</script> 
</body>
</html>