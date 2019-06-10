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
	
	
	<h3 class="text-c">《高处安全作业证》</h3>
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
    	<td style="width: 25%;">作业起始时间</td>
    	<td style="width: 25%;"><input id="zysj_s" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
    	<td style="width: 25%;">作业截止时间</td>
    	<td><input id="zysj_e" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">作业地点</td>
    	<td><input id="zydd" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">作业内容</td>
    	<td><input id="zynr" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">作业高度</td>
    	<td style="width: 25%;"><input id="zygd" type="text"/></td>
    	<td style="width: 25%;">作业类别</td>
    	<td><input id="zylb" type="text"/></td>
    	</tr>
    </table>
	<table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">作业单位</td>
    	<td style="width: 25%;"><input id="zydw" type="text"/></td>
    	<td style="width: 25%;">监护人</td>
    	<td><input id="jhr" type="text"/></td>
    	</tr>
    </table>
	<table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">作业人</td>
    	<td style="width: 25%;"><input id="zyr" type="text"/></td>
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
    	<td style="width: 10%;">序号</td>
    	<td style="width: 75%;">安全措施</td>
    	<td>确认人</td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">1</td>
    	<td style="width: 75%;">作业人员身体条件符合要求</td>
    	<td><input id="qrr1" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">2</td>
    	<td style="width: 75%;">作业人员着装符合工作要求</td>
    	<td><input id="qrr2" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">3</td>
    	<td style="width: 75%;">作业人员佩戴合格的安全帽</td>
    	<td><input id="qrr3" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr rowspan="2">
    	<td style="width: 10%;">4</td>
    	<td style="width: 75%;">作业人员佩戴安全带、安全带高挂低用</td>
    	<td><input id="qrr4" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr rowspan="2">
    	<td style="width: 10%;">5</td>
    	<td style="width: 75%;">作业人员携带有工具袋及安全绳</td>
    	<td><input id="qrr5" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">6</td>
    	<td style="width: 75%;">作业人员佩戴：A.过滤式防毒面具或口罩，B.空气呼吸器（<input id="xzab6" style="width:6%;" type="text" placeholder="A/B" />）</td>
    	<td><input id="qrr6" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">7</td>
    	<td style="width: 75%;">现场搭设的脚手架、防护网、围栏符合安全规定</td>
    	<td><input id="qrr7" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">8</td>
    	<td style="width: 75%;">垂直分层作业中间有隔离设施</td>
    	<td><input id="qrr8" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">9</td>
    	<td style="width: 75%;">梯子、绳子符合安全规定</td>
    	<td><input id="qrr9" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">10</td>
    	<td style="width: 75%;">石棉瓦等轻型棚的承重梁、柱能承重负荷的要求</td>
    	<td><input id="qrr10" type="text"/></td>
    	</tr>
    </table>
   	<table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">11</td>
    	<td style="width: 75%;">作业人员在石棉瓦等不承重物作业所搭设的承重板稳定牢固</td>
    	<td><input id="qrr11" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">12</td>
    	<td style="width: 75%;">采光、夜间作业照明符合作业要求，（<input id="sfcy12" style="width:30%;" type="text" placeholder="需采用并已采用/无需采用" />）防爆灯</td>
    	<td><input id="qrr12" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">13</td>
    	<td style="width: 75%;">30m以上高处作业配备通讯、联络工具</td>
    	<td><input id="qrr13" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">14</td>
    	<td style="width: 75%;">其他安全措施：<input id="qtaqcs" style="width:70%;" type="text"/>编制人：<input id="bzr" style="width:10%;" type="text"/></td>
    	<td><input id="qrr14" type="text"/></td>
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
    	<td>生产单位作业负责人意见：<input id="scdwzyfzr" style="width:56%;" type="text"/>签字：<input id="scdwzyfzrqz" style="width:10%;" type="text"/>日期：<input id="scdwzyfzrqzsj" style="width:10%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td>作业单位负责人意见：<input id="zydwfzr" style="width:60%;" type="text"/>签字：<input id="zydwfzrqz" style="width:10%;" type="text"/>日期：<input id="zydwfzrqzsj" style="width:10%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td>审核部门意见：<input id="shbm" style="width:60%;" type="text"/>签字：<input id="shbmqz" style="width:10%;" type="text"/>日期：<input id="shbmqzsj" style="width:10%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td>审批部门意见：<input id="spbm" style="width:60%;" type="text"/>签字：<input id="spbmqz" style="width:10%;" type="text"/>日期：<input id="spbmqzsj" style="width:10%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/></td>
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
	
	$("#sqdw").val("${hsw1.sqdw }");
    $("#sqr").val("${hsw1.sqr }");
    $("#zyzbh").val("${hsw1.zyzbh}");
    
    $("#zysj_s").val("<fmt:formatDate value='${hsw1.zysjS}' pattern='yyyy-MM-dd HH:mm'/>");
    $("#zysj_e").val("<fmt:formatDate value='${hsw1.zysjE}' pattern='yyyy-MM-dd HH:mm'/>");
    $("#zydd").val("${hsw1.zydd}");
    $("#zynr").val("${hsw1.zynr}");
    $("#zygd").val("${hsw1.zygd}");
    $("#zylb").val("${hsw1.zylb}");
    $("#zydw").val("${hsw1.zydw}");
    $("#jhr").val("${hsw1.jhr}");
    $("#zyr").val("${hsw1.zyr}");
    $("#sjdqttszy").val("${hsw1.sjdqttszy}");
    $("#whbs").val("${hsw1.whbs}");

    $("#qrr1").val("${hsw1.qrr1}");
    $("#qrr2").val("${hsw1.qrr2}");
    $("#qrr3").val("${hsw1.qrr3}");
    $("#qrr4").val("${hsw1.qrr4}");
    $("#qrr5").val("${hsw1.qrr5}");
    $("#qrr6").val("${hsw1.qrr6}");
    $("#qrr7").val("${hsw1.qrr7}");
    $("#qrr8").val("${hsw1.qrr8}");
    $("#qrr9").val("${hsw1.qrr9}");
    $("#qrr10").val("${hsw1.qrr10}");
    $("#qrr11").val("${hsw1.qrr11}");
    $("#qrr12").val("${hsw1.qrr12}");
    $("#qrr13").val("${hsw1.qrr13}");
    $("#qrr14").val("${hsw1.qrr14}");   
    $("#xzab6").val("${hsw1.xzab6}");
    $("#sfcy12").val("${hsw1.sfcy12}"); 
    $("#qtaqcs").val("${hsw1.qtaqcs}");
    $("#bzr").val("${hsw1.bzr}");
    
    $("#ssaqjyr1").val("${hsw1.ssaqjyr1}");
    $("#ssaqjyr2").val("${hsw1.ssaqjyr2}");
    $("#ssaqjyr3").val("${hsw1.ssaqjyr3}");
    
    $("#scdwzyfzr").val("${hsw1.scdwzyfzr}");  
    $("#scdwzyfzrqz").val("${hsw1.scdwzyfzrqz}");  
    $("#scdwzyfzrqzsj").val("<fmt:formatDate value='${hsw1.scdwzyfzrqzsj}' pattern='yyyy-MM-dd'/>"); 
    
    $("#zydwfzr").val("${hsw1.zydwfzr}");
    $("#zydwfzrqz").val("${hsw1.zydwfzrqz}"); 
    $("#zydwfzrqzsj").val("<fmt:formatDate value='${hsw1.zydwfzrqzsj}' pattern='yyyy-MM-dd'/>"); 
    
    $("#shbm").val("${hsw1.shbm}");
    $("#shbmqz").val("${hsw1.shbmqz}");  
    $("#shbmqzsj").val("<fmt:formatDate value='${hsw1.shbmqzsj}' pattern='yyyy-MM-dd'/>");
    
    $("#spbm").val("${hsw1.spbm}");
    $("#spbmqz").val("${hsw1.spbmqz}");  
    $("#spbmqzsj").val("<fmt:formatDate value='${hsw1.spbmqzsj}' pattern='yyyy-MM-dd'/>"); 
    
    $("#wgys").val("${hsw1.wgys}");
    $("#wgysqz").val("${hsw1.wgysqz}");
    $("#wgysqzsj").val("<fmt:formatDate value='${hsw1.wgysqzsj}' pattern='yyyy-MM-dd'/>");
    
}

function save() {
  var obj = {
      "sqdw" : $("#sqdw").val(),
      "sqr" : $("#sqr").val(),
      "zyzbh" : $("#zyzbh").val(),
      
      "zysjS":$("#zysj_s").val(),
      "zysjE":$("#zysj_e").val(),
      "zydd":$("#zydd").val(),
      "zynr":$("#zynr").val(),
      "zygd":$("#zygd").val(),
      "zylb":$("#zylb").val(),
      "zydw":$("#zydw").val(),
      "jhr":$("#jhr").val(),
      "zyr":$("#zyr").val(),
      "sjdqttszy":$("#sjdqttszy").val(), 
      "whbs":$("#whbs").val(),

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
      "qrr12" : $("#qrr12").val(),
      "qrr13" : $("#qrr13").val(),
      "qrr14" : $("#qrr14").val(),   
      "xzab6":$("#xzab6").val(),
      "sfcy12":$("#sfcy12").val(), 
      "qtaqcs":$("#qtaqcs").val(),
      "bzr":$("#bzr").val(),
      
      "ssaqjyr1":$("#ssaqjyr1").val(),
      "ssaqjyr2":$("#ssaqjyr2").val(),
      "ssaqjyr3":$("#ssaqjyr3").val(),
      
      "scdwzyfzr":$("#scdwzyfzr").val(),  
      "scdwzyfzrqz":$("#scdwzyfzrqz").val(),  
      "scdwzyfzrqzsj":$("#scdwzyfzrqzsj").val(),  
      
      "zydwfzr":$("#zydwfzr").val(),  
      "zydwfzrqz":$("#zydwfzrqz").val(), 
      "zydwfzrqzsj":$("#zydwfzrqzsj").val(), 
      
      "shbm":$("#shbm").val(),  
      "shbmqz":$("#shbmqz").val(),  
      "shbmqzsj":$("#shbmqzsj").val(),  
      
      "spbm":$("#spbm").val(),  
      "spbmqz":$("#spbmqz").val(),  
      "spbmqzsj":$("#spbmqzsj").val(),  
      
      "wgys" : $("#wgys").val(),
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
  $.post(getRootPath() + "/company/study/highsafety-save", obj,function(result){
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