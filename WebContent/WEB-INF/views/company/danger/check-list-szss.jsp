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
.btn-group .btn{height: 34px;line-height: 34px;padding: 0 25px;}
</style>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>${flag==1 ? '隐患排查治理系统' : '行政执法系统'}</span>
    <c:if test="${flag == 1}">
      <span class="c-gray en">&gt;</span> <span>${dmname}隐患排查记录</span>
    </c:if>
    <c:if test="${flag != 1}">
      <span class="c-gray en">&gt;</span> <span>执法记录</span> 
    </c:if>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">

    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l">

      </span>
      <span class="r">检查总数：<strong>${fn:length(list) }</strong> 条；隐患数量：<strong>${sum}</strong> 条</span>
    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
          <thead>
          <tr class="text-c">
              <th width="5%">序号</th>
              <th width="20%">检查表名称</th>
              <th width="10%">${flag == 1 ? '检查方式' : '检查日期'}</th>
              <th width="10%">${flag == 1 ? '检查日期' : '检查部门'}</th>
              <%--<th width="10%">${flag == 1 ? '实际检查' : '录入'}时间</th>--%>
              <th width="10%">检查人员</th>
              <th width="10%">隐患数量</th>
              <%-- <th width="15%">${flag == 1 ? '受检部门' : '检查单位'}</th>--%>
              <th width="10%">操作</th>
          </tr>
          </thead>
        <tbody>
          <!-- 循环-->
          <c:forEach items="${list }" varStatus="index" var="be">
            <tr class="text-c">
            <td>${index.index + 1 }</td>
            <td><c:if test="${be.status == 1 and (!empty be.expectTime and be.expectTime.time < t)}"><font color="red">【过期】</font></c:if>${be.title }</td>
                <c:if test="${flag == 1}">
                    <td>
                        <c:choose>
                            <c:when test="${be.type == 1 }">日常</c:when>
                            <c:when test="${be.type == 2 }">定期</c:when>
                            <c:when test="${be.type == 3 }">季节</c:when>
                            <c:when test="${be.type == 4 }">其它</c:when>
                            <c:when test="${be.type == 5 }">综合</c:when>
                        </c:choose>
                    </td>
                    <td>
                        <fmt:formatDate value="${be.realTime }" pattern="yyyy-MM-dd"/>
                    </td>
                </c:if>


                <c:if test="${flag != 1}">
                    <td>
                        <fmt:formatDate value="${be.realTime }" pattern="yyyy-MM-dd"/>
                    </td>

                    <td>
                            ${be.depart}
                    </td>
                </c:if>
            <td>
              <%--${be.status == 1 ? '未检查' : '已检查'}--%>
              ${be.cheker}
            </td>
            <td>${be.c }</td>
            <%--<td>${flag == 1 ? be.depart : be.checkCompany}</td>--%>
            <td>
                <c:if test="${be.c!=0}">
                    <a style="text-decoration:none;margin-top: 2px" onClick="show_dialog('整改详情_${be.id }', '${ly}/village/check-rectification?flag=${flag}&id=${be.id}&number=${be.c}')" href="javascript:;">整改详情</a></br>
                    <a style="text-decoration:none;margin-top: 2px" onClick="show_dialog('实施复查_${be.id }', '${ly}/company/recheck-add?checkId=${be.id}')" href="javascript:;">实施复查</a>
                </c:if>
            </td>
            </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
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
/*删除*/
/* function del_(id){
  layer.confirm("要删除该记录？", function(i) {
    layer.close(i);
    $.post(getRootPath()+"/company/check-del",{
      id:id
    },function(reuslt){
      location.reload();
    })
  })
} */

var type = '${type}';
function copy_(id) {
  show_tab('添加自查记录', getRootPath()+'/company/check-copy?id=' + id);
}
</script> 
</body>
</html>