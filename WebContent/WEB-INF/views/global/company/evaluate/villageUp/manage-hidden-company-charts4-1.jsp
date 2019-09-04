    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%@ include file="/WEB-INF/views/taglibs.jsp"%>
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="utf-8" />
            <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
        <title></title>
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
        border-collapse: collapse;
        width: 100%;
            font-size: 13px
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
            padding: 3px;
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
        font-weight: 500;
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
            <%--<i class="Hui-iconfont">&#xe67f;</i>--%>
            <nav class="breadcrumb">
            <i class="Hui-iconfont"></i>
            <span>首页</span>
            <span class="c-gray en">&gt;</span> <span>治理数据分析</span>
            <span class="c-gray en">&gt;</span> <span class="aaa">隐患治理地区数据分析</span>
            <a class="btn btn-success radius r bit_hid" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:history.back(-1)" title="返回">返回</a>
            </nav>
        <div class="cx">
        <div class="text-c mt-20 ">
        <h3 class="center">隐患治理地区数据分析</h3>
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
        <td class="first" rowspan="2"><span class="title1">等级</span><br><span class="title3">行业</span></td>
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

        var myDate = new Date();
        var a = myDate.toLocaleDateString();
        $("#time").text('时间：' + a);
        </script>
        </div>
        </body>

        </html>