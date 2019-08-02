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
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> <span class="c-gray en">&gt;</span> <span>安全生产标准化</span> <span class="c-gray en">&gt;</span> <span>管理档案</span>
    <span class="c-gray en">&gt;</span> <span>教育培训</span> <span class="c-gray en">&gt;</span> <span>持证上岗</span> <a class="btn btn-success radius r"
      style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新"> <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div id="spTab" class="btn-group" style="text-align: center; margin-bottom: 20px;">
      <a class="btn default radius" href="${ly }/company/train/special-list?spFlag=0&isTime=${isTime}">全部</a> 
      <a class="btn default radius" href="${ly }/company/train/special-list?spFlag=1&isTime=${isTime}">安全管理人员</a> 
      <a class="btn default radius" href="${ly }/company/train/special-list?spFlag=2&isTime=${isTime}">危险化学品安全管理人员</a> 
      <a class="btn default radius" href="${ly }/company/train/special-list?spFlag=3&isTime=${isTime}">特种作业人员</a>
    </div>
    <!-- 判断全部显示 -->
    <%-- <div class="list_gang">
      <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="15%">姓名</th>
            <th width="10%">所属岗位</th>
            <th width="10%">工种</th>
            <th width="15%">证书编号</th>
            <th width="10%">取证时间</th>
            <th width="10%">审核时间（最近）</th>
            <th width="10%">复审时间（下次）</th>
            <th width="15%">备注</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环-->
          <c:forEach items="${list }" varStatus="index" var="spe">
            <tr class="text-c">
              <td>${index.index+1 }</td>
              <td>${spe.name }</td>
              <td>xxx</td>
              <td>${spe.type }</td>
              <td>${spe.certificateNumber }</td>
              <td>${spe.forensicTime }</td>
              <td>${spe.auditTime }</td>
              <td>${spe.reviewTime }</td>
              <td>${spe.remark }</td>
            </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
    </div> --%>   
    
    <!-- 判断其他持证上岗显示 -->
    <div class="list_other">
    <div class="text-c">
      <form action="${ly }/company/train/special-list?spFlag=${spFlag}" method="post">
        <div class="dis-ib">
          <span>姓名：</span> <input type="text" value="${spName }" name="spName" class="input-text mb-5 mt-5" style="width: 150px;">
        </div>
        <c:if test="${spFlag != 0 }">
        <div class="dis-ib">
          <span>工种：</span> 
          <span class="select-box inline"> 
            <select id="type" name="spType" class="select" style="width: 235px;">
              <option value="">请选择</option>
              <c:forEach items="${lib }" varStatus="index" var="sp">
                <option value="${sp.name }" <c:if test="${spType == sp.name}"> selected</c:if>>${sp.name }</option>
              </c:forEach>
            </select>
          </span>
        </div>
        </c:if>
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
      <a id="specialAdd" class="btn btn-primary radius" onclick="Hui_admin_tab(this)" href="javascript:void(0);"> </a>
      <a id="specialPrint" onclick="show_dialog('特种作业人员-打印', '/company/special-print?spFlag=${spFlag}&spType=${spType }&isTime=${isTime }&spName=${spName }')" class="btn btn-primary radius" href="javascript:void(0);"><i class="Hui-iconfont mr-10">&#xe652;</i>打印 </a>
      </span> <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条
      </span>
    </div>
    <c:set var="flagV" value="${fn:split('安全管理人员/危险化学品安全管理人员/特种作业人员','/') }" />
    <c:set var="wid1" value="${fn:split('13/15/15/15','/') }" />
    <c:set var="wid2" value="${fn:split('6/10/10/10','/') }" />
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="${wid1[spFlag]}%">姓名</th>
            <c:if test="${spFlag == 0 }">
            <th width="10%">所属岗位</th>
            </c:if>
            <th width="10%">工种</th>
            <th width="${wid1[spFlag]}%">证书编号</th>
            <th width="${wid1[spFlag]}%">取证时间</th>
            <th width="10%">下次复审时间</th>
            <th width="${wid1[spFlag]}%">备注</th>
            <th width="10%">操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环-->
          <c:forEach items="${list }" varStatus="index" var="spe">
            <tr class="text-c">
              <td>${index.index+1 }</td>
              <td>${spe.name }</td>
              <c:if test="${spFlag == 0 }">
              <td>${flagV[spe.flag - 1] }</td>
              </c:if>
              <td>${spe.type }</td>
              <td>${spe.certificateNumber }</td>
              <td>${spe.forensicTime }</td>
              <td>${spe.reviewTime }</td>
              <td>${spe.remark }</td>
              <td><a style="text-decoration: none" onClick="admin_edit(${spe.id},${spe.flag })" href="javascript:;" title="编辑">查看/编辑</a> <a
                style="text-decoration: none" onClick="del(${spe.id})" href="javascript:;" title="删除">删除</a></td>
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
    /*编辑*/
    function admin_edit(id,type) {
        var title = ['编辑安全管理人员', '编辑危险化学品安全管理人员', '编辑特种作业人员'];
  		var i = '${spFlag}';
        show_tab(title[parseInt(type - 1)], getRootPath() + "/company/train/special-edit?id=" + id+"&spFlag="+i);
        //show_tab("编辑持证上岗", getRootPath() + "/company/train/special-edit?id=" + id+"&spFlag="+i);
    }

    /*删除*/
    function del(id){
      layer.confirm("确认要删除吗？",function(r){
        $.post(getRootPath() + "/company/train/deleteSpecial", {
          id : id,
        },function(result) {
          $("form").submit();
        });
      });
    }
    
    function getUrlParam(name) {
      var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
      var r = window.location.search.substr(1).match(reg); //匹配目标参数
      if (r != null) return unescape(r[2]);
      return null; //返回参数值
  	}
	
	$(function() {
		var title = ['添加安全管理人员', '添加危险化学品安全管理人员', '添加特种作业人员'];
		var i = getUrlParam('spFlag');
		if (i == undefined || i == null) {
		    i = '0';
		}
		var j = 1;
		if (i != 0) {
		  j = i;
		}
		$("#specialAdd").attr("data-title", title[parseInt(j - 1)]);
		$("#specialAdd").attr("data-href", "${ly }/company/train/special-add?spFlag=" + parseInt(i));
		$("#specialAdd").html('<i class="Hui-iconfont" style="font-size: 15px;">&#xe600;</i> ' + title[parseInt(j - 1)]);
		if(i == 0) {
		  $("#specialAdd").hide();
		} else {
		  $("#specialAdd").show();
		}
		$("#spTab").find('a').eq(i).addClass("btn-primary").removeClass("default");
	})
    </script>
</body>

</html>