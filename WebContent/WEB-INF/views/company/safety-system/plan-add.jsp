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
<%-- <%@ include file="/WEB-INF/inc/back-header.inc"%> --%>
<%@ include file="/WEB-INF/inc/print.inc"%>
<%@ include file="/WEB-INF/inc/ueditor.inc"%>
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<link rel="stylesheet" type="text/css" media="print"  href="${ly}/js/H-ui.admin_v3.0/static/h-ui/css/H-ui.css" />
<script src="${ly }/js/jquery-migrate-1.2.1.min.js"></script>
<script src="${ly }/js/jquery.jqprint-0.3.js"></script>
<script type="text/javascript" src="${ly}/js/H-ui.admin_v3.0/lib/layer/2.4/layer.js"></script> 
<style type="text/css" media="all">
body,html{}
body .btn-page{padding:0 20px;margin:0 5px;}
.contents{display:none;margin-top:20px;}
#contents1{display:block;}
.page-container{min-width:1300px;min-height:600px}
.left-editor,.right-editor{min-height:600px;}
body .div-edi{box-shadow: 0px 0 0 #B6B8C2;}
body .check-box{padding-left:0;margin-bottom:7px;padding-right:0;width:23%}
.page2 
</style>
<script type="text/javascript">
var im = ${im};
var type = ${type};
var e1;
var e2;
$(function() {
  /* 固定高度 */
  /* $(".left-editor,.right-editor").height($(window).height()-$(".layui-layer-title").height());
  $(".left-editor").css("padding","30px 10px 50px 10px");
  $(".right-editor").css("padding","30px 0 0 0"); */
  /* 富文本 及高度*/
  e1 = UE.getEditor('contents1',{
    scaleEnabled:true,
  });
  e2 = UE.getEditor('contents2');
  e2.ready(function(){
    e2.setHeight($(window).height()-200);
       });
  e1.ready(function(){
    e1.setHeight($(window).height()-200);
       });
  
  if(type == 2) {
    $(".page1").hide();
    $(".page2").show();
    $("#contents2").css("display","block");
  }else{
    $(".page2").hide();
    $(".page1").show();
  }
  
  
  /* 目录选择效果 */
  $(".bt-edi").click(function(){
    $(".bt-edi").removeClass("edixz");
    $(this).addClass("edixz");
    type = $(this).data("t");
    setContent();
  })
  
  e1.ready(function() {
    setContent();
  });
  e2.ready(function() {
    setContent();
  });
});

function pr_() {
  $("#cont").jqprint();
}
function setContent() {
  var c = eval("im.text" + type);
  if(!c || c == '') {
    $.ajax({
      url : '/html/editor/editor' + type + ".html",
      type:"GET",
      dataType:"html",
      success:function(result){
          e1.setContent(result);
          e2.setContent(result);
          $("#cont").html(result);
      }
    
    });
  } else {
    e1.setContent(c);
    e2.setContent(c);
    $("#cont").html(c);
  }
}

/* 点击编辑展示富文本 */
function shownr(e){
  $(e).closest(".show-edi").hide();
  $(e).closest(".div-edi").find(".edi-nr").show();
}

/* 点击保存展示效果 */
function save(e){
  var h = e1.getContent();
  var h2 = e2.getContent();
  if(type == 0) {
    im.text0 = h;
  }
  if(type == 1) {
    im.text1 = h;
  }
  if(type == 2) {
    im.text2 = h2;
  }
  if(type == 3) {
    im.text3 = h;
  }
  if(type == 4) {
    im.text4 = h;
  }
  if(type == 5) {
    im.text5 = h;
  }
  if(type == 6) {
    im.text6 = h;
  }
  if(type == 7) {
	im.text7 = h;
  }
  if(type == 8) {
	im.text8 = h;
  }
	  
  if(type == 2) {
    $.post("/company/safety-system/plan-save", im, function(result) {
      setContent();
    })
  }else{
    $.post("/company/safety-system/plan-save", im, function(result) {
      $(e).closest(".edi-nr").hide();
      $(e).closest(".div-edi").find(".show-edi").show();
      setContent();
    })
  }
  
}
function chosemen(){
  $("#win-add").modal("show");
  }
function pr2_() {
  $("#cont").html(e2.getContent());
  $("#cont table").css("margin","auto");
  $(".div-edi").hide();
  $("#count").show();
  $("#cont").jqprint();
  $("#count").hide();
  $(".div-edi").show();
}
</script> 
</head>
<body>
<div class="page1">
  <div class="page-container" style="padding:0;min-width:1000px">
    <!-- 左侧目录 -->
    <!-- <div class="left-editor">
      <div class="bt-main">
        <font class="bt-edi2">目录</font>
        <a class="bt-edi edixz" data-t="0" href="javascript:;">颁布令</a>
        <a class="bt-edi" data-t="1" href="javascript:;">一、组织机构及职责</a>
        <a class="bt-edi" href="javascript:;" data-t="2">二、《双控机制》运行管理责任制</a>
        <a class="bt-edi" href="javascript:;" data-t="3">三、风险分级管控及作业指导书</a>
        <a class="bt-edi" href="javascript:;" data-t="4">四、隐患排查治理及作业指导书</a>
        <a class="bt-edi" href="javascript:;" data-t="5">五、考核</a>
        <a class="bt-edi" href="javascript:;" data-t="6">六、持续改进</a>
      </div>
    </div> -->
    <!-- 右侧内容 -->
    <div class="right-editor" style="width:90%;margin-left:5%;">
      <div class="div-edi">
        <!-- 展示效果模块 -->
        <div class="show-edi">
          <div class="text-r w-edi mt-20">
            <%-- <c:if test="${type == 1}">
             <font style="float:left;line-height:30px;font-size:15px;font-weight:bold;color:#f40">请确定风险点名称和责任人名称，部门名称</font>
            </c:if> --%>
            <c:if test="${type == 1}">
              <button class="btn btn-primary radius" type="button" onclick="chosemen()">
                <i class="Hui-iconfont">&#xe652;</i>任命成员
              </button>
            </c:if>
            <button class="btn btn-primary radius" type="button" onclick="pr_()">
              <i class="Hui-iconfont">&#xe652;</i>打印
            </button>
            <button class="btn btn-primary radius" type="button" onclick="shownr(this)">
              <i class="Hui-iconfont">&#xe60c;</i>编辑
            </button>
          </div>
          <div class="detail-plan mt-20 w-edi" id="cont"></div>
        </div>
        
        <!-- 富文本模块 -->
        <div class="edi-nr">
          <div class="text-r w-edi mt-20">
            <button class="btn btn-primary radius" type="button" onclick="save(this)">
              <i class="Hui-iconfont">&#xe632;</i>保存
            </button>
          </div>
          <script class="contents w-edi" id="contents1" type="text/plain" style="width:800px;height:400px"></script>
        </div>
        
      </div>
    </div>
  </div>
</div>
<div class="page2">  
  <div class="page-container" style="padding:0;min-width:1000px">
    <div class="right-editor" style="width:90%;margin-left:5%;">
      <div class="detail-plan mt-20 w-edi" id="cont"></div>
      <div class="div-edi">
        <div class="show-edi">
          <div class="text-r w-edi mt-20">
            <button class="btn btn-primary radius" type="button" onclick="pr2_()">
              <i class="Hui-iconfont">&#xe652;</i>打印
            </button>
            <button class="btn btn-primary radius" type="button" onclick="save(this)">
              <i class="Hui-iconfont">&#xe632;</i>保存
            </button>
          </div>
          <script class="contents w-edi" id="contents2" type="text/plain" style="width:800px;height:500px"></script>
        </div>
        
      </div>
    </div>
  </div>
</div>
  <div id="win-add" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog" style="width: 900px">
        <div class="modal-content radius">
          <div class="modal-header">
            <h3 class="modal-title">选择成员</h3>
            <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
          </div>
          <div class="modal-body">
            <div class="row cl">
              <label class="form-label col-xs-4 col-sm-2" style="width: 20%; text-align: right;">选择组长：</label>
              <div class="formControls col-xs-8 col-sm-9" style="width: 80%;">
                <select name="" class="sel_area">
                  <option value="0">请选择公司</option>
                </select>
                <select name="" class="sel_area">
                  <option value="0">请选择部门</option>
                </select>
                <select name="" class="sel_area">
                  <option value="0">请选择科室</option>
                </select>
                <select name="" class="sel_area">
                  <option value="0">请选择成员</option>
                </select>
              </div>
            </div>
            <div class="row cl mt-10">
              <label class="form-label col-xs-4 col-sm-2" style="width: 20%; text-align: right;">选择副组长：</label>
              <div class="formControls col-xs-8 col-sm-9" style="width: 80%;">
                <select name="" class="sel_area">
                  <option value="0">请选择公司</option>
                </select>
                <select name="" class="sel_area">
                  <option value="0">请选择部门</option>
                </select>
                <select name="" class="sel_area">
                  <option value="0">请选择科室</option>
                </select>
                <select name="" class="sel_area">
                  <option value="0">请选择成员</option>
                </select>
              </div>
            </div>
            <div class="row cl mt-10">
              <label class="form-label col-xs-4 col-sm-2" style="width: 20%; text-align: right;">选择成员：</label>
              <div class="formControls col-xs-8 col-sm-9" style="width: 80%;">
                <!-- 循环 -->
                <div class="check-box">
                  <input type="checkbox" value="" name="mem"/>
                  <label>xx</label>
                </div>
                <!-- 循环结束 -->
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button class="btn btn-primary" onclick="save_hxys()">确定</button>
            <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
          </div>
        </div>
      </div>
    </div>
  
  
  
  
</body>
</html>
