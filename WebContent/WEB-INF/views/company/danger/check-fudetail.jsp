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
    <link rel="stylesheet" type="text/css" media="print"  href="${ly}/js/H-ui.admin_v3.0/static/h-ui/css/H-ui.css" />
    <link rel="stylesheet" type="text/css" href="${ly}/styles/common.css" media="print"  />
<style type="text/css">
.input-text{position:relative}
.txtarea_sq{height:100px;width:350px;}
body .check-box, .radio-box{line-height:30px;}
.div_pdetail{width:100%;font-size:15px;}
.div_pdetail font{float:left;font-size:15px;margin-left:15px;font-weight:bold;}
.div_pdetail label{float:right;font-size:15px;margin-right:15px;}
.div_imgp{float:left;width:90%;margin-left:5%;text-align: center;}
.div_imgp img{max-width:100%}
.div_pleft{width:150px;display: inline-block;line-height:32px;font-size:14px;}
.div_pright{width:300px;display: inline-block;}
.div_pri{width:100%;min-height: 32px}
.div_pri font{float:right;line-height:32px;font-size:14px;margin-right:15px;}
.div_pri .Wdate{float:right;}
body .select{height:32px;line-height:32px;}
body .radio-box{float:Left;}
    .center{
       text-align:center!important;
    }
</style>
<script type="text/javascript">
    console.log("c/check-fudetail");
    var a='${itemList}';
    var result = JSON.stringify(a);
    console.log(result);
function showpicture(src, obj){
  var memo = $(obj).closest("td").find("div[data-name='item-memo']").html();
  $(".modal-body .mt-10").html("隐患描述：" + (memo == '' ? '暂无描述' : memo));
  $(".div_imgp").html('');
  $.each(src.split(","), function(i, item) {
    $(".div_imgp").append('<div class="div_imgp">' + 
        '<img src="'+item+'" class="img-responsive mt-20" >' +
        '</div>');
  })
  $("#modal-plan").modal("show")}
  
function showpicture2(src, obj){
  var memo = $(obj).closest("td").find("div[data-name='item-memo2']").html();
  $(".modal-body .mt-10").html("复查描述：" + (memo == '' ? '暂无描述' : memo));
  $(".div_imgp").html('');
  $.each(src.split(","), function(i, item) {
    $(".div_imgp").append('<div class="div_imgp">' + 
        '<img src="'+item+'" class="img-responsive mt-20" >' +
        '</div>');
  })
  $("#modal-plan").modal("show")}
</script>
</head>
<body>
    <script src="/js/jquery.jqprint-0.3.js"></script>
    <script>

    function pr_() {
    $('.bit_hid').css('display','none')
    $(".page-container").jqprint();
    setTimeout(function () {
    $('.bit_hid').css('display','inline-block')
    },2000)

    }
    </script>
<div class="page-container">
  <div class="div_pdetail" style="margin-bottom:30px;margin-top:15px;">
    <c:choose>
    <c:when test="${check.flag == 1}">
        <button onClick="pr_()" class="btn btn-primary radius bit_hid" type="button">
        <i class="Hui-iconfont">&#xe652;</i>打印预览
        </button>
      <%--<button onClick="javascript:show_dialog('检查表打印', '/company/print-recheck-detail?checkId=${check.id}')" class="btn btn-success radius" type="button" style="padding: 0 60px;margin-right: 20px">--%>
        <%--<i class="Hui-iconfont mr-10">&#xe652;</i>打印预览--%>
      <%--</button>--%>
    </c:when>
    <c:otherwise>
        <button onClick="pr_()" class="btn btn-primary radius bit_hid" type="button">
        <i class="Hui-iconfont">&#xe652;</i>打印预览
        </button>
      <%--<button onClick="javascript:show_dialog('复查意见书', '/village/check-document?checkId=${check.id}&flag=2')" class="btn btn-success radius" type="button" style="padding: 0 60px;margin-right: 20px">--%>
        <%--<i class="Hui-iconfont mr-10">&#xe652;</i><!-- 打印文书 -->打印预览--%>
      <%--</button>--%>
    </c:otherwise>
    </c:choose>
    <a class="btn btn-success radius r bit_hid" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:history.back(-1)" title="返回">返回</a>

    <%-- <button onClick="top.show_tab('排查治理记录', '${ly }/village/check-list?flag=${flag }')" class="btn btn-success radius" type="button" style="padding: 0 15px;margin-right: 20px">
       <i class="Hui-iconfont mr-10">&#xe652;</i>返回排查治理记录列表
   </button> --%>
<%--    <button onClick="location.href = '/company/check-detail?flag=${flag}&id=${check.id }&number=${number}'"--%>
<%--              class="btn btn-success radius" type="button" style="padding: 0 60px;margin-right: 20px">检查详情</button>--%>
<%--      <!-- 判断是否有治理意见表 -->--%>
<%--      <c:if test="${check.c > 0 && check.status == 2 }">--%>
<%--       --%>
<%--        <!-- 已检查过且有隐患的需要治理意见表 -->--%>
<%--        <c:choose>--%>
<%--          <c:when test="${check.t > 0}">--%>
<%--            <!-- 已设置整改意见 -->--%>
<%--            <button onClick="location.href = '/village/check-rectification?flag=${flag }&id=${check.id }'"--%>
<%--              class="btn btn-success radius" type="button" style="padding: 0 60px;">整改详情</button>--%>
<%--          </c:when>--%>
<%--        </c:choose>--%>
<%--      </c:if>--%>
  </div>
  <h2 class="text-c mb-10">隐患整改复查意见书</h2>
  <!-- 循环 -->
  <c:forEach items="${recheckList }" var="p">
  <div class="f-l div_pdetail mt-20">

    <font>
      <c:if test="${flag==1}">
        受检部门:  ${check.depart}
      </c:if>
    <c:if test="${flag!=1}">
      受检单位:  ${company.name}
    </c:if>

    </font>

    <label>检查日期：<fmt:formatDate value="${check.realTime }" pattern="yyyy年MM月dd日"/></label>
  </div>
  <div class="f-l mt-20 mb-10" style="width:100%">
    <table class="f-l table table-border table-bordered table-bg table-hover table-sort">
      <thead>
        <tr class="text-c">
<%--          <th width="3%">序号</th>--%>
<%--          <th width="15%">部门/装置</th>--%>
<%--          <th width="25%">检查项目</th>--%>
<%--          <th width="30%">检查内容</th>--%>
<%--          <th width="15%">整改期限</th>--%>
<%--          <th width="12%">检查结果</th>--%>

      <th width="6%">序号</th>
      <th width="10%">检查部位</th>
      <th width="10%">检查类型</th>
      <th width="10%">检查方式</th>
      <th width="10%">检查项目</th>
      <th width="10%">检查结果</th>
      <th width="20%">隐患内容</th>
      <th width="15%">隐患图片</th>
        </tr>
      </thead>
      <tbody>
        <c:set value="0" var="ind"/>
        <c:forEach items="${itemList }" var="be">
        <c:if test="${be.recheckId == p.id}">
        <c:set value="${ind + 1}" var="ind"/>
        <tr>
<%--           1.序号 --%>
          <td class="text-c">${ind }</td>
<%--            2.检查部位--%>
          <td class="text-c"><c:if test="${!empty be.partImg}">
            <img src="${be.partImg }" class="mr-10" style="height:100px;"/>
            </c:if>
            ${be.partName }</td>

            <%--3检查类型--%>
            <td class=" center">
            <c:if test="${check.industryType==1}">基础检查</c:if>
            <c:if test="${check.industryType==2}">现场检查</c:if>
            <c:if test="${check.industryType==3}">高危检查</c:if>
            </td>


            <%--4检查方式--%>
            <td class=" center">
            <c:if test="${check.type==1}">日常检查</c:if>
            <c:if test="${check.type==2}">定期检查</c:if>
            <c:if test="${check.type==3}">季节检查</c:if>
            <c:if test="${check.type==4}">其他检查</c:if>
            <c:if test="${check.type==5}">综合检查</c:if>
            </td>
		 <c:set var="item" value="${fn:split(be.levels,'/') }"/>
<%--     5.检查项目--%>
		<td>${item[2]}</td>
<%--     6检查结果--%>
            <td class="text-c">
            ${be.status == 2 ? '不合格' : '合格'}
            <c:if test="${!empty be.files or !empty be.memo}">

                <br>
                <c:if test="${be.status != 3}"><a href="javascript:void(0)" onclick="showpicture('${be.files}', this)">隐患描述</a></c:if>
                <div data-name="item-memo" style="display: none;">${be.memo }</div>
            </c:if>
            <c:if test="${!empty be.recheckFile or !empty be.recheckMemo}">
                <br><a href="javascript:void(0)" onclick="showpicture2('${be.recheckFile}', this)">复查描述</a>
                <div data-name="item-memo2" style="display: none;">${be.recheckMemo }</div>
            </c:if>

            <%--     7隐患内容--%>
          <td class="text-c">${be.memo }</td>

            <%--8-隐患图片--%>
            <td class="text-c">
            <c:if test="${!empty be.files or !empty be.memo}">

                <a href="javascript:void(0)" onclick="showpicture('${be.files}', this)">
                <img src="${be.recheckFile}" align="隐患图片" width="100" height="100">
                </a>
                <%-- <img alt="" src="${be.files }" style="max-height:100px;cursor:pointer;" onClick="showpicture('${be.files}')"/> --%>
            </c:if>
            <div data-lid="${be.levelId }" data-name="item-memo" style="display: none;">${be.memo }</div>
            </td>


<%--          <td class="text-c"><fmt:formatDate value="${be.deadline }"/></td>--%>




          </td>
        </tr>
        </c:if>
        </c:forEach>
        <tr>
          <td colspan="6">
            <div class="div_pri  mt-10">针对上次隐患整改要求，经复查，提出如下复查意见：</div>
            <div class="div_pri  mt-10">1、已整改项：基本符合安全要求</div>
            <div class="div_pri">
             <!-- 循环 -->
             <c:set value="0" var="ind"/>
             <c:forEach items="${itemList }" var="be">
              <c:if test="${be.recheckId == p.id}">
              <c:set value="${ind + 1}" var="ind"/>
              <div class="check-box">
                <input type="checkbox" name="isli" data-i="" disabled="disabled" <c:if test="${be.status == 3}"> checked</c:if>/>
                <label>${ind }</label>
              </div>
              </c:if>
             </c:forEach>
              <!-- 循环结束 -->
            </div>
            <div class="div_pri  mt-10">2、未整改项：</div>
            <div class="div_pri">
             <!-- 循环 -->
              <c:set value="0" var="ind"/>
             <c:forEach items="${itemList }" var="be">
              <c:if test="${be.recheckId == p.id}">
              <c:set value="${ind + 1}" var="ind"/>
              <div class="check-box">
                <input type="checkbox" name="isli" data-i="" disabled="disabled" <c:if test="${be.status == 2}"> checked</c:if>/>
                <label>${ind }</label>
              </div>
              </c:if>
             </c:forEach>
              <!-- 循环结束 -->
            </div>
            <div class="div_pri  mb-10  mt-10">
              <font style="float:Left;">3、对未整改项要求于</font>
              <input type="text" id="planTime" value="<fmt:formatDate value="${p.nextTime }" pattern="yyyy-MM-dd"/>" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})" class="input-text Wdate" style="float:left;width:250px;">
              <font style="float:Left;">整改完毕</font>
            </div>
          </td>
        </tr>
        <tr>
          <td colspan="3">
            <div style="float:left;width:100%;position: relative;min-height:150px;">
            <c:if test="${check.flag == 3 && check.checkCompany eq '无锡市安泰安全技术服务有限公司'}">
            <img alt="" src="${ly }/images/zhang.png" style="position: absolute;bottom:10px;right:10px;height:130px;"/>
            </c:if>
            <c:if test="${check.flag > 1}">
            <div class="div_pleft  mt-10 mb-10">检查部门/单位：</div>
            <div class="div_pright  mt-10 mb-10">
              <input type="text" style="width:150px" id="checkCompany" value="${check.checkCompany }" class="input-text" maxlength="50" disabled="disabled"/>
            </div><br/>
            </c:if>
            <div class="div_pleft  mt-10 mb-10">检查人员签字：</div>
            <div class="div_pright  mt-10 mb-10">
              <input type="text" style="width:150px" value="${p.checker }" class="input-text" maxlength="50" disabled="disabled"/>
            </div>
            <div class="div_pri mt-10">
              <font><fmt:formatDate value="${p.createTime }" pattern="yyyy年MM月dd日"/></font>
            </div>
            </div>
          </td>
          <td colspan="3">
            <div style="float:left;width:100%;min-height:150px;">
            <div class="div_pleft  mt-10 mb-10">受检负责人签字：</div>
            <div class="div_pright  mt-10 mb-10">
              <input type="text" style="width:150px" value="${check.dapartContact }" class="input-text" maxlength="50" disabled="disabled"/>
            </div>
            <div class="div_pri mt-10">
              <font><fmt:formatDate value="${p.createTime }" pattern="yyyy年MM月dd日"/></font>
            </div>
            </div>
          </td>
        </tr>

      </tbody>
    </table>
  </div>
  </c:forEach>
  <!-- 循环结束 -->
  
</div>

  <!-- 弹窗图片 -->
  <div id="modal-plan" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
          <div class="modal-content radius">
              <div class="modal-header">
                  <h3 class="modal-title">查看详情</h3>
                  <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
              </div>
              <div class="modal-body" style="height:400px;overflow-y:auto">
                <p class="mt-10">隐患描述：xxx</p>
                <!-- 循环图片 -->
                <div class="div_imgp">
                  <img src="${ly }/images/zwtp.jpg" class="img-responsive mt-20" >
                </div>
                <!-- 循环结束 -->
              </div>
          </div>
      </div>
  </div>
</body>
</html>