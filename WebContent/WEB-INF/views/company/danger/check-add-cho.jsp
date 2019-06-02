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
body .dis-ib{margin-right:15px;}
.list_jian{padding:15px;min-height:180px;float:left;}
.font_rou{float:Left;widht:auto;line-height:180px;font-size:15px;font-weight:bold;margin-right:30px}
.div_bgrou{float:left;width:100%;padding: 0 50px 30px 50px;border-bottom:1px dashed #ddd;margin-bottom:40px;}
.div_bgrou .btn{margin-right:20px;height:70px;line-height:70px;padding:0 50px;font-size:16px;background:#F6F6F7;color:#333}
.btn-group{width:100%;text-align: center;margin-bottom:30px;}
.btn-group .btn{height:38px;line-height:38px;padding:0 25px;font-size:14px;}
.modal-dialog{margin-top:150px;}
.font_wz{color:red;margin-left:15px;display:none;}
</style>
<script type="text/javascript">

</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>行政执法系统</span> 
    <span class="c-gray en">&gt;</span> <span>执法检查</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
      <div class="div_bgrou">
        <font class="font_rou">执法检查</font>
        <div class="list_jian">
         <a class="btn_jian btn_an3" href="javascript:void(0)" onclick="show_dialog('执法检查-标准检查','${ly }/village/check-add?flag=2')">
           <span>
             <img alt="" src="${ly }/images/b1wxz.png" class="wxz"/>
             <img alt="" src="${ly }/images/b1xz.png" class="xz"/>
           </span> 
           <font>标准检查</font>
         </a>
         <a class="btn_jian btn_an4" href="javascript:void(0)" onclick="show_dialog('执法检查-自定义检查','${ly }/village/danger/opinion-add?flag=8&flag2=2')">
           <span>
             <img alt="" src="${ly }/images/b2wxz.png" class="wxz"/>
             <img alt="" src="${ly }/images/b2xz.png" class="xz"/>
           </span> 
           <font>自定义检查</font>
         </a>
       </div>
      </div>
    </div>
</body>
</html>