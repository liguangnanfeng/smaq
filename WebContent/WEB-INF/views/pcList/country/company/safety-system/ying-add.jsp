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
<link rel="stylesheet" type="text/css" href="${ly}/styles/print-tab.css" media="print" />
<link rel="stylesheet" type="text/css" media="print"  href="${ly}/js/H-ui.admin_v3.0/static/h-ui/css/H-ui.css" />
<script src="/js/jquery.jqprint-0.3.js"></script>
<style type="text/css" media="print">
.input-text{border:0px;}
</style>
<script type="text/javascript">
function chosem2(){
  var departId = $("#departId").val();
  if(departId != '') {
    $("#win-add2").modal("show");
  } else {
    layer.alert("请选择岗位")
  }
}

function chosem3(obj) {
  window.fzrId = $(obj).attr("id");
  var value = $(obj).val();
  if(value != '') {
    $(":radio[value='"+value+"']").prop("checked", true);
  } else {
    $(":radio:checked").prop("checked", false);
  }
  $("#win-add3").modal("show");
}

function pr_() {
  $("#div_container").jqprint();
}

function per_choose() {
  if($(":radio:checked").length == 0) {
    layer.alert("请选择人员");
    return false;
  }
  var obj = {"id" : $("#id").val()}
  var fzr = $(":radio:checked").val();
  if(fzrId == 'fzr1') {
    obj.fzr1 = fzr
  }
  if(fzrId == 'fzr2') {
    obj.fzr2 = fzr
  }
  if(fzrId == 'fzr3') {
    obj.fzr3 = fzr
  }
  $.post("/company/safety-system/ying-update", obj,function(result) {
    $("#"+ fzrId).val(fzr);
    $("#win-add3").modal("hide");
  })
}

function sh_choose() {
  var i = 1;
  var shIds = [];
  $("#xx_table tbody tr").each(function(){
    if(!$(this).hasClass("tr-bq")){
      $(this).remove();
    }
  });
  $(":checkbox:checked").each(function() {
    var tr = $(this).closest("tr");
    shIds.push($(this).val());
    $("#xx_table tbody").append('<tr><td class="text-c">' + i + '</td><td>' 
        + tr.children("td").eq(1).html() +'</td><td>' + tr.children("td").eq(2).html() +'</td></tr>');
    i++;
  })
  if($("#shIds").val() != shIds.join(",")) {
    $.post("/company/safety-system/ying-update", {
      id : $("#id").val(),
      shIds : shIds.join(",")
    },function(result) {
      $("#shIds").val(shIds.join(","));
      $("#win-add2").modal("hide");
    })
  }
}

$(function() {
    load();
  $("#departId").change(function() {
    load();
  })
})

function load() {
  var departId = $("#departId").val();
  if(departId != '') {
    $("#div_container").load("/company/safety-system/ying-load", {
      departId : departId
    },function(result) {
      $(":checkbox").prop("checked", false);
      var shIds = $("#shIds").val();
      if(shIds != '') {
        $.each(shIds.split(","),function(i,item) {
          $(":checkbox[value='"+item+"']").prop("checked", true);
        })
        sh_choose();
      }
    })
  }
}
</script>
</head>
<body>
   <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>风险分级管控</span> 
    <span class="c-gray en">&gt;</span> <span>岗位应急处置卡</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:history.back(-1)" title="返回">返回</a>
  </nav>
  <div class="page-container">
    <div class="text-c">
      <div class="dis-ib" style="vertical-align: top;">
        <span>岗位：</span>
        <select name="" id="departId" class="sel_area">
          <c:forEach items="${depL }" var="be">
          <option value="${be.id }">${be.parName }/${be.name }</option>
          </c:forEach>
        </select>
      </div> 
      <button onClick="chosem2()" class="btn btn-primary radius" type="button">
        <i class="Hui-iconfont">&#xe692;</i>选择岗位存在的事故类型
      </button>
      <button onClick="pr_()" class="btn btn-success radius ml-20" type="button">
        <i class="Hui-iconfont">&#xe652;</i>打印
      </button>
    </div>
    
    <div id="div_container" class="tab-ying" style="width:220mm;margin:auto;">
     <%--  <h3 class="text-c mt-10">岗位应急处置卡</h3>
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <tbody>
          <tr class="text-c">
            <td>岗位名称</td>
            <td colspan="2">
              <input type="text" id="" value="" class="input-text required" readonly="readonly" onclick="chosem()" placeholder="请选择岗位">
            </td>
          </tr>
          <tr class="text-c">
            <td>序号</td>
            <td>本岗位存在的事故类型</td>
            <td>应急处置措施</td>
          </tr>
          <tr>
            <td class="text-c">1</td>
            <td></td>
            <td></td>
          </tr>
      </table>
      <table class="table table-border table-bordered table-bg table-hover table-sort" style="border-top:0;">
        <tbody>
          <tr class="text-c">
            <td colspan="4" style="border-top:0;">应急联系方式</td>
          </tr>
          <tr class="text-c">
            <td rowspan="2">内部</td>
            <td>主要负责人</td>
            <td>部门负责人</td>
            <td>工段负责人</td>
          </tr>
          <tr>
            <td><input type="text" id="" value="" class="input-text required" readonly="readonly" onclick="chosem2()" placeholder="请选择负责人"></td>
            <td><input type="text" id="" value="" class="input-text required" readonly="readonly" onclick="chosem2()" placeholder="请选择负责人"></td>
            <td><input type="text" id="" value="" class="input-text required" readonly="readonly" onclick="chosem2()" placeholder="请选择负责人"></td>
          </tr>
          <tr class="text-c">
            <td rowspan="2">外部</td>
            <td>火警电话</td>
            <td>急救电话</td>
            <td>报警电话</td>
          </tr>
          <tr class="text-c">
            <td>119</td>
            <td>120</td>
            <td>120</td>
          </tr>
        </tbody>
      </table> --%>
    </div>
  </div>
    
    <div id="win-add2" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog" style="width: 900px">
        <div class="modal-content radius">
          <div class="modal-header">
            <h3 class="modal-title">选择伤害类型</h3>
            <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
          </div>
          <div class="modal-body">
            <table class="table table-border table-bordered table-bg table-hover table-sort" style="float:none;">
              <thead>
                <tr class="text-c">
                  <th style="width:50px;">&nbsp;</th>
                  <th style="min-width:150px">本岗位存在的事故类型</th>
                  <th>应急处置措施</th>
                </tr>
              </thead>
              <c:forEach items="${shL }" var="be">
              <tr class="text-c">
                <td><input type="checkbox" name="shL" value="${be.id }"></td>
                <td>${be.name }</td>
                <td>${be.measures }</td>
              </tr>
              </c:forEach>
            </table>
          </div>
          <div class="modal-footer">
            <button class="btn btn-primary" onclick="sh_choose()">确定</button>
            <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
          </div>
        </div>
      </div>
    </div>
    
    <div id="win-add3" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog" style="width: 900px">
        <div class="modal-content radius">
          <div class="modal-header">
            <h3 class="modal-title">选择责任人</h3>
            <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
          </div>
          <div class="modal-body">
            <table class="table table-border table-bordered table-bg table-hover table-sort" style="float:none;">
              <thead>
                <tr class="text-c">
                  <th style="width:50px;">&nbsp;</th>
                  <th>姓名</th>
                  <th>公司</th>
                  <th>部门</th>
                  <th>班组</th>
                  <th>职务</th>
                </tr>
              </thead>
              <c:forEach items="${perL }" var="be">
              <tr class="text-c">
                <td><input type="radio" name="radio-2" value="${be.name }${empty be.mobile ? '':','}${be.mobile}"></td>
                <td>${be.name }</td>
                <td>${be.companyName }</td>
                <td>${be.level == 1 ? be.dname : be.dpname}</td>
                <td>${be.level == 1 ? '' : be.dname}</td>
                <td>${be.position }</td>
              </tr>
              </c:forEach>
            </table>
          </div>
          <div class="modal-footer">
            <button class="btn btn-primary" onclick="per_choose()">确定</button>
            <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
          </div>
        </div>
      </div>
    </div>
</body>
</html>