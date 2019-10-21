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
      //自评保存
      function zpLevel_save_(id,e) {
          $.post(getRootPath() + "/company/safety-system/zp-save", {
              id : id,
              level : $(e).attr("value")
          },function(result) {
              location.reload();
          })
      }
  </script>
</head>
<body>

<div class="page-container">
    <div class="text-c">
    <form action="" method="post">
    <div class="dis-ib">
    <span>公司名称：</span>
    <input type="text" value="" id="maName" name="materialName" class="input-text mb-5 mt-5" style="width:150px;">
    </div>
    <button class="btn btn-success" type="submit">
    <i class="Hui-iconfont"></i> 查询
    </button>
    </form>
    </div>
  <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span>
  </div>
  <div class="mt-20">
    <table class="table table-border table-bordered table-bg table-hover table-sort tab-ass">
      <thead>
      <tr class="text-c">
        <th style="width:80px">企业名称</th>
        <th style="min-width:80px">辨识类型</th>
        <th style="min-width:80px">车间/场所</th>
        <th style="min-width:80px">系统</th>
        <th style="min-width:80px">环节/部位</th>
        <th style="min-width:70px">风险等级</th>
        <th style="min-width:150px">事故类型</th>
        <th style="min-width:200px">风险因素</th>
        <!-- <th>场所/部位/环节</th> -->
        <%--<th style="min-width:100px">环节/部位</th>
        <th style="min-width:100px">风险等级</th>
        <th style="min-width:100px">风险因素</th>--%>
        <%--<th style="min-width:100px">风险类型</th>--%>
<%--        <th style="min-width:50px">评估</th>--%>
      </tr>
      </thead>
      <tbody>
      
      <c:forEach items="${list }" var="be" varStatus="index">
        <tr>
          <th class="text-c">${be.name}</th>
            <%-- <td class="text-c">${be.level3 }</td> --%>
          <c:set value="${fn:split(be.level3,'/')}" var="ls"></c:set>

          <td class="text-c">${ls[0] != null ? ls[0] : "暂无数据" }</td>

          <td class="text-c">${be.level1 }</td>

          <td class="text-c">${ls[1] != null ? ls[1] : "暂无数据" }</td>

          <td class="text-c">${be.Level2 == null ? "暂无数据" : be.Level2}</td>
          <td class="text-c">
            <c:choose>
              <c:when test="${be.level eq '红色'}"><font class="col-a">${be.level}</font></c:when>
              <c:when test="${be.level eq '橙色'}"><font class="col-b">${be.level}</font></c:when>
              <c:when test="${be.level eq '黄色'}"><font class="col-c">${be.level}</font></c:when>
              <c:when test="${be.level eq '蓝色'}"><font class="col-d">${be.level}</font></c:when>
            </c:choose>
          </td>
          <td class="text-c">${be.type }</td>
          <td class="text-c">${be.factors }</td>
           <%-- <c:set value="${fn:split(be.level3,'/')}" var="ls"></c:set>
            <c:set value="0" var="y"/>
            <c:forEach items="${ls}" var="l">
              <c:set value="${y+1}" var="y"/>
              <c:if test="${y==3}">
                <c:set value="${l}" var="point"/>
              </c:if>
            </c:forEach>
            <p>${point}</p>--%>
         <%-- </td>
          <td class="text-c"><font class="col-a">红色</font></td>
          <td>${be.factors }</td>
          &lt;%&ndash;<td>${be.type }</td>&ndash;%&gt;
          <td class="text-c">
            <input class="btn btn-danger-outline size-S radius ml-5" type="button" value="红色" onclick="zpLevel_save_(${be.id},this)"/>
            <input class="btn btn-warning-outline size-S radius ml-5" type="button" value="橙色" onclick="zpLevel_save_(${be.id},this)"/>
            <input class="btn btn-warning-outline size-S radius btn-huang ml-5" type="button" value="黄色" onclick="zpLevel_save_(${be.id},this)"/>
            <input class="btn btn-secondary-outline size-S radius ml-5 mr-5" type="button" value="蓝色" onclick="zpLevel_save_(${be.id},this)"/>
            <a class="btn-cz" style="text-decoration:none;" onClick="del_(${be.id})" href="javascript:;" title="删除">删除</a>
          </td>--%>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>
</body>
</html>
