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
    .col-b,.col-a,.col-c,.col-d{margin:0;padding:0 15px;height:25px;line-height:25px;}
  </style>
  <script type="text/javascript">
      $(function() {
      })

      function add(){
          $("#win-add").modal("show");
      }

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
          $.post(getRootPath() + "/company/safety-system/aCompanyManual-deletes", {
              ids : ids
          }, function(result) {
              location.reload();
          })
      }
      $(function(){
          $('.table-sort').dataTable({
              "searching": false,
              "ordering": false,
              "bStateSave": false,//状态保存
              "aoColumnDefs": [
              ]
          });
          /* 全选和反选 */
          $(".check-all").on('click', function() {
              $(".modal-body tbody input:checkbox").prop("checked", $(this).prop('checked'));
          })
          $(".tab-ass tbody input:checkbox").on('click', function() {
              if($(".tab-ass tbody input:checkbox").length == $(".tab-ass tbody input:checked").length) {
                  $(".check-all").prop("checked", true);
              } else {
                  $(".check-all").prop("checked", false);
              }
          })
      })
  </script>
</head>
<body>
<nav class="breadcrumb text-c">
  <a class="btn-page pagexz" href="javascript:;">重大风险判定</a>
  <a class="btn-page" data-href="${ly }/company/safety-system/assess-list"  data-title="较大风险风险判定" onclick="Hui_admin_tab(this)" href="javascript:;">较大风险风险判定</a>
  <a class="btn-page"  data-href="${ly }/company/safety-system/assess6"  data-title="未定和一般风险判定" onclick="Hui_admin_tab(this)" href="javascript:;">未定和一般风险判定</a>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="parent.close_tab(function(){})">返回</a>
</nav>
<div class="page-container">
  <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
<%--
          <a class="btn btn-primary radius" onClick="show_dialog('评估','/company/safety-system/assess5-2-choose')" href="javascript:;"><i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 评估</a>
--%>
          <a class="btn btn-danger radius" href="javascript:void(0)" onClick="del_bath()"><i class="Hui-iconfont" style="font-size:15px;">&#xe609;</i> 删除</a>
        </span>
  </div>
  <div class="mt-20">
    <table class="table table-border table-bordered table-bg table-hover table-sort tab-ass">
      <thead>
      <tr class="text-c">
        <th style="width:40px"><input type="checkbox" value="" class="check-all"/></th>
        <!-- <th>场所/部位/环节</th> -->
        <th style="min-width:100px">环节/部位</th>
        <th style="min-width:100px">风险等级</th>
        <th style="min-width:100px">风险因素</th>
        <%--<th style="min-width:100px">风险类型</th>--%>
        <th style="min-width:50px">操作</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${list }" var="be" varStatus="index">
        <tr>
          <td class="text-c"><input type="checkbox" value="${be.id}"/></td>
            <%-- <td class="text-c">${be.level3 }</td> --%>
          <td>
            <c:set value="${fn:split(be.level3,'/')}" var="ls"></c:set>
            <c:set value="0" var="y"/>
            <c:forEach items="${ls}" var="l">
              <c:set value="${y+1}" var="y"/>
              <c:if test="${y==3}">
                <c:set value="${l}" var="point"/>
              </c:if>
            </c:forEach>
            <p>${point}</p>
          </td>
          <td class="text-c"><font class="col-a">红色</font></td>
          <td>${be.factors }</td>
          <%--<td>${be.type }</td>--%>
          <td class="text-c"><a class="btn-cz" style="text-decoration:none;" onClick="del_(${be.id})" href="javascript:;" title="删除">删除</a></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>
</body>
</html>
