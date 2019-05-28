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
.panel-default{display: inline-block;border:0px;}
body,html{background:#EBEEF3}
.title_xw{width:100%;font-weight:bold;font-size:15px;}
.text-overflow{display: inline-block;width:70%;line-height:25px;color:#7C7C7C;margin-right:0px;}
.text-overflow i{height:3px;width:3px;background:#7C7C7C;border-radius:50%;display:inline-block;vertical-align: middle;margin-right:5px;}
.text-overflow:hover a{color:#5889D8;text-decoration: underline;}
.text-overflow:hover i{background:#5889D8;}
.text-riqi{float:right;color:#AEAEAE;line-height:25px;}
body .iconxw{transform: rotate(-90deg);float:right;margin-right:-5px;margin-top: -3px;}
</style>
<script type="text/javascript">
$(function(){
  
  });
</script>
</head>
<body>
  <div class="page-container">
    <div class="panel panel-default" style="width:79%;margin-left:10px;float:left;padding:15px">
    <c:forEach items="${list }" varStatus="index" var="no">
      <div class="div_znotice">
        <p><a onClick="admin_show(${no.id})"  href="javascript:;"><lanlin:htmlToText length="200" value="${no.title }"/></a></p>
        <font>${no.time }</font>
      </div>
      </c:forEach>
    </div>
    <div style="float:right;width:15%;margin-right:10px">
    <div class="panel panel-default" style="width:100%;">
    <div class="panel-body" style="padding:8px">
      <a class="title_xw div_xinw" data-href="${ly }/back/notice-list?type=3"  data-title="法律法规标准规范" onclick="Hui_admin_tab(this)" href="javascript:;">
        <font>法律法规标准规范</font>
        <i class="Hui-iconfont iconxw">&#xe6d5;</i>
      </a>
    </div>
  </div>
  <div class="panel panel-default" style="width:100%;margin-top:15px;">
    <div class="panel-body" style="padding:8px">
      <a class="title_xw div_xinw" data-href="${ly }/back/notice-list?type=2"  data-title="安全员之家" onclick="Hui_admin_tab(this)" href="javascript:;">
        <font>安全员之家</font>
        <i class="Hui-iconfont iconxw">&#xe6d5;</i>
      </a>
    </div>
  </div>
  <!-- <div class="panel panel-default" style="width:100%;margin-top:15px;">
    <div class="panel-body" style="padding:8px">
      <a class="title_xw div_xinw" data-href="http://www.hupinwang.com/info" data-title="护品网" onclick="Hui_admin_tab(this)" href="javascript:;">
        <font>获取更多信息</font>
        <i class="Hui-iconfont iconxw">&#xe6d5;</i>
      </a>
    </div>
  </div> -->
    </div>
  </div>

<script type="text/javascript">
/*公告详情*/
function admin_show(id){
  show_tab("公告详情", getRootPath() + "/back/notice/notice-show?id=" + id + "&type=1");
}

</script> 
</body>
</html>