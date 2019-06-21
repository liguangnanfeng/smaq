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
.input-text{position:relative}
.txtarea_sq{height:100px;width:350px;}
body .check-box, .radio-box{line-height:30px;}
.div_pdetail{width:100%;font-size:15px;}
.div_imgp{float:left;width:90%;margin-left:5%;text-align: center;}
.div_imgp img{max-width:100%}
.div_pright{width:300px;display: inline-block;}
.div_pri{width:100%;min-height: 32px}
.div_pri font{float:right;line-height:32px;font-size:14px;margin-right:15px;}
.div_pri .Wdate{float:right;}
body .select{height:32px;line-height:32px;}
body .radio-box{float:Left;padding-left:0px;padding-right:0}
.div_pleft{width:150px;display: inline-block;line-height:32px;font-size:9pt;}
.div_pdetail font{font-size:10pt;margin-left:15px;font-weight:500;}
body .page-container .div_pdetail label{float:right;font-size:10pt;margin-right:15px;}
body th,body td,body .page-container label{font-size:9pt}
body .table-bordered,body .table-bordered th,body .table-bordered td{border:1px solid #000;}
body .table-border.table-bordered{border-bottom:1px solid #000;border-collapse: collapse;}
body .table-bg thead th{background:#ddd;padding:20px 8px}
</style>
<script type="text/javascript">
function pr_() {
  $("#div_container").jqprint();
}
</script>
</head>
<body>
<nav class="breadcrumb">
  <i class="Hui-iconfont">&#xe67f;</i>
  <span>首页</span>
  <span class="c-gray en">&gt;</span>
  <span>${check.flag!=2 ? '隐患排查治理系统' : '行政执法系统'}</span>
  <span class="c-gray en">&gt;</span>
  <span>复查结果详情</span>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
    <i class="Hui-iconfont">&#xe68f;</i>
  </a>
</nav>
<div class="page-container">
  <div id="div_container" style="width:150mm;margin:auto;padding:50px 0;">
  <h2 class="text-c mb-20" style="font-size:14pt">隐患整改复查意见表</h2>
  <!-- 循环 -->
  <div class="div_pdetail mt-20">
    <font>检查单位：${check.companyName }</font>
    <label>检查日期：<fmt:formatDate value="${check.realTime }" pattern="yyyy年MM月dd日"/></label>
  </div>
  <div class="mt-20 mb-10" style="width:100%">
    <table class="table table-border table-bordered table-bg table-hover table-sort">
      <thead>
        <tr class="text-c">
          <th width="10%">序号</th>
          <th width="15%">部门/装置</th>
          <th width="24%">检查项目</th>
          <th width="21%">隐患内容</th>
          <th width="15%">整改期限</th>
          <th width="15%">检查结果</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${itemL }" var="be" varStatus="index">
        <tr>
          <td class="text-c">${index.index + 1}</td>
          <td class="text-c">${be.partName }</td>
          <td class="text-c">${be.levels }</td>
          <td class="text-c">
          <c:if test="${!empty be.memo }">${be.memo }</c:if>
          </td>
          <td class="text-c"><fmt:formatDate value="${be.deadline }"/></td>
          <td data-n="xxx" class="text-l" data-i="${be.id }" data-file="" data-status="2" data-dead="<fmt:formatDate value="${be.deadline }" pattern="yyyy-MM-dd"/>">&nbsp;
            <div class="radio-box">
              <i class="img_check">&nbsp;</i>
              <label>整改</label>
            </div>
            <div class="radio-box">
              <i class="img_check">&nbsp;</i>
              <label>未整改</label>
            </div>
          </td>
        </tr>
        </c:forEach>
        <tr>
          <td colspan="6">
            <div class="div_pri  mt-10">针对上次隐患整改要求，经复查，提出如下复查意见：</div>
            <div class="div_pri  mt-10">1、已整改项：基本符合安全要求</div>
            <div class="div_pri">
             <!-- 循环 -->
             <c:forEach items="${itemL }" var="be" varStatus="index">
              <div class="check-box">
                <i class="img_check">&nbsp;</i>
                <label>${index.index + 1}</label>
              </div>
             </c:forEach>
              <!-- 循环结束 -->
            </div>
            <div class="div_pri  mt-10">2、未整改项：</div>
            <div class="div_pri">
             <!-- 循环 -->
              <c:forEach items="${itemL }" var="be" varStatus="index">
              <div class="check-box">
                <i class="img_check">&nbsp;</i>
                <label>${index.index + 1}</label>
              </div>
              </c:forEach>
              <!-- 循环结束 -->
            </div>
            <div class="div_pri  mb-10  mt-10">
              <font style="float:Left;">3、对未整改项要求于</font>
              <font style="float:Left;margin-left:150px">整改完毕</font>
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
            </c:if>
            <div class="div_pleft  mt-10 mb-10">检查人员签字：</div>
            <div class="div_pri mt-10">
            </div>
            </div>
          </td>
          <td colspan="3">
          <div style="float:left;width:100%;min-height:150px;">
            <div class="div_pleft  mt-10 mb-10">受检单位负责人签字：</div>
            <div class="div_pri mt-10">
            </div>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
  <!-- 循环结束 -->
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