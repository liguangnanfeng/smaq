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
function addyan1(){
  $("#yan1").modal("show")
  }
</script>
</head>
<body>
  <nav class="breadcrumb">
        <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
        <span class="c-gray en">&gt;</span> <span>${flag!=2 ? '隐患排查治理系统' : '行政执法系统'}</span> 
        <c:if test="${flag != 2}">
          <span class="c-gray en">&gt;</span> <span>上传图片视频资料</span> 
        </c:if>
        <c:if test="${flag == 2}">
          <span class="c-gray en">&gt;</span> <span>执法图片视频资料</span> 
        </c:if>
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
        <div class="text-c">
          <form action="" method="post">
            <div class="dis-ib">
                <span>企业名称：</span>
                <input type="text" value="${companyName }" name="companyName" class="input-text mb-5 mt-5" style="width:230px;">
            </div>
            <button class="btn btn-success" type="submit">
              <i class="Hui-iconfont">&#xe665;</i> 查询
            </button>
          </form>
        </div>
        <div class="cl pd-5 bg-1 bk-gray mt-20">
            <c:if test="${((session_user.userType >= 6 || session_user.userName eq '港口') && flag ==2) || flag ==3 || (session_user.userType < 6 && flag ==4)}">
            <div class="l">
                <button class="btn btn-warning radius ml-10" type="button" onClick="addyan1()"><i class="Hui-iconfont" style="font-size:15px;">&#xe642;</i> 上传资料</button>
            </div>
            </c:if>
            <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span>
        </div>
        <div class="mt-20">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
                <thead>
                    <tr class="text-c">
                        <th width="5%">序号</th>
                        <th width="30%">企业</th>
                        <th width="35%">资料名称</th>
                        <th width="10%">上传时间</th>
                        <th width="20%">操作</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- 循环-->
                       <c:forEach items="${list }" varStatus="index" var="list">
                    <tr class="text-c">
                        <td>${index.index+1 }</td>
                        <td>${list.companyName}</td>
                        <td>${list.name}</td>
                        <td><fmt:formatDate value="${list.createTime}" pattern="yyyy-MM-dd"/></td>
                        <td>
                            <c:if test="${!empty list.docUrl}">
                            <a style="text-decoration:none" href="${ly}/common/download?filename=${list.docName}&fileurl=${list.docUrl}" title="查看资料" target="_blank">查看资料</a>
                            </c:if>
                            <c:if test="${session_user.userType >= 6}">
                            <a style="text-decoration:none" onclick="del(${list.id})" href="javascript:;" title="删除">删除</a>
                            </c:if>
                        </td>
                    </tr>
                    </c:forEach>
                    <!-- 循环结束 -->
                </tbody>
            </table>
        </div>
  </div>
  
<!-- 弹窗预案 -->
<div id="yan1" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 90%;">
        <div class="modal-content radius">
            <div class="modal-header">
                <h3 class="modal-title">执法文书</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body">
                <div class="row cl">
                  <label class="form-label col-xs-4 col-sm-2" style="width:20%;text-align:right;line-height: 41px;">企业搜索：</label>
                  <div class="formControls col-xs-8 col-sm-9" style="width:40%;">
                  <input type="search" id="companyName" class="input-text mb-5 mt-5" placeholder="请输入企业名称进行过滤">
                  </div> 
                  <button type="button" class="btn btn-primary radius" onclick="search()">搜索</button>
                </div>
                <div class="row cl mt-15">
                  <label class="form-label col-xs-4 col-sm-2" style="width:20%;text-align:right;">所属企业：</label>
                  <div class="formControls col-xs-8 col-sm-9" style="width:70%;max-height: 219px;overflow: auto;" id="load">
                    <p>请输入企业名称搜索企业</p>
                  </div> 
                </div>
                
                <script type="text/javascript">
                function search() {
                  if($("#companyName").val() == '') {
                    layer.alert("请输入企业名称进行查询");
                    return false;
                  }
                  $("#load").load("/global/companyLoad", {
                    companyName : $("#companyName").val()
                  })
                }
                </script>
                
                <div class="row cl mt-15">
                    <label class="form-label col-xs-4 col-sm-2" style="width:20%;text-align:right;line-height: 31px;">文件上传：</label>
                    <div class="formControls col-xs-8 col-sm-9" style="width:70%;">
                        <input type="text" id="modal_docName" style="width: 200px" class="input-text required" readonly="readonly" />
                        <input type="hidden" id="modal_docUrl" />
                        <button id="insertfile" class="btn btn-warning radius ml-10" type="button">
                            <i class="Hui-iconfont" style="font-size: 15px;">&#xe642;</i> 上传资料
                        </button>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" onClick="form_submit()">确定</button>
                <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>
</body>
  <script type="text/javascript">
  var flag_ = '${flag}';
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
                              K('#modal_docName').val(title);
                              K('#modal_docUrl').val(url);
                              editor.hideDialog();
                    }
                });
            });
        });
    })
    
    //添加
    function form_submit(){
          if ($(":radio:checked").length == 0) {
            layer.alert("请选择所属企业");
            return false;
        }
        if ($("#modal_docName").val() == '') {
            layer.alert("上传文件不能为空", function(i) {
                layer.close(i);
                $("#modal_docName").focus();
            })
            return false;
        }
        if ($("#modal_docUrl").val() == '') {
        layer.alert("上传文件不能为空", function(i) {
            layer.close(i);
            $("#modal_docUrl").focus();
        })
        return false;
    }
       $.post(getRootPath() + "/village/evaluate/lawDoc-add",{
         userId: $(":radio:checked").val(),
         name: $("#modal_docName").val(),
         docName: $("#modal_docName").val(),
         docUrl: $("#modal_docUrl").val(),
         flag : flag_
       },function(result){
         $("form").submit();
       })
    }
    
    /* $(function() {
      $('#search').bind('input propertychange ',function(){
        var x = $(this).val();
        $(".radio-box").each(function() {
          var name = $(this).find("label").text();
          if(name.indexOf(x) >= 0) {
            $(this).show();
          } else {
            $(this).hide();
          }
        })
      });
  }) */
</script>
</html>