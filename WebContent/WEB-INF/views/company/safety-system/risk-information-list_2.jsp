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
<link rel="stylesheet" type="text/css" href="${ly}/styles/print-tab.css"/>
<link rel="stylesheet" type="text/css" href="${ly}/styles/print-tab.css" media="print" />
<link rel="stylesheet" type="text/css" media="print"  href="${ly}/js/H-ui.admin_v3.0/static/h-ui/css/H-ui.css" />
<script type="text/javascript" src="${ly}/js/H-ui.admin_v3.0/lib/layer/2.4/layer.js"></script> 
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style type="text/css" media="print">
.div-print{width:98%;margin:auto;padding:30px 0 0 0;}
body .dis-ib{margin-right:15px;}
</style>
<style type="text/css">
.div-print{width:60%;margin:auto;padding:30px 0 0 0;}
body .dis-ib{margin-right:15px;}
</style>
<script type="text/javascript">
function load_() {
  $(".mt-20").html("加载中").load("/company/safety-system/fxggp-load .div-set", {
    name : $("#name").val()
  })
  
  $("#name").change(function() {
    load_();
  })
}
$(function() {
  load_();
})
function pr_() {
  $("#div_container").jqprint();
}
</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>风险分级管控</span> 
    <span class="c-gray en">&gt;</span> <span>公告警示</span> 
    <span class="c-gray en">&gt;</span> <span>车间风险公告牌</span> 
    <%--<a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="parent.close_tab(function(){})">返回</a>--%>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;" href="javascript:history.back(-1)" title="返回">返回</a>
  </nav>
  <div class="page-container">
    <div class="text-l">
      <div class="dis-ib">
        <span>风险名称：</span>
        <select id="name" class="sel_area">
          <c:forEach items="${nameL }" var="be">
          <option>${be }</option>
          </c:forEach>
        </select>
      </div> 
    </div>
    <div class="mt-20">
      <!-- 加载进来 -->
       <div class="div-set">
        <div class="top-set">
          <font>安全风险公告牌</font>
          <a class="btn_rbj" style="right:146px;" onClick="show_dialog('编辑风险公告牌', '/company/safety-system/risk-information-add?id=${be.id}&flag=${flag }')" href="javascript:;" title="编辑">编辑</a>
          <a class="btn_rbj" style="right:56px;"  onClick="show_dialog('打印公告牌', '/company/safety-system/risk-information-detail?id=${be.id}')" href="javascript:;" title="打印公告牌">打印公告牌</a>
          <c:if test="${be.isedit == 1}">
            <a class="btn_rbj" onClick="re_()" href="javascript:;" title="重新生成">重新生成</a>
          </c:if>
          <a class="btn_rbj" onClick="del(${be.id})" href="javascript:;" title="删除">删除</a>
        </div>
        <div class="div-print">
          <h3 class="text-c" style="padding-top:0">${be.name }安全风险公告牌</h3>
          <div class="main-gao">
            <table class="tab-gao" >
              <tr>
                <td class="text-c" rowspan="2" style="width:30%">
                  <div class="div-gaoz">
                    <b>风险名称：</b>
                    <p><b>${be.name }</b></p>
                  </div>
                </td>
                <td class="text-c tcol-red" style="width:30%">事故类型</td>
                <td class="text-c tcol-red" style="width:40%">风险因素</td>
              </tr>
              <tr>
                <td rowspan="3" class="td-gao">
                  <p>${be.sglx }</p>
                </td>
                <td  rowspan="3" class="td-gao">
                <p>${be.fxys }</p>
                </td>
              </tr>
              <tr>
                <td class="text-c">
                  <div class="div-gaoz">
                    <b>风险等级：</b>
                    <div class="div-xz">
                      <div class="check-box">
                        <label>红</label>
                        <img alt="" src="/images/fore/${fn:contains(be.fxdj, '红') ? 'gx.png' : 'wgx.png'}"/>
                      </div>
                      <div class="check-box">
                        <label>橙</label>
                        <img alt="" src="/images/fore/${fn:contains(be.fxdj, '橙') ? 'gx.png' : 'wgx.png'}"/>
                      </div>
                    </div>
                    <div class="div-xz">
                      <div class="check-box">
                        <label>黄</label>
                        <img alt="" src="/images/fore/${fn:contains(be.fxdj, '黄') ? 'gx.png' : 'wgx.png'}"/>
                      </div>
                      <div class="check-box">
                        <label>蓝</label>
                        <img alt="" src="/images/fore/${fn:contains(be.fxdj, '蓝') ? 'gx.png' : 'wgx.png'}"/>
                      </div>
                    </div>
                  </div>
                </td>
              </tr>
              <tr>
                <td class="text-c">
                  <div class="div-gaoz" style="margin-bottom:0;">
                    <b>管理责任人：</b>
                    <p>${be.glzrr }</p>
                  </div>
                </td>
              </tr>
              <tr><td colspan="3" style="height:5px"></td></tr>
              <tr>
                <td colspan="3" class="text-c tcol-blue">应急防范措施</td>
              </tr>
              <tr>
                <td colspan="3" class="td-gao">
                  <!-- 循环 -->
                  <p>${be.yjczcs }</p>
                </td>
              </tr>
              <tr>
                <td colspan="3">
                  <div class="div-gimg" style="margin-bottom:6px;">
                    <img alt="" src="${ly }/images/gao/aqgz1.jpg"/>
                    <img alt="" src="${ly }/images/gao/aqgz2.jpg"/>
                    <img alt="" src="${ly }/images/gao/aqgz3.jpg"/>
                    <img alt="" src="${ly }/images/gao/aqgz4.jpg"/>
                    <img alt="" src="${ly }/images/gao/aqgz6.jpg"/>
                    <img alt="" src="${ly }/images/gao/l1.jpg"/>
                    <img alt="" src="${ly }/images/gao/l2.jpg"/>
                    <img alt="" src="${ly }/images/gao/l3.jpg"/>
                    <img alt="" src="${ly }/images/gao/l4.jpg" style="margin-right:0;"/>
                  </div>
                </td>
              </tr>
              <tr>
                <td colspan="2">
                  <div class="div-gaoz" style="padding:0;">
                    <div class="xx-gaoz">重要<br>提示</div>
                    <font class="xx-gaoz1">安全是一种责任、为己为家为他人777777777</font>
                  </div>
                </td>
                <td>
                  <div class="div-gaoz" style="margin-bottom:0;padding:5px 10px;">
                    <b>安全部电话</b>
                    <font>火警电话：119&emsp;急救电话：120</font>
                  </div>
                </td>
              </tr>
              <tr>
              </tr>
            </table>
          </div>
        </div> 
      </div>
    </div>
  </div>
</body>
<script type="text/javascript">
function del(id) {
  $.post("/company/safety-system/risk-information-save", {
    id : id,
    del : 1
  },function(result) {
    location.reload();
  })
}
</script>
</html>