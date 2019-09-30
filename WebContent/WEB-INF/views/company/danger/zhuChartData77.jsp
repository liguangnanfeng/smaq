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
		height: 172px;
		top: 0;
		left: 0;
		background-color: #ddd;
		display: block;
		transform: rotate(-64deg);
		transform-origin: top;
		}

		.title1 {
		position: absolute;
		top: 8px;
		right: 18px;
		}

		.title2 {
		position: absolute;
		top: 5px;
		right: 7px;
		}

		.title3 {
		position: absolute;
		top: 50px;
		left: 18px;
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
		margin: 15px 0;
		}
		.container{
		padding: 20px;
		}
		.f{
		background-color: #F5F9FE;
		font-weight: bold;
		}
		#time{
		 font-size:15px;
		}
		</style>

		</head>
		<body>
		<nav class="breadcrumb">
		<c:set var="x1" value="${fn:split('企业自查/ /第三方检查/行政检查','/') }"/>
		<i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
		<span class="c-gray en">&gt;</span> <span>治理数据分析</span>
		<span class="c-gray en">&gt;</span> <span class="zc">隐患等级数据分析 </span>
        <a class="btn btn-success radius r bit_hid" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:history.back(-1)" title="返回">返回</a>
		</nav>
		<div class="container">
		<div class="text-c mt-20 "><h3 class="center">隐患等级数据分析</h3></div>
		<section class="title">
		<div class="flexspa">
		<span></span>
		<span id="time"></span>
		</div>
		</section>
		<table class=".table table-hover" style="margin: auto;" cellpadding="2">
		<tr class="f">
		<td rowspan="2">序号</td>
		<td class="first" rowspan="2"><span class="title1">等级</span><br><span class="title3">部门</span></td>
		<td colspan="3">一般隐患</td>
		<td colspan="3">较大隐患</td>
		<td colspan="3">重大隐患</td>
		<td colspan="3">合计</td>
		<td rowspan="2">排名</td>
		</tr>
		<tr class="f">

		<td>数量</td>
		<td>占比%</td>
		<td>同比</td>
		<td>数量</td>
		<td>占比%</td>
		<td>同比</td>
		<td>数量</td>
		<td>占比%</td>
		<td>同比</td>
		<td>数量</td>
		<td>占比%</td>
		<td>同比</td>
<script>
console.log('${list}')
</script>
		</tr>
		<c:forEach items="${list}" varStatus="index" var="be">
			<c:if test="${index.index<fn:length(list)-1 }">
				<tr>
				<td>${index.index + 1}</td>
				<td style="width:126px;word-wrap: break-word;word-break: break-all;overflow: hidden;">${be.name}</td>
				<td>${be.danger1}</td>
				<td>${be.result11}</td>
				<td></td>

				<td>${be.danger2}</td>
				<td>${be.result22}</td>
				<td></td>

				<td>${be.danger3}</td>
				<td>${be.result33}</td>
				<td></td>

				<td>${be.sum}</td>
				<td>${be.result44}</td>
				<td></td>
				<td>${be.data}</td>
				</tr>
			</c:if>

			<c:if test="${index.index>fn:length(list)-2 }">
				<tr>
				<td>合计</td>
				<td>---</td>
				<td>${be.count1}</td>
				<td></td>
				<td></td>

				<td>${be.count2}</td>
				<td></td>
				<td></td>

				<td>${be.count3}</td>
				<td></td>
				<td></td>

				<td>${sums}</td>
				<td></td>
				<td></td>
				<td></td>
				</tr>
			</c:if>
			<c:if test="${index.index>fn:length(list)-2 }">
				<tr>
				<td>占比%</td>
				<td>---</td>
				<td>${be.result1}</td>
				<td></td>
				<td></td>

				<td>${be.result2}</td>
				<td></td>
				<td></td>

				<td>${be.result3}</td>
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
		</div>
		<script>

		var endTime = new Date(new Date().getTime());
		console.log(endTime);
		var myDate = new Date();
		var mytime=myDate.toLocaleTimeString();     //获取当前时间
		var a=myDate.toLocaleDateString();
		$("#time").text('时间：'+a);
		</script>
		</body>
		</html>
