    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%@ include file="/WEB-INF/views/taglibs.jsp"%>
        <%@ include file="/WEB-INF/inc/back-header.inc"%>
        <!DOCTYPE html>
        <html>
        <head>
        <title>ECharts</title>
        <!-- 引入 echarts.js -->
        <style>
        .select-btn{
        margin: 15px auto 64px auto;
        display: flex;
        justify-content: center;
        }
        .sbtn{
        height: 34px;
        line-height: 34px;
        padding: 0 25px;
        display: inline-block;
        box-sizing: border-box;
        cursor: pointer;
        text-align: center;
        font-weight: 400;
        white-space: nowrap;
        vertical-align: middle;
        -moz-padding-start: npx;
        -moz-padding-end: npx;
        border: solid 1px #ddd;
        background-color: #fff;
        width: auto;
        }
        .btn-default{
        color: #fff!important;
        background-color: #5a98de!important;
        border-color: #5a98de!important;
        }
        a:hover{
        text-decoration:none;
        }
        </style>
        </head>
        <body style="height: 100vh;">
        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
        <div id="main2" style="width: 90%;height:400px;margin: 50px auto 0 auto;"></div>
        <%--        <iframe id="colum" src=""></iframe>--%>
        <div class="select-btn">
        <a class="sbtn" id="chaBtn1"  href="${ly }/company/yh-analysis?flag=1">企业自查</a>
        <a class="sbtn" id="chaBtn2" href="${ly }/company/yh-analysis?flag=2">政府抽查</a>
        <a class="sbtn" id="chaBtn3" href="${ly }/company/yh-analysis?flag=3">第三方检查</a>
        </div>
        <script src="/js/echarts.min.js"></script>
        <%--        柱状图--%>
        <script>
        console.log("flag:","${flag}");
        var url='';
        if('${flag}'==1){
        $("#chaBtn1").addClass('btn-default');
        url='${ly }/company/zhuChartData22?flag=1';
        }
        if('${flag}'==2){
        $("#chaBtn2").addClass('btn-default');
        url='${ly }/company/zhuChartData22?flag=2';
        }
        if('${flag}'==3){
        $("#chaBtn3").addClass('btn-default');
        url='${ly }/company/zhuChartData22?flag=3';
        }
        function addgjs() {
        show_dialog(" ", "/company/yh-analysis?flag=1");
        }
        // 基于准备好的dom，初始化echarts实例
        var number = ["${a}", "${b}", "${c}", "${d}", "${e}", "${f}", "${g}", "${h}", "${i}", "${j}", "${k}", "${l}", "${m}", "${n}"];
        var firstdata2 = ["${a1}", "${b1}", "${c1}", "${d1}", "${e1}", "${f1}", "${g1}", "${h1}", "${i1}", "${j1}", "${k1}", "${l1}", "${m1}", "${n1}"];
        var x="",fdata=[];
        firstdata2.map((v,i)=>{
        x=v*100;
        fdata.push(x);
        });
        console.log(number);
        var myChart = echarts.init(document.getElementById('main2'));
        // 使用刚指定的配置项和数据显示图表。
        // 指定图表的配置项和数据
        option = {
        title: {
        text: '隐患数据分析',
        left: 'center',

        },
        tooltip: {
        trigger:'item',
        padding:[20,10,20,10],
        formatter: function (params) {
        var value=params.value;
        var name=params.name;
        var index=params.dataIndex;
        var num=number[index];
        var tip='<section>'+name+'</section><section>占比: '+value+'%</section><section>数量: '+num+'</section>';
        return '<div class="showBox">' + tip + '</div>';
        }
        },
        legend: {
        data: ['']
        },
        xAxis: {
        type: 'category',
        data: ["生产工艺", "设备设施", "特种特备", "消防安全", "用电安全", "行为环境", "公辅设备", "危化管理", "基础管理", "防雷防电", "安全设施", "职业卫生", "生产现场",
        "其他"
        ],
        axisLabel: {
        interval: 0, //横轴信息全部显示
        rotate: 0, //-30度角倾斜显示
        }
        },

        yAxis: {
        type: 'value',
        axisLabel: {
        show: true,
        showMinLabel: true,
        formatter: '{value} %'
        },
        min: 0,
        max: 100,
        splitNumber: 10

        },
        series: {
        type: 'bar',
        data: fdata,
        itemStyle: {
        normal: {
        color: '#C0504D'
        }
        },
        }
        }
        myChart.setOption(option, true);

        // 监听事件
        $("#chaBtn1").click(() => {
        $("#chaBtn1").addClass('btn-default')
        $("#chaBtn2").removeClass('btn-default')
        $("#chaBtn3").removeClass('btn-default')
        })
        $("#chaBtn2").click(() => {
        $("#chaBtn2").addClass('btn-default')
        $("#chaBtn1").removeClass('btn-default')
        $("#chaBtn3").removeClass('btn-default')

        })
        $("#chaBtn3").click(() => {
        $("#chaBtn3").addClass('btn-default')
        $("#chaBtn1").removeClass('btn-default')
        $("#chaBtn2").removeClass('btn-default')
        });

        myChart.on('click', function(p) {
        window.location.href =url;
        })
        </script>
        </body>

        </html>
