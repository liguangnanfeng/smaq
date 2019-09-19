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
body .dis-ib{margin-right:15px;}
</style>

</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>风险分级管控</span> 
    <span class="c-gray en">&gt;</span> <span>数据分析</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container" style="padding:20px 0">
    <div id="container" style="width:100%;display: inline-block;height:500px;margin-bottom:50px"></div>
    <div style="display: inline-block;width:100%;height:15px;background:#EBEEF3"></div>
    <div id="container2" style="width:100%;display: inline-block;height:500px;margin:50px 0"></div>
    <div id="win-show" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog" style="width:1000px">
        <div class="modal-content radius">
          <div class="modal-header">
              <h3 class="modal-title">企业列表</h3>
              <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
          </div>
          <div class="modal-body">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
              <thead>
                <tr class="text-c">
                  <th width="5%">序号</th>
                  <th width="25%">企业名称</th>
                  <th width="15%">所属行业</th>
                  <th width="8%">风险等级</th>
                  <th width="20%">所在地</th>
                  <th width="14%">规模类型</th>
                  <th width="8%">运行状态</th>
                </tr>
              </thead>
              <tbody>
                <!-- 循环村 -->
               <c:forEach items="${list }" varStatus="index" var="co">
                <tr class="text-c">
                  <td>${index.index + 1}</td>
                  <td ondblclick="window.open('/fore/company?un=${co.name}');"><c:if test="${co.isKey == '1' && not empty sk}"><label style="color:#DF2E30">【重点企业】</label></c:if>${co.name }</td>
                  <td>${co.industry }</td>
                  <td>${co.dlevel }</td>
                  <td>${co.regionName} ${co.address }</td>
                  <td>${co.scale}</td>
                  <c:if test="${0 == co.isFreeze }">
                  <td>正常</td>
                  </c:if>
                  <c:if test="${1 == co.isFreeze }">
                  <td>冻结</td>
                  </c:if>
                </tr>
                </c:forEach>
                <!-- 循环结束 -->
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
    <div id="win-show2" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog" style="width:1000px">
        <div class="modal-content radius">
          <div class="modal-header">
              <h3 class="modal-title">风险点列表</h3>
              <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
          </div>
          <div class="modal-body">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
              <thead>
                <tr class="text-c">
                  <th width="5%">序号</th>
                  <th width="60%">风险点</th>
                  <th width="20%">所属行业</th>
                  <th width="15%">风险等级</th>
                </tr>
              </thead>
              <tbody>
                <!-- 循环-->
                <tr>
                  <td class="text-c">1</td>
                  <td><p>xx</p></td>
                  <td class="text-c"><p>xx</p></td>
                  <td class="text-c">xx</td>
                </tr>
                <!-- 循环结束 -->
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
  <script type="text/javascript">
 /*  饼图 */
var chart = Highcharts.chart('container', {
  credits: {
    enabled: false
},
  title: {
      text: '风险分析'
  },
  colors:  ['#FF0000', '#FFA500', '#FFFF00', '#3B9BFF'],
  tooltip: {
    pointFormat: '{point.name}: <b>{point.y}</b>'
  },
  plotOptions: {
      pie: {
          allowPointSelect: false,  // 可以被选择
          cursor: 'pointer',       // 鼠标样式
          dataLabels: {
              enabled: true,
              format: '<b>{point.name}</b>: {point.percentage:.1f} %',
              style: {
                  color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
              }
          },
          showInLegend: true,
          point: {
            events: {
                legendItemClick: function(e) {
<%--                  alert(this.name);--%>
<%--                  $("#win-show").modal("show");--%>
                  return false;
                },
                click:function(x){
                  window.location.href="/global/all-statistics-list";
                }
            }
        }
      },
  },
  series: [{
      type: 'pie',
      name: '占比',
      data: [
          {name:'重大风险',y:${flag1[0]} ,url:'http://www.baidu.com'},
          {name:'较大风险',y:${flag1[1]} ,url:'http://www.baidu.com'},
          {name:'一般风险',y:${flag1[2]} ,url:'http://www.baidu.com'},
          {name:'较低风险',y:${flag1[3]} ,url:'http://www.baidu.com'}
      ]
  }]
});
/* 柱状图 */

    const f1='#FF0000';
    const f2='#FFA500';
    const f3='#FFFF00';
    const f4='#3B9BFF';
    var d=${gg2};
    console.log('d',d);

var chart2 = Highcharts.chart('container2',{
  chart: {
          type: 'column'
  },
  credits: {
    enabled: false
},
  title: {
          text: '风险统计'
  },
  colors: [f1, f2, f3,f4],
  xAxis: {
          categories: ${industrys},
          labels: {
            style: {
                fontWeight: 'bold',//颜色
                fontSize:'15px'  //字体
            }
        },
          crosshair: true
  },
  yAxis: {
          min: 0,
          title: {
                  text: '数量'
          },
          labels: {
            style: {
                fontWeight: 'bold',//颜色
                fontSize:'15px'  //字体
            }
        }
  },
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
                  borderWidth: 0,
                  pointWidth: 35
          },
  series: {
    events: {
        legendItemClick: function(e) {
    let x= e.target.index;
    var exSeries =  $('#container2').highcharts().get('gg2');
    console.log(exSeries);
    if(x===0){
      d=${gg1};
    exSeries.color=f1;
    exSeries.name="重大风险";
    exSeries.setData(d, true, true, false);
    }
    else if(x===1){
    d=${gg2};
    exSeries.color=f2;
    exSeries.name="较大风险";
    exSeries.setData(d, true, true, false);
    }
    else if(x===2){
    console.log(3);
    d=${gg3};
    console.log(d)
    exSeries.color=f3;
    exSeries.name="一般风险";
    exSeries.setData(d, true, true, false);
    }
    else if(x===3){
    console.log(4);
    d=${gg4};
    console.log(d)
    exSeries.color=f4;
    exSeries.name="较低风险";
    exSeries.setData(d, true, true, false);
    }


            return false; // 直接 return false 即可禁用图例点击事件

        },
        click:function(e){
<%--          alert(e.point.category);--%>
<%--          $("#win-show2").modal("show");--%>
            console.log(e)
        }
    },
  }
  },
  series: [{
          name: '重大风险',
<%--          data: ${gg1}--%>
  }
    , {
          name: '较大风险',
         id:'gg2',
<%--          data: ${gg2}--%>
            data:d
  }, {
          name: '一般风险',
<%--          data: ${gg3}--%>
  }, {
    name: '较低风险',
    <%--          data: ${gg3}--%>
    }

    ]
});

    console.log('industrys---------------')
    console.log('${industrys}')
    let a=${gg1};
    let b=${gg1};
    let c=${gg1};
    let ddd={
     a,b,c
    };
    console.log(ddd)
</script>
</body>
</html>