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
<%@ include file="/WEB-INF/inc/print.inc"%>
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style type="text/css">
.div_pri{width:100%;min-height: 32px}
.div_pdetail{width:100%;font-size:15px;}
.div_pri font{float:right;line-height:32px;font-size:14px;margin-right:15px;}
body .radio-box{float:Left;}
body .radio-box{padding-left:0px;padding-right:0;} 
.div_pleft{width:150px;display: inline-block;line-height:32px;font-size:9pt;}
.div_pdetail font{font-size:10pt;margin-left:15px;font-weight:500;}
body .page-container .div_pdetail label{float:right;font-size:10pt;margin-right:130px;}
body th,body td,body .page-container label{font-size:9pt}
body .table-bordered,body .table-bordered th,body .table-bordered td{border:1px solid #000}
body .table-border.table-bordered{border-bottom:1px solid #000;border-collapse: collapse;}
body .table-bg thead th{background:#ddd;padding:20px 8px}
</style>
<script src="${ly }/js/jquery-migrate-1.2.1.min.js"></script>
<script src="${ly }/js/jquery.jqprint-0.3.js"></script>
<script type="text/javascript">
function pr_() {
  $("#div_container").jqprint();
}
</script>
</head>
<body>
<div class="page-container">
  <div class="row cl">
    <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2  mt-20">
      <button onClick="pr_()" class="btn btn-success radius ml-20" type="button" style="padding: 0 70px;">
         <i class="Hui-iconfont mr-10">&#xe652;</i>打印 
      </button>
    </div>
  </div>
      
  <div id="div_container" style="width:180mm;margin:auto;padding:50px 0;">
  <h2 class="text-c mb-20" style="font-size:14pt">主要设备清单</h2>
  <div class="mt-20" style="width:100%">
    <table class="f-l table table-border table-bordered table-bg table-hover table-sort">
      <thead>
        <tr class="text-c">
          <th width="10%">编号</th>
          <th width="25%">名称</th>
          <th width="15%">型号规格</th>
          <th width="15%">工艺参数</th>
          <th width="15%">数量</th>
          <th width="20%">备注</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${list }" varStatus="index" var="be">
            <tr class="text-c">
            <td>${index.index + 1 }</td>
            <td>
              <span>${be.equipmentName }</span>
            </td>
            <td>
              <span>${be.size }</span>
            </td>
            <td>
              <span>${be.processParameters }</span>
            </td>
            <td>
              <span>${be.amount }</span>
            </td>
            <td>
              <span>${be.remark }</span>
            </td>
          </c:forEach>
      </tbody>
    </table>
  </div>
  </div>
</div>
</body>
</html>