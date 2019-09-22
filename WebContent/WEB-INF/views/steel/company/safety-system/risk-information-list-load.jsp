<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<div class="div-set">
  <div class="top-set">
    <font>安全风险公告牌</font>
    <a class="btn_rbj" style="right:186px;" onClick="show_dialog('编辑风险公告牌', '/company/safety-system/risk-information-add?id=${be.id}&flag=${flag }')" href="javascript:;" title="编辑">编辑</a>
    <a class="btn_rbj" style="right:96px;"  onClick="pr_()" href="javascript:;" title="打印公告牌">打印公告牌</a>
    <a class="btn_rbj" onClick="del(${be.id})" href="javascript:;" title="重新生成">重新生成</a>
  </div>
  <div class="div-print" id="div_container">
    <c:set var="n" value="${be.name }"/>
    <c:if test="${be.flag == 3}">
    <c:set value="${fn:split(n, '/') }" var="l"/>
    <c:set var="n" value="${l[fn:length(l) - 1] }"/>
    </c:if>
    <h3 class="text-c" style="padding-top:0">${n }安全风险公告牌</h3>
    <div class="main-gao">
      <table class="tab-gao" >
        <tr>
          <td class="text-c" rowspan="2" style="width:30%;vertical-align: top">
            <div class="div-gaoz">
              <b style="font-size:14px">风险等级：</b>
              <div class="div-xz">
                <div class="check-box">
                  <label>红</label>
                  <img alt="" src="/images/fore/${fn:contains(be.fxdj, '红') ? 'gx1.png' : 'wgx.png'}"/>
                </div>
                <div class="check-box">
                  <label>橙</label>
                  <img alt="" src="/images/fore/${fn:contains(be.fxdj, '橙') ? 'gx2.png' : 'wgx.png'}"/>
                </div>
              </div>
              <div class="div-xz">
                <div class="check-box">
                  <label>黄</label>
                  <img alt="" src="/images/fore/${fn:contains(be.fxdj, '黄') ? 'gx3.png' : 'wgx.png'}"/>
                </div>
                <div class="check-box">
                  <label>蓝</label>
                  <img alt="" src="/images/fore/${fn:contains(be.fxdj, '蓝') ? 'gx4.png' : 'wgx.png'}"/>
                </div>
              </div>
            </div>
          </td>
          <c:choose>
             <c:when test="${fn:contains(be.fxdj, '红')}"><c:set value="tcol-a" var="cl"/></c:when>
             <c:when test="${fn:contains(be.fxdj, '橙')}"><c:set value="tcol-b" var="cl"/></c:when>
             <c:when test="${fn:contains(be.fxdj, '黄')}"><c:set value="tcol-c" var="cl"/></c:when>
             <c:when test="${fn:contains(be.fxdj, '蓝')}"><c:set value="tcol-d" var="cl"/></c:when>
          </c:choose>
          <td class="text-c ${cl }" style="width:30%;font-size:14px">事故类型</td>
          <td class="text-c ${cl }" style="width:40%;font-size:14px">风险因素</td>
        </tr>
        <tr>
          <td rowspan="2" class="td-gao">
            <p>${be.sglx }</p>
          </td>
          <td  rowspan="2" class="td-gao">
          <p>${be.fxysZd }${be.fxys }</p>
          </td>
        </tr>
        <tr>
          <td class="text-c">
            <div class="div-gaoz" style="margin-bottom:0;">
              <b style="font-size:14px">管理责任人：</b>
              <p>${be.glzrr }</p>
            </div>
          </td>
        </tr>
        <tr><td colspan="3" style="height:5px"></td></tr>
        <tr>
          <td colspan="3" class="text-c ${cl }" style="font-size:14px">应急防范措施</td>
        </tr>
        <tr>
          <td colspan="3" class="td-gao">
            <!-- 循环 -->
            <p>${be.yjczcs }</p>
          </td>
        </tr>
        <tr>
          <td colspan="3">
            <div class="div-gimg" style="margin-bottom:6px;">
              <img alt="" src="${ly }/images/gao/aqgz1.jpg"/>
              <img alt="" src="${ly }/images/gao/aqgz2.jpg"/>
              <img alt="" src="${ly }/images/gao/aqgz3.jpg"/>
              <img alt="" src="${ly }/images/gao/aqgz4.jpg"/>
              <img alt="" src="${ly }/images/gao/aqgz6.jpg"/>
              <img alt="" src="${ly }/images/gao/l1.jpg"/>
              <img alt="" src="${ly }/images/gao/l2.jpg"/>
              <img alt="" src="${ly }/images/gao/l3.jpg"/>
              <img alt="" src="${ly }/images/gao/l4.jpg" style="margin-right:0;"/>
            </div>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <div class="div-gaoz" style="padding:0;">
              <div class="xx-gaoz">重要<br>提示</div>
              <font class="xx-gaoz1">安全是一种责任、为己为家为他人</font>
            </div>
          </td>
          <td>
            <div class="div-gaoz" style="margin-bottom:0;padding:5px 10px;">
              <b>安全部电话</b>
              <font>火警电话：119&emsp;急救电话：120</font>
            </div>
          </td>
        </tr>
        <tr>
        </tr>
      </table>
    </div>
  </div>
</div>