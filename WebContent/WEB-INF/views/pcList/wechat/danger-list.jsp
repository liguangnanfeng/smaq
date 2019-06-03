<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script type="text/javascript" src="${ly}/js/H-ui.admin_v3.0/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="${ly}/js/zoom.min.js"></script>
    
    <link rel="stylesheet" href="${ly}/styles/zoom.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>隐患列表</title>
</head>
<body>
    <div class="container">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">隐患列表</h3>
            </div>
            <div class="panel-body">
                <div class="list">
        			<c:forEach items="${list }" varStatus="index" var="d">
                    <div class="box">
                        <div class="title clearfix">
                            <div class="p1">编号：${d.id }</div>
                            <div class="p2">
                            	<c:if test="${d.status == 2}">不合格</c:if>
                            	<c:if test="${d.status == 3}">已复查</c:if>
                           	</div>
                        </div>
                        <div class="content clearfix">
                        	<c:if test="${ not empty d.files }">
                        		<div class="imgbox">
	                        		<img data-action="zoom" src='${d.files}'>
                        		</div>
                        	</c:if>
                            <p class="text">${d.memo}</p>
                        </div>
                    </div>
                    </c:forEach>
                    <div class="box emptybox">
                        <div class="emptytext">暂无数据</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
	$(function(){
  		if($(".list").children().size() == 1){
	  		$(".emptybox").css("display","block");
  		}
	})
</script>
<style>
    body {
        font-family: "Microsoft YaHei";
    }

    .container {
        padding: 0;
    }

    .panel-heading {
        text-align: center;
        
    }

    .box {
        border: 1px solid #eee;
        border-radius: 2px;
        margin-bottom: 15px;
    }

    .title {
        height: 35px;
        line-height: 35px;
        border-bottom: 1px solid #eee;
        padding: 0 15px;
    }

    .title .p1 {
        float: left;
    }

    .title .p2 {
        color: red;
        float: right;
    }

    .content {
        padding: 15px;
        text-align: center;
    }
    
    .content .imgbox{
    	width: 200px;
        height: 200px;
        margin: 0 auto 15px;
        object-fit: cover;
    }
    
    .content .imgbox img{
        height: 100%;
    }

    .text {
        justify-content: space-between;
        display: table-cell;
    }
    
    .emptybox {
        display: none;
        text-align: center;
    }
    
    .emptybox{
    	line-height: 60px;
    }
    .container img {
   		width: 100%;
    }
</style>

</html>