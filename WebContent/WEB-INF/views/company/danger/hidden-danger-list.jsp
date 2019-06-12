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
        <%--console.log('${list}');--%>

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
                <th width="15%">隐患内容</th>
                <th width="5%">隐患图片</th>
                <th width="5%">隐患等级</th>
                <th width="5%">治理方案</th>
                <%--<th width="5%">治理结果及日期</th>--%>
                <th width="5%">治理日期</th>
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
                    <c:if test="${list.levels!=null}">
                        <c:set var="item" value="${fn:split(list.levels,'/') }"/>
                    </c:if>
                    <td>${list.depart }</td>
                    <c:if test="${empty item[1]}">
                        <td>暂无数据</td>
                    </c:if>
                    <c:if test="${not empty item[1]}">
                        <td>${item[1]}</td>
                    </c:if>
                    <c:if test="${empty list.level2}">
                        <td>暂无数据</td>
                    </c:if>
                    <c:if test="${not empty list.level2}">
                        <td>${list.level2 }</td>
                    </c:if>
                    <%--<c:if test="${list.levels!=null}">
                        <c:if test="${not empty item[0]}">
                            <td>${item[1]}</td>
                            &lt;%&ndash;<c:if test="${not empty item[1]&&not empty item[2]}">
                                <td>${item[1]}/${item[2]}</td>
                            </c:if>
                            <c:if test="${not empty item[1]&& empty item[2]}">
                                <td>${item[1]}</td>
                            </c:if>
                            <c:if test="${ empty item[1]&& empty item[2]}">
                                <td>暂无数据</td>
                            </c:if>&ndash;%&gt;
                        </c:if>

                        <c:if test="${empty item[0]}">
                            <td>暂无数据</td>
                            <td>暂无数据</td>
                        </c:if>

                    </c:if>--%>
                        <%--                    <td>${item[0]}</td>--%>
                        <%--                    <td>${item[1]+item[2] }</td>--%>
                    <td>${list.realTimeStr}</td>
                    <td>${list.content }</td>
                    <td><c:if test="${list.files!=null}">
                        <button class="btn radius btn-danger size-S ml-20"
                                onClick="showpicture(getRootPath()+'${list.files }')">
                            <i class="Hui-iconfont" style="font-size: 15px;">&#xe613;</i> 隐患图片
                        </button>
                    </c:if>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${list.flag eq '红色'}"><font class="col-a">${list.flag}</font></c:when>
                            <c:when test="${list.flag eq '橙色'}"><font class="col-b">${list.flag}</font></c:when>
                            <c:when test="${list.flag eq '黄色'}"><font class="col-c">${list.flag}</font></c:when>
                            <c:when test="${list.flag eq '蓝色'}"><font class="col-d">${list.flag}</font></c:when>
                        </c:choose>
                    </td>
                    <td>
                        <c:if test="${list.file_address==null}">
                            <a style="text-decoration:none;margin-bottom:5px;display: none" onclick="yulan(this)"
                               href="javascript:;">预览文件</a>
                            <a style="text-decoration:none;margin-bottom:5px;display: none" onclick="xiazai(this) "
                               href="javascript:;">下载文件</a>
                        </c:if>
                        <c:if test="${list.file_address!=null}">
                            <a style="text-decoration:none;margin-bottom:5px;display: inline-block"
                               data-src="${list.file_address}" onclick="yulan(this)" href="javascript:;">预览文件</a>
                            <a style="text-decoration:none;margin-bottom:5px;display: inline-block"
                               data-src="${list.file_address}" onclick="xiazai(this)" href="javascript:;">下载文件</a>
                        </c:if>
                        <a style="text-decoration:none" onclick="uploadfile(${list.checkItemId},this)"
                           href="javascript:void(0);">上传文件</a>
                    </td>

                    <td>
                        <fmt:formatDate value="${list.recheck_time == null? '' : list.recheck_time}" pattern="yyyy-MM-dd"/>

                        <%--<c:choose>
                            <c:when test="${list.status eq 2}">未合格</c:when>
                            <c:when test="${list.status eq 3}">复查通过</c:when>
                        </c:choose>--%>
                    </td>
                    <td>${list.fjgkfzr}</td>
                    <c:if test="${list.money==null}">
                        <td onclick="touru(${list.checkItemId},this)">0</td>
                    </c:if>
                    <c:if test="${list.money!=null}">
                        <td onclick="touru(${list.checkItemId},this)">${list.money}</td>
                    </c:if>
                    <td>上报</td>
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

    <!-- 弹窗输入 -->
    <div id="modal-plan2" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" style="margin-top: 150px">
            <div class="modal-content radius">
                <div class="modal-header">
                    <h3 class="modal-title">输入治理投入(只能输入数字)</h3>
                    <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
                </div>
                <div class="modal-body" style="height: 200px; overflow-y: auto">
                    <div class="form form-horizontal">
                        <div class="row cl dq">
                            <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>输入治理投入
                                :</label>
                            <div class="formControls col-xs-5 col-sm-5">
                                <input class="input-text" type="text" name="" id="trInput"
                                       oninput="value=value.replace(/[^\d]/g,'')"
                                       style="width:150px">
                            </div>
                            <div class="col-xs-3 col-sm-3">
                                <button class="btn radius btn-primary size-S" onclick="bctr()">
                                    保存
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <form enctype="multipart/form-data" id="fm1" method='post'>
        <input type="text" name="itemId" value='' style="display: none" id="fm1_imput"/>
        <input type="file" name="file"
               accept="application/vnd.openxmlformats-officedocument.wordprocessingml.document"
               id="upload" style="display: none">
    </form>


</div>
<script type="text/javascript">
    var item_id = '';    //当前条目id
    var current = '';  //要操作的dom节点


    function uploadfile(id, dom) {
        item_id = id;
        current = $(dom);
        $("#upload").click();
    }

    $("#upload").change(function () {
        $('#fm1_imput').val(item_id);
        var form = document.querySelector("#fm1");
        var formdata = new FormData(form);
        $.ajax({
            url: getRootPath() + "/api/map/B004",    //请求的url地址 
            data: formdata,    //参数值
            type: "POST",   //请求方式
            processData: false,
            contentType: false,
            success: function (res) {
//请求成功时处理
                if (res.status == 0) {
                    current.prevAll().css('display', 'inline-block');//把隐藏的按钮显示出来
                    current.prevAll().attr("data-src", getRootPath() + res);//为隐藏的按钮设置属性为返回的路径
                    var file = document.getElementById('upload');
                    file.value = ''; //把file 内容清空
                    layer.msg('上传成功');
                } else {
                    layer.msg('上传失败');
                }
            },
            error: function (res) {
//请求出错处理
                console.log(res, '请求失败');
                layer.msg('上传失败');
            }
        });
    });

    function yulan(dom) {
        var src = $(dom).attr('data-src');
        show_dialog('查看方案', '${ly}/village/process-see?url=' + src);

    }

    function xiazai(dom) {
        var src = $(dom).attr('data-src');
        window.location.href = src;

    }

    function touru(id, dom) {
        item_id = id;
        current = $(dom);
        $("#modal-plan2").modal("show");
    }


    function bctr() {
        var val = $('#trInput').val();
        $.ajax({
            url: getRootPath() + "/village/save-money",    //请求的url地址 
            data: {itemId: item_id, money: val},    //参数值
            type: "POST",   //请求方式
            success: function (res) {
                //请求成功时处理
                if (res.status == 0) {
                    current.text(val);
                    $("#modal-plan2").modal("hide");
                    layer.msg('修改成功');
                } else {
                    layer.msg('修改失败');
                }
            },
            error: function (res) {
                //请求出错处理
                console.log(res, '请求失败');
                layer.msg('修改失败');
            }
        });
    }

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