<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- zhangcl 2018.11.6 --%>
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
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>风险研判与承诺公告<span class="c-gray en">&gt;</span>管理手册
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
    <i class="Hui-iconfont">&#xe68f;</i></a>
  </nav>
    <div class="page-container">
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort tab-ndan">
        <thead>
          <tr class="text-c">
            <th>目录</th>
            <th style="width:150px">操作</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td style="text-align:center">安全风险研判与承诺公告制度</td>
            <td class="text-c">
              <a style="text-decoration:none" onClick="show_dialog('编辑文档', '/company/safety-system/plan-add?type=7')" href="javascript:;" title="编辑文档">编辑文档</a>
            </td>
          </tr>
                  <tr>
            <td style="text-align:center">企业体系架构图</td>
            <td class="text-c">
              <a style="text-decoration:none" onClick="show_dialog('查看详情', '${ly }/company/study/department-list?sign=1')" href="javascript:;" title="查看详情">查看详情</a>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</body>
</html>