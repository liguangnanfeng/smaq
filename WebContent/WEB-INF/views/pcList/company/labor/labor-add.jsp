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
<%@ include file="/WEB-INF/inc/kindeditor.inc"%>
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
</head>
<script type="text/javascript">
var id = '${p.id}';
var list1 = '普通防护服,普通工作帽,普通工作鞋,劳动防护手套,防寒服,雨衣,胶鞋,防噪声耳塞（耳罩）'.split(",");
var list2 = '防护足趾安全鞋,防刺穿鞋,电绝缘鞋,防静电鞋,耐酸碱皮鞋,耐酸碱胶靴,防砸安全靴,防静电工作服,防酸工作服,阻燃防护服,安全带,安全帽,焊接眼面防护具,防冲击眼护具,防尘口罩,过滤式防毒面具'.split(",");

var libId = '${p.libId}';
var name = '${p.name}';
$(function() {
  $("#level1").change(function() {
    if($("#level1").val() == '' || $("#level1").val() == '3') {
      $("#name").show();
      $("#level2").hide();
    } else {
      $("#name").hide();
      $("#level2").show();
      
      $("#level2").empty().append("<option value=''>请选择</option>");
      $.each($(this).val() == '1' ? list1 : list2,function(i, item) {
        $("#level2").append("<option>" + item + "</option>");
      })
    }
  })
  if(libId != '') {
    $("#level1").val(libId).change();
    $("#level2").val(name);
  }
})

function save() {
  if($("#level1").val() == '') {
    layer.alert("请选择劳动用品类型")
    return false;
  }
  var name = $("#name").val();
  if($("#level1").val() != '3') {
    name = $("#level2").val();
  }
  if(name == '') {
    layer.alert("劳动用品名称不能为空");
    return false;
  }
  var i = layer.load();
  $.post(getRootPath() + "/company/labor/labor-save", {
    id : id,
    libId : $("#level1").val(),
    name : name,
    work : $("#work").val(),
    cycle : $("#cycle").val(),
    nextDate : $("#nextDate").val(),
    number : $("#number").val(),
    memo : $("#memo").val()
  },function(result) {
    layer.close(i);
    if(result.status == '') {
      layer.alert(result.map.message);
    } else {
        show_tab('劳动防护用品配备', '/company/labor/labor-list');
    }
  })
}
</script>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> <span class="c-gray en">&gt;</span> <span>职业健康管理</span> <span class="c-gray en">&gt;</span> <span>添加劳动防护用品配备</span>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <form class="form form-horizontal" id="form">
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">劳动用品类型：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <select id="level1" class="sel_area" style="width: 150px;">
            <option value="">请选择</option>
            <option value="1" <c:if test="${p.libId == 1}"> selected</c:if>>一般劳动防护用品</option>
            <option value="2" <c:if test="${p.libId == 2}"> selected</c:if>>特种劳动防护用品</option>
            <option value="3" <c:if test="${p.libId == 3}"> selected</c:if>>其他</option>
          </select> 
        </div> 
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">劳动用品名称：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <select id="level2" class="sel_area" style="width: 150px;display: none;">
            <option value="">请选择</option>
          </select>
          <input type="text" id="name" value="${p.name }" style="width:350px;" class="input-text required">
        </div> 
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">工种：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="text" id="work" value="${p.work }" style="width:350px" class="input-text required"  >
        </div> 
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">发放日期：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="text" id="cycle" value="${p.cycle }" style="width:350px" class="input-text required"  >
        </div> 
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">下次发放日期：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="text" id="nextDate" value="${p.nextDate }" style="width:350px" class="input-text required"  >
        </div> 
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">数量：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="text" id="number" value="${p.number }" style="width:350px" class="input-text required"  >
        </div> 
      </div>
      <%-- <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">其他：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="text" id="memo" value="${p.memo }" style="width:350px" class="input-text required"  >
        </div> 
      </div> --%>
      <div class="row cl">
        <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
          <button onClick="save()" class="btn btn-primary radius" type="button" style="padding: 0 70px;"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
        </div>
      </div>
    </form>
  </div>
</body>
</html>