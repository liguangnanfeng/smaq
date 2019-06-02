<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<script type="text/javascript">
  $(document).ready(function() {
    $("body").on("click",".Hui-aside .menu_dropdown li a", function() {
      $(".Hui-aside .menu_dropdown li a").removeClass("l_active");
      $(this).addClass("l_active");
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
          <i class="Hui-iconfont">&#xe62d;</i>村庄管理
          <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
          <ul>
            <li><a data-href="${ly }/back/village/village-add" data-title="添加村庄" href="javascript:void(0)">添加村庄</a></li>
            <li><a data-href="${ly }/back/village/village-list" data-title="村庄列表" href="javascript:void(0)">村庄列表</a></li>
          </ul>
        </dd>
      </dl>
      <dl>
        <dt>
          <i class="Hui-iconfont">&#xe62b;</i>乡镇管理
          <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
          <ul>
            <li><a data-href="${ly }/back/town/town-add" data-title="添加乡镇" href="javascript:void(0)">添加乡镇</a></li>
            <li><a data-href="${ly }/back/town/town-list" data-title="乡镇列表" href="javascript:void(0)">乡镇列表</a></li>
          </ul>
        </dd>
      </dl>
      <dl>
        <dt>
          <i class="Hui-iconfont">&#xe62b;</i>区县管理
          <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
          <ul>
            <li><a data-href="${ly }/back/district-add" data-title="添加乡镇" href="javascript:void(0)">添加区县</a></li>
            <li><a data-href="${ly }/back/district-list" data-title="乡镇列表" href="javascript:void(0)">区县列表</a></li>
          </ul>
        </dd>
      </dl>
      <dl>
        <dt>
          <i class="Hui-iconfont">&#xe62b;</i>行业管理
          <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
          <ul>
            <li><a data-href="${ly }/back/trade-add" data-title="添加行业" href="javascript:void(0)">添加行业</a></li>
            <li><a data-href="${ly }/back/trade-list" data-title="乡镇行业" href="javascript:void(0)">行业列表</a></li>
          </ul>
        </dd>
      </dl>
      <dl>
        <dt>
          <i class="Hui-iconfont">&#xe643;</i>企业管理
          <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
          <ul>
            <li><a data-href="${ly }/back/company/company-list" data-title="全部企业" href="javascript:void(0)">全部企业</a></li>
          </ul>
        </dd>
      </dl>
      
      <dl>
        <dt>
          <i class="Hui-iconfont">&#xe643;</i>设备管理
          <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
          <ul>
            <li><a data-href="${ly }/back/toDeviceList" data-title="全部设备" href="javascript:void(0)">全部设备</a></li>
            <li><a data-href="${ly }/back/toAddEquipment" data-title="添加设备" href="javascript:void(0)">添加设备</a></li>
          </ul>
        </dd>
      </dl>
      
      <dl>
        <dt>
          <i class="Hui-iconfont">&#xe643;</i>报警清除
          <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
          <ul>
            <li><a data-href="${ly }/back/change-timesetting" data-title="清除报警时间间隔" href="javascript:void(0)">报警信息清除设置</a></li>
          </ul>
        </dd>
      </dl>
      
      <dl>
        <dt>
          <i class="Hui-iconfont">&#xe728;</i>相关信息汇总
          <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
          <ul>
            <li><a data-href="${ly }/back/warning/sperson-list" data-title="持证上岗" href="javascript:void(0)">持证上岗</a></li>
            <li><a data-href="${ly }/back/warning/sequipment-list" data-title="特种设备检测" href="javascript:void(0)">特种设备检测</a></li>
            <li><a data-href="${ly }/back/warning/detection-list" data-title="职业卫生检测" href="javascript:void(0)">职业卫生检测</a></li>
            <li><a data-href="${ly }/back/warning/evaluation-list" data-title="评价评估" href="javascript:void(0)">评价评估</a></li>
            <li><a data-href="${ly }/back/evaluate/zheng-list" data-title="各类许可证" href="javascript:void(0)">各类许可证</a></li>
          </ul>
        </dd>
      </dl>
      
      <dl>
        <dt>
          <i class="Hui-iconfont">&#xe70b;</i>网站公告
          <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
          <ul>
            <li><a data-href="${ly }/back/notice-add" data-title="添加公告" href="javascript:void(0)">添加公告</a></li>
            <li><a data-href="${ly }/back/notice-list" data-title="公告列表" href="javascript:void(0)">公告列表</a></li>
          </ul>
        </dd>
      </dl>
      <dl>
        <dt>
          <i class="Hui-iconfont">&#xe62b;</i>基库列表
          <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
          <ul>
             <!-- 循环基库并进行判断 -->
            <li><a data-href="${ly }/back/system/base-list?type=1" data-title="所属行业基库" href="javascript:void(0)">所属行业基库</a></li>
         	<li><a data-href="${ly }/back/system/base-list?type=2" data-title="风险分级基库" href="javascript:void(0)">风险分级基库</a></li>
         	<li><a data-href="${ly }/back/system/base-list?type=3" data-title="安全管理人员类别基库" href="javascript:void(0)">安全管理人员类别基库</a></li>
         	<li><a data-href="${ly }/back/system/base-list?type=4" data-title="特种工作人员工种基库" href="javascript:void(0)">特种工作人员工种基库</a></li>
         	<li><a data-href="${ly }/back/system/base-list?type=5" data-title="评价类型基库" href="javascript:void(0)">评价类型基库</a></li>
         	<li><a data-href="${ly }/back/system/base-list?type=6" data-title="检测类别基库" href="javascript:void(0)">检测类别基库</a></li>
            <!-- 循环结束 -->
          </ul>
        </dd>
      </dl>
      
      <dl>
        <dt>
          <i class="Hui-iconfont">&#xe62b;</i>企业自查-基础
          <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
          <ul>
            <li><a data-href="${ly }/back/system/t-industry/2" data-title="危化品企业" href="javascript:void(0)">危化品企业</a></li>
            <li><a data-href="${ly }/back/system/t-industry/1" data-title="工贸企业" href="javascript:void(0)">工贸企业</a></li>
          </ul>
        </dd>
      </dl>
      <dl>
        <dt>
          <i class="Hui-iconfont">&#xe62b;</i>企业自查-高危
          <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
          <ul>
            <li><a data-href="${ly }/back/system/t-industry/3" data-title="非化工液氨使用企业" href="javascript:void(0)">非化工液氨使用企业</a></li>
            <li><a data-href="${ly }/back/system/t-industry/4" data-title="粉尘涉爆企业" href="javascript:void(0)">粉尘涉爆企业</a></li>
            <li><a data-href="${ly }/back/system/t-industry/5" data-title="高温熔融金属现场运输" href="javascript:void(0)">高温熔融金属现场运输</a></li>
            <li><a data-href="${ly }/back/system/t-industry/6" data-title="工贸行业有限空间安全作业" href="javascript:void(0)">工贸行业有限空间安全作业</a></li>
            <li><a data-href="${ly }/back/system/t-industry/7" data-title="冶金企业煤气" href="javascript:void(0)">冶金企业煤气</a></li>
          </ul>
        </dd>
      </dl>
      <dl>
        <dt>
          <i class="Hui-iconfont">&#xe62b;</i>企业自查-现场
          <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
          <ul>
            <li><a data-href="${ly }/back/system/t-industry/8" data-title="纺织行业" href="javascript:void(0)">纺织行业</a></li>
            <li><a data-href="${ly }/back/system/t-industry/9" data-title="非煤矿山行业" href="javascript:void(0)">非煤矿山行业</a></li>
            <li><a data-href="${ly }/back/system/t-industry/10" data-title="化工企业" href="javascript:void(0)">化工企业</a></li>
            <li><a data-href="${ly }/back/system/t-industry/11" data-title="机械行业" href="javascript:void(0)">机械行业</a></li>
            <li><a data-href="${ly }/back/system/t-industry/12" data-title="加油站" href="javascript:void(0)">加油站</a></li>
            <li><a data-href="${ly }/back/system/t-industry/13" data-title="建材" href="javascript:void(0)">建材</a></li>
            <li><a data-href="${ly }/back/system/t-industry/14" data-title="有色金属" href="javascript:void(0)">有色金属</a></li>
            <li><a data-href="${ly }/back/system/t-industry/15" data-title="印刷包装" href="javascript:void(0)">印刷包装</a></li>
            <li><a data-href="${ly }/back/system/t-industry/16" data-title="冶金" href="javascript:void(0)">冶金</a></li>
            <li><a data-href="${ly }/back/system/t-industry/17" data-title="危化品生产企业" href="javascript:void(0)">危化品生产企业</a></li>
            <li><a data-href="${ly }/back/system/t-industry/18" data-title="危化品经营" href="javascript:void(0)">危化品经营</a></li>
            <%-- <li><a data-href="${ly }/back/system/t-industry/19" data-title="特种设备隐患检查" href="javascript:void(0)">特种设备隐患检查</a></li> --%>
            <li><a data-href="${ly }/back/system/t-industry/20" data-title="市场事故隐患" href="javascript:void(0)">市场事故隐患</a></li>
            <li><a data-href="${ly }/back/system/t-industry/21" data-title="食品和饮料" href="javascript:void(0)">食品和饮料</a></li>
            <li><a data-href="${ly }/back/system/t-industry/22" data-title="商业零售经营单位(1000m2以上）" href="javascript:void(0)">商业零售经营单位(1000m2以上）</a></li>
            <li><a data-href="${ly }/back/system/t-industry/23" data-title="商业零售经营单位(100㎡-1000㎡）" href="javascript:void(0)">商业零售经营单位(100㎡-1000㎡）</a></li>
            <li><a data-href="${ly }/back/system/t-industry/24" data-title="其他" href="javascript:void(0)">其他</a></li>
          </ul>
        </dd>
      </dl>
      
      <dl>
        <dt>
          <i class="Hui-iconfont" >&#xe62b;</i>企业排查-重大隐患
          <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
          <ul>
            <li><a data-href="${ly}/back/key-list" data-title="企业排查-重大隐患" href="javascript:void(0)">企业排查-重大隐患</a></li>
           
          </ul>
        </dd>
      </dl>
      
      <dl>
        <dt>
          <i class="Hui-iconfont">&#xe61d;</i>系统设置
          <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
          <ul>
            <li><a data-href="${ly}/back/system/passwordSet" data-title="修改密码" href="javascript:void(0)">修改密码</a></li>
          </ul>
        </dd>
      </dl>
    </div>
  </aside>
</body>
</html>