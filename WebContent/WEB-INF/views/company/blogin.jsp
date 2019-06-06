    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <%@ include file="/WEB-INF/views/taglibs.jsp" %>
        <!DOCTYPE HTML>
        <html>
        <head>
        <meta charset="utf-8">
        <meta name="renderer" content="webkit|ie-comp|ie-stand">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=0, maximum-scale=1.0, user-scalable=yes" name="viewport" />
        <meta content="yes" name="apple-mobile-web-app-capable" />
        <%@ include file="/WEB-INF/inc/back-header.inc" %>
        <title>风险分级管控 隐患排查治理智能化平台</title>
        <meta name="keywords" content="风险分级管控 隐患排查治理智能化平台">
        <meta name="description" content="风险分级管控 隐患排查治理智能化平台">
        <style type="text/css">
        body .footer{background:none;border-top:0px;}
        body .header{background:none;border:0;text-align:center;top:30%;margin-top:-170px;color:#fff;font-size:35px;}
        .input-text::-webkit-input-placeholder { color:#333;}
        .input-text:-moz-placeholder {color:#333;}
        .input-text::-moz-placeholder {color:#333;}
        .input-text:-ms-input-placeholder {color:#333;}
        .btn_zhuc{float:right;margin:10px;color:#2472D4;font-size:15px;}
        .div_row .input-text{
        background:#fff;
        }
        .font_w{
        color:#111;
        font-weight: bolder;
        font-size: 15px;}
        .one-f{
        padding-left: 10%;
        text-align:left;
        width:65%;
        }
        .img_dewm{
        position: absolute;
        top: 12px;
        bottom:0;
        }
        .div_lomain{
        margin-left:0;
        margin-top:0;
        }
        .name{color: red;font-size: 30px;letter-spacing: 0.1em;}
        @media screen and (min-width:1621px){
        .max_wi{
        position: absolute;top: 33%;right:48%
        }
        }
        @media screen and (min-width:1320px) and (max-width: 1620px) {
        .max_wi {
        position: absolute;top: 34%;right:44.5%
        }
        }
        @media screen and (max-width: 1320px) {
        .max_wi {
        position: absolute;top: 34%;right:0;left:0;
        }
        }
        </style>
        <script type="text/javascript">
        var url = '${url}', un = '${un}';
        $(function() {
        document.querySelector('#remember').checked = true
        var name = getCookie('user-name');
        var pwd = getCookie('user-pwd');
        if (null != name && '' != name) {
        console.log(name)
        $("#userName").val(name);
        $(".name").append(name);
        }else{
        $(".name").append('无锡南方石油添加剂有限公司');
        }
        if (null != pwd && '' != pwd) {
        $("#password").val(pwd);
        }
        <%--        if(un != '') {--%>
        <%--        $("#userName").val(un);--%>
        <%--        } else {--%>
        <%--        var name = getCookie('user-name');--%>
        <%--        if (null != name && '' != name) {--%>
        <%--        $("#userName").val(name);--%>
        <%--        }--%>
        <%--        }--%>

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


        if ($("#remember").is(":checked")) {
        setCookie("user-name", $("#userName").val());
        setCookie("user-pwd", $("#password").val());
        }else{
        setCookie("user-name", '');
        setCookie("user-pwd", '');
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
        <img alt="" src="${ly }/images/home/logo.png" class="img_dlogo" style="width:140px;max-width:
        48%;margin-top:0;position: absolute;left:4%;top:4%;margin-left:0;"/>
        <table style="float:left;width:100%;height:100%">
        <tr>
        <td valign="middle">
        <div style="float:left;width:100%;margin-top: 10px;">
        <div style="max-width:1366px;min-width:1000px;margin:auto;">
        <div class="div_login">
        <div style="display: inline-block;width: 100%;text-align: center;">
        <p class='name'></p>
        <p style="color: #1A69DF;font-size: 45px;">风险分级管控 隐患排查治理智能化平台</p>
        </div>
        <%--        <img alt="" src="${ly }/images/llogo.jpg" class="img_dlogo" style="max-width: 48%;margin-left: 13%;"/>--%>
        <%--<img alt="" src="${ly }/images/dit.png" class="img_dldt" style="top: 73px;max-width: 47%;"/>--%>
        </div>
        </div>
        </div>
        <div style="float:left;width:100%;margin-top:20px;">
        <img src="${ly }/images/home/qy_loginBg.png" style="width: 100%;position: relative;z-index: 0;">
        <div class="max_wi">
        <div class="div_login">
        <div class="div_lomain">
        <form class="form form-horizontal" method="post">
        <div class="toplogin">
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
        <div class="div_row">
        <div class="row_left">&nbsp;</div>
        <div class="row_right">
        <input id="login_btn" type="button" class="btn btn-success size-L"
        style="width:100%;background:#00FF00;color:#111;border:0px;height:50px;font-size:20px"
        value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
        </div>
        </div>
        <div class="div_row">
        <div class="row_left">&nbsp;</div>
        <div class="row_right">
        <label style="color: white;float:right;" class="forgetpwd"><input id="remember" type="checkbox"
        value=""/><b>&nbsp;记住账号</b> </label>
        </div>
        </div>
        </form>
        </div>
        </div>
        </div>
        </div>
        <div style="float:left;width:100%;">
        <div style="max-width:1366px;min-width:1000px;margin:auto;">
        <div class="div_login">
        <div class="div_lomain2">
        <div class="one-f" style="margin-top:12px;padding-left:11%;">
        <a href="${ly }/html/fore.html?id=1" target="_blank" class="font_w">关于我们</a>
        <font>|</font>
        <a href="${ly }/html/fore.html?id=2" target="_blank" class="font_w">网站声明</a>
        <font>|</font>
        <a href="${ly }/html/fore.html?id=3" target="_blank" class="font_w">联系我们</a>
        <font>|</font>
        <%-- <a href="${ly }/html/tui.html" target="_blank">平台推广</a>
        <font>|</font> --%>
        <a href="http://new.cnzz.com/v1/login.php?siteid=1273938480" target="_blank" class="font_w">站长统计</a>
        </div>
        <div class="one-f">
        <font>主办方：江苏安泰信息技术发展有限公司</font>
        <font>联系电话：0510-83556999</font>
        </div>
        <div class="one-f">
        <font><a href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=32020602000446"
        target="_blank">苏公网安备：32020602000446号</a></font>
        <font>增值电信业务经营许可证：苏B1-20150079</font>
        </div>
        <%--<div class="one-f">--%>
        <%--<font>技术支撑方：无锡同翔科技有限公司</font><font>法律顾问：江苏欣林律师事务所</font>--%>
        <%--</div>--%>
        <div class="one-f">
        <font size="2"><a href="http://www.miitbeian.gov.cn/" target="_blank">苏ICP备17056652号-1</a></font>
        </div>
        <img alt="" src="${ly }/images/xcx.jpg" class="img_dewm" style="width:121px;right:200px">
        <img alt="" src="${ly }/images/ewm.png" class="img_dewm">
        </div>
        </div>
        </div>
        </div>
        </td></tr></table>
        <%--<script src="https://s19.cnzz.com/z_stat.php?id=1273938480&web_id=1273938480" language="JavaScript"></script>--%>
        </body>
        </html>
