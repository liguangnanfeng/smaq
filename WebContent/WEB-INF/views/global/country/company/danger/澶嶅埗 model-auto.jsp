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
.input-text{position:relative}
.txtarea_sq{height:100px;width:350px;}
.div_model{float:left;width:80%;margin-left:10%}
</style>
<script type="text/javascript">
var modelId = '${model.id}';
</script>
</head>
<body>
<nav class="breadcrumb">
<!--   <i class="Hui-iconfont">&#xe67f;</i>
  <span>首页</span>
  <span class="c-gray en">&gt;</span>
  <span>隐患排查治理系统</span>
  <span class="c-gray en">&gt;</span>
  <span>隐患自查部门或装置</span> -->
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
    <i class="Hui-iconfont">&#xe68f;</i>
  </a>
</nav>
<div class="page-container">
  <form class="form form-horizontal" id="form">
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">检查表名称：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="title" value="${model.title }" style="width:350px" class="input-text" maxlength="50" placeholder="根据检查需要填写或空缺"/>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">受检${model.flag == 1 ? '部门' : '单位'}：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="part" value="${model.part }" style="width:350px" <c:if test="${model.flag == 1}">placeholder="根据实际具体填写部门  如一车间"</c:if> class="input-text required" maxlength="50"/>
      </div> 
    </div>
    
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">自动化设置：</label>
      <div class="formControls col-xs-8 col-sm-9" style="line-height:30px;">
        <input type="radio" name="open" value="0" <c:if test="${model.open == 0}">checked="checked"</c:if>/>关闭
        <input type="radio" name="open" value="1" checked="checked" class="ml-20"/>开启
        <span class="ml-20 ">开启后，系统会自动创建一封检查表，截止时间为报表开始时间</span>
      </div> 
    </div>
    
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">检查表周期(天)：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="cycle" value="${model.cycle }" style="width:350px" class="input-text required ll-numberbox" data-options="min:1,max:365"/>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">报表开始时间：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" value="<fmt:formatDate value="${model.nextTime }" pattern="yyyy-MM-dd"/>" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd', minDate:'%y-%M-{%d+1}'})" id="nextTime" class="input-text Wdate mb-5 mt-5" style="width:350px;">
      </div> 
    </div>
    
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">备注：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="memo" value="${model.memo }" style="width:350px" class="input-text" maxlength="500"/>
      </div> 
    </div>
    
    <div class="row cl">
      <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2  mt-20">
        <button onClick="article_save_submit()" class="btn btn-primary radius" type="button" style="padding: 0 70px;">
          <i class="Hui-iconfont">&#xe632;</i>保存
        </button>
      </div>
    </div>
  </form>
</div>

</body>
<script type="text/javascript">
function article_save_submit() {
  /* if($("#part").val() == '') {
    layer.alert("请输入受检部门");
    return false;
  } */
  
  var obj = new Object();
  
  var model = new Object();
  model.open = $(":radio:checked").val();
  model.cycle = $("#cycle").val();
  model.nextTime = $("#nextTime").val();
  model.part = $("#part").val();
  model.memo = $("#memo").val();
  if(model.open == 1) {
    if(model.cycle == '') {
      layer.alert("请输入报表生成周期")
      return false;
    }
    if(model.nextTime == '') {
      layer.alert("请选择下次报表自动创建时间")
      return false;
    }
  }
  obj.model = model;
  var index = layer.load();
  $.post(getRootPath() + "/company/model-auto", {
    id : modelId,
    open : model.open,
    cycle : model.cycle,
    nextTime : model.nextTime,
    part : model.part,
    memo : model.memo
  }, function(result) {
    layer.close(index);
    if(result.status == '0') {
    	show_tab("定期检查", "/company/model-list?type=2");
    } else {
      layer.alert(result.map.message);
    }
  })
}

//部门单个检查项删除
function del(obj) {
  var tbody = $(obj).closest("tbody");
  $(obj).closest("tr").remove();
  var trList = tbody.children("tr");
  $.each(trList, function(i, item) {
    $(this).children("td").first().text(i + 1);
  })
  
  if(trList.length == 0) {
    tbody.closest("li").remove();
  }
}

function part_del(obj) {
  $(obj).closest("li").remove();
}

function part_edit(obj) {
  var li = $(obj).closest("li");
  var partN = li.attr("data-n");
  var levels = '';
  li.find("tbody").children("tr").each(function(i, item){
    var i = $(item).attr("data-lid");
    levels = levels==''? i : levels + "," + i;
  })
  var index = li.index();
  show_dialog('编辑检查部位', '/company/model-part-add?industryId=' + industryId + "&partN=" + partN + "&levels=" + levels + "&index=" + index);
}
</script>
</html>