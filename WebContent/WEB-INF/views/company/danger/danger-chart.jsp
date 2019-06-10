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
.title_xw{width:100%;font-weight:bold;font-size:15px;}
.one_notice{width:100%;margin-top:0px;}
.list_aq{margin-top:10px;}
.text-overflow{display: inline-block;width:70%;line-height:25px;color:#7C7C7C;margin-right:0px;}
.text-overflow i{height:3px;width:3px;background:#7C7C7C;border-radius:50%;display:inline-block;vertical-align: middle;margin-right:5px;}
.text-overflow:hover a{color:#5889D8;text-decoration: underline;}
.text-overflow:hover i{background:#5889D8;}
.text-riqi{float:right;color:#AEAEAE;line-height:25px;}
.one_tx{margin-top:20px;width:31%;margin-right:2%}
body .div_company2 span{margin-top:10px;}
.clearfix:after{content:"\20";display:block;height:0;clear:both;visibility:hidden}
.clearfix{zoom:1}
.tabBar {border-bottom: 2px solid #F6F6F6}
.tabBar span {background-color: #fff;cursor: pointer;font-size:15px;display: inline-block;float: left;font-weight: bold;height: 40px;line-height: 40px;padding: 0 35px}
.tabBar span.current{background-color: #fff;color: #FF9900;border:0px;border-bottom:2px solid #FF9900}
.tabCon {display: none;border:0px;padding:0;}
.bt_zd{background:#DF2E30;padding:0 9px;color:#fff;font-size:12px;margin-left:10px;border-radius:5px;height:25px;line-height:25px;display: inline-block;}
.div_zhe{width:100%;}
.change_zhe{width:100%;height:35px;position: relative;}
.btn-group{position: absolute;right:0;top:14px;}
.btnyh{color: #333;background: #e6e6e6;}
.btnyhxz{color: #fff;background: #5a98de;}
body .iconxw{transform: rotate(-90deg);float:right;margin-right:-5px;margin-top: -3px;}
</style>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>隐患统计分析系统</span> 
    <span class="c-gray en">&gt;</span> <span>隐患数据分析</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="panel panel-default" style="width:99%;margin-left:10px;float:left;background:none;">
      <div class="div_tx">
        <div class="title_xw pb-5 pos-r" style="border-bottom:1px solid #F6F6F6">隐患数据分析
          <!-- 判断并提示至少选择3个月 -->
          <div class="search_rq pos-a">
            <font>查询时间段：</font>
<%--            <input type="text" class="Wdate input-text" id="sT" style="width:150px;" onfocus="WdatePicker({dateFmt:'yyyy-MM',maxDate:'#F{$dp.$D(\'eT\',{M:-3}) || \'%y-{%M-3}\'}'})"/>--%>
<%--            <font>-</font>--%>
<%--            <input type="text" class="Wdate input-text" id="eT" style="width:150px;" onfocus="WdatePicker({dateFmt:'yyyy-MM',minDate:'#F{$dp.$D(\'sT\',{M:+3})}', maxDate : '%y-%M'})"/>--%>
            <input type="text" class="Wdate input-text" id="sT" style="width:150px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'eT\',{M:-1})}',maxDate:'#F{$dp.$D(\'eT\',{d:-1}) || \'%y-%M\'}'})"/>
            <font>-</font>
            <input type="text" class="Wdate input-text" id="eT" style="width:150px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'sT\',{d:+1})}',maxDate :'#F{$dp.$D(\'sT\',{M:+1}) && \'%y-%M\'}'})"/>
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
</body>
<script type="text/javascript" src="${ly }/js/c-chart.js"></script>
</html>