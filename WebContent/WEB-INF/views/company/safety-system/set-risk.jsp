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
</style>
<script type="text/javascript">
  $(function(){
    $("[data-toggle='tooltip']").tooltip()
  })
  
  //type 1 物理因素 2 化学因素 3 工艺 4 物料辨识
  function redit(id, type) {
    var titles;
    titles = ['',"职业危害物理因素选择",'职业危害化学因素选择','职业危害高危工艺选择','物料辨识选择'];
    show_dialog(titles[type], '/company/safety-system/set-choose?id=' + id + "&type=" + type);
  }
</script>
</head>
<body>
  <div class="page-container">
    <!-- 循环分布 -->
    <div class="div-set">
      <div class="top-set">
        <font>职业危害物理因素</font>
        <a href="javascript:void(0)" class="btn_rbj" onclick="redit(${ac.id}, 1)">选择</a>
      </div>
      <!-- 展示模块 -->
      <div class="div-rshow">
        <!-- 循环 -->
        <c:if test="${empty ac.hxys}">
          <div class="one-rshow"><p data-toggle="tooltip" data-placement="top" title="暂无涉及的物理因素">暂无涉及的物理因素</p></div>
        </c:if>
        <c:forEach items="${fn:split(ac.hxys, '!@#') }" var="be">
        <div class="one-rshow"><p data-toggle="tooltip" data-placement="top" title="${be }">${be }</p></div>
        </c:forEach>
        <!-- 循环结束 -->
      </div>
      <!-- 编辑模块 -->
      
    </div>
    
    <div class="div-set">
      <div class="top-set">
        <font>职业危害化学因素</font>
        <a href="javascript:void(0)" class="btn_rbj" onclick="redit(${ac.id}, 2)">选择</a>
      </div>
      <!-- 展示模块 -->
      <div class="div-rshow">
        <!-- 循环 -->
        <c:if test="${empty ac.material}">
          <div class="one-rshow"><p data-toggle="tooltip" data-placement="top" title="暂无涉及的化学因素">暂无涉及的化学因素</p></div>
        </c:if>
        <c:forEach items="${fn:split(ac.material, '!@#') }" var="be">
        <div class="one-rshow"><p data-toggle="tooltip" data-placement="top" title="${be }">${be }</p></div>
        </c:forEach>
        <!-- 循环结束 -->
      </div>
    </div>
    
    <c:if test="${company.industry eq '化工企业（危险化学品生产、经营、使用）、加油站'}">
    <div class="div-set">
      <div class="top-set">
        <font>高危工艺</font>
        <a href="javascript:void(0)" class="btn_rbj" onclick="redit(${ac.id}, 3)">选择</a>
      </div>
      <!-- 展示模块 -->
      <div class="div-rshow">
        <!-- 循环 -->
        <c:if test="${empty ac.gy}">
          <div class="one-rshow"><p data-toggle="tooltip" data-placement="top" title="暂无涉及的高危工艺">暂无涉及的高危工艺</p></div>
        </c:if>
        <c:forEach items="${fn:split(ac.gy, '!@#') }" var="be">
        <div class="one-rshow"><p data-toggle="tooltip" data-placement="top" title="${be }">${be }</p></div>
        </c:forEach>
        <!-- 循环结束 -->
      </div>
    </div>
    
    <div class="div-set">
      <div class="top-set">
        <font>物料辨识</font>
        <a href="javascript:void(0)" class="btn_rbj" onclick="redit(${ac.id}, 4)">选择</a>
      </div>
      <!-- 展示模块 -->
      <div class="div-rshow">
        <!-- 循环 -->
        <c:if test="${empty ac.wlbs}">
          <div class="one-rshow"><p data-toggle="tooltip" data-placement="top" title="暂无涉及的物料辨识">暂无涉及的物料辨识</p></div>
        </c:if>
        <c:forEach items="${fn:split(ac.wlbs, '!@#') }" var="be">
        <div class="one-rshow"><p data-toggle="tooltip" data-placement="top" title="${be }">${be }</p></div>
        </c:forEach>
        <!-- 循环结束 -->
      </div>
    </div>
    </c:if>
    
    <!-- 循环结束 -->
    <!-- <button onClick="save()" class="btn btn-primary radius mt-20" type="button" style="padding: 0 70px;">
      <i class="Hui-iconfont mr-10">&#xe632;</i>保存
    </button> -->
  </div>
</body>
</html>