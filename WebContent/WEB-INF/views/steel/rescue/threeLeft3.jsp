    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <%@ include file="/WEB-INF/views/taglibs.jsp" %>
        <!doctype html>
        <html lang="en">
        <head>
        <meta charset="UTF-8">
        <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Document</title>
        <style>
        ul{
        text-align:center;
        margin:0;
        padding:0;
        margin-top:18%;
        display:flex;
        justify-content: center;
        <%--        background: #efefef;--%>
        }
        ul li{
        display: inline-block;
        list-style-type: none;
        <%--        border-right: 1px solid #efefef;--%>
        text-align: center;
        padding: 10px 20px;
		margin-left: 12px;
        height: 4.2vw;
        line-height: 4.2vw;
        width: 15%;
        background:#5A98DE;
        color:#fff;
        border-radius:3px;
        }
        ul li:hover{
        background:#3275BF;
        }
        <%--        ul li:last-child{--%>
        <%--        border:none;--%>
        <%--        }--%>
        ul li a{
        text-decoration: none;
        display: block;
        text-align: center;
        width: 100%;
        padding-left: 0 !important;
        color: #111;
        font-size: 18px;
        }
        ul li a img{
        display:block;
        }
        ul li a{
        color:#fff;
        }
        .children_div{
        display:none;
        }
        .children_div ul{
        background:#fff;
        text-align:center;
        }
        .children_div ul li{
        border:none;
        }
        .children_div ul li a{
        color:#999;
        }
        .children_div ul li:hover{
        border-bottom:1px solid rgb(35,126,255);
        color:#333;
        }
        .children_div ul li a:hover{
        color:#333;
        }
        .three>ul:nth-child(2){
        margin-top:3%;
        }
        .fanhui{
        text-decoration: none;
        padding-left: 20px;
        color: #333;
        }
        .fanhui:hover{
        color:rgb(35,126,255);
        }
        </style>
        </head>
        <body style="margin:0;padding:0;">
        <script>
          console.log("threeleft!");
        </script>
        <div class="three">
        <!--组织架构-->
            <ul>
                <li><a href="${ly}/village/to_monitor-list?flag=2" data-title="企业实时监控" href="javascript:void(0)">企业实时监控</a></li>
                <li><a href="#" data-title="指挥中心与企业现场信息互动" href="javascript:void(0)">指挥中心与企业现场信息互动</a>
                <li><a href="#" data-title="应急响应模块" href="javascript:void(0)">应急响应模块</a>
                <li><a href="#" data-title="九宫格和页面缩放" href="javascript:void(0)">九宫格和页面缩放</a>
                <li><a href="#" data-title="数据存储" href="javascript:void(0)">数据存储</a>
                <li><a href="#" data-title="会议中心" href="javascript:void(0)">会议中心</a>
            </li>
            </ul>










        </div>
        </body>
        </html>
        <script>
        function firstAcitive(v){
        var str = document.querySelector('.children_div')
        v.parentNode.parentNode.style.display="none"
        if(str.style.display=="block"){
        str.style.display="none"
        }else{{
        str.style.display="block"
        }
        }
        }

        localStorage.setItem('areaType', 2);
        <%--function threeActive(v){--%>
        <%--var str = document.querySelectorAll('.children_div')--%>
        <%--v.classList.add('active')--%>
        <%--var arr = v.parentNode.parentNode--%>
        <%--arr.style.display = "none"--%>
        <%--if(v.innerText=='教育培训'){--%>
        <%--str[0].style.display = 'block'--%>
        <%--str[1].style.display = 'none'--%>
        <%--}else if(v.innerText=='设备设施管理'){--%>
        <%--str[0].style.display = 'none'--%>
        <%--str[1].style.display = 'block'--%>
        <%--}--%>

        <%--}--%>
        </script>
