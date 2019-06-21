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
  var url = '${url}', un = '${un}';
  $(function() {
    $(".div-lobj").height($(window).height());
    var h=$(window).height()-$(".bj-white").height();
    $(".bj-white").css("margin-top",h/2+"px");
    
    if(un != '') {
      $("#userName").val(un);
    } else {
      var name = getCookie('user-name');
      if (null != name && '' != name) {
        $("#userName").val(name);
      }
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
      $.post(getRootPath() + "/fore/companyLogin", {
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
            window.location.href = getRootPath() + '/company/main';
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
  <div class="div-lobj">
    <div class="bj-white">
      <div class="cen-white">
        <div class="img-bjnew"><img src="${ly }/images/backwz.png"/></div>
        <div class="div-ndl">
          <font class="bt-ydl">用户平台登录</font>
          <div class="div-ydl">
            <div class="top-ydl">
              <a href="${ly }/village" class="one-ydl">政府端登录</a>
              <a href="javascript:void(0)" class="one-ydl one-ydlxz" style="margin-right:0;">企业端登录</a>
            </div>
            <div class="div-tdl">
              <form class="form form-horizontal" method="post">
                <div class="one-tdl">
                  <i class="Hui-iconfont">&#xe60d;</i>
                  <input id="userName" type="text" placeholder="请输入账户" class="input-text" required style="outline: none;">
                </div>
                <div class="one-tdl">
                  <i class="Hui-iconfont">&#xe60e;</i>
                  <input id="password" type="password" placeholder="请输入密码" class="input-text" required style="outline: none;">
                </div>
                <input id="login_btn" type="button" class="btn btn-success size-L" style="width:100%;background:#0071DB;border:0px;height:50px;font-size:20px;border-radius:5px;"
              value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;"> 
              </form>
            </div>
          </div>
        </div>
      </div>
      <div class="one-f" style="margin-top:25px;text-align: center">
        <a href="javascript:void(0)">关于我们</a>
        <font>|</font>
        <a href="javascript:void(0)">网站声明</a>
        <font>|</font>
        <a href="javascript:void(0)">联系我们</a>
        <font>|</font>
        <a href="javascript:void(0)">站长统计</a>
      </div>
    </div>
  </div>
<script src="https://s19.cnzz.com/z_stat.php?id=1273938480&web_id=1273938480" language="JavaScript"></script>
</body>
</html>