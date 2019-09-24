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
  <link rel="stylesheet" type="text/css" href="${ly}/styles/print.css" />
    <title>风险分级管控   隐患排查治理智能化平台</title>
    <meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
    <meta name="description" content="风险分级管控   隐患排查治理智能化平台">
    <style type="text/css">
    </style>
    <script type="text/javascript">
    function pr_(e) {
      layer.alert("打印之前，请先保存！");
  	}
    
    function save_() {
      $.post(getRootPath() + "/village/danger/opinion-save", {
       	  userId: '${userId }',
  		  copy: $("#container").html(),
          flag: 3,
          flag2 : 2
      }, function(reuslt) {
        	window.location.href = getRootPath() + "/village/danger/opinion-detail?uid=${uid}&id="+reuslt.data.id;
      })
  	}
    </script>
</head>

<body>
    <div class="page-container" style="width:150mm;margin:auto;padding:10px 0 0 0;">
        <div class="row cl row_ac">
            <button onClick="save_()" class="btn radius" type="button">
                <i class="Hui-iconfont mr-10">&#xe632;</i>保存
            </button>
            <button onClick="pr_()" class="btn radius" type="button">
                <i class="Hui-iconfont mr-10">&#xe652;</i>打印
            </button>
            <button onClick="top.show_tab('检查文书', '${ly }/global/check-list?flag=2')" class="btn radius" type="button">
                <i class="Hui-iconfont mr-10">&#xe66b;</i>返回列表
            </button>
            <%-- <button onClick="top.show_tab('检查文书', '${ly }/village/opinion-show?flag2=2')" class="btn radius" type="button" >
                <i class="Hui-iconfont mr-10">&#xe66b;</i>返回文书
            </button> --%>
        </div>
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
            <span style=""><span contenteditable="true">&nbsp;<fmt:formatDate value="${timenow }" pattern="yyyy"/>&nbsp;</span>年<span contenteditable="true">&nbsp;&nbsp;<fmt:formatDate value="${timenow }" pattern="MM"/>&nbsp;</span>月<span contenteditable="true">&nbsp;&nbsp;<fmt:formatDate value="${timenow }" pattern="dd"/>&nbsp;</span>日</span><span style=""></span>
        </p>
        <p class="MsoNormal" style="border-top:1px solid #000;">
            <span style=""><span>本文书一式两份：一份由安全生产监督管理部门备案，一份交拟处罚当事人。</span> </span><span style=""></span>
        </p>
        </div>
    </div>
</body>

</html>