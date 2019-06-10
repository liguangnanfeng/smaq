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
</head>
<body>
	<!--<nav class="breadcrumb">
	    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
	    <span class="c-gray en">&gt;</span> <span>风险研判与承诺公告</span> 
	    <span class="c-gray en">&gt;</span> <span>特殊流程作业</span> 
	    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="parent.close_tab(function(){})">返回</a>
	  </nav>-->
	
	
	<h3 class="text-c">《受限空间安全作业证》</h3>
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
    	<td style="width: 25%;">受限空间所属单位</td>
    	<td style="width: 25%;"><input id="sxkjssdw" type="text"/></td>
    	<td style="width: 25%;">受限空间名称</td>
    	<td><input id="sxkjmc" type="text"/></td>
    	</tr>
    </table>
	<table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">作业内容</td>
    	<td style="width: 25%;"><input id="zynr" type="text"/></td>
    	<td style="width: 25%;">受限空间内原有介质名称</td>
    	<td><input id="sxkjyyjzmc" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">作业起始时间</td>
    	<td style="width: 25%;"><input id="zysj_s" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
    	<td style="width: 25%;">作业截止时间</td>
    	<td><input id="zysj_e" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">作业单位负责人</td>
    	<td><input id="zydwfzr" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">监护人</td>
    	<td><input id="jhr" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">作业人</td>
    	<td><input id="zyr" type="text"/></td>
    	</tr>
    </table>
     <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">涉及的其他特殊作业</td>
    	<td><input id="sjdqttszy" type="text"/></td>
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
    		<td style="width: 25%;" >分析</td>
    		<td style="width: 11%;" >分析项目</td>
    		<td style="width: 11%;" >有毒有害介质</td>
    		<td style="width: 10%;" >可燃气</td>
    		<td style="width: 10%;" >氧含量</td>
    		<td style="width: 11%;" rowspan="2">时间</td>
    		<td style="width: 11%;" rowspan="2">部位</td>
    		<td style="width: 11%;" rowspan="2">分析人</td>
    	</tr>
    	<tr>
    		<td style="width: 25%;" >分析</td>
    		<td style="width: 11%;" >分析标准</td>
    		<td style="width: 11%;" ><input id="ydyhjzbz" type="text"/></td>
    		<td style="width: 10%;" ><input id="krqbz" type="text"/></td>
    		<td style="width: 10%;" ><input id="yhlbz" type="text"/></td>	
    	</tr>
    	<tr>
    		<td style="width: 25%;" >分析</td>
    		<td style="width: 11%;" rowspan="4">分析数据</td>
    		<td style="width: 11%;" ><input id="ydyhjzsj1" type="text"/></td>
    		<td style="width: 10%;" ><input id="krqsj1" type="text"/></td>
    		<td style="width: 10%;" ><input id="yhlsj1" type="text"/></td>
    		<td style="width: 11%;" ><input id="sjsj1" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
    		<td style="width: 11%;" ><input id="bwsj1" type="text"/></td>
    		<td style="width: 11%;" ><input id="fxrsj1" type="text"/></td>
    	</tr>
    	<tr>
    		<td style="width: 25%;" >分析</td>
    		<td style="width: 11%;" rowspan="4">分析数据</td>
    		<td style="width: 11%;" ><input id="ydyhjzsj2" type="text"/></td>
    		<td style="width: 10%;" ><input id="krqsj2" type="text"/></td>
    		<td style="width: 10%;" ><input id="yhlsj2" type="text"/></td>
    		<td style="width: 11%;" ><input id="sjsj2" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
    		<td style="width: 11%;" ><input id="bwsj2" type="text"/></td>
    		<td style="width: 11%;" ><input id="fxrsj2" type="text"/></td>
    	</tr>
    	<tr>
    		<td style="width: 25%;" >分析</td>
    		<td style="width: 11%;" rowspan="4">分析数据</td>
    		<td style="width: 11%;" ><input id="ydyhjzsj3" type="text"/></td>
    		<td style="width: 10%;" ><input id="krqsj3" type="text"/></td>
    		<td style="width: 10%;" ><input id="yhlsj3" type="text"/></td>
    		<td style="width: 11%;" ><input id="sjsj3" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
    		<td style="width: 11%;" ><input id="bwsj3" type="text"/></td>
    		<td style="width: 11%;" ><input id="fxrsj3" type="text"/></td>
    	</tr>
    	<tr>
    		<td style="width: 25%;" >分析</td>
    		<td style="width: 11%;" rowspan="4">分析数据</td>
    		<td style="width: 11%;" ><input id="ydyhjzsj4" type="text"/></td>
    		<td style="width: 10%;" ><input id="krqsj4" type="text"/></td>
    		<td style="width: 10%;" ><input id="yhlsj4" type="text"/></td>
    		<td style="width: 11%;" ><input id="sjsj4" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
    		<td style="width: 11%;" ><input id="bwsj4" type="text"/></td>
    		<td style="width: 11%;" ><input id="fxrsj4" type="text"/></td>
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
    	<td style="width: 75%;">对进入受限空间危险性进行分析</td>
    	<td><input id="qrr1" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">2</td>
    	<td style="width: 75%;">所有与受限空间有联系的阀门、管线加盲板隔离，列出盲板清单，落实抽堵盲板责任人</td>
    	<td><input id="qrr2" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">3</td>
    	<td style="width: 75%;">设备经过置换、吹扫、蒸煮</td>
    	<td><input id="qrr3" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr rowspan="2">
    	<td style="width: 10%;">4</td>
    	<td style="width: 75%;">设备打开通风孔进行自然通风，温度适宜人员作业；必要时采用强制通风或佩戴空气呼吸器，不能用通氧气或富氧空气的方法补充氧</td>
    	<td><input id="qrr4" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr rowspan="2">
    	<td style="width: 10%;">5</td>
    	<td style="width: 75%;">相关设备进行处理，带搅拌机的设备已切断电源，电源开关处加锁或挂“禁止合闸”标志牌，设专人监护</td>
    	<td><input id="qrr5" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">6</td>
    	<td style="width: 75%;">检查受限空间内部已具备作业条件，清罐时（<input id="sfcy1" style="width:15%;" type="text" placeholder="无需用/已采用" />）防爆工具</td>
    	<td><input id="qrr6" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">7</td>
    	<td style="width: 75%;">检查受限空间进出口通道，无阻碍人员进出的障碍物</td>
    	<td><input id="qrr7" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">8</td>
    	<td style="width: 75%;">分析盛装过可燃有毒液体、气体的受限空间内的可燃、有毒有害气体含量</td>
    	<td><input id="qrr8" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">9</td>
    	<td style="width: 75%;">作业人员清楚受限空间内存在的其他危险因素，如内部附件、集渣坑等</td>
    	<td><input id="qrr9" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">10</td>
    	<td style="width: 75%;">作业监护措施：消防器材（<input id="xfqc1" style="width:10%;" type="text"/>）、救生绳（<input id="jss1" style="width:10%;" type="text"/>）、气防装备（<input id="qfzb1" style="width:10%;" type="text"/>）</td>
    	<td><input id="qrr10" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">11</td>
    	<td style="width: 75%;">其他安全措施：<input id="qtaqcs" style="width:70%;" type="text"/>编制人：<input id="bzr" style="width:10%;" type="text"/></td>
    	<td><input id="qrr11" type="text"/></td>
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
    	<td>审批单位意见：<input id="spdwyj" style="width:60%;" type="text"/>签字：<input id="spdwqz" style="width:10%;" type="text"/>日期：<input id="spdwqzsj" style="width:10%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td>完工验收：<input id="wgys" style="width:60%;" type="text"/>签字：<input id="wgysqz" style="width:10%;" type="text"/>日期：<input id="wgysqzsj" style="width:10%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/></td>
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
if("${action}" == "read"){
	$("input").attr("disabled","disabled");
	$("input").css("background-color","white");
	
	$("#sqdw").val("${ls.sqdw }");
    $("#sqr").val("${ls.sqr }");
    $("#zyzbh").val("${ls.zyzbh}");
    
    $("#sxkjssdw").val("${ls.sxkjssdw}");
    $("#sxkjmc").val("${ls.sxkjmc}");
    $("#zynr").val("${ls.zynr}");
    $("#sxkjyyjzmc").val("${ls.sxkjyyjzmc}");
    $("#zysj_s").val("<fmt:formatDate value='${ls.zysjS}' pattern='yyyy-MM-dd HH:mm'/>");
    $("#zysj_e").val("<fmt:formatDate value='${ls.zysjE}' pattern='yyyy-MM-dd HH:mm'/>");
    
    $("#zydwfzr").val("${ls.zydwfzr}");
    $("#jhr").val("${ls.jhr}");
    $("#zyr").val("${ls.zyr}");
    $("#sjdqttszy").val("${ls.sjdqttszy}");
    $("#whbs").val("${ls.sjdqttszy}");
    $("#ydyhjzbz").val("${ls.yhydwzbz}");
    $("#krqbz").val("${ls.yhydwzbz}");
    $("#yhlbz").val("${ls.yhlbz}");
    
    $("#ydyhjzsj1").val("${ls.ydyhjzsj1}");
    $("#krqsj1").val("${ls.krqsj1}");
    $("#yhlsj1").val("${ls.yhlsj1}");
    $("#sjsj1").val("<fmt:formatDate value='${ls.sjsj1}' pattern='yyyy-MM-dd HH:mm'/>");
    $("#bwsj1").val("${ls.bwsj1}");
    $("#fxrsj1").val("${ls.bwsj1}");
    $("#ydyhjzsj2").val("${ls.ydyhjzsj2}");
    $("#krqsj2").val("${ls.krqsj2}");
    $("#yhlsj2").val("${ls.yhlsj2}");
    $("#sjsj2").val("<fmt:formatDate value='${ls.sjsj2}' pattern='yyyy-MM-dd HH:mm'/>");
    $("#bwsj2").val("${ls.bwsj2}");
    $("#fxrsj2").val("${ls.fxrsj2}");
    $("#ydyhjzsj3").val("${ls.ydyhjzsj3}");
    $("#krqsj3").val("${ls.krqsj3}");
    $("#yhlsj3").val("${ls.yhlsj3}");
    $("#sjsj3").val("<fmt:formatDate value='${ls.sjsj3}' pattern='yyyy-MM-dd HH:mm'/>");
    $("#bwsj3").val("${ls.bwsj3}");
    $("#fxrsj3").val("${ls.fxrsj3}");
    $("#ydyhjzsj4").val("${ls.ydyhjzsj4}");
    $("#krqsj4").val("${ls.krqsj4}");
    $("#yhlsj4").val("${ls.yhlsj4}");
    $("#sjsj4").val("<fmt:formatDate value='${ls.sjsj4}' pattern='yyyy-MM-dd HH:mm'/>");
    $("#bwsj4").val("${ls.bwsj4}");
    $("#fxrsj4").val("${ls.fxrsj4}");
    
    $("#qrr1").val("${ls.qrr1}");
    $("#qrr2").val("${ls.qrr2}");
    $("#qrr3").val("${ls.qrr3}");
    $("#qrr4").val("${ls.qrr4}");
    $("#qrr5").val("${ls.qrr5}");
    $("#qrr6").val("${ls.qrr6}");
    $("#qrr7").val("${ls.qrr7}");
    $("#qrr8").val("${ls.qrr8}");
    $("#qrr9").val("${ls.qrr9}");
    $("#qrr10").val("${ls.qrr10}");
    $("#qrr11").val("${ls.qrr11}");
    
    $("#sfcy1").val("${ls.sfcy1}");
    $("#xfqc1").val("${ls.xfqc1}");
    $("#jss1").val("${ls.jss1}");
    $("#qfzb1").val("${ls.qfzb1}");
    $("#qtaqcs").val("${ls.qtaqcs}");
    $("#bzr").val("${ls.bzr}");
    
    $("#ssaqjyr1").val("${ls.ssaqjyr1}");
    $("#ssaqjyr2").val("${ls.ssaqjyr2}");
    $("#ssaqjyr3").val("${ls.ssaqjyr3}");
    
    $("#sqdwyj").val("${ls.sqdwyj}");
    $("#sqdwqz").val("${ls.sqdwqz}");
    $("#sqdwqzsj").val("<fmt:formatDate value='${ls.sqdwqzsj}' pattern='yyyy-MM-dd'/>");
    
    $("#spdwyj").val("${ls.spdwyj}");
    $("#spdwqz").val("${ls.spdwqz}");
    $("#spdwqzsj").val("<fmt:formatDate value='${ls.spdwqzsj}' pattern='yyyy-MM-dd'/>");
    
    $("#wgys").val("${ls.wgysyj}");
    $("#wgysqz").val("${ls.wgysqz}");
    $("#wgysqzsj").val("<fmt:formatDate value='${ls.wgysqzsj}' pattern='yyyy-MM-dd'/>");
    
}

function save() {
  var obj = {
      "sqdw" : $("#sqdw").val(),
      "sqr" : $("#sqr").val(),
      "zyzbh" : $("#zyzbh").val(),
      "sxkjssdw":$("#sxkjssdw").val(),
      "sxkjmc":$("#sxkjmc").val(),
      "zynr":$("#zynr").val(),
      "sxkjyyjzmc":$("#sxkjyyjzmc").val(),
      "zysjS":$("#zysj_s").val(),
      "zysjE":$("#zysj_e").val(),
      
      "zydwfzr":$("#zydwfzr").val(),
      "jhr":$("#jhr").val(),
      "zyr":$("#zyr").val(),
      "sjdqttszy":$("#sjdqttszy").val(),
      "whbs":$("#whbs").val(),
      
      "yhydwzbz":$("#ydyhjzbz").val(),
      "krqbz":$("#krqbz").val(),
      "yhlbz":$("#yhlbz").val(),
      "ydyhjzsj1":$("#ydyhjzsj1").val(),
      "krqsj1":$("#krqsj1").val(),
      "yhlsj1":$("#yhlsj1").val(),
      "sjsj1":$("#sjsj1").val(),
      "bwsj1":$("#bwsj1").val(),
      "fxrsj1":$("#fxrsj1").val(),
      "ydyhjzsj2":$("#ydyhjzsj2").val(),
      "krqsj2":$("#krqsj2").val(),
      "yhlsj2":$("#yhlsj2").val(),
      "sjsj2":$("#sjsj2").val(),
      "bwsj2":$("#bwsj2").val(),
      "fxrsj2":$("#fxrsj2").val(),
      "ydyhjzsj3":$("#ydyhjzsj3").val(),
      "krqsj3":$("#krqsj3").val(),
      "yhlsj3":$("#yhlsj3").val(),
      "sjsj3":$("#sjsj3").val(),
      "bwsj3":$("#bwsj3").val(),
      "fxrsj3":$("#fxrsj3").val(),
      "ydyhjzsj4":$("#ydyhjzsj4").val(),
      "krqsj4":$("#krqsj4").val(),
      "yhlsj4":$("#yhlsj4").val(),
      "sjsj4":$("#sjsj4").val(),
      "bwsj4":$("#bwsj4").val(),
      "fxrsj4":$("#fxrsj4").val(),
      
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
      "qrr11" : $("#qrr11").val(),
      
      "sfcy1":$("#sfcy1").val(),
      "xfqc1":$("#xfqc1").val(),
      "jss1":$("#jss1").val(),
      "qfzb1":$("#qfzb1").val(),
      "qtaqcs" : $("#qtaqcs").val(),
      "bzr" : $("#bzr").val(),
      
      "ssaqjyr1" : $("#ssaqjyr1").val(),
      "ssaqjyr2" : $("#ssaqjyr2").val(),
      "ssaqjyr3" : $("#ssaqjyr3").val(),
      
      "sqdwyj" : $("#sqdwyj").val(),
      "sqdwqz" : $("#sqdwqz").val(),
      "sqdwqzsj" : $("#sqdwqzsj").val(), 
      
      "spdwyj" : $("#spdwyj").val(),
      "spdwqz" : $("#spdwqz").val(),
      "spdwqzsj" : $("#spdwqzsj").val(),
      
      "wgysyj" : $("#wgys").val(),
      "wgysqz" : $("#wgysqz").val(),
      "wgysqzsj" : $("#wgysqzsj").val(),
  }
  //alert($("#zysj_s").val());
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
  $.post(getRootPath() + "/company/study/limitspace-save", obj,function(result){
    layer.close(i);
    if(result.status == '1') {
    	layer.alert(result.map.message);
    } else {
    	layer.alert("保存成功！");
        parent.location.reload();
    }
  })
}
</script>
</html>