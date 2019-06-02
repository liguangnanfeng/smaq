<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width; initial-scale=0; maximum-scale=1.0; user-scalable=yes" name="viewport" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<%@ include file="/WEB-INF/inc/back-header.inc"%>
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style type="text/css">
.one-f a:hover{color:#333;text-decoration: none}
</style>
<script type="text/javascript">
 
</script>
</head>
<body>
  <div class="bt-gangk2">惠山港口危险化学品码头实时监控平台</div>
  <div class="div-gjk">
    <!-- 循环企业 -->
    <!-- 循环结束 -->
    <!-- 循环-->
     <c:forEach items="${list }" varStatus="index" var="be">
     	<a href="${ly}/village/to_monitor-list?userId=${be.userId }&companyName=${be.companyName }" target="_blank" class="one-gjk"><p>${be.companyName }</p></a>
     </c:forEach>
     <a href="" target="_blank" class="one-gjk"></a>
     <a href="" target="_blank" class="one-gjk"></a>
     <a href="" target="_blank" class="one-gjk"></a>
     <a href="" target="_blank" class="one-gjk"></a>
     <a href="" target="_blank" class="one-gjk"></a>
     <a href="" target="_blank" class="one-gjk"></a>
     <a href="" target="_blank" class="one-gjk"></a>
     <a href="" target="_blank" class="one-gjk"></a>
     <a href="" target="_blank" class="one-gjk"></a>
     <a href="" target="_blank" class="one-gjk"></a>
     <a href="" target="_blank" class="one-gjk"></a>
     <a href="" target="_blank" class="one-gjk"></a>
     <a href="" target="_blank" class="one-gjk"></a>
     <a href="" target="_blank" class="one-gjk"></a>
     <a href="" target="_blank" class="one-gjk"></a>
     <a href="" target="_blank" class="one-gjk"></a>
     <a href="" target="_blank" class="one-gjk"></a>
     <a href="" target="_blank" class="one-gjk"></a>
     <a href="" target="_blank" class="one-gjk"></a>
     <!-- 循环结束 -->
  </div>
</body>
</html>