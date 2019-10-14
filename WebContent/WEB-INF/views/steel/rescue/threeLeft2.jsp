    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <%@ include file="/WEB-INF/views/taglibs.jsp" %>
        <!doctype html>
        <html lang="en">
        <head>
        <meta charset="UTF-8">
        <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link src="https://sec.dicarl.com/js/H-ui.admin_v3.0/lib/Hui-iconfont/1.0.8/iconfont.css">
        <title>Document</title>
        <style>
        .three{
         display:flex;
        height: 100vh;
        }
        .right-arrow{
        content: " ";
        height: 10px;
        width: 10px;
        border-width: 1.5px 1.5px 0 0;
        border-color: #fff;
        border-style: solid;
        transform: rotate(45deg);
        position: absolute;
        top: 27%;
        right: 3.8%;
        }

        #ul{
        margin: 0!important;
        background:#EBEEF3;
        justify-content: normal!important;
        }
        #ul li{
        margin-top: 24px;
        }
        #ul >li >a{
        color: #333!important;
        font-weight: bold;
        font-size: 15px;
        }
        #ul li:hover{
        background:#fff!important;
        }
        ul{
        text-align: center;
        margin: 0;
        padding: 0;
        margin-top: 1%;
        display: flex;
        justify-content: space-evenly;
        flex-direction: column;
        width:240px;
        }
        ul li{
        display: inline-block;
        list-style-type: none;
        text-align: center;
        padding: 3px 0px;
        margin-left: 12px;
        height: 2.2vw;
        line-height: 2.2vw;
        width: 84%;
        background: #fff;
        border-radius: 3px;
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
        font-size: 15px;
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

        }
        .fanhui{
        text-decoration: none;
        padding-left: 20px;
        color: #333;
        }
        .fanhui:hover{
        color:rgb(35,126,255);
        }
            #imgd{
            width: 100%;
            height:100vh;
            display: flex;
            justify-content: center;
            }
            #img{
                width:auto;
                height:25vw;
                margin-top:60px;
            }
        .Hui-iconfont {
        font-family: "Hui-iconfont" !important;
        font-style: normal;
        -webkit-font-smoothing: antialiased;
        -webkit-text-stroke-width: 0.2px;
        -moz-osx-font-smoothing: grayscale;
        }
        </style>
        </head>
        <body style="margin:0;padding:0;">
        <div class="three">
        <!--组织架构-->
        <iframe style="display:none" src="${ly }/global/control-list2?flag=2&userId=${userId}"  onload="loadFrame()" scrolling="no" id="iframe" frameborder="0" width="100%" height="650px" ></iframe>
        <div id="imgd">
            <img id="img" src=""/>
        </div>
        <ul id="ul" >

        <li><a onclick="sub1()"  data-title="企业风险分布图" href="javascript:void(0)">企业风险分布图</a>


        </li>

        <li><a onclick="sub2()"  data-title="应急预案" href="javascript:void(0)">应急预案</a></li>
                <li><a onclick="sub3()" data-title="应急物资" href="javascript:void(0)">应急物资</a></li>
                <li><a onclick="sub4()"  data-title="预案演练" href="javascript:void(0)">预案演练</a></li>
                <li><a onclick="sub5()"  data-title="预案备案" href="javascript:void(0)">预案备案</a></li>
                <li><a onclick="sub6()" data-title="应急培训" href="javascript:void(0)">应急培训</a></li>

        </ul>
            <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
            <script>
            $.ajax({
            url: "/company/safety-system/find-photo",    //请求的url地址 
            data: {},    //参数值
            type: "POST",   //请求方式
            success: function (res) {
            console.log("res");
            console.log(res);
            //请求成功时处理
            if (res != null) {
            $("#img").attr("src",res.url);
            if(res&&res.flag&&res.flag==2){
            dataObj = res
            // $("#fxt1").attr("src",res.url1);
            }
            <%--$("#fxt").attr("src", '${ly}/images/ceshi.jpg');--%>
            } else {
            window.location.href = '${ly}/company/safety-system/control-list2?flag=2';
            }
            },
            error: function (res) {
            //请求出错处理
            console.log(res, '请求失败');
            window.location.href = '${ly}/company/safety-system/control-list2?flag=2';
            }
            });
            function sub1(){
            $("#iframe").hide();
            $("#imgd").show();
            }
            function sub2(){
            $("#imgd").hide();
            $("#iframe").show();
            $("#iframe").attr("src","${ly }/global/tables/tab-yjlist?userId=${userId}" );
            }
            function sub3(){
            $("#imgd").hide();
            $("#iframe").show();
            $("#iframe").attr("src","/api/safety_Standard/findOne?safetyStandardlistId=170");
            }
            function sub4(){
            $("#imgd").hide();
            $("#iframe").show();
            $("#iframe").attr("src","/api/safety_Standard/findOne?safetyStandardlistId=176");
            }
            function sub5(){
            $("#imgd").hide();
            $("#iframe").show();
            $("#iframe").attr("src","/company/evaluate/zheng-list");
            }
            function sub6(){
            $("#imgd").hide();
            $("#iframe").show();
            $("#iframe").attr("src","/api/safety_Standard/findOne?safetyStandardlistId=175");
            }
            </script>
        </div>
        </body>
        </html>
            <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
        <script>
        function loadFrame(){
            $("#iframe").contents().find("nav").hide();
        }
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
