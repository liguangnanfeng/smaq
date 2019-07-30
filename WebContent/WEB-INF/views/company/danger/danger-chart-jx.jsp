    <%@ page language="java" contentType="text/html; charset=gb2312" pageEncoding="gb2312" %>
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

        #tab-index-cartegory .tabBar {
        height: 40px;
        line-height: 40px;
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
        background: #5a98de !important;
        }

        .tabCon {
        display: none;
        padding: 10px;
        padding-bottom: 80px;
        border: 1px solid #ccc;
        }

        .used {
        background-color: #980c10 !important;
        color: #fff !important;
        }

        <%-- 修改  --%>
        .tabCon1 input {
        text-align: center;
        height: 24px;
        border: none;
        }
        </style>

        </head>
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
        <script type="text/javascript">
        function pr_() {
        $(".tabCon1 table").jqprint();
        }
        </script>
        <script src="/js/jquery.jqprint-0.3.js"></script>
        <div class="page-container">


        <c:if test="${flag == 1}">
            <div class="cl pd-5 bg-1 bk-gray mt-20">
            <span class="r">日期：<strong>${data}</strong>
            </span>
            </div>
        </c:if>

        <div class="text-c mt-20">
        <h3>企业自查绩效分析</h3>
        <button onClick="pr_()" class="btn btn-primary radius" type="button">
        <i class="Hui-iconfont">&#xe652;</i>打印
        </button>
        </div>
        <div class="mt-20">
        <table class="table table-border table-bordered table-bg table-hover">
        <thead>
        <tr class="text-c">
        <th width="45px" rowspan="2">序号</th>
        <%--<th rowspan="2" class="inline"><span--%>
        <%--class="title2">检查方式</span><br><span class="title1">部门名称</span></th>--%>
        <th colspan="1">检查方式</th>
        <th colspan="3">综合检查</th>
        <th colspan="3">日常检查</th>
        <th colspan="3">定期检查</th>
        <th colspan="3">季节检查</th>
        <th colspan="3">其他检查</th>
        <th colspan="3">基础检测</th>
        <th colspan="3">累计</th>
        </tr>
        <tr class="text-c">
        <th>部门名称</th>
        <th>计划</th>
        <th>实绩</th>
        <th>同比</th>
        <th>计划</th>
        <th>实绩</th>
        <th>同比</th>
        <th>计划</th>
        <th>实绩</th>
        <th>同比</th>
        <th>计划</th>
        <th>实绩</th>
        <th>同比</th>
        <th>计划</th>
        <th>实绩</th>
        <th>同比</th>
        <th>计划</th>
        <th>实绩</th>
        <th>同比</th>
        <th>数量</th>
        <th>完成率%</th>
        <th>同比%</th>
        </tr>
        </thead>
        <tbody>
        <!-- 循环-->
        ${list}
        <c:forEach items="${list}" varStatus="index" var="be">
            <tr class="text-c">
            <td>${index.index + 1}</td>
            <td>${be.name }</td>

            <td>${be.syn_year }</td>
            <td>${be.danger1 }</td>
            <td>${be.eve_month }</td>

            <td>${be.eve_year }</td>
            <td>${be.danger2 }</td>
            <td>${be.reg_month }</td>

            <td>${be.reg_year }</td>
            <td>${be.danger3 }</td>
            <td>${be.sea_month }</td>

            <td>${be.sea_year }</td>
            <td>${be.danger4 }</td>
            <td>${be.sea_year }</td>

            <td>${be.els_year }</td>
            <td>${be.danger5 }</td>
            <td>${be.sea_year }</td>

            <td>${be.bas_year }</td>
            <td>${be.sea_year }</td>
            <td>${be.sea_year }</td>

            <td>${be.sea_year }</td>
            <td>${be.sea_year }</td>
            <td>${be.sea_year }</td>
            </tr>
        </c:forEach>
        <%--<tr class="text-c">--%>
        <%--<td></td>--%>
        <%--<td>合计</td>--%>

        <%--<td>--%>
        <%--<c:forEach items="${list}" varStatus="index" var="be">--%>
            <%--${be.syn_year }--%>
        <%--</c:forEach>--%>
        <%--</td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>

        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>

        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>

        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>

        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>

        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>

        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--<td></td>--%>
        <%--</tr>--%>
        <!-- 循环结束 -->
        </tbody>
        </table>
        </div>
        </div>
        <!-- 弹窗输入 -->
        <div id="modal-plan2" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" style="margin-top: 150px">
        <div class="modal-content radius">
        <div class="modal-header">
        <h3 class="modal-title">修改</h3>
        <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
        </div>
        <div class="modal-body" style="height: 200px; overflow-y: auto">
        <div class="form form-horizontal">
        <div class="row cl dq">
        <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>计划
        :</label>
        <div class="formControls col-xs-5 col-sm-5">
        <input class="input-text" type="text" name="" id="trInput" style="width:300px">
        </div>
        </div>
        <div class="row">
        <div class="col-xs-3 col-sm-3 col-xs-offset-8 col-sm-offset-8">
        <button class="btn radius btn-primary size-S" onclick="inputS()">
        确认
        </button>
        </div>
        </div>
        </div>
        </div>
        </div>
        </div>

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
        var idx = '';
        var zzJid = '';
        var vid = '';
        var sss = 0;
        $('.table-sort').dataTable({
        "aaSorting": [[0, "asc"]],//默认第几个排序
        "bStateSave": false,//状态保存
        "searching": false,
        "aoColumnDefs": []
        });
        $(function () {
        if('${plan}'==1){
        HUItab("#tab-index-cartegory .tabBar span", "#tab-index-cartegory .tabCon", "selected", "click", "2")
        }else{
        HUItab("#tab-index-cartegory .tabBar span", "#tab-index-cartegory .tabCon", "selected", "click", "0")
        }

        })
        });


        </script>
        </body>
        </html>