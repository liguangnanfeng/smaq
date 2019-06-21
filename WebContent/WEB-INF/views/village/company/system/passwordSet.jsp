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
    if ($("#password").val() == $("#newPassword").val()) {
      layer.close(index);
      layer.alert('新密码不能与原密码相同，请重新设置密码');
      return false;
    }
    if ($("#newPassword").val() != $("#comfirmPassword").val()) {
      layer.close(index);
      layer.alert('确认密码和新密码不一致');
      return false;
    }
    $.post(getRootPath() + "/back/modify-pwd",{
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
          top.location.href = getRootPath() + "/fore/companyForeLogout";
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
<nav class="breadcrumb">
  <i class="Hui-iconfont">&#xe67f;</i>
  <span>首页</span>
  <span class="c-gray en">&gt;</span>
  <span>系统管理</span>
  <span class="c-gray en">&gt;</span>
  <span>修改密码</span>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
    <i class="Hui-iconfont">&#xe68f;</i>
  </a>
</nav>
<div class="page-container">
  <form class="form form-horizontal" id="form">
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>旧密码：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="password" id="password" name="password" placeholder="输入原密码" style="width:350px" class="input-text required" maxlength="50">
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>新密码：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="password" id="newPassword" name="newPassword" placeholder="输入新密码" style="width:350px" class="input-text required" maxlength="50">
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>确认密码：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="password" id="comfirmPassword" name="comfirmPassword" placeholder="确认新密码" style="width:350px" class="input-text required" maxlength="50">
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
</html>