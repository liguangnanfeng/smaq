<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <%@ include file="/WEB-INF/inc/back-header.inc" %>
    <title>风险分级管控 隐患排查治理智能化平台</title>
    <meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
    <meta name="description" content="风险分级管控   隐患排查治理智能化平台">
    <style type="text/css">
        .panel-default {
            display: inline-block;
            border: 0px;
        }

        body, html {
            background: #EBEEF3
        }

        .title_xw {
            width: 100%;
            font-weight: bold;
            font-size: 15px;
        }

        .one_notice {
            width: 100%;
            margin-top: 0px;
        }

        .list_aq {
            margin-top: 10px;
        }

        .text-overflow {
            display: inline-block;
            width: 70%;
            line-height: 25px;
            color: #7C7C7C;
            margin-right: 0px;
        }

        .text-overflow i {
            height: 3px;
            width: 3px;
            background: #7C7C7C;
            border-radius: 50%;
            display: inline-block;
            vertical-align: middle;
            margin-right: 5px;
        }

        .text-overflow:hover a {
            color: #5889D8;
            text-decoration: underline;
        }

        .text-overflow:hover i {
            background: #5889D8;
        }

        .text-riqi {
            float: right;
            color: #AEAEAE;
            line-height: 25px;
        }

        .one_tx {
            margin-top: 20px;
            width: 31%;
            margin-right: 2%
        }

        body .div_company2 span {
            margin-top: 10px;
        }

        .clearfix:after {
            content: "\20";
            display: block;
            height: 0;
            clear: both;
            visibility: hidden
        }

        .clearfix {
            zoom: 1
        }

        .tabBar {
            border-bottom: 2px solid #F6F6F6
        }

        .tabBar span {
            background-color: #fff;
            cursor: pointer;
            font-size: 15px;
            display: inline-block;
            float: left;
            font-weight: bold;
            height: 40px;
            line-height: 40px;
            padding: 0 35px
        }

        .tabBar span.current {
            background-color: #fff;
            color: #FF9900;
            border: 0px;
            border-bottom: 2px solid #FF9900
        }

        .tabCon {
            display: none;
            border: 0px;
            padding: 0;
        }

        .bt_zd {
            background: #DF2E30;
            padding: 0 9px;
            color: #fff;
            font-size: 12px;
            margin-left: 10px;
            border-radius: 5px;
            height: 25px;
            line-height: 25px;
            display: inline-block;
        }

        .div_zhe {
            width: 100%;
        }

        .change_zhe {
            width: 100%;
            height: 35px;
            position: relative;
        }

        .btn-group {
            position: absolute;
            right: 0;
            top: 14px;
        }

        .btnyh {
            color: #333;
            background: #e6e6e6;
        }

        .btnyhxz {
            color: #fff;
            background: #5a98de;
        }

        body .iconxw {
            transform: rotate(-90deg);
            float: right;
            margin-right: -5px;
            margin-top: -3px;
        }
    </style>
</head>
<body>
<nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
    <span class="c-gray en">&gt;</span> <span>隐患统计分析系统</span>
    <span class="c-gray en">&gt;</span> <span>隐患数据分析</span>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
</nav>
<div class="panel panel-default" style="width:99%;margin-left:10px;float:left;background:none;">
    <div class="div_tx">
        <div class="title_xw pb-5 pos-r" style="border-bottom:1px solid #F6F6F6">排查数据分析

            <select class="sel_area isShow" id="partNamme"  onchange="int()" style="position:relative;top:3px">
                <option value="" >全部</option>
                <option value="1" >企业自查</option>
                <option value="2" >行政检查</option>
                <option value="3" >部门抽查</option>
            </select>

            <select class="sel_area isShow" id="partNammes"  onchange="int()" style="position:relative;top:3px">
                <option value="" >全部</option>
                <c:forEach items="${zzjg }" var="be">
                    <option value="${be.id}" >${be.name }</option>
                </c:forEach>
            </select>

            <!-- 判断并提示至少选择3个月 -->
            <div class="search_rq pos-a">
                <font>查询时间段：</font>
                <input type="text" class="Wdate input-text" id="sT" style="width:150px;"
                       onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'eT\',{M:-1})}',maxDate:'#F{$dp.$D(\'eT\',{d:-1}) || \'%y-%M-{%d-1}\'}'})"/>
                <font>-</font>
                <input type="text" class="Wdate input-text" id="eT" style="width:150px;"
                       onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'sT\',{d:+1})}',maxDate :getMaxDate()})"/>
                <button class="btn btn-success" type="button" onclick="my_search()">
                    <i class="Hui-iconfont">&#xe665;</i> 查询
                </button>
            </div>
        </div>
        <div class="div_zhe">
            <div class="change_zhe">
                <div class="btn-group">
                    <span class="btn btnyh btnyhxz radius zhtj2">综合统计</span>
                    <span class="btn btnyh radius yhlx2">隐患类型</span>
                    <span class="btn btnyh radius yhly2">隐患来源</span>
                    <span class="btn btnyh radius yhdj2">隐患等级</span>
                </div>
            </div>
            <div id="container3" style="width:100%;height:450px;display: inline-block;margin-top:25px;"></div>
        </div>
    </div>
</div>
</body>

<script>

    var categories = [];//日期数组
    var series = {};  //数据数组
    var flag = 0;      //查看类型0:综合数据 1:隐患类型 2:隐患来源 3:隐患等级


    function int(f) {
        var vs = $('#partNammes  option:selected').val();
        var vs1 = $('#partNamme  option:selected').val();
    console.log(vs)
    console.log(vs1)
        var url ='';
        if(f==0){
            url= "/company/zhuChartData3";
        }else if(f==1){
            url= "/company/zhuChartData5?flags="+vs1+"&depart="+vs;
        }else if(f==2){
            url= "/company/zhuChartData6";
        }else{
            url= "/company/zhuChartData7";
        }
        $.post(getRootPath() + url, {
            sT: $("#sT").val(),
            eT: $("#eT").val()
        }, function (result) {
    <%--console.log(result)--%>

    categories = result.map.categories;
            series = result.map.series;
            var extra = {};
            extra.name =
            extra.name = '条目汇总 '+result.map.count+' 条';
            var li = new Array();
            for(var i =0;i<categories.length;i++) {
                li.push(0);
            }
            extra.data = li;
            extra.visible = false;
            series.push(extra);
            createChart(categories, series);
        })
    }

    function createChart(categories, series) {
        $('#container3').highcharts({
            credits: {enabled: false},
            chart: {type: 'column'},
            title: {text: '排查数据分析'},
            legend: {
                itemHiddenStyle: {
                    color: '#f00'
                }
            },
            xAxis: {categories: categories},
            yAxis: {title: {text: '数量'}, min: 0},
            tooltip: {
                formatter: function (res,ret,rep) {

    <%--console.log(res)--%>
    <%--console.log(ret)--%>
    <%--console.log(rep)--%>
    <%--return this.y + '个月 ';--%>
                    var content ='<table>'
                    for (var i = 0; i < this.points.length; i++) {
                    content += '<tr><td style="padding:0;color: ' + this.points[i].series.color + '">' + this.points[i].series.name + ':</td> <td style="padding:0"><b>' + this.points[i].y + '</b></td></tr>';

                    if(i==1){
                        var jj;
                        if(this.points[0].y==0){
                            jj=100;
                        }else{
                            jj=Highcharts.numberFormat(this.points[0].y/(this.points[0].y+this.points[1].y+this.points[2].y)*100,2);
                        }
                        content += '<tr><td style="padding:0;color:#333">占比率：</td><td>'+jj +'%</td></tr>';
                        content += '<tr><td style="padding:0;color:#333">占比率：</td><td>'+jj +'%</td></tr>';
                    }
                    };
                    return content;
                },
                shared: true,
                useHTML: true
            },
            plotOptions: {
                line: {
                    dataLabels: {enabled: true},// 开启数据标签
                    // enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
                },
                // series: {
                //   events: {
                //     legendItemClick: function (e) {
                //       return false; // 直接 return false 即可禁用图例点击事件
                //     }
                //   }
                // }
            },
            series: series
        });
    }

    function my_search() {
        var sT = $("#sT").val();
        var eT = $("#eT").val();
        if (!sT) {
            layer.msg('请选择开始日期');
            return
        }
        if (!eT) {
            layer.msg('请选择结束日期');
            return
        }
        int(flag);
    }

    $(".zhtj2").click(function(){
        $(".btnyh").removeClass("btnyhxz");
        $(this).addClass("btnyhxz");
        flag = 0;
        int(flag);
    });


    $(".yhlx2").click(function(){
        $(".btnyh").removeClass("btnyhxz");
        $(this).addClass("btnyhxz");
        flag = 1;
        int(flag);
    });



    $(".yhly2").click(function(){
        $(".btnyh").removeClass("btnyhxz");
        $(this).addClass("btnyhxz");
        $("#my_flag").val(2);
        flag = 2;
        int(flag);
    });

    $(".yhdj2").click(function(){
        $(".btnyh").removeClass("btnyhxz");
        $(this).addClass("btnyhxz");
        $("#my_flag").val(3);
        flag = 3;
        int(flag);
    });


</script>
<script type="text/javascript" src="${ly }/js/c-chart.js"></script>
</html>