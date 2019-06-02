<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="format-detection" content="telephone=no" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/inc/back-header.inc"%>
<link type="text/css" href="${ly }/styles/all.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${ly}/styles/print.css" />
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style type="text/css">
.tabBar{margin-top:5px;}
.tabBar a{width:32%;overflow: hidden;padding:0;font-size:0.9em;}
.row{position: fixed;top:45px;left:0;width:100%;background:#fff;padding-bottom:5px;}
</style>
<script type="text/javascript">
    function pr_(e) {
      layer.alert("打印之前，请先保存！");
    }
    
    function save_() {
      $.post(getRootPath() + "/api/village/xlb/danger/opinion-save", {
          userId: '${userId }',
          copy: $("#container").html(),
          flag: 3,
          flag2 : 2
      }, function(reuslt) {
            window.location.href = getRootPath() + "/api/village/xlb/danger/opinion-detail?id="+reuslt.data.id;
      })
    }
    </script>
</head>
<body>
<div class="container">
  <div class="top_all">
    <div class="top_main">
      <a onclick="window.android.finishButton()" class="back_top"><img alt="" src="${ly }/images/weixin/fanhuib.png"/></a>
      <font class="top_title">文书检查</font>
      <a href="javascript:void(0)" class="btn_submit" onClick="save_()">保存</a> 
    </div>
  </div>
  <div class="allh1"></div>
  <!-- 原文件信息 -->
  <div id="container" style="width:150mm;margin:auto;padding:50px 0 0px 0;">
        <p class="dant1  mt-20" style="text-align:center;border-bottom:1px solid #000;margin-bottom:3px;" align="center">
            <b><span style="font-family:华文中宋;font-weight:bold;font-size:18pt;"><span>安全生产行政执法文书</span></span></b>
        </p>
        <p class="dant1" style="text-align:center;border-top:1px solid #000;margin-bottom:3px;" align="center">
            <span></span><b><span style="font-family:华文中宋;font-weight:bold;font-size:18pt;"><span>行政处罚告知书</span></span></b><b><span style="font-family:华文中宋;font-weight:bold;font-size:18.0000pt;"></span></b>
        </p>
        <p class="MsoNormal" style="text-align:center;margin-top:10px;" align="center">
            <span style="font-size:14.7pt;"><span contenteditable="true">（&nbsp;&nbsp;&nbsp;）</span><span>安监罚告</span><span contenteditable="true">〔&nbsp;&nbsp;&nbsp;〕</span> <span>号</span></span>
        </p>
        <p class="MsoNormal" style="margin-top:30px;">
            <u><span style="float:left;text-decoration:underline;" contenteditable="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${company.name }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u><span style=""><span>：</span></span><span style=""></span>
        </p>
        <p class="MsoNormal" style="text-indent:23.0000pt;text-align:left;">
            <span style="vertical-align: bottom;"><span>现查明，你（单位）存在下列行为：</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:50%;display:inline-block;text-indent: 0;" contenteditable="true">&nbsp;</span></u>
        </p>
        <p class="MsoNormal" style="width:100%;border-bottom:1px solid #333;text-indent: 0;min-height:23px" contenteditable="true">&nbsp;</p>
        <p class="MsoNormal" style="width:100%;border-bottom:1px solid #333;text-indent: 0;min-height:23px" contenteditable="true">&nbsp;</p>
        <p class="MsoNormal" style="text-indent:23.0000pt;text-align:left;">
            <span style="vertical-align: bottom;"><span>以上事实主要证据如下：</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:63%;display:inline-block;text-indent: 0;" contenteditable="true">&nbsp;</span></u>
        </p>
        <p class="MsoNormal" style="width:100%;border-bottom:1px solid #333;min-height:23px" contenteditable="true">&nbsp;</p>
        <p class="MsoNormal" style="width:100%;border-bottom:1px solid #333;min-height:23px" contenteditable="true">&nbsp;</p>
        <p class="MsoNormal" style="text-indent:23.0000pt;text-align:left;">
            <span style="vertical-align: bottom;"><span>以上行为违反了</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:74%;display:inline-block;text-indent: 0;" contenteditable="true">&nbsp;</span></u>
        </p>
        <p class="MsoNormal" style="text-align:left;">
            <span style="vertical-align: bottom;"><span>的规定，依据</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:82%;display:inline-block;" contenteditable="true">&nbsp;</span></u>
        </p>
        <p class="MsoNormal" style="text-align:left;">
            <span style="vertical-align: bottom;"><span>的规定，拟对你（单位）作出</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:46%;display:inline-block;" contenteditable="true">&nbsp;</span></u>
            <span style="vertical-align: bottom;"><span>的行政处罚。</span></span>
        </p>
        <p class="MsoNormal" style="text-indent:23.0000pt;text-align:left;line-height:28px;">
            <span style="vertical-align: bottom;"><span>如对上述处罚有异议，根据《中华人民共和国行政处罚法》第三十一条和第三十二条的规定，你（单位）有权在收到本告知书之日起3日内向</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:10%;display:inline-block;text-indent: 0;" contenteditable="true">&nbsp;</span></u>
            <span style="vertical-align: bottom;"><span>安全生产监督管理部门进行陈述和申辩，逾期不提出申请的，视为放弃上述权利。</span></span>
        </p>
        <p class="MsoNormal" style="text-align:left;margin-top:15px">
            <span style="vertical-align: bottom;"><span>安全生产监督管理部门地址：</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:64%;display:inline-block;" contenteditable="true">&nbsp;</span></u>
        </p>
        <p class="MsoNormal" style="text-align:left;margin-top:15px;">
            <span style="vertical-align: bottom;"><span>联系人：</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:18%;display:inline-block;" contenteditable="true">&nbsp;</span></u>
            <span style="vertical-align: bottom;"><span>联系电话：</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:18%;display:inline-block;" contenteditable="true">&nbsp;${company.legalContact }</span></u>
            <span style="vertical-align: bottom;"><span>邮政编码：</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:19%;display:inline-block;" contenteditable="true">&nbsp;${company.postage }</span></u>
        </p>
        <p class="MsoNormal" style="text-align:right;margin-top:60px">
            <span style=""><span>安全生产监督管理部门（印章）</span></span><span style=""></span>
        </p>
        <p class="MsoNormal" style="text-align:right">
            <span style=""><span contenteditable="true">&nbsp;<fmt:formatDate value="${timenow }" pattern="yyyy"/></span> 年&nbsp;&nbsp;<span contenteditable="true">&nbsp;<fmt:formatDate value="${timenow }" pattern="MM"/></span> 月&nbsp;&nbsp;<span contenteditable="true">&nbsp;<fmt:formatDate value="${timenow }" pattern="dd"/></span>日</span><span style=""></span>
        </p>
        <p class="MsoNormal" style="border-top:1px solid #000;">
            <span style=""><span>本文书一式两份：一份由安全生产监督管理部门备案，一份交拟处罚当事人。</span> </span><span style=""></span>
        </p>
        </div>
        <div class="allh1"></div>
</div>

</body>
</html>
