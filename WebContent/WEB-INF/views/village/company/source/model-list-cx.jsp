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
.list_jian{display:none;padding:15px;min-height:180px;}
.div_bgrou{width:100%;text-align: center;padding-bottom:30px;border-bottom:1px dashed #ddd;margin-bottom:40px;}
.div_bgrou .btn{margin-right:20px;height:70px;line-height:70px;padding:0 50px;font-size:16px;background:#F6F6F7;color:#333}
.btn-group{width:100%;text-align: center;margin-bottom:30px;}
.btn-group .btn{height:38px;line-height:38px;padding:0 25px;font-size:14px;}
.modal-dialog{margin-top:150px;}
.font_wz{color:red;margin-left:15px;display:none;}
</style>
<script type="text/javascript">
function open_jian(e, flag){
  $("#win_jian").modal("show");
  $(".list_jian").hide();
  $(".font_wz").hide();
  window.flag_ = flag;
  $(""+e+"").show();
}
var userId = '${session_user.id}';
function l_(t) {
  if(t == 1) {
    if(flag_ == 2) { show_dialog('行政执法-添加检查表', '/village/check-add?flag=2');}
    if(flag_ == 3) { show_dialog('部门抽查-添加检查表', '/village/check-add?flag=3');}
    if(flag_ == 4) { show_dialog('行政检查-添加检查表', '/village/check-add?flag=4');}
  }
  if(t == 2) {
    if(flag_ == 2) { show_dialog('行政执法-添加现场检查记录', '/village/danger/opinion-add?flag=8&flag2=2&userId=' + userId);}
    if(flag_ == 3) { show_dialog('部门抽查-添加现场检查记录', '/village/danger/opinion-add?flag=8&flag2=3&userId=' + userId);}
    if(flag_ == 4) { show_dialog('行政检查-添加现场检查记录', '/village/danger/opinion-add?flag=8&flag2=4&userId=' + userId);}
  }
}

$(function(){
  $(".btn-group .btn").click(function(){
    if($(this).hasClass("btn-default")){
      $(this).removeClass("btn-default");
      $(this).addClass("btn-primary");
    }
  });
});

</script>
</head>
<body>
  <nav class="breadcrumb">
    <c:set var="x1" value="${fn:split('企业自查/ /部门抽查/行政检查','/') }"/>
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>重大危险源源长制</span> 
    	<span class="c-gray en">&gt;</span> <span>隐患排查治理 </span> 
		<a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <c:set var="x" value="${fn:split('综合检查表/检查表/定期检查表/整改实施','/') }"/>
    <div class="text-c">
    <c:if test="${flag == 1 }">
      <div id="spTab" class="btn-group" style="text-align: center;margin-bottom: 20px;margin-top:20px;">
        <a class="btn default ${flag == 1&&type==2 ? 'btn-primary' : 'radius'}" href="${ly }/company/source/model-list-cx">每日检查表</a>
        <a class="btn default ${flag == 1&&type==3 ? 'btn-primary' : 'radius'}" href="${ly }/company/source/check-list">隐患治理</a>
      </div>
    </c:if>
    </a>
    <!--<div class="cl pd-5 bg-1 bk-gray mt-20">
    <c:if test="${flag == 1 && type != 3 && type!=9}">
        <span class="l">
          <a class="btn btn-primary radius" onclick="show_dialog('添加${x[type-1] }','${ly }/company/model-add?type=${type }&flag=1')" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 添加${x[type-1] }</a>
        </span>
    </c:if>
    <c:if test="${flag == 3 }">
        <span class="l">
          <%-- <a class="btn btn-primary radius" data-title="添加" data-href="${ly }/company/model-add?type=${type }&flag=1" onclick="Hui_admin_tab(this)" href="javascript:;"> <i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 选择添加-部门抽查</a>--%>
          <input type="button" class="btn btn-primary radius" onClick="open_jian('.list_oth',3)" value="选择添加-部门抽查" />
        </span>
    </c:if>
    <c:if test="${flag == 4 }">
        <span class="l">
          <%-- <a class="btn btn-primary radius" data-title="添加" data-href="${ly }/company/model-add?type=${type }&flag=1" onclick="Hui_admin_tab(this)" href="javascript:;"> <i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 选择添加-行政检查</a>--%>
          <input type="button" class="btn btn-primary radius" onClick="open_jian('.list_oth',4)" value="选择添加-行政检查" />
        </span>
    </c:if>
    <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span>
    </div>-->
      <!-- 弹窗选择 -->
      <div id="win_jian" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content radius">
                <div class="modal-header">
                    <h3 class="modal-title">选择添加<font class="font_wz list_zi">（定期检查表用于定期检查，逾期不检查系统会预警提醒）</font></h3>
                    <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
                </div>
                <div class="modal-body">
                   <div class="list_jian list_zi">
                     <a class="btn_jian btn_an1" data-dismiss="modal" aria-hidden="true" data-title="企业自查-添加综合检查表" data-href="${ly }/company/model-add?type=1&flag=1" onclick="Hui_admin_tab(this)" href="javascript:;">
                       <span>
                         <img alt="" src="${ly }/images/b3wxz.png" class="wxz"/>
                         <img alt="" src="${ly }/images/b3xz.png" class="xz"/>
                       </span>
                       <font>综合检查表</font>
                     </a>
                     <a class="btn_jian btn_an2" data-dismiss="modal" aria-hidden="true" data-title="企业自查-添加定期检查表" data-href="${ly }/company/model-add?type=2&flag=1" onclick="Hui_admin_tab(this)" href="javascript:;">
                       <span>
                         <img alt="" src="${ly }/images/b4wxz.png" class="wxz"/>
                         <img alt="" src="${ly }/images/b4xz.png" class="xz"/>
                       </span>
                       <font>定期检查表</font> 
                     </a>
                   </div>
                   <div class="list_jian list_oth">
                     <a class="btn_jian btn_an3" data-dismiss="modal" onclick="l_(1)" aria-hidden="true" href="javascript:;">
                       <span>
                         <img alt="" src="${ly }/images/b1wxz.png" class="wxz"/>
                         <img alt="" src="${ly }/images/b1xz.png" class="xz"/>
                       </span> 
                       <font>标准检查</font>
                     </a>
                     <a class="btn_jian btn_an4" data-dismiss="modal" onclick="l_(2)" aria-hidden="true" href="javascript:;">
                       <span>
                         <img alt="" src="${ly }/images/b2wxz.png" class="wxz"/>
                         <img alt="" src="${ly }/images/b2xz.png" class="xz"/>
                       </span> 
                       <font>自定义检查</font>
                     </a>
                   </div>
                </div>
            </div>
        </div>
      </div>
      
    <c:if test="${type == 1 || type == 2 || type == 9}">
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th>检查表名称</th>   
            <c:choose>  
   						<c:when test="${type == 9 }">
   							<th>车间</th>
   						</c:when>  
   						<c:otherwise>
   							<th>受检${flag == 1 ? '部门' : '单位'}</th>
   						</c:otherwise>  
						</c:choose> 
            <c:if test="${type != 9 }"><th>检查类型</th></c:if>
            <c:choose>  
   						<c:when test="${type == 9 }">
   							<th>岗位/部位</th>
   						</c:when>  
   						<c:otherwise>
   							<th>检查部位</th>
   						</c:otherwise>  
						</c:choose>
            <th>检查次数</th>
            <th>最近${flag == 1 ? '检查' : '录入'}时间</th>
            <c:if test="${type == 2 || type == 9}">
            <th>自动化设置</th>
            </c:if>
            <th width="10%">操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环-->
          <c:forEach items="${list }" varStatus="index" var="be">
            <tr class="text-c">
            <td>${index.index + 1}</td>
            <td>${be.title }</td>
            <td>${be.part }</td>
            <c:if test="${type != 9 }">
            <td>
              <c:choose>
                <c:when test="${be.industryType == 1}">基础管理</c:when>
                <c:when test="${be.industryType == 2}">现场管理</c:when>
                <c:when test="${be.industryType == 3}">高危作业</c:when>
              </c:choose>
            </td>
            </c:if>
            <td>${be.partName }</td>
            <td>${be.c2 }/${be.c }</td>
            <td><fmt:formatDate value="${be.useTime }"/></td>
            <c:if test="${type == 2 || type==9}">
            <td>
                <span>开启/关闭状态：${be.open == 1 ? '开启':'关闭'}</span><br>
                <span>检查周期：${be.cycle }天</span><br>
                <span>下次${flag == 1 ? '检查' : '录入'}时间：<fmt:formatDate value="${be.nextTime }"/></span><br>
                <%-- <span>下次报表检查时间：<fmt:formatDate value="${be.nextCheckTime }"/></span> --%>
            </td>
            </c:if>
            <td>
              <a style="text-decoration:none" onClick="check_add(${be.id})" href="javascript:;">实施检查</a><br/>
              <a style="text-decoration:none" onclick="show_dialog('查看检查表_${be.id }', '${ly }/company/model-show/${be.id}?flag=1&type=${type}')" onclick="Hui_admin_tab(this)" href="javascript:;">查看检查表</a><br/>
              <!--<c:if test="${be.type != 9}">
              <a style="text-decoration:none" onClick="show_dialog('编辑检查表_${be.id }', '${ly}/company/model-edit?modelId=${be.id }')" href="javascript:;">编辑检查表</a><br/>
              </c:if>-->
              <!--<c:if test="${be.type == 2 || be.type == 9}">!-- zhangcl 2018.10.27--
              <a style="text-decoration:none" onClick="show_dialog('自动化设置_${be.id }', '${ly}/company/plan-auto?modelId=${be.id }&type=${type}')" href="javascript:;">自动化设置</a><br/>
              </c:if>-->
              <!--<a style="text-decoration:none" onClick="del1_(${be.id })" href="javascript:;">删除检查表</a>-->
            </td>
            </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
    </c:if>
    
<!--    整改复查-->
    <c:if test="${type == 3}">
       <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">编号</th>
            <th width="20%">检查表名称</th>
            <th>检查方式</th>
            <th width="10%">${flag == 1 ? '实际检查' : '录入'}时间</th>
            <th width="10%">状态</th>
            <th width="10%">隐患数量</th>
            <th width="15%">受检${flag == 1 ? '部门' : '单位'}</th>
            <th width="10%">操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环-->
          <c:forEach items="${list }" varStatus="index" var="be">
            <tr class="text-c">
            <td>${index.index + 1 }</td>
            <td><c:if test="${be.status == 1 and (!empty be.expectTime and be.expectTime.time < t)}"><font color="red">【过期】</font></c:if>${be.title }</td>
            <td>${empty be.expectTime ? '日常' : '定期'}</td>
            <td><fmt:formatDate value="${be.realTime }" pattern="yyyy-MM-dd"/></td>
            <td>
              ${be.status == 1 ? '未检查' : '已检查'}
            </td>
            <td>${be.c }</td>
            <td>${be.depart }</td>
            <td>
               <c:if test="${be.status == 1}">
                <a style="text-decoration:none" onClick="show_dialog('实施检查_${be.id }', '${ly}/village/plan-next?flag=${flag }&id=${be.id }')" href="javascript:;">实施检查</a><br>
               </c:if>
               <c:if test="${be.c > 0 && be.status == 2 }">
               <c:choose>
                    <c:when test="${be.t > 0}"><!-- 已设置整改意见 -->
                       <%-- <a style="text-decoration:none" onClick="show_dialog('整改意见_${be.id }', '${ly}/village/check-rectification?flag=${flag }&id=${be.id }')" href="javascript:;">整改意见</a><br> --%>
                    </c:when>
                    <c:otherwise><!-- 未设置整改意见 -->
                    <c:if test="${flag == 1}">
                      <a style="text-decoration:none" onClick="show_dialog('实施整改_${be.id }', '${ly}/village/check-rectification?flag=${flag }&id=${be.id }')" href="javascript:;">实施整改</a><br>
                      </c:if>
                      <c:if test="${flag == 4}">
                       <a style="text-decoration:none" onClick="show_dialog('实施整改_${be.id }', '${ly}/village/check-document?checkId=${be.id }&flag=1')" href="javascript:;">实施整改</a><br>
                       </c:if>
                       <c:if test="${flag == 3}">
                       <a style="text-decoration:none" onClick="show_dialog('实施整改_${be.id }', '${ly}/village/check-document?checkId=${be.id }&flag=8')" href="javascript:;">实施整改</a><br>
                       </c:if>
                   </c:otherwise>
                  </c:choose>
               <c:if test="${be.c3 > 0}">
              <%-- <a style="text-decoration:none" onClick="show_dialog('整改复查_${be.checkId}', '${ly }/company/recheck-add?checkId=${be.checkId }')" href="javascript:;">整改复查</a><br> --%>
              </c:if>
              <c:if test="${be.c3 == 0}">
              <c:if test="${flag == 1}">
               <a style="text-decoration:none" onClick="show_dialog('实施复查_${be.id }', '${ly }/company/recheck-add?checkId=${be.id }')" href="javascript:;">实施复查</a><br> 
               </c:if>
               <c:if test="${flag == 4 || flag == 3}">
               <a style="text-decoration:none" onClick="show_dialog('实施复查_${be.id }', '${ly}/village/check-document?checkId=${be.id }&flag=2')" href="javascript:;">实施复查</a><br>
               </c:if>
               </c:if>
              </c:if>
              <c:if test="${flag == 1}">
              <a style="text-decoration:none" onClick="show_dialog('检查详情_${be.id }', '${ly}/company/check-detail?flag=${flag }&id=${be.id }')" href="javascript:;">查看检查记录</a>
              </c:if>
              <c:if test="${flag == 4 || flag == 3}">
              <a style="text-decoration:none" onClick="show_dialog('检查详情_${be.id }', '${ly}/village/check-document?checkId=${be.id }&flag=8')" href="javascript:;">查看检查记录</a>
              </c:if>
                <a style="text-decoration:none" onClick="del_(${be.id})" href="javascript:;">删除检查记录</a>
              
            </td>
            </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
    </c:if>
    
    </div>
<script type="text/javascript">
	
function check_add(modelId) {
  var i = layer.load();
  //alert("modelId:"+modelId);
  $.post(getRootPath() + "/company/plan-save2", {
    modelId : modelId,
    type: type
  },function(result){
    layer.close(i);
    var id_ = result.map.id;
    if(result.status == '1') {
      layer.alert(result.map.message);
    } else {
    		show_dialog('实施检查_' + id_, getRootPath()+'/village/plan-next?id=' + id_+"&type="+type);
    }
  })
}

$(function() {
  $('.table-sort').dataTable({
    "aaSorting": [[0, "asc"]],//默认第几个排序
    "bStateSave": false,//状态保存
    "searching":false,
    "aoColumnDefs": [
    ]
    });
});

/*删除*/
function del_(id){
  layer.confirm("要删除该检查表？", function(i) {
    layer.close(i);
    $.post(getRootPath()+"/company/check-del",{
      id:id
    },function(reuslt){
      location.reload();
    })
  })
}

/*删除*/
function del1_(id){
  layer.confirm("要删除该检查表？", function(i) {
    layer.close(i);
    $.post(getRootPath()+"/company/model-del",{
      id:id
    },function(reuslt){
      location.reload();
    })
  })
}

var type = '${type}';
function copy_(id) {
  show_tab('添加自查记录', getRootPath()+'/company/check-copy?id=' + id);
}
</script> 
</body>
</html>