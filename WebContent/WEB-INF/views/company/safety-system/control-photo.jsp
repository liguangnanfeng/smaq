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
        <meta name="keywords" content="风险分级管控 隐患排查治理智能化平台">
        <meta name="description" content="风险分级管控 隐患排查治理智能化平台">
        <link rel="stylesheet" href="${ly}/js/jquery.imageLabel.min.css">
        <link rel="stylesheet" href="${ly}/js/edit.css">
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

        .photo {
        text-align: center;
        width:97%;
        padding: 10px;
        height: 245px;
        box-shadow: 0 0 5px #f1f1f1;
        margin-top: 20px;
        }
        .photo img {
        max-width: 100%;
        max-height: 85%;
        }

        .photo span {
        font-size: 24px;
        color: red;
        font-weight: 600;
        cursor: pointer;
        float: right;
        }

        .detailBtn {
        color: #fff;
        font-size: 15px;
        margin-right: 15px;
        display: inline-block;
        background: #5A98DE;
        border: none;
        margin-top: 8px;
        padding: 5px 20px;
        border-radius: 3px;
        }

        <%--标注--%>

        .top-tab {
        background-color: #333;
        padding: 10px;
        margin-bottom: 50px
        }

        .top-tab a {
        display: flex;
        align-items: center;
        color: #fff
        }

        #imgform {
        margin: 0 auto;
        width: 500px
        }
        .contarName{
        text-align: center;
        font-size: 20px;
        margin-top: 10px;
        }
        </style>
        </head>
        <body style="padding:0 15px;">
        <div>
        <div class="row cl mt-15">
        <label class="form-label col-xs-4 col-sm-2" style="width:20%;text-align:right;">资料上传：</label>
        <div class="formControls col-xs-8 col-sm-9" style="width:300px;">
        <span class="btn-upload form-group">
        <input class="input-text upload-url" type="text" name="uploadfile-1" id="uploadfile-1" readonly><a
        href="javascript:void();" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe642;</i> 浏览文件</a>
        <input type="file" multiple name="file" id="file" class="input-file">
        </span>
        </div>
        <button class="btn btn-primary" onclick="upload()">确定上传</button>
            <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:history.back(-1)" title="返回">返回</a>
        </div>
        </div>
        <p class="contarName">${compangName}风险分布图</p>
        <c:forEach items="${list }" var="be">
            <form action="#" id="imgform${be.id}" class="photo">
            <input name="src" value="${be.url1}" style="display:none;">
            <div style="height:100%;position: relative;">
            <a href="${be.url}" target="_blank"><img src="${be.url}"></a>
            <%--<span onclick="del(${be.id})">X</span>--%>
            <p style="position: absolute;bottom: 0;left: 45.5%;margin-bottom: 0;">
            <button class="detailBtn" onclick="amend('${be.id}','${be.coordinate}')">编辑</button>
            <button class="detailBtn" onclick="del(${be.id})">删除</button>
            </p>
            </div>
            </form>
        </c:forEach>

        <script type="text/javascript" src="${ly}/js/jquery.imageLabel.min.js"></script>
        <script type="text/javascript" src="${ly}/js/html2canvas.min.js"></script>
        <script type="text/javascript">
        $(function () {
        <%--$('.contarName').text(getCookie('companyName')+'公司风险分布图')--%>
        $('.table-sort').dataTable({
        "aaSorting": [[0, "asc"]],//默认第几个排序
        "bStateSave": false,//状态保存
        searching: false,
        ordering: false,
        "aoColumnDefs": []
        });
        });

        /*删除*/
        function del(id) {
        $.post(getRootPath() + "/company/safety-system/delete-photo", {
        id: id
        }, function (result) {
        window.location.reload()
        })
        }

        /*上传*/
        function upload() {
        $.ajaxFileUpload({
        url: getRootPath() + '/company/safety-system/save-photo', //用于文件上传的服务器端请求地址
        secureuri: false, //一般设置为false
        fileElementId: 'file', //文件上传空间的id属性 <input type="file" id="file" name="file" />
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

        function amend(v, s) {
        function shuju(arr) {
        if (arr && arr.length > 0) {
        var narr = []
        for (var i = 0; i < arr.length; i++) {
        var newArr = []
        newArr.push(arr[i].split(','))
        var obj = {
        'ex':newArr[0][0].split(':')[1],
        'ey':newArr[0][1].split(':')[1],
        'name':newArr[0][2].split(':')[1],
        'x':newArr[0][3].split(':')[1],
        'y':newArr[0][4].split(':')[1]
        }
        narr.push(obj)
        }
        }
        return narr
        }
        var q = []
        if(!s){
        q = []
        }else{
        q = shuju(s.split('/'))
        }
        // 图片
        $('#imgform' + v).click(function (t) {
        t.preventDefault();
        window.c = imageLabel({
        img: $("#imgform" + v + " [name=src]").val(),
        data: q,
        editPop: !0,
        close: function (t) {
        return t.length, !0
        },
        clickArea: function() {},
        edit: function(t) {},
        startArea: function() {},
        confirm: function (t) {
        <%-- 提交 --%>

        function dataArr(arr) {
        if (arr && arr.length > 0) {
        var newA = []
        for (var i = 0; i < arr.length; i++) {
        var str = ''
        str = "'ex':" + arr[i].ex + ",'ey':" + arr[i].ey + ",'name':" + arr[i].name + ",'x':" + arr[i].x + ",'y':" +
        arr[i].y
        newA.push(str)
        }
        }
        return newA
        }

        <%-- 截图 --%>
        layer.msg('标注比较耗时,请耐心等待 ! 不要做其他操作!', {
        time: 1000, //1s后自动关闭
        });
        setTimeout(function () {
        html2canvas(document.querySelector(".imageLabel-jisuan"), {
        useCORS: true, allowTaint: false, foreignObjectRendering: true, taintTest: true, scale: 1
        }).then(function (canvas) {
        var image = canvas.toDataURL("image/png", 0.1);
        $.ajax({
        type: "POST",
        url: getRootPath() + '/company/safety-system/control-addCoordinate',
        contentType: ' application/x-www-form-urlencoded',
        data: {
        id: v,
        images: image,
        coordinate: JSON.stringify(dataArr(t))
        },
        success: function (result) { //服务器成功响应处理函数
        window.location.reload()
        },
        error: function (data, status, e) {//服务器响应失败处理函数
        alert("文件上传失败");
        }
        })
        });
        }, 1000);


        }
        })
        })
        }
        </script>
        </body>
        </html>