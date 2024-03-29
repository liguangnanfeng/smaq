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
.input-text{position:relative}
.txtarea_sq{height:100px;width:350px;}
</style>
<script type="text/javascript">

</script>
</head>
<body>
<nav class="breadcrumb">
  <i class="Hui-iconfont">&#xe67f;</i>
  <span>首页</span>
  <span class="c-gray en">&gt;</span>
  <span>重大危险源源长制</span>
  <span class="c-gray en">&gt;</span>
  <span>管理措施</span>
  <span class="c-gray en">&gt;</span>
  <span>设备设施管理</span>
  <span class="c-gray en">&gt;</span>
  <span>编辑设备</span>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
    <i class="Hui-iconfont">&#xe68f;</i>
  </a>
</nav>
<div class="page-container">
  <form class="form form-horizontal" id="form">
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">名称：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="name" name="" value="${editEquip.name }" style="width:350px" class="input-text required">
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">维护保养检测时间：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="maintenance" value="${editEquip.maintenance }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:350px" class="input-text Wdate mb-5 mt-5">
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">设备状态：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="radio" name="equipmentStatus" id="${editEquip.id }_s" checked="checked" value="1">
        <label for="${editEquip.id }_s">正常</label>
        <input type="radio" name="equipmentStatus" id="${editEquip.id }_f" value="0"> 
        <label for="${editEquip.id }_f">不正常</label>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">主体/责任人：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <span class="select-box inline"> 
          <select name="dp" class="select" id="dp">
            <option value="">选择主体</option>
            <c:forEach items="${dpnames }" var="dpn">
                <option value="${dpn }"<c:if test="${editEquip.dpname == dpn}"> selected</c:if>>${dpn }</option>
            </c:forEach>
          </select>
        </span>
        <span class="select-box inline"> 
          <select name="responsible" class="select" id="responsible">
            <option value="">选择责任人</option>
          </select>
        </span>
      </div> 
    </div>
    <div class="row cl">
      <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
        <button onClick="save()" class="btn btn-primary radius" type="button" style="padding: 0 70px;">
          <i class="Hui-iconfont">&#xe632;</i>保存
        </button>
      </div>
    </div>
  </form>
<script type="text/javascript">
$(function() {
  $("input[name='maintenance'][value='${editEquip.maintenance}']").attr("checked","checked");

});


var id = '${editEquip.id}', type = ${type};
function save() {
  if($("#name").val() == '') {
    layer.alert("名称不能为空",function(i) {
      layer.close(i);
      $("#name").focus();
    })
    return false;
  }

  $.post(getRootPath() + "/company/source/equipment-save" + type,{
  id : id,
  name : $("#name").val(),
  maintenance : $("#maintenance").val(),
  equipmentStatus : $("input[name='equipmentStatus']:checked").val(),
  dpname : $("#dp").val(),
  responsible : $("#responsible").val()
  }, function (result) {
       show_tab("主要设备清单", getRootPath() + '/company/source/equipment?type=' + type);
  })
  
}


var staffs= ${staffs};
var editDp ='${editEquip.dpname}', editRes = '${editEquip.responsible}';
$(function() {
  $("#dp").change(function() {
    loadStaffs($(this).val());
  })
  if(editDp != ''){$("#dp").val(editDp).change();}
})

function loadStaffs(department) {
   $("#responsible").empty().append('<option value="">选择责任人</option>');
  if(department == '') {
    return false;
  }
  $.each(staffs, function(i, item) {
    if(item.dpname == department) {
      $("#responsible").append('<option value="' + item.name + '">' + item.name + '</option>');
    }
  })
  if(editRes != '') {
    $("#responsible").val(editRes).change();
    editRes = '';
  }
}
</script>
  
</div>
</body>
</html>