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
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style type="text/css">
body .dis-ib{margin-right:15px;}
</style>
</head>
<script type="text/javascript">
function del_(id) {
  layer.confirm("确定删除？",function(i){
    layer.close(i);
    $.post(getRootPath() + "/company/health-del",{
      id : id
    },function(result){
      location.href = '/company/evaluate/detection-list?show=2';
    })
  })
}
</script>
<body>
  
  <div class="page-container">
    <div class="text-c">
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l">
        <a class="btn btn-primary radius" data-title="添加职业健康检测" data-href="${ly }/company/evaluate/health-edit" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 添加职业健康检查</a>
      </span>
      <span class="r">共有数据：<strong>${fn:length(list2) }</strong> 条</span> 
    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="15%">体检类别</th>
            <th width="20%">体检时间</th>
            <th width="15%">体检人数</th>
            <th width="15%">到期时间</th>
            <th width="15%">备注</th>
            <th width="15%">操作</th>
          </tr>
        </thead>
        <tbody>
        <c:forEach items="${list2 }" varStatus="index" var="e">
          <tr class="text-c">
            <td>${index.index+1 }</td>
            <td>${e.type }</td>
            <td>${e.time }</td>
            <td>${e.healthcheckMember }</td>
            <td>${e.endTime }</td>
            <td>${e.remark }</td>
            <td>
              <a style="text-decoration: none" onclick="show_tab('职业健康检测编辑', '/company/evaluate/health-edit?id=${e.id}')" href="javascript:;">修改</a>
              <a style="text-decoration: none" onclick="del_(${e.id})" href="javascript:;">删除</a>
            </td>
          </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</body>
</html>