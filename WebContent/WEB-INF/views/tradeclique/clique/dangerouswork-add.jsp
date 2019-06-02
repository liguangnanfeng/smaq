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
<div class="page-container">
  <form class="form form-horizontal" id="form">
  	<div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">所属企业：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <select id="uid" name="uid"  style="width:350px" class="input-text required">
          <option value="">请选择</option>
          <c:forEach items="${branches }" varStatus="index" var="br">
          	<option value="${br.userId }">${br.name }</option>
          </c:forEach>
        </select>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">高危作业：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <c:forEach items="${ind3L }" var="be">
        <div class="one_yxuan">
          <input class="check_danger" type="checkbox" name="dangers" value="${be.name }"/>
          <font>${be.name }</font>
        </div>
        </c:forEach>
        <!-- 循环结束 -->
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
  function save() {
	if($("#uid").val() == '') {
	  layer.alert("请选择所属企业",function(i) {
	    layer.close(i);
	    $("#uid").focus();
	  })
	  return false;
	}
	var dangers = '';
	$(":checkbox[name='dangers']:checked").each(function(){
	  var v = $(this).val();
	  dangers = dangers == '' ? v : dangers + "," + v;
	})
    $.post(getRootPath() + "/tradeclique/dangerouswork-save",{
    	userId  : $("#uid").val(),
    	dangers : dangers

    }, function (result) {
         show_tab("高危作业", getRootPath() +'/tradeclique/dangerouswork-list');
    })
  }
  </script>
  
</div>
</body>
</html>