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
<%@ include file="/WEB-INF/inc/print.inc"%>
<link href="/styles/print-department-list.css" rel="stylesheet"type="text/css" media="print">
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style>
/*body{  
  text-align:center;  
}*/

.line{border-top: 0;}

.jOrgChart .line {
  height                : 20px;
  width                 : 4px;
  
}

.jOrgChart .down {
  background-color     : green;
  margin              : 0px auto;
  border-down          : 3px solid green;
}

.jOrgChart .top {
  border-top          : 3px solid green;
}

.jOrgChart .left {
  border-right          : 2px solid green;
}

.jOrgChart .right {
  border-left           : 2px solid green;
}

/* node cell */
.jOrgChart td {
  text-align            : center;
  vertical-align        : top;
  padding               : 0;
}

.jOrgChart .node {
  /*background-color    : #35363B;*/
  background-color      : deepskyblue;
  display               : inline-block;
  width                 : 200px;
  height                : 100px;
  z-index               : 10;
  margin                : 0 0px;
}

/* jQuery drag 'n drop */

.drag-active {
  border-style      : dotted !important;
}

.drop-hover {
  border-style      : solid !important;
  border-color      : #E05E00 !important;
}

a {
            text-decoration: none;
            color: #000000;
            font-size: 12px;
}

.jOrgChart .node {
            width: 130px;
            height: 30%;
            line-height: 40px;
            border-radius: 4px;
            margin: 0 8px;
            overflow:hidden;
        }

.jOrgChart tr tr .node{width: 40%;}
.jOrgChart tr tr tr .node{width: 40%;}
.jOrgChart td {width: 10%;}
table {table-layout: fixed;word-wrap:break-word;}

</style>
</head>
<body>
  <nav class="breadcrumb">
    <c:if test="${signjgt == 1}"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>风险研判与承诺公告<span class="c-gray en">&gt;</span>管理手册<span class="c-gray en">&gt;</span>体系建设</c:if>
    <c:if test="${signjgt == 2}"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>重大危险源源长制<span class="c-gray en">&gt;</span>管控机制<span class="c-gray en">&gt;</span>管控架构</c:if>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
    <i class="Hui-iconfont">&#xe68f;</i></a>
  </nav>
    <!--startprint-->
  <div style="margin-left: 1%;" id='jOrgChart'>
  	<div class="page-container">
	  <c:if test="${signjgt == 1}"><h3 class="text-c">风险研判与承诺公告组织架构图</h3></c:if>
	  <c:if test="${signjgt == 2}"><h3 class="text-c">${username }重大危险源源长制组织架构图</h3></c:if>
    </div>
  </div>
  <!--endprint-->
  <div class="mt-20 f-l mb-40" style="width:100%;text-align:center;">
  	 <button onClick="pr_()" class="btn btn-primary radius mt-20" type="button" style="padding:0 70px;">
        <i class="Hui-iconfont mr-10">&#xe632;</i>打印
     </button>
  </div>
</body>
<script src="/js/jquery.jOrgChart.js"></script>
<script type="text/javascript">

function pr_() {
		$("#jOrgChart").jqprint();
}

  var companyids=${list.id};
  var departmentids = new Array();
  var departmentcids = new Array();
  var departmentpids = new Array();
  var departmentlevels = new Array();
  var departmentnames = new Array();

  <c:forEach items="${listbm}" var="department">
    departmentids.push(${department.id});
    departmentlevels.push(${department.level});
    departmentnames.push("${department.name}");
    departmentcids.push(${department.cid});
    if("${department.pid}"!=""){
      departmentpids.push(${department.pid});
    }
    else{
      departmentpids.push(-1);
    }
    
  </c:forEach>
  
  var dataobj = new Object();
  var id=1;
  var pid=null;
  var comp = new Array();
  var bumen = new Array();
  comp.push({'id':id,'name':"${username}",'pid':null,'childrens':bumen});
  pid=id;
  id++;
  for(var j=0;j<departmentids.length;j++){
    var banzu = new Array();
    if(companyids==departmentcids[j]&&departmentlevels[j]==1){
      bumen.push({'id':id,'name':departmentnames[j],'pid':pid,'childrens':banzu});
        pid=id;
        id++;
        for(var k=0;k<departmentids.length;k++){
          if(departmentids[j]==departmentpids[k]&&departmentlevels[k]==2){
            banzu.push({'id':id,'name':departmentnames[k],'pid':pid,'childrens':[]});
            id++;
          }
        }
    }
  }
  dataobj = {'comp':comp};
    
  function showall(menu_list, parent) {
    $.each(menu_list, function (index, val) {
        if (val.childrens.length > 0) {
            var li = $("<li></li>");
            li.append("<a href='javascript:void(0)' onclick=getOrgId(" + val.id + ");>" + val.name +
                "</a>").append("<ul></ul>").appendTo(parent);
            //递归显示
            showall(val.childrens, $(li).children().eq(1));
        } else {
            $("<li></li>").append("<a href='javascript:void(0)' onclick=getOrgId(" + val.id + ");>" +
                val.name + "</a>").appendTo(parent);
        }
    });
  }

  var showlist = $("<ul id='org' style='display:none'></ul>");
  showall(dataobj.comp, showlist);
  $("#jOrgChart").append(showlist);
  $("#org").jOrgChart({
  chartElement: '#jOrgChart', //指定在某个dom生成jorgchart
  dragAndDrop: false //设置是否可拖动
  });

  function getOrgId(val) {
  	console.log(val);
  }

</script>
</html>