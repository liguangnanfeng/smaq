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

    <%@ include file="/WEB-INF/inc/kindeditor.inc" %>
    <%@ include file="/WEB-INF/inc/back-header.inc" %>
    <title>风险分级管控 隐患排查治理智能化平台</title>
    <meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
    <meta name="description" content="风险分级管控   隐患排查治理智能化平台">
</head>

<body>

<form class="form form-horizontal" id="my_form" method="post">
    <div class="page-container">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span
                    class="c-red">*</span>检查方式：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <c:choose>
                    <c:when test="${checkType == 1}"> <input class="input-text" style="width: 300px" value="日常检查"
                                                             disabled/></c:when>
                    <c:when test="${checkType == 2}"> <input class="input-text" style="width: 300px" value="定期检查"
                                                             disabled/></c:when>
                    <c:when test="${checkType == 3}"> <input class="input-text" style="width: 300px" value="季节检查"
                                                             disabled/></c:when>
                    <c:when test="${checkType == 4}"> <input class="input-text" style="width: 300px" value="其他检查"
                                                             disabled/></c:when>
                    <c:when test="${checkType == 5}"> <input class="input-text" style="width: 300px" value="综合检查"
                                                             disabled/></c:when>
                </c:choose>
                <input type="hidden" class="input-text" style="width: 300px" id="checkType" value="${checkType}"/>
            </div>
        </div>
        <%--如果为定期检查显示一个选择天数的框--%>
        <c:if test="${checkType==2}">
        <div class="row cl dq" id="days">
            <label class="form-label col-xs-4 col-sm-2"><span
                    class="c-red">*</span>请填写定期时间 :
            </label>
            <div class="formControls col-xs-8 col-sm-9">
                <input class="input-text" autofocus type="number" min="0" max="7" style="width: 300px" id="my_days"/>
            </div>
        </div>
        </c:if>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span
                    class="c-red">*</span>检查类型：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <c:choose>
                    <c:when test="${industryType == -1}"> <input class="input-text" style="width: 300px" value="基础检查"
                                                                 disabled/></c:when>
                    <c:when test="${industryType == -2}"> <input class="input-text" style="width: 300px" value="现场检查"
                                                                 disabled/></c:when>
                </c:choose>
                <input type="hidden" class="input-text" style="width: 300px" id="industryType" value="${industryType}"/>
            </div>
        </div>


        <div class="row cl" id="bm">
            <label class="form-label col-xs-4 col-sm-2"><span
                    class="c-red">*</span>检查部门 :</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input class="input-text" style="width: 300px" id="dmid" value="${dmname}" disabled/>
                <input type="hidden" class="input-text" style="width: 300px"  value="${dmid}"/>
            </div>
        </div>

        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <button onClick="save()" class="btn btn-primary radius" type="button" style="padding: 0 70px;"><i
                        class="Hui-iconfont">&#xe632;</i> 生成模版
                </button>
            </div>
        </div>


</form>
</div>
</form>
</body>

<script>
    // 保存数据到数据库
    function save() {
        var dmid = $('#dmid').val();
        var days = $('#my_days').val();
        var checkType = $('#checkType').val();
        var industryType = $('#industryType').val();
        if(checkType==2&&!days){
            alert('请填写定期时间')
            return
        }
        var arr=[2,3,4,5,6];
        var tem = arr[checkType-1];
        $.ajax({
            type: "POST",
            url: getRootPath() + '/village/saveCheckMenu3',
            data: {dmid: dmid,checkType:industryType,dptitle:checkType,times:days},
            success: function (result) {
                if (result.status == 0) {
                    alert('保存成功');
                    window.parent.location.href = '${ly }/company/model-list-main';
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);

                } else {
                    alert('保存失败');
                }
            },
        })

    }
</script>

</html>