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
.clearfix:after{content:"\20";display:block;height:0;clear:both;visibility:hidden}
.clearfix{zoom:1}
.tabBar {border-bottom:0px;text-align:center;background:#fff;height:42px;line-height:42px;margin-top:28px;}
.tabBar span {height:42px;line-height:42px;background-color:#E5F3F7;border:0px;font-size:14px;color:#687E99;cursor: pointer;display: inline-block;float: none;font-weight: bold;padding: 0 25px}
.tabBar span.current{background-color:#008DFF;color: #fff;border:0px;}
.tabCon {display: none;padding:0;border:0px;}
</style>
<script type="text/javascript">
$(function(){
  $.Huitab("#tab_demo .tabBar span","#tab_demo .tabCon","current","click","0")});
</script>
</head>
<body>
    <div id="tab_demo" class="HuiTab">
      <div class="tabBar clearfix">
        <span style="border-radius:5px 0 0 5px;">安全管理人员</span><span onclick="show()"  style="border-radius:0 5px 5px 0;">特种作业人员</span>
        <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top:4px;margin-right:20px;" href="javascript:location.replace(location.href);" title="刷新">
            <i class="Hui-iconfont">&#xe68f;</i>
          </a>
      </div>
      <div class="tabCon">
        <!-- <nav class="breadcrumb">
          <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
          <span>安全生产标准化</span><span class="c-gray en">&gt;</span>
          <span>安全生产教育培训</span>
          <span class="c-gray en">&gt;</span> <span>安全管理人员</span> 
          
        </nav> -->
        <div class="page-container">
          <div class="text-c">
            <form action="${ly }/company/train/safety-list" method="post">
              <div class="dis-ib">
                <span>姓名：</span>
                <input type="text" value="${saName }" id="saName" name="saName" class="input-text mb-5 mt-5" style="width:150px;">
              </div>
             
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
              <button class="btn btn-success" type="submit">
                <i class="Hui-iconfont">&#xe665;</i> 查询
              </button>
            </form>
          </div>
          <div class="cl pd-5 bg-1 bk-gray mt-20">
            <span class="l">
              <a class="btn btn-primary radius" data-title="添加安全管理人员" data-href="${ly }/company/train/safety-add" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 添加安全管理人员</a>
            </span>
            <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span> 
          </div>
          <div class="mt-20">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
              <thead>
                <tr class="text-c">
                  <th width="5%">序号</th>
                  <th width="15%">姓名</th>
                  <th width="10%">类别</th>
                  <th width="15%">证书编号</th>
                  <th width="15%">取证时间</th>
                  <th width="15%">下次复审时间</th>
                  <th width="15%">备注</th>
                  <th width="10%">操作</th>
                </tr>
              </thead>
              <tbody>
                <!-- 循环-->
                <c:forEach items="${list }" varStatus="index" var="saf">
                <tr class="text-c">
                  <td>${index.index+1 }</td>
                  <td>${saf.name }</td>
                  <td>${saf.type }</td>
                  <td>${saf.certificateNumber }</td>
                  <td>${saf.forensicTime }</td>
                  <td>${saf.reviewTime }</td>
                  <td>${saf.remark }</td>
                  <td>
                    <a style="text-decoration:none" onClick="admin_edit(${saf.id})" href="javascript:;" title="编辑">查看/编辑</a>
                    <a style="text-decoration:none" onClick="del(${saf.id})" href="javascript:;" title="删除">删除</a>
                  </td>
                </tr>
                </c:forEach>
                <!-- 循环结束 -->
              </tbody>
      </table>
    </div>
  </div>
      </div>
      <div id="tab2" class="tabCon">
      	<jsp:include page="special-list.jsp" flush="true"/>
<%--         <%@ include file="special-list.jsp"%> --%>
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
  $('.table-sort2').dataTable({
    "aaSorting": [[0, "asc"]],//默认第几个排序
    "bStateSave": false,//状态保存
    searching: false,
    ordering:  false,
    "aoColumnDefs": [
    ]
    });
});
/*编辑*/
function admin_edit(id){
  show_tab("编辑安全管理人员", getRootPath() + "/company/train/safety-edit?id=" + id);
}

/*删除*/
function del(id){
  $.post(getRootPath()+"/company/train/deleteSafety",{
    id:id,
  },function(reuslt){
    location.reload()
  })
}

function show() {
  $.post(getRootPath()+"/company/train/special-list"
      ,{pageNo:0},function(result){
    $("#tab2").html(result)	
  })
}

</script> 
</body>
</html>