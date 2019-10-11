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
</style>
<script type="text/javascript">
function showpicture(memoImg){
  //memoImg = "";
  if (memoImg.length !== 0) { 
    $("#memoImg").attr("src",getRootPath()+memoImg);
  }
  $("#modal-plan").modal("show");
}
    </script>
</head>

<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>评价评估系统</span> 
    <span class="c-gray en">&gt;</span> <span>各类许可证档案</span>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
    <a onclick="back()" class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:void(0)" title="返回">返回</a>
  </nav>
  <div class="page-container">
    <div class="text-c">
      <form action="${ly }/company/evaluate/zheng-list" method="post">
        <div class="dis-ib">
          <span>证件类型：</span> <span class="select-box inline"> <select name="type" class="select">
              <option value="">全部</option>
              <option value="1" <c:if test="${type == '1'}"> selected="selected"</c:if>>危化品经营许可证</option>
              <option value="2" <c:if test="${type == '2'}"> selected="selected"</c:if>>危化品生产许可证</option>
              <option value="3" <c:if test="${type == '3'}"> selected="selected"</c:if>>危化品使用许可证</option>
              <option value="4" <c:if test="${type == '4'}"> selected="selected"</c:if>>危化品登记证</option>
              <option value="5" <c:if test="${type == '5'}"> selected="selected"</c:if>>易制毒化学品备案</option>
              <option value="6" <c:if test="${type == '6'}"> selected="selected"</c:if>>易制爆化学品备案</option>
              <option value="7" <c:if test="${type == '7'}"> selected="selected"</c:if>>标准化二级证书</option>
              <option value="8" <c:if test="${type == '8'}"> selected="selected"</c:if>>标准化三级证书</option>
              <option value="9" <c:if test="${type == '9'}"> selected="selected"</c:if>>港口经营许可证</option>
              <option value="10" <c:if test="${type == '10'}"> selected="selected"</c:if>>成品油零售经营批准证书</option>
              <option value="11" <c:if test="${type == '11'}"> selected="selected"</c:if>>小微企业标准化证书</option>
          </select>
          </span>
        </div>
        <div class="dis-ib">
                <span>到期时间：</span>
                <span class="select-box inline">
                <select name="isTime" class="select">
                  <option value="">全部</option>
                  <option value="6" <c:if test="${6==isTime}">selected</c:if>>6个月内到期</option>
                  <option value="0" <c:if test="${0==isTime}">selected</c:if>>已过期</option>
                </select>
                </span>
              </div>
        <button class="btn btn-success" type="submit">
          <i class="Hui-iconfont">&#xe665;</i> 查询
        </button>
      </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l"> <a class="btn btn-primary radius" data-title="添加各类许可证" data-href="${ly }/company/evaluate/zheng-add"
        onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont" style="font-size: 15px;">&#xe642;</i> 添加各类许可证</a>
      </span>
      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span>
    </div>
    <c:set var="type_" value="${fn:split('危化品经营许可证/危化品生产许可证/危化品使用许可证/危化品登记证/易制毒化学品备案/易制爆化学品备案/标准化二级证书/标准化三级证书/港口经营许可证/成品油零售经营批准证书', '/') }"/>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="15%">证书类型</th>
            <th width="15%">证书编号</th>
            <th width="20%">证书图片</th>
            <th width="15%">取证时间</th>
            <th width="15%">到期时间</th>
            <th width="15%">操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环-->
          <c:forEach items="${list }" varStatus="index" var="list">
            <tr class="text-c">
              <td>${index.index+1 }</td>
              <td>${type_[list.type - 1]}</td>
              <td>${list.certificateNumber}</td>
              <td><img alt="${list.img}" src="${list.img}" style="max-height: 100px;cursor:pointer;" onclick="showpicture('${list.img}')"/></td>
              <td>${list.timeGet}</td>
              <td>${list.timeExp}</td>
              <td><a style="text-decoration: none" onclick="edt(${list.id})" href="javascript:;" title="编辑证书">编辑证书</a> <a
                style="text-decoration: none" onclick="del(${list.id})" href="javascript:;" title="删除">删除</a></td>
            </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
  </div>
  
  <!-- 弹窗图片 -->
  <div id="modal-plan" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
          <div class="modal-content radius">
              <div class="modal-header">
                  <h3 class="modal-title">证书图片</h3>
                  <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
              </div>
              <div class="modal-body" style="height:400px;overflow-y:auto">
                <div class="div_imgp" style="text-align:center;">
                  <img src="${ly }/images/zwtp.jpg" id="memoImg" class="img-responsive2 mt-20" >
                </div>
              </div>
          </div>
      </div>
  </div>
  
  <script type="text/javascript">
    $(function() {
        $('.table-sort').dataTable({
            "aaSorting": [
                [0, "asc"]
            ], //默认第几个排序
            "bStateSave": false, //状态保存
            searching: false,
            ordering: false,
            "aoColumnDefs": []
        });
    });
    
    /*编辑*/
    function edt(id) {
      show_tab("编辑各类许可证", getRootPath() + '/company/evaluate/zheng-add?id='+id);
	}

    /*删除*/
	function del(id) {
	  	layer.confirm("确认要删除此条记录吗？", function() {
		    $.post(getRootPath() + "/company/evaluate/zheng-del", {
		      	id: id
		    }, function(result) {
		        $('form').submit();
		    })
	  	});
	}
    </script>
</body>

</html>