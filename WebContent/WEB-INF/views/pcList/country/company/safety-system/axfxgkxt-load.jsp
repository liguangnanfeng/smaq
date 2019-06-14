<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<div id="axfxgkxt_load">
  <ul>
    <li><a data-href="${ly }/company/safety-system/plan-add" data-title="实施方案" href="javascript:void(0)">实施方案</a></li>
    <li><a data-href="${ly }/company/safety-system/risk-list" data-title="风险辨识" href="javascript:void(0)">风险辨识</a></li>
  </ul>
  <div class="div_omenu">
    <div  class="div_otop">风险评估<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>
    <ul>
      <c:if test="${empty company.industry || company.industry eq '化工企业（危化生产、使用）'}">
        <!-- 判断危化品企业显示 -->
        <li><a data-href="${ly }/company/safety-system/assess1" data-title="危化品" href="javascript:void(0)">危化品</a></li>
      </c:if>
      <c:if test="${company.industry != '化工企业（危化生产、使用）'}">
        <!-- 判断工贸企业显示：作业条件风险程度评价、风险矩阵法 -->
        <li><a data-href="${ly }/company/safety-system/assess2" data-title="作业条件风险程度评价" href="javascript:void(0)">作业条件风险程度评价</a></li>
        <li><a data-href="${ly }/company/safety-system/assess3" data-title="风险矩阵法" href="javascript:void(0)">风险矩阵法</a></li>
        <li><a data-href="${ly }/company/safety-system/assess4" data-title="安全风险空间分布图" href="javascript:void(0)">安全风险空间分布图</a></li>
      </c:if>
    </ul>
  </div>
  <div class="div_omenu">
    <div  class="div_otop">风险控制<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>
    <ul>
      <li><a data-href="${ly }/company/safety-system/control-list?t=1" data-title="重大风险" href="javascript:void(0)">重大风险</a></li>
      <li><a data-href="${ly }/company/safety-system/control-list?t=2" data-title="较大风险" href="javascript:void(0)">较大风险</a></li>
      <li><a data-href="${ly }/company/safety-system/control-list?t=3" data-title="一般和较低风险" href="javascript:void(0)">一般和较低风险</a></li>
      <li><a data-href="${ly }/company/safety-system/control-list?t=4" data-title="分级管控" href="javascript:void(0)">分级管控</a></li>
    </ul>
  </div>
</div>