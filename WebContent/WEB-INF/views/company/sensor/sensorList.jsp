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
<title>安全分级管控   隐患排查治理智能化信息平台</title>
<link href="${ly }/js/plugins/laypage/skin/laypage.css" rel="stylesheet"/>
<meta name="keywords" content="安全分级管控   隐患排查治理智能化信息平台">
<meta name="description" content="安全分级管控   隐患排查治理智能化信息平台">
<style type="text/css">
body .dis-ib{margin-right:15px;}
.laypage_main a, .laypage_main span {
    margin-left: 5px;
    background-color: #fff;
    width: auto;
    line-height: 33px;
    padding: 4px 2px;
    border: 1px solid #d2d2d2;
    border-radius: 2px;
    text-align: center;
    min-width: 33px;
    line-height:20px !important;
    height:20px !important;
}
</style>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>设备列表</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="text-c">
      <!-- <form action="" method="post"> -->
      <div class="dis-ib">
          <span>请选择设备：</span>
          <select id="deviceSelect" class="input-text mb-5 mt-5" style="width:150px;">
          	<option value="">全部</option>
          	<c:forEach items="${device }" var="cp">
          		<option value="${cp.deviceSn }">${cp.deviceName }</option>
          	</c:forEach>
          </select>
        </div>
        <div class="dis-ib">
          <span>传感器名称：</span>
          <input type="text" id="searchSensorInput" value="" class="input-text mb-5 mt-5" style="width:150px;">
        </div>
        <button class="btn btn-success" type="button" id="toSearchSensorName">
          <i class="Hui-iconfont">&#xe665;</i> 查询
        </button>
      <!-- </form> -->
    </div>
  <div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="r">共有数据：<strong id="showTotal">0</strong> 条</span> 
    </div>
    <div class="mt-20" id="showList">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="25%">传感器名称</th>
            <th width="5%">传感器通道号</th>
            <th width="20%">所属设备</th>
            <th width="15%">放置场所</th>
            <th width="10%">传感器类型</th>
            <th width="15%">创建时间</th>
            <th width="10%">操作</th>
          </tr>
        </thead>
        <tbody id="showListBody">
          <!-- 循环-->

          <!-- 循环结束 -->
        </tbody>
      </table>
      <div id="demo11" style="text-align:center;padding-top: 8px;"></div>
    </div>
  </div>
<input type="hidden" id="listPageFlag" value="1"/>
<script type="text/javascript" src="${ly }/js/plugins/juicer/juicer.js"></script>
<script type="text/javascript" src="${ly }/js/plugins/laypage/laypage.js"></script>
<script type="text/javascript" src="${ly }/js/modules/common/common.js"></script>
<script type="text/javascript" src="${ly }/js/modules/sensor/addSensor.js"></script>
<script type="type/juicer" id="reviceListTemplate">
{@each list as m,index}
    <tr class="text-c">
          <td>$<{m.sensorName}></td>
          <td>$<{m.sensorSn}></td>
          <td>$<{m.deviceName}></td>
          <td>$<{m.site}></td>
          <td>$<{m.typeName}></td>
          <td>{@if m.createTime != null}
				$<{m.createTime}>
			  {@else}
				--
			  {@/if}
		  </td>
          <td><a href="<c:url value='/village/toAddSensor?sensorId=$<{m.id}>'/>">编辑</a>
			 <a href="javascript:void(0)" core-id="$<{m.id}>" class="toDel">删除</a>
			</td>
     </tr>
{@/each}
</script> 
</body>
</html>