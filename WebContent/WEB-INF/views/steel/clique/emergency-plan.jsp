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
.clearfix:after{content:"\20";display:block;height:0;clear:both;visibility:hidden}
.clearfix{zoom:1}
.tabBar {border-bottom:0px;text-align:center;background:#fff;height:42px;line-height:42px;margin-top:28px;}
.tabBar .tab {height:42px;line-height:42px;background-color:#E5F3F7;border:0px;font-size:14px;color:#687E99;cursor: pointer;display: inline-block;float: none;font-weight: bold;padding: 0 25px}
.tabBar .tab .current{background-color:#008DFF;color: #fff;border:0px;}
.tabBar .current{background-color:#008DFF;color: #fff;border:0px;}
.tabCon {display: none;padding:0;border:0px;}
body div{width:auto;height:auto;}
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
    <span class="c-gray en">&gt;</span> <span>安全监察监管</span> 
    <span class="c-gray en">&gt;</span> <span>应急预案</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
<div class="page-container">
    <div class="HuiTab">
      <div class="tabBar clearfix">
        <a href="${ly }/tradeclique/clique/emergency-plan" class="tab current" style="border-radius:5px 0 0 5px;">应急预案</a>
        <a class="tab" href="${ly }/tradeclique/clique/emergency-drill" style="border-radius:0 5px 5px 0;">应急预案演练</a>
      </div>
      <div class="text-c mt-20">
        <form action="${ly }/tradeclique/clique/emergency-plan" method="post">
          <div class="dis-ib">
            <span>企业名称：</span>
             <input type="text" value="${companyName }" name="companyName" class="input-text mb-5 mt-5" style="width:150px;">
          </div>
          <div class="dis-ib">
            <span>应急预案名称：</span>
             <input type="text" value="${name }" id="" name="name" class="input-text mb-5 mt-5" style="width:150px;">
          </div>
          <div class="dis-ib">
            <span>到期时间：</span>
            <span class="select-box inline">
            <select name="isTime" class="select">
              <option value="">全部</option>
              <option value="6" <c:if test="${6==isTime}">selected</c:if>>6个月内到期</option>
              <option value="0" <c:if test="${0==isTime}">selected</c:if>>已过期</option>
            </select>
            </span>
          </div>
          <button class="btn btn-success" type="submit">
            <i class="Hui-iconfont">&#xe665;</i> 查询
          </button>
        </form>
       </div>
       <div class="cl pd-5 bg-1 bk-gray mt-20">
         <span class="l">
           <a class="btn btn-primary radius" onclick="addyan1()" href="javascript:;"><i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 添加应急预案</a>
         </span>
         <span class="r">共有数据：<strong>${fn:length(cliqueList)+fn:length(list) }</strong> 条</span> 
       </div>
       <div class="mt-20">
         <table class="table table-border table-bordered table-bg table-hover table-sort">
           <thead>
             <tr class="text-c">
               <th width="5%">序号</th>
               <th width="10%">应急预案名称</th>
               <th width="10%">所属企业</th>
               <th width="10%">颁布时间</th>
               <th width="10%">备案时间</th>
               <th width="10%">备案有效期</th>
               <th width="10%">操作</th>
             </tr>
           </thead>
           <tbody>
           	<!-- 循环开始 -->
           	<c:forEach items="${cliqueList }" varStatus="index" var="clist">
             <tr class="text-c">
               <td>${index.index+1 }</td>
               <td>${clist.name }</td>
               <td>${clique.name }</td>
               <td>${clist.createTime }</td>
               <td>${clist.filingTime }</td>
               <td>${clist.activeTime }</td>
               <td>
                 <c:if test="${!empty clist.docName}">
                 <a style="text-decoration:none" href="${ly}/common/download?filename=${clist.docName}&fileurl=${clist.docUrl}" title="查看预案文档" target="_blank" >查看预案文档</a><br/>
                 </c:if>
                 <c:if test="${!empty clist.djbName}">
                 <a style="text-decoration:none" href="${ly}/common/download?filename=${clist.djbName}&fileurl=${clist.djbUrl}" title="查看备案登记表" target="_blank" >查看备案登记表</a><br/>
                 </c:if>
                 <a style="text-decoration:none" onClick="del(${clist.id })" href="javascript:;" title="删除">删除</a>
               </td>
             </tr>
            </c:forEach>
            
			<c:forEach items="${list }" varStatus="index" var="list">
             <tr class="text-c">
               <td>${fn:length(cliqueList)+index.index+1 }</td>
               <td>${list.name }</td>
               <c:if test="${c != 1}">
               <td>${list.companyName }</td>
               </c:if>
               <td>${list.createTime }</td>
               <td>${list.filingTime }</td>
               <td>${list.activeTime }</td>
               <td>
                 <c:if test="${!empty list.docName}">
                 <a style="text-decoration:none" href="${ly}/common/download?filename=${list.docName}&fileurl=${list.docUrl}" title="查看预案文档" target="_blank" >查看预案文档</a><br/>
                 </c:if>
                 <c:if test="${!empty list.djbName}">
                 <a style="text-decoration:none" href="${ly}/common/download?filename=${list.djbName}&fileurl=${list.djbUrl}" title="查看备案登记表" target="_blank" >查看备案登记表</a><br/>
                 </c:if>
                 <a style="text-decoration:none" onClick="del(${list.id })" href="javascript:;" title="删除">删除</a>
               </td>
             </tr>
            </c:forEach>
			<!-- 循环结束 -->
           </tbody>
         </table>
       </div>
    </div>
  </div>
   
  <!-- 弹窗预案 -->
  <div id="yan1" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
          <div class="modal-header">
              <h3 class="modal-title">应急预案</h3>
              <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
          </div>
          <div class="modal-body">
	       <div class="row cl mt-15">
			 <label class="form-label col-xs-4 col-sm-2" style="width:20%;text-align:right;">所属企业：</label>
			 <div class="formControls col-xs-8 col-sm-9" style="width:70%;">
			   <select id="uid" name="uid" style="width:350px" class="input-text required">
			     <option value="">请选择</option>
			     <option value="${clique.userId }">${clique.name }</option>
			     <c:forEach items="${branches }" varStatus="index" var="br">
			     	<option value="${br.userId }">${br.name }</option>
			     </c:forEach>
			    </select>
			  </div> 
			</div>
            <div class="row cl mt-15">
              <label class="form-label col-xs-4 col-sm-2" style="width:20%;text-align:right;">预案名称：</label>
              <div class="formControls col-xs-8 col-sm-9" style="width:70%;">
                <input type="text" id="modal_name"  value="" style="width:350px" class="input-text required">
              </div> 
            </div>
            <div class="row cl mt-15">
              <label class="form-label col-xs-4 col-sm-2" style="width:20%;text-align:right;">颁布时间：</label>
              <div class="formControls col-xs-8 col-sm-9" style="width:70%;">
                <input type="text" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="modal_createTime" class="input-text Wdate mb-5 mt-5" style="width:350px;">
              </div> 
            </div>
            <div class="row cl mt-15">
              <label class="form-label col-xs-4 col-sm-2" style="width:20%;text-align:right;">备案时间：</label>
              <div class="formControls col-xs-8 col-sm-9" style="width:70%;">
                <input type="text" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="modal_filingTime" class="input-text Wdate mb-5 mt-5" style="width:350px;">
              </div> 
            </div>
            <div class="row cl mt-15">
              <label class="form-label col-xs-4 col-sm-2" style="width:20%;text-align:right;">备案有效期：</label>
              <div class="formControls col-xs-8 col-sm-9" style="width:70%;">
                <input type="text" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="modal_activeTime" class="input-text Wdate mb-5 mt-5" style="width:350px;">
              </div> 
            </div>
            <div class="row cl mt-15">
              <label class="form-label col-xs-4 col-sm-2" style="width:20%;text-align:right;">预案上传：</label>
              <div class="formControls col-xs-8 col-sm-9" style="width:70%;">
				<input type="text" id="modal_docName" style="width: 200px" class="input-text required" readonly="readonly" />
				<input type="hidden" id="modal_docUrl" />
				<button id="insertfile" class="btn btn-warning radius ml-10" type="button">
				  <i class="Hui-iconfont" style="font-size: 15px;">&#xe642;</i> 上传资料
				</button>
              </div> 
            </div>
            <div class="row cl mt-15">
              <label class="form-label col-xs-4 col-sm-2" style="width:20%;text-align:right;">备案登记表：</label>
              <div class="formControls col-xs-8 col-sm-9" style="width:70%;">
                <input type="text" id="djb_name" style="width: 200px" class="input-text required" readonly="readonly" />
                <input type="hidden" id="djb_url" />
                <button id="insertfile1" class="btn btn-warning radius ml-10" type="button">
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

//添加
function form_submit(){
	if ($("#uid").val() == '') {
	    layer.alert("所属公司不能为空", function(i) {
	        layer.close(i);
	        $("#uid").focus();
	    })
	    return false;
	}
  	if ($("#modal_name").val() == '') {
	    layer.alert("预案名称不能为空", function(i) {
	        layer.close(i);
	        $("#modal_name").focus();
	    })
	    return false;
	}
	if ($("#modal_createTime").val() == '') {
	    layer.alert("颁布时间不能为空", function(i) {
	        layer.close(i);
	        $("#modal_createTime").focus();
	    })
	    return false;
	}
	if ($("#modal_filingTime").val() == '') {
	    layer.alert("备案时间不能为空", function(i) {
	        layer.close(i);
	        $("#modal_filingTime").focus();
	    })
	    return false;
	}
	if ($("#modal_activeTime").val() == '') {
	    layer.alert("备案有效期不能为空", function(i) {
	        layer.close(i);
	        $("#modal_activeTime").focus();
	    })
	    return false;
	}
   $.post(getRootPath() + "/tradeclique/clique/emergency-plan-add",{
	 userId: $("#uid").val(),
     name: $("#modal_name").val(),
     createTime: $("#modal_createTime").val(),
     filingTime: $("#modal_filingTime").val(),
     activeTime: $("#modal_activeTime").val(),
     docName: $("#modal_docName").val(),
     docUrl: $("#modal_docUrl").val(),
     djbName : $("#djb_name").val(),
     djbUrl : $("#djb_url").val(),
   },function(result){
     location.reload();
   })
}

/*删除*/
function del(id){
  $.post(getRootPath()+"/tradeclique/clique/emergency-plan-del",{
    id:id,
  },function(reuslt){
    location.reload()
  })
}

KindEditor.ready(function(K) {
  var editor = K.editor({
    uploadJson : getRootPath() + '/UploadServlet'
  });
  K('#insertfile').click(function() {
    editor.loadPlugin('insertfile', function() {
      editor.plugin.fileDialog({
        fileUrl : K('#modal_docName').val(),
        clickFn : function(url, title) {
          docUrl = url;
          K('#modal_docName').val(title);
          K('#modal_docUrl').val(url);
          editor.hideDialog();
        }
      });
    });
  });
  K('#insertfile1').click(function() {
    editor.loadPlugin('insertfile', function() {
      editor.plugin.fileDialog({
        fileUrl : K('#djb_name').val(),
        clickFn : function(url, title) {
          docUrl = url;
          K('#djb_name').val(title);
          K('#djb_url').val(url);
          editor.hideDialog();
        }
      });
    });
  });
});
</script> 
</body>
</html>