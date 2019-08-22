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
			class="c-gray en">&gt;</span> <span>粉尘涉爆企业</span>
	<%--<a--%>
			<%--class="btn btn-success radius r"--%>
			<%--style="line-height: 1.6em; margin-top: 3px"--%>
			<%--href="javascript:void(0);" title="返回"--%>
			<%--onclick="parent.close_tab(function(){})">返回</a>--%>

	<a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;" href="javascript:history.back(-1)" title="返回">返回</a>
	</nav>


	<h3 class="text-c">《粉尘涉爆企业》</h3>
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
					<td class="text-c" rowspan="1">建筑物</td>
					<td class="text-c">粉尘爆炸危险作业场所的厂房，必须满足相关规定要求，并通过消防建筑设计审核，严禁设置在违规多层厂房内。 粉尘爆炸危险作业场所严禁设置在居民区，严禁与员工宿舍在同一座建筑物内，严禁存放汽油、煤油、苯等易燃物。粉尘爆炸危险场所设置在非框架结构的多层建构筑物内，或与居民区、员工宿舍、会议室等人员密集场所安全距离不足。</td>
					<td id="fcsbqy11" class="text-c">
						<div class="radio-box">
							<input type="radio" name="fcsbqy11" id="fcsbqy11_s" checked="checked"
								value="1"><label for="fcsbqy11_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="fcsbqy11" id="fcsbqy11_f" value="0"><label
								for="fcsbqy11_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="3">防爆电气设备</td>
					<td class="text-c">企业应根据本单位粉尘爆炸危险场所的具体情况，正确介定爆炸危险区域，根据不同的防爆等级，采用相应的防爆电器。电气设备的铭牌标识清楚，有防爆标志、防爆合格证号，外壳无裂缝、损伤，电机不得漏油。 粉尘爆炸危险场所的20区使用防爆电气设备设施。</td>
					<td id="fcsbqy21" class="text-c">
						<div class="radio-box">
							<input type="radio" name="fcsbqy21" id="fcsbqy21_s" checked="checked"
								value="1"><label for="fcsbqy21_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="fcsbqy21" id="fcsbqy21_f" value="0"><label
								for="fcsbqy21_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="3">防爆电气设备</td>
					<td class="text-c">在爆炸性粉尘环境内，应尽量减少插座和局部照明灯具的数量。如需采用时，插座宜布置在爆炸性粉尘不易积聚的地点，局部宜布置在事故气流不易冲击的位置。插座开口的一面应朝下，且与垂直面的角度不应大于60度。</td>
					<td id="fcsbqy22" class="text-c">
						<div class="radio-box">
							<input type="radio" name="fcsbqy22" id="fcsbqy22_s" checked="checked"
								value="1"><label for="fcsbqy22_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="fcsbqy22" id="fcsbqy22_f" value="0"><label
								for="fcsbqy22_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="3">防爆电气设备</td>
					<td class="text-c">电气布线应敷设在钢管中；管线穿墙及楼板时，孔洞应采用非可燃性填料严密堵塞。</td>
					<td id="fcsbqy23" class="text-c">
						<div class="radio-box">
							<input type="radio" name="fcsbqy23" id="fcsbqy23_s" checked="checked"
								value="1"><label for="fcsbqy23_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="fcsbqy23" id="fcsbqy23_f" value="0"><label
								for="fcsbqy23_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="2">防雷防静电</td>
					<td class="text-c">粉尘爆炸危险作业场所的厂房（建构筑物）应按规定设置防雷系统，并可靠接地。 </td>
					<td id="fcsbqy31" class="text-c">
						<div class="radio-box">
							<input type="radio" name="fcsbqy31" id="fcsbqy31_s" checked="checked"
								value="1"><label for="fcsbqy31_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="fcsbqy31" id="fcsbqy31_f" value="0"><label
								for="fcsbqy31_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="2">防雷防静电</td>
					<td class="text-c">粉尘爆炸危险作业场所应对所有金属设备、装置外壳、金属管道、支架、构件、部件等，应按规定采取防静电接地。所有金属管道连接处(如法兰)应进行跨接。除尘系统静电接地必须独立设置。</td>
					<td id="fcsbqy32" class="text-c">
						<div class="radio-box">
							<input type="radio" name="fcsbqy32" id="fcsbqy32_s" checked="checked"
								value="1"><label for="fcsbqy32_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="fcsbqy32" id="fcsbqy32_f" value="0"><label
								for="fcsbqy32_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="5">通风除尘</td>
					<td class="text-c">处理有爆炸危险粉尘的除尘器、排风机的设置应符合下列规定： 1）应与其它普通型的风机、除尘器分开设置； 2）应按生产工艺分片（分区域）设置相对独立的除尘系统，并保证除尘系统有足够的风量，风管中不应有粉尘沉降。 可燃性粉尘与可燃气体等易加剧爆炸危险的介质不能共用一套除尘系统，不同防火分区的除尘系统不能互联互通。</td>
					<td id="fcsbqy41" class="text-c">
						<div class="radio-box">
							<input type="radio" name="fcsbqy41" id="fcsbqy41_s" checked="checked"
								value="1"><label for="fcsbqy41_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="fcsbqy41" id="fcsbqy41_f" value="0"><label
								for="fcsbqy41_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="5">通风除尘</td>
					<td class="text-c">所有产尘点均应按规定要求设置吸尘罩，风口保证有足够的吸尘风量风速应大于1m/s，主管道系统垂直风管设计风速应大于19m/s，水平风管设计风速应大于23m/s，满足作业岗位安全和职业卫生要求。</td>
					<td id="fcsbqy42" class="text-c">
						<div class="radio-box">
							<input type="radio" name="fcsbqy42" id="fcsbqy42_s" checked="checked"
								value="1"><label for="fcsbqy42_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="fcsbqy42" id="fcsbqy42_f" value="0"><label
								for="fcsbqy42_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="5">通风除尘</td>
					<td class="text-c">处理有爆炸危险粉尘和碎屑的除尘器、过滤器、管道均应设置泄压装置，泄爆口应按规定设置。净化有爆炸危险粉尘的干式除尘器和过滤器应布置在系统的负压段上。除尘器的进出口风管应设风压监测装置。 干式除尘系统应规范采用泄爆、隔爆、惰化、抑爆等任一种控爆措施。</td>
					<td id="fcsbqy43" class="text-c">
						<div class="radio-box">
							<input type="radio" name="fcsbqy43" id="fcsbqy43_s" checked="checked"
								value="1"><label for="fcsbqy43_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="fcsbqy43" id="fcsbqy43_f" value="0"><label
								for="fcsbqy43_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="5">通风除尘</td>
					<td class="text-c">除尘器宜安装在室外；如安装在室内，其泄爆管应直通室外，且长度小于3m，并根据粉尘属性设立隔（阻）爆装置。排风设备不应布置在地下、半地下建筑（室）中。 除尘系统采用粉尘沉降室除尘，或者采用干式巷道式构筑物作为除尘风道。</td>
					<td id="fcsbqy44" class="text-c">
						<div class="radio-box">
							<input type="radio" name="fcsbqy44" id="fcsbqy44_s" checked="checked"
								value="1"><label for="fcsbqy44_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="fcsbqy44" id="fcsbqy44_f" value="0"><label
								for="fcsbqy44_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="5">通风除尘</td>
					<td class="text-c">除尘器应采用具有连续卸灰或自动周期卸灰功能的灰斗锁气卸灰装置，收尘器箱体内不应有明显结灰。 袋式除尘器应采用脉冲喷吹等强力清灰方式进行可靠清灰。并根据除尘器类型、清灰方式、过滤风速、入口粉尘浓度等确定合理清理周期，清灰气源应符合产品说明书规定要求，并详细记录。铝镁等金属粉尘生产、收集、贮存必须按照GB15577要求配备防水防潮设施。 干式除尘系统应规范采用泄爆、隔爆、惰化、抑爆等任一种控爆措施。</td>
					<td id="fcsbqy45" class="text-c">
						<div class="radio-box">
							<input type="radio" name="fcsbqy45" id="fcsbqy45_s" checked="checked"
								value="1"><label for="fcsbqy45_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="fcsbqy45" id="fcsbqy45_f" value="0"><label
								for="fcsbqy45_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="2">作业前准备</td>
					<td class="text-c">企业应当在粉尘爆炸危险场所和设施、设备上设置明显的安全警示标志，并对作业场所的安全风险进行公告。</td>
					<td id="fcsbqy51" class="text-c">
						<div class="radio-box">
							<input type="radio" name="fcsbqy51" id="fcsbqy51_s" checked="checked"
								value="1"><label for="fcsbqy51_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="fcsbqy51" id="fcsbqy51_f" value="0"><label
								for="fcsbqy51_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="2">作业前准备</td>
					<td class="text-c">在粉尘爆炸危险场所作业前，要先检查各类仪器、仪表、装置是否正常，并将检查情况如实记录；粉尘除、排尘系统的排风风机运行要先开启（运行10分钟）后停止（作业完全停止后运行15分钟）。</td>
					<td id="fcsbqy52" class="text-c">
						<div class="radio-box">
							<input type="radio" name="fcsbqy52" id="fcsbqy52_s" checked="checked"
								value="1"><label for="fcsbqy52_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="fcsbqy52" id="fcsbqy52_f" value="0"><label
								for="fcsbqy52_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="1">危险场所的明火作业要求</td>
					<td class="text-c">粉尘爆炸危险作业场所严禁明火作业，确需要明火作业遵守下列规定: 1）停产撤离作业人员，有安全负责人批准并取得动火证； 2）明火作业开始前，应清除明火作业场所的可燃粉尘并配备充足的灭火器材，金属粉尘应使用D类干粉灭火器； 3）进行明火作业的区段应与其他区段分开或隔开； 4）进行明火作业期间和作业完成后的冷却期间，不应有粉尘进入明火作业场所。</td>
					<td id="fcsbqy61" class="text-c">
						<div class="radio-box">
							<input type="radio" name="fcsbqy61" id="fcsbqy61_s" checked="checked"
								value="1"><label for="fcsbqy61_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="fcsbqy61" id="fcsbqy61_f" value="0"><label
								for="fcsbqy61_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="1">检维修作业要求</td>
					<td class="text-c">检维修作业：生产系统应完全停止、现场积尘清理干净后，方可进行检维修作业，应使用防爆工具。严禁非维修人员进入现场；检维修作业如涉及有限空间作业应严格遵守“先通风、再检测、后作业”的原则，确定符合安全要求后，方可进入。</td>
					<td id="fcsbqy71" class="text-c">
						<div class="radio-box">
							<input type="radio" name="fcsbqy71" id="fcsbqy71_s" checked="checked"
								value="1"><label for="fcsbqy71_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="fcsbqy71" id="fcsbqy71_f" value="0"><label
								for="fcsbqy71_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="2">作业场所清洁要求</td>
					<td class="text-c"> 作业场所清洁应当遵守以下规定： 1）定期对生产场所残留的粉尘进行清理，清洁所有可能积累粉尘的场所，包括地面、墙角墙面、设备表面和横梁等，并及时对除尘器、吸排尘管道等设备的粉尘进行清理； 2）应当采用不产生火花、静电、扬尘等方法进行粉尘清理。</td>
					<td id="fcsbqy81" class="text-c">
						<div class="radio-box">
							<input type="radio" name="fcsbqy81" id="fcsbqy81_s" checked="checked"
								value="1"><label for="fcsbqy81_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="fcsbqy81" id="fcsbqy81_f" value="0"><label
								for="fcsbqy81_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="2">作业场所清洁要求</td>
					<td class="text-c">应严格按照作业规程进行清扫作业，严禁使用压缩空气正压吹扫粉尘。</td>
					<td id="fcsbqy82" class="text-c">
						<div class="radio-box">
							<input type="radio" name="fcsbqy82" id="fcsbqy82_s" checked="checked"
								value="1"><label for="fcsbqy82_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="fcsbqy82" id="fcsbqy82_f" value="0"><label
								for="fcsbqy82_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="3">作业的清洁防护</td>
					<td class="text-c">企业应为可燃粉尘作业人员配备防尘口罩、防静电手套、防静电鞋、防静电服或棉布工作服、防尘服、阻燃防护服等个体防护装备。禁止穿化纤类易产生静电的工装。</td>
					<td id="fcsbqy91" class="text-c">
						<div class="radio-box">
							<input type="radio" name="fcsbqy91" id="fcsbqy91_s" checked="checked"
								value="1"><label for="fcsbqy91_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="fcsbqy91" id="fcsbqy91_f" value="0"><label
								for="fcsbqy91_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="3">作业的清洁防护</td>
					<td class="text-c">在液氨场所作业时，从业人员是否戴化学安全防护眼镜，穿防静电工作服，戴橡胶手套。</td>
					<td id="fcsbqy92" class="text-c">
						<div class="radio-box">
							<input type="radio" name="fcsbqy92" id="fcsbqy92_s" checked="checked"
								value="1"><label for="fcsbqy92_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="fcsbqy92" id="fcsbqy92_f" value="0"><label
								for="fcsbqy92_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="3">作业的清洁防护</td>
					<td class="text-c">快速冻结装置未设置在单独的作业间内，且作业间内作业人员数量超过9人。</td>
					<td id="fcsbqy93" class="text-c">
						<div class="radio-box">
							<input type="radio" name="fcsbqy93" id="fcsbqy93_s" checked="checked"
								value="1"><label for="fcsbqy93_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="fcsbqy93" id="fcsbqy93_f" value="0"><label
								for="fcsbqy93_f">否</label>
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
	
	$("input[name='fcsbqy11'][value='${se.fcsbqy11}']").attr("checked","checked");
	$("input[name='fcsbqy21'][value='${se.fcsbqy21}']").attr("checked","checked");
	$("input[name='fcsbqy22'][value='${se.fcsbqy22}']").attr("checked","checked");
	$("input[name='fcsbqy23'][value='${se.fcsbqy23}']").attr("checked","checked");
	$("input[name='fcsbqy31'][value='${se.fcsbqy31}']").attr("checked","checked");
	$("input[name='fcsbqy32'][value='${se.fcsbqy32}']").attr("checked","checked");
	$("input[name='fcsbqy41'][value='${se.fcsbqy41}']").attr("checked","checked");
	$("input[name='fcsbqy42'][value='${se.fcsbqy42}']").attr("checked","checked");
	$("input[name='fcsbqy43'][value='${se.fcsbqy43}']").attr("checked","checked");
	$("input[name='fcsbqy44'][value='${se.fcsbqy44}']").attr("checked","checked");
	$("input[name='fcsbqy45'][value='${se.fcsbqy45}']").attr("checked","checked");
	$("input[name='fcsbqy51'][value='${se.fcsbqy51}']").attr("checked","checked");
	$("input[name='fcsbqy52'][value='${se.fcsbqy52}']").attr("checked","checked");
	$("input[name='fcsbqy61'][value='${se.fcsbqy61}']").attr("checked","checked");
	$("input[name='fcsbqy71'][value='${se.fcsbqy71}']").attr("checked","checked");
	$("input[name='fcsbqy81'][value='${se.fcsbqy81}']").attr("checked","checked");
	$("input[name='fcsbqy82'][value='${se.fcsbqy82}']").attr("checked","checked");
	$("input[name='fcsbqy91'][value='${se.fcsbqy91}']").attr("checked","checked");
	$("input[name='fcsbqy92'][value='${se.fcsbqy92}']").attr("checked","checked");
	$("input[name='fcsbqy93'][value='${se.fcsbqy93}']").attr("checked","checked");
	$("#jcry").attr("value","${se.jcry }");

}
function save() {
	if(!$("#jcry").val()){
		$("#jcry").focus();
		alert("请签字！");
		return;
	}
	
  var obj = {
	"fcsbqy11" : $("input[name='fcsbqy11']:checked").val(),
	"fcsbqy21" : $("input[name='fcsbqy21']:checked").val(),
	"fcsbqy22" : $("input[name='fcsbqy22']:checked").val(),
	"fcsbqy23" : $("input[name='fcsbqy23']:checked").val(),
	"fcsbqy31" : $("input[name='fcsbqy31']:checked").val(),
	"fcsbqy32" : $("input[name='fcsbqy32']:checked").val(),
	"fcsbqy41" : $("input[name='fcsbqy41']:checked").val(),
	"fcsbqy42" : $("input[name='fcsbqy42']:checked").val(),
	"fcsbqy43" : $("input[name='fcsbqy43']:checked").val(),
	"fcsbqy44" : $("input[name='fcsbqy44']:checked").val(),
	"fcsbqy45" : $("input[name='fcsbqy45']:checked").val(),
	"fcsbqy51" : $("input[name='fcsbqy51']:checked").val(),
	"fcsbqy52" : $("input[name='fcsbqy52']:checked").val(),
	"fcsbqy61" : $("input[name='fcsbqy61']:checked").val(),
	"fcsbqy71" : $("input[name='fcsbqy71']:checked").val(),
	"fcsbqy81" : $("input[name='fcsbqy81']:checked").val(),
	"fcsbqy82" : $("input[name='fcsbqy82']:checked").val(),
	"fcsbqy91" : $("input[name='fcsbqy91']:checked").val(),
	"fcsbqy92" : $("input[name='fcsbqy92']:checked").val(),
	"fcsbqy93" : $("input[name='fcsbqy93']:checked").val(),
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
  $.post(getRootPath() + "/company/source/tstive-explosion-save", obj,function(result){
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