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
.check-box{padding:0;width:17%;text-align:left;margin-left:3%}
.radio-lx{display:inline-block;cursor:pointer;height:50px;font-size:14px;line-height:50px;margin-bottom:10px;padding:0 35px;margin-right:30px;background:#f5f5f5;border-radius:5px;}
.radio-lxxz{background:#D9EDF7;color:#31708F}
</style>
<script type="text/javascript">
var checkId = '${check.id}';
var type=${type};
var flag=${flag};
var industryId = '', industryType = '';
var userId = ${company.userId};
$(function(){
  $(".div_fold").on("click", ".item h4", function(){
    var info=$(this).closest(".item").find(".info");
    if(info.is(":hidden")){
      info.show();
      $(this).find("b").html("-");
    }else{
      info.hide();
      $(this).find("b").html("+");
    }
  })
});
function choose_() {
  $("#jfry").modal("show");
}

//设置检查类型
function indu_(i,t) {
  if(industryId != i && $(".div_fold li").length > 0) {
    layer.confirm("切换检查类型会清空重置检查部位，是否确定切换？", function(i) {
      layer.close(i);
      $(".div_fold").empty();
      industryId = i;
      industryType = t;
      $(".radio-lx").removeClass("radio-lxxz");
      $(".radio-lx[type='"+t+"']").addClass("radio-lxxz");
    })
  } else {
      industryId = i;
      industryType = t;
      $(".radio-lx").removeClass("radio-lxxz");
      $(".radio-lx[type='"+t+"']").addClass("radio-lxxz");
  }
}

//显示检查内容选择弹窗
function show_add_dialog() {
  if(industryId == '') {
    layer.alert("请选择检查类型");
    return false;
  } else {
    show_dialog('添加检查类别', '/company/check-part-add?industryId=' + industryId);
  }
}
</script>
</head>
<body>
<div class="page-container">
  <form class="form form-horizontal" id="form">
    <div class="row cl" style="display: none;">
      <label class="form-label col-xs-4 col-sm-2">检查表名称：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="title" value="${check.title }" style="width:350px" class="input-text" maxlength="50" placeholder="根据检查需要填写或空缺"/>
      </div> 
    </div>
    <div class="row cl" style="display: none;">
      <label class="form-label col-xs-4 col-sm-2">受检${flag == 1 ? '部门' : '单位'}：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="depart" value="${empty check.depart ? company.name : check.depart}" style="width:350px" class="input-text required" maxlength="50"/>
      </div> 
    </div>
    <div class="row cl" style="display: none;">
      <label class="form-label col-xs-4 col-sm-2">受检${flag == 1 ? '部门' : '单位'}负责人：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="dapartContact" value="${empty check.dapartContact ? company.legal : check.dapartContact}" style="width:350px" class="input-text required" maxlength="50"/>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2" style="line-height:50px;">检测类型：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <div class="radio-lx" onclick="indu_(${ind1.id },1)" type="1">基础管理</div>
        <c:forEach items="${ind2L }" var="be">
          <div class="radio-lx" onclick="indu_(${be.id },2)" type="2">现场管理-${be.name }</div>
        </c:forEach>
        <c:forEach items="${ind3L }" var="be" varStatus="index">
          <div class="radio-lx" onclick="indu_(${be.id },${index.index + 3})" type="${index.index + 3}">高危作业-${be.name }</div>
        </c:forEach>
      </div> 
    </div>
    <div class="row cl" style="display: none;">
      <label class="form-label col-xs-4 col-sm-2">检查人：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="cheker" value="${check.cheker }" style="width:350px" class="input-text" maxlength="500"/>
        <c:if test="${!empty jcL && (flag == '2' || flag == '4')}">
        <button type="button" onclick="choose_()" class="btn btn-primary radius">从执法人员库中选择</button>
        </c:if>
      </div> 
    </div>
    <div class="row cl" style="display: none;">
      <label class="form-label col-xs-4 col-sm-2">检查部门/单位：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="checkCompany" value="${(empty check.checkCompany && flag == 3) ? '无锡市安泰安全技术服务有限公司' : check.checkCompany}" style="width:350px" class="input-text" maxlength="500"/>
      </div> 
    </div>
    
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">检查部位：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <button class="btn radius btn-warning size-M" type="button" onClick="show_add_dialog();"><i class="Hui-iconfont">&#xe600;</i>添加检查类别</button>
      </div> 
      <div class="div_model mt-20">
        <ul class="div_fold">
          <!-- 循环检查部位 -->
          <c:forEach items="${partL }" var="p">
            <li class="item" data-n="${p.name }">
              <div class="title_item">
                <h4><b>+</b>${p.name }</h4>
                <a class="btn_ejc" style="text-decoration:none" onclick="part_edit(this)" href="javascript:;" title="编辑检查项">编辑检查项</a>
                <a class="btn_djc" style="text-decoration:none" onclick="part_del(this)" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe609;</i></a>
              </div>
              <div class="info">
                <table class="table table-border table-bordered table-bg table-hover table-sort">
                  <thead>
                    <tr class="text-c">
                      <th width="5%">序号</th>
                      <th width="80%">检查项目</th>
                      <th width="15%">操作</th>
                    </tr>
                  </thead>
                  <tbody>
                    <!-- 循环检查项目-->
                    <c:set value="0" var="x"/>
                    <c:forEach items="${levelL }" var="l">
                    <c:if test="${lanlin:constains(p.levels, l.id, ',')}">
                    <c:set value="${x + 1}" var="x"/>
                    <tr class="text-c" data-lid="${l.id }">
                      <td>${x }</td>
                      <td>${l.level1 }/${l.level2 }<c:if test="${!empty l.level3}">/${l.level3 }</c:if></td>
                      <td>
                        <a style="text-decoration:none" onclick="del(this)" href="javascript:;" title="删除">删除</a>
                      </td>
                    </tr>
                    </c:if>
                    </c:forEach>
                    <!-- 循环结束 -->
                  </tbody>
                </table>
              </div>
            </li>
          </c:forEach>
          <!-- 循环结束 -->
        </ul>
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
function save_zfry() {
  var s = '';
  $(":checkbox:checked").each(function(i, item) {
    var v = $(this).val();
    if(s != '') {
      s += ",";
    }
    s += v;
  })
  if(s == '') {
    alert("请选择执法人员");
    return false;
  }
  $("#cheker").val(s);
  $('#jfry').modal("hide");
}
function article_save_submit() {
  var index = layer.load();
  /* if($("#depart").val() == '') {
    layer.close(index);
    layer.alert("请输入受检" + flag == 1 ? '部门' : '单位');
    return false;
  } */
  
  if($(".div_fold li").length == 0) {
    layer.close(index);
    layer.alert("请添加检查部位");
    return false;
  }
  
  var obj = new Object();
  
  var check = new Object();
  check.id = checkId;
  check.userId = userId;
  check.title = $("#title").val();
  check.type = type;
  check.flag = flag;
  check.depart = $("#depart").val();
  check.industryId = industryId;
  check.industryType = industryType;
  check.cheker = $("#cheker").val();
  check.dapartContact = $("#dapartContact").val();
  check.checkCompany = $("#checkCompany").val();
  //check.expectTime = $("#expectTime").val();
  check.del = 0;
  obj.check = check;
  
  var partList = new Array();
  $(".div_fold li").each(function(){
    var partN = $(this).attr("data-n");
    var levels = '';
    $(this).find("tbody").children("tr").each(function(i, item){
      var i = $(item).attr("data-lid");
      levels = levels==''? i : levels + "," + i;
    })
    
    var part = new Object();
    part.name = partN;
    part.levels = levels;
    partList.push(part);
  })
  
  obj.partList = partList;
  //alert(JSON.stringify(obj))
  $.ajax({
    type : "POST",
    url : getRootPath() + '/village/check-add-do',
    data : JSON.stringify(obj),
    async: false,
    dataType : "json",
    contentType : 'application/json',
    success : function(result) {
      layer.close(index);
      if(result.status == '0') {
        layer.confirm("保存成功，是否继续添加检查表？", function(i) {
          layer.close(i);
          location.reload();
        },function(i) {
          layer.close(i);
          window.location.href = getRootPath() + "/village/plan-next?id=" + result.map.checkId;
        })
      } else {
        layer.alert(result.map.message);
      }
    },
    complete : function(XMLHttpRequest, textStatus) {
      layer.close(index);
    },
    error : function(XMLHttpRequest, textStatus, errorThrown) {
      layer.close(index);
      alert("保存失败")
    }
  });
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
  show_dialog('编辑检查部位', getRootPath() + '/company/check-part-add?industryId=' + industryId + "&partN=" + partN + "&levels=" + levels + "&index=" + index);
}
</script>
</html>