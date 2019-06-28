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

        #container {
            width: 99%;
            position: absolute;
            top: 130px;
            bottom: 10px;
            left: 0px;
        }
    </style>
    <script type="text/javascript" src="${ly }/js/modules/common/common.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ly }/js/plugins/uedit/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ly }/js/plugins/uedit/ueditor.all.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ly }/js/plugins/uedit/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" src="${ly }/js/plugins/webuploader/webuploader.js"></script>
    <script type="text/javascript" src="${ly }/js/modules/userGroup/upload.js"></script>
    <script type="text/javascript" src="${ly }/js/plugins/websocket/swfobject.js"></script>
    <script type="text/javascript" src="${ly }/js/plugins/websocket/web_socket.js"></script>
    <script type="text/javascript" src="${ly }/js/plugins/juicer/juicer.js"></script>
    <script type="text/javascript" src="${ly }/js/modules/userGroup/messages.js"></script>
</head>
<body>
<div class="page-container">
<%--    <div class="cl pd-5 bg-1 bk-gray mt-20">--%>
<%--        <button onClick="add1()"--%>
<%--                class="btn btn-success radius" title="编辑富文本" type="button" style="padding: 0 50px;">编辑富文本--%>
<%--        </button>--%>
<%--        <button onClick="uploadfile()"--%>
<%--                class="btn btn-success radius" title="上传PDF" type="button" style="padding: 0 50px;">上传PDF--%>
<%--        </button>--%>
<%--        <button onClick="add3()"--%>
<%--                class="btn btn-success radius" title="编辑清单网址" type="button" style="padding: 0 50px;">编辑网址--%>
<%--        </button>--%>

<%--    </div>--%>
    <script id="editor" type="text/plain" style="width:auto;height:400px;"></script>



    <div class="mt-20 f-l mb-40" style="width:100%;text-align:center;">
        <button onClick="save()" class="btn btn-primary radius mt-20" type="button" style="padding: 0 70px;">
            <i class="Hui-iconfont mr-10">&#xe632;</i>保存
        </button>
    </div>

</div>


<script type="text/javascript">
    var id='${safetyStandardlistId}';
    var type ='${type}';
    var html = '${item.content}';
    var ue=null;
    $(function(){
         ue = UE.getEditor('editor',{
            imageActionName : "<c:url value='/village/new/ueditorUpload'/>",
            imageFieldName : "file",
            imageAllowFiles : ['.png','.jpg','.jpeg','.gif','.bmp']
        });



    })



    function save() {
        var content = ue.getContent();
        $.ajax({
            url: getRootPath() + "/api/safety_Standard/update-tSafetyStandard",    //请求的url地址 
            data: JSON.stringify({          //参数值
                id:id,
                type:type,
                content: content
            }),
            type: "POST",   //请求方式
            dataType: 'json', //返回值类型 一般设置为json
            contentType: "application/json",
            success: function (res) {
                layer.msg(res.mess);
                if(res.status==0){
                    setInterval(function(){
                        window.parent.location.href = '${ly}/api/safety_Standard/findOne?safetyStandardlistId='+id;
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    }, 1000)
                }

            }
        });
    }



</script>
</body>
</html>