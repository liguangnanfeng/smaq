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
    <link rel="stylesheet" type="text/css" media="print"  href="${ly}/js/H-ui.admin_v3.0/static/h-ui/css/H-ui.css" />
    <link rel="stylesheet" type="text/css" href="${ly}/styles/common.css" media="print"  />
    <style type="text/css">
        .input-text {
            position: relative
        }

        .txtarea_sq {
            height: 100px;
            width: 350px;
        }

        body .check-box, .radio-box {
            line-height: 30px;
        }

        .div_pdetail {
            width: 100%;
            font-size: 15px;
    display: flex;
    justify-content: space-between;
        }

        .div_pdetail font {
            float: left;
            font-size: 15px;
            margin-left: 15px;
            font-weight: bold;
        }

        .div_pdetail label {
            float: right!important;
            font-size: 15px;
            margin-right: 15px;
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

        .div_pleft {
            width: 150px;
            display: inline-block;
            line-height: 32px;
            font-size: 14px;
        }

        .div_pright {
            width: 300px;
            display: inline-block;
        }

        .div_pri {
            width: 100%;
            min-height: 32px
        }

        .div_pri font {
            float: right;
            line-height: 32px;
            font-size: 14px;
            margin-right: 15px;
        }

        .div_pri .Wdate {
            float: right;
        }

        body .select {
            height: 32px;
            line-height: 32px;
        }
      .center{
      text-align:center!important;
    }
    </style>
    <script type="text/javascript">
    console.log("v/opinion-detail");
    console.log(${itemL});
        function showpicture(src, obj) {
            var memo = $(obj).closest("td").find("div[data-name='item-memo']").html();
            $(".modal-body .mt-10").html("隐患描述：" + (memo == '' ? '暂无描述' : memo));
            $(".div_imgp").html('');
            $.each(src.split(","), function (i, item) {
                $(".div_imgp").append('<div class="div_imgp">' +
                    '<img src="' + item + '" class="img-responsive mt-20" >' +
                    '</div>');
            })
            $("#modal-plan").modal("show")
        }

        $(function () {
            $(":checkbox[name='lj_qx']").click(function () {
                if (!$(this).prop("checked")) {
                    $(":checkbox[name='isli']").prop("checked", true);
                    $(":checkbox[name='isxian']").prop("checked", false);
                } else {
                    $(":checkbox[name='isli']").prop("checked", false);
                    $(":checkbox[name='isxian']").prop("checked", true);
                }
                init_all_box();
            })

            $(":checkbox[name='xq_qx']").click(function () {
                if (!$(this).prop("checked")) {
                    $(":checkbox[name='isli']").prop("checked", false);
                    $(":checkbox[name='isxian']").prop("checked", true);
                } else {
                    $(":checkbox[name='isli']").prop("checked", true);
                    $(":checkbox[name='isxian']").prop("checked", false);
                }
                init_all_box();
            })

            $(":checkbox[name='yz_qx']").click(function () {
                if (!$(this).prop("checked")) {
                    $(":checkbox[name='isyan']").prop("checked", true);
                } else {
                    $(":checkbox[name='isyan']").prop("checked", false);
                }
                init_all_box();
            })
        })

        function init_all_box() {
            if ($(":checkbox[name='isli']").length == $(":checkbox[name='isli']:checked").length) {
                $(":checkbox[name='lj_qx']").prop("checked", true);
            } else {
                $(":checkbox[name='lj_qx']").prop("checked", false);
            }
            if ($(":checkbox[name='isxian']").length == $(":checkbox[name='isxian']:checked").length) {
                $(":checkbox[name='xq_qx']").prop("checked", true);
            } else {
                $(":checkbox[name='xq_qx']").prop("checked", false);
            }
            if ($(":checkbox[name='isyan']").length == $(":checkbox[name='isyan']:checked").length) {
                $(":checkbox[name='yz_qx']").prop("checked", true);
            } else {
                $(":checkbox[name='yz_qx']").prop("checked", false);
            }
        }
    </script>

    </head>
<body>
<div class="page-container"  style="page-break-after:always">
    <div class="div_pdetail">
        <%--<button onClick="top.show_tab('治理意见表', '${ly}/village/check-document?checkId=${check.id }&flag=${check.flag == 3 ? 8 : 1}')"--%>
                <%--class="btn btn-success radius" type="button" style="padding: 0 70px; margin-right: 20px">--%>
            <%--<i class="Hui-iconfont mr-10">&#xe652;</i>打印预览--%>
        <%--</button>--%>

    <button onClick="pr_()" class="btn btn-primary radius btn_hid" type="button">
    <i class="Hui-iconfont">&#xe652;</i>打印预览
    </button>
    <button id="back"   onclick="top.show_tab('检查文书', '/village/check-list?flag=2')" class="btn radius" type="button">
    <i class="Hui-iconfont mr-10"></i>返回列表
    </button>
<%--    <a class="btn btn-success radius r btn_hid" style="line-height: 1.6em; margin-top: 3px" href="${ly }/company/model-list-main?flag=1"--%>
<%--    title="返回">返回</a>--%>
        <%-- <button onClick="top.show_tab('排查治理记录', '${ly }/village/check-list?flag=${flag }')" class="btn btn-success radius" type="button" style="padding: 0 15px;margin-right: 20px">
            <i class="Hui-iconfont mr-10">&#xe652;</i>返回排查治理记录列表
        </button> --%>
<%--        <button onClick="location.href = '/company/check-detail?flag=${flag}&id=${check.id }&number=${number}'"--%>
<%--                class="btn btn-success radius" type="button" style="padding: 0 60px;margin-right: 20px">检查详情--%>
<%--        </button>--%>
        <!-- 判断是否有治理意见表 -->
<%--        <c:if test="${listM.c > 0 && listM.status == 2 }">--%>

<%--            <!-- 已经有复查 -->--%>
<%--            <c:if test="${listM.c3 > 0}">--%>
<%--                <button onClick="location.href = '/company/recheck-detail?checkId=${listM.id }&flag=${flag}&number=${number}'"--%>
<%--                        class="btn btn-success radius" type="button" style="padding: 0 70px;">复查详情--%>
<%--                </button>--%>
<%--            </c:if>--%>
<%--        </c:if>--%>
    </div>


    <c:if test="${empty rectification}">
        <h2 class="ss  text-c mb-10">隐患整改意见表</h2>
    </c:if>
    <c:if test="${!empty rectification}">
        <h2 class="ss  text-c mb-10">隐患整改意见书</h2>
    </c:if>

    <script>
    let searchURL = window.location.search;
    searchURL = searchURL.substring(1, searchURL.length);
    substring = "book";
    b= searchURL.includes(substring);   // true
    if(b){
    $(".ss").text("隐患整改意见书");
    }
    </script>

    <div class="div_pdetail">
        <font>受检${check.flag == 1 ? '部门' : '单位'}：${check.depart }</font>
        <label>检查日期：<fmt:formatDate value="${check.realTime }" pattern="yyyy年MM月dd日"/></label>
    </div>

    <div class="f-l mt-20 mb-20" style="width:100%">
        <table class="f-l table table-border table-bordered table-bg table-hover table-sort">
            <thead>
            <tr class="text-c">
                <th width="6%">序号</th>
                <th width="10%">检查部位</th>
                <th width="10%">检查类型</th>
                <th width="10%">检查方式</th>
                <th width="10%">检查项目</th>
                <th width="10%">检查结果</th>
                <th width="20%">隐患内容</th>
                <th width="15%">隐患图片</th>
                <!-- <th width="10%">备注</th> -->
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${itemL }" var="be" varStatus="index">
                <tr>

                 <%--1序号--%>
                <script>
                console.log('${index.index}');
                </script>
                    <td class="text-c">${index.index + 1}</td>
					<c:set var="item" value="${fn:split(be.levels,'/') }"/>


                <%--2检查部位--%>
                <td class=" center" >
                ${be.partName}
                </td>
                <%--3检查类型--%>
                <td class=" center">
                <c:if test="${check.industryType==1}">基础检查</c:if>
                <c:if test="${check.industryType==2}">现场检查</c:if>
                <c:if test="${check.industryType==3}">高危检查</c:if>
                </td>

                <%--4检查方式--%>
                <td class=" center">
                <c:if test="${check.type==1}">日常检查</c:if>
                <c:if test="${check.type==2}">定期检查</c:if>
                <c:if test="${check.type==3}">季节检查</c:if>
                <c:if test="${check.type==4}">其他检查</c:if>
                <c:if test="${check.type==5}">综合检查</c:if>
                </td>
                <%--&lt;%&ndash;系统&ndash;%&gt;--%>
                    <%--<td class="text-c">--%>
                        <%--<c:if test="${!empty be.partImg}">--%>
                            <%--<img src="${be.partImg }" class="mr-10" style="height:100px;"/>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${ not empty item[1]}">--%>
                            <%--${item[1] }--%>
                        <%--</c:if>--%>
                    <%--</td>--%>
                <%--&lt;%&ndash;检查内容&ndash;%&gt;--%>
                    <%--<c:if test="${!empty itemL[0].content}">--%>
                        <%--<td class="text-c">${be.content }</td>--%>
                    <%--</c:if>--%>

                <%--5检查项目--%>
                <td class="text-c"> ${item[2] }</td>

                <%--6-检查结果--%>
                    <c:if test="${ empty be.status}">
                        <td style="text-align: center;">没有数据</td>
                    </c:if>
                    <c:if test="${ not empty be.status}">
                        <td style="text-align: center;">
                            <c:choose>
                                <c:when test="${be.status == 1}">合格</c:when>
                                <c:when test="${be.status == 2}">不合格</c:when>
                                <c:when test="${be.status == 3}">合格</c:when>
                            </c:choose>
                        </td>
                    </c:if>


<%--                &lt;%&ndash;检查内容&ndash;%&gt;--%>
<%--                <c:if test="${!empty itemL[0].content}">--%>
<%--                <td class="text-c">${be.content }</td>--%>
<%--                </c:if>--%>
                <%--7隐患内容--%>
                <td class="text-l">${be.memo }</td>


                <%--8-隐患图片--%>
                    <td class="text-c">
                        <c:if test="${!empty be.files or !empty be.memo}">

                            <a href="javascript:void(0)" onclick="showpicture('${be.files}', this)">
                                <img src="${be.recheckFile}" align="隐患图片" width="100" height="100">
                            </a>
                            <%-- <img alt="" src="${be.files }" style="max-height:100px;cursor:pointer;" onClick="showpicture('${be.files}')"/> --%>
                        </c:if>
                        <div data-lid="${be.levelId }" data-name="item-memo" style="display: none;">${be.memo }</div>
                    </td>

                </tr>
            </c:forEach>

            <tr>
                <td colspan="8">
                    <div class="div_pri  mt-10">针对上述隐患，根据有关规定，现提出如下整改要求：</div>
                    <div class="div_pri  mt-10" style="display: none;">1、对下列隐患，进行立即整改
                        <div class="check-box">
                            <input type="checkbox" name="lj_qx"/>
                            <label>全选</label>
                        </div>
                    </div>
                    <div class="div_pri" style="display: none;">
                        <!-- 循环 -->
<%--                         isli--%>
                        <c:forEach items="${itemL }" var="be" varStatus="index">
                            <div class="check-box">
                                <input type="checkbox" name="isli" data-i="${be.id }" checked="checked"/>
                                <label>${index.index + 1}</label>
                            </div>
                        </c:forEach>
                        <!-- 循环结束 -->
                    </div>
                    <div class="div_pri  mt-10">1、对上述隐患进行限期整改
                        <!-- <div class="check-box">
                          <input type="checkbox" name="xq_qx" checked="checked"/>
                          <label>全选</label>
                        </div> -->
                    </div>
                    <div class="div_pri" style="display: none;">
                        <!-- 循环 -->
<%--    isxian--%>
                        <c:forEach items="${itemL }" var="be" varStatus="index">
                            <div class="check-box">
                                <input type="checkbox" name="isxian" data-i="${be.id }" checked="checked"/>
                                <label>${index.index + 1}</label>
                            </div>
                        </c:forEach>
                        <!-- 循环结束 -->
                    </div>
                    <div class="div_pri mt-10">
                        <font style="float:Left;margin-left:30px;">期限：</font>
                        <input type="text" id="deadline" autocomplete="off"
                               value="<fmt:formatDate value="${rectification.deadline }" pattern="yyyy-MM-dd"/>"
                               onfocus="WdatePicker({minDate:'<fmt:formatDate value="${check.realTime }"
                                                                              pattern="yyyy-MM-dd" />',dateFmt:'yyyy-MM-dd'})"
                               class="input-text Wdate" style="float:Left;width:250px;">
                    </div>
                    <div class="div_pri  mt-10">2、下列隐患属于重大隐患
                        <div class="check-box">
                            <input id="yx_qx" type="checkbox" name="yz_qx"/>
                            <label>全选</label>
                        </div>
                    </div>
                    <%--使用每一条数据危险等级自动绑定--%>
<%--                    wjj--%>
                    <div class="div_pri">
                        <!-- 循环 -->


<%--    isyan--%>
                        <c:forEach items="${itemL }" var="be" varStatus="index">
                            <div class="check-box">
                                <input type="checkbox" name="isyan" data-i="${be.id }" data-item="${be.dangerFlag}"  />
                                <label>${index.index + 1}</label>
                            </div>
                        </c:forEach>
                        <!-- 循环结束 -->
                    </div>

<%--script--%>



 <%--script--%>
                    <div class="div_pri  mb-10  mt-10">
                        <font style="float:Left;">3、整改复查时间：</font>
                        <input type="text" id="planTime"  autocomplete="off"
                               value="<fmt:formatDate value="${rectification.plan_time }" pattern="yyyy-MM-dd"/>"
                               onfocus="WdatePicker({minDate:'#F{$dp.$D(\'deadline\')||$dp.$DV(\'<fmt:formatDate
                                       value="${check.realTime }" pattern="yyyy-MM-dd"/>\');}',dateFmt:'yyyy-MM-dd'})"
                               class="input-text Wdate" style="float:left;width:250px;">
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <div style="float:left;width:100%;position: relative;min-height:150px;">
                        <c:if test="${check.flag == 3 && check.checkCompany eq '无锡市安泰安全技术服务有限公司'}">
                            <img alt="" src="${ly }/images/zhang.png"
                                 style="position: absolute;bottom:10px;right:10px;height:130px;"/>
                        </c:if>
                        <c:if test="${check.flag > 1}">
                            <div class="div_pleft  mt-10 mb-10">检查部门/单位：</div>
                            <div class="div_pright  mt-10 mb-10">
                                <input type="text" style="width:150px" id="checkCompany" value="${check.checkCompany }"
                                       class="input-text" maxlength="50" disabled="disabled"/>
                            </div>
                            <br/>
                        </c:if>
                        <div class="div_pleft  mt-10 mb-10">检查人员签字：</div>
                        <div class="div_pright  mt-10 mb-10">
                            <input type="text" style="width:150px" value="${check.cheker }" class="input-text"
                                   maxlength="50" disabled="disabled"/>
                        </div>
                        <div class="div_pri mt-10">
                            <font><fmt:formatDate value="${now }" pattern="yyyy年MM月dd日"/></font>
                        </div>
                    </div>
                </td>
                <td colspan="4">
                    <div style="float:left;width:100%;min-height:150px;">
                        <div class="div_pleft  mt-10 mb-10">
                            <c:if test="${flag == 1}">受检部门负责人签字：</c:if>
                            <c:if test="${flag != 1}">受检单位负责人签字：</c:if>
                        </div>
                        <div class="div_pright  mt-10 mb-10">
                            <input type="text" style="width:150px" value="${check.dapartContact }" class="input-text"
                                   maxlength="50" disabled="disabled"/>
                        </div>
                        <div class="div_pri mt-10">
                            <font><fmt:formatDate value="${now }" pattern="yyyy年MM月dd日"/></font>
                        </div>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>

        <c:if test="${empty rectification}">
            <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2  mt-20" style="text-align:center;margin-left:0">
                    <button onClick="save_()" class="btn btn-primary radius btn_hid" type="button" style="padding: 0 70px;">
                        <i class="Hui-iconfont">&#xe632;</i>保存
                    </button>
                </div>
            </div>
        </c:if>

    </div>
</div>
<!-- 弹窗图片 -->
<div id="modal-plan" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header">
                <h3 class="modal-title">隐患图片</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body" style="height:400px;overflow-y:auto">
                <!-- 循环图片 -->
                <div class="div_imgp">
                    <img src="${ly }/images/zwtp.jpg" class="img-responsive mt-20">
                </div>
                <!-- 循环结束 -->
            </div>
        </div>
    </div>
</div>
<div>

</div>
</body>
    <script src="/js/jquery.jqprint-0.3.js"></script>
<script type="text/javascript">
    var serious_i = ${serList};
    var checkId = ${check.id};
    var flag = ${check.flag};
    var item1 = '${rectification.item1}', item2 = '${rectification.item2}', item3 = '${rectification.item3}';

    $(function(){
        $("input[data-item='2']").attr('checked','true');

    });

    $(":checkbox").click(function () {
        // var name = $(this).attr("name");
        // if (name != 'isyan') {
        //     var i = $(this).attr("data-i");
        //     // if ($(this).is(":checked")) {//选中
        //     //     $(":checkbox[data-i='" + i + "'][name='" + (name == 'isxian' ? 'isli' : 'isxian') + "']").removeAttr("checked");
        //     // } else {
        //     //     $(":checkbox[data-i='" + i + "'][name='" + (name == 'isxian' ? 'isli' : 'isxian') + "']").prop("checked", true);
        //     // }
        // }
        init_all_box();
    })
    $.each(item2.split(","), function (i, item) {
        $(":checkbox[data-i='" + item + "'][name='isxian']").click();
    })
    $.each(item3.split(","), function (i, item) {
        $(":checkbox[data-i='" + item + "'][name='isyan']").prop("checked", true);
    })
    if ('' != item1 || '' != item2) {
        $("input").prop("disabled", true);
    } else {
        var ids = "";
        var cL = $(":checkbox[name='isyan']");
        $("div[data-name='item-memo").each(function (i, item) {
            var lid = $(this).attr("data-lid");
            var memo = $(this).text();
            if (lid != '') {
                $.each(serious_i, function (j, it) {
                    if (it.levelid == lid && memo.indexOf(it.keywords) > -1) {
                        cL.eq(i).click();
                        if (ids == '') {
                            ids = (i + 1);
                        } else {
                            ids = ids + "," + (i + 1);
                        }
                        return false;
                    }
                })
            }
        })

        if (ids != '') {
            layer.alert("系统检测到第" + ids + "项隐患为重大隐患");
        }
    }
    init_all_box();

    function save_() {
        //$("#").prop("checked",true);
        if ($(":checkbox[name='isxian']:checked").length > 0 && $("#deadline").val() == '') {
            layer.alert("请选择限期整改期限");
            return false;
        }
        if ($("#planTime").val() == '') {
            layer.alert("请选择整改复查时间");
            return false;
        }
        var item1 = '', item2 = '', item3 = '';
        $(":checkbox[name='isxian']:checked").each(function () {
            var it = $(this).attr("data-i");
            item2 = item2 == '' ? it : item2 + "," + it;
        });
        $(":checkbox[name='isli']:checked").each(function () {
            var it = $(this).attr("data-i");
            item1 = item1 == '' ? it : item1 + "," + it;
        });
        $(":checkbox[name='isyan']:checked").each(function () {
            var it = $(this).attr("data-i");
            item3 = item3 == '' ? it : item3 + "," + it;
        });

        //alert(checkId+",item1="+item1+",item2="+item2+",item3="+item3+","+$("#deadline").val()+","+$("#planTime").val());
        //return;

        var i = layer.load();
        $.post(getRootPath() + "/company/rectification-save", {
            //	$.post(getRootPath() + "/api/tongx/A003", {
            checkId: checkId,
            item1: item1,
            item2: item2,
            item3: item3,
            deadline: $("#deadline").val(),
            planTime: $("#planTime").val()
        }, function (result) {

            layer.close(i);
            if (result.status == '1') {
                layer.alert(result.map.message);
            } else {
                top.ref_ifm();
                var x = "企业自查";
                if (flag == 2) {
                    x = "行政检查";
                }
                if (flag == 3) {
                    x = "部门抽查";
                }
                if (flag != 1) {
                    // show_tab('现场检查记录_' + checkId, '/village/check-document?checkId=' + checkId + '&flag=8')
                    //parent.location.reload();
    show_tab('文书' + checkId, '/village/check-document?checkId=' + checkId + '&flag=1');

                console.log('1')
                } else {
                    if(sessionStorage.getItem('flag')){
                            console.log('2')
                            location.replace(location.href);
                        <%--var l_flag = parseInt(sessionStorage.getItem('flag'))  ;--%>
                        <%--var l_dmname =sessionStorage.getItem('dmname').replace(/\s*/g,"");--%>
                        <%--var l_industryType = parseInt(sessionStorage.getItem('industryType'));--%>
                        <%--var url = '${ly}/company/check-list-szss?dmName='+l_dmname+'&flag='+l_flag+'&industryType='+l_industryType;--%>
                        <%--// top.show_tab(x, url)--%>
                      <%--show_tab(x, url)--%>
                    }else{
                        close_dialog(function () {
    console.log('3')
                            parent.location.reload();
                        })
                    }

                    // location.reload();
                }

            }
        })
        //
        // var b=$("input[name='isyan']").is(':checked');
        // // if(b){
        // //     console.log('hi!')
        // //     $("input[name='isyan']").prop('data-item','2');
        // // }
        // if(!b){
        //     $("input[name='isyan']").prop('checked',false);
        //     console.log('hello!');
        // }
    }

</script>
<script>

    function pr_() {
    $('.btn_hid').css('display','none');
    $('#back').css('display','none');
    $(".page-container").jqprint();
    setTimeout(function () {
    $('.btn_hid').css('display','inline-block')
    },2000)
    }
    var a=$("input[data-item='2']").length;
    var b=$("input[name='isyan']").length;
    if (a===b){
        console.log("全选中");
        $("#yx_qx").prop('checked','true');
    }
</script>
</html>