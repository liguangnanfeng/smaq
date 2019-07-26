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
			show_dialog(" ", "/company/study/files-list?type=1");
			break;
		case 2:
			show_dialog(" ","/company/study/files-list?type=2");
			break;
		case 3:
			show_dialog(" ","/company/study/files-list?type=3");
			break;
		case 4:
			show_dialog(" ","/company/study/files-list?type=4");
			break;
		case 5:
			show_dialog(" ","/company/study/files-list?type=5");
			break;
		case 6:
			show_dialog(" ","/company/study/files-list?type=6");
			break;
		case 7:
			show_dialog(" ","/company/study/files-list?type=7");
			break;
		case 8:
			show_dialog(" ","/company/study/files-list?type=8");
			break;
		case 9:
			show_dialog(" ","/company/study/files-list?type=9");
			break;
		case 10:
			show_dialog(" ","/company/study/files-list?type=10");
			break;
		case 11:
			show_dialog(" ","/company/study/files-list?type=11");
			break;
		case 12:
			show_dialog(" ","/company/study/files-list?type=12");
			break;
		case 13:
			show_dialog(" ","/company/study/files-list?type=13");
			break;		
		case 14:
			show_dialog(" ","/company/study/files-list?type=14");
			break;	
		case 15:
			show_dialog(" ","/company/study/files-list?type=15");
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
          	<td class="text-c" style="width:20%;height: 60px;" rowspan="8">特殊作业流程</td>
          	<td class="text-c" style="width:60%;height: 60px;">
               		《动火作业证》
           </td>
            <td class="text-c" style="width:20%;height: 60px;">
                <button class="btn btn-primary radius" onClick="edit(1)">查看</button>
            </td>
          </tr>
          <tr>
          	<td class="text-c" style="width:20%;height: 60px;" rowspan="8">特殊作业流程</td>
          	<td class="text-c" style="width:80%;height: 60px;">
                	《受限空间安全作业证》
           </td>
            <td class="text-c" style="width:20%;height: 60px;">
                <button class="btn btn-primary radius" onClick="edit(2)">查看</button>
            </td>
          </tr>
          <tr>
          	<td class="text-c" style="width:20%;height: 60px;" rowspan="8">特殊作业流程</td>
          	<td class="text-c" style="width:80%;height: 60px;">
                	《盲板抽堵安全作业证》
           </td>
            <td class="text-c" style="width:20%;height: 60px;">
                <button class="btn btn-primary radius" onClick="edit(3)">查看</button>
            </td>
          </tr>
          <tr>
          	<td class="text-c" style="width:20%;height: 60px;" rowspan="8">特殊作业流程</td>
          	<td class="text-c" style="width:80%;height: 60px;">
                	《高处安全作业证》
           </td>
            <td class="text-c" style="width:20%;height: 60px;">
                <button class="btn btn-primary radius" onClick="edit(4)">查看</button>
            </td>
          </tr>
          <tr>
          	<td class="text-c" style="width:20%;height: 60px;" rowspan="8">特殊作业流程</td>
          	<td class="text-c" style="width:80%;height: 60px;">
                	《吊装安全作业证》
           </td>
            <td class="text-c" style="width:20%;height: 60px;">
                <button class="btn btn-primary radius" onClick="edit(5)">查看</button>
            </td>
          </tr>
          <tr>
          	<td class="text-c" style="width:20%;height: 60px;" rowspan="8">特殊作业流程</td>
          	<td class="text-c" style="width:80%;height: 60px;">
                	《临时用电安全作业证》
           </td>
            <td class="text-c" style="width:20%;height: 60px;">
                <button class="btn btn-primary radius" onClick="edit(6)">查看</button>
            </td>
          </tr>
          <tr>
          	<td class="text-c" style="width:20%;height: 60px;" rowspan="8">特殊作业流程</td>
          	<td class="text-c" style="width:80%;height: 60px;">
                	《动土安全作业证》
           </td>
            <td class="text-c" style="width:20%;height: 60px;">
                <button class="btn btn-primary radius" onClick="edit(7)">查看</button>
            </td>
          </tr>
          <tr>
          	<td class="text-c" style="width:20%;height: 60px;" rowspan="8">特殊作业流程</td>
          	<td class="text-c" style="width:80%;height: 60px;">
                	《断路安全作业证》
           </td>
            <td class="text-c" style="width:20%;height: 60px;">
                <button class="btn btn-primary radius" onClick="edit(8)">查看</button>
            </td>
          </tr>
          
           <tr>
          	<td class="text-c" style="width:20%;height: 60px;" rowspan="8">检维修作业流程</td>
          	<td class="text-c" style="width:80%;height: 60px;">
                	《对检修作业现场进行安全检查的记录》
           </td>
            <td class="text-c" style="width:20%;height: 60px;">
                <button class="btn btn-primary radius" onClick="edit(9)">查看</button>
            </td>
          </tr>
           <tr>
          	<td class="text-c" style="width:20%;height: 60px;" rowspan="8">承包商作业流程</td>
          	<td class="text-c" style="width:80%;height: 60px;">
                	《承包商评价表》
           </td>
            <td class="text-c" style="width:20%;height: 60px;">
                <button class="btn btn-primary radius" onClick="edit(10)">查看</button>
            </td>
          </tr>         
         <tr>
          	<td class="text-c" style="width:20%;height: 60px;" rowspan="8">目标履职考核</td>
          	<td class="text-c" style="width:80%;height: 60px;">
                	《安全风险研判目标管理履职考核》
           </td>
            <td class="text-c" style="width:20%;height: 60px;">
                <button class="btn btn-primary radius" onClick="edit(11)">查看</button>
            </td>
          </tr>
          <tr>
          	<td class="text-c" style="width:20%;height: 60px;" rowspan="8">固有风险研判</td>
          	<td class="text-c" style="width:80%;height: 60px;">
                	《固有风险研判》
           </td>
            <td class="text-c" style="width:20%;height: 60px;">
                <button class="btn btn-primary radius" onClick="edit(12)">查看</button>
            </td>
          </tr>
          <tr>
          	<td class="text-c" style="width:20%;height: 60px;" rowspan="8">临时风险研判 </td>
          	<td class="text-c" style="width:80%;height: 60px;">
                	《临时风险研》
           </td>
            <td class="text-c" style="width:20%;height: 60px;">
                <button class="btn btn-primary radius" onClick="edit(13)">查看</button>
            </td>
          </tr>
          <tr>
          	<td class="text-c" style="width:20%;height: 60px;" rowspan="8">重大、较大隐患治理风险研判 </td>
          	<td class="text-c" style="width:80%;height: 60px;">
                	《重大、较大隐患治理风险研》
           </td>
            <td class="text-c" style="width:20%;height: 60px;">
                <button class="btn btn-primary radius" onClick="edit(14)">查看</button>
            </td>
          </tr>
          <tr>
          	<td class="text-c" style="width:20%;height: 60px;" rowspan="8">承诺公告公示 </td>
          	<td class="text-c" style="width:80%;height: 60px;">
                	《安全承诺公告牌》
           </td>
            <td class="text-c" style="width:20%;height: 60px;">
                <button class="btn btn-primary radius" onClick="edit(15)">查看</button>
            </td>
          </tr>
          
          </tbody>

     </table>
  </div>
  <div class="mt-20 f-l mb-40" style="width:100%;text-align:center;"></div>
</body>
<script type="text/javascript" src="/js/fxgk/tbl-rowspan-reset.js"></script>
</html>