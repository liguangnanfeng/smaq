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
<link rel="stylesheet" type="text/css" href="${ly}/styles/print-model.css" />
<script src="${ly }/js/jquery-migrate-1.2.1.min.js"></script>
<script src="${ly }/js/jquery.jqprint-0.3.js"></script>
<script type="text/javascript" src="${ly}/js/H-ui.admin_v3.0/lib/layer/2.4/layer.js"></script> 
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style type="text/css">
.print-bt{display:none}
</style>
<script type="text/javascript">
function uploadpicture(obj){
  window.itemId = $(obj).attr("data-item");
  var memo = $(this).closest("td").find("div[name='item-memo']").html();
  var file = $(obj).attr("data-file");
  $("#pic3").attr("src", file==""?"/images/zwtp.jpg":file).attr("url", file);
  $("#memo").val(memo);
  $("#modal-plan").modal("show");
}
function pr_() {
  $("#cheker").hide();
  $("#dapartContact").hide();
  $(".f-m1").show();
  $(".f-m2").show();
  $(".print-bt").show();
  $(".f-m1").html($("#cheker").val());
  $(".f-m2").html($("#dapartContact").val());
  $("#container").jqprint();
  $(".f-m1").hide();
  $(".f-m2").hide();
  $("#cheker").show();
  $("#dapartContact").show();
  $(".print-bt").hide();
}
</script>
</head>
<body>
 <div class="text-c mt-20"><a class="btn btn-success radius ml-10" onclick="pr_()"><i class="Hui-iconfont" style="font-size:15px;">&#xe652;</i> 打印</a></div>
<div class="page-container" id="container">
  <h3 class="text-c print-bt" style="width:100%;margin:0px;margin-bottom:20px;float:Left;padding:0;">${session_user.userName }公司检查表</h3>
  <div class="div_pdetail">
    <font>受检${model.flag == 1 ? '部门' : '单位'}：${model.part }</font>
    <label>检查日期：<fmt:formatDate value="${model.createTime }" pattern="yyyy年MM月dd日"/></label>
  </div>
  <div class="f-l mt-20" style="width:100%">
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
          <c:if test="${lanlin:constains(part.levels, ch.levelId, ',')}">
            <c:set var="x" value="${x + 1}"/>
          </c:if>
        </c:forEach>
        <tr>
          <td class="text-c" rowspan="${x }">${pi.index + 1}</td>
          <td class="text-c" rowspan="${x }">${part.name }</td>
          <c:set var="y" value="0"/>
          <c:forEach items="${itemL }" var="ch">
          <c:if test="${lanlin:constains(part.levels, ch.levelId, ',')}">
          <c:if test="${y > 0}">
          <tr>
          </c:if>
          <td class="text-l">${ch.level1 }/${ch.level2 }<c:if test="${!empty ch.level3}">/${ch.level3 }</c:if></td>
          <td class="text-l">${ch.content }</td>
          <td class="text-c">&nbsp;
            <div class="radio-box">
              <input type="radio" name="xx_${ch.id }">
              <label>合格</label>
            </div>
            <div class="radio-box">
              <input type="radio" name="xx_${ch.id }">
              <label>不合格</label>
            </div>
          </td>
          <c:if test="${lanlin:constains(part.levels, ch.levelId, ',')}">
          </tr>
          </c:if>
          <c:set var="y" value="1"/>
          </c:if>
          </c:forEach>
        </tr>
        
        </c:forEach>
        
        <tr>
          <td colspan="3">
            <div class="div_pleft  mt-10 mb-10">检查人员签字：</div>
            <div class="div_pright  mt-10 mb-10">
              <input type="text" id="cheker" value="" class="input-text" maxlength="50"/>
              <font class="f-m1"></font>
            </div>
          </td>
          <td colspan="3">
            <div class="div_pleft  mt-10 mb-10">受检部门负责人签字：</div>
            <div class="div_pright  mt-10 mb-10">
              <input type="text" id="dapartContact" value="" class="input-text" maxlength="50"/>
              <font class="f-m2"></font>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</div>

</body>
</html>