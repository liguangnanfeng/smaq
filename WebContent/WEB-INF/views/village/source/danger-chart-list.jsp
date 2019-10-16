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
body .dis-ib{margin-right:15px;}
</style>
<script type="text/javascript">
$(function(){
  var osl = ${osl};
  var tb = document.getElementById('companies');
  for(var i=1;i<tb.rows.length;i++){
    if(osl[i-1] == true){
      tb.rows[i].cells[6].innerText = "正常";
    }else{
      tb.rows[i].cells[6].innerText = "未运行";
    }
  }
})
</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>重大危险源长制</span>
    <span class="c-gray en">&gt;</span> <span>分类统计</span> 
    <span class="c-gray en">&gt;</span> <span>企业列表</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="text-c">
      <form action="/villageAdd/source/danger-chart-list" method="post">
        <div class="dis-ib">
        <span>企业名称：</span>
        <input type="text" value="${dto.userIds }" name="userIds" class="input-text mb-5 mt-5" style="width:150px;display: none;">
        <input type="text" value="${totalwyx }" name="totalwyx" class="input-text mb-5 mt-5" style="width:150px;display: none;">
        <input type="text" value="${totalzc }" name="totalzc" class="input-text mb-5 mt-5" style="width:150px;display: none;">
        <input type="text" value="${dto.companyName }" name="companyName" class="input-text mb-5 mt-5" style="width:150px;">
        <input type="text" value="${dto.page }" name="page" id="page" style="display: none;">
        <input type="text" value="${dto.rows }" name="rows" id="rows" style="display: none;">
        <input type="text" value="${dto.doubleDanger }" name="doubleDanger"  style="display: none;">
        <input type="text" value="${dto.hazard }" name="hazard" style="display: none;">
        <input type="text" value="${dto.danger }" name="danger" style="display: none;">
        <input type="text" value="${dto.villageId }" name="villageId" style="display: none;">
        <input type="text" value="${dto.townId }" name="townId" style="display: none;">
        <input type="text" value="${dto.districtId }" name="districtId" style="display: none;">
        </div>

        <c:if test="${session_user.userType == 3 || session_user.userType == 6}">
        <div class="dis-ib">
            <span>所属村：</span>
            <span class="select-box inline">
              <select id="villageId" name="villageId" class="select" style="width:100px;">
                <option value="">全部</option>
                <c:forEach items="${villagelist }" varStatus="index" var="be">
                    <option value="${be.userId }" <c:if test="${dto.villageId == be.userId}"> selected</c:if>>${be.name }</option>
                </c:forEach>
              </select>
            </span>
        </div>
        </c:if>
        
        <div class="dis-ib">
          <span>所属行业：</span>
          <%-- <select id="industry" name="industry" class="select" style="width:230px;">
            <option value="">全部</option>
            <c:forEach items="${lib }" varStatus="index" var="be">
        <option value="${be.name }" <c:if test="${industry == be.name}"> selected</c:if>>${be.name }</option>
        </c:forEach>
          </select> --%>
          <select class="sel_area" id="industry2_" name="industry2_" style="width: 100px;">
            <option value="">请选择</option>
          </select>
          <select class="sel_area" id="industry2_2" name="industry2_2" style="width: 100px;">
            <option value="">请选择</option>
          </select>
          <script type="text/javascript" src="${ly }/js/fxgk/industry.js?v=180920"></script>
          <script type="text/javascript">
          var industry2 = '${dto.industry2}';
          $(function() {
            $("#industry2_").change(function() {
              $("#industry2_2").empty().append('<option value="">请选择</option>');
              var v = $(this).val();
              if(v != '') {
                $.each(industry2List, function(i, item) {
                  if(item.name == v) {
                    $.each(item.list, function(j, jtem) {
                      $("#industry2_2").append("<option>" + jtem + "</option>");
                    })
                  }
                })
                
                if(industry2 != '') {
                  $("#industry2_2").val(industry2.split(" > ")[1]);
                  industry2 = '';
                }
              }
            })
            $.each(industry2List, function(i, item) {
              $("#industry2_").append("<option>" + item.name + "</option>");
            })
            if(industry2 != '') {
              $("#industry2_").val(industry2.split(" > ")[0]).change();
            }
          })
          </script>
        </div>
        <div class="dis-ib">
          <span>运行状态：</span>
          <span class="select-box inline">
          <select name="isFreeze" class="select">
            <option value="">全部</option>
            <option value="1"<c:if test="${dto.isFreeze == '1'}"> selected</c:if>>正常</option>
            <option value="0"<c:if test="${dto.isFreeze == '0'}"> selected</c:if>>未运行</option>
          </select>
          </span>
        </div>
        
        <c:if test="${session_user.userType == 4}">
        <div class="dis-ib">
          <span>是否重点企业：</span>
          <span class="select-box inline">
          <select name="isKey" class="select">
            <option value="">全部</option>
            <option value="1"<c:if test="${dto.isKey == '1'}"> selected</c:if>>是</option>
            <option value="0"<c:if test="${dto.isKey == '0'}"> selected</c:if>>否</option>
          </select>
          </span>
        </div>
        </c:if>
        
        <c:if test="${session_user.userType == 3}">
        <div class="dis-ib">
          <span>重点企业：</span>
          <span class="select-box inline">
          <select name="key1" class="select">
            <option value="">全部</option>
            <option value="1"<c:if test="${dto.key1 == '1'}"> selected</c:if>>是</option>
            <option value="0"<c:if test="${dto.key1 == '0'}"> selected</c:if>>否</option>
          </select>
          </span>
        </div>
        </c:if>
        
        <c:if test="${session_user.userType == 6}">
        <div class="dis-ib">
          <span>重点企业：</span>
          <span class="select-box inline">
          <select name="key2" class="select">
            <option value="">全部</option>
            <option value="1"<c:if test="${dto.key2 == '1'}"> selected</c:if>>是</option>
            <option value="0"<c:if test="${dto.key2 == '0'}"> selected</c:if>>否</option>
          </select>
          </span>
        </div>
        </c:if>
        
        <c:if test="${session_user.userType == 10}">
        <div class="dis-ib">
          <span>是否重点企业：</span>
          <span class="select-box inline">
          <select name="isTradeKey" class="select">
            <option value="">全部</option>
            <option value="1"<c:if test="${m.isTradeKey == '1'}"> selected</c:if>>是</option>
            <option value="0"<c:if test="${m.isTradeKey == '0'}"> selected</c:if>>否</option>
          </select>
          </span>
        </div>
        </c:if>
        
        <button class="btn btn-success" type="submit" onclick="$('#page').val(0);$('input[name=userIds]').val('');">
          <i class="Hui-iconfont">&#xe665;</i> 查询
        </button>
      </form>
    </div>
    
    <%--<div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="r">未运行企业：<strong id="totalwyx">${totalwyx }</strong> 家。</span>
      <span class="r">所有数据中正常企业：<strong id="totalzc">${totalzc }</strong> 家，</span>
      <span class="r">当前共有数据：<strong>${total }</strong> 条。</span> 
    </div> --%>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort" id="companies">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="15%">企业名称</th>
            <th width="15%">所属行业</th>
            <th width="10%">风险等级</th>
            <th width="20%">所在地</th>
            <th width="10%">规模类型</th>
            <th width="10%">运行状态</th>
            <th width="10%">操作</th>
          </tr>
        </thead>
        <tbody>
        
          <!-- 循环村 -->
         <c:forEach items="${list }" varStatus="index" var="co">
          <tr class="text-c">
            <td>${dto.page * dto.rows + index.index + 1}</td>
            <td ondblclick="window.open('/fore/company?un=${co.name}');">
                
                <c:choose>
                    <c:when test="${session_user.userType == 4 }">
                    <c:if test="${co.isKey == '1' && not empty sk}"><label style="color:#DF2E30">【重点企业】</label></c:if>
                    </c:when>
                    <c:when test="${session_user.userType == 3 }">
                    <c:if test="${co.key1 == 1 && not empty sk}"><label style="color:#DF2E30">【重点】</label></c:if>
                    </c:when>
                    <c:when test="${session_user.userType == 6 }">
                    <c:if test="${co.key2 == 1 && not empty sk}"><label style="color:#DF2E30">【重点】</label></c:if>
                    </c:when>
                    <c:when test="${session_user.userType == 10 }">
                    <c:if test="${co.tradeKey == '1' && sk == 0}"><label style="color:#DF2E30">【重点企业】</label></c:if>
                    </c:when>
                </c:choose>
                ${co.name }
            </td>
            <td>${co.industry2 }</td>
            <td>${co.dlevel }</td>
            <td>${co.regionName} ${co.address }</td>
            <td>${co.scale}</td>
            <td></td>
            <td>
              <a class="label label-primary radius" style="text-decoration:none" onClick='show_dialog("企业详细信息", "${ly }/village/company/company-show?userId=${co.userId }")' href="javascript:;" title="查看企业详细资料">查看详情</a>
            </td>
          </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
      <div id="layer_page_div" style="margin-top: 15px;"></div>
    </div>
  </div>
  
</body>
<script type="text/javascript" src="${ly }/js/H-ui.admin_v3.0/lib/laypage/1.2/laypage.js"></script>
<link type="text/css" href="${ly }/js/H-ui.admin_v3.0/lib/laypage/1.2/skin/laypage.css" rel="stylesheet" />
<script type="text/javascript">
//页码，每页条数，总数目
var page = ${dto.page};
var rows = ${dto.rows}
var total = ${total};
var a_p = 0;
if(total % rows == 0) {
  a_p = (total) / rows;
} else {
  a_p = (total - total % rows) / rows + 1;
}
if(a_p > 1) {
  laypage({
    cont : "layer_page_div", //容器。值支持id名、原生dom对象，jquery对象,
    pages : a_p, //总页数
    //skin : '#AF0000', //加载内置皮肤，也可以直接赋值16进制颜色值，如:#c00
    groups : 8, //连续显示分页数
    curr : page + 1,//当前页
    jump: function(obj){ //触发分页后的回调
      if($("#page").val() != obj.curr - 1) {
            $("#page").val(obj.curr - 1);
            $("form").submit();
            layer.load();
      }
    }
  });
}
</script>
</html>