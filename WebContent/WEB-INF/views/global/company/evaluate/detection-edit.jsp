<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
    content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<%@ include file="/WEB-INF/inc/back-header.inc"%>
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style type="text/css">
.input-text {
    position: relative
}

.txtarea_sq {
    height: 100px;
    width: 350px;
}
</style>
<script type="text/javascript">

</script>
</head>
<body>
    <nav class="breadcrumb">
        <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> <span
            class="c-gray en">&gt;</span> <span>职业健康管理</span>
           <span class="c-gray en">&gt;</span>
            <span>职业健康管理台账</span> <span
            class="c-gray en">&gt;</span> <span>编辑检测检验</span> <a
            class="btn btn-success radius r"
            style="line-height: 1.6em; margin-top: 3px"
            href="javascript:location.replace(location.href);" title="刷新"> <i
            class="Hui-iconfont">&#xe68f;</i>
        </a>
    </nav>
    <div class="page-container">
        <form class="form form-horizontal" id="form">
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">检测类别：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <select id="a" class="sel_area">
                        <option value="" >请选择</option>
                        <c:forEach items="${list }" varStatus="index" var="l">
                            <option value="${l.name }" <c:if test="${d.type eq l.name}"> selected</c:if>>${l.name }</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">上次检测时间 ：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" id="b" value="${d.reportTime }" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" class="input-text Wdate mb-5 mt-5"
                        style="width: 350px;">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">到期时间：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" id="c" value="${d.expirationTime }"  onfocus="WdatePicker({dateFmt:'yyyy-MM'})" class="input-text Wdate mb-5 mt-5"
                        style="width: 350px;">
                </div>
            </div>
            
            <div class="row cl" style="display:none">
                <label class="form-label col-xs-4 col-sm-2">监测点：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" id="jcd" value="${d.jcd }" class="input-text mb-5 mt-5" style="width: 350px;">
                </div>
            </div>
            
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">检测职业病危害因素：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" id="whys" value="${d.whys }" class="input-text mb-5 mt-5" style="width: 350px;">
                </div>
            </div>
            
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">检测结果：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" id="jcjg" value="${d.jcjg }" class="input-text mb-5 mt-5" style="width: 350px;">
                </div>
            </div>
            
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">检测单位：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" id="jcdw" value="${d.jcdw }" class="input-text mb-5 mt-5" style="width: 350px;">
                </div>
            </div>
            
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">检测编号：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" id="jcbh" value="${d.jcbh }" class="input-text mb-5 mt-5" style="width: 350px;">
                </div>
            </div>
             
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">网上申报情况</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <textarea id="ba" class="textarea txtarea_sq">${d.ba }</textarea>
                </div>
            </div>
             
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">备注：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <textarea id="d" class="textarea txtarea_sq">${d.remark }</textarea>
                </div>
            </div>
            <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                    <button onClick="article_save_submit()"
                        class="btn btn-primary radius" type="button"
                        style="padding: 0 70px;">
                        <i class="Hui-iconfont">&#xe632;</i>保存
                    </button>
                </div>
            </div>
        </form>
    </div>
</body>
<script type="text/javascript">
var id='${d.id}';

function article_save_submit() {
  if($("#a").val() == '') {
    layer.alert("检测类型不能为空",function(i) {
      layer.close(i);
      $("#a").focus();
    })
    return false;
  }
  if($("#b").val() == '') {
    layer.alert("检测时间不能为空",function(i) {
      layer.close(i);
      $("#b").focus();
    })
    return false;
  }
  if($("#c").val() == '') {
    layer.alert("到期时间不能为空",function(i) {
      layer.close(i);
      $("#c").focus();
    })
    return false;
  }
  
  $.post(getRootPath() + "/company/evaluate/detection-save",{
    id : id,
    type : $("#a").val(),
    reportTime:$("#b").val(),
    expirationTime:$("#c").val(),
    remark:$("#d").val(),
    jcd:$("#jcd").val(),
    whys:$("#whys").val(),
    jcjg:$("#jcjg").val(),
    jcdw:$("#jcdw").val(),
    jcbh:$("#jcbh").val(),
    ba:$("#ba").val()
  }, function (result) {
       show_tab("检测检验",  getRootPath() +'/company/evaluate/detection-list');
  })
}
</script>
</html>