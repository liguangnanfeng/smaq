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
</head>
<body>
  <nav class="breadcrumb">
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="text-c">
      <form action="${ly }/town/cx/company/company-lists?villageId=${villageId }&townId=${townId }&districtId=${districtId }" method="post">
        <div class="dis-ib">
        <span>企业名称：</span>
        <input type="text" value="${companyName }" name="companyName" class="input-text mb-5 mt-5" style="width:230px;">
        </div>
        <div class="dis-ib">
          <span>所属行业：</span>
          <select class="sel_area" id="industry2_" name="industry2_">
            <option value="">请选择</option>
          </select>
          <select class="sel_area" id="industry2_2" name="industry2_2">
            <option value="">请选择</option>
          </select>
          <script type="text/javascript" src="${ly }/js/fxgk/industry.js?v=180920"></script>
          <script type="text/javascript">
          var industry2 = '${industry2}';
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
            <option value="1"<c:if test="${isFreeze == '1'}"> selected</c:if>>已冻结</option>
            <option value="0"<c:if test="${isFreeze == '0'}"> selected</c:if>>未冻结</option>
          </select>
          </span>
        </div>
        <div class="dis-ib">
          <span>是否重点企业：</span>
          <span class="select-box inline">
          <select name="isKey" class="select">
            <option value="">全部</option>
            <option value="1"<c:if test="${isKey == '1'}"> selected</c:if>>是</option>
            <option value="0"<c:if test="${isKey == '0'}"> selected</c:if>>否</option>
          </select>
          </span>
        </div>
        <button class="btn btn-success" type="submit">
          <i class="Hui-iconfont">&#xe665;</i> 查询
        </button>
      </form>
    </div>
    
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l isAll">
        <c:if test="${session_user.userType == 4}">
        <!-- <a class="btn btn-primary radius" data-title="导入企业信息"  onclick="leadin()" href="javascript:;"><i class="Hui-iconfont" style="font-size:15px;">&#xe642;</i> 导入企业信息</a> -->
        <a class="btn btn-primary radius" data-title="添加企业"  data-href="${ly }/village/company/company-add" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 添加企业</a>
        </c:if>
      </span>
      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span> 
    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="15%">企业名称</th>
            <th width="15%">所属行业</th>
            <th width="8%">风险等级</th>
            <th width="15%">所在地</th>
            <th width="14%">规模类型</th>
            <th width="8%">运行状态</th>
            <th width="15%">操作</th>
          </tr>
        </thead>
        <tbody>
        
          <!-- 循环村 -->
         <c:forEach items="${list }" varStatus="index" var="co">
          <tr class="text-c">
            <td>${index.index + 1}</td>
            <td ondblclick="window.open('/fore/company?un=${co.name}');"><c:if test="${co.isKey == '1' && not empty sk}"><label style="color:#DF2E30">【村企业】</label></c:if>${co.name }</td>
            <td>${co.industry2 }</td>
            <td>${co.dlevel }</td>
            <td>${co.regionName} ${co.address }</td>
            <td>${co.scale}</td>
            <c:if test="${0 == co.isFreeze }">
            <td>正常</td>
            </c:if>
            <c:if test="${1 == co.isFreeze }">
            <td>冻结</td>
            </c:if>
            <td>
              <a class="label label-primary radius" style="text-decoration:none" onClick='show_dialog("企业详细信息", "${ly }/village/company/company-show?userId=${co.userId }")' href="javascript:;" title="查看企业详细资料">查看详情</a>
            </td>
          </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
  </div>
<script type="text/javascript">
$(function() {
  $('.table-sort').dataTable({
    "aaSorting": [[0, "asc"]],//默认第几个排序
    "bStateSave": false,//状态保存
    searching: true,
    ordering:  true,
    "aoColumnDefs": [
    ]
    });
});

</script> 
</body>
</html>