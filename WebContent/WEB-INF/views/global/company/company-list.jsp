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
</style>
<script type="text/javascript">
$(function() {
  if($("input[name='totalwyx']").val() == '') {
    $.post("/village/company-list-tj", {
      
    },function(result) {
      var totalwyx = result.map.totalwyx;
      var totalzc = result.map.totalzc;
      $("#totalwyx").text(totalwyx);
      $("#totalzc").text(totalzc);
      $("input[name='totalwyx']").val(totalwyx);
      $("input[name='totalzc']").val(totalzc);
    })
  }
})
</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>企业信息库</span> 
    <span class="c-gray en">&gt;</span> <span>${title }</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="text-c">
      <form action="/global/company/company-list" method="post">
        <div class="dis-ib">
        <span>企业名称：</span>
        <input type="text" value="${dto.userIds }" name="userIds" class="input-text mb-5 mt-5" style="width:150px;display: none;">
        <input type="text" value="${totalwyx }" name="totalwyx" class="input-text mb-5 mt-5" style="width:150px;display: none;">
        <input type="text" value="${totalzc }" name="totalzc" class="input-text mb-5 mt-5" style="width:150px;display: none;">
        <input type="text" value="${dto.companyName }" name="companyName" class="input-text mb-5 mt-5" style="width:150px;">
        <input type="text" value="${dto.page }" name="page" id="page" style="display: none;">
        <input type="text" value="${dto.rows }" name="rows" id="rows" style="display: none;">
        <input type="text" value="${dto.doubleDanger }" name="doubleDanger"  style="display: none;">
        <input type="text" value="${dto.hazard }" name="hazard" style="display: none;">
        <input type="text" value="${dto.danger }" name="danger" style="display: none;">
        <%-- <input type="text" value="${dto.villageId }" name="villageId" style="display: none;">
        <input type="text" value="${dto.townId }" name="townId" style="display: none;">
        <input type="text" value="${dto.districtId }" name="districtId" style="display: none;"> --%>
        </div>

        <c:if test="${session_user.userType == 3 || session_user.userType == 6}">
        <div class="dis-ib">
            <span>所属村：</span>
            <span class="select-box inline">
              <select id="villageId" name="villageId" class="select" style="width:100px;">
                <option value="">全部</option>
                <c:forEach items="${villagelist }" varStatus="index" var="be">
                    <option value="${be.userId }" <c:if test="${dto.villageId == be.userId}"> selected</c:if>>${be.name }</option>
                </c:forEach>
              </select>
            </span>
        </div>
        </c:if>
        
        <div class="dis-ib">
          <span>所属行业：</span>
          <%-- <select id="industry" name="industry" class="select" style="width:230px;">
            <option value="">全部</option>
            <c:forEach items="${lib }" varStatus="index" var="be">
				<option value="${be.name }" <c:if test="${industry == be.name}"> selected</c:if>>${be.name }</option>
		    </c:forEach>
          </select> --%>
          <select class="sel_area" id="industry2_" name="industry2_" style="width: 100px;">
            <option value="">请选择</option>
          </select>
          <select class="sel_area" id="industry2_2" name="industry2_2" style="width: 100px;">
            <option value="">请选择</option>
          </select>
          <script type="text/javascript" src="${ly }/js/fxgk/industry.js?v=180920"></script>
          <script type="text/javascript">
          var industry2 = '${dto.industry2}';
          $(function() {
            $("#industry2_").change(function() {
              $("#industry2_2").empty().append('<option value="">请选择</option>');
              var v = $(this).val();
              if(v != '') {
                $.each(industry2List, function(i, item) {
                  if(item.name == v) {
                    $.each(item.list, function(j, jtem) {
                      $("#industry2_2").append("<option>" + jtem + "</option>");
                    })
                  }
                })
                
                if(industry2 != '') {
                  $("#industry2_2").val(industry2.split(" > ")[1]);
                  industry2 = '';
                }
              }
            })
            $.each(industry2List, function(i, item) {
              $("#industry2_").append("<option>" + item.name + "</option>");
            })
            if(industry2 != '') {
              $("#industry2_").val(industry2.split(" > ")[0]).change();
            }
          })
          </script>
        </div>
        <div class="dis-ib">
          <span>运行状态：</span>
          <span class="select-box inline">
          <select name="isFreeze" class="select">
            <option value="">全部</option>
            <option value="1"<c:if test="${dto.isFreeze == '1'}"> selected</c:if>>正常</option>
            <option value="0"<c:if test="${dto.isFreeze == '0'}"> selected</c:if>>未运行</option>
          </select>
          </span>
        </div>
        
        <c:if test="${session_user.userType == 4}">
        <div class="dis-ib">
          <span>是否重点企业：</span>
          <span class="select-box inline">
          <select name="isKey" class="select">
            <option value="">全部</option>
            <option value="1"<c:if test="${dto.isKey == '1'}"> selected</c:if>>是</option>
            <option value="0"<c:if test="${dto.isKey == '0'}"> selected</c:if>>否</option>
          </select>
          </span>
        </div>
        </c:if>
        
        <c:if test="${session_user.userType == 3}">
        <div class="dis-ib">
          <span>重点企业：</span>
          <span class="select-box inline">
          <select name="key1" class="select">
            <option value="">全部</option>
            <option value="1"<c:if test="${dto.key1 == '1'}"> selected</c:if>>是</option>
            <option value="0"<c:if test="${dto.key1 == '0'}"> selected</c:if>>否</option>
          </select>
          </span>
        </div>
        </c:if>
        
        <c:if test="${session_user.userType == 6}">
        <div class="dis-ib">
          <span>重点企业：</span>
          <span class="select-box inline">
          <select name="key2" class="select">
            <option value="">全部</option>
            <option value="1"<c:if test="${dto.key2 == '1'}"> selected</c:if>>是</option>
            <option value="0"<c:if test="${dto.key2 == '0'}"> selected</c:if>>否</option>
          </select>
          </span>
        </div>
        </c:if>
        
        <c:if test="${session_user.userType == 10}">
        <div class="dis-ib">
          <span>是否重点企业：</span>
          <span class="select-box inline">
          <select name="isTradeKey" class="select">
            <option value="">全部</option>
            <option value="1"<c:if test="${m.isTradeKey == '1'}"> selected</c:if>>是</option>
            <option value="0"<c:if test="${m.isTradeKey == '0'}"> selected</c:if>>否</option>
          </select>
          </span>
        </div>
        </c:if>
        
        <button class="btn btn-success" type="submit" onclick="$('#page').val(0);$('input[name=userIds]').val('');">
          <i class="Hui-iconfont">&#xe665;</i> 查询
        </button>
      </form>
    </div>
    
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l isAll">
        <c:if test="${session_user.userType == 4 || session_user.userType == 3 || session_user.userType == 6}">
        <!-- <a class="btn btn-primary radius" data-title="导入企业信息"  onclick="leadin()" href="javascript:;"><i class="Hui-iconfont" style="font-size:15px;">&#xe642;</i> 导入企业信息</a> -->
        <a class="btn btn-primary radius" data-title="添加企业" data-href="${ly }/global/company/company-add" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 添加企业</a>
        </c:if>
        
        <c:if test="${session_user.userType == 10}">
        <a class="btn btn-primary radius" data-title="添加企业" data-href="${ly }/global/company/company-add-trade" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 添加企业</a>
        </c:if>
        
      </span>
      <span class="r">未运行企业：<strong id="totalwyx">${totalwyx }</strong> 家。</span>
      <span class="r">所有数据中正常企业：<strong id="totalzc">${totalzc }</strong> 家，</span>
      <span class="r">当前共有数据：<strong>${total }</strong> 条。</span> 
    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="15%">企业名称</th>
            <th width="15%">所属行业</th>
            <th width="8%">风险等级</th>
            <th width="15%">所在地</th>
            <th width="14%">规模类型</th>
            <th width="8%">运行状态</th>
            <th width="15%">操作</th>
          </tr>
        </thead>
        <tbody>
        
          <!-- 循环村 -->
         <c:forEach items="${list }" varStatus="index" var="co">
          <tr class="text-c">
            <td>${dto.page * dto.rows + index.index + 1}</td>
            <td ondblclick="window.open('/fore/company?un=${co.name}');">
                
                <c:choose>
                    <c:when test="${session_user.userType == 4 }">
                    <c:if test="${co.isKey == '1' && not empty sk}"><label style="color:#DF2E30">【重点企业】</label></c:if>
                    </c:when>
                    <c:when test="${session_user.userType == 3 }">
                    <c:if test="${co.key1 == 1 && not empty sk}"><label style="color:#DF2E30">【重点】</label></c:if>
                    </c:when>
                    <c:when test="${session_user.userType == 6 }">
                    <c:if test="${co.key2 == 1 && not empty sk}"><label style="color:#DF2E30">【重点】</label></c:if>
                    </c:when>
                    <c:when test="${session_user.userType == 10 }">
                    <c:if test="${co.tradeKey == '1' && sk == 0}"><label style="color:#DF2E30">【重点企业】</label></c:if>
                    </c:when>
                </c:choose>
                <a  style="color:black;display: inline-block;margin: -18px 0px;
                	line-height: 46px;width:180px;background-color: #eee;
                	border: 0px solid #ddd;padding:3px 3px;
                	border-radius: 0px;text-decoration:none" 
                	href="${ly }/global/move/company?uid=${co.userId }" target="_parent">${co.name }</a>
            </td>
            <td>${co.industry2 }</td>
            <td>${co.dlevel }</td>
            <td>${co.regionName} ${co.address }</td>
            <td>${co.scale}</td>
            <c:if test="${0 == co.count }">
            <td>未运行</td>
            </c:if>
            <c:if test="${0 != co.count }">
            <td>正常</td>
            </c:if>
            <td>
            
              <a class="label label-primary radius" style="text-decoration:none" onClick='show_dialog("企业详细信息", "${ly }/global/company/company-show?userId=${co.userId }")' href="javascript:;" title="查看企业详细资料">查看详情</a>
              <a class="label label-primary radius" style="text-decoration:none" onClick='show_dialog("企业详细信息", "${ly }/global/company-edit?userId=${co.userId }")' href="javascript:;" title="企业资料编辑">编辑</a>
              
              <!-- 村管辖企业  -->
              <c:if test="${session_user.userType == 4}">
                <c:if test="${co.isKey == 0}">
                <a style="text-decoration:none" onClick="user_isKey(${co.userId },1)" href="javascript:;">设为重点企业</a>
                </c:if>
                <c:if test="${co.isKey == 1}">
                <a style="text-decoration:none" onClick="user_isKey(${co.userId },0)" href="javascript:;">取消重点企业</a>
                </c:if>
              </c:if>
              
              <!-- 镇管辖企业 -->
              <c:if test="${session_user.userType == 3}">
                <c:if test="${co.key1 != 1}">
                <a style="text-decoration:none" onClick="user_isKey1(${co.userId }, 1)" href="javascript:;">设为重点企业</a>
                </c:if>
                <c:if test="${co.key1 == 1}">
                <a style="text-decoration:none" onClick="user_isKey1(${co.userId }, 0)" href="javascript:;">取消重点企业</a>
                </c:if>
              </c:if>
              
              <!-- 区管辖企业 -->
              <c:if test="${session_user.userType == 6}">
                <c:if test="${co.key2 != 1}">
                <a style="text-decoration:none" onClick="user_isKey2(${co.userId }, 1)" href="javascript:;">设为重点企业</a>
                </c:if>
                <c:if test="${co.key2 == 1}">
                <a style="text-decoration:none" onClick="user_isKey2(${co.userId }, 0)" href="javascript:;">取消重点企业</a>
                </c:if>
              </c:if>
              
              <c:if test="${session_user.userType == 10}">
                <a style="text-decoration:none" onClick="user_tradeKey(${co.tradeCompanyId },${co.tradeKey == 1 ? 0 : 1})" href="javascript:;">${co.tradeKey == 1 ? '取消' : '设为'}重点企业</a>
                <!-- 行业企业删除 -->
                <c:if test="${session_user.userType == 10}">
                  <a style="text-decoration:none" onclick="deltrade(${co.userId })" href="javascript:;" title="删除">删除</a>
                </c:if>
              </c:if>
              
              <%-- <c:if test="${co.isFreeze == '0'}">
                <a style="text-decoration:none" onClick="user_freeze(${co.userId })" href="javascript:;" title="冻结">冻结</a>
              </c:if>
              <c:if test="${co.isFreeze == '1'}">
                <a style="text-decoration:none" onClick="user_unfreeze(${co.userId })" href="javascript:;" title="解冻">解冻</a>
              </c:if>
              
              <a style="text-decoration:none" onclick="psw(${co.userId })" href="javascript:;" title="重置密码">重置密码</a> --%>
            </td>
          </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
      <div id="layer_page_div" style="margin-top: 15px;"></div>
    </div>
  </div>
  
  
<script type="text/javascript">
//冻结
function user_freeze(userId){
  layer.confirm("确认要冻结该企业吗？",function(){
    var index = layer.load();
    $.post(getRootPath() + "/village/user-freeze",{
      id : userId,
      isFreeze : 1
    },function(result){
      layer.close(index);
      $("#page").val(0);
      $("form").submit();
    })
  });
}

function leadin(){
  show_tab("导入企业", getRootPath() + "/village/company/company-leadin")
}

//解冻
function user_unfreeze(userId){
  layer.confirm("确认要解冻该企业吗？",function(){
    var index = layer.load();
    $.post(getRootPath() + "/village/user-freeze",{
      id : userId,
      isFreeze : 0
    },function(result){
      layer.close(index);
      $("#page").val(0);
      $("form").submit();
    })
  });
}

//修改村重点企业
function user_isKey(userId,isKey){
  /*isKey = isKey=="1"?"0":"1";*/
  var text = isKey=="1"?"确认设置该企业为重点企业吗":"确认取消该重点企业吗";
  layer.confirm(text,function(){
    var index = layer.load();
    $.post(getRootPath() + "/global/user-isKey",{
      userId : userId,
      isKey : isKey
    },function(result){
      layer.close(index);
      $("#page").val(0);
      $("form").submit();
    })
  });
}

//修改镇重点企业
function user_isKey1(userId, key1){
  var text = key1=="1"?"确认设置该企业为重点企业吗":"确认取消该重点企业吗";
  layer.confirm(text,function(){
    var index = layer.load();
    $.post(getRootPath() + "/global/user-key",{
      userId : userId,
      key1 : key1
    },function(result){
      layer.close(index);
      $("#page").val(0);
      $("form").submit();
    })
  });
}

//修改区重点企业
function user_isKey2(userId, key2){
var text = key2=="1"?"确认设置该企业为重点企业吗":"确认取消该重点企业吗";
layer.confirm(text,function(){
  var index = layer.load();
  $.post(getRootPath() + "/global/user-key2",{
    userId : userId,
    key2 : key2
  },function(result){
    layer.close(index);
    $("#page").val(0);
    $("form").submit();
  })
});
}

//删除行业企业
function deltrade(userId) {
  layer.confirm("确认要删除该企业吗？",function(){
    var index = layer.load();
    $.post(getRootPath() + "/global/trade-user-del",{
      userId : userId,
    },function(result){
      layer.close(index);
      $("#page").val(0);
      $("form").submit();
    })
  });
}

//修改重点企业
function user_tradeKey(userId,isKey){
isKey = isKey=="1"?"0":"1";
var text = isKey=="1"?"确认设置该企业为重点企业吗":"确认取消该重点企业吗";
layer.confirm(text,function(){
  var index = layer.load();
  $.post(getRootPath() + "/global/user-tradeKey",{
    id : userId,
    iskey : isKey
  },function(result){
    layer.close(index);
    $("form").submit();
  })
});
}

//密码重置
function psw(userId){
layer.confirm("确认要重置密码么？",function(index){
  $.post(getRootPath() + "/village/system/user-psw",{
    id : userId
  },function(result){
    layer.alert("密码重置为‘123456’");
  });
});
}

</script> 
</body>
<script type="text/javascript" src="${ly }/js/H-ui.admin_v3.0/lib/laypage/1.2/laypage.js"></script>
<link type="text/css" href="${ly }/js/H-ui.admin_v3.0/lib/laypage/1.2/skin/laypage.css" rel="stylesheet" />
<script type="text/javascript">
//页码，每页条数，总数目
var page = ${dto.page};
var rows = ${dto.rows}
var total = ${total};
var a_p = 0;
if(total % rows == 0) {
  a_p = (total) / rows;
} else {
  a_p = (total - total % rows) / rows + 1;
}
if(a_p > 1) {
  laypage({
    cont : "layer_page_div", //容器。值支持id名、原生dom对象，jquery对象,
    pages : a_p, //总页数
    //skin : '#AF0000', //加载内置皮肤，也可以直接赋值16进制颜色值，如:#c00
    groups : 8, //连续显示分页数
    curr : page + 1,//当前页
    jump: function(obj){ //触发分页后的回调
      if($("#page").val() != obj.curr - 1) {
            $("#page").val(obj.curr - 1);
            $("form").submit();
            layer.load();
      }
    }
  });
}
</script>
</html>