<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<%@ include file="/WEB-INF/inc/back-header.inc"%>
<title>风险分级管控 隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style type="text/css">
body .dis-ib {
	margin-right: 15px;
}

.wid1 {
	width: 30%
}

.wid2 {
	width: 30%
}

.wid3 {
	width: 30%
}

.wid4 {
	width: 10%
}

.wid5 {
	width: 70%
}

.btn-cz {
	margin: auto;
	background: #eee;
	border: 1px solid #ddd;
	padding: 0 5px;
	height: 20px;
	line-height: 20px;
	border-radius: 5px;
	margin-left: 15px
}

.btn-cz:hover {
	border: 1px solid #06c;
	color: #06c;
	text-decoration: none;
}

body .check-box label {
	margin-left: 5px;
}

.one_danger .list_odan {
	margin-left: 20px
}

.two_danger .list_twdan {
	margin-left: 45px;
}

.one_danger .Hui-iconfont {
	margin-top: 2px
}

.one_danger, .list_danger {
	display: inline-block
}

.one_danger, .one_danger .list_odan, .two_danger, .two_danger .list_twdan,
	.list_danger {
	float: none;
}

.check-box {
	width: 30%;
	margin-bottom: 15px;
}

.btn-group .btn {
	height: 34px;
	line-height: 34px;
	padding: 0 25px;
}

.one-danrisk {
	float: left;
	width: 100%;
	border-bottom: 1px solid #ddd
}

.one-danrisk p {
	float: left;
	width: 80%;
	padding: 8px
}

.table tbody tr td .one-danrisk a {
	float: right;
	margin-top: 8px;
	margin-right: 8px;
}
</style>
<style type="text/css">
table, th, td {
	border: 1px solid #E0E0E0;
	font-size: 15px;
	text-align: center;
	vertical-align: middle;
}

td {
	height: 30px;
}

td input {
	font-size: 15px;
	border: none;
	width: 100%;
	text-align: center;
	vertical-align: middle;
	height: 30px;
}
</style>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> <span
			class="c-gray en">&gt;</span> <span>重大危险源源长制</span> <span
			class="c-gray en">&gt;</span> <span>高温熔融金属现场运输</span> <a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:void(0);" title="返回"
			onclick="parent.close_tab(function(){})">返回</a>
	</nav>


	<h3 class="text-c">《高温熔融金属现场运输》</h3>
	<div class="mt-20">

		<!--<table style="width:50%;" cellpadding="0" cellspacing="0"> table-sort tab-ndan table-bg   table   table-border table-bordered-->
		<table class="table-hover" cellspacing="0" cellpadding="0" style="width: 80%; margin-left: 10%;">
			<thead>
				<tr class="text-c">
					<th style="width: 100px">要求种类</th>
					<th style="min-width: 300px">要求内容</th>
					<th style="min-width: 200px">是否达标</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="text-c" rowspan="5">吊运装有铁水、钢水、液渣的罐或红热电极操作</td>
					<td class="text-c">起重吊运是否有专人指挥。</td>
					<td id="gwrrjs0101" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0101" id="gwrrjs0101_s" checked="checked"
								value="1"><label for="gwrrjs0101_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0101" id="gwrrjs0101_f" value="0"><label
								for="gwrrjs0101_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="5">吊运装有铁水、钢水、液渣的罐或红热电极操作</td>
					<td class="text-c">吊运钢包是否检查确认挂钩、脱钩可靠，方可通知司机起吊。</td>
					<td id="gwrrjs0102" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0102" id="gwrrjs0102_s" checked="checked"
								value="1"><label for="gwrrjs0102_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0102" id="gwrrjs0102_f" value="0"><label
								for="gwrrjs0102_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="5">吊运装有铁水、钢水、液渣的罐或红热电极操作</td>
					<td class="text-c">吊运装有铁水、钢水、液渣的罐，应与邻近设备或建、构筑物是否保持大于1.5m的净空距离。</td>
					<td id="gwrrjs0103" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0103" id="gwrrjs0103_s" checked="checked"
								value="1"><label for="gwrrjs0103_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0103" id="gwrrjs0103_f" value="0"><label
								for="gwrrjs0103_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="5">吊运装有铁水、钢水、液渣的罐或红热电极操作</td>
					<td class="text-c">龙门钩挂重铁水罐时是否有专人对两边进行检查确认的。</td>
					<td id="gwrrjs0104" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0104" id="gwrrjs0104_s" checked="checked"
								value="1"><label for="gwrrjs0104_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0104" id="gwrrjs0104_f" value="0"><label
								for="gwrrjs0104_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="5">吊运装有铁水、钢水、液渣的罐或红热电极操作</td>
					<td class="text-c">吊起的铁水罐在等待往转炉兑铁水期间，是否做到不提前挂上倾翻铁水罐的小钩。 </td>
					<td id="gwrrjs0105" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0105" id="gwrrjs0105_s" checked="checked"
								value="1"><label for="gwrrjs0105_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0105" id="gwrrjs0105_f" value="0"><label
								for="gwrrjs0105_f">否</label>
						</div>
					</td>
				</tr>


				<tr>
					<td class="text-c" rowspan="17">外部运输</td>
					<td class="text-c">外部运输使用铁路线运输的，尽头铁路线末端，是否设车挡与车挡指示器。</td>
					<td id="gwrrjs0201" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0201" id="gwrrjs0201_s" checked="checked"
								value="1"><label for="gwrrjs0201_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0201" id="gwrrjs0201_f" value="0"><label
								for="gwrrjs0201_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="17">外部运输</td>
					<td class="text-c">铁水运输是否使用专线，不应与其他交通工具混行。</td>
					<td id="gwrrjs0202" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0202" id="gwrrjs0202_s" checked="checked"
								value="1"><label for="gwrrjs0202_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0202" id="gwrrjs0202_f" value="0"><label
								for="gwrrjs0202_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="17">外部运输</td>
					<td class="text-c">铁水运输混行是否制定严格安全措施方案。</td>
					<td id="gwrrjs0203" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0203" id="gwrrjs0203_s" checked="checked"
								value="1"><label for="gwrrjs0203_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0203" id="gwrrjs0203_f" value="0"><label
								for="gwrrjs0203_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="17">外部运输</td>
					<td class="text-c">厂外公路铁水运输的安全措施方案是否报当地有关部门备案。</td>
					<td id="gwrrjs0204" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0204" id="gwrrjs0204_s" checked="checked"
								value="1"><label for="gwrrjs0204_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0204" id="gwrrjs0204_f" value="0"><label
								for="gwrrjs0204_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="17">外部运输</td>
					<td class="text-c">平交道口是否符合《工业企业铁路道口安全标准》（GB 6389）的规定。</td>
					<td id="gwrrjs0205" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0205" id="gwrrjs0205_s" checked="checked"
								value="1"><label for="gwrrjs0205_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0205" id="gwrrjs0205_f" value="0"><label
								for="gwrrjs0205_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="17">外部运输</td>
					<td class="text-c">进出炼钢生产厂房的铁路出入口或道口是否按规定设置报警装置。</td>
					<td id="gwrrjs0206" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0206" id="gwrrjs0206_s" checked="checked"
								value="1"><label for="gwrrjs0206_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0206" id="gwrrjs0206_f" value="0"><label
								for="gwrrjs0206_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="17">外部运输</td>
					<td class="text-c">在煤气附近的操作室或有人员作业的场所是否安装煤气报警器（安装高度1.0～2.0米之间）。</td>
					<td id="gwrrjs0207" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0207" id="gwrrjs0207_s" checked="checked"
								value="1"><label for="gwrrjs0207_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0207" id="gwrrjs0207_f" value="0"><label
								for="gwrrjs0207_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="17">外部运输</td>
					<td class="text-c">在煤气释放点（阀门、水封等）附近是否安装煤气报警器（高度在1米左右）。</td>
					<td id="gwrrjs0208" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0208" id="gwrrjs0208_s" checked="checked"
								value="1"><label for="gwrrjs0208_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0208" id="gwrrjs0208_f" value="0"><label
								for="gwrrjs0208_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="17">外部运输</td>
					<td class="text-c">铁路线室内车挡后6m、露天车挡后15m范围内，是否设置建筑物与设备。</td>
					<td id="gwrrjs0209" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0209" id="gwrrjs0209_s" checked="checked"
								value="1"><label for="gwrrjs0209_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0209" id="gwrrjs0209_f" value="0"><label
								for="gwrrjs0209_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="17">外部运输</td>
					<td class="text-c">采用抱罐汽车运输液体渣罐时，罐内液渣与罐沿是否保持安全距离。</td>
					<td id="gwrrjs0210" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0210" id="gwrrjs0210_s" checked="checked"
								value="1"><label for="gwrrjs0210_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0210" id="gwrrjs0210_f" value="0"><label
								for="gwrrjs0210_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="17">外部运输</td>
					<td class="text-c">抱罐汽车司机室的顶部与背面是否加设防护装置。</td>
					<td id="gwrrjs0211" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0211" id="gwrrjs0211_s" checked="checked"
								value="1"><label for="gwrrjs0211_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0211" id="gwrrjs0211_f" value="0"><label
								for="gwrrjs0211_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="17">外部运输</td>
					<td class="text-c">外部运输使用柴油车运输的，载运炽热物体是否使用专用的柴油车，其油箱应采取隔热措施。</td>
					<td id="gwrrjs0212" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0212" id="gwrrjs0212_s" checked="checked"
								value="1"><label for="gwrrjs0212_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0212" id="gwrrjs0212_f" value="0"><label
								for="gwrrjs0212_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="17">外部运输</td>
					<td class="text-c">锭坯车、铁水罐车、钢水罐车、渣罐车或运渣车、废钢料篮车及其他料车是否有无关人员乘坐。</td>
					<td id="gwrrjs0213" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0213" id="gwrrjs0213_s" checked="checked"
								value="1"><label for="gwrrjs0213_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0213" id="gwrrjs0213_f" value="0"><label
								for="gwrrjs0213_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="17">外部运输</td>
					<td class="text-c">煤气或氧气管道、电缆通廊、管架等下方是否有停留运输炽热物体的车辆。</td>
					<td id="gwrrjs0214" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0214" id="gwrrjs0214_s" checked="checked"
								value="1"><label for="gwrrjs0214_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0214" id="gwrrjs0214_f" value="0"><label
								for="gwrrjs0214_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="17">外部运输</td>
					<td class="text-c">铁路运输重罐车的行驶速度，是否大于10km/h；在高炉下行驶或倒罐时，是否大于5km/h。</td>
					<td id="gwrrjs0215" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0215" id="gwrrjs0215_s" checked="checked"
								value="1"><label for="gwrrjs0215_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0215" id="gwrrjs0215_f" value="0"><label
								for="gwrrjs0215_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="17">外部运输</td>
					<td class="text-c">高炉炉下、铁水倒罐坑、道口等行走液体金属的铁路线是否进行不定期清理，保持铁路上无杂物。</td>
					<td id="gwrrjs0216" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0216" id="gwrrjs0216_s" checked="checked"
								value="1"><label for="gwrrjs0216_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0216" id="gwrrjs0216_f" value="0"><label
								for="gwrrjs0216_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="17">外部运输</td>
					<td class="text-c">铁水钢水运输线上是否设置了紧急停靠避难线。</td>
					<td id="gwrrjs0217" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0217" id="gwrrjs0217_s" checked="checked"
								value="1"><label for="gwrrjs0217_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0217" id="gwrrjs0217_f" value="0"><label
								for="gwrrjs0217_f">否</label>
						</div>
					</td>
				</tr>



				<tr>
					<td class="text-c" rowspan="12">熔融金属运行生产区域</td>
					<td class="text-c">熔融金属吊运和生产区域是否未设置会议室、员工休息室等。</td>
					<td id="gwrrjs0301" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0301" id="gwrrjs0301_s" checked="checked"
								value="1"><label for="gwrrjs0301_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0301" id="gwrrjs0301_f" value="0"><label
								for="gwrrjs0301_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="12">熔融金属运行生产区域</td>
					<td class="text-c">是否设置安全通道，设置警示标志，防止无关人员进入熔融金属吊运和生产区域。</td>
					<td id="gwrrjs0302" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0302" id="gwrrjs0302_s" checked="checked"
								value="1"><label for="gwrrjs0302_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0302" id="gwrrjs0302_f" value="0"><label
								for="gwrrjs0302_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="12">熔融金属运行生产区域</td>
					<td class="text-c">铁水倒罐坑和铁水接受坑内是否保持干燥。</td>
					<td id="gwrrjs0303" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0303" id="gwrrjs0303_s" checked="checked"
								value="1"><label for="gwrrjs0303_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0303" id="gwrrjs0303_f" value="0"><label
								for="gwrrjs0303_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="12">熔融金属运行生产区域</td>
					<td class="text-c">正常生产和吊运时铁水倒罐坑和铁水接受坑内是否有人。</td>
					<td id="gwrrjs0304" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0304" id="gwrrjs0304_s" checked="checked"
								value="1"><label for="gwrrjs0304_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0304" id="gwrrjs0304_f" value="0"><label
								for="gwrrjs0304_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="12">熔融金属运行生产区域</td>
					<td class="text-c">易受高温辐射、液渣喷溅危害的建，构筑物，是否有防护措施。</td>
					<td id="gwrrjs0305" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0305" id="gwrrjs0305_s" checked="checked"
								value="1"><label for="gwrrjs0305_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0305" id="gwrrjs0305_f" value="0"><label
								for="gwrrjs0305_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="12">熔融金属运行生产区域</td>
					<td class="text-c">易受钢水与液渣喷溅的平台工作面，是否采用铸铁板或钢板贴面混凝土块(耐火材料)铺设。</td>
					<td id="gwrrjs0306" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0306" id="gwrrjs0306_s" checked="checked"
								value="1"><label for="gwrrjs0306_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0306" id="gwrrjs0306_f" value="0"><label
								for="gwrrjs0306_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="12">熔融金属运行生产区域</td>
					<td class="text-c">运载铁水罐、渣罐等高温物品的过跨车运载物的外表面距楼板和厂房（平台）柱的外表面的距离是否不小于0.8米，且楼板和柱应有绝热保护。</td>
					<td id="gwrrjs0307" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0307" id="gwrrjs0307_s" checked="checked"
								value="1"><label for="gwrrjs0307_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0307" id="gwrrjs0307_f" value="0"><label
								for="gwrrjs0307_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="12">熔融金属运行生产区域</td>
					<td class="text-c">炼钢炉、钢水与液渣运输线、钢水吊运通道与浇注区及其附近的地表与地下，是否未设置水管(专用渗水管除外)、电缆等管线。</td>
					<td id="gwrrjs0308" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0308" id="gwrrjs0308_s" checked="checked"
								value="1"><label for="gwrrjs0308_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0308" id="gwrrjs0308_f" value="0"><label
								for="gwrrjs0308_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="12">熔融金属运行生产区域</td>
					<td class="text-c">所有与钢水、铁水、液渣接触的罐、槽、工具及其作业区域，是否有冰雪、积水，或堆放潮湿物品和其他易燃、易爆物品。</td>
					<td id="gwrrjs0309" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0309" id="gwrrjs0309_s" checked="checked"
								value="1"><label for="gwrrjs0309_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0309" id="gwrrjs0309_f" value="0"><label
								for="gwrrjs0309_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="12">熔融金属运行生产区域</td>
					<td class="text-c">废钢配料间应部分是否有房屋遮盖，以供雨、雪天配料。</td>
					<td id="gwrrjs0310" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0310" id="gwrrjs0310_s" checked="checked"
								value="1"><label for="gwrrjs0310_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0310" id="gwrrjs0310_f" value="0"><label
								for="gwrrjs0310_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="12">熔融金属运行生产区域</td>
					<td class="text-c">熔融金属作业和通行地坪是否保持干燥，无有水或潮湿物品。</td>
					<td id="gwrrjs0311" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0311" id="gwrrjs0311_s" checked="checked"
								value="1"><label for="gwrrjs0311_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0311" id="gwrrjs0311_f" value="0"><label
								for="gwrrjs0311_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="12">熔融金属运行生产区域</td>
					<td class="text-c">熔融金属生产时是否有防喷溅措施的。</td>
					<td id="gwrrjs0312" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0312" id="gwrrjs0312_s" checked="checked"
								value="1"><label for="gwrrjs0312_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0312" id="gwrrjs0312_f" value="0"><label
								for="gwrrjs0312_f">否</label>
						</div>
					</td>
				</tr>


				<tr>
					<td class="text-c" rowspan="4">炼钢炼铁生产是一级电力负荷</td>
					<td class="text-c">供电应是否有两路独立的高压电源。</td>
					<td id="gwrrjs0401" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0401" id="gwrrjs0401_s" checked="checked"
								value="1"><label for="gwrrjs0401_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0401" id="gwrrjs0401_f" value="0"><label
								for="gwrrjs0401_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">炼钢炼铁生产是一级电力负荷</td>
					<td class="text-c">当一路电源发生故障或检修时，另一路电源是否能保证车间正常生产的用电负荷。</td>
					<td id="gwrrjs0402" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0402" id="gwrrjs0402_s" checked="checked"
								value="1"><label for="gwrrjs0402_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0402" id="gwrrjs0402_f" value="0"><label
								for="gwrrjs0402_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">炼钢炼铁生产是一级电力负荷</td>
					<td class="text-c">供电与电气设备使用的电缆不应架设在热力与燃气管道上，是否远离高温、火源与液渣喷溅区。</td>
					<td id="gwrrjs0403" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0403" id="gwrrjs0403_s" checked="checked"
								value="1"><label for="gwrrjs0403_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0403" id="gwrrjs0403_f" value="0"><label
								for="gwrrjs0403_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">炼钢炼铁生产是一级电力负荷</td>
					<td class="text-c">电缆必须通过或邻近这些区域时，是否采取可靠的防护措施。</td>
					<td id="gwrrjs0404" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0404" id="gwrrjs0404_s" checked="checked"
								value="1"><label for="gwrrjs0404_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0404" id="gwrrjs0404_f" value="0"><label
								for="gwrrjs0404_f">否</label>
						</div>
					</td>
				</tr>


				<tr>
					<td class="text-c" rowspan="4">炼钢、炼铁供水</td>
					<td class="text-c">供水系统是否设两路独立电源供电。</td>
					<td id="gwrrjs0501" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0501" id="gwrrjs0501_s" checked="checked"
								value="1"><label for="gwrrjs0501_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0501" id="gwrrjs0501_f" value="0"><label
								for="gwrrjs0501_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">炼钢、炼铁供水</td>
					<td class="text-c">供水泵是否设置备用水泵。</td>
					<td id="gwrrjs0502" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0502" id="gwrrjs0502_s" checked="checked"
								value="1"><label for="gwrrjs0502_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0502" id="gwrrjs0502_f" value="0"><label
								for="gwrrjs0502_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">炼钢、炼铁供水</td>
					<td class="text-c">高炉、转炉、电炉等是否有事故供水设施。</td>
					<td id="gwrrjs0503" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0503" id="gwrrjs0503_s" checked="checked"
								value="1"><label for="gwrrjs0503_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0503" id="gwrrjs0503_f" value="0"><label
								for="gwrrjs0503_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">炼钢、炼铁供水</td>
					<td class="text-c">供水量是否满足生产的需要。</td>
					<td id="gwrrjs0504" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0504" id="gwrrjs0504_s" checked="checked"
								value="1"><label for="gwrrjs0504_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0504" id="gwrrjs0504_f" value="0"><label
								for="gwrrjs0504_f">否</label>
						</div>
					</td>
				</tr>


				<tr>
					<td class="text-c" rowspan="4">炉渣处理</td>
					<td class="text-c">渣罐使用前是否进行检查。</td>
					<td id="gwrrjs0601" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0601" id="gwrrjs0601_s" checked="checked"
								value="1"><label for="gwrrjs0601_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0601" id="gwrrjs0601_f" value="0"><label
								for="gwrrjs0601_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">炉渣处理</td>
					<td class="text-c">罐内是否有无水、积雪或其他潮湿物料。</td>
					<td id="gwrrjs0602" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0602" id="gwrrjs0602_s" checked="checked"
								value="1"><label for="gwrrjs0602_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0602" id="gwrrjs0602_f" value="0"><label
								for="gwrrjs0602_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">炉渣处理</td>
					<td class="text-c">倒渣或渣罐地坑内是否保持干燥无积水。</td>
					<td id="gwrrjs0603" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0603" id="gwrrjs0603_s" checked="checked"
								value="1"><label for="gwrrjs0603_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0603" id="gwrrjs0603_f" value="0"><label
								for="gwrrjs0603_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">炉渣处理</td>
					<td class="text-c">采用钢渣水淬工艺，发现冲渣水量小于规定值时，是否停止水淬，以防爆炸。</td>
					<td id="gwrrjs0604" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0604" id="gwrrjs0604_s" checked="checked"
								value="1"><label for="gwrrjs0604_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0604" id="gwrrjs0604_f" value="0"><label
								for="gwrrjs0604_f">否</label>
						</div>
					</td>
				</tr>


				<tr>
					<td class="text-c" rowspan="2">铁水罐、钢水罐内的铁水、钢水凝盖处理</td>
					<td class="text-c">罐口结壳及翻渣后罐内结壳，是否使用打渣壳机和撞罐机处理。</td>
					<td id="gwrrjs0701" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0701" id="gwrrjs0701_s" checked="checked"
								value="1"><label for="gwrrjs0701_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0701" id="gwrrjs0701_f" value="0"><label
								for="gwrrjs0701_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="2">铁水罐、钢水罐内的铁水、钢水凝盖处理</td>
					<td class="text-c">铁水罐、钢水罐内的铁水、钢水有凝盖时，是否禁止用其他铁水罐、钢水罐压凝盖或人工使用管状物撞击凝盖。</td>
					<td id="gwrrjs0702" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0702" id="gwrrjs0702_s" checked="checked"
								value="1"><label for="gwrrjs0702_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0702" id="gwrrjs0702_f" value="0"><label
								for="gwrrjs0702_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="1">高炉内衬耐火材料、填料、泥浆等</td>
					<td class="text-c">高炉内衬耐火材料、填料、泥浆等是否符合设计要求。</td>
					<td id="gwrrjs0801" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0801" id="gwrrjs0801_s" checked="checked"
								value="1"><label for="gwrrjs0801_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0801" id="gwrrjs0801_f" value="0"><label
								for="gwrrjs0801_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="1">炉基测温</td>
					<td class="text-c">热电偶应对整个炉底进行自动、连续测温，其结果是否正确显示于中控室（值班室）。</td>
					<td id="gwrrjs0901" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs0901" id="gwrrjs0901_s" checked="checked"
								value="1"><label for="gwrrjs0901_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs0901" id="gwrrjs0901_f" value="0"><label
								for="gwrrjs0901_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="2">通风冷却的炉基</td>
					<td class="text-c">采用强制通风冷却炉底时，炉基温度是否不高于250℃。</td>
					<td id="gwrrjs1001" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1001" id="gwrrjs1001_s" checked="checked"
								value="1"><label for="gwrrjs1001_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1001" id="gwrrjs1001_f" value="0"><label
								for="gwrrjs1001_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="2">通风冷却的炉基</td>
					<td class="text-c">应有备用鼓风机，鼓风机运转情况是否显示于高炉中控室。</td>
					<td id="gwrrjs1002" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1002" id="gwrrjs1002_s" checked="checked"
								value="1"><label for="gwrrjs1002_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1002" id="gwrrjs1002_f" value="0"><label
								for="gwrrjs1002_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="1">水冷却炉基</td>
					<td class="text-c">采用水冷却炉底时，炉基温度是否不高于200℃。</td>
					<td id="gwrrjs1101" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1101" id="gwrrjs1101_s" checked="checked"
								value="1"><label for="gwrrjs1101_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1101" id="gwrrjs1101_f" value="0"><label
								for="gwrrjs1101_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="2">高炉出铁厂平台应保持干燥</td>
					<td class="text-c">出铁场平台是否有防止屋面漏水和天窗飘雨的措施。</td>
					<td id="gwrrjs1201" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1201" id="gwrrjs1201_s" checked="checked"
								value="1"><label for="gwrrjs1201_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1201" id="gwrrjs1201_f" value="0"><label
								for="gwrrjs1201_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="2">高炉出铁厂平台应保持干燥</td>
					<td class="text-c">天棚顶有清灰装置时，天棚顶的坡度可为1/12；无清灰装置时，其倾斜角是否小于45℃。</td>
					<td id="gwrrjs1202" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1202" id="gwrrjs1202_s" checked="checked"
								value="1"><label for="gwrrjs1202_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1202" id="gwrrjs1202_f" value="0"><label
								for="gwrrjs1202_f">否</label>
						</div>
					</td>
				</tr>



				<tr>
					<td class="text-c" rowspan="4">渣、铁沟</td>
					<td class="text-c">渣、铁沟是否有钢盖板或供横跨用的活动小桥。</td>
					<td id="gwrrjs1301" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1301" id="gwrrjs1301_s" checked="checked"
								value="1"><label for="gwrrjs1301_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1301" id="gwrrjs1301_f" value="0"><label
								for="gwrrjs1301_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">渣、铁沟</td>
					<td class="text-c">撇渣器上是否设防护罩。</td>
					<td id="gwrrjs1302" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1302" id="gwrrjs1302_s" checked="checked"
								value="1"><label for="gwrrjs1302_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1302" id="gwrrjs1302_f" value="0"><label
								for="gwrrjs1302_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">渣、铁沟</td>
					<td class="text-c">渣口正前方是否设挡渣墙。</td>
					<td id="gwrrjs1303" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1303" id="gwrrjs1303_s" checked="checked"
								value="1"><label for="gwrrjs1303_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1303" id="gwrrjs1303_f" value="0"><label
								for="gwrrjs1303_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">渣、铁沟</td>
					<td class="text-c">渣口和渣铁罐上面，是否设防雨棚和排烟罩。</td>
					<td id="gwrrjs1304" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1304" id="gwrrjs1304_s" checked="checked"
								value="1"><label for="gwrrjs1304_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1304" id="gwrrjs1304_f" value="0"><label
								for="gwrrjs1304_f">否</label>
						</div>
					</td>
				</tr>



				<tr>
					<td class="text-c" rowspan="4">水冲渣安全要求</td>
					<td class="text-c">水冲渣是否有备用电源和备用水泵。</td>
					<td id="gwrrjs1401" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1401" id="gwrrjs1401_s" checked="checked"
								value="1"><label for="gwrrjs1401_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1401" id="gwrrjs1401_f" value="0"><label
								for="gwrrjs1401_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">水冲渣安全要求</td>
					<td class="text-c">冲渣喷口的水压，是否不低于0.25MPa。</td>
					<td id="gwrrjs1402" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1402" id="gwrrjs1402_s" checked="checked"
								value="1"><label for="gwrrjs1402_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1402" id="gwrrjs1402_f" value="0"><label
								for="gwrrjs1402_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">水冲渣安全要求</td>
					<td class="text-c">靠近炉台的水渣沟，其流嘴前是否有活动护栏，或净空尺寸不大于200mm的活动栏网。</td>
					<td id="gwrrjs1403" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1403" id="gwrrjs1403_s" checked="checked"
								value="1"><label for="gwrrjs1403_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1403" id="gwrrjs1403_f" value="0"><label
								for="gwrrjs1403_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">水冲渣安全要求</td>
					<td class="text-c">水冲渣发生故障时，是否有改向渣罐放渣或向干渣坑放渣的备用设施。</td>
					<td id="gwrrjs1404" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1404" id="gwrrjs1404_s" checked="checked"
								value="1"><label for="gwrrjs1404_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1404" id="gwrrjs1404_f" value="0"><label
								for="gwrrjs1404_f">否</label>
						</div>
					</td>
				</tr>


				<tr>
					<td class="text-c" rowspan="4">转鼓渣过滤系统</td>
					<td class="text-c">系统正常运转时，其粒化水量、吹扫空气量、清扫水量、粒化器的最高水温及渣流量，是否达到设计规定值。</td>
					<td id="gwrrjs1501" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1501" id="gwrrjs1501_s" checked="checked"
								value="1"><label for="gwrrjs1501_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1501" id="gwrrjs1501_f" value="0"><label
								for="gwrrjs1501_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">转鼓渣过滤系统</td>
					<td class="text-c">系统运转时，事故水位是否正常。</td>
					<td id="gwrrjs1502" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1502" id="gwrrjs1502_s" checked="checked"
								value="1"><label for="gwrrjs1502_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1502" id="gwrrjs1502_f" value="0"><label
								for="gwrrjs1502_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">转鼓渣过滤系统</td>
					<td class="text-c">是否有严防液态渣进入粒化系统。</td>
					<td id="gwrrjs1503" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1503" id="gwrrjs1503_s" checked="checked"
								value="1"><label for="gwrrjs1503_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1503" id="gwrrjs1503_f" value="0"><label
								for="gwrrjs1503_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">转鼓渣过滤系统</td>
					<td class="text-c">采用轮法冲渣工艺时，是否在粒化轮附近设安全防护网。</td>
					<td id="gwrrjs1504" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1504" id="gwrrjs1504_s" checked="checked"
								value="1"><label for="gwrrjs1504_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1504" id="gwrrjs1504_f" value="0"><label
								for="gwrrjs1504_f">否</label>
						</div>
					</td>
				</tr>

				<tr>
					<td class="text-c" rowspan="2">倾翻渣罐</td>
					<td class="text-c">渣罐倾翻装置应能自锁，倾翻渣罐的倾翻角度是否小于116°（丝杆剩5～6扣）。</td>
					<td id="gwrrjs1601" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1601" id="gwrrjs1601_s" checked="checked"
								value="1"><label for="gwrrjs1601_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1601" id="gwrrjs1601_f" value="0"><label
								for="gwrrjs1601_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="2">倾翻渣罐</td>
					<td class="text-c">翻罐供电，是否采用隐蔽插头的软电缆，并在离罐30m以外操作开关。</td>
					<td id="gwrrjs1602" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1602" id="gwrrjs1602_s" checked="checked"
								value="1"><label for="gwrrjs1602_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1602" id="gwrrjs1602_f" value="0"><label
								for="gwrrjs1602_f">否</label>
						</div>
					</td>
				</tr>



				<tr>
					<td class="text-c" rowspan="14">铸铁机</td>
					<td class="text-c">铸铁车间的铁罐道两侧，是否设带栏杆的人行道，行人应在线界以外行走。</td>
					<td id="gwrrjs1701" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1701" id="gwrrjs1701_s" checked="checked"
								value="1"><label for="gwrrjs1701_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1701" id="gwrrjs1701_f" value="0"><label
								for="gwrrjs1701_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="14">铸铁机</td>
					<td class="text-c">铸铁机主厂房是否有排气天窗，小型铸铁机车间至少应有防雨棚。</td>
					<td id="gwrrjs1702" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1702" id="gwrrjs1702_s" checked="checked"
								value="1"><label for="gwrrjs1702_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1702" id="gwrrjs1702_f" value="0"><label
								for="gwrrjs1702_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="14">铸铁机</td>
					<td class="text-c">铸铁机厂房的主要操作室及工作间，是否有通风除尘设施。</td>
					<td id="gwrrjs1703" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1703" id="gwrrjs1703_s" checked="checked"
								value="1"><label for="gwrrjs1703_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1703" id="gwrrjs1703_f" value="0"><label
								for="gwrrjs1703_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="14">铸铁机</td>
					<td class="text-c">铸铁机操作室，是否能清楚地观察到翻罐、铁水溜槽及前半部铸模的工作情况。</td>
					<td id="gwrrjs1704" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1704" id="gwrrjs1704_s" checked="checked"
								value="1"><label for="gwrrjs1704_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1704" id="gwrrjs1704_f" value="0"><label
								for="gwrrjs1704_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="14">铸铁机</td>
					<td class="text-c">操作室窗户是否采用耐热玻璃，并设有两个方向相对、通往安全地点的出入口。</td>
					<td id="gwrrjs1705" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1705" id="gwrrjs1705_s" checked="checked"
								value="1"><label for="gwrrjs1705_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1705" id="gwrrjs1705_f" value="0"><label
								for="gwrrjs1705_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="14">铸铁机</td>
					<td class="text-c">铸铁机工作台是否采用耐火砖砌筑，宽度应大于5m。</td>
					<td id="gwrrjs1706" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1706" id="gwrrjs1706_s" checked="checked"
								value="1"><label for="gwrrjs1706_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1706" id="gwrrjs1706_f" value="0"><label
								for="gwrrjs1706_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="14">铸铁机</td>
					<td class="text-c">铸铁机下不应通行，需要通行时，是否设置专用的安全通道。</td>
					<td id="gwrrjs1707" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1707" id="gwrrjs1707_s" checked="checked"
								value="1"><label for="gwrrjs1707_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1707" id="gwrrjs1707_f" value="0"><label
								for="gwrrjs1707_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="14">铸铁机</td>
					<td class="text-c">铸铁机地坑内是否无积水。</td>
					<td id="gwrrjs1708" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1708" id="gwrrjs1708_s" checked="checked"
								value="1"><label for="gwrrjs1708_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1708" id="gwrrjs1708_f" value="0"><label
								for="gwrrjs1708_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="14">铸铁机</td>
					<td class="text-c">铸铁机链带下面（有人出入的地方），是否设置防护格网，以防止没脱模的铁块突然下落伤人。</td>
					<td id="gwrrjs1709" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1709" id="gwrrjs1709_s" checked="checked"
								value="1"><label for="gwrrjs1709_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1709" id="gwrrjs1709_f" value="0"><label
								for="gwrrjs1709_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="14">铸铁机</td>
					<td class="text-c">翻罐提升机和移动小车，是否有电动极限控制装置。</td>
					<td id="gwrrjs1710" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1710" id="gwrrjs1710_s" checked="checked"
								value="1"><label for="gwrrjs1710_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1710" id="gwrrjs1710_f" value="0"><label
								for="gwrrjs1710_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="14">铸铁机</td>
					<td class="text-c">铁水溜槽坡度是否为3％左右。</td>
					<td id="gwrrjs1711" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1711" id="gwrrjs1711_s" checked="checked"
								value="1"><label for="gwrrjs1711_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1711" id="gwrrjs1711_f" value="0"><label
								for="gwrrjs1711_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="14">铸铁机</td>
					<td class="text-c">铸铁时铁水流是否均匀，炉前铸铁是否使用铁口缓冲包，缓冲包在出铁前是否烘干。</td>
					<td id="gwrrjs1712" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1712" id="gwrrjs1712_s" checked="checked"
								value="1"><label for="gwrrjs1712_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1712" id="gwrrjs1712_f" value="0"><label
								for="gwrrjs1712_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="14">铸铁机</td>
					<td class="text-c">铸模内是否无水，模耳磨损是否不应大于5％，是否不使用开裂及内表面有缺陷的铸模。</td>
					<td id="gwrrjs1713" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1713" id="gwrrjs1713_s" checked="checked"
								value="1"><label for="gwrrjs1713_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1713" id="gwrrjs1713_f" value="0"><label
								for="gwrrjs1713_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="14">铸铁机</td>
					<td class="text-c">铁水罐对位、复位是否准确，防止偏位和移位。</td>
					<td id="gwrrjs1714" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1714" id="gwrrjs1714_s" checked="checked"
								value="1"><label for="gwrrjs1714_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1714" id="gwrrjs1714_f" value="0"><label
								for="gwrrjs1714_f">否</label>
						</div>
					</td>
				</tr>


				<tr>
					<td class="text-c" rowspan="7">高炉操作</td>
					<td class="text-c">高炉炉顶压力不断增高又无法控制时，是否按照制定的预案，及时减风，并打开炉顶放散阀，找出原因，排除故障，方可恢复工作。</td>
					<td id="gwrrjs1801" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1801" id="gwrrjs1801_s" checked="checked"
								value="1"><label for="gwrrjs1801_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1801" id="gwrrjs1801_f" value="0"><label
								for="gwrrjs1801_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="7">高炉操作</td>
					<td class="text-c">开、停炉及计划检修期间，是否有煤气专业防护人员监护。</td>
					<td id="gwrrjs1802" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1802" id="gwrrjs1802_s" checked="checked"
								value="1"><label for="gwrrjs1802_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1802" id="gwrrjs1802_f" value="0"><label
								for="gwrrjs1802_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="7">高炉操作</td>
					<td class="text-c">负责指挥开、停炉，是否制定开停炉方案和安全技术措施，并组成以生产厂长或总工程师为首的领导小组。</td>
					<td id="gwrrjs1803" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1803" id="gwrrjs1803_s" checked="checked"
								value="1"><label for="gwrrjs1803_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1803" id="gwrrjs1803_f" value="0"><label
								for="gwrrjs1803_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="7">高炉操作</td>
					<td class="text-c">高炉突然断风，是否按紧急休风程序休风，同时出净炉内的渣和铁。</td>
					<td id="gwrrjs1804" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1804" id="gwrrjs1804_s" checked="checked"
								value="1"><label for="gwrrjs1804_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1804" id="gwrrjs1804_f" value="0"><label
								for="gwrrjs1804_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="7">高炉操作</td>
					<td class="text-c">高炉生产系统（包括鼓风机）突然停电时，是否按紧急休风程序处理。</td>
					<td id="gwrrjs1805" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1805" id="gwrrjs1805_s" checked="checked"
								value="1"><label for="gwrrjs1805_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1805" id="gwrrjs1805_f" value="0"><label
								for="gwrrjs1805_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="7">高炉操作</td>
					<td class="text-c">人员进入高炉炉缸作业前，是否拆除所有直吹管，有效切断煤气、氧气、氮气等危险气源，并认真做好监护、检测和通风措施，是否锁死炉顶布料等相关装置。</td>
					<td id="gwrrjs1806" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1806" id="gwrrjs1806_s" checked="checked"
								value="1"><label for="gwrrjs1806_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1806" id="gwrrjs1806_f" value="0"><label
								for="gwrrjs1806_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="7">高炉操作</td>
					<td class="text-c">炉皮开裂的护炉方案，是否制定有保护人员和设备安全的安全措施。</td>
					<td id="gwrrjs1807" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1807" id="gwrrjs1807_s" checked="checked"
								value="1"><label for="gwrrjs1807_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1807" id="gwrrjs1807_f" value="0"><label
								for="gwrrjs1807_f">否</label>
						</div>
					</td>
				</tr>


				<tr>
					<td class="text-c" rowspan="4">出渣、出铁</td>
					<td class="text-c">非电气信号倒罐的，是否建立渣、铁罐使用牌制度。</td>
					<td id="gwrrjs1901" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1901" id="gwrrjs1901_s" checked="checked"
								value="1"><label for="gwrrjs1901_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1901" id="gwrrjs1901_f" value="0"><label
								for="gwrrjs1901_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">出渣、出铁</td>
					<td class="text-c">出铁、出渣以前，是否做好准备工作，并发出出铁、出渣或停止的声响信号。</td>
					<td id="gwrrjs1902" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1902" id="gwrrjs1902_s" checked="checked"
								value="1"><label for="gwrrjs1902_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1902" id="gwrrjs1902_f" value="0"><label
								for="gwrrjs1902_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">出渣、出铁</td>
					<td class="text-c">无渣、铁罐使用牌，不应倒罐，高炉不应出铁、出渣。</td>
					<td id="gwrrjs1903" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1903" id="gwrrjs1903_s" checked="checked"
								value="1"><label for="gwrrjs1903_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1903" id="gwrrjs1903_f" value="0"><label
								for="gwrrjs1903_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">出渣、出铁</td>
					<td class="text-c">水冲渣的高炉，在出铁前是否先开动冲渣水泵（或打开冲渣水阀门）。</td>
					<td id="gwrrjs1904" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs1904" id="gwrrjs1904_s" checked="checked"
								value="1"><label for="gwrrjs1904_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs1904" id="gwrrjs1904_f" value="0"><label
								for="gwrrjs1904_f">否</label>
						</div>
					</td>
				</tr>

				<tr>
					<td class="text-c" rowspan="1">渣、铁口维护</td>
					<td class="text-c">维护铁口和渣口作业前，是否先点燃煤气，防止中毒。</td>
					<td id="gwrrjs2001" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2001" id="gwrrjs2001_s" checked="checked"
								value="1"><label for="gwrrjs2001_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2001" id="gwrrjs2001_f" value="0"><label
								for="gwrrjs2001_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="3">摆动溜嘴的操作</td>
					<td class="text-c">维护铁口和渣口作业前，是否先点燃煤气，防止中毒。</td>
					<td id="gwrrjs2101" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2101" id="gwrrjs2101_s" checked="checked"
								value="1"><label for="gwrrjs2101_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2101" id="gwrrjs2101_f" value="0"><label
								for="gwrrjs2101_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="3">摆动溜嘴的操作</td>
					<td class="text-c">维护铁口和渣口作业前，是否先点燃煤气，防止中毒。</td>
					<td id="gwrrjs2102" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2102" id="gwrrjs2102_s" checked="checked"
								value="1"><label for="gwrrjs2102_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2102" id="gwrrjs2102_f" value="0"><label
								for="gwrrjs2102_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="3">摆动溜嘴的操作</td>
					<td class="text-c">维护铁口和渣口作业前，是否先点燃煤气，防止中毒。</td>
					<td id="gwrrjs2103" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2103" id="gwrrjs2103_s" checked="checked"
								value="1"><label for="gwrrjs2103_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2103" id="gwrrjs2103_f" value="0"><label
								for="gwrrjs2103_f">否</label>
						</div>
					</td>
				</tr>

				<tr>
					<td class="text-c" rowspan="3">转鼓渣过滤系统运转</td>
					<td class="text-c">转鼓渣过滤系统运转前的检查是否做到：设备无异常，粒化头无堵塞，接受槽格栅无渣块，高低沟、渣闸正常，热水槽无积渣，地坑无积水，管道阀门无泄漏，胶带运行平稳、无偏离，事故水位正常。</td>
					<td id="gwrrjs2201" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2201" id="gwrrjs2201_s" checked="checked"
								value="1"><label for="gwrrjs2201_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2201" id="gwrrjs2201_f" value="0"><label
								for="gwrrjs2201_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="3">转鼓渣过滤系统运转</td>
					<td class="text-c">正常生产时，系统设备的运转是否实行自动控制。</td>
					<td id="gwrrjs2202" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2202" id="gwrrjs2202_s" checked="checked"
								value="1"><label for="gwrrjs2202_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2202" id="gwrrjs2202_f" value="0"><label
								for="gwrrjs2202_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="3">转鼓渣过滤系统运转</td>
					<td class="text-c">转鼓渣过滤系统各种联锁、保护装置的调整，是否经主管部门同意，并报主管厂长批准；调整应记录存档。</td>
					<td id="gwrrjs2203" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2203" id="gwrrjs2203_s" checked="checked"
								value="1"><label for="gwrrjs2203_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2203" id="gwrrjs2203_f" value="0"><label
								for="gwrrjs2203_f">否</label>
						</div>
					</td>
				</tr>

				<tr>
					<td class="text-c" rowspan="4">加废钢、兑铁水</td>
					<td class="text-c">是否有专人（穿好黄马甲、带好对讲机、手势准确明了）负责加废钢、兑铁水</td>
					<td id="gwrrjs2301" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2301" id="gwrrjs2301_s" checked="checked"
								value="1"><label for="gwrrjs2301_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2301" id="gwrrjs2301_f" value="0"><label
								for="gwrrjs2301_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">加废钢、兑铁水</td>
					<td class="text-c">兑铁水时120度扇形内是否禁止有人。</td>
					<td id="gwrrjs2302" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2302" id="gwrrjs2302_s" checked="checked"
								value="1"><label for="gwrrjs2302_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2302" id="gwrrjs2302_f" value="0"><label
								for="gwrrjs2302_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">加废钢、兑铁水</td>
					<td class="text-c">主控室防爆门在兑铁水、加补炉料前是否关闭。</td>
					<td id="gwrrjs2303" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2303" id="gwrrjs2303_s" checked="checked"
								value="1"><label for="gwrrjs2303_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2303" id="gwrrjs2303_f" value="0"><label
								for="gwrrjs2303_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">加废钢、兑铁水</td>
					<td class="text-c">兑铁水前是否确认炉内无稀渣，严禁留稀渣兑铁水。</td>
					<td id="gwrrjs2304" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2304" id="gwrrjs2304_s" checked="checked"
								value="1"><label for="gwrrjs2304_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2304" id="gwrrjs2304_f" value="0"><label
								for="gwrrjs2304_f">否</label>
						</div>
					</td>
				</tr>

				<tr>
					<td class="text-c" rowspan="3">倒炉、出钢</td>
					<td class="text-c">转炉倾动设备是否设有可靠的事故断电紧急开关；氧枪、副枪驱动，设有事故电源(直流驱动采用蓄电池，交流驱动采用UPS电源)。</td>
					<td id="gwrrjs2401" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2401" id="gwrrjs2401_s" checked="checked"
								value="1"><label for="gwrrjs2401_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2401" id="gwrrjs2401_f" value="0"><label
								for="gwrrjs2401_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="3">倒炉、出钢</td>
					<td class="text-c">倒炉开始和过程中，是否做到不长时间正对炉口站立、操作。</td>
					<td id="gwrrjs2402" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2402" id="gwrrjs2402_s" checked="checked"
								value="1"><label for="gwrrjs2402_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2402" id="gwrrjs2402_f" value="0"><label
								for="gwrrjs2402_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="3">倒炉、出钢</td>
					<td class="text-c">补炉第一炉出钢，是否严禁正对炉口站立、操作。</td>
					<td id="gwrrjs2403" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2403" id="gwrrjs2403_s" checked="checked"
								value="1"><label for="gwrrjs2403_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2403" id="gwrrjs2403_f" value="0"><label
								for="gwrrjs2403_f">否</label>
						</div>
					</td>
				</tr>

				<tr>
					<td class="text-c" rowspan="4">转炉、电炉、精炼炉的炉下区域</td>
					<td class="text-c">炉下漏钢坑是否按防水要求设计施工,有防积水的措施。</td>
					<td id="gwrrjs2501" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2501" id="gwrrjs2501_s" checked="checked"
								value="1"><label for="gwrrjs2501_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2501" id="gwrrjs2501_f" value="0"><label
								for="gwrrjs2501_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">转炉、电炉、精炼炉的炉下区域</td>
					<td class="text-c">炉下钢水罐车、渣罐车运行区域，地面是否保持干燥。</td>
					<td id="gwrrjs2502" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2502" id="gwrrjs2502_s" checked="checked"
								value="1"><label for="gwrrjs2502_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2502" id="gwrrjs2502_f" value="0"><label
								for="gwrrjs2502_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">转炉、电炉、精炼炉的炉下区域</td>
					<td class="text-c">炉区、连铸区域厂房的屋面是否有防漏雨设施、下水道是否保持畅通。</td>
					<td id="gwrrjs2503" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2503" id="gwrrjs2503_s" checked="checked"
								value="1"><label for="gwrrjs2503_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2503" id="gwrrjs2503_f" value="0"><label
								for="gwrrjs2503_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">转炉、电炉、精炼炉的炉下区域</td>
					<td class="text-c">炉下热泼渣区地坪应防止积水，周围是否设防护结构。</td>
					<td id="gwrrjs2504" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2504" id="gwrrjs2504_s" checked="checked"
								value="1"><label for="gwrrjs2504_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2504" id="gwrrjs2504_f" value="0"><label
								for="gwrrjs2504_f">否</label>
						</div>
					</td>
				</tr>

				<tr>
					<td class="text-c" rowspan="4">转炉和电炉主控室及各操作室的布置</td>
					<td class="text-c">大喷事故可能威胁转炉和电炉主控室是否采取必要的安全防护措施。</td>
					<td id="gwrrjs2601" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2601" id="gwrrjs2601_s" checked="checked"
								value="1"><label for="gwrrjs2601_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2601" id="gwrrjs2601_f" value="0"><label
								for="gwrrjs2601_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">转炉和电炉主控室及各操作室的布置</td>
					<td class="text-c">连铸主控室是否不正对中间罐。</td>
					<td id="gwrrjs2602" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2602" id="gwrrjs2602_s" checked="checked"
								value="1"><label for="gwrrjs2602_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2602" id="gwrrjs2602_f" value="0"><label
								for="gwrrjs2602_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">转炉和电炉主控室及各操作室的布置</td>
					<td class="text-c">转炉炉旁操作室是否采取隔热防喷溅措施。</td>
					<td id="gwrrjs2603" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2603" id="gwrrjs2603_s" checked="checked"
								value="1"><label for="gwrrjs2603_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2603" id="gwrrjs2603_f" value="0"><label
								for="gwrrjs2603_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">转炉和电炉主控室及各操作室的布置</td>
					<td class="text-c">电炉炉后出钢操作室，是否不正对出钢方向开门，其窗户应采取防喷溅措施。</td>
					<td id="gwrrjs2604" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2604" id="gwrrjs2604_s" checked="checked"
								value="1"><label for="gwrrjs2604_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2604" id="gwrrjs2604_f" value="0"><label
								for="gwrrjs2604_f">否</label>
						</div>
					</td>
				</tr>

				<tr>
					<td class="text-c" rowspan="4">入炉物料的保存和分拣要求</td>
					<td class="text-c">具有爆炸和自燃危险的物料，如CaC2粉剂、镁粉、煤粉、直接还原铁（DRI）等是否贮存于密闭贮仓内，必要时用氮气保护。</td>
					<td id="gwrrjs2701" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2701" id="gwrrjs2701_s" checked="checked"
								value="1"><label for="gwrrjs2701_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2701" id="gwrrjs2701_f" value="0"><label
								for="gwrrjs2701_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">入炉物料的保存和分拣要求</td>
					<td class="text-c">入炉物料是否保持干燥。</td>
					<td id="gwrrjs2702" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2702" id="gwrrjs2702_s" checked="checked"
								value="1"><label for="gwrrjs2702_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2702" id="gwrrjs2702_f" value="0"><label
								for="gwrrjs2702_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">入炉物料的保存和分拣要求</td>
					<td class="text-c">入炉废钢是否有拣选措施，入炉废钢内严禁夹带有密封、易爆物。</td>
					<td id="gwrrjs2703" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2703" id="gwrrjs2703_s" checked="checked"
								value="1"><label for="gwrrjs2703_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2703" id="gwrrjs2703_f" value="0"><label
								for="gwrrjs2703_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">入炉物料的保存和分拣要求</td>
					<td class="text-c">地下料仓、高位料仓的受料口，是否设置格栅板并按照受限空间管控。</td>
					<td id="gwrrjs2704" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2704" id="gwrrjs2704_s" checked="checked"
								value="1"><label for="gwrrjs2704_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2704" id="gwrrjs2704_f" value="0"><label
								for="gwrrjs2704_f">否</label>
						</div>
					</td>
				</tr>



				<tr>
					<td class="text-c" rowspan="15">连铸</td>
					<td class="text-c">大包回转台是否配置安全制动与停电事故驱动装置。</td>
					<td id="gwrrjs2801" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2801" id="gwrrjs2801_s" checked="checked"
								value="1"><label for="gwrrjs2801_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2801" id="gwrrjs2801_f" value="0"><label
								for="gwrrjs2801_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="15">连铸</td>
					<td class="text-c">连铸浇注区是否设置事故钢水包。</td>
					<td id="gwrrjs2802" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2802" id="gwrrjs2802_s" checked="checked"
								value="1"><label for="gwrrjs2802_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2802" id="gwrrjs2802_f" value="0"><label
								for="gwrrjs2802_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="15">连铸</td>
					<td class="text-c">连铸浇注区是否设置溢流槽。</td>
					<td id="gwrrjs2803" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2803" id="gwrrjs2803_s" checked="checked"
								value="1"><label for="gwrrjs2803_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2803" id="gwrrjs2803_f" value="0"><label
								for="gwrrjs2803_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="15">连铸</td>
					<td class="text-c">连铸浇注区是否中间溢流罐。</td>
					<td id="gwrrjs2804" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2804" id="gwrrjs2804_s" checked="checked"
								value="1"><label for="gwrrjs2804_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2804" id="gwrrjs2804_f" value="0"><label
								for="gwrrjs2804_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="15">连铸</td>
					<td class="text-c">连铸浇注之前，是否检查各介质参数符合要求。</td>
					<td id="gwrrjs2805" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2805" id="gwrrjs2805_s" checked="checked"
								value="1"><label for="gwrrjs2805_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2805" id="gwrrjs2805_f" value="0"><label
								for="gwrrjs2805_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="15">连铸</td>
					<td class="text-c">是否确认结晶器无漏水、渗水、冷却水满足工艺要求，事故槽内不允许有水或潮湿物。</td>
					<td id="gwrrjs2806" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2806" id="gwrrjs2806_s" checked="checked"
								value="1"><label for="gwrrjs2806_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2806" id="gwrrjs2806_f" value="0"><label
								for="gwrrjs2806_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="15">连铸</td>
					<td class="text-c">是否禁止使用有缺陷的中包，中包上线使用前检查中包内衬工作层是否完好；包口座砖按装时是否对中；中包外观检查，发现中包外壁发红，则立即关闭大包，停机换包。</td>
					<td id="gwrrjs2807" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2807" id="gwrrjs2807_s" checked="checked"
								value="1"><label for="gwrrjs2807_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2807" id="gwrrjs2807_f" value="0"><label
								for="gwrrjs2807_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="15">连铸</td>
					<td class="text-c">连铸钢包或中间罐滑动水口开启时，是否做到滑动水口正面无人。</td>
					<td id="gwrrjs2808" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2808" id="gwrrjs2808_s" checked="checked"
								value="1"><label for="gwrrjs2808_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2808" id="gwrrjs2808_f" value="0"><label
								for="gwrrjs2808_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="15">连铸</td>
					<td class="text-c">检查煤气管道接口是否有漏气现象；保证使用的冷料干燥。</td>
					<td id="gwrrjs2809" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2809" id="gwrrjs2809_s" checked="checked"
								value="1"><label for="gwrrjs2809_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2809" id="gwrrjs2809_f" value="0"><label
								for="gwrrjs2809_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="15">连铸</td>
					<td class="text-c">连铸浇注时二次冷却区无人，严禁进入扇形段，并严禁进入密封室及冲渣沟是否有管控措施。</td>
					<td id="gwrrjs2810" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2810" id="gwrrjs2810_s" checked="checked"
								value="1"><label for="gwrrjs2810_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2810" id="gwrrjs2810_f" value="0"><label
								for="gwrrjs2810_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="15">连铸</td>
					<td class="text-c">出现结晶器冷却水减少报警时，是否立即停止浇注。</td>
					<td id="gwrrjs2811" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2811" id="gwrrjs2811_s" checked="checked"
								value="1"><label for="gwrrjs2811_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2811" id="gwrrjs2811_f" value="0"><label
								for="gwrrjs2811_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="15">连铸</td>
					<td class="text-c">大包回转台旋转过程中，旋转区域内是否无人。</td>
					<td id="gwrrjs2812" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2812" id="gwrrjs2812_s" checked="checked"
								value="1"><label for="gwrrjs2812_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2812" id="gwrrjs2812_f" value="0"><label
								for="gwrrjs2812_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="15">连铸</td>
					<td class="text-c">收尾坯中包液面200mm走车，严禁下渣；是否佩戴好防护面罩。</td>
					<td id="gwrrjs2813" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2813" id="gwrrjs2813_s" checked="checked"
								value="1"><label for="gwrrjs2813_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2813" id="gwrrjs2813_f" value="0"><label
								for="gwrrjs2813_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="15">连铸</td>
					<td class="text-c">采用放射源控制结晶器液面时，是否建立严格的管理和检测制度，放射源的装、卸、运输和存放，使用专用工具。</td>
					<td id="gwrrjs2814" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2814" id="gwrrjs2814_s" checked="checked"
								value="1"><label for="gwrrjs2814_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2814" id="gwrrjs2814_f" value="0"><label
								for="gwrrjs2814_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="15">连铸</td>
					<td class="text-c">放射源的启闭是否有检查确认制度，即放射源只是在调试或浇注时打开，打开时人员应避开其辐射方向，存放箱与存放地点设有警告标志。</td>
					<td id="gwrrjs2815" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2815" id="gwrrjs2815_s" checked="checked"
								value="1"><label for="gwrrjs2815_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2815" id="gwrrjs2815_f" value="0"><label
								for="gwrrjs2815_f">否</label>
						</div>
					</td>
				</tr>


				<tr>
					<td class="text-c" rowspan="4">模铸浇注</td>
					<td class="text-c">开浇和烧氧时是否有预防钢水喷溅的措施。</td>
					<td id="gwrrjs2901" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2901" id="gwrrjs2901_s" checked="checked"
								value="1"><label for="gwrrjs2901_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2901" id="gwrrjs2901_f" value="0"><label
								for="gwrrjs2901_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">模铸浇注</td>
					<td class="text-c">水口烧开后，是否迅速关闭氧气。</td>
					<td id="gwrrjs2902" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2902" id="gwrrjs2902_s" checked="checked"
								value="1"><label for="gwrrjs2902_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2902" id="gwrrjs2902_f" value="0"><label
								for="gwrrjs2902_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">模铸浇注</td>
					<td class="text-c">浇注中是否做到不往钢水包内投料调温。</td>
					<td id="gwrrjs2903" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2903" id="gwrrjs2903_s" checked="checked"
								value="1"><label for="gwrrjs2903_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2903" id="gwrrjs2903_f" value="0"><label
								for="gwrrjs2903_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">模铸浇注</td>
					<td class="text-c">人员是否做到不在有红锭的钢锭模沿上站立、行走和进行其他操作。</td>
					<td id="gwrrjs2904" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs2904" id="gwrrjs2904_s" checked="checked"
								value="1"><label for="gwrrjs2904_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs2904" id="gwrrjs2904_f" value="0"><label
								for="gwrrjs2904_f">否</label>
						</div>
					</td>
				</tr>

				<tr>
					<td class="text-c" rowspan="3">平板运输过跨车</td>
					<td class="text-c">所有车辆投入使用前是否经重车运行试验合格的。</td>
					<td id="gwrrjs3001" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3001" id="gwrrjs3001_s" checked="checked"
								value="1"><label for="gwrrjs3001_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3001" id="gwrrjs3001_f" value="0"><label
								for="gwrrjs3001_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="3">平板运输过跨车</td>
					<td class="text-c">平板车辆运行时，是否有发出红色闪光与轰鸣等警示信号的。</td>
					<td id="gwrrjs3002" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3002" id="gwrrjs3002_s" checked="checked"
								value="1"><label for="gwrrjs3002_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3002" id="gwrrjs3002_f" value="0"><label
								for="gwrrjs3002_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="3">平板运输过跨车</td>
					<td class="text-c">电动铁水罐车、钢水罐车、渣罐车的停靠处，是否设两个限位开关的。</td>
					<td id="gwrrjs3003" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3003" id="gwrrjs3003_s" checked="checked"
								value="1"><label for="gwrrjs3003_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3003" id="gwrrjs3003_f" value="0"><label
								for="gwrrjs3003_f">否</label>
						</div>
					</td>
				</tr>

				<tr>
					<td class="text-c" rowspan="3">炼钢炉出钢量要求</td>
					<td class="text-c">150t以下的转炉，最大出钢量是否不超过公称容量的120％。</td>
					<td id="gwrrjs3101" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3101" id="gwrrjs3101_s" checked="checked"
								value="1"><label for="gwrrjs3101_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3101" id="gwrrjs3101_f" value="0"><label
								for="gwrrjs3101_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="3">炼钢炉出钢量要求</td>
					<td class="text-c">200t以上的转炉，是否按定量法操作。</td>
					<td id="gwrrjs3102" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3102" id="gwrrjs3102_s" checked="checked"
								value="1"><label for="gwrrjs3102_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3102" id="gwrrjs3102_f" value="0"><label
								for="gwrrjs3102_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="3">炼钢炉出钢量要求</td>
					<td class="text-c">电炉的最大出钢量，是否不超过平均出钢量的120％。</td>
					<td id="gwrrjs3103" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3103" id="gwrrjs3103_s" checked="checked"
								value="1"><label for="gwrrjs3103_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3103" id="gwrrjs3103_f" value="0"><label
								for="gwrrjs3103_f">否</label>
						</div>
					</td>
				</tr>

				<tr>
					<td class="text-c" rowspan="5">转炉氧枪</td>
					<td class="text-c">转炉氧枪与副枪升降装置是否配备钢绳张力测定装置。</td>
					<td id="gwrrjs3201" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3201" id="gwrrjs3201_s" checked="checked"
								value="1"><label for="gwrrjs3201_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3201" id="gwrrjs3201_f" value="0"><label
								for="gwrrjs3201_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="5">转炉氧枪</td>
					<td class="text-c">转炉氧枪与副枪升降装置是否有钢绳断裂防坠装置。</td>
					<td id="gwrrjs3202" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3202" id="gwrrjs3202_s" checked="checked"
								value="1"><label for="gwrrjs3202_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3202" id="gwrrjs3202_f" value="0"><label
								for="gwrrjs3202_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="5">转炉氧枪</td>
					<td class="text-c">转炉氧枪与副枪升降装置是否有事故驱动等安全装置。</td>
					<td id="gwrrjs3203" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3203" id="gwrrjs3203_s" checked="checked"
								value="1"><label for="gwrrjs3203_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3203" id="gwrrjs3203_f" value="0"><label
								for="gwrrjs3203_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="5">转炉氧枪</td>
					<td class="text-c">各枪位停靠点，是否与转炉倾动、氧气开闭、冷却水流量和温度等联锁。</td>
					<td id="gwrrjs3204" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3204" id="gwrrjs3204_s" checked="checked"
								value="1"><label for="gwrrjs3204_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3204" id="gwrrjs3204_f" value="0"><label
								for="gwrrjs3204_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="5">转炉氧枪</td>
					<td class="text-c">转炉氧枪供水，是否设置电动或气动快速切断阀。</td>
					<td id="gwrrjs3205" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3205" id="gwrrjs3205_s" checked="checked"
								value="1"><label for="gwrrjs3205_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3205" id="gwrrjs3205_f" value="0"><label
								for="gwrrjs3205_f">否</label>
						</div>
					</td>
				</tr>

				<tr>
					<td class="text-c" rowspan="4">钢水炉外精炼</td>
					<td class="text-c">钢水炉外精炼装置，是否有事故漏钢措施。</td>
					<td id="gwrrjs3301" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3301" id="gwrrjs3301_s" checked="checked"
								value="1"><label for="gwrrjs3301_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3301" id="gwrrjs3301_f" value="0"><label
								for="gwrrjs3301_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">钢水炉外精炼</td>
					<td class="text-c">VD、VOD等钢包真空精炼装置，其蒸汽喷射真空泵系统是否有抑制钢液溢出钢包的真空度调节措施。</td>
					<td id="gwrrjs3302" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3302" id="gwrrjs3302_s" checked="checked"
								value="1"><label for="gwrrjs3302_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3302" id="gwrrjs3302_f" value="0"><label
								for="gwrrjs3302_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">钢水炉外精炼</td>
					<td class="text-c">真空精炼装置是否设彩色工业电视，监视真空罐内钢液面的升降。</td>
					<td id="gwrrjs3303" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3303" id="gwrrjs3303_s" checked="checked"
								value="1"><label for="gwrrjs3303_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3303" id="gwrrjs3303_f" value="0"><label
								for="gwrrjs3303_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">钢水炉外精炼</td>
					<td class="text-c">炉外精炼炉工作之前，是否进行检查，确保设备处于良好待机状态、各介质参数符合要求。</td>
					<td id="gwrrjs3304" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3304" id="gwrrjs3304_s" checked="checked"
								value="1"><label for="gwrrjs3304_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3304" id="gwrrjs3304_f" value="0"><label
								for="gwrrjs3304_f">否</label>
						</div>
					</td>
				</tr>

				<tr>
					<td class="text-c" rowspan="6">电炉修炉</td>
					<td class="text-c">修炉时，是否锁定电炉倾动机械。</td>
					<td id="gwrrjs3401" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3401" id="gwrrjs3401_s" checked="checked"
								value="1"><label for="gwrrjs3401_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3401" id="gwrrjs3401_f" value="0"><label
								for="gwrrjs3401_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="6">电炉修炉</td>
					<td class="text-c">修炉时是否旋开炉盖并将其锁定。</td>
					<td id="gwrrjs3402" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3402" id="gwrrjs3402_s" checked="checked"
								value="1"><label for="gwrrjs3402_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3402" id="gwrrjs3402_f" value="0"><label
								for="gwrrjs3402_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="6">电炉修炉</td>
					<td class="text-c">修炉时是否关闭液压站。</td>
					<td id="gwrrjs3403" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3403" id="gwrrjs3403_s" checked="checked"
								value="1"><label for="gwrrjs3403_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3403" id="gwrrjs3403_f" value="0"><label
								for="gwrrjs3403_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="6">电炉修炉</td>
					<td class="text-c">是否将炉前碳氧喷枪转至停放位的，并切断气源。</td>
					<td id="gwrrjs3404" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3404" id="gwrrjs3404_s" checked="checked"
								value="1"><label for="gwrrjs3404_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3404" id="gwrrjs3404_f" value="0"><label
								for="gwrrjs3404_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="6">电炉修炉</td>
					<td class="text-c">是否切断炉底搅拌气源或并未采取隔离措施。</td>
					<td id="gwrrjs3405" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3405" id="gwrrjs3405_s" checked="checked"
								value="1"><label for="gwrrjs3405_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3405" id="gwrrjs3405_f" value="0"><label
								for="gwrrjs3405_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="6">电炉修炉</td>
					<td class="text-c">氧燃烧嘴或炉壁氧枪的氧气是否切断并采取隔离措施。</td>
					<td id="gwrrjs3406" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3406" id="gwrrjs3406_s" checked="checked"
								value="1"><label for="gwrrjs3406_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3406" id="gwrrjs3406_f" value="0"><label
								for="gwrrjs3406_f">否</label>
						</div>
					</td>
				</tr>

				<tr>
					<td class="text-c" rowspan="4">氧气转炉操作</td>
					<td class="text-c">出钢后，炉内不准许留有剩渣，特殊工艺要求留渣时，转炉留渣操作是否有防喷渣措施。</td>
					<td id="gwrrjs3501" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3501" id="gwrrjs3501_s" checked="checked"
								value="1"><label for="gwrrjs3501_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3501" id="gwrrjs3501_f" value="0"><label
								for="gwrrjs3501_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">氧气转炉操作</td>
					<td class="text-c">烘炉是否严格执行烘炉操作规程。</td>
					<td id="gwrrjs3502" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3502" id="gwrrjs3502_s" checked="checked"
								value="1"><label for="gwrrjs3502_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3502" id="gwrrjs3502_f" value="0"><label
								for="gwrrjs3502_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">氧气转炉操作</td>
					<td class="text-c">转炉生产期间需到炉下区域作业时，是否通知转炉控制室停止吹炼，并不得倾动转炉。</td>
					<td id="gwrrjs3503" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3503" id="gwrrjs3503_s" checked="checked"
								value="1"><label for="gwrrjs3503_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3503" id="gwrrjs3503_f" value="0"><label
								for="gwrrjs3503_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">氧气转炉操作</td>
					<td class="text-c">倒炉测温取样和出钢时，人员是否避免正对炉口，待炉子停稳，无喷溅时，方可作业。</td>
					<td id="gwrrjs3504" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3504" id="gwrrjs3504_s" checked="checked"
								value="1"><label for="gwrrjs3504_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3504" id="gwrrjs3504_f" value="0"><label
								for="gwrrjs3504_f">否</label>
						</div>
					</td>
				</tr>

				<tr>
					<td class="text-c" rowspan="5">电炉开炉前检查</td>
					<td class="text-c">确保各机械设备及联锁装置是否处于正常的待机状态。</td>
					<td id="gwrrjs3601" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3601" id="gwrrjs3601_s" checked="checked"
								value="1"><label for="gwrrjs3601_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3601" id="gwrrjs3601_f" value="0"><label
								for="gwrrjs3601_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="5">电炉开炉前检查</td>
					<td class="text-c">各种介质处于设计是否在要求的参数范围。</td>
					<td id="gwrrjs3602" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3602" id="gwrrjs3602_s" checked="checked"
								value="1"><label for="gwrrjs3602_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3602" id="gwrrjs3602_f" value="0"><label
								for="gwrrjs3602_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="5">电炉开炉前检查</td>
					<td class="text-c">各水冷元件供排水上方是否有异常现象。</td>
					<td id="gwrrjs3603" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3603" id="gwrrjs3603_s" checked="checked"
								value="1"><label for="gwrrjs3603_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3603" id="gwrrjs3603_f" value="0"><label
								for="gwrrjs3603_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="5">电炉开炉前检查</td>
					<td class="text-c">供电系统与电控是否正常。</td>
					<td id="gwrrjs3604" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3604" id="gwrrjs3604_s" checked="checked"
								value="1"><label for="gwrrjs3604_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3604" id="gwrrjs3604_f" value="0"><label
								for="gwrrjs3604_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="5">电炉开炉前检查</td>
					<td class="text-c">工作平台是否整洁有序无杂物。</td>
					<td id="gwrrjs3605" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3605" id="gwrrjs3605_s" checked="checked"
								value="1"><label for="gwrrjs3605_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3605" id="gwrrjs3605_f" value="0"><label
								for="gwrrjs3605_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="2">电炉氧燃烧嘴开停</td>
					<td class="text-c">电炉氧燃烧嘴开启时是否先供燃料，点火后再供氧。</td>
					<td id="gwrrjs3701" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3701" id="gwrrjs3701_s" checked="checked"
								value="1"><label for="gwrrjs3701_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3701" id="gwrrjs3701_f" value="0"><label
								for="gwrrjs3701_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="2">电炉氧燃烧嘴开停</td>
					<td class="text-c">关闭时是否先停止供氧，再停止供燃料。</td>
					<td id="gwrrjs3702" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3702" id="gwrrjs3702_s" checked="checked"
								value="1"><label for="gwrrjs3702_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3702" id="gwrrjs3702_f" value="0"><label
								for="gwrrjs3702_f">否</label>
						</div>
					</td>
				</tr>

				<tr>
					<td class="text-c" rowspan="3">电炉通电冶炼或出钢期间</td>
					<td class="text-c">人员是否处于安全位置，不应登上炉顶维护平台。</td>
					<td id="gwrrjs3801" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3801" id="gwrrjs3801_s" checked="checked"
								value="1"><label for="gwrrjs3801_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3801" id="gwrrjs3801_f" value="0"><label
								for="gwrrjs3801_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="3">电炉通电冶炼或出钢期间</td>
					<td class="text-c">短网下和炉下区域是否有人通行。</td>
					<td id="gwrrjs3802" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3802" id="gwrrjs3802_s" checked="checked"
								value="1"><label for="gwrrjs3802_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3802" id="gwrrjs3802_f" value="0"><label
								for="gwrrjs3802_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="3">电炉通电冶炼或出钢期间</td>
					<td class="text-c">电炉维修炉底出钢口的作业人员与电炉主控室人员之间，是否建立联系与确认制度。</td>
					<td id="gwrrjs3803" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3803" id="gwrrjs3803_s" checked="checked"
								value="1"><label for="gwrrjs3803_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3803" id="gwrrjs3803_f" value="0"><label
								for="gwrrjs3803_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="1">电炉、精炼炉在断电和锁定炉盖时</td>
					<td class="text-c">人员在炉盖上方更换电极或检修作业是否采取防高空坠落措施。</td>
					<td id="gwrrjs3901" class="text-c">
						<div class="radio-box">
							<input type="radio" name="gwrrjs3901" id="gwrrjs3901_s" checked="checked"
								value="1"><label for="gwrrjs3901_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="gwrrjs3901" id="gwrrjs3901_f" value="0"><label
								for="gwrrjs3901_f">否</label>
						</div>
					</td>
				</tr>



			</tbody>
		</table>
		<table class="table-hover" cellspacing="0" cellpadding="0" style="width: 80%; margin-left: 10%;">
	    	<tr>
	    		<td class="text-c">检查人员签字：</td>
	    		<td class="text-c"><input id="jcry" style="text-align:center;width:100%; height:100%;border:none" type="text" value=""/></td>
	    		<!--<td class="text-c">日期：</td>
	    		<td class="text-c"><input id="jcry" style="text-align:center;width:100%;height:100%;border:none" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>-->
	    	</tr>
	    </table>
	</div>


	<div class="mt-20 f-l mb-40" style="width: 100%; text-align: center;">
		<c:if test="${action != 'read'}">
			<button onClick="save()" class="btn btn-primary radius mt-20"
				type="button" style="padding: 0 70px;">
				<i class="Hui-iconfont mr-10">&#xe632;</i>保存
			</button>
		</c:if>
	</div>


</body>
<script type="text/javascript" src="/js/fxgk/tbl-rowspan-reset.js"></script>
<script type="text/javascript">
if("${action}" == "read"){
	
	$("input").attr("disabled","disabled");
	$("input").css("background-color","white");
	
	$("input[name='gwrrjs0101'][value='${ht.gwrrjs0101}']").attr("checked","checked");
	$("input[name='gwrrjs0102'][value='${ht.gwrrjs0102}']").attr("checked","checked");
	$("input[name='gwrrjs0103'][value='${ht.gwrrjs0103}']").attr("checked","checked");
	$("input[name='gwrrjs0104'][value='${ht.gwrrjs0104}']").attr("checked","checked");
	$("input[name='gwrrjs0105'][value='${ht.gwrrjs0105}']").attr("checked","checked");
	$("input[name='gwrrjs0201'][value='${ht.gwrrjs0201}']").attr("checked","checked");
	$("input[name='gwrrjs0202'][value='${ht.gwrrjs0202}']").attr("checked","checked");
	$("input[name='gwrrjs0203'][value='${ht.gwrrjs0203}']").attr("checked","checked");
	$("input[name='gwrrjs0204'][value='${ht.gwrrjs0204}']").attr("checked","checked");
	$("input[name='gwrrjs0205'][value='${ht.gwrrjs0205}']").attr("checked","checked");
	$("input[name='gwrrjs0206'][value='${ht.gwrrjs0206}']").attr("checked","checked");
	$("input[name='gwrrjs0207'][value='${ht.gwrrjs0207}']").attr("checked","checked");
	$("input[name='gwrrjs0208'][value='${ht.gwrrjs0208}']").attr("checked","checked");
	$("input[name='gwrrjs0209'][value='${ht.gwrrjs0209}']").attr("checked","checked");
	$("input[name='gwrrjs0210'][value='${ht.gwrrjs0210}']").attr("checked","checked");
	$("input[name='gwrrjs0211'][value='${ht.gwrrjs0211}']").attr("checked","checked");
	$("input[name='gwrrjs0212'][value='${ht.gwrrjs0212}']").attr("checked","checked");
	$("input[name='gwrrjs0213'][value='${ht.gwrrjs0213}']").attr("checked","checked");
	$("input[name='gwrrjs0214'][value='${ht.gwrrjs0214}']").attr("checked","checked");
	$("input[name='gwrrjs0215'][value='${ht.gwrrjs0215}']").attr("checked","checked");
	$("input[name='gwrrjs0216'][value='${ht.gwrrjs0216}']").attr("checked","checked");
	$("input[name='gwrrjs0217'][value='${ht.gwrrjs0217}']").attr("checked","checked");
	$("input[name='gwrrjs0301'][value='${ht.gwrrjs0301}']").attr("checked","checked");
	$("input[name='gwrrjs0302'][value='${ht.gwrrjs0302}']").attr("checked","checked");
	$("input[name='gwrrjs0303'][value='${ht.gwrrjs0303}']").attr("checked","checked");
	$("input[name='gwrrjs0304'][value='${ht.gwrrjs0304}']").attr("checked","checked");
	$("input[name='gwrrjs0305'][value='${ht.gwrrjs0305}']").attr("checked","checked");
	$("input[name='gwrrjs0306'][value='${ht.gwrrjs0306}']").attr("checked","checked");
	$("input[name='gwrrjs0307'][value='${ht.gwrrjs0307}']").attr("checked","checked");
	$("input[name='gwrrjs0308'][value='${ht.gwrrjs0308}']").attr("checked","checked");
	$("input[name='gwrrjs0309'][value='${ht.gwrrjs0309}']").attr("checked","checked");
	$("input[name='gwrrjs0310'][value='${ht.gwrrjs0310}']").attr("checked","checked");
	$("input[name='gwrrjs0311'][value='${ht.gwrrjs0311}']").attr("checked","checked");
	$("input[name='gwrrjs0312'][value='${ht.gwrrjs0312}']").attr("checked","checked");
	$("input[name='gwrrjs0401'][value='${ht.gwrrjs0401}']").attr("checked","checked");
	$("input[name='gwrrjs0402'][value='${ht.gwrrjs0402}']").attr("checked","checked");
	$("input[name='gwrrjs0403'][value='${ht.gwrrjs0403}']").attr("checked","checked");
	$("input[name='gwrrjs0404'][value='${ht.gwrrjs0404}']").attr("checked","checked");
	$("input[name='gwrrjs0501'][value='${ht.gwrrjs0501}']").attr("checked","checked");
	$("input[name='gwrrjs0502'][value='${ht.gwrrjs0502}']").attr("checked","checked");
	$("input[name='gwrrjs0503'][value='${ht.gwrrjs0503}']").attr("checked","checked");
	$("input[name='gwrrjs0504'][value='${ht.gwrrjs0504}']").attr("checked","checked");
	$("input[name='gwrrjs0601'][value='${ht.gwrrjs0601}']").attr("checked","checked");
	$("input[name='gwrrjs0602'][value='${ht.gwrrjs0602}']").attr("checked","checked");
	$("input[name='gwrrjs0603'][value='${ht.gwrrjs0603}']").attr("checked","checked");
	$("input[name='gwrrjs0604'][value='${ht.gwrrjs0604}']").attr("checked","checked");
	$("input[name='gwrrjs0701'][value='${ht.gwrrjs0701}']").attr("checked","checked");
	$("input[name='gwrrjs0702'][value='${ht.gwrrjs0702}']").attr("checked","checked");
	$("input[name='gwrrjs0801'][value='${ht.gwrrjs0801}']").attr("checked","checked");
	$("input[name='gwrrjs0901'][value='${ht.gwrrjs0901}']").attr("checked","checked");
	$("input[name='gwrrjs1001'][value='${ht.gwrrjs1001}']").attr("checked","checked");
	$("input[name='gwrrjs1002'][value='${ht.gwrrjs1002}']").attr("checked","checked");
	$("input[name='gwrrjs1101'][value='${ht.gwrrjs1101}']").attr("checked","checked");
	$("input[name='gwrrjs1201'][value='${ht.gwrrjs1201}']").attr("checked","checked");
	$("input[name='gwrrjs1202'][value='${ht.gwrrjs1202}']").attr("checked","checked");
	$("input[name='gwrrjs1301'][value='${ht.gwrrjs1301}']").attr("checked","checked");
	$("input[name='gwrrjs1302'][value='${ht.gwrrjs1302}']").attr("checked","checked");
	$("input[name='gwrrjs1303'][value='${ht.gwrrjs1303}']").attr("checked","checked");
	$("input[name='gwrrjs1304'][value='${ht.gwrrjs1304}']").attr("checked","checked");
	$("input[name='gwrrjs1401'][value='${ht.gwrrjs1401}']").attr("checked","checked");
	$("input[name='gwrrjs1402'][value='${ht.gwrrjs1402}']").attr("checked","checked");
	$("input[name='gwrrjs1403'][value='${ht.gwrrjs1403}']").attr("checked","checked");
	$("input[name='gwrrjs1404'][value='${ht.gwrrjs1404}']").attr("checked","checked");
	$("input[name='gwrrjs1501'][value='${ht.gwrrjs1501}']").attr("checked","checked");
	$("input[name='gwrrjs1502'][value='${ht.gwrrjs1502}']").attr("checked","checked");
	$("input[name='gwrrjs1503'][value='${ht.gwrrjs1503}']").attr("checked","checked");
	$("input[name='gwrrjs1504'][value='${ht.gwrrjs1504}']").attr("checked","checked");
	$("input[name='gwrrjs1601'][value='${ht.gwrrjs1601}']").attr("checked","checked");
	$("input[name='gwrrjs1602'][value='${ht.gwrrjs1602}']").attr("checked","checked");
	$("input[name='gwrrjs1701'][value='${ht.gwrrjs1701}']").attr("checked","checked");
	$("input[name='gwrrjs1702'][value='${ht.gwrrjs1702}']").attr("checked","checked");
	$("input[name='gwrrjs1703'][value='${ht.gwrrjs1703}']").attr("checked","checked");
	$("input[name='gwrrjs1704'][value='${ht.gwrrjs1704}']").attr("checked","checked");
	$("input[name='gwrrjs1705'][value='${ht.gwrrjs1705}']").attr("checked","checked");
	$("input[name='gwrrjs1706'][value='${ht.gwrrjs1706}']").attr("checked","checked");
	$("input[name='gwrrjs1707'][value='${ht.gwrrjs1707}']").attr("checked","checked");
	$("input[name='gwrrjs1708'][value='${ht.gwrrjs1708}']").attr("checked","checked");
	$("input[name='gwrrjs1709'][value='${ht.gwrrjs1709}']").attr("checked","checked");
	$("input[name='gwrrjs1710'][value='${ht.gwrrjs1710}']").attr("checked","checked");
	$("input[name='gwrrjs1711'][value='${ht.gwrrjs1711}']").attr("checked","checked");
	$("input[name='gwrrjs1712'][value='${ht.gwrrjs1712}']").attr("checked","checked");
	$("input[name='gwrrjs1713'][value='${ht.gwrrjs1713}']").attr("checked","checked");
	$("input[name='gwrrjs1714'][value='${ht.gwrrjs1714}']").attr("checked","checked");
	$("input[name='gwrrjs1801'][value='${ht.gwrrjs1801}']").attr("checked","checked");
	$("input[name='gwrrjs1802'][value='${ht.gwrrjs1802}']").attr("checked","checked");
	$("input[name='gwrrjs1803'][value='${ht.gwrrjs1803}']").attr("checked","checked");
	$("input[name='gwrrjs1804'][value='${ht.gwrrjs1804}']").attr("checked","checked");
	$("input[name='gwrrjs1805'][value='${ht.gwrrjs1805}']").attr("checked","checked");
	$("input[name='gwrrjs1806'][value='${ht.gwrrjs1806}']").attr("checked","checked");
	$("input[name='gwrrjs1807'][value='${ht.gwrrjs1807}']").attr("checked","checked");
	$("input[name='gwrrjs1901'][value='${ht.gwrrjs1901}']").attr("checked","checked");
	$("input[name='gwrrjs1902'][value='${ht.gwrrjs1902}']").attr("checked","checked");
	$("input[name='gwrrjs1903'][value='${ht.gwrrjs1903}']").attr("checked","checked");
	$("input[name='gwrrjs1904'][value='${ht.gwrrjs1904}']").attr("checked","checked");
	$("input[name='gwrrjs2001'][value='${ht.gwrrjs2001}']").attr("checked","checked");
	$("input[name='gwrrjs2101'][value='${ht.gwrrjs2101}']").attr("checked","checked");
	$("input[name='gwrrjs2102'][value='${ht.gwrrjs2102}']").attr("checked","checked");
	$("input[name='gwrrjs2103'][value='${ht.gwrrjs2103}']").attr("checked","checked");
	$("input[name='gwrrjs2201'][value='${ht.gwrrjs2201}']").attr("checked","checked");
	$("input[name='gwrrjs2202'][value='${ht.gwrrjs2202}']").attr("checked","checked");
	$("input[name='gwrrjs2203'][value='${ht.gwrrjs2203}']").attr("checked","checked");
	$("input[name='gwrrjs2301'][value='${ht.gwrrjs2301}']").attr("checked","checked");
	$("input[name='gwrrjs2302'][value='${ht.gwrrjs2302}']").attr("checked","checked");
	$("input[name='gwrrjs2303'][value='${ht.gwrrjs2303}']").attr("checked","checked");
	$("input[name='gwrrjs2304'][value='${ht.gwrrjs2304}']").attr("checked","checked");
	$("input[name='gwrrjs2401'][value='${ht.gwrrjs2401}']").attr("checked","checked");
	$("input[name='gwrrjs2402'][value='${ht.gwrrjs2402}']").attr("checked","checked");
	$("input[name='gwrrjs2403'][value='${ht.gwrrjs2403}']").attr("checked","checked");
	$("input[name='gwrrjs2501'][value='${ht.gwrrjs2501}']").attr("checked","checked");
	$("input[name='gwrrjs2502'][value='${ht.gwrrjs2502}']").attr("checked","checked");
	$("input[name='gwrrjs2503'][value='${ht.gwrrjs2503}']").attr("checked","checked");
	$("input[name='gwrrjs2504'][value='${ht.gwrrjs2504}']").attr("checked","checked");
	$("input[name='gwrrjs2601'][value='${ht.gwrrjs2601}']").attr("checked","checked");
	$("input[name='gwrrjs2602'][value='${ht.gwrrjs2602}']").attr("checked","checked");
	$("input[name='gwrrjs2603'][value='${ht.gwrrjs2603}']").attr("checked","checked");
	$("input[name='gwrrjs2604'][value='${ht.gwrrjs2604}']").attr("checked","checked");
	$("input[name='gwrrjs2701'][value='${ht.gwrrjs2701}']").attr("checked","checked");
	$("input[name='gwrrjs2702'][value='${ht.gwrrjs2702}']").attr("checked","checked");
	$("input[name='gwrrjs2703'][value='${ht.gwrrjs2703}']").attr("checked","checked");
	$("input[name='gwrrjs2704'][value='${ht.gwrrjs2704}']").attr("checked","checked");
	$("input[name='gwrrjs2801'][value='${ht.gwrrjs2801}']").attr("checked","checked");
	$("input[name='gwrrjs2802'][value='${ht.gwrrjs2802}']").attr("checked","checked");
	$("input[name='gwrrjs2803'][value='${ht.gwrrjs2803}']").attr("checked","checked");
	$("input[name='gwrrjs2804'][value='${ht.gwrrjs2804}']").attr("checked","checked");
	$("input[name='gwrrjs2805'][value='${ht.gwrrjs2805}']").attr("checked","checked");
	$("input[name='gwrrjs2806'][value='${ht.gwrrjs2806}']").attr("checked","checked");
	$("input[name='gwrrjs2807'][value='${ht.gwrrjs2807}']").attr("checked","checked");
	$("input[name='gwrrjs2808'][value='${ht.gwrrjs2808}']").attr("checked","checked");
	$("input[name='gwrrjs2809'][value='${ht.gwrrjs2809}']").attr("checked","checked");
	$("input[name='gwrrjs2810'][value='${ht.gwrrjs2810}']").attr("checked","checked");
	$("input[name='gwrrjs2811'][value='${ht.gwrrjs2811}']").attr("checked","checked");
	$("input[name='gwrrjs2812'][value='${ht.gwrrjs2812}']").attr("checked","checked");
	$("input[name='gwrrjs2813'][value='${ht.gwrrjs2813}']").attr("checked","checked");
	$("input[name='gwrrjs2814'][value='${ht.gwrrjs2814}']").attr("checked","checked");
	$("input[name='gwrrjs2815'][value='${ht.gwrrjs2815}']").attr("checked","checked");
	$("input[name='gwrrjs2901'][value='${ht.gwrrjs2901}']").attr("checked","checked");
	$("input[name='gwrrjs2902'][value='${ht.gwrrjs2902}']").attr("checked","checked");
	$("input[name='gwrrjs2903'][value='${ht.gwrrjs2903}']").attr("checked","checked");
	$("input[name='gwrrjs2904'][value='${ht.gwrrjs2904}']").attr("checked","checked");
	$("input[name='gwrrjs3001'][value='${ht.gwrrjs3001}']").attr("checked","checked");
	$("input[name='gwrrjs3002'][value='${ht.gwrrjs3002}']").attr("checked","checked");
	$("input[name='gwrrjs3003'][value='${ht.gwrrjs3003}']").attr("checked","checked");
	$("input[name='gwrrjs3101'][value='${ht.gwrrjs3101}']").attr("checked","checked");
	$("input[name='gwrrjs3102'][value='${ht.gwrrjs3102}']").attr("checked","checked");
	$("input[name='gwrrjs3103'][value='${ht.gwrrjs3103}']").attr("checked","checked");
	$("input[name='gwrrjs3201'][value='${ht.gwrrjs3201}']").attr("checked","checked");
	$("input[name='gwrrjs3202'][value='${ht.gwrrjs3202}']").attr("checked","checked");
	$("input[name='gwrrjs3203'][value='${ht.gwrrjs3203}']").attr("checked","checked");
	$("input[name='gwrrjs3204'][value='${ht.gwrrjs3204}']").attr("checked","checked");
	$("input[name='gwrrjs3205'][value='${ht.gwrrjs3205}']").attr("checked","checked");
	$("input[name='gwrrjs3301'][value='${ht.gwrrjs3301}']").attr("checked","checked");
	$("input[name='gwrrjs3302'][value='${ht.gwrrjs3302}']").attr("checked","checked");
	$("input[name='gwrrjs3303'][value='${ht.gwrrjs3303}']").attr("checked","checked");
	$("input[name='gwrrjs3304'][value='${ht.gwrrjs3304}']").attr("checked","checked");
	$("input[name='gwrrjs3401'][value='${ht.gwrrjs3401}']").attr("checked","checked");
	$("input[name='gwrrjs3402'][value='${ht.gwrrjs3402}']").attr("checked","checked");
	$("input[name='gwrrjs3403'][value='${ht.gwrrjs3403}']").attr("checked","checked");
	$("input[name='gwrrjs3404'][value='${ht.gwrrjs3404}']").attr("checked","checked");
	$("input[name='gwrrjs3405'][value='${ht.gwrrjs3405}']").attr("checked","checked");
	$("input[name='gwrrjs3406'][value='${ht.gwrrjs3406}']").attr("checked","checked");
	$("input[name='gwrrjs3501'][value='${ht.gwrrjs3501}']").attr("checked","checked");
	$("input[name='gwrrjs3502'][value='${ht.gwrrjs3502}']").attr("checked","checked");
	$("input[name='gwrrjs3503'][value='${ht.gwrrjs3503}']").attr("checked","checked");
	$("input[name='gwrrjs3504'][value='${ht.gwrrjs3504}']").attr("checked","checked");
	$("input[name='gwrrjs3601'][value='${ht.gwrrjs3601}']").attr("checked","checked");
	$("input[name='gwrrjs3602'][value='${ht.gwrrjs3602}']").attr("checked","checked");
	$("input[name='gwrrjs3603'][value='${ht.gwrrjs3603}']").attr("checked","checked");
	$("input[name='gwrrjs3604'][value='${ht.gwrrjs3604}']").attr("checked","checked");
	$("input[name='gwrrjs3605'][value='${ht.gwrrjs3605}']").attr("checked","checked");
	$("input[name='gwrrjs3701'][value='${ht.gwrrjs3701}']").attr("checked","checked");
	$("input[name='gwrrjs3702'][value='${ht.gwrrjs3702}']").attr("checked","checked");
	$("input[name='gwrrjs3801'][value='${ht.gwrrjs3801}']").attr("checked","checked");
	$("input[name='gwrrjs3802'][value='${ht.gwrrjs3802}']").attr("checked","checked");
	$("input[name='gwrrjs3803'][value='${ht.gwrrjs3803}']").attr("checked","checked");
	$("input[name='gwrrjs3901'][value='${ht.gwrrjs3901}']").attr("checked","checked");
	$("#jcry").attr("value","${ht.jcry }");

}
function save() {
	if(!$("#jcry").val()){
		$("#jcry").focus();
		alert("请签字！");
		return;
	}
	
  var obj = {
	"gwrrjs0101" : $("input[name='gwrrjs0101']:checked").val(),
	"gwrrjs0102" : $("input[name='gwrrjs0102']:checked").val(),
	"gwrrjs0103" : $("input[name='gwrrjs0103']:checked").val(),
	"gwrrjs0104" : $("input[name='gwrrjs0104']:checked").val(),
	"gwrrjs0105" : $("input[name='gwrrjs0105']:checked").val(),
	"gwrrjs0201" : $("input[name='gwrrjs0201']:checked").val(),
	"gwrrjs0202" : $("input[name='gwrrjs0202']:checked").val(),
	"gwrrjs0203" : $("input[name='gwrrjs0203']:checked").val(),
	"gwrrjs0204" : $("input[name='gwrrjs0204']:checked").val(),
	"gwrrjs0205" : $("input[name='gwrrjs0205']:checked").val(),
	"gwrrjs0206" : $("input[name='gwrrjs0206']:checked").val(),
	"gwrrjs0207" : $("input[name='gwrrjs0207']:checked").val(),
	"gwrrjs0208" : $("input[name='gwrrjs0208']:checked").val(),
	"gwrrjs0209" : $("input[name='gwrrjs0209']:checked").val(),
	"gwrrjs0210" : $("input[name='gwrrjs0210']:checked").val(),
	"gwrrjs0211" : $("input[name='gwrrjs0211']:checked").val(),
	"gwrrjs0212" : $("input[name='gwrrjs0212']:checked").val(),
	"gwrrjs0213" : $("input[name='gwrrjs0213']:checked").val(),
	"gwrrjs0214" : $("input[name='gwrrjs0214']:checked").val(),
	"gwrrjs0215" : $("input[name='gwrrjs0215']:checked").val(),
	"gwrrjs0216" : $("input[name='gwrrjs0216']:checked").val(),
	"gwrrjs0217" : $("input[name='gwrrjs0217']:checked").val(),
	"gwrrjs0301" : $("input[name='gwrrjs0301']:checked").val(),
	"gwrrjs0302" : $("input[name='gwrrjs0302']:checked").val(),
	"gwrrjs0303" : $("input[name='gwrrjs0303']:checked").val(),
	"gwrrjs0304" : $("input[name='gwrrjs0304']:checked").val(),
	"gwrrjs0305" : $("input[name='gwrrjs0305']:checked").val(),
	"gwrrjs0306" : $("input[name='gwrrjs0306']:checked").val(),
	"gwrrjs0307" : $("input[name='gwrrjs0307']:checked").val(),
	"gwrrjs0308" : $("input[name='gwrrjs0308']:checked").val(),
	"gwrrjs0309" : $("input[name='gwrrjs0309']:checked").val(),
	"gwrrjs0310" : $("input[name='gwrrjs0310']:checked").val(),
	"gwrrjs0311" : $("input[name='gwrrjs0311']:checked").val(),
	"gwrrjs0312" : $("input[name='gwrrjs0312']:checked").val(),
	"gwrrjs0401" : $("input[name='gwrrjs0401']:checked").val(),
	"gwrrjs0402" : $("input[name='gwrrjs0402']:checked").val(),
	"gwrrjs0403" : $("input[name='gwrrjs0403']:checked").val(),
	"gwrrjs0404" : $("input[name='gwrrjs0404']:checked").val(),
	"gwrrjs0501" : $("input[name='gwrrjs0501']:checked").val(),
	"gwrrjs0502" : $("input[name='gwrrjs0502']:checked").val(),
	"gwrrjs0503" : $("input[name='gwrrjs0503']:checked").val(),
	"gwrrjs0504" : $("input[name='gwrrjs0504']:checked").val(),
	"gwrrjs0601" : $("input[name='gwrrjs0601']:checked").val(),
	"gwrrjs0602" : $("input[name='gwrrjs0602']:checked").val(),
	"gwrrjs0603" : $("input[name='gwrrjs0603']:checked").val(),
	"gwrrjs0604" : $("input[name='gwrrjs0604']:checked").val(),
	"gwrrjs0701" : $("input[name='gwrrjs0701']:checked").val(),
	"gwrrjs0702" : $("input[name='gwrrjs0702']:checked").val(),
	"gwrrjs0801" : $("input[name='gwrrjs0801']:checked").val(),
	"gwrrjs0901" : $("input[name='gwrrjs0901']:checked").val(),
	"gwrrjs1001" : $("input[name='gwrrjs1001']:checked").val(),
	"gwrrjs1002" : $("input[name='gwrrjs1002']:checked").val(),
	"gwrrjs1101" : $("input[name='gwrrjs1101']:checked").val(),
	"gwrrjs1201" : $("input[name='gwrrjs1201']:checked").val(),
	"gwrrjs1202" : $("input[name='gwrrjs1202']:checked").val(),
	"gwrrjs1301" : $("input[name='gwrrjs1301']:checked").val(),
	"gwrrjs1302" : $("input[name='gwrrjs1302']:checked").val(),
	"gwrrjs1303" : $("input[name='gwrrjs1303']:checked").val(),
	"gwrrjs1304" : $("input[name='gwrrjs1304']:checked").val(),
	"gwrrjs1401" : $("input[name='gwrrjs1401']:checked").val(),
	"gwrrjs1402" : $("input[name='gwrrjs1402']:checked").val(),
	"gwrrjs1403" : $("input[name='gwrrjs1403']:checked").val(),
	"gwrrjs1404" : $("input[name='gwrrjs1404']:checked").val(),
	"gwrrjs1501" : $("input[name='gwrrjs1501']:checked").val(),
	"gwrrjs1502" : $("input[name='gwrrjs1502']:checked").val(),
	"gwrrjs1503" : $("input[name='gwrrjs1503']:checked").val(),
	"gwrrjs1504" : $("input[name='gwrrjs1504']:checked").val(),
	"gwrrjs1601" : $("input[name='gwrrjs1601']:checked").val(),
	"gwrrjs1602" : $("input[name='gwrrjs1602']:checked").val(),
	"gwrrjs1701" : $("input[name='gwrrjs1701']:checked").val(),
	"gwrrjs1702" : $("input[name='gwrrjs1702']:checked").val(),
	"gwrrjs1703" : $("input[name='gwrrjs1703']:checked").val(),
	"gwrrjs1704" : $("input[name='gwrrjs1704']:checked").val(),
	"gwrrjs1705" : $("input[name='gwrrjs1705']:checked").val(),
	"gwrrjs1706" : $("input[name='gwrrjs1706']:checked").val(),
	"gwrrjs1707" : $("input[name='gwrrjs1707']:checked").val(),
	"gwrrjs1708" : $("input[name='gwrrjs1708']:checked").val(),
	"gwrrjs1709" : $("input[name='gwrrjs1709']:checked").val(),
	"gwrrjs1710" : $("input[name='gwrrjs1710']:checked").val(),
	"gwrrjs1711" : $("input[name='gwrrjs1711']:checked").val(),
	"gwrrjs1712" : $("input[name='gwrrjs1712']:checked").val(),
	"gwrrjs1713" : $("input[name='gwrrjs1713']:checked").val(),
	"gwrrjs1714" : $("input[name='gwrrjs1714']:checked").val(),
	"gwrrjs1801" : $("input[name='gwrrjs1801']:checked").val(),
	"gwrrjs1802" : $("input[name='gwrrjs1802']:checked").val(),
	"gwrrjs1803" : $("input[name='gwrrjs1803']:checked").val(),
	"gwrrjs1804" : $("input[name='gwrrjs1804']:checked").val(),
	"gwrrjs1805" : $("input[name='gwrrjs1805']:checked").val(),
	"gwrrjs1806" : $("input[name='gwrrjs1806']:checked").val(),
	"gwrrjs1807" : $("input[name='gwrrjs1807']:checked").val(),
	"gwrrjs1901" : $("input[name='gwrrjs1901']:checked").val(),
	"gwrrjs1902" : $("input[name='gwrrjs1902']:checked").val(),
	"gwrrjs1903" : $("input[name='gwrrjs1903']:checked").val(),
	"gwrrjs1904" : $("input[name='gwrrjs1904']:checked").val(),
	"gwrrjs2001" : $("input[name='gwrrjs2001']:checked").val(),
	"gwrrjs2101" : $("input[name='gwrrjs2101']:checked").val(),
	"gwrrjs2102" : $("input[name='gwrrjs2102']:checked").val(),
	"gwrrjs2103" : $("input[name='gwrrjs2103']:checked").val(),
	"gwrrjs2201" : $("input[name='gwrrjs2201']:checked").val(),
	"gwrrjs2202" : $("input[name='gwrrjs2202']:checked").val(),
	"gwrrjs2203" : $("input[name='gwrrjs2203']:checked").val(),
	"gwrrjs2301" : $("input[name='gwrrjs2301']:checked").val(),
	"gwrrjs2302" : $("input[name='gwrrjs2302']:checked").val(),
	"gwrrjs2303" : $("input[name='gwrrjs2303']:checked").val(),
	"gwrrjs2304" : $("input[name='gwrrjs2304']:checked").val(),
	"gwrrjs2401" : $("input[name='gwrrjs2401']:checked").val(),
	"gwrrjs2402" : $("input[name='gwrrjs2402']:checked").val(),
	"gwrrjs2403" : $("input[name='gwrrjs2403']:checked").val(),
	"gwrrjs2501" : $("input[name='gwrrjs2501']:checked").val(),
	"gwrrjs2502" : $("input[name='gwrrjs2502']:checked").val(),
	"gwrrjs2503" : $("input[name='gwrrjs2503']:checked").val(),
	"gwrrjs2504" : $("input[name='gwrrjs2504']:checked").val(),
	"gwrrjs2601" : $("input[name='gwrrjs2601']:checked").val(),
	"gwrrjs2602" : $("input[name='gwrrjs2602']:checked").val(),
	"gwrrjs2603" : $("input[name='gwrrjs2603']:checked").val(),
	"gwrrjs2604" : $("input[name='gwrrjs2604']:checked").val(),
	"gwrrjs2701" : $("input[name='gwrrjs2701']:checked").val(),
	"gwrrjs2702" : $("input[name='gwrrjs2702']:checked").val(),
	"gwrrjs2703" : $("input[name='gwrrjs2703']:checked").val(),
	"gwrrjs2704" : $("input[name='gwrrjs2704']:checked").val(),
	"gwrrjs2801" : $("input[name='gwrrjs2801']:checked").val(),
	"gwrrjs2802" : $("input[name='gwrrjs2802']:checked").val(),
	"gwrrjs2803" : $("input[name='gwrrjs2803']:checked").val(),
	"gwrrjs2804" : $("input[name='gwrrjs2804']:checked").val(),
	"gwrrjs2805" : $("input[name='gwrrjs2805']:checked").val(),
	"gwrrjs2806" : $("input[name='gwrrjs2806']:checked").val(),
	"gwrrjs2807" : $("input[name='gwrrjs2807']:checked").val(),
	"gwrrjs2808" : $("input[name='gwrrjs2808']:checked").val(),
	"gwrrjs2809" : $("input[name='gwrrjs2809']:checked").val(),
	"gwrrjs2810" : $("input[name='gwrrjs2810']:checked").val(),
	"gwrrjs2811" : $("input[name='gwrrjs2811']:checked").val(),
	"gwrrjs2812" : $("input[name='gwrrjs2812']:checked").val(),
	"gwrrjs2813" : $("input[name='gwrrjs2813']:checked").val(),
	"gwrrjs2814" : $("input[name='gwrrjs2814']:checked").val(),
	"gwrrjs2815" : $("input[name='gwrrjs2815']:checked").val(),
	"gwrrjs2901" : $("input[name='gwrrjs2901']:checked").val(),
	"gwrrjs2902" : $("input[name='gwrrjs2902']:checked").val(),
	"gwrrjs2903" : $("input[name='gwrrjs2903']:checked").val(),
	"gwrrjs2904" : $("input[name='gwrrjs2904']:checked").val(),
	"gwrrjs3001" : $("input[name='gwrrjs3001']:checked").val(),
	"gwrrjs3002" : $("input[name='gwrrjs3002']:checked").val(),
	"gwrrjs3003" : $("input[name='gwrrjs3003']:checked").val(),
	"gwrrjs3101" : $("input[name='gwrrjs3101']:checked").val(),
	"gwrrjs3102" : $("input[name='gwrrjs3102']:checked").val(),
	"gwrrjs3103" : $("input[name='gwrrjs3103']:checked").val(),
	"gwrrjs3201" : $("input[name='gwrrjs3201']:checked").val(),
	"gwrrjs3202" : $("input[name='gwrrjs3202']:checked").val(),
	"gwrrjs3203" : $("input[name='gwrrjs3203']:checked").val(),
	"gwrrjs3204" : $("input[name='gwrrjs3204']:checked").val(),
	"gwrrjs3205" : $("input[name='gwrrjs3205']:checked").val(),
	"gwrrjs3301" : $("input[name='gwrrjs3301']:checked").val(),
	"gwrrjs3302" : $("input[name='gwrrjs3302']:checked").val(),
	"gwrrjs3303" : $("input[name='gwrrjs3303']:checked").val(),
	"gwrrjs3304" : $("input[name='gwrrjs3304']:checked").val(),
	"gwrrjs3401" : $("input[name='gwrrjs3401']:checked").val(),
	"gwrrjs3402" : $("input[name='gwrrjs3402']:checked").val(),
	"gwrrjs3403" : $("input[name='gwrrjs3403']:checked").val(),
	"gwrrjs3404" : $("input[name='gwrrjs3404']:checked").val(),
	"gwrrjs3405" : $("input[name='gwrrjs3405']:checked").val(),
	"gwrrjs3406" : $("input[name='gwrrjs3406']:checked").val(),
	"gwrrjs3501" : $("input[name='gwrrjs3501']:checked").val(),
	"gwrrjs3502" : $("input[name='gwrrjs3502']:checked").val(),
	"gwrrjs3503" : $("input[name='gwrrjs3503']:checked").val(),
	"gwrrjs3504" : $("input[name='gwrrjs3504']:checked").val(),
	"gwrrjs3601" : $("input[name='gwrrjs3601']:checked").val(),
	"gwrrjs3602" : $("input[name='gwrrjs3602']:checked").val(),
	"gwrrjs3603" : $("input[name='gwrrjs3603']:checked").val(),
	"gwrrjs3604" : $("input[name='gwrrjs3604']:checked").val(),
	"gwrrjs3605" : $("input[name='gwrrjs3605']:checked").val(),
	"gwrrjs3701" : $("input[name='gwrrjs3701']:checked").val(),
	"gwrrjs3702" : $("input[name='gwrrjs3702']:checked").val(),
	"gwrrjs3801" : $("input[name='gwrrjs3801']:checked").val(),
	"gwrrjs3802" : $("input[name='gwrrjs3802']:checked").val(),
	"gwrrjs3803" : $("input[name='gwrrjs3803']:checked").val(),
	"gwrrjs3901" : $("input[name='gwrrjs3901']:checked").val(),
	"jcry" : $("#jcry").val(),
	
    
  }
/*  if(obj.sqdw == '') {
    $("#sqdw").focus();
    alert("请填写申请单位！");
    return false;
  }
  if(obj.sqr == '') {
    $("#sqr").focus();
    alert("请填写申请人！");
    return false;
  }*/
  //alert($("#sqdwqzsj").val());
  var i = layer.load();
  $.post(getRootPath() + "/company/source/thigh-temperature-save", obj,function(result){
    layer.close(i);
    if(result.status == '1') {
    	layer.alert(result.map.message);
    } else {
    	layer.alert("保存成功！",{},function(i){
    		parent.location.reload();
    	});
    }
  })
}
</script>
</html>