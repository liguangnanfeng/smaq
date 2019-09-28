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

        /*.dataTables_wrapper {*/
        /*    margin-bottom: 50px;*/
        /*}*/

        <%-- 修改  --%>
        .tabCon1 input {
            text-align: center;
            height: 24px;
            border: none;
        }
        #selectBranch{
        position: absolute;
        top: 127px;
        right: 31px;
        width: 100px;
        height: 32px;
        padding: 4px;
        font-size: 16px;
        border: 1px solid #ddd;
        box-shadow: 5px 5px 5px #fff;
        color: black;
        border-radius: 5px;
        }
    </style>

</head>
<nav class="breadcrumb">
    <c:set var="x1" value="${fn:split('企业自查/ /第三方检查/行政检查','/') }"/>
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
    <div id="spTab" class="btn-group" style="text-align: center;margin-bottom: 20px;">
        <a class="btn default ${flag == 1 ? 'btn-primary' : 'radius'}"
           href="${ly }/steel/model-list-main?flag=1&userId=${userId}">企业自查</a>
        <a class="btn default ${flag == 2 ? 'btn-primary' : 'radius'}"
           href="${ly }/steel/model-list-cx2?flag=2&type=1&template=2&userId=${userId}">行政检查</a>
        <a class="btn default ${flag == 3 ? 'btn-primary' : 'radius'}"
           href="${ly }/steel/model-list-cx2?flag=3&type=1&template=2&userId=${userId}">第三方检查</a>
        <%-- <a class="btn default ${flag == 2 ? 'btn-primary' : 'radius'}" href="${ly }/village/check-list?flag=2">执法检查</a> --%>
    </div>
    <div id="tab-index-cartegory">
        <div class="tabBar">
            <span class="selected"> 现场管理</span>
            <span>基础管理</span>
            <span >排查计划</span>
        </div>
        <c:if test="${flag == 1}">
            <div class="cl pd-5 bg-1 bk-gray mt-20">
     		<span class="r">日期：<strong>${data}</strong>
            </span>
            </div>
        </c:if>

        <div class="tabCon" >
            <div class="mt-20">
                <table class="table table-border table-bordered table-bg table-hover table-sort">
                    <thead>
                    <tr class="text-c">
                        <th width="45px">序号</th>
                        <th>受检单位</th>
                        <th>综合检查</th>
                        <th>日常检查</th>
                        <th>定期检查</th>
                        <%--                            <th>季节检查</th>--%>
                        <th>其他检查</th>
                        <th>整改复查</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- 循环-->
                    <c:forEach items="${xianChangItem}" varStatus="index" var="be">
                        <script>
                            console.log("${be}");
                            console.log("${be.count}");
                        </script>
                        <tr class="text-c">
                            <td>${index.index + 1}</td>
                            <td>${be.level1 }</td>
                            <c:forEach items="${be.array}" varStatus="index2" var="item">
                                <td>
                                    <c:set value="${fn:split(item,'=' )}" var="status"></c:set>
                                    <c:if test="${be.level1==companyName }">
                                        <a style="text-decoration:none"
                                           onclick="show_dialog('生成检查表','${ly}/village/select-all-level1?flag=${flag}&checkType=-2&lxType=${status[0]}')"
                                           href="javascript:;">设置</a>
                                    </c:if>
                                    <c:if test="${be.level1!=companyName }">
                                        <a style="text-decoration:none"
                                           onClick="show_dialog('生成检查表', '${ly}/village/model-add4?dmname=${be.level1 }&dmid=${be.dmid }&checkType=${status[0]}&industryType=-2&flag=${flag}')"
                                           href="javascript:;">设置</a>
                                    </c:if>


                                        <%--                                        <a style="text-decoration:none"--%>
                                        <%--                                           onClick="show_dialog('生成检查表', '${ly}/village/model-add4?dmname=${be.level1 }&dmid=${be.dmid }&checkType=${status[0]}&industryType=-2&flag=${flag}')"--%>
                                        <%--                                           href="javascript:;">设置</a>--%>
                                        <%--                                <a style="text-decoration:none"--%>
                                        <%--                                   onClick="tz('${ly}/company/model-list-tj?dmname=${be.level1 }&dmid=${be.dmid }&checkType=5&industryType=-1&template=6&flag=1')"--%>
                                        <%--
                                                                        href="javascript:;">实施</a>--%>
                                    <c:if test="${status[1]==1}">
                                        <c:if test="${index2.index==2}">
                                            <a style="text-decoration:none"


                                               href="${ly}/company/model-list-showAll?dmname=${be.level1 }&dmid=${be.dmid }&checkType=${status[0]}&industryType=-2&template=${status[0]+1}&flag=${flag}&status=2"
                                                <%-- onClick="showAll('${be.level1 }','${be.dmid }',${status[0]},-2,${status[0]+1},'${flag}','2')"--%>
                                               href="javascript:;">实施</a>
                                        </c:if>
                                        <c:if test="${index2.index!=2}">
                                            <a style="text-decoration:none"
                                               onClick="ss('${be.level1 }','${be.dmid }',${status[0]},-2,${status[0]+1},'${flag}')"
                                               href="javascript:;">实施</a>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${status[1]==0}">

                                        <c:if test="${index2.index==2}">
                                            <a style="text-decoration:none"
                                               class="used"
                                               onClick="layer.msg('没有要检查的整改项')"
                                                <%--onClick="szss_list('${be.level1 }',2,'${status[0]+1}','${flag}')"--%>
                                               href="javascript:;">实施</a>
                                        </c:if>
                                        <c:if test="${index2.index!=2}">
                                            <a style="text-decoration:none"
                                               class="used"
                                               onClick="layer.msg('没有要检查的整改项')"
                                               href="javascript:;">实施</a></c:if>
                                    </c:if>
                                </td>
                            </c:forEach>
                            <td>
                                <c:if test="${be.count==0}">
                                    <a style="text-decoration:none;"
                                       class="used"
                                       onClick="layer.msg('没有要复查整改的项')"
                                       href="javascript:;">实施
                                    </a>
                                </c:if>
                                <c:if test="${be.count!=0}">
                                    <a style="text-decoration:none"
                                       onClick="szss_list('${be.level1 }',-2,${status[0]+1},'${flag}')"
                                       href="javascript:;">实施
                                    </a>
                                </c:if>

                            </td>

                        </tr>
                    </c:forEach>
                    <!-- 循环结束 -->
                    </tbody>
                </table>
            </div>
        </div>
        <!--企业列表-->
        <select id="selectBranch">
        </select>
        <div class="tabCon" >
            <div class="mt-20">
                <table class="table table-border table-bordered table-bg table-hover table-sort">
                    <thead>
                    <tr class="text-c">
                        <th width="45px">序号</th>
                        <th>受检单位</th>
                        <th>综合检查</th>
                        <th>日常检查</th>
                        <th>定期检查</th>
                        <%--                            <th>季节检查</th>--%>
                        <th>其他检查</th>
                        <th>整改复查</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- 循环-->
                    <c:forEach items="${jiChuItem}" varStatus="index" var="be">
                        <tr class="text-c">
                            <td>${index.index + 1}</td>
                            <td>${be.level1 }</td>
                            <c:forEach items="${be.array}" varStatus="index2" var="item">
                                <td>
                                    <c:set value="${fn:split(item,'=' )}" var="status"></c:set>
                                    <c:if test="${be.level1==companyName }">
                                        <a style="text-decoration:none"
                                           onclick="show_dialog('生成检查表','${ly}/village/select-all-level1?flag=${flag}&checkType=-1&lxType=${status[0]}')"
                                           href="javascript:;">设置</a>
                                    </c:if>
                                    <c:if test="${be.level1!=companyName }">
                                        <a style="text-decoration:none"
                                           onClick="show_dialog('生成检查表', '${ly}/village/model-add4?dmname=${be.level1 }&dmid=${be.dmid }&checkType=${status[0]}&industryType=-1&flag=${flag}')"
                                           href="javascript:;">设置</a>
                                    </c:if>
                                        <%--                                        <a style="text-decoration:none"--%>
                                        <%--                                           onClick="show_dialog('生成检查表', '${ly}/village/model-add4?dmname=${be.level1 }&dmid=${be.dmid }&checkType=${status[0]}&industryType=-1&flag=${flag}')"--%>
                                        <%--                                           href="javascript:;">设置</a>--%>
                                        <%--                                <a style="text-decoration:none"--%>
                                        <%--                                   onClick="tz('${ly}/company/model-list-tj?dmname=${be.level1 }&dmid=${be.dmid }&checkType=5&industryType=-1&template=6&flag=1')"--%>
                                        <%--
                                                                        href="javascript:;">实施</a>--%>
                                    <c:if test="${status[1]==1}">
                                        <a style="text-decoration:none"
                                           onClick="ss('${be.level1 }','${be.dmid }',${status[0]},-1,${status[0]+1},'${flag}')"
                                           href="javascript:;">实施</a>
                                    </c:if>
                                    <c:if test="${status[1]==0}">
                                        <a style="text-decoration:none"
                                           class="used"
                                           onClick="ss('${be.level1 }','${be.dmid }',${status[0]},-1,${status[0]+1},'${flag}')"
                                           href="javascript:;">实施</a>
                                    </c:if>
                                </td>
                            </c:forEach>
                            <td>
                                <c:if test="${be.count==0}">
                                    <a style="text-decoration:none"
                                       class="used"
                                       onClick="layer.msg('没有要复查整改的项')"
                                       href="javascript:;">实施
                                    </a>
                                </c:if>
                                <c:if test="${be.count!=0}">
                                    <a style="text-decoration:none"
                                       onClick="szss_list('${be.level1 }',-1,${status[0]+1},'${flag}')"
                                       href="javascript:;">实施
                                    </a>
                                </c:if>

                            </td>
                        </tr>
                    </c:forEach>
                    <!-- 循环结束 -->
                    </tbody>
                </table>
            </div>
        </div>
        <div class="tabCon tabCon1">
            <div class="text-c mt-20">
                <h3>${companyName}隐患排查计划表</h3>
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
                        <th colspan="2">季节检查</th>
                        <th colspan="2">其他检查</th>
                        <th colspan="2">基础检测</th>
                        <th colspan="2">累计</th>
                    </tr>
                    <tr class="text-c">
                        <th>部门名称</th>
                        <th>月计划</th>
                        <th>年计划</th>
                        <th>同比</th>
                        <th>月计划</th>
                        <th>年计划</th>
                        <th>同比</th>
                        <th>月计划</th>
                        <th>年计划</th>
                        <th>同比</th>
                        <%--<th>月计划</th>--%>
                        <th>年计划</th>
                        <th>同比</th>
                        <%--<th>月计划</th>--%>
                        <th>年计划</th>
                        <th>同比</th>
                        <%--<th>月计划</th>--%>
                        <th>年计划</th>
                        <th>同比</th>
                        <th>数量</th>
                        <th>同比</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${hiddenPlanList}" varStatus="index" var="be">
                        <tr class="text-c">
                            <td>1</td>
                            <td>公司级</td>
                            <td onClick="inputC(this,1,'${be.c}',0)">${be.syn_month }</td>
                            <td>${be.syn_year }</td>
                            <td onClick="inputC(this,3,'${be.c}',0)">${be.syn_ratio }</td>
                            <td onClick="inputC(this,4,'${be.c}',0)">${be.eve_month }</td>
                            <td>${be.eve_year }</td>
                            <td onClick="inputC(this,6,'${be.c}',0)">${be.eve_ratio }</td>
                            <td onClick="inputC(this,7,'${be.c}',0)">${be.reg_month }</td>
                            <td>${be.reg_year }</td>
                            <td onClick="inputC(this,9,'${be.c}',0)">${be.reg_ratio }</td>
                                <%--<td onClick="inputC(this,10,'${be.c}',0)">${be.sea_month }</td>--%>
                            <td onClick="inputC(this,11,'${be.c}',0)">${be.sea_year }</td>
                            <td onClick="inputC(this,12,'${be.c}',0)">${be.sea_ratio }</td>
                                <%--<td onClick="inputC(this,13,'${be.c}',0)">${be.els_month }</td>--%>
                            <td onClick="inputC(this,14,'${be.c}',0)">${be.els_year }</td>
                            <td onClick="inputC(this,15,'${be.c}',0)">${be.els_ratio }</td>
                                <%--<td onClick="inputC(this,16,'${be.c}',0)">${be.bas_month }</td>--%>
                            <td onClick="inputC(this,17,'${be.c}',0)">${be.bas_year }</td>
                            <td onClick="inputC(this,18,'${be.c}',0)">${be.bas_ratio }</td>
                            <td onClick="inputC(this,19,'${be.c}',0)">${be.total_count }</td>
                            <td onClick="inputC(this,20,'${be.c}',0)">${be.total_ratio }</td>
                        </tr>
                    </c:forEach>
                    <!-- 循环-->
                    <c:forEach items="${list}" varStatus="index" var="be">
                        <tr class="text-c">
                            <td>${index.index + 2}</td>
                            <td>${be.name }</td>
                            <td onClick="inputC(this,1,'${be.c}',${be.id})">${be.syn_month }</td>
                            <td>${be.syn_year }</td>
                            <td onClick="inputC(this,3,'${be.c}',${be.id})">${be.syn_ratio }</td>
                            <td onClick="inputC(this,4,'${be.c}',${be.id})">${be.eve_month }</td>
                            <td>${be.eve_year }</td>
                            <td onClick="inputC(this,6,'${be.c}',${be.id})">${be.eve_ratio }</td>
                            <td onClick="inputC(this,7,'${be.c}',${be.id})">${be.reg_month }</td>
                            <td>${be.reg_year }</td>
                            <td onClick="inputC(this,9,'${be.c}',${be.id})">${be.reg_ratio }</td>
                                <%--<td onClick="inputC(this,10,'${be.c}',${be.id})">${be.sea_month }</td>--%>
                            <td onClick="inputC(this,11,'${be.c}',${be.id})">${be.sea_year }</td>
                            <td onClick="inputC(this,12,'${be.c}',${be.id})">${be.sea_ratio }</td>
                                <%--<td onClick="inputC(this,13,'${be.c}',${be.id})">${be.els_month }</td>--%>
                            <td onClick="inputC(this,14,'${be.c}',${be.id})">${be.els_year }</td>
                            <td onClick="inputC(this,15,'${be.c}',${be.id})">${be.els_ratio }</td>
                                <%--<td onClick="inputC(this,16,'${be.c}',${be.id})">${be.bas_month }</td>--%>
                            <td onClick="inputC(this,17,'${be.c}',${be.id})">${be.bas_year }</td>
                            <td onClick="inputC(this,18,'${be.c}',${be.id})">${be.bas_ratio }</td>
                            <td onClick="inputC(this,19,'${be.c}',${be.id})">${be.total_count }</td>
                            <td onClick="inputC(this,20,'${be.c}',${be.id})">${be.total_ratio }</td>
                        </tr>
                    </c:forEach>
                    <c:forEach items="${list1}" varStatus="index" var="be">
                        <tr class="text-c">
                            <td></td>
                            <td style="font-weight:bold;">合计</td>
                            <td>${be.danger21 }</td>
                            <td>${be.danger22 }</td>
                            <td>${be.danger23 }</td>
                            <td>${be.danger24 }</td>
                            <td>${be.danger25 }</td>
                            <td>${be.danger26 }</td>
                            <td>${be.danger27 }</td>
                            <td>${be.danger28 }</td>
                                <%--<td onClick="inputC(this,29,'${be.c}',${be.id})">${be.sea_month }</td>--%>
                            <td>${be.danger30 }</td>
                                <%--<td onClick="inputC(this,31,'${be.c}',${be.id})">${be.els_month }</td>--%>
                            <td>${be.danger32 }</td>
                                <%--<td onClick="inputC(this,33,'${be.c}',${be.id})">${be.bas_month }</td>--%>
                            <td>${be.danger34 }</td>
                            <td>${be.danger35 }</td>
                            <td>${be.danger36 }</td>
                            <td>${be.danger37 }</td>
                            <td>${be.danger38 }</td>
                            <td>${be.danger39 }</td>
                            <td>${be.danger40 }</td>
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
    <%--                        <th>受检单位</th>--%>
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
    <%--                        <th>受检单位</th>--%>
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
        //展示特钢的各个分厂
        $.ajax({
            url:"${ly}/steel/company-list8",
            type:"get",
            success:function (data) {
                var html="";
                for(var i=0; i<data.map.list.length; i++){
                    if(data.map.list[i]["id"]==${userId}){
                        html += '<option value="'+data.map.list[i]["id"]+'" selected="selected">'+data.map.list[i]["name"]+'</option>';
                    }else{
                        html += '<option value="'+data.map.list[i]["id"]+'">'+data.map.list[i]["name"]+'</option>';
                    }

                }
                $("#selectBranch").html(html);
            },
            error:function(){
                alert("error");
            }
        });
        $("#selectBranch").change(function () {

            window.location.href="${ly }/steel/model-list-main?flag=${flag}&userId="+$(this).val();
        })
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

    function tz(url) {
        window.location.href = url;
    }

    // 实施计划 保存
    function inputS() {
        var cont = parseInt($('#trInput').val());
        if (cont !== '' || cont !== null || cont !== undefined) {
            if(sss==1){
                $.post(getRootPath() + "/company/model-list-save", pj(cont, idx, zzJid, vid), function (result, status) {
                    if (result.status == 0) {
                        var url = "${ly }/company/model-list-main?flag=1&plan=1";
                        window.location.href=url;
                    } else {
                        layer.msg(result.mess);
                    }
                });
            }else{
                $.post(getRootPath() + "/company/model-list-save-company", pj(cont, idx, zzJid, vid), function (result, status) {
                    if (result.status == 0) {
                        var url = "${ly }/company/model-list-main?flag=1&plan=1";
                        window.location.href=url;
                    } else {
                        layer.msg(result.mess);
                    }
                });
            }
        } else {
            layer.msg('计划不能为空');
        }

        function pj(val, idx, zzjid, vid) {
            var obj = {};
            if (idx == 1) {
                obj.danger1 = val
            } else if (idx == 2) {
                obj.danger2 = val
            } else if (idx == 3) {
                obj.danger3 = val
            } else if (idx == 4) {
                obj.danger4 = val
            } else if (idx == 5) {
                obj.danger5 = val
            } else if (idx == 6) {
                obj.danger6 = val
            } else if (idx == 7) {
                obj.danger7 = val
            } else if (idx == 8) {
                obj.danger8 = val
            } else if (idx == 9) {
                obj.danger9 = val
            } else if (idx == 10) {
                obj.danger10 = val
            } else if (idx == 11) {
                obj.danger11 = val
            } else if (idx == 12) {
                obj.danger12 = val
            } else if (idx == 13) {
                obj.danger13 = val
            } else if (idx == 14) {
                obj.danger14 = val
            } else if (idx == 15) {
                obj.danger15 = val
            } else if (idx == 16) {
                obj.danger16 = val
            } else if (idx == 17) {
                obj.danger17 = val
            } else if (idx == 18) {
                obj.danger18 = val
            } else if (idx == 19) {
                obj.danger19 = val
            } else if (idx == 20) {
                obj.danger20 = val
            }
            obj.id = vid;
            obj.dpid = zzJid;
            return obj
        }
    }


    function inputC(that, ind, zid, vvid){
        idx = ind;
        zzJid = zid;
        vid = vvid;
        if(vvid==0){
            sss = 0
        }else{
            sss = 1
        }
        $("#modal-plan2").modal("show");
    }

    function showAll(dmname, dmid, checkType, industryType, template, flag, status) {   //点击实施方法
        $.post(getRootPath() + "/company/model-list-showAll", {
            dmname: dmname, dmid: dmid, checkType: checkType, industryType: industryType, template: template, flag: flag, status : status
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
    function szss_list(dmname, industryType,template, flag) {   //点击整改复查实施方法
        sessionStorage.setItem('flag', flag);
        sessionStorage.setItem('dmname', dmname);
        sessionStorage.setItem('industryType', industryType);
        sessionStorage.setItem('template', template);
        var url = '${ly}/company/check-list-szss?dmName='+dmname+'&flag=' + flag + '&industryType=' +industryType+'&template = '+template;
        window.location.href = url;

    }




</script>
</body>
</html>