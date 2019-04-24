if(typeof(villageId) == "undefined") {
  villageId = "";
}
if(typeof(townId) == "undefined") {
  townId = "";
}
if(typeof(districtId) == "undefined") {
  districtId = "";
}
$(function() {
  $.post("/village/industry2", {
    villageId : villageId,
    townId : townId,
    districtId : districtId
  }, function(result) {
    var list = result.map.list;
    var list5 = result.map.list5;
    var dataD = result.map.dataD;
    var dataL = result.map.dataL;
    var d = [];
    var d_scale = [];
    $.each(industry2List, function(i, item) {
      d.push([item.name, 0]);
      d_scale.push([item.name, 0]);
    })

    $.each(d_scale, function(i, item) {
      $.each(list5, function(j, jtem) {
        if (jtem.industry2 && jtem.industry2.startWith(item[0])) {
          item[1] = item[1] + jtem.c;
        }
      })
      //item[0] = item[0] + "(" + item[1] + ")";
    })

    $.each(d, function(i, item) {
      $.each(list, function(j, jtem) {
        if (jtem.industry2 && jtem.industry2.startWith(item[0])) {
          item[1] = item[1] + jtem.c;
        }
      })
      item[0] = item[0] +"(" + item[1] + ",规上企业" + d_scale[i][1] + "家。)";
    })
    
    //替换化工文字
     $.each(dataL, function(i, item) {
       if(item[0].indexOf("危险化学品生产、经营、使用") > 0) {
         item[0] = item[0].replace("危险化学品生产、经营、使用", "危化品生产、经营、使用");
         return false;
       }
     })
     /*设置位置*/
    var w1=$(".div-chart").width();
    var w2=w1-$("#container").width();
    var w3=w1-$("#container4").width();
    $("#container").css("margin-left",w2/2+"px");
    $("#container4").css("margin-left",w3/2+"px");
    //行业
    $('#container').highcharts({
      credits : {
        enabled : false
      },
      chart : {
        plotBackgroundColor : null,
        plotBorderWidth : null,
        plotShadow : false
      },
      title : {
        text : '所属行业分类统计',
        y : 20,
      },
      legend : {
        layout : 'vertical',
        align : 'right',
        verticalAlign : 'top',
        y : 60,
        itemMarginBottom : 5,
        itemStyle : {
          'word-break' : 'break-all',
          'fontSize' : "14px",
          'fontWeight' : 'normal'
        }
      },
      tooltip : {
        headerFormat : '{series.name}<br>',
        pointFormat : '{point.name}: <b>{point.percentage:.1f}%</b>'
      },
      plotOptions : {
        pie : {
          size:300,
          point: {
            events: {
                legendItemClick: function(e) {
                  show_dialog(this.name.split("(")[0] + "行业_企业列表", "/village/company/company-tab-detail?villageId="+villageId+"&townId="+townId+"&districtId="+districtId+"&industry2=" + this.name.split("(")[0]);
                  return false; 
                },
                click:function(e){
                  show_dialog(this.name.split("(")[0] + "行业_企业列表", "/village/company/company-tab-detail?villageId="+villageId+"&townId="+townId+"&districtId="+districtId+"&industry2=" + this.name.split("(")[0]);
                }
            }
        },
          allowPointSelect : false,
          cursor : 'pointer',
          dataLabels : {
            enabled : true,
            format : '{point.percentage:.1f} %',
            style : {
              color : (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
            }
          },
          showInLegend : true,
        }
      },

      series : [{
        type : 'pie',
        name : '企业行业占比',
        data : d
      }]
    });
    
    //监管行业
    $('#container4').highcharts({
      credits : {
        enabled : false
      },
      chart : {
        plotBackgroundColor : null,
        plotBorderWidth : null,
        plotShadow : false
      },
      title : {
        text : '监管行业分类统计',
        y : 20,
      },
      legend : {
        layout : 'vertical',
        align : 'right',
        verticalAlign : 'top',
        y : 60,
        itemMarginBottom : 5,
        title:{
          text:this.name,
        },
        itemStyle : {
          'word-break' : 'break-all',
          'fontSize' : "14px",
          'fontWeight' : 'normal',
        }
      },
      tooltip : {
        headerFormat : '{series.name}<br>',
        pointFormat : '{point.name}: <b>{point.percentage:.1f}%</b>'
      },
      plotOptions : {
        pie : {
          size:300,
          point: {
            events: {
              legendItemClick: function(e) {
                show_dialog(this.name.split("(")[0].replace('危化品生产、经营、使用','危险化学品生产、经营、使用') + "_企业列表", "/village/company/company-tab-detail?villageId="+villageId+"&townId="+townId+"&districtId="+districtId+"&industry=" + this.name.split("(")[0].replace('危化品生产、经营、使用','危险化学品生产、经营、使用'));
                return false; 
              },
              click:function(e){
                show_dialog(this.name.split("(")[0].replace('危化品生产、经营、使用','危险化学品生产、经营、使用') + "_企业列表", "/village/company/company-tab-detail?villageId="+villageId+"&townId="+townId+"&districtId="+districtId+"&industry=" + this.name.split("(")[0].replace('危化品生产、经营、使用','危险化学品生产、经营、使用'));
              }
            }
          },
          allowPointSelect : false,
          cursor : 'pointer',
          dataLabels : {
            enabled : true,
            format : '{point.percentage:.1f} %',
            style : {
              color : (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
            }
          },
          showInLegend : true,
        }
      },
      
      series : [{
        type : 'pie',
        name : '企业行业占比',
        data : dataL
      }]
    });
    
    //企业风险等级
    $('#container2').highcharts({
      credits : {
        enabled : false
      },
      chart : {
        plotBackgroundColor : null,
        plotBorderWidth : null,
        plotShadow : false,
      },  
      colors : ['#FF0000', '#FFA160', '#FFF000', '#0000FF'],
      title : {
        text : '风险等级统计',
        y : 20,
      },
      legend : {
        width : 300,
        layout : 'vertical',
        align : 'right',
        verticalAlign : 'top',
        y : 100,
        itemMarginBottom : 5,
        itemStyle : {
          width : 200,
          'fontSize' : "14px",
          'fontWeight' : 'normal',
          'word-break' : 'break-all'
        }
      },
      tooltip : {
        pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
      },
      plotOptions : {
        pie : {
          size:300,
          point: {
            events: {
                legendItemClick: function(e) {
                  show_dialog(this.name.split("(")[0] + "风险_企业列表", "/village/company/company-tab-detail?villageId="+villageId+"&townId="+townId+"&districtId="+districtId+"&dlevel='" + this.name.split("(")[0] + "'");
                  return false; 
                },
                click: function(e){
                  show_dialog(this.name.split("(")[0] + "风险_企业列表", "/village/company/company-tab-detail?villageId="+villageId+"&townId="+townId+"&districtId="+districtId+"&dlevel='" + this.name.split("(")[0] + "'");
                }
            }
        },
          allowPointSelect : false,
          cursor : 'pointer',
          dataLabels : {
            enabled : true,
            format : '<b>{point.name}</b>: {point.percentage:.1f} %',
            style : {
              color : (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
            }
          },
          showInLegend : true,
        }
      },
      series : [{
        type : 'pie',
        name : '企业风险比重',
        data : dataD
      }]
    });
    
    //规上企业 行业
    /*$('#container5').highcharts({
      credits : {
        enabled : false
      },
      chart : {
        plotBackgroundColor : null,
        plotBorderWidth : null,
        plotShadow : false
      },
      title : {
        text : '规上企业行业分类统计',
        y : 20,
      },
      legend : {
        layout : 'vertical',
        align : 'right',
        verticalAlign : 'top',
        y : 60,
        itemMarginBottom : 5,
        itemStyle : {
          'word-break' : 'break-all',
          'fontSize' : "14px",
          'fontWeight' : 'normal'
        }
      },
      tooltip : {
        headerFormat : '{series.name}<br>',
        pointFormat : '{point.name}: <b>{point.percentage:.1f}%</b>'
      },
      plotOptions : {
        pie : {
          size:300,
          point: {
            events: {
                legendItemClick: function(e) {
                  show_dialog(this.name.split("(")[0] + "行业_企业列表", "/village/company/company-tab-detail?industry2=" + this.name.split("(")[0]);
                  return false; 
                },
                click:function(e){
                  show_dialog(this.name.split("(")[0] + "行业_企业列表", "/village/company/company-tab-detail?industry2=" + this.name.split("(")[0]);
                }
            }
        },
          allowPointSelect : false,
          cursor : 'pointer',
          dataLabels : {
            enabled : true,
            format : '{point.percentage:.1f} %',
            style : {
              color : (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
            }
          },
          showInLegend : true,
        }
      },

      series : [{
        type : 'pie',
        name : '企业行业占比',
        data : d_scale
      }]
    });*/
  })

})