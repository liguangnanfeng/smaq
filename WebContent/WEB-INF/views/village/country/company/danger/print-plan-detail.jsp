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
<%@ include file="/WEB-INF/inc/print.inc"%>
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style type="text/css">
.div_pri{width:100%;min-height: 32px}
.div_pdetail{width:100%;font-size:15px;}
.div_pri font{float:right;line-height:32px;font-size:14px;margin-right:15px;}
body .radio-box{float:Left;}
body .radio-box{padding-left:0px;padding-right:0;} 
.div_pleft{width:150px;display: inline-block;line-height:32px;font-size:9pt;}
.div_pdetail font{font-size:10pt;margin-left:15px;font-weight:500;}
body .page-container .div_pdetail label{float:right;font-size:10pt;margin-right:130px;}
body th,body td,body .page-container label{font-size:9pt}
body .table-bordered,body .table-bordered th,body .table-bordered td{border:1px solid #000}
body .table-border.table-bordered{border-bottom:1px solid #000;border-collapse: collapse;}
body .table-bg thead th{background:#ddd;padding:20px 8px}
</style>
<script src="${ly }/js/jquery-migrate-1.2.1.min.js"></script>
<script src="${ly }/js/jquery.jqprint-0.3.js"></script>
<script type="text/javascript">
function pr_() {
  $("#div_container").jqprint();
}
</script>
</head>
<body>
<div class="page-container">
  <div id="div_container" style="width:150mm;margin:auto;padding:50px 0;">
  <h2 class="text-c mb-20" style="font-size:14pt">${check.title }</h2>
  <div class="div_pdetail mt-20">
      <font>检查部门：${check.depart }</font>
      <label>检查日期：<fmt:formatDate value="${check.realTime }" pattern="yyyy年MM月dd日"/>
        <c:if test="${empty check.realTime}">&nbsp;&nbsp;年&nbsp;月&nbsp;日</c:if>
      </label>
    </div>
  <div class="mt-20" style="width:100%">
    <table class="f-l table table-border table-bordered table-bg table-hover table-sort">
      <thead>
        <tr class="text-c">
          <th width="3%">序号</th>
          <th width="15%">检查部位</th>
          <th width="30%">检查项目</th>
          <th width="40%">检查内容</th>
          <th width="12%">检查结果</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${partL }" var="part" varStatus="pi">
        <c:set var="x" value="0"/>
        <c:forEach items="${itemL }" var="ch">
          <c:if test="${ch.partId == part.id}">
            <c:set var="x" value="${x + 1}"/>
          </c:if>
        </c:forEach>
        <tr>
          <td class="text-c" rowspan="${x }">${pi.index + 1}</td>
          <td class="text-c" rowspan="${x }">${part.name }</td>
          <c:set var="y" value="0"/>
          <c:forEach items="${itemL }" var="ch">
          <c:if test="${ch.partId == part.id}">
          <c:if test="${y > 0}">
          <tr>
          </c:if>
          <td class="text-l">${ch.levels }</td>
          <td class="text-l">${ch.content }</td>
          <td class="text-c">&nbsp;
            <!-- 判断不合格显示 -->
            <c:if test="${ch.status == 1}">
            <font>合格</font>
            </c:if>
            <c:if test="${ch.status > 1}">
            <font>不合格</font>
            </c:if>
          </td>
          <c:if test="${y > 0}">
          </tr>
          </c:if>
          <c:set var="y" value="1"/>
          </c:if>
          </c:forEach>
        </tr>
        </c:forEach>
        <tr>
          <td colspan="3">
            <div style="float:left;width:100%;position: relative;min-height:150px;">
            <c:if test="${check.flag == 3 && check.checkCompany eq '无锡市安泰安全技术服务有限公司'}">
            <img alt="" src="${ly }/images/zhang.png" style="position: absolute;bottom:10px;right:10px;height:130px;"/>
            </c:if>
            <c:if test="${check.flag > 1}">
            <div class="div_pleft  mt-10 mb-10">检查部门/单位：</div>
            <div class="div_pright  mt-10 mb-10">
              <input type="text" style="width:150px" id="checkCompany" value="${check.checkCompany }" class="input-text" maxlength="50" disabled="disabled"/>
            </div><br/>
            </c:if>
            <div class="div_pleft  mt-10 mb-10">检查人员签字：</div>
            <div class="div_pright  mt-10 mb-10">
              <input type="text" style="width:150px" id="cheker" value="${check.cheker }" class="input-text" maxlength="50" disabled="disabled"/>
            </div>
            </div>
          </td>
          <td colspan="3">
          <div style="float:left;width:100%;min-height:150px;">
            <div class="div_pleft  mt-10 mb-10">受检负责人签字：</div>
            <div class="div_pright  mt-10 mb-10">
              <input type="text" style="width:150px" value="${check.dapartContact }" class="input-text" maxlength="50" disabled="disabled"/>
            </div>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
  </div>
  <div class="row cl">
    <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2  mt-20">
      <button onClick="pr_()" class="btn btn-success radius ml-20" type="button" style="padding: 0 70px;">
         <i class="Hui-iconfont mr-10">&#xe652;</i>打印 
      </button>
    </div>
  </div>
</div>

</body>
</html>