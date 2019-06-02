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
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>企业信息库</span> 
    <span class="c-gray en">&gt;</span> <span>添加企业</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <form class="form form-horizontal" id="form">
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">企业名称：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="text" id="userName" name="userName" placeholder="" style="width:350px" class="input-text required" maxlength="50" >
        </div> 
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">所在地：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <select id="villageId" class="sel_area">
            <c:forEach items="${list }" var="be">
            <option value="${be.userId }">${be.name }</option>
            </c:forEach>
          </select>
        </div> 
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">密码：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="password" id="password" name="password" placeholder="" style="width:350px" class="input-text required" maxlength="50" >
        </div> 
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">确认密码：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="password" id="coPassword" name="password" placeholder="" style="width:350px" class="input-text required" maxlength="50" >
        </div> 
      </div>
      <div class="row cl">
        <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
          <button onClick="save()" class="btn btn-primary radius" type="button" style="padding: 0 70px;"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
        </div>
      </div>
    </form>
  </div>
<script type="text/javascript">
function save() {
  if($("#userName").val() == '') {
    layer.alert("企业名不能为空");
    return false;
  }
  if($("#villageId").val() == '') {
    layer.alert("请选择所在地");
    return false;
  }
  if($("#password").val() == '') {
    layer.alert("密码不能为空");
    return false;
  }
  if($("#coPassword").val() == '') {
    layer.alert("确认密码不能为空");
    return false;
  }
  if($("#coPassword").val() != $("#password").val()) {
    layer.alert("密码不一致");
    return false;
  }
  $.post(getRootPath() + "/village/company/company-save",{
    userName : $("#userName").val(),
    psw : $("#password").val(),
    villageId : $("#villageId").val()
  }, function (result) {
    if(result.status == '1') {
      layer.alert(result.map.message);
      return false;
    }
       show_tab("企业列表", getRootPath() +'/village/company/company-list');
  })
}
</script> 
</body>
</html>