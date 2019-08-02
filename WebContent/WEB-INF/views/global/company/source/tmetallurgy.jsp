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
			class="c-gray en">&gt;</span> <span>冶金企业煤气</span> <a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:void(0);" title="返回"
			onclick="parent.close_tab(function(){})">返回</a>
	</nav>


	<h3 class="text-c">《冶金企业煤气》</h3>
	<div class="mt-20">

		<!--<table style="width:50%;" cellpadding="0" cellspacing="0"> table-sort tab-ndan table-bg   table   table-border table-bordered-->
		<table class="table-hover" cellspacing="0" cellpadding="0"
			style="width: 80%; margin-left: 10%;">
			<thead>
				<tr class="text-c">
					<th style="width: 100px">要求种类</th>
					<th style="min-width: 300px">要求内容</th>
					<th style="min-width: 200px">是否达标</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="text-c" rowspan="2">现场爬梯、平台、护栏安全要求</td>
					<td class="text-c">直梯、斜梯、防护栏杆和工作平台是否符合《固定式钢梯及平台安全要求》 （GB4053.1-3）的规定。</td>
					<td id="yjqymq0101" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0101" id="yjqymq0101_s" checked="checked"
								value="1"><label for="yjqymq0101_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0101" id="yjqymq0101_f" value="0"><label
								for="yjqymq0101_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="2">现场爬梯、平台、护栏安全要求</td>
					<td class="text-c">煤气设施的人孔、阀门、仪表等经常有人操作的部位，是否设置固定平台。</td>
					<td id="yjqymq0102" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0102" id="yjqymq0102_s" checked="checked"
								value="1"><label for="yjqymq0102_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0102" id="yjqymq0102_f" value="0"><label
								for="yjqymq0102_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="13">现场及岗位应设置安全检测装置及救援装备</td>
					<td class="text-c">煤气危险区域，包括高炉风口及以上各层平台、转炉炉口以上平台、高炉热风炉、干法除尘各层平台、煤气柜活塞上部、烧结点火器及热风炉、加热炉、管式炉、燃气锅炉等燃烧器旁等易产生煤气泄漏的区域和焦炉地下室、加压站房、风机房等封闭或半封闭空间等，设置高度1米至2米的固定式煤气报警器。易泄漏的设备设施释放源周边2米范围内，高度高于释放源0.5米至2米的位置设置具备声光报警功能的固定式CO报警器，现场报警信号是否引入相关岗位进行集中报警显示。</td>
					<td id="yjqymq0201" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0201" id="yjqymq0201_s" checked="checked"
								value="1"><label for="yjqymq0201_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0201" id="yjqymq0201_f" value="0"><label
								for="yjqymq0201_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="13">现场及岗位应设置安全检测装置及救援装备</td>
					<td class="text-c">是否配备符合岗位需求的正压式空气呼吸器、氧含量检测仪、便携式CO报警器。</td>
					<td id="yjqymq0202" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0202" id="yjqymq0202_s" checked="checked"
								value="1"><label for="yjqymq0202_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0202" id="yjqymq0202_f" value="0"><label
								for="yjqymq0202_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="13">现场及岗位应设置安全检测装置及救援装备</td>
					<td class="text-c">正压式空气呼吸器、氧含量检测仪、便携式CO报警器是否定期检查保持完好。 </td>
					<td id="yjqymq0203" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0203" id="yjqymq0203_s" checked="checked"
								value="1"><label for="yjqymq0203_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0203" id="yjqymq0203_f" value="0"><label
								for="yjqymq0203_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="13">现场及岗位应设置安全检测装置及救援装备</td>
					<td class="text-c">岗位是否有相关设施的检查记录和检查标准。</td>
					<td id="yjqymq0204" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0204" id="yjqymq0204_s" checked="checked"
								value="1"><label for="yjqymq0204_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0204" id="yjqymq0204_f" value="0"><label
								for="yjqymq0204_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="13">现场及岗位应设置安全检测装置及救援装备</td>
					<td class="text-c">空气呼吸器正常压力是否在15MPa以上。</td>
					<td id="yjqymq0205" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0205" id="yjqymq0205_s" checked="checked"
								value="1"><label for="yjqymq0205_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0205" id="yjqymq0205_f" value="0"><label
								for="yjqymq0205_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="13">现场及岗位应设置安全检测装置及救援装备</td>
					<td class="text-c">氧含量检测仪量程是否在0至25%。</td>
					<td id="yjqymq0206" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0206" id="yjqymq0206_s" checked="checked"
								value="1"><label for="yjqymq0206_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0206" id="yjqymq0206_f" value="0"><label
								for="yjqymq0206_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="13">现场及岗位应设置安全检测装置及救援装备</td>
					<td class="text-c">固定式、便携式CO报警器量程是否在0至1000ppm。</td>
					<td id="yjqymq0207" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0207" id="yjqymq0207_s" checked="checked"
								value="1"><label for="yjqymq0207_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0207" id="yjqymq0207_f" value="0"><label
								for="yjqymq0207_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="13">现场及岗位应设置安全检测装置及救援装备</td>
					<td class="text-c">煤气、氧气检测设备是否由具备鉴定资质的单位每年校验一次。</td>
					<td id="yjqymq0208" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0208" id="yjqymq0208_s" checked="checked"
								value="1"><label for="yjqymq0208_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0208" id="yjqymq0208_f" value="0"><label
								for="yjqymq0208_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="13">现场及岗位应设置安全检测装置及救援装备</td>
					<td class="text-c">固定式、便携式报警器检验标贴是否完好。</td>
					<td id="yjqymq0209" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0209" id="yjqymq0209_s" checked="checked"
								value="1"><label for="yjqymq0209_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0209" id="yjqymq0209_f" value="0"><label
								for="yjqymq0209_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="13">现场及岗位应设置安全检测装置及救援装备</td>
					<td class="text-c">煤气、氧气检测设备是否在检定有效期（一年）内。</td>
					<td id="yjqymq0210" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0210" id="yjqymq0210_s" checked="checked"
								value="1"><label for="yjqymq0210_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0210" id="yjqymq0210_f" value="0"><label
								for="yjqymq0210_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="13">现场及岗位应设置安全检测装置及救援装备</td>
					<td class="text-c">固定报警器是否具备声光报警功能。</td>
					<td id="yjqymq0211" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0211" id="yjqymq0211_s" checked="checked"
								value="1"><label for="yjqymq0211_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0211" id="yjqymq0211_f" value="0"><label
								for="yjqymq0211_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="13">现场及岗位应设置安全检测装置及救援装备</td>
					<td class="text-c">固定报警器接线是否符合隔爆要求。</td>
					<td id="yjqymq0212" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0212" id="yjqymq0212_s" checked="checked"
								value="1"><label for="yjqymq0212_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0212" id="yjqymq0212_f" value="0"><label
								for="yjqymq0212_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="13">现场及岗位应设置安全检测装置及救援装备</td>
					<td class="text-c">固定报警器安装高度是否在0.8至1.5米之间。</td>
					<td id="yjqymq0213" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0213" id="yjqymq0213_s" checked="checked"
								value="1"><label for="yjqymq0213_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0213" id="yjqymq0213_f" value="0"><label
								for="yjqymq0213_f">否</label>
						</div>
					</td>
				</tr>

				<tr>
					<td class="text-c" rowspan="11">隔断装置安全要求</td>
					<td class="text-c">经常检修的部位是否设可靠的隔断装置。</td>
					<td id="yjqymq0301" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0301" id="yjqymq0301_s" checked="checked"
								value="1"><label for="yjqymq0301_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0301" id="yjqymq0301_f" value="0"><label
								for="yjqymq0301_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="11">隔断装置安全要求</td>
					<td class="text-c">寒冷地区的隔断装置，是否根据当地的气温条件采取防冻措施。</td>
					<td id="yjqymq0302" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0302" id="yjqymq0302_s" checked="checked"
								value="1"><label for="yjqymq0302_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0302" id="yjqymq0302_f" value="0"><label
								for="yjqymq0302_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="11">隔断装置安全要求</td>
					<td class="text-c">金属密封面的插板是否不小于8m，非金属密封面的插板是否不小于6m，在煤气不易扩散地区须适当加高；封闭式插板的安设高度可适当降低。 </td>
					<td id="yjqymq0303" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0303" id="yjqymq0303_s" checked="checked"
								value="1"><label for="yjqymq0303_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0303" id="yjqymq0303_f" value="0"><label
								for="yjqymq0303_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="11">隔断装置安全要求</td>
					<td class="text-c">眼镜阀和扇形阀不宜单独使用，应设在密封蝶阀或闸阀后面。</td>
					<td id="yjqymq0304" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0304" id="yjqymq0304_s" checked="checked"
								value="1"><label for="yjqymq0304_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0304" id="yjqymq0304_f" value="0"><label
								for="yjqymq0304_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="11">隔断装置安全要求</td>
					<td class="text-c">水封是否装在其他隔断装置之后并用，水封的有效高度为煤气计算压力至少加500mm。</td>
					<td id="yjqymq0305" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0305" id="yjqymq0305_s" checked="checked"
								value="1"><label for="yjqymq0305_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0305" id="yjqymq0305_f" value="0"><label
								for="yjqymq0305_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="11">隔断装置安全要求</td>
					<td class="text-c">水封的给水管上是否设给水阀和止回阀。</td>
					<td id="yjqymq0306" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0306" id="yjqymq0306_s" checked="checked"
								value="1"><label for="yjqymq0306_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0306" id="yjqymq0306_f" value="0"><label
								for="yjqymq0306_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="11">隔断装置安全要求</td>
					<td class="text-c">水封下部侧壁上是否安设清扫孔和放水头。</td>
					<td id="yjqymq0307" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0307" id="yjqymq0307_s" checked="checked"
								value="1"><label for="yjqymq0307_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0307" id="yjqymq0307_f" value="0"><label
								for="yjqymq0307_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="11">隔断装置安全要求</td>
					<td class="text-c">U型水封两侧是否安设放散管、吹刷用的进气头和取样管。</td>
					<td id="yjqymq0308" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0308" id="yjqymq0308_s" checked="checked"
								value="1"><label for="yjqymq0308_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0308" id="yjqymq0308_f" value="0"><label
								for="yjqymq0308_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="11">隔断装置安全要求</td>
					<td class="text-c">眼镜阀和扇形阀不宜单独使用，是否设在密封蝶阀或闸阀后面。</td>
					<td id="yjqymq0309" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0309" id="yjqymq0309_s" checked="checked"
								value="1"><label for="yjqymq0309_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0309" id="yjqymq0309_f" value="0"><label
								for="yjqymq0309_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="11">隔断装置安全要求</td>
					<td class="text-c">敞开眼镜阀和扇形阀是否安设在厂房外，如设在厂房内，应离炉子10 m以上。</td>
					<td id="yjqymq0310" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0310" id="yjqymq0310_s" checked="checked"
								value="1"><label for="yjqymq0310_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0310" id="yjqymq0310_f" value="0"><label
								for="yjqymq0310_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="11">隔断装置安全要求</td>
					<td class="text-c">密封蝶阀和旋塞头部应有明显的开关标志。</td>
					<td id="yjqymq0311" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0311" id="yjqymq0311_s" checked="checked"
								value="1"><label for="yjqymq0311_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0311" id="yjqymq0311_f" value="0"><label
								for="yjqymq0311_f">否</label>
						</div>
					</td>
				</tr>

				<tr>
					<td class="text-c" rowspan="4">排水器或水封的设置要求</td>
					<td class="text-c">排水器的有效高度是否符合《工业企业煤气安全规程》（GB6222）的要求，为煤气计算压力至少加500mm。</td>
					<td id="yjqymq0401" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0401" id="yjqymq0401_s" checked="checked"
								value="1"><label for="yjqymq0401_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0401" id="yjqymq0401_f" value="0"><label
								for="yjqymq0401_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">排水器或水封的设置要求</td>
					<td class="text-c">水封装置（含排水器）是否有能够方便检查水封高度和高水位溢流的排水口。</td>
					<td id="yjqymq0402" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0402" id="yjqymq0402_s" checked="checked"
								value="1"><label for="yjqymq0402_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0402" id="yjqymq0402_f" value="0"><label
								for="yjqymq0402_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">排水器或水封的设置要求</td>
					<td class="text-c">排水器连接总管的排水管是否加上、下两道阀门。 </td>
					<td id="yjqymq0403" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0403" id="yjqymq0403_s" checked="checked"
								value="1"><label for="yjqymq0403_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0403" id="yjqymq0403_f" value="0"><label
								for="yjqymq0403_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">排水器或水封的设置要求</td>
					<td class="text-c">水封或排水器是否建立定期检查排水、排污等的管理制度和相关记录。</td>
					<td id="yjqymq0404" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0404" id="yjqymq0404_s" checked="checked"
								value="1"><label for="yjqymq0404_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0404" id="yjqymq0404_f" value="0"><label
								for="yjqymq0404_f">否</label>
						</div>
					</td>
				</tr>


				<tr>
					<td class="text-c" rowspan="9">煤气放散管和放散装置的设置要求和标准</td>
					<td class="text-c">放散管是否安装在煤气设备和管道的最高处及卧式设备的末端。</td>
					<td id="yjqymq0501" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0501" id="yjqymq0501_s" checked="checked"
								value="1"><label for="yjqymq0501_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0501" id="yjqymq0501_f" value="0"><label
								for="yjqymq0501_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">煤气放散管和放散装置的设置要求和标准</td>
					<td class="text-c">放散管管口是否高出煤气管道、设备、走台和屋顶4m，离地面不小于10m。</td>
					<td id="yjqymq0502" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0502" id="yjqymq0502_s" checked="checked"
								value="1"><label for="yjqymq0502_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0502" id="yjqymq0502_f" value="0"><label
								for="yjqymq0502_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">煤气放散管和放散装置的设置要求和标准</td>
					<td class="text-c">放散管管口是否采取防雨、防堵塞措施。 </td>
					<td id="yjqymq0503" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0503" id="yjqymq0503_s" checked="checked"
								value="1"><label for="yjqymq0503_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0503" id="yjqymq0503_f" value="0"><label
								for="yjqymq0503_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">煤气放散管和放散装置的设置要求和标准</td>
					<td class="text-c">放散管根部是否焊接加强筋，上部用挣绳固定。</td>
					<td id="yjqymq0504" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0504" id="yjqymq0504_s" checked="checked"
								value="1"><label for="yjqymq0504_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0504" id="yjqymq0504_f" value="0"><label
								for="yjqymq0504_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">煤气放散管和放散装置的设置要求和标准</td>
					<td class="text-c">放散管的闸阀前是否设取样管。</td>
					<td id="yjqymq0505" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0505" id="yjqymq0505_s" checked="checked"
								value="1"><label for="yjqymq0505_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0505" id="yjqymq0505_f" value="0"><label
								for="yjqymq0505_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">煤气放散管和放散装置的设置要求和标准</td>
					<td class="text-c">剩余煤气放散塔是否高出周围建构筑物。</td>
					<td id="yjqymq0506" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0506" id="yjqymq0506_s" checked="checked"
								value="1"><label for="yjqymq0506_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0506" id="yjqymq0506_f" value="0"><label
								for="yjqymq0506_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">煤气放散管和放散装置的设置要求和标准</td>
					<td class="text-c">剩余煤气放散塔的管口距离地面是否不小于30m。</td>
					<td id="yjqymq0507" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0507" id="yjqymq0507_s" checked="checked"
								value="1"><label for="yjqymq0507_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0507" id="yjqymq0507_f" value="0"><label
								for="yjqymq0507_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">煤气放散管和放散装置的设置要求和标准</td>
					<td class="text-c">剩余煤气放散是否点燃放散。</td>
					<td id="yjqymq0508" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0508" id="yjqymq0508_s" checked="checked"
								value="1"><label for="yjqymq0508_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0508" id="yjqymq0508_f" value="0"><label
								for="yjqymq0508_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">煤气放散管和放散装置的设置要求和标准</td>
					<td class="text-c">剩余煤气放散塔放散时是否有火焰监测装置和蒸汽或氮气灭火设施。</td>
					<td id="yjqymq0509" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0509" id="yjqymq0509_s" checked="checked"
								value="1"><label for="yjqymq0509_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0509" id="yjqymq0509_f" value="0"><label
								for="yjqymq0509_f">否</label>
						</div>
					</td>
				</tr>

				<tr>
					<td class="text-c" rowspan="4">泄爆装置或泄爆阀设置要求</td>
					<td class="text-c">是否设置在煤气发生炉空气总管末端。</td>
					<td id="yjqymq0601" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0601" id="yjqymq0601_s" checked="checked"
								value="1"><label for="yjqymq0601_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0601" id="yjqymq0601_f" value="0"><label
								for="yjqymq0601_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">泄爆装置或泄爆阀设置要求</td>
					<td class="text-c">电捕焦油器在鼓风机后，是否设泄爆装置。</td>
					<td id="yjqymq0602" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0602" id="yjqymq0602_s" checked="checked"
								value="1"><label for="yjqymq0602_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0602" id="yjqymq0602_f" value="0"><label
								for="yjqymq0602_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">泄爆装置或泄爆阀设置要求</td>
					<td class="text-c">电除尘器是否设有放散管及泄爆装置。 </td>
					<td id="yjqymq0603" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0603" id="yjqymq0603_s" checked="checked"
								value="1"><label for="yjqymq0603_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0603" id="yjqymq0603_f" value="0"><label
								for="yjqymq0603_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">泄爆装置或泄爆阀设置要求</td>
					<td class="text-c">布袋除尘器箱体是否采用泄爆装置 。</td>
					<td id="yjqymq0604" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0604" id="yjqymq0604_s" checked="checked"
								value="1"><label for="yjqymq0604_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0604" id="yjqymq0604_f" value="0"><label
								for="yjqymq0604_f">否</label>
						</div>
					</td>
				</tr>

				<tr>
					<td class="text-c" rowspan="6">人孔及安全检查管的设置要求及标准</td>
					<td class="text-c">闸阀后，较低的管段上，膨胀器或蝶阀组附近、设备的顶部和底部，煤气设备和管道需经常入内检查的地方，是否设人孔。</td>
					<td id="yjqymq0701" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0701" id="yjqymq0701_s" checked="checked"
								value="1"><label for="yjqymq0701_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0701" id="yjqymq0701_f" value="0"><label
								for="yjqymq0701_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="6">人孔及安全检查管的设置要求及标准</td>
					<td class="text-c">煤气设备或单独的管段上可根据需要设置人孔，人孔是否不少于两个。</td>
					<td id="yjqymq0702" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0702" id="yjqymq0702_s" checked="checked"
								value="1"><label for="yjqymq0702_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0702" id="yjqymq0702_f" value="0"><label
								for="yjqymq0702_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="6">人孔及安全检查管的设置要求及标准</td>
					<td class="text-c">人孔直径是否不小于600mm。 </td>
					<td id="yjqymq0703" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0703" id="yjqymq0703_s" checked="checked"
								value="1"><label for="yjqymq0703_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0703" id="yjqymq0703_f" value="0"><label
								for="yjqymq0703_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="6">人孔及安全检查管的设置要求及标准</td>
					<td class="text-c">直径小于600mm的煤气管道设手孔时，其直径与管道直径是否相同。</td>
					<td id="yjqymq0704" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0704" id="yjqymq0704_s" checked="checked"
								value="1"><label for="yjqymq0704_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0704" id="yjqymq0704_f" value="0"><label
								for="yjqymq0704_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="6">人孔及安全检查管的设置要求及标准</td>
					<td class="text-c">在容易积存沉淀物的管段上部，是否安设检查管。</td>
					<td id="yjqymq0705" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0705" id="yjqymq0705_s" checked="checked"
								value="1"><label for="yjqymq0705_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0705" id="yjqymq0705_f" value="0"><label
								for="yjqymq0705_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="6">人孔及安全检查管的设置要求及标准</td>
					<td class="text-c">人孔盖上是否根据需要安设吹刷管头。</td>
					<td id="yjqymq0706" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0706" id="yjqymq0706_s" checked="checked"
								value="1"><label for="yjqymq0706_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0706" id="yjqymq0706_f" value="0"><label
								for="yjqymq0706_f">否</label>
						</div>
					</td>
				</tr>


				<tr>
					<td class="text-c" rowspan="15">架空管道安装敷设要求</td>
					<td class="text-c">发生炉煤气、水煤气、半水煤气、高炉煤气和转炉煤气等管道是否没有埋地敷设。</td>
					<td id="yjqymq0801" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0801" id="yjqymq0801_s" checked="checked"
								value="1"><label for="yjqymq0801_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0801" id="yjqymq0801_f" value="0"><label
								for="yjqymq0801_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="15">架空管道安装敷设要求</td>
					<td class="text-c">煤气是否没有穿过不使用煤气的建筑物、办公室、进风道、配电室、变电所、碎煤室以及通风不良的地点等。</td>
					<td id="yjqymq0802" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0802" id="yjqymq0802_s" checked="checked"
								value="1"><label for="yjqymq0802_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0802" id="yjqymq0802_f" value="0"><label
								for="yjqymq0802_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="15">架空管道安装敷设要求</td>
					<td class="text-c">煤气管道在每个路段和站所内是否标明介质、流向及工艺参数。 </td>
					<td id="yjqymq0803" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0803" id="yjqymq0803_s" checked="checked"
								value="1"><label for="yjqymq0803_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0803" id="yjqymq0803_f" value="0"><label
								for="yjqymq0803_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="15">架空管道安装敷设要求</td>
					<td class="text-c">横跨主要道路的煤气管道，是否标识标高标识、设置防撞护栏。</td>
					<td id="yjqymq0804" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0804" id="yjqymq0804_s" checked="checked"
								value="1"><label for="yjqymq0804_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0804" id="yjqymq0804_f" value="0"><label
								for="yjqymq0804_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="15">架空管道安装敷设要求</td>
					<td class="text-c">架空煤气管道的垂直焊缝距支座边端是否不小于300mm，水平焊缝必须位于支座的上方，管道支架是否接地。</td>
					<td id="yjqymq0805" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0805" id="yjqymq0805_s" checked="checked"
								value="1"><label for="yjqymq0805_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0805" id="yjqymq0805_f" value="0"><label
								for="yjqymq0805_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="15">架空管道安装敷设要求</td>
					<td class="text-c">煤气管道是否敷设在非燃烧体的支柱或栈桥上。</td>
					<td id="yjqymq0806" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0806" id="yjqymq0806_s" checked="checked"
								value="1"><label for="yjqymq0806_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0806" id="yjqymq0806_f" value="0"><label
								for="yjqymq0806_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="15">架空管道安装敷设要求</td>
					<td class="text-c">煤气管道与水管、热力管、燃油管和不燃气体管在同一支柱或栈桥上敷设时，其上下敷设的垂直净距是否不小于250 mm。</td>
					<td id="yjqymq0807" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0807" id="yjqymq0807_s" checked="checked"
								value="1"><label for="yjqymq0807_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0807" id="yjqymq0807_f" value="0"><label
								for="yjqymq0807_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="15">架空管道安装敷设要求</td>
					<td class="text-c">与输送腐蚀性介质的管道共架敷设时，煤气管道应架设在上方，对于容易漏气、漏油、漏腐蚀性液体的部位如法兰、阀门等，是否在煤气管道上采取保护措施。</td>
					<td id="yjqymq0808" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0808" id="yjqymq0808_s" checked="checked"
								value="1"><label for="yjqymq0808_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0808" id="yjqymq0808_f" value="0"><label
								for="yjqymq0808_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="15">架空管道安装敷设要求</td>
					<td class="text-c">大型企业煤气输送主管管底距地面净距是否不低于6m。</td>
					<td id="yjqymq0809" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0809" id="yjqymq0809_s" checked="checked"
								value="1"><label for="yjqymq0809_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0809" id="yjqymq0809_f" value="0"><label
								for="yjqymq0809_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="15">架空管道安装敷设要求</td>
					<td class="text-c">大型企业煤气分配主管是否不低于4.5m。</td>
					<td id="yjqymq0810" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0810" id="yjqymq0810_s" checked="checked"
								value="1"><label for="yjqymq0810_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0810" id="yjqymq0810_f" value="0"><label
								for="yjqymq0810_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="15">架空管道安装敷设要求</td>
					<td class="text-c">厂区架空煤气管道与架空电力线路交叉时，煤气管道如敷设在电力线路下面，是否在煤气管道上设置防护网及阻止通行的横向栏杆，交叉处的煤气管道应可靠接地。</td>
					<td id="yjqymq0811" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0811" id="yjqymq0811_s" checked="checked"
								value="1"><label for="yjqymq0811_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0811" id="yjqymq0811_f" value="0"><label
								for="yjqymq0811_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="15">架空管道安装敷设要求</td>
					<td class="text-c">通过厂内铁路调车场的煤气管道是否没有管道附属装置。</td>
					<td id="yjqymq0812" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0812" id="yjqymq0812_s" checked="checked"
								value="1"><label for="yjqymq0812_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0812" id="yjqymq0812_f" value="0"><label
								for="yjqymq0812_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="15">架空管道安装敷设要求</td>
					<td class="text-c">煤气与氧气平行净距是否不小于0.5米（焊接结构且无阀门时不小于0.25米），交叉净距是否不小于0.25米。</td>
					<td id="yjqymq0813" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0813" id="yjqymq0813_s" checked="checked"
								value="1"><label for="yjqymq0813_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0813" id="yjqymq0813_f" value="0"><label
								for="yjqymq0813_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="15">架空管道安装敷设要求</td>
					<td class="text-c">新建管道计算压力等于或大于105Pa是否进行强度试验，合格后再进行气密性试验。</td>
					<td id="yjqymq0814" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0814" id="yjqymq0814_s" checked="checked"
								value="1"><label for="yjqymq0814_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0814" id="yjqymq0814_f" value="0"><label
								for="yjqymq0814_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="15">架空管道安装敷设要求</td>
					<td class="text-c">新建管道计算压力小于105Pa时，是否进行气密性试验，试验泄漏率不得大于1%。</td>
					<td id="yjqymq0815" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0815" id="yjqymq0815_s" checked="checked"
								value="1"><label for="yjqymq0815_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0815" id="yjqymq0815_f" value="0"><label
								for="yjqymq0815_f">否</label>
						</div>
					</td>
				</tr>



				<tr>
					<td class="text-c" rowspan="9">电捕焦油器的安全要求（高炉、发生炉煤气）</td>
					<td class="text-c">电捕焦油器入口和洗涤塔后是否设可靠隔断装置。</td>
					<td id="yjqymq0901" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0901" id="yjqymq0901_s" checked="checked"
								value="1"><label for="yjqymq0901_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0901" id="yjqymq0901_f" value="0"><label
								for="yjqymq0901_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">电捕焦油器的安全要求（高炉、发生炉煤气）</td>
					<td class="text-c">电捕焦油器是否设泄爆装置，并定期检查，有记录。</td>
					<td id="yjqymq0902" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0902" id="yjqymq0902_s" checked="checked"
								value="1"><label for="yjqymq0902_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0902" id="yjqymq0902_f" value="0"><label
								for="yjqymq0902_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">电捕焦油器的安全要求（高炉、发生炉煤气）</td>
					<td class="text-c">电捕焦油器是否设自动的连续式氧含量分析仪。 </td>
					<td id="yjqymq0903" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0903" id="yjqymq0903_s" checked="checked"
								value="1"><label for="yjqymq0903_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0903" id="yjqymq0903_f" value="0"><label
								for="yjqymq0903_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">电捕焦油器的安全要求（高炉、发生炉煤气）</td>
					<td class="text-c">电捕焦油器含氧量大于1%或煤气压力是低于50Pa时，是否设置有及时切断电源装置。</td>
					<td id="yjqymq0904" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0904" id="yjqymq0904_s" checked="checked"
								value="1"><label for="yjqymq0904_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0904" id="yjqymq0904_f" value="0"><label
								for="yjqymq0904_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">电捕焦油器的安全要求（高炉、发生炉煤气）</td>
					<td class="text-c">绝缘保温箱温度应是否不低于煤气进口温度加25度。</td>
					<td id="yjqymq0905" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0905" id="yjqymq0905_s" checked="checked"
								value="1"><label for="yjqymq0905_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0905" id="yjqymq0905_f" value="0"><label
								for="yjqymq0905_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">电捕焦油器的安全要求（高炉、发生炉煤气）</td>
					<td class="text-c">电捕焦油器是否设放散管、蒸汽管。</td>
					<td id="yjqymq0906" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0906" id="yjqymq0906_s" checked="checked"
								value="1"><label for="yjqymq0906_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0906" id="yjqymq0906_f" value="0"><label
								for="yjqymq0906_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">电捕焦油器的安全要求（高炉、发生炉煤气）</td>
					<td class="text-c">电捕焦油器底部是否设保温或加热装置。</td>
					<td id="yjqymq0907" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0907" id="yjqymq0907_s" checked="checked"
								value="1"><label for="yjqymq0907_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0907" id="yjqymq0907_f" value="0"><label
								for="yjqymq0907_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">电捕焦油器的安全要求（高炉、发生炉煤气）</td>
					<td class="text-c">电捕焦油器沉淀管间是否设带阀门的连接管。</td>
					<td id="yjqymq0908" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0908" id="yjqymq0908_s" checked="checked"
								value="1"><label for="yjqymq0908_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0908" id="yjqymq0908_f" value="0"><label
								for="yjqymq0908_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">电捕焦油器的安全要求（高炉、发生炉煤气）</td>
					<td class="text-c">抽气机出口与电捕焦油器之间是否设避震器。</td>
					<td id="yjqymq0909" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq0909" id="yjqymq0909_s" checked="checked"
								value="1"><label for="yjqymq0909_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq0909" id="yjqymq0909_f" value="0"><label
								for="yjqymq0909_f">否</label>
						</div>
					</td>
				</tr>


				<tr>
					<td class="text-c" rowspan="4">电除尘器的安全要求（高炉、转炉煤气）</td>
					<td class="text-c">电除尘器的出、入口处是否设可靠的隔断装置。</td>
					<td id="yjqymq1001" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1001" id="yjqymq1001_s" checked="checked"
								value="1"><label for="yjqymq1001_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1001" id="yjqymq1001_f" value="0"><label
								for="yjqymq1001_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">电除尘器的安全要求（高炉、转炉煤气）</td>
					<td class="text-c">电除尘器是否设有煤气低压报警装置，不得低于51mmH2O。</td>
					<td id="yjqymq1002" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1002" id="yjqymq1002_s" checked="checked"
								value="1"><label for="yjqymq1002_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1002" id="yjqymq1002_f" value="0"><label
								for="yjqymq1002_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">电除尘器的安全要求（高炉、转炉煤气）</td>
					<td class="text-c">电除尘器中水煤气的含氧量是否设报警连锁（含氧量达到1％时，能自动切断电源的装置）。 </td>
					<td id="yjqymq1003" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1003" id="yjqymq1003_s" checked="checked"
								value="1"><label for="yjqymq1003_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1003" id="yjqymq1003_f" value="0"><label
								for="yjqymq1003_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">电除尘器的安全要求（高炉、转炉煤气）</td>
					<td class="text-c">电除尘是否设放散管、蒸汽管和泄爆装置。</td>
					<td id="yjqymq1004" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1004" id="yjqymq1004_s" checked="checked"
								value="1"><label for="yjqymq1004_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1004" id="yjqymq1004_f" value="0"><label
								for="yjqymq1004_f">否</label>
						</div>
					</td>
				</tr>

				<tr>
					<td class="text-c" rowspan="5">布袋除尘器安全要求（高炉煤气）</td>
					<td class="text-c">布袋除尘器的出入口是否设有可靠的隔断装置。</td>
					<td id="yjqymq1101" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1101" id="yjqymq1101_s" checked="checked"
								value="1"><label for="yjqymq1101_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1101" id="yjqymq1101_f" value="0"><label
								for="yjqymq1101_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="5">布袋除尘器安全要求（高炉煤气）</td>
					<td class="text-c">布袋除尘器的每个箱体是否设放散管。</td>
					<td id="yjqymq1102" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1102" id="yjqymq1102_s" checked="checked"
								value="1"><label for="yjqymq1102_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1102" id="yjqymq1102_f" value="0"><label
								for="yjqymq1102_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="5">布袋除尘器安全要求（高炉煤气）</td>
					<td class="text-c">布袋除尘器是否设煤气高、低温报警和低压报警装置。 </td>
					<td id="yjqymq1103" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1103" id="yjqymq1103_s" checked="checked"
								value="1"><label for="yjqymq1103_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1103" id="yjqymq1103_f" value="0"><label
								for="yjqymq1103_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="5">布袋除尘器安全要求（高炉煤气）</td>
					<td class="text-c">布袋除尘器箱体是否采用泄爆装置。</td>
					<td id="yjqymq1104" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1104" id="yjqymq1104_s" checked="checked"
								value="1"><label for="yjqymq1104_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1104" id="yjqymq1104_f" value="0"><label
								for="yjqymq1104_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="5">布袋除尘器安全要求（高炉煤气）</td>
					<td class="text-c">布袋除尘器向外界卸灰时，是否有防止煤气外泄的措施。</td>
					<td id="yjqymq1105" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1105" id="yjqymq1105_s" checked="checked"
								value="1"><label for="yjqymq1105_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1105" id="yjqymq1105_f" value="0"><label
								for="yjqymq1105_f">否</label>
						</div>
					</td>
				</tr>



				<tr>
					<td class="text-c" rowspan="12">煤气加压站区域安全要求</td>
					<td class="text-c">管理室是否装设二次检测仪表及调节装置。</td>
					<td id="yjqymq1201" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1201" id="yjqymq1201_s" checked="checked"
								value="1"><label for="yjqymq1201_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1201" id="yjqymq1201_f" value="0"><label
								for="yjqymq1201_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="12">煤气加压站区域安全要求</td>
					<td class="text-c">管理室是否装有电话。</td>
					<td id="yjqymq1202" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1202" id="yjqymq1202_s" checked="checked"
								value="1"><label for="yjqymq1202_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1202" id="yjqymq1202_f" value="0"><label
								for="yjqymq1202_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="12">煤气加压站区域安全要求</td>
					<td class="text-c">一次仪表室是否设强制通风装置。 </td>
					<td id="yjqymq1203" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1203" id="yjqymq1203_s" checked="checked"
								value="1"><label for="yjqymq1203_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1203" id="yjqymq1203_f" value="0"><label
								for="yjqymq1203_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="12">煤气加压站区域安全要求</td>
					<td class="text-c">加压站内是否设有CO报警装置。</td>
					<td id="yjqymq1204" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1204" id="yjqymq1204_s" checked="checked"
								value="1"><label for="yjqymq1204_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1204" id="yjqymq1204_f" value="0"><label
								for="yjqymq1204_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="12">煤气加压站区域安全要求</td>
					<td class="text-c">加压站所是否两路供电。</td>
					<td id="yjqymq1205" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1205" id="yjqymq1205_s" checked="checked"
								value="1"><label for="yjqymq1205_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1205" id="yjqymq1205_f" value="0"><label
								for="yjqymq1205_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="12">煤气加压站区域安全要求</td>
					<td class="text-c">有人值班的机械房、加压站、混合站、抽气机房内的值班人员是否不少于二人。</td>
					<td id="yjqymq1206" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1206" id="yjqymq1206_s" checked="checked"
								value="1"><label for="yjqymq1206_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1206" id="yjqymq1206_f" value="0"><label
								for="yjqymq1206_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="12">煤气加压站区域安全要求</td>
					<td class="text-c">加压站房内主机之间以及主机与墙壁之间的净距是否不小于1.3m。</td>
					<td id="yjqymq1207" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1207" id="yjqymq1207_s" checked="checked"
								value="1"><label for="yjqymq1207_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1207" id="yjqymq1207_f" value="0"><label
								for="yjqymq1207_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="12">煤气加压站区域安全要求</td>
					<td class="text-c">两条引入混合的煤气管道的净距是否不小于800 mm。</td>
					<td id="yjqymq1208" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1208" id="yjqymq1208_s" checked="checked"
								value="1"><label for="yjqymq1208_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1208" id="yjqymq1208_f" value="0"><label
								for="yjqymq1208_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="12">煤气加压站区域安全要求</td>
					<td class="text-c">两条引入混合的煤气管道的敷设坡度是否不小于0.5％。</td>
					<td id="yjqymq1209" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1209" id="yjqymq1209_s" checked="checked"
								value="1"><label for="yjqymq1209_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1209" id="yjqymq1209_f" value="0"><label
								for="yjqymq1209_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="12">煤气加压站区域安全要求</td>
					<td class="text-c">引入混合站的两条混合管道，在引入的起始端是否设可靠的隔断装置。</td>
					<td id="yjqymq1210" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1210" id="yjqymq1210_s" checked="checked"
								value="1"><label for="yjqymq1210_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1210" id="yjqymq1210_f" value="0"><label
								for="yjqymq1210_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="12">煤气加压站区域安全要求</td>
					<td class="text-c">每台加压机、抽气机前后是否设可靠的隔断装置。</td>
					<td id="yjqymq1211" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1211" id="yjqymq1211_s" checked="checked"
								value="1"><label for="yjqymq1211_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1211" id="yjqymq1211_f" value="0"><label
								for="yjqymq1211_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="12">煤气加压站区域安全要求</td>
					<td class="text-c">每台设备、阀门、放散管和管道支架应编号管理。</td>
					<td id="yjqymq1212" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1212" id="yjqymq1212_s" checked="checked"
								value="1"><label for="yjqymq1212_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1212" id="yjqymq1212_f" value="0"><label
								for="yjqymq1212_f">否</label>
						</div>
					</td>
				</tr>


				<tr>
					<td class="text-c" rowspan="13">煤气柜区域的安全要求</td>
					<td class="text-c">气柜值班室应设有煤气压力、流量、气柜高度、气柜倾斜度和柜内CO检测仪表等监控仪表。</td>
					<td id="yjqymq1301" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1301" id="yjqymq1301_s" checked="checked"
								value="1"><label for="yjqymq1301_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1301" id="yjqymq1301_f" value="0"><label
								for="yjqymq1301_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="13">煤气柜区域的安全要求</td>
					<td class="text-c">稀油密封柜要有油沟、油位自动检测仪表，并灵敏可靠，有运行记录。</td>
					<td id="yjqymq1302" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1302" id="yjqymq1302_s" checked="checked"
								value="1"><label for="yjqymq1302_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1302" id="yjqymq1302_f" value="0"><label
								for="yjqymq1302_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="13">煤气柜区域的安全要求</td>
					<td class="text-c">柜区内应设专用消防栓，水压符合要求，并随时能投入使用，配备的消防器材齐全有效。 </td>
					<td id="yjqymq1303" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1303" id="yjqymq1303_s" checked="checked"
								value="1"><label for="yjqymq1303_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1303" id="yjqymq1303_f" value="0"><label
								for="yjqymq1303_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="13">煤气柜区域的安全要求</td>
					<td class="text-c">煤气柜出入口管道上是否设隔断装置。</td>
					<td id="yjqymq1304" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1304" id="yjqymq1304_s" checked="checked"
								value="1"><label for="yjqymq1304_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1304" id="yjqymq1304_f" value="0"><label
								for="yjqymq1304_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="13">煤气柜区域的安全要求</td>
					<td class="text-c">煤气柜出人口管道最低处是否设排水器。</td>
					<td id="yjqymq1305" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1305" id="yjqymq1305_s" checked="checked"
								value="1"><label for="yjqymq1305_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1305" id="yjqymq1305_f" value="0"><label
								for="yjqymq1305_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="13">煤气柜区域的安全要求</td>
					<td class="text-c">煤气柜上是否有容积指示装置。</td>
					<td id="yjqymq1306" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1306" id="yjqymq1306_s" checked="checked"
								value="1"><label for="yjqymq1306_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1306" id="yjqymq1306_f" value="0"><label
								for="yjqymq1306_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="13">煤气柜区域的安全要求</td>
					<td class="text-c">煤气柜是否设有自动控制装备（柜位达到上限时关闭煤气入口阀，并设有放散设施；煤气柜位降到下限时，自动停止向外输出煤气或自动充压的装置）。</td>
					<td id="yjqymq1307" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1307" id="yjqymq1307_s" checked="checked"
								value="1"><label for="yjqymq1307_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1307" id="yjqymq1307_f" value="0"><label
								for="yjqymq1307_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="13">煤气柜区域的安全要求</td>
					<td class="text-c">干式煤气柜是否设内、外部电梯，供检修及检查时载人用。</td>
					<td id="yjqymq1308" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1308" id="yjqymq1308_s" checked="checked"
								value="1"><label for="yjqymq1308_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1308" id="yjqymq1308_f" value="0"><label
								for="yjqymq1308_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="13">煤气柜区域的安全要求</td>
					<td class="text-c">电梯应设最终位置极限开关、升降异常灯。</td>
					<td id="yjqymq1309" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1309" id="yjqymq1309_s" checked="checked"
								value="1"><label for="yjqymq1309_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1309" id="yjqymq1309_f" value="0"><label
								for="yjqymq1309_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="13">煤气柜区域的安全要求</td>
					<td class="text-c">电梯内部是否设安全开关、安全扣和联络电话。</td>
					<td id="yjqymq1310" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1310" id="yjqymq1310_s" checked="checked"
								value="1"><label for="yjqymq1310_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1310" id="yjqymq1310_f" value="0"><label
								for="yjqymq1310_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="13">煤气柜区域的安全要求</td>
					<td class="text-c">电梯活塞上部是否有一氧化碳检测报警装置。</td>
					<td id="yjqymq1311" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1311" id="yjqymq1311_s" checked="checked"
								value="1"><label for="yjqymq1311_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1311" id="yjqymq1311_f" value="0"><label
								for="yjqymq1311_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="13">煤气柜区域的安全要求</td>
					<td class="text-c">人员进行电梯活塞上部是否备正压式空气呼吸器。</td>
					<td id="yjqymq1312" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1312" id="yjqymq1312_s" checked="checked"
								value="1"><label for="yjqymq1312_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1312" id="yjqymq1312_f" value="0"><label
								for="yjqymq1312_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="13">煤气柜区域的安全要求</td>
					<td class="text-c">干式柜除生产照明外是否设有事故照明、检修照明、楼梯及过道照明、各种检测仪表照明以及外部升降机上、下出入口照明。</td>
					<td id="yjqymq1313" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1313" id="yjqymq1313_s" checked="checked"
								value="1"><label for="yjqymq1313_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1313" id="yjqymq1313_f" value="0"><label
								for="yjqymq1313_f">否</label>
						</div>
					</td>
				</tr>

				<tr>
					<td class="text-c" rowspan="4">煤气调度室、休息室的管理</td>
					<td class="text-c">调度室、休息室是否为无爆炸危险房屋，与有爆炸危险的房屋分开。</td>
					<td id="yjqymq1401" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1401" id="yjqymq1401_s" checked="checked"
								value="1"><label for="yjqymq1401_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1401" id="yjqymq1401_f" value="0"><label
								for="yjqymq1401_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">煤气调度室、休息室的管理</td>
					<td class="text-c">休息室是否避免建设在煤气等有毒气体环境介质中。</td>
					<td id="yjqymq1402" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1402" id="yjqymq1402_s" checked="checked"
								value="1"><label for="yjqymq1402_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1402" id="yjqymq1402_f" value="0"><label
								for="yjqymq1402_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">煤气调度室、休息室的管理</td>
					<td class="text-c">其他有专人值守的场所，如果必须建在煤气区域，是否设有不少于2个对开的安全出口，且装设固定式煤气声光报警器。 </td>
					<td id="yjqymq1403" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1403" id="yjqymq1403_s" checked="checked"
								value="1"><label for="yjqymq1403_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1403" id="yjqymq1403_f" value="0"><label
								for="yjqymq1403_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="4">煤气调度室、休息室的管理</td>
					<td class="text-c">煤气管道下方是否没有设置操作室、休息室等有人设施。</td>
					<td id="yjqymq1404" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1404" id="yjqymq1404_s" checked="checked"
								value="1"><label for="yjqymq1404_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1404" id="yjqymq1404_f" value="0"><label
								for="yjqymq1404_f">否</label>
						</div>
					</td>
				</tr>

				<tr>
					<td class="text-c" rowspan="3">煤气区域防雷、防静电管理</td>
					<td class="text-c">煤气区域内的建构筑物，是否按《建筑物防雷设计规范》（GB50057）的规定设置避雷针、避雷网、避雷器等防雷设施。</td>
					<td id="yjqymq1501" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1501" id="yjqymq1501_s" checked="checked"
								value="1"><label for="yjqymq1501_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1501" id="yjqymq1501_f" value="0"><label
								for="yjqymq1501_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="3">煤气区域防雷、防静电管理</td>
					<td class="text-c">煤气管道及相关设备设施是否采取消除静电的措施。</td>
					<td id="yjqymq1502" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1502" id="yjqymq1502_s" checked="checked"
								value="1"><label for="yjqymq1502_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1502" id="yjqymq1502_f" value="0"><label
								for="yjqymq1502_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="3">煤气区域防雷、防静电管理</td>
					<td class="text-c">煤气管道及相关设备设施是否每半年检测一次，确保防雷设施完好，检测报告是否可查。 </td>
					<td id="yjqymq1503" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1503" id="yjqymq1503_s" checked="checked"
								value="1"><label for="yjqymq1503_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1503" id="yjqymq1503_f" value="0"><label
								for="yjqymq1503_f">否</label>
						</div>
					</td>
				</tr>

				<tr>
					<td class="text-c" rowspan="3">煤气区域防爆管理</td>
					<td class="text-c">在爆炸性气体环境、爆炸性粉尘环境、火灾危险环境中危险区域划分、危险区域范围确定、电气设备选型是否符合《爆炸和火灾危险环境电力装置设计规范》（GB50058）的要求。</td>
					<td id="yjqymq1601" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1601" id="yjqymq1601_s" checked="checked"
								value="1"><label for="yjqymq1601_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1601" id="yjqymq1601_f" value="0"><label
								for="yjqymq1601_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="3">煤气区域防爆管理</td>
					<td class="text-c">厂房的照明，是否符合《建筑采光设计标准》（GB50033）和《建筑照明设计标准》（GB50034）的规定。</td>
					<td id="yjqymq1602" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1602" id="yjqymq1602_s" checked="checked"
								value="1"><label for="yjqymq1602_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1602" id="yjqymq1602_f" value="0"><label
								for="yjqymq1602_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="3">煤气区域防爆管理</td>
					<td class="text-c">有爆炸和火灾危险的场所，是否按其危险等级选用相应的照明器材。 </td>
					<td id="yjqymq1603" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1603" id="yjqymq1603_s" checked="checked"
								value="1"><label for="yjqymq1603_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1603" id="yjqymq1603_f" value="0"><label
								for="yjqymq1603_f">否</label>
						</div>
					</td>
				</tr>

				<tr>
					<td class="text-c" rowspan="3">煤气作业环境CO浓度好和氧气含量管理</td>
					<td class="text-c">是否按照CO含量与工作时间的要求进行作业（煤气区域一氧化碳含量不超过24ppm时，可连续工作；一氧化碳含量不超过40ppm时连续工作时间不应超过1小时；一氧化碳含量不超过80ppm时，连续工作时间不应超过30分钟；一氧化碳含量不超过160ppm时，连续工作时间8应超过15分钟）。</td>
					<td id="yjqymq1701" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1701" id="yjqymq1701_s" checked="checked"
								value="1"><label for="yjqymq1701_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1701" id="yjqymq1701_f" value="0"><label
								for="yjqymq1701_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="3">煤气作业环境CO浓度好和氧气含量管理</td>
					<td class="text-c">在煤气设施内作业，除检测的CO含量外，是否检测氧含量不小于19.5%。</td>
					<td id="yjqymq1702" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1702" id="yjqymq1702_s" checked="checked"
								value="1"><label for="yjqymq1702_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1702" id="yjqymq1702_f" value="0"><label
								for="yjqymq1702_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="3">煤气作业环境CO浓度好和氧气含量管理</td>
					<td class="text-c">当现场检测数据不满足规定要求时，作业人员是否使用呼吸器，是否有专人监护。 </td>
					<td id="yjqymq1703" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1703" id="yjqymq1703_s" checked="checked"
								value="1"><label for="yjqymq1703_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1703" id="yjqymq1703_f" value="0"><label
								for="yjqymq1703_f">否</label>
						</div>
					</td>
				</tr>


				<tr>
					<td class="text-c" rowspan="18">煤气单元配置管理</td>
					<td class="text-c">调度室、煤气操作室、值班室是否设有各类煤气主管压力，流量和必要的安全报警装置。</td>
					<td id="yjqymq1801" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1801" id="yjqymq1801_s" checked="checked"
								value="1"><label for="yjqymq1801_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1801" id="yjqymq1801_f" value="0"><label
								for="yjqymq1801_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="18">煤气单元配置管理</td>
					<td class="text-c">调度室、煤气操作室、值班室是否配备与生产煤气厂（车间）、煤气防护站和主要用户的直通电话。</td>
					<td id="yjqymq1802" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1802" id="yjqymq1802_s" checked="checked"
								value="1"><label for="yjqymq1802_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1802" id="yjqymq1802_f" value="0"><label
								for="yjqymq1802_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="18">煤气单元配置管理</td>
					<td class="text-c">凡开、闭时冒出煤气的隔断装置盲板、眼睛阀或扇型阀及敞开式插板阀等，是否未安装在厂房内或通风不良之处，离明火设备距离是否不少于40米。 </td>
					<td id="yjqymq1803" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1803" id="yjqymq1803_s" checked="checked"
								value="1"><label for="yjqymq1803_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1803" id="yjqymq1803_f" value="0"><label
								for="yjqymq1803_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="18">煤气单元配置管理</td>
					<td class="text-c">是否有置换、吹扫、加压需要的设备和管道应设置可拆卸式的蒸气或氮气接头。</td>
					<td id="yjqymq1804" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1804" id="yjqymq1804_s" checked="checked"
								value="1"><label for="yjqymq1804_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1804" id="yjqymq1804_f" value="0"><label
								for="yjqymq1804_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="18">煤气单元配置管理</td>
					<td class="text-c">煤气吹扫装置使用后是否将接头与煤气设施断开，或堵盲板。</td>
					<td id="yjqymq1805" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1805" id="yjqymq1805_s" checked="checked"
								value="1"><label for="yjqymq1805_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1805" id="yjqymq1805_f" value="0"><label
								for="yjqymq1805_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="18">煤气单元配置管理</td>
					<td class="text-c">设备裸露的转动或快速移动部分，是否设有结构可靠的安全防护罩、防护栏杆或防护挡板。</td>
					<td id="yjqymq1806" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1806" id="yjqymq1806_s" checked="checked"
								value="1"><label for="yjqymq1806_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1806" id="yjqymq1806_f" value="0"><label
								for="yjqymq1806_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="18">煤气单元配置管理</td>
					<td class="text-c">在易发生爆炸的煤气设备部位应安装泄爆装置，其应保持严密，泄爆口不应正对建筑物的门窗。</td>
					<td id="yjqymq1807" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1807" id="yjqymq1807_s" checked="checked"
								value="1"><label for="yjqymq1807_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1807" id="yjqymq1807_f" value="0"><label
								for="yjqymq1807_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="18">煤气单元配置管理</td>
					<td class="text-c">站所和气柜在醒目位置处是否设风险告知牌、危险化学品告知牌和职业健康告知牌、安全警示牌、平面布置图。</td>
					<td id="yjqymq1808" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1808" id="yjqymq1808_s" checked="checked"
								value="1"><label for="yjqymq1808_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1808" id="yjqymq1808_f" value="0"><label
								for="yjqymq1808_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="18">煤气单元配置管理</td>
					<td class="text-c">气柜区域和加压机域内照明、电气、电机是否使用防爆型设施。</td>
					<td id="yjqymq1809" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1809" id="yjqymq1809_s" checked="checked"
								value="1"><label for="yjqymq1809_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1809" id="yjqymq1809_f" value="0"><label
								for="yjqymq1809_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="18">煤气单元配置管理</td>
					<td class="text-c">气柜周围消防通道是否畅通无阻，柜区内是否设置两个以上安全疏散通道。</td>
					<td id="yjqymq1810" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1810" id="yjqymq1810_s" checked="checked"
								value="1"><label for="yjqymq1810_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1810" id="yjqymq1810_f" value="0"><label
								for="yjqymq1810_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="18">煤气单元配置管理</td>
					<td class="text-c">气柜区域和加压机域内照明、电气、电机是否使用防爆型设施。</td>
					<td id="yjqymq1811" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1811" id="yjqymq1811_s" checked="checked"
								value="1"><label for="yjqymq1811_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1811" id="yjqymq1811_f" value="0"><label
								for="yjqymq1811_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="18">煤气单元配置管理</td>
					<td class="text-c">在有较大危险因素的作业场所或有关设备上，是否设置符合《安全标志及其使用导则》（GB2894）和《安全色》（GB2893）规定的安全警示标志和安全色。</td>
					<td id="yjqymq1812" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1812" id="yjqymq1812_s" checked="checked"
								value="1"><label for="yjqymq1812_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1812" id="yjqymq1812_f" value="0"><label
								for="yjqymq1812_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="18">煤气单元配置管理</td>
					<td class="text-c">高炉和焦炉厂区边缘与居民区边缘相距是否在1000m以上。</td>
					<td id="yjqymq1813" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1813" id="yjqymq1813_s" checked="checked"
								value="1"><label for="yjqymq1813_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1813" id="yjqymq1813_f" value="0"><label
								for="yjqymq1813_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="18">煤气单元配置管理</td>
					<td class="text-c">站所内是否设有消防设备。</td>
					<td id="yjqymq1814" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1814" id="yjqymq1814_s" checked="checked"
								value="1"><label for="yjqymq1814_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1814" id="yjqymq1814_f" value="0"><label
								for="yjqymq1814_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="18">煤气单元配置管理</td>
					<td class="text-c">对变更的煤气设施是否进行审批和验收管理，变更过程及变更后所产生的风险和隐患是否进行辨识、评估和控制。</td>
					<td id="yjqymq1815" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1815" id="yjqymq1815_s" checked="checked"
								value="1"><label for="yjqymq1815_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1815" id="yjqymq1815_f" value="0"><label
								for="yjqymq1815_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="18">煤气单元配置管理</td>
					<td class="text-c">煤气管道波纹膨胀器拉杆螺栓应齐全，内侧螺栓应松开运行。</td>
					<td id="yjqymq1816" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1816" id="yjqymq1816_s" checked="checked"
								value="1"><label for="yjqymq1816_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1816" id="yjqymq1816_f" value="0"><label
								for="yjqymq1816_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="18">煤气单元配置管理</td>
					<td class="text-c">煤气管道是否采取消除静电和防雷措施，有无检测报告。</td>
					<td id="yjqymq1817" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1817" id="yjqymq1817_s" checked="checked"
								value="1"><label for="yjqymq1817_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1817" id="yjqymq1817_f" value="0"><label
								for="yjqymq1817_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="18">煤气单元配置管理</td>
					<td class="text-c">是否指定专人负责保管、定期校验和维护各种防护用具，确保其处于正常状态。</td>
					<td id="yjqymq1818" class="text-c">
						<div class="radio-box">
							<input type="radio" name="yjqymq1818" id="yjqymq1818_s" checked="checked"
								value="1"><label for="yjqymq1818_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="yjqymq1818" id="yjqymq1818_f" value="0"><label
								for="yjqymq1818_f">否</label>
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
	
	$("input[name='yjqymq0101'][value='${me.yjqymq0101}']").attr("checked","checked");
	$("input[name='yjqymq0102'][value='${me.yjqymq0102}']").attr("checked","checked");
	$("input[name='yjqymq0201'][value='${me.yjqymq0201}']").attr("checked","checked");
	$("input[name='yjqymq0202'][value='${me.yjqymq0202}']").attr("checked","checked");
	$("input[name='yjqymq0203'][value='${me.yjqymq0203}']").attr("checked","checked");
	$("input[name='yjqymq0204'][value='${me.yjqymq0204}']").attr("checked","checked");
	$("input[name='yjqymq0205'][value='${me.yjqymq0205}']").attr("checked","checked");
	$("input[name='yjqymq0206'][value='${me.yjqymq0206}']").attr("checked","checked");
	$("input[name='yjqymq0207'][value='${me.yjqymq0207}']").attr("checked","checked");
	$("input[name='yjqymq0208'][value='${me.yjqymq0208}']").attr("checked","checked");
	$("input[name='yjqymq0209'][value='${me.yjqymq0209}']").attr("checked","checked");
	$("input[name='yjqymq0210'][value='${me.yjqymq0210}']").attr("checked","checked");
	$("input[name='yjqymq0211'][value='${me.yjqymq0211}']").attr("checked","checked");
	$("input[name='yjqymq0212'][value='${me.yjqymq0212}']").attr("checked","checked");
	$("input[name='yjqymq0213'][value='${me.yjqymq0213}']").attr("checked","checked");
	$("input[name='yjqymq0301'][value='${me.yjqymq0301}']").attr("checked","checked");
	$("input[name='yjqymq0302'][value='${me.yjqymq0302}']").attr("checked","checked");
	$("input[name='yjqymq0303'][value='${me.yjqymq0303}']").attr("checked","checked");
	$("input[name='yjqymq0304'][value='${me.yjqymq0304}']").attr("checked","checked");
	$("input[name='yjqymq0305'][value='${me.yjqymq0305}']").attr("checked","checked");
	$("input[name='yjqymq0306'][value='${me.yjqymq0306}']").attr("checked","checked");
	$("input[name='yjqymq0307'][value='${me.yjqymq0307}']").attr("checked","checked");
	$("input[name='yjqymq0308'][value='${me.yjqymq0308}']").attr("checked","checked");
	$("input[name='yjqymq0309'][value='${me.yjqymq0309}']").attr("checked","checked");
	$("input[name='yjqymq0310'][value='${me.yjqymq0310}']").attr("checked","checked");
	$("input[name='yjqymq0311'][value='${me.yjqymq0311}']").attr("checked","checked");
	$("input[name='yjqymq0312'][value='${me.yjqymq0312}']").attr("checked","checked");
	$("input[name='yjqymq0313'][value='${me.yjqymq0313}']").attr("checked","checked");
	$("input[name='yjqymq0401'][value='${me.yjqymq0401}']").attr("checked","checked");
	$("input[name='yjqymq0402'][value='${me.yjqymq0402}']").attr("checked","checked");
	$("input[name='yjqymq0403'][value='${me.yjqymq0403}']").attr("checked","checked");
	$("input[name='yjqymq0404'][value='${me.yjqymq0404}']").attr("checked","checked");
	$("input[name='yjqymq0501'][value='${me.yjqymq0501}']").attr("checked","checked");
	$("input[name='yjqymq0502'][value='${me.yjqymq0502}']").attr("checked","checked");
	$("input[name='yjqymq0503'][value='${me.yjqymq0503}']").attr("checked","checked");
	$("input[name='yjqymq0504'][value='${me.yjqymq0504}']").attr("checked","checked");
	$("input[name='yjqymq0505'][value='${me.yjqymq0505}']").attr("checked","checked");
	$("input[name='yjqymq0506'][value='${me.yjqymq0506}']").attr("checked","checked");
	$("input[name='yjqymq0507'][value='${me.yjqymq0507}']").attr("checked","checked");
	$("input[name='yjqymq0508'][value='${me.yjqymq0508}']").attr("checked","checked");
	$("input[name='yjqymq0509'][value='${me.yjqymq0509}']").attr("checked","checked");
	$("input[name='yjqymq0601'][value='${me.yjqymq0601}']").attr("checked","checked");
	$("input[name='yjqymq0602'][value='${me.yjqymq0602}']").attr("checked","checked");
	$("input[name='yjqymq0603'][value='${me.yjqymq0603}']").attr("checked","checked");
	$("input[name='yjqymq0604'][value='${me.yjqymq0604}']").attr("checked","checked");
	$("input[name='yjqymq0701'][value='${me.yjqymq0701}']").attr("checked","checked");
	$("input[name='yjqymq0702'][value='${me.yjqymq0702}']").attr("checked","checked");
	$("input[name='yjqymq0703'][value='${me.yjqymq0703}']").attr("checked","checked");
	$("input[name='yjqymq0704'][value='${me.yjqymq0704}']").attr("checked","checked");
	$("input[name='yjqymq0705'][value='${me.yjqymq0705}']").attr("checked","checked");
	$("input[name='yjqymq0706'][value='${me.yjqymq0706}']").attr("checked","checked");
	$("input[name='yjqymq0801'][value='${me.yjqymq0801}']").attr("checked","checked");
	$("input[name='yjqymq0802'][value='${me.yjqymq0802}']").attr("checked","checked");
	$("input[name='yjqymq0803'][value='${me.yjqymq0803}']").attr("checked","checked");
	$("input[name='yjqymq0804'][value='${me.yjqymq0804}']").attr("checked","checked");
	$("input[name='yjqymq0805'][value='${me.yjqymq0805}']").attr("checked","checked");
	$("input[name='yjqymq0806'][value='${me.yjqymq0806}']").attr("checked","checked");
	$("input[name='yjqymq0807'][value='${me.yjqymq0807}']").attr("checked","checked");
	$("input[name='yjqymq0808'][value='${me.yjqymq0808}']").attr("checked","checked");
	$("input[name='yjqymq0809'][value='${me.yjqymq0809}']").attr("checked","checked");
	$("input[name='yjqymq0810'][value='${me.yjqymq0810}']").attr("checked","checked");
	$("input[name='yjqymq0811'][value='${me.yjqymq0811}']").attr("checked","checked");
	$("input[name='yjqymq0812'][value='${me.yjqymq0812}']").attr("checked","checked");
	$("input[name='yjqymq0813'][value='${me.yjqymq0813}']").attr("checked","checked");
	$("input[name='yjqymq0814'][value='${me.yjqymq0814}']").attr("checked","checked");
	$("input[name='yjqymq0815'][value='${me.yjqymq0815}']").attr("checked","checked");
	$("input[name='yjqymq0901'][value='${me.yjqymq0901}']").attr("checked","checked");
	$("input[name='yjqymq0902'][value='${me.yjqymq0902}']").attr("checked","checked");
	$("input[name='yjqymq0903'][value='${me.yjqymq0903}']").attr("checked","checked");
	$("input[name='yjqymq0904'][value='${me.yjqymq0904}']").attr("checked","checked");
	$("input[name='yjqymq0905'][value='${me.yjqymq0905}']").attr("checked","checked");
	$("input[name='yjqymq0906'][value='${me.yjqymq0906}']").attr("checked","checked");
	$("input[name='yjqymq0907'][value='${me.yjqymq0907}']").attr("checked","checked");
	$("input[name='yjqymq0908'][value='${me.yjqymq0908}']").attr("checked","checked");
	$("input[name='yjqymq0909'][value='${me.yjqymq0909}']").attr("checked","checked");
	$("input[name='yjqymq1001'][value='${me.yjqymq1001}']").attr("checked","checked");
	$("input[name='yjqymq1002'][value='${me.yjqymq1002}']").attr("checked","checked");
	$("input[name='yjqymq1003'][value='${me.yjqymq1003}']").attr("checked","checked");
	$("input[name='yjqymq1004'][value='${me.yjqymq1004}']").attr("checked","checked");
	$("input[name='yjqymq1101'][value='${me.yjqymq1101}']").attr("checked","checked");
	$("input[name='yjqymq1102'][value='${me.yjqymq1102}']").attr("checked","checked");
	$("input[name='yjqymq1103'][value='${me.yjqymq1103}']").attr("checked","checked");
	$("input[name='yjqymq1104'][value='${me.yjqymq1104}']").attr("checked","checked");
	$("input[name='yjqymq1105'][value='${me.yjqymq1105}']").attr("checked","checked");
	$("input[name='yjqymq1201'][value='${me.yjqymq1201}']").attr("checked","checked");
	$("input[name='yjqymq1202'][value='${me.yjqymq1202}']").attr("checked","checked");
	$("input[name='yjqymq1203'][value='${me.yjqymq1203}']").attr("checked","checked");
	$("input[name='yjqymq1204'][value='${me.yjqymq1204}']").attr("checked","checked");
	$("input[name='yjqymq1205'][value='${me.yjqymq1205}']").attr("checked","checked");
	$("input[name='yjqymq1206'][value='${me.yjqymq1206}']").attr("checked","checked");
	$("input[name='yjqymq1207'][value='${me.yjqymq1207}']").attr("checked","checked");
	$("input[name='yjqymq1208'][value='${me.yjqymq1208}']").attr("checked","checked");
	$("input[name='yjqymq1209'][value='${me.yjqymq1209}']").attr("checked","checked");
	$("input[name='yjqymq1210'][value='${me.yjqymq1210}']").attr("checked","checked");
	$("input[name='yjqymq1211'][value='${me.yjqymq1211}']").attr("checked","checked");
	$("input[name='yjqymq1212'][value='${me.yjqymq1212}']").attr("checked","checked");
	$("input[name='yjqymq1301'][value='${me.yjqymq1301}']").attr("checked","checked");
	$("input[name='yjqymq1302'][value='${me.yjqymq1302}']").attr("checked","checked");
	$("input[name='yjqymq1303'][value='${me.yjqymq1303}']").attr("checked","checked");
	$("input[name='yjqymq1304'][value='${me.yjqymq1304}']").attr("checked","checked");
	$("input[name='yjqymq1305'][value='${me.yjqymq1305}']").attr("checked","checked");
	$("input[name='yjqymq1306'][value='${me.yjqymq1306}']").attr("checked","checked");
	$("input[name='yjqymq1307'][value='${me.yjqymq1307}']").attr("checked","checked");
	$("input[name='yjqymq1308'][value='${me.yjqymq1308}']").attr("checked","checked");
	$("input[name='yjqymq1309'][value='${me.yjqymq1309}']").attr("checked","checked");
	$("input[name='yjqymq1310'][value='${me.yjqymq1310}']").attr("checked","checked");
	$("input[name='yjqymq1311'][value='${me.yjqymq1311}']").attr("checked","checked");
	$("input[name='yjqymq1312'][value='${me.yjqymq1312}']").attr("checked","checked");
	$("input[name='yjqymq1313'][value='${me.yjqymq1313}']").attr("checked","checked");
	$("input[name='yjqymq1401'][value='${me.yjqymq1401}']").attr("checked","checked");
	$("input[name='yjqymq1402'][value='${me.yjqymq1402}']").attr("checked","checked");
	$("input[name='yjqymq1403'][value='${me.yjqymq1403}']").attr("checked","checked");
	$("input[name='yjqymq1404'][value='${me.yjqymq1404}']").attr("checked","checked");
	$("input[name='yjqymq1501'][value='${me.yjqymq1501}']").attr("checked","checked");
	$("input[name='yjqymq1502'][value='${me.yjqymq1502}']").attr("checked","checked");
	$("input[name='yjqymq1503'][value='${me.yjqymq1503}']").attr("checked","checked");
	$("input[name='yjqymq1601'][value='${me.yjqymq1601}']").attr("checked","checked");
	$("input[name='yjqymq1602'][value='${me.yjqymq1602}']").attr("checked","checked");
	$("input[name='yjqymq1603'][value='${me.yjqymq1603}']").attr("checked","checked");
	$("input[name='yjqymq1701'][value='${me.yjqymq1701}']").attr("checked","checked");
	$("input[name='yjqymq1702'][value='${me.yjqymq1702}']").attr("checked","checked");
	$("input[name='yjqymq1703'][value='${me.yjqymq1703}']").attr("checked","checked");
	$("input[name='yjqymq1801'][value='${me.yjqymq1801}']").attr("checked","checked");
	$("input[name='yjqymq1802'][value='${me.yjqymq1802}']").attr("checked","checked");
	$("input[name='yjqymq1803'][value='${me.yjqymq1803}']").attr("checked","checked");
	$("input[name='yjqymq1804'][value='${me.yjqymq1804}']").attr("checked","checked");
	$("input[name='yjqymq1805'][value='${me.yjqymq1805}']").attr("checked","checked");
	$("input[name='yjqymq1806'][value='${me.yjqymq1806}']").attr("checked","checked");
	$("input[name='yjqymq1807'][value='${me.yjqymq1807}']").attr("checked","checked");
	$("input[name='yjqymq1808'][value='${me.yjqymq1808}']").attr("checked","checked");
	$("input[name='yjqymq1809'][value='${me.yjqymq1809}']").attr("checked","checked");
	$("input[name='yjqymq1810'][value='${me.yjqymq1810}']").attr("checked","checked");
	$("input[name='yjqymq1811'][value='${me.yjqymq1811}']").attr("checked","checked");
	$("input[name='yjqymq1812'][value='${me.yjqymq1812}']").attr("checked","checked");
	$("input[name='yjqymq1813'][value='${me.yjqymq1813}']").attr("checked","checked");
	$("input[name='yjqymq1814'][value='${me.yjqymq1814}']").attr("checked","checked");
	$("input[name='yjqymq1815'][value='${me.yjqymq1815}']").attr("checked","checked");
	$("input[name='yjqymq1816'][value='${me.yjqymq1816}']").attr("checked","checked");
	$("input[name='yjqymq1817'][value='${me.yjqymq1817}']").attr("checked","checked");
	$("input[name='yjqymq1818'][value='${me.yjqymq1818}']").attr("checked","checked");
	$("#jcry").attr("value","${me.jcry }");
	

}
function save() {
	if(!$("#jcry").val()){
		$("#jcry").focus();
		alert("请签字！");
		return;
	}
	
  var obj = {
	"yjqymq0101" : $("input[name='yjqymq0101']:checked").val(),
	"yjqymq0102" : $("input[name='yjqymq0102']:checked").val(),
	"yjqymq0201" : $("input[name='yjqymq0201']:checked").val(),
	"yjqymq0202" : $("input[name='yjqymq0202']:checked").val(),
	"yjqymq0203" : $("input[name='yjqymq0203']:checked").val(),
	"yjqymq0204" : $("input[name='yjqymq0204']:checked").val(),
	"yjqymq0205" : $("input[name='yjqymq0205']:checked").val(),
	"yjqymq0206" : $("input[name='yjqymq0206']:checked").val(),
	"yjqymq0207" : $("input[name='yjqymq0207']:checked").val(),
	"yjqymq0208" : $("input[name='yjqymq0208']:checked").val(),
	"yjqymq0209" : $("input[name='yjqymq0209']:checked").val(),
	"yjqymq0210" : $("input[name='yjqymq0210']:checked").val(),
	"yjqymq0211" : $("input[name='yjqymq0211']:checked").val(),
	"yjqymq0212" : $("input[name='yjqymq0212']:checked").val(),
	"yjqymq0213" : $("input[name='yjqymq0213']:checked").val(),
	"yjqymq0301" : $("input[name='yjqymq0301']:checked").val(),
	"yjqymq0302" : $("input[name='yjqymq0302']:checked").val(),
	"yjqymq0303" : $("input[name='yjqymq0303']:checked").val(),
	"yjqymq0304" : $("input[name='yjqymq0304']:checked").val(),
	"yjqymq0305" : $("input[name='yjqymq0305']:checked").val(),
	"yjqymq0306" : $("input[name='yjqymq0306']:checked").val(),
	"yjqymq0307" : $("input[name='yjqymq0307']:checked").val(),
	"yjqymq0308" : $("input[name='yjqymq0308']:checked").val(),
	"yjqymq0309" : $("input[name='yjqymq0309']:checked").val(),
	"yjqymq0310" : $("input[name='yjqymq0310']:checked").val(),
	"yjqymq0311" : $("input[name='yjqymq0311']:checked").val(),
	"yjqymq0312" : $("input[name='yjqymq0312']:checked").val(),
	"yjqymq0313" : $("input[name='yjqymq0313']:checked").val(),
	"yjqymq0401" : $("input[name='yjqymq0401']:checked").val(),
	"yjqymq0402" : $("input[name='yjqymq0402']:checked").val(),
	"yjqymq0403" : $("input[name='yjqymq0403']:checked").val(),
	"yjqymq0404" : $("input[name='yjqymq0404']:checked").val(),
	"yjqymq0501" : $("input[name='yjqymq0501']:checked").val(),
	"yjqymq0502" : $("input[name='yjqymq0502']:checked").val(),
	"yjqymq0503" : $("input[name='yjqymq0503']:checked").val(),
	"yjqymq0504" : $("input[name='yjqymq0504']:checked").val(),
	"yjqymq0505" : $("input[name='yjqymq0505']:checked").val(),
	"yjqymq0506" : $("input[name='yjqymq0506']:checked").val(),
	"yjqymq0507" : $("input[name='yjqymq0507']:checked").val(),
	"yjqymq0508" : $("input[name='yjqymq0508']:checked").val(),
	"yjqymq0509" : $("input[name='yjqymq0509']:checked").val(),
	"yjqymq0601" : $("input[name='yjqymq0601']:checked").val(),
	"yjqymq0602" : $("input[name='yjqymq0602']:checked").val(),
	"yjqymq0603" : $("input[name='yjqymq0603']:checked").val(),
	"yjqymq0604" : $("input[name='yjqymq0604']:checked").val(),
	"yjqymq0701" : $("input[name='yjqymq0701']:checked").val(),
	"yjqymq0702" : $("input[name='yjqymq0702']:checked").val(),
	"yjqymq0703" : $("input[name='yjqymq0703']:checked").val(),
	"yjqymq0704" : $("input[name='yjqymq0704']:checked").val(),
	"yjqymq0705" : $("input[name='yjqymq0705']:checked").val(),
	"yjqymq0706" : $("input[name='yjqymq0706']:checked").val(),
	"yjqymq0801" : $("input[name='yjqymq0801']:checked").val(),
	"yjqymq0802" : $("input[name='yjqymq0802']:checked").val(),
	"yjqymq0803" : $("input[name='yjqymq0803']:checked").val(),
	"yjqymq0804" : $("input[name='yjqymq0804']:checked").val(),
	"yjqymq0805" : $("input[name='yjqymq0805']:checked").val(),
	"yjqymq0806" : $("input[name='yjqymq0806']:checked").val(),
	"yjqymq0807" : $("input[name='yjqymq0807']:checked").val(),
	"yjqymq0808" : $("input[name='yjqymq0808']:checked").val(),
	"yjqymq0809" : $("input[name='yjqymq0809']:checked").val(),
	"yjqymq0810" : $("input[name='yjqymq0810']:checked").val(),
	"yjqymq0811" : $("input[name='yjqymq0811']:checked").val(),
	"yjqymq0812" : $("input[name='yjqymq0812']:checked").val(),
	"yjqymq0813" : $("input[name='yjqymq0813']:checked").val(),
	"yjqymq0814" : $("input[name='yjqymq0814']:checked").val(),
	"yjqymq0815" : $("input[name='yjqymq0815']:checked").val(),
	"yjqymq0901" : $("input[name='yjqymq0901']:checked").val(),
	"yjqymq0902" : $("input[name='yjqymq0902']:checked").val(),
	"yjqymq0903" : $("input[name='yjqymq0903']:checked").val(),
	"yjqymq0904" : $("input[name='yjqymq0904']:checked").val(),
	"yjqymq0905" : $("input[name='yjqymq0905']:checked").val(),
	"yjqymq0906" : $("input[name='yjqymq0906']:checked").val(),
	"yjqymq0907" : $("input[name='yjqymq0907']:checked").val(),
	"yjqymq0908" : $("input[name='yjqymq0908']:checked").val(),
	"yjqymq0909" : $("input[name='yjqymq0909']:checked").val(),
	"yjqymq1001" : $("input[name='yjqymq1001']:checked").val(),
	"yjqymq1002" : $("input[name='yjqymq1002']:checked").val(),
	"yjqymq1003" : $("input[name='yjqymq1003']:checked").val(),
	"yjqymq1004" : $("input[name='yjqymq1004']:checked").val(),
	"yjqymq1101" : $("input[name='yjqymq1101']:checked").val(),
	"yjqymq1102" : $("input[name='yjqymq1102']:checked").val(),
	"yjqymq1103" : $("input[name='yjqymq1103']:checked").val(),
	"yjqymq1104" : $("input[name='yjqymq1104']:checked").val(),
	"yjqymq1105" : $("input[name='yjqymq1105']:checked").val(),
	"yjqymq1201" : $("input[name='yjqymq1201']:checked").val(),
	"yjqymq1202" : $("input[name='yjqymq1202']:checked").val(),
	"yjqymq1203" : $("input[name='yjqymq1203']:checked").val(),
	"yjqymq1204" : $("input[name='yjqymq1204']:checked").val(),
	"yjqymq1205" : $("input[name='yjqymq1205']:checked").val(),
	"yjqymq1206" : $("input[name='yjqymq1206']:checked").val(),
	"yjqymq1207" : $("input[name='yjqymq1207']:checked").val(),
	"yjqymq1208" : $("input[name='yjqymq1208']:checked").val(),
	"yjqymq1209" : $("input[name='yjqymq1209']:checked").val(),
	"yjqymq1210" : $("input[name='yjqymq1210']:checked").val(),
	"yjqymq1211" : $("input[name='yjqymq1211']:checked").val(),
	"yjqymq1212" : $("input[name='yjqymq1212']:checked").val(),
	"yjqymq1301" : $("input[name='yjqymq1301']:checked").val(),
	"yjqymq1302" : $("input[name='yjqymq1302']:checked").val(),
	"yjqymq1303" : $("input[name='yjqymq1303']:checked").val(),
	"yjqymq1304" : $("input[name='yjqymq1304']:checked").val(),
	"yjqymq1305" : $("input[name='yjqymq1305']:checked").val(),
	"yjqymq1306" : $("input[name='yjqymq1306']:checked").val(),
	"yjqymq1307" : $("input[name='yjqymq1307']:checked").val(),
	"yjqymq1308" : $("input[name='yjqymq1308']:checked").val(),
	"yjqymq1309" : $("input[name='yjqymq1309']:checked").val(),
	"yjqymq1310" : $("input[name='yjqymq1310']:checked").val(),
	"yjqymq1311" : $("input[name='yjqymq1311']:checked").val(),
	"yjqymq1312" : $("input[name='yjqymq1312']:checked").val(),
	"yjqymq1313" : $("input[name='yjqymq1313']:checked").val(),
	"yjqymq1401" : $("input[name='yjqymq1401']:checked").val(),
	"yjqymq1402" : $("input[name='yjqymq1402']:checked").val(),
	"yjqymq1403" : $("input[name='yjqymq1403']:checked").val(),
	"yjqymq1404" : $("input[name='yjqymq1404']:checked").val(),
	"yjqymq1501" : $("input[name='yjqymq1501']:checked").val(),
	"yjqymq1502" : $("input[name='yjqymq1502']:checked").val(),
	"yjqymq1503" : $("input[name='yjqymq1503']:checked").val(),
	"yjqymq1601" : $("input[name='yjqymq1601']:checked").val(),
	"yjqymq1602" : $("input[name='yjqymq1602']:checked").val(),
	"yjqymq1603" : $("input[name='yjqymq1603']:checked").val(),
	"yjqymq1701" : $("input[name='yjqymq1701']:checked").val(),
	"yjqymq1702" : $("input[name='yjqymq1702']:checked").val(),
	"yjqymq1703" : $("input[name='yjqymq1703']:checked").val(),
	"yjqymq1801" : $("input[name='yjqymq1801']:checked").val(),
	"yjqymq1802" : $("input[name='yjqymq1802']:checked").val(),
	"yjqymq1803" : $("input[name='yjqymq1803']:checked").val(),
	"yjqymq1804" : $("input[name='yjqymq1804']:checked").val(),
	"yjqymq1805" : $("input[name='yjqymq1805']:checked").val(),
	"yjqymq1806" : $("input[name='yjqymq1806']:checked").val(),
	"yjqymq1807" : $("input[name='yjqymq1807']:checked").val(),
	"yjqymq1808" : $("input[name='yjqymq1808']:checked").val(),
	"yjqymq1809" : $("input[name='yjqymq1809']:checked").val(),
	"yjqymq1810" : $("input[name='yjqymq1810']:checked").val(),
	"yjqymq1811" : $("input[name='yjqymq1811']:checked").val(),
	"yjqymq1812" : $("input[name='yjqymq1812']:checked").val(),
	"yjqymq1813" : $("input[name='yjqymq1813']:checked").val(),
	"yjqymq1814" : $("input[name='yjqymq1814']:checked").val(),
	"yjqymq1815" : $("input[name='yjqymq1815']:checked").val(),
	"yjqymq1816" : $("input[name='yjqymq1816']:checked").val(),
	"yjqymq1817" : $("input[name='yjqymq1817']:checked").val(),
	"yjqymq1818" : $("input[name='yjqymq1818']:checked").val(),
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
  $.post(getRootPath() + "/company/source/tmetallurgy-save", obj,function(result){
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