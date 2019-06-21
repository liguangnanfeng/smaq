<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>

<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <%@ include file="/WEB-INF/inc/back-header.inc"%>
    <title>风险分级管控   隐患排查治理智能化平台</title>
    <meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
    <meta name="description" content="风险分级管控   隐患排查治理智能化平台">
    <script type="text/javascript" src="https://webapi.amap.com/maps?v=1.3&key=441462b46b736497a5f2a377aafb5b69"></script>
    <style type="text/css">
    .ter1 {
        width: 15%
    }

    .tab_tmr1 {
        width: 35%
    }
    </style>
</head>

<body>
    <nav class="breadcrumb">
    	<i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    	<span class="c-gray en">&gt;</span> <span>风险研判与承诺公告</span> 
    	<span class="c-gray en">&gt;</span> <span>运行管理</span> 
    	<span class="c-gray en">&gt;</span> <span>重大、较大隐患治理风险研判</span> 
    	<span class="c-gray en">&gt;</span> <span>风险图片添加</span> 
        <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
    </nav>
    <div class="page-container">
        <div class="row cl mt-15">
            <div class="formControls col-xs-8 col-sm-9">
                <img id="pic3" src="${ndi.img }" style="width:300px;min-height:300px;" url="${ndi.img }" />
                <a href="javascript:void(0);" onclick="img_upload('pic3', null)" class="btn btn-primary radius upload-btn"><i class="Hui-iconfont">&#xe642;</i> 添加图片</a>
            </div>
        </div>
        <div class="row cl mt-20">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <button onClick="save_submit()" class="btn btn-primary radius" type="button" style="padding: 0 70px;">
                    <i class="Hui-iconfont">&#xe632;</i>保存
                </button>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
var id_ = '${ndi.id}';
var dangerid_ = '${ndi.dangerid}';
var dangerstudyid_ = '${ndi.dangerstudyid}';
function save_submit() {
      $.post(getRootPath() + "/company/study/greatdanger-imgsave", {
    	id : id_ ,
    	dangerid : dangerid_ ,
    	dangerstudyid : dangerstudyid_,
        img: $("#pic3").attr("url"),
    }, function(result) {
            show_tab("各类许可证", getRootPath() + '/company/study/greatdanger-list');
    })
}

$(function () {
    if($("#pic3").attr("url")==""){
          $("#pic3").attr("src", getRootPath() + "/images/zwtp.jpg");
    }
})
</script>
</html>