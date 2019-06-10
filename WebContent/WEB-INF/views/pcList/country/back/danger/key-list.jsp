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
<title>安全分级管控智能化信息平台</title>
<meta name="keywords" content="安全分级管控智能化信息平台">
<meta name="description" content="安全分级管控智能化信息平台">
<script type="text/javascript">
  $(function(){
    $(".three_dtitle").on("click",function(){
      if($(this).parent().hasClass("three_dangerxz")){
        $(this).parent().removeClass("three_dangerxz");
        $(this).find(".Hui-iconfont").html("&#xe604;");
      }else{
        $(this).parent().addClass("three_dangerxz");
        $(this).find(".Hui-iconfont").html("&#xe631;");
      }
    });
    
    $(".two_dtitle").on("click",function(){
      if($(this).parent().hasClass("two_dangerxz")){
        $(this).parent().removeClass("two_dangerxz");
        $(this).parent().find(".Hui-iconfont").html("&#xe604;");
        $(this).closest(".two_danger").find(".three_danger").removeClass("three_dangerxz");
      }else{
        $(this).parent().addClass("two_dangerxz");
        $(this).find(".Hui-iconfont").html("&#xe631;");
      }
    });
    
    $(".one_dtitle").on("click",function(){
      if($(this).parent().hasClass("one_dangerxz")){
        $(this).parent().removeClass("one_dangerxz");
        $(this).parent().find(".Hui-iconfont").html("&#xe604;");
        $(this).parent().find(".three_danger").removeClass("three_dangerxz");
        $(this).parent().find(".two_danger").removeClass("two_dangerxz");
      }else{
        $(this).parent().addClass("one_dangerxz");
        $(this).find(".Hui-iconfont").html("&#xe631;");
      }
    });
  })
</script> 
</head>
<body>
  <nav class="breadcrumb">
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
    <a class="btn btn-primary radius mb-20" type="button" style="margin-left:50px; margin-top: 3px" href="javascript:show_dialog('编辑','/back/danger-editor')">编辑</a>
  </nav>
  <div class="page-container">
    <div class="list_danger">
      <!-- 包含三级 -->
      <!-- 循环 -->
      <c:set value="${fn:split(' /基础管理-/现场管理-/高危作业-', '/') }" var="x"/>
      <c:forEach items="${TIndustry }" var="be">
      <div class="one_danger">
        <div class="one_dtitle">
          <i class="Hui-iconfont">&#xe604;</i>
            <p>${x[be.type]}${be.name }</p>
        </div>
        <div class="list_odan">
          <c:forEach items="${level }" var="l">
          <c:if test="${be.id == l.industryId}">
          <div class="two_danger">
            <div class="two_dtitle">
              <i class="Hui-iconfont">&#xe604;</i>
              <p>${l.level1 }/${l.level2 }<c:if test="${not empty l.level3}">/${l.level3 }</c:if></p>
            </div>
            <div class="list_twdan">
             <c:forEach items="${keywords }" var="list1">
              <c:if test="${list1.levelid == l.levelid}">
              <div class="three_danger">
                <div class="three_dtitle">
                  <bb>&nbsp;</bb>
                  <p>${list1.keywords }</p>
                </div>
              </div>
              </c:if>
              </c:forEach>
            </div>
          </div>
          </c:if>
          </c:forEach>
        </div>
      </div>
      <!-- 循环结束 -->
      </c:forEach>
      
      <div class="one_danger">
        <div class="one_dtitle">
          <i class="Hui-iconfont">&#xe604;</i>
            <p>特种设备 </p>
        </div>
        <div class="list_odan">
          <c:forEach items="${level }" var="l">
          <c:if test="${empty l.industryId}">
          <div class="two_danger">
            <div class="two_dtitle">
              <i class="Hui-iconfont">&#xe604;</i>
              <p>${l.level1 }/${l.level2 }<c:if test="${not empty l.level3}">/${l.level3 }</c:if></p>
            </div>
            <div class="list_twdan">
             <c:forEach items="${keywords }" var="list1">
              <c:if test="${list1.levelid == l.levelid}">
              <div class="three_danger">
                <div class="three_dtitle">
                  <bb>&nbsp;</bb>
                  <p>${list1.keywords }</p>
                </div>
              </div>
              </c:if>
              </c:forEach>
            </div>
          </div>
          </c:if>
          </c:forEach>
        </div>
      </div>
    </div>
  </div>
</body>

</html>