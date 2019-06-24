<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<!-- add by zhangcl 2018.10.13 -->
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <%@ include file="/WEB-INF/inc/back-header.inc" %>
    <title>风险分级管控 隐患排查治理智能化平台</title>
    <meta name="keywords" content="风险分级管控 隐患排查治理智能化平台">
    <meta name="description" content="风险分级管控 隐患排查治理智能化平台">

    <%--    <link rel="stylesheet" type="text/css" href="css/normalize.css" />--%>

    <%--    <link rel="stylesheet" type="text/css" href="css/demo.css" />--%>

    <%--    <link rel="stylesheet" type="text/css" href="css/component.css" />--%>


    <style type="text/css">
        .ter1 {
            width: 15%
        }

        .tab_tmr1 {
            width: 35%
        }

        .switch-left {
            height: 26px;
            vertical-align: top;
        }

        .switch-right {
            height: 26px;
            vertical-align: top;
        }

        #menu {
            padding: 10px 10px 10px;
            text-align: center;
            position: fixed;
            z-index: 998;
            background-color: #fcf9f2;
            display: none;
            border-radius: 5px;
            box-shadow: 2px 2px 5px #333333;
            height: 300px;
            overflow-y: auto;
        }

        .my_delete {
            float: right;
            margin-top: 5px;

        }
    </style>

    <%@ include file="/WEB-INF/inc/print.inc" %>
    <link rel="stylesheet" type="text/css" media="print" href="${ly}/js/H-ui.admin_v3.0/static/h-ui/css/H-ui.css"/>
    <link rel="stylesheet" type="text/css" href="${ly}/styles/common.css" media="print"/>
    <style type="text/css" media="print">
        body .dis-ib {
            margin-right: 15px;
        }

        .col-b, .col-a, .col-c, .col-d {
            margin: 0;
            padding: 0 15px;
            height: 25px;
            line-height: 25px;
        }

        .div-print {
            width: 90%;
            margin: auto;
            padding: 20px 0 0 0;
        }

        h3 {
            display: block;
            width: 100%;
            margin-bottom: 15px;
        }
    </style>
    <style type="text/css">
        body .dis-ib {
            margin-right: 15px;
        }

        .col-b, .col-a, .col-c, .col-d {
            margin: 0;
            padding: 0 15px;
            height: 25px;
            line-height: 25px;
        }

        .div-print {
            width: 90%;
            margin: auto;
            padding: 20px 0 0 0;
        }

        /* h3{display:none;} */
    </style>

    <%--    截取地图css--%>
    <style>
        .jtrq {
            width: 100%;
            height: calc(100% - 39px);
            display: none;
            display: -webkit-box; /* Chrome 4+, Safari 3.1, iOS Safari 3.2+ */
            display: -moz-box; /* Firefox 17- */
            display: -webkit-flex; /* Chrome 21+, Safari 6.1+, iOS Safari 7+, Opera 15/16 */
            display: -moz-flex; /* Firefox 18+ */
            display: -ms-flexbox; /* IE 10 */
            display: flex; /* Chrome 29+, Firefox 22+, IE 11+, Opera 12.1/17/18, Android 4.4+ */
            -webkit-box-orient: vertical;
            -ms-flex-direction: column;
            -webkit-flex-direction: column;
            flex-direction: column;
            -webkit-box-pack: center;
            -ms-flex-pack: center;
            -webkit-justify-content: center;
            justify-content: center;
            -webkit-box-align: center;
            -ms-flex-align: center;
            -webkit-align-items: center;
            align-items: center;
        }

        .jt {
            max-width: 80%;
            width: auto;
            height: auto;
            max-height: 80%;
        }


    </style>
    <%--    截取地图js--%>

    <script type="text/javascript">
        function print() {
            $("#jietu").jqprint();
        }
    </script>

</head>

<body>
<nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
    <span class="c-gray en">&gt;</span> <span>风险分级管控</span>
    <span class="c-gray en">&gt;</span> <span>风险评估</span>
    <span class="c-gray en">&gt;</span> <span>${company.name}风险分布图</span>

    <!--</div>-->

    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>

    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;"
       href="javascript:history.back(-1)" title="返回">返回</a>
    <a class="btn btn-success radius r " style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;"
       href="javascript:print();" title="打印">
        打印
    </a>
    <a class="btn btn-success radius r " style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;"
       href="javascript:showMAp();" title="查看地图">
        查看地图
    </a>

</nav>

<%--截图地图--%>


<div id="jietu" class="jtrq">
    <h3 id="title" style="margin-bottom: 20px;">${companyName}风险分布图</h3>
    <img id="fxt" src="" class="jt"/>
</div>


</body>

<script type="text/javascript"
        src="https://webapi.amap.com/maps?v=1.4.14&key=da3f11570a7a5d170923792f72357543&plugin=AMap.PolyEditor"></script>
<script type="text/javascript" src="${ly}/js/html2canvas.min.js"></script>
<script type="text/javascript">


    //加载时请求有没有风险图
    $(function () {
        $.ajax({
            url: getRootPath() + "/api/map/B003",    //请求的url地址 
            data: {},    //参数值
            type: "POST",   //请求方式
            success: function (res) {
                //请求成功时处理
                if (res != null) {
                    $("#fxt").attr("src", res);
                    <%--$("#fxt").attr("src", '${ly}/images/ceshi.jpg');--%>
                    ;
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
    })

    function showMAp() {
        window.location.href = '${ly}/company/safety-system/control-list2?flag=2';
    }


</script>
</html>
