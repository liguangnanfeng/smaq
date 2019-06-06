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
.wid1{width:30%}
.wid2{width:30%}
.wid3{width:30%}
.wid4{width:10%}
.wid5{width:70%}
.btn-cz{margin:auto;background:#eee;border:1px solid #ddd;padding:0 5px;height:20px;line-height:20px;border-radius:5px;margin-left:15px}
.btn-cz:hover{border:1px solid #06c;color:#06c;text-decoration: none;}
body .check-box label{margin-left:5px;}
.one_danger .list_odan{margin-left:20px}
.two_danger .list_twdan{margin-left:45px;}
.one_danger .Hui-iconfont{margin-top:2px}
.one_danger,.list_danger{display:inline-block}
.one_danger,.one_danger .list_odan,.two_danger,.two_danger .list_twdan,.list_danger{float:none;}
.check-box{width:30%;margin-bottom:15px;}
.btn-group .btn{height: 34px;line-height: 34px;padding: 0 25px;}
.one-danrisk{float:left;width:100%;border-bottom:1px solid #ddd}
.one-danrisk p{float:left;width:80%;padding: 8px}
.table tbody tr td .one-danrisk a{float:right;margin-top:8px;margin-right:8px;}
</style>
<script type="text/javascript">
/* 弹窗工具添加 */
function addgj() {
  window.location.href = "/company/study/targetassessment";
}

</script>
</head>
<body>
		<nav class="breadcrumb">
	    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
	    <span class="c-gray en">&gt;</span> <span>风险研判与承诺公告</span> 
	    <span class="c-gray en">&gt;</span> <span>目标履职考核提示</span>
    <%--<a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;" href="javascript:history.back(-1)" title="返回">返回</a>--%>
	    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="parent.close_tab(function(){})">返回</a>
	  </nav>

    <div class="mt-20">
    	<table>
          <tr>
		     <td class="text-c" style="width:60px;height: 90px;text-align:center;">
                <button class="btn btn-primary radius" onClick="addgj()">请完善《安全风险研判目标管理履职考核》信息</button>
            </td>
          </tr>

     </table>
  </div>

  
</body>
<script type="text/javascript" src="/js/fxgk/tbl-rowspan-reset.js"></script>
</html>