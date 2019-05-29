<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width; initial-scale=0; maximum-scale=1.0; user-scalable=yes" name="viewport" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<%@ include file="/WEB-INF/inc/back-header.inc"%>
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style type="text/css">
.one-f a:hover{color:#333;text-decoration: none}
</style>
<script type="text/javascript">
$(function() {
  $("#login_btn").click(function() {
    if ($("#userName").val() == '') {
      $("#userName").focus();
      return false;
    }
    if ($("#password").val() == '') {
      $("#password").focus();
      return false;
    }
    
    $("#login_btn").attr("disabled", "disabled");
    $.post(getRootPath() + "/fore/blogin", {
      userName : $("#userName").val(),
      password : $("#password").val(),
      /* validateCode : $("#validateCode").val() */
    }, function(result) {
      if (result.status == '1') {
        $("#login_btn").removeAttr("disabled");
        layer.alert(result.map.message, {}, function(index) {
          layer.close(index);
        });
        return false;
      } else {
        window.location.href = getRootPath() + '/gang-list';
      }
    });
  });
});

</script>
</head>
<body>
  <div class="div-lobj2">
      <div class="bt-gangk">惠山港口危险化学品码头实时监控平台</div>
      <form class="form form-horizontal form-gdl" method="post">
        <div class="div-gangk"> 
          <div class="one-gdl">
            <font>用户</font>
            <input id="userName" type="text" placeholder="" class="input-text" required style="outline: none;">
          </div>
          <div class="one-gdl">
            <font>密码</font>
            <input id="password" type="password" placeholder="" class="input-text" required style="outline: none;">
          </div>
          <input id="login_btn" type="button" class="btn-gangdl" value="登录"> 
        </div>
      </form>
  </div>
<script src="https://s19.cnzz.com/z_stat.php?id=1273938480&web_id=1273938480" language="JavaScript"></script>
</body>
</html>