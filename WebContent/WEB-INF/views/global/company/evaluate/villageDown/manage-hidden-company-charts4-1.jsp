    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%@ include file="/WEB-INF/views/taglibs.jsp"%>
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="utf-8" />
        <title></title>
        <style>
        table {
        border-collapse: collapse;
        width: 100%;
        }

        .title>div {
        margin: 10px 0;
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
        }

        td[class=first] {
        width: 120px;
        }

        td[class=first]:before {
            content: "";
            position: absolute;
            width: 1px;
            height: 150px;
            top: 0;
            left: 0;
            background-color: #ddd;
            display: block;
            transform: rotate(-56deg);
            transform-origin: top;
        }

        .title {
        margin-top: 43px;
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
        }

        .flexcenter {
        display: flex;
        justify-content: center;
        }

        .flexspa {
        display: flex;
        justify-content: space-between;
        }

        .tit {
        background: #F5F9FE;
        font-weight: bold;
        }

        .containerx {
        padding: 0 10px;
        }

        .t {
        background: #F5F9FE;
        font-weight: bold;
        }

        .cx {
        padding: 20px;
        }

        #time {
        font-size: 15px;
        }
        </style>
        </head>

        <body>
        <script>console.log('[{uid=26, utime=2019-07-06 14:51:48.0, number=0.0, pid=null, dangerId=,4,5,6,7,8,,1,2,3, ctime=2019-06-22 15:00:18.0, cid=260, result11=0.0, danger2=0, danger1=0, danger3=0, id=1, result33=0.0, number2=0, number1=0, result22=0.0, level=1, flag=1, name=车间一, del=0, danger33=0, danger22=0, danger11=0}, {uid=26, utime=2019-07-06 14:51:48.0, number=0.0, pid=null, dangerId=1,2,3,4,5,6,7,8, ctime=2019-06-22 15:00:25.0, cid=260, result11=0.0, danger2=0, danger1=0, danger3=0, id=2, result33=0.0, number2=0, number1=0, result22=0.0, level=1, flag=1, name=车间二, del=0, danger33=0, danger22=0, danger11=0}, {uid=26, utime=2019-07-06 14:51:48.0, number=0.00%, pid=null, dangerId=1,2,3,4,5,6,7,8, ctime=2019-06-22 15:00:31.0, cid=260, result11=0.0, danger2=0, danger1=0, danger3=0, id=3, result33=0.0, number2=7, number1=0, result22=0.00%, level=1, flag=1, name=实验室, del=0, danger33=0, danger22=7, danger11=0}, {uid=26, utime=2019-07-06 14:51:48.0, number=0.00%, pid=null, dangerId=1,2,3,4,5,6,7,8, ctime=2019-06-22 15:00:42.0, cid=260, result11=0.0, danger2=0, danger1=0, danger3=0, id=4, result33=0.0, number2=4, number1=0, result22=0.00%, level=1, flag=1, name=仓库, del=0, danger33=0, danger22=4, danger11=0}, {uid=26, utime=2019-07-06 16:27:27.0, number=0.0, pid=null, dangerId=1,2,3,4,5,7,6,8, ctime=2019-07-06 16:27:27.0, cid=260, result11=0.0, danger2=0, danger1=0, danger3=0, id=417, result33=0.0, number2=0, number1=0, result22=0.0, level=1, flag=1, name=车间1, del=0, danger33=0, danger22=0, danger11=0}, {sign3=0, sign2=0, sign1=0, sign11=0, result3=0.00%, proportion11=0.00%, sign22=11, result1=0.00%, result2=0.00%, sign33=0, proportion33=0.00%, proportion22=0.00%, proportion55=1.00%, proportion44=0.00%, proportion66=0.00%}]')</script>
        <nav class="breadcrumb">
        <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
        <span class="c-gray en">&gt;</span> <span>治理数据分析</span>
        <span class="c-gray en">&gt;</span> <span class="zc">隐患治理数据分析 </span>
        <a class="btn btn-success radius r bit_hid" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;"
        href="javascript:history.back(-1)" title="返回">返回</a>
        </nav>
        <div class="cx">
        <div class="text-c mt-20 ">
        <h3 class="center">隐患治理数据分析</h3>
        </div>
        <section class="title">
        <div class="flexspa">
        <span style="visibility: hidden;"></span>
        <span id="time"></span>
        </div>
        </section>
        <table class=".table table-hover" style="margin: auto;" cellpadding="2">
        <tr class="t">
        <td rowspan="2">序号</td>
        <td class="first" rowspan="2"><span class="title1">等级</span><br><span class="title3">企业</span></td>
        <td colspan="4">一般隐患</td>
        <td colspan="4">较大隐患</td>
        <td colspan="4">重大隐患</td>

        <td rowspan="2">排名</td>
        </tr>


        <tr class="t">

        <td>已治理</td>
        <td>未治理</td>
        <td>治理率%</td>
        <td>同比%</td>
        <td>已治理</td>
        <td>未治理</td>
        <td>治理率%</td>
        <td>同比%</td>
        <td>已治理</td>
        <td>未治理</td>
        <td>治理率%</td>
        <td>同比%</td>
        </tr>

            <c:forEach items="${list}" varStatus="index" var="be">
                    <c:if test="${index.index<fn:length(list)-1 }">
                            <tr>
                            <td>${index.index + 1}</td>
                            <td>${be.name}</td>
                            <td>${be.danger1}</td>
                            <td>${be.danger11}</td>
                            <td>${be.result11}</td>
                            <td></td>

                            <td>${be.danger2}</td>
                            <td>${be.danger22}</td>
                            <td>${be.result22}</td>
                            <td></td>

                            <td>${be.danger3}</td>
                            <td>${be.danger33}</td>
                            <td>${be.result33}</td>
                            <td></td>


                            <td></td>
                            </tr>
                    </c:if>

                    <c:if test="${index.index>fn:length(list)-2 }">
                            <tr>
                            <td>合计</td>
                            <td>---</td>
                            <td>${be.sign1}</td>
                            <td>${be.sign11}</td>
                            <td>${be.result1}</td>
                            <td></td>

                            <td>${be.sign2}</td>
                            <td>${be.sign22}</td>
                            <td>${be.result2}</td>
                            <td></td>

                            <td>${be.sign3}</td>
                            <td>${be.sign33}</td>
                            <td>${be.result3}</td>
                            <td></td>


                            <td></td>

                            </tr>
                    </c:if>

            </c:forEach>

            </table>
        <script>

        var endTime = new Date(new Date().getTime());
        console.log(endTime);
        var myDate = new Date();
        var mytime = myDate.toLocaleTimeString();     //获取当前时间
        var a = myDate.toLocaleDateString();
        $("#time").text('时间：' + a);
        </script>
        </div>
        </body>

        </html>