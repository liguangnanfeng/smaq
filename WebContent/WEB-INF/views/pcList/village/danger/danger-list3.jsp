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
body .three_danger .list_thdan,body  .two_danger .list_twdan,body .one_danger .list_odan{width:94%;margin-left:50px;}

</style>
</head>
<body>
  <div class="page-container form-horizontal">
    <div class="row cl mt-20">
      <span style="color:red;margin-left:10%;background: red;margin-left: 10%;padding: 5px 20px;font-size:14px;color: #fff;">注：企业对其所在行业、涉及的高危作业等选项进行设置，系统会自动为其确定相应的安全检查标准。</span>
    </div>
    <div class="row cl mt-20">
      <label class="form-label col-xs-4 col-sm-2">行业选择：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <select class="sel_area" id="industryId" style="width:350px">
          <option value="">请选择</option>
          <c:forEach items="${libL }" var="be">
            <option value="${be.id }" data-name='${be.name }'>${be.name }</option>
          </c:forEach>
        </select>
      </div> 
    </div>
    
    <div class="row cl mt-20">
      <label class="form-label col-xs-4 col-sm-2">基础管理：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <c:forEach items="${list }" var="be">
        <c:if test="${be.type == 1}">
        <div class="one_yxuan">
          <input class="check_gao" type="radio" name="radio1" data-type="1" data-id="${be.id }" disabled="disabled"/>
          <font>${be.name }</font>
        </div>
        </c:if>
        </c:forEach>
        <!-- 循环结束 -->
      </div> 
    </div>
    
    <div class="row cl mt-20">
      <label class="form-label col-xs-4 col-sm-2">现场管理：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <!-- 循环现场管理分类  -->
        <c:forEach items="${list }" var="be">
        <c:if test="${be.type == 2}">
        <div class="one_yxuan">
          <input class="radio_xian" type="radio" name="radio2" data-type="2" data-id="${be.id }" disabled="disabled"/>
          <font>${be.name }</font>
        </div>
        </c:if>
        </c:forEach>
        <!-- 循环结束 -->
      </div> 
    </div>
    
    <div class="row cl mt-20">
      <label class="form-label col-xs-4 col-sm-2">高危作业选择：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <!-- 循环高危行业分类 -->
        <c:forEach items="${list }" var="be">
        <c:if test="${be.type == 3}">
        <div class="one_yxuan">
          <input class="check_gao" type="checkbox" name="danger" data-type="3" data-id="${be.id }" value="${be.name }"/>
          <font>${be.name }</font>
        </div>
        </c:if>
        </c:forEach>
        <!-- 循环结束 -->
      </div> 
    </div>
    <div class="row cl mt-30" style="float:left;">
      <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
        <button onClick="article_save_submit();" class="btn btn-primary radius" type="button" style="padding: 0 70px;"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
      </div>
    </div>
  </div>

</body>
<script type="text/javascript">
var ttindustry = ${ttindustry};
var url = '${url}';
var userId = ${userId};
var dangers = '${company.dangers}';
function reload() {
  
  $(":radio:checked,:checkbox:checked").removeAttr("checked");
  $(":radio,:checkbox").attr("disabled", "disabled").parent().show();
  $(":radio[data-type='2']").attr("name", 'radio2');
  var v = $("#industryId").val();
  if(v == '') {
  } else {
  $(":radio,:checkbox").parent().hide();
    $.each(ttindustry, function(i, item) {
      if(item.id == v) {
        var in1 = item.in1;//对应基础检查id
        var in2a = item.in2a;//对应现场检查 必检项目id
        var in2o = item.in2o;//对应现场检查 多选一检查项目
        $(":radio[data-id='"+in1+"']").removeAttr("disabled").click().attr("checked", "checked").parent().show();
        $(":checkbox").removeAttr("disabled").parent().show();
        $.each(in2a.split(","), function(i, it) {
          $(":radio[data-id='" + it + "']").attr("name", "radio2_and").removeAttr("disabled").click().attr("checked", "checked").parent().show();
        })
        
        $.each(in2o.split(","), function(i, it) {
          $(":radio[data-id='" + it + "']").attr("name", "radio2_or").removeAttr("disabled").parent().show();
        })
        if($(":radio[name='radio2_or']").length == 1) {
          $(":radio[name='radio2_or']").click().attr("checked", "checked");
        }
        return false;//跳出循环
      }
    })
    
      if('' != dangers) {
        $.each(dangers.split(","),function(i, item) {
          $(":checkbox[name='danger'][value='"+item+"']").prop("checked", "checked");
        })
      }
  }
}
$(function() {
  $("#industryId").change(function(){
    reload();
  })
})
function article_save_submit() {
  if($("#industryId").val() == '') {
    layer.alert("请选择所属行业");
    return false;
  }
  var industry_id = $("#industryId").val();
  var industry1 = $(":radio[data-type='1']:checked").attr("data-id");
  var industry2 = "";
  $(":radio[data-type='2']:checked").each(function(){
    var i = $(this).attr("data-id");
    industry2 = industry2==""?i:industry2+","+i;
  })
  var industry3 = "";
  $(":checkbox[data-type='3']:checked").each(function(){
    var i = $(this).attr("data-id");
    industry3 = industry3==""?i:industry3+","+i;
  })
  if(industry2.split(",").length < 1 || industry2 == '') {
    layer.alert("请选择现场管理自查标准");
    return false;
  }
  $.post(getRootPath() + "/village/trouble-set-do",{
    userId : userId,
    industryId : industry_id,
    industry1 : industry1,
    industry2 : industry2,
    industry3 : industry3,
  },function(result) {
    layer.alert("操作成功");
    if(url != '') {
      window.location.href = url;
    }
  })
}

var industry_id = '${tc.industryId}';
$(function(){
  if(industry_id == '') {
    var industry = '${industry}';
    if(industry != '') {
      $("option[data-name='"+industry+"']").attr("selected", "selected");
      reload();
    }
  } else {
    $("#industryId").val(industry_id).change();
    var industry1 = '${tc.industry1}';
    var industry2 = '${tc.industry2}';
    var industry3 = '${company.dangers}';
    $.each(industry2.split(","), function(i, it) {
      $(":radio[data-id='" + it + "']").removeAttr("disabled").click().attr("checked", "checked").parent().show();
    })
    $.each(industry3.split(","), function(i, it) {
      $(":checkbox[data-id='" + it + "']").removeAttr("disabled").click().attr("checked", "checked").parent().show();
    })
  }
})
</script>
</html>