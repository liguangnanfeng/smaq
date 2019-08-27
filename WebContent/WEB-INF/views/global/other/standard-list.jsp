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
    <span class="c-gray en">&gt;</span> <span>安全生产化标准</span>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <input type="hidden" id="currentpage" value="0"><!--放置是当前的页码, 默认为0-->
  <input type="hidden" id="totalpage" value="0"><!-----存放的是总页码------>
  <div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l">
        <a class="btn btn-primary radius" href="javascript:show_dialog('添加主要生产产品','${ly }/steel/product/product-add')"><i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 添加主要生产产品</a>
      </span>
      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span> 
    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="20%">企业名称</th>
            <th width="10%">行业</th>
            <th width="10%">标准化等级</th>
            <th width="10%">最近操作时间</th>
            <th width="15%">查看详情</th>
          </tr>
        </thead>
        <tbody>

          <!-- 循环-->
          <c:forEach items="${list }" varStatus="index" var="pro">
          <tr class="text-c">
            <td>${index.index+1 }</td>
            <td>${pro.name }</td>
            <td>${pro.industry }</td>
            <td>${pro.dlevel }</td>
            <td>0</td>
            <td>
              <a style="text-decoration:none"  href="${ly }/global/findAll?parendId=0&flag=1&userId=${pro.userId}" title="查看详情">查看详情</a>
            </td>
          </tr>
          </c:forEach>
          <!-- 循环结束 -->
          <input type="button" onclick="sub()" value="上一页">&nbsp;&nbsp;
          <input type="button" onclick="add()" value="下一页">
        </tbody>
      </table>
    </div>
  </div>
<script type="text/javascript">

$(function() {
  $('.table-sort').dataTable({
    "aaSorting": [[0, "asc"]],//默认第几个排序
    "bStateSave": false,//状态保存
    searching: false,
    ordering: false,
    "aoColumnDefs": []
  });
  //页面初始化的工作就是获取总页数,存放在id为totalpage中input当中
  //请求page为0的数据
});

  function sub(){ //上一页

  }
  function add(){ //下一页

  }
/*编辑*/
function admin_edit(id){
  show_tab("编辑主要生产产品", getRootPath() + "/steel/product/product-edit?id=" + id);
}

/*删除*/
function del(id){
  $.post(getRootPath()+"/steel/product/deleteProduct",{
    id:id,
  },function(reuslt){
    location.reload()
  })
}
</script> 
</body>
</html>