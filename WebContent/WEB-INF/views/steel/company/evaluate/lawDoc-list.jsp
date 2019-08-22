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
.div_btzl{width:100%;font-size:16px;font-weight:bold;margin-bottom:10px;}
.p_zl{width:100%;text-indent:2em;margin:0;padding:0;margin-bottom:8px;}
</style>
<script type="text/javascript">
var flag = '${flag}';
function showzl(){
  $("#modal-zl").modal("show")}
</script>
</head>
<body>
  <nav class="breadcrumb">
        <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
        <span class="c-gray en">&gt;</span> <span>${flag!=2 ? '隐患排查治理系统' : '行政执法系统'}</span> 
        <span class="c-gray en">&gt;</span> <span>上传图片视频资料</span> 
        <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
    </nav>
    <div class="page-container">
          <c:if test="${flag != 2}">
          <div id="spTab" class="btn-group" style="text-align: center;margin-bottom: 20px;margin-top:30px;">
              <%-- <a class="btn default ${flag == 1 ? 'btn-primary' : 'radius'}" href="${ly }/village/evaluate/lawDoc-list?flag=1">企业自查</a> --%>
              <a class="btn default ${flag == 4 ? 'btn-primary' : 'radius'}" href="${ly }/village/evaluate/lawDoc-list?flag=4">行政检查</a>
              <a class="btn default ${flag == 3 ? 'btn-primary' : 'radius'}" href="${ly }/village/evaluate/lawDoc-list?flag=3">部门抽查</a>
              <%-- <a class="btn default ${flag == 2 ? 'btn-primary' : 'radius'}" href="${ly }/village/evaluate/lawDoc-list?flag=2">执法检查</a> --%>
          </div>
          </c:if>
        <div class="cl pd-5 bg-1 bk-gray mt-20">
            <div class="l">
                <!-- <button class="btn btn-primary radius ml-10" type="button" onclick="showzl()"><i class="Hui-iconfont" style="font-size:15px;">&#xe695;</i> 查看资料</button> -->
                <button id="insertfile" class="btn btn-warning radius ml-10" type="button"><i class="Hui-iconfont" style="font-size:15px;">&#xe642;</i> 上传资料</button>
            </div>
            <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span>
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
                        <td>${index.index+1 }</td>
                        <td>${list.name}</td>
                        <td><fmt:formatDate value="${list.createTime}" pattern="yyyy-MM-dd"/></td>
                        <td>
                            <c:if test="${!empty list.docUrl}">
                            <a style="text-decoration:none" href="${ly}/common/download?filename=${list.docName}&fileurl=${list.docUrl}" title="查看资料" target="_blank">查看资料</a>
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
  <script type="text/javascript">
    $(function() {
        $('.table-sort').dataTable({
            "aaSorting": [
                [0, "asc"]
            ], //默认第几个排序
            "bStateSave": false, //状态保存
            searching: false,
            ordering: false,
            "aoColumnDefs": []
        });
    });

    /*删除*/
    function del(id) {
        $.post(getRootPath() + "/village/evaluate/lawDoc-del", {
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
                          $.post(getRootPath() + "/village/evaluate/lawDoc-add", {
                                name: title,
                                flag : flag,
                              docUrl: url,
                              docName: title
                          }, function(result) {
                            location.reload()
                          })
                        }
                    });
               });
        });
    })
    </script>
</body>
</html>