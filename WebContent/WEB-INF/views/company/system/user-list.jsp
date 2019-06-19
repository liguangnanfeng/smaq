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
<script type="text/javascript">
$(function() {
  $('.table-sort').dataTable({
    "retrieve" : true,
    "bSort" : false,
    'ordering' : false,
    "bStateSave" : false,// 状态保存
    "searching" : false,
    "aoColumnDefs" : [{
      "orderable" : false,
    }]
  });
});
</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span> 部门人员 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
    <i class="Hui-iconfont">&#xe68f;</i></a>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:history.back(-1)" title="返回">返回</a>
  </nav>
  <div class="page-container">
    <div class="text-c div-search">
      <form action="" method="post">
        <span>姓名：</span><input type="text" name="name" value="${m.name }" placeholder="姓名" style="width: 150px" class="input-text"> 
        <span><c:if test="${session_user.userType != 3}">所在公司：</c:if><c:if test="${session_user.userType == 3}">所在单位：</c:if></span>
        <span class="select-box inline"> 
          <select name="cid" class="select" id="cid">
            <option value=""><c:if test="${session_user.userType != 3}">所在公司</c:if><c:if test="${session_user.userType == 3}">所在单位</c:if></option>
            <c:forEach items="${companyL }" var="be">
                <option value="${be.id }"<c:if test="${m.cid == be.id}"> selected</c:if>>${be.name }</option>
            </c:forEach>
          </select>
        </span>
        <span class="select-box inline"> 
          <select name="dpid" class="select" id="dpid">
            <option value=""><c:if test="${session_user.userType != 3}">所在车间</c:if><c:if test="${session_user.userType == 3}">所在部门</c:if></option>
          </select>
        </span>
        <span class="select-box inline"> 
          <select name="did" class="select" id="did">
            <option value=""><c:if test="${session_user.userType != 3}">所在环节/部位</c:if><c:if test="${session_user.userType == 3}">所在科室</c:if></option>
          </select>
        </span> 
        <button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
      </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l">
        <button class="btn btn-primary radius" onclick="showadd('')"><i class="Hui-iconfont">&#xe600;</i> 添加人员</button>
		
      </span>
      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span>
    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th>序号</th>
            <th>姓名</th>
            <th>联系手机</th>
            <th>所属车间/部位</th>
            <th>职务</th>
            <th>权限</th>
            <th>操作</th>

          </tr>
        </thead>
        <tbody>
          <c:forEach items="${list }" var="be" varStatus="index">
          <tr class="text-c">
            <td>${index.index + 1}</td>
            <td>${be.id }</td>
            <td>${be.name }</td>
            <td>${be.mobile }</td>
            <td>${be.dpname } / ${be.dname }</td>
            <td>${be.position }</td>
            <td>
              <%-- test里面是布尔值
                   eq方法可以进行字符串的比较
              --%>
              <c:if test="${ be.status eq '1'}" >安全检查员</c:if>
              <c:if test="${ be.status eq '2'}" >安全负责人</c:if>
              <c:if test="${ be.status eq '0'}" >无</c:if>
            </td>
            <td>
              <a style="text-decoration: none" class="ml-5" onClick="showadd(${be.id})" href="javascript:;" title="编辑">编辑</a>
              <a style="text-decoration: none" class="ml-5" onClick="del_(${be.id})" href="javascript:;" title="删除">删除</a>
             <%-- <c:if test="${ be.status eq '1' || be.status eq '2'}" >
                <a style="text-decoration: none" class="ml-5" onClick="(${be.id})" href="javascript:;" title="编辑">编辑</a>


              </c:if>--%>

            </td>

          </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</body>
<div id="importPersonBox" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content radius">

      <div class="modal-header">
        <h3 class="modal-title">导入人员</h3>
        <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
      </div>


      <div class="modal-body" id="importBox">
        <div class="one_txt cl">
          <div class="text-left" style="float: left;width: 70px;">导入文件：</div>
          <div class="text-right formControls">
                           <span class="btn-upload form-group">
                               <input type="file" id="file" name="file">
                           </span>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" onclick="importPersonImportSave()">保存</button>
        <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
function importPerson() {
        $("#importPersonBox").modal("show");
    }

    function importPersonImportSave() {
        var index = layer.load();
        $.ajaxFileUpload({
            url: getRootPath() + '/company/importPersonExcel',
            secureuri: false, //一般设置为false
            fileElementId: 'file',
            dataType: 'json',
            async: false,
            success: function (data, status) {
                layer.close(index);
                var status = data.status;
                if (status != '1') {
                    layer.alert("导入成功", {}, function (ind) {
                        layer.close(ind);
                        window.location.reload();
                    });
                }else {
                    layer.alert(data.map.message ,{}, function (ind) {
                        layer.close(ind);
                        window.location.reload();
                    });
                }
            },
            error: function (data, status, e) {
                layer.close(index);
                alert("导入失败！请检查数据");
            }
        })
    }
var dep1= ${dep1};
var dep2= ${dep2};
var userType = '${session_user.userType}';
var cid='${m.cid}', dpid = '${m.dpid}', did = '${m.did}';
$(function() {
  $("#cid").change(function() {
    loadDep1($(this).val());
  })
  $("#dpid").change(function() {
    loadDep2($(this).val());
  })
  if(cid != '') $("#cid").val(cid).change();
})

function loadDep1(cid) {
  if(userType =='3'){
    $("#dpid").empty().append('<option value="">所在部门</option>');
 }else{
   $("#dpid").empty().append('<option value="">所在车间</option>');
 }
  if(cid == '') {
    return false;
  }
  $.each(dep1, function(i, item) {
    if(item.cid == cid) {
      $("#dpid").append('<option value="' + item.id + '">' + item.name + '</option>');
    }
  })
  if(dpid != '') {
    $("#dpid").val(dpid).change();
    dpid = '';
  }
}

function loadDep2(pid) {
  if(userType =='3'){
    $("#did").empty().append('<option value="">所在科室</option>');
 }else{
   $("#did").empty().append('<option value="">所在岗位/部位</option>');
 }
  if(pid == '') {
    return false;
  }
  $.each(dep2, function(i, item) {
    if(item.pid == pid) {
      $("#did").append('<option value="' + item.id + '">' + item.name + '</option>');
    }
  })
  if(did != '') {
    $("#did").val(did);
    did = '';
  }
}

function showadd(id){
  show_dialog("部门人员", '/company/system/user-add?id=' + id);
}

function del_(id) {
  layer.confirm("确认要删除该数据么？", function(index) {
    $.post("/company/system/user-update", {
      id : id,
      del : 1
    },function(result) {
      layer.close(index);
      alert(result.mess);
      location.reload();
    })
  });
}

</script>
</html>