	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<%@ include file="/WEB-INF/views/taglibs.jsp"%>
		<%@ include file="/WEB-INF/inc/back-header.inc"%>
	<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
		<title>安泰大讲堂</title>
		<style>
			body {
				height: 100vh;
				margin: 0;
				padding: 0;
			}

			header {
				height: 83px;
				display: flex;
				justify-content: space-between;
				padding: 0 70px;
			}

			main {
				height: 465px;
				background: url(/images/companyImg/newimg/safe.jpg) no-repeat center;
				background-size: 100% 100%;
				position: relative;
			}

			footer {
				height: 71px;
				display: flex;
				align-items: center;
				justify-content: center;
			}

			ul {
				list-style: none;
			}

			#topleft {
				display: flex;
		      align-items: center;
			}

			.topleftmod {
				display: flex;
			}

			.antai {
				color: #19365F;
				font-size: 24px;
				font-weight: bold;
			}

			.logo {
				margin-left: 10px;
				display: flex;
				flex-direction: column;
				align-items: center;
				justify-content: center;
			}
			.topright{
				display: flex;
				align-items: center;
			}
			.toprightmod{
				list-style: none;
				display: flex;
				align-items: center;
				font-size:14px;
			}
			.toprightmod>li{
				margin-right: 35px;
			}
			a{
				text-decoration: none;
				color: inherit;
				text-align: center;
			}
			a:hover{
				color:red;
		        text-decoration:none;
			}
			.func>section {
				height: 87px;
				border-radius: 10px;
				margin-right: 20px;
				display: flex;
				justify-content: center;
				align-items: center;
				font-size: 18px;
				font-weight: bold;
			}
			
			.func {
				display: flex;
				justify-content: center;
			}

			.time2 {
				font-size: 45px;
				color: #fff;
			}
			.com{
				display: flex;
				justify-content: center;
				position: relative;
				top: 17%;
			}
		.img{
		   width:60px;
		   height:60px;
		}
		</style>
	</head>
	<body>
		<header>
			<div id="topleft">
				<div class="topleftmod">
					<img style="display: flex;" class="img" src="/images/companyImg/newimg/logo.png" />
					<span class="logo" style="">
						<span class="antai">安泰大讲堂</span>
						<span class="webadd">www.antai.com</span>
					</span>
				</div>
			</div>
<%--		①首页、②课程、③题库、④持证上岗、⑤管理台帐、⑥登录注册--%>
			<div class="topright" >
			<div class="toprightmod">
				<li><a target="_blank" href="http://seclass.dicarl.com/">首页</a></li>
				<li><a target="_blank" href="http://seclass.dicarl.com/app/video/index">课程</a></li>
				<li><a target="_blank" href="http://seclass.dicarl.com/index.php?app=exam">题库</a></li>
				<li><a target="_blank" href="javascript:void(0)">持证上岗</a></li>
				<li><a target="_blank" href="javascript:void(0)">管理台帐</a></li>
				<li><a target="_blank" id="login" href="http://seclass.dicarl.com/">登录注册</a></li>
		</div>
			</div>
		<script>
		$('#login').on('click', function() {
		sessionStorage.setItem("login", "login");
		});
		</script>
		</header>
		<main>
					<div class="com"><span class="time2">${userName}</span></div>
			
		</main>

		<iframe src="http://jswx.zayxy.com/index.php?user-app-login&mobile=${userName}" width="100%" height="100%" sandbox="allow-scripts" frameborder="0"  allowtransparency="true"></iframe>
		<footer>

<%--		1、三级教育；--%>
<%--		2、四新教育；--%>
<%--		3、负责人、安管员培训；--%>
<%--		4、特种作业培训；--%>
<%--		5、全员培训；--%>
<%--		6、职业资格培训；--%>
				<div class="func">
				<section ><a href="javascript:volid(0)">三级教育</a></section>
				<section ><a href="javascript:volid(0)">四新教育</a></section>
				<section ><a href="javascript:volid(0)">负责人、安管员培训</a></section>
				<section ><a href="javascript:volid(0)">特种作业培训</a></section>
				<section ><a href="javascript:volid(0)">全员培训</a></section>
				<section ><a href="jswx.zayxy.com/index.php?user-app-login&mobile=${userName}" target="_blank">职业资格培训</a></section>
			</div>
		</footer>
		<script>
			var H=window.innerWidth;
			if(H<=1080){
				console.log("缩小字体");
				var h=document.querySelector(".time2");
				h.style.fontSize=37+"px";
			}
		</script>
	</body>
</html>
