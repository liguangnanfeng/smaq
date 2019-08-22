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
	
	
	<h3 class="text-c">《动土安全作业证》</h3>
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
    	<td style="width: 25%;">监护人</td>
    	<td><input id="jhr" type="text"/></td>
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
    	<td style="width: 25%;">作业单位</td>
    	<td><input id="zydw" type="text"/></td>
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
    	<td>作业范围、内容、方式（包括深度、面积）：<input id="zyfw" style="width:46%;" type="text"/>签字：<input id="zyfwqz" style="width:10%;" type="text"/>日期：<input id="zyfwqzsj" style="width:10%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
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
    	<td style="width: 75%;">作业人员作业前已进行了安全教育</td>
    	<td><input id="qrr1" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">2</td>
    	<td style="width: 75%;">作业地点处于易燃易爆场所，需要动火时已办理了动火证</td>
    	<td><input id="qrr2" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">3</td>
    	<td style="width: 75%;">地下电力电缆已确认保护措施已落实</td>
    	<td><input id="qrr3" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">4</td>
    	<td style="width: 75%;">地下通讯电（光）缆，局域网络电（光）缆已确认保护措施已落实</td>
    	<td><input id="qrr4" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">5</td>
    	<td style="width: 75%;">地下供排水、消防管线、工艺管线已确认保护措施已落实</td>
    	<td><input id="qrr5" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">6</td>
    	<td style="width: 75%;">已按作业方案图规划线和立桩</td>
    	<td><input id="qrr6" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">7</td>
    	<td style="width: 75%;">动土地点有电线、管道等地下设施，已向作业单位交待并派人监护；作业时轻挖，未使用铁棒、铁镐或抓斗等机械工具</td>
    	<td><input id="qrr7" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">8</td>
    	<td style="width: 75%;">作业现场围栏、警戒线、警告牌夜间警示灯已按要求设置</td>
    	<td><input id="qrr8" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">9</td>
    	<td style="width: 75%;">已进行放坡处理和固壁支撑</td>
    	<td><input id="qrr9" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">10</td>
    	<td style="width: 75%;">人员出入口和撤离安全措施已落实：A.梯子；B.修坡道</td>
    	<td><input id="qrr10" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">11</td>
    	<td style="width: 75%;">道路施工作业已报：交通、消防、安全监督部门、应急中心</td>
    	<td><input id="qrr11" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">12</td>
    	<td style="width: 75%;">备有可燃气体检测仪、有毒介质检测仪</td>
    	<td><input id="qrr12" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">13</td>
    	<td style="width: 75%;">现场夜间有充足照明：A.36V、24V、12V防水型灯；B.36V、24V、12V防爆型灯</td>
    	<td><input id="qrr13" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">14</td>
    	<td style="width: 75%;">作业人员已佩戴防护器具</td>
    	<td><input id="qrr14" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">15</td>
    	<td style="width: 75%;">动土范围内无障碍物，并已在总图上做标记</td>
    	<td><input id="qrr15" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">16</td>
    	<td style="width: 75%;">其他安全措施：<input id="qtaqcs" style="width:70%;" type="text"/>编制人：<input id="bzr" style="width:10%;" type="text"/></td>
    	<td><input id="qrr16" type="text"/></td>
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
    	<td>申请单位意见：<input id="sqdwyj" style="width:60%;" type="text"/>签字：<input id="sqdwqz" style="width:10%;" type="text"/>日期：<input id="sqdwqzsj" style="width:10%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td>作业单位意见：<input id="zydwyj" style="width:60%;" type="text"/>签字：<input id="zydwqz" style="width:10%;" type="text"/>日期：<input id="zydwqzsj" style="width:10%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td>有关水、电、汽、工艺、设备、消防、安全等部门意见：<input id="ygbmyj" style="width:40%;" type="text"/>签字：<input id="ygbmqz" style="width:10%;" type="text"/>日期：<input id="ygbmqzsj" style="width:10%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td>审批部门意见：<input id="spbmyj" style="width:60%;" type="text"/>签字：<input id="spbmqz" style="width:10%;" type="text"/>日期：<input id="spbmqzsj" style="width:10%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td>完工验收：<input id="wgysyj" style="width:60%;" type="text"/>签字：<input id="wgysqz" style="width:10%;" type="text"/>日期：<input id="wgysqzsj" style="width:10%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
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
	
	$("#sqdw").val("${lw.sqdw }");
    $("#sqr").val("${lw.sqr }");
    $("#zyzbh").val("${lw.zyzbh}");
 
    $("#jhr").val("${lw.jhr}"),
    $("#zysj_s").val("<fmt:formatDate value='${lw.zysjS}' pattern='yyyy-MM-dd HH:mm'/>");
    $("#zysj_e").val("<fmt:formatDate value='${lw.zysjE}' pattern='yyyy-MM-dd HH:mm'/>");
    $("#zydd").val("${lw.zydd}");
    $("#zydw").val("${lw.zydw}");
    $("#sjdqttszy").val("${lw.sjdqttszy}");
    $("#zyfw").val("${lw.zyfw}");
    $("#zyfwqz").val("${lw.zyfwqz}");
    $("#zyfwqzsj").val("<fmt:formatDate value='${lw.zyfwqzsj}' pattern='yyyy-MM-dd HH:mm'/>");
    $("#whbs").val("${lw.whbs}");
    
    $("#qrr1").val("${lw.qrr1}");
    $("#qrr2").val("${lw.qrr2}");
    $("#qrr3").val("${lw.qrr3}");
    $("#qrr4").val("${lw.qrr4}");
    $("#qrr5").val("${lw.qrr5}");
    $("#qrr6").val("${lw.qrr6}");
    $("#qrr7").val("${lw.qrr7}");
    $("#qrr8").val("${lw.qrr8}");
    $("#qrr9").val("${lw.qrr9}");
    $("#qrr10").val("${lw.qrr10}");
    $("#qrr11").val("${lw.qrr11}");
    $("#qrr12").val("${lw.qrr12}");
    $("#qrr13").val("${lw.qrr13}");
    $("#qrr14").val("${lw.qrr14}");
    $("#qrr15").val("${lw.qrr15}");
    $("#qrr16").val("${lw.qrr16}"); 
    $("#qtaqcs").val("${lw.qtaqcs}");
    $("#bzr").val("${lw.bzr}");
    
    $("#ssaqjyr1").val("${lw.ssaqjyr1}");
    $("#ssaqjyr2").val("${lw.ssaqjyr2}");
    $("#ssaqjyr3").val("${lw.ssaqjyr3}");
    
    $("#sqdwyj").val("${lw.sqdwyj}");
    $("#sqdwqz").val("${lw.sqdwqz}");
    $("#sqdwqzsj").val("<fmt:formatDate value='${lw.sqdwqzsj}' pattern='yyyy-MM-dd'/>");
    
    $("#zydwyj").val("${lw.zydwyj}");
    $("#zydwqz").val("${lw.zydwqz}");
    $("#zydwqzsj").val("<fmt:formatDate value='${lw.zydwqzsj}' pattern='yyyy-MM-dd'/>");
    
    $("#ygbmyj").val("${lw.ygbmyj}");
    $("#ygbmqz").val("${lw.ygbmqz}");
    $("#ygbmqzsj").val("<fmt:formatDate value='${lw.ygbmqzsj}' pattern='yyyy-MM-dd'/>");
    
    $("#spbmyj").val("${lw.spbmyj}");
    $("#spbmqz").val("${lw.spbmqz}");
    $("#spbmqzsj").val("<fmt:formatDate value='${lw.spbmqzsj}' pattern='yyyy-MM-dd'/>");

    $("#wgysyj").val("${lw.wgysyj}");
    $("#wgysqz").val("${lw.wgysqz}");
    $("#wgysqzsj").val("<fmt:formatDate value='${lw.wgysqzsj}' pattern='yyyy-MM-dd'/>");
}

function save() {
  var obj = {
      "sqdw" : $("#sqdw").val(),
      "sqr" : $("#sqr").val(),
      "zyzbh" : $("#zyzbh").val(),
      "jhr" : $("#jhr").val(),
      "zysjS" : $("#zysj_s").val(),
      "zysjE" : $("#zysj_e").val(),
      "zydd" : $("#zydd").val(),
      "zydw" : $("#zydw").val(),
      "sjdqttszy" : $("#sjdqttszy").val(),
      "zyfw" : $("#zyfw").val(),
      "zyfwqz" : $("#zyfwqz").val(),
      "zyfwqzsj" : $("#zyfwqzsj").val(),
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
      "qrr11" : $("#qrr11").val(),
      "qrr12" : $("#qrr12").val(),
      "qrr13" : $("#qrr13").val(),
      "qrr14" : $("#qrr14").val(),
      "qrr15" : $("#qrr15").val(),
      "qrr16" : $("#qrr16").val(), 
      "qtaqcs" : $("#qtaqcs").val(),
      "bzr" : $("#bzr").val(),
      
      "ssaqjyr1":$("#ssaqjyr1").val(),
      "ssaqjyr2":$("#ssaqjyr2").val(),
      "ssaqjyr3":$("#ssaqjyr3").val(),
      
      "sqdwyj" : $("#sqdwyj").val(),
      "sqdwqz" : $("#sqdwqz").val(),
      "sqdwqzsj" : $("#sqdwqzsj").val(),
      
      "zydwyj" : $("#zydwyj").val(),
      "zydwqz" : $("#zydwqz").val(),
      "zydwqzsj" : $("#zydwqzsj").val(),
      
      "ygbmyj" : $("#ygbmyj").val(),
      "ygbmqz" : $("#ygbmqz").val(),
      "ygbmqzsj" : $("#ygbmqzsj").val(),
      
      "spbmyj" : $("#spbmyj").val(),
      "spbmqz" : $("#spbmqz").val(),
      "spbmqzsj" : $("#spbmqzsj").val(),

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
  $.post(getRootPath() + "/company/study/landwork-save", obj,function(result){
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