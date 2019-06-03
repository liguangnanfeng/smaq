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
body .footer{background:none;border-top:0px;}
body .header{background:none;border:0;text-align:center;top:30%;margin-top:-170px;color:#fff;font-size:35px;}
.input-text::-webkit-input-placeholder { color:#333;}
.input-text:-moz-placeholder {color:#333;}
.input-text::-moz-placeholder {color:#333;}
.input-text:-ms-input-placeholder {color:#333;}
.btn_zhuc{float:right;margin:10px;color:#2472D4;font-size:15px;}
</style>
<script type="text/javascript">
  var url = '${url}';
  $(function() {
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
      /* if ($("#validateCode").val() == '') {
        $("#validateCode").focus();
        return false;
      } */

      if ($("#online").is(":checked")) {
        setCookie("user-name", $("#userName").val());
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
            refreshcode();
            layer.close(index);
          });
          return false;
        } else {
          if (url != '') {
            window.location.href = url;
          } else {
            if(result.map.userType == '4') {
              window.location.href = getRootPath() + '/village/main';
            } else {
              window.location.href = getRootPath() + '/town/main';
            }
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
<body>
<table style="float:left;width:100%;height:100%">
<tr>
<td valign="middle">
 <div style="float:left;width:100%;">
 <div style="width:1366px;margin:auto;">
 <div class="div_login">
    <img alt="" src="${ly }/images/llogo.jpg" class="img_dlogo"/>
    <img alt="" src="${ly }/images/dit.png" class="img_dldt"/>
  </div>
  </div>
  </div>
  <div style="float:left;width:100%;background:#1A69DF;margin-top:35px;">
 <div style="width:1366px;margin:auto;">
  <div class="div_login">
    <div class="div_lomain">
      <form class="form form-horizontal" method="post">
        <div class="toplogin">
           <a href="" class="a_login2" style="width: 400px;margin-left: 117px;">监管端</a>
         </div>
         <div class="div_row">
           <div class="row_left">&nbsp;</div>
           <div class="row_right">
             <input id="userName" type="text" placeholder="请输入账户" class="input-text size-L" required>
           </div>
         </div>
         <div class="div_row">
           <div class="row_left">&nbsp;</div>
           <div class="row_right">
             <input id="password" type="password" placeholder="请输入密码" class="input-text size-L" required>
           </div>
         </div>
         <%-- <div class="div_row">
           <div class="row_left">&nbsp;</div>
           <div class="row_right">
             <input class="input-text size-L" id="validateCode" type="text" placeholder="验证码" style="width:165px;" required> 
             <a id="kanbuq" href="javascript:void(0)" onclick="refreshcode()"><img src="${ly }/imageServlet" id="code_img" style="height:45px;margin:0 8px">&nbsp;<img alt="" src="${ly }/images/back/sxb.png"></a>
           </div>
         </div> --%>
         <div class="div_row">
           <div class="row_left">&nbsp;</div>
           <div class="row_right">
             <input id="login_btn" type="button" class="btn btn-success size-L" style="width:100%;background:#092B5B;border:0px;height:50px;font-size:20px"
              value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;"> 
           </div>
         </div>
         <div class="div_row">
           <div class="row_left">&nbsp;</div>
           <div class="row_right">
            <a href="${ly }/fore/town" class="a_ruk">政府端入口</a>
            <a href="${ly }/fore/company" class="a_ruk">企业端入口</a>
            <%-- <a href="${ly }/fore/toRegister" class="a_ruk" style="float:left;">企业注册</a> --%>
           </div>
         </div>
      </form>
    </div>
    <img alt="" src="${ly }/images/ewm.png" class="img_dewm">
  </div>
</div>
</div>
<div style="float:left;width:100%;">
 <div style="width:1366px;margin:auto;">
  <div class="div_login">
    <div class="div_lomain">
      <font class="font_bz">运行方     江苏安泰信息科技发展有限公司</font>
    </div>
  </div>
  </div>
</div>
</td></tr></table>
</body>
</html>