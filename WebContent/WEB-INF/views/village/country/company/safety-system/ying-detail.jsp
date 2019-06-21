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
<link rel="stylesheet" type="text/css" href="${ly}/styles/print-tab.css"/>
<link rel="stylesheet" type="text/css" href="${ly}/styles/print-tab.css" media="print" />
<link rel="stylesheet" type="text/css" media="print"  href="${ly}/js/H-ui.admin_v3.0/static/h-ui/css/H-ui.css" />
<script src="/js/jquery.jqprint-0.3.js"></script>
<style type="text/css">
</style>
<script type="text/javascript">
function pr_() {
  $("#div_container").jqprint();
}
</script>
</head>
<body>
  <div class="mt-20 text-c">
    <button onClick="pr_()" class="btn btn-primary radius" type="button">
      <i class="Hui-iconfont">&#xe652;</i>打印
    </button>
  </div>
  <div class="page-container" id="div_container">
    <div class="mt-20">
      <h3 class="text-c">岗位应急处置卡</h3>
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <tbody>
          <tr class="text-c">
            <td>岗位名称</td>
            <td colspan="2">xx</td>
          </tr>
          <tr class="text-c">
            <td>序号</td>
            <td>本岗位存在的事故类型</td>
            <td>应急处置措施</td>
          </tr>
          <tr>
            <td class="text-c">1</td>
            <td>xx</td>
            <td>xx</td>
          </tr>
      </table>
      <table class="table table-border table-bordered table-bg table-hover table-sort" style="border-top:0;">
        <tbody>
          <tr class="text-c">
            <td colspan="4" style="border-top:0">应急联系方式</td>
          </tr>
          <tr class="text-c">
            <td rowspan="2">内部</td>
            <td>主要负责人</td>
            <td>部门负责人</td>
            <td>工段负责人</td>
          </tr>
          <tr class="text-c">
            <td>xx</td>
            <td>xx</td>
            <td>xx</td>
          </tr>
          <tr class="text-c">
            <td rowspan="2">外部</td>
            <td>火警电话</td>
            <td>急救电话</td>
            <td>报警电话</td>
          </tr>
          <tr class="text-c">
            <td>119</td>
            <td>120</td>
            <td>120</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</body>
</html>