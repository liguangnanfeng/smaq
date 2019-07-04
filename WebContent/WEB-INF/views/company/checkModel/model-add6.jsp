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

        .wid1 {
            width: 30%
        }

        .wid2 {
            width: 30%
        }

        .wid3 {
            width: 30%
        }

        .wid4 {
            width: 10%
        }

        .wid5 {
            width: 70%
        }

        .btn-cz {
            margin: auto;
            background: #eee;
            border: 1px solid #ddd;
            padding: 0 5px;
            height: 20px;
            line-height: 20px;
            border-radius: 5px;
            margin-left: 15px
        }

        .btn-cz:hover {
            border: 1px solid #06c;
            color: #06c;
            text-decoration: none;
        }

        body .check-box label {
            margin-left: 5px;
        }

        .one_danger .list_odan {
            margin-left: 20px
        }

        .two_danger .list_twdan {
            margin-left: 45px;
        }

        .one_danger .Hui-iconfont {
            margin-top: 2px
        }

        .one_danger, .list_danger {
            display: inline-block
        }

        .one_danger, .one_danger .list_odan, .two_danger, .two_danger .list_twdan, .list_danger {
            float: none;
        }

        .radio-lx {
            display: inline-block;
            cursor: pointer;
            height: 50px;
            font-size: 14px;
            line-height: 50px;
            margin-bottom: 10px;
            padding: 0 35px;
            margin-right: 30px;
            background: #f5f5f5;
            border-radius: 5px;
        }

        .radio-lxxz {
            background: #D9EDF7;
            color: #31708F;
        }

        .l2 {
            display: none;
        }

        .bxt {
            margin-right: 5px;
        }
    </style>
    <script type="text/javascript">
        var m_index = 0;

        $(function () {
            $(".three_danger").each(function () {
                var s = $(this).find("label").html();
                var h = $(this).prev().find("label").html();
                if (s == h) {
                    $(this).find(".three_dtitle").hide();
                    $(this).find(".bxt").show();
                    $(this).prev().find(".bxt").show();
                }
            })
            /* 弹窗树 */
            /* 弹窗树二级选中 */
            $(".two_dtitle .Hui-iconfont").on("click", function () {
                if ($(this).parent().parent().hasClass("two_dangerxz")) {
                    $(this).parent().parent().removeClass("two_dangerxz");
                    $(this).parent().parent().find(".Hui-iconfont").html("&#xe604;");
                    $(this).closest(".two_danger").find(".three_danger").removeClass("three_dangerxz");
                } else {
                    $(this).parent().parent().addClass("two_dangerxz");
                    $(this).html("&#xe631;");
                }
            });
            /* 弹窗树一级选中 */
            $(".one_dtitle .Hui-iconfont").on("click", function () {
                if ($(this).parent().parent().hasClass("one_dangerxz")) {
                    $(this).parent().parent().removeClass("one_dangerxz");
                    $(this).parent().parent().find(".Hui-iconfont").html("&#xe604;");
                    $(this).closest(".one_danger").find(".three_danger").removeClass("three_dangerxz");
                    $(this).closest(".one_danger").find(".two_danger").removeClass("two_dangerxz");
                } else {
                    $(this).parent().parent().addClass("one_dangerxz");
                    $(this).html("&#xe631;");
                }
            });

            //选择事件
            $(":checkbox").click(function () {
                var l = $(this).data("l");
                if (l == 1) {
                    $(this).closest(".one_danger").find(":checkbox").prop("checked", $(this).is(":checked"));
                } else if (l == 2) {
                    $(this).closest(".two_danger").find(":checkbox").prop("checked", $(this).is(":checked"));
                    var t = $(this).closest(".one_danger");
                    t.find(":checkbox[data-l='1']").prop("checked", false).prop("checked", t.find(":checkbox:checked").length > 0);
                } else if (l == 3) {
                    $(this).closest(".three_danger").find(":checkbox").prop("checked", $(this).is(":checked"));
                    var t = $(this).closest(".one_danger");
                    var t2 = $(this).closest(".two_danger");
                    t2.find(":checkbox[data-l='2']").prop("checked", false).prop("checked", t2.find(":checkbox:checked").length > 0);
                    t.find(":checkbox[data-l='1']").prop("checked", false).prop("checked", t.find(":checkbox:checked").length > 0);
                } else if (l == 4) {
                    var t = $(this).closest(".one_danger");
                    var t2 = $(this).closest(".two_danger");
                    var t3 = $(this).closest(".three_danger");
                    t3.find(":checkbox[data-l='3']").prop("checked", false).prop("checked", t3.find(":checkbox:checked").length > 0);
                    t2.find(":checkbox[data-l='2']").prop("checked", false).prop("checked", t2.find(":checkbox:checked").length > 0);
                    t.find(":checkbox[data-l='1']").prop("checked", false).prop("checked", t.find(":checkbox:checked").length > 0);
                }
            })
        });


        function nature(obj) {

            var cType = $(obj);
            checkType = parseInt(cType.val());
            if (checkType == 0) {
                window.location.href = "${ly}/village/getCheckModelBasic?flag=${flag}"
            } else {
                // var tableName = $('#title').val();
                <%--window.location.href="${ly}/village/select-all-level1?flag=${flag}&tableName="+tableName+'&checkType='+checkType--%>
                window.location.href = "${ly}/village/select-all-level1?flag=${flag}&checkType=" + checkType
            }

        }


    </script>
</head>
<body>
<div class="page-container">


    <div class="modal-body">
        <form class="form form-horizontal" id="form">
            <%--                        <div class="row cl">--%>
            <%--                            <label class="form-label col-xs-2 col-sm-2" style=""><span class="c-red">*</span>检查表名称：</label>--%>
            <%--                            <div class="formControls col-xs-8 col-sm-9">--%>
            <%--                                <c:if test="${not empty tableName}">--%>
            <%--                                    <input type="text" id="title" style="width:350px" value="${tableName}" class="input-text"--%>
            <%--                                           maxlength="50" placeholder="请填写检查表名名称"/>--%>
            <%--                                </c:if>--%>
            <%--                                <c:if test="${empty tableName}">--%>
            <%--                                    <input type="text" id="title" style="width:350px" class="input-text"--%>
            <%--                                           maxlength="50" placeholder="请填写检查表名名称"/>--%>
            <%--                                </c:if>--%>
            <%--                            </div>--%>
            <%--                        </div>--%>

            <c:if test="${flag==1&&lxType==2}">
                <div class="row cl">
                    <label class="form-label col-xs-2 col-sm-2" style=""><span class="c-red">*</span>填写周期天数：</label>
                    <div class="formControls col-xs-8 col-sm-9">
                        <input type="number" min="1" max="7" id="cycle" style="width:200px" class="input-text"
                               maxlength="50" placeholder="填写周期天数"/>
                    </div>
                </div>
            </c:if>
            <div class="row cl" style="margin-top: 20px;${flag==1?'display:none':'display:block'}">
                <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>请选择检查性质 :</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <c:if test="${checkType==-1}">
                        <select name="checkNature" class="select" id="checkNature" onchange="nature(this)"
                                style="width:350px;height: 31px">
                            <option value="0">请选择检查类别</option>
                            <option value="-1" selected>基础</option>
                            <option value="-2">现场</option>
                        </select>
                    </c:if>
                    <c:if test="${checkType==-2}">
                        <select name="checkNature" class="select" id="checkNature" onchange="nature(this)"
                                style="width:350px;height: 31px">
                            <option value="0">请选择检查类别</option>
                            <option value="-1">基础</option>
                            <option value="-2" selected>现场</option>
                        </select>
                    </c:if>
                    <c:if test="${empty checkType}">
                        <select name="checkNature" class="select" id="checkNature" onchange="nature(this)"
                                style="width:350px;height: 31px">
                            <option value="0">请选择检查类别</option>
                            <option value="-1">基础</option>
                            <option value="-2">现场</option>
                        </select>
                    </c:if>
                </div>
            </div>
        </form>

        <div class="list_danger l1" style="width:80%;margin-left:17%;margin-top: 30px">
            <!-- 循环一级 -->
            <c:forEach items="${list }" var="be">
                <div class="one_danger one_dangerxz">
                    <div class="one_dtitle">
                        <i class="Hui-iconfont">&#xe631;</i>
                        <div class="check-box">
                            <input type="checkbox" data-l="1" name="check">
                            <label>${be.key }</label>
                        </div>
                    </div>
                    <div class="list_odan">
                        <!-- 循环二级 -->
                        <c:forEach items="${be.value }" var="be2">
                            <div class="two_danger">
                                <div class="two_dtitle">
                                    <i class="Hui-iconfont">&#xe604;</i>
                                    <div class="check-box">
                                        <input type="checkbox" data-l="2" name="check">
                                        <label>${be2 }</label>
                                    </div>
                                </div>
                                <div class="list_twdan">
                                    <!-- 循环三级 -->
                                    <c:set var="l3" value=""/>
                                    <c:set var="l3_" value="1"/>
                                    <c:forEach items="${dL }" var="be3">
                                        <c:set var="key_">${be3.level1}_${be3.level2}_${be3.level3}</c:set>
                                        <c:if test="${be3.level1 eq be.key and be3.level2 eq be2 and !lanlin:constains(l3,key_,',')}">
                                            <c:set var="l3">${l3 },${key_ }</c:set>
                                            <div class="three_danger">
                                                <div class="three_dtitle">
                                                    <div class="check-box">
                                                        <input type="checkbox" data-l="3" name="check"
                                                               value="${be3.id }" class="xt">
                                                        <label>${be3.level3 }</label>
                                                    </div>
                                                </div>
                                                <!-- 判断三级名称是否相同，循环 -->
                                                <c:forEach items="${dL }" var="be4">
                                                    <c:if test="${be4.level1 eq be.key and be4.level2 eq be2 and be4.level3 eq be3.level3}">
                                                        <p class="p-three">
                                                            <!-- 判断三级相同，复选框显示 -->
                                                            <input type="checkbox" data-l="4" name="check"
                                                                   value="${be4.id }" class="bxt">${be4.factors }
                                                        </p>
                                                    </c:if>
                                                </c:forEach>
                                                <!-- 循环结束 -->
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                    <!-- 三级结束 -->
                                </div>
                            </div>
                        </c:forEach>
                        <!-- 二级结束 -->
                    </div>
                </div>
            </c:forEach>
            <!-- 一级结束  -->
        </div>


        <form class="form form-horizontal" id="form2">
            <div id="addContainer" class="row"></div>
            <div class="addCh1 row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2 mt-20">
                    <button onClick="addItem()" class="btn btn-primary radius" type="button"
                            style="padding: 0 70px;">
                        <i class="Hui-iconfont">&#xe632;</i>新增自定义检查项
                    </button>
                </div>
            </div>
        </form>
    </div>

    <div class="mt-20 f-l mb-40" style="width:100%;text-align:center;">
        <button onClick="save_1()" class="btn btn-primary radius mt-20" type="button" style="padding: 0 70px;">
            <i class="Hui-iconfont mr-10">&#xe632;</i>保存
        </button>
    </div>
</div>
</body>
<script type="text/javascript">
    var depId = '${depId}';
    var flag = '${flag}';

    function addItem() {
        m_index++;
        var add1 = `<div class="row" style="height: 31px">
        <div class="col-xs-5 cl level1">
        <label class="form-label col-xs-5 col-sm-5"><span class="c-red">*</span>检查项目 :</label>
        <div class="formControls col-xs-7 col-sm-7">
        <input type="text" id="project` + m_index + `" class="input-text" style="width:300px;">
        </div>
        </div>
        <div class="col-xs-5 cl level1">
        <label class="form-label col-xs-5 col-sm-5"><span class="c-red">*</span>检查内容 :</label>
        <div class="formControls col-xs-7 col-sm-7">
        <input type="text" id="content` + m_index + `"class="input-text" style="width:300px;">
        </div>
        </div>
        </div>`;
        $('#addContainer').append(add1);
    }


    function save_1() {
        // var tableName = $("#title").val();
        var checkType = $("#checkNature").val();
        // if(!tableName){
        //     layer.alert('请填写检查表名称')
        // return false;
        // }
        if (checkType == 0 || checkType == '' || checkType == null) {
            layer.alert('请选择检查类别')
            return false;
        }

        if ('${flag}' == 1 && '${lxType}' == 2) {
            if (!$('#cycle').val()) {
                layer.alert('请填写周期天数,且不能为零')
                return false;
            }

        }
        var cusCheckItemList = [];//自定义检查项
        for (var j = 1; j <= m_index; j++) {
            var cusCheck = {
                'level3': $('#project' + j).val(),
                'level4': $('#content' + j).val(),
            }
            if (cusCheck.level3 && cusCheck.level4) {
                cusCheckItemList.push(cusCheck);
            }

        }
        var l = $(":checkbox:checked[data-l='4']");
        if (l.length == 0 && cusCheckItemList.length == 0) {
            layer.alert("请至少选择一个风险。");
            return false;
        }
        var ids = new Array();
        $.each(l, function (i, item) {
            ids.push($(this).val());
        })
        ids = ids.join(",");
        ids += ',';
        var i = layer.load();
        var params = {
            "flag": parseInt('${flag}'),
            // "tableName": tableName,//检查表名称
            "checkType": parseInt(checkType),//检查方式
            "selectItems": ids,      //检查项
            "cusCheckItemList": cusCheckItemList  //自定义检查项
        }
        if (flag == 1) {
            params.lxType = parseInt('${lxType}');
            if ('${lxType}' == 2) {
                params.cycle = parseInt($('#cycle').val());
            }
        }
        $.ajax({
            type: "POST",
            url: getRootPath() + '/village/save-administrative',
            data: JSON.stringify(params),
            async: false,
            contentType: "application/json",
            dataType: "json",
            success: function (result) {
                if (result.status == 0) {
                    layer.close(i);
                    layer.alert('保存成功');
                    if (flag == 1) {
                        window.parent.location.href = '${ly }/company/model-list-main?flag=1';
                    } else {
                        window.parent.location.href = '${ly }/company/model-list-cx?flag=${flag}&type=1&template=2';
                    }

                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                } else {
                    layer.alert('保存失败');
                    layer.close(i);
                }

            },
            complete: function (XMLHttpRequest, textStatus) {
                layer.close(i);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                layer.alert("添加失败")
                layer.close(i);
            }
        });

    }
</script>
</html>