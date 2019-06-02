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
<%@ include file="/WEB-INF/inc/print.inc"%>
<link rel="stylesheet" type="text/css" media="print" href="${ly}/js/H-ui.admin_v3.0/static/h-ui/css/H-ui.css" />
<link rel="stylesheet" type="text/css" href="${ly}/styles/common.css" media="print"/>
<style type="text/css" media="all">
body,html{}
body .btn-page{padding:0 20px;margin:0 5px;}
.contents{display:none;margin-top:20px;}
#contents1{display:block;}
.page-container{min-width:1300px;min-height:600px}
.left-editor,.right-editor{min-height:600px;}
body .div-edi{box-shadow: 0px 0 0 #B6B8C2;}
body .check-box{padding-left:0;margin-bottom:7px;padding-right:0;width:23%}
</style>
<script type="text/javascript">
$(function() {
});

function pr_() {
  $("#plan0").jqprint();
}

function saveTextz1() {
  $.post("/company/safety-system/plan-save", {
    "text0" : $("#plan0").html()
  }, function(result) {
  })
}

</script> 
</head>
<body>
  <div class="page-container" style="padding:0;min-width:1000px">
    <div class="right-editor" style="width:90%;margin-left:5%;">
      <div class="div-edi">
        <!-- 展示效果模块 -->
        <div class="show-edi">
          <div class="text-r w-edi mt-20">
            <button class="btn btn-primary radius" type="button" onclick="pr_()">
              <i class="Hui-iconfont">&#xe652;</i>打印
            </button>
          </div>
          <div id="plan0" style="width:210mm;margin:auto">
            <c:choose>
                <c:when test="${empty im.text0}">
                  <div class="detail-plan mt-20">
                    <h1 style="text-align:center">
                        <span style=";font-family:宋体;font-weight:normal;font-size:19px"><span style="font-family:宋体">颁</span></span><span style=";font-family:宋体;font-weight:normal;font-size:19px">&nbsp;</span><span style=";font-family:宋体;font-weight:normal;font-size:19px"><span style="font-family:宋体">布</span></span><span style=";font-family:宋体;font-weight:normal;font-size:19px">&nbsp;</span><span style=";font-family:宋体;font-weight:normal;font-size:19px"><span style="font-family:宋体">令</span></span>
                    </h1>
                    <p>
                        <span style=";font-family:宋体;font-size:19px">&nbsp;</span>
                    </p>
                    <p>
                        <span style=";font-family:宋体;font-size:19px"><span style="font-family:宋体">各部门：</span></span>
                    </p>
                    <p style="text-indent:37px">
                        <span style=";font-family:宋体;font-size:19px"><span style="font-family:宋体">根据《中共中央、国务院关于推荐安全生产领域改革发展的意见》（中发【</span></span><span style=";font-family:宋体;font-size:19px">2016】32号）、《国务院安委会办公室关于实施遏制重特大事故工作指南，构建双重预防机制的意见》（安委办【2016】11号）、《省安委办公室关于印发江苏省遏制重特大事故构建双重预防机制实施办法的通知》（苏安办【2016】103号）的规定，按照国家相关标准规范，结合办单位实际，编制了《风险分级管控、隐患排查治理》（以下简称“双控机制”管理体系文件，经公司安全生产领导小组审议并通过，现予颁布，并于颁布之日起生效</span><span style=";font-family:宋体;font-size:19px"><span style="font-family:宋体">。</span></span>
                    </p>
                    <p style="text-indent:37px">
                        <span style=";font-family:宋体;font-size:19px">&nbsp;</span>
                    </p>
                    <p style="text-indent:37px">
                        <span style=";font-family:宋体;font-size:19px">&nbsp;</span>
                    </p>
                    <p style="text-indent:37px">
                        <span style=";font-family:宋体;font-size:19px">&nbsp;</span>
                    </p>
                    <p style="text-indent:37px">
                        <span style=";font-family:宋体;font-size:19px">&nbsp;</span>
                    </p>
                    <p style="text-align:right;">
                        <span style=";font-family:宋体;font-size:19px"><strong>${company.name }</strong></span>
                    </p>
                    <p style="text-align:right;">
                        <span style=";font-family:宋体;font-size:19px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-family:宋体">总经理：${company.legal }</span></span>
                    </p>
                    <p style="text-align:right;">
                        <span style=";font-family:宋体;font-size:19px" contenteditable="true" onblur="saveTextz1()"><fmt:formatDate value="${now }" pattern="yyyy年MM月dd日"/></span>
                    </p>
                    <p>
                        <br/>
                    </p>
                  </div>
                
                </c:when>
                <c:otherwise>
                ${im.text0 }
                </c:otherwise>
            </c:choose>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>