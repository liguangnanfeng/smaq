<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@ include file="/WEB-INF/inc/print.inc" %>
<!-- add by zhangcl 2018.10.13 -->
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
    <link rel="stylesheet" type="text/css" media="print" href="${ly}/js/H-ui.admin_v3.0/static/h-ui/css/H-ui.css"/>
    <link rel="stylesheet" type="text/css" href="${ly}/styles/common.css" media="print"/>
    <%--    <link rel="stylesheet" type="text/css" href="css/normalize.css" />--%>

    <%--    <link rel="stylesheet" type="text/css" href="css/demo.css" />--%>

    <%--    <link rel="stylesheet" type="text/css" href="css/component.css" />--%>


    <style type="text/css">
        .ter1 {
            width: 15%
        }

        .tab_tmr1 {
            width: 35%
        }

        .switch-left {
            height: 26px;
            vertical-align: top;
        }

        .switch-right {
            height: 26px;
            vertical-align: top;
        }

        #menu {
            padding: 10px 10px 10px;
            text-align: center;
            position: fixed;
            z-index: 998;
            background-color: #fcf9f2;
            display: none;
            border-radius: 5px;
            box-shadow: 2px 2px 5px #333333;
            height: 300px;
            overflow-y: auto;
        }

        .my_delete {
            float: right;
            margin-top: 5px;

        }
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
            width: 628px;
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


    <style type="text/css" media="print">
        body .dis-ib {
            margin-right: 15px;
        }

        .col-b, .col-a, .col-c, .col-d {
            margin: 0;
            padding: 0 15px;
            height: 25px;
            line-height: 25px;
        }

        .div-print {
            width: 90%;
            margin: auto;
            padding: 20px 0 0 0;
        }

        h3 {
            display: block;
            width: 100%;
            margin-bottom: 15px;
        }
    </style>
    <style type="text/css">
        body .dis-ib {
            margin-right: 15px;
        }

        .col-b, .col-a, .col-c, .col-d {
            margin: 0;
            padding: 0 15px;
            height: 25px;
            line-height: 25px;
        }

        .div-print {
            width: 90%;
            margin: auto;
            padding: 20px 0 0 0;
        }

        /* h3{display:none;} */
    </style>

    <%--    截取地图css--%>
    <style>
        .jtrq {
            width: 100%;
            height: calc(100% - 39px);
            display: none;
            display: -webkit-box; /* Chrome 4+, Safari 3.1, iOS Safari 3.2+ */
            display: -moz-box; /* Firefox 17- */
            display: -webkit-flex; /* Chrome 21+, Safari 6.1+, iOS Safari 7+, Opera 15/16 */
            display: -moz-flex; /* Firefox 18+ */
            display: -ms-flexbox; /* IE 10 */
            display: flex; /* Chrome 29+, Firefox 22+, IE 11+, Opera 12.1/17/18, Android 4.4+ */
            -webkit-box-orient: vertical;
            -ms-flex-direction: column;
            -webkit-flex-direction: column;
            flex-direction: column;
            -webkit-box-pack: center;
            -ms-flex-pack: center;
            -webkit-justify-content: center;
            justify-content: center;
            -webkit-box-align: center;
            -ms-flex-align: center;
            -webkit-align-items: center;
            align-items: center;
        }

        .jt {
            max-width:100%;
			width:auto;
            height: auto;
            max-height: 80%;
        }

    .contarName{
    text-align: center;
    font-size: 20px;
    margin-top: 10px;
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
    filter: alpha(opacity=0);
    height:50px;
    }

    .btn-upload .input-text {
    width: auto
    }

    .form-group .upload-btn {
    margin-left: -1px
    }
    </style>

    <link rel="stylesheet" href="${ly}/js/jquery.imageLabel.min.css">
    <link rel="stylesheet" href="${ly}/js/edit.css">
    <script src="/js/jquery.jqprint-0.3.js"></script>
    <%--    截取地图js--%>

    <script type="text/javascript">
	  function print() {
            $("#jietu").jqprint();
        }
    </script>

</head>

<body>
<nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
    <span class="c-gray en">&gt;</span> <span>风险分级管控</span>
    <span class="c-gray en">&gt;</span> <span>风险评估</span>
    <span class="c-gray en">&gt;</span> <span>${company.name}风险分布图</span>

    <!--</div>-->

    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>

<%--    <a id="aback" class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:history.back(-1)" title="返回">返回</a>--%>
	 <a class="btn btn-success radius r " style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;"
       href="javascript:print();" title="打印">
        打印
    </a>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:amend();" title="标注">标注</a>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:upLo();" title="上传">上传</a>
    <a class="btn btn-success radius r " style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;"
       href="javascript:showMAp();" title="查看地图">
        查看地图
    </a>

</nav>

<%--截图地图--%>


  <!--   <p class="contarName"></p> -->
<div id="jietu" class="jtrq">
    <h3 id="title" style="margin-bottom: 20px;"></h3>
    <a id="imgform">
    <img id="fxt" src="" class="jt"/>
    <img id="fxt1" src="" class="jt"style="display:none;"/>
    </a>

</div>

    <div id="modal-plan2" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
    aria-hidden="true">
    <div class="modal-dialog" style="margin-top: 150px">
    <div class="modal-content radius">
    <div class="modal-header">
    <h3 class="modal-title">上传图片</h3>
    <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
    </div>
    <div class="modal-body" style="height: 200px; overflow-y: auto">
        <label class="form-label col-xs-6 col-sm-2" style="width:130px;text-align:right;margin-top: 50px;">资料上传：</label>
        <div class="formControls col-xs-6 col-sm-9" style="width:300px;margin-top: 50px;">
            <span class="btn-upload form-group">
            <input class="input-text upload-url" type="text" name="uploadfile-1" id="uploadfile-1" readonly><a
                    href="javascript:void();" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe642;</i> 浏览文件</a>
            <input type="file" multiple name="file" id="file" class="input-file">
            </span>
        </div>
        <button class="btn btn-primary" onclick="upload()" style="margin-top: 50px;">确定上传</button>
    </div>
    </div>
    </div>
    </div>

    </div>

</body>

    <script type="text/javascript" src="${ly}/js/jquery.imageLabel.min.js"></script>
    <script type="text/javascript"
        src="https://webapi.amap.com/maps?v=1.4.14&key=da3f11570a7a5d170923792f72357543&plugin=AMap.PolyEditor"></script>
<script type="text/javascript" src="${ly}/js/html2canvas.min.js"></script>
<script type="text/javascript">


    let dataObj = {}
    //加载时请求有没有风险图
    $(function () {
    $("#modal-plan2").modal("hide");
   $('#title').text( sessionStorage.getItem('companyName')+'风险分布图')
        $.ajax({
            <%--url: getRootPath() + "/api/map/B003",    //请求的url地址 --%>
            url: getRootPath() + "/company/safety-system/find-photo",    //请求的url地址 
            data: {},    //参数值
            type: "POST",   //请求方式
            success: function (res) {
                console.log(res)
                //请求成功时处理
                if (res != null) {
                    $("#fxt").attr("src",res.url);
                    if(res&&res.flag&&res.flag==2){
                        dataObj = res
                        $("#fxt1").attr("src",res.url1);
                    }
                    <%--$("#fxt").attr("src", '${ly}/images/ceshi.jpg');--%>
                } else {
                    window.location.href = '${ly}/company/safety-system/control-list2?flag=2';
                }
            },
            error: function (res) {
                //请求出错处理
                console.log(res, '请求失败');
                window.location.href = '${ly}/company/safety-system/control-list2?flag=2';
            }
        });
    })

    function showMAp() {
        window.location.href = '${ly}/company/safety-system/control-list2?flag=2';
    }
    <%-- 标注 --%>
<%--function biaozhu(){--%>

    <%--}--%>
    function amend() {
        if(dataObj.flag&&dataObj.flag==2){
            document.querySelector('#imgform').click()
        }else{
    layer.alert('该图片非上传图片，不可标注');
    }
    }
    // 图片
        $('#imgform').click(function (t) {
            t.preventDefault();
    console.log(dataObj)
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
    if(!dataObj.coordinate||dataObj.coordinate==null||dataObj.coordinate==[]){
    q = []
    }else{
    q = shuju(dataObj.coordinate.split('/'))
    }
            window.c = imageLabel({
            img: $("#fxt1").attr("src"),
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
    id: dataObj.id,
    images: image,
    coordinate: JSON.stringify(dataArr(t)),
    flag:2
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
    <%--}--%>
<%-- 上传 --%>
    function upLo(){
     $("#modal-plan2").modal("show");
    }

    /*确认上传*/
    function upload() {
        $.ajaxFileUpload({
            url: getRootPath() + '/company/safety-system/save-photo?flag=2&name=""', //用于文件上传的服务器端请求地址
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
</script>
</html>
