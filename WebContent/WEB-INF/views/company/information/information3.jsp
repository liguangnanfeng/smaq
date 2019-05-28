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
.ter2{width:20%}
.tab_tmr2{width:30%}
</style>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>安全生产标准化</span>
    <span class="c-gray en">&gt;</span><span>安全生产管理档案</span>
    <span class="c-gray en">&gt;</span> <span>两重点一重大</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l">
        <a class="btn btn-primary radius" data-title="修改两重点一重大" data-href="${ly }/company/information/information-edit3" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont" style="font-size:15px;">&#xe6df;</i> 修改两重点一重大</a>
      </span>
    </div>
    <div class="mt-20" style="width:90%">
      <div class="Huialert Huialert-info" style="margin-top:20px;"><i class="icon-remove"></i>重点监管危险化学品</div>
      <table class="table table-border table-bordered">
        <tr>
          <td class="active text-r ter2">是否有重点监管危险化学品</td>
          <td class="tab_tmr2">
            <c:if test="${r.chemic==0 }">否</c:if>
            <c:if test="${r.chemic==1 }">是</c:if>
          </td>
          <!-- 判断是显示 -->
          <c:if test="${r.chemic==1 }">
          <td class="active text-r ter2">是否满足自控要求</td>
          <td class="tab_tmr2"><c:if test="${r.chemicControl==0 }">否</c:if>
            <c:if test="${r.chemicControl==1 }">是</c:if></td>
          </c:if>
        </tr>
        <c:if test="${r.chemic==1 }">
        <tr>
          <td class="active text-r ter2">物质名称</td>
          <td class="tab_tmr2" colspan="3">${r.chemicName }</td>
        </tr>
        </c:if>
      </table>
      <div class="Huialert Huialert-info" style="margin-top:20px;"><i class="icon-remove"></i>重点监管危险化工工艺</div>
      <table class="table table-border table-bordered">
        <tr>
          <td class="active text-r ter2">是否有重点监管危险化工工艺</td>
          <td class="tab_tmr2">
            <c:if test="${r.process==0 }">否</c:if>
            <c:if test="${r.process==1 }">是</c:if>
          </td>
          <!-- 判断是显示 -->
          <c:if test="${r.process==1 }">
          <td class="active text-r ter2">是否满足自控要求</td>
          <td class="tab_tmr2"><c:if test="${r.processControl==0 }">否</c:if>
            <c:if test="${r.processControl==1 }">是</c:if></td>
          </c:if>
        </tr>
        <c:if test="${r.process==1 }">
        <tr>
          <td class="active text-r ter2">备案日期</td>
          <td class="tab_tmr2" colspan="3">${r.processRecordDate }</td>
        </tr>
        <tr>
          <td class="active text-r ter2">工艺名称</td>
          <td class="tab_tmr2" colspan="3">${r.processName }</td>
        </tr>
        </c:if>
      </table>
      <div class="Huialert Huialert-info" style="margin-top:20px;"><i class="icon-remove"></i>重大危险源</div>
      <table class="table table-border table-bordered">
        <tr>
          <td class="active text-r ter2">是否构成重大危险源</td>
          <td class="tab_tmr2">
            <c:if test="${r.cisDanger==0 }">否</c:if>
            <c:if test="${r.cisDanger==1 }">是</c:if>
          </td>
          <!-- 判断是显示 -->
          <c:if test="${r.cisDanger==1 }">
          <td class="active text-r ter2">重大危险源等级</td>
          <td class="tab_tmr2">${r.dangerLvl }</td>
          </c:if>
        </tr>
        <c:if test="${r.cisDanger==1 }">
        <tr>
          <td class="active text-r ter2">备案日期</td>
          <td class="tab_tmr2">${r.dangerRecordDate }</td>
          <td class="active text-r ter2">有效日期</td>
          <td class="tab_tmr2">${r.dangerEndDate }</td>
        </tr>
        </c:if>
      </table>
      <table class="table table-border table-bg table-bordered mt-20">
        <thead>
          <tr class="text-c">
            <th width="10%">序号</th>
            <th width="30%">主要物质</th>
            <th width="30%">现场理论最大存量</th>
            <th width="30%">使用/储存装置</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环检查项目-->
          <c:forEach items="${fn:split(r.dangerMaterial, ',') }" var="be" varStatus="index">
          <tr class="text-c" >
            <td>${index.index + 1}</td>
            <td>${fn:split(be, '/')[0] }</td>
            <td>${fn:split(be, '/')[1] }</td>
            <td>${fn:split(be, '/')[2] }</td>
          </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
      
      
      
      <%-- <table class="table table-border table-bordered radius">
        <tr>
          <td class="active text-r ter2">是否构成重大危险源</td>
          <td class="tab_tmr2">
          <c:if test="${r.cisDanger==0 }">否</c:if>
          <c:if test="${r.cisDanger==1 }">是</c:if>
          </td>
          <td class="active text-r ter2">重大危险源等级</td>
          <td class="tab_tmr2">${r.dangerLvl }</td>
          <td class="active text-r ter2">重点监管的危险化工工艺</td>
          <td class="tab_tmr2">
          <c:if test="${r.process==0 }">否</c:if>
          <c:if test="${r.process==1 }">是</c:if>
          </td>
        </tr>
        <tr>
          <td class="active text-r ter2">工艺名称</td>
          <td class="tab_tmr2">${r.processName }</td>
          <td class="active text-r ter2">重点监管的危险化学品</td>
          <td class="tab_tmr2">
          <c:if test="${r.chemic==0 }">否</c:if>
          <c:if test="${r.chemic==1 }">是</c:if>
          </td>
          <td class="active text-r ter2">涉及危险化学品名称</td>
          <td class="tab_tmr2" colspan="3">${r.chemicName }</td>
        </tr>
        <tr>
          <td class="active text-r ter2">工艺流程</td>
          <td class="tab_tmr2" colspan="7"><img style="height:180px;width:150px" alt="" src="${r.processImg}"></td>
        </tr>
      </table> --%>
    </div>
  </div>
</body>
</html>