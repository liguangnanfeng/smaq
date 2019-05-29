<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!-- 循环班组,默认第一个选中 -->
<c:forEach items="${listbz}" var="be" varStatus="index">
  <tr class="text-c dantd dantd2" data-level="${be.level }" data-name="${be.name }" 
                        data-id="${be.id }" data-cid="${be.cid }" data-pid="${be.pid }">
    <td>${be.name }<i class="Hui-iconfont" name="edit" style="right: 38px">&#xe6df;</i> <i class="Hui-iconfont" name="del">&#xe609;</i>
    </td>
  </tr>
</c:forEach>
