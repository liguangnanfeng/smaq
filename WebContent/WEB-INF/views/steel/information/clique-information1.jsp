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
<%@ include file="/WEB-INF/inc/print.inc"%>
<script src="${ly }/js/jquery-migrate-1.2.1.min.js"></script>
<script src="${ly }/js/jquery.jqprint-0.3.js"></script>
<script type="text/javascript" src="${ly}/js/H-ui.admin_v3.0/lib/layer/2.4/layer.js"></script> 
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style type="text/css">
.ter1{width:15%}
.tab_tmr1{width:35%}
.ter2{width:10%}
.tab_tmr2{width:15%}
.print-bt{display:none;}
</style>
<script type="text/javascript">
function pr_() {
  $(".print-bt").show();
  $("#container").jqprint();
  $(".print-bt").hide();
}
</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>企业基础信息</span> 
    <span class="c-gray en">&gt;</span> <span>基本信息</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l">
        <a class="btn btn-primary radius" href="javascript:show_dialog('修改基本信息','${ly }/steel/clique/basic-information-change')"<%-- data-title="修改基本信息" data-href="${ly }/company/basic-information-change" onclick="Hui_admin_tab(this)" href="javascript:;" --%>><i class="Hui-iconfont" style="font-size:15px;">&#xe6df;</i> 修改基本信息</a>
        <a class="btn btn-success radius ml-10" onclick="pr_()"><i class="Hui-iconfont" style="font-size:15px;">&#xe652;</i> 打印</a>
      </span>
    </div>
    <div class="mt-20" id="container">
       <h3 class="text-c print-bt" style="width:100%;margin:0px;margin-bottom:20px;float:Left;padding:0;">${c.name}公司企业概况</h3>
       <table class="table table-bg table-border table-bordered radius">
            <thead>
              <tr><th colspan="4" class="text-c">一、基础信息</th></tr>
            </thead>
            <tr>
              <td class="active text-r ter1">企业名称</td>
              <td class="tab_tmr1">${c.name }</td>
              <td class="active text-r ter1">企业类型</td>
              <td class="tab_tmr1">${c.regType }<c:if test="${!empty c.regType2 }">-${c.regType2 }</c:if></td>
            </tr>
            <tr>
              <td class="active text-r ter1">企业地址</td>
              <td class="tab_tmr1" colspan="3">${regionName}${c.address }</td>
            </tr>
            <tr>
              <td class="active text-r ter1">法定代表人</td>
              <td class="tab_tmr1">${c.legal }</td>
              <td class="active text-r ter1">法定代表人手机</td>
              <td class="tab_tmr1">${c.legalContact }</td>
            </tr>
            <tr>
              <td class="active text-r ter1">注册资本</td>
              <td class="tab_tmr1">${c.capital }</td>
              <td class="active text-r ter1">成立时间</td>
              <td class="tab_tmr1">${c.establish }</td>
            </tr>
            <tr>
              <td class="active text-r ter1">营业期限</td>
              <td class="tab_tmr1">${c.business }</td>
              <td class="active text-r ter1">经营范围</td>
              <td class="tab_tmr1">${c.scope }</td>
            </tr>
            <tr>
              <td class="active text-r ter1">统一社会信用代码</td>
              <td class="tab_tmr1">${c.license }</td>
              <td class="active text-r ter1">登记机关</td>
              <td class="tab_tmr1">${c.authority }</td>
            </tr>
            <tr>
              <td class="active text-r ter1">所属行业</td>
              <td class="tab_tmr1">${c.industry2 }</td>
              <td class="active text-r ter1">固定资产</td>
              <td class="tab_tmr1">${c.fixed }</td>
            </tr>
          </table>
          <table class="table table-bg table-border table-bordered radius mt-20">
            <thead>
              <tr><th colspan="4" class="text-c">二、管理信息</th></tr>
            </thead>
            <tr>
              <td class="active text-r ter1">员工人数</td>
              <td class="tab_tmr1">${c.staff }</td>
              <td class="active text-r ter1">上年销售收入</td>
              <td class="tab_tmr1">${c.income }</td>
            </tr>
            <tr>
              <td class="active text-r ter1">监管行业</td>
              <td class="tab_tmr1">${c.industry}</td>
              <td class="active text-r ter1">规模类型</td>
              <td class="tab_tmr1">${c.scale}</td>
            </tr>
            <tr>
              <td class="active text-r ter1">风险分级</td>
              <td class="tab_tmr1">${c.dlevel }</td>
              <td class="active text-r ter1">高危作业</td>
              <td class="tab_tmr1">
                <c:if test="${c.danger == 1 }">${c.dangers }</c:if>
                <c:if test="${c.danger == 0 }">否</c:if>
              </td>
            </tr>
            <tr>
              <td class="active text-r ter1">是否构成重大危险源</td>
              <td class="tab_tmr1" >
                <c:choose>
                  <c:when test="${c.hazard == 0}">
                    <select id="hazard">
                      <option value="1">是</option>
                      <option value="0" selected="selected">否</option>
                    </select>
                  </c:when>
                  <c:when test="${c.hazard == 1}">
                    <select id="hazard">
                      <option value="1" selected="selected">是</option>
                      <option value="0">否</option>
                    </select>
                  </c:when>
                </c:choose>
              </td>
              <td class="active text-r ter1">企业值班电话</td>
              <td class="tab_tmr1">${c.duty }</td>
            </tr>
            <tr>
              <td class="active text-r ter1">企业邮政编码</td>
              <td class="tab_tmr1">${c.postage }</td>
              <td class="active text-r ter1">企业邮箱</td>
              <td class="tab_tmr1">${c.email }</td>
            </tr>
            <tr>
              <td class="active text-r ter1">主要负责人</td>
              <td class="tab_tmr1">${c.charge }</td>
              <td class="active text-r ter1">主要负责人联系手机</td>
              <td class="tab_tmr1">${c.chargeContact }</td>
            </tr>
            <tr>
              <td class="active text-r ter1">专兼职安全员</td>
              <td class="tab_tmr1">${c.safety }</td>
              <td class="active text-r ter1">专兼职安全员联系手机</td>
              <td class="tab_tmr1">${c.safetyContact }</td>
            </tr>
            <tr>
              <td class="active text-r ter1">安全管理部门负责人</td>
              <td class="tab_tmr1">${c.safetyM }</td>
              <td class="active text-r ter1">安全管理部门负责人手机</td>
              <td class="tab_tmr1">${c.safetyMContact }</td>
            </tr>
          </table>
      <%-- <table class="table table-border table-bg table-bordered radius mt-20">
        <thead>
          <tr><th colspan="8" class="text-c">规章制度</th></tr>
        </thead>
        <tr>
          <td class="active text-r ter2">管理制度</td>
          <td class="tab_tmr2">
          <c:if test="${r.managementSystem==0 }">无</c:if>
          <c:if test="${r.managementSystem==1 }">有</c:if>
          </td>
          <td class="active text-r ter2">是否在有效期内</td>
          <td class="tab_tmr2">
          <c:if test="${r.misInforce==0 }">否</c:if>
          <c:if test="${r.misInforce==1 }">是</c:if>
          </td>
          <td class="active text-r ter2">操作规程</td>
          <td class="tab_tmr2">
          <c:if test="${r.regulations==0 }">无</c:if>
          <c:if test="${r.regulations==1 }">有</c:if>
          </td>
          <td class="active text-r ter2">是否在有效期内</td>
          <td class="tab_tmr2">
          <c:if test="${r.risInforce==0 }">否</c:if>
          <c:if test="${r.risInforce==1 }">是</c:if>
          </td>
        </tr>
        <tr>
          <td class="active text-r ter2">安全生产责任制</td>
          <td class="tab_tmr2">
          <c:if test="${r.safety==0 }">无</c:if>
          <c:if test="${r.safety==1 }">有</c:if>
          </td>
          <td class="active text-r ter2">是否在有效期内</td>
          <td class="tab_tmr2">
          <c:if test="${r.sisInforce==0 }">否</c:if>
          <c:if test="${r.sisInforce==1 }">是</c:if>
          </td>
          <td class="active text-r ter2">责任制落实情况</td>
          <td class="tab_tmr2" colspan="3">
          <c:if test="${r.implementation==0 }">否</c:if>
          <c:if test="${r.implementation==1 }">是</c:if>
          </td>
        </tr>
        <tr>
          <td class="active text-r ter2">应急预案</td>
          <td class="tab_tmr2">
          <c:if test="${r.contingency==0 }">无</c:if>
          <c:if test="${r.contingency==1 }">有</c:if>
          </td>
          <td class="active text-r ter2">是否在有效期内</td>
          <td class="tab_tmr2" colspan="5">
          <c:if test="${r.cisInforce==0 }">否</c:if>
          <c:if test="${r.cisInforce==1 }">是</c:if>
          </td>
        </tr>
      </table> --%>
      <%-- <table class="table table-border table-bg table-bordered radius" style="float:left;width:48%;margin-top:15px;">
        <thead>
          <tr><th colspan="4" class="text-c">主要负责人：${company.charge }</th></tr>
        </thead>
        <tr>
          <td class="active text-r ter1">电话</td>
          <td class="tab_tmr1">${company.chargePhone }</td>
          <td class="active text-r ter1">手机</td>
          <td class="tab_tmr1">${company.chargeMobile }</td>
        </tr>
        <tr>
          <td class="active text-r ter1">传真</td>
          <td class="tab_tmr1">${company.chargeFax}</td>
          <td class="active text-r ter1">电子信箱</td>
          <td class="tab_tmr1">${company.chargeEmail}</td>
        </tr>
      </table>
      <table class="table table-border table-bg table-bordered radius" style="float:right;width:48%;margin-top:15px;">
        <thead>
          <tr><th colspan="4" class="text-c">联系人：${company.contact}</th></tr>
        </thead>
        <tr>
          <td class="active text-r ter1">电话</td>
          <td class="tab_tmr1">${company.contactPhone}</td>
          <td class="active text-r ter1">手机</td>
          <td class="tab_tmr1">${company.contactMobile}</td>
        </tr>
        <tr>
          <td class="active text-r ter1">传真</td>
          <td class="tab_tmr1">${company.contactFax}</td>
          <td class="active text-r ter1">电子信箱</td>
          <td class="tab_tmr1">${company.contactEmail}</td>
        </tr>
      </table>
      <table class="table table-border table-bg table-bordered radius" style="float:left;width:48%;margin-top:15px;">
        <thead>
          <tr><th class="text-c">营业执照：${company.license}</th></tr>
        </thead>
        <tr>
          <td class="text-c">
            <img style="height:180px;width:150px" alt="" src="${company.licenseImg}">
          </td>
        </tr>
      </table>
      <table class="table table-border table-bg table-bordered radius" style="float:right;width:48%;margin-top:15px;">
        <thead>
          <tr><th class="text-c">组织机构图</th></tr>
        </thead>
        <tr>
          <td class="text-c">
            <img style="height:180px;width:150px" alt="" src="${company.organization}">
          </td>
        </tr>
      </table> --%>
    </div>
  </div>
<script>
  $(function () {
    $("#hazard").change(function () {
      var value = $(this).val();
      $.ajax({
        "url":"${ly}/steel/updateHazard?hazard="+value,
        "success":function(){
          alert("修改成功");
        },
        "error":function(){
          alert("发生错误");
        }
      })
      if(value==0){

      }else{
        window.location.href="${ly}/company/information/information-edit3";
      }
    })
  })
</script>
</body>
</html>