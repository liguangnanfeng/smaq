<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-

scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<%@ include file="/WEB-INF/inc/back-header.inc"%>
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style type="text/css">
.check-um{float:left;width:32%;margin-bottom:8px}
</style>
<script type="text/javascript">
$(function(){
    $("#type2").hide();
    $("#type3").hide();
    $("#type4").hide();
    if($("#type").val() == '2'){
      $("#type4").show();
    }
    if($("#type").val() == '3'){
      $("#type2").show();
      if($("#type2").val() == '3' || $("#type2").val() == '2'){
        $("#type3").show();
      }
    }
  
  $("#type").change(function(){
    $("#type2").hide().val("");
    $("#type3").hide().val("");
    $("#type4").hide().val("");
    if($(this).val() == '2') {
      $("#type4").show();
    }
    if($(this).val() == '3'){
      $("#type2").show();
    }
  })
  
  $("#type2").change(function(){
    $("#type3").val("");
    $("#type4").val("");
    if($(this).val() == 1) {
      $("#type3").hide();
    } else {
      $("#type3").show();
    }
  })
})
</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>网站公告</span> 
    <span class="c-gray en">&gt;</span> <span>公告列表</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" 

href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="text-c">
      <form action="${ly }/back/notice-list" method="post">
        <!-- <div class="dis-ib"><span>公告标题：</span><input type="text" value="" name="" 

class="input-text mb-5 mt-5" style="width:150px;"></div> -->
        <div class="dis-ib"><span>公告类型：</span>
          <select id="type" name="type" class="sel_area">
            <option value="">请选择</option>
            <option value="1" <c:if test="${type == '1'}"> selected</c:if>>监管动态</option>
            <option value="2" <c:if test="${type == '2'}"> selected</c:if>>安全员之家</option>
            <option value="3" <c:if test="${type == '3'}"> selected</c:if>>法律法规标准规范</option>
            <option value="4" <c:if test="${type == '4'}"> selected</c:if>>一周监管动态</option>
          </select>
          <!-- 判断选择法律法规标准规范显示 -->
          <select class="sel_area" name="type2" id="type2" style="display: none;">
            <option value="">请选择</option>
            <option value="1" <c:if test="${type2 == 1}"> selected</c:if>>法律</option>
            <option value="2" <c:if test="${type2 == 2}"> selected</c:if>>法规</option>
            <option value="3" <c:if test="${type2 == 3}"> selected</c:if>>标准规范</option>
          </select>
          <select class="sel_area" name="type3"  id="type3" style="display: none;">
          <option value="">请选择</option>
            <option value="21" <c:if test="${type3 == 21 || type3 == 31}"> selected</c:if>>危化

</option>
            <option value="22" <c:if test="${type3 == 22 || type3 == 32}"> selected</c:if>>工贸

</option>
            <option value="24" <c:if test="${type3 == 24 || type3 == 34}"> selected</c:if>>消防

</option>
            <option value="25" <c:if test="${type3 == 25 || type3 == 35}"> selected</c:if>>港口

</option>
            <option value="26" <c:if test="${type3 == 26 || type3 == 36}"> selected</c:if>>应急

</option>
            <option value="27" <c:if test="${type3 == 27 || type3 == 37}"> selected</c:if>>职卫

</option>
          </select>
          <select class="sel_area" name="type4"  id="type4" style="display: none;">
          <option value="">请选择</option>
            <option value="1" <c:if test="${type4 == 1}"> selected</c:if>>怎样当好安全员</option>
            <option value="3" <c:if test="${type4 == 3}"> selected</c:if>>一周事故警示</option>      

      
            <option value="5" <c:if test="${type4 == 5}"> selected</c:if>>安全常识</option>
          </select>
          <!-- 判断结束 -->
        </div>
        <button class="btn btn-success" type="submit">
          <i class="Hui-iconfont">&#xe665;</i> 查询
        </button>
      </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
    <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span>
  </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="20%">标题</th>
            <th width="20%">公告类型</th>
            <th width="40%">详情</th>
            <th width="15%">操作</th>
          </tr>
        </thead>
        <tbody>
          <c:set value="${fn:split('监管动态/安全员之家/法律法规标准规范/一周监管动态', '/') }" 

var="x"/>
          <!-- 循环公告 -->
          <c:forEach items="${list }" varStatus="index" var="no">
          <tr class="text-c">
            <td>${index.index + 1 }</td>
            <td>${no.title }</td>
            <td>${x[no.type - 1] }
              <c:choose>
                <c:when test="${no.type2 == 1}">-怎样当好安全员</c:when>
                <c:when test="${no.type2 == 3}">-一周事故警示</c:when>
                <c:when test="${no.type2 == 4}">-监管动态</c:when>
                <c:when test="${no.type2 == 5}">-安全常识</c:when>
                <%-- <c:when test="${no.type2 == 6}">-获取更多信息</c:when> --%>
                <c:when test="${no.type2 == 10}">-法律</c:when>
                <c:when test="${no.type2 == 21}">-法规-危化</c:when>
                <c:when test="${no.type2 == 22}">-法规-工贸</c:when>
                <c:when test="${no.type2 == 24}">-法规-消防</c:when>
                <c:when test="${no.type2 == 25}">-法规-港口</c:when>
                <c:when test="${no.type2 == 31}">-标准规范-危化</c:when>
                <c:when test="${no.type2 == 32}">-标准规范-工贸</c:when>
                <c:when test="${no.type2 == 34}">-标准规范-消防</c:when>
                <c:when test="${no.type2 == 35}">-标准规范-港口</c:when>
              </c:choose>
            </td>
            <td><lanlin:htmlToText length="50" value="${no.content }"/></td><!-- 显示50字 -->
            <td>
            <c:if test="${no.type == 4 }">
                <a style="text-decoration:none" onClick="admin_tuis(${no.id})"  href="javascript:;" 

title="推送">推送</a>
                <!-- <button class="btn btn-success" type="button" onclick="comapnyImportBoxShow()">

批量导入企业用户</button> -->
            </c:if>
              <a style="text-decoration:none" onClick="admin_edit(${no.id})"  href="javascript:;" 

title="编辑">编辑</a>
              <a style="text-decoration:none" onClick="admin_show(${no.id})"  href="javascript:;" 

title="查看详情">查看详情</a>
              <a style="text-decoration:none"  href="javascript:;" title="删除" onclick="del_

(${no.id})">删除</a>
            </td>
          </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
  </div>
  <div id="comapnyImportBox" class="modal fade" tabindex="-1" role="dialog" aria-

labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content radius">
      <div class="modal-header">
        <h3 class="modal-title">消息推送</h3>
        <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
      </div>
      <div class="modal-body">
        <div class="one_txt cl">
          <div class="text-left" style="float:left; width: 100px;">请选择推送区：</div>
          <div class="formControls col-xs-8 col-sm-9">
           <c:forEach items="${listL}" var="be">
             <div class="check-um">
               <input type="checkbox" name="check-um" value="${be.code }"/>${be.name }
             </div>
          </c:forEach>
         <input type="hidden" id="noticeId" value="">
         </div>
       </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" onclick="comapnyImportSave()">发送</button>
        <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
      </div>
    </div>
  </div>
</div>
  
<script type="text/javascript">
$(function() {
  $('.table-sort').dataTable({
    "bStateSave": false,//状态保存
    "aoColumnDefs": [
    ]
    });
});
/*编辑*/
function admin_edit(id){
  show_dialog("编辑公告", getRootPath() + "/back/notice-edit?id=" + id);
}
/*详情*/
function admin_show(id){
  show_dialog("详情", getRootPath() + "/back/notice/notice-show?id=" + id);
}
function del_(id) {
  layer.confirm("确定删除么？",function(i) {
    $.post(getRootPath() + "/back/notice-del",{
      id : id
    },function(result) {
      window.location.reload();
    })
  })
}

function admin_tuis(id) {
  $("input[name=check-um]").removeAttr("checked"); 
  $.post("/back/notice-push-show",{
    noticeId : id
  },function(result) {
    var districtIds = result.map.list;
    var a = districtIds.split(',');
    $(":checkbox").click(function() {
      if($(this).is(":checked")) {
        $(this).parent().find(":text").show();
      } else {
        $(this).parent().find(":text").hide();
      }
    })
    
    $.each(a, function(i, item) {
      $(":checkbox[value='"+item+"']").click();
    })
  })
  $("#comapnyImportBox").modal("show");
  $("#noticeId").val(id);
}

function comapnyImportSave() {
  var index = layer.load();
  obj = document.getElementsByName("check-um");
  check_val = [];
  for(k in obj){
      if(obj[k].checked)
          check_val.push(obj[k].value);
  } 
  var districtId = check_val.join(',');
  if(districtId == ""){
    layer.alert("请选择推送地址！");
    return false;
  }
  layer.confirm("确定推送本公告信息吗？",function(i) {
    layer.close(i);
    $.post("/back/notice-push",{
      districtId : districtId,
      noticeId : $("#noticeId").val()
    },function(result) {
      layer.alert("保存成功","",function(index){
      layer.close(index);
      $("form").submit();
      })
    })
  })
}
</script> 
</body>
</html>