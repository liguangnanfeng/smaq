<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<c:forEach items="${list }" var="be">
<div class="radio-box" style="width:30%;padding-left:0px;">
  <input type="radio" name="demo-radio1" value="${be.userId }">
  <label>${be.name }</label>
</div>
</c:forEach>
<c:if test="${empty list}">
<p>没有搜索到企业</p>
</c:if>