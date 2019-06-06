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
	    <span class="c-gray en">&gt;</span> <span>检维修作业流程</span> 
  	<c:if test="${action != 'read'}">
        <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;" href="javascript:history.back(-1)" title="返回">返回</a>
        <%--<a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="parent.close_tab(function(){})">返回</a>--%>
    </c:if>
	</nav>
	<h3 class="text-c">《对检修作业现场进行安全检查的记录》</h3>
    <div class="mt-20">
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 15%;">项目</td>
    	<td style="width: 60%;">检维修作业现场检查项目</td>
		<td>评价结果</td>
		</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 15%;">现场作业</td>
    	<td style="width: 60%;">是否遵守安全生产规章制度和安全操作规程：</td>
        <td id="xczy">
            <div class="radio-box">
              <input type="radio" name="xczy" id="xczy_s" checked = "checked" value="1"><label for="xczy_s">是</label>
            </div>
            <div class="radio-box">
              <input type="radio" name="xczy" id="xczy_f" value="0"><label for="xczy_f">否</label>
            </div>
          </td>
    	</tr>
    </table> 
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 15%;">安全防护用户品穿戴</td>
    	<td style="width: 60%;">根据劳动防护用品管理制度和发放标准是否按规定穿戴：</td>
        <td id="aqfhyhpcd">
            <div class="radio-box">
              <input type="radio" name="aqfhyhpcd" id="aqfhyhpcd_s" checked = "checked" value="1"><label for="aqfhyhpcd_s">是</label>
            </div>
            <div class="radio-box">
              <input type="radio" name="aqfhyhpcd" id="aqfhyhpcd_f" value="0"><label for="aqfhyhpcd_f">否</label>
            </div>
          </td>
    	</tr>
    </table>
    
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 15%;">相关证书</td>
    	<td style="width: 60%;">现场抽查检维修作业人员操作证书是否携带相关证书： </td>
        <td id="xgzs">
            <div class="radio-box">
              <input type="radio" name="xgzs" id="xgzs_s" checked = "checked" value="1"><label for="xgzs_s">是</label>
            </div>
            <div class="radio-box">
            	<input type="radio" name="xgzs" id="xgzs_f" value="0"><label for="xgzs_f">否</label>
            </div>
          </td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    		<td style="width: 15%;">检查时间</td>
    		<td style="width: 20%;"><input id="jcsj" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
    		<td style="width: 15%;">检查部门</td>
    		<td style="width: 15%;"><input id="jcbm" type="text"/></td>
    		<td style="width: 15%;">参与检查人员</td>
    		<td style="width: 15%;"><input id="cyjcry" type="text"/></td>
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
if("${action}" == "read"){
	
	$("input").attr("disabled","disabled");
	$("input").css("background-color","white");
	
    $("input[name='xczy'][value='${oh.xczy}']").attr("checked","checked");
    $("input[name='aqfhyhpcd'][value='${oh.aqfhyhpcd}']").attr("checked","checked");
    $("input[name='xgzs'][value='${oh.xgzs}']").attr("checked","checked");
	
	$("#jcsj").val("<fmt:formatDate value='${oh.jcsj}' pattern='yyyy-MM-dd HH:mm'/>");
    $("#jcbm").val("${oh.jcbm }");
    $("#cyjcry").val("${oh.cyjcry }");

}
function save() {
  var obj = {
      "xczy" : $("input[name='xczy']:checked").val(),
      "aqfhyhpcd" : $("input[name='aqfhyhpcd']:checked").val(),
      "xgzs" : $("input[name='xgzs']:checked").val(),
      
      "jcsj" : $("#jcsj").val(),
      "jcbm" : $("#jcbm").val(),
      "cyjcry" : $("#cyjcry").val(),
  }
  
  //alert(JSON.stringify(obj));
  if(obj.jcsj == '') {
    $("#jcsj").focus();
    alert("请填写检查时间！");
    return false;
  }
  if(obj.jcbm == '') {
    $("#jcbm").focus();
    alert("请填写检查部门！");
    return false;
  }
  if(obj.cyjcry == '') {
	    $("#cyjcry").focus();
	    alert("请填写参与检查人员 ！");
	    return false;
	  }
  //alert($("#sqdwqzsj").val());
  var i = layer.load();
  $.post(getRootPath() + "/company/study/overhaul-save", obj,function(result){
    layer.close(i);
    if(result.status == '1') {
    	layer.alert(result.map.message);
    } else {
    	layer.alert("保存成功！",{},function(i){
    		window.location.href = '${ly }/company/study/select?type=2';
    		
    	});
        //parent.location.reload();
    }
  });
}
</script>
</html>