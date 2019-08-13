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
    if(flag_ == 2) { show_dialog('行政执法-添加检查表', '/global/check-add?flag=2');}
    if(flag_ == 3) { show_dialog('部门抽查-添加检查表', '/global/check-add?flag=3');}
    if(flag_ == 4) { show_dialog('行政检查-添加检查表', '/global/check-add?flag=4');}
  }
  if(t == 2) {
    if(flag_ == 2) { show_dialog('行政执法-添加现场检查记录', '/global/danger/opinion-add?flag=8&flag2=2&userId=');}
    if(flag_ == 3) { show_dialog('部门抽查-添加现场检查记录', '/global/danger/opinion-add?flag=8&flag2=3&userId=');}
    if(flag_ == 4) { show_dialog('行政检查-添加现场检查记录', '/global/danger/opinion-add?flag=8&flag2=4&userId=');}
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
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>${flag!=2 ? '隐患排查治理系统' : '行政执法系统'}</span> 
    <c:if test="${flag != 2}">
      <span class="c-gray en">&gt;</span> <span>隐患排查记录</span> 
    </c:if>
    <c:if test="${flag == 2}">
      <span class="c-gray en">&gt;</span> <span>执法记录</span> 
    </c:if>
    
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span>
         <c:if test="${flag == 2 }">
        <span class="l">
          <input type="button" class="btn btn-primary radius" onClick="open_jian('.list_oth',2)" value="选择添加-执法检查" />
        </span>
        </c:if>
       <c:if test="${flag == 3 }">
        <span class="l">
          <input type="button" class="btn btn-primary radius" onClick="open_jian('.list_oth',3)" value="选择添加-部门抽查" />
        </span>
        </c:if>
        <c:if test="${flag == 4 }">
        <span class="l">
          <input type="button" class="btn btn-primary radius" onClick="open_jian('.list_oth',4)" value="选择添加-行政检查" />
        </span>
        </c:if>
      </div>
    
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
    
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">编号</th>
            <th width="20%">企业名称</th>
            <th width="20%">检查表名称</th>
            <th width="10%">${flag == 1 ? '检查' : '录入'}时间</th>
            <th width="10%">状态</th>
            <th width="10%">隐患数量</th>
            <th width="15%">${flag == 1 ? '受检部门' : '检查单位'}</th>
            <th width="10%">操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环-->
          <c:forEach items="${list }" varStatus="index" var="be">
            <tr class="text-c">
            <td>${index.index + 1 }</td>
            <td>${be.companyName }</td>
            <td><c:if test="${be.status == 1 and (!empty be.expectTime and be.expectTime.time < t)}"><font color="red">【过期】</font></c:if>${be.title }</td>
            <td><fmt:formatDate value="${be.realTime }" pattern="yyyy-MM-dd"/></td>
            <td>
              ${be.status == 1 ? '未检查' : '已检查'}
            </td>
            <td>${be.c }</td>
            <td>${flag == 1 ? be.depart : be.checkCompany}</td>
            <td>
              
              <!-- 村镇 走这套 -->
              <c:if test="${session_user.userType == 3 || session_user.userType == 4}">
              <!-- 判断能否录入 行政检查 没填过 没过期的 -->
              <c:choose>
                <c:when test="${be.status == 1 && (empty be.expectTime or be.expectTime.time >= t) }">
                     <a style="text-decoration:none" onClick="show_dialog('实施检查_${be.id }', '${ly}/global/plan-next?flag=${flag }&id=${be.id }')" href="javascript:;">实施检查</a><br>
                </c:when>
                <c:otherwise>
                <%-- <c:if test="${be.flag == 4 }">
                     <a style="text-decoration:none" onClick="show_dialog('检查详情_${be.id }', '/village/check-document?checkId=${be.id}&flag=8')" href="javascript:;">现场检查记录</a><br>
                     </c:if> --%>
              <!-- 判断是否有整改意见 -->
              <c:if test="${be.c > 0 && be.status == 2 }"><%-- 已检查过且有隐患的需要整改意见--%>
              <c:choose>
                    <c:when test="${be.t > 0}"><!-- 已设置整改意见 -->
                       <%-- <a style="text-decoration:none" onClick="show_dialog('整改意见_${be.id }', '${ly}/village/check-rectification?flag=${flag }&id=${be.id }')" href="javascript:;">整改意见</a><br> --%>
                    </c:when>
                    <c:otherwise><!-- 未设置整改意见 -->
                      <c:if test="${flag == 4}">
                       <a style="text-decoration:none" onClick="show_dialog('实施整改_${be.id }', '${ly}/global/check-document?checkId=${be.id }&flag=1')" href="javascript:;">实施整改</a><br>
                       </c:if>
                       <c:if test="${flag == 3}">
                       <a style="text-decoration:none" onClick="show_dialog('实施整改_${be.id }', '${ly}/global/check-document?checkId=${be.id }&flag=1')" href="javascript:;">实施整改</a><br>
                       </c:if>
                   </c:otherwise>
                  </c:choose>
              </c:if>
              <c:if test="${be.c3 == 0 && be.c > 0}">
                <a style="text-decoration:none" onClick="show_dialog('实施复查_${be.id }', '${ly}/global/check-document?checkId=${be.id }&flag=2')" href="javascript:;">实施复查</a><br>
                </c:if>
                </c:otherwise>
              </c:choose>
              
              <a style="text-decoration:none" onClick="show_dialog('检查详情_${be.id }', '${ly}/global/check-document?checkId=${be.id }&flag=8')" href="javascript:;">查看检查记录</a>
              <a style="text-decoration:none" onClick="del_(${be.id})" href="javascript:;">删除检查记录</a>
              </c:if>
              
              <!-- 区级 市 省 走这套 -->
              <c:if test="${session_user.userType >= 6 || session_user.userName eq '港口'}">
              <!-- 判断能否录入 行政检查 没填过 没过期的 -->
              <c:choose>
                <c:when test="${be.status == 1 && be.flag == 2 && (empty be.expectTime or be.expectTime.time >= t) }">
                     <a style="text-decoration:none" onClick="show_dialog('实施检查_${be.id }', '${ly}/global/plan-next?flag=${flag }&id=${be.id }')" href="javascript:;">实施检查</a><br>
                </c:when>
                <c:otherwise>
                     <%-- <a style="text-decoration:none" onClick="show_dialog('检查详情_${be.id }', '${ly}/company/check-detail?flag=${flag }&id=${be.id }')" href="javascript:;">检查详情</a><br> --%>
                     <!-- 判断是否有整改意见 -->
              <c:if test="${be.c > 0 && be.status == 2 }"><%-- 已检查过且有隐患的需要整改意见--%>
              <c:choose>
                    <c:when test="${be.t > 0}"><!-- 已设置整改意见 -->
                       <%-- <a style="text-decoration:none" onClick="show_dialog('整改意见_${be.id }', '${ly}/village/check-rectification?flag=${flag }&id=${be.id }')" href="javascript:;">整改意见</a><br> --%>
                    </c:when>
                    <c:otherwise><!-- 未设置整改意见 -->
                      <c:if test="${flag == 4}">
                       <a style="text-decoration:none" onClick="show_dialog('实施整改_${be.id }', '${ly}/global/check-document?checkId=${be.id }&flag=1')" href="javascript:;">实施整改</a><br>
                       </c:if>
                       <c:if test="${flag == 3}">
                       <a style="text-decoration:none" onClick="show_dialog('实施整改_${be.id }', '${ly}/global/check-document?checkId=${be.id }&flag=1')" href="javascript:;">实施整改</a><br>
                       </c:if>
                   </c:otherwise>
                  </c:choose>
              </c:if>
              <c:if test="${be.c3 == 0 && be.c > 0}">
                <a style="text-decoration:none" onClick="show_dialog('实施复查_${be.id }', '${ly}/global/check-document?checkId=${be.id }&flag=2')" href="javascript:;">实施复查</a><br>
                </c:if>
                     <%-- <c:if test="${flag == 1 }">
                     <a style="text-decoration:none" onClick="show_dialog('检查详情_${be.id }', '${ly}/company/check-detail?flag=${flag }&id=${be.id }')" href="javascript:;">检查详情</a><br>
                     </c:if>
                     <c:if test="${flag != 1 }">
                     <a style="text-decoration:none" onClick="show_dialog('检查详情_${be.id }', '/village/check-document?checkId=${be.id}&flag=8')" href="javascript:;">检查详情</a><br>
                     </c:if> --%>
                </c:otherwise>
              </c:choose>
              <a style="text-decoration:none" onClick="show_dialog('检查详情_${be.id }', '${ly}/global/check-document?checkId=${be.id }&flag=8')" href="javascript:;">查看检查记录</a>
              <a style="text-decoration:none" onClick="del_(${be.id})" href="javascript:;">删除检查记录</a>
             <%--  <!-- 判断是否有整改意见 -->
              <c:if test="${be.c > 0 && be.status == 2 }">已检查过且有隐患的需要整改意见
              <c:choose>
                <c:when test="${be.t > 0}">已设置整改意见
                   <a style="text-decoration:none" onClick="show_dialog('整改意见_${be.id }', '${ly}/village/check-rectification?flag=${flag }&id=${be.id }')" href="javascript:;">整改意见</a><br>
                   <c:if test="${flag == 1 }">
                   <a style="text-decoration:none" onClick="show_dialog('整改意见_${be.id }', '${ly}/village/check-rectification?flag=${flag }&id=${be.id }')" href="javascript:;">整改意见</a><br>
                   </c:if>
                   <c:if test="${flag != 1 }">
                   <a style="text-decoration:none" onClick="show_dialog('整改意见_${be.id }', '/village/check-document?checkId=${be.id}&flag=${flag == 3 ? 8 : 1}')" href="javascript:;">整改意见</a><br>
                   </c:if>
                </c:when>
                <c:when test="${be.flag == 2 || be.flag == 3}">行政执法可编辑可查看
                  <a style="text-decoration:none" onClick="show_dialog('整改意见_${be.id }', '${ly}/village/check-rectification?flag=${flag }&id=${be.id }')" href="javascript:;">整改意见</a><br>
                </c:when>
                <c:otherwise>未设置整改意见
                   <a style="text-decoration:none" onClick="layer.alert('还未设置整改意见')" href="javascript:;">整改意见</a><br>
                </c:otherwise>
              </c:choose>
                
                <c:if test="${be.c3 > 0}">
                  <a style="text-decoration:none" onClick="show_dialog('复查详情_${be.id}', '${ly }/company/recheck-detail?checkId=${be.id }')" href="javascript:;">复查详情</a><br>
                </c:if>
                <c:if test="${be.c3 == 0}">
                  <a style="text-decoration:none" onClick="layer.alert('还没有复查记录哦。。。')" href="javascript:;">复查详情</a><br>
                </c:if>
                
              </c:if>
               <c:if test="${be.flag == 2 || be.flag == 3}">
                <a style="text-decoration:none" onClick="show_dialog('检查文书-现场检查记录_${be.id }', '${ly}/village/check-document?checkId=${be.id }&flag=8')" href="javascript:;">查看检查文书</a><br>
               </c:if>
              
              <c:if test="${be.flag == 2 || be.flag == 3}">
                <a style="text-decoration:none" onClick="del_(${be.id})" href="javascript:;">删除检查记录</a>
              </c:if> --%>
              </c:if>
              
            </td>
            </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
  </div>
<script type="text/javascript">
$(function() {
  $('.table-sort').dataTable({
    "aaSorting": [[0, "asc"]],//默认第几个排序
    "bStateSave": false,//状态保存
    "aoColumnDefs": [
    ]
    });
});
/*删除*/
function del_(id){
  layer.confirm("要删除该记录？", function(i) {
    layer.close(i);
    $.post(getRootPath()+"/village/check-del",{
      id:id
    },function(reuslt){
      location.reload();
    })
  })
}
</script> 
</body>
</html>