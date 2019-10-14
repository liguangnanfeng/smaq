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
<title>安全分级管控   隐患排查治理智能化信息平台</title>
<meta name="keywords" content="安全分级管控   隐患排查治理智能化信息平台">
<meta name="description" content="安全分级管控   隐患排查治理智能化信息平台">
<style type="text/css">
body .dis-ib{margin-right:15px;}
.btn-edit {
    background: url("${ly}/images/btn-edit.png") no-repeat 4px 7px;
    width: 22px;
    height: 22px;
}
.btn-edit.active{
    background-position: 4px -104px;
}
#toCancel {
    display: inline-block;
    margin: 1px 0;
    line-height: 18px;
    background-color: #eee !important;
    border: 1px solid #ddd;
    padding: 0 2px;
    border-radius: 4px;
    margin-left:15px;
    color:#999;
}
</style>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
    <span class="c-gray en">&gt;</span> <span>系统设置管理</span>
    <span class="c-gray en">&gt;</span> <span>在线监测</span>
    <span class="c-gray en">&gt;</span> <span>水质五参数设置</span> 
    <!-- 获取风险等级1和2的企业 -->
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="15%">水质参数</th>
            <th width="15%">参数名称</th>
            <th width="8%">单位</th>
            <th width="14%">最大值</th>
            <th width="15%">最小值</th>
            <th width="15%">操作</th>
          </tr>
        </thead>
        <tbody>
        
          <!-- 循环村 -->
         <c:forEach items="${waterParams }" varStatus="index" var="co">
          <tr class="text-c">
            <td>${index.index + 1}</td>
            <td>${co.para }</td>
            <td>${co.name }</td>
            <td>${co.unit }</td>
            <td id="max_${index.index + 1}">${co.maxVal}</td>
            <td id="min_${index.index + 1}">${co.minVal }</td>
            <td><a core-index="${index.index + 1}" core-id="${co.id }" core-name="${co.name }" core-max="${co.maxVal}" core-min="${co.minVal }" href="javascript:void(0)" class="toEditParam">编辑</a></td>
          </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
  </div>
  <script type="text/javascript" src="<c:url value='/js/modules/system/parameterSetting.js'/>"></script>
</body>
</html>