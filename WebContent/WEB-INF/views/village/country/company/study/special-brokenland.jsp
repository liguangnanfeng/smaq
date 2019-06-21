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
	
	
	<h3 class="text-c">《断路安全作业证》</h3>
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
    	<td style="width: 25%;">作业单位</td>
    	<td><input id="zydw" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">涉及相关单位(部门)</td>
    	<td><input id="sjxgdw" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">断路原因</td>
    	<td><input id="dlyy" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">断路起始时间</td>
    	<td style="width: 25%;"><input id="dlsj_s" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
    	<td style="width: 25%;">断路截止时间</td>
    	<td><input id="dlsj_e" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td>断路地段及相关说明：<input id="dldd" style="width:50%;" type="text"/>签字：<input id="dlddqz" style="width:10%;" type="text"/>日期：<input id="dlddqzsj" style="width:10%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
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
    	<td style="width: 75%;">作业前，制定交通组织方案（附后），并已通知相关部门或单位</td>
    	<td><input id="qrr1" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">2</td>
    	<td style="width: 75%;">作业前，在断路的路口和相关道路上设置交通警示标志，在作业区附近设置路栏、道路作业警示灯、导向标等交通警示设施</td>
    	<td><input id="qrr2" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">3</td>
    	<td style="width: 75%;">夜间作业设置警示灯</td>
    	<td><input id="qrr3" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">4</td>
    	<td style="width: 75%;">其他安全措施：<input id="qtaqcs" style="width:70%;" type="text"/>编制人：<input id="bzr" style="width:10%;" type="text"/></td>
    	<td><input id="qrr4" type="text"/></td>
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
	
	$("#sqdw").val("${bl.sqdw }");
    $("#sqr").val("${bl.sqr }");
    $("#zyzbh").val("${bl.zyzbh}");
    
    $("#zydw").val("${bl.zydw}");
    $("#sjxgdw").val("${bl.sjxgdw}");
    $("#dlyy").val("${bl.dlyy}");
    $("#dlsj_s").val("<fmt:formatDate value='${bl.dlsjS}' pattern='yyyy-MM-dd HH:mm'/>");
    $("#dlsj_e").val("<fmt:formatDate value='${bl.dlsjE}' pattern='yyyy-MM-dd HH:mm'/>");
    $("#dldd").val("${bl.dldd}");
    $("#dlddqz").val("${bl.dlddqz}");
    $("#dlddqzsj").val("<fmt:formatDate value='${bl.dlddqzsj}' pattern='yyyy-MM-dd HH:mm'/>");
    $("#whbs").val("${bl.whbs}");
    
    $("#qrr1").val("${bl.qrr1}");
    $("#qrr2").val("${bl.qrr2}");
    $("#qrr3").val("${bl.qrr3}");
    $("#qrr4").val("${bl.qrr4}");
    $("#qtaqcs").val("${bl.qtaqcs}");
    $("#bzr").val("${bl.bzr}");
    
    $("#ssaqjyr1").val("${bl.ssaqjyr1}");
    $("#ssaqjyr2").val("${bl.ssaqjyr2}");
    $("#ssaqjyr3").val("${bl.ssaqjyr3}");
    
    $("#sqdwyj").val("${bl.sqdwyj}");
    $("#sqdwqz").val("${bl.sqdwqz}");
    $("#sqdwqzsj").val("<fmt:formatDate value='${bl.sqdwqzsj}' pattern='yyyy-MM-dd'/>");
    
    $("#zydwyj").val("${bl.zydwyj}");
    $("#zydwqz").val("${bl.zydwqz}");
    $("#zydwqzsj").val("<fmt:formatDate value='${bl.zydwqzsj}' pattern='yyyy-MM-dd'/>");
    
    $("#ygbmyj").val("${bl.ygbmyj}");
    $("#ygbmqz").val("${bl.ygbmqz}");
    $("#ygbmqzsj").val("<fmt:formatDate value='${bl.ygbmqzsj}' pattern='yyyy-MM-dd'/>");
    
    $("#spbmyj").val("${bl.spbmyj}");
    $("#spbmqz").val("${bl.spbmqz}");
    $("#spbmqzsj").val("<fmt:formatDate value='${bl.spbmqzsj}' pattern='yyyy-MM-dd'/>");

    $("#wgysyj").val("${bl.wgysyj}");
    $("#wgysqz").val("${bl.wgysqz}");
    $("#wgysqzsj").val("<fmt:formatDate value='${bl.wgysqzsj}' pattern='yyyy-MM-dd'/>");
}

function save() {
  var obj = {
      "sqdw" : $("#sqdw").val(),
      "sqr" : $("#sqr").val(),
      "zyzbh" : $("#zyzbh").val(),
      "zydw" : $("#zydw").val(),
      "sjxgdw" : $("#sjxgdw").val(),
      "dlyy" : $("#dlyy").val(),
      "dlsjS" : $("#dlsj_s").val(),
      "dlsjE" : $("#dlsj_e").val(),
      "dldd" : $("#dldd").val(),
      "dlddqz" : $("#dlddqz").val(),
      "dlddqzsj" : $("#dlddqzsj").val(),
      "whbs" : $("#whbs").val(),
      
      "qrr1" : $("#qrr1").val(),
      "qrr2" : $("#qrr2").val(),
      "qrr3" : $("#qrr3").val(),
      "qrr4" : $("#qrr4").val(),
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
  $.post(getRootPath() + "/company/study/brokenland-save", obj,function(result){
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