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
.title_xw{width:100%;border-bottom:0px solid #F6F6F6;font-weight:bold;font-size:14px;}
.one_notice{width:100%;margin-top:10px;}
.text-overflow{display: inline-block;width:70%;line-height:25px;color:#7C7C7C;margin-right:0px;}
.text-overflow i{height:3px;width:3px;background:#7C7C7C;border-radius:50%;display:inline-block;vertical-align: middle;margin-right:5px;}
.text-overflow:hover a{color:#5889D8;text-decoration: underline;}
.text-overflow:hover i{background:#5889D8;}
.text-riqi{float:right;color:#AEAEAE;line-height:25px;}
body .iconxw{transform: rotate(-90deg);float:right;margin-right:-5px;margin-top: -3px;}
</style>
<script type="text/javascript">
$(function(){
  $(".title_danger").on("click",function(){
    if($(this).parent().hasClass("listdxz")){
      $(this).parent().removeClass("listdxz");
      $(this).find("i").html("&#xe6d5;");
    }else{
      $(this).parent().addClass("listdxz");
      $(this).find("i").html("&#xe6d7;");
    }
  });
  });
</script>
</head>
<body>
  <div class="panel panel-default" style="width:79%;margin-left:10px;float:left;padding:15px;margin-top:20px;">
    <div class="panel-body" style="padding:0px;">
      <div class="page-container">
        <div class="titi_gg">${n.title }</div>
        <div class="titi_gg2" style="position: relative;">发布时间：${n.time }&nbsp;&nbsp;来源：${n.source }
          <div class="bshare-custom" style="position: absolute;right:0;top:3px;">
          <div class="bsPromo bsPromo2"></div>
          <a title="分享到腾讯微博" style="float:right;" class="bshare-qqmb"></a>
          <a title="分享到i贴吧" style="float:right;" class="bshare-itieba" href="javascript:void(0);"></a>
          <a title="分享到新浪微博" style="float:right;" class="bshare-sinaminiblog"></a>
          <a title="分享到QQ空间" style="float:right;" class="bshare-qzone"></a>
          <font style="float:right;line-height:20px;margin-right:5px;color:#585858;font-size:12px;">分享到</font>
          <a title="更多平台" style="float:right;" class="bshare-more bshare-more-icon more-style-addthis"></a>
        </div>
        <script type="text/javascript" charset="utf-8" src="${ly }/js/buttonLite.js#style=-1&amp;uuid=&amp;pophcol=1&amp;lang=zh"></script>
        <script type="text/javascript" charset="utf-8" src="${ly }/js/bshareC0.js"></script>
        </div>
        <div class="notice-detail">${n.content}</div>
      </div>
    </div>
  </div>
  <div style="width:15%;position: fixed;right:20px;top:20px;">
    <div class="panel panel-default" style="width:100%;">
    <div class="panel-body"  style="padding:8px">
      <a class="title_xw div_xinw" data-href="${ly }/fore/notice-list"  data-title="监管动态" onclick="Hui_admin_tab(this)" href="javascript:;">
        <font>监管动态</font>
        <i class="Hui-iconfont iconxw">&#xe6d5;</i>
      </a>
    </div>
  </div>
    <div class="panel panel-default" style="width:100%;margin-top:15px;">
    <div class="panel-body" style="padding:8px">
      <a class="title_xw div_xinw" data-href="${ly }/fore/notice-list1"  data-title="法律法规标准规范" onclick="Hui_admin_tab(this)" href="javascript:;">
        <font>法律法规标准规范</font>
        <i class="Hui-iconfont iconxw">&#xe6d5;</i>
      </a>
    </div>
  </div>
  <div class="panel panel-default" style="width:100%;margin-top:15px;">
    <div class="panel-body" style="padding:8px">
      <a class="title_xw div_xinw" data-href="${ly }/fore/notice-list2"  data-title="安全员之家" onclick="Hui_admin_tab(this)" href="javascript:;">
        <font>安全员之家</font>
        <i class="Hui-iconfont iconxw">&#xe6d5;</i>
      </a>
    </div>
  </div>
  <div class="panel panel-default" style="width:100%;margin-top:15px;">
    <div class="panel-body listd" style="padding:8px">
      <div class="title_xw div_xinw title_danger">
        <font>风险因素检索</font>
        <i class="Hui-iconfont iconxw">&#xe6d5;</i>
      </div>
      <div class="list_gg">
        <div class="one_notice">
          <div class="text-overflow">
            <i class="Hui-iconfont">&nbsp;</i>
            <a data-href="${ly }/html/danger.html" data-title="重大事故隐患判定标准" onclick="Hui_admin_tab(this)" href="javascript:;">重大事故隐患判定标准</a>
          </div>
        </div>
        <div class="one_notice">
          <div class="text-overflow">
            <i class="Hui-iconfont">&nbsp;</i>
            <a data-href="${ly }/fore/notice-show?id=543" data-title="重大火灾隐患判定方法" onclick="Hui_admin_tab(this)" href="javascript:;">重大火灾隐患判定方法</a>
          </div>
        </div>
        <div class="one_notice">
          <div class="text-overflow">
            <i class="Hui-iconfont">&nbsp;</i>
            <a data-href="${ly }/fore/danger/wei-list" data-title="重大危险源辨识目录" onclick="Hui_admin_tab(this)" href="javascript:;">重大危险源辨识目录</a>
          </div>
        </div>
        <div class="one_notice">
          <div class="text-overflow">
            <i class="Hui-iconfont">&nbsp;</i>
            <a data-href="${ly }/fore/danger/danger-list" data-title="危化品目录" onclick="Hui_admin_tab(this)" href="javascript:;">危化品目录</a>
          </div>
        </div>
        <div class="one_notice">
          <div class="text-overflow">
            <i class="Hui-iconfont">&nbsp;</i>
            <a data-href="${ly }/fore/danger/bao-list" data-title="易制爆危险化学品" onclick="Hui_admin_tab(this)" href="javascript:;">易制爆危险化学品</a>
          </div>
        </div>
        <div class="one_notice">
          <div class="text-overflow">
            <i class="Hui-iconfont">&nbsp;</i>
            <a data-href="${ly }/fore/danger/du-list" data-title="易制毒化学品" onclick="Hui_admin_tab(this)" href="javascript:;">易制毒化学品</a>
          </div>
        </div>
        <div class="one_notice">
          <div class="text-overflow">
            <i class="Hui-iconfont">&nbsp;</i>
            <a data-href="${ly }/fore/danger/zhi-list" data-title="职业病有害因素" onclick="Hui_admin_tab(this)" href="javascript:;">职业病有害因素</a>
          </div>
        </div>
        <div class="one_notice">
          <div class="text-overflow">
            <i class="Hui-iconfont">&nbsp;</i>
            <a data-href="${ly }/company/trouble/trouble-information" data-title="隐患排查治理标准信息库" onclick="Hui_admin_tab(this)" href="javascript:;">隐患排查治理标准</a>
          </div>
        </div>
        <div class="one_notice">
          <div class="text-overflow">
            <i class="Hui-iconfont">&nbsp;</i>
            <a data-href="${ly }/company/trouble/trouble-information-list" data-title="较大危险因素辨识与防范指导手册" onclick="Hui_admin_tab(this)" href="javascript:;">较大危险因素辨识与防范指导手册</a>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="panel panel-default" style="width:100%;margin-top:15px;">
    <div class="panel-body" style="padding:8px">
      <a class="title_xw div_xinw" data-href="http://www.hupinwang.com/info" data-title="护品网" onclick="Hui_admin_tab(this)" href="javascript:;">
        <font>获取更多信息</font>
        <i class="Hui-iconfont iconxw">&#xe6d5;</i>
      </a>
    </div>
  </div>
    </div>
</body>
</html>