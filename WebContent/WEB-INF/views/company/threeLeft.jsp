  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="/WEB-INF/views/taglibs.jsp" %>
    <!doctype html>
    <html lang="en">
    <head>
    <meta charset="UTF-8">
    <meta name="viewport"
    content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
    ul{
    text-align:center;
    }
    ul li{
    display: inline-block;
    list-style-type: none;
    border: 1px solid;
    text-align: center;
    padding: 7px 15px;
    background: rgba(35,126,255,0.7);
    color: #fff;
    border-radius: 5px;
    }
    ul li a{
    text-decoration: none;
    display: block;
    text-align: center;
    width: 100%;
    padding-left: 0 !important;
    color: #fff;
    font-size: 15px;
    }
    </style>
    </head>
    <body>
    <div>
    <!--组织架构-->
    <c:if test="${leftBasic == 1} ">
      <ul>
      <li><a data-href="${ly }/company/system/department-list" data-title="组织架构"
      href="javascript:void(0)">组织架构</a></li>
      <li><a data-href="${ly }/company/system/user-list" data-title="部门人员" href="javascript:void(0)">部门人员</a>
      </li>
      </ul>
    </c:if>
    <!-- 风险评估 -->
    <c:if test="${leftBasic== 2} ">
      <ul>
      <li><a data-href="${ly }/company/safety-system/assess5" data-title="评估操作"
      href="javascript:void(0)">评估操作</a></li>
      <li><a data-href="${ly }/company/safety-system/control-list2?flag=1" data-title="评估结果"
      href="javascript:void(0)">评估结果</a></li>
      <li><a data-href="${ly }/company/safety-system/control-list2?flag=3" data-title="风险分布表"
      href="javascript:void(0)">风险分布表</a></li>
      <li><a data-href="${ly }/company/safety-system/control-list2?flag=2" data-title="风险分布图"
      href="javascript:void(0)">风险分布图</a></li>
      </ul>
    </c:if>
    <!--公告警示-->
    <c:if test="${leftBasic == 3} ">
      <ul>
      <li><a data-href="${ly }/company/safety-system/risk-information-list?flag=1"
      data-title="公司风险公告牌" href="javascript:void(0)">公司风险公告牌</a></li>
      <li><a data-href="${ly }/company/safety-system/risk-information-list?flag=2"
      data-title="车间风险公告牌" href="javascript:void(0)">车间风险公告牌</a></li>
      <li><a data-href="${ly }/company/safety-system/risk-information-list?flag=3"
      data-title="岗位风险公告牌" href="javascript:void(0)">岗位风险公告牌</a></li>
      <li><a data-href="${ly }/company/safety-system/ying-add" data-title="岗位应急处置卡"
      href="javascript:void(0)">岗位应急处置卡</a></li>
      <li><a data-href="${ly }/company/safety-system/harm-list" data-title="告知牌"
      href="javascript:void(0)">告知牌</a></li>
      </ul>
    </c:if>
    <!--检查模版设置-->
    <c:if test="${leftBasic == 4} ">
      <ul>
      <li><a data-href="${ly }/village/addCheckModel" data-title="添加检查模版" href="javascript:void(0)">添加检查模版</a>
      </li>
      <li><a data-href="${ly }/village/showCheckModel" data-title="查看检查模版" href="javascript:void(0)">查看检查模版</a>
      </li>
      </ul>
    </c:if>
    <!--体系管理-->
    <c:if test="${leftBasic == 5}">
      <ul class="oul">
      <li><a data-href="${ly }/company/study/management-guide" data-title="管理手册"
      href="javascript:void(0)">管理手册</a></li>
      </ul>
    </c:if>
    <!--流程管理-->
    <c:if test="${leftBasic == 6} ">
      <ul>
      <li><a data-href="${ly }/company/study/select?type=1"
      data-title="特殊作业流程" href="javascript:void(0)">特殊作业流程</a></li>
      <li><a data-href="${ly }/company/study/select?type=2"
      data-title="检维修作业流程" href="javascript:void(0)">检维修作业流程</a></li>
      <li><a data-href="${ly }/company/study/select?type=3"
      data-title="承包商作业流程" href="javascript:void(0)">承包商作业流程</a></li>
      </ul>
    </c:if>
    <!--运行管理-->
    <c:if test="${leftBasic == 7}">
      <ul>
      <li><a data-href="${ly }/company/study/naturaldanger-list" data-title="固有风险研判"
      href="javascript:void(0)">固有风险研判</a></li>
      <li><a data-href="${ly }/company/study/temporaryrisk-list" data-title="临时风险研判"
      href="javascript:void(0)">临时风险研判</a></li>
      <li><a data-href="${ly }/company/study/greatdanger-list" data-title="重大/较大隐患治理风险研判"
      href="javascript:void(0)">重大/较大隐患治理风险研判</a></li>
      </ul>
    </c:if>
    <!--责任制-->
    <c:if test="${leftBasic == 8} ">
      <ul>
      <li><a data-href="${ly }/company/tables/tab-list?isType=0" data-title="安全生产责任制"
      href="javascript:void(0)">责任制</a></li>
      <li><a data-href="${ly }/company/information/information6" data-title="安全生产管理网络图"
      href="javascript:void(0)">管理网络图</a></li>
      </ul>
    </c:if>
    <!--管理档案-->
    <c:if test="${leftBasic == 9} ">
      <ul>
      <li><a data-href="${ly }/company/study/select?type=1"
      data-title="教育培训" href="javascript:void(0)">教育培训</a></li>
      <li><a data-href="${ly }/company/study/select?type=2"
      data-title="设备设施管理" href="javascript:void(0)">设备设施管理</a></li>
      </ul>
      <div>
        <ul>
        <li><a data-href="${ly }/company/train/special-list?spFlag=0"
        data-title="持证上岗" href="javascript:void(0)">持证上岗</a></li>
        <li><a data-href="${ly }/company/tables/tab-anlist"
        data-title="安全生产教育台账" href="javascript:void(0)">教育台账</a></li>
        </ul>
      </div>
      <%--<div class="div_omenu">--%>
      <%--<div class="div_otop">教育培训<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>--%>
      <%--<ul>--%>
      <%--<li><a data-href="${ly }/company/train/special-list?spFlag=0"--%>
      <%--data-title="持证上岗" href="javascript:void(0)">持证上岗</a></li>--%>
      <%--<li><a data-href="${ly }/company/tables/tab-anlist"--%>
      <%--data-title="安全生产教育台账" href="javascript:void(0)">教育台账</a></li>--%>
      <%--</ul>--%>
      <%--</div>--%>
      <%--<div class="div_omenu">--%>
      <%--<div class="div_otop">设备设施管理<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>--%>
      <%--<ul>--%>
      <%--<li><a data-href="${ly }/company/product/mequipment-list"--%>
      <%--data-title="主要设备清单" href="javascript:void(0)">主要设备清单</a></li>--%>
      <%--<li><a data-href="${ly }/company/sequipment/sequipment-list"--%>
      <%--data-title="特种设备检测" href="javascript:void(0)">特种设备检测</a></li>--%>
      <%--<li><a data-href="${ly }/company/product/lightning-list"--%>
      <%--data-title="防雷防静电" href="javascript:void(0)">防雷防静电</a></li>--%>
      <%--<li><a data-href="${ly }/company/safety-facilities"--%>
      <%--data-title="安全设施登记台账" href="javascript:void(0)">安全设施登记台账</a></li>--%>
      <%--</ul>--%>
      <%--</div>--%>
      <%--<ul class="oul">--%>
      <%--<li><a data-href="${ly }/company/evaluate/evaluation-list?flag=0" data-title="评价评估档案"--%>
      <%--href="javascript:void(0)">评价评估档案</a></li>--%>
      <%--<li><a data-href="${ly }/company/evaluate/zheng-list" data-title="各类许可证档案"--%>
      <%--href="javascript:void(0)">各类许可证档案</a></li>--%>
      <%--<li><a data-href="${ly }/company/information/information3" data-title="两重点一重大"--%>
      <%--href="javascript:void(0)">两重点一重大</a></li>--%>
      <%--<li><a data-href="${ly }/company/tables/tab-weilist" data-title="危险作业证"--%>
      <%--href="javascript:void(0)">危险作业证</a></li>--%>
      <%--<li><a data-href="${ly }/company/tables/tab-jianglist" data-title="安全奖惩"--%>
      <%--href="javascript:void(0)">安全奖惩</a></li>--%>
      <%--<li><a data-href="${ly }/company/tables/tab-shilist" data-title="事故报告和处理"--%>
      <%--href="javascript:void(0)">事故报告和处理</a></li>--%>
      <%--</ul>--%>
    </c:if>
    <!--职业健康管理 管理档案-->
    <c:if test="${leftBasic == 10}">
      <ul>
      <li><a data-href="${ly }/company/evaluate/detection-list" data-title="职卫检测、体检"
      href="javascript:void(0)">职卫检测、体检</a></li>
      <li><a data-href="${ly }/company/tables/tab-health/2" data-title="职业卫生管理"
      href="javascript:void(0)">职业卫生管理</a></li>
      <li><a data-href="${ly }/company/tables/tab-health/3" data-title="职业卫生宣传培训"
      href="javascript:void(0)">职业卫生宣传培训</a></li>
      <li><a data-href="${ly }/company/tables/tab-health/4" data-title="职业病危害因素监测与检测评价"
      href="javascript:void(0)">职业病危害因素检测评价</a></li>
      <li><a data-href="${ly }/company/tables/tab-health/5" data-title="用人单位职业健康监护管理"
      href="javascript:void(0)">职业健康监护管理</a></li>
      <li><a data-href="${ly }/company/cyryjkjh" data-title="劳动者个人职业健康监护" href="javascript:void(0)">劳动者个人职业健康监护</a>
      </li>
      <li><a data-href="${ly }/company/tables/tab-health/7" data-title="其他" href="javascript:void(0)">其他</a>
      </li>
      </ul>
    </c:if>
    <!--在线监测设置-->
    <c:if test="${leftBasic == 11} ">
      <ul>
      <li><a data-href="${ly }/village/toSensorList" data-title="传感器列表" href="javascript:void(0)">传感器列表</a>
      </li>
      <li><a data-href="${ly }/village/toAddSensor" data-title="传感器设置"
      href="javascript:void(0)">传感器设置</a></li>
      </ul>
    </c:if>
    </div>
    </body>
    </html>
<script>
      console.log(${leftBasic});
    </script>
