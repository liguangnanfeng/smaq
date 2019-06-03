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
<%@ include file="/WEB-INF/inc/back-header.inc"%>
<link rel="stylesheet" type="text/css" href="${ly}/styles/print-tab.css"/>
<link rel="stylesheet" type="text/css" href="${ly}/styles/print-tab.css" media="print" />
<link rel="stylesheet" type="text/css" media="print"  href="${ly}/js/H-ui.admin_v3.0/static/h-ui/css/H-ui.css" />
<script type="text/javascript" src="${ly}/js/H-ui.admin_v3.0/lib/layer/2.4/layer.js"></script> 
<style type="text/css" media="print">
.div-print{width:98%;margin:auto;padding:30px 0 0 0;}
body .dis-ib{margin-right:15px;}
</style>
<style type="text/css">
.div-print{width:250mm;margin:auto;padding:30px 0 0 0;}
body .dis-ib{margin-right:15px;}
</style>
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<script type="text/javascript">
function pr_() {
  $("#div_container").jqprint();
}
</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>重大危险源源长制</span> 
    <span class="c-gray en">&gt;</span> <span>管控措施</span> 
    <span class="c-gray en">&gt;</span> <span>公示</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="parent.close_tab(function(){})">返回</a>
  </nav>
  <div class="page-container">
    <div class="mt-20">
      <div class="div-set">
        <div class="top-set">
          <font>${be.name }重大危险源公示牌</font>
          <a class="btn_rbj" style="right:186px;" onClick="show_dialog('编辑风险公告牌', '/company/safety-system/risk-information-add?id=${be.id}')" href="javascript:;" title="编辑">编辑</a>
          <a class="btn_rbj" style="right:96px;"  onClick="pr_()" href="javascript:;" title="打印公告牌">打印公告牌</a>
          <a class="btn_rbj" onClick="del(${be.id})" href="javascript:;" title="重新生成">重新生成</a>
        </div>
        <div class="div-print" id="div_container">
          <h3 class="text-c" style="padding-top:0">${be.name }重大危险源公示牌</h3>
          <div class="main-gao">
            <table class="tab-gao" >
              <tr>
                <td  rowspan="2" class="text-c" style="width:25%;border:1px solid #BEBEBE">
                  <div class="div-gaoz" style="border:0px;">
                    <b style="font-size:14px">风险等级：</b>
                    <div class="div-xz">
                      <div class="check-box">
                        <label>红</label>
                        <img alt="" src="/images/fore/gx1.png"/>
                      </div>
                      <div class="check-box">
                        <label>橙</label>
                        <img alt="" src="/images/fore/wgx.png"/>
                      </div>
                    </div>
                    <div class="div-xz">
                      <div class="check-box">
                        <label>黄</label>
                        <img alt="" src="/images/fore/wgx.png"/>
                      </div>
                      <div class="check-box">
                        <label>蓝</label>
                        <img alt="" src="/images/fore/wgx.png"/>
                      </div>
                    </div>
                  </div>
                </td>
                <td style="border:1px solid #BEBEBE;vertical-align: top;background:red">
                  <p style="text-align:center;font-size:14px;color:#fff">重大危险源</p>
                </td>
                <td style="border:1px solid #BEBEBE;vertical-align: top;background:red">
                  <p style="text-align:center;font-size:14px;color:#fff">风险种类</p>
                </td>
              </tr>
              <tr>
                <td style="border:1px solid #BEBEBE;vertical-align: top;">
                  <p>${equipList }</p>
                </td>
                <td colspan="1" style="border:1px solid #BEBEBE;vertical-align: top;">
                  <p>${be.sglx }</p>
                </td>
              </tr>
              <tr style="border:1px solid #BEBEBE">
                <td colspan="3" class="td-gao" style="border:1px solid #BEBEBE;vertical-align: top;">
                  <b style="font-size:14px;text-align:center;width:100%;display:inline-block;padding:5px 0;background:#0F40F5;color:#fff">应急处置措施</b>
                  <!-- 循环 -->
                  <p>${be.yjczcs }</p>
                </td>
              </tr>
              <%-- <tr>
                <td rowspan="3" class="td-gao">
                  <p>${be.sglx }</p>
                </td>
                <td  rowspan="3" class="td-gao">
                  <c:if test="${! empty be.fxysZd}">
                    <p><b style="font-size:14px;color:#FF0000">重大：</b>${be.fxysZd }</p>
                  </c:if>
                  <c:if test="${! empty be.fxys}">
                    <p><b style="font-size:14px;color:#FFA500">较大：</b>${be.fxys }</p>
                  </c:if>
                </td>
              </tr>
              <tr>
                
              </tr>
              <tr>
                
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
              </tr> --%>
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
                <td>
                  <div class="div-gaoz" style="padding:2px 10px;">
                    <b style="font-size:14px">管理责任人：</b>
                    <p>${be.glzrr }</p>
                  </div>
                </td>
                <td>
                  <div class="div-gaoz" style="padding:2px 10px;">
                    <b style="font-size:14px">安全部电话</b>
                    <p>火警电话：119&emsp;急救电话：120</p>
                  </div>
                </td>
                <td>
                  <div class="div-gaoz" style="padding:0;">
                    <div class="xx-gaoz">重要<br>提示</div>
                    <p class="xx-gaoz1" style="width:auto">安全是一种责任、为己为家为他人</p>
                  </div>
                </td>
              </tr>
              <tr>
                <td></td>
                <td style="width:30%"></td>
                <td></td>
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