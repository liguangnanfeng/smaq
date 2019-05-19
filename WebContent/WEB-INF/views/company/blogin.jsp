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
        * {
            margin: 0;
            padding: 0;
        }

        body {
            width: 100%;
        }

        .container {
            width: 80%;
            margin: 0 auto;
        }

        .container_100 {
            width: 100%;
            margin: 0 auto;
        }

        a {
            text-decoration: none;
            cursor: pointer;
        }

        .header {
            border-bottom: 2px solid #999;
            background: #F7F7F7;
            height: 35px;
        }

        .header .container ul {
            text-align: right;
            line-height: 35px;
        }

        .header ul li {
            display: inline-block;
            padding: 0 10px;
        }

        .header ul span {
            color: #999;
        }

        .header ul li a {
            font-size: 13px;
            color: #666;
        }

        .header_two {
            height: 75px;
            line-height: 75px;
        }

        .header_two img, .header_two ul {
            display: inline-block;
        }

        .header_two img {
            float: left;
            height: 70px;
            margin-top: 4px;
        }

        .header_two ul {
            float: right;
        }

        .header_two ul li {
            display: inline-block;
            padding: 0 35px;
        }

        .header_two ul li a {
            color: #666;
            letter-spacing: 0.3px;
            font-size: 20px;
        }

        .header_two li a.active {
            color: #08388E;
        }

        .first_form {
            width: 100%;
            background: url("${ly }/images/home/banner.png") no-repeat no-repeat;
            background-size: 100% 100%;
            height: 400px;
        }

        .first_form .container > div {
            display: inline-block;
            width: 49%;
        }

        .first_form .container > div:first-child {
            float: left;
            padding-top: 30px;
        }

        .first_form .container > div p {
            color: #fff;
        }

        .first_form .container > div p:first-child {
            font-size: 40px;
            letter-spacing: 2px;
            margin-top: 20px;
        }

        .first_form .container > div p:nth-child(2) {
            font-size: 27px;
            margin-top: 12px;
            letter-spacing: 1px;
        }

        .first_form .container > div p:last-child {
            font-size: 13px;
            margin-top: 10px;
        }

        .first_form .container > div img {
            margin-top: 30px;
        }

        .first_form .container > div:last-child {
            margin-top: 30px;
            background: rgba(82,102,15,0.5);
            padding: 20px;
            width: 31%;
            text-align: center;
            float: right;
        }

        .first_form .container > div:last-child form {
            width: 289px;
            margin: 0 auto;
        }

        .first_form .container > div:last-child form button {
            width: 289px;
            height: 40px;
            line-height: 40px;
            color: #fff;
            background: #007EC4;
            border: none;
            font-size: 17px;
            letter-spacing: 2px;
        }

        .first_form .container > div:last-child form .form_p {
            width: 100%;
            text-align: right;
            padding: 10px 0;
        }

        .first_form .container > div:last-child>p {
            font-size: 27px !important;
            margin-top: 0 !important;
            margin-bottom: 30px;
        }
        .first_form .container > div:last-child form .form_p a {
            color: #fff;
            font-size: 13px;
        }

        .first_form .container > div:last-child form .form_p a:first-child {
            float:left;
        }



        .first_form .container > div:last-child form p:last-child {
            width: 289px;
            text-align: center;
        }
        .first_form .container > div:last-child form p:last-child a{
            color: #fff;
        }

        .first_form .container > div:last-child label {
            width: 45px;
            height: 45px;
            background: #373737;
            display: inline-block;
            border: none;
            float: left;
            text-align: center;
        }

        .first_form .container > div:last-child input {
            height: 45px;
            width: 220px;
            padding: 0 11px;
            border: none;
        }

        .first_form .container > div:last-child label img {
            width: 40px;
            margin-top: 3px;
        }

        .container_3 ul li {
            display: inline-block;
            width: 32%;
            text-align: center;
        }

        .container_3 ul li:first-child {
            text-align: left;
        }

        .container_3 ul li:last-child {
            text-align: right;
        }

        .div_con {
            border-bottom: 1px solid #999;
        }

        .div_con li {
            padding: 30px 0;
        }

        .div_con li p {
            line-height: 18px;
        }

        .div_con li p:first-child {
            font-weight: 600;
            font-size: 20px;
            color: #5B5B5B;
            line-height: 35px;
        }

        .div_con li p:nth-child(2), .div_con li p:nth-child(3), .div_con li p:nth-child(4) {
            font-size: 13px;
            color: #555;
        }

        .div_con li p:nth-child(2), .div_con li p:nth-child(3) {
            line-height: 17px;
        }

        .div_con li p:nth-child(4) {
            line-height: 35px;
        }

        .div_con li p:nth-child(4) span img {
            vertical-align: middle;
        }

        .div_foot {
            height: 180px;
            line-height: 180px;
            color: #5B5B5B;
            font-weight: 600;
            font-size: 20px;
        }

        .div_foot img {
            vertical-align: middle;
            margin-right: 7px;
        }

        .footer {
            box-shadow: 0 -1px 0 #999;
            background: #EDEDED;
            height: 45px;
            line-height: 45px;
            position: inherit;
        }

        .footer ul li, .footer ul, .footer p {
            display: inline-block;
            font-size: 13px;
            color: #555;
        }

        .footer p {
            float: right;
        }

        .footer ul li {
            padding: 0 20px;
        }

    </style>
    <script type="text/javascript">
        var url = '${url}';
        $(function() {
            console.log(111)
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
                console.log($("#userName").val())
                console.log($("#password").val())
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
            $('#dl').click(function(){
                window.location.href = getRootPath() + '/village';
            })
        });
        function refreshcode() {
            $('#code_img').attr('src', getRootPath() + '/imageServlet?time=' + new Date().getTime());
        }
    </script>
</head>
<body>

<div class="container_100">
    <div class="container header_two">
        <img src="${ly }/images/home/safe365.jpg" alt="">
        <ul>
            <li><a href="#" class="active">首页</a></li>
            <li><a href="#">企业数据</a></li>
            <li><a href="#">风险分级</a></li>
            <li><a href="#">隐患排查</a></li>
        </ul>
    </div>
</div>
<div class="first_form container_100">
    <div class="container">
        <div>
            <p>风险管控，隐患治理</p>
            <p>智能平台，高速应急处理</p>
            <img src="${ly }/images/home/er.png" alt="">
            <p>扫描二维码下载APP</p>
        </div>
        <div>
            <p>登录安全365智能应急</p>
            <form>
                <label for="name"><img src="${ly }/images/home/ren.png" alt=""></label>
                <input id="userName" type="text" placeholder="请输入账户" required>
                <br />
                <div style="height: 10px"></div>
                <label for="pwd"><img src="${ly }/images/home/suo.png" alt=""></label>
                <input id="password" type="password" placeholder="请输入密码" required>
                <br />
                <p class="form_p"><a href="#" id="dl">登录政府端</a><a href="#">忘记密码？</a></p>
                <button type="submit" id="login_btn">登录</button>
                <p><a href="#">免费注册</a></p>
            </form>
        </div>
    </div>
</div>
<div class="container container_3 div_con">
    <ul>
        <li>
            <p>重点企业实时监督</p>
            <p>加强构建管理信息化平台</p>
            <p>提升管理水平保障实时监督</p>
            <p>
                <span><img src="${ly }/images/home/anquan.png" alt=""></span>
                安全检查监管监控
            </p>
        </li>
        <li>
            <p>风险分级管理</p>
            <p>对照风险因素辨识作业指导书</p>
            <p>逐一辨识出各个环节中存在或可能的风险</p>
            <p>
                <span><img src="${ly }/images/home/zhuanye.png" alt=""></span>
                科学的风险研判
            </p>
        </li>
        <li>
            <p>隐患排查治理</p>
            <p>开展隐患排查治理专项行动</p>
            <p>落实企业的安全生产主体责任</p>
            <p>
                <span><img src="${ly }/images/home/fangbian.png" alt=""></span>
                事前排查风险
            </p>
        </li>
    </ul>
</div>
<div class="container container_3 div_foot">
    <ul>
        <li>
            <img src="${ly }/images/home/1.png" alt="">
            重点监控企业数据库
        </li>
        <li>
            <img src="${ly }/images/home/2.png" alt="">
            安全检查监管
        </li>
        <li>
            <img src="${ly }/images/home/3.png" alt="">
            风险分级管控
        </li>
    </ul>
</div>
<div class="container_100 footer">
    <div class=" container">
        <ul>
            <li>关于我们</li>
            <span> | </span>
            <li>网站声明</li>
            <span> | </span>
            <li>联系我们</li>
            <span> | </span>
            <li>站长统计</li>
        </ul>
        <p>江苏安泰信息技术发展有限公司 苏公网安备：32020602000446号 增值电信业务经营许可证：苏B1-20150079</p>
    </div>
</div>
</body>
</html>
