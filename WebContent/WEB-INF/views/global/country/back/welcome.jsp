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
.title_xw{width:100%;border-bottom:0px solid #F6F6F6;font-weight:bold;font-size:14px;}
.one_notice{width:100%;margin-top:0px;}
.text-overflow{display: inline-block;width:70%;line-height:25px;color:#7C7C7C;margin-right:0px;}
.text-overflow i{height:3px;width:3px;background:#7C7C7C;border-radius:50%;display:inline-block;vertical-align: middle;margin-right:5px;}
.text-overflow:hover a{color:#5889D8;text-decoration: underline;}
.text-overflow:hover i{background:#5889D8;}
.text-riqi{float:right;color:#AEAEAE;line-height:25px;}
.one_tx{margin-top:20px;}
.clearfix:after{content:"\20";display:block;height:0;clear:both;visibility:hidden}
.clearfix{zoom:1}
.tabBar {border-bottom: 2px solid #F6F6F6}
.tabBar span {background-color: #fff;cursor: pointer;font-size:15px;display: inline-block;float: left;font-weight: bold;height: 40px;line-height: 40px;padding: 0 35px}
.tabBar span.current{background-color: #fff;color: #FB0005;border:0px;border-bottom:2px solid #FB0005}
.tabCon {display: none;border:0px;padding:0;}
.div_yj .one_tx{margin-top:20px;width:31%;margin-right:2%}
.div_zhe{width:100%;}
.change_zhe{width:100%;height:35px;position: relative;}
.btn-group{position: absolute;right:0;top:14px;}
.btnyh{color: #333;background: #e6e6e6;}
.btnyhxz{color: #fff;background: #5a98de;}
body .iconxw{transform: rotate(-90deg);float:right;margin-right:-5px;margin-top: -3px;}
</style>
<script type="text/javascript">
$(function(){
  $.Huitab("#tab_yj .tabBar span","#tab_yj .tabCon","current","click","0")
  
  });
  
</script>
</head>
<body>
  <div class="panel panel-default" style="width:83%;margin-left:10px;float:left;background:none;">
    <div class="panel-body">
      <div class="div_tx">
        <div class="title_xw pb-5 pos-r">统计分析</div>
        <a class="one_tx radius"  data-title="村庄列表" data-href="${ly }/back/village/village-list" onclick="Hui_admin_tab(this)" href="javascript:;" style="background:#6CA3FF;margin-left:3%">
          <font class="font_txs">村庄（${c[0]}）</font>
        </a>
        <a class="one_tx radius" data-title="乡镇列表" data-href="${ly }/back/town/town-list" onclick="Hui_admin_tab(this)" href="javascript:;" style="background:#956EFC">
          <font class="font_txs">乡镇（${c[1]}）</font>
        </a>
        <a class="one_tx radius" data-title="企业列表"  data-href="${ly }/back/company/company-list" style="background:#4EC564;margin-right:0" onclick="Hui_admin_tab(this)" href="javascript:;">
          <font class="font_txs">企业（${c[2]}）</font>
        </a>
      </div>
      <div class="div_tx mt-15">
        <div class="title_xw pb-5 pos-r">相关信息汇总</div>
        <a class="one_tx radius" data-title="持证上岗" data-href="${ly }/back/warning/sperson-list" onclick="Hui_admin_tab(this)" href="javascript:;" style="background:#87C272;">
          <font class="font_txs">持证上岗（${c[3]}）</font>
        </a>
        <a class="one_tx radius" data-title="特种设备检测"  data-href="${ly }/back/warning/sequipment-list" style="background:#D18DCA" onclick="Hui_admin_tab(this)" href="javascript:;">
          <font class="font_txs">特种设备检测（${c[4]}）</font>
        </a>
        <a class="one_tx radius"  data-title="职业卫生检测" data-href="${ly }/back/warning/detection-list" onclick="Hui_admin_tab(this)" href="javascript:;" style="background:#A08BCE;margin-left:3%">
          <font class="font_txs">职业卫生检测（${c[5]}）</font>
        </a>
        <a class="one_tx radius" data-title="评价评估" data-href="${ly }/back/warning/evaluation-list" onclick="Hui_admin_tab(this)" href="javascript:;" style="background:#87C272;">
          <font class="font_txs">评价评估（${c[6]}）</font>
        </a>
        <a class="one_tx radius" data-title="各类许可证"  data-href="${ly }/back/evaluate/zheng-list" style="background:#D18DCA" onclick="Hui_admin_tab(this)" href="javascript:;">
          <font class="font_txs">各类许可证（${c[7]}）</font>
        </a>
      </div>
      <div class="mt-15 pos-r">
        <div class="div_tx div_yj" style="padding-top:15px;">
          <div id="tab_yj" class="HuiTab">
            <div class="tabBar clearfix">
              <span data-toggle="tooltip" data-placement="bottom" title="三个月内即将过期">预警提醒(${count[1] + count[2] + count[3] + count[4] + count[5] + count[6]})</span>
            </div>
            <div class="tabCon">
              <a class="one_tx radius pos-r" data-title="安全管理人员" data-href="${ly }/back/warning/security-list?isTime=3" onclick="Hui_admin_tab(this)" href="javascript:;" style="margin-left:1%;background:#FFD060;">
                <img alt="" src="${ly }/images/httb5.png"/>
                <font class="font_tx1 pos-a">${count[1] }</font>
                <font class="font_tx2 pos-a">安全管理人员</font>
              </a>
              <a class="one_tx radius pos-r" data-title="特种作业人员" data-href="${ly }/back/warning/sperson-list?isTime=3" onclick="Hui_admin_tab(this)" href="javascript:;" style="background:#46D1E5;">
                <img alt="" src="${ly }/images/httb4.png"/>
                <font class="font_tx1 pos-a">${count[2] }</font>
                <font class="font_tx2 pos-a">特种作业人员</font>
              </a>
              <a class="one_tx radius pos-r" data-title="特种设备" data-href="${ly }/back/warning/sequipment-list?isTime=3" onclick="Hui_admin_tab(this)" href="javascript:;" style="background:#6BCD6E;margin-right:0">
                <img alt="" src="${ly }/images/httb3.png"/>
                <font class="font_tx1 pos-a">${count[3] }</font>
                <font class="font_tx2 pos-a">特种设备</font>
              </a>
              <a class="one_tx radius pos-r" data-title="评价评估" data-title="评价评估" data-href="${ly }/back/warning/evaluation-list?isTime=3" onclick="Hui_admin_tab(this)" href="javascript:;" style="margin-left:1%;background:#FFA160;">
                <img alt="" src="${ly }/images/httb2.png"/>
                <font class="font_tx1 pos-a">${count[4] }</font>
                <font class="font_tx2 pos-a">评价评估</font>
              </a>
              <a class="one_tx radius pos-r" data-title="检测检验" data-title="安全监测" data-href="${ly }/back/warning/detection-list?isTime=3" onclick="Hui_admin_tab(this)" href="javascript:;" style="background:#60D0FE;">
                <img alt="" src="${ly }/images/httb7.png"/>
                <font class="font_tx1 pos-a">${count[5] }</font>
                <font class="font_tx2 pos-a">检测检验</font>
              </a>
              <a class="one_tx radius pos-r" data-title="消防应急设施配备" data-href="${ly }/back/warning/facilities-list?flag=1" onclick="Hui_admin_tab(this)" href="javascript:;" style="background:#FF606C;margin-right:0">
                <img alt="" src="${ly }/images/httb6.png"/>
                <font class="font_tx1 pos-a">${count[6] }</font>
                <font class="font_tx2 pos-a">消防、应急设施配备</font>
              </a>
            </div>
            <div class="tabCon">
              <a class="one_tx radius pos-r" data-title="安全管理人员" data-href="${ly }/back/warning/security-list?isTime=0" onclick="Hui_admin_tab(this)" href="javascript:;" style="margin-left:1%;background:#FFD060;">
                <img alt="" src="${ly }/images/httb5.png"/>
                <font class="font_tx1 pos-a">${count[8] }</font>
                <font class="font_tx2 pos-a">安全管理人员</font>
              </a>
              <a class="one_tx radius pos-r" data-title="特种作业人员" data-href="${ly }/back/warning/sperson-list?isTime=0" onclick="Hui_admin_tab(this)" href="javascript:;" style="background:#46D1E5;">
                <img alt="" src="${ly }/images/httb4.png"/>
                <font class="font_tx1 pos-a">${count[9] }</font>
                <font class="font_tx2 pos-a">特种作业人员</font>
              </a>
              <a class="one_tx radius pos-r" data-title="特种设备" data-href="${ly }/back/warning/sequipment-list?isTime=0" onclick="Hui_admin_tab(this)" href="javascript:;" style="background:#6BCD6E;margin-right:0">
                <img alt="" src="${ly }/images/httb3.png"/>
                <font class="font_tx1 pos-a">${count[10] }</font>
                <font class="font_tx2 pos-a">特种设备</font>
              </a>
              <a class="one_tx radius pos-r" data-title="评价评估" data-title="评价评估" data-href="${ly }/back/warning/evaluation-list?isTime=0" onclick="Hui_admin_tab(this)" href="javascript:;" style="margin-left:1%;background:#FFA160;">
                <img alt="" src="${ly }/images/httb2.png"/>
                <font class="font_tx1 pos-a">${count[11] }</font>
                <font class="font_tx2 pos-a">评价评估</font>
              </a>
              <a class="one_tx radius pos-r" data-title="检测检验" data-title="安全监测" data-href="${ly }/back/warning/detection-list?isTime=0" onclick="Hui_admin_tab(this)" href="javascript:;" style="background:#60D0FE;">
                <img alt="" src="${ly }/images/httb7.png"/>
                <font class="font_tx1 pos-a">${count[12] }</font>
                <font class="font_tx2 pos-a">检测检验</font>
              </a>
              <a class="one_tx radius pos-r" data-title="消防应急设施配备" data-href="${ly }/back/warning/facilities-list?flag=1" onclick="Hui_admin_tab(this)" href="javascript:;" style="background:#FF606C;margin-right:0">
                <img alt="" src="${ly }/images/httb6.png"/>
                <font class="font_tx1 pos-a">${count[6] }</font>
                <font class="font_tx2 pos-a">消防、应急设施配备</font>
              </a>
            </div>
          </div>
        </div>
        <div class="div_tx div_yh pos-a">
          <div class="title_xw pb-5 pos-r">隐患排查</div>
          <a class="one_tx radius pos-r"  data-title="企业自查隐患" data-href="${ly }/back/warning/trouble-list?isZheng=2" onclick="Hui_admin_tab(this)" href="javascript:;" style="width:100%;margin-right:0;">
            <img alt="" src="${ly }/images/httb1.png"/>
            <font class="font_tx1 pos-a">${count[0] }</font>
            <font class="font_tx2 pos-a">企业自查隐患</font>
          </a>
          <a class="one_tx radius pos-r"  data-title="政府检查隐患" data-href="${ly }/back/warning/trouble-list?isZheng=2" onclick="Hui_admin_tab(this)" href="javascript:;" style="width:100%;margin-right:0;background:#956EFC;">
            <img alt="" src="${ly }/images/httb1.png"/>
            <font class="font_tx1 pos-a">${count[7] }</font>
            <font class="font_tx2 pos-a">政府检查隐患</font>
          </a>
        </div>
      </div>
    </div>
  </div>
  <div style="position: fixed;right:0;top:0px;width:15%">
  <div class="panel panel-default" style="width:100%;margin-right:10px;float:right;margin-top:15px;">
    <div class="panel-body" style="padding:8px">
      <a class="title_xw div_xinw" data-href="${ly }/back/notice/notice-zlist?type=1"  data-title="监管动态" onclick="Hui_admin_tab(this)" href="javascript:;">
        <font>监管动态</font>
        <i class="Hui-iconfont iconxw">&#xe6d5;</i>
      </a>
    </div>
  </div>
  <div class="panel panel-default" style="width:100%;margin-right:10px;float:right;margin-top:15px;">
    <div class="panel-body" style="padding:8px">
      <a class="title_xw div_xinw" data-href="${ly }/back/notice-list?type=3"  data-title="法律法规标准规范" onclick="Hui_admin_tab(this)" href="javascript:;">
        <font>法律法规标准规范</font>
        <i class="Hui-iconfont iconxw">&#xe6d5;</i>
      </a>
    </div>
  </div>
  <div class="panel panel-default" style="width:100%;margin-right:10px;float:right;margin-top:15px;">
    <div class="panel-body" style="padding:8px">
      <a class="title_xw div_xinw" data-href="${ly }/back/notice-list?type=2"  data-title="安全员之家" onclick="Hui_admin_tab(this)" href="javascript:;">
        <font>安全员之家</font>
        <i class="Hui-iconfont iconxw">&#xe6d5;</i>
      </a>
      </div>
    </div>
    <!-- <div class="panel panel-default" style="width:100%;margin-right:10px;float:right;margin-top:15px;">
      <div class="panel-body" style="padding:8px">
        <a class="title_xw div_xinw" data-href="http://www.hupinwang.com/info" data-title="护品网" onclick="Hui_admin_tab(this)" href="javascript:;">
          <font>获取更多信息</font>
          <i class="Hui-iconfont iconxw">&#xe6d5;</i>
        </a>
      </div>
    </div> -->
  </div>
</body>
</html>