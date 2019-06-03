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
body .three_danger .list_thdan,body  .two_danger .list_twdan,body .one_danger .list_odan{width:94%;margin-left:50px;}
</style>
<script type="text/javascript">
var levelL = ${levelL};
</script>
<script type="text/javascript" src="${ly }/js/ll-tree/company-ll-tree.js"></script>
<script type="text/javascript">
var levelIds = '${levelIds}', index = '${index}';
$(function(){
  if(levelIds != '') {
    $.each(levelIds.split(","), function(i, item) {
      $(":checkbox[data-id='"+item+"']").click().attr("checked", "checked");
    })
  }
})
</script>
</head>
<body>
<div class="page-container">
  <form class="form form-horizontal" id="form">
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2" style="width:20%;text-align:right;"><span style="color: red">*</span> 检查部位：</label>
      <div class="formControls col-xs-8 col-sm-9" style="width:70%;">
        <input type="text" id="name" value="${partN }" style="width:350px" class="input-text" placeholder="根据检查需要填写或空缺"/>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2" style="width:20%;text-align:right;">选择检查项：</label>
      <div class="formControls col-xs-8 col-sm-9" style="width:70%;">
        <div class="list_danger">
          <!-- 包含两级 -->
        </div>
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
<!-- 模板 -->
<ul id="module" style="display: none;">
  <li class="item" data-n>
    <div class="title_item">
      <h4><b>+</b>部位名称xx</h4>
      <a class="btn_ejc" style="text-decoration:none" onclick="part_edit(this)" href="javascript:;" title="修改检查项">cc修改检查项</a>
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
          <tr class="text-c" data-lid="">
            <td>1</td>
            <td>检查项目xx</td>
            <td>
              <a style="text-decoration:none" onclick="del()" href="javascript:;" title="删除">删除</a>
            </td>
          </tr>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
  </li>
</ul>
</body>
<script type="text/javascript">
function article_save_submit() {
  var ids = '';
  list= $(":checkbox[data-level='2']:checked");
  $.each(list, function(){
    if($(this).attr("data-id")) {
      var id = $(this).attr("data-id");
      ids = (ids == '' ? id : ids + "," + id);
    }
  })
  list= $(":checkbox[data-level='3']:checked");
  $.each(list, function(){
    var id = $(this).attr("data-id");
    ids = (ids == '' ? id : ids + "," + id);
  })
  if(ids == '') {
    layer.alert("请选择本次检查的项目");
    return false;
  }
  if($("#name").val() == '') {
    //$("#name").val('检查部位');
    layer.alert("请选择本次检查的检查部位");
    return false;
  }
  $("h4").html('<b>+</b>' + $("#name").val());
  $(".item").attr("data-n", $("#name").val());
  $("tbody tr").remove();
  
  var i = 0;
  list= $(":checkbox[data-level='2']:checked");
  $.each(list, function(){
    if($(this).attr("data-id")) {
      i++;
      var t = $(this).closest('.two_danger').attr("data-t").split('###').join('/');
      $("tbody").append('<tr class="text-c" data-lid="' + $(this).attr("data-id") + '">' +
          '<td>' + i + '</td>' +
          '<td>' + t + '</td>' +
          '<td>' +
            '<a style="text-decoration:none" onclick="del(this)" href="javascript:;" title="删除">删除</a>' +
          '</td>' +
        '</tr>')
    }
  })
  list= $(":checkbox[data-level='3']:checked");
  $.each(list, function(){
    i++;
    var t = $(this).closest('.three_danger').attr("data-t").split('###').join('/');
    $("tbody").append('<tr class="text-c" data-lid="' + $(this).attr("data-id") + '">' +
        '<td>' + i + '</td>' +
        '<td>' + t + '</td>' +
        '<td>' +
          '<a style="text-decoration:none" onclick="del(this)" href="javascript:;" title="删除">删除</a>' +
        '</td>' +
      '</tr>')
  })
  
  if(index == '') {
    $(".div_fold", window.parent.document).append($("#module").html());
  } else {
    $(".div_fold li", window.parent.document).eq(index).html($("#module li").html());
  }
  parent.layer.closeAll();
}

</script>
</html>