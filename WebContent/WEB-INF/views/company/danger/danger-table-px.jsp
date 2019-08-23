	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
		<%@ include file="/WEB-INF/views/taglibs.jsp" %>
		<%@ include file="/WEB-INF/inc/back-header.inc" %>
		<!DOCTYPE html>
		<html>
		<head>
		<meta charset="utf-8">
		<title></title>
		<style>
		table {
		border-collapse: collapse;
		width: 100%;
		}

		table,
		tr,
		td {
		border: 1px solid #ddd;
		}

		td {
		text-align: center;
		height: 38px;
		position: relative;
		}

		td[class=first] {
		width: 155px;
		}

		td[class=first]:before {
		content: "";
		position: absolute;
		width: 1px;
		height: 160px;
		top: 0;
		left: 0;
		background-color: #ddd;;
		display: block;
		transform: rotate(-77deg);
		transform-origin: top;
		}

		.title1 {
		position: absolute;
		top: 6px;
		right: 7px;
		}

		.title2 {
		position: absolute;
		top: 5px;
		right: 7px;
		}

		.title3 {
		position: absolute;
		top: 20px;
		left: 10px;
		}

		.center {
		text-align: center;
		}

		.flexcenter>span {
		display: flex;
		justify-content: center;
		margin: 15px 0;
		}

		.flexspa {
		display: flex;
		justify-content: space-between;
		margin: 15px 5px;
		font-size: 18px;

		}
		.th{
		background-color: #F5FAFE;
		font-weight: bold;
		}
		.containerx{
		padding: 20px;
		}
		</style>

		</head>
		<body>
		<script>
		console.log('${list}');
     //获取日期与时间

		</script>
		<div class="containerx">
		<section class="title">
		<div class="flexspa">
		<span>部门抽查绩效分析 </span>
		<span id="time">

	</span>
		</div>
		</section>
		<table class=".table"  style="margin: auto;" cellpadding="2">
		<tr class="th">
		<td>序号</td>
		<td class="first"><span class="title1">类型</span><br><span class="title3">检查部门</span></td>
		<td>现场</td>
		<td>基础</td>
		<td>合计</td>
		<td>同比%</td>
		</tr>
		<script>
		window.a=0;
		</script>

		<c:forEach items="${list}" varStatus="index" var="be">
			<c:if test="${index.index<fn:length(list)-1 }">
		<tr>

		<td>${index.index + 1}</td>
		<td>${be.name}</td>
		<td>${be.danger1}</td>
		<td>${be.danger2}</td>
		<td>${be.sum}</td>
		<td></td>
           <script>
				window.a=window.a+'${be.sum}'*1;
				console.log('${be.sum}');
				</script>
		</tr>
		</c:if>

		<c:if test="${index.index>fn:length(list)-2 }">
		<tr>
		<td>合计</td>
		<td>---</td>
		<td>${be.count1}</td>
		<td>${be.count2}</td>
		<td class="tdd"></td>
		<td></td>

		</tr>

		</c:if>
		<c:if test="${index.index>fn:length(list)-2 }">
		<tr>
		<td>占比%</td>
		<td>---</td>
		<td>${be.result1}</td>
		<td>${be.result2}</td>
		<td>---</td>
		<td></td>
		</tr>
		</c:if>

		</c:forEach>
		</table>
		</div>
		</body>

		<script>

		var endTime = new Date(new Date().getTime());
		console.log(endTime);
		var myDate = new Date();
		var mytime=myDate.toLocaleTimeString();     //获取当前时间
		var a=myDate.toLocaleDateString();
		$("#time").text('时间：'+a);
		</script>
		</html>