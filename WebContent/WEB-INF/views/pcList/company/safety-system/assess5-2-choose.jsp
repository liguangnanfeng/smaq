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
.col-b,.col-a,.col-c,.col-d{margin:0;padding:0 15px;height:25px;line-height:25px;}
</style>
<script type="text/javascript" src="${ly }/js/fxgk/zdsg.js?v=11"></script>
<script type="text/javascript">
var zdfx = ${list};
$(function() {
  var tbody_hg_1 = $("#hg_1");
  $.each(hg_1, function(i, item) {
    tbody_hg_1.append('<tr><td><p>'+(i+1)+". "+item[3]+'</p></td><td class="text-c" style="width:50px">' + 
        '<input type="checkbox" data-level3="'+item[0]+'" data-type="'+item[2]+'" data-flag2="'+item[1]+'" value="' + item[3] + '"></td></tr>');
  })
  var tbody_hg_2 = $("#hg_2");
  $.each(hg_2, function(i, item) {
    tbody_hg_2.append('<tr><td><p>'+(i+1)+". "+item+'</p></td><td class="text-c" style="width:50px">' + 
        '<input type="checkbox" data-level3="" data-type="" data-flag2="1" value="' + item + '"></td></tr>');
  })
  
  $.each(zdfx,function(i, item) {
    $(":checkbox[value='" + item.factors + "']").prop("checked", true);
  })
})

function save() {
  var list = [];
  $(":checkbox:checked").each(function() {
    var $this = $(this);
    list.push({
      flag : 2,
      level3 : $this.attr("data-level3"),
      level : '红色',
      factors : $this.val(),
      type : $this.attr("data-type"),
      flag2 : $this.attr("data-flag2")
    })
  })
  $.post("/company/safety-system/a-zdfxys-save2", {
    json : list.length > 0 ? JSON.stringify(list) : ''
  },function(result) {
    parent.location.reload();
  })
}
</script>
</head>
<body>
  <div class="page-container">
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort tab-ndan">
        <thead>
          <tr class="text-c">
            <th colspan="2">化工和危险化学品生产经营单位重大风险判断</th>
          </tr>
        </thead>
        <tbody id="hg_1">
          
        </tbody>
      </table>
      <table class="table table-border table-bordered table-bg table-hover table-sort tab-ndan">
        <thead>
          <tr class="text-c">
            <th colspan="2">烟花爆竹生产经营单位重大风险判断</th>
          </tr>
        </thead>
        <tbody id="hg_2">
            
        </tbody>
      </table>
      <div class="mt-20 text-c f-l mb-20" style="width:100%">
        <button onClick="save()" class="btn btn-primary radius" type="button" style="padding: 0 70px;">
          <i class="Hui-iconfont mr-10">&#xe632;</i>保存
        </button>
      </div>
    </div>
  </div>
  
  
</body>
</html>