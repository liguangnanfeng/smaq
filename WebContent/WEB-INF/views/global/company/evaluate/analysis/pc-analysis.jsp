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
        <script>
       console.log('${list}');
        console.log('${lists}');
        let listCount=[];
        let listRate=[];
        let lists;
        <c:forEach items="${list}" varStatus="index" var="be">
            console.log('${be.danger1}');
                a1='${be.danger1}'; a2='${be.danger2}'; a3='${be.danger3}'; a4='${be.danger4}'; a5='${be.danger5}';
                a6='${be.danger6}'; a7='${be.danger7}'; a8='${be.danger8}'; a9='${be.danger9}'; a10='${be.danger10}';
                let a=a1*1+a2*1+a3*1+a4*1+a5*1+a6*1+a7*1+a8*1+a9*1+a10*1;
            listCount=[a1,a2,a3,a4,a5,a6,a7,a8,a9,a10];
            listCount.map((v,i)=>{
              x1= (v/a).toFixed(2);
              x1=x1*100;
              listRate.push(x1)
            });
            console.log('listCount:',listCount);
            console.log('listRate:',listRate);
        </c:forEach>

    <c:forEach items="${lists}" varStatus="index" var="be">
        let list2={
        '${be.name19}':'${be.number19}','${be.name1}':'${be.number1}', '${be.name2}':'${be.number2}', '${be.name3}':'${be.number3}', '${be.name4}':'${be.number4}',
        '${be.name5}':'${be.number5}', '${be.name6}':'${be.number6}', '${be.name7}':'${be.number7}', '${be.name8}':'${be.number8}',
        '${be.name9}':'${be.number9}', '${be.name10}':'${be.number10}', '${be.name11}':'${be.number11}', '${be.name12}':'${be.number12}',
        '${be.name13}':'${be.number13}', '${be.name14}':'${be.number14}', '${be.name15}':'${be.number15}', '${be.name16}':'${be.number16}'
        , '${be.name17}':'${be.number17}', '${be.name18}':'${be.number18}'
        }
        let listRateb=[
        '${be.double19}','${be.double1}','${be.double2}','${be.double3}','${be.double4}','${be.double5}','${be.double6}','${be.double7}','${be.double8}',
        '${be.double9}','${be.double10}','${be.double11}','${be.double12}','${be.double13}','${be.double14}','${be.double15}','${be.double16}','${be.double17}',
        '${be.double18}',
        ];
    </c:forEach>
<%--        data:["","", "", "", "设备安全", "", "", "", "",--%>
<%--        "", "", "", "", "",--%>
<%--        "", "", "", "",""--%>
<%--        ],--%>
        listCount2=[
          list2.基础管理,list2.设计总图,list2.试生产,list2.装置运行,list2.设备安全,list2.仪表安全,list2.电气安全,list2.应急消防,list2.特殊管控,
        list2.行为环境,list2.生产现场,list2.公辅工程,list2.特种设备,list2.专项行业,list2.生产工艺,list2.设备设施,list2.危化管理,list2.安全设施,
        list2.其他
        ];

        let listRate2 = Array.from(listRateb,x=>x*100);
        console.log('listCount2:',listCount2)
        console.log('listRateb:',listRateb)



        </script>

        <%--柱状图1--%>

        <div class="select-btn">
        <a class="sbtn btn-default" id="chaBtn0"  href="###">汇总</a>
        <a class="sbtn" onclick="tz('#chaBtn1')" id="chaBtn1"  data-href="${ly }/global/zf-performance-industry?flag=1">企业自查</a>
        <a class="sbtn" onclick="tz('#chaBtn2')" id="chaBtn2" data-href="${ly }/global/zf-performance-industry?flag=2">行政检查</a>
        <a class="sbtn" onclick="tz('#chaBtn3')" id="chaBtn3" data-href="${ly }/global/zf-performance-industry?flag=3">第三方检查</a>
        </div>
        <div id="main" style="width: 90%;height:400px;margin: 50px auto 0 auto;"></div>
<script src="https://sec.dicarl.com/js/H-ui.admin_v3.0/lib/layer/2.4/layer.js"></script>
<script>
        function tz(p){
        layer.load(0, {
        shade: [1,'#fff'] //0.1透明度的白色背景
        });
        window.location.href=$(p).data("href")
        }

</script>
        <%--柱状图2--%>



        <div class="select-btn">
        <a class="sbtn btn-default" id="chaBtn0"  href="###">汇总</a>
        <a class="sbtn" onclick="tz('#chaBtn11')" id="chaBtn11"  href="${ly }/global/zf-check-hidden-trouble?flag=1">企业自查</a>
        <a class="sbtn" onclick="tz('#chaBtn22')" id="chaBtn22" href="${ly }/global/zf-check-hidden-trouble?flag=2">行政检查</a>
        <a class="sbtn" onclick="tz('#chaBtn33')" id="chaBtn33" href="${ly }/global/zf-check-hidden-trouble?flag=3">第三方检查</a>
        </div>
        <div id="main2" style="width: 90%;height:60%;margin: 100px auto 0 auto;"></div>


            <%--        柱状图1--%>
            <script>
            console.log("flag:","${flag}");
            // 基于准备好的dom，初始化echarts实例
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
            },
            }
            ],
            tooltip: {
            trigger:'item',
            padding:[20,10,20,10],
            formatter: function (params) {
            var value=params.value;
            var name=params.name;
            var index=params.dataIndex;
            var num=listCount[index];
            var tip='<section>'+name+'</section><section>占比: '+value+'%</section><section>数量: '+num+'</section>';
            return '<div class="showBox">' + tip + '</div>';
            }
            },
            legend: {
            data: ['']
            },
            xAxis: {
            type: 'category',
            data: ["化工", "冶金", "有色", "建材", "机械", "轻工", "纺织", "商贸", "烟花", "其他"
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
            data: listRate,
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
        var fdata=null;
        console.log("flag:","${flag}");
        // 基于准备好的dom，初始化echarts实例

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
        },
        }
        ],
        tooltip: {
        trigger:'item',
        padding:[20,10,20,10],
        formatter: function (params) {
        var value2=params.value;
        var name2=params.name;
        var index2=params.dataIndex;
        var num2=listCount2[index2];
        var tip2='<section>'+name2+'</section><section>占比: '+value2+'%</section><section>数量: '+num2+'</section>';
        return '<div class="showBox">' + tip2 + '</div>';
        }
        },
        legend: {
        data: ['']
        },
        xAxis: {
        type: 'category',
        data:["基础管理","设计总图", "试生产", "装置运行", "设备安全", "仪表安全", "电气安全", "应急消防", "特殊管控", "行为环境", "生产现场", "公辅工程", "特种设备", "专项行业",
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
        formatter: '{value}%'
        },
        min: 0,
        max: 100,
        splitNumber: 10

        },
        series: {
        type: 'bar',
        data: listRate2,
        itemStyle: {
        normal: {
        color: '#C0504D'
        }
        },
        }
        }
        myChart2.setOption(option2, true);


        </script>




        </body>

        </html>
