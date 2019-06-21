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
	<nav class="breadcrumb">
	    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
	    <span class="c-gray en">&gt;</span> <span>风险研判与承诺公告</span> 
	    <span class="c-gray en">&gt;</span> <span>目标履职考核</span>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;" href="javascript:history.back(-1)" title="返回">返回</a>
		<%--<a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="parent.close_tab(function(){})">返回</a>--%>
	</nav>
	<h3 class="text-c">《${CompanyName}安全风险研判目标管理履职考核》</h3>
    <div class="mt-20">
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">被考核岗位</td>
    	<td style="width: 25%;"><input id="bkhgw" type="text"/></td>
    	<td style="width: 25%;">日期</td>
    	<td style="width: 25%;"><input id="khsj" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width:15%;">序号</td>
    	<td colspan="2" style="width:60%;">目标管理</td>
    	<td style="width:25%;">是否符合要求</td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width:15%;">1</td>
    	<td colspan="3" style="width:60%;">生产装置设备安全可靠</td>
        <td id="khd1">
           <div class="radio-box">
             <input type="radio" name="khd1" id="khd1_s" value="1"><label for="khd1_s">是</label>
           </div>
           <div class="radio-box">
             <input type="radio" name="khd1" id="khd1_f" checked = "checked" value="0"><label for="khd1_f">否</label>
           </div>
        </td>
    	</tr>
    </table> 
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width:15%;">2</td>
    	<td colspan="3" style="width:60%;">储存装置、场所安全可靠规范运行</td>
        <td id="khd2">
           <div class="radio-box">
             <input type="radio" name="khd2" id="khd2_s" value="1"><label for="khd2_s">是</label>
           </div>
           <div class="radio-box">
             <input type="radio" name="khd2" id="khd2_f" checked = "checked" value="0"><label for="khd2_f">否</label>
           </div>
        </td>
    	</tr>
    </table> 
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width:15%;">3</td>
    	<td colspan="3" style="width:60%;">指挥、操作人员无“三违”行为</td>
        <td id="khd3">
           <div class="radio-box">
             <input type="radio" name="khd3" id="khd3_s" value="1"><label for="khd3_s">是</label>
           </div>
           <div class="radio-box">
             <input type="radio" name="khd3" id="khd3_f" checked = "checked" value="0"><label for="khd3_f">否</label>
           </div>
        </td>
    	</tr>
    </table> 
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width:15%;">4</td>
    	<td colspan="3" style="width:60%;">安全联锁装置、安全附件，报警装置正常运行</td>
        <td id="khd4">
           <div class="radio-box">
             <input type="radio" name="khd4" id="khd4_s" value="1"><label for="khd4_s">是</label>
           </div>
           <div class="radio-box">
             <input type="radio" name="khd4" id="khd4_f" checked = "checked" value="0"><label for="khd4_f">否</label>
           </div>
        </td>
    	</tr>
    </table> 
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width:15%;">5</td>
    	<td colspan="3" style="width:60%;">特殊作业按规定运行</td>
        <td id="khd5">
           <div class="radio-box">
             <input type="radio" name="khd5" id="khd5_s" value="1"><label for="khd5_s">是</label>
           </div>
           <div class="radio-box">
             <input type="radio" name="khd5" id="khd5_f" checked = "checked" value="0"><label for="khd5_f">否</label>
           </div>
        </td>
    	</tr>
    </table> 
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width:15%;">6</td>
    	<td colspan="3" style="width:60%;">检维修作业按规定运行</td>
        <td id="khd6">
           <div class="radio-box">
             <input type="radio" name="khd6" id="khd6_s" value="1"><label for="khd6_s">是</label>
           </div>
           <div class="radio-box">
             <input type="radio" name="khd6" id="khd6_f" checked = "checked" value="0"><label for="khd6_f">否</label>
           </div>
        </td>
    	</tr>
    </table> 
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width:15%;">7</td>
    	<td colspan="3" style="width:60%;">承包商作业按规定运行</td>
        <td id="khd7">
           <div class="radio-box">
             <input type="radio" name="khd7" id="khd7_s" value="1"><label for="khd7_s">是</label>
           </div>
           <div class="radio-box">
             <input type="radio" name="khd7" id="khd7_f" checked = "checked" value="0"><label for="khd7_f">否</label>
           </div>
        </td>
    	</tr>
    </table> 
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    		<td style="width: 25%;">考核部门</td>
    		<td style="width: 25%;"><input id="khbm" type="text"/></td>
    		<td style="width: 25%;">被考核部门</td>
    		<td style="width: 25%;"><input id="bkhbm" type="text"/></td>
    	</tr>
    </table>
     <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    		<td style="width: 25%;">考核人(考核部门)</td>
    		<td style="width: 25%;"><input id="khr_khbm" type="text"/></td>
    		<td style="width: 25%;">负责人(被考核部门)</td>
    		<td style="width: 25%;"><input id="fzr_bkhbm" type="text"/></td>
    	</tr>
    </table>  
     <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    		<td style="width: 25%;">车间主任(考核部门)</td>
    		<td style="width: 25%;"><input id="cjzr_khbm" type="text"/></td>
    		<td style="width: 25%;">车间主任(被考核部门)</td>
    		<td style="width: 25%;"><input id="cjzr_bkhbm" type="text"/></td>
    	</tr>
    </table>  
     <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    		<td style="width: 25%;">部门经理(考核部门)</td>
    		<td style="width: 25%;"><input id="bmjl_khbm" type="text"/></td>
    		<td style="width: 25%;">部门经理(被考核部门)</td>
    		<td style="width: 25%;"><input id="bmjl_bkhbm" type="text"/></td>
    	</tr>
    </table>  
 
  </div>
  
  <div class="mt-20 f-l mb-40" style="width:100%;text-align:center;">
     <c:if test="${action != 'read'}">
      <button onClick="save()" class="btn btn-primary radius mt-20" type="button" style="padding:0 70px;">
        <i class="Hui-iconfont mr-10">&#xe632;</i>保存
      </button>
     </c:if>
  </div>

  
</body>
<script type="text/javascript" src="/js/fxgk/tbl-rowspan-reset.js"></script>
<script type="text/javascript">
//if("${action}" == "read"){
	
	$("#khsj").attr("disabled","disabled");
	
	$("input").css("background-color","white");
	
    $("#bkhgw").val("${ta.bkhgw}");
    $("#khsj").val("<fmt:formatDate value='${ta.khsj}' pattern='yyyy-MM-dd'/>");
    
    
    $("input[name='khd1'][value='${ta.khd1}']").attr("checked","checked");
    $("input[name='khd2'][value='${ta.khd2}']").attr("checked","checked");
    $("input[name='khd3'][value='${ta.khd3}']").attr("checked","checked");
    $("input[name='khd4'][value='${ta.khd4}']").attr("checked","checked");
    $("input[name='khd5'][value='${ta.khd5}']").attr("checked","checked");
    $("input[name='khd6'][value='${ta.khd6}']").attr("checked","checked");
    $("input[name='khd7'][value='${ta.khd7}']").attr("checked","checked");
    
    $("#khbm").val("${ta.khbm}");
    $("#bkhbm").val("${ta.bkhbm}");
    $("#khr_khbm").val("${ta.khrkhbm}");
    $("#fzr_bkhbm").val("${ta.fzrbkhbm}");
    $("#cjzr_khbm").val("${ta.cjzrkhbm}");
    $("#cjzr_bkhbm").val("${ta.cjzrbkhbm}");
    $("#bmjl_khbm").val("${ta.bmjlkhbm}");
    $("#bmjl_bkhbm").val("${ta.bmjlbkhbm}");
	

//}else{
//	var today=new Date();
//	var submitTime = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
//	$("#khsj").attr('value',submitTime);
//}


function save() {
  var obj = {
      "bkhgw" : $("#bkhgw").val(),
//    "khsj" : $("#khsj").val(),
      
//      "khd1" : $("#khd1").val(),
//      "khd2" : $("#khd2").val(),
//      "khd3" : $("#khd3").val(),
//      "khd4" : $("#khd4").val(),
//      "khd5" : $("#khd5").val(),
//      "khd6" : $("#khd6").val(),
//      "khd7" : $("#khd7").val(),
      
      "khbm" : $("#khbm").val(),
      "bkhbm" : $("#bkhbm").val(),
      "khrkhbm" : $("#khr_khbm").val(),
      "fzrbkhbm" : $("#fzr_bkhbm").val(),
      "cjzrkhbm" : $("#cjzr_khbm").val(),
      "cjzrbkhbm" : $("#cjzr_bkhbm").val(),
      "bmjlkhbm" : $("#bmjl_khbm").val(),
      "bmjlbkhbm" : $("#bmjl_bkhbm").val(),
  }
  
//  alert(JSON.stringify(obj));
//  if(obj.pj1 == '') {
//    $("#pj1").focus();
//    alert("目标管理1是否符合要求！");
//    return false;
//  }
  
  var i = layer.load();
  $.post(getRootPath() + "/company/study/targetassessment-save", obj,function(result){
    layer.close(i);
    if(result.status == '1') {
    	layer.alert(result.map.message);
    } else {
    	//layer.alert("保存成功！");
        window.location.href = "/company/study/targetassessment";
    }
  })
}
</script>
</html>