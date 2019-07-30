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
            display: block;
            padding: 10px;
            padding-bottom: 80px;
            border: 1px solid #ccc;
        }


        <%-- 修改  --%>
        .tabCon1 input {
            text-align: center;
            height: 24px;
            border: none;
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
    <a class="btn btn-success radius r bit_hid" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:history.back(-1)" title="返回">返回</a>
</nav>
<script type="text/javascript">
    function pr_() {
        $(".tabCon1 table").jqprint();
    }
    function ss(dmname, dmid, checkType, industryType, template, flag) {   //点击实施方法
    $.post(getRootPath() + "/company/model-list-ss", {
    dmname: dmname, dmid: dmid, checkType: checkType, industryType: industryType, template: template, flag: flag
    }, function (result, status) {
    if (status == 'success') {
    if (result.status == 0) {
    sessionStorage.setItem('flag', flag);
    sessionStorage.setItem('dmname', dmname);
    sessionStorage.setItem('industryType', industryType);
    sessionStorage.setItem('template', template);
    var url = '${ly}/village/plan-next?flag=' + result.data.flag + '&id=' + result.data.modelId;
    window.location.href = url;
    } else {
    layer.msg('请先设置检查表');
    }
    } else {
    layer.msg('网络错误');
    }
    })
    }
</script>
<script src="/js/jquery.jqprint-0.3.js"></script>
<div class="page-container">
    <div id="spTab" class="btn-group" style="text-align: center;margin-bottom: 20px;">
        <a class="btn default ${flag == 1 ? 'btn-primary' : 'radius'}"
           href="${ly }/company/model-list-main?flag=1">企业自查</a>
        <a class="btn default ${flag == 2 ? 'btn-primary' : 'radius'}"
           href="${ly }/company/model-list-cx2?flag=2&type=1&template=2">行政检查</a>
        <a class="btn default ${flag == 3 ? 'btn-primary' : 'radius'}"
           href="${ly }/company/model-list-cx2?flag=3&type=1&template=2">部门抽查</a>
        <%-- <a class="btn default ${flag == 2 ? 'btn-primary' : 'radius'}" href="${ly }/village/check-list?flag=2">执法检查</a> --%>
    </div>
</div>
<div class="tabCon" >
    <div class="mt-20">
        <table class="table table-border table-bordered table-bg table-hover table-sort">
            <thead>
            <tr class="text-c">
                <th width="45px">序号</th>
                <th>受检车间/场所</th>
                <th>检查方式</th>
                <th>检查日期</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <!-- 循环-->
                <c:forEach items="${list}" varStatus="index" var="be">
                    <tr class="text-c">
                        <td>${index.index + 1}</td>
                        <td>${be.depart }</td>
                        <td>定期</td>
                        <td>${be.flag }</td>
                        <td><a style="text-decoration:none"
                    onClick="ss('${dmname }','${dmid }','${checkType}','${industryType}','${template}','${flag}')"
                    href="javascript:;">查看</a></td>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
    </body>
</html>