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
    <meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
    <meta name="description" content="风险分级管控   隐患排查治理智能化平台">
    <style type="text/css">
        body .dis-ib {
            margin-right: 15px;
        }

        .list_jian {
            display: none;
            padding: 15px;
            min-height: 180px;
        }

        .div_bgrou {
            width: 100%;
            text-align: center;
            padding-bottom: 30px;
            border-bottom: 1px dashed #ddd;
            margin-bottom: 40px;
        }

        .div_bgrou .btn {
            margin-right: 20px;
            height: 70px;
            line-height: 70px;
            padding: 0 50px;
            font-size: 16px;
            background: #F6F6F7;
            color: #333
        }

        .btn-group {
            width: 100%;
            text-align: center;
            margin-bottom: 30px;
        }

        .btn-group .btn {
            height: 38px;
            line-height: 38px;
            padding: 0 25px;
            font-size: 14px;
        }

        .modal-dialog {
            margin-top: 150px;
        }

        .font_wz {
            color: red;
            margin-left: 15px;
            display: none;
        }

        #tab-index-cartegory {

        }

        #tab-index-cartegory .tabBar {
            height: 40px;
            line-height: 40px;
            /*background: #666666;*/
        }

        #tab-index-cartegory .tabBar span {
            display: inline-block;
            font-size: 16px;
            padding: 0 40px;
            color: #ffffff;
            height: 40px;
            line-height: 40px;
            border-top: 0;
            cursor: pointer;
            border-top-left-radius: 10px;
            border-top-right-radius: 10px;
            margin-right: 5px;
            background: #ccc;
        }

        #tab-index-cartegory .tabBar span.selected {
            background: #0099CC !important;
        }

        .tabCon {
            display: none;
            padding: 10px;
            padding-bottom: 80px;
            border: 1px solid #ccc;
        }
    </style>

</head>
<body>
<nav class="breadcrumb">
    <c:set var="x1" value="${fn:split('企业自查/ /部门抽查/行政检查','/') }"/>
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
    <span class="c-gray en">&gt;</span> <span>隐患排查治理系统</span>
    <span class="c-gray en">&gt;</span> <span>${x1[flag-1] }检查设置 </span>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
</nav>
<div class="page-container">
    <div id="tab-index-cartegory">
        <div class="tabBar">
            <span class="selected">现场管理</span>
            <span>基础管理</span>
        </div>
        <div class="tabCon" style="display: block;">
            <div class="mt-20">
                <table class="table table-border table-bordered table-bg table-hover table-sort">
                    <thead>
                    <tr class="text-c">
                        <th width="45px">序号</th>
                        <th>受检车间/场所</th>
                        <th>综合检查</th>
                        <th>日常检查</th>
                        <th>定期检查</th>
                        <th>季节检查</th>
                        <th>其他检查</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- 循环-->
                    <c:forEach items="${xianChangItem}" varStatus="index" var="be">
                        <tr class="text-c">
                            <td>${index.index + 1}</td>
                            <td>${be.level1 }</td>
                            <td>
                                <a style="text-decoration:none"
                                   onClick="show_dialog('生成检查表', '${ly}/village/model-add4?dmname=${be.level1 }&dmid=${be.dmid }&checkType=5&industryType=-2')"
                                   href="javascript:;">设置</a>
<%--                                <a style="text-decoration:none"--%>
<%--                                   onClick="tz( '${ly}/company/model-list-tj?dmname=${be.level1 }&dmid=${be.dmid }&checkType=5&industryType=-2&template=6&flag=1')"--%>
<%--                                   href="javascript:;">实施</a>--%>
                                <a style="text-decoration:none"
                                   onClick="ss('${be.level1 }','${be.dmid }',5,-2,6,1)"
                                   href="javascript:;">实施</a>
                            </td>
                            <td>
                                <a style="text-decoration:none"
                                   onClick="show_dialog('生成检查表', '${ly}/village/model-add4?dmname=${be.level1 }&dmid=${be.dmid }&checkType=1&industryType=-2')"
                                   href="javascript:;">设置</a>
<%--                                <a style="text-decoration:none"--%>
<%--                                   onClick="tz( '${ly}/company/model-list-tj?dmname=${be.level1 }&dmid=${be.dmid }&checkType=1&industryType=-2&template=2&flag=1')"--%>
<%--                                   href="javascript:;">实施</a>--%>
                                <a style="text-decoration:none"
                                   onClick="ss('${be.level1 }','${be.dmid }',1,-2,2,1)"
                                   href="javascript:;">实施</a>
                            </td>
                            <td>
                                <a style="text-decoration:none"
                                   onClick="show_dialog('生成检查表', '${ly}/village/model-add4?dmname=${be.level1 }&dmid=${be.dmid }&checkType=2&industryType=-2')"
                                   href="javascript:;">设置</a>
<%--                                <a style="text-decoration:none"--%>
<%--                                   onClick="tz( '${ly}/company/model-list-tj?dmname=${be.level1 }&dmid=${be.dmid }&checkType=2&industryType=-2&template=3&flag=1')"--%>
<%--                                   href="javascript:;">实施</a>--%>
                                <a style="text-decoration:none"
                                   onClick="ss('${be.level1 }','${be.dmid }',2,-2,3,1)"
                                   href="javascript:;">实施</a>
                            </td>
                            <td>
                                <a style="text-decoration:none"
                                   onClick="show_dialog('生成检查表', '${ly}/village/model-add4?dmname=${be.level1 }&dmid=${be.dmid }&checkType=3&industryType=-2')"
                                   href="javascript:;">设置</a>
<%--                                <a style="text-decoration:none"--%>
<%--                                   onClick="tz('${ly}/company/model-list-tj?dmname=${be.level1 }&dmid=${be.dmid }&checkType=3&industryType=-2&template=4&flag=1')"--%>
<%--                                   href="javascript:;">实施</a>--%>
                                <a style="text-decoration:none"
                                   onClick="ss('${be.level1 }','${be.dmid }',3,-2,4,1)"
                                   href="javascript:;">实施</a>
                            </td>
                            <td>
                                <a style="text-decoration:none"
                                   onClick="show_dialog('生成检查表', '${ly}/village/model-add4?dmname=${be.level1 }&dmid=${be.dmid }&checkType=4&industryType=-2')"
                                   href="javascript:;">设置</a>
<%--                                <a style="text-decoration:none"--%>
<%--                                   onClick="tz( '${ly}/company/model-list-tj?dmname=${be.level1 }&dmid=${be.dmid }&checkType=4&industryType=-2&template=5&flag=1')"--%>
<%--                                   href="javascript:;">实施</a>--%>
                                <a style="text-decoration:none"
                                   onClick="ss('${be.level1 }','${be.dmid }',4,-2,5,1)"
                                   href="javascript:;">实施</a>
                            </td>

                        </tr>
                    </c:forEach>
                    <!-- 循环结束 -->
                    </tbody>
                </table>
            </div>
        </div>
        <div class="tabCon">
            <div class="mt-20">
                <table class="table table-border table-bordered table-bg table-hover table-sort">
                    <thead>
                    <tr class="text-c">
                        <th width="45px">序号</th>
                        <th>受检车间/场所</th>
                        <th>综合检查</th>
                        <th>日常检查</th>
                        <th>定期检查</th>
                        <th>季节检查</th>
                        <th>其他检查</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- 循环-->
                    <c:forEach items="${jiChuItem}" varStatus="index" var="be">
                        <tr class="text-c">
                            <td>${index.index + 1}</td>
                            <td>${be.level1 }</td>
                            <td>
                                <a style="text-decoration:none"
                                   onClick="show_dialog('生成检查表', '${ly}/village/model-add4?dmname=${be.level1 }&dmid=${be.dmid }&checkType=5&industryType=-1')"
                                   href="javascript:;">设置</a>
<%--                                <a style="text-decoration:none"--%>
<%--                                   onClick="tz('${ly}/company/model-list-tj?dmname=${be.level1 }&dmid=${be.dmid }&checkType=5&industryType=-1&template=6&flag=1')"--%>
<%--                                   href="javascript:;">实施</a>--%>
                                <a style="text-decoration:none"
                                   onClick="ss('${be.level1 }','${be.dmid }',5,-1,6,1)"
                                   href="javascript:;">实施</a>
                            </td>
                            <td>
                                <a style="text-decoration:none"
                                   onClick="show_dialog('生成检查表', '${ly}/village/model-add4?dmname=${be.level1 }&dmid=${be.dmid }&checkType=1&industryType=-1')"
                                   href="javascript:;">设置</a>
<%--                                <a style="text-decoration:none"--%>
<%--                                   onClick="tz('${ly}/company/model-list-tj?dmname=${be.level1 }&dmid=${be.dmid }&checkType=1&industryType=-1&template=2&flag=1')"--%>
<%--                                   href="javascript:;">实施</a>--%>
                                <a style="text-decoration:none"
                                   onClick="ss('${be.level1 }','${be.dmid }',1,-1,2,1)"
                                   href="javascript:;">实施</a>
                            </td>
                            <td>
                                <a style="text-decoration:none"
                                   onClick="show_dialog('生成检查表', '${ly}/village/model-add4?dmname=${be.level1 }&dmid=${be.dmid }&checkType=2&industryType=-1')"
                                   href="javascript:;">设置</a>
<%--                                <a style="text-decoration:none"--%>
<%--                                   onClick="tz('${ly}/company/model-list-tj?dmname=${be.level1 }&dmid=${be.dmid }&checkType=2&industryType=-1&template=3&flag=1')"--%>
<%--                                   href="javascript:;">实施</a>--%>
                                <a style="text-decoration:none"
                                   onClick="ss('${be.level1 }','${be.dmid }',2,-1,3,1)"
                                   href="javascript:;">实施</a>
                            </td>
                            <td>
                                <a style="text-decoration:none"
                                   onClick="show_dialog('生成检查表', '${ly}/village/model-add4?dmname=${be.level1 }&dmid=${be.dmid }&checkType=3&industryType=-1')"
                                   href="javascript:;">设置</a>
<%--                                <a style="text-decoration:none"--%>
<%--                                   onClick="tz('${ly}/company/model-list-tj?dmname=${be.level1 }&dmid=${be.dmid }&checkType=3&industryType=-1&template=4&flag=1')"--%>
<%--                                   href="javascript:;">实施</a>--%>
                                <a style="text-decoration:none"
                                   onClick="ss('${be.level1 }','${be.dmid }',3,-1,4,1)"
                                   href="javascript:;">实施</a>
                            </td>
                            <td>
                                <a style="text-decoration:none"
                                   onClick="show_dialog('生成检查表', '${ly}/village/model-add4?dmname=${be.level1 }&dmid=${be.dmid }&checkType=4&industryType=-1')"
                                   href="javascript:;">设置</a>
<%--                                <a style="text-decoration:none"--%>
<%--                                   onClick="tz( '${ly}/company/model-list-tj?dmname=${be.level1 }&dmid=${be.dmid }&checkType=4&industryType=-1&template=5&flag=1')"--%>
<%--                                   href="javascript:;">实施</a>--%>
                                <a style="text-decoration:none"
                                   onClick="ss('${be.level1 }','${be.dmid }',4,-1,5,1)"
                                   href="javascript:;">实施</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <!-- 循环结束 -->
                    </tbody>
                </table>
            </div>
        </div>
    </div>


    <%--    &lt;%&ndash; 添加标准检查设置  定位&ndash;%&gt;--%>
    <%--        <c:if test="${not empty jiChuItem}">--%>
    <%--            <div class="mt-20">--%>
    <%--                <h3>基础检查</h3>--%>
    <%--                <table class="table table-border table-bordered table-bg table-hover table-sort">--%>
    <%--                    <thead>--%>
    <%--                    <tr class="text-c">--%>
    <%--                        <th width="5%">序号</th>--%>
    <%--                        <th>受检车间/场所</th>--%>
    <%--                        <th>综合检查</th>--%>
    <%--                        <th>日常检查</th>--%>
    <%--                        <th>定期检查</th>--%>
    <%--                        <th>季节检查</th>--%>
    <%--                        <th>其他检查</th>--%>
    <%--                    </tr>--%>
    <%--                    </thead>--%>
    <%--                    <tbody>--%>
    <%--                    <!-- 循环-->--%>
    <%--                    <c:forEach items="${jiChuItem}" varStatus="index" var="be">--%>
    <%--                        <tr class="text-c">--%>
    <%--                            <td>${index.index + 1}</td>--%>
    <%--                            <td>${be.level1 }</td>--%>
    <%--                            <td>--%>
    <%--                                <a style="text-decoration:none"--%>
    <%--                                   onClick="show_dialog('生成检查表', '${ly}/company/model-add-main?dmname=${be.level1 }&dmid=${be.dmid }&checkType=5&industryType=-1')"--%>
    <%--                                   href="javascript:;">设置</a>--%>
    <%--                                <a style="text-decoration:none"--%>
    <%--                                   onClick="tz('${ly}/company/model-list-tj?dmname=${be.level1 }&dmid=${be.dmid }&checkType=5&industryType=-1&template=6&flag=1')"--%>
    <%--                                   href="javascript:;">执行</a>--%>
    <%--                            </td>--%>
    <%--                            <td>--%>
    <%--                                <a style="text-decoration:none"--%>
    <%--                                   onClick="show_dialog('生成检查表', '${ly}/company/model-add-main?dmname=${be.level1 }&dmid=${be.dmid }&checkType=1&industryType=-1')"--%>
    <%--                                   href="javascript:;">设置</a>--%>
    <%--                                <a style="text-decoration:none"--%>
    <%--                                   onClick="tz('${ly}/company/model-list-tj?dmname=${be.level1 }&dmid=${be.dmid }&checkType=1&industryType=-1&template=2&flag=1')"--%>
    <%--                                   href="javascript:;">执行</a>--%>
    <%--                            </td>--%>
    <%--                            <td>--%>
    <%--                                <a style="text-decoration:none"--%>
    <%--                                   onClick="show_dialog('生成检查表', '${ly}/company/model-add-main?dmname=${be.level1 }&dmid=${be.dmid }&checkType=2&industryType=-1')"--%>
    <%--                                   href="javascript:;">设置</a>--%>
    <%--                                <a style="text-decoration:none"--%>
    <%--                                   onClick="tz('${ly}/company/model-list-tj?dmname=${be.level1 }&dmid=${be.dmid }&checkType=2&industryType=-1&template=3&flag=1')"--%>
    <%--                                   href="javascript:;">执行</a>--%>
    <%--                            </td>--%>
    <%--                            <td>--%>
    <%--                                <a style="text-decoration:none"--%>
    <%--                                   onClick="show_dialog('生成检查表', '${ly}/company/model-add-main?dmname=${be.level1 }&dmid=${be.dmid }&checkType=3&industryType=-1')"--%>
    <%--                                   href="javascript:;">设置</a>--%>
    <%--                                <a style="text-decoration:none"--%>
    <%--                                   onClick="tz('${ly}/company/model-list-tj?dmname=${be.level1 }&dmid=${be.dmid }&checkType=3&industryType=-1&template=4&flag=1')"--%>
    <%--                                   href="javascript:;">执行</a>--%>
    <%--                            </td>--%>
    <%--                            <td>--%>
    <%--                                <a style="text-decoration:none"--%>
    <%--                                   onClick="show_dialog('生成检查表', '${ly}/company/model-add-main?dmname=${be.level1 }&dmid=${be.dmid }&checkType=4&industryType=-1')"--%>
    <%--                                   href="javascript:;">设置</a>--%>
    <%--                                <a style="text-decoration:none"--%>
    <%--                                   onClick="tz( '${ly}/company/model-list-tj?dmname=${be.level1 }&dmid=${be.dmid }&checkType=4&industryType=-1&template=5&flag=1')"--%>
    <%--                                   href="javascript:;">执行</a>--%>
    <%--                            </td>--%>

    <%--                        </tr>--%>
    <%--                    </c:forEach>--%>
    <%--                    <!-- 循环结束 -->--%>
    <%--                    </tbody>--%>
    <%--                </table>--%>
    <%--            </div>--%>
    <%--        </c:if>--%>
    <%--        <c:if test="${not empty xianChangItem}">--%>
    <%--            <div class="mt-20" style="margin-top: 50px">--%>
    <%--                <h3>现场检查</h3>--%>
    <%--                <table class="table table-border table-bordered table-bg table-hover table-sort">--%>
    <%--                    <thead>--%>
    <%--                    <tr class="text-c">--%>
    <%--                        <th width="5%">序号</th>--%>
    <%--                        <th>受检车间/场所</th>--%>
    <%--                        <th>综合检查</th>--%>
    <%--                        <th>日常检查</th>--%>
    <%--                        <th>定期检查</th>--%>
    <%--                        <th>季节检查</th>--%>
    <%--                        <th>其他检查</th>--%>
    <%--                    </tr>--%>
    <%--                    </thead>--%>
    <%--                    <tbody>--%>
    <%--                    <!-- 循环-->--%>
    <%--                    <c:forEach items="${xianChangItem}" varStatus="index" var="be">--%>
    <%--                        <tr class="text-c">--%>
    <%--                            <td>${index.index + 1}</td>--%>
    <%--                            <td>${be.level1 }</td>--%>
    <%--                            <td>--%>
    <%--                                <a style="text-decoration:none"--%>
    <%--                                   onClick="show_dialog('生成检查表', '${ly}/company/model-add-main?dmname=${be.level1 }&dmid=${be.dmid }&checkType=5&industryType=-2')"--%>
    <%--                                   href="javascript:;">设置</a>--%>
    <%--                                <a style="text-decoration:none"--%>
    <%--                                   onClick="tz( '${ly}/company/model-list-tj?dmname=${be.level1 }&dmid=${be.dmid }&checkType=5&industryType=-2&template=6&flag=1')"--%>
    <%--                                   href="javascript:;">执行</a>--%>
    <%--                            </td>--%>
    <%--                            <td>--%>
    <%--                                <a style="text-decoration:none"--%>
    <%--                                   onClick="show_dialog('生成检查表', '${ly}/company/model-add-main?dmname=${be.level1 }&dmid=${be.dmid }&checkType=1&industryType=-2')"--%>
    <%--                                   href="javascript:;">设置</a>--%>
    <%--                                <a style="text-decoration:none"--%>
    <%--                                   onClick="tz( '${ly}/company/model-list-tj?dmname=${be.level1 }&dmid=${be.dmid }&checkType=1&industryType=-2&template=2&flag=1')"--%>
    <%--                                   href="javascript:;">执行</a>--%>
    <%--                            </td>--%>
    <%--                            <td>--%>
    <%--                                <a style="text-decoration:none"--%>
    <%--                                   onClick="show_dialog('生成检查表', '${ly}/company/model-add-main?dmname=${be.level1 }&dmid=${be.dmid }&checkType=2&industryType=-2')"--%>
    <%--                                   href="javascript:;">设置</a>--%>
    <%--                                <a style="text-decoration:none"--%>
    <%--                                   onClick="tz( '${ly}/company/model-list-tj?dmname=${be.level1 }&dmid=${be.dmid }&checkType=2&industryType=-2&template=3&flag=1')"--%>
    <%--                                   href="javascript:;">执行</a>--%>
    <%--                            </td>--%>
    <%--                            <td>--%>
    <%--                                <a style="text-decoration:none"--%>
    <%--                                   onClick="show_dialog('生成检查表', '${ly}/company/model-add-main?dmname=${be.level1 }&dmid=${be.dmid }&checkType=3&industryType=-2')"--%>
    <%--                                   href="javascript:;">设置</a>--%>
    <%--                                <a style="text-decoration:none"--%>
    <%--                                   onClick="tz('${ly}/company/model-list-tj?dmname=${be.level1 }&dmid=${be.dmid }&checkType=3&industryType=-2&template=4&flag=1')"--%>
    <%--                                   href="javascript:;">执行</a>--%>
    <%--                            </td>--%>
    <%--                            <td>--%>
    <%--                                <a style="text-decoration:none"--%>
    <%--                                   onClick="show_dialog('生成检查表', '${ly}/company/model-add-main?dmname=${be.level1 }&dmid=${be.dmid }&checkType=4&industryType=-2')"--%>
    <%--                                   href="javascript:;">设置</a>--%>
    <%--                                <a style="text-decoration:none"--%>
    <%--                                   onClick="tz( '${ly}/company/model-list-tj?dmname=${be.level1 }&dmid=${be.dmid }&checkType=4&industryType=-2&template=5&flag=1')"--%>
    <%--                                   href="javascript:;">执行</a>--%>
    <%--                            </td>--%>

    <%--                        </tr>--%>
    <%--                    </c:forEach>--%>
    <%--                    <!-- 循环结束 -->--%>
    <%--                    </tbody>--%>
    <%--                </table>--%>
    <%--            </div>--%>
    <%--        </c:if>--%>


</div>
<script type="text/javascript">

    HUItab = function (tabBar, tabCon, class_name, tabEvent, i) {
        var $tab_menu = $(tabBar);
        // 初始化操作
        $tab_menu.removeClass(class_name);
        $(tabBar).eq(i).addClass(class_name);
        $(tabCon).hide();
        $(tabCon).eq(i).show();

        $tab_menu.bind(tabEvent, function () {
            $tab_menu.removeClass(class_name);
            $(this).addClass(class_name);
            var index = $tab_menu.index(this);
            $(tabCon).hide();
            $(tabCon).eq(index).show();
        });
    }

    $(function () {
        $('.table-sort').dataTable({
            "aaSorting": [[0, "asc"]],//默认第几个排序
            "bStateSave": false,//状态保存
            "searching": false,
            "aoColumnDefs": []
        });
        $(function () {
            HUItab("#tab-index-cartegory .tabBar span", "#tab-index-cartegory .tabCon", "selected", "click", "0")
        })
    });

    function tz(url) {
        window.location.href = url;
    }

    function ss(dmname, dmid, checkType, industryType, template, flag) {   //点击实施方法
        $.post(getRootPath() + "/company/model-list-ss", {
            dmname: dmname, dmid: dmid, checkType: checkType, industryType: industryType, template: template, flag: flag
        }, function (result, status) {
            console.log(result);
            if (status == 'success') {
                if (result.status == 0) {
                    var url = '${ly}/village/plan-next?flag='+result.data.flag+'&id='+result.data.modelId;
                    console.log(url);
                    window.location.href=url;
                } else {
                    layer.msg('请先设置检查表');
                }
            } else {
                layer.msg('网络错误');
            }
        })
    }
</script>
</body>
</html>