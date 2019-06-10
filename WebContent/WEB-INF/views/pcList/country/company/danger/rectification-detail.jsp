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
</style>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>隐患排查治理系统</span> 
    <span class="c-gray en">&gt;</span> <span>隐患汇总表</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
   <!--  <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l">事实描述：xx部位</span> 
    </div> -->
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="15%">检查部位</th>
            <th width="15%">检查项目</th>
            <th width="25%">检查内容</th>
            <th width="10%">检查结果</th>
            <th width="15%">整改意见</th>
            <th width="10%">复查时间</th>
            <th width="10%">操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环-->
           <c:forEach items="${itemL }" var="be" varStatus="index">
            <tr>
            <td>${index.index + 1}</td>
            <td>
            <c:if test="${!empty be.partImg}">
            <img src="${be.partImg }" class="mr-10" style="height:100px;"/>
            </c:if>
            ${be.partName }</td>
            <td>${be.levels }</td>
            <td>${be.content }</td>
            <td>${be.status == 2 ? '不合格' : '已整改'}</td>
            <td>整改意见：
              <c:if test="${empty be.suggest}">未设置</c:if>
              <c:if test="${!empty be.suggest}">
                ${be.suggest == 1 ? '立即整改' : '限期整改'}
              </c:if>
              <c:if test="${!empty be.deadline}"><br>限期时间:<fmt:formatDate value="${be.deadline }"/></c:if>
              <c:if test="${!empty be.planTime}"><br>计划复查时间:<fmt:formatDate value="${be.planTime }"/></c:if>
              <c:if test="${!empty be.recheckTime}"><br>实际复查时间:<fmt:formatDate value="${be.recheckTime }"/></c:if>
            </td>
            <td></td>
            <td>
              <c:if test="${!empty be.files || !empty be.memo}">
              <a href="javascript:void(0)" onclick="showpicture('${be.files}', this)">隐患描述</a>
              </c:if>
              <c:if test="${!empty be.recheckFile}">
              <a href="javascript:void(0)" onclick="showpicture('${be.recheckFile}', this)">复查详情</a>
              </c:if>
              <div data-name="item-memo" style="display: none;">${be.memo }</div>
            </td>
            </tr>
           </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
  </div>
  <script type="text/javascript">
function showpicture(src, obj){
  var memo = $(obj).closest("td").find("div[data-name='item-memo']").html();
  $(".modal-body .mt-10").html("隐患描述：" + (memo == '' ? '暂无描述' : memo));
  $(".div_imgp").html('');
  $.each(src.split(","), function(i, item) {
    $(".div_imgp").append('<div class="div_imgp">' + 
        '<img src="'+item+'" class="img-responsive mt-20" >' +
        '</div>');
  })
  $("#modal-plan").modal("show")}
$(function() {
  $('.table-sort').dataTable({
    });
});

</script> 
<!-- 弹窗图片 -->
  <div id="modal-plan" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
          <div class="modal-content radius">
              <div class="modal-header">
                  <h3 class="modal-title">查看详情</h3>
                  <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
              </div>
              <div class="modal-body" style="height:400px;overflow-y:auto">
                <p class="mt-10">隐患描述：xxx</p>
                <!-- 循环图片 -->
                <div class="div_imgp">
                  <img src="${ly }/images/zwtp.jpg" class="img-responsive mt-20" >
                </div>
                <!-- 循环结束 -->
              </div>
          </div>
      </div>
  </div>
</body>
</html>