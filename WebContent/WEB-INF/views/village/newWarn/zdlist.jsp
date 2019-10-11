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
<link href="${ly }/js/plugins/laypage/skin/laypage.css" rel="stylesheet"/>
<meta name="keywords" content="安全分级管控   隐患排查治理智能化信息平台">
<meta name="description" content="安全分级管控   隐患排查治理智能化信息平台">
<style type="text/css">
body .dis-ib{margin-right:15px;}
.laypage_main a, .laypage_main span {
    margin-left: 5px;
    background-color: #fff;
    width: auto;
    line-height: 33px;
    padding: 4px 2px;
    border: 1px solid #d2d2d2;
    border-radius: 2px;
    text-align: center;
    min-width: 33px;
    line-height:20px !important;
    height:20px !important;
}
</style>
</head>
<body>
<c:if test="${yzz != 1 }">
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
    <span class="c-gray en">&gt;</span> <span>安全监管</span>
    <span class="c-gray en">&gt;</span> <span>重大危险源</span>
    <%--<c:if test="${not empty from && from == '1' }">--%>
    	<%--<a class="btn btn-success radius r" style="line-height: 1.6em;    margin-left: 10px; margin-top: 3px" href="${ly }/village/company/toZdCompanyList" title="返回">--%>
    		<%--返回--%>
    	<%--</a>--%>
    <%--</c:if>--%>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
    <a onclick="back()" class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:void(0)" title="返回">返回</a>
  </nav>
</c:if>
<c:if test="${yzz == 1 }">
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
    <span class="c-gray en">&gt;</span> <span>重大危险源源长制</span>
    <span class="c-gray en">&gt;</span> <span>管控措施</span>
    <span class="c-gray en">&gt;</span> <span>在线监测</span>
    <span class="c-gray en">&gt;</span> <span>报警栏信息</span>
    <%--<c:if test="${not empty from && from == '1' }">--%>
    	<%--<a class="btn btn-success radius r" style="line-height: 1.6em;    margin-left: 10px; margin-top: 3px" href="${ly }/village/company/toZdCompanyList" title="返回">--%>
    		<%--返回--%>
    	<%--</a>--%>
    <%--</c:if>--%>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
    <a onclick="back()" class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:void(0)" title="返回">返回</a>

  </nav>
</c:if>
  <div class="page-container">

  	<div class="text-c">
     <%--  <form action="${ly }/village/company/company-list-zg?hazard=1" method="post"> --%>
        <div class="dis-ib">
        <span>报警类型：</span>
        <select id="typeSelect" class="input-text mb-5 mt-5" style="width:230px;">
        	<option value="">全部</option>
        	<c:forEach items="${typeList }" var="tl">
        		<option value="${tl.typeCode }">${tl.typeName }报警</option>
        	</c:forEach>
        </select>
        <!-- <input  type="text" value="" name="companyName" id="searchCompany" class="input-text mb-5 mt-5" style="width:230px;"> -->
      </div>
        <!-- <button class="btn btn-success inline" type="button" id="toSearchCompany">
          <i class="Hui-iconfont">&#xe665;</i> 查询
        </button> -->
      <!-- </form> -->
    </div>

    <div class="text-c" style="display:none;">
     <%--  <form action="${ly }/village/company/company-list-zg?hazard=1" method="post"> --%>
        <div class="dis-ib">
        <span>企业名称：</span>
        <input  type="text" value="" name="companyName" id="searchCompany" class="input-text mb-5 mt-5" style="width:230px;">
      </div>
        <button class="btn btn-success inline" type="button" id="toSearchCompany">
          <i class="Hui-iconfont">&#xe665;</i> 查询
        </button>
      <!-- </form> -->
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="r">共有数据：<strong id="showTotal">0</strong> 条</span>
	</div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <c:if test="${sessionScope.session_user.userType != 5 }"><th width="15%">企业名称</th></c:if>
            <th width="15%">传感器名称</th>
            <th width="15%">报警场所</th>
            <th width="15%">报警种类</th>
            <th width="15%">报警类型</th>
            <th width="15%">报警时间</th>
          </tr>
        </thead>
        <tbody id="showListBody">
          <!-- 循环-->
          <%-- <c:forEach items="${list }" varStatus="index" var="se">
          <tr class="text-c">
            <td>${index.index + 1 }</td>
            <td>${se.name}</td>
            <td>${se.material }</td>
            <td>${se.maximumStorage }</td>
            <td>${se.state }</td>
          </tr>
          </c:forEach> --%>
          <!-- 循环结束 -->
        </tbody>
      </table>
      <div id="demo11" style="text-align:center;padding-top: 8px;"></div>
    </div>
  </div>
  <input type="hidden" id="companyId" value="${companyId }"/>
  <input type="hidden" id="typeId" value="${sessionScope.session_user.userType }"/>
<script type="text/javascript" src="${ly }/js/plugins/juicer/juicer.js"></script>
<script type="text/javascript" src="${ly }/js/plugins/laypage/laypage.js"></script>
<script type="text/javascript" src="${ly }/js/modules/common/common.js"></script>
<script type="text/javascript" src="${ly }/js/modules/newWarn/zdlist.js"></script>
<script type="type/juicer" id="listTemplate">
{@each list as m,index}
    <tr class="text-c">
		  <td>$<{m.index}></td>
          {@if m.isCompany != 1}<td>$<{m.companyName}></td>{@/if}
          <td>$<{m.tsName}></td>
          <td>$<{m.site}></td>
		  <td>$<{m.typeName|subname}></td>
		  <td>
			{@if m.warn == 1}
				低于下限预警
			{@else if m.warn == 2}
				高于上线预警
			{@else if m.warn == 3}
				低于下限报警
			{@else if m.warn == 4}
				低于上线报警
			{@else}
				--
			{@/if}
		  </td>
          <td>$<{m.warnTime}></td>
     </tr>
{@/each}
</script>
</body>
</html>