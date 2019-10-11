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
        border: 1px solid #ddd;
        }

        td {
        text-align: center;
        height: 38px;
        /*这里需要自己调整，根据自己的需求调整高度*/
        position: relative;
            padding: 2px;
        }

        td[class=first] {
        width: 155px;
        }

        td[class=first]:before {
        content: "";
        position: absolute;
        width: 1px;
        height: 180px;
        top: 0;
        left: 0;
        background-color: #ddd;
        display: block;
        transform: rotate(-62deg);
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
        <h3 class="center">隐患数据分析</h3>
        </div>
        <section class="title">
        <div class="flexspa">
        <span class=""></span>
        <span id="time">日期：2019/8/24
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
        title = "企业自查隐患行业数据分析";
        console.log('title:', title);
        }
        if (targetPageId == 2) {
        title = "行政检查隐患行业数据分析"; console.log('title:', title);
        }
        if (targetPageId == 3) {
        title = "第三方检查隐患行业数据分析"; console.log('title:', title);
        }
        $(".center").text(title);
        $(".aaa").text(title);
        </script>
        </section>
        <table class=".table table-hover" style="margin: auto;width: 100%" cellpadding="2">
        <tr style="background-color: #F5F9FE;font-weight: bold;">
        <td rowspan="2">序号</td>
        <td class="first" rowspan="2"><span class="title1">行业</span><br><span class="title3">企业</span></td>

        <td>化工</td>
        <td>冶金</td>
        <td>有色</td>
        <td>建材</td>
        <td>机械</td>
        <td>轻工</td>
        <td>纺织</td>
        <td>商贸</td>
        <td>烟花</td>
        <td>其他</td>
        <td rowspan="2" class="sum">合计</td>
        <td rowspan="2">排名</td>
        <td rowspan="2">同比</td>
        </tr>
        <tr style="background-color: #F5F9FE;    font-weight: bold;">
        <td>数量</td>
        <td>数量</td>
        <td>数量</td>
        <td>数量</td>
        <td>数量</td>
        <td>数量</td>
        <td>数量</td>
        <td>数量</td>
        <td>数量</td>
        <td>数量</td>
        <script>
        console.log('[{sum10=0, sum11=0, sum12=0, sum13=0, sum14=0, result14=0.00%, result3=0.00%, result4=0.00%, result1=0.00%, result2=0.00%, result9=0.00%, result7=0.00%, result8=0.00%, result5=0.00%, result6=0.00%, result13=0.00%, result12=0.00%, result11=0.00%, result10=0.00%, sum2=0, sum1=0, sum4=0, sum3=0, sum6=0, sum5=0, sum8=0, sum7=0, sum9=0}]');
        window.a = 0;
        </script>
        </tr>
            <c:forEach items="${list}" varStatus="index" var="be">
                    <c:if test="${index.index<fn:length(list)-1 }">
                            <tr>
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
                            <td></td>
                            <td></td>
                            <script>
                            window.a=window.a+'${be.danger11}'*1;
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
                            <td class="tdd"></td>
                            <td></td>
                            <td></td>
                            </tr>
                    </c:if>
                    <script>
                    $(".tdd").text(window.a);
                    </script>

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