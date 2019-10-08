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
  <div class="page-container">
    <div class="text-c">
      <form action="" method="post">
        <div class="dis-ib">
            <span>企业规模123：</span>
            <input type="text" value="${dto.page }" name="page" id="page" style="display: none;">
            <input type="text" value="${dto.rows }" name="rows" id="rows" style="display: none;">
            <input type="text" value="${dto.doubleDanger }" name="doubleDanger"  style="display: none;">
            <input type="text" value="${dto.hazard }" name="hazard" style="display: none;">
            <input type="text" value="${dto.danger }" name="danger" style="display: none;">
            <span class="select-box inline">
              <select class="select" id="scale" name="scale">
                <option value="">全部</option>
                <option value="规上">规上</option>
                <option value="中小">中小</option>
                <option value="小微">小微</option>
              </select>
              <script type="text/javascript">
              var scale = '${dto.scale}';
              $("#scale").val(scale);
              </script>
            </span>
        </div>
        <button class="btn btn-success" type="submit" onclick="$('#page').val(0)">
          <i class="Hui-iconfont">&#xe665;</i> 查询
        </button>
      </form>
    </div>
    
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="r">共有数据：<strong>${total }</strong> 条</span> 
    </div>
    
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="20%">企业名称</th>
            <th width="15%">所属行业</th>
            <th width="10%">风险等级</th>
            <th width="20%">所在地</th>
            <th width="15%">规模类型</th>
            <th width="10%">运行状态</th>
            <th width="10%">操作</th>
          </tr>
        </thead>
        <tbody>
        
          <!-- 循环村 -->
         <c:forEach items="${list }" varStatus="index" var="co">
          <tr class="text-c">
            <td>${dto.page * dto.rows + index.index + 1}</td>
            <td>${co.name }</td>
            <td>${co.industry2 }</td>
            <td>${co.dlevel }</td>
            <td>${co.regionName} ${co.address }</td>
            <td>${co.scale}</td>
            <c:if test="${0 == co.isFreeze }">
            <td>正常</td>
            </c:if>
            <c:if test="${1 == co.isFreeze }">
            <td>冻结</td>
            </c:if>
            <td>
                <a class="label label-primary radius" style="text-decoration:none" onClick='show_dialog("企业详细信息", "${ly }/village/company/company-show?userId=${co.userId }")' href="javascript:;" title="查看企业详细资料">查看详情</a>
            </td>
          </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
      <div id="layer_page_div" style="margin-top: 15px;"></div>
    </div>
  </div>
<script type="text/javascript">
 $(function() {
   /*$('.table-sort').dataTable({
    "aaSorting": [[0, "asc"]],//默认第几个排序
    "bStateSave": false,//状态保存
    searching: true,
    ordering:  true,
    "aoColumnDefs": [
    ]
    }); */
});
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
</script>
</html>