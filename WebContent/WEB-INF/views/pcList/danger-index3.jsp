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
body .dis-ib{margin-right:15px;}
.r strong {color: red;}
</style>
<script type="text/javascript">
function showpicture(memoImg){
  //memoImg = "";
  if (memoImg.length !== 0) { 
    $("#memoImg").attr("src",getRootPath()+memoImg);
  }
  $("#modal-plan").modal("show");
}
</script>
</head>
<body>
  <div class="page-container">
    <div class="text-c">
      <form action="${ly }/town/cx/check-item3?villageId=${villageId }&townId=${townId }&districtId=${districtId }" method="post">
        <c:if test="${session_user.userType != 5}">
        <div class="dis-ib">
          <span>企业名称：</span>
          <input type="text" value="${companyName }" name="companyName" class="input-text mb-5 mt-5" style="width:150px;">
        </div>
        </c:if>
        <div class="dis-ib">
          <span>状态：</span>
          <select name="status" class="input-text mb-5 mt-5" style="width:150px;">
            <option value="">全部</option>
            <option value="2" <c:if test="${status == 2}"> selected</c:if>>未整改</option>
            <option value="3" <c:if test="${status == 3}"> selected</c:if>>已整改</option>
          </select>
          <span>来源：</span>
          <select name="flag" class="input-text mb-5 mt-5" style="width:150px;">
            <option value="">全部</option>
            <option value="1" <c:if test="${flag == 1}"> selected</c:if>>企业自查</option>
            <option value="2" <c:if test="${flag == 2}"> selected</c:if>>行政执法</option>
            <option value="3" <c:if test="${flag == 3}"> selected</c:if>>部门抽查</option>
            <option value="4" <c:if test="${flag == 4}"> selected</c:if>>行政检查</option>
          </select>
        </div>
        <button class="btn btn-success" type="submit">
          <i class="Hui-iconfont">&#xe665;</i> 查询
        </button>
      </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="r">
              隐患总数：<strong>${list_s3 + list_s2}</strong> 条,
              已整改数量：<strong>${list_s3 }</strong> 条,
              未整改数量：<strong>${list_s2 }</strong> 条
      </span> 
    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">编号</th>
            <th width="10%">企业名称</th>
            <th width="8%">检查部位</th>
            <th width="10%">检查项目</th>
            <th width="15%">检查内容</th>
            <th width="10%">隐患描述</th>
            <th width="8%">检查结果</th>
            <th width="7%">检查日期</th>
            <th width="10%">${flag == 1 ? '受检部门' : '检查单位'}</th>
            <th width="10%">隐患图片</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环开始 -->
          <c:forEach items="${list }" varStatus="index" var="list">
            <tr class="text-c">
            <td>${index.index + 1}</td>
            <td>${list.companyName }</td>
            <td>${list.partName }</td>
            <td>${list.levels }</td>
            <td>${list.content }</td>
            <td>
              ${list.memo }
               </td>
            <td>
                <c:if test="${list.status == '1'}">合格</c:if>
                <c:if test="${list.status == '2'}">未整改</c:if>
                <c:if test="${list.status == '3'}">已整改</c:if>
               </td>
            <td><fmt:formatDate value="${list.realTime }"/></td>
               <td>${flag != 1 ? list.checkCompany : list.depart}</td>
            <td>
              <c:if test="${!empty list.files}">
                <img alt="" src="${ly }${list.files }" style="max-width: 200px;cursor:pointer;" onclick="showpicture('${list.files}')">
              </c:if>
            </td>
            </tr>
        </c:forEach>
        <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
    <!-- 弹窗图片 -->
  <div id="modal-plan" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
          <div class="modal-content radius">
              <div class="modal-header">
                  <h3 class="modal-title">隐患描述</h3>
                  <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
              </div>
              <div class="modal-body" style="height:400px;overflow-y:auto">
                <!-- 循环图片 -->
                <div class="div_imgp" style="text-align:center;">
                  <img id="memoImg" src="${ly }/images/zwtp.jpg" class="img-responsive mt-20" >
                </div>
                <!-- 循环结束 -->
              </div>
          </div>
      </div>
  </div>
  </div>
<script type="text/javascript">
$(function() {
  $('.table-sort').dataTable({
    "aaSorting": [[0, "asc"]],//默认第几个排序
    "bStateSave": false,//状态保存
    "aoColumnDefs": [
    ]
    });
});
</script> 
</body>
</html>