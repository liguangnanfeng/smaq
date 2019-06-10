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
.row2{width:100%;margin-top:15px;}
#model_add{display:block;}
</style>
<script type="text/javascript">
$(function(){
  
})

//部位装置保存
function article_save_submit() {
 	var name = $("#name").val();
 	if(name == '') {
 	  name = '检查部位';
 	}
 	if($("#tbody1 tr").length == 0) {
 	  layer.alert("检查项不能为空");
 	  return false;
 	}
 	
}


function item_esc() {
  //$('#model_add').hide();
}

//编辑是使用，将编辑的tr存到全局变量
var item_tr = null;

//保存检查项
function item_save() {
  var levels = $("#levels").val();
  var memo = $("#memo").val();
  var files = $("#files").attr("url");
  
  if(levels == '') {
    layer.alert("请选择检查项");
    return false;
  }
  var tds = '<td></td>' +
            '<td>' + levels + '</td>' +
            '<td>' + memo + '</td>' +
            '<td>' + (files == '' ? '' : '<img src="' + files + '"/>') + '</td>' +
            '<td>' +
              '<a style="text-decoration:none" data-cl="edit" onclick="edit(this)" href="javascript:;" title="编辑">编辑</a>' +
              '<a style="text-decoration:none" onclick="del(this)" href="javascript:;" title="删除">删除</a>' +
            '</td>'
  if(item_tr == null) {
    $("#tbody1").append('<tr class="text-c" data-id="">' + tds + '</tr>')
  } else {
    item_tr.html(tds);
    item_tr = null;
  }
  $("#levels").val('');
  $("#memo").val('');
  $("#files").attr("url", '').attr("src", getRootPath() + '/images/zwtp.jpg');
  //$('#model_add').hide();
  index_load();     
}

//编辑检查项
function edit(obj) {
  item_tr = $(obj).closest("tr");
  var tds = item_tr.children("td");
  
  var levels = tds.eq(1).text();
  var memo = tds.eq(2).text();
  var files = "";
  if(tds.eq(3).find("img").length > 0) {
    files = tds.eq(3).find("img").attr("src");
  }
  $("#levels").val(levels);
  $("#memo").val(memo);
  $("#files").attr("url", files).attr("src", files == '' ? getRootPath() + '/images/zwtp.jpg' : files);
  //$('#model_add').show();
}

//检查项删除
function del(obj) {
  var tr = $(obj).closest("tr");
  tr.remove();
  index_load();
}

//初始化tr 序号
function index_load() {
  $("#tbody1 tr").each(function(i, item){
    $(this).children("td").eq(0).text(i + 1);
  })
}
</script>
</head>
<body>
<div class="page-container">
  <form class="form form-horizontal" id="form">
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">检查部位：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="name" value="" style="width:350px" class="input-text required" maxlength="50" placeholder="根据检查需要填写或空缺"/>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">添加隐患描述：</label>
      <div class="formControls col-xs-8 col-sm-9">
        
      </div> 
    </div>
    <div class="row cl pt-10 pb-10" id="model_add" style="background:#f7f7f7" >
      <div class="row2 cl">
        <label class="form-label col-xs-4 col-sm-2">检查项：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <select id="levels" style="float:Left;width:100px" class="input-text required">
            <option value="">请选择</option>
            <option>基础管理</option>
            <option>现场管理</option>
          </select>
          <label class="form-label col-xs-4 col-sm-2" style="float:Left;width:100px;">隐患描述：</label>
          <textarea id="memo" class="textarea txtarea_sq" style="float:Left;width:200px;"></textarea>
          <label class="form-label col-xs-4 col-sm-2" style="float:Left;width:100px;">隐患图片：</label>
          <img id="files" src="${ly }/images/zwtp.jpg" url="" style="float:Left;width:100px;min-height:100px;"/>
          <a href="javascript:void(0);" onclick="img_upload('files', null)" class="btn btn-primary radius upload-btn ml-10" style="float:left;">添加隐患图片</a>&#160;&#160;
          <button onClick="item_save()" class="btn btn-primary radius ml-20" type="button" style="float:left;padding: 0 20px;">+ 添加隐患</button>
        </div> 
      </div>
      <%-- <div class="row2 cl">
         
      </div>
      <div class="row2 cl">
        <label class="form-label col-xs-4 col-sm-2">隐患图片：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <img id="files" src="${ly }/images/zwtp.jpg" url="" style="width:200px;min-height:200px;"/>
          <a href="javascript:void(0);" onclick="img_upload('files', null)" class="btn btn-primary radius upload-btn"><i class="Hui-iconfont">&#xe642;</i> 添加图片</a>
        </div> 
      </div> 
      <div class="row2 cl">
        <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
          <button onClick="item_save()" class="btn btn-primary radius" type="button" style="padding: 0 70px;">
            <i class="Hui-iconfont">&#xe632;</i>保存
          </button>
          <button onClick="item_esc()" class="btn btn-primary radius" type="button" style="padding: 0 70px;">
            <i class="Hui-iconfont">&#xe632;</i>取消
          </button>
        </div>
      </div>--%>
    </div>
    
    <!-- 判断添加检查项后才显示 -->
    <div class="row cl" style="width:90%;margin-left:5%">
      <table class="table table-border table-bordered table-bg table-hover table-sort mt-20">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="20%">检查项</th>
            <th width="15%">隐患描述</th>
            <th width="15%">隐患图片</th>
            <th width="15%">操作</th>
          </tr>
        </thead>
        <tbody id="tbody1">
          <!-- 循环检查项目-->
          <%-- <tr class="text-c">
            <td>1</td>
            <td>xx</td>
            <td>xx</td>
            <td>xx</td>
            <td><img src="${ly }/images/zwtp.jpg"/></td>
            <td>xx</td>
            <td>
              <a style="text-decoration:none" data-cl="edit" onclick="edit(this)" href="javascript:;" title="编辑">编辑</a>
              <a style="text-decoration:none" onclick="del(this)" href="javascript:;" title="删除">删除</a>
            </td>
          </tr> --%>
          <!-- 循环结束 -->
        </tbody>
      </table>
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

<ul class="div_fold" id="module" style="display: none;">
  <li class="item" data-n="">
    <div class="title_item">
      <h4><b>+</b></h4>
      <a class="btn_ejc" style="text-decoration:none" onclick="part_edit(this)" href="javascript:;" title="编辑检查项">编辑检查项</a>
      <a class="btn_djc" style="text-decoration:none" onclick="part_del(this)" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe609;</i></a>
    </div>
    <div class="info">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th>检查项目</th>
            <th>隐患描述</th>
            <th>隐患图片</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody id="tbody2">
          <!-- 循环检查项目-->
          <tr class="text-c">
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td>
              <a style="text-decoration:none" onclick="del(this)" href="javascript:void(0);" title="删除">删除</a>
            </td>
          </tr>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
  </li>
<!-- 循环结束 -->
</ul>
</body>
<script type="text/javascript">
var index = '${index}';
$(function(){
  if(index != '') {
    var li =  $(".div_fold li", window.parent.document).eq(index);
    $("#name").val(li.attr("data-n"));
    $("#tbody1").html(li.find("tbody").html());
    $("#tbody1 a[data-cl='edit']").show();
  }
})
function article_save_submit() {
  if( $("#name").val() == '') {
    //$("#name").val('检查部位');
  }
  if($("#tbody1 tr").length == 0) {
    layer.alert("检查项目不能为空");
    return false;
  }
  
  $("h4").html('<b>+</b>' + $("#name").val());
  $(".item").attr("data-n", $("#name").val());
  $("#tbody2 tr").remove();
  
  list= $("#tbody1 tr");
  $.each(list, function(){
    $("#tbody2").append($(this).clone());
  })
  
  $("#tbody2 a[data-cl='edit']").hide();
  
  if(index == '') {
    $(".div_fold", window.parent.document).append($("#module").html());
  } else {
    $(".div_fold li", window.parent.document).eq(index).html($("#module li").html());
  }
  parent.layer.closeAll();
}
</script>
</html>