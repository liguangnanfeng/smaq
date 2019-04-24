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
.chosi{color:#298BFF;cursor:pointer;}
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

var m1 = '${per[0]}', m2 = '${per[1]}', m3 = '${per[2]}';
function save_hxys() {
  var list = $(":checked");
  var v = [];
  list.each(function() {
    v.push($(this).val());
  })
  
  v = v.join(",");
  $("" + xx + "").text(v);
  if(xx == '.m1') {
    m1 = v;
  }
  if(xx == '.m2') {
    m2 = v;
  }
  if(xx == '.m3') {
    m3 = v;
  }
  saveTextz1();
  $("#win-add").modal("hide");
  $(":checked").prop("checked", false);
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

function saveTextz1() {
  $.post("/company/safety-system/plan-save", {
    "textz1" : m1 + " | " + m2 + " | " + m3 + " | " + $("#date__").html()
  }, function(result) {
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
              <p style="text-indent: 37px; text-align: center; line-height: 2em;">
                  <span style=";font-family:宋体;font-size:19px"><span style="font-family:宋体">任</span></span><span style=";font-family:宋体;font-size:19px">&nbsp;&nbsp;</span><span style=";font-family:宋体;font-size:19px"><span style="font-family:宋体">命</span></span><span style=";font-family:宋体;font-size:19px">&nbsp;&nbsp;</span><span style=";font-family:宋体;font-size:19px"><span style="font-family:宋体">书</span></span>
              </p>
              <p style="text-indent: 37px; line-height: 2em;">
                  <span style=";font-family:宋体;font-size:19px"><span style="font-family:宋体">各部门：</span></span>
              </p>
              <p style="text-indent: 37px; line-height: 2em;">
                  <span style=";font-family:宋体;font-size:19px"><span style="font-family:宋体">为使公司《双控机制》体系有效运行，强化对《双控机制》的管理，现对公司《双控机制》领导小组的组成作出如下任命：</span></span>
              </p>
              <p style="text-indent: 37px; line-height: 2em;">
                  <span style="font-family:宋体;font-size:19px">1、</span><span style="font-family:宋体;font-size:19px"><span style="font-family:宋体">任命</span> <span class="m1">${per[0] }</span><i class="Hui-iconfont chosi" onclick="chosemen('.m1')">&#xe60c;</i><span style="font-family:宋体">为公司《双控机制》领导小组组长</span></span>
              </p>
              <p style="margin-left: 0px; text-indent: 37px; line-height: 2em;">
                  <span style="font-family:宋体;font-size:19px">2、</span><span style="font-family:宋体;font-size:19px"><span style="font-family:宋体">任命</span> <span class="m2">${per[1] }</span><i class="Hui-iconfont chosi" onclick="chosemen('.m2')">&#xe60c;</i><span style="font-family:宋体">为公司《双控机制》领导小组副组长</span></span>
              </p>
              <p style="margin-left: 0px; text-indent: 37px; line-height: 2em;">
                  <span style="font-family:宋体;font-size:19px">3、</span><span style="font-family:宋体;font-size:19px"><span style="font-family:宋体">任命</span> <span class="m3">${per[2] }</span><i class="Hui-iconfont chosi" onclick="chosemen('.m3')">&#xe60c;</i><span style="font-family:宋体">为领导小组成员</span></span>
              </p>
              <p style="text-align: justify; line-height: 2em;">
                  <span style=";font-family:宋体;font-size:19px">&nbsp;</span>
              </p>
              <p style="text-align: justify; line-height: 2em;text-indent: 37px;">
                  <span style=";font-family:宋体;font-size:19px"><span style="font-family:宋体">特此任命</span></span>
              </p>
              <p style="text-align: justify; line-height: 2em;">
                  <span style=";font-family:宋体;font-size:19px">&nbsp;</span>
              </p>
              <p style="text-align:right;">
                  <strong><span style="font-family: 宋体;font-size: 20px"><span style="font-family:宋体">${company.name }</span></span></strong>
              </p>
              <p style="text-align:right;">
                  <span style=";font-family:宋体;font-size:19px" contentEditable="true" onblur="saveTextz1()" id="date__">
                  <c:choose>
                    <c:when test="${fn:length(per) < 4 or empty per[3]}"><fmt:formatDate value="${now }" pattern="yyyy年MM月dd日"/></c:when>
                    <c:otherwise>
                    ${per[3] }
                    </c:otherwise>
                  </c:choose>
                  </span>
              </p>
              <p style="text-align: justify; line-height: 2em;">
                  <span style=";font-family:宋体;font-size:19px"><br/></span>
              </p>
            </div>
          </div>
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
            <c:if test="${empty perL}">
            <!-- 判断没有部门显示 -->
            <div class="row cl">
              <div class="nomem">
                <font>您还未添加成员，立即去&emsp;</font>
                <a class="btn btn-primary radius" href="${ly }/company/system/department-list"><i class="Hui-iconfont">&#xe600;</i>添加成员</a>
              </div>
            </div>
            <!-- 判断结束 -->
            </c:if>
            <table class="table table-border table-bordered table-bg table-hover table-sort" style="float:none;">
              <thead>
                <tr class="text-c">
                  <th style="width:50px;">&nbsp;</th>
                  <th>姓名</th>
                  <th>公司</th>
                  <th>部门</th>
                  <th>班组</th>
                  <th>职务</th>
                </tr>
              </thead>
              <c:forEach items="${perL }" var="be">
              <tr class="text-c">
                <td><input type="checkbox" value="${be.name }"></td>
                <td>${be.name }</td>
                <td>${be.companyName }</td>
                <td>${be.level == 1 ? be.dname : be.dpname}</td>
                <td>${be.level == 1 ? '' : be.dname}</td>
                <td>${be.position }</td>
              </tr>
              </c:forEach>
            </table>
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