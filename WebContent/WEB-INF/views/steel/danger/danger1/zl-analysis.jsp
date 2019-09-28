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
        var a2={
            较大:'${number4}',//较大
            重大:'${number5}',//重大
            一般:'${number6}'//一般
        }
        console.log('a2:',a2);
        </script>
        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
        <div id="main2" style="width: 90%;height:60%;margin: 30px auto 30px auto;"></div>

        <div id="main" style="width: 90%;height:70%;margin: 50px  auto 0 auto;"></div>

        <script src="/js/echarts.min.js"></script>
        <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例

        var option = {
        title:{ text:'隐患治理等级分析',
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
        value: '${number6}',
        },
        {
        name: '较大隐患',
        value: '${number4}',
        },
        {
        name: '重大隐患',
        value: '${number5}',
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
        var url = '${ly }/company/zhuChartData44';
        myChart.on('click', function(params) {
        window.location.href = url;

        });
        </script>




<%--2柱状图--%>
            <script>
            var myChart2 = echarts.init(document.getElementById('main2'));
            // 使用刚指定的配置项和数据显示图表。
            // 指定图表的配置项和数据
            var number = ["${s}","${a}", "${b}", "${c}", "${d}", "${e}", "${f}", "${g}", "${h}", "${i}", "${j}", "${k}", "${l}", "${m}", "${n}", "${o}", "${p}", "${q}", "${r}"];
            var firstdata2 = ["${s1}","${a1}", "${b1}", "${c1}", "${d1}", "${e1}", "${f1}", "${g1}", "${h1}", "${i1}", "${j1}", "${k1}", "${l1}", "${m1}", "${n1}", "${o1}", "${p1}", "${q1}", "${r1}"];
            var x="",fdata=[];
            firstdata2.map((v,i)=>{
            x=v*100;
            fdata.push(x);
            });
            option2 = {
            title: [{
            text: '隐患治理对象分析',
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
        }
        } ],
            tooltip: {
            trigger:'item',
            padding:[20,10,20,10],
            formatter: function (params) {
            console.log(params.dataIndex);
            var value=params.value.toFixed(2);
            var name=params.name;
            var index=params.dataIndex;
            var num=number[index];
            console.log(value,num);
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
            myChart2.setOption(option2);
            var url2="${ly }/steel/zhuChartData123";
            myChart2.on('click', function(p) {
            window.location.href =url2;
            })



            </script>
























        </body>

        </html>
