<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=gbk" />
  <meta name="renderer" content="webkit|ie-comp|ie-stand">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <%@ include file="/WEB-INF/inc/back-header.inc"%>
  <title>���շּ��ܿ�   �����Ų��������ܻ�ƽ̨</title>
  <meta name="keywords" content="���շּ��ܿ�   �����Ų��������ܻ�ƽ̨">
  <meta name="description" content="���շּ��ܿ�   �����Ų��������ܻ�ƽ̨">
  <style type="text/css">
    body .dis-ib{margin-right:15px;}
  </style>
</head>

<body>
<nav class="breadcrumb">
  <i class="Hui-iconfont">&#xe67f;</i> <span>��ҳ</span>
  <span class="c-gray en">&gt;</span> <span>��ҵ������Ϣ</span>
  <span class="c-gray en">&gt;</span> <span>������������ͼ</span>
  <span class="c-gray en">&gt;</span> <span>�鿴�ĵ�</span>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href=" " title="ˢ��">
    <i class="Hui-iconfont">&#xe68f;</i>
  </a>
</nav>
<p>${list}</p>
  <iframe src="${list}" width='100%' height='100%' frameborder='1'>

  </iframe>

<%--<iframe src='http://www.xdocin.com/xdoc?_func=to&_format=html&_cache=1&_xdoc=${list}' width='100%' height='100%' frameborder='1'>
</iframe>--%>

</body>
</html>