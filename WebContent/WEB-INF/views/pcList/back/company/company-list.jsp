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
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>企业信息库</span> 
    <span class="c-gray en">&gt;</span> <span>全部企业</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="text-c">
      <form action="${ly }/back/company/company-list" method="post">
        <div class="dis-ib"><span>村庄名称：</span><input type="text" value="${dto.villageName }" name="villageName" class="input-text mb-5 mt-5" style="width:150px;"></div>
        <div class="dis-ib">
        <span>企业名称：</span>
        <%-- <input type="text" value="${companyName }" name="companyName" class="input-text mb-5 mt-5" style="width:150px;"> --%>
        <input type="text" value="${dto.companyName }" name="companyName" class="input-text mb-5 mt-5" style="width:150px;">
        <input type="text" value="${dto.page }" name="page" id="page" style="display: none;">
        <input type="text" value="${dto.rows }" name="rows" id="rows" style="display: none;">
        <input type="text" value="${dto.doubleDanger }" name="doubleDanger"  style="display: none;">
        <input type="text" value="${dto.hazard }" name="hazard" style="display: none;">
        <input type="text" value="${dto.danger }" name="danger" style="display: none;">
        </div>
        <div class="dis-ib">
        <span>企业状态：</span>
            <select name="isFreeze" class="input-text mb-5 mt-5" style="width:150px;">
                <option value="">全部</option>
                <option value="0"<c:if test="${dto.isFreeze == '0'}"> selected</c:if>>正常</option>
                <option value="1"<c:if test="${dto.isFreeze == '1'}"> selected</c:if>>冻结</option>
            </select>
        </div>
        <button class="btn btn-success" type="submit" onclick="$('#page').val(0)">
          <i class="Hui-iconfont">&#xe665;</i> 查询
        </button>
      </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
    <span class="l isAll">
        <button class="btn btn-success" type="button" onclick="comapnyImportBoxShow()">批量导入企业用户</button>
        <a class="btn btn-success" href="${ly}/back/download?filename=导入模板.xlsx&fileurl=${ly}/upload/companymodel.xlsx">导入模板下载</a>
        <a class="btn btn-success" data-title="添加企业" data-href="${ly }/back/company-add" onclick="Hui_admin_tab(this)" href="javascript:;">添加企业</a>
    </span>
		<span class="r">共有数据：<strong><%-- ${fn:length(list) } --%>${total }</strong> 条</span>
	</div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="15%">企业名称</th>
            <th width="15%">所属村庄</th>
            <th width="15%">所属行业</th>
            <th width="15%">风险等级</th>
            <th width="20%">详细地址</th>
            <th width="15%">操作</th>
          </tr>
        </thead>
        <tbody>
        
          <!-- 循环村 -->
         <c:forEach items="${list }" varStatus="index" var="co">
          <tr class="text-c">
            <td>${dto.page * dto.rows + index.index + 1}</td>
            <td><c:if test="${co.isFreeze == '1'}"><label style="color:red;">【冻结】</label></c:if>${co.name }</td>
            <td ondblclick="window.open('/fore/company?un=${co.userName}');">${co.villageName }</td>
            <td>${co.industry2 }</td>
            <td>${co.dlevel }</td>
            <td>${co.regionName} ${co.address }</td>
            <td>
              <a class="label label-primary radius" style="text-decoration:none" onClick="admin_edit(${co.userId })" href="javascript:;" title="查看企业详细资料">查看企业详细资料</a>
              <a class="label label-primary radius" style="text-decoration:none" onClick="name_edit(${co.userId })" href="javascript:;" title="更改名称">更改名称</a>
              <c:if test="${co.isFreeze == '1'}">
              <a class="label label-primary radius" style="text-decoration:none" onClick="upd(${co.userId}, 0)" href="javascript:;" title="企业解冻">企业解冻</a>
              </c:if>
              <c:if test="${co.isFreeze == '0'}">
              <a class="label label-primary radius" style="text-decoration:none" onClick="upd(${co.userId}, 1)" href="javascript:;" title="冻结企业">企业冻结</a>
              </c:if>
               <a class="label label-primary radius" style="text-decoration:none" onClick="del_(${co.userId}, 1)" href="javascript:;" title="删除企业">企业删除</a>
            </td>
          </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
      <div id="layer_page_div" style="margin-top: 15px;"></div>
    </div>
  </div>
  
  <div id="comapnyImportBox" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
       <div class="modal-dialog">
           <div class="modal-content radius">
           
               <div class="modal-header">
                   <h3 class="modal-title">导入企业用户</h3>
                   <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
               </div>


        <div class="modal-body">
          <div class="one_txt cl">
            <div class="text-left" style="float: top; width: 100px;">请选择所在地：</div>
            <div class="formControls col-xs-8 col-sm-9">
              <select id="townId" class="sel_area">
                <option value="">请选择</option>
                <c:forEach items="${listL }" var="be">
                  <option value="${be.userId }">${be.name }</option>
                </c:forEach>
              </select> <select id="villageId" class="sel_area">
                <option value="">请选择</option>
              </select>
            </div>
          </div>
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
                   <button class="btn btn-primary" onclick="comapnyImportSave()">保存</button>
                   <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
               </div>
           </div>
       </div>
   </div>
   
   
   
    <div id="companyNameEdit" class="modal fade" tabindex="-1" role="dialog" aria-

labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content radius">
      <div class="modal-header">
        <h3 class="modal-title">更改企业名称</h3>
        <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
      </div>
      <div class="modal-body">
        <div class="one_txt cl">
          <div class="text-left" style="float:left;">企业名称：</div>
          <div class="formControls col-xs-8 col-sm-9">
         <input type="text" style="width:200px;" id="editName" value="">
         <input type="hidden" id="companyId" value="">
         </div>
       </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" onclick="comapnyNameSave()">保存</button>
        <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
$(function() {
  /* $('.table-sort').dataTable({
    "aaSorting": [[0, "asc"]],//默认第几个排序
    "bStateSave": false,//状态保存
    searching: true,
    ordering:  true,
    "aoColumnDefs": [
    ]
    }); */


  $("#townId").change(function() {
    $("#villageId").empty().append('<option value="">请选择</option>');
    if($(this).val() != '') {
      $.post(getRootPath() + "/back/vilage-list", {
        townId : $("#townId").val()
      },function(result) {
        var list = result.map.list;
        $.each(list,function(i,item) {
          $("#villageId").append('<option value="'+item.userId+'">'+item.name+'</option>');
          
        })
      })
    }
  })
  
  $("#villageId").change(function() {$("#importBox").show();})
  
});

/*企业详细信息*/
function admin_edit(userId){
  show_dialog("企业详细信息",getRootPath() + "/back/company/company-show?userId="+userId)
}

function upd(id, isF) {
  var xx = "确定冻结该企业？";
  if(isF == 0) {
    xx = "确定解除该企业冻结状态？";
  }
  layer.confirm(xx,function(i) {
    layer.close(i);
    $.post("/back/com-upd",{
      id : id,
      isFreeze : isF
    },function(result) {
      $("#page").val(0);
      $("form").submit();
    })
  })
}

function del_(id) {
  layer.confirm("确定删除该企业？",function(i) {
    layer.close(i);
    $.post("/back/com-upd",{
      id : id,
      isDel : 1
    },function(result) {
      $("form").submit();
    })
  })
}

function initCode(){
  $("#townId").val("");
  $("#villageId").val("");
}

function comapnyImportBoxShow() {
    initCode();
	$("#comapnyImportBox").modal("show");
	$("#importBox").hide();
}

function comapnyImportSave() {
  	var index = layer.load();
  	var villageId = $("#villageId").val();
  	if(villageId == ""){
  	  layer.alert("请选择所在地！");
      return false;
  	}
	$.ajaxFileUpload({
	    url: getRootPath() + '/back/importExcel?villageId='+ villageId,
	    secureuri: false, //一般设置为false
	    fileElementId: 'file',
	    dataType: 'json',
	    async: false,
	    success: function (data, status) {
	      	layer.close(index);
	        var status = data.status;
	        var count = data.map.count;
	        var mess = data.mess;
	        if (status == '0') {
	          layer.alert("成功导入" + count + "条数据\r\n" +"。"+ mess, {}, function (ind) {
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
</script> 
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



function name_edit(id) {
  $("#editName").attr("value","");
  $.post("/back/company-name-show",{
    id : id
  },function(result) {
    var name = result.map.list;
    $("#editName").val(name);
  })
  $("#companyNameEdit").modal("show");
  $("#companyId").val(id);
}

function comapnyNameSave() {
  var index = layer.load();
  var name = $("#editName").val();
  if(name == ""){
    layer.alert("企业名称不能为空！");
    return false;
  }
  layer.confirm("确定更改该企业名称吗？",function(i) {
    layer.close(i);
    $.post("/back/edit-name-save",{
      userName : name,
      id : $("#companyId").val()
    },function(result) {
      if(result.status == '1') {
        layer.alert(result.map.message);
        return false;
      }
      layer.alert("保存成功","",function(index){
      layer.close(index);
      $("form").submit();
      })
    })
  })
}
</script>
</html>