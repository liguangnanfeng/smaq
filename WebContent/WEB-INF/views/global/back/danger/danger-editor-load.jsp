<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<%@ include file="/WEB-INF/inc/back-header.inc"%>
</head>
<body>
<div id="load_">
<div class="row cl">
  <label class="form-label col-xs-4 col-sm-2">检查项：</label>
  <div class="formControls col-xs-8 col-sm-9">
    <input class="btn btn-primary radius" type="button" value="添加" id="add_jcx"/>
    
    <c:forEach items="${list }" var="be">
      <div class="one_jcx" data-id="${be.id }">
          <textarea class="textarea txtarea_sq" disabled="disabled">${be.content }</textarea>
          <textarea class="textarea txtarea_sq" disabled="disabled" style="margin-left: 10px;">${be.reference }</textarea>
          <input class="btn btn-primary-outline radius ml-10 edit-jcx" type="button" value="编辑"/>
          <input class="btn btn-success-outline radius ml-10 save-jcx" type="button" value="保存" style="display: none;"/>
          <input class="btn btn-danger-outline radius ml-10 del-jcx" type="button" value="删除"/>
      </div>
    </c:forEach>
  </div> 
</div>

<div class="row cl">
  <label class="form-label col-xs-4 col-sm-2">重大隐患关键词：</label>
  <div class="formControls col-xs-8 col-sm-9">
    <input class="btn btn-primary radius" type="button" value="添加" id="add_zd"/>
    <c:forEach items="${sList }" var="be">
      <div class="one_jcx" data-id="${be.id }">
          <textarea class="textarea txtarea_sq" disabled="disabled">${be.keywords }</textarea>
          <input class="btn btn-primary-outline radius ml-10 edit-zd" type="button" value="编辑"/>
          <input class="btn btn-success-outline radius ml-10 save-zd" type="button" value="保存" style="display: none;"/>
          <input class="btn btn-danger-outline radius ml-10 del-zd" type="button" value="删除"/>
      </div>
    </c:forEach>
  </div> 
</div>
</div>
</html>