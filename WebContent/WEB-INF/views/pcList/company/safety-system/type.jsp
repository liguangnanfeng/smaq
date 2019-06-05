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
body .row{margin:0;padding:10px 30px;width:500px;margin:auto;}
body .radio-box{float:Left;width:100%;margin-top:15px;padding-right:0;padding-left:0;}
</style>
<script type="text/javascript">
$(function() {
});


</script> 
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>风险分级管控</span> 
    <span class="c-gray en">&gt;</span> <span>选择行业</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="mt-20">
      <div class="row cl">
        <h4 class="mt-20">选择行业</h4>
        <div class="radio-box">
          <input type="radio" name="type">
          <label>化工企业（危化生产、使用）</label>
        </div>
        <div class="radio-box">
          <input type="radio" name="type">
          <label>冶金行业</label>
        </div>
        <div class="radio-box">
          <input type="radio" name="type">
          <label>有色行业</label>
        </div>
        <div class="radio-box">
          <input type="radio" name="type">
          <label>建材行业</label>
        </div>
        <div class="radio-box">
          <input type="radio"  name="type">
          <label>机械行业</label>
        </div>
        <div class="radio-box">
          <input type="radio"  name="type">
          <label>轻工行业</label>
        </div>
        <div class="radio-box">
          <input type="radio"  name="type">
          <label>纺织行业</label>
        </div>
        <div class="radio-box">
          <input type="radio"  name="type">
          <label>烟草行业</label>
        </div>
        <div class="radio-box">
          <input type="radio"  name="type">
          <label>商贸行业</label>
        </div>
        <div class="radio-box">
          <input type="radio"  name="type">
          <label>非煤矿山行业</label>
        </div>
        <div class="radio-box">
          <input type="radio"  name="type">
          <label>其他行业</label>
        </div>
        <button onClick="save_ind()" class="btn btn-primary radius mt-20" type="button" style="padding: 0 70px;">
          <i class="Hui-iconfont mr-10">&#xe632;</i>保存
        </button>
      </div>
    </div>
  </div>
</body>
<script type="text/javascript">
var url = '${url}';
//行业保存
function save_ind() {
  if($(":radio:checked").length ==0) {
    layer.alert("请选择所属行业");
    return false;
  } else {
    $.post(getRootPath() + "/company/safety-system/company-industry", {
      industry : $(":radio:checked").parent().find("label").text()
    }, function(result) {
      parent.load_axfxgkxt();
      //location.href = url;
      parent.location.reload();
    })
  }
}
</script>
</html>