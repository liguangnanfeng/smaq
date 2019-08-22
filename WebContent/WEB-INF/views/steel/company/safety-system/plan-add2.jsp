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
<%@ include file="/WEB-INF/inc/ueditor.inc"%>
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
.edi-nr{display:none;}
</style>
<script type="text/javascript">
var e1;
var text1 = '${im.text1}';
$(function() {
  e1 = UE.getEditor('contents1',{
    initialFrameHeight:$(window).height()-200,
    scaleEnabled:true,
  });
  
  if(text1 == '') {
    $("#cont").load("/html/editor/editor1.html");
  } else {
    $("#cont").html(text1);
  }
  
  e1.ready(function() {
    
  });
});

function pr_() {
  $("#plan2").jqprint();
}

/* 点击编辑展示富文本 */
function shownr(e){
  $(e).closest(".show-edi").hide();
  $(e).closest(".div-edi").find(".edi-nr").show();
  
  var h = e1.getContent();
  if(h == '') {
    e1.setContent($("#cont").html());
  }
}

/* 点击保存展示效果 */
function save(e){
  var h = e1.getContent();
  $.post("/company/safety-system/plan-save", {
    "text1" : h
  }, function(result) {
    $(e).closest(".edi-nr").hide();
    $(e).closest(".div-edi").find(".show-edi").show();
    $("#cont").html(h);
  })
}

function chosemen(e){
  window.xx = e;
  $("#win-add").modal("show");
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
            <!-- <font style="float:left;line-height:30px;font-size:15px;font-weight:bold;color:#f40">请确定风险点名称和责任人名称，部门名称</font> -->
            <button class="btn btn-primary radius" type="button" onclick="pr_()">
              <i class="Hui-iconfont">&#xe652;</i>打印
            </button>
            
          </div>
          <div id="plan2" style="width:210mm;margin:auto">
            <div id="textz1">
            <div class="detail-plan mt-20">
              <p style="text-align: center; line-height: 2em;">
                  <strong><span style="font-family: 宋体;font-size: 19px"><span style="font-family:宋体">一、组织机构及职责</span></span></strong>
              </p>
              <p style="text-align: justify; text-indent: 37px; line-height: 2em;">
                  <span style="font-family:宋体;font-size:19px">1、</span><span style="font-family:宋体;font-size:19px"><span style="font-family:宋体">组织机构：为加强</span>“双控”体系建设工作的组织领导，确保取得实效，成立“双控”体系建设工作领导小组，对工作进行全面组织、指导和检查。</span>
              </p>
              <p style="text-indent: 56px; text-align: justify; line-height: 2em;">
                  <span style=";font-family:宋体;font-size:19px"><span style="font-family:宋体">组长：</span><span style="font-family:宋体" class="m1">${per[0] }</span></span>
              </p>
              <p style="text-indent: 56px; text-align: justify; line-height: 2em;">
                  <span style=";font-family:宋体;font-size:19px"><span style="font-family:宋体">副组长：</span><span style="font-family:宋体" class="m2">${per[1] }</span></span>
              </p>
              <p style="text-indent: 56px; text-align: justify; line-height: 2em;">
                  <span style=";font-family:宋体;font-size:19px"><span style="font-family:宋体">组员：</span><span style="font-family:宋体" class="m3">${per[2] }</span></span>
              </p>
            </div>
          </div>
          
          <div class="btn-bj">
            <button class="btn btn-primary radius f-r" type="button" onclick="shownr(this)">
              <i class="Hui-iconfont">&#xe60c;</i>编辑
            </button>
          </div>
            <div class="detail-plan mt-20" id="cont"></div>
          </div>
        </div>
        
        <!-- 富文本模块 -->
        <div class="edi-nr">
          <div class="text-r w-edi mt-20">
            <button class="btn btn-primary radius" type="button" onclick="save(this)">
              <i class="Hui-iconfont">&#xe632;</i>保存
            </button>
          </div>
          <script class="contents w-edi" id="contents1" type="text/plain" style="width:800px;min-height:300px"></script>
        </div>
        
      </div>
    </div>
  </div>
  
</body>
</html>