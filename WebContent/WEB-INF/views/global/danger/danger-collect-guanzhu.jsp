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
.text-c td a {text-decoration: underline;}
.table tbody tr td .a_danger{padding:0 15px;}
.table tbody tr td .a_danger:hover{background:#06c;color:#fff}
.l strong {color: red;margin:0 10px;}
.l{width:100%;margin:5px 25px;}
.lhover:hover{color:red}
.lhover{display:inline-block}
body .lhover strong {color: red;margin:0;}
</style>
<script type="text/javascript">
var villageId = '${m.villageId}';
function openzl(){
  $("#upload-zl").modal("show")}
  
$(function() {
  if($("#townId").length > 0) {
    $("#townId").change(function() {
      $("#villageId").empty().append("<option value=''>全部</option>");
      if($(this).val() != '') {
        $.post(getRootPath() + "/village/getVillageByTown", {
          townId : $("#townId").val()
        },function(result) {
          var list = result.map.list;
          $.each(list, function(i, item) {
            $("#villageId").append("<option value='"+item.userId+"'>"+item.name+"</option>");
          })
          if(villageId != '') {
            $("#villageId").val(villageId);
            villageId = '';
          }
        })
      }
    })
    
    $("#townId").change();
  }
})

function print_() {
  var obj = $("form").serialize();
  show_dialog("隐患数据分析", getRootPath() + "/global/danger-collect-print?" + obj);
}
</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>隐患统计分析系统</span> 
    <span class="c-gray en">&gt;</span> <span>隐患数据分析</span> 
    
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="text-c">
      <form action="${ly }/global/danger-collect" method="post">
        <c:if test="${session_user.userType == 6}">
            <span>所在镇：</span>
            <select name="townId" id="townId" class="select" style="width:100px;">
                <option value="">全部</option>
                <c:forEach items="${townL }" var="be">
                <option value="${be.userId }" <c:if test="${m.townId == be.userId}"> selected</c:if>>${be.name }</option>
                </c:forEach>
            </select>
            <span>所在村：</span>
            <select name="villageId" id="villageId" class="select" style="width:100px;">
                <option value="">全部</option>
            </select>
        </c:if>
        <c:if test="${session_user.userType == 3}">
        <div class="dis-ib">
          <span>所在村：</span>
          <select id="villageId" name="villageId" class="select" style="width:100px;">
            <option value="">全部</option>
            <c:forEach items="${villageL }" var="be">
            <option value="${be.userId }" <c:if test="${m.villageId == be.userId}"> selected</c:if>>${be.name }</option>
            </c:forEach>
          </select>
        </div>
        </c:if>
        <div class="dis-ib">
          <span>企业类型：</span>
          <select name="industryType" class="select" style="width:100px;">
            <option value="">全部</option>
            <option value="1" <c:if test="${m.industryType == 1}"> selected</c:if>>化工企业</option>
            <option value="2" <c:if test="${m.industryType == 2}"> selected</c:if>>一般工贸</option>
          </select>
          <span>查询时间段：</span>
          <input type="text" name="startTime" value="${m.startTime }" class="Wdate input-text" id="sT" style="width:100px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
          <font>-</font>
          <input type="text" name="endTime" value="${m.endTime }" class="Wdate input-text" id="eT" style="width:100px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
        </div>
        <button class="btn btn-success" type="submit">
          <i class="Hui-iconfont">&#xe665;</i> 查询
        </button>
      </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <div class="l isAll">
        <button onClick="print_()" class="btn btn-primary radius" type="button" style="padding: 0 30px; margin-right: 20px">
          <i class="Hui-iconfont mr-10">&#xe652;</i>打印预览
        </button>
        <div class="lhover" onclick="openzl()" style="cursor: pointer;"><i class="Hui-iconfont mr-10">&#xe665;</i><font style="text-decoration: underline;">共有<strong>${fn:length(list) }</strong> 家企业存在隐患 </font></div>
      </div>
    </div>
    <c:set value="0" var="xxx"/><!-- 全部 -->
    <c:set value="0" var="yyy"/><!-- 已整改 -->
    <c:forEach items="${list }" varStatus="index" var="be">
    <c:set value="${be[2] + be[3] + be[6] + be[7] + be[10] + be[11] + be[14] + be[15] + xxx}" var="xxx"/>
    <c:set value="${be[3] + be[7] + be[11] + be[15] + yyy}" var="yyy"/>
    </c:forEach>
   <!--  <div class="cl pd-5 bg-1 bk-gray mt-20"> -->
      <%-- <span class="l">共有<strong>${fn:length(list) }</strong> 家企业存在隐患；</span> 
      <span class="l">隐患总数<strong>${xxx }</strong> 条；</span> 
      <span class="l">已整改<strong>${yyy }</strong> 条；</span> 
      <span class="l">未整改<strong>${xxx - yyy}</strong> 条；</span>
      <span class="l">重大隐患<strong>${d}</strong> 条；</span> --%>
      
      <c:set var="hg" value="0"/>
      <c:set var="yb" value="0"/>
      <c:forEach items="${groupIndu }" var="be">
          <c:if test="${be.industry_id == 76}">
          <c:set var="hg" value="${hg + be.c}"/>
          </c:if>
          <c:if test="${be.industry_id != 76}">
          <c:set var="yb" value="${yb + be.c}"/>
          </c:if>
      </c:forEach>
      
      <%-- <span class="l">化工企业（危险化学品生产、经营、使用）、加油站<strong>${hg}</strong> 条；</span>
      <span class="l">工贸企业<strong>${yb}</strong> 条；</span> --%>
    <!-- </div> -->
    <div class="mt-20" >
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">编号</th>
            <c:choose>
              <c:when test="${m.isZhongDa == 1 }">
                <th width="20%">企业名称</th>
                <th width="15%">隐患总数</th>
                <th width="15%">企业自查</th>
                <th width="15%">执法检查</th>
                <th width="15%">部门抽查</th>
                <th width="15%">行政检查</th>
              </c:when>
              <c:when test="${m.isZhongDa == 2 }">
                <th width="20%">企业名称</th>
                <th width="15%">隐患总数</th>
                <th width="15%">企业自查</th>
                <th width="15%">执法检查</th>
                <th width="15%">部门抽查</th>
                <th width="15%">行政检查</th>
              </c:when>
              <c:otherwise>
                <th width="35%">企业名称</th>
                <th width="30%">隐患总数</th>
                <th width="30%">行政检查</th>
              </c:otherwise>
            </c:choose>
          </tr>
        </thead>
        <tbody>
          <!-- 循环-->
          <c:forEach items="${list }" varStatus="index" var="be">
            <tr class="text-c">
            <td>${index.index + 1 }</td>
            <td>${be[1] }</td>
            <td>
                <c:choose>
                  <c:when test="${m.isZhongDa == 1 }">
                    <span>${be[2] + be[3] + be[6] + be[7] + be[10] + be[11] + be[14] + be[15]}</span>
                  </c:when>
                  <c:when test="${m.isZhongDa == 2 }">
                    <span>${be[2] + be[3]  + be[6] + be[7] + be[10] + be[11] }</span>
                  </c:when>
                  <c:otherwise>
                    <span>${be[14] + be[15]}</span>
                  </c:otherwise>
                </c:choose> 
                <%--  <span>已整改：${be[3] + be[7] + be[11] + be[15]}</span>
              <span>未整改：${be[2] + be[6] + be[10] + be[14]}</span><br>
              <span>重大隐患：${be[4] + be[5] + be[8] + be[9] + be[12] + be[13] + be[16] + be[17]}</span>
              <span>已整改：${be[5] + be[9] + be[13] + be[17]}</span>
              <span>未整改：${be[4] + be[8] + be[12] + be[16]}</span> --%>
            </td>
            <c:if test="${m.isZhongDa == 1 }">
            <td>
              <a href="javascript:show_dialog('企业自查' ,'${ly }/global/danger-index-list?flag=1&cId=${be[0]}&startTime=${m.startTime }&endTime=${m.endTime }')" class="a_danger">${be[2] + be[3]}</a><br>
              <%-- <a href="${ly }/village/danger-index-list?flag=1&cId=${be[0]}&status=3">已整改：${be[3]}</a>
              <a href="${ly }/village/danger-index-list?flag=1&cId=${be[0]}&status=2">未整改：${be[2]}</a><br>
              <a href="${ly }/village/danger-index-list?flag=1&cId=${be[0]}&d=1">重大隐患：${be[4] + be[5]}</a><br>
              <a href="${ly }/village/danger-index-list?flag=1&cId=${be[0]}&d=1&status=3">已整改：${be[5]}</a>
              <a href="${ly }/village/danger-index-list?flag=1&cId=${be[0]}&d=1&status=2">未整改：${be[4]}</a> --%>
            </td>
            <td>
              <a href="javascript:show_dialog('执法检查' ,'${ly }/global/danger-index-list?flag=2&cId=${be[0]}&startTime=${m.startTime }&endTime=${m.endTime }')" class="a_danger">${be[6] + be[7]}</a><br>
              <%-- <a href="${ly }/village/danger-index-list?flag=2&cId=${be[0]}&status=3">已整改：${be[7]}</a>
              <a href="${ly }/village/danger-index-list?flag=2&cId=${be[0]}&status=2">未整改：${be[6]}</a><br>
              <a href="${ly }/village/danger-index-list?flag=2&cId=${be[0]}&d=1">重大隐患：${be[8] + be[9]}</a><br>
              <a href="${ly }/village/danger-index-list?flag=2&cId=${be[0]}&d=1&status=3">已整改：${be[9]}</a>
              <a href="${ly }/village/danger-index-list?flag=2&cId=${be[0]}&d=1&status=2">未整改：${be[8]}</a> --%>
            </td>
            <td>
              <a href="javascript:show_dialog('部门抽查' ,'${ly }/global/danger-index-list?flag=3&cId=${be[0]}&startTime=${m.startTime }&endTime=${m.endTime }')" class="a_danger">${be[10] + be[11]}</a><br>
              <%-- <a href="${ly }/village/danger-index-list?flag=3&cId=${be[0]}&status=3">已整改：${be[11]}</a>
              <a href="${ly }/village/danger-index-list?flag=3&cId=${be[0]}&status=2">未整改：${be[10]}</a><br>
              <a href="${ly }/village/danger-index-list?flag=3&cId=${be[0]}&d=1">重大隐患：${be[12] + be[13]}</a><br>
              <a href="${ly }/village/danger-index-list?flag=3&cId=${be[0]}&d=1&status=3">已整改：${be[13]}</a>
              <a href="${ly }/village/danger-index-list?flag=3&cId=${be[0]}&d=1&status=2">未整改：${be[12]}</a> --%>
            </td>
            <td>
              <a href="javascript:show_dialog('行政检查' ,'${ly }/global/danger-index-list?flag=4&cId=${be[0]}&startTime=${m.startTime }&endTime=${m.endTime }')" class="a_danger">${be[14] + be[15]}</a><br>
              <%-- <a href="${ly }/village/danger-index-list?flag=4&cId=${be[0]}&status=3">已整改：${be[15]}</a>
              <a href="${ly }/village/danger-index-list?flag=4&cId=${be[0]}&status=2">未整改：${be[14]}</a><br>
              <a href="${ly }/village/danger-index-list?flag=4&cId=${be[0]}&d=1">重大隐患：${be[16] + be[17]}</a><br>
              <a href="${ly }/village/danger-index-list?flag=4&cId=${be[0]}&d=1&status=3">已整改：${be[17]}</a>
              <a href="${ly }/village/danger-index-list?flag=4&cId=${be[0]}&d=1&status=2">未整改：${be[16]}</a> --%>
            </td>
            </c:if>
            <c:if test="${m.isZhongDa == 2 }">
            <td>
              <a href="javascript:show_dialog('企业自查' ,'${ly }/global/danger-index-list?flag=1&cId=${be[0]}&startTime=${m.startTime }&endTime=${m.endTime }&d=1')" class="a_danger">${be[2] + be[3]}</a><br>
            </td>
            <td>
              <a href="javascript:show_dialog('执法检查' ,'${ly }/global/danger-index-list?flag=2&cId=${be[0]}&startTime=${m.startTime }&endTime=${m.endTime }&d=1')" class="a_danger">${be[6] + be[7]}</a><br>
            </td>
            <td>
              <a href="javascript:show_dialog('部门抽查' ,'${ly }/global/danger-index-list?flag=3&cId=${be[0]}&startTime=${m.startTime }&endTime=${m.endTime }&d=1')" class="a_danger">${be[10] + be[11]}</a><br>
            </td>
            <td>
              <a href="javascript:show_dialog('行政检查' ,'${ly }/global/danger-index-list?flag=4&cId=${be[0]}&startTime=${m.startTime }&endTime=${m.endTime }&d=1')" class="a_danger">${be[14] + be[15]}</a><br>
            </td>
            </c:if>
            <c:if test="${m.isZhongDa == 3 }">
            <td>
              <a href="javascript:show_dialog('执法检查' ,'${ly }/global/danger-index-list?flag=2&cId=${be[0]}&startTime=${m.startTime }&endTime=${m.endTime }')" class="a_danger">${be[6] + be[7]}</a><br>
            </td>
            </c:if>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
  </div>
  
  <div id="upload-zl" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content radius">
              <div class="modal-body">
                <div class="row cl">
                  <span class="l">隐患总数<strong>${xxx }</strong> 条</span> 
                  <span class="l">已整改<strong>${yyy }</strong> 条</span> 
                  <span class="l">未整改<strong>${xxx - yyy}</strong> 条</span>
                  <span class="l">重大隐患<strong>${d}</strong> 条</span>
                  <%-- <span class="l">化工企业（危险化学品生产、经营、使用）、加油站<strong>${hg}</strong> 条</span>
                  <span class="l">工贸企业<strong>${yb}</strong> 条</span> --%>
                </div>
              </div>
              <div class="modal-footer">
                  <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
              </div>
          </div>
      </div>
  </div>
  
  
<script type="text/javascript">
$(function() {
  $('.table-sort').dataTable({
    "aaSorting": [[0, "asc"]],//默认第几个排序
    "bStateSave": false,//状态保存
    "aoColumnDefs": [
    ]
    });
});
</script> 
</body>
</html>