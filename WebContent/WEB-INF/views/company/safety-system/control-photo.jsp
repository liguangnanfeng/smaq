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
            display: inline-block;
            width: 33%;
            text-align: center;
            padding: 10px;
            height: 200px;
            box-shadow: 0 0 5px #f1f1f1;
            margin-top: 10px;
        }

        .photo img {
            max-width: 100%;
            max-height: 100%;
        }

        .photo span {
            font-size: 24px;
            color: red;
            font-weight: 600;
            cursor: pointer;
            float: right;
        }

        .detailBtn {
            color: #999;
            font-size: 15px;
            margin-right: 15px;
            display: inline-block;
            float: right;
            background: transparent;
            border: none;
            margin-top: 8px;
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
    </div>
</div>

<c:forEach items="${list }" var="be">
    <form action="#" id="imgform${be.id}" class="photo">
        <input name="src" value="${be.url1}" style="display:none;">
        <div style="height:100%;">
            <img src="${be.url}">
            <span onclick="del(${be.id})">X</span>
            <button class="detailBtn" onclick="amend('${be.id}','${be.coordinate}')">编辑</button>
        </div>
    </form>
</c:forEach>

<script type="text/javascript" src="${ly}/js/jquery.imageLabel.min.js"></script>
<script type="text/javascript" src="${ly}/js/html2canvas.min.js"></script>
<script type="text/javascript">
    $(function () {
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
        console.log(v)
        console.log(s)
        // 图片
        $('#imgform' + v).click(function (t) {
            t.preventDefault();
            window.c = imageLabel({
                img: $("#imgform" + v + " [name=src]").val(),
                // data: s,
                editPop: !0,
                close: function (t) {
                    <%-- 关闭 --%>
                    return t.length, !0
                },
                confirm: function (t) {
                    <%-- 提交 --%>
                    function dataArr(arr){
                        if(arr&&arr.length>0){
                            var newA = []
                            for(var i=0;i<arr.length;i++){
                                var str = ''
                                str = "'ex':"+arr[i].ex+",'ey':"+arr[i].ey+",'name':"+arr[i].name+",'x':"+arr[i].x+",'y':"+arr[i].y
                                newA.push(str)
                            }
                        }
                        return newA
                    }
                    console.log(dataArr(t))
                    console.log(dataArr(t).toString())
                    console.log(JSON.stringify(dataArr(t)))

                    <%-- 截图 --%>
                    layer.msg('标注比较耗时,请耐心等待 ! 不要做其他操作!', {
                        time: 1000, //1s后自动关闭
                    });
                    setTimeout(function () {
                        html2canvas(document.querySelector(".imageLabel-jisuan"), {
                            useCORS: true, allowTaint: false, foreignObjectRendering: true, taintTest: true, scale: 1
                        }).then(function (canvas) {
                            var image = canvas.toDataURL("image/png", 0.1);
                            console.log(image)
                            console.log(v)
                            $.ajax({
                                type:"POST",
                                url: getRootPath() + '/company/safety-system/control-addCoordinate',
                                contentType: ' application/x-www-form-urlencoded',
                                data: {
                                    id: v,
                                    images: image,
                                    coordinate: JSON.stringify(dataArr(t))
                                },
                                success: function (result) { //服务器成功响应处理函数
                                    console.log('成功');
                                    console.log(result);
                                    return t.length, !0
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