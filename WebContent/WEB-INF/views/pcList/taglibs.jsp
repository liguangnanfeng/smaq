<%@ page language="java" errorPage="/WEB-INF/views/error.jsp" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tld/lanlin.tld" prefix="lanlin" %>
<%-- base前段 绝对路径统一进行引入--%>
<c:set var="ly" value="${pageContext.request.contextPath}"/>
<c:set var="ab" value='<%=request.getServletContext().getRealPath("/") %>' /> 

