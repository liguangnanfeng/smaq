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

        .btn-group .btn {
            height: 34px;
            line-height: 34px;
            padding: 0 25px;
        }

        .r strong {
            color: red;
        }

        .div_imgp {
            float: left;
            width: 90%;
            margin-left: 5%;
            text-align: center;
        }

        .div_imgp img {
            max-width: 100%
        }
    </style>
    <script type="text/javascript">


        console.log("${list}");

        function showpicture(memoImg) {
            //memoImg = "";
            if (memoImg.length !== 0) {
                $("#memoImg").attr("src", getRootPath() + memoImg);
            }
            $("#modal-plan").modal("show");
        }
    </script>
</head>
<body>
<nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
    <span class="c-gray en">&gt;</span> <span>隐患统计分析系统</span>
    <span class="c-gray en">&gt;</span> <span>隐患治理记录</span>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
</nav>
<div class="page-container">
    <div id="spTab" class="btn-group" style="text-align: center;margin-bottom: 20px;">
        <a class="btn default ${flag == 1 ? 'btn-primary' : 'radius'}"
           href="${ly }/village/hidden-danger-list?flag=1&status=1">企业自查</a>
        <a class="btn default ${flag == 4 ? 'btn-primary' : 'radius'}"
           href="${ly }/village/hidden-danger-list?flag=4">行政检查</a>
        <a class="btn default ${flag == 3 ? 'btn-primary' : 'radius'}"
           href="${ly }/village/hidden-danger-list?flag=3">部门抽查</a>
        <a class="btn default ${flag == 2 ? 'btn-primary' : 'radius'}"
           href="${ly }/village/hidden-danger-list?flag=2">执法检查</a>
    </div>
    <%--<div class="text-c">--%>
    <%--<form action="${ly }/village/danger-index-list?flag=${flag}" method="post">--%>
    <%--<div class="dis-ib">--%>
    <%--<span>部位名称：</span>--%>
    <%--<input type="text" value="${partName }" name="partName" class="input-text mb-5 mt-5" style="width:150px;">--%>
    <%--</div>--%>
    <%--<div class="dis-ib">--%>
    <%--<span>整改状态：</span>--%>
    <%--<select name="status" class="input-text mb-5 mt-5" style="width:150px;">--%>
    <%--<option value="1">全部</option>--%>
    <%--<option value="2" <c:if test="${status == 2}"> selected</c:if>>未整改</option>--%>
    <%--<option value="3" <c:if test="${status == 3}"> selected</c:if>>已整改</option>--%>
    <%--</select>--%>
    <%--</div>--%>
    <%--<div class="dis-ib">--%>
    <%--<span>隐患等级：</span>--%>
    <%--<select name="d" class="input-text mb-5 mt-5" style="width:150px;">--%>
    <%--<option value="">全部</option>--%>
    <%--<option value="1" <c:if test="${d == 1}"> selected</c:if>>重大隐患</option>--%>
    <%--<option value="0" <c:if test="${d == 0}"> selected</c:if>>一般隐患</option>--%>
    <%--</select>--%>
    <%--</div>--%>
    <%--<div class="dis-ib">--%>
    <%--<span>查询时间段：</span>--%>
    <%--<input type="text" name="startTime" value="${startTime }" class="Wdate input-text" id="sT" style="width:100px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>--%>
    <%--<font>-</font>--%>
    <%--<input type="text" name="endTime" value="${endTime }" class="Wdate input-text" id="eT" style="width:100px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>--%>
    <%--</div>--%>
    <%--<button class="btn btn-success" type="submit">--%>
    <%--<i class="Hui-iconfont">&#xe665;</i> 查询--%>
    <%--</button>--%>
    <%--</form>--%>
    <%--</div>--%>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <%--<p class="paging">--%>
        <%--<a href="${ly }/village/recheck-list?flag=${flag}&page=${page-1 }">    &lt; 上一页 </a>--%>
        <%--<strong>第${page+1}页/共${count}页</strong>--%>
        <%--<a href="${ly }/village/recheck-list?flag=${flag}&page=${page+1}">下一页 &gt;</a>--%>
        <%--</p>--%>
    </div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-bg table-hover table-sort">
            <thead>
            <tr class="text-c">
                <th width="5%">检查类型</th>
                <th width="5%">车间/场所</th>
                <th width="5%">系统</th>
                <th width="5%">环节/部位</th>
                <th width="5%">发生日期</th>
                <th width="10%">隐患内容</th>
                <th width="5%">隐患图片</th>
                <th width="5%">隐患等级</th>
                <th width="5%">治理方案</th>
                <th width="10%">治理结果及日期</th>
                <th width="5%">治理责任人</th>
                <th width="5%">治理投入</th>
                <th width="5%">上报</th>
            </tr>
            </thead>
            <tbody>
            <!-- 循环开始 -->
            <c:set var="x" value="${fn:split('基础检查/现场检查/高危检查','/') }"/>
            <c:forEach items="${list }" varStatus="index" var="list">
                <tr class="text-c">
                    <c:choose>
                        <c:when test="${list.type == 1}">
                            <td>日常</td>
                        </c:when>
                        <c:when test="${list.type == 2}">
                            <td>定期</td>
                        </c:when>
                        <c:when test="${list.type == 3}">
                            <td>季节</td>
                        </c:when>
                        <c:when test="${list.type == 4}">
                            <td>其他</td>
                        </c:when>
                        <c:when test="${list.type == 5}">
                            <td>综合</td>
                        </c:when>
                    </c:choose>
                    <c:set var="item" value="${fn:split(list.levels,'/') }"/>
                    <td>${list.depart }</td>
                    <td>${item[0]}</td>
                    <td>${item[1]+item[2] }</td>
                    <td>${list.realTimeStr}</td>
                    <td>${list.content }</td>
                    <td><c:if test="${list.files!=null}">
                        <button class="btn radius btn-danger size-S ml-20"
                                onClick="showpicture(getRootPath()+'${list.files }')">
                            <i class="Hui-iconfont" style="font-size: 15px;">&#xe613;</i> 隐患图片
                        </button>
                    </c:if>
                    </td>
                    <td>${list.flag }</td>
                    <td>
                        <c:if test="${list.address!=null}">
                            <button class="btn radius btn-danger size-S ml-20"
                                    onClick="showpicture(getRootPath()+'${list.files }')">
                                <i class="Hui-iconfont" style="font-size: 15px;">&#xe613;</i> 预览文件
                            </button>
                            <button class="btn radius btn-danger size-S ml-20"
                                    onClick="showpicture(getRootPath()+'${list.files }')">
                                <i class="Hui-iconfont" style="font-size: 15px;">&#xe613;</i> 下载文件
                            </button>
                        </c:if>
                        <button class="btn radius btn-danger size-S ml-20"
                                onClick="showpicture(getRootPath()+'${list.files }')">
                            <i class="Hui-iconfont" style="font-size: 15px;">&#xe613;</i> 上传文件
                        </button>
                    </td>
                    <td>治理结果及日期</td>
                    <td>${list.fjgkfzr}</td>
                    <td>${list.money}</td>
                    <td>上传</td>
                </tr>
            </c:forEach>
            <!-- 循环结束 -->
            </tbody>
        </table>
    </div>
    <!-- 弹窗图片 -->


    <div id="modal-plan" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content radius">
                <div class="modal-header">
                    <h3 class="modal-title">查看图片</h3>
                    <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
                </div>
                <div class="modal-body" style="height: 400px; overflow-y: auto">
                    <!-- 循环图片 -->
                    <div class="div_imgp">
                        <img src="${ly }/images/zwtp.jpg" class="img-responsive mt-20">
                    </div>
                    <!-- 循环结束 -->
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $('.table-sort').dataTable({
            "aaSorting": [[0, "asc"]],//默认第几个排序
            "bStateSave": false,//状态保存
            "aoColumnDefs": []
        });
    });

    function showpicture(src) {
        $(".div_imgp img").attr("src", src);
        $("#modal-plan").modal("show")
    }
</script>
</body>
</html>