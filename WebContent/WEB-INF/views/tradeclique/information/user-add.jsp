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
</style>
<script type="text/javascript">
function article_save_submit() {
  var index = layer.load();
  if($("#form").valid()) {
    $.post("/back/modify-pwd",{
      password : $("#password").val(),
      newPassword : $("#newPassword").val(),
      comfirmPassword : $("#comfirmPassword").val()
    },function (result) {
      layer.close(index);
      var status = result.status;
      var map = result.map;
      if(status == '1') {
        layer.alert(map.message);
        return false;
      } else {
        layer.alert("操作成功",{},function(ind){
          layer.close(ind);
          top.location.href = "/backLogout";
        });
      }
    });
  } else {
    layer.close(index);
    layer.alert('请完善密码信息');
    return false;
  }
}
</script>
</head>
<body>
<div class="page-container">
  <form class="form form-horizontal" id="form">
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>姓名：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="name" name="name" value="${user.name }" placeholder="输入姓名" style="width:350px" class="input-text required" maxlength="50">
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">职务：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="position" name="position" value="${user.position }" placeholder="输入职务" style="width:350px" class="input-text required" maxlength="50">
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>手机：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="mobile" name="mobile" value="${user.mobile }" placeholder="输入手机号" style="width:350px" class="input-text required" maxlength="50">
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>车间/部位：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <span class="select-box inline"> 
          <select name="cid" class="select" id="cid">
            <option value="">选择所在公司</option>
            <c:forEach items="${companyL }" var="be">
                <option value="${be.id }"<c:if test="${user.cid == be.id}"> selected</c:if>>${be.name }</option>
            </c:forEach>
          </select>
        </span>
        <span class="select-box inline"> 
          <select name="dpid" class="select" id="dpid">
            <option value="">选择车间</option>
          </select>
        </span>
        <span class="select-box inline"> 
          <select name="did" class="select" id="did">
            <option value="">选择部位</option>
          </select>
        </span>
      </div>
    </div>
    <div class="row cl">
      <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
        <button onClick="article_save_submit()" class="btn btn-primary radius" type="button" style="padding: 0 70px;">
          <i class="Hui-iconfont">&#xe632;</i>保存
        </button>
      </div>
    </div>
  </form>
</div>
</body>
<script type="text/javascript">
var id = '${user.id}';
var userType = '${session_user.userType}';
function article_save_submit() {
  var user = {
	  uid : '${userId}',
      name : $("#name").val(),
      position : $("#position").val(),
      mobile : $("#mobile").val(),
      cid : $("#cid").val(),
      dpid : $("#dpid").val(),
      did : $("#did").val()
  }
  if(id != '') {
    user.id = id;
  }
  if(user.name == '') {
    $("#name").focus();
    return false;
  }
  if(user.mobile == '') {
    $("#mobile").focus();
    return false;
  }
  if(user.cid == '') {
    if(userType =='3'){
      layer.alert("请选择单位");
   }else{
     layer.alert("请选择公司");
   }
    return false;
  }
  if(user.dpid == '') {
    if(userType =='3'){
      layer.alert("请选择部门/科室");
   }else{
     layer.alert("请选择车间/部位");
   }
    return false;
  }
  var i = layer.load();
  $.post("/tradeclique/information/user-save", user ,function(result) {
    layer.close(i);
    if(result.status == 1) {
      layer.alert(result.map.message);
    } else {
      if(user.id == '') {
        parent.location.reload();
      } else {
        parent.location.reload();
      }
    }
  })
}

var dep1= ${dep1};
var dep2= ${dep2};
var cid='${user.cid}', dpid = '${user.dpid}', did = '${user.did}';
$(function() {
  $("#cid").change(function() {
    loadDep1($(this).val());
  })
  $("#dpid").change(function() {
    loadDep2($(this).val());
  })
  if(cid != '') $("#cid").val(cid).change();
})

function loadDep1(cid) {
  if(userType =='3'){
    $("#dpid").empty().append('<option value="">所在部门</option>');
 }else{
   $("#dpid").empty().append('<option value="">所在车间</option>');
 }
  if(cid == '') {
    return false;
  }
  $.each(dep1, function(i, item) {
    if(item.cid == cid) {
      $("#dpid").append('<option value="' + item.id + '">' + item.name + '</option>');
    }
  })
  if(dpid != '') {
    $("#dpid").val(dpid).change();
    dpid = '';
  }
}

function loadDep2(pid) {
  if(userType =='3'){
    $("#did").empty().append('<option value="">所在科室</option>');
 }else{
   $("#did").empty().append('<option value="">所在岗位/部位</option>');
 }
  if(pid == '') {
    return false;
  }
  $.each(dep2, function(i, item) {
    if(item.pid == pid) {
      $("#did").append('<option value="' + item.id + '">' + item.name + '</option>');
    }
  })
  if(did != '') {
    $("#did").val(did);
    did = '';
  }
}
</script>
</html>