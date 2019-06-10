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
<%@ include file="/WEB-INF/inc/kindeditor.inc"%>
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<script type="text/javascript">
var list1 = '普通防护服,普通工作帽,普通工作鞋,劳动防护手套,防寒服,雨衣,胶鞋,防噪声耳塞（耳罩）'.split(",");
</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> <span class="c-gray en">&gt;</span> <span>职业健康管理</span> <span class="c-gray en">&gt;</span> <span>劳动防护用品配备</span>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="text-c">
      <form action="${ly }/company/labor/labor-list" method="post">
        <div class="dis-ib">
          <span>名称：</span>
          <input type="text" value="${name }" name="name" class="input-text mb-5 mt-5" style="width:150px;">
        </div>
        <div class="dis-ib">
          <span>类型：</span>
          <span class="select-box inline mr-20">
          <select name="libId" class="select" style="width: 150px;">
            <option value="">全部</option>
            <option value="1"<c:if test="${libId == 1}"> selected</c:if>>一般劳动防护用品</option>
            <option value="2"<c:if test="${libId == 2}"> selected</c:if>>特种劳动防护用品</option>
            <option value="3"<c:if test="${libId == 3}"> selected</c:if>>其他</option>
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
        <a class="btn btn-primary radius" data-title="添加劳动防护用品" data-href="${ly }/company/pequipment-add" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 添加劳动防护用品</a>
      </span>
      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span> 
    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">编号</th>
            <th>劳动用品类型</th>
            <th>劳动用品名称</th>
            <th>工种</th>
            <th>发放日期</th>
            <th>下次发放日期</th>
            <th>数量</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <c:set var="x" value="${fn:split('一般劳动防护用品/特种劳动防护用品/其他', '/') }"/>
          <!-- 循环-->
          <c:forEach items="${list }" var="be" varStatus="index">
          <tr>
            <td>${index.index + 1}</td>
            <td>${x[be.libId - 1] }</td>
            <td>${be.name }</td>
            <td>${be.work }</td>
            <td>${be.cycle }</td>
            <td>${be.nextDate }</td>
            <td>${be.number }</td>
            <td>
              <a style="text-decoration:none" onClick="show_tab('修改劳动用品', '${ly }/company/pequipment-add?id=${be.id }')" href="javascript:;">修改劳动用品</a>
              <a style="text-decoration:none" onClick="del_(${be.id})" href="javascript:;">删除</a>
            </td>
          </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
  </div>
</body>
<script type="text/javascript">
$(function() {
  $('.table-sort').dataTable({
    "bStateSave": false,//状态保存
    "aoColumnDefs": [
    ]
    });
});

function del_(id) {
  layer.confirm("确认删除该记录么？",function(i) {
    layer.close(i);
    $.post(getRootPath() + "/company/pequipment-del",{
      id : id
    },function(result) {
      if(result.status == '1') {
        layer.alert(result.map.message);
      } else {
        $("form").submit();
      }
    })
  })
}
</script>
</html>