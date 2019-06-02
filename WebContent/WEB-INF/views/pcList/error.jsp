<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<style type="text/css">
html,body{margin,padding:0;text-align: center;}
div {width:800px;height:500px;margin-left: auto;margin-right: auto;POSITION: relative;}
</style>
</head>
<body>
  <div>
    出错了
  </div>

  <%-- <% Exception ex = (Exception)request.getAttribute("exception"); %> 
  <%if(ex != null) {
      ex.printStackTrace(new java.io.PrintWriter(out));
    } 
    if (exception != null) {
      exception.printStackTrace(new java.io.PrintWriter(out));
    } else if (request.getAttribute("javax.servlet.error.exception") != null) { 
      ((Exception)request.getAttribute("javax.servlet.error.exception")).printStackTrace(new java.io.PrintWriter(out)); 
    }%> --%>
</body>
</html>
