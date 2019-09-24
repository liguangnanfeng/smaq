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
    $(function(){
      $(".MsoNormal i").click(function(){
        if($(this).hasClass("ixz")){
          $(this).removeClass("ixz");
        }else{
          $(this).addClass("ixz");
        }
      })
    });
    function pr_(e) {
      layer.alert("打印之前，请先保存！");
  	}
    
    function save_() {
      $.post(getRootPath() + "/village/danger/opinion-save", {
          userId: '${userId }',
  		  copy: $("#container").html(),
          flag: 4,
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
            <span></span><b><span style="font-family:华文中宋;font-weight:bold;font-size:18pt;"><span>行政（当场）处罚决定书（单位）</span></span></b><b><span style="font-family:华文中宋;font-weight:bold;font-size:18.0000pt;"></span></b>
        </p>
        <p class="MsoNormal" style="text-align:center;margin-top:10px;" align="center">
            <span style="font-size:14.7pt;"><span contenteditable="true">（&nbsp;&nbsp;&nbsp;）</span><span>安监罚当</span><span contenteditable="true">〔&nbsp;&nbsp;&nbsp;〕</span> <span>号</span></span>
        </p>
        <p class="MsoNormal" style="text-align:left;margin-top:30px">
            <span style="vertical-align: bottom;"><span>被处罚单位：</span></span>
            <u><span style="text-align:center;vertical-align: text-bottom;border-bottom:1px solid #333;width:81%;display:inline-block;" contenteditable="true">&nbsp;${company.name }</span></u>
        </p>
        <p class="MsoNormal" style="text-align:left;">
            <span style="vertical-align: bottom;"><span>地址：</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:60%;display:inline-block;" contenteditable="true">&nbsp;${company.address }</span></u>
            <span style="vertical-align: bottom;"><span>邮政编码：</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:14%;display:inline-block;" contenteditable="true">&nbsp;${company.postage }</span></u>
        </p>
        <p class="MsoNormal" style="text-align:left;">
            <span style="vertical-align: bottom;"><span>法定代表人（负责人）：</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:12%;display:inline-block;" contenteditable="true">&nbsp;${company.legal }</span></u>
            <span style="vertical-align: bottom;"><span>职务：</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:12%;display:inline-block;" contenteditable="true">&nbsp;</span></u>
            <span style="vertical-align: bottom;"><span>联系电话：</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:17%;display:inline-block;" contenteditable="true">&nbsp;${company.legalContact }</span></u>
        </p>
        <p class="MsoNormal" style="text-indent:23.0000pt;text-align:left;">
            <span style="vertical-align: bottom;"><span>违法事实及证据：</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:70%;display:inline-block;text-indent: 0;" contenteditable="true">&nbsp;</span></u>
        </p>
        <p class="MsoNormal" style="width:100%;border-bottom:1px solid #333;min-height: 23px;" contenteditable="true">&nbsp;</p>
        <p class="MsoNormal" style="width:100%;border-bottom:1px solid #333;min-height: 23px;" contenteditable="true">&nbsp;</p>
        <p class="MsoNormal" style="text-align:left;">
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:65%;display:inline-block;" contenteditable="true">&nbsp;</span></u>
            <span style="vertical-align: bottom;"><span>（此栏不够，可另附页）</span></span>
        </p>
        
        <p class="MsoNormal" style="text-indent:23.0000pt;text-align:left;">
            <span style="vertical-align: bottom;"><span>以上行为违反了</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:74%;display:inline-block;text-indent: 0;" contenteditable="true">&nbsp;</span></u>
        </p>
        <p class="MsoNormal" style="text-align:left;">
            <span style="vertical-align: bottom;"><span>的规定，依据</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:65%;display:inline-block;" contenteditable="true">&nbsp;</span></u>
            <span style="vertical-align: bottom;"><span>的规定，决定</span></span>
        </p>
        <p class="MsoNormal" style="text-align:left;">
            <span style="vertical-align: bottom;"><span>给予</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:75%;display:inline-block;" contenteditable="true">&nbsp;</span></u>
            <span style="vertical-align: bottom;"><span>的行政处罚。</span></span>
        </p>
        <p class="MsoNormal" style="text-indent:23.0000pt;text-align:left;">
            <span style=""><span>罚款的履行方式和期限（见打√处）：</span></span>
        </p>
        <p class="MsoNormal" style="text-indent:23.0000pt;text-align:left;">
            <span style="position: relative;"><i class="gouw">&nbsp;</i><span style="margin-left:15px;">当场缴纳</span></span>
        </p>
        <p class="MsoNormal" style="text-indent:23.0000pt;text-align:left;">
            <span style="position: relative;"><i class="gouw">&nbsp;</i><span style="margin-left:15px;vertical-align: bottom;">自收到本决定书之日起15日内缴至 </span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:46%;display:inline-block;text-indent: 0;" contenteditable="true">&nbsp;</span></u>
            <span style="vertical-align: bottom;"><span>， </span></span>
        </p>
        <p class="MsoNormal" style="text-align:left;">
            <span style="vertical-align: bottom;"><span>账号</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:41%;display:inline-block;" contenteditable="true">&nbsp;</span></u>
            <span style="vertical-align: bottom;"><span>，到期不缴每日按罚款数额的3%加处罚款。</span></span>
        </p>
        <p class="MsoNormal" style="text-indent:23.0000pt;text-align:left;">
            <span style="vertical-align: bottom;"><span>如果不服本处罚决定，可以依法在60日内向</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:35%;display:inline-block;text-indent: 0;" contenteditable="true">&nbsp;</span></u>
            <span style="vertical-align: bottom;"><span>人民</span></span>
        </p>
        <p class="MsoNormal" style="text-align:left;">
            <span style="vertical-align: bottom;"><span>政府或者</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:45%;display:inline-block;" contenteditable="true">&nbsp;</span></u>
            <span style="vertical-align: bottom;"><span>申请行政复议，或者在6个月内依法</span></span>
        </p>
        <p class="MsoNormal" style="text-align:left;">
            <span style="vertical-align: bottom;"><span>向</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:28%;display:inline-block;" contenteditable="true">&nbsp;</span></u>
            <span style="vertical-align: bottom;"><span>人民法院提起行政诉讼，但本决定不停止执行，法律另有</span></span>
        </p>
        <p class="MsoNormal" style="text-align:left;line-height:30px;">
          <span style=""><span>规定的除外。逾期不申请行政复议、不提起行政诉讼又不履行的，本机关将依法申请人民法院强制执行或者依照有关规定强制执行。</span></span>
        </p>
        <p class="MsoNormal" style="text-align:left;">
            <span style="vertical-align: bottom;"><span>安全生产监管行政执法人员（签名）：</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:20%;display:inline-block;" contenteditable="true">&nbsp;</span></u>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:20%;display:inline-block;margin-left:10px" contenteditable="true">&nbsp;</span></u>
        </p>
        <p class="MsoNormal" style="text-align:left;">
            <span style="vertical-align: bottom;"><span>当事人或者委托代理人（签名）：</span></span>
            <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom;width:20%;display:inline-block;" contenteditable="true">&nbsp;</span></u>
        </p>
        <p class="MsoNormal" style="text-align:right;margin-top:40px">
            <span style=""><span>安全生产监督管理部门（印章）</span></span><span style=""></span>
        </p>
        <p class="MsoNormal" style="text-align:right">
            <span style=""><span contenteditable="true">&nbsp;<fmt:formatDate value="${timenow }" pattern="yyyy"/>&nbsp;</span>年<span contenteditable="true">&nbsp;&nbsp;<fmt:formatDate value="${timenow }" pattern="MM"/>&nbsp;</span>月<span contenteditable="true">&nbsp;&nbsp;<fmt:formatDate value="${timenow }" pattern="dd"/>&nbsp;</span>日</span><span style=""></span>
        </p>
        <p class="MsoNormal" style="border-top:1px solid #000;">
            <span style=""><span>本文书一式两份：一份由安全生产监督管理部门备案，一份交被处罚单位。</span> </span><span style=""></span>
        </p>
        </div>
    </div>
</body>

</html>