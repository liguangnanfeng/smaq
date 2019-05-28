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
<title>后台首页</title>
<meta name="keywords" content="后台首页">
<meta name="description" content="后台首页">
<style type="text/css">
.win1{top:50px;width:94%;left:3%}
.dis-b{float:left;margin-bottom:15px;width:33%}
.dis-b input{margin-left:10px;margin-right:5px;}
.allsq{width:94%;margin-left:3%;}
.list-btn{float:left;width:100%;margin-top:5px;}
</style>
<script type="text/javascript">
var type = 1;//1 发送给企业 2发送给村 3发送给镇 4 区  5行业

function chosearea(t){
  type = t;
  $(".win2").height($(document).height());
  $(".win2").show();
  $("#win1").show();
}

function closechose(){
  $(".win2").hide();
  $("#win1").hide();
  $("#win2").hide();
}
</script>
</head>
<body>
<div class="page-container">
  <form class="form form-horizontal" id="form">
      <!-- <div class="allsq">
        <div class="dis-ib">
          <select id="" name="" class="sel_area">
            <option>请选择区</option>
          </select>
          <select id="" name="" class="sel_area">
            <option>请选择镇</option>
          </select>
          <select id="" name="" class="sel_area">
            <option>请选择村</option>
          </select>
          <button class="btn btn-success" type="submit">
            <i class="Hui-iconfont">&#xe665;</i> 查询
          </button>
        </div>
      </div> -->
      <div class="allsq" style="max-height:340px;overflow-Y:auto;">
        <c:forEach items="${list }" var="be">
        <label class="dis-b" ><input type="checkbox" name="keyword" value="${be.userId }"/>${be.name }</label>
        </c:forEach>
      </div>
      <div style="position:absolute;bottom:10px;left:0;width:100%;text-align:center">
        <button onClick="savetownChoose()" class="btn btn-primary radius" type="button" style="margin:0 20px;padding: 0 70px;">保存</button>
        <button onClick="closeChoose()" class="btn btn-default radius" type="button" style="margin:0 20px;padding: 0 70px;">取消</button>
      </div>
  </form>
</div>
</body>
<script type="text/javascript">
function closeChoose() {
  var index = parent.layer.getFrameIndex(window.name)
  parent.layer.close(index);
}
function savetownChoose() {
  if($(":checkbox:checked").length == 0) {
    layer.alert("请选择接受会员");
    return false;
  }
  var ids="",names="";
  $(":checkbox:checked").each(function() {
    var uid = $(this).val();
    var name = $(this).parent().text();
    if(ids != '') {
      ids = ids + ",";
      names = names + ",";
    }
    ids = ids + uid;
    names = names + name;
  })
  parent.savetownChoose(ids, names);
  closeChoose();
}
</script>
</html>