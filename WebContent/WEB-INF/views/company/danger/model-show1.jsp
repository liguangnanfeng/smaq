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
<style type="text/css">
.input-text{position:relative}
.txtarea_sq{height:100px;width:350px;}
body .check-box, .radio-box{line-height:30px;}
.div_pdetail{width:100%;font-size:15px;}
.div_pdetail font{float:left;font-size:15px;margin-left:15px;font-weight:bold;}
.div_pdetail label{float:right;font-size:15px;margin-right:15px;}
.div_imgp{float:left;width:90%;margin-left:5%;text-align: center;}
.div_imgp img{max-width:100%}
.div_pleft{width:150px;display: inline-block;line-height:32px;font-size:14px;}
.div_pright{width:300px;display: inline-block;}
.div_pri{width:100%;min-height: 32px}
.div_pri font{float:right;line-height:32px;font-size:14px;margin-right:15px;}
.div_pri .Wdate{float:right;}
body .select{height:32px;line-height:32px;}
body .radio-box{float:Left;}
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
</script>
</head>
<body>
<div class="page-container">
  <div class="div_pdetail">

    <c:if test="${flag==2||falg==3}">
      <font>公司名称:${user.userName}</font>
    </c:if>
    <c:if test="${flag==1}">
      <font>车间名称:${model.part}</font>
    </c:if>
    <label>检查日期：<fmt:formatDate value="${model.createTime }" pattern="yyyy年MM月dd日"/></label>
  </div>
  <div class="f-l mt-20" style="width:100%">
    <table class="f-l table table-border table-bordered table-bg table-hover table-sort">
      <thead>
        <tr class="text-c">
          <th width="3%">序号</th>
          <%--<th width="15%">岗位/部位</th>--%>
          <th width="30%">风险类型</th>
          <th width="40%">检查内容</th>
          <th width="12%">操作</th>
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
          <%--<td class="text-c" rowspan="${x }">${part.name }</td>--%>
          <c:set var="y" value="0"/>
          <c:forEach items="${itemL }" var="ch">
          <c:if test="${lanlin:constains(part.levels, ch.levelId, ',')}">
          <c:if test="${y > 0}">
          <tr>
          </c:if>
          <td class="text-l">${ch.dangerType }</td>
          <td class="text-l">${ch.measures }</td>
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
              <input type="text" style="width:150px" id="cheker" value="" class="input-text" maxlength="50"/>
            </div>
          </td>
          <td colspan="3">
            <div class="div_pleft  mt-10 mb-10">受检部门负责人签字：</div>
            <div class="div_pright  mt-10 mb-10">
              <input type="text" style="width:150px" id="dapartContact" value="" class="input-text" maxlength="50"/>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</div>
<biv>
  显示内容:  ${itemL }
</biv>
</body>
</html>