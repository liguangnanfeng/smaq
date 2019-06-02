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
<%@ include file="/WEB-INF/inc/print.inc"%>
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
	input{
		font-size:15px;
		border:none;
		width:3%;
		text-align:center;
		vertical-align:middel;
		height:30px;
	}

</style>
<script type="text/javascript">
	function pr_() {
  		$("#div_container").jqprint();
	}
</script>
</head>
<body>
	<nav class="breadcrumb">
	    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
	    <span class="c-gray en">&gt;</span> <span>风险研判与承诺公告</span> 
	    <span class="c-gray en">&gt;</span> <span>承诺公告公示</span> 
	    <c:if test="${action1 == 'read1'}"><a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="window.location.href = '${ly }/villageAdd/study/promise-list?uid=${pro.uid }&type=${type }';">返回</a></c:if>
	</nav>
<div class="mt-20" id="div_container">
	<h1 class="text-c">${CompanyName }安全承诺公告牌</h1>
    <table class="table table-border table-bordered table-bg table-hover table-sort"  style="width:80%;margin-left:10%;">
    	<tr style="text-align:center;">
    		<td ><h3 class="text-c">${CompanyName }</h3></td>
    	</tr>
    </table>
    <table class="table table-border table-bordered table-bg table-hover table-sort" style="width:80%;margin-left:10%;" >
    	<tr>
    		<td style="width: 25%;text-align:center;" rowspan="4">
    			<h4>企</h4>
    			<h4>业</h4>
    			<h4>承</h4>
    			<h4>诺</h4>
			</td>
    		<td style="width: 75%;text-align:left;padding-left:40px;">
    			<%--<textarea id="qyzt" style="margin-top:5px;width:99%;height:99%;font-size:16pt"> </textarea>--%>
    			<h4>生产装置（<b><input id="numSc" value="${pro.numSc }"/></b>）套，其中运行（<b><input id="numYx" value="${pro.numYx }"/></b>）套，停产（<b><input id="numTc" value="${pro.numTc }"/></b>）套，检修&nbsp;（<b>${pro.numJx }</b>）&nbsp;套；</h4>	
    		</td>
    	</tr>
    	<tr>
    		<td style="width: 75%;text-align:left;padding-left:40px;">
    			<h4>特殊、一级、二级动火作业各&nbsp;（<b>${pro.numDh }</b>）&nbsp;处；进入受限空间作业各&nbsp;（<b>${pro.numSx }</b>）&nbsp;处;</h4>
    		</td>
    	</tr>
    	<tr>
    		<td style="width: 75%;text-align:left;padding-left:40px;">
    			<h4>
    				是否处于试生产（<b><c:if test="${pro.ssc == 1 }" >是</c:if><c:if test="${pro.ssc != 1 }" >否</c:if></b>）；
    				是否处于开停车状态（<b><c:if test="${pro.ktc == 1 }" >是</c:if><c:if test="${pro.ktc != 1 }" >否</c:if></b>）；
    			</h4>
    		</td>
    	</tr>
    	<tr>
    		<td style="width: 75%;text-align:left;padding-left:40px;">
  				<h4>罐区、仓库等重大危险源是否处于安全状态（<b><c:if test="${pro.aqzt == 1 }" >是</c:if><c:if test="${pro.aqzt != 1 }" >否</c:if></b>）；</h4>
    		</td>
    	</tr>
    </table>
     <table class="table table-border table-bordered table-bg table-hover table-sort" style="width:80%;margin-left:10%;text-align:center;">
    	<tr>
    		<td style="width: 25%;text-align:center;" >
    			<h4>企</h4>
    			<h4>业</h4>
    			<h4>承</h4>
    			<h4>诺</h4>
    		</td>
    		<td style="width: 75%;text-align:left;padding-left:40px;padding-right:60px;">		
    			<%--<textarea id="qycn" style="margin-top:5px;width:99%;height:99%;font-size:16pt" readonly="readonly"></textarea> --%>
    			<input type="hidden" id="cnggid" value="${pro.id }" />
    			<h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;今天我们公司已经进行安全风险研判，各项安全风险防控措施已落实到位，
    			我承诺所有生产装置处于安全运行状态，罐区、仓库等重大危险源安全风险得到有效管控。
    			</h4>
    		</td>
    	</tr>
    </table>  
     <table class="table table-border table-bordered table-bg table-hover table-sort" style="width:80%;margin-left:10%;">
    	<tr>
    		<td style="width: 25%;text-align:center;"><h4>主要负责人</h4></td>
    		<td style="width: 25%;text-align:center;"><h5>${pro.zyfzr }</h5></td>
    		<td style="width: 25%;text-align:center;"><h4>安全承诺时间</h4></td>
    		<td style="width: 25%;text-align:center;"><h5><fmt:formatDate value='${pro.aqcnsj }' pattern='yyyy-MM-dd HH:mm'/></h5></td>
    	</tr>
    </table>
</div>
  
  <div class="mt-20 f-l mb-40" style="width:100%;text-align:center;">
  	 <button onClick="pr_()" class="btn btn-primary radius mt-20" type="button" style="padding:0 70px;">
        <i class="Hui-iconfont mr-10">&#xe632;</i>打印
     </button>
  	<c:if test="${action != 'read'}">
	  <button onClick="save()" class="btn btn-primary radius mt-20" type="button" style="padding:0 70px;">
        <i class="Hui-iconfont mr-10">&#xe632;</i>保存
      </button>
    </c:if>
  </div>
</body>
<!-- <script type="text/javascript" src="/js/fxgk/tbl-rowspan-reset.js"></script> -->
<script type="text/javascript">
//if("${action}" == "read"){
	//$("textarea").attr("readonly","readonly");	
	//$("input").attr("disabled","disabled");
	//$("input").css("background-color","white");
	
    //$("#qyzt").val("${pr.qyzt}");
    //$("#qycn").val("${pr.qycn}");
    //$("#zyfzr").val("${pr.zyfzr}");
    //$("#aqcnsj").val("<fmt:formatDate value='${pr.aqcnsj}' pattern='yyyy-MM-dd'/>");
    
// }else{
	//var status1 = '${status}';
	//alert(status1);
	//$("#qyzt").val(status1);
	//var undertake1 = '${undertake}';
	//alert(undertake1);
	//$("#qycn").val(undertake1);	
//	var today=new Date();
//	var submitTime = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
//	$("#aqcnsj").attr('value',submitTime);
//	var zyfzr1 = '${legal }';
//	$("#zyfzr").val(zyfzr1);		
// }
function save() {
  var obj = {
	"id" :  $("#cnggid").val(),
	"numSc" : $("#numSc").val(),	
	"numYx" : $("#numYx").val(),
	"numTc" : $("#numTc").val(),
  }
  //alert(JSON.stringify(obj));
  if(obj.numSc == '') {
	    $("#numSc").focus();
	    alert("请填写生产装置套数 ！");
	    return false;
	  }
  if(obj.numYx == '') {
	    $("#numYx").focus();
	    alert("请填写生产装置运行套数 ！");
	    return false;
	  }
  if(obj.numTc == '') {
	    $("#numTc").focus();
	    alert("请填写生产装置停产套数 ！");
	    return false;
	  }
  var i = layer.load();
  $.post(getRootPath() + "/company/study/promise-save", obj,function(result){
    layer.close(i);
 //   if(result.status == '1') {
 //   	layer.alert(result.map.message);
 //   } else {
 //   	layer.alert("保存成功！");
 //       parent.location.reload();
 //  }
    if(result.status == '0') {
      layer.alert("保存成功！",{},function(i){
  		window.location.href = '${ly }/company/study/promise';
  	});
    } else {
      layer.alert(result.mess);
    }
  }
  
  );
}
</script>
</html>