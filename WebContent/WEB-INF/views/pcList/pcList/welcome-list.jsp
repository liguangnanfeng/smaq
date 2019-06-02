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
.panel-default{display: inline-block;border:0px;}
body,html{background:#EBEEF3}
.title_xw{width:100%;font-weight:bold;font-size:15px;line-height: 32px;}
.one_notice{width:100%;margin-top:0px;}
.text-overflow{display: inline-block;width:100%;line-height:25px;color:#7C7C7C;margin-right:0px;}
.text-overflow i{height:3px;width:3px;background:#7C7C7C;border-radius:50%;display:inline-block;vertical-align: middle;margin-right:5px;}
.text-overflow:hover a{color:#5889D8;text-decoration: underline;}
.text-overflow:hover i{background:#5889D8;}
.text-riqi{float:right;color:#AEAEAE;line-height:25px;}
body .lianj_com img{margin-right:6px;margin-left:6px;}
.clearfix:after{content:"\20";display:block;height:0;clear:both;visibility:hidden}
.clearfix{zoom:1}
.tabBar {border-bottom: 2px solid #F6F6F6}
.tabBar span {background-color: #fff;cursor: pointer;font-size:15px;display: inline-block;float: left;font-weight: bold;height: 40px;line-height: 40px;padding: 0 35px}
.tabBar span.current{background-color: #fff;color: #FF9900;border:0px;border-bottom:2px solid #FF9900}
.tabCon {display: none;border:0px;padding:0;}
.one_tx{margin-top:20px;width:31%;margin-right:2%}
.bt_zd{background:#DF2E30;padding:0 9px;color:#fff;font-size:12px;margin-left:10px;border-radius:5px;height:25px;line-height:25px;display: inline-block;}
.div_zhe{width:100%;}
.change_zhe{width:100%;height:35px;position: relative;}
.btn-group{position: absolute;right:0;top:14px;}
.btnyh{color: #333;background: #e6e6e6;}
.btnyhxz{color: #fff;background: #5a98de;}
body .iconxw{transform: rotate(-90deg);float:right;margin-right:-5px;margin-top: -3px;}
</style>
<script type="text/javascript">
var chart = null;
$(function () {
  $(".title_danger").on("click",function(){
    if($(this).parent().hasClass("listdxz")){
      $(this).parent().removeClass("listdxz");
      $(this).find("i").html("&#xe6d5;");
    }else{
      $(this).parent().addClass("listdxz");
      $(this).find("i").html("&#xe6d7;");
    }
  });
  $.Huitab("#tab_yj .tabBar span","#tab_yj .tabCon","current","click","0")
});
function showping(){
  $("#win-ping").modal("show")}
  
</script>
</head>
<body>
<c:set var="c1_2" value="0"/>
<c:set var="c1_3" value="0"/>
<c:set var="c2_2" value="0"/>
<c:set var="c2_3" value="0"/>
<c:set var="c3_2" value="0"/>
<c:set var="c3_3" value="0"/>
<c:set var="c4_2" value="0"/>
<c:set var="c4_3" value="0"/>
<c:forEach items="${dangerC }" var="be">
<c:if test="${be.flag == 1 and be.status == 2}"><c:set var="c1_2" value="${be.c }"/></c:if>
<c:if test="${be.flag == 1 and be.status == 3}"><c:set var="c1_3" value="${be.c }"/></c:if>
<c:if test="${be.flag == 2 and be.status == 2}"><c:set var="c2_2" value="${be.c }"/></c:if>
<c:if test="${be.flag == 2 and be.status == 3}"><c:set var="c2_3" value="${be.c }"/></c:if>
<c:if test="${be.flag == 3 and be.status == 2}"><c:set var="c3_2" value="${be.c }"/></c:if>
<c:if test="${be.flag == 3 and be.status == 3}"><c:set var="c3_3" value="${be.c }"/></c:if>
<c:if test="${be.flag == 4 and be.status == 2}"><c:set var="c4_2" value="${be.c }"/></c:if>
<c:if test="${be.flag == 4 and be.status == 3}"><c:set var="c4_3" value="${be.c }"/></c:if>
</c:forEach>

<c:set var="d1_2" value="0"/>
<c:set var="d1_3" value="0"/>
<c:set var="d2_2" value="0"/>
<c:set var="d2_3" value="0"/>
<c:set var="d3_2" value="0"/>
<c:set var="d3_3" value="0"/>
<c:set var="d4_2" value="0"/>
<c:set var="d4_3" value="0"/>
<c:forEach items="${dangerC2 }" var="be">
<c:if test="${be.flag == 1 and be.status == 2}"><c:set var="d1_2" value="${be.c }"/></c:if>
<c:if test="${be.flag == 1 and be.status == 3}"><c:set var="d1_3" value="${be.c }"/></c:if>
<c:if test="${be.flag == 2 and be.status == 2}"><c:set var="d2_2" value="${be.c }"/></c:if>
<c:if test="${be.flag == 2 and be.status == 3}"><c:set var="d2_3" value="${be.c }"/></c:if>
<c:if test="${be.flag == 3 and be.status == 2}"><c:set var="d3_2" value="${be.c }"/></c:if>
<c:if test="${be.flag == 3 and be.status == 3}"><c:set var="d3_3" value="${be.c }"/></c:if>
<c:if test="${be.flag == 4 and be.status == 2}"><c:set var="d4_2" value="${be.c }"/></c:if>
<c:if test="${be.flag == 4 and be.status == 3}"><c:set var="d4_3" value="${be.c }"/></c:if>
</c:forEach>
<c:set var="zdsgyh" value="${d1_2 + d2_2 + d3_2 + d4_2}"/>
  <div class="panel panel-default" style="width:97%;margin-left:10px;float:left;background:none;">
    <div class="panel-body">
      <div class="div_tx">
        <div class="div_img">
          <img alt="" src="${ly }/images/mrqy.jpg"/>
        </div>
        <div class="div_company">
        <!--<c:if test="${moveD != 1 }">
            <p style="margin-top:10px">
                <a href="${ly }/town/cx/move/next?uid=${movUserId }" target="_parent">${name }</a>
            </p>
        </c:if>
        <c:if test="${moveD == 1 }">-->
            <p style="margin-top:10px">
                ${name }
            </p>
        <!--</c:if>-->

          <span  style="margin-top:10px"><a onclick="show_dialog('全部企业', '${ly }/town/cx/company/company-lists?villageId=${villageId }&townId=${townId }&districtId=${districtId }')" href="javascript:;" class="lianj_com">企业数量：${number }</a></span>
          <span style="margin-top:10px">
            <a onclick="show_dialog('企业分布图', '${ly }/town/cx/company/company-maps?villageId=${villageId }&townId=${townId }&districtId=${districtId }')" href="javascript:;" class="lianj_com">企业分布图<img alt="" src="${ly }/images/fore/fb.jpg"/></a>
          </span>
        </div>
        <div class="div_company2">
          <span style="margin-top:10px">
            <a onclick="show_dialog('重点部位/装置实时监控', '${ly }/town/cx/monitor-list-supervise?villageId=${villageId }&townId=${townId }&districtId=${districtId }')" href="javascript:;" class="lianj_com">重点部位/装置实时监控<img alt="" src="${ly }/images/fore/sous.jpg"/></a>
          </span>
          <span style="margin-top:10px">
            <a onclick="show_dialog('高危行业', '${ly }/village/company/company-lists?doubleDanger=1&villageId=${villageId }&townId=${townId }&districtId=${districtId }')" href="javascript:;" class="lianj_com">高危行业<img alt="" src="${ly }/images/fore/sous.jpg"/></a>
          </span>
          <span style="margin-top:10px"><a onclick="show_dialog('应急救援', '${ly }/town/cx/tables/tab-yjlist?c=1&villageId=${villageId }&townId=${townId }&districtId=${districtId }')" href="javascript:;" class="lianj_com"><img alt="" src="${ly }/images/fore/yj.jpg"/>应急救援</a></span>
        </div>
      </div>
      <div class="mt-15 pos-r">
        <div class="div_tx div_yj" style="padding-top:15px;">
          <div class="title_xw pos-r" style="border-bottom:1px solid #F6F6F6;color:#FB0005;"><div class="bt_yuj">
              <c:choose>
                <c:when test="${districtId != null }">       
                                                 预警栏(${count[0] + count[1] + count[2] + count[3] + count[4] + count[5] + count[6] + zdsgyh + count[8]})
                </c:when>
                <c:otherwise>
                                                预警栏(${count[0] + count[1] + count[2] + count[3] + count[4] + count[5] + count[9] + zdsgyh + count[8]})
                </c:otherwise>
              </c:choose>
            </div></div>
          <a class="one_tx radius pos-r" onclick="show_dialog('持证上岗', '${ly }/town/cx/warning/sperson-list?isTime=3&villageId=${villageId }&townId=${townId }&districtId=${districtId }')" href="javascript:;" style="margin-left:1%;background:#46D1E5;">
                <img alt="" src="${ly }/images/httb1.png"/>
                <font class="font_tx1 pos-a <c:if test="${count[0] >0}">blink</c:if>">${count[0]}</font>
                <font class="font_tx2 pos-a">持证上岗</font>
              </a>
              <a class="one_tx radius pos-r" onclick="show_dialog('特种设备检测', '${ly }/town/cx/warning/sequipment-list?isTime=3&villageId=${villageId }&townId=${townId }&districtId=${districtId }')" href="javascript:;" style="background:#60D1FF;">
                <img alt="" src="${ly }/images/httb2.png"/>
                <font class="font_tx1 pos-a <c:if test="${count[1] >0}">blink</c:if>">${count[1] }</font>
                <font class="font_tx2 pos-a">特种设备检测</font>
              </a>
              <a class="one_tx radius pos-r" onclick="show_dialog('职卫检测、体检', '${ly }/town/cx/warning/detection-list?isTime=3&villageId=${villageId }&townId=${townId }&districtId=${districtId }')" href="javascript:;" style="background:#34B5ED;margin-right:0;">
                <img alt="" src="${ly }/images/httb3.png"/>
                <font class="font_tx1 pos-a <c:if test="${count[2] + count[3] >0}">blink</c:if>">${count[2] + count[3]}</font>
                <font class="font_tx2 pos-a">职卫检测、体检</font>
              </a>
              <a class="one_tx radius pos-r" onClick="showping()" href="javascript:;" style="margin-left:1%;background:#FFA160;">
                <img alt="" src="${ly }/images/httb4.png"/>
                <font class="font_tx1 pos-a <c:if test="${count[4]+ count[5]+ count[8] >0}">blink</c:if>">${count[4]+ count[5]+ count[8] }</font>
                <font class="font_tx2 pos-a">评价评估</font>
              </a>
              <c:choose>
              <c:when test="${districtId != null }">
              <a class="one_tx radius pos-r" onclick="show_dialog('行政执法', '${ly }/town/cx/rectification-list?flag=2&c=0&villageId=${villageId }&townId=${townId }&districtId=${districtId }')" href="javascript:;" style="background:#ED6840;">
                <img alt="" src="${ly }/images/httb5.png"/>
                <font class="font_tx1 pos-a <c:if test="${count[6] >0}">blink</c:if>">${count[6] }</font>
                <font class="font_tx2 pos-a">行政执法</font>
              </a>
              </c:when>
              <c:otherwise>
              <a class="one_tx radius pos-r" onclick="show_dialog('定期检查', '${ly }/town/cx/check-list?flag=1&status=1&villageId=${villageId }&townId=${townId }&districtId=${districtId }')" href="javascript:;" style="background:#ED6840;">
                <img alt="" src="${ly }/images/httb5.png"/>
                <font class="font_tx1 pos-a <c:if test="${count[9] >0}">blink</c:if>">${count[9] }</font>
                <font class="font_tx2 pos-a">定期检查</font>
              </a>
              </c:otherwise>
              </c:choose>
              <a class="one_tx radius pos-r" href="javascript:;" onclick="show_dialog('重大事故隐患', '${ly }/town/cx/check-item3?status=2&villageId=${villageId }&townId=${townId }&districtId=${districtId }')" style="background:#ea4f21;margin-right:0">
                <img alt="" src="${ly }/images/httb9.png"/>
                <font class="font_tx1 pos-a <c:if test="${zdsgyh >0}">blink</c:if>">${zdsgyh }</font>
                <font class="font_tx2 pos-a">重大事故隐患</font>
              </a>
        </div>
        <div class="div_tx div_yh pos-a" style="height:315px;padding: 17px 20px;">
          <div class="title_xw pos-r" style="border-bottom:1px solid #F6F6F6;color:#FB0005;"><div class="bt_yuj" style="width:180px">在线监测预警.报警</div></div>
          <div class="list_zd">
           <%-- <a class="one_tx radius pos-r" id="toOpenDanger"  href="javascript:;"  style="width:100%;margin-right:0;background:#1450DD;">
              <img alt="" src="${ly }/images/httb7.png"/>
              <font class="font_tx1 pos-a <c:if test="${totalCount >0}">blink</c:if>">${totalCount }</font>
              <font class="font_tx2 pos-a" title="重大危险源/高危作业">重大危险源/高危作业</font>
            </a> --%>
            <a class="one_tx radius pos-r"  data-title="重大危险源隐患企业列表" onclick="Hui_admin_tab(this)" href="javascript:;" data-href="${ly }/village/company/toZdCompanyList?hazard=1&villageId=${villageId }&townId=${townId }&districtId=${districtId }" style="width:100%;margin-right:0;background:#1450DD;" >
            	<font style="width: 95%;text-align: center;left:auto;" class="font_tx1 pos-a " id="zd_font"></font>
              	<font style="left:5px;width:88%;" class="font_tx2 pos-a" title="重大危险源">重大危险源</font>
            </a>
            <a class="one_tx radius pos-r"  data-title="有限（受限）空间作业" onclick="Hui_admin_tab(this)" href="javascript:;" data-href="${ly }/village/company/toGwCompanyList?danger=1&villageId=${villageId }&townId=${townId }&districtId=${districtId }" style="width:100%;margin-right:0;background:#1450DD;" >
            	<font id="gw_font" style="width: 95%;text-align: center;left:auto;" class="font_tx1 pos-a "></font>
               <font style="left:13px;width:88%;" class="font_tx2 pos-a" title="有限（受限）空间作业">有限（受限）空间作业</font>
            </a>
            <%-- <a class="one_tx radius pos-r" data-title="河道污染隐患企业列表" onclick="Hui_admin_tab(this)" href="javascript:;" data-href="${ly }/village/company/toWaterWarnCompanyList?danger=1&villageId=${villageId }&townId=${townId }&districtId=${districtId }" style="width:100%;margin-right:0;background:#DF3030;">
            <img alt="" src="${ly }/images/httb8.png"/>
            <font class="font_tx1 pos-a "></font>
            <font class="font_tx2 pos-a" title="河道污染">河道污染</font>
          </a> --%>
          </div>
        </div>
      </div>
      <!-- 评价评估弹窗 -->
      <div id="win-ping" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="width:800px">
            <div class="modal-content radius">
                <div class="modal-header">
                    <h3 class="modal-title">评价评估</h3>
                    <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
                </div>
                <div class="modal-body">
                  <a class="one_tx radius pos-r" data-dismiss="modal" aria-hidden="true" onclick="show_dialog('各类评价评估', '${ly }/town/cx/evaluate/evaluation-list?isTime=6&villageId=${villageId }&townId=${townId }&districtId=${districtId }')" href="javascript:;" style="background:#FFA160;">
                    <img alt="" src="${ly }/images/httb4.png"/>
                    <font class="font_tx1 pos-a <c:if test="${count[4] >0}">blink</c:if>">${count[4] }</font>
                    <font class="font_tx2 pos-a">评价评估</font>
                  </a>
                  <a class="one_tx radius pos-r" data-dismiss="modal" aria-hidden="true" onclick="show_dialog('各类许可证', '${ly }/town/cx/evaluate/zheng-list?isTime=6&villageId=${villageId }&townId=${townId }&districtId=${districtId }')" href="javascript:;" style="background:#FF9346;">
                    <img alt="" src="${ly }/images/httb6.png"/>
                    <font class="font_tx1 pos-a <c:if test="${count[5] >0}">blink</c:if>">${count[5] }</font>
                    <font class="font_tx2 pos-a">各类许可证</font>
                  </a> 
                  <a class="one_tx radius pos-r" data-dismiss="modal" aria-hidden="true" onclick="show_dialog('应急预案', '${ly }/town/cx/tables/tab-yjlist?isTime=6&villageId=${villageId }&townId=${townId }&districtId=${districtId }')" href="javascript:;" style="margin-right:0;background:#ff8048;">
                    <img alt="" src="${ly }/images/httb10.png"/>
                    <font class="font_tx1 pos-a <c:if test="${count[8] >0}">blink</c:if>">${count[8] }</font>
                    <font class="font_tx2 pos-a">应急预案</font>
                  </a> 
                </div>
            </div>
        </div>
    </div>
     <div class="div_tx mt-15">
        <div class="title_xw pb-5 pos-r" style="border-bottom:1px solid #F6F6F6">隐患排查治理汇总</div>
        <div class="div_hz">
          <span>企业自查</span>
          <div class="div_hui">
            <a class="one_huiz" onclick="show_dialog('企业自查-隐患排查记录', '${ly }/town/cx/danger-index-list?flag=1&villageId=${villageId }&townId=${townId }&districtId=${districtId }')" href="javascript:;">
              <font>${c1_2 + c1_3}</font>
              <label>全部隐患</label>
            </a>
            <a class="one_huiz" onclick="show_dialog('企业自查-隐患排查记录', '${ly }/town/cx/danger-index-list?flag=1&villageId=${villageId }&townId=${townId }&districtId=${districtId }')" href="javascript:;">
              <font style="color:#FF9345"><fmt:formatNumber value="${(c1_2 + c1_3) == 0 ? 1 : c1_3 / (c1_2 + c1_3) }" pattern="0.0%"/></font>
              <label>整改率</label>
            </a>
            <a class="one_huiz" style="width:40%;border:0px;" onclick="show_dialog('企业自查-隐患排查记录', '${ly }/town/cx/danger-index-list?flag=1&villageId=${villageId }&townId=${townId }&districtId=${districtId }')" href="javascript:;" style="border:0px;">
              <font style="color:#E13031"><fmt:formatNumber value="${(d1_2 + d1_3) == 0 ? 1 : d1_3 / (d1_2 + d1_3) }" pattern="0.0%"/></font>
              <label>重大隐患整改率</label>
            </a>
          </div>
        </div>
        <div class="div_hz">
          <span>部门抽查</span>
          <div class="div_hui">
            <a class="one_huiz" onclick="show_dialog('部门抽查-隐患排查记录', '${ly }/town/cx/danger-index-list?flag=3&villageId=${villageId }&townId=${townId }&districtId=${districtId }')" href="javascript:;">
              <font>${c3_2 + c3_3}</font>
              <label>全部隐患</label>
            </a>
            <a class="one_huiz" onclick="show_dialog('部门抽查-隐患排查记录', '${ly }/town/cx/danger-index-list?flag=3&villageId=${villageId }&townId=${townId }&districtId=${districtId }')" href="javascript:;">
              <font style="color:#FF9345"><fmt:formatNumber value="${(c3_2 + c3_3) == 0 ? 1 : c3_3 / (c3_2 + c3_3) }" pattern="0.0%"/></font>
              <label>整改率</label>
            </a>
            <a class="one_huiz" style="width:40%;border:0px;" onclick="show_dialog('部门抽查-隐患排查记录', '${ly }/town/cx/danger-index-list?flag=3&villageId=${villageId }&townId=${townId }&districtId=${districtId }')" href="javascript:;" style="border:0px;">
              <font style="color:#E13031"><fmt:formatNumber value="${(d3_2 + d3_3) == 0 ? 1 : d3_3 / (d3_2 + d3_3) }" pattern="0.0%"/></font>
              <label>重大隐患整改率</label>
            </a>
          </div>
        </div>
        <c:choose>
              <c:when test="${districtId == null }">
        <div class="div_hz" style="margin-right:0px;">
          <span>行政检查</span>
          <div class="div_hui">
            <a class="one_huiz" onclick="show_dialog('行政检查-隐患排查记录', '${ly }/town/cx/danger-index-list?flag=4&villageId=${villageId }&townId=${townId }&districtId=${districtId }')" href="javascript:;">
              <font>${c4_2 + c4_3}</font>
              <label>全部隐患</label>
            </a>
            <a class="one_huiz" onclick="show_dialog('行政检查-隐患排查记录', '${ly }/town/cx/danger-index-list?flag=4&villageId=${villageId }&townId=${townId }&districtId=${districtId }')" href="javascript:;">
              <font style="color:#FF9345"><fmt:formatNumber value="${(c4_2 + c4_3) == 0 ? 1 : c4_3 / (c4_2 + c4_3) }" pattern="0.0%"/></font>
              <label>整改率</label>
            </a>
            <a class="one_huiz" style="width:40%;border:0px;" onclick="show_dialog('行政检查-隐患排查记录', '${ly }/town/cx/danger-index-list?flag=4&villageId=${villageId }&townId=${townId }&districtId=${districtId }')" href="javascript:;" style="border:0px;">
              <font style="color:#E13031"><fmt:formatNumber value="${(d4_2 + d4_3) == 0 ? 1 : d4_3 / (d4_2 + d4_3) }" pattern="0.0%"/></font>
              <label>重大隐患整改率</label>
            </a>
          </div>
        </div>
        </c:when>
        <c:otherwise>
        <div class="div_hz" style="margin-right:0px;">
          <span>行政执法</span>
          <div class="div_hui">
            <a class="one_huiz" onclick="show_dialog('行政执法-隐患排查记录', '${ly }/town/cx/danger-index-list?flag=2&villageId=${villageId }&townId=${townId }&districtId=${districtId }')" href="javascript:;">
              <font>${c2_2 + c2_3}</font>
              <label>全部隐患</label>
            </a>
            <a class="one_huiz" onclick="show_dialog('行政执法-隐患排查记录', '${ly }/town/cx/danger-index-list?flag=2&villageId=${villageId }&townId=${townId }&districtId=${districtId }')" href="javascript:;">
              <font style="color:#FF9345"><fmt:formatNumber value="${(c2_2 + c2_3) == 0 ? 1 : c2_3 / (c2_2 + c2_3) }" pattern="0.0%"/></font>
              <label>整改率</label>
            </a>
            <a class="one_huiz" style="width:40%;border:0px;" onclick="show_dialog('行政执法-隐患排查记录', '${ly }/town/cx/danger-index-list?flag=2&villageId=${villageId }&townId=${townId }&districtId=${districtId }')" href="javascript:;" style="border:0px;">
              <font style="color:#E13031"><fmt:formatNumber value="${(d2_2 + d2_3) == 0 ? 1 : d2_3 / (d2_2 + d2_3) }" pattern="0.0%"/></font>
              <label>重大隐患整改率</label>
            </a>
          </div>
        </div>
        </c:otherwise>
        </c:choose>
      </div>
      <div class="div_tx mt-15" style="padding-top:15px">
        <div id="tab_yj" class="HuiTab">
          <div class="tabBar clearfix"><span>公共服务</span><span>安全许可服务</span></div>
          <div class="tabCon">
            <a class="one_tx2 radius" onclick="show_dialog('无锡特种设备检验', 'http://at.lbypmall.com/')" href="javascript:;" style="margin-left:1%">
              <img alt="" src="${ly }/images/fore/atpx3.jpg"/>
              <font class="font_txs2">无锡特种设备检验</font>
            </a>
            <a class="one_tx2 radius" onclick="show_dialog('劳保用品商城', 'http://shop.lbypmall.com/')" href="javascript:;">
              <img alt="" src="${ly }/images/fore/atpx2.jpg"/>
              <font class="font_txs2">劳保用品商城</font>
            </a>
            <a class="one_tx2 radius" onclick="show_dialog('安泰培训系统', 'http://www.antaisafety.com/')" href="javascript:;">
              <img alt="" src="${ly }/images/fore/atpx.jpg"/>
              <font class="font_txs2">安泰培训系统</font>
            </a>
            <a class="one_tx2 radius" onclick="show_dialog('慈铭华康体检', 'http://www.wxhktj.cn/Default.aspx')" href="javascript:;">
              <img alt="" src="${ly }/images/fore/hpw.jpg"/>
              <font class="font_txs2">慈铭华康体检</font>
            </a>
            <a class="one_tx2 radius" onclick="show_dialog('易安商城', 'http://www.esafety.cn/shop/')" href="javascript:;">
              <img alt="" src="${ly }/images/fore/atpx1.jpg"/>
              <font class="font_txs2">易安商城</font>
            </a>
            <a class="one_tx2 radius" style="margin-right:0" onclick="show_dialog('普天防雷检测', 'http://www.ptfljt.com/')" href="javascript:;">
              <img alt="" src="${ly }/images/fore/atpx4.jpg"/>
              <font class="font_txs2">普天防雷检测</font>
            </a>
          
          </div>
          <div class="tabCon">
             <a class="one_tx2 radius" style="width:23%;margin-left:1%" onclick="show_dialog('行政许可', 'http://223.111.68.8:8008/waoswh1/main1.aspx')" href="javascript:;" style="margin-left:1%">
                <img alt="" src="${ly }/images/fore/atpx5.jpg"/>
                <font class="font_txs2">行政许可</font>
              </a>
              <a class="one_tx2 radius" style="width:23%" onclick="show_dialog('标准化', 'http://aqbzh.chinasafety.gov.cn/fw/base/login')" target="_blank">
                <img alt="" src="${ly }/images/fore/atpx6.jpg"/>
                <font class="font_txs2">标准化</font>
              </a>
              <a class="one_tx2 radius" style="width:23%;" onclick="show_dialog('危化登记', 'http://211.100.28.219/')" href="javascript:;">
                <img alt="" src="${ly }/images/fore/atpx7.jpg"/>
                <font class="font_txs2">危化登记</font>
              </a>
              <a class="one_tx2 radius" style="width:23%;margin-right:0" onclick="show_dialog('职业危害申报', 'http://211.100.47.109/zywsmain/index.asp')" href="javascript:;">
                <img alt="" src="${ly }/images/fore/atpx8.jpg"/>
                <font class="font_txs2">职业危害申报</font>
              </a>
          </div>
        </div>
      </div>
      <div class="div_tx mt-15 div-chart" style="padding:25px 0">
        <div class="title_xw" style="padding:0 20px 5px 20px">企业分类统计</div>
        <div style="display: inline-block;width:100%;text-align:center;">
          <div id="container" style="float:left;width:850px;height:500px"></div>
        </div>
        <div style="display: inline-block;width:100%;height:15px;background:#EBEEF3"></div>
        <div style="display: inline-block;width:100%;text-align:center;">
          <div id="container4" style="float:left;width:950px;margin-top:40px"></div>
        </div>
        <div style="display: inline-block;width:100%;height:15px;background:#EBEEF3"></div>
        <div style="display: inline-block;width:100%;text-align:center;">
          <div id="container2" style="width:900px;height:500px;display: inline-block;margin-top:40px"></div>
        </div>
      </div>
      <div class="div_tx mt-15">
        <div class="title_xw pb-5 pos-r" style="border-bottom:1px solid #F6F6F6">排查治理统计分析
          <!-- 判断并提示至少选择3个月 -->
          <div class="search_rq pos-a">
            <font>查询时间段：</font>
            <input type="text" class="Wdate input-text" id="sT" style="width:150px;" onfocus="WdatePicker({dateFmt:'yyyy-MM',maxDate:'#F{$dp.$D(\'eT\',{M:-3}) || \'%y-{%M-3}\'}'})"/>
            <font>-</font>
            <input type="text" class="Wdate input-text" id="eT" style="width:150px;" onfocus="WdatePicker({dateFmt:'yyyy-MM',minDate:'#F{$dp.$D(\'sT\',{M:+3})}', maxDate : '%y-%M'})"/>
            <button class="btn btn-success" type="button" onclick="reload()">
              <i class="Hui-iconfont">&#xe665;</i> 查询
            </button>
          </div>
        </div>
        <div class="div_zhe">
          <div class="change_zhe">
            <div class="btn-group">
              <span class="btn btnyh btnyhxz radius yhlx">隐患类型</span>
              <span class="btn btnyh radius yhdj">隐患等级</span>
              <span class="btn btnyh radius yhzg">整改率</span>
              <span class="btn btnyh radius yhly">隐患来源</span>
            </div>
          </div>
          <div id="container3" style="width:100%;height:450px;display: inline-block;margin-top:25px;"></div>
        </div>
      </div>
    </div>
  </div>
</body>
<script type="text/javascript" src="${ly }/js/c-chart-pcList.js"></script>
<script type="text/javascript" src="/js/fxgk/industry.js?v=180920"></script>
<script type="text/javascript">
  function getCountData(){
    $.ajax({
      url : "${ly}/village/getDangerCount",
      type : 'post',
      data : {
        villageId : '${villageId }',
        townId : '${townId }',
        districtId : '${districtId }'
      },
      success : function(data){
        if(undefined != data.zdTotal){
          if(data.zdTotal > 0){
            if(!$("#zd_font").hasClass("blink")){
          		$("#zd_font").addClass("blink");
            }
          		$("#zd_font").html(data.zdTotal);
          }else{
            $("#zd_font").removeClass("blink");
            $("#zd_font").html(0);
          }
        }
        if(undefined != data.gwTotal){
          if(data.gwTotal > 0){
            if(!$("#gw_font").hasClass("blink")){
          		$("#gw_font").addClass("blink");
            }
          		$("#gw_font").html(data.gwTotal);
          }else{
            $("#gw_font").removeClass("blink");
            $("#gw_font").html(0);
          }
        }
      }
    });
  }
  
  getCountData();
 
 </script>
<script type="text/javascript">
var villageId = '${villageId }';
var townId = '${townId }';
var districtId = '${districtId }';
</script>
<script type="text/javascript" src="/js/fxgk/industry2-pie.js"></script>
</html>