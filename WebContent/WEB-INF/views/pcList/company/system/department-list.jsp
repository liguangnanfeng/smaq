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
<style>
.table{width:30%;margin-right:3%;float:left;}
.table td{padding:0;height:35px;line-height:35px;cursor:pointer;}
.btn-depart{display:inline-block;height:35px;line-height:35px;width:100%;background:#4298FB;color:#fff;border:0px;}
.dantd td{position: relative;}
.dantd .Hui-iconfont{font-size:15px;position: absolute;right:15px;top:0;}
.dantdxz{background:#BBD7F7}
</style>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span> 组织架构 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
    <i class="Hui-iconfont">&#xe68f;</i></a>
  </nav>
   <div class="page-container">
    <div class="text-c">
      <table class="table table-border table-bordered" >
        <thead>
          <tr class="text-c">
<%--添加公司--%>
            <td>
                <button class="btn-depart" onclick="showadd(1)">
                    <i class="Hui-iconfont">&#xe600;</i>
                    <c:if test="${session_user.userType != 3}">
                        添加分(子)公司
                    </c:if>
                    <c:if test="${session_user.userType == 3}">
                        添加单位</c:if>
                </button>
            </td>
          </tr>
        </thead>
        <!-- 循环公司,默认第一个选中 -->
        <tbody id="tbl">
<%--循环公司或者单位--%>
          <c:forEach items="${list}" var="be" varStatus="index">
          <tr class="text-c dantd customdantd" data-id="${be.id }" data-name="${be.name }">
            <td>${be.name }
              <i class="Hui-iconfont" name="edit" style="right:38px">&#xe6df;</i><!-- 修改 -->
                <!--<i class="Hui-iconfont" name="del">&#xe609;</i>--><!-- 删除 -->
            </td>
          </tr>
          </c:forEach>
        </tbody>
      </table>
      
      <table class="table table-border table-bordered" >
        <thead>
          <tr class="text-c">
            <td><button class="btn-depart" onclick="showadd(2)"><i class="Hui-iconfont">&#xe600;</i> 添加车间</button></td>
          </tr>
          </thead>
          <tbody id="table1">
          <!-- 循环部门,默认第一个选中 -->
          <!-- <tr class="text-c dantd"> -->
            <!-- <td>部门名称xx 
              <i class="Hui-iconfont" style="right:38px">&#xe6df;</i>修改
              <i class="Hui-iconfont">&#xe609;</i>删除
            </td> -->
          <!-- </tr> -->
        </tbody>
      </table>
      <table class="table table-border table-bordered" >
        <thead>
          <tr class="text-c">
            <td><button class="btn-depart" onclick="showadd(3)">
                <i class="Hui-iconfont">&#xe600;</i> <c:if test="${session_user.userType != 3}">添加岗位/部位</c:if><c:if test="${session_user.userType == 3}">添加科室</c:if>
              </button></td>
          </tr>
        </thead>
        <tbody id="table2">
        <!--  循环班组,默认第一个选中 -->
          <!-- <tr class="text-c dantd">
            <td>班组名称xx 
              <i class="Hui-iconfont" style="right:38px">&#xe6df;</i>修改
              <i class="Hui-iconfont">&#xe609;</i>删除
            </td>
          </tr> -->
        </tbody>
      </table>
    </div>
  </div>

  <!-- TODO  弹窗添加 进行添加数据 -->
  <div id="win-add" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
          <div class="modal-content radius">
              <div class="modal-header">
                  <h3 class="modal-title">添加</h3>
                  <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
              </div>
              <div class="modal-body">
                  <div class="row cl row1">
                      <label class="form-label col-xs-4 col-sm-2" style="width:120px;" id="label_">公司/车间/岗位/部位名称：</label>
                      <div class="formControls col-xs-8 col-sm-9" style="text-align:left;width:320px;">
                          <input type="text" value="" name="name" id="name" class="input-text">
                      </div>
                  </div>
                  <%--四个隐藏域 将域中的数据进行传递 --%>
                  <input type="hidden" id="type" /><!-- 1 添加企业 2添加部门 3添加班组 -->
                  <input type="hidden" id="pid" /><!-- 添加班组时保存父级id -->
                  <input type="hidden" id="id" />
                  <input type="hidden" id="cid" />
              </div>

              <div class="modal-footer">
                  <button class="btn btn-primary" id="save_btn">确定</button>
                  <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
              </div>
          </div>
      </div>
  </div>
</body>

<script type="text/javascript">





$(function() {
  var userType = '${session_user.userType}';
  // 点击确定的时候进行数据的添加
  $("#save_btn").click(function() {
    if($("#name").val() == '') {
      layer.alert("请输入名称",{},function(i) {
        layer.close(i);
        $("#name").focus();
      })
      return false;
    }
    
    var i = layer.load();
    var type = $("#type").val();
    if(type == 1) {//企业
      $.post("/company/system/company-save", {
        name : $("#name").val(),
        id : $("#id").val()
      }, function(result) {
        layer.close(i);
        $("#win-add").modal("hide");
        window.location.reload();
      })
    }
    
    if(type == 2) {//部门
      $.post("/company/system/depart-save", {
        name : $("#name").val(),
        cid : $("#cid").val(),
        level : 1,
        id : $("#id").val()
      }, function(result) {
        layer.close(i);
        $("#win-add").modal("hide");
        $("#tbl .dantdxz").click();
      })
    }
    
    if(type == 3) {//班组
      if($("#pid").val() == '') {
        layer.alert("请选择车间");
        layer.close(i);
        return false;
      }
      
      $.post("/company/system/depart-save", {
        name : $("#name").val(),
        cid : $("#cid").val(),
        pid : $("#pid").val(),
        level : 2,
        id : $("#id").val()
      }, function(result) {
        layer.close(i);
        $("#win-add").modal("hide");
        $("#table1 .dantdxz").click();
      })
    }
  })
  
  //tr 点击样式控制
  $("table").on("click", ".dantd", function(){
    $(this).closest(".table").find(".dantd").removeClass("dantdxz");
    $(this).addClass("dantdxz");
  })
  
  //部门点击事件
  $("#table1").on("click", "tr", function() {
    $("#pid").val($(this).attr("data-id"));
    $("#table2").load("/company/system/department-list-banzu",{
      pid : $(this).attr("data-id"),
      level : 2
    });
  })
  
  //选择企业事件
  $("#tbl").on("click", "tr", function() {
    var cid = $(this).attr("data-id");
    $("#cid").val(cid)
    $("#table1").load("/company/system/department-list-bumen",{
      cid : cid,
      level : 1
    },function(r) {
      $("#table2").empty();
      //默认选中第一个部门
      if($("#table1 tr").length > 0) {
        $("#table1 tr").eq(0).click();
      } 
    });
  })
  
   //企业编辑、删除点击事件
   $("#tbl").on("click", ".dantd i", function() {
     var td = $(this).closest("td");
     var tr = td.parent();
     var titles;
     if($(this).attr("name") == 'edit') {//编辑
       $("#name").val(tr.attr("data-name"));
       $("#id").val(tr.attr("data-id"));
       if(userType =='3'){
         titles = ["", "单位名称", "部门名称", "科室名称"];
       }else{
         titles = ["", "公司名称", "车间名称", "岗位/部位名称"];
       }
       $("#label_").text(titles[1]);
       $("#type").val(1);
       $("#win-add").modal("show");
       $("#name").focus();
     } else {
       layer.confirm("确定删除该记录么？", function(i) {
         $.post("/company/system/company-save", {
           id : tr.attr("data-id"),
           del : 1
         }, function(result) {
           layer.close(i);
           if(tr.is(".dantdxz")) {
             var trs = tr.parent().find("tr");
             if(trs.length > 0) {
               trs.eq(0).click();
             }
           }
           tr.remove();
         })
       })
     }
   })
   
   //部门编辑、删除点击事件
   $("#table1").on("click", ".dantd i", function() {
     var td = $(this).closest("td");
     var tr = td.parent();
     var titles;
     if($(this).attr("name") == 'edit') {//编辑
       $("#name").val(tr.attr("data-name"));
       $("#id").val(tr.attr("data-id"));
       $("#cid").val(tr.attr("data-cid"));
       if(userType =='3'){
          titles = ["", "单位名称", "部门名称", "科室名称"];
       }else{
          titles = ["", "公司名称", "车间名称", "岗位/部位名称"];
       }
       $("#label_").text(titles[2]);
       $("#type").val(2);
       $("#win-add").modal("show");
       $("#name").focus();
     } else {
       layer.confirm("确定删除该记录么？", function(i) {
         $.post("/company/system/depart-save", {
           id : tr.attr("data-id"),
           del : 1
         }, function(result) {
           layer.close(i);
           if(tr.is(".dantdxz")) {
             var trs = tr.parent().find("tr");
             if(trs.length > 0) {
               trs.eq(0).click();
             }
           }
           tr.remove();
         })
       })
     }
   })
   
   //班组编辑、删除点击事件
   $("#table2").on("click", ".dantd i", function() {
     var td = $(this).closest("td");
     var tr = td.parent();
     var titles;
     if($(this).attr("name") == 'edit') {//编辑
       $("#name").val(tr.attr("data-name"));
       $("#id").val(tr.attr("data-id"));
       $("#cid").val(tr.attr("data-cid"));
       $("#pid").val(tr.attr("data-pid"));
       if(userType =='3'){
          titles= ["", "单位名称", "部门名称", "科室名称"];
       }else{
          titles = ["", "公司名称", "车间名称", "岗位/部位名称"];
       }
       $("#label_").text(titles[3]);
       $("#type").val(3);
       $("#win-add").modal("show");
       $("#name").focus();
     } else {
       layer.confirm("确定删除该记录么？", function(i) {
         $.post("/company/system/depart-save", {
           id : tr.attr("data-id"),
           del : 1
         }, function(result) {
           layer.close(i);
           if(tr.is(".dantdxz")) {
             var trs = tr.parent().find("tr");
             if(trs.length > 0) {
               trs.eq(0).click();
             }
           }
           tr.remove();
         })
       })
     }
   })
   
   //默认选中第一个企业
   if($("#tbl tr").length > 0) {
     $("#tbl tr").eq(0).click();
   }
 })
 
 //添加弹窗显示 1企业 2部门 3班组
 function showadd(type) {
  // TODO 根据session中的用户信息, 对新系统进行添加
  var userType = '${session_user.userType}';
  var titles;
  if(userType =='3'){
     titles = ["", "单位名称", "部门名称", "科室名称"];
  }else{
     titles = ["", "公司名称", "车间名称", "岗位/部位名称"];
  }
  $("#label_").text(titles[type]);
  $("#type").val(type);
  $("#name").val('');
  $("#id").val('');
  $("#win-add").modal("show");
 } 
</script>
</html>