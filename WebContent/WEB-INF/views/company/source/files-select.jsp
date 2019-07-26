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
<script type="text/javascript">
/* 弹窗工具添加 */
function edit(flag) {
	switch(flag){
		case 1:
			show_dialog(" ", "/company/source/files-list?type=1");
			break;
		case 2:
			show_dialog(" ","/company/source/files-list?type=2");
			break;
		case 3:
			show_dialog(" ","/company/source/files-list?type=3");
			break;
		case 4:
			show_dialog(" ","/company/source/files-list?type=4");
			break;
		case 5:
			show_dialog(" ","/company/source/files-list?type=5");
			break;
		case 6:
			show_dialog(" ","/company/source/publicity?flag=1");
			break;
		case 7:
			show_dialog(" ","/company/source/model-list-cx");
			break;
		case 8:
			show_dialog(" ","/company/source/check-list");
			break;

		default:
			return;
	}
  
}

</script>
</head>
<body>
		<nav class="breadcrumb">
	    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
	    <span class="c-gray en">&gt;</span> <span>风险研判与承诺公告</span> 
		<span class="c-gray en">&gt;</span> <span>运行管理</span> 
	    <span class="c-gray en">&gt;</span> <span>档案管理</span> 
	    <%--<a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="parent.close_tab(function(){})">返回</a>--%>
	<a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;margin-left:10px;" href="javascript:location.replace(location.href);" title="刷新">
	<i class="Hui-iconfont">&#xe68f;</i>
	</a>
	  </nav>

    <div class="mt-20">
    	<table class="table table-border table-bordered table-bg table-hover table-sort tab-ndan" style="width:60%;margin-left: 20%;">
    	<thead>
          <tr class="text-c">
            <th colspan="2" >档案汇总</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>  
          <tr>
          	<td class="text-c" style="width:20%;height: 60px;" rowspan="5">特殊作业流程</td>
          	<td class="text-c" style="width:60%;height: 60px;">
               		《非化工液氨使用企业》
           </td>
            <td class="text-c" style="width:20%;height: 60px;">
                <button class="btn btn-primary radius" onClick="edit(1)">查看</button>
            </td>
          </tr>
          <tr>
          	<td class="text-c" style="width:20%;height: 60px;" rowspan="5">特殊作业流程</td>
          	<td class="text-c" style="width:80%;height: 60px;">
                	《粉尘涉爆企业》
           </td>
            <td class="text-c" style="width:20%;height: 60px;">
                <button class="btn btn-primary radius" onClick="edit(2)">查看</button>
            </td>
          </tr>
          <tr>
          	<td class="text-c" style="width:20%;height: 60px;" rowspan="5">特殊作业流程</td>
          	<td class="text-c" style="width:80%;height: 60px;">
                	《高温熔融金属现场运输》
           </td>
            <td class="text-c" style="width:20%;height: 60px;">
                <button class="btn btn-primary radius" onClick="edit(3)">查看</button>
            </td>
          </tr>
          <tr>
          	<td class="text-c" style="width:20%;height: 60px;" rowspan="5">特殊作业流程</td>
          	<td class="text-c" style="width:80%;height: 60px;">
                	《工贸行业有限空间安全作业》
           </td>
            <td class="text-c" style="width:20%;height: 60px;">
                <button class="btn btn-primary radius" onClick="edit(4)">查看</button>
            </td>
          </tr>
          <tr>
          	<td class="text-c" style="width:20%;height: 60px;" rowspan="5">特殊作业流程</td>
          	<td class="text-c" style="width:80%;height: 60px;">
                	《冶金企业煤气》
           </td>
            <td class="text-c" style="width:20%;height: 60px;">
                <button class="btn btn-primary radius" onClick="edit(5)">查看</button>
            </td>
          </tr>
          
          
           <tr>
          	<td class="text-c" style="width:20%;height: 60px;" rowspan="1">管控措施</td>
          	<td class="text-c" style="width:80%;height: 60px;">
                	《公示》
           </td>
            <td class="text-c" style="width:20%;height: 60px;">
                <button class="btn btn-primary radius" onClick="edit(6)">查看</button>
            </td>
          </tr>
           <tr>
          	<td class="text-c" style="width:20%;height: 60px;" rowspan="2">隐患排查治理</td>
          	<td class="text-c" style="width:80%;height: 60px;">
                	《每日检查表》
           </td>
            <td class="text-c" style="width:20%;height: 60px;">
                <button class="btn btn-primary radius" onClick="edit(7)">查看</button>
            </td>
          </tr>         
         <tr>
          	<td class="text-c" style="width:20%;height: 60px;" rowspan="2">隐患排查治理</td>
          	<td class="text-c" style="width:80%;height: 60px;">
                	《隐患治理》
           </td>
            <td class="text-c" style="width:20%;height: 60px;">
                <button class="btn btn-primary radius" onClick="edit(8)">查看</button>
            </td>
          </tr>
          
          
          </tbody>

     </table>
  </div>
  <div class="mt-20 f-l mb-40" style="width:100%;text-align:center;"></div>
</body>
<script type="text/javascript" src="/js/fxgk/tbl-rowspan-reset.js"></script>
</html>