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

        .btn-upload {
            position: relative;
            display: inline-block;
            height: 36px;
            *display: inline;
            overflow: hidden;
            vertical-align: middle;
            cursor: pointer
        }

        .upload-url {
            cursor: pointer
        }

        .input-file {
            position: absolute;
            right: 0;
            top: 0;
            cursor: pointer;
            z-index: 1;
            font-size: 30em;
            *font-size: 30px;
            opacity: 0;
            filter: alpha(opacity=0)
        }

        .btn-upload .input-text {
            width: auto
        }

        .form-group .upload-btn {
            margin-left: -1px
        }

        .my_flex {
            display: -webkit-box; /* Chrome 4+, Safari 3.1, iOS Safari 3.2+ */
            display: -moz-box; /* Firefox 17- */
            display: -webkit-flex; /* Chrome 21+, Safari 6.1+, iOS Safari 7+, Opera 15/16 */
            display: -moz-flex; /* Firefox 18+ */
            display: -ms-flexbox; /* IE 10 */
            display: flex; /* Chrome 29+, Firefox 22+, IE 11+, Opera 12.1/17/18, Android 4.4+ */
        }

        .f_c {
            -webkit-box-orient: vertical;
            -ms-flex-direction: column;
            -webkit-flex-direction: column;
            flex-direction: column;
        }

        .f_r {
            -webkit-box-orient: horizontal;
            -ms-flex-direction: row;
            -webkit-flex-direction: row;
            flex-direction: row;
        }

        .f_z_c {
            -webkit-box-pack: center;
            -ms-flex-pack: center;
            -webkit-justify-content: center;
            justify-content: center;
        }

        .f_j_c {
            -webkit-box-align: center;
            -ms-flex-align: center;
            -webkit-align-items: center;
            align-items: center;
        }

        .f_wrap {
            flex-wrap: wrap;
        }

        .item_container {
            width: 20%;
            height: 10vw;
            padding: 10px;
            box-sizing: border-box;
            position: relative;
        }

        .item_content {
            -moz-box-shadow: 0px 0px 10px #efefef;
            box-shadow: 0px 0px 10px #efefef;
            width: 100%;
            height: 100%;
            padding: 10px;
            cursor: pointer;
            overflow: hidden;
            text-overflow: ellipsis;
            /*background-color: #0B9EFB;*/
            box-sizing: border-box;
            position: relative;
        }

        .item_container > span {
            position: absolute;
            right: 12px;
            top: 17px;
            padding-right: 5px;
            display: none;
        }

        .item_container > span a {
            color: #fff;
            background: #429842;
            border-radius: 6px;
            padding: 4px 15px;
            text-decoration: none;
        }

        .item_container > span a:first-child {
            margin-right: 5px;
        }

        .item_container:hover .my_span {
            display: block;
        }
    </style>
    <script type="text/javascript">
        var id;

        function openzl(Id) {
            id = Id
            $("#upload-zl").modal("show")
        }
    </script>
</head>
<body>
<nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
    <span class="c-gray en">&gt;</span> <span>安全生产标准化</span>
    <span class="c-gray en">&gt;</span><span>安全生产管理档案</span>
    <span class="c-gray en">&gt;</span> <span>危险作业证</span>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
</nav>
<div class="page-container">
    <div class="text-c">
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <c:if test="${sort==1}">
            <button onClick="location.href = '${ly}/api/safety_Standard/findAll?parendId=0&flag=1&sort=2'"

                    class="btn btn-success radius" title="顺序" type="button" style="padding: 0 50px;">顺序
            </button>
        </c:if>
        <c:if test="${sort==2}">
            <button onClick="location.href = '${ly}/api/safety_Standard/findAll?parendId=0&flag=1&sort=1'"

                    class="btn btn-success radius" title="顺序" type="button" style="padding: 0 50px;">顺序
            </button>
        </c:if>

        <%--        <button onClick="location.href = '${ly}/api/safety_Standard/findAll?parendId=0&flag=1&sort=1'"--%>
        <%--                class="btn btn-success radius" title="倒序" type="button" style="padding: 0 50px;">添加--%>
        <%--        </button>--%>
        <button onClick="addNew()"
                class="btn btn-success radius" title="添加" type="button" style="padding: 0 50px;">添加
        </button>

        <button onClick="daoru()"
                class="btn btn-success radius" title="一键导入" type="button" style="padding: 0 50px;">一键导入
        </button>

        <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span>
    </div>
    <div class="mt-20 my_flex f_j_c f_wrap" style="width:100%;">
        <%--        <table class="table table-border table-bordered table-bg table-hover table-sort">--%>
        <%--            <thead>--%>
        <%--            <tr class="text-c">--%>
        <%--                <th width="5%">序号</th>--%>
        <%--                <th width="40%">要素名称</th>--%>
        <%--                <th width="15%">操作</th>--%>
        <%--            </tr>--%>
        <%--            </thead>--%>
        <%--            <tbody>--%>
        <%--            <!-- 循环-->--%>
        <%--            <c:forEach items="${list }" varStatus="index" var="t">--%>
        <%--                <tr class="text-c">--%>
        <%--                    <td>${index.index+1 }</td>--%>
        <%--                    <td>${t.name }</td>--%>
        <%--                    <td>--%>
        <%--                        详情,删除,修改--%>
        <%--                    </td>--%>
        <%--                </tr>--%>
        <%--            </c:forEach>--%>
        <%--            <!-- 循环结束 -->--%>
        <%--            </tbody>--%>
        <%--        </table>--%>

        <c:forEach items="${list }" varStatus="index" var="t">
            <div class="item_container">
                <div class="item_content my_flex f_r f_j_c f_z_c"
                     onclick="location.href = '${ly}/api/safety_Standard/findByParentId?safetyStandardlistId=${t.id}'">
                        ${t.name }
                </div>
                <span class="my_span"><a onclick="addNew(${t.id})">编辑</a><a onclick="del(${t.id})">删除</a></span>
            </div>
        </c:forEach>
    </div>


    <!-- 弹窗输入 -->
    <div id="modal-plan2" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" style="margin-top: 150px">
            <div class="modal-content radius">
                <div class="modal-header">
                    <h3 class="modal-title">输入要新增A级要素</h3>
                    <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
                </div>
                <div class="modal-body" style="height: 200px; overflow-y: auto">
                    <div class="form form-horizontal">
                        <div class="row cl dq">
                            <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>输入
                                :</label>
                            <div class="formControls col-xs-5 col-sm-5">
                                                       <%--         <input class="input-text" type="text" name="" id="trInput"
                                                                       style="width:150px">--%>
                                <textarea id="trInput" cols="6" rows="3" style="overflow:hidden;width: 300px"></textarea>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-xs-3 col-sm-3 col-xs-offset-8 col-sm-offset-8">
                                <button class="btn radius btn-primary size-S" onclick="queren()">
                                    确认
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <!-- 弹窗修改输入 -->
    <div id="modal-plan3" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" style="margin-top: 150px">
            <div class="modal-content radius">
                <div class="modal-header">
                    <h3 class="modal-title">输入要新增A级要素</h3>
                    <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
                </div>
                <div class="modal-body" style="height: 200px; overflow-y: auto">
                    <div class="form form-horizontal">
                        <div class="row cl dq">
                            <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>输入
                                :</label>
                            <div class="formControls col-xs-5 col-sm-5">
                                                           <%--     <input class="input-text" type="text" name="" id="trInput"
                                                                       style="width:150px">--%>
                                <textarea id="trInput"  cols="6" rows="6" style="overflow:hidden;width: 300px"></textarea>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-xs-3 col-sm-3 offset3">
                                <button class="btn radius btn-primary size-S" onclick="queren()">
                                    确认
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<!-- 弹窗上传资料 -->

<script type="text/javascript">
    <%--  显示  --%>

    function showDiv(that) {
        console.log(that)
    }

    <%-- 新增 --%>
    var str = 0

    function addNew() {
        $("#modal-plan2").modal("show");
    }

    function edit() {
        $("#modal-plan3").modal("show");
    }

    <%--  添加保存  --%>

    function queren() {
        $.ajax({
            url: getRootPath() + "/api/safety_Standard/save",    //请求的url地址 
            data: JSON.stringify({
                parentId: str,
                name: $('#trInput').val()
            }),    //参数值
            type: "POST",   //请求方式
            dataType: 'json', //返回值类型 一般设置为json
            contentType: "application/json",
            success: function (res) {
                layer.alert(res.mess, "", function () {
                    $("#modal-plan2").modal("hide");
                    if (res.mess == "保存成功") {
                        location.reload();
                    }
                })
            }
        });
    }


    function daoru() {
        $.ajax({
            url: getRootPath() + "/api/safety_Standard/Automatic-import",    //请求的url地址 
            type: "POST",   //请求方式
            dataType: 'json', //返回值类型 一般设置为json
            success: function (res) {
               layer.msg(res.mess);
                location.reload();
            }
        });
    }

    /*删除*/
    function del(id) {
        $.ajax({
            url: getRootPath() + "/api/safety_Standard/delete-tSafetyStandard",    //请求的url地址 
            data: {
                'safetyStandardlistId': parseInt(id)
            },    //参数值
            type: "POST",   //请求方式
            success: function (res) {
                layer.alert(res.mess, "", function () {
                    if (res.status == '0') {
                        location.reload();
                    }
                })
            }
        });
    }

    /*上传*/
    function upload(isType) {
        $.ajaxFileUpload({
            url: getRootPath() + '/company/tables/tab-leadin?isType=' + isType + "&id=" + id, //用于文件上传的服务器端请求地址
            secureuri: false, //一般设置为false
            fileElementId: 'file', //文件上传空间的id属性  <input type="file" id="file" name="file" />
            dataType: 'json', //返回值类型 一般设置为json
            async: false,
            success: function (result) { //服务器成功响应处理函数
                $(".upload-url").val("");
                if (result.status == '0') {
                    layer.alert("导入成功", "", function () {
                        location.reload();
                    })
                } else {
                    if (null != result.map.message) {
                        layer.alert(result.map.message);
                    } else {
                        var list = result.map.list;
                        layer.alert(list.join("<br>"));
                    }
                }
            },
            error: function (data, status, e) {//服务器响应失败处理函数
                alert("文件上传失败");
            }
        })
    }
</script>
</body>
</html>