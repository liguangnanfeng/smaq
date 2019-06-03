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
body .dis-ib {
  margin-right: 15px;
}

.btn-group .btn {
  height: 34px;
  line-height: 34px;
  padding: 0 25px;
}
</style>
</head>

<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> <span class="c-gray en">&gt;</span> <span>安全生产标准化</span> <span class="c-gray en">&gt;</span> <span>管理档案</span>
    <span class="c-gray en">&gt;</span> <span>教育培训</span> <span class="c-gray en">&gt;</span> <span>持证上岗</span> <a class="btn btn-success radius r"
      style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新"> <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div id="spTab" class="btn-group" style="text-align: center; margin-bottom: 20px;">
      <a class="btn default radius <c:if test="${empty spFlag || spFlag == 0}"> btn-primary</c:if>" href="${ly }/village/warning/sperson-list?spFlag=0&isTime=${isTime }&home=${home}">全部</a> 
      <a class="btn default radius <c:if test="${spFlag == 1}"> btn-primary</c:if>" href="${ly }/village/warning/sperson-list?spFlag=1&isTime=${isTime }&home=${home}">安全管理人员</a> 
      <a class="btn default radius <c:if test="${spFlag == 2}"> btn-primary</c:if>" href="${ly }/village/warning/sperson-list?spFlag=2&isTime=${isTime }&home=${home}">危险化学品安全管理人员</a> 
      <a class="btn default radius <c:if test="${spFlag == 3}"> btn-primary</c:if>" href="${ly }/village/warning/sperson-list?spFlag=3&isTime=${isTime }&home=${home}">特种作业人员</a>
    </div>    
    
    <!-- 判断其他持证上岗显示 -->
    <div class="list_other">
    <div class="text-c">
      <form action="${ly }/village/warning/sperson-list?spFlag=${spFlag}&home=${home}" method="post">
        <div class="dis-ib">
          <span>企业名称：</span> <input type="text" value="${companyName }" name="companyName" class="input-text mb-5 mt-5" style="width: 150px;">
        </div>
        <c:if test="${home != 1 }">
        <div class="dis-ib">
          <span>到期时间：</span> 
          <span class="select-box inline"> 
            <select name="isTime" class="select">
              <option value="">全部</option>
              <option value="1" <c:if test="${1==isTime}">selected</c:if>>1个月内到期</option>
              <option value="2" <c:if test="${2==isTime}">selected</c:if>>2个月内到期</option>
              <option value="3" <c:if test="${3==isTime}">selected</c:if>>3个月内到期</option>
              <option value="0" <c:if test="${0==isTime}">selected</c:if>>已过期</option>
            </select>
          </span>
        </div>
        </c:if>
        <button class="btn btn-success" type="submit">
          <i class="Hui-iconfont">&#xe665;</i> 查询
        </button>
      </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
        <a id="specialPrint" onclick="show_dialog('特种作业人员-打印', '${ly }/company/special-print?spFlag=${spFlag}&spType=${spType }&isTime=${isTime }&spName=${spName }')" class="btn btn-primary radius" href="javascript:void(0);"><i class="Hui-iconfont mr-10">&#xe652;</i>打印 </a>
        <button class="btn btn-success" type="button" onclick="comapnyImportBoxShow(1)">导入安全管理人员名单</button>
        <button class="btn btn-success" type="button" onclick="comapnyImportBoxShow(2)">导入危险化学品安全管理人员名单</button>
        <button class="btn btn-success" type="button" onclick="comapnyImportBoxShow(3)">导入持证上岗人员名单</button>
        <a class="btn btn-success" href="${ly}/village/download?filename=人员模板.xlsx&fileurl=${ly}/upload/spersonmodel.xlsx">人员模板下载</a>
        </span>
		<span class="r">共有数据：<strong>${fn:length(list) }</strong> 条 </span>
    </div>
    <c:set var="flagV" value="${fn:split('安全管理人员/危险化学品安全管理人员/特种作业人员','/') }" />
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="10%">企业名称</th>
            <th width="${wid1[spFlag]}%">姓名</th>
            <c:if test="${spFlag == 0 }">
            <th width="10%">所属岗位</th>
            </c:if>
            <th width="10%">工种</th>
            <th width="${wid1[spFlag]}%">证书编号</th>
            <th width="${wid1[spFlag]}%">取证时间</th>
            <th width="10%">下次复审时间</th>
            <th width="${wid1[spFlag]}%">备注</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环-->
          <c:forEach items="${list }" varStatus="index" var="spe">
            <tr class="text-c">
              <td>${index.index+1 }</td>
              <td>${spe.companyName }</td>
              <td>${spe.name }</td>
              <c:if test="${spFlag == 0 }">
              <td>${flagV[spe.flag - 1] }</td>
              </c:if>
              <td>${spe.type }</td>
              <td>${spe.certificateNumber }</td>
              <td>${spe.forensicTime }</td>
              <td>${spe.reviewTime }</td>
              <td>${spe.remark }</td>
              
            </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
    </div>
  </div>
  
  <div id="comapnyImportBox" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
       <div class="modal-dialog">
           <div class="modal-content radius">
           
               <div class="modal-header">
                   <h3 class="modal-title">导入持证上岗人员名单</h3>
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
                   <button class="btn btn-primary" onclick="comapnyImportSave()">保存</button>
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
      searching: false,
      ordering:  false,
      "aoColumnDefs": [
      ]
    });
  });
  

  function comapnyImportBoxShow(flag) {
    window.sperson_flag = flag;
    if(flag == 1) {
    $("#comapnyImportBox .modal-title").text("导入安全管理人员");
    }
    if(flag == 2) {
    $("#comapnyImportBox .modal-title").text("导入危险化学品安全管理人员");
    }
    if(flag == 3) {
    $("#comapnyImportBox .modal-title").text("导入持证上岗人员");
    }
    $("#comapnyImportBox").modal("show");
  }


  function comapnyImportSave() {
    var index = layer.load();
  $.ajaxFileUpload({
      url: getRootPath() + '/village/importSpersonExcel?flag=' + sperson_flag,
      secureuri: false, //一般设置为false
      fileElementId: 'file',
      dataType: 'json',
      async: false,
      success: function (data, status) {
          layer.close(index);
          var status = data.status;
          if (status == '0') {
            layer.alert("导入成功");
            $("#comapnyImportBox").modal("hide");
          }else {
            layer.alert(data.map.message);
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
</html>