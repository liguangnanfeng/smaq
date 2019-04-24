<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
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
          <i class="Hui-iconfont">&#xe643;</i>企业数据库
          <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
          <ul>
            <li><a data-href="${ly }/village/company/company-list" data-title="全部企业" href="javascript:void(0)">全部企业</a></li>
            <li><a data-href="${ly }/village/company/company-tab" data-title="企业分类" href="javascript:void(0)">企业分类</a></li>
            <div class="div_omenu">
                <div  class="div_otop">各地企业<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>
                <ul>
                    <!-- 循环开始 -->
                    <c:forEach items="${list }" varStatus="index" var="list">
                        <li><a data-href="${ly }/town/cx/wel?districtId=${list.code }" data-title="${list.name}" href="javascript:void(0)">${list.name}</a></li>
                    </c:forEach>
                    <!-- 循环结束 -->
                </ul>
            </div>
           <div class="div_omenu">
                <div  class="div_otop">部门、人员管理<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>
                <ul>
                    <!-- 循环开始 -->
                    <c:forEach items="${list1 }" varStatus="index" var="list1">
                        <li><a data-href="${ly }/country/welcometrade?tradeId=${list1.code }" data-title="${list1.name}" href="javascript:void(0)">${list1.name}</a></li>
                    </c:forEach>
                    <!-- 循环结束 -->
                </ul>
            </div>
          </ul>
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
              <li><a data-href="${ly }/village/check-list-cx?flag=4" data-title="行政检查" href="javascript:void(0)">行政检查</a></li>
              <li><a data-href="${ly }/village/check-list-cx?flag=3" data-title="部门抽查" href="javascript:void(0)">部门抽查</a></li>
            </ul>
          </div>
          <ul>
            <li><a data-href="${ly }/village/check-list?flag=4" data-title="排查治理记录" href="javascript:void(0)">排查治理记录</a></li>
            <%-- <li><a data-href="${ly }/village/check-add-cho" data-title="检查表设置" href="javascript:void(0)">检查表设置</a></li>
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
          <i class="Hui-iconfont">&#xe687;</i>风险研判与承诺公告
          <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
          <ul>
            <li><a data-href="${ly }/villageAdd/study/promise-collect" data-title="安全承诺公告" href="javascript:void(0)">承诺公告公示汇总</a></li>
          </ul>
        </dd>
      </dl>
      <dl>
        <dt>
          <i class="Hui-iconfont">&#xe687;</i>重大危险源源长制
          <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
          <ul>
            <li><a data-href="${ly }/villageAdd/source/company-list" data-title="全部企业" onclick="javascript:void(0)">全部企业</a></li>
            <li><a data-href="${ly }/villageAdd/source/video" data-title="在线监测" href="javascript:void(0)">在线监测</a></li>
            <li><a data-href="${ly }/villageAdd/source/company-map" data-title="企业分布" href="javascript:void(0)">企业分布</a></li>
            <li><a data-href="${ly }/villageAdd/source/danger-collect" data-title="隐患排查治理" href="javascript:void(0)">隐患排查治理</a></li>
            <li><a data-href="${ly }/villageAdd/source/danger-chart" data-title="分类统计" href="javascript:void(0)">分类统计</a></li>
          </ul>
        </dd>
      </dl>
      
      <dl>
        <dt>
          <i class="Hui-iconfont">&#xe70c;</i>安全监察监管
          <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
          <ul>
              <li><a data-href="${ly }/village/company/company-list-zg?cisDanger=1" data-title="重大危险源" href="javascript:void(0)">重大危险源</a></li>
              <li><a data-href="${ly }/village/company/company-list-zg?danger=1" data-title="高危作业" onclick="javascript:void(0)">高危作业</a></li>
              <%-- <li><a data-href="${ly }/village/warning/sperson-list" data-title="重大危险源" onclick="javascript:alert('暂未开通');">重大危险源</a></li>
              <li><a data-href="${ly }/village/warning/sperson-list" data-title="高危作业" onclick="javascript:alert('暂未开通');">高危作业</a></li> --%>
              <li><a data-href="${ly }/village/warning/sperson-list" data-title="持证上岗" href="javascript:void(0)">持证上岗</a></li>
              <li><a data-href="${ly }/village/warning/sequipment-list" data-title="特种设备检测" href="javascript:void(0)">特种设备检测</a></li>
              <li><a data-href="${ly }/village/warning/detection-list" data-title="职业卫生检测" href="javascript:void(0)">职业卫生检测</a></li>
              <li><a data-href="${ly }/village/warning/lightning-list" data-title="防雷防静电" href="javascript:void(0)">防雷防静电</a></li>
              <li><a data-href="${ly }/village/evaluate/evaluation-list" data-title="评价评估" href="javascript:void(0)">评价评估</a></li>
              <li><a data-href="${ly }/village/evaluate/zheng-list" data-title="各类许可证" href="javascript:void(0)">各类许可证</a></li>
              <li><a data-href="${ly }/company/tables/tab-yjlist" data-title="应急预案" href="javascript:void(0)">应急预案</a></li>
          </ul>
        </dd>
      </dl>
      
      <dl>
        <dt>
          <i class="Hui-iconfont">&#xe70c;</i>行政执法
          <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
          <ul>
            <li><a data-href="${ly }/village/check-list-cx?flag=2" data-title="执法检查" href="javascript:void(0)">执法检查</a></li>
            <%-- <li><a data-href="${ly }/village/check-add-cho?flag=2" data-title="执法检查" href="javascript:void(0)">执法检查</a></li> --%>
            <%-- <li><a data-href="${ly }/village/rectification-list?flag=2" data-title="执法复查" href="javascript:void(0)">执法复查</a></li> --%>
            <li><a data-href="${ly }/village/check-list?flag=2" data-title="执法记录" href="javascript:void(0)">执法记录</a></li>
            <li><a data-href="${ly }/village/danger/opinion-list?type=3&flag2=2" data-title="行政处罚" href="javascript:void(0)">行政处罚</a></li>
            <li><a data-href="${ly }/village/opinion-show?flag2=2" data-title="执法档案" href="javascript:void(0)">执法档案</a></li>
            <li><a data-href="${ly }/village/evaluate/lawDoc-list?flag=2" data-title="上传图片视频资料" href="javascript:void(0)">上传图片视频资料</a></li>
            <li><a data-href="${ly }/district/off-list" data-title="执法人员信息库" href="javascript:void(0)">执法人员信息库</a></li>
          </ul>
        </dd>
      </dl>
      <dl>
        <dt>
          <i class="Hui-iconfont">&#xe653;</i>领导关注
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
      </dl> --%>
      
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
       <%-- <dl>
        <dt>
          <i class="Hui-iconfont">&#xe70b;</i>监管动态
          <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
          <ul>
            <li><a data-href="${ly }/village/notice/notice-add" data-title="添加公告" href="javascript:void(0)">添加公告</a></li>
            <li><a data-href="${ly }/village/notice/notice-list" data-title="公告列表" href="javascript:void(0)">公告列表</a></li>
          </ul>
        </dd>
      </dl> --%>
      <dl>
        <dt>
          <i class="Hui-iconfont">&#xe61d;</i>系统设置
          <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
          <ul>
            <li><a data-href="${ly }/village/system/change-psw" data-title="修改密码" href="javascript:void(0)">修改密码</a></li>
            <li><a data-href="${ly }/company/system/change-userName" data-title="修改登录名" href="javascript:void(0)">修改登录名</a></li>
          </ul>
            <%-- <li><a data-href="${ly }/village/tuiSet" data-title="推送设置" href="javascript:void(0)">推送设置</a></li> --%>
        </dd>
      </dl>
    </div>
  </aside>
</body>
</html>