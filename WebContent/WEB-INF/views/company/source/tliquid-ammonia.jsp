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
			class="c-gray en">&gt;</span> <span>非化工液氨使用企业</span> <a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:void(0);" title="返回"
			onclick="parent.close_tab(function(){})">返回</a>
	</nav>


	<h3 class="text-c">《非化工液氨使用企业》</h3>
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
					<td class="text-c" rowspan="9">安全措施</td>
					<td class="text-c">液氨储罐应设置不燃烧体防护围堤或隔离设施、设备，并密实闭合。</td>
					<td id="aqcs1" class="text-c">
						<div class="radio-box">
							<input type="radio" name="aqcs1" id="aqcs1_s" checked="checked"
								value="1"><label for="aqcs1_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="aqcs1" id="aqcs1_f" value="0"><label
								for="aqcs1_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">安全措施</td>
					<td class="text-c">液氨储罐等压力容器和设备应设置安全阀、压力表、液位计、温度计，并应装有带压力、液位、温度远传记录和报警功能的安全装置，设置整流装置与压力机、动力电源、管线压力、通风设施或相应的吸收装置的联锁装置。液氨储罐需设置紧急切断装置。</td>
					<td id="aqcs2" class="text-c">
						<div class="radio-box">
							<input type="radio" name="aqcs2" id="aqcs2_s" checked="checked"
								value="1"><label for="aqcs2_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="aqcs2" id="aqcs2_f" value="0"><label
								for="aqcs2_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">安全措施</td>
					<td class="text-c">氨分解车间应设固定式可燃气体（氢）泄漏检测报警仪。在液氨场所（包括储存场所）应设固定式有毒气体（氨）泄漏检测报警仪。</td>
					<td id="aqcs3" class="text-c">
						<div class="radio-box">
							<input type="radio" name="aqcs3" id="aqcs3_s" checked="checked"
								value="1"><label for="aqcs3_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="aqcs3" id="aqcs3_f" value="0"><label
								for="aqcs3_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">安全措施</td>
					<td class="text-c">防雷装置检测应当每年一次，液氨场所的防雷装置应当每半年检测一次。所有防静电设施（设备）要定期检查、维修，并建立档案。</td>
					<td id="aqcs4" class="text-c">
						<div class="radio-box">
							<input type="radio" name="aqcs4" id="aqcs4_s" checked="checked"
								value="1"><label for="aqcs4_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="aqcs4" id="aqcs4_f" value="0"><label
								for="aqcs4_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">安全措施</td>
					<td class="text-c">有毒/可燃气体报警仪应由法定计量检定机构定期检定合格。</td>
					<td id="aqcs5" class="text-c">
						<div class="radio-box">
							<input type="radio" name="aqcs5" id="aqcs5_s" checked="checked"
								value="1"><label for="aqcs5_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="aqcs5" id="aqcs5_f" value="0"><label
								for="aqcs5_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">安全措施</td>
					<td class="text-c">在厂区内显著位置设风向标。</td>
					<td id="aqcs6" class="text-c">
						<div class="radio-box">
							<input type="radio" name="aqcs6" id="aqcs6_s" checked="checked"
								value="1"><label for="aqcs6_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="aqcs6" id="aqcs6_f" value="0"><label
								for="aqcs6_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">安全措施</td>
					<td class="text-c">有毒气体报警仪应与事故排风装置联锁。</td>
					<td id="aqcs7" class="text-c">
						<div class="radio-box">
							<input type="radio" name="aqcs7" id="aqcs7_s" checked="checked"
								value="1"><label for="aqcs7_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="aqcs7" id="aqcs7_f" value="0"><label
								for="aqcs7_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">安全措施</td>
					<td class="text-c">有毒可燃气体报警仪及重大危险源视频监控等信号，应传输至本单位液氨操作控制室或值班室。</td>
					<td id="aqcs8" class="text-c">
						<div class="radio-box">
							<input type="radio" name="aqcs8" id="aqcs8_s" checked="checked"
								value="1"><label for="aqcs8_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="aqcs8" id="aqcs8_f" value="0"><label
								for="aqcs8_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">安全措施</td>
					<td class="text-c">控制仪表系统设置UPS不间断电源和应急照明。</td>
					<td id="aqcs9" class="text-c">
						<div class="radio-box">
							<input type="radio" name="aqcs9" id="aqcs9_s" checked="checked"
								value="1"><label for="aqcs9_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="aqcs9" id="aqcs9_f" value="0"><label
								for="aqcs9_f">否</label>
						</div>
					</td>
				</tr>
				
				
				<!-- 作业场所要求 -->
				<tr>
					<td class="text-c" rowspan="9">作业场所</td>
					<td class="text-c">在液氨场所应提供充分的局部排风和全面通风，严禁吸烟。</td>
					<td id="zycs1" class="text-c">
						<div class="radio-box">
							<input type="radio" name="zycs1" id="zycs1_s" checked="checked"
								value="1"><label for="zycs1_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="zycs1" id="zycs1_f" value="0"><label
								for="zycs1_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">作业场所</td>
					<td class="text-c">涉氨厂房内、储罐区应采用防爆型照明、通风设施。</td>
					<td id="zycs2" class="text-c">
						<div class="radio-box">
							<input type="radio" name="zycs2" id="zycs2_s" checked="checked"
								value="1"><label for="zycs2_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="zycs2" id="zycs2_f" value="0"><label
								for="zycs2_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">作业场所</td>
					<td class="text-c">在液氨场所应配备2%硼酸溶液、生理盐水等急救药品。 </td>
					<td id="zycs3" class="text-c">
						<div class="radio-box">
							<input type="radio" name="zycs3" id="zycs3_s" checked="checked"
								value="1"><label for="zycs3_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="zycs3" id="zycs3_f" value="0"><label
								for="zycs3_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">作业场所</td>
					<td class="text-c">在液氨场所应配备紧急淋浴和洗眼器。</td>
					<td id="zycs4" class="text-c">
						<div class="radio-box">
							<input type="radio" name="zycs4" id="zycs4_s" checked="checked"
								value="1"><label for="zycs4_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="zycs4" id="zycs4_f" value="0"><label
								for="zycs4_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">作业场所</td>
					<td class="text-c"> 在液氨场所（包括装置、罐区、装卸区、管道）等危险场所的醒目位置，是否设置符合GB2894规定的安全标志；是否设置明显的安全告知牌，安全告知牌应注明液氨特性、危害防护、处置措施、报警电话等内容。</td>
					<td id="zycs5" class="text-c">
						<div class="radio-box">
							<input type="radio" name="zycs5" id="zycs5_s" checked="checked"
								value="1"><label for="zycs5_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="zycs5" id="zycs5_f" value="0"><label
								for="zycs5_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">作业场所</td>
					<td class="text-c">在液氨场所是否在醒目位置设置公告栏，公布有关职业病防治的规章制度、操作规程、职业病危害事故应急救援措施和工作场所职业病危害因素检测结果。对产生严重职业病危害的作业岗位，应当在其醒目位置，设置符合GBZ158的警示标识和中文警示说明。</td>
					<td id="zycs6" class="text-c">
						<div class="radio-box">
							<input type="radio" name="zycs6" id="zycs6_s" checked="checked"
								value="1"><label for="zycs6_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="zycs6" id="zycs6_f" value="0"><label
								for="zycs6_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">作业场所</td>
					<td class="text-c">包装间、分割间、产品整理间等人员较多生产场所的空调系统采用氨直接蒸发制冷系统。</td>
					<td id="zycs7" class="text-c">
						<div class="radio-box">
							<input type="radio" name="zycs7" id="zycs7_s" checked="checked"
								value="1"><label for="zycs7_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="zycs7" id="zycs7_f" value="0"><label
								for="zycs7_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">作业场所</td>
					<td class="text-c">在液氨场所作业时，从业人员是否戴化学安全防护眼镜，穿防静电工作服，戴橡胶手套。</td>
					<td id="zycs8" class="text-c">
						<div class="radio-box">
							<input type="radio" name="zycs8" id="zycs8_s" checked="checked"
								value="1"><label for="zycs8_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="zycs8" id="zycs8_f" value="0"><label
								for="zycs8_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">作业场所</td>
					<td class="text-c">快速冻结装置未设置在单独的作业间内，且作业间内作业人员数量超过9人。</td>
					<td id="zycs9" class="text-c">
						<div class="radio-box">
							<input type="radio" name="zycs9" id="zycs9_s" checked="checked"
								value="1"><label for="zycs9_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="zycs9" id="zycs9_f" value="0"><label
								for="zycs9_f">否</label>
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
	
	$("input[name='aqcs1'][value='${la.aqcs1}']").attr("checked","checked");
	$("input[name='aqcs2'][value='${la.aqcs2}']").attr("checked","checked");
	$("input[name='aqcs3'][value='${la.aqcs3}']").attr("checked","checked");
	$("input[name='aqcs4'][value='${la.aqcs4}']").attr("checked","checked");
	$("input[name='aqcs5'][value='${la.aqcs5}']").attr("checked","checked");
	$("input[name='aqcs6'][value='${la.aqcs6}']").attr("checked","checked");
	$("input[name='aqcs7'][value='${la.aqcs7}']").attr("checked","checked");
	$("input[name='aqcs8'][value='${la.aqcs8}']").attr("checked","checked");
	$("input[name='aqcs9'][value='${la.aqcs9}']").attr("checked","checked");
	
	$("input[name='zycs1'][value='${la.zycs1}']").attr("checked","checked");
	$("input[name='zycs2'][value='${la.zycs2}']").attr("checked","checked");
	$("input[name='zycs3'][value='${la.zycs3}']").attr("checked","checked");
	$("input[name='zycs4'][value='${la.zycs4}']").attr("checked","checked");
	$("input[name='zycs5'][value='${la.zycs5}']").attr("checked","checked");
	$("input[name='zycs6'][value='${la.zycs6}']").attr("checked","checked");
	$("input[name='zycs7'][value='${la.zycs7}']").attr("checked","checked");
	$("input[name='zycs8'][value='${la.zycs8}']").attr("checked","checked");
	$("input[name='zycs9'][value='${la.zycs9}']").attr("checked","checked");
	$("#jcry").attr("value","${la.jcry }");
}
function save() {
	if(!$("#jcry").val()){
		$("#jcry").focus();
		alert("请签字！");
		return;
	}

  var obj = {
	"aqcs1" : $("input[name='aqcs1']:checked").val(),
	"aqcs2" : $("input[name='aqcs2']:checked").val(),
	"aqcs3" : $("input[name='aqcs3']:checked").val(),
	"aqcs4" : $("input[name='aqcs4']:checked").val(),
	"aqcs5" : $("input[name='aqcs5']:checked").val(),
	"aqcs6" : $("input[name='aqcs6']:checked").val(),
	"aqcs7" : $("input[name='aqcs7']:checked").val(),
	"aqcs8" : $("input[name='aqcs8']:checked").val(),
	"aqcs9" : $("input[name='aqcs9']:checked").val(),
	
	"zycs1" : $("input[name='zycs1']:checked").val(),
	"zycs2" : $("input[name='zycs2']:checked").val(),
	"zycs3" : $("input[name='zycs3']:checked").val(),
	"zycs4" : $("input[name='zycs4']:checked").val(),
	"zycs5" : $("input[name='zycs5']:checked").val(),
	"zycs6" : $("input[name='zycs6']:checked").val(),
	"zycs7" : $("input[name='zycs7']:checked").val(),
	"zycs8" : $("input[name='zycs8']:checked").val(),
	"zycs9" : $("input[name='zycs9']:checked").val(),
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
  $.post(getRootPath() + "/company/source/tliquid-ammonia-save", obj,function(result){
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