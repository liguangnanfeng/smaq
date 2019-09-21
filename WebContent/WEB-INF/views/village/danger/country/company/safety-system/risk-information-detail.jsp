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
<link rel="stylesheet" type="text/css" href="${ly}/styles/print-tab.css"/>
<link rel="stylesheet" type="text/css" href="${ly}/styles/print-tab.css" media="print" />
<link rel="stylesheet" type="text/css" media="print"  href="${ly}/js/H-ui.admin_v3.0/static/h-ui/css/H-ui.css" />
<style type="text/css" media="print">
.div-print{width:98%;margin:auto;padding:30px 0 0 0;}
</style>
<style type="text/css">
.div-print{width:60%;margin:auto;padding:30px 0 0 0;}
</style>
<script type="text/javascript">
function pr_() {
  $("#div_container").jqprint();
}
</script>
</head>
<body>
  <div class="text-c mt-20">
    <button onClick="pr_()" class="btn btn-primary radius" type="button">
        <i class="Hui-iconfont">&#xe652;</i>打印
    </button>
  </div>
  <div class="page-container" id="div_container">
    <div class="div-print">
    <h3 class="text-c" style="padding-top:0">${be.name }安全风险公告牌</h3>
    <div class="main-gao">
            <table class="tab-gao" >
              <tr>
                <td class="text-c" style="width:35%;border:1px solid #BEBEBE">
                  <div class="div-gaoz" style="border:0px;">
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
                <td colspan="2" style="border:1px solid #BEBEBE;vertical-align: top;">
                  <b style="text-align:center;width:100%;display:inline-block;padding:5px 0;background:#FE0002;color:#fff">本企业存在以下风险类型，进入厂区，注意安全</b>
                  <p>${be.sglx }</p>
                </td>
              </tr>
              <tr style="border:1px solid #BEBEBE">
                <td class="text-c" style="border:1px solid #BEBEBE">
                  <div class="div-gaoz" style="margin-bottom:0;border:0;">
                    <b>管理责任人：</b>
                    <p>${be.glzrr }</p>
                  </div>
                </td>
                <td class="td-gao" style="border:1px solid #BEBEBE;vertical-align: top;">
                  <b style="text-align:center;width:100%;display:inline-block;padding:5px 0;background:#0F40F5;color:#fff">应急处置措施</b>
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
                <td >
                  <div class="div-gaoz" style="padding:0;">
                    <div class="xx-gaoz">重要<br>提示</div>
                    <font class="xx-gaoz1">安全是一种责任、为己为家为他人</font>
                  </div>
                </td>
                <td colspan="2">
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
</body>
</html>