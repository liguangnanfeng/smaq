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
	    <span class="c-gray en">&gt;</span> <span>承包商作业流程</span> 
  	<c:if test="${action != 'read'}"><a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="parent.close_tab(function(){})">返回</a>   </c:if>
	</nav>
	<h3 class="text-c">《承包商评价表》</h3>
    <div class="mt-20">
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">承包商名称</td>
    	<td style="width: 25%;"><input id="cbsmc" type="text"/></td>
    	<td style="width: 25%;">No：</td>
    	<td style="width: 25%;"><input id="no1" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td colspan="3" style="width:75%;">项目</td>
    	<td>评价结果</td>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td colspan="3" style="width:75%;">承包商资质是否符合要求</td>
        <td id="pj1">
            <div class="radio-box">
              <input type="radio" name="pj1" id="pj1_s" checked = "checked" value="1"><label for="pj1_s">是</label>
            </div>
            <div class="radio-box">
              <input type="radio" name="pj1" id="pj1_f" value="0"><label for="pj1_f">否</label>
            </div>
          </td>
    	</tr>
    </table> 
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td colspan="3" style="width:75%;">是否有投诉、违反法律法规事故、事件</td>
        <td id="pj2">
            <div class="radio-box">
              <input type="radio" name="pj2" id="pj2_s" checked = "checked" value="1"><label for="pj2_s">是</label>
            </div>
            <div class="radio-box">
              <input type="radio" name="pj2" id="pj2_f" value="0"><label for="pj2_f">否</label>
            </div>
          </td>
    	</tr>
    </table> 
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td colspan="3" style="width:75%;">是否落实安全规章制度及操作规程要求</td>
        <td id="pj3">
            <div class="radio-box">
              <input type="radio" name="pj3" id="pj3_s" checked = "checked" value="1"><label for="pj3_s">是</label>
            </div>
            <div class="radio-box">
              <input type="radio" name="pj3" id="pj3_f" value="0"><label for="pj3_f">否</label>
            </div>
          </td>
    	</tr>
    </table> 
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td colspan="3" style="width:75%;">关键现场人员资质、安全意识是否符合要求</td>
        <td id="pj4">
            <div class="radio-box">
              <input type="radio" name="pj4" id="pj4_s" checked = "checked" value="1"><label for="pj4_s">是</label>
            </div>
            <div class="radio-box">
              <input type="radio" name="pj4" id="py4_f" value="0"><label for="pj4_f">否</label>
            </div>
          </td>
    	</tr>
    </table> 
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td colspan="3" style="width:75%;">现场人员安全意识和素质是否符合要求</td>
        <td id="pj1">
            <div class="radio-box">
              <input type="radio" name="pj5" id="pj5_s" checked = "checked" value="1"><label for="pj5_s">是</label>
            </div>
            <div class="radio-box">
              <input type="radio" name="pj5" id="pj5_f" value="0"><label for="pj5_f">否</label>
            </div>
          </td>
    	</tr>
    </table> 
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td colspan="3" style="width:75%;">设备是否符合要求</td>
        <td id="pj6">
            <div class="radio-box">
              <input type="radio" name="pj6" id="pj6_s" checked = "checked" value="1"><label for="pj6_s">是</label>
            </div>
            <div class="radio-box">
              <input type="radio" name="pj6" id="pj6_f" value="0"><label for="pj6_f">否</label>
            </div>
          </td>
    	</tr>
    </table> 
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td colspan="3" style="width:75%;">是否有个人防护</td>
        <td id="pj7">
            <div class="radio-box">
              <input type="radio" name="pj7" id="pj7_s" checked = "checked" value="1"><label for="pj7_s">是</label>
            </div>
            <div class="radio-box">
              <input type="radio" name="pj7" id="pj7_f" value="0"><label for="pj7_f">否</label>
            </div>
          </td>
    	</tr>
    </table> 
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td colspan="3" style="width:75%;">是否落实整改纠正措施</td>
        <td id="pj8">
            <div class="radio-box">
              <input type="radio" name="pj8" id="pj8_s" checked = "checked" value="1"><label for="pj8_s">是</label>
            </div>
            <div class="radio-box">
              <input type="radio" name="pj8" id="pj8_f" value="0"><label for="pj8_f">否</label>
            </div>
          </td>
    	</tr>
    </table> 
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td colspan="3" style="width:75%;">现场安全操作情况是否合格</td>
        <td id="pj9">
            <div class="radio-box">
              <input type="radio" name="pj9" id="pj9_s" checked = "checked" value="1"><label for="pj9_s">是</label>
            </div>
            <div class="radio-box">
              <input type="radio" name="pj9" id="pj9_f" value="0"><label for="pj9_f">否</label>
            </div>
          </td>
    	</tr>
    </table> 
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td colspan="3" style="width:75%;">与业主的关系是否良好</td>
        <td id="pj10">
            <div class="radio-box">
              <input type="radio" name="pj10" id="pj10_s" checked = "checked" value="1"><label for="pj10_s">是</label>
            </div>
            <div class="radio-box">
              <input type="radio" name="pj10" id="pj10_f" value="0"><label for="pj10_f">否</label>
            </div>
          </td>
    	</tr>
    </table> 
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    		<td>参加评价人员</td>
    		<td colspan="3" style="width:75%;"><input id="cjpjry" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    		<td>评价结论</td>
    		<td colspan="3" style="width:75%;"><input id="pjjl" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td colspan="2" style="width:50%;">批准</td>
    	<td style="width: 25%;">批准时间</td>
    	<td style="width: 25%;"><input id="pzsj" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
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
	
	$("#cbsmc").val("${con.cbsmc}");
	$("#no1").val("${con.no1}");
	
    $("input[name='pj1'][value='${con.pj1}']").attr("checked","checked");
    $("input[name='pj2'][value='${con.pj2}']").attr("checked","checked");
    $("input[name='pj3'][value='${con.pj3}']").attr("checked","checked");
    $("input[name='pj4'][value='${con.pj4}']").attr("checked","checked");
    $("input[name='pj5'][value='${con.pj5}']").attr("checked","checked");
    $("input[name='pj6'][value='${con.pj6}']").attr("checked","checked");
    $("input[name='pj7'][value='${con.pj7}']").attr("checked","checked");
    $("input[name='pj8'][value='${con.pj8}']").attr("checked","checked");
    $("input[name='pj9'][value='${con.pj9}']").attr("checked","checked");
    $("input[name='pj10'][value='${con.pj10}']").attr("checked","checked");
	
	$("#cjpjry").val("${con.cjpjry }");
    $("#pjjl").val("${con.pjjl }");
    $("#pzsj").val("<fmt:formatDate value='${con.pzsj}' pattern='yyyy-MM-dd HH:mm'/>");

}
function save() {
  var obj = {
	  "cbsmc":$("#cbsmc").val(),
	  "no1":$("#no1").val(),
      "pj1" : $("input[name='pj1']:checked").val(),
      "pj2" : $("input[name='pj2']:checked").val(),
      "pj3" : $("input[name='pj3']:checked").val(),
      "pj4" : $("input[name='pj4']:checked").val(),
      "pj5" : $("input[name='pj5']:checked").val(),
      "pj6" : $("input[name='pj6']:checked").val(),
      "pj7" : $("input[name='pj7']:checked").val(),
      "pj8" : $("input[name='pj8']:checked").val(),
      "pj9" : $("input[name='pj9']:checked").val(),
      "pj10" : $("input[name='pj10']:checked").val(),
      
      "cjpjry" : $("#cjpjry").val(),
      "pjjl" : $("#pjjl").val(),
      "pzsj" : $("#pzsj").val(),

  }
  //alert(JSON.stringify(obj));
  if(obj.cbsmc == '') {
    $("#cbsmc").focus();
    alert("请填写承包商名称！");
    return false;
  }
  
  if(obj.pzsj == '') {
    $("#pzsj").focus();
    alert("请填写批准时间 ！");
    return false;
  }
  //alert($("#sqdwqzsj").val());
  var i = layer.load();
  $.post(getRootPath() + "/company/study/contractor-save", obj,function(result){
    layer.close(i);
    if(result.status == '1') {
    	layer.alert(result.map.message);
    } else {
    	layer.alert("保存成功！",{},function(i){
    		window.location.href = '${ly }/company/study/select?type=3';
    		
    	});
        //parent.location.reload();
    }
  });
}
</script>
</html>