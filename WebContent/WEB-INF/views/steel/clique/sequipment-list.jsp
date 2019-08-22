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
<%@ include file="/WEB-INF/inc/print.inc"%>
<script src="${ly }/js/jquery-migrate-1.2.1.min.js"></script>
<script src="${ly }/js/jquery.jqprint-0.3.js"></script>
<script src="${ly }/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ly}/js/H-ui.admin_v3.0/lib/layer/2.4/layer.js"></script>
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style type="text/css">
body .dis-ib{margin-right:15px;}
#container{display:none}
.print-bt{display:none;}
</style>
<script type="text/javascript">
function pr_() {
  $("#container").show();
  $(".print-bt").show();
  $("#container").jqprint();
  $("#container").hide();
  $(".print-bt").hide();
}
</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>预警提醒</span> 
    <span class="c-gray en">&gt;</span> <span>特种设备</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="text-c">
      <form action="${ly }/tradeclique/sequipment/sequipment-list" method="post">
        <div class="dis-ib">
        <span>所属企业：</span>
        <input type="text" value="${companyName }" name="companyName" class="input-text mb-5 mt-5" style="width:230px;">
      </div>
        <div class="dis-ib">
          <span>到期时间：</span>
          <span class="select-box inline">
          <select name="isTime" class="select">
            <option value="">全部</option>
            <option value="1"  <c:if test="${1==isTime}">selected</c:if>>1个月内到期</option>
            <option value="2"  <c:if test="${2==isTime}">selected</c:if>>2个月内到期</option>
            <option value="3"  <c:if test="${3==isTime}">selected</c:if>>3个月内到期</option>
            <option value="0" <c:if test="${0==isTime}">selected</c:if>>已过期</option>
          </select>
          </span>
        </div>
        <button class="btn btn-success inline" type="submit">
          <i class="Hui-iconfont">&#xe665;</i> 查询
        </button>
      </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l">
	    <button class="btn btn-primary radius" onclick="showadd('')"><i class="Hui-iconfont">&#xe600;</i> 添加特种设备</button>
	    <a class="btn btn-success radius ml-10" onclick="pr_()"><i class="Hui-iconfont" style="font-size:15px;">&#xe652;</i> 打印</a>
	    <button class="btn btn-success radius ml-10" type="button" onclick="sequipmentImportBoxShow()">批量导入特种设备</button>
	    <a class="btn btn-success radius ml-10" href="${ly}/company/download?filename=特种设备模板.xlsx&fileurl=${ly}/upload/sequipmentmodel.xlsx">特种设备模板下载</a>
      </span>
	  <span class="r">共有数据：<strong>${fn:length(cliqueList)+fn:length(list) }</strong> 条</span>
	</div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="15%">所属企业</th>
            <th width="15%">特种设备名称</th>
            <th width="10%">型号</th>
            <th width="15%">设备档案号</th>
            <!-- <th width="10%">数量</th> -->
            <th width="10%">上次检测时间 </th>
            <th width="10%">到期时间</th>
            <th width="10%">备注</th>
            <th width="10%">操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环-->
          <c:forEach items="${cliqueList }" varStatus="index" var="se">
          <tr class="text-c">
            <td>${index.index + 1 }</td>
            <td>${cliqueName}</td>
            <td>${se.seName }</td>
            <td>${se.type }</td>
            <td>${se.fileNumber }</td>
            <%-- <td>${se.amount }</td> --%>
            <td>${se.detectionTime }</td>
            <td>${se.expirationTime }</td>
            <td>${se.remark }</td>
            <td>
              <a style="text-decoration:none" onClick="edit(${se.id})" href="javascript:;" title="编辑">编辑</a>
              <a style="text-decoration:none" onClick="del(${se.id})" href="javascript:;" title="删除">删除</a>
            </td>
          </tr>
          </c:forEach>
          <c:forEach items="${list }" varStatus="index" var="se">
          <tr class="text-c">
            <td>${fn:length(cliqueList)+index.index + 1 }</td>
            <td>${se.companyName}</td>
            <td>${se.seName }</td>
            <td>${se.type }</td>
            <td>${se.fileNumber }</td>
            <%-- <td>${se.amount }</td> --%>
            <td>${se.detectionTime }</td>
            <td>${se.expirationTime }</td>
            <td>${se.remark }</td>
            <td>
              <a style="text-decoration:none" onClick="edit(${se.id})" href="javascript:;" title="编辑">编辑</a>
              <a style="text-decoration:none" onClick="del(${se.id})" href="javascript:;" title="删除">删除</a>
            </td>
          </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
    <div class="mt-20" id="container"><!-- print -->
      <h3 class="text-c print-bt" style="width:100%;margin:0px;margin-bottom:20px;float:Left;padding:0;">${session_user.userName }公司特种设备一览表</h3>
      <table class="table table-border table-bordered table-bg table-hover">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="10%">所属企业</th>
            <th width="15%">特种设备名称</th>
            <th width="10%">型号</th>
            <th width="15%">设备档案号</th>
            <!-- <th width="10%">数量</th> -->
            <th width="15%">上次检测时间 </th>
            <th width="15%">到期时间</th>
            <th width="10%">备注</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环-->
          <c:forEach items="${cliqueList }" varStatus="index" var="se">
          <tr class="text-c">
            <td>${index.index + 1 }</td>
            <td>${cliqueName}</td>
            <td>${se.seName }</td>
            <td>${se.type }</td>
            <td>${se.fileNumber }</td>
            <%-- <td>${se.amount }</td> --%>
            <td>${se.detectionTime }</td>
            <td>${se.expirationTime }</td>
            <td>${se.remark }</td>
          </tr>
          </c:forEach>
          <c:forEach items="${list }" varStatus="index" var="se">
          <tr class="text-c">
            <td>${fn:length(cliqueList)+index.index + 1 }</td>
            <td>${se.companyName}</td>
            <td>${se.seName }</td>
            <td>${se.type }</td>
            <td>${se.fileNumber }</td>
            <%-- <td>${se.amount }</td> --%>
            <td>${se.detectionTime }</td>
            <td>${se.expirationTime }</td>
            <td>${se.remark }</td>
          </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
    
  </div>
    <div id="seqipumentImportBox" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
       <div class="modal-dialog">
           <div class="modal-content radius">
           
               <div class="modal-header">
                   <h3 class="modal-title">导入特种设备</h3>
                   <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
               </div>


        <div class="modal-body" id="importBox">
                   <div class="one_txt cl">
                       <div class="text-left" style="float: left;width: 70px;">导入文件：</div>
                       <div class="text-right formControls">
                           <span class="btn-upload form-group">
                               <input type="file" id="file" name="file">
                           </span>
                       </div>
                   </div>
               </div>
               <div class="modal-footer">
                   <button class="btn btn-primary" onclick="sequipmentImportSave()">保存</button>
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

function showadd(id){
	show_dialog("添加特种设备", '${ly }/tradeclique/sequipment/sequipment-add');
}

/*编辑*/
function edit(id){
  show_tab("编辑特种设备", getRootPath() + "/tradeclique/sequipment/sequipment-add?id=" + id);
}

/*删除*/
function del(id){
  $.post(getRootPath()+"/tradeclique/sequipment/sequipment-del",{
    id:id,
  },function(reuslt){
    location.reload()
  })
}

function sequipmentImportBoxShow() {
	  $("#seqipumentImportBox").modal("show");
}

function sequipmentImportSave() {
  var index = layer.load();
  $.ajaxFileUpload({
      url: getRootPath() + '/tradeclique/importSequipmentExcel',
      secureuri: false, //一般设置为false
      fileElementId: 'file',
      dataType: 'json',
      async: false,
      success: function (data, status) {
          layer.close(index);
          var status = data.status;
          if (status != '1') {
            layer.alert("导入成功", {}, function (ind) {
                layer.close(ind);
                window.location.reload();
            });
          }else {
            layer.alert(data.map.message ,{}, function (ind) {
              layer.close(ind);
              window.location.reload();
             });
          }
      },
      error: function (data, status, e) {
          layer.close(index);
          alert("导入失败！请检查数据");
      }
  })
}
</script> 
</body>
</html>