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
.change_zhe{width:100%;height:70px;position: relative;}
.btn-group{position: absolute;right:0;top:14px;}
.btnyh{color: #333;background: #e6e6e6;}
.selected{color: #fff;background: #5a98de;}
body .iconxw{transform: rotate(-90deg);float:right;margin-right:-5px;margin-top: -3px;}
</style>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>重大危险源源长制</span> 
    <span class="c-gray en">&gt;</span> <span>分类统计</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="panel panel-default" style="width:99%;margin-left:10px;float:left;background:none;">
      <div class="div_tx">
        <div class="title_xw pb-5 pos-r" style="border-bottom:1px solid #F6F6F6">重大危险源数据分析
          <!-- 判断并提示至少选择3个月 -->
          <!-- <div class="search_rq pos-a">
            <font>查询时间段：</font>
            <input type="text" class="Wdate input-text" id="sT" style="width:150px;" onfocus="WdatePicker({dateFmt:'yyyy-MM',maxDate:'#F{$dp.$D(\'eT\',{M:-3}) || \'%y-{%M-3}\'}'})"/>
            <font>-</font>
            <input type="text" class="Wdate input-text" id="eT" style="width:150px;" onfocus="WdatePicker({dateFmt:'yyyy-MM',minDate:'#F{$dp.$D(\'sT\',{M:+3})}', maxDate : '%y-%M'})"/>
            <button class="btn btn-success" type="button" onclick="reload()">
              <i class="Hui-iconfont">&#xe665;</i> 查询
            </button>
          </div> -->
        </div>
        <div class="div_zhe">
          <div class="change_zhe">
            <div class="btn-group">
              <span class="btn btnyh selected radius yhzg">企业运行率</span>
              <span class="btn btnyh radius yhly">特殊作业</span>
            </div>
          </div>
          <div id="container3" style="width:100%;height:450px;display: inline-block;"></div>
        </div>
      </div>
    </div>
</body>
<script type="text/javascript">
$(function () {
    /* 企业运行率 */
    $(".yhzg").click(function(){
      $(".btnyh").removeClass("selected");
      $(this).addClass("selected");
      //$("#container3").css("width","50%");
      $.post(getRootPath() + "/villageAdd/source/zhuChartData",{},function(result) {
        var categories = result.map.categories;
        var series = result.map.series;
        $('#container3').highcharts({
          credits: {enabled: false},
          chart: { type: 'column'},
          title: { text: '' },
          xAxis: { categories:categories, crosshair: true },
          yAxis: { min: 0, title: { text: '数量'},min:0,tickAmount: 8},
          tooltip: {
            formatter: function () {
              var content ='<table>'
              for (var i = 0; i < this.points.length; i++) {
                  content += '<tr><td style="padding:0;color: ' + this.points[i].series.color + '">' + this.points[i].series.name + ':</td> <td style="padding:0"><b>' + this.points[i].y + '</b></td></tr>';
                  if(i==1){
                    var jj;
                    if(this.points[0].y==0){
                      jj=0;
                    }else{
                      jj=Highcharts.numberFormat((this.points[0].y-this.points[1].y)/this.points[0].y*100,2);
                    }
                    content += '<tr><td style="padding:0;color:#333">运行率：</td><td>'+jj +'%</td></tr>';
                  }
              };
              return content;
            },
              shared: true,
              useHTML: true
          },
          plotOptions: {
              column: {
                  pointPadding: 0.2,
                  borderWidth: 0
              },
              series: {
                events: {
                    legendItemClick: function() {
                    	if(this.name == '全部企业'){
                    		show_dialog("企业信息", "${ly }/villageAdd/source/danger-chart-list");
                    	}else{
                    		show_dialog("企业信息", "${ly }/villageAdd/source/danger-chart-list?status=0");
                    	}
                        return false; //return false 即可禁用图例点击隐藏图例对应数据的事件
                    }
                }
              }
          },
          series: series
        });
      })
    });
    /* 隐患来源 */
    $(".yhly").click(function(){
      $(".btnyh").removeClass("selected");
      $(this).addClass("selected");
      $.post(getRootPath() + "/villageAdd/source/zhuChartData2",{},function(result) {
        var categories = result.map.categories;
        var series = result.map.series;
        $('#container3').highcharts({
          credits: { enabled: false },
          chart: { type: 'column' },
          title: { text: '' },
          xAxis: { categories: categories, crosshair: true },
          yAxis: { min: 0, title: { text: '数量' },min:0,tickAmount: 8 },
          tooltip: {
              headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
              pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
              '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
              footerFormat: '</table>',
              shared: true,
              useHTML: true
          },
          plotOptions: {
              column: {
                  pointPadding: 0.2,
                  borderWidth: 0
              },
              series: {
                events: {
                    legendItemClick: function(e) {
                    	switch(this.name){
                    	case '非化工液氨使用企业':
                    		show_dialog("企业信息", "${ly }/villageAdd/source/danger-chart-list?type=1");
                    		return false;
                    	case '粉尘涉爆企业':
                    		show_dialog("企业信息", "${ly }/villageAdd/source/danger-chart-list?type=2");
                    		return false;
                    	case '高温熔融金属现场运输':
                    		show_dialog("企业信息", "${ly }/villageAdd/source/danger-chart-list?type=3");
                    		return false;
                    	case '工贸行业有限空间安全作业':
                    		show_dialog("企业信息", "${ly }/villageAdd/source/danger-chart-list?type=4");
                    		return false;
                    	case '冶金企业煤气':
                    		show_dialog("企业信息", "${ly }/villageAdd/source/danger-chart-list?type=5");
                    		return false;
                    	}
                        return false; // 直接 return false 即可禁用图例点击事件
                    }
                }
              }
          },
          series: series
      });
      })
    });
    
    reload();
  });

  function reload() {
    $(".selected").click();
  }
</script>
</html>