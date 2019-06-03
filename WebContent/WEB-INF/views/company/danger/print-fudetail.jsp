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
function pr_(x) {
  $("#" + x).jqprint();
}
</script>
</head>
<body>
<div class="page-container">
  <div  style="width:150mm;margin:auto;">
  <!-- 循环表 -->
  
  <c:forEach items="${recheckList }" var="p" varStatus="index">
  <div class="row cl" style="margin-top:20px;">
    <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2  mt-20">
      <button onClick="pr_('div_container${index.index}')" class="btn btn-success radius ml-20" type="button" style="padding: 0 70px;">
        <i class="Hui-iconfont mr-10">&#xe652;</i>打印
      </button>
    </div>
  </div>
  <div id="div_container${index.index }" style="padding:50px 0;">
  <div class="div_pdetail mt-10">
    <font>检查${check.flag == 1 ? '部门' : '单位'}：${check.companyName }</font>
    <label>检查日期：<fmt:formatDate value="${check.realTime }" pattern="yyyy年MM月dd日"/></label>
  </div>
  <div class="f-l mt-20 mb-10" style="width:100%">
    <table class="f-l table table-border table-bordered table-bg table-hover table-sort">
      <thead>
        <tr class="text-c">
          <th width="3%">序号</th>
          <th width="15%">部门/装置</th>
          <th width="25%">检查项目</th>
          <th width="30%">隐患内容</th>
          <th width="15%">整改期限</th>
          <th width="12%">检查结果</th>
        </tr>
      </thead>
      <tbody>
        <c:set value="0" var="ind"/>
        <c:forEach items="${itemList }" var="be">
        <c:if test="${be.recheckId == p.id}">
        <c:set value="${ind + 1}" var="ind"/>
        <tr>
          <td class="text-c">${ind }</td>
          <td class="text-c"><c:if test="${!empty be.partImg}">
            <img src="${be.partImg }" class="mr-10" style="height:100px;"/>
            </c:if>${be.partName }</td>
          <td class="text-c">${be.levels }</td>
          <td class="text-c">${be.memo }</td>
          <td class="text-c"><fmt:formatDate value="${be.deadline }"/></td>
          <td class="text-c">
            ${be.status == 2 ? '未整改' : '已整改'}
          </td>
        </tr>
        </c:if>
        </c:forEach>
        <tr>
          <td colspan="6">
            <div class="div_pri  mt-10">针对上次隐患整改要求，经复查，提出如下复查意见：</div>
            <div class="div_pri  mt-10">1、已整改项：基本符合安全要求</div>
            <div class="div_pri">
             <!-- 循环 -->
             <c:set value="0" var="ind"/>
             <c:forEach items="${itemList }" var="be">
              <c:if test="${be.recheckId == p.id}">
              <c:set value="${ind + 1}" var="ind"/>
              <div class="check-box">
                <i class="img_check <c:if test="${be.status == 3}"> img_checkxz</c:if>">&nbsp;</i>
                <label>${ind }</label>
              </div>
              </c:if>
             </c:forEach>
              <!-- 循环结束 -->
            </div>
            <div class="div_pri  mt-10">2、未整改项：</div>
            <div class="div_pri">
             <!-- 循环 -->
              <c:set value="0" var="ind"/>
             <c:forEach items="${itemList }" var="be">
              <c:if test="${be.recheckId == p.id}">
              <c:set value="${ind + 1}" var="ind"/>
              <div class="check-box">
                <i class="img_check <c:if test="${be.status == 2}"> img_checkxz</c:if>">&nbsp;</i>
                <label>${ind }</label>
              </div>
              </c:if>
             </c:forEach>
              <!-- 循环结束 -->
            </div>
            <div class="div_pri  mb-10  mt-10">
              <font style="float:Left;">3、对未整改项要求于</font>
              <font style="float:Left;width:200px;text-align:center;"><fmt:formatDate value="${p.nextTime }" pattern="yyyy-MM-dd"/></font>
              <font style="float:Left;">整改完毕</font>
            </div>
          </td>
        </tr>
        <tr>
          <td colspan="3">
            <div style="float:left;width:100%;position: relative;min-height:150px;">
            <c:if test="${check.flag == 3 && check.checkCompany eq '无锡市安泰安全技术服务有限公司'}">
            <img alt="" src="${ly }/images/zhang.png" style="position: absolute;bottom:10px;right:10px;height:130px;"/>
            </c:if>
            <c:if test="${check.flag > 1}">
            <div class="div_pleft  mt-10 mb-10">检查部门/单位：</div>
            <div class="div_pright  mt-10 mb-10">
              <input type="text" style="width:150px" id="checkCompany" value="${check.checkCompany }" class="input-text" maxlength="50" readonly="readonly"/>
            </div><br/>
            </c:if>
            <div class="div_pleft  mt-10 mb-10">检查人员签字：</div>
            <div class="div_pright  mt-10 mb-10">
              <input type="text" style="width:150px;border:0;" value="${p.checker }" class="input-text" maxlength="50" readonly="readonly"/>
            </div>
            <div class="div_pri mt-10">
              <font><fmt:formatDate value="${p.createTime }" pattern="yyyy年MM月dd日"/></font>
            </div>
            </div>
          </td>
          <td colspan="3">
            <div style="float:left;width:100%;min-height:150px;">
            <div class="div_pleft  mt-10 mb-10">受检负责人签字：</div>
            <div class="div_pright  mt-10 mb-10">
              <input type="text" style="width:150px;border:0;" value="${check.dapartContact }" class="input-text" maxlength="50" readonly="readonly"/>
            </div>
            <div class="div_pri mt-10">
              <font><fmt:formatDate value="${p.createTime }" pattern="yyyy年MM月dd日"/></font>
            </div>
            </div>
          </td>
        </tr>
        
      </tbody>
    </table>
  </div>
  </div>
  </c:forEach>
  <!-- 循环结束 -->
  
  </div>
</div>
</body>
</html>