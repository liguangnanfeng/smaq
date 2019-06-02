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
.Huifold {float:left;width:80%;margin-left:5%;margin-bottom:80px;}
.Huifold .item{ position:relative}
.Huifold .item h4{float:left;width:100%;font-weight:bold;box-sizing: border-box;position:relative;border-top: 1px solid #fff;font-size:15px;line-height:22px;padding:7px 10px;background-color:#eee;cursor:pointer;padding-right:30px}
.Huifold .item h4 b{position:absolute;display: block; cursor:pointer;right:10px;top:7px;width:16px;height:16px; te xt-align:center; color:#666}
.Huifold .item .info{float:left;width:100%;display:none;padding:10px;min-height:100px;box-sizing: border-box;}
.Huifold .item .selected{background:#2472D4;color:#fff}
.Huifold .item .selected b{color:#fff}
.ter1{width:15%}
.tab_tmr1{width:35%}
.ter2{width:10%}
.tab_tmr2{width:15%}
.clearfix:after{content:"\20";display:block;height:0;clear:both;visibility:hidden}
.clearfix{zoom:1}
.tabBar {border-bottom: 2px solid #D9EDF7}
.tabBar span {background-color: #e8e8e8;cursor: pointer;display: inline-block;float: left;font-weight: bold;height: 30px;line-height: 30px;padding: 0 15px}
.tabBar span.current{background-color: #D9EDF7;color: #31708f;border:0px;}
.tabCon {display: none;border:0px;}
.detial_info{float:left;width:100%;word-wrap:break-word;word-break:break-all;white-space:normal;overflow:hidden;}
.btnyh{color: #333;background: #e6e6e6;}
.btnyhxz{color: #fff;background: #5a98de;}
.slideTxtBox .hd li{line-height: 22px;}
.tabBar div{background-color: #e8e8e8;cursor: pointer;display: inline-block;float: left;font-weight: bold;height: 30px;line-height: 30px;padding: 0 15px;}
</style>
<script type="text/javascript">
$(function(){
  $(".Huifold .item .selected").next().show();
  $.Huifold("#Huifold1 .item h4","#Huifold1 .item .info","fast",3,"click"); 
  $.Huitab("#tab_list0 .tabBar span","#tab_list0 .tabCon","current","click","0");
  $.Huitab("#tab_list1 .tabBar span","#tab_list1 .tabCon","current","click","0");
  $.Huitab("#tab_list2 .tabBar span","#tab_list2 .tabCon","current","click","0");
  $.Huitab("#tab_list3 .tabBar span","#tab_list3 .tabCon","current","click","0");
  $.Huitab("#tab_list4 .tabBar span","#tab_list4 .tabCon","current","click","0");
  $(".btn_per").click(function(){
    $(".btn_per").removeClass("btnyhxz");
    $(this).addClass("btnyhxz");
  });
  $(".btn_jian").click(function(){
    $(".btn_jian").removeClass("btnyhxz");
    $(this).addClass("btnyhxz");
  });
  
  
});
</script>
</head>
<body>
  <div class="page-container">
    <ul id="Huifold1" class="Huifold">
      <li class="item">
        <h4 class="selected">企业基础信息<b>-</b></h4>
        <div class="info">
          <div id="tab_list0" class="HuiTab">
            <div class="tabBar clearfix">
                <span>基本信息</span><span>主要原辅材料</span><span>主要生产产品</span><span>生产工艺流程图</span><div onclick="parent.show_dialog('${c.name }_地图定位', '/company/information/information4?userId=${c.userId }')">企业地图</div>
            </div>
            <div class="tabCon">
              <table class="table table-bg table-border table-bordered radius">
                <thead>
                  <tr><th colspan="8" class="text-c">基础信息</th></tr>
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
                  <td class="tab_tmr1"colspan="3">${c.fixed }</td>
                </tr>
              </table>
              <table class="table table-bg table-border table-bordered radius  mt-20">
                <thead>
                  <tr><th colspan="8" class="text-c">管理信息</th></tr>
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
                        <c:when test="${c.hazard == 0}">否</c:when>
                        <c:when test="${c.hazard == 1}">是</c:when>
                    </c:choose>
                  </td>
                  <td class="active text-r ter1">企业值班电话</td>
                  <td class="tab_tmr1">${c.duty }</td>
                  <%-- <td class="active text-r ter1">重大危险源信息</td>
                  <td class="tab_tmr1">${c.dangers }</td> --%>
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
            </div>
            <!-- 主要原辅材料 -->
            <div class="tabCon">
              <table class="table table-border table-bordered table-bg table-hover table-sort">
                <thead>
                  <tr class="text-c">
                    <th width="5%">序号</th>
                    <th width="15%">原辅材料名称</th>
                    <th width="5%">物态</th>
                    <th width="10%">年用量</th>
                    <th width="15%">最大储存量</th>
                    <th width="15%">储存地点</th>
                    <th width="15%">储存方式</th>
                    <th width="20%">备注</th>
                  </tr>
                </thead>
                <tbody>
                  <!-- 循环-->
                  <c:forEach items="${malist }" varStatus="index" var="ma">
                  <tr class="text-c">
                    <td>${index.index + 1 }</td>
                    <td>${ma.material }</td>
                    <td>${ma.state }</td>
                    <td>${ma.annualConsumption }</td>
                    <td>${ma.maximumStorage }</td>
                    <td>${ma.storageLocation }</td>
                    <td>${ma.storageMethod }</td>
                    <td>${ma.remark }</td>
                  </tr>
                  </c:forEach>
                  <!-- 循环结束 -->
                </tbody>
              </table>
            </div>
            <!-- 主要产品 -->
            <div class="tabCon">
              <table class="table table-border table-bordered table-bg table-hover table-sort">
                <thead>
                  <tr class="text-c">
                    <th width="5%">序号</th>
                    <th width="20%">产品名称</th>
                    <th width="15%">生产能力</th>
                    <th width="20%">上年度产量</th>
                    <th width="15%">储存地点</th>
                    <th width="25%">备注</th>
                  </tr>
                </thead>
                <tbody>
                  <!-- 循环-->
                  <c:set value="${fn:split('无/有','/') }" var="x"/>
                  <c:forEach items="${prlist }" varStatus="index" var="pr">
                  <tr class="text-c">
                    <td>${index.index + 1 }</td>
                    <td>${pr.productName }</td>
                    <td>${pr.efficiency }</td>
                    <td>${pr.lastyearProduction }</td>
                    <td>${pr.storageLocation }</td>
                    <td>${pr.remark }</td>
                  </tr>
                  </c:forEach>
                  <!-- 循环结束 -->
                </tbody>
              </table>
            </div>
            <div class="tabCon">
              <table class="table table-border table-bordered table-bg table-hover table-sort">
                <thead>
                  <tr class="text-c">
                    <th width="5%" rowspan="2">序号</th>
                    <th width="15%" rowspan="2">项目</th>
                    <th width="15%" rowspan="2">制程描述</th>
                    <th width="20%" colspan="2">材料说明</th>
                    <th width="15%" rowspan="2">人员要求</th>
                    <th width="30%" colspan="3">控制说明</th>
                  </tr>
                  <tr class="text-c">
                    <th width="10%">材料</th>
                    <th width="10%">材料规格</th>
                    <th width="10%">检查标准</th>
                    <th width="10%">操作规定</th>
                    <th width="10%">设备/工具</th>
                  </tr>
                </thead>
                <tbody>
                  <!-- 循环开始 -->
				  <c:forEach items="${productionProcessDiagram }" varStatus="index" var="list">
		          <tr class="text-c">
		            <td>${index.index+1 }</td>
		            <td>${list.project }</td>
		            <td>${list.description }</td>
		            <td>${list.material }</td>
		            <td>${list.materialSpecifications }</td>
		            <td>${list.personnelRequirements }</td>
		            <td>${list.inspectionStandards }</td>
		            <td>${list.operatingRules }</td>
		            <td>${list.equipment }</td>
		          </tr>
		          </c:forEach>
				  <!-- 循环结束 -->
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </li>
      <li class="item">
        <h4>安全生产标准化<b>+</b></h4>
        <div class="info">
          <div id="tab_list1" class="HuiTab">
            <div class="tabBar clearfix"><span>管理制度</span><span>责任制</span><span>持证上岗</span><span>主要设备清单</span><span>特种设备检测</span><span>两重点一重大</span></div>
             <!-- 管理网络图 -->
            <div class="tabCon">
              <table class="table table-border table-bordered table-bg table-hover table-sort">
                <thead>
                  <tr class="text-c">
                    <th width="5%">序号</th>
                    <th width="55%">资料名称</th>
                    <th width="20%">上传时间</th>
                    <th width="20%">操作</th>
                  </tr>
                </thead>
                <tbody>
                  <!-- 循环-->
                  <c:forEach items="${glzd_list }" varStatus="index" var="tab">
                    <tr class="text-c">
                      <td>${index.index+1} </td>
                      <td>${tab.name }</td>
                      <td><fmt:formatDate value="${tab.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                      <td>
                        <a style="text-decoration:none" href="${ly}/common/download?filename=${tab.name}&fileurl=${tab.url}" title="查看资料" target="_blank">查看资料</a>
                      </td>
                    </tr>
                    </c:forEach>
                  <!-- 循环结束 -->
                </tbody>
              </table>
            </div>
              <!-- 责任制 -->
            <div class="tabCon">
              <table class="table table-border table-bordered table-bg table-hover">
                <thead>
                  <tr class="text-c">
                    <th width="100%">安全生产管理网络图</th>
                  </tr>
                </thead>
                <tbody>
                  <!-- 循环-->
                    <tr class="text-c">
                      <td><img id="pic1" src="${empty c.pic1 ? '/images/zwtp.jpg' : c.pic1}" url="" style="max-width:80%"/></td>
                    </tr>
                  <!-- 循环结束 -->
                </tbody>
              </table>
              <table class="table table-border table-bordered table-bg table-hover mt-10" >
                <thead>
                  <tr class="text-c">
                    <th width="5%">序号</th>
                    <th width="55%">资料名称</th>
                    <th width="20%">上传时间</th>
                    <th width="20%">操作</th>
                  </tr>
                </thead>
                <tbody>
                  <!-- 循环-->
                  <c:forEach items="${zrz_list }" varStatus="index" var="tab">
                    <tr class="text-c">
                      <td>${index.index+1} </td>
                      <td>${tab.name }</td>
                      <td><fmt:formatDate value="${tab.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                      <td>
                        <a style="text-decoration:none" href="${ly}/common/download?filename=${tab.name}&fileurl=${tab.url}" title="查看资料" target="_blank">查看资料</a>
                      </td>
                    </tr>
                    </c:forEach>
                  <!-- 循环结束 -->
                </tbody>
              </table>
              
            </div>
            
            <!-- 持证上岗 -->
            <div class="tabCon">
	            <div class="slideTxtBox">
				    <div class="hd">
				        <ul class="btn-group mb-20">
				        	<li class="btn btnyh btnyhxz radius btn_per">安全管理人员</li>
				        	<li class="btn btnyh radius btn_per">危险化学品安全管理人员</li>
				        	<li class="btn btnyh radius btn_per">特种作业人员</li>
			        	</ul>
				    </div>
				    <div class="bd">
				        <ul>
				            <li>
				            	<div id="tab-chi">
					                <table class="table table-border table-bordered table-bg table-hover table-sort">
					                  <thead>
					                    <tr class="text-c">
					                      <th width="5%">序号</th>
					                      <th width="15%">姓名</th>
					                      <th width="15%">工种</th>
					                      <th width="15%">证书编号</th>
					                      <th width="20%">取证时间</th>
					                      <th width="20%">下次复审时间</th>
					                      <th width="10%">备注</th>
					                    </tr>
					                  </thead>
					                  <tbody>
					                    <!-- 循环-->
					                    <c:forEach items="${spelist }" varStatus="index" var="spe">
					                    <c:if test="${spe.flag ==1}">
					                      <tr class="text-c">
					                        <td>${index.index+1 }</td>
					                        <td>${spe.name }</td>
					                        <td>${spe.type }</td>
					                        <td>${spe.certificateNumber }</td>
					                        <td>${spe.forensicTime }</td>
					                        <td>${spe.reviewTime }</td>
					                        <td>${spe.remark }</td>
					                      </tr>
				                        </c:if>
					                    </c:forEach>
					                    <!-- 循环结束 -->
					                  </tbody>
					                </table>
				                </div>
				            </li>
				        </ul>
				        <ul>
				            <li>
				            	<div id="tab-chi">
					                <table class="table table-border table-bordered table-bg table-hover table-sort">
					                  <thead>
					                    <tr class="text-c">
					                      <th width="5%">序号</th>
					                      <th width="15%">姓名</th>
					                      <th width="15%">工种</th>
					                      <th width="15%">证书编号</th>
					                      <th width="20%">取证时间</th>
					                      <th width="20%">下次复审时间</th>
					                      <th width="10%">备注</th>
					                    </tr>
					                  </thead>
					                  <tbody>
					                    <!-- 循环-->
					                    <c:forEach items="${spelist }" varStatus="index" var="spe">
					                    <c:if test="${spe.flag ==2}">
					                      <tr class="text-c">
					                        <td>${index.index+1 }</td>
					                        <td>${spe.name }</td>
					                        <td>${spe.type }</td>
					                        <td>${spe.certificateNumber }</td>
					                        <td>${spe.forensicTime }</td>
					                        <td>${spe.auditTime }</td>
					                        <td>${spe.reviewTime }</td>
					                        <td>${spe.remark }</td>
					                      </tr>
				                        </c:if>
					                    </c:forEach>
					                    <!-- 循环结束 -->
					                  </tbody>
					                </table>
				                </div>
				            </li>
				        </ul>
				        <ul>
				            <li>
				            	<div id="tab-chi">
					                <table class="table table-border table-bordered table-bg table-hover table-sort">
					                  <thead>
					                    <tr class="text-c">
					                      <th width="5%">序号</th>
					                      <th width="15%">姓名</th>
					                      <th width="15%">工种</th>
					                      <th width="15%">证书编号</th>
					                      <th width="20%">取证时间</th>
					                      <th width="20%">下次复审时间</th>
					                      <th width="10%">备注</th>
					                    </tr>
					                  </thead>
					                  <tbody>
					                    <!-- 循环-->
					                    <c:forEach items="${spelist }" varStatus="index" var="spe">
					                    <c:if test="${spe.flag ==3}">
					                      <tr class="text-c">
					                        <td>${index.index+1 }</td>
					                        <td>${spe.name }</td>
					                        <td>${spe.type }</td>
					                        <td>${spe.certificateNumber }</td>
					                        <td>${spe.forensicTime }</td>
					                        <td>${spe.auditTime }</td>
					                        <td>${spe.reviewTime }</td>
					                        <td>${spe.remark }</td>
					                      </tr>
				                        </c:if>
					                    </c:forEach>
					                    <!-- 循环结束 -->
					                  </tbody>
					                </table>
				                </div>
				            </li>
				        </ul>
				    </div>
				</div>
            </div>
            <!-- 主要设备清单 -->
            <div class="tabCon">
              <table class="table table-border table-bordered table-bg table-hover table-sort">
                <thead>
                  <tr class="text-c">
                    <th width="5%">序号</th>
                    <th width="20%">名称</th>
                    <th width="20%">型号规格</th>
                    <th width="20%">工艺参数</th>
                    <th width="15%">数量</th>
                    <th width="20%">备注</th>
                  </tr>
                </thead>
                <tbody>
                  <!-- 循环-->
                  <c:forEach items="${melist }" varStatus="index" var="equip">
                  <tr class="text-c">
                    <td>${index.index + 1 }</td>
                    <td>${equip.equipmentName }</td>
                    <td>${equip.size }</td>
                    <td>${equip.processParameters }</td>
                    <td>${equip.amount }</td>
                    <td>${equip.remark }</td>
                  </tr>
                  </c:forEach>
                  <!-- 循环结束 -->
                </tbody>
              </table>
            </div>
            <!-- 特种设备检测 -->
            <div class="tabCon">
              <table class="table table-border table-bordered table-bg table-hover table-sort">
                <thead>
                  <tr class="text-c">
                    <th width="5%">序号</th>
                    <th width="15%">特种设备名称</th>
                    <th width="15%">型号</th>
                    <th width="15%">设备档案号</th>
                    <th width="20%">上次检测时间 </th>
                    <th width="15%">到期时间</th>
                    <th width="15%">备注</th>
                  </tr>
                </thead>
                <tbody>
                  <!-- 循环-->
                  <c:forEach items="${seqlist }" varStatus="index" var="seq">
                  <tr class="text-c">
                    <td>${index.index+1 }</td>
                    <td>${seq.seName }</td>
                    <td>${seq.type }</td>
                    <td>${seq.fileNumber }</td>
                    <td>${seq.detectionTime }</td>
                    <td>${seq.expirationTime }</td>
                    <td>${seq.remark }</td>
                  </tr>
                  </c:forEach>
                  <!-- 循环结束 -->
                </tbody>
              </table>
            </div>
            <!-- 两重点一重大 -->
            <div class="tabCon">
              <table class="table table-bg table-border table-bordered radius  mt-20">
                <thead>
                  <tr><th colspan="8" class="text-c">重点监管危险化学品</th></tr>
                </thead>
                <tr>
                  <td class="active text-r ter2">是否有重点监管危险化学品</td>
                  <td class="tab_tmr2">
                    <c:if test="${r.chemic==0 }">否</c:if>
                    <c:if test="${r.chemic==1 }">是</c:if>
                  </td>
                  <!-- 判断是显示 -->
                  <c:if test="${r.chemic==1 }">
                  <td class="active text-r ter2">是否满足自控要求</td>
                  <td class="tab_tmr2"><c:if test="${r.chemicControl==0 }">否</c:if>
                    <c:if test="${r.chemicControl==1 }">是</c:if></td>
                  </c:if>
                </tr>
                <c:if test="${r.chemic==1 }">
                <tr>
                  <td class="active text-r ter2">物质名称</td>
                  <td class="tab_tmr2" colspan="3">${r.chemicName }</td>
                </tr>
                </c:if>
              </table>
              <table class="table table-bg table-border table-bordered radius  mt-20">
                <thead>
                  <tr><th colspan="8" class="text-c">重点监管危险化工工艺</th></tr>
                </thead>
                <tr>
                  <td class="active text-r ter2">是否有重点监管危险化工工艺</td>
                  <td class="tab_tmr2">
                    <c:if test="${r.process==0 }">否</c:if>
                    <c:if test="${r.process==1 }">是</c:if>
                  </td>
                  <!-- 判断是显示 -->
                  <c:if test="${r.process==1 }">
                  <td class="active text-r ter2">是否满足自控要求</td>
                  <td class="tab_tmr2"><c:if test="${r.processControl==0 }">否</c:if>
                    <c:if test="${r.processControl==1 }">是</c:if></td>
                  </c:if>
                </tr>
                <c:if test="${r.process==1 }">
                <tr>
                  <td class="active text-r ter2">备案日期</td>
                  <td class="tab_tmr2" colspan="3">${r.processRecordDate }</td>
                </tr>
                <tr>
                  <td class="active text-r ter2">工艺名称</td>
                  <td class="tab_tmr2" colspan="3">${r.processName }</td>
                </tr>
                </c:if>
              </table>
              <table class="table table-bg table-border table-bordered radius  mt-20">
                <thead>
                  <tr><th colspan="8" class="text-c">重大危险源</th></tr>
                </thead>
                <tr>
                  <td class="active text-r ter2">是否构成重大危险源</td>
                  <td class="tab_tmr2">
                    <c:if test="${r.cisDanger==0 }">否</c:if>
                    <c:if test="${r.cisDanger==1 }">是</c:if>
                  </td>
                  <!-- 判断是显示 -->
                  <c:if test="${r.cisDanger==1 }">
                  <td class="active text-r ter2">重大危险源等级</td>
                  <td class="tab_tmr2">${r.dangerLvl }</td>
                  </c:if>
                </tr>
                <c:if test="${r.cisDanger==1 }">
                <tr>
                  <td class="active text-r ter2">备案日期</td>
                  <td class="tab_tmr2">${r.dangerRecordDate }</td>
                  <td class="active text-r ter2">有效日期</td>
                  <td class="tab_tmr2">${r.dangerEndDate }</td>
                </tr>
                </c:if>
              </table>
              <table class="table table-border table-bg table-bordered mt-20">
                <thead>
                  <tr class="text-c">
                    <th width="5%">序号</th>
                    <th width="45%">构成重大危险源的主要物质</th>
                    <th width="50%">现场理论最大存量</th>
                  </tr>
                </thead>
                <tbody>
                  <!-- 循环检查项目-->
                  <c:forEach items="${fn:split(r.dangerMaterial, ',') }" var="be" varStatus="index">
                  <tr class="text-c" >
                    <td>${index.index + 1}</td>
                    <td>${fn:split(be, '/')[0] }</td>
                    <td>${fn:split(be, '/')[1] }</td>
                  </tr>
                  </c:forEach>
                  <!-- 循环结束 -->
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </li>
      <li class="item">
        <h4>职业安全卫生管理<b>+</b></h4>
        <div class="info">
          <div id="tab_list2" class="HuiTab">
            <div class="tabBar clearfix"><span>劳动防护用品配备</span><span>职业卫生检测</span><span>职业健康体检</span></div>
            <div class="tabCon">
              <table class="table table-border table-bordered table-bg table-hover table-sort">
                <thead>
                  <tr class="text-c">
                    <th width="5%">编号</th>
                    <th width="15%">劳动用品类型</th>
                    <th width="20%">劳动用品名称</th>
                    <th width="15%">工种</th>
                    <th width="15%">发放日期</th>
                    <th width="15%">下次发放日期</th>
                    <th width="15%">数量</th>
                  </tr>
                </thead>
                <tbody>
                  <c:set var="x" value="${fn:split('一般劳动防护用品/特种劳动防护用品/其他', '/') }"/>
                  <!-- 循环-->
                  <c:forEach items="${peq }" var="be" varStatus="index">
                  <tr>
                    <td>${index.index + 1}</td>
                    <td>${x[be.libId - 1] }</td>
                    <td>${be.name }</td>
                    <td>${be.work }</td>
                    <td>${be.cycle }</td>
                    <td>${be.nextDate }</td>
                    <td>${be.number }</td>
                  </tr>
                  </c:forEach>
                  <!-- 循环结束 -->
                </tbody>
              </table>
            </div>
            <div class="tabCon">
              <table class="table table-border table-bordered table-bg table-hover table-sort">
                <thead>
                  <tr class="text-c">
                    <th width="5%">序号</th>
                    <th>检测类别</th>
                    <th>上次检测时间 </th>
                    <th>到期时间</th>
                    <th>监测点</th>
                    <th>检测职业病危害因素</th>
                    <th>检测结果</th>
                    <th>检测单位</th>
                    <th>检测编号</th>
                    <th>备案情况及备案表</th>
                    <th>备注</th>
                  </tr>
                </thead>
                <tbody>
                  <!-- 循环-->
                  <c:forEach items="${jclist }" varStatus="index" var="de">
                  <tr class="text-c">
                    <td>${index.index+1 }</td>
                    <td>${de.type }</td>
                    <td>${de.reportTime }</td>
                    <td>${de.expirationTime }</td>
                    <td>${de.jcd}</td>
                    <td>${de.whys}</td>
                    <td>${de.jcjg}</td>
                    <td>${de.jcdw}</td>
                    <td>${de.jcbh}</td>
                    <td>${de.ba}</td>
                    <td>${de.remark }</td>
                  </tr>
                  </c:forEach>
                  <!-- 循环结束 -->
                </tbody>
              </table>
            </div>
            <div class="tabCon">
              <table class="table table-border table-bordered table-bg">
                <thead>
                  <tr class="text-c">
                    <th width="5%">序号</th>
                    <th >体检类别</th>
                    <th >体检项目</th>
                    <th >体检类别</th>
                    <th >体检时间</th>
                    <th >体检人数</th>
                    <th >职业病危害因素</th>
                    <th >体检机构</th>
                    <th >到期时间</th>
                    <th >备注</th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach items="${exalist }" varStatus="index" var="exa">
                  <tr class="text-c">
                    <td>${index.index+1 }</td>
                    <td>${exa.type }</td>
                    <td>${exa.project }</td>
                    <td>${exa.time }</td>
                    <td>${exa.healthcheckMember }</td>
                    <td>${exa.whys }</td>
                    <td>${exa.dqsj }</td>
                    <td>${exa.endTime }</td>
                    <td>${exa.remark }</td>
                  </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </li>
      <li class="item">
        <h4>评价评估系统<b>+</b></h4>
        <div class="info">
          <div id="tab_list3" class="HuiTab">
            <div class="tabBar clearfix"><span>安全生产类评价评估</span><span>职业健康类评价评估</span><span>各类许可证</span></div>
            <div class="tabCon">
              <table class="table table-border table-bordered table-bg table-hover table-sort">
                <thead>
                  <tr class="text-c">
                    <th width="5%">序号</th>
                    <th width="15%">评价类型</th>
                    <th width="20%">评价项目名称</th>
                    <th width="15%">编制日期</th>
                    <th width="10%">有效期</th>
                    <th width="10%">编制单位</th>
                    <th width="25%">报告文档</th>
                  </tr>
                </thead>
                <tbody>
                  <!-- 循环-->
                  <c:forEach items="${pjlist }" varStatus="index" var="eva">
                  <c:if test="${eva.flag ==1}">
                  <tr class="text-c">
                    <td>${index.index+1 }</td>
                    <td>
                        <c:if test="${eva.flag==1 && eva.type==1}">预评价</c:if>
                        <c:if test="${eva.flag==1 && eva.type==2}">验收评价</c:if>
                        <c:if test="${eva.flag==1 && eva.type==3}">现状评价</c:if>
                        <c:if test="${eva.flag==1 && eva.type==4}">危化品经营证评价</c:if>
                        <c:if test="${eva.flag==1 && eva.type==5}">危化品生产证评价</c:if>
                        <c:if test="${eva.flag==1 && eva.type==6}">危化品使用证评价</c:if>
                        <c:if test="${eva.flag==1 && eva.type==7}">重大危险源评估</c:if>
                        <c:if test="${eva.flag==1 && eva.type==8}">安全条件论证报告</c:if>
                        <c:if test="${eva.flag==1 && eva.type==9}">安全条件综合分析报告</c:if>
                        <c:if test="${eva.flag==2 && eva.type==1}">预评价</c:if>
                        <c:if test="${eva.flag==2 && eva.type==2}">控效评价</c:if>
                        <c:if test="${eva.flag==2 && eva.type==3}">现状评价</c:if>
                    </td>
                    <td>${eva.reportName }</td>
                    <td>${eva.reportTime }</td>
                    <td>${eva.expirationTime }</td>
                    <td>${eva.unit }</td>
                    <td>
                      ${eva.docName }<br/>
                      <c:if test="${eva.docUrl!=''}">
                        <a style="text-decoration:none" href="${ly}/common/download?filename=${eva.docName}&fileurl=${eva.docUrl}" title="查看文档" target="_blank">查看文档</a>
                      </c:if>
                    </td>
                  </tr>
                  </c:if>
                  </c:forEach>
                  <!-- 循环结束 -->
                </tbody>
              </table>
            </div>
            <div class="tabCon">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
                <thead>
                  <tr class="text-c">
                    <th width="5%">序号</th>
                    <th width="15%">评价类型</th>
                    <th width="20%">评价项目名称</th>
                    <th width="15%">编制日期</th>
                    <th width="10%">有效期</th>
                    <th width="10%">编制单位</th>
                    <th width="25%">报告文档</th>
                  </tr>
                </thead>
                <tbody>
                  <!-- 循环-->
                  <c:forEach items="${pjlist }" varStatus="index" var="eva">
                  <c:if test="${eva.flag ==2}">
                  <tr class="text-c">
                    <td>${index.index+1 }</td>
                    <td>
                        <c:if test="${eva.flag==1 && eva.type==1}">预评价</c:if>
                        <c:if test="${eva.flag==1 && eva.type==2}">验收评价</c:if>
                        <c:if test="${eva.flag==1 && eva.type==3}">现状评价</c:if>
                        <c:if test="${eva.flag==1 && eva.type==4}">危化品经营证评价</c:if>
                        <c:if test="${eva.flag==1 && eva.type==5}">危化品生产证评价</c:if>
                        <c:if test="${eva.flag==1 && eva.type==6}">危化品使用证评价</c:if>
                        <c:if test="${eva.flag==1 && eva.type==7}">重大危险源评估</c:if>
                        <c:if test="${eva.flag==1 && eva.type==8}">安全条件论证报告</c:if>
                        <c:if test="${eva.flag==1 && eva.type==9}">安全条件综合分析报告</c:if>
                        <c:if test="${eva.flag==2 && eva.type==1}">预评价</c:if>
                        <c:if test="${eva.flag==2 && eva.type==2}">控效评价</c:if>
                        <c:if test="${eva.flag==2 && eva.type==3}">现状评价</c:if>
                    </td>
                    <td>${eva.reportName }</td>
                    <td>${eva.reportTime }</td>
                    <td>${eva.expirationTime }</td>
                    <td>${eva.unit }</td>
                    <td>
                      ${eva.docName }<br/>
                      <c:if test="${eva.docUrl!=''}">
                        <a style="text-decoration:none" href="${ly}/common/download?filename=${eva.docName}&fileurl=${eva.docUrl}" title="查看文档" target="_blank">查看文档</a>
                      </c:if>
                    </td>
                  </tr>
                  </c:if>
                  </c:forEach>
                  <!-- 循环结束 -->
                </tbody>
              </table>
            </div>
            <div class="tabCon">
              <table class="table table-border table-bordered table-bg table-hover table-sort">
                <thead>
                  <tr class="text-c">
                    <th width="5%">序号</th>
                    <th width="20%">证书类型</th>
                    <th width="20%">证书编号</th>
                    <th width="20%">证书图片</th>
                    <th width="20%">取证时间</th>
                    <th width="15%">到期时间</th>
                  </tr>
                </thead>
                <tbody>
                  <!-- 循环-->
                 <c:set var="type_" value="${fn:split('危化品经营许可证/危化品生产许可证/危化品使用许可证/危化品登记证/易制毒化学品备案/易制爆化学品备案/标准化二级证书/标准化三级证书/港口经营许可证/成品油零售经营批准证书/小微企业标准化证书', '/') }"/>
                  <c:forEach items="${standard }" varStatus="index" var="list">
                    <tr class="text-c">
                      <td>${list.id}</td>
                      <td>${type_[list.type - 1]}</td>
                      <td>${list.certificateNumber}</td>
                      <td><img alt="${list.img}" src="${list.img}" style="max-height: 100px;" /></td>
                      <td>${list.timeGet}</td>
                      <td>${list.timeExp}</td>
                    </tr>
                  </c:forEach>
                  <!-- 循环结束 -->
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </li>
      <li class="item">
        <h4>消防设备设施<b>+</b></h4>
        <div class="info">
          <table class="table table-border table-bordered table-bg table-hover table-sort">
            <thead>
              <tr class="text-c">
                <th width="5%">序号</th>
                <th width="20%">种类</th>
                <th width="15%">型号</th>
                <th width="15%">配备位置</th>
                <th width="15%">数量</th>
                <th width="15%">有效期</th>
                <th width="15%">备注</th>
              </tr>
            </thead>
            <tbody>
            <c:forEach items="${faclist }" varStatus="index" var="fac">
              <c:if test="${fac.flag == 1}">
              <tr class="text-c">
                <td>${index.index+1 }</td>
                <td>${fac.type }</td>
                <td>${fac.model }</td>
                <td>${fac.equippedPosition }</td>
                <td>${fac.amount }</td>
                <td>${fac.expiryDate }</td>
                <td>${fac.remark }</td>
              </tr>
              </c:if>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </li>
      <li class="item">
        <h4>应急设备设施<b>+</b></h4>
        <div class="info">
          <table class="table table-border table-bordered table-bg table-hover table-sort">
            <thead>
              <tr class="text-c">
                <th width="5%">序号</th>
                <th width="20%">种类</th>
                <th width="15%">型号</th>
                <th width="15%">配备位置</th>
                <th width="15%">数量</th>
                <th width="15%">有效期</th>
                <th width="15%">备注</th>
              </tr>
            </thead>
            <tbody>
            <c:forEach items="${faclist }" varStatus="index" var="fac">
              <c:if test="${fac.flag == 2}">
              <tr class="text-c">
                <td>${index.index+1 }</td>
                <td>${fac.type }</td>
                <td>${fac.model }</td>
                <td>${fac.equippedPosition }</td>
                <td>${fac.amount }</td>
                <td>${fac.expiryDate }</td>
                <td>${fac.remark }</td>
              </tr>
              </c:if>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </li>
     <li class="item">
        <h4>隐患汇总信息<b>+</b></h4>
        <div class="info">
          <div id="tab_list4" class="HuiTab">
            <div class="tabBar clearfix"><span>企业自查</span><span>部门抽查</span><span>行政检查</span></div>
            <div class="tabCon">
              <table class="table table-border table-bordered table-bg table-hover table-sort">
		        <thead>
		          <tr class="text-c">
		            <th width="5%">编号</th>
		            <th width="10%">检查部位</th>
		            <th width="10%">检查项目</th>
		            <th width="20%">检查内容</th>
		            <th width="10%">检查结果</th>
		            <th width="10%">受检${flag == 1 ? '部门' : '单位'}</th>
		            <th width="15%">隐患描述</th>
		            <th width="20%">隐患图片</th>
		          </tr>
		        </thead>
		        <tbody>
		          <!-- 循环开始 -->
				  <c:forEach items="${danger1 }" varStatus="index" var="list">
				  <c:if test="${list.flag ==1}">
				    <tr class="text-c">
					<td>${index.index + 1}</td>
		            <td>${list.partName }</td>
		            <td>${list.levels }</td>
		            <td>${list.content }</td>
		            <td>
		            	<c:if test="${list.status == '1'}">合格</c:if>
		            	<c:if test="${list.status == '2'}">未整改</c:if>
		            	<c:if test="${list.status == '3'}">已整改</c:if>
		           	</td>
		            <td>${list.checkCompany }</td>
		            <td>
		              ${list.memo }
		           	</td>
		            <td>
		              <c:if test="${!empty list.files}">
		                <img alt="" src="${list.files }" onclick="showimg('${list.files }')" style="width:200px; height:200px;">
		              </c:if>
		            </td>
		            </tr>
	            </c:if>
				</c:forEach>
				<!-- 循环结束 -->
		        </tbody>
		      </table>
            </div>
            <div class="tabCon">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
		        <thead>
		          <tr class="text-c">
		            <th width="5%">编号</th>
		            <th width="10%">检查部位</th>
		            <th width="10%">检查项目</th>
		            <th width="20%">检查内容</th>
		            <th width="10%">检查结果</th>
		            <th width="10%">受检${flag == 1 ? '部门' : '单位'}</th>
		            <th width="15%">隐患描述</th>
		            <th width="20%">隐患图片</th>
		          </tr>
		        </thead>
		        <tbody>
		          <!-- 循环开始 -->
				  <c:forEach items="${danger1 }" varStatus="index" var="list">
				  <c:if test="${list.flag ==3}">
				    <tr class="text-c">
					<td>${index.index + 1}</td>
		            <td>${list.partName }</td>
		            <td>${list.levels }</td>
		            <td>${list.content }</td>
		            <td>
		            	<c:if test="${list.status == '1'}">合格</c:if>
		            	<c:if test="${list.status == '2'}">未整改</c:if>
		            	<c:if test="${list.status == '3'}">已整改</c:if>
		           	</td>
		            <td>${list.checkCompany }</td>
		            <td>
		              ${list.memo }
		           	</td>
		            <td>
		              <c:if test="${!empty list.files}">
		                <img alt="" src="${list.files }" onclick="showimg('${list.files }')" style="width:200px; height:200px;">
		              </c:if>
		            </td>
		            </tr>
	            </c:if>
				</c:forEach>
				<!-- 循环结束 -->
		        </tbody>
		      </table>
            </div>
            <div class="tabCon">
              <table class="table table-border table-bordered table-bg table-hover table-sort">
		        <thead>
		          <tr class="text-c">
		            <th width="5%">编号</th>
		            <th width="10%">检查部位</th>
		            <th width="10%">检查项目</th>
		            <th width="20%">检查内容</th>
		            <th width="10%">检查结果</th>
		            <th width="10%">受检${flag == 1 ? '部门' : '单位'}</th>
		            <th width="15%">隐患描述</th>
		            <th width="20%">隐患图片</th>
		          </tr>
		        </thead>
		        <tbody>
		          <!-- 循环开始 -->
				  <c:forEach items="${danger1 }" varStatus="index" var="list">
				  <c:if test="${list.flag == 4}">
				    <tr class="text-c">
					<td>${index.index + 1}</td>
		            <td>${list.partName }</td>
		            <td>${list.levels }</td>
		            <td>${list.content }</td>
		            <td>
		            	<c:if test="${list.status == '1'}">合格</c:if>
		            	<c:if test="${list.status == '2'}">未整改</c:if>
		            	<c:if test="${list.status == '3'}">已整改</c:if>
		           	</td>
		            <td>${list.checkCompany }</td>
		            <td>
		              ${list.memo }
		           	</td>
		            <td>
		              <c:if test="${!empty list.files}">
		                <img alt="" src="${list.files }" onclick="showimg('${list.files }')" style="width:200px; height:200px;">
		              </c:if>
		            </td>
		            </tr>
	            </c:if>
				</c:forEach>
				<!-- 循环结束 -->
		        </tbody>
		      </table>
            </div>
          </div>
        </div>
      </li>
    </ul>
  </div>
  
  <!-- 弹窗显示大图 -->
  <div id="win-img" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog" style="width:700px;">
          <div class="modal-content radius page-container">
            <div class="modal-header">
                <h3 class="modal-title">查看图片</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body" style="height:400px;overflow-y:auto">
              <div class="div_imgp" style="text-align:center;">
                <img id="showimgbox" alt="" src="${ly }/images/zwtp.jpg" class="img-responsive2 mt-20"/>
              </div>
            </div>
          </div>
      </div>
  </div>
<script type="text/javascript">
$(function() {
  $('.table-sort').dataTable({
    "aaSorting": [[0, "asc"]],//默认第几个排序
    "bStateSave": false,//状态保存
    searching: false,
    ordering:  false,
    "aoColumnDefs": [
    ]
    });
});


function showimg(url){
  $("#win-img").modal("show");
  $("#showimgbox").attr("src", url);
  }
</script>	
<script type="text/javascript">jQuery(".slideTxtBox").slide({trigger:"click"}); </script>
</body>
</html>