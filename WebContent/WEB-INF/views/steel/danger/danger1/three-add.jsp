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
body .check-box, .radio-box{line-height:30px;}
</style>
<script type="text/javascript">
$(function() {
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
})
</script>
</head>
<body>
<nav class="breadcrumb">
  <i class="Hui-iconfont">&#xe67f;</i>
  <span>首页</span>
  <span class="c-gray en">&gt;</span>
  <span>隐患排查治理系统</span>
  <span class="c-gray en">&gt;</span>
  <span>第三方检查隐患</span>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
    <i class="Hui-iconfont">&#xe68f;</i>
  </a>
</nav>
<div class="page-container">
  <form class="form form-horizontal" id="form">
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">检查表名称：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="title" value="" style="width:350px" class="input-text" maxlength="50" placeholder="根据检查需要填写或空缺"/>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">${flag == 1 ? '检查' : '录入'}时间：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="realTime" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="input-text Wdate mb-5 mt-5" style="width:350px;">
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">检查单位：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="checkCompany" value="" style="width:350px" class="input-text" maxlength="50"/>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">检查人：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="cheker" value="" style="width:350px" class="input-text" maxlength="50"/>
      </div> 
    </div>
    <div class="row cl" style="display: none;">
      <label class="form-label col-xs-4 col-sm-2">受检单位：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="depart" value="${session_user.userName }" style="width:350px" class="input-text required" maxlength="50"/>
      </div> 
    </div>
    <div class="row cl" style="display: none;">
      <label class="form-label col-xs-4 col-sm-2">受检单位负责人：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="dapartContact" value="" style="width:350px" class="input-text required" maxlength="50"/>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">检查部位：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <button class="btn radius btn-warning size-M" type="button" onClick="show_dialog('添加检查类别', '${ly }/company/three-window')"><i class="Hui-iconfont">&#xe600;</i>添加检查类别</button>
        <!-- 判断添加检查部位后才显示 -->
        <%-- <table class="table table-border table-bordered table-bg table-hover table-sort mt-20">
          <thead>
            <tr class="text-c">
              <th width="5%">序号</th>
              <th width="10%">检查部位</th>
              <th width="15%">检查项</th>
              <th width="15%">检查内容</th>
              <th width="10%">检查结果</th>
              <th width="15%">隐患图片</th>
              <th width="15%">隐患描述</th>
              <th width="15%">操作</th>
            </tr>
          </thead>
          <tbody>
            <!-- 循环检查项目-->
            <tr class="text-c" >
              <td>1</td>
              <td>xx</td>
              <td>xx</td>
              <td>xx</td>
              <td>xx</td>
              <td><img src="${ly }/images/zwtp.jpg"/></td>
              <td>xx</td>
              <td>
                <a style="text-decoration:none" onclick="del(this)" href="javascript:;" title="删除">删除</a>
              </td>
            </tr>
            <!-- 循环结束 -->
          </tbody>
        </table> --%>
      
      </div> 
      <div class="formControls col-xs-8 col-sm-9" style="margin-left: 100px;margin-top: 20px;">
        <ul class="div_fold">
            <!-- <li class="item" data-n="">
              <div class="title_item">
                <h4><b>+</b>3123123</h4>
                <a class="btn_ejc" style="text-decoration:none" onclick="part_edit(this)" href="javascript:;" title="编辑检查项">编辑检查项</a>
                <a class="btn_djc" style="text-decoration:none" onclick="part_del(this)" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe609;</i></a>
              </div>
              <div class="info">
                <table class="table table-border table-bordered table-bg table-hover table-sort">
                  <thead>
                    <tr class="text-c">
                      <th width="5%">序号</th>
                      <th>检查项目</th>
                      <th>检查内容</th>
                      <th>检查结果</th>
                      <th>隐患图片</th>
                      <th>隐患描述</th>
                      <th>操作</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr class="text-c" data-lid="">
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td>
                        <a style="text-decoration:none" onclick="del(this)" href="javascript:;" title="删除">删除</a>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </li> -->
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
<script type="text/javascript">
var id_ = '${check.id}';
var flag = '${flag}';
function part_edit(obj) {
  var li = $(obj).closest("li");
  var index = li.index();
  show_dialog('编辑检查部位', '/company/three-window?index=' + index);
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

function article_save_submit() {
  var ind = layer.load();
  var obj = new Object();
  var check = new Object();
  check.id = id_;
  check.status = 2;
  check.flag = flag;
  check.title = $("#title").val();
  check.realTime = $("#realTime").val();
  if(check.realTime == '') {layer.close(ind);layer.alert("请输入${flag == 1 ? '检查' : '录入'}时间");return false;}
  
  check.checkCompany = $("#checkCompany").val();
  //if(check.checkCompany == '') {layer.close(ind);layer.alert("请输入检查单位");return false;}
  check.cheker = $("#checker").val();
  //if(check.cheker == '') {layer.close(ind);layer.alert("请输入人员");return false;}
  check.depart = $("#depart").val();
  //if(check.depart == '') {layer.close(ind);layer.alert("请输入受检部门");return false;}
  check.dapartContact = $("#dapartContact").val();
  //if(check.dapartContact == '') {layer.close(ind);layer.alert("请输入受检部门负责人");return false;}
  obj.check = check;
  var list = new Array();
  $(".div_fold li").each(function() {
    var o = new Object();
    o.name = $(this).attr("data-n");
    var l = new Array();
    $(this).find("#tbody2 tr").each(function() {
      var i = new Object();
      var tds = $(this).children("td");
      i.levelId = null;
      i.reference = '';
      i.partId = null;
      i.levels = tds.eq(1).text();
      i.content = '';
      i.status = 2;
      i.memo = tds.eq(2).text();
      i.files = "";
      if(tds.eq(3).find("img").length > 0) {
        i.files = tds.eq(3).find("img").attr("src");
      }
      l.push(i);
    })
    o.list = l;
    
    list.push(o);
  })
  
  obj.partList = list;
  if(list.length == 0) {
    layer.close(ind)
    layer.alert("'检查检查部位'不能为空");
    return false;
  }
  //alert(JSON.stringify(obj))
  $.ajax({
    type : "POST",
    url : getRootPath() + '/company/three-save',
    data : JSON.stringify(obj),
    async: false,
    dataType : "json",
    contentType : 'application/json',
    success : function(result) {
      layer.close(ind);
      if(result.status == '0') {
          show_tab("隐患排查记录", getRootPath() + "/village/check-list?flag=" + flag);
      } else {
        layer.alert(result.map.message);
      }
    },
    complete : function(XMLHttpRequest, textStatus) {
      layer.close(ind);
    },
    error : function(XMLHttpRequest, textStatus, errorThrown) {
      layer.close(ind);
      layer.alert("保存失败");
    }
  });
}
</script>
</body>
</html>