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
body .dis-ib{margin-right:15px;}
body .cl .l .btn-primary{width:240px}
.slideTxtBox .hd{text-align: center;margin-top: 20px;}
.slideTxtBox .hd li{line-height: 22px;}
.slideTxtBox .hd .on{background-color: #429842;color: #fff;}
</style>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>行政执法系统</span> 
    <span class="c-gray en">&gt;</span> <span>执法档案</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <c:if test="${session_user.userType != 5}">
    <div class="text-c">
      <form action="" method="post">
        <div class="dis-ib">
          <span>企业名称：</span>
          <input type="text" value="${companyName }" name="companyName" class="input-text mb-5 mt-5" style="width:150px;">
          </div>
        <button class="btn btn-success" type="submit">
          <i class="Hui-iconfont">&#xe665;</i> 查询
        </button>
      </form>
    </div>
    </c:if>
    
    <div class="slideTxtBox">
	    <div class="hd">
	        <ul class="btn-group mb-20">
	        	<li class="btn btnyh btnyhxz radius btn_per">现场检查记录</li>
	        	<li class="btn btnyh radius btn_per">整改指令书</li>
	        	<li class="btn btnyh radius btn_per">复查意见书</li>
	        	<li class="btn btnyh radius btn_per">行政处罚告知书</li>
	        	<li class="btn btnyh radius btn_per">行政（当场）处罚决定书（单位）</li>
	        	<li class="btn btnyh radius btn_per">行政（当场）处罚决定书（个人）</li>
	        	<li class="btn btnyh radius btn_per">行政处罚决定书（单位）</li>
	        	<li class="btn btnyh radius btn_per">行政处罚决定书（个人）</li>
	       	</ul>
	    </div>
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
                            <th>企业名称</th>
                            <th>摘要</th>
                            <th>创建时间</th>
                            <th>操作</th>
                          </tr>
                        </thead>
                        <tbody>
                          <!-- 循环-->
                          <c:forEach items="${list }" varStatus="index" var="list">
                            <c:if test="${list.flag == 8}">
                            <tr class="text-c">
                            <td>${index.index+1 }</td>
                            <td>${list.companyName }</td>
                            <td><lanlin:htmlToText length="200" value="${list.copy }"/></td>
                            <td><fmt:formatDate value="${list.createTime }" pattern="yyyy-MM-dd"/></td>
                            <td>
                              <a style="text-decoration:none" onClick='show_dialog("行政文书详情", "${ly }/village/danger/opinion-detail?id=${list.id }");' href="javascript:;">查看详情</a><br>
                            </td>
                            </tr>
                            </c:if>
                           </c:forEach>
                          <!-- 循环结束 -->
                        </tbody>
                      </table>
                    </div>
                </li>
            </ul>
	        <ul>
                <li>
                    <div class="mt-20">
                      <table class="table table-border table-bordered table-bg table-hover table-sort">
                        <thead>
                          <tr class="text-c">
                            <th width="5%">编号</th>
                            <th>企业名称</th>
                            <th>摘要</th>
                            <th>创建时间</th>
                            <th>操作</th>
                          </tr>
                        </thead>
                        <tbody>
                          <!-- 循环-->
                          <c:forEach items="${list }" varStatus="index" var="list">
                            <c:if test="${list.flag == 1}">
                            <tr class="text-c">
                            <td>${index.index+1 }</td>
                            <td>${list.companyName }</td>
                            <td><lanlin:htmlToText length="200" value="${list.copy }"/></td>
                            <td><fmt:formatDate value="${list.createTime }" pattern="yyyy-MM-dd"/></td>
                            <td>
                              <a style="text-decoration:none" onClick='show_dialog("行政文书详情", "/village/danger/opinion-detail?id=${list.id }");' href="javascript:;">查看详情</a><br>
                            </td>
                            </tr>
                            </c:if>
                           </c:forEach>
                          <!-- 循环结束 -->
                        </tbody>
                      </table>
                    </div>
                </li>
            </ul>
	        <ul>
                <li>
                    <div class="mt-20">
                      <table class="table table-border table-bordered table-bg table-hover table-sort">
                        <thead>
                          <tr class="text-c">
                            <th width="5%">编号</th>
                            <th>企业名称</th>
                            <th>摘要</th>
                            <th>创建时间</th>
                            <th>操作</th>
                          </tr>
                        </thead>
                        <tbody>
                          <!-- 循环-->
                          <c:forEach items="${list }" varStatus="index" var="list">
                            <c:if test="${list.flag == 2}">
                            <tr class="text-c">
                            <td>${index.index+1 }</td>
                            <td>${list.companyName }</td>
                            <td><lanlin:htmlToText length="200" value="${list.copy }"/></td>
                            <td><fmt:formatDate value="${list.createTime }" pattern="yyyy-MM-dd"/></td>
                            <td>
                              <a style="text-decoration:none" onClick='show_dialog("行政文书详情", "/village/danger/opinion-detail?id=${list.id }");' href="javascript:;">查看详情</a><br>
                            </td>
                            </tr>
                            </c:if>
                           </c:forEach>
                          <!-- 循环结束 -->
                        </tbody>
                      </table>
                    </div>
                </li>
            </ul>
          
	        <ul>
	            <li>
	            	<div class="mt-20">
				      <table class="table table-border table-bordered table-bg table-hover table-sort">
				        <thead>
				          <tr class="text-c">
				            <th width="5%">编号</th>
                            <th>企业名称</th>
                            <th>摘要</th>
                            <th>创建时间</th>
                            <th>操作</th>
				          </tr>
				        </thead>
				        <tbody>
				          <!-- 循环-->
				          <c:forEach items="${list }" varStatus="index" var="list">
                            <c:if test="${list.flag == 3}">
                            <tr class="text-c">
                            <td>${index.index+1 }</td>
                            <td>${list.companyName }</td>
                            <td><lanlin:htmlToText length="200" value="${list.copy }"/></td>
                            <td><fmt:formatDate value="${list.createTime }" pattern="yyyy-MM-dd"/></td>
                            <td>
                              <a style="text-decoration:none" onClick='show_dialog("行政文书详情", "/village/danger/opinion-detail?id=${list.id }");' href="javascript:;">查看详情</a><br>
                            </td>
                            </tr>
                            </c:if>
                           </c:forEach>
				          <!-- 循环结束 -->
				        </tbody>
				      </table>
				    </div>
	            </li>
	        </ul>
          
            <ul>
                <li>
                    <div class="mt-20">
                      <table class="table table-border table-bordered table-bg table-hover table-sort">
                        <thead>
                          <tr class="text-c">
                            <th width="5%">编号</th>
                            <th>企业名称</th>
                            <th>摘要</th>
                            <th>创建时间</th>
                            <th>操作</th>
                          </tr>
                        </thead>
                        <tbody>
                          <!-- 循环-->
                          <c:forEach items="${list }" varStatus="index" var="list">
                            <c:if test="${list.flag == 4}">
                            <tr class="text-c">
                            <td>${index.index+1 }</td>
                            <td>${list.companyName }</td>
                            <td><lanlin:htmlToText length="200" value="${list.copy }"/></td>
                            <td><fmt:formatDate value="${list.createTime }" pattern="yyyy-MM-dd"/></td>
                            <td>
                              <a style="text-decoration:none" onClick='show_dialog("行政文书详情", "/village/danger/opinion-detail?id=${list.id }");' href="javascript:;">查看详情</a><br>
                            </td>
                            </tr>
                            </c:if>
                           </c:forEach>
                          <!-- 循环结束 -->
                        </tbody>
                      </table>
                    </div>
                </li>
            </ul>
            
            <ul>
                <li>
                    <div class="mt-20">
                      <table class="table table-border table-bordered table-bg table-hover table-sort">
                        <thead>
                          <tr class="text-c">
                            <th width="5%">编号</th>
                            <th>企业名称</th>
                            <th>摘要</th>
                            <th>创建时间</th>
                            <th>操作</th>
                          </tr>
                        </thead>
                        <tbody>
                          <!-- 循环-->
                          <c:forEach items="${list }" varStatus="index" var="list">
                            <c:if test="${list.flag == 5}">
                            <tr class="text-c">
                            <td>${index.index+1 }</td>
                            <td>${list.companyName }</td>
                            <td><lanlin:htmlToText length="200" value="${list.copy }"/></td>
                            <td><fmt:formatDate value="${list.createTime }" pattern="yyyy-MM-dd"/></td>
                            <td>
                              <a style="text-decoration:none" onClick='show_dialog("行政文书详情", "/village/danger/opinion-detail?id=${list.id }");' href="javascript:;">查看详情</a><br>
                            </td>
                            </tr>
                            </c:if>
                           </c:forEach>
                          <!-- 循环结束 -->
                        </tbody>
                      </table>
                    </div>
                </li>
            </ul>
            
            <ul>
                <li>
                    <div class="mt-20">
                      <table class="table table-border table-bordered table-bg table-hover table-sort">
                        <thead>
                          <tr class="text-c">
                            <th width="5%">编号</th>
                            <th>企业名称</th>
                            <th>摘要</th>
                            <th>创建时间</th>
                            <th>操作</th>
                          </tr>
                        </thead>
                        <tbody>
                          <!-- 循环-->
                          <c:forEach items="${list }" varStatus="index" var="list">
                            <c:if test="${list.flag == 6}">
                            <tr class="text-c">
                            <td>${index.index+1 }</td>
                            <td>${list.companyName }</td>
                            <td><lanlin:htmlToText length="200" value="${list.copy }"/></td>
                            <td><fmt:formatDate value="${list.createTime }" pattern="yyyy-MM-dd"/></td>
                            <td>
                              <a style="text-decoration:none" onClick='show_dialog("行政文书详情", "/village/danger/opinion-detail?id=${list.id }");' href="javascript:;">查看详情</a><br>
                            </td>
                            </tr>
                            </c:if>
                           </c:forEach>
                          <!-- 循环结束 -->
                        </tbody>
                      </table>
                    </div>
                </li>
            </ul>
            
            <ul>
                <li>
                    <div class="mt-20">
                      <table class="table table-border table-bordered table-bg table-hover table-sort">
                        <thead>
                          <tr class="text-c">
                            <th width="5%">编号</th>
                            <th>企业名称</th>
                            <th>摘要</th>
                            <th>创建时间</th>
                            <th>操作</th>
                          </tr>
                        </thead>
                        <tbody>
                          <!-- 循环-->
                          <c:forEach items="${list }" varStatus="index" var="list">
                            <c:if test="${list.flag == 7}">
                            <tr class="text-c">
                            <td>${index.index+1 }</td>
                            <td>${list.companyName }</td>
                            <td><lanlin:htmlToText length="200" value="${list.copy }"/></td>
                            <td><fmt:formatDate value="${list.createTime }" pattern="yyyy-MM-dd"/></td>
                            <td>
                              <a style="text-decoration:none" onClick='show_dialog("行政文书详情", "/village/danger/opinion-detail?id=${list.id }");' href="javascript:;">查看详情</a><br>
                            </td>
                            </tr>
                            </c:if>
                           </c:forEach>
                          <!-- 循环结束 -->
                        </tbody>
                      </table>
                    </div>
                </li>
            </ul>
          
	    </div>
	</div>
  </div>
<script type="text/javascript">
$(function() {
  $('.table-sort').dataTable({
    "aaSorting": [[0, "asc"]],//默认第几个排序
    "bStateSave": false,//状态保存
    "aoColumnDefs": [
    ]
    });
});

/*详情*/
function detail(userId,flag,id,content){
  show_dialog('执法文书详情',"/village/administrative/archives-detail?userId="+userId+"&id=" + id + "&flag=" +flag);
  /* window.location.href = getRootPath() + "/village/administrative/archives-detail?userId="+userId+"&id=" + id + "&flag=" +flag; */
}
</script>
<script type="text/javascript">jQuery(".slideTxtBox").slide({trigger:"click"}); </script>
</body>
</html>