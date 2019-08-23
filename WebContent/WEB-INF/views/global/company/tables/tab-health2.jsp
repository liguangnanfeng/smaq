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
.btn-upload{position: relative; display:inline-block;height:36px; *display:inline;overflow:hidden;vertical-align:middle;cursor:pointer}
.upload-url{cursor: pointer}
.input-file{position:absolute; right:0; top:0; cursor: pointer; z-index:1; font-size:30em; *font-size:30px;opacity:0;filter: alpha(opacity=0)}
.btn-upload .input-text{ width:auto}
.form-group .upload-btn{ margin-left:-1px}
</style>
<script type="text/javascript">
var id;
function openzl(Id){
  id = Id;
  $("#upload-zl").modal("show")
}
</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>职业健康管理</span> 
    <span class="c-gray en">&gt;</span> <span>职业健康管理档案</span> 
    <span class="c-gray en">&gt;</span> <span>职业卫生管理</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:history.back(-1)" title="返回">返回</a>
  </nav>
  <div class="page-container">
    <div class="text-c">
    <form action="${ly }/company/tables/tab-health/2" method="post">
      <div class="dis-ib">
        <span>资料名称：</span>
        <input type="text" value="${seName }" id="seName" name="seName" class="input-text mb-5 mt-5" style="width:150px;">
      </div>
      <div class="dis-ib">
        <span>资料类型：</span>
        <span class="select-box inline">
        <select name="isType" class="select">
          <option value="">全部</option>
          <option value="10" <c:if test="${10==isType}">selected</c:if>>职业病防治法律、行政法规、规章、标准、文件</option>
          <option value="11" <c:if test="${11==isType}">selected</c:if>>职业病防治领导机构及职业卫生管理机构成立文件</option>
          <option value="12" <c:if test="${12==isType}">selected</c:if>>职业病防治年度计划及实施方案</option>
          <option value="13" <c:if test="${13==isType}">selected</c:if>>职业卫生管理制度及重点岗位职业卫生操作规程</option>
          <option value="14" <c:if test="${14==isType}">selected</c:if>>职业病危害项目申报表及回执</option>
          <option value="15" <c:if test="${15==isType}">selected</c:if>>职业病防治经费</option>
          <option value="16" <c:if test="${16==isType}">selected</c:if>>职业病防护设施一览表</option>
          <option value="17" <c:if test="${17==isType}">selected</c:if>>职业病防护设施维护和检修记录</option>
          <option value="18" <c:if test="${18==isType}">selected</c:if>>个人防护用品的购买、发放使用记录</option>
          <option value="19" <c:if test="${19==isType}">selected</c:if>>警示标识与职业病危害告知</option>
          <option value="20" <c:if test="${20==isType}">selected</c:if>>职业病危害事故应急救援预案</option>
          <option value="21" <c:if test="${21==isType}">selected</c:if>>用人单位职业卫生检查和处理记录</option>
          <option value="22" <c:if test="${22==isType}">selected</c:if>>职业卫生监管意见和落实情况资料</option>
        </select>
        </span>
      </div>
      <button class="btn btn-success" type="submit">
        <i class="Hui-iconfont">&#xe665;</i> 查询
      </button>
      <button class="btn btn-warning ml-10" type="button" onclick="openzl(-1)"><i class="Hui-iconfont" style="font-size:15px;">&#xe642;</i> 上传资料</button>
	</form>   
	</div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <%-- <div class="l">
        <a class="btn btn-primary radius mt-5 mb-5" href="${ly}/company/tables/hDownload?isType=1"><i class="Hui-iconfont" style="font-size:15px;">&#xe640;</i> 下载职业病防治经费一览表</a>
        <a class="btn btn-primary radius mt-5 mb-5" href="${ly}/company/tables/hDownload?isType=2"><i class="Hui-iconfont" style="font-size:15px;">&#xe640;</i> 下载职业病防护设施一览表</a>
        <a class="btn btn-primary radius mt-5 mb-5" href="${ly}/company/tables/hDownload?isType=3"><i class="Hui-iconfont" style="font-size:15px;">&#xe640;</i> 下载职业病防护设施维护和检修记录表</a>
        <a class="btn btn-primary radius mt-5 mb-5" href="${ly}/company/tables/hDownload?isType=4"><i class="Hui-iconfont" style="font-size:15px;">&#xe640;</i> 下载个人防护用品发放使用记录表</a>
        <a class="btn btn-primary radius mt-5 mb-5" href="${ly}/company/tables/hDownload?isType=5"><i class="Hui-iconfont" style="font-size:15px;">&#xe640;</i> 下载工作场所警示标识一览表</a>
        <a class="btn btn-primary radius mt-5 mb-5" href="${ly}/company/tables/hDownload?isType=6" style="margin-top:10px;"><i class="Hui-iconfont" style="font-size:15px;">&#xe640;</i> 下载用人单位职业卫生检查和处理记录表</a>
      </div> --%>
      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span>
    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="40%">资料名称</th>
            <th width="20%">上传时间</th>
            <th width="20%">资料类型</th>
            <th width="15%">操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环-->
          <c:forEach items="${list }" varStatus="index" var="h">
          <tr class="text-c">
            <td>${index.index+1 }</td>
            <td>${h.name} </td>
            <td><fmt:formatDate value="${h.createTime }" pattern="yyyy-MM-dd"/></td>
            <td>
                <c:if test="${10==h.isType}">职业病防治法律、行政法规、规章、标准、文件</c:if>
                <c:if test="${11==h.isType}">职业病防治领导机构及职业卫生管理机构成立文件</c:if>
                <c:if test="${12==h.isType}">职业病防治年度计划及实施方案</c:if>
                <c:if test="${13==h.isType}">职业卫生管理制度及重点岗位职业卫生操作规程</c:if>
                <c:if test="${14==h.isType}">职业病危害项目申报表及回执</c:if>
                <c:if test="${15==h.isType}">职业病防治经费</c:if>
                <c:if test="${16==h.isType}">职业病防护设施一览表</c:if>
                <c:if test="${17==h.isType}">职业病防护设施维护和检修记录</c:if>
                <c:if test="${18==h.isType}">个人防护用品的购买、发放使用记录</c:if>
                <c:if test="${19==h.isType}">警示标识与职业病危害告知</c:if>
                <c:if test="${20==h.isType}">职业病危害事故应急救援预案</c:if>
                <c:if test="${21==h.isType}">用人单位职业卫生检查和处理记录</c:if>
                <c:if test="${22==h.isType}">职业卫生监管意见和落实情况资料</c:if>
            </td>
            <td>
              <div class="btn-upload">
                <div class="btn btn-primary-outline radius" onclick="openzl(${h.id})"  style="height: 21px;line-height: 21px;padding: 0 2px;margin-top: 6px;">更改资料</div>
              </div>
              <c:if test="${!empty h.url}">
              <a style="text-decoration:none" href="${ly}/common/download?filename=${h.name}&fileurl=${h.url}" title="查看资料" target="_blank">查看资料</a>
              </c:if>
              <a style="text-decoration:none" onclick="del(${h.id})" href="javascript:;" title="删除">删除</a>
            </td>
          </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
  </div>
  <!-- 弹窗上传资料 -->
  <div id="upload-zl" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header">
                <h3 class="modal-title">上传资料</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body">
              <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2" style="width:20%;text-align:right;">资料类型：</label>
                <div class="formControls col-xs-8 col-sm-9" style="width:70%;">
                  <select name="isType" id="b" class="select" style="height:31px;">
                     <option value="">全部</option>
		             <option value="10" <c:if test="${10==isType}">selected</c:if>>职业病防治法律、行政法规、规章、标准、文件</option>
			         <option value="11" <c:if test="${11==isType}">selected</c:if>>职业病防治领导机构及职业卫生管理机构成立文件</option>
			         <option value="12" <c:if test="${12==isType}">selected</c:if>>职业病防治年度计划及实施方案</option>
			         <option value="13" <c:if test="${13==isType}">selected</c:if>>职业卫生管理制度及重点岗位职业卫生操作规程</option>
			         <option value="14" <c:if test="${14==isType}">selected</c:if>>职业病危害项目申报表及回执</option>
			         <option value="15" <c:if test="${15==isType}">selected</c:if>>职业病防治经费</option>
			         <option value="16" <c:if test="${16==isType}">selected</c:if>>职业病防护设施一览表</option>
			         <option value="17" <c:if test="${17==isType}">selected</c:if>>职业病防护设施维护和检修记录</option>
			         <option value="18" <c:if test="${18==isType}">selected</c:if>>个人防护用品的购买、发放使用记录</option>
			         <option value="19" <c:if test="${19==isType}">selected</c:if>>警示标识与职业病危害告知</option>
			         <option value="20" <c:if test="${20==isType}">selected</c:if>>职业病危害事故应急救援预案</option>
			         <option value="21" <c:if test="${21==isType}">selected</c:if>>用人单位职业卫生检查和处理记录</option>
			         <option value="22" <c:if test="${22==isType}">selected</c:if>>职业卫生监管意见和落实情况资料</option>
                  </select>
                </div> 
              </div>
              <div class="row cl mt-15">
                <label class="form-label col-xs-4 col-sm-2" style="width:20%;text-align:right;">资料上传：</label>
                <div class="formControls col-xs-8 col-sm-9" style="width:70%;">
                  <span class="btn-upload form-group">
                    <input class="input-text upload-url" type="text" name="uploadfile-1" id="uploadfile-1" readonly><a href="javascript:void();" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe642;</i> 浏览文件</a>
                    <input type="file" multiple name="file" id="file"  class="input-file">
                  </span>
                </div> 
              </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" onclick="upload($('#b option:selected').attr('value'))" >确定</button>
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

/*删除*/
function del(id){
  $.post(getRootPath()+"/company/tables/h1-del",{
    id:id,
  },function(reuslt){
    location.reload()
  })
}

/*上传*/
function upload(isType) {
  $.ajaxFileUpload({
      url: getRootPath() + '/company/tables/h-leadin?isType='+isType+ "&id="+id, //用于文件上传的服务器端请求地址
      secureuri: false, //一般设置为false
      fileElementId: 'file', //文件上传空间的id属性  <input type="file" id="file" name="file" />
      dataType: 'json', //返回值类型 一般设置为json
      async: false,
      success: function (result) { //服务器成功响应处理函数
        $(".upload-url").val("");
        if(result.status == '0') {
          layer.alert("导入成功","",function() {
            location.reload();
          })
        } else {
          if(null != result.map.message) {
            layer.alert(result.map.message);
          } else {
            var list = result.map.list;
            layer.alert(list.join("<br>"));
          }
        }
      },
      error: function (data, status, e) {//服务器响应失败处理函数
        alert("文件上传失败");
      }
  })
}
</script>
</body>
</html>