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
  <!-- <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> <span class="c-gray en">&gt;</span> <span>安全生产标准化</span> <span class="c-gray en">&gt;</span> <span>管理档案</span>
    <span class="c-gray en">&gt;</span> <span>教育培训</span> <span class="c-gray en">&gt;</span> <span>持证上岗</span> <a class="btn btn-success radius r"
      style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新"> <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav> -->
  <div class="page-container">
    <div id="spTab" class="btn-group" style="text-align: center; margin-bottom: 20px;">
      <a class="btn default radius <c:if test="${empty spFlag || spFlag == 0}"> btn-primary</c:if>" href="${ly }/town/cx/warning/sperson-list?spFlag=0&isTime=${isTime }&villageId=${villageId }&townId=${townId }&districtId=${districtId }">全部</a> 
      <a class="btn default radius <c:if test="${spFlag == 1}"> btn-primary</c:if>" href="${ly }/town/cx/warning/sperson-list?spFlag=1&isTime=${isTime }&villageId=${villageId }&townId=${townId }&districtId=${districtId }">安全管理人员</a> 
      <a class="btn default radius <c:if test="${spFlag == 2}"> btn-primary</c:if>" href="${ly }/town/cx/warning/sperson-list?spFlag=2&isTime=${isTime }&villageId=${villageId }&townId=${townId }&districtId=${districtId }">危险化学品安全管理人员</a> 
      <a class="btn default radius <c:if test="${spFlag == 3}"> btn-primary</c:if>" href="${ly }/town/cx/warning/sperson-list?spFlag=3&isTime=${isTime }&villageId=${villageId }&townId=${townId }&districtId=${districtId }">特种作业人员</a>
    </div>    
    
    <!-- 判断其他持证上岗显示 -->
    <div class="list_other">
    <div class="text-c">
      <form action="${ly }/town/cx/warning/sperson-list?spFlag=${spFlag}&villageId=${villageId }&townId=${townId }&districtId=${districtId }" method="post">
        <div class="dis-ib">
          <span>企业名称：</span> <input type="text" value="${companyName }" name="companyName" class="input-text mb-5 mt-5" style="width: 150px;">
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
        <%-- <span class="l"><a id="specialPrint" onclick="show_dialog('特种作业人员-打印', '${ly }/town/cx/special-print?spFlag=${spFlag}&spType=${spType }&isTime=${isTime }&spName=${spName }&villageId=${villageId }')" class="btn btn-primary radius" href="javascript:void(0);"><i class="Hui-iconfont mr-10">&#xe652;</i>打印 </a></span> --%>
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
    
    </script>
</body>

</html>