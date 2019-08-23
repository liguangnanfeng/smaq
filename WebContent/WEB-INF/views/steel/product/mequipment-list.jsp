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
<%-- <%@ include file="/WEB-INF/inc/back-header.inc"%> --%>
<%@ include file="/WEB-INF/inc/print.inc"%>
<script src="${ly }/js/jquery-migrate-1.2.1.min.js"></script>
<script src="${ly }/js/jquery.jqprint-0.3.js"></script>
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
    <span class="c-gray en">&gt;</span> <span>企业基础信息</span> 
    <span class="c-gray en">&gt;</span> <span>主要设备设施</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="text-c">
      <form action="${ly }/tradeclique/product/mequipment-list" method="post">
        <div class="dis-ib">
          <span>名称：</span>
          <input type="text" value="${equipmentName }" name="equipmentName" class="input-text mb-5 mt-5" style="width:150px;">
          </div>
        <button class="btn btn-success" type="submit">
          <i class="Hui-iconfont">&#xe665;</i> 查询
        </button>
      </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l">
        <a class="btn btn-primary radius" data-title="添加主要设备" data-href="${ly }/tradeclique/product/mequipment-add" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 添加主要设备</a>
          <a class="btn btn-primary radius" href="${ly}/village/download?filename=主要设备设施.xlsx&fileurl=${ly}/upload/主要设备设施.xlsx">批量导入主要设备设施下载</a>
          <button class="btn btn-primary radius" type="button" onclick="comapnyImportBoxShow()">导入主要设备</button>
        <a class="btn btn-success radius ml-10" onclick="pr_()"><i class="Hui-iconfont" style="font-size:15px;">&#xe652;</i> 打印</a>
    	<!--<button class="btn btn-success radius ml-10" type="button" onclick="majorequipmentImportBoxShow()">批量导入主要设备</button>
        <a class="btn btn-success radius ml-10" href="${ly}/company/download?filename=主要设备模板.xlsx&fileurl=${ly}/upload/majorequipment.xlsx">主要设备模板下载</a>
         <button onclick="print_()" class="btn btn-primary radius" type="button" style="padding: 0 30px; margin-right: 20px">
          <i class="Hui-iconfont mr-10"></i>打印预览
        </button> -->
      </span>
      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span> 
    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="20%">名称</th>
            <th width="15%">型号规格</th>
            <th width="15%">工艺参数</th>
            <th width="15%">数量</th>
            <th width="20%">备注</th>
            <th width="10%">操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环-->
          <c:forEach items="${list }" varStatus="index" var="equip">
          <tr class="text-c">
            <td>${index.index + 1 }</td>
            <td>${equip.equipmentName }</td>
            <td>${equip.size }</td>
            <td>${equip.processParameters }</td>
            <td>${equip.amount }</td>
            <td>${equip.remark }</td>
            <td>
              <a style="text-decoration:none" onClick="show_tab('编辑主要设备', '${ly }/tradeclique/product/mequipment-edit?id=${equip.id}')" href="javascript:;" title="编辑">查看/编辑</a>
              <a style="text-decoration:none" onClick="del(${equip.id})" href="javascript:;" title="删除">删除</a>
            </td>
          </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
    <div class="mt-20" id="container">
      <h3 class="text-c print-bt" style="width:100%;margin:0px;margin-bottom:20px;float:Left;padding:0;">${session_user.userName }公司主要设备一览表</h3>
      <table class="table table-border table-bordered table-bg table-hover">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="20%">名称</th>
            <th width="15%">型号规格</th>
            <th width="15%">工艺参数</th>
            <th width="15%">数量</th>
            <th width="20%">备注</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环-->
          <c:forEach items="${list }" varStatus="index" var="equip">
          <tr class="text-c">
            <td>${index.index + 1 }</td>
            <td>${equip.equipmentName }</td>
            <td>${equip.size }</td>
            <td>${equip.processParameters }</td>
            <td>${equip.amount }</td>
            <td>${equip.remark }</td>
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
                  <h3 class="modal-title">导入主要设备</h3>
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
<script type="text/javascript">
$(function() {
  $('.table-sort').dataTable({
    "aaSorting": [[0, "asc"]],//默认第几个排序
    "bStateSave": false,//状态保存
    "aoColumnDefs": [
    ]
    });
});
/*批量导入*/
function comapnyImportBoxShow() {

    $("#comapnyImportBox").modal("show");
}
/*删除*/
function del(id){
  $.post(getRootPath()+"/tradeclique/product/deleteEquipment",{
    id:id,
  },function(reuslt){
    location.reload()
  })
}

function majorequipmentImportBoxShow() {
  $("#majorequipmentImportBox").modal("show");
}


function comapnyImportBoxShow(flag) {

    $("#comapnyImportBox").modal("show");
}


function comapnyImportSave() {
    var index = layer.load();
    $.ajaxFileUpload({
        url: getRootPath() + '/steel/importMequipmentExcel',
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

function print_() {
  var obj = $("form").serialize();
  show_dialog("主要设备", getRootPath() + "/tradeclique/product/mequipment-list-print?" + obj);
}
</script> 
</body>
</html>