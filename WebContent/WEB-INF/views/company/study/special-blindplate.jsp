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
	
	
	<h3 class="text-c">《盲板抽堵安全作业证》</h3>
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
    	<td style="width: 15%;">设备管道名称</td>
    	<td style="width: 5%;">介质</td>
    	<td style="width: 5%;" rowspan="2">温度</td>
    	<td style="width: 5%;" rowspan="2">压力</td>
    	<td style="width: 15%;" colspan="3">盲板</td>
    	<td style="width: 15%;" colspan="2">实施时间</td>
    	<td style="width: 15%;" colspan="2">作业人</td>
    	<td style="width: 25%;" colspan="2">监护人</td>
    	</tr>
    	<tr>
    	<td style="width: 15%;">设备管道名称</td>
    	<td style="width: 5%;">介质</td>
  
    	<td style="width: 5%;">材质</td>
    	<td style="width: 5%;">规格</td>
    	<td style="width: 5%;">编号</td>
    	<td style="width: 8%;">堵</td>
    	<td style="width: 7%;">抽</td>
    	<td style="width: 8%;">堵</td>
    	<td style="width: 7%;">抽</td>
    	<td style="width: 13%;">堵</td>
    	<td style="width: 12%;">抽</td>
    	</tr>
	</table>
     <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">	
    	<tr>
    	<td style="width: 15%;"><input id="sbgdmc" type="text"/></td>
    	<td style="width: 5%;"><input id="jz" type="text"/></td>
    	<td style="width: 5%;"><input id="wd" type="text"/></td>
    	<td style="width: 5%;"><input id="yl" type="text"/></td>
    	<td style="width: 5%;"><input id="mb_cz" type="text"/></td>
    	<td style="width: 5%;"><input id="mb_gg" type="text"/></td>
    	<td style="width: 5%;"><input id="mb_bh" type="text"/></td>
    	<td style="width: 8%;"><input id="sssj_d" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
    	<td style="width: 7%;"><input id="sssj_c" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
    	<td style="width: 8%;"><input id="zyr_d" type="text"/></td>
    	<td style="width: 7%;"><input id="zyr_c" type="text"/></td>
    	<td style="width: 13%;"><input id="jhr_d" type="text"/></td>
    	<td style="width: 12%;"><input id="jhr_c" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 15%;">生产单位作业指挥</td>
    	<td><input id="scdwzyzh" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 15%;">作业单位负责人</td>
    	<td><input id="zydwfzr" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 15%;">涉及的其他特殊操作</td>
    	<td><input id="sjdqttscz" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td>盲板位置及编号：<input id="mbwztjbh" style="width:60%;" type="text"/>
    	编制人：<input id="bzr_mb" style="width:10%;" type="text"/>日期：<input id="bzr_mbsj" style="width:10%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
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
    	<td style="width: 75%;">在有毒介质的管道、设备上作业时，尽可能降低系统压力，作业点应为常压</td>
    	<td><input id="qrr1" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">2</td>
    	<td style="width: 75%;">在有毒介质的管道、设备上作业时，作业人员穿戴适合的防护用具</td>
    	<td><input id="qrr2" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">3</td>
    	<td style="width: 75%;">易燃易爆场所，作业人员穿防静电工作服、工作鞋；作业时使用防爆灯具和防爆工具</td>
    	<td><input id="qrr3" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">4</td>
    	<td style="width: 75%;">易燃易爆场所，距作业地点30m内无其他动火作业</td>
    	<td><input id="qrr4" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">5</td>
    	<td style="width: 75%;">在强腐蚀性介质的管道、设备上作业时，作业人员已采取防止酸碱灼伤的措施</td>
    	<td><input id="qrr5" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">6</td>
    	<td style="width: 75%;">介质温度较高、可能造成烫伤的情况下，作业人员已采取防烫措施</td>
    	<td><input id="qrr6" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">7</td>
    	<td style="width: 75%;">同一管道上不同时进行两处以上的盲板抽堵作业</td>
    	<td><input id="qrr7" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">8</td>
    	<td style="width: 75%;">其他安全措施：<input id="qtaqcs" style="width:70%;" type="text"/>编制人：<input id="bzr" style="width:10%;" type="text"/></td>
    	<td><input id="qrr8" type="text"/></td>
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
    	<td>安全员意见：<input id="aqyyj" style="width:60%;" type="text"/>签字：<input id="aqyqz" style="width:10%;" type="text"/>日期：<input id="aqyqzsj" style="width:10%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td>作业单位意见：<input id="zydwyj" style="width:60%;" type="text"/>签字：<input id="zydwqz" style="width:10%;" type="text"/>日期：<input id="zydwqzsj" style="width:10%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td>审批单位意见：<input id="spdwyj" style="width:60%;" type="text"/>签字：<input id="spdwqz" style="width:10%;" type="text"/>日期：<input id="spdwqzsj" style="width:10%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td>盲板抽堵作业单位确认情况：<input id="mbjczydwqrqk" style="width:55%;" type="text"/>签字：<input id="mbjczydwqrqkqz" style="width:10%;" type="text"/>日期：<input id="mbjczydwqrqkqzsj" style="width:10%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td>安全员确认情况：<input id="aqyqrqk" style="width:60%;" type="text"/>签字：<input id="aqyqrqkqz" style="width:10%;" type="text"/>日期：<input id="aqyqrqkqzsj" style="width:10%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/></td>
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
	
	$("#sqdw").val("${bp.sqdw }");
    $("#sqr").val("${bp.sqr }");
    $("#zyzbh").val("${bp.zyzbh}");
    
    $("#sbgdmc").val("${bp.sbgdmc}");
    $("#jz").val("${bp.jz}");
    $("#wd").val("${bp.wd}");
    $("#yl").val("${bp.yl}");
    $("#mb_cz").val("${bp.mbcz}");
    $("#mb_gg").val("${bp.mbgg}");
    $("#mb_bh").val("${bp.mbbh}");
    $("#sssj_d").val("<fmt:formatDate value='${bp.sssjd}' pattern='MM-dd HH:mm'/>");
    $("#sssj_c").val("<fmt:formatDate value='${bp.sssjc}' pattern='MM-dd HH:mm'/>");
    $("#zyr_d").val("${bp.zyrd}");
    $("#zyr_c").val("${bp.zyrc}");
    $("#jhr_d").val("${bp.jhrd}");
    $("#jhr_c").val("${bp.jhrc}");
    $("#scdwzyzh").val("${bp.scdwzyzh}");
    $("#zydwfzr").val("${bp.zydwfzr}");
    $("#sjdqttscz").val("${bp.sjdqttscz}");
    
    $("#mbwztjbh").val("${bp.mbwztjbh}");
    $("#bzr_mb").val("${bp.bzrmb}");
    $("#bzr_mbsj").val("<fmt:formatDate value='${bp.bzrmbsj}' pattern='yyyy-MM-dd HH:mm'/>");

    $("#qrr1").val("${bp.qrr1}");
    $("#qrr2").val("${bp.qrr2}");
    $("#qrr3").val("${bp.qrr3}");
    $("#qrr4").val("${bp.qrr4}");
    $("#qrr5").val("${bp.qrr5}");
    $("#qrr6").val("${bp.qrr6}");
    $("#qrr7").val("${bp.qrr7}"); 
    $("#qrr8").val("${bp.qrr8}");   
    $("#qtaqcs").val("${bp.qtaqcs}");
    $("#bzr").val("${bp.bzr}");
    
    $("#ssaqjyr1").val("${bp.ssaqjyr1}");
    $("#ssaqjyr2").val("${bp.ssaqjyr2}");
    $("#ssaqjyr3").val("${bp.ssaqjyr3}");

    $("#aqyyj").val("${bp.aqyyj}");
    $("#aqyqz").val("${bp.aqyqz}");
    $("#aqyqzsj").val("<fmt:formatDate value='${bp.aqyqzsj}' pattern='yyyy-MM-dd'/>");
    
    $("#zydwyj").val("${bp.zydwyj}");
    $("#zydwqz").val("${bp.zydwqz}");
    $("#zydwqzsj").val("<fmt:formatDate value='${bp.zydwqzsj}' pattern='yyyy-MM-dd'/>");
    
    $("#spdwyj").val("${bp.spdwyj}");
    $("#spdwqz").val("${bp.spdwqz}");
    $("#spdwqzsj").val("<fmt:formatDate value='${bp.spdwqzsj}' pattern='yyyy-MM-dd'/>");
    
    $("#mbjczydwqrqk").val("${bp.mbjczydwqrqk}");
    $("#mbjczydwqrqkqz").val("${bp.mbjczydwqrqkqz}");
    $("#mbjczydwqrqkqzsj").val("<fmt:formatDate value='${bp.mbjczydwqrqkqzsj}' pattern='yyyy-MM-dd'/>");
    
    $("#aqyqrqk").val("${bp.aqyqrqk}");
    $("#aqyqrqkqz").val("${bp.aqyqrqkqz}");
    $("#aqyqrqkqzsj").val("<fmt:formatDate value='${bp.aqyqrqkqzsj}' pattern='yyyy-MM-dd'/>"); 
}
function save() {
  var obj = {
      "sqdw" : $("#sqdw").val(),
      "sqr" : $("#sqr").val(),
      "zyzbh" : $("#zyzbh").val(),
      "sbgdmc" : $("#sbgdmc").val(),
      "jz" : $("#jz").val(),
      "wd" : $("#wd").val(),
      "yl" : $("#yl").val(),
      "mbcz" : $("#mb_cz").val(),
      "mbgg" : $("#mb_gg").val(),
      "mbbh" : $("#mb_bh").val(),
      "sssjd" : $("#sssj_d").val(),
      "sssjc" : $("#sssj_c").val(), 
      "zyrd" : $("#zyr_d").val(),
      "zyrc" : $("#zyr_c").val(),
      "jhrd" : $("#jhr_d").val(),
      "jhrc" : $("#jhr_c").val(),
      "scdwzyzh" : $("#scdwzyzh").val(),
      "zydwfzr" : $("#zydwfzr").val(),
      "sjdqttscz" : $("#sjdqttscz").val(),
      
      "mbwztjbh" : $("#mbwztjbh").val(),
      "bzrmb" : $("#bzr_mb").val(),
      "bzrmbsj" : $("#bzr_mbsj").val(),

      "qrr1" : $("#qrr1").val(),
      "qrr2" : $("#qrr2").val(),
      "qrr3" : $("#qrr3").val(),
      "qrr4" : $("#qrr4").val(),
      "qrr5" : $("#qrr5").val(),
      "qrr6" : $("#qrr6").val(),
      "qrr7" : $("#qrr7").val(), 
      "qrr8" : $("#qrr8").val(),   
      "qtaqcs" : $("#qtaqcs").val(),
      "bzr" : $("#bzr").val(),
      
      "ssaqjyr1" : $("#ssaqjyr1").val(),
      "ssaqjyr2" : $("#ssaqjyr2").val(),
      "ssaqjyr3" : $("#ssaqjyr3").val(),

      "aqyyj" : $("#aqyyj").val(),
      "aqyqz" : $("#aqyqz").val(),
      "aqyqzsj" : $("#aqyqzsj").val(),
      
      "zydwyj" : $("#zydwyj").val(),
      "zydwqz" : $("#zydwqz").val(),
      "zydwqzsj" : $("#zydwqzsj").val(),
      
      "spdwyj" : $("#spdwyj").val(),
      "spdwqz" : $("#spdwqz").val(),
      "spdwqzsj" : $("#spdwqzsj").val(),
      
      "mbjczydwqrqk" : $("#mbjczydwqrqk").val(),
      "mbjczydwqrqkqz" : $("#mbjczydwqrqkqz").val(),
      "mbjczydwqrqkqzsj" : $("#mbjczydwqrqkqzsj").val(),
      
      "aqyqrqk" : $("#aqyqrqk").val(),
      "aqyqrqkqz" : $("#aqyqrqkqz").val(),
      "aqyqrqkqzsj" : $("#aqyqrqkqzsj").val(), 
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
  $.post(getRootPath() + "/company/study/blindplate-save", obj,function(result){
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