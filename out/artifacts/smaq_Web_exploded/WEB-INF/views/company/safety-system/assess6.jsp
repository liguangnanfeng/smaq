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
.p-show{display:none;}
.col-b,.col-a,.col-c,.col-d{margin:0;padding:0 15px;height:25px;line-height:25px;}
</style>
</head>
<script type="text/javascript">
function del_(id) {
  layer.confirm("确定删除该记录?", function(r) {
    $.post(getRootPath() + "/company/safety-system/aCompanyManual-save", {
      id : id,
      del : 1
    }, function(result) {
      layer.close(r);
      location.reload();
    })
  })
}
$(function(){
  /* 全选和反选 */
   $(".check-all").on('click', function() {
     $(".tab-ass tbody input:checkbox").prop("checked", $(this).prop('checked'));
   })
   $(".tab-ass tbody input:checkbox").on('click', function() {
       if($(".tab-ass tbody input:checkbox").length == $(".tab-ass tbody input:checked").length) {
           $(".check-all").prop("checked", true);
       } else {
           $(".check-all").prop("checked", false);
       }
   })
 })
 
 
function del_bath() {
  var ids = new Array();
  $("tbody :checked").each(function() {
    ids.push($(this).val());
  })
  ids = ids.join(",");
  if(ids == '') {
    layer.alert("请选择需要删除的数据");
    return false;
  }
  $.post(getRootPath() + "/company/safety-system/aCompanyManual-del", {
    ids : ids
  }, function(result) {
    location.reload();
  })
}
</script>
<body>
  <nav class="breadcrumb text-c">
    <a class="btn-page"  data-href="${ly }/company/safety-system/assess5"  data-title="重大风险判定" onclick="Hui_admin_tab(this)" href="javascript:;">重大风险判定</a>
    <a class="btn-page pagexz" href="javascript:;">较大风险判定</a>
    <a class="btn-page" data-href="${ly }/company/safety-system/assess-list"  data-title="其他风险评估" onclick="Hui_admin_tab(this)" href="javascript:;">其他风险评估</a>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="parent.close_tab(function(){})">返回</a>
  </nav>
  <div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l">
        <a class="btn btn-danger radius" href="javascript:void(0)" onClick="del_bath()"><i class="Hui-iconfont" style="font-size:15px;">&#xe609;</i> 删除</a>
      </span>
    </div> 
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort tab-ass">
        <thead>
          <tr class="text-c"> 
            <th style="min-width:100px">车间</th>
            <th style="min-width:100px">岗位/部位</th>
            <th style="min-width:100px">风险点</th>
            <th style="min-width:100px">风险因素</th>
            <!-- <th>管控措施</th> -->
            <th style="min-width:100px">风险等级</th>
            <th>操作</th>
            <th style="width:40px"><input type="checkbox" value="" class="check-all"/></th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环 -->
          <c:forEach items="${list }" var="be">
          <c:forEach items="${be.value }" var="be2">
          <c:forEach items="${dL }" var="be3">
              <c:if test="${be3.level1 eq be.key and be3.level2 eq be2}">
              <tr>
                <td class="text-c" rowspan="1">${be.key }</td>
                <td class="text-c">${be2 }</td>
            <td class="text-c">
                <c:set value="${fn:split(be3.level3,'/')}" var="ls"></c:set>
              	<c:set value="0" var="y"/>
              	<c:forEach items="${ls}" var="l">
              		<c:set value="${y+1}" var="y"/>
                  <c:if test="${y==3}">
                  	<c:set value="${l}" var="point"/>
                  </c:if>
               </c:forEach>
               <p>${point}</p>
            </td>
                <td class="text-c">${be3.factors }</td>
                <!-- <td><p class="p-show">管控措施xx</p></td> -->
                <td class="text-c">
                  <c:choose>
                    <c:when test="${be3.level eq '红色'}"><font class="col-a">${be3.level}</font></c:when>
                    <c:when test="${be3.level eq '橙色'}"><font class="col-b">${be3.level}</font></c:when>
                    <c:when test="${be3.level eq '黄色'}"><font class="col-c">${be3.level}</font></c:when>
                    <c:when test="${be3.level eq '蓝色'}"><font class="col-d">${be3.level}</font></c:when>
                   </c:choose>
                </td>
                <%-- <td class="text-c"><font class="col-b"><c:if test="${be3.issys == 1}">${be3.level }</c:if></font></td> --%>
                <td class="text-c" style="width:50px">
                  <a style="text-decoration:none" onClick="del_(${be3.id})" href="javascript:;" title="删除">删除</a>
                  <%-- <c:if test="${be3.issys == 0}">
                  <a style="text-decoration:none" onClick="show(${be3.id})" href="javascript:;" title="评估">评估</a>
                  </c:if> --%>
                </td>
                <td class="text-c"><input type="checkbox" value="${be3.id }"/></td>
              </tr>
              </c:if>
          </c:forEach>
          </c:forEach>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
  </div>
</body>
<script type="text/javascript" src="/js/fxgk/tbl-rowspan-reset.js"></script>
<script type="text/javascript">
function show(id) {
  $.post(getRootPath() + "/company/safety-system/aCompanyManual-save", {
    id : id,
    issys : 1
  }, function(result) {
    layer.alert("评估结果为‘橙色’较大隐患",{},function() {
      location.reload();
    })
  })
}
</script>
</html>
