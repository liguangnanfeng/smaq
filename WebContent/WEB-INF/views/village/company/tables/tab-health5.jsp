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
  id=Id
  $("#upload-zl").modal("show")}
</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>职业健康管理</span> 
    <span class="c-gray en">&gt;</span> <span>职业健康管理档案</span> 
    <span class="c-gray en">&gt;</span> <span>用人单位职业健康监护管理</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:history.back(-1)" title="返回">返回</a>
  </nav>
  <div class="page-container">
    <div class="text-c">
    <form action="${ly }/company/tables/tab-health/5" method="post">
      <div class="dis-ib">
        <span>资料名称：</span>
        <input type="text" value="${seName }" id="seName" name="seName" class="input-text mb-5 mt-5" style="width:150px;">
      </div>
      <div class="dis-ib">
        <span>资料类型：</span>
        <span class="select-box inline">
        <select name="isType" class="select">
          <option value="">全部</option>
          <option value="36" <c:if test="${36==isType}">selected</c:if>>职业健康检查机构资质证书</option>
          <option value="37" <c:if test="${37==isType}">selected</c:if>>职业健康检查结果汇总表</option>
          <option value="38" <c:if test="${38==isType}">selected</c:if>>职业健康检查异常结果登记表</option>
          <option value="39" <c:if test="${39==isType}">selected</c:if>>职业病患者、疑似职业病患者一览表</option>
          <option value="40" <c:if test="${40==isType}">selected</c:if>>职业病和疑似职业病人的报告</option>
          <option value="41" <c:if test="${41==isType}">selected</c:if>>职业病危害事故报告和处理记录</option>
          <option value="42" <c:if test="${42==isType}">selected</c:if>>职业健康监护档案汇总表</option>
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
        <a class="btn btn-primary radius mt-5 mb-5"  onclick="" href="${ly}/company/tables/hDownload?isType=11"><i class="Hui-iconfont" style="font-size:15px;">&#xe640;</i> 下载职业健康检查结果汇总表</a>
        <a class="btn btn-primary radius mt-5 mb-5"  onclick="" href="${ly}/company/tables/hDownload?isType=12"><i class="Hui-iconfont" style="font-size:15px;">&#xe640;</i> 下载职业健康检查异常结果登记表</a>
        <a class="btn btn-primary radius mt-5 mb-5"  onclick="" href="${ly}/company/tables/hDownload?isType=13"><i class="Hui-iconfont" style="font-size:15px;">&#xe640;</i> 下载职业病患者一览表</a>
        <a class="btn btn-primary radius mt-5 mb-5"  onclick="" href="${ly}/company/tables/hDownload?isType=14"><i class="Hui-iconfont" style="font-size:15px;">&#xe640;</i> 下载疑似职业病患者一览表</a>
      </div> --%>
      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span>
    </div>
    <div class="Huialert Huialert-danger mt-5">职业健康检查异常结果登记表（附：职业健康监护结果评价报告）
      <br>职业病患者、疑似职业病患者一览表（附：职业病诊断证明书、职业病诊断鉴定书等）
      <br>职业病和疑似职业病人的报告（注：在接到体检结果、诊断结果5日内报告）
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
                <c:if test="${36==h.isType}">职业健康检查机构资质证书</c:if>
                <c:if test="${37==h.isType}">职业健康检查结果汇总表</c:if>
                <c:if test="${38==h.isType}">职业健康检查异常结果登记表</c:if>
                <c:if test="${39==h.isType}">职业病患者、疑似职业病患者一览表</c:if>
                <c:if test="${40==h.isType}">职业病和疑似职业病人的报告</c:if>
                <c:if test="${41==h.isType}">职业病危害事故报告和处理记录</c:if>
                <c:if test="${42==h.isType}">职业健康监护档案汇总表</c:if>
            </td>
            <td>
              <div class="btn-upload">
                <div class="btn btn-primary-outline radius" onclick="openzl(${h.id})"  style="height: 21px;line-height: 21px;padding: 0 2px;margin-top: 6px;">更改资料</div>
              </div>
              <c:if test="${!empty h.url}">
              <a style="text-decoration:none" onclick="" href="${ly}/common/download?filename=${h.name}&fileurl=${h.url}" title="查看资料" target="_blank">查看资料</a>
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
                  <select name="isTab" id="b"  class="select" style="height:31px;">
                      <option value="">全部</option>
			          <option value="36" <c:if test="${36==isType}">selected</c:if>>职业健康检查机构资质证书</option>
			          <option value="37" <c:if test="${37==isType}">selected</c:if>>职业健康检查结果汇总表</option>
			          <option value="38" <c:if test="${38==isType}">selected</c:if>>职业健康检查异常结果登记表</option>
			          <option value="39" <c:if test="${39==isType}">selected</c:if>>职业病患者、疑似职业病患者一览表</option>
			          <option value="40" <c:if test="${40==isType}">selected</c:if>>职业病和疑似职业病人的报告</option>
			          <option value="41" <c:if test="${41==isType}">selected</c:if>>职业病危害事故报告和处理记录</option>
			          <option value="42" <c:if test="${42==isType}">selected</c:if>>职业健康监护档案汇总表</option>
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