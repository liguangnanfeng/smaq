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
body .dis-ib{margin-right:15px;}
</style>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>区县管理</span> 
    <span class="c-gray en">&gt;</span> <span>区县列表</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="text-c">
      <form action="" method="post">
        <div class="dis-ib"><span>登录账号：</span><input type="text" value="${userName }" name="userName" class="input-text mb-5 mt-5" style="width:150px;"></div>
        <div class="dis-ib"><span>区县名称：</span><input type="text" value="${name }" name="name" class="input-text mb-5 mt-5" style="width:150px;"></div>
        <div class="dis-ib">
          <span>运行状态：</span>
          <span class="select-box inline">
          <select name="isFreeze" class="select">
            <option value="">全部</option>
            <option value="1"<c:if test="${isFreeze == '1'}"> selected</c:if>>已冻结</option>
            <option value="0"<c:if test="${isFreeze == '0'}"> selected</c:if>>未冻结</option>
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
        <a class="btn btn-primary radius" data-title="添加区县" data-href="${ly }/back/district-add" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加区县</a>
        <button class="btn btn-success" type="button" onclick="comapnyImportBoxShow(1)">导入安全管理人员名单</button>
        <button class="btn btn-success" type="button" onclick="comapnyImportBoxShow(2)">导入危险化学品安全管理人员名单</button>
        <button class="btn btn-success" type="button" onclick="comapnyImportBoxShow(3)">导入持证上岗人员名单</button>
        <a class="btn btn-success" href="${ly}/back/download?filename=人员模板.xlsx&fileurl=${ly}/upload/spersonmodel.xlsx">人员模板下载</a>
        <button class="btn btn-success" type="button" onclick="sequipmentImportBoxShow()">批量导入特种设备</button>
        <a class="btn btn-success" href="${ly}/back/download?filename=特种设备模板.xlsx&fileurl=${ly}/upload/sequipmentmodel.xlsx">特种设备模板下载</a>
      </span>
      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span> 
    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="15%">登录账号</th>
            <th width="15%">区县名称</th>
            <th width="10%">区县地址</th>
            <th width="20%">下属镇</th>
            <th width="15%">运行状态</th>
            <th width="15%">操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环镇 -->
          <c:forEach items="${list }" varStatus="index" var="to">
          <tr class="text-c">
            <td>${index.index + 1}</td>
            <td>${to.userName}</td>
            <td>${to.name}</td>
            <td>${to.regionName}, ${to.address}</td>
            <td>${to.c }</td>
            <td>${to.isFreeze eq '1' ? '冻结' : '正常'}</td>
            <td>
              <a style="text-decoration:none" onClick="show_dialog('区县信息', '${ly}/back/district-edit?userId=${to.userId }');" href="javascript:;" title="编辑">查看/编辑</a>
              <c:if test="${to.isFreeze == '0'}">
              <a style="text-decoration:none" onClick="user_freeze(${to.userId})" href="javascript:;" title="冻结">冻结</a>
              </c:if>
              <c:if test="${to.isFreeze == '1'}">
              <a style="text-decoration:none" onClick="user_unfreeze(${to.userId})" href="javascript:;" title="解冻">解冻</a>
              </c:if>
              <a style="text-decoration:none" onclick="psw(${to.userId})" href="javascript:;" title="重置密码">重置密码</a>
            </td>
          </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
  </div>
  
  
  
  <div id="comapnyImportBox" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
       <div class="modal-dialog">
           <div class="modal-content radius">
           
               <div class="modal-header">
                   <h3 class="modal-title">导入持证上岗人员名单</h3>
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
                   <button class="btn btn-primary" onclick="comapnyImportSave()">保存</button>
                   <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
               </div>
           </div>
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
                               <input type="file" id="file2" name="file2">
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
    "aoColumnDefs": [
    ]
    });
});

//冻结
function user_freeze(userId){
layer.confirm("确认要冻结该区县吗？",function(){
  var index = layer.load();
  $.post(getRootPath() + "/back/user-freeze",{
    id : userId,
    isFreeze : 1
  },function(result){
    layer.close(index);
    $("form").submit();
  })
});
}

//解冻
function user_unfreeze(userId){
layer.confirm("确认要解冻该区县吗？",function(){
  var index = layer.load();
  $.post(getRootPath() + "/back/user-freeze",{
    id : userId,
    isFreeze : 0
  },function(result){
    layer.close(index);
    $("form").submit();
  })
});
}

//密码重置
function psw(userId){
  layer.confirm("确认要重置密码么？",function(index){
    $.post(getRootPath() + "/back/user-psw",{
      id : userId
    },function(result){
      layer.alert("密码重置为‘123456’");
    });
  });
}


function sequipmentImportBoxShow() {
  $("#seqipumentImportBox").modal("show");
}

function sequipmentImportSave() {
  var index = layer.load();
  $.ajaxFileUpload({
      url: getRootPath() + '/back/importSequipmentExcel',
      secureuri: false, //一般设置为false
      fileElementId: 'file2',
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
          //alert("导入失败！请检查数据");
      }
  })
}

function comapnyImportBoxShow(flag) {
  window.sperson_flag = flag;
  if(flag == 1) {
  $("#comapnyImportBox .modal-title").text("导入安全管理人员");
  }
  if(flag == 2) {
  $("#comapnyImportBox .modal-title").text("导入危险化学品安全管理人员");
  }
  if(flag == 3) {
  $("#comapnyImportBox .modal-title").text("导入持证上岗人员");
  }
  $("#comapnyImportBox").modal("show");
}


function comapnyImportSave() {
  var index = layer.load();
$.ajaxFileUpload({
    url: getRootPath() + '/back/importSpersonExcel?flag=' + sperson_flag,
    secureuri: false, //一般设置为false
    fileElementId: 'file',
    dataType: 'json',
    async: false,
    success: function (data, status) {
        layer.close(index);
        var status = data.status;
        if (status == '0') {
          layer.alert("导入成功");
          $("#comapnyImportBox").modal("hide");
        }else {
          layer.alert(data.map.message);
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