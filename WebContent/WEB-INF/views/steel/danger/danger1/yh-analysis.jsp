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
        <div class="select-btn">
        <a class="sbtn btn-default" id="chaBtn0"  href="###">汇总</a>
        <a class="sbtn" id="chaBtn1"  href="${ly }/company/zhuChartData22?flag=1">企业自查</a>
        <a class="sbtn" id="chaBtn2" href="${ly }/company/zhuChartData22?flag=2">行政检查</a>
        <a class="sbtn" id="chaBtn3" href="${ly }/company/zhuChartData22?flag=3">第三方检查</a>
        </div>
        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
        <div id="main2" style="width: 90%;height:400px;margin: 50px auto 0 auto;"></div>
        <%--        <iframe id="colum" src=""></iframe>--%>

        <div id="main3" style="width: 90%;height:70%;margin: auto;"></div>
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
        var number = ["${s}","${a}", "${b}", "${c}", "${d}", "${e}", "${f}", "${g}", "${h}", "${i}", "${j}", "${k}", "${l}", "${m}", "${n}", "${o}", "${p}", "${q}", "${r}"];
        var firstdata2 = ["${s1}","${a1}", "${b1}", "${c1}", "${d1}", "${e1}", "${f1}", "${g1}", "${h1}", "${i1}", "${j1}", "${k1}", "${l1}", "${m1}", "${n1}", "${o1}", "${p1}", "${q1}", "${r1}"];
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
        title: [{
        text: '隐患数据分析',
        left: 'center',

        },
        {
        subtext:'合计：${count}',
        top:10,
        right: '15%',
        subtextStyle:{//副标题内容的样式
        color:'#333',//绿色
        <%--        fontStyle:'normal',//主标题文字字体风格，默认normal，有italic(斜体),oblique(斜体)--%>
        <%--        fontWeight:"lighter",//可选normal(正常)，bold(加粗)，bolder(加粗)，lighter(变细)，100|200|300|400|500...--%>
        <%--        fontFamily:"san-serif",//主题文字字体，默认微软雅黑--%>
        <%--        fontSize:12//主题文字字体大小，默认为12px--%>
        },
        }
        ],
        tooltip: {
        trigger:'item',
        padding:[20,10,20,10],
        formatter: function (params) {
        var value=params.value.toFixed(2);
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
        data: ["基础管理","设计总图", "试生产", "装置运行", "设备安全", "仪表安全", "电气安全", "应急消防", "特殊管控", "行为环境", "生产现场", "公辅工程", "特种设备", "专项行业",
        "生产工艺", "设备设施", "危化管理", "安全设施","其他"
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


        </script>





















        <script>
        var a={
        较大:'${number1}',//较大
        重大:'${number2}',//重大
        一般:'${number3}'//一般
        }
        console.log('a:',a);
        </script>
        <%--3隐患等级数据分析--%>
        <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var url3 = '${ly }/company/zhuChartData77';

        var option3 = {
        title:{
        text:'隐患等级数据分析',
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
        url:url3,
        data: [
        {
        name: '一般隐患',
        value: '${number3}',
        },
        {
        name: '较大隐患',
        value: '${number1}',
        },
        {
        name: '重大隐患',
        value: '${number2}',
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
        var myChart3 = echarts.init(document.getElementById('main3'));
        myChart3.setOption(option3);
        myChart3.on('click', function(params) {
        console.log(url3);
        window.location.href = url3;
        });

        </script>

        </body>

        </html>
