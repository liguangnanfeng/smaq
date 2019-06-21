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
        <span class="c-gray en">&gt;</span> <span>评价评估系统</span>
        <span class="c-gray en">&gt;</span> <span>添加各类许可证档案</span>
        <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
    </nav>
    <div class="page-container">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">证书类型：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select id="a" name="type" class="sel_area">
                    <option value="">全部</option>
                    <option value="1"
                        <c:if test="${standard.type == '1'}"> selected="selected"</c:if>>危化品经营许可证</option>
                    <option value="2"
                        <c:if test="${standard.type == '2'}"> selected="selected"</c:if>>危化品生产许可证</option>
                    <option value="3"
                        <c:if test="${standard.type == '3'}"> selected="selected"</c:if>>危化品使用许可证</option>
                    <option value="4"
                        <c:if test="${standard.type == '4'}"> selected="selected"</c:if>>危化品登记证</option>
                    <option value="5"
                        <c:if test="${standard.type == '5'}"> selected="selected"</c:if>>易制毒化学品备案</option>
                    <option value="6"
                        <c:if test="${standard.type == '6'}"> selected="selected"</c:if>>易制爆化学品备案</option>
                    <option value="7"
                        <c:if test="${standard.type == '7'}"> selected="selected"</c:if>>标准化二级证书</option>
                    <option value="8"
                        <c:if test="${standard.type == '8'}"> selected="selected"</c:if>>标准化三级证书</option>
                    <option value="9"
                        <c:if test="${standard.type == '9'}"> selected="selected"</c:if>>港口经营许可证</option>
                    <option value="10"
                        <c:if test="${standard.type == '10'}"> selected="selected"</c:if>>成品油零售经营批准证书</option>
                    <option value="11"
                        <c:if test="${standard.type == '11'}"> selected="selected"</c:if>>小微企业标准化证书</option>
                </select>
            </div>
        </div>
        <div class="row cl mt-15">
            <label class="form-label col-xs-4 col-sm-2">证书图片：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <img id="pic3" src="${standard.img }" style="width:300px;min-height:300px;" url="${standard.img }" />
                <a href="javascript:void(0);" onclick="img_upload('pic3', null)" class="btn btn-primary radius upload-btn"><i class="Hui-iconfont">&#xe642;</i> 添加图片</a>
            </div>
        </div>
        <div class="row cl mt-15">
            <label class="form-label col-xs-4 col-sm-2">证书编号：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" id="c" value="${standard.certificateNumber }" style="width:350px" class="input-text required">
            </div>
        </div>
        <div class="row cl mt-15">
            <label class="form-label col-xs-4 col-sm-2">取证时间：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" id="d" name="time" value="${standard.timeGet }" onchange="" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" class="input-text Wdate mb-5 mt-5" style="width:350px;">
            </div>
        </div>
        <div class="row cl mt-15">
            <label class="form-label col-xs-4 col-sm-2">到期时间：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" id="e" name="time2" value="${standard.timeExp }" onchange="" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" class="input-text Wdate mb-5 mt-5" style="width:350px;">
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
var id_ = '${standard.id}';
function save_submit() {
  if($("#a").val() == '') {
    layer.alert("请选择证书类型",function(i) {
      layer.close(i);
      $("#a").focus();
    })
    return false;
  }
  if($("#pic3").attr("url") == '') {
    layer.alert("证书图片不能为空",function(i) {
      layer.close(i);
      $("#pic3").focus();
    })
    return false;
  }
  if($("#c").val() == '') {
    layer.alert("证书编号不能为空",function(i) {
      layer.close(i);
      $("#c").focus();
    })
    return false;
  }
  if($("#d").val() == '') {
    layer.alert("取证时间不能为空",function(i) {
      layer.close(i);
      $("#d").focus();
    })
    return false;
  }
  if($("#e").val() == '') {
    layer.alert("到期时间不能为空",function(i) {
      layer.close(i);
      $("#e").focus();
    })
    return false;
  }
  
      $.post(getRootPath() + "/company/evaluate/zheng-save", {
        id : id_,
        type: $("#a").val(),
        img: $("#pic3").attr("url"),
        certificateNumber: $("#c").val(),
        timeGet: $("#d").val(),
        timeExp: $("#e").val()
    }, function(result) {
            show_tab("各类许可证", getRootPath() + '/company/evaluate/zheng-list');
    })
}

$(function () {
    if($("#pic3").attr("url")==""){
          $("#pic3").attr("src", getRootPath() + "/images/zwtp.jpg");
    }
    if($("#a").val()!=""){
          $('#a').attr("disabled","false");
    }
})
</script>

</html>