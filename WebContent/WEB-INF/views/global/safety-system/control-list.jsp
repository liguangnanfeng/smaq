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
.table-bg thead th{
	color:#f73f3f;
}
</style>
<script type="text/javascript">
/* 弹窗管控信息添加 */
function add(){
  $("#win-add").modal("show")
}
var villageId = '${m.villageId}';
$(function() {
  if($("#townId").length > 0) {
    $("#townId").change(function() {
      $("#villageId").empty().append("<option value=''>全部</option>");
      if($(this).val() != '') {
        $.post(getRootPath() + "/village/getVillageByTown", {
          townId : $("#townId").val()
        },function(result) {
          var list = result.map.list;
          $.each(list, function(i, item) {
            $("#villageId").append("<option value='"+item.userId+"'>"+item.name+"</option>");
          })
          if(villageId != '') {
            $("#villageId").val(villageId);
            villageId = '';
          }
        })
      }
    })
    
    $("#townId").change();
  }
})
</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>风险分级管控</span> 
    <span class="c-gray en">&gt;</span> <span>风险控制</span> 
    <span class="c-gray en">&gt;</span> <span>重大风险汇总</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="text-c">
      <form action="${ly }/global/safety-system/control-list?level=${m.level }" method="post">
        <c:if test="${session_user.userType == 6}">
            <span>所在镇：</span>
            <select name="townId" id="townId" class="select" style="width:100px;">
                <option value="">全部</option>
                <c:forEach items="${townL }" var="be">
                    <option value="${be.userId }" <c:if test="${m.townId == be.userId}"> selected</c:if>>${be.name }</option>
                </c:forEach>
            </select>
            <span>所在村：</span>
            <select name="villageId" id="villageId" class="select" style="width:100px;">
                <option value="">全部</option>
            </select>
        </c:if>
        <c:if test="${session_user.userType == 3}">
        <div class="dis-ib">
          <span>所在村：</span>
          <select id="villageId" name="villageId" class="select" style="width:100px;">
            <option value="">全部</option>
            <c:forEach items="${villageL }" var="be">
            <option value="${be.userId }" <c:if test="${m.villageId == be.userId}"> selected</c:if>>${be.name }</option>
            </c:forEach>
          </select>
        </div>
        </c:if>
        <div class="dis-ib">
          <span>企业名称：</span>
          <input type="text" name="companyName" value="${m.companyName }" class="input-text mb-5 mt-5" style="width:150px;">
        </div>
        <button class="btn btn-success" type="submit">
          <i class="Hui-iconfont">&#xe665;</i> 查询
        </button>
      </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span> 
    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort tab-ndan">
        <thead>
          <tr class="text-c" style="background-color:red;">
            <th style="min-width:150px">企业名称</th>
            <th style="min-width:100px">岗位/部位</th>
            <th style="min-width:150px">风险类型</th>
            <th style="min-width:150px">风险因素</th>
            <th style="min-width:100px">管控主体</th>
            <th style="min-width:100px">责任人</th>
            <th style="min-width:200px">管控措施</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${list }" var="be" varStatus="index">
          <tr>
            <td class="td-color">${be.companyName }</td>
            <td class="td-color"><c:if test="${empty be.level1}">公司</c:if><c:if test="${not empty be.level1}">${be.level1 } > ${be.level2 }</c:if></td>
            <td>${be.type }</td>
            <td>${be.factors }</td>
            <td>${be.gkzt}</td>
            <td>${be.fjgkfzr }</td>

            <td><p>${empty be.gkcs ? be.measures : be.gkcs }</p></td>

          </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</body>
<script type="text/javascript" src="/js/fxgk/tbl-rowspan-reset.js"></script>
</html>
