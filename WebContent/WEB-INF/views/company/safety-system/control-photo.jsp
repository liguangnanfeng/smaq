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
        height: 260px;
        box-shadow: 0 0 5px #f1f1f1;
        margin-top: 20px;
        }
        .photo img {
        max-width: 100%;
        max-height: 80%;
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
        margin-top: -7px;
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
        font-size: 28px;
        margin-top: 10px;
            margin-bottom:35px;
        }
            .detailBtn:hover{
            cursor: pointer;
            }
        </style>
        </head>
        <body style="padding:0 15px;">
        <div>
        <input type="radio" name="co" value="222">
            <p class="contarName">${compangName}车间风险分布图
            <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:history.back(-1)" title="返回">返回</a></p>
        <div class="row cl mt-15">
        <label class="form-label col-xs-4 col-sm-2" style="width:9%;text-align:right;">资料上传：</label>
        <div class="formControls col-xs-8 col-sm-9" style="width:300px;">
        <span class="btn-upload form-group">
        <input class="input-text upload-url" type="text" name="uploadfile-1" id="uploadfile-1" readonly><a
        href="javascript:void();" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe642;</i> 浏览文件</a>
        <input type="file" multiple name="file" id="file" class="input-file">
        </span>
        </div>
            <label class="form-label col-xs-4 col-sm-2" style="width:7%;text-align:right;">名称：</label>
            <div class="formControls col-xs-8 col-sm-9" style="width:200px;">
            <span class="btn-upload form-group">
            <input class="input-text upload-url" type="text" name="filename" id="filename">
            </span>
            </div>
        <button class="btn btn-primary" onclick="upload()">确定上传</button>
        </div>
        </div>

            <table class="table table-border table-bordered table-bg table-hover table-sort">
            <thead>
            <tr class="text-c">
                    <th width="45px">序号</th>
                    <th>名称</th>
                    <th>图片</th>
                    <th width="295">操作</th>
            </tr>
            </thead>
            <tbody>
            <!-- 循环-->
            <c:forEach items="${list}" varStatus="index" var="be">
                    <tr class="text-c">
                    <td>${index.index + 1}</td>
                    <td>${be.name }</td>
                    <td><a href="${be.url}" target="_blank" style="border:none;"><img style="width:100px;" src="${be.url}"></a></td>
                    <td id="imgform${be.id}">
                        <input name="src" value="${be.url1}" style="display:none;">
                         <button class="detailBtn" onclick="amend('${be.id}','${be.coordinate}')">标注</button>
                         <button class="detailBtn" onclick="redact('${be.id}','${be.name }')">编辑</button>
                         <button class="detailBtn" onclick="del(${be.id})">删除</button>
                    </td>
                    </tr>
            </c:forEach>
            <!-- 循环结束 -->
            </tbody>
            </table>

            <div id="modal-plan2" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
            aria-hidden="true">
            <div class="modal-dialog" style="margin-top: 150px">
            <div class="modal-content radius">
            <div class="modal-header">
            <h3 class="modal-title">修改名称</h3>
            <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body" style="height: 200px; overflow-y: auto">
            <div class="form form-horizontal">
            <div class="row cl dq">
            <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>名称
            :</label>
            <div class="formControls col-xs-5 col-sm-5">
            <input class="input-text" type="text" name="" id="trInput" style="width:300px">
            </div>
            </div>
            <div class="row">
            <div class="col-xs-3 col-sm-3 col-xs-offset-8 col-sm-offset-8">
            <button class="btn radius btn-primary size-S" onclick="nameSave()">
            确认
            </button>
            </div>
            </div>
            </div>
            </div>
            </div>
            </div>

            </div>
        <script type="text/javascript" src="${ly}/js/jquery.imageLabel.min.js"></script>
        <script type="text/javascript" src="${ly}/js/html2canvas.min.js"></script>
        <script type="text/javascript">
            var obj = {id: '',name:''}
        $(function () {
        $('.imageLabel-input-box>div').css('background','red !important')
            $("#modal-plan2").modal("hide");
        <%--$('.contarName').text(getCookie('companyName')+'公司风险分布图')--%>
        $('.table-sort').dataTable({
        "aaSorting": [[0, "asc"]],//默认第几个排序
        "bStateSave": false,//状态保存
        searching: false,
        ordering: false,
        "aoColumnDefs": []
        });
        });
        function redact(id,name){
            $("#modal-plan2").modal("show");
            $('#trInput').val(name)
            obj.id = id
            }
            <%-- 修改 --%>
            function nameSave(){
                    if($('#trInput').val()!=''){
                            obj.name = $('#trInput').val()
                            $.post(getRootPath() + "/company/safety-system/update-photo   ", obj, function (res) {

                            if(res.status =='0'){
                                    window.location.reload()
                                    }else{
            layer.alert(res.mess, "")
            }
                            })
                    }else{
            layer.alert("请填写修改名称", "")
            }
            }
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
            if($('#filename').val()!=''){
            $.ajaxFileUpload({
            url: getRootPath() + '/company/safety-system/save-photo?flag=1&name='+$('#filename').val(), //用于文件上传的服务器端请求地址
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
            }else{
            layer.alert("请填写文件名称", "")
            }
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
                'y':newArr[0][4].split(':')[1],
                'color':newArr[0][5].split(':')[1]
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
        console.log(t)
        t.preventDefault();
        window.c = imageLabel({
        img: $("#imgform" + v + " [name=src]").val(),
        data: q,
        editPop: !0,
        close: function (t) {
        console.log(t)
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
        arr[i].y+",'color':"+arr[i].color
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
        coordinate: JSON.stringify(dataArr(t)),
        flag:1
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