    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%@ include file="/WEB-INF/views/taglibs.jsp"%>
        <%@ include file="/WEB-INF/inc/back-header.inc"%>
        <!DOCTYPE html>
        <html>
        <head>
        <title>ECharts</title>
        <!-- 引入 echarts.js -->
            <script src="/js/echarts.min.js"></script>
        <style>
        .select-btn{
        margin: 47px auto 64px auto;
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


        <%--柱状图1--%>

        <div class="select-btn">
        <a class="sbtn btn-default" id="chaBtn0"  href="###">汇总</a>
        <a class="sbtn" id="chaBtn1"  href="${ly }/steel/zf-performance-industry?flag=1">企业自查</a>
        <a class="sbtn" id="chaBtn2" href="${ly }/steel/zf-performance-industry?flag=2">行政检查</a>
        <a class="sbtn" id="chaBtn3" href="${ly }/steel/zf-performance-industry?flag=3">第三方检查</a>
        </div>
        <div id="main" style="width: 90%;height:400px;margin: 50px auto 0 auto;"></div>


        <%--柱状图2--%>

        <div class="select-btn">
        <a class="sbtn btn-default" id="chaBtn0"  href="###">汇总</a>
        <a class="sbtn" id="chaBtn11"  href="${ly }/steel/zf-check-hidden-trouble?flag=1">企业自查</a>
        <a class="sbtn" id="chaBtn22" href="${ly }/steel/zf-check-hidden-trouble?flag=2">行政检查</a>
        <a class="sbtn" id="chaBtn33" href="${ly}/steel/zf-check-hidden-trouble?flag=3">第三方检查</a>
        </div>
        <div id="main2" style="width: 90%;height:60%;margin: 100px auto 0 auto;"></div>


            <%--        柱状图1--%>
            <script>
            console.log("flag:","${flag}");
            // 基于准备好的dom，初始化echarts实例
            let number = ["${danger1}", "${danger2}", "${danger3}", "${danger4}", "${danger5}", "${danger6}", "${danger7}", "${danger8}"];
            var firstdata = ["${result1}", "${result2}", "${result3}", "${result4}", "${result5}", "${result6}", "${result7}", "${result8}"];
            var x="",fdata=[];
            console.log(number);
            firstdata.map((v,i)=>{
            x=v*100;
            fdata.push(x);
            });
            console.log('fdata:',fdata)
            var myChart = echarts.init(document.getElementById('main'));
            // 使用刚指定的配置项和数据显示图表。
            // 指定图表的配置项和数据
            option = {
            title: [{
            text: '隐患排查绩效分析',
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
            data: ${companyNames},
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
            formatter: '{value}%'
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
            };
            myChart.setOption(option, true);


</script>





            <%--        柱状图2--%>







<script>
        console.log("flag:","${flag}");

        console.log('sign:,${sign}')
        // 基于准备好的dom，初始化echarts实例
        var number2 = ["${number1}", "${number2}", "${number3}", "${number4}", "${number5}", "${number6}", "${number7}", "${number8}", "${number9}", "${number10}", "${number11}", "${number12}", "${number13}", "${number14}","${number15}"];
        var firstdata2 = ["${double1}", "${double2}", "${double3}", "${double4}", "${double5}", "${double6}", "${double7}", "${double8}", "${double9}", "${double10}", "${double11}", "${double12}", "${double13}", "${double14}","${double15}"];
        var x2="",fdata2=[];
        console.log(number);
        firstdata2.map((v,i)=>{
        x2=v*100;
        fdata2.push(x2);
        });
        console.log('fdata2:',fdata2)
        var myChart2 = echarts.init(document.getElementById('main2'));
        // 使用刚指定的配置项和数据显示图表。
        // 指定图表的配置项和数据
        option2 = {
        title: [{
        text: '隐患排查对象分析',
        left: 'center',
        },
        {
        subtext:'合计：${count1}',
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
        var value2=params.value.toFixed(2);
        var name2=params.name;
        var index2=params.dataIndex;
        var num2=number2[index2];
        var tip2='<section>'+name2+'</section><section>占比: '+value2+'%</section><section>数量: '+num2+'</section>';
        return '<div class="showBox">' + tip2 + '</div>';
        }
        },
        legend: {
        data: ['']
        },
        xAxis: {
        type: 'category',
        data: ["基础管理", "生产工艺", "设备设施", "公用工程", "特种设备", "生产现场", "行为环境", "危化管理", "消防安全", "用电安全", "安全设施", "防雷静电", "职业卫生",
        "专项行业","其他"
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
        formatter: '{value}%'
        },
        min: 0,
        max: 100,
        splitNumber: 10

        },
        series: {
        type: 'bar',
        data: fdata2,
        itemStyle: {
        normal: {
        color: '#C0504D'
        }
        },
        }
        }
        myChart2.setOption(option2, true);

        // 监听事件

        </script>




        </body>

        </html>
