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

</head>
<body>
<div class="page-container">
    <div class="text-c">
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <button onClick="add1()"
                class="btn btn-success radius" title="编辑富文本" type="button" style="padding: 0 50px;">编辑富文本
        </button>
        <button onClick="uploadfile()"
                class="btn btn-success radius" title="上传PDF" type="button" style="padding: 0 50px;">上传PDF
        </button>
        <button onClick="add3()"
                class="btn btn-success radius" title="编辑清单网址" type="button" style="padding: 0 50px;">编辑清单网址
        </button>

    </div>




    <div class="mt-20" style="width:100%;">
                <c:if test="${item.type==1}">
                    <div>我是富文本</div>
                </c:if>
                <c:if test="${item.type==2}">
<%--                    <iframe src="${item.file}" width='100%' height='100%' frameborder='1'>--%>
<%--                    </iframe>--%>
                    <div>我是pdf</div>
                </c:if>
                <c:if test="${item.type==3}">
                    <div>我是网页</div>
                </c:if>
                <c:if test="${item.type==null}">
                    <div>请先添加清单</div>
                </c:if>
    </div>






    <!-- 富文本编辑 -->
    <div id="modal-plan1" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
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
                                <textarea id="trInput" cols="6" rows="3"
                                          style="width: 300px;padding:5px"></textarea>
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


    <!-- pdf上传 -->
    <form enctype="multipart/form-data" id="fm1" method='post'>
        <input type="text" name="safetyStandardlistId" value='' style="display: none" id="fm1_imput"/>
        <input type="text" name="type" value='' style="display: none" id="fm1_imput2"/>
        <input type="file" name="file"   accept=".pdf" id="upload" style="display: none">
    </form>


    <!-- 编辑网址 -->
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
                                <textarea id="trInput" cols="6" rows="3"
                                          style="width: 300px;padding:5px"></textarea>
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

















    </div>
</div>


<script type="text/javascript">
    var id='${item.id}';
    var type = 0;
    console.log("${item.file}")






    function uploadfile() {
        type=2;
        $("#upload").click();
    }

    $("#upload").change(function () {
        $('#fm1_imput').val(id);
        $('#fm1_imput2').val(type);
        var form = document.querySelector("#fm1");
        var formdata = new FormData(form);
        $.ajax({
            url: getRootPath() + "/api/safety_Standard/B005",    //请求的url地址 
            data: formdata,    //参数值
            type: "POST",   //请求方式
            processData: false,
            contentType: false,
            success: function (res) {
                //请求成功时处理
                layer.msg(res.message);
                var file = document.getElementById('upload');
                file.value = ''; //把file 内容清空
                if (res.status == 0) {
                    setInterval(function(){
                        location.reload();
                    }, 1000)
                }
            },
            error: function (res) {
                //请求出错处理
                console.log(res, '请求失败');
                layer.msg('上传失败');
            }
        });
    });




</script>
</body>
</html>