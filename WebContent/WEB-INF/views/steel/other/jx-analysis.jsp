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
        #selectBranch{
            position: absolute;
            top: 142px;
            right: 100px;
            width: 100px;
            height: 32px;
            padding: 4px;
            font-size: 16px;
            border: 1px solid #ddd;
            box-shadow: 5px 5px 5px #fff;
            color: black;
            border-radius: 5px;
            z-index: 100000000000000000000000;
        }
        </style>
        </head>
        <body style="height: 100vh;">
        <script>
        var a={
            1:'${number1}',
            2:'${number2}',
            3:'${number3}'
        }
        </script>
        <select id="selectBranch">
        </select>
        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
        <div class="select-btn">
        <a class="sbtn" id="chaBtn1"  href="${ly }/steel/jx-analysis?flag=1&userId=${userId}">企业自查</a>
        <a class="sbtn" id="chaBtn2" href="${ly }/steel/jx-analysis?flag=2&userId=${userId}">行政检查</a>
        <a class="sbtn" id="chaBtn3" href="${ly }/steel/jx-analysis?flag=3&userId=${userId}">第三方检查</a>
        </div>
        <!--企业列表-->

        <div id="main2" style="width: 90%;height:400px;margin: 50px auto 0 auto;"></div>
<%--        <iframe id="colum" src=""></iframe>--%>

        <div id="main" style="width: 90%;height:60%;margin: 100px auto 0 auto;"></div>
        <script src="/js/echarts.min.js"></script>
        <script>
            $(function () {
                $.ajax({
                    url:"${ly}/steel/company-list8",
                    type:"get",
                    success:function (data) {
                        var html="";
                        for(var i=0; i<data.map.list.length; i++){
                            if(data.map.list[i]["id"]==${userId}){
                                html += '<option value="'+data.map.list[i]["id"]+'" selected="selected">'+data.map.list[i]["name"]+'</option>';
                            }else{
                                html += '<option value="'+data.map.list[i]["id"]+'">'+data.map.list[i]["name"]+'</option>';
                            }

                        }
                        $("#selectBranch").html(html);
                    },
                    error:function(){
                        alert("error");
                    }
                });
                $("#selectBranch").change(function () {

                    window.location.href="${ly }/steel/jx-analysis?flag=${flag}&userId="+$(this).val();
                })
            })
        </script>
        <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        window.onload = function() {
        var u1 = '${ly }/steel/danger-chart-jx?userId=${userId}';
        var u2 = '${ly }/steel/zhuChartData55?flag=2&userId=${userId}';
        var u3 = '${ly }/steel/zhuChartData55?flag=3&userId=${userId}';
        var option = {
        title:{
        text:'排查绩效分析',
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
        type: 'pie',
        center: ['50%', '50%'],
        data: [
        {
        name: '企业自查',
        value: '${number1}',
        url: u1
        },
        {
        name: '行政检查',
        value: '${number2}',
        url: u2
        },
        {
        name: '第三方检查',
        value: '${number3}',
        url: u3
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
        var url = params.data.url;
        window.location.href = url;
        });
        }
        </script>



        <%--        柱状图--%>
        <script>
         console.log("flag:","${flag}");
        var url='';
        if('${flag}'==1){
        $("#chaBtn1").addClass('btn-default');
         url='${ly }/steel/zhuChartData66?flag=1&userId=${userId}';
        }
        if('${flag}'==2){
        $("#chaBtn2").addClass('btn-default');
         url='${ly }/steel/zhuChartData66?flag=2&userId=${userId}';
        }
        if('${flag}'==3){
        $("#chaBtn3").addClass('btn-default');
         url='${ly }/steel/zhuChartData66?flag=3&userId=${userId}';
        }
            function addgjs() {
                show_dialog(" ", "/steel/jx-analysis?flag=1&userId=${userId}");
            }
        // 基于准备好的dom，初始化echarts实例
        var number = ["${a}", "${b}", "${c}", "${d}", "${e}", "${f}", "${g}", "${h}", "${i}", "${j}", "${k}", "${l}", "${m}", "${n}"];
        var firstdata2 = ["${a1}", "${b1}", "${c1}", "${d1}", "${e1}", "${f1}", "${g1}", "${h1}", "${i1}", "${j1}", "${k1}", "${l1}", "${m1}", "${n1}"];
        var x="",fdata=[];
        console.log(number);
        firstdata2.map((v,i)=>{
           x=v*100;
           fdata.push(x);
        });
        var myChart = echarts.init(document.getElementById('main2'));
        // 使用刚指定的配置项和数据显示图表。
        // 指定图表的配置项和数据
        option = {
        title: [{
        text: '排查对象分析',
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
          console.log(params);
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
        url='${ly }/company/zhuChartData66?flag=1';
        $("#chaBtn1").addClass('btn-default')
        $("#chaBtn2").removeClass('btn-default')
        $("#chaBtn3").removeClass('btn-default')
        let number11 = ["${a}", "${b}", "${c}", "${d}", "${e}", "${f}", "${g}", "${h}", "${i}", "${j}", "${k}", "${l}", "${m}", "${n}"];
        let firstdata11 = ["${a1}", "${b1}", "${c1}", "${d1}", "${e1}", "${f1}", "${g1}", "${h1}", "${i1}", "${j1}", "${k1}", "${l1}", "${m1}", "${n1}"];
        let x11="",fdata11=[];
        firstdata11.map((v,i)=>{
        x11=v*100;
        fdata11.push(x11);
        });
        })
        $("#chaBtn2").click(() => {
        url='${ly }/company/zhuChartData66?flag=2';

        $("#chaBtn2").addClass('btn-default')
        $("#chaBtn1").removeClass('btn-default')
        $("#chaBtn3").removeClass('btn-default')

        let number22= ["${a}", "${b}", "${c}", "${d}", "${e}", "${f}", "${g}", "${h}", "${i}", "${j}", "${k}", "${l}", "${m}", "${n}"];
        let firstdata22 = ["${a1}", "${b1}", "${c1}", "${d1}", "${e1}", "${f1}", "${g1}", "${h1}", "${i1}", "${j1}", "${k1}", "${l1}", "${m1}", "${n1}"];
        let x22="",fdata22=[];
        firstdata22.map((v,i)=>{
        x22=v*100;
        fdata22.push(x22);
        });
        })
        $("#chaBtn3").click(() => {
        url='${ly }/company/zhuChartData66?flag=3';
        $("#chaBtn3").addClass('btn-default')
        $("#chaBtn1").removeClass('btn-default')
        $("#chaBtn2").removeClass('btn-default')
        let number33 = ["${a}", "${b}", "${c}", "${d}", "${e}", "${f}", "${g}", "${h}", "${i}", "${j}", "${k}", "${l}", "${m}", "${n}"];
        let firstdata33 = ["${a1}", "${b1}", "${c1}", "${d1}", "${e1}", "${f1}", "${g1}", "${h1}", "${i1}", "${j1}", "${k1}", "${l1}", "${m1}", "${n1}"];
        let x33="",fdata33=[];
        firstdata33.map((v,i)=>{

        x33=v*100;
        fdata33.push(x33);
        });
        });
       myChart.on('click', function(p) {
        window.location.href =url;
        })
        </script>
        </body>

        </html>