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
</head>
<body>
	<nav class="breadcrumb">
	    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
	    <span class="c-gray en">&gt;</span> <span>风险研判与承诺公告</span> 
		<span class="c-gray en">&gt;</span> <span>运行管理</span> 
	    <span class="c-gray en">&gt;</span> <span>档案管理</span> 
	 </nav>
  <div class="page-container">
	    <div class="cl pd-5 bg-1 bk-gray mt-20">
	      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span>
	    </div>
	    <div class="bd">
	        <ul>
                <li>
                    <div class="mt-20">
                      <table class="table table-border table-bordered table-bg table-hover table-sort">
                        <thead>
                          <tr class="text-c">
                            <th width="5%">编号</th>
                            <th>名称</th>
                            <th>操作</th>
                          </tr>
                        </thead>
                        <tbody>
                          <!-- 循环-->
                          <c:forEach items="${list }" varStatus="index" var="list">        
                            <tr class="text-c">
                            <td>${index.index+1 }</td>
                            <td>
                            	<c:if test="${type==1}">动火作业证_<fmt:formatDate value="${list.ctime }" pattern="yyyy-MM-dd HH:mm"/></c:if>
                            	<c:if test="${type==2}">受限空间安全作业证_<fmt:formatDate value="${list.ctime }" pattern="yyyy-MM-dd HH:mm"/></c:if>
                            	<c:if test="${type==3}">盲板抽堵安全作业证_<fmt:formatDate value="${list.ctime }" pattern="yyyy-MM-dd HH:mm"/></c:if>
                            	<c:if test="${type==4}">高处安全作业证_<fmt:formatDate value="${list.ctime }" pattern="yyyy-MM-dd HH:mm"/></c:if>
                            	<c:if test="${type==5}">吊装安全作业证_<fmt:formatDate value="${list.ctime }" pattern="yyyy-MM-dd HH:mm"/></c:if>
                            	<c:if test="${type==6}">临时用电安全作业证_<fmt:formatDate value="${list.ctime }" pattern="yyyy-MM-dd HH:mm"/></c:if>
                            	<c:if test="${type==7}">动土安全作业证_<fmt:formatDate value="${list.ctime }" pattern="yyyy-MM-dd HH:mm"/></c:if>
                            	<c:if test="${type==8}">断路安全作业证_<fmt:formatDate value="${list.ctime }" pattern="yyyy-MM-dd HH:mm"/></c:if>
                            	<c:if test="${type==9}">对检修作业现场进行安全检查的记录_<fmt:formatDate value="${list.ctime }" pattern="yyyy-MM-dd HH:mm"/></c:if>
                            	<c:if test="${type==10}">承包商评价表_<fmt:formatDate value="${list.ctime }" pattern="yyyy-MM-dd HH:mm"/></c:if>
                            	<c:if test="${type==11}">安全风险研判目标管理履职考核_<fmt:formatDate value="${list.ctime }" pattern="yyyy-MM-dd HH:mm"/></c:if>
                            	<c:if test="${type==12}">固有风险研判_<fmt:formatDate value="${list.ctime }" pattern="yyyy-MM-dd HH:mm"/></c:if>
                            	<c:if test="${type==13}">临时风险研判_<fmt:formatDate value="${list.ctime }" pattern="yyyy-MM-dd HH:mm"/></c:if>
                            	<c:if test="${type==14}">重大、较大隐患治理风险研_<fmt:formatDate value="${list.ctime }" pattern="yyyy-MM-dd HH:mm"/></c:if>
                            	<c:if test="${type==15}">安全承诺公告牌_<fmt:formatDate value="${list.ctime }" pattern="yyyy-MM-dd HH:mm"/></c:if>
                            </td>                          	
                            <td>
                            	<c:if test="${type==1}"><a href="${ly }/company/study/files-detail?id=${list.id }&type=${type}">查看详情</a></c:if>
                            	<c:if test="${type==2}"><a href="${ly }/company/study/files-detail?id=${list.id }&type=${type}">查看详情</a></c:if>
                            	<c:if test="${type==3}"><a href="${ly }/company/study/files-detail?id=${list.id }&type=${type}">查看详情</a></c:if>
                            	<c:if test="${type==4}"><a href="${ly }/company/study/files-detail?id=${list.id }&type=${type}">查看详情</a></c:if>
                            	<c:if test="${type==5}"><a href="${ly }/company/study/files-detail?id=${list.id }&type=${type}">查看详情</a></c:if>
                            	<c:if test="${type==6}"><a href="${ly }/company/study/files-detail?id=${list.id }&type=${type}">查看详情</a></c:if>
                            	<c:if test="${type==7}"><a href="${ly }/company/study/files-detail?id=${list.id }&type=${type}">查看详情</a></c:if>
                            	<c:if test="${type==8}"><a href="${ly }/company/study/files-detail?id=${list.id }&type=${type}">查看详情</a></c:if>
                            	<c:if test="${type==9}"><a href="${ly }/company/study/files-detail?id=${list.id }&type=${type}">查看详情</a></c:if>
                            	<c:if test="${type==10}"><a href="${ly }/company/study/files-detail?id=${list.id }&type=${type}">查看详情</a></c:if>
                            	<c:if test="${type==11}"><a href="${ly }/company/study/files-detail?id=${list.id }&type=${type}">查看详情</a></c:if>
          <%--固有风险研判 --%>  <c:if test="${type==12}"><a href="${ly }/company/study/naturaldanger-show?id=${list.id }">查看详情</a></c:if>
                            	<c:if test="${type==13}"><a href="${ly }/company/study/temporaryrisk-show?id=${list.id }">查看详情</a></c:if>
                            	<c:if test="${type==14}"><a href="${ly }/company/study/greatdanger-show?id=${list.dangerstudyid }">查看详情</a></c:if>
                            	<c:if test="${type==15}"><a href="${ly }/company/study/files-detail?id=${list.id }&type=${type}">查看详情</a></c:if>
                            </td>
                            </tr>
                           </c:forEach>
                          <!-- 循环结束 -->
                        </tbody>
                      </table>
                    </div>
                </li>
            </ul>
	    </div>
  </div>
</body>
<script type="text/javascript">
$(function() {
  $('.table-sort').dataTable({
    "aaSorting": [[0, "asc"]],//默认第几个排序
    "bStateSave": false,//状态保存
    "aoColumnDefs": [
    ]
    });
});
</script>
<script type="text/javascript" src="/js/fxgk/tbl-rowspan-reset.js"></script>
</html>