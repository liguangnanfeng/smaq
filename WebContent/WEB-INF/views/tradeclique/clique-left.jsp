  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!DOCTYPE HTML>
    <html>
    <%@ include file="/WEB-INF/views/taglibs.jsp"%>
    <style type="text/css">
    #winpop {
    width: 275px;
    height: 120px;
    position: absolute;
    right: 0;
    bottom: 0;
    border: 1px solid #666;
    margin: 0;
    padding: 1px;
    overflow: hidden;
    z-index: 3000;
    background: #fff;
    }
    #winpop .title{
    width:100%;
    height:22px;
    line-height:20px;
    background:#FFCC00;
    font-weight:bold;
    text-align:center;
    font-size:12px;
    }
    #winpop .con{
    width:100%;
    height:90px;
    line-height:80px;
    font-weight:bold;
    font-size:12px;
    color:#FF0000;
    text-decoration:underline;
    text-align:center
    }
    #silu{
    font-size:12px;
    color:#666;
    position:absolute;
    right:0;
    text-decoration:underline;
    line-height:22px;
    }
    .close{
    position:absolute;
    right:4px;
    top:-1px;
    color:#FFF;
    cursor:pointer
    }
    </style>
    <script type="text/javascript">
    $(document).ready(function() {
    $("body").on("click",".Hui-aside .menu_dropdown li a", function() {
    $(".Hui-aside .menu_dropdown li a").removeClass("l_active");
    $(this).addClass("l_active");
    });
    $(".div_otop2").on("click",function(){
    if($(this).closest(".div_omenu2").hasClass("div_omenu2xz")){
    $(this).closest(".div_omenu2").removeClass("div_omenu2xz");
    $(this).parent().find(".div_omenu").removeClass("div_omenuxz");
    }else{
    $(".div_omenu2").removeClass("div_omenuxz");
    $(this).closest(".div_omenu2").addClass("div_omenu2xz");
    }
    });
    $(".div_otop").on("click",function(){
    if($(this).closest(".div_omenu").hasClass("div_omenuxz")){
    $(this).closest(".div_omenu").removeClass("div_omenuxz");
    }else{
    $(".div_omenu").removeClass("div_omenuxz");
    $(this).closest(".div_omenu").addClass("div_omenuxz");
    }
    });
    $(".Hui-aside .menu_dropdown dt").on("click",function(){
    if(!$(this).hasClass("selected")){
    $(this).parent().find(".div_omenu").removeClass("div_omenuxz");
    $(this).parent().find(".div_omenu2").removeClass("div_omenu2xz");
    }
    });


    });
    </script>
    <body>
    <!-- 左侧导航 -->
    <aside class="Hui-aside">
    <input runat="server" id="divScrollValue" type="hidden" value="" />
    <div class="menu_dropdown bk_2">
    <dl>
    <dt>
    <i class="Hui-iconfont">&#xe643;</i>企业信息数据
    <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
    </dt>
    <dd>
    <ul>
    <li><a data-href="${ly }/tradeclique/clique/basic-information" data-title="基本信息" href="javascript:void(0)">基本信息</a></li>
    <%--  	 <div class="div_omenu">
             <div  class="div_otop">所属企业<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>
             <ul>
                 <!-- 循环开始 -->
                 <c:forEach items="${list }" varStatus="index" var="list">
                     <li><a data-href="${ly }/tradeclique/welcomecompany?companyId=${list.uid }" data-title="${list.companyname}" href="javascript:void(0)">${list.companyname}</a></li>
                 </c:forEach>
                 <!-- 循环结束 -->
             </ul>
         </div>
    --%>
    </ul>
    <div class="div_omenu">
    <div  class="div_otop">组织架构<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>
    <ul>
    <li><a data-href="${ly }/company/system/department-list" data-title="组织架构" href="javascript:void(0)">组织架构</a></li>
    <li><a data-href="${ly }/tradeclique/information/user-list" data-title="部门人员" href="javascript:void(0)">部门人员</a></li>
    </ul>
    </div>
    <ul>
    <li><a data-href="${ly }/tradeclique/product/mequipment-list" data-title="主要设备设施" href="javascript:void(0)">主要设备设施</a></li>
    <li><a data-href="${ly }/tradeclique/product/material-list" data-title="主要原辅材料" href="javascript:void(0)">主要原辅材料</a></li>
    <li><a data-href="${ly }/tradeclique/product/product-list" data-title="主要生产产品" href="javascript:void(0)">主要生产产品</a></li>
    <li><a data-href="${ly }/tradeclique/process/process-list" data-title="生产工艺流程图" href="javascript:void(0)">生产工艺流程图</a></li>
    <li><a data-href="${ly }/tradeclique/company/company-list" data-title="所属企业" href="javascript:void(0)">所属企业</a></li>
    </ul>
    <%--
        <ul>
          <c:if test="${session_user.id != 788}">
          <li><a data-href="${ly }/village/company/company-list" data-title="全部企业" href="javascript:void(0)">全部企业</a></li>
          <li><a data-href="${ly }/village/company/company-tab" data-title="企业分类" href="javascript:void(0)">企业分类</a></li>
          <li><a data-href="${ly }/village/company/company-list?isKey=1&sk=0" data-title="重点企业" href="javascript:void(0)">重点企业</a></li>
          </c:if>
          <c:if test="${session_user.id == 788}">
          <li><a data-href="${ly }/village/company/company-tab" data-title="企业分类" href="javascript:void(0)">企业分类</a></li>
          <li><a data-href="${ly }/village/company/company-list" data-title="各地企业" href="javascript:void(0)">各地企业</a></li>
          </c:if>
        </ul>
       --%>
    <%-- <div class="div_omenu">
      <div  class="div_otop">信息汇总<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>
      <ul>
        <li><a data-href="${ly }/village/warning/sperson-list" data-title="持证上岗" href="javascript:void(0)">持证上岗</a></li>
        <li><a data-href="${ly }/village/warning/sequipment-list" data-title="特种设备检测" href="javascript:void(0)">特种设备检测</a></li>
        <li><a data-href="${ly }/village/warning/detection-list" data-title="职业卫生检测" href="javascript:void(0)">职业卫生检测</a></li>
        <li><a data-href="${ly }/village/warning/lightning-list" data-title="防雷防静电" href="javascript:void(0)">防雷防静电</a></li>
        <li><a data-href="${ly }/village/evaluate/evaluation-list" data-title="评价评估" href="javascript:void(0)">评价评估</a></li>
        <li><a data-href="${ly }/village/evaluate/zheng-list" data-title="各类许可证" href="javascript:void(0)">各类许可证</a></li>
        <li><a data-href="${ly }/company/tables/tab-yjlist" data-title="应急预案" href="javascript:void(0)">应急预案</a></li>
      </ul>
    </div> --%>
    </dd>
    </dl>
    <dl>
    <dt>
    <i class="Hui-iconfont">&#xe63c;</i>风险分级管控
    <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
    </dt>
    <dd>
    <ul>
    <li><a data-href="${ly }/village/safety-system/control-listmenu?level=红色" data-title="重大风险汇总" href="javascript:void(0)">重大风险汇总</a></li>
    <li><a data-href="${ly }/village/safety-system/control-listmenu?level=橙色" data-title="较大风险汇总" href="javascript:void(0)">较大风险汇总</a></li>
    <li><a data-href="${ly }/village/safety-system/statistics-list" data-title="数据分析" href="javascript:void(0)">数据分析</a></li>
    </ul>
    </dd>
    </dl>
    <dl>
    <dt>
    <i class="Hui-iconfont">&#xe687;</i>隐患排查治理
    <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
    </dt>
    <dd>
    <div class="div_omenu">
    <div  class="div_otop">检查设置与实施<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>
    <ul>
    <%-- <li><a data-href="${ly }/tradeclique/model-list-cx?flag=1&type=1" data-title="企业自查" href="javascript:void(0)">企业自查</a></li> --%>
    <li><a data-href="${ly }/village/check-list-cx?flag=4" data-title="行政检查" href="javascript:void(0)">行政检查</a></li>
    <li><a data-href="${ly }/village/check-list-cx?flag=3" data-title="部门抽查" href="javascript:void(0)">部门抽查</a></li>
    </ul>
    </div>
    <ul>
    <li><a data-href="${ly }/village/check-list?flag=4" data-title="排查治理记录" href="javascript:void(0)">排查治理记录</a></li>
    <%--  <li><a data-href="${ly }/village/check-add-cho" data-title="检查表设置" href="javascript:void(0)">检查表设置</a></li>
     <li><a data-href="${ly }/village/rectification-list?flag=1" data-title="隐患治理复查" href="javascript:void(0)">隐患治理复查</a></li>
     <li><a data-href="${ly }/village/check-list?flag=1" data-title="排查治理记录" href="javascript:void(0)">排查治理记录</a></li>
     <li><a data-href="${ly }/village/evaluate/lawDoc-list?flag=4" data-title="上传图片视频资料" href="javascript:void(0)">上传图片视频资料</a></li>
     <li><a data-href="${ly }/village/danger/opinion-list?type=1&flag2=4" data-title="检查文书" href="javascript:void(0)">检查文书</a></li> --%>
    <li><a data-href="${ly }/village/danger-collect" data-title="隐患统计汇总" href="javascript:void(0)">隐患统计汇总</a></li>
    <li><a data-href="${ly }/village/danger-chart" data-title="隐患数据分析" href="javascript:void(0)">隐患数据分析</a></li>
    <%-- <li><a data-href="${ly }/html/about.html" data-title="新手指南" href="javascript:void(0)">新手指南</a></li> --%>
    </ul>
    </dd>
    </dl>
    <dl>
    <dt>
    <i class="Hui-iconfont">&#xe687;</i>风险研判公告
    <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
    </dt>
    <dd>
    <ul>
    <li><a data-href="${ly }/villageAdd/study/promise-collect" data-title="安全承诺公告" href="javascript:void(0)">承诺公告公示汇总</a></li>
    </ul>
    </dd>
    </dl>

      <dl class="list_an">
      <dt>
      <i class="Hui-iconfont">&#xe639;</i>安全生产标准化
      <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
      </dt>
      <dd>
      <ul>
      <%--<li><a data-href="${ly }/company/threeLeft?leftBasic=8" data-title="安全管理"--%>
      <%--href="javascript:void(0)">安全管理</a>--%>
      <%--</li>--%>

      <%--<li><a data-href="${ly }/company/threeLeft?leftBasic=9" data-title="安全台账"--%>
      <%--href="javascript:void(0)">安全台账</a>--%>
      <%--</li>--%>

      <!-- 旧版(废弃) <li><a data-href="${ly }/company/tables/tab-biaozhun" data-title="安全标准化"
      href="javascript:void(0)">安全标准化</a></li> -->
      <li><a data-href="${ly }/api/safety_Standard/findAll?parendId=0&flag=1" data-title="运行操作系统"
      href="javascript:void(0)">运行操作系统</a></li>

      <%--<li><a data-href="${ly }/company/threeLeft?leftBasic=18" data-title="设备设施管理"--%>
      <%--href="javascript:void(0)">设备设施管理</a></li>--%>
      <%--<li><a data-href="${ly }/company/threeLeft?leftBasic=17&userName=${userName}" data-title="教育培训"--%>
      <%--href="javascript:void(0)">教育培训</a></li>--%>
      <%--<li><a target="_blank" data-href="http://exam.dicarl.com/index.php?user-app-login&mobile=${userName }" data-title="安全培训"  >安全培训</a></li>--%>


      <%--<li><a data-href="${ly }/company/threeLeft?leftBasic=8" data-title="责任制"
             href="javascript:void(0)">安全台账</a></li>
      <li><a data-href="${ly }/company/threeLeft?leftBasic=9" data-title="管理档案"
             href="javascript:void(0)">安全标准化</a></li>--%>


      <%--<li><a data-href="${ly }/company/tables/tab-list?isType=1" data-title="安全生产管理制度"
      href="javascript:void(0)">管理制度</a></li>
      <li><a data-href="${ly }/company/threeLeft?leftBasic=8" data-title="责任制"
      href="javascript:void(0)">责任制</a></li>
      <li><a data-href="${ly }/company/threeLeft?leftBasic=9" data-title="管理档案"
      href="javascript:void(0)">管理档案</a></li>--%>
      </ul>


      <%--<div class="div_omenu">--%>
      <%--<div class="div_otop">责任制<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>--%>
      <%--<ul>--%>
      <%--<li><a data-href="${ly }/company/tables/tab-list?isType=0" data-title="安全生产责任制"--%>
      <%--href="javascript:void(0)">责任制</a></li>--%>
      <%--<li><a data-href="${ly }/company/information/information6" data-title="安全生产管理网络图"--%>
      <%--href="javascript:void(0)">管理网络图</a></li>--%>
      <%--</ul>--%>
      <%--</div>--%>
      <%--<div class="div_omenu2">--%>
      <%--<div class="div_otop2">管理档案<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>--%>
      <%--<div class="div_omenu">--%>
      <%--<div class="div_otop">教育培训<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>--%>
      <%--<ul>--%>
      <%--<li><a data-href="${ly }/company/train/special-list?spFlag=0" style="padding-left:55px;"--%>
      <%--data-title="持证上岗" href="javascript:void(0)">持证上岗</a></li>--%>
      <%--<li><a data-href="${ly }/company/tables/tab-anlist" style="padding-left:55px;"--%>
      <%--data-title="安全生产教育台账" href="javascript:void(0)">教育台账</a></li>--%>
      <%--&lt;%&ndash; <li><a data-href="${ly }/company/tables/tab-list?isType=2" data-title="培训需求调查表" href="javascript:void(0)">培训需求调查表</a></li>--%>
      <%--<li><a data-href="${ly }/company/tables/tab-list?isType=3" data-title="年度培训计划" href="javascript:void(0)">年度培训计划</a></li>--%>
      <%--<li><a data-href="${ly }/company/tables/tab-list?isType=4" data-title="变换工种/四新安全教育卡" href="javascript:void(0)">变换工种安全教育卡</a></li>--%>
      <%--<li><a data-href="${ly }/company/tables/tab-list?isType=5" data-title="复工安全教育卡" href="javascript:void(0)">复工安全教育卡</a></li>--%>
      <%--<li><a data-href="${ly }/company/tables/tab-list?isType=6" data-title="三级安全教育卡" href="javascript:void(0)">三级安全教育卡</a></li>--%>
      <%--<li><a data-href="${ly }/company/tables/tab-list?isType=7" data-title="培训记录表" href="javascript:void(0)">培训记录表</a></li> &ndash;%&gt;--%>
      <%--</ul>--%>
      <%--</div>--%>
      <%--<div class="div_omenu">--%>
      <%--<div class="div_otop">设备设施管理<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>--%>
      <%--<ul>--%>
      <%--<li><a data-href="${ly }/company/product/mequipment-list" style="padding-left:55px;"--%>
      <%--data-title="主要设备清单" href="javascript:void(0)">主要设备清单</a></li>--%>
      <%--<li><a data-href="${ly }/company/sequipment/sequipment-list" style="padding-left:55px;"--%>
      <%--data-title="特种设备检测" href="javascript:void(0)">特种设备检测</a></li>--%>
      <%--<li><a data-href="${ly }/company/product/lightning-list" style="padding-left:55px;"--%>
      <%--data-title="防雷防静电" href="javascript:void(0)">防雷防静电</a></li>--%>
      <%--<li><a data-href="${ly }/company/safety-facilities" style="padding-left:55px;"--%>
      <%--data-title="安全设施登记台账" href="javascript:void(0)">安全设施登记台账</a></li>--%>
      <%--&lt;%&ndash; <li><a data-href="${ly }/company/tables/tab-list?isType=9" style="padding-left:55px;" data-title="年度综合检维修计划" href="javascript:void(0)">年度综合检维修计划</a></li>--%>
      <%--<li><a data-href="${ly }/company/tables/tab-list?isType=10" style="padding-left:55px;" data-title="手持电动工具绝缘电阻值测量记录表" href="javascript:void(0)">手持工具绝缘测量表</a></li>--%>
      <%--<li><a data-href="${ly }/company/tables/tab-list?isType=11" style="padding-left:55px;" data-title="移动电气设备绝缘电阻测试表" href="javascript:void(0)">移动设备绝缘测试表</a></li> &ndash;%&gt;--%>
      <%--</ul>--%>
      <%--</div>--%>
      <%--<ul class="oul">--%>
      <%--<li><a data-href="${ly }/company/evaluate/evaluation-list?flag=0" data-title="评价评估档案"--%>
      <%--href="javascript:void(0)">评价评估档案</a></li>--%>
      <%--<li><a data-href="${ly }/company/evaluate/zheng-list" data-title="各类许可证档案"--%>
      <%--href="javascript:void(0)">各类许可证档案</a></li>--%>
      <%--<li><a data-href="${ly }/company/information/information3" data-title="两重点一重大"--%>
      <%--href="javascript:void(0)">两重点一重大</a></li>--%>
      <%--&lt;%&ndash; <li><a data-href="${ly }/company/tables/tab-yjlist" data-title="应急预案" href="javascript:void(0)">应急预案</a></li> &ndash;%&gt;--%>
      <%--<li><a data-href="${ly }/company/tables/tab-weilist" data-title="危险作业证"--%>
      <%--href="javascript:void(0)">危险作业证</a></li>--%>
      <%--<li><a data-href="${ly }/company/tables/tab-jianglist" data-title="安全奖惩"--%>
      <%--href="javascript:void(0)">安全奖惩</a></li>--%>
      <%--<li><a data-href="${ly }/company/tables/tab-shilist" data-title="事故报告和处理"--%>
      <%--href="javascript:void(0)">事故报告和处理</a></li>--%>
      <%--</ul>--%>
      <%--</div>--%>


      <%-- <div class="div_omenu">
          <div  class="div_otop">应急救援<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>
          <ul>
            <li><a data-href="${ly }/company/tables/tab-list?isType=" data-title="事故应急救援预案演练计划" href="javascript:void(0)">应急救援预案演练计划</a></li>
            <li><a data-href="${ly }/company/tables/tab-list?isType=" data-title="事故应急救援预案演练记录" href="javascript:void(0)">应急救援预案演练记录</a></li>
            <li><a data-href="${ly }/company/tables/tab-list?isType=" data-title="事故应急救援预案演练评估报告" href="javascript:void(0)">预案演练评估报告</a></li>
            <li><a data-href="${ly }/company/tables/tab-list?isType=27" data-title="消防器材“三定”管理台帐" href="javascript:void(0)">消防器材“三定”管理台帐</a></li>
            <li><a data-href="${ly }/company/tables/tab-list?isType=28" data-title="应急器材物资检查记录表" href="javascript:void(0)">应急器材物资检查表</a></li>
          </ul>
        </div> --%>

      <%--
      <div class="div_omenu">
        <div  class="div_otop">危险作业证<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>
        <ul>
          <li><a data-href="${ly }/company/tables/tab-list?isType=12" data-title="动火作业证" href="javascript:void(0)">动火作业证</a></li>
          <li><a data-href="${ly }/company/tables/tab-list?isType=13" data-title="受限空间安全作业证" href="javascript:void(0)">受限空间安全作业证</a></li>
          <li><a data-href="${ly }/company/tables/tab-list?isType=14" data-title="盲板抽堵安全作业证" href="javascript:void(0)">盲板抽堵安全作业证</a></li>
          <li><a data-href="${ly }/company/tables/tab-list?isType=15" data-title="高处安全作业证" href="javascript:void(0)">高处安全作业证</a></li>
          <li><a data-href="${ly }/company/tables/tab-list?isType=16" data-title="吊装安全作业证" href="javascript:void(0)">吊装安全作业证</a></li>
          <li><a data-href="${ly }/company/tables/tab-list?isType=17" data-title="临时用电安全作业证" href="javascript:void(0)">临时用电安全作业证</a></li>
          <li><a data-href="${ly }/company/tables/tab-list?isType=18" data-title="动土安全作业证" href="javascript:void(0)">动土安全作业证</a></li>
          <li><a data-href="${ly }/company/tables/tab-list?isType=19" data-title="断路安全作业证" href="javascript:void(0)">断路安全作业证</a></li>
        </ul>
      </div> --%>

      <%-- <div class="div_omenu">
        <div  class="div_otop">安全奖惩<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>
        <ul>
          <li><a data-href="${ly }/company/tables/tab-list?isType=22" data-title="安全责任制考核和奖惩台帐" href="javascript:void(0)">责任制考核和奖惩台帐</a></li>
          <li><a data-href="${ly }/company/tables/tab-list?isType=23" data-title="安全工作考核和奖惩台帐" href="javascript:void(0)">工作考核和奖惩台帐</a></li>
        </ul>
      </div> --%>
      <%-- <div class="div_omenu">
        <div  class="div_otop">事故报告和处理<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>
        <ul>
          <li><a data-href="${ly }/company/tables/tab-list?isType=24" data-title="工伤事故档案" href="javascript:void(0)">工伤事故档案</a></li>
          <li><a data-href="${ly }/company/tables/tab-list?isType=25" data-title="工伤事故调查及处理报告" href="javascript:void(0)">工伤事故调查及处理</a></li>
          <li><a data-href="${ly }/company/tables/tab-list?isType=26" data-title="工伤事故台帐" href="javascript:void(0)">工伤事故台帐</a></li>
        </ul>
      </div> --%>


      </dd>
      </dl>
    <dl>
    <dt>
    <i class="Hui-iconfont">&#xe687;</i>重大危险源长制
    <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
    </dt>
    <dd>
    <ul>
    <li><a data-href="${ly }/villageAdd/source/company-list" data-title="全部企业" href="javascript:void(0)">全部企业</a></li>
    <li><a data-href="${ly }/villageAdd/source/video" data-title="在线监测" href="javascript:void(0)">在线监测</a></li>
    <li><a data-href="${ly }/villageAdd/source/company-map" data-title="企业分布" href="javascript:void(0)">企业分布</a></li>
    <li><a data-href="${ly }/villageAdd/source/danger-collect" data-title="隐患排查治理" href="javascript:void(0)">隐患排查治理</a></li>
    <li><a data-href="${ly }/villageAdd/source/danger-chart" data-title="分类统计" href="javascript:void(0)">分类统计</a></li>
    </ul>
    </dd>
    </dl>

    <%--<dl>--%>
    <%--<dt>--%>
    <%--<i class="Hui-iconfont">&#xe70c;</i>安全监察与监管--%>
    <%--<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>--%>
    <%--</dt>--%>
    <%--<dd>--%>
    <%--<ul>--%>
    <%--<li><a data-href="${ly }/village/company/company-list-zg?cisDanger=1" data-title="重大危险源" onclick="javascript:void(0)">重大危险源</a></li>--%>
    <%--<li><a data-href="${ly }/tradeclique/dangerouswork-list" data-title="高危作业" onclick="javascript:void(0)">高危作业</a></li>--%>
    <%--<li><a data-href="${ly }/tradeclique/certified?spFlag=0" data-title="持证上岗" href="javascript:void(0)">持证上岗</a></li>--%>
    <%--<li><a data-href="${ly }/tradeclique/sequipment/sequipment-list" data-title="特种设备检测" href="javascript:void(0)">特种设备检测</a></li>--%>
    <%--<li><a data-href="${ly }/tradeclique/detection-list" data-title="职业卫生检测" href="javascript:void(0)">职业卫生检测</a></li>--%>
    <%--<li><a data-href="${ly }/tradeclique/clique/lightning-list" data-title="防雷防静电" href="javascript:void(0)">防雷防静电</a></li>--%>
    <%--<li><a data-href="${ly }/tradeclique/clique/evaluation-list" data-title="评价评估" href="javascript:void(0)">评价评估</a></li>--%>
    <%--<li><a data-href="${ly }/tradeclique/clique/license-list" data-title="各类许可证" href="javascript:void(0)">各类许可证</a></li>--%>
    <%--<li><a data-href="${ly }/tradeclique/clique/emergency-plan" data-title="应急预案" href="javascript:void(0)">应急预案</a></li>--%>
    <%--</ul>--%>
    <%--</dd>--%>
    <%--</dl>--%>


    <dl>
    <dt>
    <i class="Hui-iconfont">&#xe70c;</i>行政执法检查
    <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
    </dt>
    <dd>
    <ul>
    <%--<li><a data-href="${ly }/village/check-list-cx?flag=2" data-title="执法检查" href="javascript:void(0)">执法检查</a></li>
     <li><a data-href="${ly }/village/rectification-list?flag=2" data-title="执法复查" href="javascript:void(0)">执法复查</a></li>
    <li><a data-href="${ly }/village/check-list?flag=2" data-title="执法记录" href="javascript:void(0)">执法记录</a></li>
    <li><a data-href="${ly }/village/danger/opinion-list?type=3&flag2=2" data-title="行政处罚" href="javascript:void(0)">行政处罚</a></li>
    <li><a data-href="${ly }/village/opinion-show?flag2=2" data-title="执法档案" href="javascript:void(0)">执法档案</a></li>
    <li><a data-href="${ly }/village/evaluate/lawDoc-list?flag=2" data-title="上传图片视频资料" href="javascript:void(0)">上传图片视频资料</a></li>
    <li><a data-href="${ly }/district/off-list" data-title="执法人员信息库" href="javascript:void(0)">执法人员信息库</a></li>--%>
    <li><a data-href="${ly }/village/opinion-show?flag2=2" data-title="执法档案" href="javascript:void(0)">执法档案</a></li>
    <%--<li><a data-href="${ly }/village/evaluate/lawDoc-list?flag=2" data-title="执法图片视频资料" href="javascript:void(0)">执法图片视频资料</a></li>--%>
    </ul>
    </dd>
    </dl>
    <%-- <dl>
      <dt>
        <i class="Hui-iconfont">&#xe70c;</i>隐患统计分析系统
        <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
      </dt>
      <dd>
        <ul>
          <li><a data-href="${ly }/village/danger-collect" data-title="隐患统计汇总" href="javascript:void(0)">隐患统计汇总</a></li>
          <li><a data-href="${ly }/village/danger-chart" data-title="隐患数据分析" href="javascript:void(0)" style="color:#fff;">隐患数据分析</a></li>
        </ul>
      </dd>
    </dl>

    <%-- <dl>
      <dt>
        <i class="Hui-iconfont">&#xe6cd;</i>危险有害因素检索
        <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
      </dt>
      <dd>
        <ul>
          <li><a data-href="${ly }/html/danger.html" data-title="重大事故隐患判定标准" href="javascript:void(0)">重大事故隐患判定标准</a></li>
          <li><a data-href="${ly }/fore/notice-show?id=543" data-title="重大火灾隐患判定方法" href="javascript:void(0)">重大火灾隐患判定方法</a></li>
          <li><a data-href="${ly }/fore/danger/wei-list" data-title="重大危险源辨识目录" href="javascript:void(0)">重大危险源辨识目录</a></li>
          <li><a data-href="${ly }/fore/danger/danger-list" data-title="危化品目录" href="javascript:void(0)">危化品目录</a></li>
          <li><a data-href="${ly }/fore/danger/bao-list" data-title="易制爆危险化学品" href="javascript:void(0)">易制爆危险化学品</a></li>
          <li><a data-href="${ly }/fore/danger/du-list" data-title="易制毒化学品" href="javascript:void(0)">易制毒化学品</a></li>
          <li><a data-href="${ly }/fore/danger/zhi-list" data-title="职业病有害因素" href="javascript:void(0)">职业病有害因素</a></li>
          <li><a data-href="${ly }/company/trouble/trouble-information" data-title="隐患排查治理标准信息库" href="javascript:void(0)">隐患排查治理标准</a></li>
        </ul>
      </dd>
    </dl> --%>
    <dl>
    <dt>
    <i class="Hui-iconfont">&#xe61d;</i>信息推送互动
    <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
    </dt>
    <dd>
    <ul>
    <li><a data-href="${ly }/village/sendMessageList" data-title="信息发送列表" href="javascript:void(0)">信息发送列表</a></li>
    <li><a data-href="${ly }/village/reviceMessageList" data-title="信息接收列表" href="javascript:void(0)">信息接收列表</a></li>
    <li><a data-href="${ly }/village/addMessages" data-title="信息推送" href="javascript:void(0)">信息推送</a></li>
    <li><a data-href="${ly }/village/groupIndex" data-title="群组设置" href="javascript:void(0)">企业群组设置</a></li>
    </ul>
    </dd>
    </dl>

    <dl>
    <dt>
    <i class="Hui-iconfont">&#xe61d;</i>账号设置管理
    <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
    </dt>
    <dd>
    <ul>
    <li><a data-href="${ly }/company/threeLeft?leftBasic=11" data-title="在线监测设置"
    href="javascript:void(0)">在线监测设置</a></li>
    <li><a data-href="${ly }/village/to_monitor-list?flag=1" data-title="实时监控设置"
    href="javascript:void(0)">实时监控设置</a></li>
    <li><a data-href="${ly }/village/system/change-psw" data-title="修改密码" href="javascript:void(0)">修改密码</a></li>
    <li><a data-href="${ly }/company/system/change-userName" data-title="修改登录名" href="javascript:void(0)">修改登录名</a></li>
    </ul>
    </dd>
    </dl>

      <dl>
      <dt>
      <i class="Hui-iconfont">&#xe653;</i>领导关注信息
      <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
      </dt>
      <dd>
      <ul>
      <li><a data-href="javascript:void(0)" data-title="一周监管动态" href="javascript:void(0)">一周监管动态</a></li>
      <li><a data-href="${ly }/village/danger-collect-guanzhu?isZhongDa=1" data-title="隐患排查治理数据" href="javascript:void(0)">隐患排查治理数据</a></li>
      <li><a data-href="${ly }/village/danger-collect-guanzhu?isZhongDa=2" data-title="重大事故隐患数据" href="javascript:void(0)">重大事故隐患数据</a></li>
      <li><a data-href="${ly }/village/danger-collect-guanzhu?isZhongDa=3" data-title="行政执法数据" href="javascript:void(0)">行政执法数据</a></li>
      </ul>
      </dd>
      </dl>
    </div>
    </aside>
    <div id="winpop" style="display:none;" >
    <div class="title">
    您有新的信息！<span class="close" title="关闭" id="toclose">X</span>
    </div>
    <div class="con" id="shwomsgTips" style="cursor:pointer;height:60px;line-height: 60px;"></div>
    <div class="con" id="shwomsg" style="cursor:pointer;height:30px;line-height:30px;">1条未读信息</div>
    </div>
    <script type="text/javascript" src="${ly }/js/plugins/websocket/swfobject.js"></script>
    <script type="text/javascript" src="${ly }/js/plugins/websocket/web_socket.js"></script>
    <script type="text/javascript">

    getUnreadMsg();
    var intervalIndex2 = setInterval(function() {
    getUnreadMsg();
    }, 1000 * 60*5);
    function getUnreadMsg() {
    $.ajax({
    url : "<c:url value='/village/getUnReadMessage'/>",
    type : 'post',
    success : function(data) {
    if (undefined != data.message) {
    tips_pop(data.message.id, data.message.title, data.message.createName, data.message.createTime)
    }
    }
    });
    }
    var websocket = null;
    var userno="${loginUserId}";
    //判断当前浏览器是否支持WebSocket
    WEB_SOCKET_SWF_LOCATION = "${ly }/js/plugins/websocket/WebSocketMain.swf";
    if(window.WebSocket){
    websocket = new WebSocket("ws://"+location.hostname+":"+location.port+"/village/websocket/"+userno);
    }
    else {
    //alert("当前版本不支持");
    }


    //连接发生错误的回调方法
    websocket.onerror = function () {
    //alert("error");
    //setMessageInnerHTML("WebSocket连接发生错误");
    };


    //连接成功建立的回调方法
    websocket.onopen = function () {
    clearInterval(intervalIndex2);
    }


    //接收到消息的回调方法
    websocket.onmessage = function (event) {
    //tips_pop();
    //setMessageInnerHTML(event.data);
    //alert(event.data);
    //tips_pop(event.data);
    if("" != $.trim(event.data)){
    $.ajax({
    url : "<c:url value='/village/getMessageById'/>",
    type : 'post',
    data : {
    messageId : event.data,
    from : 1
    },
    success : function(data){
    var title = "";
    if(undefined != data.title ){
    title = data.title;
    }
    tips_pop(event.data,title,data.createName,data.createTime);
    }
    });
    }else{

    }

    }


    //连接关闭的回调方法
    websocket.onclose = function () {
    //setMessageInnerHTML("WebSocket连接关闭");
    //alert("close");
    }


    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
    //closeWebSocket();
    }
    //关闭WebSocket连接
    function closeWebSocket() {
    websocket.close();
    }

    </script>

    <script type="text/javascript">
    $.ajax({
    url : "<c:url value='/village/getUnReadMessage'/>",
    type : 'post',
    success : function(data){
    if(undefined != data.message){
    tips_pop(data.message.id,data.message.title,data.message.createName,data.message.createTime)
    }
    }
    });
    </script>
    <script type="text/javascript">
    function tips_pop(messageId,title,createName,createTime){
    $("#winpop").show();
    $("#shwomsg").attr("core-msgid",messageId);
    if("" != title){
    $("#shwomsg").html(title);
    }
    $("#shwomsgTips").html(createName+"在"+createTime+"发来信息：");
    $("#toclose").unbind("click").bind("click",function(){
    $("#winpop").hide();

    $.ajax({
    url : "<c:url value='/village/getUnReadMessage'/>",
    type : 'post',
    success : function(data){
    if(undefined != data.message){
    tips_pop(data.message.id,data.message.title,data.message.createName,data.message.createTime)
    }
    }
    });
    });

    $("#shwomsg").unbind("click").bind("click",function(){
    var messageId = $(this).attr("core-msgid");

    window.open("<c:url value='/village/messageDetail?messageId="+messageId+"'/>");
    });

    }

    </script>
    </body>
    </html>