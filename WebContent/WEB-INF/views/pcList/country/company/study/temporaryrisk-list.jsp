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
<link rel="stylesheet" type="text/css" media="print"  href="${ly}/js/H-ui.admin_v3.0/static/h-ui/css/H-ui.css" />
<link rel="stylesheet" type="text/css" href="${ly}/styles/common.css" media="print"  />
<style type="text/css" media="print">
body .dis-ib{margin-right:15px;}
.col-b,.col-a,.col-c,.col-d{margin:0;padding:0 15px;height:25px;line-height:25px;}
.div-print{width:90%;margin:auto;padding:20px 0 0 0;}
h3{display:block;width:100%;margin-bottom:15px;}
</style>
<style type="text/css">
body .dis-ib{margin-right:15px;}
.col-b,.col-a,.col-c,.col-d{margin:0;padding:0 15px;height:25px;line-height:25px;}
.div-print{width:90%;margin:auto;padding:20px 0 0 0;}
/* h3{display:none;} */
</style>
</head>
<body>
 <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>风险研判与承诺公告</span> 
    <span class="c-gray en">&gt;</span> <span>运行管理</span> 
    <span class="c-gray en">&gt;</span> <span>临时风险研判</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:history.back(-1)" title="返回">返回</a>
  </nav>
  <div class="page-container" id="div_container" >
    <div class="div-print">
     <h3 class="text-c">${company.name}临时风险研判表</h3>
      <table class="table table-border table-bordered table-bg table-hover table-sort">  
        <thead>
          <tr class="text-c">
            <th style="width:15%">流程名称</th>
            <th style="width:15%">表单名称</th>
            <th style="width:55%">考核项目</th>
            <th style="width:15%">检查结果 <c:if test="${action != 'read'}"><a style="text-decoration: none;color:green;" onclick="setYes()" href="javascript:;">[一键合格]</a></c:if></th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${treeMap }" var="be1" varStatus="index">
          <c:forEach items="${be1.value }" var="be2">
          <c:forEach items="${list }" var="be">
          <c:if test="${(be.sslcmc eq be1.key && be.zyzmc eq be2) || (empty be1.key && empty be.ddlcmc )}">
          <tr>
            <td class="text-c">${be1.key}</td>
            <td class="text-c">《${be2}》</td>
            <td class="text-c">${be.khxm }</td>
            <td data-n="xxx" Tdangerid="${be.id}" class="text-c">
            	<div class="radio-box">
              		<input type="radio" name="pj_${be.id}" id="pj_s" value="1" <c:if test="${be.checkret == 1 }">checked="checked"</c:if>><label for="pj_s">&nbsp;&nbsp;&nbsp;合格&nbsp;&nbsp;</label>
            	</div>
            	<div class="radio-box">
              		<input type="radio" name="pj_${be.id}" id="pj_f" value="0" <c:if test="${be.checkret == 0 }">checked="checked"</c:if>><label for="pj_f">&nbsp;&nbsp;不合格</label>
            	</div>
         	</td>
          </tr>
          </c:if>
          </c:forEach>
          </c:forEach>
          </c:forEach>
        </tbody>
      </table>
     <table class="table table-border table-bordered table-bg table-hover table-sort" cellspacing="0" cellpadding="0"> 
    	<tr>
    		<td class="text-c">检查人员签字：</td>
    		<td class="text-c"><input id="jcry" style="text-align:center;width:100%; height:100%;border:none" type="text" value="${checker }"/></td>
    	</tr>
    </table>
    </div>
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
function setYes() {
	$("input[id='pj_s'][value='1']").attr("checked","checked");	
}
function save() {
	if(!$("#jcry").val()){
		alert("请签字！");
		return;
	}
  var obj = new Object();
  var study = new Object();
  study.uid = ${uid};
  study.checker = $("#jcry").val();
  obj.study = study;
  //alert("study.uid:"+study.uid+",study.checker:"+study.checker);
  
  var list = new Array();
  $("td[data-n='xxx']").each(function() {
    var it = new Object();
    it.tdangerid = $(this).attr("Tdangerid");
    it.checkret = $("input[name='pj_"+it.tdangerid+"']:checked").val();
    list.push(it);
    //alert("it.Tdangerid:"+it.tdangerid+",it.checkret:"+it.checkret);
  })
  obj.list = list;
  
  var ind = layer.load();
  $.ajax({
    type : "POST",
    url : getRootPath() + '/company/study/temporaryrisk-save',
    data : JSON.stringify(obj),
    async: false,
    dataType : "json",
    contentType : 'application/json',
    success : function(result) {
    	//alert(JSON.stringify(result));
      layer.close(ind);
      if(result.status == '0') {
        layer.alert("保存成功！",{},function(i){
    		window.location.href = '${ly }/company/study/temporaryrisk-list';
    	});
      } else {
        layer.alert(result.map.message);
      }
    },
    complete : function(XMLHttpRequest, textStatus) {
      layer.close(ind);
    },
    error : function(XMLHttpRequest, textStatus, errorThrown) {
      alert("保存失败")
    }
  });
}
</script>
</html>