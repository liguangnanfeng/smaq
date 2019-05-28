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
.ter2{width:10%}
.tab_tmr2{width:15%}
</style>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>安全生产标准化</span> 
    <span class="c-gray en">&gt;</span> <span>安全生产管理制度</span>
    <span class="c-gray en">&gt;</span> <span>规章制度</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l">
        <a class="btn btn-primary radius" data-title="修改规章制度" data-href="${ly }/company/rules-institution-change" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont" style="font-size:15px;">&#xe6df;</i> 规章制度编辑</a>
      </span>
    </div>
    <div class="mt-20" style="width:80%">
      <table class="table table-border table-bordered radius">
        <tr>
          <td class="active text-r ter2">管理制度</td>
          <td class="tab_tmr2">
          <c:if test="${r.managementSystem==0 }">无</c:if>
          <c:if test="${r.managementSystem==1 }">有</c:if>
          </td>
          <td class="active text-r ter2">是否在有效期内</td>
          <td class="tab_tmr2">
          <c:if test="${r.misInforce==0 }">否</c:if>
          <c:if test="${r.misInforce==1 }">是</c:if>
          </td>
          <td class="active text-r ter2">操作规程</td>
          <td class="tab_tmr2">
          <c:if test="${r.regulations==0 }">无</c:if>
          <c:if test="${r.regulations==1 }">有</c:if>
          </td>
          <td class="active text-r ter2">是否在有效期内</td>
          <td class="tab_tmr2">
          <c:if test="${r.risInforce==0 }">否</c:if>
          <c:if test="${r.risInforce==1 }">是</c:if>
          </td>
        </tr>
        <tr>
          <td class="active text-r ter2">安全生产责任制</td>
          <td class="tab_tmr2">
          <c:if test="${r.safety==0 }">无</c:if>
          <c:if test="${r.safety==1 }">有</c:if>
          </td>
          <td class="active text-r ter2">是否在有效期内</td>
          <td class="tab_tmr2">
          <c:if test="${r.sisInforce==0 }">否</c:if>
          <c:if test="${r.sisInforce==1 }">是</c:if>
          </td>
          <td class="active text-r ter2">责任制落实情况</td>
          <td class="tab_tmr2" colspan="3">
          <c:if test="${r.implementation==0 }">否</c:if>
          <c:if test="${r.implementation==1 }">是</c:if>
          </td>
        </tr>
        <tr>
          <td class="active text-r ter2">应急预案</td>
          <td class="tab_tmr2">
          <c:if test="${r.contingency==0 }">无</c:if>
          <c:if test="${r.contingency==1 }">有</c:if>
          </td>
          <td class="active text-r ter2">是否在有效期内</td>
          <td class="tab_tmr2" colspan="5">
          <c:if test="${r.cisInforce==0 }">否</c:if>
          <c:if test="${r.cisInforce==1 }">是</c:if>
          </td>
        </tr>
      </table>
    </div>
  </div>
</body>
</html>