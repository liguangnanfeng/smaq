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
        .wid1{width:30%}
        .wid2{width:30%}
        .wid3{width:30%}
        .wid4{width:10%}
        .wid5{width:70%}
        .btn-cz{margin:auto;background:#eee;border:1px solid #ddd;padding:0 5px;height:20px;line-height:20px;border-radius:5px;margin-left:15px}
        .btn-cz:hover{border:1px solid #06c;color:#06c;text-decoration: none;}
        body .check-box label{margin-left:5px;}
        .one_danger .list_odan{margin-left:20px}
        .two_danger .list_twdan{margin-left:45px;}
        .one_danger .Hui-iconfont{margin-top:2px}
        .one_danger,.list_danger{display:inline-block}
        .one_danger,.one_danger .list_odan,.two_danger,.two_danger .list_twdan,.list_danger{float:none;}
        .check-box{width:30%;margin-bottom:15px;}
        .btn-group .btn{height: 34px;line-height: 34px;padding: 0 25px;}
        .one-danrisk{float:left;width:100%;border-bottom:1px solid #ddd;height:auto;}
        .one-danrisk p{float:left;width:80%;padding: 8px;height:auto;}
        .table tbody tr td .one-danrisk a{float:right;margin-top:8px;margin-right:8px;}
    </style>
</head>
<body>
<nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
    <span class="c-gray en">&gt;</span> <span>系统设置</span>
    <span class="c-gray en">&gt;</span> <span>风险辨识</span>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="parent.close_tab(function(){})">返回</a>
</nav>
<div class="page-container">
    <div id="spTab" class="btn-group" style="text-align: center;margin-bottom: 20px;">
        <a class="btn default ${empty type ? 'btn-primary' : 'radius'}" href="${ly }/company/safety-system/risk-list-update">风险点辨识表</a>
        <a class="btn default ${1 eq type ? 'btn-primary' : 'radius'}" href="${ly }/company/safety-system/risk-list?type=1">职业病风险物理因素分布表</a>
        <a class="btn default ${2 eq  type ? 'btn-primary' : 'radius'}" href="${ly }/company/safety-system/risk-list?type=2">职业病风险化学因素分布表</a>
        <c:if test="${company.industry eq '化工企业（危险化学品生产、经营、使用）、加油站'}">
            <c:choose>
                <c:when test="${fn:contains(company.name, '油') }">
                </c:when>
                <c:otherwise>
                    <a class="btn default ${3 eq  type ? 'btn-primary' : 'radius'}" href="${ly }/company/safety-system/risk-list?type=3">高危工艺分布表</a>
                </c:otherwise>
            </c:choose>
            <a class="btn default ${4 eq  type ? 'btn-primary' : 'radius'}" href="${ly }/company/safety-system/risk-list?type=4">物料风险分布表</a>
        </c:if>
        <a class="btn default ${5 eq  type ? 'btn-primary' : 'radius'}" href="${ly }/company/safety-system/risk-list?type=5">高危作业分布表</a>
    </div>
    <h3 class="text-c">${company.name}风险因素辨识表</h3>
    <div class="mt-20">
        <div class="cl pd-5 bg-1 bk-gray mt-20">
            <a onclick="updatedel1()" class="btn btn-danger radius" href="javascript:void(0);">重大风险数据清空</a>
            <a onclick="update1()" class="btn btn-danger radius" href="javascript:void(0);">重大风险数据导入</a>


            <a onclick="updatedel2()" class="btn btn-warning radius" href="javascript:void(0);">较大风险数据清空</a>
            <a onclick="update2()" class="btn btn-warning radius" href="javascript:void(0);">较大风险数据导入</a>

            <a onclick="updatedel3()" class="btn btn-primary radius" href="javascript:void(0);">一般和较小风险数据清空</a>
            <a onclick="update3()" class="btn btn-primary radius" href="javascript:void(0);">一般和较小风险数据导入</a>

        </div>
        <table class="table table-border table-bordered table-bg table-hover table-sort tab-ndan">
            <thead>
            <tr class="text-c">
                <th style="width:10%">工艺</th>
                <th style="width:10%">系统</th>
                <th style="width:10%">场所/环节/部位</th>
                <th style="width:60%">风险因素</th>
                <th style="width:10%">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list }" var="be" varStatus="index">
                <tr>
                    <c:set value="${fn:split(be.level3,'/')}" var="ls">

                    </c:set>

                    <td class="text-c">${ls[0] != null ? ls[0] : "无数据" }</td>
                    <td class="text-c">${ls[1] != null ? ls[1] : "无数据" }</td>
                    <td class="text-c">${ls[2] != null ? ls[2] : "无数据" }</td>

                    <td>
                        <p style="float:left;width:100%;">${be.factors}</p>
                    </td>
                    <td class="text-c" style="width:90px">
                            <%--           <button class="btn btn-primary radius" onClick="addgj('${be.id}')">确定风险操作</button> --%>
                        <a class="btn-cz" style="float:center;text-decoration:none;" onClick="del(${be.id})" href="javascript:;" title="删除">删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
<script type="text/javascript" src="/js/fxgk/tbl-rowspan-reset_top3col.js"></script>
<script type="text/javascript">
    //alert("${point}");
    var id = '';

    function reload_() {
        layer.closeAll();
        location.reload();
    }


    function update1() {
        layer.confirm("确定重置高危风险？", function(r) {
            $.post(getRootPath() + "/company/safety-system/risk-list-add1", {
            }, function(result) {
                if(result.status == '1'){
                    location.reload();
                }else{
                    location.reload();
                }
            })
        })
    }

    function updatedel1() {
        layer.confirm("确定清空高危风险数据信息？", function(r) {
            $.post(getRootPath() + "/company/safety-system/risk-list-adddel1", {
            }, function(result) {
                if(result.status == '1'){
                    alert(result.mess);
                    location.reload();
                }else{
                    location.reload();
                }
            })
        })
    }



    function update2() {
        layer.confirm("确定重置较大风险数据信息？", function(r) {
            $.post(getRootPath() + "/company/safety-system/risk-list-addssss", {
            }, function(result) {
                if(result.status == '1'){
                    alert(result.mess);
                    location.reload();
                }else{
                    location.reload();
                }
            })
        })
    }

    function updatedel2() {
        layer.confirm("确定清空较大风险数据信息？", function(r) {
            $.post(getRootPath() + "/company/safety-system/risk-list-adddel", {
            }, function(result) {
                if(result.status == '1'){
                    alert(result.mess);
                    location.reload();
                }else{
                    location.reload();
                }
            })
        })
    }





    function update3() {
        layer.confirm("确定重置现场管理风险？", function(r) {
            $.post(getRootPath() + "/company/safety-system/risk-list-add2", {
            }, function(result) {
                if(result.status == '1'){
                    alert(result.mess);
                    location.reload();
                }else{
                    location.reload();
                }
            })
        })
    }


    function updatedel3() {
        layer.confirm("确定清空现场管理风险数据信息？", function(r) {
            $.post(getRootPath() + "/company/safety-system/risk-list-adddels", {
            }, function(result) {
                if(result.status == '1'){
                    alert(result.mess);
                    location.reload();
                }else{
                    location.reload();
                }
            })
        })
    }


    function del(id) {
        layer.confirm("确定删除该记录?", function(r) {
            $.post(getRootPath() + "/company/safety-system/aCompanyManual-save", {
                id : id,
                del : 1
            }, function(result) {
                location.reload();
            })
        })
    }
</script>
</html>