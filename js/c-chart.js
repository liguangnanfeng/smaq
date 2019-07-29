$(function () {
  /* 隐患类型 */
  $(".yhlx").click(function(){
    $(".btnyh").removeClass("btnyhxz");
    $(this).addClass("btnyhxz");
    $.post(getRootPath() + "/company/lineChartData",{
      sT : $("#sT").val(),
      eT : $("#eT").val()
    },function(result) {
      var categories = result.map.categories;
      var series = result.map.series;
      var all = 0;
      for(var i=0;i<series.length;i++) {
        var data = series[i].data;
        for(var j=0;j<data.length;j++) {
          all = all + data[j];
        }
      }
      var hz = new Object();
      hz.name = '隐患汇总 '+all+' 条';
      var li = new Array();
      for(var i =0;i<categories.length;i++) {
        li.push(0);
      }
      hz.data = li;
      hz.visible = false;
      series.push(hz);
      $('#container3').highcharts({
        credits: { enabled: false},
        chart: { type: 'line' },
        title: { text: '' },
        legend: {
          itemHiddenStyle: {
            color: '#f00'
        }
      },
        xAxis: { categories: categories },
        yAxis: { title: { text: '数量' },min:0,tickAmount: 8 },
        plotOptions: {
          line: {
            dataLabels: { enabled: true },// 开启数据标签
            enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
          },
          series: {
            events: {
                legendItemClick: function(e) {
                    return false; // 直接 return false 即可禁用图例点击事件
                }
            }
          }
        },
        series: series
      });
    })
  });
  /* 隐患等级 */
  $(".yhdj").click(function(){
    $(".btnyh").removeClass("btnyhxz");
    $(this).addClass("btnyhxz");
    $.post(getRootPath() + "/company/lineChartData2",{
      sT : $("#sT").val(),
      eT : $("#eT").val()
    },function(result) {
      var categories = result.map.categories;
      var series = result.map.series;


      $('#container3').highcharts({
        credits: {enabled: false},
          chart: {type: 'line'},
          title: {text: ''},
          xAxis: {categories: categories},
          yAxis: { title: {text: '数量'},min:0,tickAmount: 8},
          tooltip: {
              formatter: function () {
                  var content ='<table>'
                  for (var i = 0; i < this.points.length; i++) {
                      content += '<tr><td style="padding:0;color: ' + this.points[i].series.color + '">' + this.points[i].series.name + ':</td> <td style="padding:0"><b>' + this.points[i].y + '</b></td></tr>';
                      if(i==1){
                          var jj;
                          if(this.points[0].y==0){
                              jj=100;
                          }else{
                              jj=Highcharts.numberFormat(this.points[1].y/this.points[0].y*100,2);
                          }
                          content += '<tr><td style="padding:0;color:#333">占比率：</td><td>'+jj +'%</td></tr>';
                      }
                  };
                  return content;
              },
              shared: true,
              useHTML: true
          },
          plotOptions: {
              series: {
                events: {
                    legendItemClick: function(e) {
                        return false; // 直接 return false 即可禁用图例点击事件
                    }
                }
              }
          },
          series: series
      });
    })
  });
  /* 隐患整改 */
  $(".yhzg").click(function(){
    $(".btnyh").removeClass("btnyhxz");
    $(this).addClass("btnyhxz");
    $.post(getRootPath() + "/company/zhuChartData",{
      sT : $("#sT").val(),
      eT : $("#eT").val()
    },function(result) {
      var categories = result.map.categories;
      var series = result.map.series;
        series[2] = {
            name:'未整改项数',
            _colorIndex:2,
            data: arrF(series[0].data,series[1].data)
        }
        function arrF(arr1,arr2){
            var arr = []
            for(var i=0;i<arr1.length;i++){
                console.log(arr1[i]);
                arr.push(arr1[i]-arr2[i])
            }
            return arr
        }


        $('#container3').highcharts({
        credits: {enabled: false},
        chart: { type: 'column'},
        title: { text: '' },
        xAxis: { categories:categories, crosshair: true },
        yAxis: { min: 0,
            title: { text: '数量'},min:0,tickAmount: 8
        },
        tooltip: {
          formatter: function () {
            var content ='<table>'
            for (var i = 0; i < this.points.length; i++) {
                content += '<tr><td style="padding:0;color: ' + this.points[i].series.color + '">' + this.points[i].series.name + ':</td> <td style="padding:0"><b>' + this.points[i].y + '</b></td></tr>';
                if(i==1){
                  var jj;
                  if(this.points[0].y==0){
                    jj=100;
                  }else{
                    jj=Highcharts.numberFormat(this.points[1].y/this.points[0].y*100,2);
                  }
                  content += '<tr><td style="padding:0;color:#333">整改率：</td><td>'+jj +'%</td></tr>';
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
                  legendItemClick: function(e) {
                      return false; // 直接 return false 即可禁用图例点击事件
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
    $(".btnyh").removeClass("btnyhxz");
    $(this).addClass("btnyhxz");
    $.post(getRootPath() + "/company/zhuChartData2",{
      sT : $("#sT").val(),
      eT : $("#eT").val()
    },function(result) {
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
  $(".btnyhxz").click();
}