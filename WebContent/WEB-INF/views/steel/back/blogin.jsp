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
<title>后台登录</title>
<meta name="keywords" content="后台系统">
<meta name="description" content="后台系统">
<style type="text/css">
body .footer{background:none;border-top:0px;}
body .header{background:none;border:0;text-align:center;top:30%;margin-top:-170px;color:#fff;font-size:35px;}
body .loginBox .input-text{border:0px;border-bottom:1px solid #D3D3D3;width:100%;color:#333;height:36px;padding:0 8px;background:none;}
.input-text::-webkit-input-placeholder { color:#999;}
.input-text:-moz-placeholder {color:#999;}
.input-text::-moz-placeholder {color:#999;}
.input-text:-ms-input-placeholder {color:#999;}
body .loginBox .input-text:focus,body .loginBox .input-text:hover{border:0px;border-bottom:1px solid #85A3C5;box-shadow:none;}
body .loginBox .row{width:80%;margin-left:10%}
.font_bl{font-size:40px;color:#fff;float:left;width:100%;text-align:center;text-shadow:5px 5px 5px #66A1B8;margin-bottom:65px; }
</style>
<script type="text/javascript">
  var url = '${url}';
  $(function() {
    var h=$(window).height()-$("#weiz").offset().top;
    $(".font_bl").css("margin-top",h/2);
    var name = getCookie('user-name');
    if (null != name && '' != name) {
      $("#userName").val(name);
    }
    
    $(document).keydown(function(event){ 
      if(event.keyCode==13){ 
        $("#login_btn").click();
      }
    });

    $("#login_btn").click(function() {
      if ($("#userName").val() == '') {
        $("#userName").focus();
        return false;
      }
      if ($("#password").val() == '') {
        $("#password").focus();
        return false;
      }
      if ($("#validateCode").val() == '') {
        $("#validateCode").focus();
        return false;
      }

      if ($("#online").is(":checked")) {
        setCookie("user-name", $("#userName").val());
      }

      $("#login_btn").attr("disabled", "disabled");
      $.post(getRootPath() + "/blogin", {
        userName : $("#userName").val(),
        password : $("#password").val(),
        validateCode : $("#validateCode").val()
      }, function(result) {
        if (result.status == '1') {
          $("#login_btn").removeAttr("disabled");
          layer.alert(result.map.message, {}, function(index) {
            refreshcode();
            layer.close(index);
          });
          return false;
        } else {
          if (url != '') {
            window.location.href = url;
          } else {
            window.location.href = getRootPath() + '/back/main';
          }
        }
      });
    });
  });
  function refreshcode() {
    $('#code_img').attr('src', getRootPath() + '/imageServlet?time=' + new Date().getTime());
  }
</script>
</head>
<body class="bj_log"  style="min-width:1366px;">
    <div style="width:1000px;margin:auto;">
        <font class="font_bl">安全分级管控  隐患排查治理智能化信息平台</font>
        <div id="loginform" class="loginBox" style="float:left;width:50%;padding-top:10px;height:265px;margin-left:25%;margin-top:10px;background:#fff !important">
      <form class="form form-horizontal" method="post">
        <div class="row cl">
          <div class="formControls">
            <input id="userName" type="text" placeholder="账户" class="input-text size-L" required>
          </div>
        </div>
        <div class="row cl">
          <div class="formControls">
            <input id="password" type="password" placeholder="密码" class="input-text size-L" required>
          </div>
        </div>
        <%-- <div class="row cl">
          <div class="formControls" style="margin-left:0px;width:100%">
            <input class="input-text size-L" id="validateCode" type="text" placeholder="验证码" style="width: 50%;" required> 
            <a id="kanbuq" href="javascript:void(0)" onclick="refreshcode()"><img src="${ly }/imageServlet" id="code_img" style="height:30px;margin:0 8px">&nbsp;<img alt="" src="${ly }/images/back/sx.png"></a>
          </div>
        </div> --%>
        <div class="row cl">
          <div class="formControls col-xs-8 col-xs-offset-3" style="margin-left:0px;width:100%">
          <label for="online">
            <input type="checkbox" id="online" checked="checked">&nbsp;&nbsp;记住用户名
          </label>
        </div>
        </div>
        <div class="row cl">
          <div class="formControls col-xs-8 col-xs-offset-3" style="margin-left:0px;width:100%;margin-top:0px;">
            <input id="login_btn" type="button" class="btn btn-success radius size-L"
              value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;" style="background:#2472D4;width:100%">
          </div>
        </div>
      </form>
    </div>
    <div id="weiz" style="float:left;width:100%"></div>
  </div>
  <!-- <div class="footer" style="background-color:none;">Copyright 江苏安泰信息科技发展有限公司  版权所有</div> -->
</body>
</html>