    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%@ include file="/WEB-INF/views/taglibs.jsp"%>
        <!DOCTYPE html>
        <html>

        <head>
        <meta charset="utf-8" />
        <title></title>
            <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
            <link rel="stylesheet" type="text/css" href="/js/H-ui.admin_v3.0/lib/Hui-iconfont/1.0.8/iconfont.css" />
            <link rel="stylesheet" type="text/css" href="/js/H-ui.admin_v3.0/static/h-ui.admin/css/H-ui.admin.css" />
            <link rel="stylesheet" type="text/css" href="/js/H-ui.admin_v3.0/static/h-ui/css/H-ui.css" />
            <style>
            nav.breadcrumb>span {
            padding: 0;
            font-size: 12px;
            }
            .breadcrumb{
            height: 39px;
            line-height: 39px;
            overflow: hidden;
            }
            </style>
            <style>
            table {
            width:100%;
            border-collapse: collapse;
            font-size: 13px;
            color: #333;
            }
        table,
        tr,
        td {
        border: 1px solid #ddd!important;
        }

        td {
        text-align: center;
        height: 38px;
        /*这里需要自己调整，根据自己的需求调整高度*/
        position: relative;
            padding: 5px;
        }

        td[class=first] {
        width: 155px;
        }

        td[class=first]:before {
            content: "";
            position: absolute;
            width: 1px;
            height: 187px;
            top: 0;
            left: 0;
            background-color: #ddd;
            display: block;
            transform: rotate(-59deg);
            transform-origin: top;
            }


        .title1 {
        position: absolute;
        top: 11px;
        right: 7px;
        }

        .title2 {
        position: absolute;
        top: 30px;
        left: 6px;
        }

        .title3 {
        position: absolute;
        top: 48px;
        left: 10px;
        }

            .center {
            text-align: center;
            font-weight: 500;
            }

        .flexcenter {
        display: flex;
        justify-content: center;
        }

        .flexspa {
        display: flex;
        justify-content: space-between;
        margin: 10px 0;
        }

        .containerx {
        padding: 20px;
        }

        .sum {
        width: 70px;
        }

        #time {
        font-size: 15px;
        }

        .tcontent>td:nth-child(2){
            padding: 3px;
        }
        </style>
        </head>

        <body>
            <nav class="breadcrumb">
            <i class="Hui-iconfont"></i>
            <span>首页</span>
            <span class="c-gray en">&gt;</span> <span>排查数据分析</span>
            <span class="c-gray en">&gt;</span> <span class="aaa"> </span>
            <a class="btn btn-success radius r bit_hid" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:history.back(-1)" title="返回">返回</a>
            </nav>

            <div class="containerx">
        <div class="text-c mt-20 ">
        <h3 class="center"></h3>
        </div>
        <section class="title">
        <div class="flexspa">
        <span class=""></span>
        <span id="time">
        </span>
        </div>
        </section>

        <script>
        var searchURL = window.location.search;
        searchURL = searchURL.substring(1, searchURL.length);
        var targetPageId = searchURL.split("&")[0].split("=")[1];
        console.log('targetPageId:', targetPageId);
        var title = "";
        if (targetPageId == 1) {
        title = "企业自查对象分析";
        console.log('title:', title);
        }
        if (targetPageId == 2) {
        title = "行政检查对象分析"; console.log('title:', title);
        }
        if (targetPageId == 3) {
        title = "第三方检查对象分析"; console.log('title:', title);
        }
        $(".center").text(title);
        $(".aaa").text(title);
        </script>
        </section>
        <table class=".table table-hover" style="margin: auto;" cellpadding="2">
        <tr class="trf" style="background-color: #F5F9FE;font-weight: bold;">
        <td rowspan="2">序号</td>
        <td class="first" rowspan="2"><span class="title1">对象</span><br><span class="title3">地区</span></td>
        <td>基础管理</td>
        <td>生产工艺</td>
        <td>设备设施</td>
        <td>公用工程</td>
        <td>特种设备</td>
        <td>生产现场</td>
        <td>行为环境</td>
        <td>危化管理</td>
        <td>消防安全</td>
        <td>用电安全</td>
        <td>安全设施</td>
        <td>防雷静电</td>
        <td>职业卫生</td>
        <td>专项行业</td>
        <td>其他</td>
        <td rowspan="2" class="sum">合计</td>
        <td rowspan="2">排名</td>
        <td rowspan="2">同比%</td>
        </tr>
        <tr style="background-color: #F5F9FE;    font-weight: bold;">
        <td>频次</td>
        <td>频次</td>
        <td>频次</td>
        <td>频次</td>
        <td>频次</td>
        <td>频次</td>
        <td>频次</td>
        <td>频次</td>
        <td>频次</td>
        <td>频次</td>
        <td>频次</td>
        <td>频次</td>
        <td>频次</td>
        <td>频次</td>
        <td>频次</td>
        <script>
        console.log('[{sum10=0, sum11=0, sum12=0, sum13=0, sum14=0, result14=0.00%, result3=0.00%, result4=0.00%, result1=0.00%, result2=0.00%, result9=0.00%, result7=0.00%, result8=0.00%, result5=0.00%, result6=0.00%, result13=0.00%, result12=0.00%, result11=0.00%, result10=0.00%, sum2=0, sum1=0, sum4=0, sum3=0, sum6=0, sum5=0, sum8=0, sum7=0, sum9=0}]');
        window.a = 0;
        </script>
        </tr>
            <c:forEach items="${list}" varStatus="index" var="be">
                    <c:if test="${index.index<fn:length(list)-1}">
                            <tr class="tcontent">
                            <td>${index.index + 1}</td>
                            <td>${be.name}</td>
                            <td>${be.danger1}</td>
                            <td>${be.danger2}</td>
                            <td>${be.danger3}</td>
                            <td>${be.danger4}</td>
                            <td>${be.danger5}</td>
                            <td>${be.danger6}</td>
                            <td>${be.danger7}</td>
                            <td>${be.danger8}</td>
                            <td>${be.danger9}</td>
                            <td>${be.danger10}</td>
                            <td>${be.danger11}</td>
                            <td>${be.danger12}</td>
                            <td>${be.danger13}</td>
                            <td>${be.danger14}</td>
                            <td>${be.danger15}</td>
                            <td>${be.danger16}</td>
                            <td></td>
                            <td></td>
                            <script>
                            window.a=window.a+'${be.danger16}'*1;
                            console.log(window.a);
                            </script>
                            </tr>
                    </c:if>

                    <c:if test="${index.index>fn:length(list)-2 }">
                            <tr>
                            <td>合计</td>
                            <td>---</td>
                            <td>${be.sum1}</td>
                            <td>${be.sum2}</td>
                            <td>${be.sum3}</td>
                            <td>${be.sum4}</td>
                            <td>${be.sum5}</td>
                            <td>${be.sum6}</td>
                            <td>${be.sum7}</td>
                            <td>${be.sum8}</td>
                            <td>${be.sum9}</td>
                            <td>${be.sum10}</td>
                            <td>${be.sum11}</td>
                            <td>${be.sum12}</td>
                            <td>${be.sum13}</td>
                            <td>${be.sum14}</td>
                            <td>${be.sum15}</td>
                            <td class="tdd">${be.counts}</td>
                            <td></td>
                            <td></td>
                            </tr>

                    </c:if>
                    <c:if test="${index.index>fn:length(list)-2 }">
                            <tr>
                            <td>占比%</td>
                            <td>---</td>
                            <td>${be.result1}</td>
                            <td>${be.result2}</td>
                            <td>${be.result3}</td>
                            <td>${be.result4}</td>
                            <td>${be.result5}</td>
                            <td>${be.result6}</td>
                            <td>${be.result7}</td>
                            <td>${be.result8}</td>
                            <td>${be.result9}</td>
                            <td>${be.result10}</td>
                            <td>${be.result11}</td>
                            <td>${be.result12}</td>
                            <td>${be.result13}</td>
                            <td>${be.result14}</td>
                            <td>${be.result15}</td>
                            <td>--</td>
                            <td></td>
                            <td></td>
                            </tr>
                    </c:if>

            </c:forEach>

            </table>
        </div>
        </div>
        <script>
        var endTime = new Date(new Date().getTime());
        console.log(endTime);
        var myDate = new Date();
        var mytime = myDate.toLocaleTimeString();     //获取当前时间
        var a = myDate.toLocaleDateString();
        $("#time").text('时间：' + a);
        </script>
        </body>

        </html>