    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <%@ include file="/WEB-INF/views/taglibs.jsp" %>
        <!DOCTYPE HTML>
        <html>
        <head>
        <meta charset="utf-8">
        <meta name="renderer" content="webkit|ie-comp|ie-stand">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width; initial-scale=0; maximum-scale=1.0; user-scalable=yes" name="viewport" />
        <meta content="yes" name="apple-mobile-web-app-capable" />
        <%@ include file="/WEB-INF/inc/back-header.inc" %>
        <title>安全生产信息智能化管理平台</title>
        <meta name="keywords" content="风险分级管控 隐患排查治理智能化平台">
        <meta name="description" content="风险分级管控 隐患排查治理智能化平台">
        <style type="text/css">
        body {
        margin: 0;
        padding: 0;
        }

        .input-text::-webkit-input-placeholder {
        color: #333;
        }

        .input-text:-moz-placeholder {
        color: #333;
        }

        .input-text::-moz-placeholder {
        color: #333;
        }

        .input-text:-ms-input-placeholder {
        color: #333;
        }

        /* css样式 */
        .container22 {
        width: 70%;
        margin: 0 auto;
        }

        .title {
        text-align: center;
        margin-top:75px;
        }

        .title_img {
        width: 140px;
        position: absolute;
        left:14.1%;
        margin-top: 8px;
        }

        .name {
        font-size: 32px;
        color: red;
        }

        .footer22 {
        padding-top: 30px;
        <%--text-align:center;--%>
        }

        .footer22 > div {
        display: inline-block;
        }

        .footer22 > div:last-child {
        float: right;
        }
        .footer22 a{
        color: #000;
        text-decoration: none;
        }
        .footer22 > div:first-child p:first-child {
        padding-top: 0;
        margin: 0;
        padding-bottom: 10px;
        }

        .footer22 > div:first-child p:first-child a {
        text-decoration: none;
        color: #111;
        font-weight: 600;
        padding: 0 13px;
        font-size: 17px;
        }

        .footer22 > div:first-child p:first-child a:first-child {
        padding-left: 0;
        }

        .footer22 span {
        margin-left: 30px;
        }

        .center {
        position: relative;
        }

        .center > img {
        width: 100%;
        }

        .center .container22 {
        position: absolute;
        top: 0;
        left: 15%;
        width: 80%;
        padding-top: 5%;
        }

        .row_right22 {
        width: 25%;
        height: 55px;
        margin-top: 12px;
        }

        .row_right22 input {
        background: #fff;
        font-size: 16px;
        padding: 8px;
        height: 44px !important;
        border: 0;
        box-shadow: inset 8px 10px 12px #959497;
        width: 100%;
        }
        input[type="checkbox"]{
        height: 15px;
        box-shadow:none;
        width: 15px;
        vertical-align: middle;
        }
        .center_container22{
        height: auto;
        }
        .name2{
        color: #1A69DF;margin-bottom: 50px;
        }
        .right_yuan{
        position: absolute;
        right: 9%;
        }
        .name2 {
        font-size: 48px!important;
        font-weight: 600!important;
        letter-spacing: 4px
        }
        @media screen and (min-width: 1671px) {
        .center .container22 {
        padding-top:5% !important;
        }
        .name2{
        font-size:45px;
        }
        .right_yuan{
        top: 183px;
        }
        }
        @media (min-width: 1367px) and (max-width: 1670px) {
        .center .container22 {
        padding-top:3% !important;
        }
        .name2{
        font-size:40px;
        }
        .right_yuan{
        top: 180px;
        }
        }
        @media screen and (max-width: 1366px) {
        .center .container22 {
        padding-top:1% !important;
        }
        .name2{
        font-size:37px;
        }
        .right_yuan{
        top: 175px;
        }
        }
        .yuan{
        width:25px;height:25px;display:inline-block;border-radius:50%;
        float: right;
        margin-left: 10px;
        }
        </style>



        <script type="text/javascript">
        var url = '${url}';
        $(function() {
        document.querySelector('#remember').checked = true

        var name = getCookie('name');
        var pwd = getCookie('pwd');
        if (null != name && '' != name) {
        $("#userName").val(name);
        $('.name').append(name)
        }else{
        $('.name').append('无锡南方石油添加剂有限公司')
        <%--无锡南方石油添加剂有限公司--%>
        }
        if (null != pwd && '' != pwd) {
        $("#password").val(pwd);
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


        if ($("#remember").is(":checked")) {
        setCookie("name", $("#userName").val());
        setCookie("pwd", $("#password").val());
        }else{
        setCookie("name", '');
        setCookie("pwd", '');
        }
        //$("#login_btn").attr("disabled", "disabled");
        $.post(getRootPath() + "/steel/glogin", {
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
          if(result.map.userType==10&&result.map.userId==35346){
              window.location.href = '/steel/main';
          }
       /* if(result.map.userType == '3') {
        window.location.href = getRootPath() + '/global/main?userType=3';
        }
        if(result.map.userType == 6) {
        window.location.href = getRootPath() + '/global/main?userType=6';
        }
        if(result.map.userType == 9) {
        window.location.href = getRootPath() + '/global/main?userType=9';
        }
        if(result.map.userType == 10) {
        window.location.href = getRootPath() + '/global/main?userType=10';
        }*/
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
        <div class="container22 title">
        <div>
            <img src="${ly }/images/home/logo.png" class="title_img">
            <div>
            <p class="name"></p>
            <p class="name2">安全生产信息智能化管理平台 </p>
            <span class="right_yuan">
            <span class="yuan" style="background:#00A1E9"></span>
            <span class="yuan" style="background:#FFFF00"></span>
            <span class="yuan" style="background:#FF9600"></span>
            <span class="yuan" style="background:#FF0000"></span></span>
            </div>
        </div>
            </div>
            <div class="center_container22">
            <div class="center">
            <img src="${ly }/images/home/qy_loginBg.png" alt="">
            <div class="container22">
            <form class="form form-horizontal" method="post">
            <div class="row_right22">
            <input id="userName" type="text" placeholder="请输入账户" class="input-text size-L" required>
            </div>
            <div class="row_right22">
            <input id="password" type="password" placeholder="请输入密码" class="input-text size-L" required>
            </div>
            <div class="row_right22">
            <input id="login_btn" type="button" class="btn btn-success size-L"
            style="width:100%;background:#00FF00;color:#111;border:0px;height:50px;font-size:24px;box-shadow: none;"
            value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
            </div>
            <div class="row_right22" style="margin-top:3px;">
            <label style="color: white;float:right;" class="forgetpwd"><input id="remember" type="checkbox"
            value=""/><b>&nbsp;记住账号</b>
            </label>
            </div>
            </form>
            </div>
            </div>
            </div>
            <div class="container22 footer22">
            <div>
            <p>
            <a href="${ly }/html/fore.html?id=1" target="_blank">关于我们</a><font>|</font>
            <a href="${ly }/html/fore.html?id=2" target="_blank">网站声明</a><font>|</font>
            <a href="${ly }/html/fore.html?id=3" target="_blank">联系我们</a><font>|</font>
            <a href="http://new.cnzz.com/v1/login.php?siteid=1273938480" target="_blank">站长统计</a>
            </p>
            <p><a href="">主办方：江苏安泰信息技术发展有限公司<span>联系电话：0510-83556999</span></a></p>
            <p><a href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=32020602000446"
            target="_blank">苏公网安备：32020602000446号</a><span>增值电信业务经营许可证：苏B1-20150079</span></p>
            <p><a href="http://www.miitbeian.gov.cn/" target="_blank">苏ICP备17056652号-1</a></p>
            </div>
            <div>
            <img alt="" src="${ly }/images/xcx.jpg" style="width:110px;margin-right: 70px;">
            <img alt="" src="${ly }/images/ewm.png" style="width:110px;">
            </div>
            </div>
        </body>
        </html>
