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
.table{float:left;margin-right:2%}
</style>
<script type="text/javascript">
$(function() {
});
/* 判断评价结果和评定页面显示 */
function show(){
  $(".div-bzs").hide();
  $(".showcp").show();
}
</script> 
</head>
<body>
  <%-- <nav class="breadcrumb text-c">
    <a class="btn-page pagexz" href="javascript:;">作业条件风险程度评价</a>
    <a class="btn-page"  data-href="${ly }/company/safety-system/assess3"  data-title="风险矩阵法" onclick="Hui_admin_tab(this)" href="javascript:;">风险矩阵法</a>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="parent.close_tab(function(){})">返回</a>
  </nav> --%>
  <div class="page-container">
    <%-- <div class="div-bzs">
      <div class="bt-fx2 text-c">
        <c:choose>
          <c:when test="${not empty bean }">
            <div class="bt-dj" style="font-size:70px">${bean.lev }</div>
            <font class="bt-fx" style="font-size:15px">风险值：${bean.score }&emsp;&emsp;危险程度：${bean.danger }</font>
            <div class="btn radius btn-cxcp" onclick="show()" >重新风险测评</div>
          </c:when>
          <c:otherwise>
            <div class="bt-dj" style="font-size:70px">?</div>
            <font class="bt-fx">当前还未进行风险评估</font>
            <div class="btn btn-primary radius btn-cxcp" onclick="show()" >前往风险测评</div>
          </c:otherwise>
        </c:choose>
      </div>
      <h4 class="mt-20 text-c">风险等级界限值</h4>
      <table class="table table-border table-bordered table-bg table-hover table-sort mb-40" style="width:80%;margin-left:10%">
        <thead>
          <tr class="text-c">
            <th>D值</th>
            <th>危险程度</th>
            <th>风险等级</th>
          </tr>
        </thead>
        <tr class="text-c">
          <td>&gt;320</td>
          <td>不可容许的危险</td>
          <td>A</td>
        </tr>
        <tr class="text-c">
          <td>160～320</td>
          <td>高度危险</td>
          <td>B</td>
        </tr>
        <tr class="text-c">
          <td>70～160</td>
          <td>中度危险</td>
          <td>C</td>
        </tr>
        <tr class="text-c">
          <td>&lt;70</td>
          <td>轻度和可容许的危险</td>
          <td>D</td>
        </tr>
      </table>
    </div> --%>
    <div class="f-l mb-20" style="width:100%"> 
      <h3 class="text-c" style="padding:10px 0">作业条件风险程度评价</h3>
      <table class="table table-border table-bordered table-bg table-hover table-sort" style="width:30%">
        <thead>
          <tr class="text-c">
            <th>分数值</th>
            <th>事故发生的可能性</th>
            <th>选择</th>
          </tr>
        </thead>
        <tr class="text-c">
          <td>10</td>
          <td>完全可能预料</td>
          <td><input type="radio" id="" name="type1" value="10"></td>
        </tr>
        <tr class="text-c">
          <td>6</td>
          <td>相当可能</td>
          <td><input type="radio" id="" name="type1" value="6"></td>
        </tr>
        <tr class="text-c">
          <td>3</td>
          <td>可能，但不经常</td>
          <td><input type="radio" id="" name="type1" value="3"></td>
        </tr>
        <tr class="text-c">
          <td>1</td>
          <td>可能性小，完全意外</td>
          <td><input type="radio" id="" name="type1" value="1"></td>
        </tr>
        <tr class="text-c">
          <td>0.5</td>
          <td>很不可能，可能设想</td>
          <td><input type="radio" id="" name="type1" value="0.5"></td>
        </tr>
        <tr class="text-c">
          <td>0.2</td>
          <td>极不可能</td>
          <td><input type="radio" id="" name="type1" value="0.2"></td>
        </tr>
        <tr class="text-c">
          <td>0.1</td>
          <td>实际不可能</td>
          <td><input type="radio" id="" name="type1" value="0.1"></td>
        </tr>
      </table>
      <table class="table table-border table-bordered table-bg table-hover table-sort" style="width:30%">
        <thead>
          <tr class="text-c">
            <th>分数值</th>
            <th>频繁程度</th>
            <th>选择</th>
          </tr>
        </thead>
        <tr class="text-c">
          <td>10</td>
          <td>连续暴露</td>
          <td><input type="radio" id="" name="type2" value="10"></td>
        </tr>
        <tr class="text-c">
          <td>6</td>
          <td>每天工作时间内暴露</td>
          <td><input type="radio" id="" name="type2" value="6"></td>
        </tr>
        <tr class="text-c">
          <td>3</td>
          <td>每周一次，或偶然暴露</td>
          <td><input type="radio" id="" name="type2" value="3"></td>
        </tr>
        <tr class="text-c">
          <td>2</td>
          <td>每月一次暴露</td>
          <td><input type="radio" id="" name="type2" value="2"></td>
        </tr>
        <tr class="text-c">
          <td>1</td>
          <td>每年几次暴露</td>
          <td><input type="radio" id="" name="type2" value="1"></td>
        </tr>
        <tr class="text-c">
          <td>0.5</td>
          <td>非常罕见地暴露</td>
          <td><input type="radio" id="" name="type2" value="0.5"></td>
        </tr>
      </table>
      <table class="table table-border table-bordered table-bg table-hover table-sort" style="width:30%">
        <thead>
          <tr class="text-c">
            <th>分数值</th>
            <th>后果</th>
            <th>选择</th>
          </tr>
        </thead>
        <tr class="text-c">
          <td>100</td>
          <td>大灾难，许多人死亡</td>
          <td><input type="radio" id="" name="type3" value="100"></td>
        </tr>
        <tr class="text-c">
          <td>40</td>
          <td>灾难，数人死亡</td>
          <td><input type="radio" id="" name="type3" value="40"></td>
        </tr>
        <tr class="text-c">
          <td>16</td>
          <td>非常严重，一人死亡</td>
          <td><input type="radio" id="" name="type3" value="16"></td>
        </tr>
        <tr class="text-c">
          <td>7</td>
          <td>重伤</td>
          <td><input type="radio" id="" name="type3" value="7"></td>
        </tr>
        <tr class="text-c">
          <td>3</td>
          <td>轻伤</td>
          <td><input type="radio" id="" name="type3" value="3"></td>
        </tr>
        <tr class="text-c">
          <td>1</td>
          <td>引人关注，不利于基本的安全卫生要求</td>
          <td><input type="radio" id="" name="type3" value="1"></td>
        </tr>
      </table>
      <div class="mt-20 f-l" style="width:100%;text-align:center;">
        <button onClick="save()" class="btn btn-primary radius mt-20" type="button" style="padding: 0 70px;">
          <i class="Hui-iconfont mr-10">&#xe632;</i>提交评估
        </button>
      </div>
    </div>
    
    
  </div>
  <script type="text/javascript">
  /* var id = ${id};
  var cores = '${bean.cores}';
  if(cores != '') {
    cores = cores.split(",");
    $.each(cores, function(i, item) {
      $(":radio[name='type" + (i + 1) + "'][value='" + item + "']").click();
    })
  } */
  function save() {
    var cores = '';
    var score = 0;
    if($(":radio:checked").length < 3) {
      layer.alert("请选择分数");
      return false
    }
    cores = $(":radio[name='type1']:checked").val() + ',' + $(":radio[name='type2']:checked").val()
        + ',' + $(":radio[name='type3']:checked").val();
    score = parseFloat($(":radio[name='type1']:checked").val()) * parseFloat($(":radio[name='type2']:checked").val())
        * parseFloat($(":radio[name='type3']:checked").val());
    var lev = '红色', danger = '不可容许的危险';
    if(score <= 70) {
      lev = '蓝色', danger = '轻度和可容许的危险'
    } else if(score <= 160) {
      lev = '黄色', danger = '中度危险'
    } else if(score <= 320) {
      lev = '橙色', danger = '高度危险'
    }
    layer.alert("本次测评结果为‘" + lev + "’风险。");
    /* $.post(getRootPath() + "/company/safety-system/assess2-save", {
      uid : id,
      cores : cores,
      score : score,
      lev : lev,
      danger : danger
    },function(result) {
      layer.alert("本次测评结果为‘" + lev + "’风险。",{},function(){
        parent.location.reload();
      })
    }) */
  }
  </script>
</body>
</html>