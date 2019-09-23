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
    <link rel="stylesheet" type="text/css" href="${ly}/styles/print.css"/>
    <title>风险分级管控 隐患排查治理智能化平台</title>
    <meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
    <meta name="description" content="风险分级管控   隐患排查治理智能化平台">
    <style type="text/css">
    </style>
</head>
<body>
<div class="page-container" style="width:250mm;margin:auto;padding:10px 0 0 0;">
    <div class="row cl row_ac">
        <!-- <button onClick="addDan()" class="btn radius" type="button">
            <i class="Hui-iconfont mr-10">&#xe600;</i>添加意见
        </button> -->
        <script type="text/javascript">
            function llo(src) {
                $("#zhang_img").html('<img src="' + src + '" style="height:180px;"/>')
            }
        </script>
        <button onClick="img_upload(null, null, llo)" class="btn radius" type="button">
            <i class="Hui-iconfont mr-10">&#xe600;</i>上传检查单位盖章
        </button>
        <button onClick="save1_()" class="btn radius" type="button">
            <i class="Hui-iconfont mr-10">&#xe632;</i>保存
        </button>
        <button onClick="pr_()" class="btn radius" type="button">
            <i class="Hui-iconfont mr-10">&#xe652;</i>打印
        </button>
        <button onClick="top.show_tab('检查文书', '${ly }/village/check-list?flag=3')" class="btn radius" type="button">
            <i class="Hui-iconfont mr-10">&#xe66b;</i>返回列表
        </button>
        <%-- <button onClick="top.show_tab('检查文书', '${ly }/village/danger/opinion-list?type=1&flag2=4')" class="btn radius" type="button" >
            <i class="Hui-iconfont mr-10">&#xe66b;</i>返回文书
        </button> --%>
    </div>
    <div class="row cl mb-20">
        <div class="tabBar clearfix">
            <c:if test="${flag2 != 3}">
                <a href="/village/check-document?checkId=${check.id}&flag=8">现场检查记录</a>
            </c:if>
            <a href="/village/check-document?checkId=${check.id}&flag=1">隐患整改意见书</a>
            <a class="current" href="JavaScript:void(0)">隐患整改复查意见书</a>
        </div>
    </div>
    <div id="container" style="width:150mm;margin:auto;padding:50px 0 0px 0;">
        <p class="dant1  mt-20" style="text-align:center;border-bottom:1px solid #000;margin-bottom:3px;"
           align="center">
            <b><span style="font-family:华文中宋;font-weight:bold;font-size:18pt;"><span>隐 患 整 改 复 查 意 见 书</span></span></b>
        </p>
        <p class="MsoNormal" style="text-align:right;margin-bottom:15px;border-top:1px solid #000;padding-top:10px;"
           align="right">
            <span style="font-size:12pt;"><span>检查日期</span><span contenteditable="true"
                                                                 style="text-decoration:underline;">&nbsp;<fmt:formatDate
                    value="${timenow }" pattern="yyyy"/>&nbsp;</span><span>年</span><span contenteditable="true"
                                                                                         style="text-decoration:underline;">&nbsp;<fmt:formatDate
                    value="${timenow }" pattern="MM"/>&nbsp;</span><span>月</span><span contenteditable="true"
                                                                                       style="text-decoration:underline;">&nbsp;<fmt:formatDate
                    value="${timenow }" pattern="dd"/>&nbsp;</span><span>&nbsp;日&nbsp;</span></span>
        </p>
        <p class="MsoNormal">
            <u><span style="float:left;">受检单位</span><span style="float:left;text-decoration:underline;"
                                                          contenteditable="true">&nbsp;&nbsp;&nbsp;${company.name }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u><span
                style=""><span>：</span></span><span style=""></span>
        </p>
        <p class="MsoNormal" style="text-indent:27.5pt;text-align:left;line-height:30px;">
            <span style=""><span>我们受</span></span></span>
            <u><span style="text-decoration:underline;text-indent: 0;" contenteditable="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u>
            <span style=""><span>委托</span></span>
            <span style=""><span>于</span></span>
            <c:if test="${empty planTime}">

                <u><span style="text-decoration:underline;text-indent: 0;"
                         contenteditable="true">&nbsp;&nbsp;&nbsp;</span></u>
                <span style=""><span>年</span></span>
                <u><span style="text-decoration:underline;text-indent: 0;"
                         contenteditable="true">&nbsp;&nbsp;</span></u>
                <span style=""><span>月</span></span>
                <u><span style="text-decoration:underline;text-indent: 0;"
                         contenteditable="true">&nbsp;&nbsp;</span></u>

            </c:if>
            <c:if test="${not empty  planTime}">
                <c:set var="date1" value="${fn:split(planTime,'-') }"/>
                <u><span style="text-decoration:underline;text-indent: 0;"
                         contenteditable="true">&nbsp;&nbsp;&nbsp;${date1[0]}</span></u>
                <span style=""><span>年</span></span>
                <u><span style="text-decoration:underline;text-indent: 0;"
                         contenteditable="true">&nbsp;&nbsp;&nbsp;${date1[1]}</span></u>
                <span style=""><span>月</span></span>
                <u><span style="text-decoration:underline;text-indent: 0;"
                         contenteditable="true">&nbsp;&nbsp;&nbsp;${date1[2]}</span></u>

            </c:if>
            <span style=""><span>对你单位进行了安全检查并提出了整改意见。现对你单位的整改情况进行了复查,根据检查情况，提出如下意见：</span></span>
            <span style=""></span>
        </p>
        <%-- <div id="div_dangerm">
          <c:forEach items="${itemL }" var="be" varStatus="index">
                <p class="MsoNormal" style="text-indent:21.0000pt;">
                    <span style="font-family:'\4EFF\5B8B_GB2312';">${index.index + 1}．</span><u><span style="font-family:'\4EFF\5B8B_GB2312';text-decoration:underline;text-indent: 0;" contenteditable="true">&nbsp;${be.memo }</span></u><u><span style="font-family:'\4EFF\5B8B_GB2312';text-decoration:underline;text-indent: 0;"></span></u> 
                </p>
            </c:forEach>
        </div> --%>
        <div id="div_dangerm">
            <c:set var="x" value="0"/>
            <c:set var="y" value="0"/>
            <c:forEach items="${itemL }" var="be" varStatus="index">
                <c:if test="${be.status == 3 }"><c:set var="x" value="1"/></c:if>
                <c:if test="${be.status == 2 }"><c:set var="y" value="1"/></c:if>
            </c:forEach>
            <c:if test="${x > 0 }"><p class="MsoNormal"><span>以下隐患已整改合格,符合安全要求：</span></p>
                <c:forEach items="${itemL }" var="be" varStatus="index">
                    <p class="MsoNormal" style="text-indent:21.0000pt;">
                        <c:if test="${be.status == 3 }">
                            <span style="">${index.index + 1}．</span><u><span
                                style="font-family:'\4EFF\5B8B_GB2312';text-decoration:underline;text-indent: 0;"
                                contenteditable="true">&nbsp;
                    ${be.memo }
                    </span></u><u><span
                                style="font-family:'\4EFF\5B8B_GB2312';text-decoration:underline;text-indent: 0;"></span></u>
                            <!-- <i class="Hui-iconfont mr-10 dustbin" style="cursor:pointer;font-size:20px" onclick="removeDan(this)">&#xe609;</i> -->
                        </c:if>
                    </p>
                </c:forEach>
            </c:if>
            <c:if test="${y > 0 }"><p class="MsoNormal"><span>以下隐患未整改合格：</span></p>
                <c:forEach items="${itemL }" var="be" varStatus="index">
                    <p class="MsoNormal" style="text-indent:21.0000pt;">
                        <c:if test="${be.status == 2 }">
                            <span style="">${index.index + 1}．</span><u><span
                                style="font-family:'\4EFF\5B8B_GB2312';text-decoration:underline;text-indent: 0;"
                                contenteditable="true">&nbsp;
                     ${be.memo }
                     </span></u><u><span
                                style="font-family:'\4EFF\5B8B_GB2312';text-decoration:underline;text-indent: 0;"></span></u>
                            <!-- <i class="Hui-iconfont mr-10 dustbin" style="cursor:pointer;font-size:20px" onclick="removeDan(this)">&#xe609;</i> -->
                        </c:if>
                    </p>
                </c:forEach>
            </c:if>
        </div>
        <p class="MsoNormal" style="margin-top:15px;">
            <span style=""><span>受检单位负责人（签名）：</span></span>
            <u><span style="text-decoration:underline;" contenteditable="true">&nbsp;&nbsp;&nbsp;${company.charge}&nbsp;&nbsp;</span></u>
        </p>
        <p class="MsoNormal" style="margin-top:15px;">
            <span style=""><span>检查人员（签名）：</span></span>
            <u><span style="text-decoration:underline;" contenteditable="true" id="id1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u>
            <u><span style="text-decoration:underline;" contenteditable="true" id="id2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u>
        </p>
        <p class="MsoNormal" style="text-align:left;margin-top:10px;height:180px;">
            <span style="float:left;margin: 0 8px 0 0;">检查单位（盖章）:</span>

            <span style="float:left;">
            	<span contenteditable="true"
                      style="border-bottom:1px solid #333;float:left;padding: 0 20px;line-height: 22px;height: 22px;">${check.checkCompany}</span>
            </span>

            <span style="" id="zhang_img">
                <%-- <c:if test="${check.checkCompany eq '无锡市安泰安全技术服务有限公司' }">
                 <img src="${ly }/images/zhang.png" style="height:180px;"/>
                 </c:if>--%>
            </span>
        </p>
        <p class="MsoNormal" style="text-align:right;margin-top:15px;">
            <span style=""><span contenteditable="true">&nbsp;<fmt:formatDate value="${timenow }"
                                                                              pattern="yyyy"/>&nbsp;</span>年<span
                    contenteditable="true">&nbsp;&nbsp;<fmt:formatDate value="${timenow }"
                                                                       pattern="MM"/>&nbsp;</span>月<span
                    contenteditable="true">&nbsp;&nbsp;<fmt:formatDate value="${timenow }" pattern="dd"/>&nbsp;</span>日</span><span
                style=""></span>
        </p>
        <p class="MsoNormal" style="border-top:1px solid #000;">
            <span style=""><span>本文书一式三份：一份交受委托单位，一份交受检单位，一份由检查单位备查。</span> </span><span style=""></span>
        </p>
    </div>
</div>
<!-- 添加-->
<div id="div_model">
    <p class="MsoNormal" style="text-indent:21.0000pt;">
        <span class="num_dan" style="">1</span><span style="">．</span><u><span
            style="text-decoration:underline;text-indent: 0;" contenteditable="true" class="yh_span">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u><u><span
            style="text-decoration:underline;text-indent: 0;"></span></u>
        <i class="Hui-iconfont mr-10 dustbin" style="cursor:pointer;font-size:20px"
           onclick="removeDan(this)">&#xe609;</i>
    </p>
</div>
<!-- 弹窗隐患 -->
<div id="modal-demo" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     data-keyboard=true>
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header">
                <h3 class="modal-title">设置限期复查意见</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body">
                <div class="row cl">

                    <div class="form-label col-sm-2" style="width: 80%">经检查该公司有下列隐患</div>
                </div>
                <div class="row cl">
                    <div class="formControls col-sm-2" style="width: 80%">
                        <!--  循环 -->
                        <c:forEach items="${itemL }" var="be" varStatus="index">
                            <p class="oneyhms">${index.index + 1}.<span data-name="item-memo">${be.memo }</span>
                            </p>
                        </c:forEach>
                        <div class="div_pri  mt-10">针对上次隐患整改要求，经复查，提出如下复查意见：</div>
                        <div class="div_pri  mt-10">1、请选择未整改项：</div>
                        <div class="div_pri">
                            <!-- 循环 -->
                            <c:forEach items="${itemL }" var="be" varStatus="index">
                                <div class="check-box">
                                    <input type="checkbox" name="isxian" data-i="${be.id }" data-file="" data-status="2"
                                           data-dead="<fmt:formatDate value="${be.deadline }" pattern="yyyy-MM-dd"/>"/>
                                    <label>${index.index + 1}</label>
                                </div>
                            </c:forEach>
                            <!-- 循环结束 -->
                        </div>
                        <div class="div_pri  mb-10  mt-10">
                            <font style="float: Left;">2、对未整改项要求于</font> <input type="text" id="nextTime" value=""
                                                                                onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"
                                                                                class="input-text Wdate"
                                                                                style="float: left; width: 186px;">
                            <font style="float: Left;">整改完毕</font>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" onclick="article_save_submit()" type="button">确定</button>
                <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>


</body>
<script type="text/javascript">
    var checkId = ${checkId};
    var userId = ${company.userId };
    var rectificationId = '${rectification.id}';
    var count = ${count };
    var flag = 2, flag2 = 3;

    $(function () {
            var str = '${check.cheker }';
            // var x = str.replace(/、/g, ',').split(",");
            var x = str.replace(/，/ig,',').split(",");
            $("#id1").html(x[0]);
            $("#id2").html(x[1]);

        }
    )




    function save1_() {
        if (count == 0) {
            $("#modal-demo").modal("show");
            return;
        }
        $(".dustbin").hide();
        $.post(getRootPath() + "/village/danger/opinion-save", {
            userId: userId,
            checkId: checkId,
            copy: $("#container").html(),
            flag: flag,
            flag2: flag2
        }, function (reuslt) {
            location.reload();
        })
    }
</script>
<script type="text/javascript" src="${ly }/js/check/opinion2.js"></script>
</html>