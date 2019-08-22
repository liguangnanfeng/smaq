        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%@ include file="/WEB-INF/views/taglibs.jsp"%>
    <!DOCTYPE html>
        <html>
        <head>
        <meta charset="utf-8">
        <%@ include file="/WEB-INF/inc/back-header.inc"%>
        <title>应急管理中心</title>
<%--        <script src="https://cdn.bootcss.com/vue/2.6.10/vue.min.js"></script>--%>
        </head>

        <style>
        body {
        width: 100%;
        height: 100vh;
        margin: 0;
        padding: 0;
        }
        .func>section {
        height: 77px;
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: 18px;
        margin-right: 31px;
        font-weight: bold;
        }
            <%--        background-color: #f95b26;--%>
            <%--        background-image: linear-gradient(180deg, #f95b26 10%, #fb801b 100%);--%>
        .func {
            position: relative;
            display: flex;
            justify-content: center;
        }

        a {
        text-decoration: none;
        }
            a:hover {
            text-decoration: none;
            color:#FF3939;
            }
        span.time {
            font-size: 25px;
            position: relative;
        }



        span.time2 {
        font-size: 45px;
        color: #fff;
        }
        .com{
            display: flex;
            justify-content: center;
            position: relative;
            top: 10%;
        } main{
            height: 72vh;
            background: url(/images/companyImg/newimg/yj.jpg) no-repeat center;
            background-size: 100% 100%;
<%--            margin-top: 22px;--%>
            position: relative;
            }
            #topleft {
            display: flex;
            position: relative;
            justify-content: space-between;
            padding: 0 70px;
            width: 100%;

            }

            .topleftmod {
            display: flex;
<%--            margin-left: 9%;--%>
            }

            .antai {
            color: #19365F;
            font-size: 24px;
            font-weight: bold;
            }

            .logo {
            margin-left: 10px;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            }
            header{
            height:13%;
            display:flex;
            align-items: center;
            }
        </style>

        <body>
            <header>
            <div id="topleft">
            <div class="topleftmod">
            <img style="display: flex;" width="60" height="60" src="/images/companyImg/newimg/logo.png" />
            <span class="logo" style="">
            <span class="antai">应急管理中心</span>
            <span class="webadd">www.antai.com</span>
            </span>
            </div>

            <div style="display: flex; align-items: center;">
            <span id="time" class="time"></span>
            </div>

            </div>

            </header>

        <main>
            <div class="com"><span class="time2" style="color:#64FFFF">${userName}</span></div>

            </main>
            <div class="func">
        <section><a href="javascript:volid(0)">应急响应</a></section>
            <section><a href="javascript:volid(0)">指挥中心</a></section>
        <section><a href="/village/to_monitor-list?flag=1" data-title="实时监控">实时监控</a></section>
        <section><a href="/company/threeLeft?leftBasic=11"  data-title="在线监测">在线监测</a></section>
        <section><a href="/village/check-item3?status=2"   data-title="动态预警">预警动态</a></section>
        <section><a href="javascript:volid(0)">应急信息</a></section>
        </div>
        </body>
            <script>
            var endTime = new Date(new Date().getTime());
            console.log(endTime);
            var myDate = new Date();
            var mytime=myDate.toLocaleTimeString();     //获取当前时间
            var a=myDate.toLocaleDateString();      //获取日期与时间
            console.log(a);
            $("#time").text('时间：'+a);
            </script>
        </html>
