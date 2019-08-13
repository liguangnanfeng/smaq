    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%@ include file="/WEB-INF/views/taglibs.jsp"%>
        <%@ include file="/WEB-INF/inc/back-header.inc"%>
        <!DOCTYPE html>
        <html>
        <head>
        <title>ECharts</title>
        <!-- 引入 echarts.js -->
        </head>
        <body style="height: 100vh;">
        <script>
        var a={
        1:'${number1}',
        2:'${number2}',
        3:'${number3}'
        }
        console.log(a);
        </script>
        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
        <div id="main" style="width: 90%;height:70%;margin: auto;"></div>
        <script src="/js/echarts.min.js"></script>
        <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        window.onload = function() {
        var url = '${ly }/company/zhuChartData77';
        var option = {
        title:{
        padding: [15,0,0,0],
        left:'center',
        textStyle:{
        fontStyle:'normal',
        //字体粗细 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
        fontWeight:'bold',
        //字体系列
        fontFamily:'sans-serif',
        //字体大小
        　　　　 fontSize:18
        }
        },
        series: {
        type: 'pie',
        center: ['50%', '50%'],
        url:url,
        data: [
        {
        name: '一般隐患',
        value: 4,
        },
        {
        name: '较大隐患',
        value: 4,
        },
        {
        name: '重大隐患',
        value: 2,
        }
        ],
        itemStyle: {
        // emphasis：英文意思是 强调;着重;（轮廓、图形等的）鲜明;突出，重读
        // emphasis：设置鼠标放到哪一块扇形上面的时候，扇形样式、阴影
        emphasis: {
        shadowBlur: 10,
        shadowOffsetX: 0,
        shadowColor: 'rgba(30, 144, 255，0.5)'
        }
        },
        // 设置值域的那指向线
        labelLine: {
        normal: {
        show: false // show设置线是否显示，默认为true，可选值：true ¦ false
        }
        },
        // 设置值域的标签
        label: {
        normal: {
        position: 'inner', // 设置标签位置，默认在饼状图外 可选值：'outer' ¦ 'inner（饼状图上）'
        // formatter: '{a} {b} : {c}个 ({d}%)'   设置标签显示内容 ，默认显示{b}
        // {a}指series.name  {b}指series.data的name
        // {c}指series.data的value  {d}%指这一部分占总数的百分比
        formatter: '{b}  {d}%',
        }
        }


        },
        tooltip: {
        show: false //隐藏鼠标悬浮提示文字
        //trigger: 'item'
        },
        color: ['#4F81BD','#C0504D','#9BBB59'],
        }
        var myChart = echarts.init(document.getElementById('main'));
        myChart.setOption(option)
        myChart.on('click', function(params) {
        var url = params.url;
        console.log(url);
        window.location.href = url;

        });
        }
        </script>
        </body>

        </html>
