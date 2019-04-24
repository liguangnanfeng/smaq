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
			class="c-gray en">&gt;</span> <span>工贸行业有限空间安全作业</span> <a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:void(0);" title="返回"
			onclick="parent.close_tab(function(){})">返回</a>
	</nav>


	<h3 class="text-c">《工贸行业有限空间安全作业》</h3>
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
					<td class="text-c" rowspan="9">作业现场管理</td>
					<td class="text-c">设置醒目的警示标志</td>
					<td id="zyxcgl1" class="text-c">
						<div class="radio-box">
							<input type="radio" name="zyxcgl1" id="zyxcgl1_s" checked="checked"
								value="1"><label for="zyxcgl1_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="zyxcgl1" id="zyxcgl1_f" value="0"><label
								for="zyxcgl1_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">作业现场管理</td>
					<td class="text-c">严格遵守“先通风、再检测、后作业”的原则、未经通风和检测合格，任何人员不得进入有限空间作业。</td>
					<td id="zyxcgl2" class="text-c">
						<div class="radio-box">
							<input type="radio" name="zyxcgl2" id="zyxcgl2_s" checked="checked"
								value="1"><label for="zyxcgl2_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="zyxcgl2" id="zyxcgl2_f" value="0"><label
								for="zyxcgl2_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">作业现场管理</td>
					<td class="text-c">作业过程中采取通风措施，保持空气流通，禁止采用纯氧通风换气。</td>
					<td id="zyxcgl3" class="text-c">
						<div class="radio-box">
							<input type="radio" name="zyxcgl3" id="zyxcgl3_s" checked="checked"
								value="1"><label for="zyxcgl3_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="zyxcgl3" id="zyxcgl3_f" value="0"><label
								for="zyxcgl3_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">作业现场管理</td>
					<td class="text-c">没有监护人员在作业现场不得实施有限空间作业。</td>
					<td id="zyxcgl4" class="text-c">
						<div class="radio-box">
							<input type="radio" name="zyxcgl4" id="zyxcgl4_s" checked="checked"
								value="1"><label for="zyxcgl4_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="zyxcgl4" id="zyxcgl4_f" value="0"><label
								for="zyxcgl4_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">作业现场管理</td>
					<td class="text-c">作业结束后，作业现场负责人、监护人员应对作业现场进行清理，撤离作业人员。 </td>
					<td id="zyxcgl5" class="text-c">
						<div class="radio-box">
							<input type="radio" name="zyxcgl5" id="zyxcgl5_s" checked="checked"
								value="1"><label for="zyxcgl5_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="zyxcgl5" id="zyxcgl5_f" value="0"><label
								for="zyxcgl5_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">作业现场管理</td>
					<td class="text-c">作业人员应正确佩戴与使用劳动防护用品。</td>
					<td id="zyxcgl6" class="text-c">
						<div class="radio-box">
							<input type="radio" name="zyxcgl6" id="zyxcgl6_s" checked="checked"
								value="1"><label for="zyxcgl6_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="zyxcgl6" id="zyxcgl6_f" value="0"><label
								for="zyxcgl6_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">作业现场管理</td>
					<td class="text-c">作业中如发生事故，现场有关人员应当立即报警，禁止盲目施救。</td>
					<td id="zyxcgl7" class="text-c">
						<div class="radio-box">
							<input type="radio" name="zyxcgl7" id="zyxcgl7_s" checked="checked"
								value="1"><label for="zyxcgl7_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="zyxcgl7" id="zyxcgl7_f" value="0"><label
								for="zyxcgl7_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">作业现场管理</td>
					<td class="text-c">存在可燃性气体和爆炸性粉尘，通风、检测、照明、通讯设备须符合有关防爆安全要求。</td>
					<td id="zyxcgl8" class="text-c">
						<div class="radio-box">
							<input type="radio" name="zyxcgl8" id="zyxcgl8_s" checked="checked"
								value="1"><label for="zyxcgl8_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="zyxcgl8" id="zyxcgl8_f" value="0"><label
								for="zyxcgl8_f">否</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="text-c" rowspan="9">作业现场管理</td>
					<td class="text-c">落实作业审批制度，未经审批不得擅自进入有限空间作业。</td>
					<td id="zyxcgl9" class="text-c">
						<div class="radio-box">
							<input type="radio" name="zyxcgl9" id="zyxcgl9_s" checked="checked"
								value="1"><label for="zyxcgl9_s">是</label>
						</div>
						<div class="radio-box">
							<input type="radio" name="zyxcgl9" id="zyxcgl9_f" value="0"><label
								for="zyxcgl9_f">否</label>
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
	
	$("input[name='zyxcgl1'][value='${ta.zyxcgl1}']").attr("checked","checked");
	$("input[name='zyxcgl2'][value='${ta.zyxcgl2}']").attr("checked","checked");
	$("input[name='zyxcgl3'][value='${ta.zyxcgl3}']").attr("checked","checked");
	$("input[name='zyxcgl4'][value='${ta.zyxcgl4}']").attr("checked","checked");
	$("input[name='zyxcgl5'][value='${ta.zyxcgl5}']").attr("checked","checked");
	$("input[name='zyxcgl6'][value='${ta.zyxcgl6}']").attr("checked","checked");
	$("input[name='zyxcgl7'][value='${ta.zyxcgl7}']").attr("checked","checked");
	$("input[name='zyxcgl8'][value='${ta.zyxcgl8}']").attr("checked","checked");
	$("input[name='zyxcgl9'][value='${ta.zyxcgl9}']").attr("checked","checked");
	$("#jcry").attr("value","${ta.jcry }");

	

}
function save() {
	if(!$("#jcry").val()){
		$("#jcry").focus();
		alert("请签字！");
		return;
	}
	
  var obj = {
	"zyxcgl1" : $("input[name='zyxcgl1']:checked").val(),
	"zyxcgl2" : $("input[name='zyxcgl2']:checked").val(),
	"zyxcgl3" : $("input[name='zyxcgl3']:checked").val(),
	"zyxcgl4" : $("input[name='zyxcgl4']:checked").val(),
	"zyxcgl5" : $("input[name='zyxcgl5']:checked").val(),
	"zyxcgl6" : $("input[name='zyxcgl6']:checked").val(),
	"zyxcgl7" : $("input[name='zyxcgl7']:checked").val(),
	"zyxcgl8" : $("input[name='zyxcgl8']:checked").val(),
	"zyxcgl9" : $("input[name='zyxcgl9']:checked").val(),
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
  $.post(getRootPath() + "/company/source/ttrade-arder-save", obj,function(result){
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