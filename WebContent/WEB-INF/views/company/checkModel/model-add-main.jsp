<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
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
    <script src="${ly}/js/react.production.min.js" crossorigin></script>
    <script src="${ly}/js/react-dom.production.min.js" crossorigin></script>
    <script src="${ly}/js/babel.min.js"></script>
    <style>
        ul{
            text-align:center;
            margin:0;
            padding:0;
            margin-top:12%;

        }
        ul li{
            display: inline-block;
            list-style-type: none;

            text-align: center;
            padding: 10px 140px;
        }

        ul li a{
            text-decoration: none;
            display: block;
            text-align: center;
            width: 100%;
            padding-left: 0 !important;
            color: #111;
            font-size: 13px;
        }
        ul li a img{
            display:block;
        }

		 ul>li>a>img{
           width:150px;
		   height:150px
        }

		 ul>li>a{
         font-size:20px;
		 font-weight:bold;
        }

        a:hover,a.active{
            color:rgb(35,126,255)
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
    <script>

    </script>
    <div class="three">
        <ul>
            <li><a href="${ly}/village/model-add4?dmname=${dmname}&dmid${dmid}&checkType=${checkType}&industryType=${industryType}" data-title="自定义检查"> <img alt="" src="
            ${ly}/images/b1wxz.png">自定义检查</a></li>
            <li><a href="${ly}/village/addCheckModel3?dmname=${dmname}&dmid${dmid}&checkType=${checkType}&industryType=${industryType}" data-title="标准检查"> <img alt="" src="
            ${ly}/images/b2wxz.png">标准检查</a></li>

        </ul>
    </div>

</html>
