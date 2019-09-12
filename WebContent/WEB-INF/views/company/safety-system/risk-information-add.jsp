    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <%@ include file="/WEB-INF/views/taglibs.jsp" %>
        <!DOCTYPE HTML>
        <html>
        <head>
        <meta charset="utf-8">
        <meta name="renderer" content="webkit|ie-comp|ie-stand">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport"
        content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
        <meta http-equiv="Cache-Control" content="no-siteapp" />
        <%@ include file="/WEB-INF/inc/back-header.inc" %>
        <title>风险分级管控 隐患排查治理智能化平台</title>
        <meta name="keywords" content="风险分级管控 隐患排查治理智能化平台">
        <meta name="description" content="风险分级管控 隐患排查治理智能化平台">
        <style type="text/css">
        .input-text{position:relative}
        .txtarea_sq{height:150px;width:550px;}
        </style>
        <script type="text/javascript">
        var id_ = '${be.id}', flag = '${be.flag}';
        function show(){
        $("#win-show").modal("show");
        }

        function save() {
        var obj = $("#form").serializeObject();
        if(obj.name == '') {
        layer.alert("请输入风险名称");
        return false;
        }
        if(obj.fxdj == '') {
        layer.alert("请选择风险等级");
        return false;
        }
        if(obj.glzrr == '') {
        layer.alert("请输入管理负责人");
        return false;
        }
        if(obj.sglx == '') {
        layer.alert("请输入事故类型");
        return false;
        }
        if(obj.fxys == '') {
        layer.alert("请输入风险因素");
        return false;
        }
        if(obj.yjczcs == '') {
        layer.alert("请输入应急防范措施");
        return false;
        }

        if (obj.fxdj.length < 1 || obj.fxdj.length == 1 ){
        obj.fxdj = obj.fxdj;
        } else {
        obj.fxdj = obj.fxdj.join(",");
        }

        var i = layer.load();
        obj.id = id_;
        obj.flag = flag;
        obj.isedit = 1;
        $.post("/company/safety-system/risk-information-save", obj, function(result) {
        layer.close(i);
        parent.location.reload();
        /*if(flag == 1) {
        parent.location.reload();
        } else {
        parent.load_();
        }*/
        })
        }
        </script>
        </head>
        <body>
        <div class="page-container">
        <form class="form form-horizontal" id="form">
        <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">风险名称：</label>
        <div class="formControls col-xs-8 col-sm-9">
        <c:choose>
            <c:when test="${be.flag == 1}">
                <input type="text" name="name" disabled="disabled" readonly="readonly"
                value="${empty be ? session_user.userName : be.name }" style="width:550px" class="input-text"
                placeholder="如：XXXX公司"/>
            </c:when>
            <c:when test="${be.flag == 2}">
                <input type="text" name="name" disabled readonly value="${be.name }" style="width:550px"
                class="input-text" placeholder="如：XXXX车间"/>
            </c:when>
            <c:otherwise>
                <input type="text" name="name" disabled readonly value="${be.name }" style="width:550px"
                class="input-text" placeholder="如：XXXX班组/岗位"/>
            </c:otherwise>
        </c:choose>

        </div>
        </div>
        <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">风险等级：</label>
        <div class="formControls col-xs-8 col-sm-9" style="line-height:30px;">
        <div class="check-box" style="padding-left:0;">
        <input type="checkbox" name="fxdj" value="红">
        <label>红</label>
        </div>
        <div class="check-box">
        <input type="checkbox" name="fxdj" value="橙">
        <label>橙</label>
        </div>
        <div class="check-box">
        <input type="checkbox" name="fxdj" value="黄">
        <label>黄</label>
        </div>
        <div class="check-box">
        <input type="checkbox" name="fxdj" value="蓝">
        <label>蓝</label>
        </div>
        </div>
        </div>

        <c:set value="0" var="fxdj1"/>
        <c:set value="0" var="fxdj2"/>
        <c:set value="0" var="fxdj3"/>
        <c:set value="0" var="fxdj4"/>
        <c:forEach items="${acs }" var="be">
            <c:if test="${be.level == '红色'}">
                <c:set value="红" var="fxdj1"/>
            </c:if>
            <c:if test="${be.level == '橙色'}">
                <c:set value="橙" var="fxdj2"/>
            </c:if>
            <c:if test="${be.level == '黄色'}">
                <c:set value="黄" var="fxdj3"/>
            </c:if>
            <c:if test="${be.level == '蓝色'}">
                <c:set value="蓝" var="fxdj4"/>
            </c:if>
        </c:forEach>
        <input type="hidden" id="xxx" value="${be.fxdj }">
        <script type="text/javascript">
        var xxx = $("#xxx").val();
        $.each(xxx.split(","), function(i, item) {
        $(":checkbox[value='"+item+"']").prop("checked", true);
        })
        </script>

        <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">管理责任人：</label>
        <div class="formControls col-xs-8 col-sm-9">
        <input type="text" name="glzrr" value="${be.glzrr }" style="width:550px" class="input-text"
        placeholder="请输入管理责任人"/>
        </div>
        </div>
        <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">事故类型：</label>
        <div class="formControls col-xs-8 col-sm-9">
        <textarea name="sglx" class="textarea txtarea_sq">${be.sglx }</textarea>
        </div>
        </div>
        <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">风险因素：</label>
        <div class="formControls col-xs-8 col-sm-9">
        <textarea name="fxys" class="textarea txtarea_sq">${be.fxys }</textarea>
        </div>
        </div>
        <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">应急防范措施：</label>
        <div class="formControls col-xs-8 col-sm-9">
        <textarea name="yjczcs" class="textarea txtarea_sq">${be.yjczcs }</textarea>
        </div>
        </div>
        <style>
        #pic3{
        cursor:pointer
        }
        .a{
        width:550px;
        }
        </style>
        <script>
        var a="https://sec.dicarl.com/upload/20190911104219_590.jpg"
        </script>
        <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">图例编辑( 点击图片编辑)：</label>
        <div class="formControls col-xs-8 col-sm-9 a">
        <img id="pic3" onclick="img_upload('pic3', null)" src="${ly }/images/zwtp.jpg"
        style="width:100px!important;min-height:100px!important;" url=""/>
        <img id="pic3" onclick="img_upload('pic4', null)" src="${ly }/images/zwtp.jpg"
        style="width:100px!important;min-height:100px!important;" url=""/>
        <img id="pic3" onclick="img_upload('pic5', null)" src="${ly }/images/zwtp.jpg"
        style="width:100px!important;min-height:100px!important;" url=""/>
        <img id="pic3" onclick="img_upload('pic6', null)" src="${ly }/images/zwtp.jpg"
        style="width:100px!important;min-height:100px!important;" url=""/>
        <img id="pic3" onclick="img_upload('pic3', null)" src="${ly }/images/zwtp.jpg"
        style="width:100px!important;min-height:100px!important;" url=""/>
        <img id="pic3" onclick="img_upload('pic3', null)" src="${ly }/images/zwtp.jpg"
        style="width:100px!important;min-height:100px!important;" url=""/>
        <img id="pic3" onclick="img_upload('pic3', null)" src="${ly }/images/zwtp.jpg"
        style="width:100px!important;min-height:100px!important;" url=""/>
        <img id="pic3" onclick="img_upload('pic3', null)" src="${ly }/images/zwtp.jpg"
        style="width:100px!important;min-height:100px!important;" url=""/>
        <img id="pic3" onclick="img_upload('pic3', null)" src="${ly }/images/zwtp.jpg"
        style="width:100px!important;min-height:100px!important;" url=""/>

        </div>

        </div>


        <div class="row cl">
        <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
        <button onClick="save()" class="btn btn-primary radius" type="button" style="padding: 0 70px;">
        <i class="Hui-iconfont">&#xe632;</i>生成公告牌
        </button>
        </div>
        </div>
        </form>
        </div>
        </body>
        </html>