    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%@ include file="/WEB-INF/views/taglibs.jsp"%>
        <%@ include file="/WEB-INF/inc/back-header.inc"%>
        <!DOCTYPE html>
        <html>
        <head>
        <title>ECharts</title>
        <!-- 引入 echarts.js -->
        <script src="/js/echarts.min.js"></script>
        </head>
        <body style="height: 100vh;">
        <script>












        var a1={
        一般:'${result11}',
        较大:'${result22}',
        重大:'${result33}'
        }
        console.log('a1:',a1);
        </script>
        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
        <div id="main" style="width: 90%;height:70%;margin: 50px  auto 0 auto;"></div>
        <div id="main2" style="width: 90%;height:60%;margin: 30px auto 30px auto;"></div>
        <div id="main3" style="width: 90%;height:60%;margin: 30px auto 30px auto;"></div>

        <%--饼状图1--%>
        <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例

        var option = {
        title:{ text:'隐患治理地区数据分析',
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
        name: '绩效分析',
        url:url,
        type: 'pie',
        center: ['50%', '50%'],
        data: [
        {
        name: '一般隐患',
        value: '${result11}',
        },
        {
        name: '较大隐患',
        value: '${result22}',
        },
        {
        name: '重大隐患',
        value: '${result33}',
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
        myChart.setOption(option);
        var url = '${ly }/global/manage-hidden-company';


        myChart.on('click', function(params) {
        window.location.href = url;

        });
        </script>




        <%--饼状图2--%>
        <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var a2={
        一般:'${result1}',
        较大:'${result2}',
        重大:'${result3}'
        }
        console.log('a2:',a2);
        var option2 = {
        title:{ text:'隐患治理对象数据分析',
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
        name: '绩效分析',
        url:url,
        type: 'pie',
        center: ['50%', '50%'],
        data: [
        {
        name: '一般隐患',
        value: '${result1}',
        },
        {
        name: '较大隐患',
        value: '${result2}',
        },
        {
        name: '重大隐患',
        value: '${result3}',
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
        var myChart2 = echarts.init(document.getElementById('main2'));
        myChart2.setOption(option2);

        var url2 = '${ly }/global/manage-hidden-danger';
        myChart2.on('click', function(params) {
        window.location.href = url2;

        });
        </script>






        <%--饼状图3--%>
        <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var a3={
        一般:'${result4}',
        较大:'${result5}',
        重大:'${result6}'
        }
        console.log('a3:',a3);
        var option3 = {
        title:{ text:'隐患治理行业数据分析',
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
        name: '绩效分析',
        url:url,
        type: 'pie',
        center: ['50%', '50%'],
        data: [
        {
        name: '一般隐患',
        value: '${result4}',
        },
        {
        name: '较大隐患',
        value: '${result5}',
        },
        {
        name: '重大隐患',
        value: '${result6}',
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
        var myChart = echarts.init(document.getElementById('main3'));
        myChart3.setOption(option3);
        var url3 = '${ly }/global/manage-hidden-industry';
        myChart3.on('click', function(params) {
        window.location.href = url3;

        });
        </script>





















        </body>

        </html>
