    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/taglibs.jsp"%>
    <!DOCTYPE html>

        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <!-- Viewport metatags -->
        <meta name="HandheldFriendly" content="true" />
        <meta name="MobileOptimized" content="320" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

        <link rel="stylesheet" type="text/css" href="/styles/dandelion.css" media="screen" />

        <title>404</title>
        <style>
        div#da-error-wrapper #da-error-code{
        line-height: 2;
        color: red
        }
        div#da-error-wrapper {
        padding: 108px 0;
        }
        </style>
        </head>

        <body>

        <!-- Main Wrapper. Set this to 'fixed' for fixed layout and 'fluid' for fluid layout' -->
        <div id="da-wrapper" class="fluid">

        <!-- Content -->
        <div id="da-content">

        <!-- Container -->
        <div class="da-container clearfix">

        <div id="da-error-wrapper">

        <div id="da-error-pin"></div>
        <div id="da-error-code">
        Error ! <span style="font-size: 33px">暂未添加直属区域</span> </div>

        <!-- <h1 class="da-error-heading">哎哟喂！页面让狗狗叼走了！</h1> -->

        </div>
        </div>
        </div>

        <!-- Footer -->


        </div>

        </body>

        </html>