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
<link rel="stylesheet" type="text/css" href="${ly}/styles/print.css" />
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style type="text/css">
</style>
<script type="text/javascript">
function pr_() {
  $("#div_container").jqprint();
}
</script>
</head>

<body>
  <div class="page-container" style="width:180mm;margin:auto;padding:10px 0 0 0;">
    <!-- <div class="row cl row_ac">
      <button onClick="pr_()" class="btn radius" type="button">
          <i class="Hui-iconfont mr-10">&#xe652;</i>打印
      </button>
    </div> -->
    <c:set var="wid1" value="${fn:split('13/15/15/15','/') }" />
    <c:set var="wid2" value="${fn:split('6/10/10/10','/') }" />
    <div id="div_container" style="width:180mm;margin:auto;padding:50px 0 0 0;">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="6%">序号</th>
            <th width="${wid1[spFlag]}%">姓名</th>
            <c:if test="${spFlag == 0 }">
            <th width="10%">所属岗位</th>
            </c:if>
            <th width="10%">工种</th>
            <th width="${wid1[spFlag]}%">证书编号</th>
            <th width="${wid1[spFlag]}%">取证时间</th>
            <th width="13%">下次复审时间</th>
            <th width="10%">备注</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环-->
          <c:forEach items="${list }" varStatus="index" var="spe">
            <tr class="text-c">
              <td>${index.index+1 }</td>
              <td>${spe.name }</td>
              <c:if test="${spFlag == 0 }">
              <td>${flagV[spe.flag - 1] }</td>
              </c:if>
              <td>${spe.type }</td>
              <td>${spe.certificateNumber }</td>
              <td>${spe.forensicTime }</td>
              <td>${spe.reviewTime }</td>
              <td>${spe.remark }</td>
            </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
</div>
</body>

</html>