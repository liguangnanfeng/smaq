<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<%@ include file="/WEB-INF/inc/back-header.inc"%>
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style type="text/css">
body .dis-ib{margin-right:15px;}
.wid1{width:30%}
.wid2{width:30%}
.wid3{width:30%}
.wid4{width:10%}
.wid5{width:70%}
.btn-cz{margin:auto;background:#eee;border:1px solid #ddd;padding:0 5px;height:20px;line-height:20px;border-radius:5px;margin-left:15px}
.btn-cz:hover{border:1px solid #06c;color:#06c;text-decoration: none;}
body .check-box label{margin-left:5px;}
.one_danger .list_odan{margin-left:20px}
.two_danger .list_twdan{margin-left:45px;}
.one_danger .Hui-iconfont{margin-top:2px}
.one_danger,.list_danger{display:inline-block}
.one_danger,.one_danger .list_odan,.two_danger,.two_danger .list_twdan,.list_danger{float:none;}
.check-box{width:30%;margin-bottom:15px;}
.btn-group .btn{height: 34px;line-height: 34px;padding: 0 25px;}
.one-danrisk{float:left;width:100%;border-bottom:1px solid #ddd}
.one-danrisk p{float:left;width:80%;padding: 8px}
.table tbody tr td .one-danrisk a{float:right;margin-top:8px;margin-right:8px;}
</style>
<style type="text/css">
	table,th,td{
		border:1px solid #E0E0E0;
		font-size:15px;
		text-align:center;
		vertical-align:middle;
	}
	td{ 
		height:30px;
	}
	td input{
		font-size:15px;
		border:none;
		width:100%;
		text-align:center;
		vertical-align:middle;
		height:30px;
	}
</style>
<script type="text/javascript">
/* 弹窗工具添加 */
function edit(flag) {
	switch(flag){
		case 1:
			show_dialog(" ", "/company/study/firework");
			break;
		
		default:
			return;
	}
  
}

</script>
</head>
<body>
	<!--<nav class="breadcrumb">
	    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
	    <span class="c-gray en">&gt;</span> <span>风险研判与承诺公告</span> 
	    <span class="c-gray en">&gt;</span> <span>特殊流程作业</span> 
	    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="parent.close_tab(function(){})">返回</a>
	  </nav>-->
	
	
	<h3 class="text-c">《动火作业证》</h3>
    <div class="mt-20">
    	
    	<!--<table style="width:50%;" cellpadding="0" cellspacing="0"> table-sort tab-ndan table-bg   table   table-border table-bordered-->
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 15%;">申请单位</td>
    	<td style="width: 15%;"><input id="sqdw" type="text"/></td>
    	<td style="width: 15%;">申请人</td>
    	<td style="width: 15%;"><input id="sqr" type="text"/></td>
    	<td style="width: 15%;">作业证编号</td>
    	<td><input id="zyzbh" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">动火作业级别</td>
    	<td style="width: 25%;"><input id="dhzyjb" type="text"/></td>
    	<td style="width: 25%;">动火方式</td>
    	<td><input id="dhfs" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 15%;">动火地点</td>
    	<td><input id="dhdd" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">动火起始时间</td>
    	<td style="width: 25%;"><input id="dhsj_s" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
    	<td style="width: 25%;">动火截止时间</td>
    	<td><input id="dhsj_e" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">动火作业负责人</td>
    	<td style="width: 25%;"><input id="dhzyfzr" type="text"/></td>
    	<td style="width: 25%;">动火人</td>
    	<td><input id="dhr" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">动火分析时间</td>
    	<td style="width: 25%;"><input id="dhfxsj1" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
    	<td style="width: 25%;"><input id="dhfxsj2" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
    	<td><input id="dhfxsj3" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">分析点名称</td>
    	<td style="width: 25%;"><input id="fxdmc1" type="text"/></td>
    	<td style="width: 25%;"><input id="fxdmc2" type="text"/></td>
    	<td><input id="fxdmc3" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">分析数据</td>
    	<td style="width: 25%;"><input id="fxsj1" type="text"/></td>
    	<td style="width: 25%;"><input id="fxsj2" type="text"/></td>
    	<td><input id="fxsj3" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">分析人</td>
    	<td style="width: 25%;"><input id="fxr1" type="text"/></td>
    	<td style="width: 25%;"><input id="fxr2" type="text"/></td>
    	<td><input id="fxr3" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">涉及的其它特殊作业</td>
    	<td style="width: 25%;"><input id="qttszy1" type="text"/></td>
    	<td style="width: 25%;"><input id="qttszy2" type="text"/></td>
    	<td><input id="qttszy3" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">危害辨识</td>
    	<td><input id="whbs" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">序号</td>
    	<td style="width: 75%;">安全措施</td>
    	<td>确认人</td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">1</td>
    	<td style="width: 75%;">动火设备内部构件清理干净，蒸汽吹扫或水洗合格，达到用火条件</td>
    	<td><input id="qrr1" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">2</td>
    	<td style="width: 75%;">断开与动火设备相连接的所有管线，加盲板（<input id="mbsl" style="width:10%;" type="text"/>）块</td>
    	<td><input id="qrr2" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">3</td>
    	<td style="width: 75%;">动火点周围的下水井、地漏、地沟、电缆沟等已清除易燃物，并已采取覆盖、铺沙、水封等手段进行隔离</td>
    	<td><input id="qrr3" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">4</td>
    	<td style="width: 75%;">罐区内动火点同一围堰和防火间距内的油罐不同时进行脱水作业</td>
    	<td><input id="qrr4" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">5</td>
    	<td style="width: 75%;">高处作业已采取防火花飞溅措施</td>
    	<td><input id="qrr5" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">6</td>
    	<td style="width: 75%;">动火点周围易燃物已清除</td>
    	<td><input id="qrr6" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">7</td>
    	<td style="width: 75%;">电焊回路线已接在焊件上，把线未穿过下水井或其他设备搭接</td>
    	<td><input id="qrr7" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">8</td>
    	<td style="width: 75%;">乙炔气瓶（直立放置）、氧气瓶与火源间的距离大于10m</td>
    	<td><input id="qrr8" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">9</td>
    	<td style="width: 75%;">现场配备消防蒸汽带（<input id="zqdsl" style="width:10%;" type="text"/>）根，灭火器（<input id="mhqsl" style="width:10%;" type="text"/>）台，铁锹（<input id="tqsl" style="width:10%;" type="text"/>）把，石棉布（<input id="smbsl" style="width:10%;" type="text"/>）块</td>
    	<td><input id="qrr9" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">10</td>
    	<td style="width: 75%;">其他安全措施：<input id="qtaqcs" style="width:70%;" type="text"/>编制人：<input id="bzr" style="width:10%;" type="text"/></td>
    	<td><input id="qrr10" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 15%;">生产单位负责人</td>
    	<td style="width: 15%;"><input id="scdwfzr" type="text"/></td>
    	<td style="width: 15%;">监火人</td>
    	<td style="width: 15%;"><input id="jhr" type="text"/></td>
    	<td style="width: 15%;">动火初审人</td>
    	<td><input id="dhcsr" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">实施安全教育人</td>
    	<td style="width: 25%;"><input id="ssaqjyr1" type="text"/></td>
    	<td style="width: 25%;"><input id="ssaqjyr2" type="text"/></td>
    	<td><input id="ssaqjyr3" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td>申请单位意见：<input id="sqdwyj" style="width:60%;" type="text"/>签字：<input id="sqdwqz" style="width:10%;" type="text"/>日期：<input id="sqdwqzsj" style="width:10%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td>安全管理部门意见：<input id="aqglbmyj" style="width:60%;" type="text"/>签字：<input id="aqglbmqz" style="width:10%;" type="text"/>日期：<input id="aqglbmqzsj" style="width:10%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td>动火审批人意见：<input id="dhspryj" style="width:60%;" type="text"/>签字：<input id="dhsprqz" style="width:10%;" type="text"/>日期：<input id="dhsprqzsj" style="width:10%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td>动火前，岗位当班班长验票：<input id="dbbzyj" style="width:55%;" type="text"/>签字：<input id="dbbzqz" style="width:10%;" type="text"/>日期：<input id="dbbzqzsj" style="width:10%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td>完工验收：<input id="wgysyj" style="width:60%;" type="text"/>签字：<input id="wgysqz" style="width:10%;" type="text"/>日期：<input id="wgysqzsj" style="width:10%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/></td>
    	</tr>
    </table>
  </div>
  
  <div class="mt-20 f-l mb-40" style="width:100%;text-align:center;">
  	<c:if test="${action != 'read'}">
      <button onClick="save()" class="btn btn-primary radius mt-20" type="button" style="padding: 0 70px;">
        <i class="Hui-iconfont mr-10">&#xe632;</i>保存
      </button>
    </c:if>
  </div>
  

  
</body>
<script type="text/javascript" src="/js/fxgk/tbl-rowspan-reset.js"></script>
<script type="text/javascript">
//var id = '';
//function edit(id, obj) {
//var p = $(obj).parent();
//window.id = id;
//$("#level1").val(p.find("textarea[name='level1']").val());
//$("#level2").val(p.find("textarea[name='level2']").val());
//$("#level3").val(p.find("textarea[name='level3']").val());
//$("#win-add2").modal("show");
//}
//
//function reload_() {
//layer.closeAll();
//location.reload();
//}
//alert(11);
//alert(JSON.stringify("${fw}"));
//alert(11);
if("${action}" == "read"){
	
	$("input").attr("disabled","disabled");
	$("input").css("background-color","white");
	
	$("#sqdw").val("${fw.sqdw }");
    $("#sqr").val("${fw.sqr }");
    $("#zyzbh").val("${fw.zyzbh}");
    $("#dhzyjb").val("${fw.dhzyjb}");
    $("#dhfs").val("${fw.dhfs}");
    $("#dhdd").val("${fw.dhdd}");
    $("#dhsj_s").val("<fmt:formatDate value='${fw.dhsjS}' pattern='yyyy-MM-dd HH:mm'/>");
    $("#dhsj_e").val("<fmt:formatDate value='${fw.dhsjE}' pattern='yyyy-MM-dd HH:mm'/>");
    $("#dhzyfzr").val("${fw.dhzyfzr}");
    $("#dhr").val("${fw.dhr}");
    $("#dhfxsj1").val("<fmt:formatDate value='${fw.dhfxsj1}' pattern='yyyy-MM-dd HH:mm'/>");
    $("#dhfxsj2").val("<fmt:formatDate value='${fw.dhfxsj2}' pattern='yyyy-MM-dd HH:mm'/>");
    $("#dhfxsj3").val("<fmt:formatDate value='${fw.dhfxsj3}' pattern='yyyy-MM-dd HH:mm'/>");
    $("#fxdmc1").val("${fw.fxdmc1}");
    $("#fxdmc2").val("${fw.fxdmc2}");
    $("#fxdmc3").val("${fw.fxdmc3}");
    $("#fxsj1").val("${fw.fxsj1}");
    $("#fxsj2").val("${fw.fxsj2}");
    $("#fxsj3").val("${fw.fxsj3}");
    $("#fxr1").val("${fw.fxr1}");
    $("#fxr2").val("${fw.fxr2}");
    $("#fxr3").val("${fw.fxr3}");
    $("#qttszy1").val("${fw.qttszy1}");
    $("#qttszy2").val("${fw.qttszy2}");
    $("#qttszy3").val("${fw.qttszy3}");
    $("#whbs").val("${fw.whbs}");
    $("#qrr1").val("${fw.qrr1}");
    $("#qrr2").val("${fw.qrr2}");
    $("#qrr3").val("${fw.qrr3}");
    $("#qrr4").val("${fw.qrr4}");
    $("#qrr5").val("${fw.qrr5}");
    $("#qrr6").val("${fw.qrr6}");
    $("#qrr7").val("${fw.qrr7}");
    $("#qrr8").val("${fw.qrr8}");
    $("#qrr9").val("${fw.qrr9}");
    $("#qrr10").val("${fw.qrr10}");
    $("#mbsl").val("${fw.mbsl}");
    $("#zqdsl").val("${fw.zqdsl}");
    $("#mhqsl").val("${fw.mhqsl}");
    $("#tqsl").val("${fw.tqsl}");
    $("#smbsl").val("${fw.smbsl}");
    $("#qtaqcs").val("${fw.qtaqcs}");
    $("#bzr").val("${fw.bzr}");
    $("#scdwfzr").val("${fw.scdwfzr}");
    $("#jhr").val("${fw.jhr}");
    $("#dhcsr").val("${fw.dhcsr}");
    $("#ssaqjyr1").val("${fw.ssaqjyr1}");
    $("#ssaqjyr2").val("${fw.ssaqjyr2}");
    $("#ssaqjyr3").val("${fw.ssaqjyr3}");
    $("#sqdwyj").val("${fw.sqdwyj}");
    $("#sqdwqz").val("${fw.sqdwqz}");
    $("#sqdwqzsj").val("<fmt:formatDate value='${fw.sqdwqzsj}' pattern='yyyy-MM-dd'/>");
    $("#aqglbmyj").val("${fw.aqglbmyj}");
    $("#aqglbmqz").val("${fw.aqglbmqz}");
    $("#aqglbmqzsj").val("<fmt:formatDate value='${fw.aqglbmqzsj}' pattern='yyyy-MM-dd'/>");
    $("#dhspryj").val("${fw.dhspryj}");
    $("#dhsprqz").val("${fw.dhsprqz}");
    $("#dhsprqzsj").val("<fmt:formatDate value='${fw.dhsprqzsj}' pattern='yyyy-MM-dd'/>");
    $("#dbbzyj").val("${fw.dbbzyj}");
    $("#dbbzqz").val("${fw.dbbzqz}");
    $("#dbbzqzsj").val("<fmt:formatDate value='${fw.dbbzqzsj}' pattern='yyyy-MM-dd'/>");
    $("#wgysyj").val("${fw.wgysyj}");
    $("#wgysqz").val("${fw.wgysqz}");
    $("#wgysqzsj").val("<fmt:formatDate value='${fw.wgysqzsj}' pattern='yyyy-MM-dd'/>");
}

function save() {
  var obj = {
      "sqdw" : $("#sqdw").val(),
      "sqr" : $("#sqr").val(),
      "zyzbh" : $("#zyzbh").val(),
      "dhzyjb" : $("#dhzyjb").val(),
      "dhfs" : $("#dhfs").val(),
      "dhdd" : $("#dhdd").val(),
      "dhsjS" : $("#dhsj_s").val(),
      "dhsjE" : $("#dhsj_e").val(),
      "dhzyfzr" : $("#dhzyfzr").val(),
      "dhr" : $("#dhr").val(),
      "dhfxsj1" : $("#dhfxsj1").val(),
      "dhfxsj2" : $("#dhfxsj2").val(),
      "dhfxsj3" : $("#dhfxsj3").val(),
      "fxdmc1" : $("#fxdmc1").val(),
      "fxdmc2" : $("#fxdmc2").val(),
      "fxdmc3" : $("#fxdmc3").val(),
      "fxsj1" : $("#fxsj1").val(),
      "fxsj2" : $("#fxsj2").val(),
      "fxsj3" : $("#fxsj3").val(),
      "fxr1" : $("#fxr1").val(),
      "fxr2" : $("#fxr2").val(),
      "fxr3" : $("#fxr3").val(),
      "qttszy1" : $("#qttszy1").val(),
      "qttszy2" : $("#qttszy2").val(),
      "qttszy3" : $("#qttszy3").val(),
      "whbs" : $("#whbs").val(),
      "qrr1" : $("#qrr1").val(),
      "qrr2" : $("#qrr2").val(),
      "qrr3" : $("#qrr3").val(),
      "qrr4" : $("#qrr4").val(),
      "qrr5" : $("#qrr5").val(),
      "qrr6" : $("#qrr6").val(),
      "qrr7" : $("#qrr7").val(),
      "qrr8" : $("#qrr8").val(),
      "qrr9" : $("#qrr9").val(),
      "qrr10" : $("#qrr10").val(),
      "mbsl" : $("#mbsl").val(),
      "zqdsl" : $("#zqdsl").val(),
      "mhqsl" : $("#mhqsl").val(),
      "tqsl" : $("#tqsl").val(),
      "smbsl" : $("#smbsl").val(),
      "qtaqcs" : $("#qtaqcs").val(),
      "bzr" : $("#bzr").val(),
      "scdwfzr" : $("#scdwfzr").val(),
      "jhr" : $("#jhr").val(),
      "dhcsr" : $("#dhcsr").val(),
      "ssaqjyr1" : $("#ssaqjyr1").val(),
      "ssaqjyr2" : $("#ssaqjyr2").val(),
      "ssaqjyr3" : $("#ssaqjyr3").val(),
      "sqdwyj" : $("#sqdwyj").val(),
      "sqdwqz" : $("#sqdwqz").val(),
      "sqdwqzsj" : $("#sqdwqzsj").val(),
      "aqglbmyj" : $("#aqglbmyj").val(),
      "aqglbmqz" : $("#aqglbmqz").val(),
      "aqglbmqzsj" : $("#aqglbmqzsj").val(),
      "dhspryj" : $("#dhspryj").val(),
      "dhsprqz" : $("#dhsprqz").val(),
      "dhsprqzsj" : $("#dhsprqzsj").val(),
      "dbbzyj" : $("#dbbzyj").val(),
      "dbbzqz" : $("#dbbzqz").val(),
      "dbbzqzsj" : $("#dbbzqzsj").val(),
      "wgysyj" : $("#wgysyj").val(),
      "wgysqz" : $("#wgysqz").val(),
      "wgysqzsj" : $("#wgysqzsj").val(),
  }
  if(obj.sqdw == '') {
    $("#sqdw").focus();
    alert("请填写申请单位！");
    return false;
  }
  if(obj.sqr == '') {
    $("#sqr").focus();
    alert("请填写申请人！");
    return false;
  }
  //alert($("#sqdwqzsj").val());
  var i = layer.load();
  $.post(getRootPath() + "/company/study/firework-save", obj,function(result){
    layer.close(i);
    if(result.status == '1') {
    	layer.alert(result.map.message);
    } else {
    	layer.alert("保存成功！");
        parent.location.reload();
    }
  })
}

//function del(id) {
//layer.confirm("确定删除该记录?", function(r) {
//  $.post(getRootPath() + "/company/safety-system/aCompanyManual-save", {
//    id : id,
//    del : 1
//  }, function(result) {
//    layer.close(r);
//    location.reload();
//  })
//})
//}
</script>
</html>