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
<style type="text/css">
.input-text{position:relative}
.txtarea_sq{height:100px;width:350px;}
</style>
<script type="text/javascript">

</script>
</head>
<body>
<nav class="breadcrumb">
  <i class="Hui-iconfont">&#xe67f;</i>
  <span>首页</span>
  <span class="c-gray en">&gt;</span> <span>职业健康管理</span> 
  <span class="c-gray en">&gt;</span> <span>劳动者个人职业健康监护</span>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
    <i class="Hui-iconfont">&#xe68f;</i>
  </a>
</nav>
<div class="page-container">
  <form class="form form-horizontal" id="form">
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">劳动者姓名 ：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="name" class="input-text mb-5 mt-5" style="width:350px;" value="${c.name }">
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">检查日期 ：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="time" value="${c.time }" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" class="input-text Wdate mb-5 mt-5" style="width:350px;">
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">检查项目摘要：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <textarea id="jcxmzy" class="textarea txtarea_sq">${c.jcxmzy }</textarea>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">检查结果：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="jcjg" class="input-text mb-5 mt-5" style="width:350px;" value="${c.jcjg }">
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">检查单位：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="jcdw" class="input-text mb-5 mt-5" style="width:350px;" value="${c.jcdw }">
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">接触的职业病危害因素名称：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="zybwhys" class="input-text mb-5 mt-5" style="width:350px;" value="${c.zybwhys }">
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">检测类别：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <select id="tjlb" class="sel_area">
          <option>在岗</option>
          <option>岗前</option>
          <option>离岗</option>
          <option>诊断</option>
          <option>复查</option>
        </select>
      </div> 
      <script type="text/javascript">
      var tjlb = '${c.tjlb}';
      if(tjlb != '') {
        $("#tjlb").val(tjlb);
      }
      </script>
    </div>
    <div class="row cl">
      <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
        <button onClick="article_save_submit()" class="btn btn-primary radius" type="button" style="padding: 0 70px;">
          <i class="Hui-iconfont">&#xe632;</i>保存
        </button>
      </div>
    </div>
  </form>
  <script type="text/javascript">
  var id = '${c.id}';
  function article_save_submit() {
    if($("#name").val() == '') {
      layer.alert("劳动者姓名不能为空",function(i) {
        layer.close(i);
        $("#name").focus();
      })
      return false;
    }
    $.post(getRootPath() + "/company/cyryjkjh-save",{
      id : id,
      name : $("#name").val(),
      time : $("#time").val(),
      jcxmzy : $("#jcxmzy").val(),
      jcjg : $("#jcjg").val(),
      jcdw : $("#jcdw").val(),
      zybwhys : $("#zybwhys").val(),
      tjlb : $("#tjlb").val(),
    }, function (result) {
      show_tab("劳动者个人职业健康监护",  getRootPath() +'/company/cyryjkjh');
    })
  }
  </script>
  
</div>
</body>
</html>