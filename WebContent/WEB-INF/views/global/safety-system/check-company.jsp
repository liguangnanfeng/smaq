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
        <span class="c-gray en">&gt;</span> <span>${flag!=2 ? '隐患排查治理系统' : '行政执法系统'}</span>
        <c:if test="${flag != 2}">
                <span class="c-gray en">&gt;</span> <span>隐患排查记录</span>
        </c:if>
        <c:if test="${flag == 2}">
                <span class="c-gray en">&gt;</span> <span>执法记录</span>
        </c:if>
        <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
                <i class="Hui-iconfont">&#xe68f;</i>
        </a>
</nav>
<div class="page-container">
       <%-- <c:if test="${flag != 2}">
                <div id="spTab" class="btn-group" style="text-align: center;margin-bottom: 20px;">
                        <a class="btn default ${flag == 1 ? 'btn-primary' : 'radius'}" href="${ly }/global/check-company?flag=1&status=2">企业自查</a>
                        <a class="btn default ${flag == 4 ? 'btn-primary' : 'radius'}" href="${ly }/global/check-company?flag=4&status=2">行政检查</a>
                        <a class="btn default ${flag == 3 ? 'btn-primary' : 'radius'}" href="${ly }/global/check-company?flag=3&status=2">部门抽查</a>
                                &lt;%&ndash; <a class="btn default ${flag == 2 ? 'btn-primary' : 'radius'}" href="${ly }/village/check-list?flag=2">执法检查</a> &ndash;%&gt;
                </div>
        </c:if>--%>
        <div class="text-c">
                <form action="${ly }/village/check-list?flag=${flag}" method="post">
                        <%--<div class="dis-ib">
                        <span>检查表名称：</span>
                       &lt;%&ndash; <input type="text" value="${title}" name="title" class="input-text mb-5 mt-5" style="width:150px;">&ndash;%&gt;
                        </div>--%>
                        <%--        <div class="dis-ib">--%>
                        <%--          <span>检查状态：</span>--%>
                        <%--          <select class="input-text mb-5 mt-5" name="status" style="width:150px;">--%>
                        <%--            <option value="">请选择</option>--%>
                        <%--            <option value="1" <c:if test="${status == 1}"> selected</c:if>>未检查</option>--%>
                        <%--            <option value="2" <c:if test="${status == 2}"> selected</c:if>>已检查</option>--%>
                        <%--          </select>--%>
                        <%--          </div>--%>
                        <%--<button class="btn btn-success" type="submit">
                        <i class="Hui-iconfont">&#xe665;</i> 查询
                        </button>--%>
                </form>
        </div>
        <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
        <!--   <c:if test="${flag == 2}">
        <a class="btn btn-primary radius" data-title="添加行政检查表" data-href="${ly }/village/check-add?flag=2"
        onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont"
        style="font-size:15px;">&#xe600;</i> 添加行政检查表</a>
    </c:if>
        <c:if test="${flag == 3}">
            <a class="btn btn-primary radius" data-title="添加线下检查记录" data-href="${ly }/village/check-add3?flag=3"
            onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont"
            style="font-size:15px;">&#xe600;</i> 添加线下检查记录</a>
        </c:if>
        <c:if test="${flag == 4}">
            <a class="btn btn-primary radius" data-title="添加线下检查记录" data-href="${ly }/village/check-add3?flag=4"
            onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 添加线下检查记录</a>
        </c:if>  -->
        </span>
                <span class="r">共有数据：<strong>${fn:length(map) }</strong> 条</span>
        </div>
        <div class="mt-20">
                <table class="table table-border table-bordered table-bg table-hover table-sort">
                        <thead>
                        <tr class="text-c">
                                <th width="5%">序号</th>
                                <th width="15%">企业名称</th>
                                <th width="15%">所属行业</th>
                                <th width="8%">风险等级</th>
                                <th width="15%">所在地</th>
                                <th width="8%">规模类型</th>
                                <th width="8%">运行状态</th>
                                <th width="8%">重点企业</th>
                                <th width="8%">本级管控</th>
                                <th width="15%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <!-- 循环-->
                        <script>

                        </script>
                        <c:forEach items="${list }" varStatus="index" var="co">
                                <tr class="text-c">
                                        <td>${index.index + 1 }</td>
                                        <td>${co.name}</td>
                                        <td>${co.industry}</td>
                                        <td>${co.dlevel}</td>
                                        <td>${co.address}</td>
                                        <td>${co.scale}</td>
                                        <td>${co.ifFreeze}</td>
                                        <td >
                                                <c:if test="${1 == co.is_key }">
                                                        是
                                                </c:if>

                                                <c:if test="${0 == co.is_key }">
                                                        否
                                                </c:if>
                                        </td>
                                        <td id="choose">
                                                <c:if test="${userType == co.is_controls }">
                                                        是
                                                </c:if>

                                                <c:if test="${userType != co.is_controls }">
                                                        否
                                                </c:if>
                                        </td>
                                        <td>
                                                <c:if test="${1 == co.flag }">
                                                        <a style="text-decoration:none" onClick="user_isControls(${co.userId },1)" href="javascript:;">设为本级管控</a>

                                                        <a style="text-decoration:none" onClick="user_isControls(${co.userId },0)" href="javascript:;">取消本级管控</a>
                                                </c:if>

                                                <c:if test="${2 == co.flag }">
                                                        <a style="text-decoration:none;  cursor: default;opacity: 0.2" onclick="return false;" >设为本级管控</a>

                                                        <a style="text-decoration:none;  cursor: default;opacity: 0.2" onclick="return false;" >取消本级管控</a>
                                                </c:if>

                                                <c:if test="${3 == co.flag }">
                                                        <a style="text-decoration:none" onClick="user_isControls(${co.userId },1)" href="javascript:;">设为本级管控</a>

                                                        <a style="text-decoration:none;  cursor: default;opacity: 0.2" onclick="return false;" >取消本级管控</a>
                                                </c:if>

                                                <c:if test="${4 == co.flag }">
                                                        <a style="text-decoration:none;  cursor: default;opacity: 0.2" onclick="return false;" >设为本级管控</a>

                                                        <a style="text-decoration:none" onClick="user_isControls(${co.userId },0)" href="javascript:;">取消本级管控</a>
                                                </c:if>
                                        </td>
                                </tr>
                        </c:forEach>
                        <!-- 循环结束 -->
                        </tbody>
                </table>
        </div>
</div>
<script type="text/javascript">

    function user_isControls(userId,isControls){

        var text = isControls=="1"?"确认设置该企业为本级管控企业吗":"确认取消该本级管控企业吗";
        layer.confirm(text,function(){
            var index = layer.load();
            $.post(getRootPath() + "/global/user-isControls",{
                userId : userId,
                flag : isControls
            },function(result){
                location.reload();
<%--                layer.closeAll();--%>
<%--               let c=$("#choose").text();--%>
<%--               c=="否"?$("#choose").text("是"):$("#choose").text("否");--%>
<%--                console.log($("#choose").text())--%>
            })
        });
    }


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