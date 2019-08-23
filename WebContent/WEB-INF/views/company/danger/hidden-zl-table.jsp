	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
		<%@ include file="/WEB-INF/views/taglibs.jsp" %>
		<%@ include file="/WEB-INF/inc/back-header.inc" %>
		<!DOCTYPE html>
		<html>
		<head>
		<meta charset="utf-8" />
		<title></title>
		<style>
		table {
		border-collapse: collapse;
		width: 100%;
		}
		.title >div{
		margin: 10px 0;
		}
		table, tr, td {
		border: 1px solid #ddd;
		}

		td {
		text-align: center;
		height: 38px; /*这里需要自己调整，根据自己的需求调整高度*/
		position: relative;
		padding: 0px 14px;
		}
		td[class=first]{
		width: 120px;
		}
		td[class=first]:before {
		content: "";
		position: absolute;
		width: 1px;
		height: 162px;
		top: 0;
		left: 0;
		background-color: #ddd;
		display: block;
		transform: rotate(-63deg);
		transform-origin: top;
		}
		.title{
		margin-top: 43px;
		}
		.title1{
		position: absolute;
		top: 11px;
		right: 7px;
		}
		.title2{
		position: absolute;
		top: 30px;
		left: 6px;
		}
		.title3{
		position: absolute;
		top: 48px;
		left: 10px;
		}
		.center{
		text-align: center;
		}
		.flexcenter{
		display: flex;
		justify-content: center;
		}
		.flexspa{
		display: flex;
		justify-content: space-around;
		}
		.tit {
		background: #F5F9FE;
		font-weight: bold;
		}
		.containerx{
		padding: 0 10px;
		}
		.t{
		background: #F5F9FE;
		}
		.cx{
		padding:0 20px;
		}

		</style>
		</head>
		<body>
		<script>console.log('${list}')</script>
		<div class="cx">
		<section class="title">
		<div class="center">隐患治理数据分析</div>
		<div class="flexspa">
		<span style="visibility: hidden;"></span>
		<span id="time"></span>
		</div>
		</section>
		<table class=".table" style="margin: auto;" cellpadding="2">
		<tr class="t">
		<td rowspan="2">序号</td>
		<td class="first" rowspan="2"><span class="title1">等级</span><br><span class="title3">部门</span></td>
		<td colspan="4">一般隐患</td>
		<td colspan="4">较大隐患</td>
		<td colspan="4">重大隐患</td>
		<td colspan="4">合计</td>
		<td rowspan="2">排名</td>
		</tr>
		<tr class="t">

		<td>已治理</td>
		<td>未治理</td>
		<td>治理率%</td>
		<td>同比%</td>
		<td>已治理</td>
		<td>未治理</td>
		<td>治理率%</td>
		<td>同比%</td>
		<td>已治理</td>
		<td>未治理</td>
		<td>治理率%</td>
		<td>同比%</td>
		<td>已治理</td>
		<td>未治理</td>
		<td>治理率%</td>
		<td>同比%</td>
		</tr>
		<c:forEach items="${list}" varStatus="index" var="be">
			<c:if test="${index.index<fn:length(list)-1 }">
				<tr>
				<td>${index.index + 1}</td>
				<td>${be.name}</td>
				<td>${be.danger1}</td>
				<td>${be.danger11}</td>
				<td>${be.result11}</td>
				<td></td>

				<td>${be.danger2}</td>
				<td>${be.danger22}</td>
				<td>${be.result22}</td>
				<td></td>

				<td>${be.danger3}</td>
				<td>${be.danger33}</td>
				<td>${be.result33}</td>
				<td></td>

				<td>${be.number1}</td>
				<td>${be.number2}</td>
				<td>${be.number}</td>
				<td></td>
				<td></td>
				</tr>
			</c:if>

			<c:if test="${index.index>fn:length(list)-2 }">
				<tr>
				<td>合计</td>
				<td>---</td>
				<td>${be.sign1}</td>
				<td>${be.sign11}</td>
				<td>${be.result1}</td>
				<td></td>

				<td>${be.sign2}</td>
				<td>${be.sign22}</td>
				<td>${be.result2}</td>
				<td></td>

				<td>${be.sign3}</td>
				<td>${be.sign33}</td>
				<td>${be.result3}</td>
				<td></td>

				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				</tr>
			</c:if>
			<c:if test="${index.index>fn:length(list)-2 }">
				<tr>
				<td>占比%</td>
				<td>---</td>
				<td>${be.proportion11}</td>
				<td>${be.proportion44}</td>
				<td></td>
				<td></td>

				<td>${be.proportion22}</td>
				<td>${be.proportion55}</td>
				<td></td>
				<td></td>

				<td>${be.proportion33}</td>
				<td>${be.proportion66}</td>
				<td></td>
				<td></td>

				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>

				</tr>
			</c:if>

		</c:forEach>

		</table>
		<script>

		var endTime = new Date(new Date().getTime());
		console.log(endTime);
		var myDate = new Date();
		var mytime=myDate.toLocaleTimeString();     //获取当前时间
		var a=myDate.toLocaleDateString();
		$("#time").text('时间：'+a);
		</script>
		</div>
		</body>
		</html>


