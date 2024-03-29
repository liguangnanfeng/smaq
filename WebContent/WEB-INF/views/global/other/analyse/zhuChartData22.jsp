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
		}
		td[class=first]{
		width: 120px;
		}
		td[class=first]:before {
        content: "";
        position: absolute;
        width: 1px;
        height: 143px;
        top: 0;
        left: 0;
        background-color: #ddd;
        display: block;
        transform: rotate(-58deg);
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
		justify-content: space-between;
		}
		.tit {
		background: #F5F9FE;
		font-weight: bold;
		}
		.containerx{
		padding: 20px;
		}
        .sum{
        width: 70px;
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
		<span class="c-gray en">&gt;</span> <span>隐患排查治理系统</span>
		<span class="c-gray en">&gt;</span> <span class="zc"> </span>
		<a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px"
		href="javascript:location.replace(location.href);" title="刷新">
		<i class="Hui-iconfont">&#xe68f;</i>
		</a>
		</nav>
		<div class="containerx">
		<div class="text-c mt-20 "><h3 class="center"></h3></div>
		<section class="title">
		<div class="flexspa">
		<span></span>
		<span id="time"></span>
		</div>
        <script>
        var searchURL = window.location.search;
        searchURL = searchURL.substring(1, searchURL.length);
        var targetPageId = searchURL.split("&")[0].split("=")[1];
        console.log('targetPageId:',targetPageId);
        var title="";
        if(targetPageId==1){
        title="企业自查隐患数据分析";
        console.log('title:',title);
        }
        if(targetPageId==2){
        title="行政检查隐患数据分析";   console.log('title:',title);
        }
        if(targetPageId==3){
        title="部门抽查隐患数据分析";   console.log('title:',title);
        }
        $(".center").text(title);
        </script>

        </section>
		<table class=".table table-hover"  style="margin: auto;"  cellpadding="2">
		<tr class="tit">
		<td rowspan="2">序号</td>
		<td class="first" rowspan="2" ><span class="title1">对象</span><br><span class="title3">部门</span></td>
      <script>
		console.log('${list}');
		</script>
		<td style="width:42px"> 工艺 </td>
		<td>设备设施</td>
		<td>特种设备</td>
		<td>消防安全</td>
		<td>用电安全</td>
		<td>行为环境</td>
		<td>公辅设备</td>
		<td>危化管理</td>
		<td>基础管理</td>
		<td>防雷静电</td>
		<td>安全设施</td>
		<td>职业卫生</td>
		<td>生产现场</td>
		<td style="width:42px"> 其他 </td>
		<td rowspan="2" class="sum">合计</td>
		<td rowspan="2">排名</td>
		<td rowspan="2">同比%</td>
		</tr>
		<tr class="tit">
		<td>数量</td>
		<td>数量</td>
		<td>数量</td>
		<td>数量</td>
		<td>数量</td>
		<td>数量</td>
		<td>数量</td>
		<td>数量</td>
		<td>数量</td>
		<td>数量</td>
		<td>数量</td>
		<td>数量</td>
		<td>数量</td>
		<td>数量</td>
       <script>
		window.a=0;
		</script>
		</tr>
		<c:forEach items="${list}" varStatus="index" var="be">
			<c:if test="${index.index<fn:length(list)-1 }">
				<tr>
				<td>${index.index + 1}</td>
				<td>${be.name==null?'公司级':be.name}</td>
				<td>${be.danger1}</td>
				<td>${be.danger2}</td>
				<td>${be.danger3}</td>
				<td>${be.danger4}</td>
				<td>${be.danger5}</td>
				<td>${be.danger6}</td>
				<td>${be.danger7}</td>
				<td>${be.danger8}</td>
				<td>${be.danger9}</td>
				<td>${be.danger10}</td>
				<td>${be.danger11}</td>
				<td>${be.danger12}</td>
				<td>${be.danger13}</td>
				<td>${be.danger14}</td>
				<td>${be.danger15}</td>
				<td></td>
				<td></td>
				<script>
				window.a=window.a+'${be.danger15}'*1;
				console.log(window.a);
				</script>
				</tr>
			</c:if>

			<c:if test="${index.index>fn:length(list)-2 }">
				<tr>
				<td>合计</td>
				<td>---</td>
				<td>${be.sum1}</td>
				<td>${be.sum2}</td>
				<td>${be.sum3}</td>
				<td>${be.sum4}</td>
				<td>${be.sum5}</td>
				<td>${be.sum6}</td>
				<td>${be.sum7}</td>
				<td>${be.sum8}</td>
				<td>${be.sum9}</td>
				<td>${be.sum10}</td>
				<td>${be.sum11}</td>
				<td>${be.sum12}</td>
				<td>${be.sum13}</td>
				<td>${be.sum14}</td>
				<td class="tdd"></td>
				<td></td>
				<td></td>
				</tr>
				<script>
				$(".tdd").text(window.a);
				</script>
			</c:if>
			<c:if test="${index.index>fn:length(list)-2 }">
				<tr>
				<td>占比%</td>
				<td>---</td>
				<td>${be.result1}</td>
				<td>${be.result2}</td>
				<td>${be.result3}</td>
				<td>${be.result4}</td>
				<td>${be.result5}</td>
				<td>${be.result6}</td>
				<td>${be.result7}</td>
				<td>${be.result8}</td>
				<td>${be.result9}</td>
				<td>${be.result10}</td>
				<td>${be.result11}</td>
				<td>${be.result12}</td>
				<td>${be.result13}</td>
				<td>${be.result14}</td>
				<td>--</td>
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
