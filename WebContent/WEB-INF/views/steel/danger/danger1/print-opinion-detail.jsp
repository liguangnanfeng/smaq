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
.div_pri font{float:left;line-height:32px;font-size:14px;margin-right:15px;}
.div_pri .Wdate{float:right;}
body .select{min-height:32px;line-height:32px;}
.div_pleft{width:150px;display: inline-block;line-height:32px;font-size:9pt;}
.div_pdetail font{font-size:10pt;margin-left:15px;font-weight:500;}
body .page-container .div_pdetail label{float:right;font-size:10pt;margin-right:15px;}
body th,body td,body .page-container label{font-size:9pt}
body .table-bordered,body .table-bordered th,body .table-bordered td{border:1px solid #000}
body .table-border.table-bordered{border-bottom:1px solid #000;border-collapse: collapse;}
body .table-bg thead th{background:#ddd;padding:20px 8px}
</style>
<script src="${ly }/js/jquery-migrate-1.2.1.min.js"></script>
<script src="${ly }/js/jquery.jqprint-0.3.js"></script>
<script type="text/javascript">
    alert("18:35");
function pr_() {
  $("#div_container").jqprint();

}
</script>
</head>
<body>
<div class="page-container" >
<div id="div_container" style="width:150mm;margin:auto;padding:50px 0;">
  <h2 class="text-c mb-20" style="font-size:14pt">隐患整改意见表</h2>
  <div class="div_pdetail mt-20">
  	<font>受检${check.flag == 1 ? '部门' : '单位'}：${check.depart }</font>
    <label>检查日期：<fmt:formatDate value="${check.realTime }" pattern="yyyy年MM月dd日"/></label>
  </div>
  <div class="mt-20" style="width:100%">
    <table class="table table-border table-bordered table-bg table-hover table-sort">
      <thead>
        <tr class="text-c">
          <th width="10%">序号</th>
          <th width="15%">检查部位</th>
          <th width="26%">检查项目</th>
          <th width="26%">隐患内容</th>
          <th width="23%">检查结果</th>
        </tr>
      </thead>
      <tbody>
       <c:forEach items="${itemL }" var="be" varStatus="index">
        <tr>
          <td class="text-c">${index.index + 1}</td>
          <td class="text-c">${be.partName }</td>
          <td class="text-l">${be.levels }</td>
          <td class="text-l">${be.memo }</td>
          <td class="text-c">不合格</td>
        </tr>
       </c:forEach>
       
        <tr>
          <td colspan="7">
            <div class="div_pri  mt-10">针对上述隐患，根据有关规定，现提出如下整改要求：</div>
            <div class="div_pri  mt-10">1、对下列隐患，进行立即整改</div>
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
            <div class="div_pri  mt-10">2、对下列隐患进行限期整改</div>
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
            <div class="div_pri mt-10">
              <font style="margin-left:30px;">期限：</font>
              <font><fmt:formatDate value="${rectification.deadline }" pattern="yyyy-MM-dd"/></font>
            </div>
            <div class="div_pri  mt-10">3、下列隐患属于重大隐患</div>
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
              <font style="float:Left;">4、整改复查时间：</font>
              <font><fmt:formatDate value="${rectification.planTime }" pattern="yyyy-MM-dd"/></font>
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
            <div class="div_pri mt-10"></div>
            </div>
          </td>
          <td colspan="4">
          <div style="float:left;width:100%;min-height:150px;">
            <div class="div_pleft  mt-10 mb-10">
            	<c:if test="${flag == 1}">受检部门负责人签字：</c:if>
    			<c:if test="${flag != 1}">受检单位负责人签字：</c:if>
            </div>
            <div class="div_pri  mt-10"></div>
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
      <button onClick="top.show_tab('排查治理记录', '${ly }/village/check-list?flag=${check.flag }')" class="btn btn-success radius" type="button" style="padding: 0 70px;">
        <i class="Hui-iconfont mr-10">&#xe652;</i>返回排查治理记录列表
      </button>
    </div>
  </div>
</div>
</body>
</html>