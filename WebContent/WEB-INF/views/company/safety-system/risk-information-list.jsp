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
<link rel="stylesheet" type="text/css" href="${ly}/styles/print-tab.css"/>
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style type="text/css">
body .dis-ib{margin-right:15px;}
.div-print{width:60%;margin:auto;padding:30px 0 20px 0;}
</style>
<script type="text/javascript">

</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>风险分级管控</span> 
    <span class="c-gray en">&gt;</span> <span>公告警示</span>
    <span class="c-gray en">&gt;</span> <span><c:if test="${flag == 1}">公司</c:if><c:if test="${flag == 2}">车间</c:if><c:if test="${flag == 3}">岗位</c:if>风险公告牌</span>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:history.back(-1)" title="返回">返回</a>
    <%--<a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="parent.close_tab(function(){})">返回</a>--%>
  </nav>
  <div class="page-container">
    <div class="text-l">
      <div class="dis-ib">
        <span>风险名称：</span>
        <select name="" class="sel_area">
          <option value="">全部</option>
        </select>
      </div> 
      <button class="btn btn-success" type="submit">
        <i class="Hui-iconfont">&#xe665;</i> 查询
      </button>
    </div>
    <%-- <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l">
        <a class="btn btn-primary radius" href="javascript:show_dialog('添加风险公告牌','/company/safety-system/risk-information-add?flag=${flag }')"><i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 添加风险公告牌</a>
      </span>
      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span> 
    </div> --%>
    <div class="mt-20">
      <%-- <table class="table table-border table-bordered table-bg table-hover table-sort tab-ndan">
        <thead>
          <tr class="text-c">
            <th style="width:40px">序号</th>
            <th style="min-width:80px">风险名称</th>
            <th style="min-width:80px">风险等级</th>
            <th style="min-width:80px">管理责任人</th>
            <th style="min-width:100px">事故类型</th>
            <th style="min-width:200px">风险因素</th>
            <th style="min-width:200px">应急防范措施</th>
            <th style="min-width:150px">操作</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${list }" var="be" varStatus="index">
          <tr>
            <td class="text-c">${index.index + 1}</td>
            <td><p>${be.name }</p></td>
            <td><p>${be.fxdj }</p></td>
            <td><p>${be.glzrr }</p></td>
            <td><p>${be.sglx }</p></td>
            <td><p>${be.fxys }</p></td>
            <td><p>${be.yjczcs }</p></td>
            <td class="text-c">
              <a style="text-decoration:none" onClick="show_dialog('编辑风险公告牌', '/company/safety-system/risk-information-add?id=${be.id}&flag=${flag }')" href="javascript:;" title="编辑">编辑</a>
              <a style="text-decoration:none" onClick="show_dialog('公告牌详情', '/company/safety-system/risk-information-detail?id=${be.id}')" href="javascript:;" title="查看公告牌">查看公告牌</a>
              <a style="text-decoration:none" onClick="del(${be.id})" href="javascript:;" title="删除">删除</a>
            </td>
          </tr>
          </c:forEach>
        </tbody>
      </table> --%>
      <div class="div-set">
        <div class="top-set">
          <font>安全风险公告牌</font>
          <a class="btn_rbj" style="right:146px;" onClick="show_dialog('编辑风险公告牌', '/company/safety-system/risk-information-add?id=${be.id}&flag=${flag }')" href="javascript:;" title="编辑">编辑</a>
          <a class="btn_rbj" style="right:56px;"  onClick="show_dialog('打印公告牌', '/company/safety-system/risk-information-detail?id=${be.id}')" href="javascript:;" title="打印公告牌">打印公告牌</a>
          <a class="btn_rbj" onClick="del(${be.id})" href="javascript:;" title="删除">删除</a>
        </div>
        <div class="div-print">
          <h3 class="text-c" style="padding-top:0">${be.name }安全风险公告牌</h3>
          <div class="main-gao">
            <table class="tab-gao" >
              <tr>
                <td class="text-c" rowspan="2" style="width:30%">
                  <div class="div-gaoz">
                    <b>风险点名称：</b>
                    <p><b>${be.name }</b></p>
                  </div>
                </td>
                <td class="text-c tcol-red" style="width:30%">事故类型</td>
                <td class="text-c tcol-red" style="width:40%">风险因素</td>
              </tr>
              <tr>
                <td rowspan="3" class="td-gao">
                  <p>${be.sglx }</p>
                </td>
                <td  rowspan="3" class="td-gao">
                <p>${be.fxys }</p>
                </td>
              </tr>
              <tr>
                <td class="text-c">
                  <div class="div-gaoz">
                    <b>风险等级：</b>
                    <div class="div-xz">
                      <div class="check-box">
                        <label>红</label>
                        <img alt="" src="/images/fore/${fn:contains(be.fxdj, '红') ? 'gx1.png' : 'wgx.png'}"/>
                      </div>
                      <div class="check-box">
                        <label>橙</label>
                        <img alt="" src="/images/fore/${fn:contains(be.fxdj, '橙') ? 'gx2.png' : 'wgx.png'}"/>
                      </div>
                    </div>
                    <div class="div-xz">
                      <div class="check-box">
                        <label>黄</label>
                        <img alt="" src="/images/fore/${fn:contains(be.fxdj, '黄') ? 'gx3.png' : 'wgx.png'}"/>
                      </div>
                      <div class="check-box">
                        <label>蓝</label>
                        <img alt="" src="/images/fore/${fn:contains(be.fxdj, '蓝') ? 'gx4.png' : 'wgx.png'}"/>
                      </div>
                    </div>
                  </div>
                </td>
              </tr>
              <tr>
                <td class="text-c">
                  <div class="div-gaoz" style="margin-bottom:0;">
                    <b>管理责任人：</b>
                    <p>${be.glzrr }</p>
                  </div>
                </td>
              </tr>
              <tr><td colspan="3" style="height:5px"></td></tr>
              <tr>
                <td colspan="3" class="text-c tcol-blue">应急防范措施</td>
              </tr>
              <tr>
                <td colspan="3" class="td-gao">
                  <!-- 循环 -->
                  <p>${be.yjczcs }</p>
                </td>
              </tr>
              <tr>
                <td colspan="3">
                  <div class="div-gimg" style="margin-bottom:6px;">
                    <img alt="" src="${ly }/images/gao/aqgz1.jpg"/>
                    <img alt="" src="${ly }/images/gao/aqgz2.jpg"/>
                    <img alt="" src="${ly }/images/gao/aqgz3.jpg"/>
                    <img alt="" src="${ly }/images/gao/aqgz4.jpg"/>
                    <img alt="" src="${ly }/images/gao/aqgz6.jpg"/>
                    <img alt="" src="${ly }/images/gao/l1.jpg"/>
                    <img alt="" src="${ly }/images/gao/l2.jpg"/>
                    <img alt="" src="${ly }/images/gao/l3.jpg"/>
                    <img alt="" src="${ly }/images/gao/l4.jpg" style="margin-right:0;"/>
                  </div>
                </td>
              </tr>
              <tr>
                <td colspan="2">
                  <div class="div-gaoz" style="padding:0;">
                    <div class="xx-gaoz">重要<br>提示</div>
                    <font class="xx-gaoz1">安全是一种责任、为己为家为他人</font>
                  </div>
                </td>
                <td>
                  <div class="div-gaoz" style="margin-bottom:0;padding:5px 10px;">
                    <b>安全部电话</b>
                    <font>火警电话：119&emsp;急救电话：120</font>
                  </div>
                </td>
              </tr>
              <tr>
              </tr>
            </table>
          </div>
        </div>
        
        
        
      </div>
      
      
      
      
      
      
      
      
      
      
      
      
      
      
    </div>
  </div>
</body>
<script type="text/javascript">
function del(id) {
  $.post("/company/safety-system/risk-information-save", {
    id : id,
    del : 1
  },function(result) {
    location.reload();
  })
}
</script>
</html>