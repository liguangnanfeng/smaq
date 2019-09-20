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

        console.log('img0-8');
        console.log('${img0},${img1},${img2},${img3},${img4},${img5},${img6},${img7},${img8}');
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
        let img="";
        let a=null;
        for(let i=1;i<=9;i++){
            let x=$('#myCheck'+i).prop('checked');
            if(x){
                     a=$("#pic"+i).attr("src");
                    if(i===1){
                    img=img+a;
                    }else{
                    img=img+'&'+a;
                    }
            }else{
                     a='null';
                    if(i===1){
                    img=img+a;
                    }else{
                    img=img+'&'+a;
                    }
            }
        }
            console.log('img---------------');
            console.log(img);
        obj.imgUrl=img;
        $.post("/company/safety-system/risk-information-save", obj, function(result) {
        layer.close(i);
        parent.location.reload();
        console.log(result)
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
        #pic3,#pic4,#pic5,#pic6,#pic7,#pic8,#pic9,#pic10,#pic11{
        cursor:pointer;
        margin-top:17px;
        }
        .a{
        width:550px;
        }
        .c{
        position:relative;
        top:-47px;
        left:-20px;
        }
        </style>
        <script>
        console.log('img0:',${img0});
        </script>
        <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">图例编辑：</label>
        <div class="formControls col-xs-8 col-sm-9 a">
        <%--                onclick="img_upload('pic3', null)"--%>
        <img id="pic1" class="pic" src="${ly }/images/gao/aqgz1.jpg"
        style="width:100px!important;min-height:100px!important;" url=""/>
        <input class="c" type="checkbox" id="myCheck1"  />
        <img id="pic2" class="pic" src="${ly }/images/gao/aqgz2.jpg"
        style="width:100px!important;min-height:100px!important;" url=""/>
        <input class="c" type="checkbox" id="myCheck2" />
        <img id="pic3" class="pic" src="${ly }/images/gao/aqgz3.jpg"
        style="width:100px!important;min-height:100px!important;" url=""/>
        <input class="c" type="checkbox" id="myCheck3" />
        <img id="pic4" class="pic" src="${ly }/images/gao/aqgz4.jpg"
        style="width:100px!important;min-height:100px!important;" url=""/>
        <input class="c" type="checkbox" id="myCheck4" />
        <img id="pic5" class="pic" src="${ly }/images/gao/aqgz6.jpg"
        style="width:100px!important;min-height:100px!important;" url=""/>
        <input class="c" type="checkbox" id="myCheck5"/>
        <img id="pic6" class="pic" src="${ly }/images/gao/l1.jpg"
        style="width:100px!important;min-height:100px!important;" url=""/>
        <input class="c" type="checkbox" id="myCheck6"/>
        <img id="pic7" class="pic" src="${ly }/images/gao/l2.jpg"
        style="width:100px!important;min-height:100px!important;" url=""/>
        <input class="c" type="checkbox" id="myCheck7"/>
        <img id="pic8" class="pic" src="${ly }/images/gao/l3.jpg"
        style="width:100px!important;min-height:100px!important;" url=""/>
        <input class="c" type="checkbox" id="myCheck8"/>
        <img id="pic9" class="pic" src="${ly }/images/gao/l4.jpg"
        style="width:100px!important;min-height:100px!important;" url=""/>
        <input class="c" type="checkbox" id="myCheck9"/>
        </div>

        </div>
        <script>
            console.log(${img6==null||img6=='null'});
            if(${img0==null||img0=='null'}){
            $('#myCheck1').prop("checked", false);

            }else{
            $('#myCheck1').prop("checked", true);
            }

            if(${img1==null||img1=='null'}){
            $('#myCheck2').prop("checked", false);
            }else{
            $('#myCheck2').prop("checked", true);
            }

            if(${img2==null||img2=='null'}){
            $('#myCheck3').prop("checked", false);
            }else{
            $('#myCheck3').prop("checked", true);
            }

            if(${img3==null||img3=='null'}){
            $('#myCheck4').prop("checked", false);
            }else{
            $('#myCheck4').prop("checked", true);
            }

            if(${img4==null||img4=='null'}){
            $('#myCheck5').prop("checked", false);
            }else{
            $('#myCheck5').prop("checked", true);
            }

            if(${img5==null||img5=='null'}){
            $('#myCheck6').prop("checked", false);
            }else{
            $('#myCheck6').prop("checked", true);
            }

            if(${img6==null||img6=='null'}){
            $('#myCheck7').prop("checked", false);
            }else{
            $('#myCheck7').prop("checked", true);
            }

            if(${img7==null||img7=='null'}){
            $('#myCheck8').prop("checked", false);
            }else{
            $('#myCheck8').prop("checked", true);
            }

            if(${img8==null||img8=='null'}){
            $('#myCheck9').prop("checked", false);
            }else{
            $('#myCheck9').prop("checked", true);
            }
        let img=null;


        </script>
        <style>
        .aa{
        margin-bottom: 50px;
        margin-top: 50px!important;
        }
        </style>
        <div class="row cl aa">
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