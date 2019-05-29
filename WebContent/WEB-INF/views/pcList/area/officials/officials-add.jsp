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
<%@ include file="/WEB-INF/inc/kindeditor.inc"%>
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
</head>
<script type="text/javascript">
var id = '${p.id}';
var userType = ${session_user.userType};
var x = (userType == 3 || userType == 4) ? '检查' : '执法';
function save() {
  if($("#depart").val() == '') {
    layer.alert(x + "部门不能为空");
    return false;
  }
  if($("#name").val() == '') {
    layer.alert(x + "人员不能为空");
    return false;
  }
  if($("#code").val() == '') {
    layer.alert(x + "人员编号不能为空");
    return false;
  }
  var i = layer.load();
  $.post(getRootPath() + "/district/off-save", {
    id : id,
    depart : $("#depart").val(),
    name : $("#name").val(),
    code : $("#code").val()
  },function(result) {
    layer.close(i);
    if(result.status == '') {
      layer.alert(result.map.message);
    } else {
        show_tab(x + "人员信息库", '/district/off-list');
    }
  })
}
</script>
<body>
  <nav class="breadcrumb">
    <%-- <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>${(session_user.userType == 3 || session_user.userType == 4) ? '隐患排查治理系统' : '、行政执法系统'}</span> 
    <span class="c-gray en">&gt;</span> <span>${(session_user.userType == 3 || session_user.userType == 4) ? '检查人员信息库' : '执法人员管理'}</span> --%>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <form class="form form-horizontal" id="form">
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">${(session_user.userType == 3 || session_user.userType == 4) ? '检查' : '执法'}部门：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="text" id="depart" value="${p.depart }" style="width:350px" class="input-text required"  >
        </div> 
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">${(session_user.userType == 3 || session_user.userType == 4) ? '检查' : '执法'}人员：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="text" id="name" value="${p.name }" style="width:350px" class="input-text required"  >
        </div> 
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">${(session_user.userType == 3 || session_user.userType == 4) ? '检查' : '执法'}编号：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="text" id="code" value="${p.code }" style="width:350px" class="input-text required"  >
        </div> 
      </div>
      <div class="row cl">
        <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
          <button onClick="save()" class="btn btn-primary radius" type="button" style="padding: 0 70px;"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
        </div>
      </div>
    </form>
  </div>
</body>
</html>