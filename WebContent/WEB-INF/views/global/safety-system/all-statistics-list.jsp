    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%@ include file="/WEB-INF/views/taglibs.jsp"%>
        <!DOCTYPE HTML>
        <html>
        <head>
        <meta charset="utf-8">
        <meta name="renderer" content="webkit|ie-comp|ie-stand">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
        <meta http-equiv="Cache-Control" content="no-siteapp" />
        <%@ include file="/WEB-INF/inc/back-header.inc"%>
        <title>风险分级管控   隐患排查治理智能化平台</title>
        <meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
        <meta name="description" content="风险分级管控   隐患排查治理智能化平台">
        <style type="text/css">
        body .dis-ib{margin-right:15px;}
        .btn-group .btn{height: 34px;line-height: 34px;padding: 0 25px;}
        </style>
        </head>
        <body>
        <nav class="breadcrumb">
        <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
                <span class="c-gray en">&gt;</span> <span>风险分级管控</span>
                <span class="c-gray en">&gt;</span> <span>管控数据</span>
       <%-- <span class="c-gray en">&gt;</span> <span>${flag!=2 ? '隐患排查治理系统' : '行政执法系统'}</span>
        <c:if test="${flag != 2}">

        </c:if>
        <c:if test="${flag == 2}">
            <span class="c-gray en">&gt;</span> <span>执法记录</span>
        </c:if>--%>
        <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
        <i class="Hui-iconfont">&#xe68f;</i>
        </a>
        </nav>
        <div>
        </div>
        <div class="mt-20">
        <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
        <tr class="text-c">
            <th width="5%">序号</th>
            <th width="25%">企业名称</th>
            <th width="15%">所属行业</th>
            <th width="8%">风险等级</th>
            <th width="20%">所在地</th>
            <th width="14%">规模类型</th>
            <th width="8%">运行状态</th>
        </tr>
        </thead>
        <tbody><%-- <th width="10%">操作</th>--%>
        <!-- 循环-->
        <script>

        </script>
        <c:forEach items="${list}" varStatus="index" var="be">
            <tr class="text-c">
            <td>${index.index + 1 }</td>
            <td>${be.name}</td>
            <td>${be.industry}</td>
            <td>${be.dlevel}</td>
            <td>${be.address}</td>
            <td>${be.scale}</td>
            <c:if test="${0 == be.isFreeze }">
                <td>正常</td>
            </c:if>
            <c:if test="${1 == be.isFreeze }">
                <td>冻结</td>
            </c:if>
            </tr>
        </c:forEach>
        <!-- 循环结束 -->
        </tbody>
        </table>
        </div>
        </div>
        <script type="text/javascript">
        $(function() {
        $('.table-sort').dataTable({
        "aaSorting": [[0, "asc"]],//默认第几个排序
        "bStateSave": false,//状态保存
        "aoColumnDefs": [
        ]
        });
        });
        /*删除*/
        /* function del_(id){
        layer.confirm("要删除该记录？", function(i) {
        layer.close(i);
        $.post(getRootPath()+"/company/check-del",{
        id:id
        },function(reuslt){
        location.reload();
        })
        })
        } */

        var type = '${type}';
        function copy_(id) {
        show_tab('添加自查记录', getRootPath()+'/company/check-copy?id=' + id);
        }
        </script>
        </body>
        </html>