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
.btn-upload{position: relative; display:inline-block;height:36px; *display:inline;overflow:hidden;vertical-align:middle;cursor:pointer}
.upload-url{cursor: pointer}
.input-file{position:absolute; right:0; top:0; cursor: pointer; z-index:1; font-size:30em; *font-size:30px;opacity:0;filter: alpha(opacity=0)}
.btn-upload .input-text{ width:auto}
.form-group .upload-btn{ margin-left:-1px}
</style>
<script type="text/javascript">
var id;
function openzl(Id){
  id=Id
  $("#upload-zl").modal("show")}
</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>安全生产标准化</span> 
    <span class="c-gray en">&gt;</span><span>安全生产管理档案</span>
    <span class="c-gray en">&gt;</span> <span>事故报告和处理</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="text-c">
    <form action="${ly }/company/tables/tab-shilist" method="post">
        <div class="dis-ib">
          <span>事故类别：</span>
          <input type="text" value="${type }" id="" name="type" class="input-text mb-5 mt-5" style="width:150px;">
        </div>
        <button class="btn btn-success" type="submit">
          <i class="Hui-iconfont">&#xe665;</i> 查询 
        </button>
   	</form>
    </div>
    
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l">
        <a class="btn btn-primary radius" data-title="添加事故报告和处理" data-href="${ly }/company/tables/tabshi-add" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 添加事故报告和处理</a>
      </span>
      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span>
    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="10%">所在部门</th>
            <th width="15%">事故时间</th>
            <th width="15%">事故类别</th>
            <th width="10%">死亡人数</th>
            <th width="10%">失踪人数</th>
            <th width="10%">受伤人数</th>
            <th width="10%">经济损失</th>
            <th width="15%">操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环-->
          <c:forEach items="${list }" varStatus="index" var="list">
          <tr class="text-c">
            <td>${index.index+1 }</td>
            <td>${list.department }</td>
            <td><fmt:formatDate value="${list.time }" pattern="yyyy-MM-dd"/></td>
            <td>${list.type }</td>
            <td>${list.personDeath }</td>
            <td>${list.personMiss }</td>
            <td>${list.personInjured }</td>
            <td>${list.economicLoss }</td>
            <td>
              <a style="text-decoration:none" onclick="edit(${list.id })" href="javascript:;" title="修改">修改</a>
              <c:if test="${!empty list.docUrl}">
              <a style="text-decoration:none"  href="${ly}/common/download?filename=${list.docName}&fileurl=${list.docUrl}" title="查看事故调查报告">查看事故调查报告</a>
              </c:if>
              <a style="text-decoration:none" onclick="del(${list.id })" href="javascript:;" title="删除">删除</a>
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
    searching: false,
    ordering:  false,
    "aoColumnDefs": [
    ]
    });
});

/*编辑*/
function edit(id){
  show_tab("事故报告编辑", getRootPath() + "/company/tables/tabshi-add?id=" + id);
}

/*删除*/
function del(id){
  $.post(getRootPath()+"/company/tables/tabshi-del",{
    id:id,
  },function(reuslt){
    location.reload()
  })
}

</script>
</body>
</html>