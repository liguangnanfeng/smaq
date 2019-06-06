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
var id;
function openzl(Id){
  id=Id
  $("#upload-zl").modal("show")}
</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <c:if test="${0==isType}">
      <span class="c-gray en">&gt;</span> <span>安全生产标准化</span> 
      <span class="c-gray en">&gt;</span> <span>安全生产责任制</span> 
      <span class="c-gray en">&gt;</span> <span>安全生产责任制</span> 
    </c:if>
    <c:if test="${1==isType}">
      <span class="c-gray en">&gt;</span> <span>安全生产标准化</span> 
      <span class="c-gray en">&gt;</span> <span>安全生产管理制度</span> 
    </c:if>
    <c:if test="${2==isType}">
      <span class="c-gray en">&gt;</span> <span>安全生产标准化</span> 
      <span class="c-gray en">&gt;</span> <span>安全生产教育培训</span> 
      <span class="c-gray en">&gt;</span> <span>培训需求调查表</span> 
    </c:if>
    <c:if test="${3==isType}">
      <span class="c-gray en">&gt;</span> <span>安全生产标准化</span> 
      <span class="c-gray en">&gt;</span> <span>安全生产教育培训</span> 
      <span class="c-gray en">&gt;</span> <span>年度培训计划</span> 
    </c:if>
    <c:if test="${4==isType}">
      <span class="c-gray en">&gt;</span> <span>安全生产标准化</span> 
      <span class="c-gray en">&gt;</span> <span>安全生产教育培训</span> 
      <span class="c-gray en">&gt;</span> <span>变换工种/四新安全教育卡</span> 
    </c:if>
    <c:if test="${5==isType}">
      <span class="c-gray en">&gt;</span> <span>安全生产标准化</span> 
      <span class="c-gray en">&gt;</span> <span>安全生产教育培训</span> 
      <span class="c-gray en">&gt;</span> <span>复工安全教育卡</span> 
    </c:if>
    <c:if test="${6==isType}">
      <span class="c-gray en">&gt;</span> <span>安全生产标准化</span> 
      <span class="c-gray en">&gt;</span> <span>安全生产教育培训</span> 
      <span class="c-gray en">&gt;</span> <span>三级安全教育卡</span> 
    </c:if>
    <c:if test="${7==isType}">
      <span class="c-gray en">&gt;</span> <span>安全生产标准化</span> 
      <span class="c-gray en">&gt;</span> <span>安全生产教育培训</span> 
      <span class="c-gray en">&gt;</span> <span>培训记录表</span> 
    </c:if>
    <c:if test="${9==isType}">
      <span class="c-gray en">&gt;</span> <span>安全生产标准化</span> 
      <span class="c-gray en">&gt;</span> <span>设备设施管理</span> 
      <span class="c-gray en">&gt;</span> <span>年度综合检维修计划</span> 
    </c:if>
    <c:if test="${10==isType}">
      <span class="c-gray en">&gt;</span> <span>安全生产标准化</span> 
      <span class="c-gray en">&gt;</span> <span>设备设施管理</span> 
      <span class="c-gray en">&gt;</span> <span>手持电动工具绝缘电阻值测量记录表</span> 
    </c:if>
    <c:if test="${11==isType}">
      <span class="c-gray en">&gt;</span> <span>安全生产标准化</span> 
      <span class="c-gray en">&gt;</span> <span>设备设施管理</span> 
      <span class="c-gray en">&gt;</span> <span>移动电气设备绝缘电阻测试表</span> 
    </c:if>
    <c:if test="${12==isType}">
      <span class="c-gray en">&gt;</span> <span>安全生产标准化</span> 
      <span class="c-gray en">&gt;</span> <span>危险作业证</span> 
      <span class="c-gray en">&gt;</span> <span>动火作业证</span> 
    </c:if>
    <c:if test="${13==isType}">
      <span class="c-gray en">&gt;</span> <span>安全生产管理</span> 
      <span class="c-gray en">&gt;</span> <span>危险作业证</span> 
      <span class="c-gray en">&gt;</span> <span>受限空间安全作业证</span> 
    </c:if>
    <c:if test="${14==isType}">
      <span class="c-gray en">&gt;</span> <span>安全生产管理</span> 
      <span class="c-gray en">&gt;</span> <span>危险作业证</span> 
      <span class="c-gray en">&gt;</span> <span>盲板抽堵安全作业证</span> 
    </c:if>
    <c:if test="${15==isType}">
      <span class="c-gray en">&gt;</span> <span>安全生产管理</span> 
      <span class="c-gray en">&gt;</span> <span>危险作业证</span> 
      <span class="c-gray en">&gt;</span> <span>高处安全作业证</span> 
    </c:if>
    <c:if test="${16==isType}">
      <span class="c-gray en">&gt;</span> <span>安全生产管理</span> 
      <span class="c-gray en">&gt;</span> <span>危险作业证</span> 
      <span class="c-gray en">&gt;</span> <span>吊装安全作业证</span> 
    </c:if>
    <c:if test="${17==isType}">
      <span class="c-gray en">&gt;</span> <span>安全生产管理</span> 
      <span class="c-gray en">&gt;</span> <span>危险作业证</span> 
      <span class="c-gray en">&gt;</span> <span>临时用电安全作业证</span> 
    </c:if>
    <c:if test="${18==isType}">
      <span class="c-gray en">&gt;</span> <span>安全生产管理</span> 
      <span class="c-gray en">&gt;</span> <span>危险作业证</span> 
      <span class="c-gray en">&gt;</span> <span>动土安全作业证</span> 
    </c:if>
    <c:if test="${19==isType}">
      <span class="c-gray en">&gt;</span> <span>安全生产管理</span> 
      <span class="c-gray en">&gt;</span> <span>危险作业证</span> 
      <span class="c-gray en">&gt;</span> <span>断路安全作业证</span> 
    </c:if>
    <c:if test="${20==isType}">
      <span class="c-gray en">&gt;</span> <span>安全生产管理</span> 
      <span class="c-gray en">&gt;</span> <span>安全检查和隐患排查</span> 
      <span class="c-gray en">&gt;</span> <span>安全检查计划</span> 
    </c:if>
    <c:if test="${21==isType}">
      <span class="c-gray en">&gt;</span> <span>安全生产管理</span> 
      <span class="c-gray en">&gt;</span> <span>安全检查和隐患排查</span> 
      <span class="c-gray en">&gt;</span> <span>安全隐患整改通知单</span> 
    </c:if>
    <c:if test="${22==isType}">
      <span class="c-gray en">&gt;</span> <span>安全生产管理</span> 
      <span class="c-gray en">&gt;</span> <span>安全奖惩</span> 
      <span class="c-gray en">&gt;</span> <span>安全责任制考核和奖惩台帐</span> 
    </c:if>
    <c:if test="${23==isType}">
      <span class="c-gray en">&gt;</span> <span>安全生产管理</span> 
      <span class="c-gray en">&gt;</span> <span>安全奖惩</span> 
      <span class="c-gray en">&gt;</span> <span>安全工作考核和奖惩台帐</span> 
    </c:if>
    <c:if test="${24==isType}">
      <span class="c-gray en">&gt;</span> <span>安全生产管理</span> 
      <span class="c-gray en">&gt;</span> <span>事故报告和处理</span> 
      <span class="c-gray en">&gt;</span> <span>工伤事故档案</span> 
    </c:if>
    <c:if test="${25==isType}">
      <span class="c-gray en">&gt;</span> <span>安全生产管理</span> 
      <span class="c-gray en">&gt;</span> <span>事故报告和处理</span> 
      <span class="c-gray en">&gt;</span> <span>工伤事故调查及处理报告</span> 
    </c:if>
    <c:if test="${26==isType}">
      <span class="c-gray en">&gt;</span> <span>安全生产管理</span> 
      <span class="c-gray en">&gt;</span> <span>事故报告和处理</span> 
      <span class="c-gray en">&gt;</span> <span>工伤事故台帐</span> 
    </c:if>
    <c:if test="${27==isType}">
      <span class="c-gray en">&gt;</span> <span>安全生产管理</span> 
      <span class="c-gray en">&gt;</span> <span>应急救援</span> 
      <span class="c-gray en">&gt;</span> <span>消防器材“三定”管理台帐</span> 
    </c:if>
    <c:if test="${28==isType}">
      <span class="c-gray en">&gt;</span> <span>安全生产管理</span> 
      <span class="c-gray en">&gt;</span> <span>应急救援</span> 
      <span class="c-gray en">&gt;</span> <span>应急器材物资检查记录表</span> 
    </c:if>
    <c:if test="${29==isType}">
      <span class="c-gray en">&gt;</span> <span>职业健康管理</span> 
      <span class="c-gray en">&gt;</span> <span>职业健康管理制度</span> 
    </c:if>
   
   
   
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:history.back(-1)" title="返回">返回</a>
  </nav>
  <div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <div class="l">
        <%--<c:if test="${1!=isType && 0!=isType}">
        <a class="btn btn-primary radius" href="${ly}/company/tables/templetDownload?isType=${isType}"><i class="Hui-iconfont" style="font-size:15px;">&#xe640;</i> 查看模板</a>
        </c:if>--%>
        <button class="btn btn-warning radius ml-10" type="button" onclick="openzl(-1)"><i class="Hui-iconfont" style="font-size:15px;">&#xe642;</i> 上传资料</button>
      </div>
      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span> 
    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="55%">资料名称</th>
            <th width="20%">上传时间</th>
            <th width="20%">操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环-->
          <c:forEach items="${list }" varStatus="index" var="tab">
          <tr class="text-c">
            <td>${index.index+1} </td>
            <td>${tab.name }</td>
            <td><fmt:formatDate value="${tab.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td>
              <div class="btn-upload">
                <div class="btn btn-primary-outline radius" onclick="openzl(${tab.id})"  style="height: 21px;line-height: 21px;padding: 0 2px;margin-top: 4px;">更改资料</div>
<!--                 <input type="file" multiple name="file_0" class="input-file"> -->
              </div>
              <c:if test="${!empty tab.url}">
              <a style="text-decoration:none" href="${ly}/common/download?filename=${tab.name}&fileurl=${tab.url}" title="查看资料" target="_blank">下载资料</a>
              </c:if>
              <a style="text-decoration:none" onclick="del(${tab.id})" href="javascript:;" title="删除">删除</a>
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
                <button class="btn btn-primary" onclick="upload()" >确定</button>
                <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>
  <script type="text/javascript">
  var isType=${isType}
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
  $.post(getRootPath()+"/company/tables/tab-del",{
    id:id,
  },function(reuslt){
    location.reload()
  })
}
/*上传*/
function upload() {
  $.ajaxFileUpload({
      url: getRootPath() + '/company/tables/tab-leadin?isType='+isType +'&id='+id , //用于文件上传的服务器端请求地址
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