<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!-- 循环部门,默认第一个选中 -->
<%--企业信息-->组织架构 --> 显示的车间信息 --%>
<c:forEach items="${listbm}" var="be" varStatus="index">
  <tr class="text-c dantd dantd1" data-level="${be.level }" data-name="${be.name }" 
                        data-id="${be.id }" data-cid="${be.cid }" data-pid="${be.id }">
    <td>${be.name }
    <i class="Hui-iconfont" style="right: 38px" name="edit">&#xe6df;</i> <i class="Hui-iconfont" name="del">&#xe609;</i>
    </td>
  </tr>
</c:forEach>
