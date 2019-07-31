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
        body .dis-ib {
            margin-right: 15px;
        }

        .wid1 {
            width: 30%
        }

        .wid2 {
            width: 30%
        }

        .wid3 {
            width: 30%
        }

        .wid4 {
            width: 10%
        }

        .wid5 {
            width: 70%
        }

        .btn-cz {
            margin: auto;
            background: #eee;
            border: 1px solid #ddd;
            padding: 0 5px;
            height: 20px;
            line-height: 20px;
            border-radius: 5px;
            margin-left: 15px
        }

        .btn-cz:hover {
            border: 1px solid #06c;
            color: #06c;
            text-decoration: none;
        }

        body .check-box label {
            margin-left: 5px;
        }

        .one_danger .list_odan {
            margin-left: 20px
        }

        .two_danger .list_twdan {
            margin-left: 45px;
        }

        .one_danger .Hui-iconfont {
            margin-top: 2px
        }

        .one_danger, .list_danger {
            display: inline-block
        }

        .one_danger, .one_danger .list_odan, .two_danger, .two_danger .list_twdan, .list_danger {
            float: none;
        }

        .check-box {
            width: 30%;
            margin-bottom: 15px;
        }

        .btn-group .btn {
            height: 34px;
            line-height: 34px;
            padding: 0 25px;
        }

        .one-danrisk {
            float: left;
            width: 100%;
            border-bottom: 1px solid #ddd;
            height: auto;
        }

        .one-danrisk p {
            float: left;
            width: 80%;
            padding: 8px;
            height: auto;
        }

        .table tbody tr td .one-danrisk a {
            float: right;
            margin-top: 8px;
            margin-right: 8px;
        }
        <%--.hideDiv{--%>
        <%--display:none;--%>
        <%--}--%>
    </style>
    <script>
        $(function(){
            $('#partNamme').val('${ids}');
            $('.isShow').css('display','inline-block')
            if('${buttons}'==2){
                $('.hid').css('display','none')
            }else{
                $('.hid').css('display','inline-block')
            }
        })
    </script>
    <script type="text/javascript">

        /* 弹窗工具添加 */
        function addgj(depid) {
            show_dialog(" ", "/company/safety-system/risk-list-load?depId=" + depid);
        }

        /* 弹窗工具添加 */
        function addgjs(depid) {
            show_dialog(" ", "/company/safety-system/risk-list-loads?depId=" + depid);
        }


        function redit(id, type) {
            console.log(id);
            var titles;
            titles = ['', "职业危害物理因素选择", '职业危害化学因素选择', '职业危害高危工艺选择', '物料辨识选择', '高危作业选择'];
            show_dialog(titles[type], '/company/safety-system/set-choose?id=' + id + "&type=" + type);
        }

        function show_ys(type) {
            var t = ['确定职业危害物理因素', '确定职业危害化学因素', '高危工艺', '物料辨识', '高危作业'];
            var index = layer.open({
                type: 2,
                title: t[type - 1],
                content: "/company/safety-system/risk-list-load2?type=" + type,
                scrollbar: false,
                end: function () {
                    // 右上角关闭回调
                    location.reload();
                }
            });
            layer.full(index);
        }
    </script>
    <script type="text/javascript">
        function pr_() {

            $('.isShow').css('display','none')
            $('.isHide').css('display','none')
            $("#div_container").jqprint();
            setTimeout(function () {
                console.log('执行1')
                $('.isShow').css('display','inline-block')
                $('.isHide').css('display','')
            },2000)
        }
    </script>
    <script src="/js/jquery.jqprint-0.3.js"></script>
</head>
<body>
<nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
    <span class="c-gray en">&gt;</span> <span>系统设置</span>
    <span class="c-gray en">&gt;</span> <span>风险辨识</span>
    <%--<a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);"--%>
    <%--title="返回" onclick="parent.close_tab(function(){})">返回</a>--%>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
    <c:if test="${number != 3}">
        <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:history.back(-1)" title="返回">返回</a>
    </c:if>
</nav>
<div class="page-container">
    <%--<div id="spTab" class="btn-group" style="text-align: center;margin-bottom: 20px;">--%>
    <%--<a class="btn default ${empty type ? 'btn-primary' : 'radius'}" href="${ly }/company/safety-system/risk-list">安全风险辨识</a>--%>
    <%--<a class="btn default ${1 eq type ? 'btn-primary' : 'radius'}"--%>
    <%--href="${ly }/company/safety-system/risk-list?type=1">职业病风险物理因素辨识</a>--%>
    <%--<a class="btn default ${2 eq  type ? 'btn-primary' : 'radius'}"--%>
    <%--href="${ly }/company/safety-system/risk-list?type=2">职业病风险化学因素辨识</a>--%>
    <%--<c:if test="${indus==1 }">--%>
    <%--<c:choose>--%>
    <%--<c:when test="${fn:contains(company.name, '油') }">--%>
    <%--</c:when>--%>
    <%--<c:otherwise>--%>
    <%--<a class="btn default ${3 eq  type ? 'btn-primary' : 'radius'}"--%>
    <%--href="${ly }/company/safety-system/risk-list?type=3">高危工艺辨识</a>--%>
    <%--</c:otherwise>--%>
    <%--</c:choose>--%>
    <%--<a class="btn default ${4 eq  type ? 'btn-primary' : 'radius'}"--%>
    <%--href="${ly }/company/safety-system/risk-list?type=4">物料风险辨识</a>--%>
    <%--</c:if>--%>
    <%--<a class="btn default ${5 eq  type ? 'btn-primary' : 'radius'}"--%>
    <%--href="${ly }/company/safety-system/risk-list?type=5">高危作业辨识</a>--%>
    <%--</div>--%>

    <div class="text-c mt-20">
        <button onClick="pr_()" class="btn btn-primary radius" type="button">
            <i class="Hui-iconfont">&#xe652;</i>打印
        </button>
    </div>
    <div class="page-container" id="div_container">
        <div class="div-print">

            <c:if test="${empty type}">
                <c:if test="${buttons == 1}">
                    <h3 class="text-c">${company.name}风险因素辨识</h3>
                </c:if>
                <c:if test="${buttons == 2}">
                    <h3 class="text-c">${company.name}风险因素辨识表</h3>
                </c:if>
                <c:if test="${buttons == 3}">
                    <c:if test="${count1==0}">
                        <h3 class="text-c">${company.name}${parName}风险因素辨识</h3>
                    </c:if>
                    <c:if test="${count1!=0}">
                        <h3 class="text-c">${company.name}${parName}风险因素辨识表</h3>
                    </c:if>
                </c:if>
            </c:if>

            <c:if test="${type eq 1}">
                <h3 class="text-c">${company.name}${parName}职业病风险物理因素辨识</h3>
            </c:if>
            <c:if test="${type eq 2}">
                <h3 class="text-c">${company.name}${parName}职业病风险化学因素辨识</h3>
            </c:if>
            <c:if test="${type eq 3}">
                <h3 class="text-c">${company.name}${parName}高危工艺辨识</h3>
            </c:if>
            <c:if test="${type eq 4}">
                <h3 class="text-c">${company.name}${parName}物料风险辨识</h3>
            </c:if>
            <c:if test="${type eq 5}">
                <h3 class="text-c">${company.name}${parName}高危作业辨识</h3>
            </c:if>


            <c:if test="${empty type}" >
                <c:if test="${number == 3}" >
                   <%-- <span class="r">共有安全风险因素：<strong>${fn:length(zzjgDep) }</strong> 条</span>--%>
                    <span class="r">共有安全风险因素：<strong>${nowCount}</strong> 条</span>
                </c:if>
                <c:if test="${number != 3}" >
                    <span class="r">共有安全风险因素：<strong>${fn:length(list) }</strong> 条</span>
                </c:if>
            </c:if>
            <c:if test="${not empty type}" >
                <span class="r">共有安全风险因素：<strong>${fn:length(zzjg) }</strong> 条</span>
            </c:if>


            <c:if test="${empty type}">

                <c:if test="${number == 3}">
                    <%--<button class="btn btn-primary radius isShow" onClick="BaseSet()">部门、对象确定</button>--%>
                    <button class="btn btn-primary radius isShow" onClick="deleteAll()">部门、对象确定</button>

                    <%-- <button class="btn btn-primary radius isShow" onClick="deleteAll()">编辑</button>

                     <button class="btn btn-primary radius isShow" onClick="saveAll()">确定</button>--%>
                </c:if>

                <c:if test="${number != 3}">

                    <button class="btn btn-primary radius isShow" onClick="selectNow()">现场管理</button>

                    <button class="btn btn-primary radius isShow" onClick="selectBase()">基础管理</button>

                </c:if>


                <%-- <c:if test="${number == null || number != 3}">
                     <select class="sel_area isShow" id="partNamme"  onchange="Partname()" style="position:relative;top:3px">
                         <option value="" >全部</option>
                         <c:forEach items="${zzjgDep1 }" var="be">
                             <option value="${be.id}" >${be.name }</option>
                         </c:forEach>
                     </select>
                 </c:if>--%>
            </c:if>

            <%--<c:if test="${not empty type}">
                &lt;%&ndash;<a class="btn btn-primary radius isShow" href="${ly }/company/safety-system/risk-list?type=${type}&flag=1">辨识</a>&ndash;%&gt;

                &lt;%&ndash;<a class="btn btn-primary radius isShow" href="${ly }/company/safety-system/risk-list?type=${type}&flag=2">确定</a>&ndash;%&gt;
               &lt;%&ndash; <c:if test="${number == null || number != 3}">
                    <select class="sel_area isShow" id="partNamme"  onChange="Partnames(this)" style="position:relative;top:3px">
                        <option value="" >全部</option>
                        <c:forEach items="${zzjgDep1 }" var="be">
                            <option value="${be.id}" >${be.name }</option>
                        </c:forEach>
                    </select>
                </c:if>&ndash;%&gt;
            </c:if>--%>
            <div class="mt-20">
                <%--如果不是设置--%>
                <c:if test="${number == null || number != 3}">
                    <table class="table table-border table-bordered table-bg table-hover table-sort tab-ndan">
                        <thead>
                        <tr class="text-c">
                            <c:if test="${empty type}">
                                <th style="width:8%">辨识类型</th>
                                <th style="width:8%">车间/场所</th>
                                <th style="width:8%">系统</th>
                                <th style="width:8%">环节/部位</th>
                                <th style="width:8%">事故类型</th>
                                <th style="width:50%">风险因素</th>
                                <%--<th class="isHide" style="width:10%">操作</th>--%>
                            </c:if>
                                <%--<c:if test="${flags == 2}">--%>
                                <%--<c:if test="${!empty type}">--%>
                                <%--<th style="padding:0;width:20%">车间/场所</th>--%>
                                <%--<th style="width:10%">岗位</th>--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${type eq 1}">--%>
                                <%--<th>职业危害物理因素</th>--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${type eq 2}">--%>
                                <%--<th>职业危害化学因素</th>--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${type eq 3}">--%>
                                <%--<th>高危工艺</th>--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${type eq 4}">--%>
                                <%--<th>物料辨识</th>--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${type eq 5}">--%>
                                <%--<th>高危作业</th>--%>
                                <%--</c:if>--%>

                                <%--</c:if>--%>

                                <%-- <c:if test="${flags == 1}">--%>
                            <c:if test="${!empty type}">
                                <th style="padding:0;width:20%">车间/场所</th>
                                <th style="width:10%">岗位</th>
                            </c:if>
                            <c:if test="${type eq 1}">
                                <th>职业危害物理因素</th>
                            </c:if>
                            <c:if test="${type eq 2}">
                                <th>职业危害化学因素</th>
                            </c:if>
                            <c:if test="${type eq 3}">
                                <th>高危工艺</th>
                            </c:if>
                            <c:if test="${type eq 4}">
                                <th>物料辨识</th>
                            </c:if>
                            <c:if test="${type eq 5}">
                                <th>高危作业</th>
                            </c:if>
                            <th class="isHide" style="width:10%">操作</th>
                                <%-- </c:if>--%>

                        </tr>
                        </thead>
                        <tbody style="display: none" id="myTable">
                        <c:if test="${empty type}">
                            <c:forEach items="${zzjgDep }" var="be">
                                <c:set value="0" var="x"/>
                                <c:forEach items="${acL }" var="ac">
                                    <c:if test="${be.name eq ac.level1}">
                                        <script>
                                            str = "${ac.level3}";
                                            arr = str.split("/");
                                        </script>
                                        <tr>
                                            <td style="width:10%;height:80px;" class="text-c">
                                                <script>
                                                    document.write(arr[0]);
                                                </script>
                                            </td>
                                            <td style="width:10%;" class="text-c">${be.name }</td>
                                            <c:if test="${x==0}">
                                            <td style="width:10%;" class="text-c"></c:if>
                                                <c:if test="${x==1}">
                                            <td style="width:10%;" class="text-c"></c:if>
                                                <p style="text-align:center;">
                                                    <script>document.write(arr[1]);</script>
                                                </p>
                                            </td>
                                            <c:if test="${x==0}">
                                                <td>${ac.level2}</td>
                                            </c:if>


                                            <c:if test="${x==1}">

                                                <td>${ac.level2}</td>
                                            </c:if>


                                            <c:if test="${x==0}">
                                            <td style="width:10%;" class="text-c">

                                                </c:if>
                                                <c:if test="${x==1}">
                                            <td style="width:10%;" class="text-c">

                                                </c:if>
                                                <c:if test="${empty  ac.type}">
                                                    <p style="text-align:center;">未识别</p>
                                                </c:if>
                                                <c:if test="${not empty  ac.type}">
                                                    <p style="text-align:center;cursor:pointer" onClick="editDanger('${ac.id}')">${ac.type}</p>
                                                </c:if>
                                            </td>
                                            <c:if test="${x==0}">
                                            <td style="width:30%;" class="text-c"></c:if>
                                                <c:if test="${x==1}">
                                            <td style="width:30%;" class="text-c"></c:if>
                                                <p style="float:left;width:80%;">${ac.factors}</p>
                                                <button class="btn btn-success radius isShow" onClick="del('${ac.id}')">删除
                                                </button>
                                            </td>
                                            <td class="text-c">
                                                <c:if test="${empty type}">
                                                    <c:if test="${number == null || number == 1}">
                                                        <button class="btn btn-primary radius isShow"
                                                                onClick="addgj('${be.id}')">现场风险辨识
                                                        </button>
                                                    </c:if>
                                                    <c:if test="${number == 2}">
                                                        <button class="btn btn-primary radius isShow"
                                                                onClick="addgjs('${be.id}')">基础风险辨识
                                                        </button>
                                                    </c:if>
                                                </c:if>
                                            </td>
                                        </tr>
                                        <c:set value="1" var="x"/>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${x == 0}">
                                    <tr>
                                        <td style="width:10%;height:80px;"><p style="text-align:center;">未辨识</p>
                                            <p style="display:none">${be.id}</p></td>
                                        <td style="width:10%;"><p style="text-align:center;">${be.name }</p></td>
                                        <td style="width:10%;"><p style="text-align:center;">未关联</p></td>
                                        <td style="width:10%;"><p style="text-align:center;">未关联</p></td>
                                        <td style="width:10%;"><p style="text-align:center;">未识别</p></td>
                                        <td style="width:40%;"><p style="text-align:center;">未辨识风险因素</p></td>
                                        <td style="width:10%;">
                                            <c:if test="${empty type}">
                                                <c:if test="${number == null || number == 1}">
                                                    <button class="btn btn-primary radius isShow" onClick="addgj('${be.id}')">
                                                        现场风险辨识
                                                    </button>
                                                </c:if>
                                                <c:if test="${number == 2}">
                                                    <button class="btn btn-primary radius isShow" onClick="addgjs('${be.id}')">
                                                        基础风险辨识
                                                    </button>
                                                </c:if>

                                            </c:if>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>

                        </c:if>
                        <c:if test="${not empty type }">

                            <%--   <c:if test="${flags == 1}">--%>
                            <c:if test="${fn:length(zzjg) != 0}" >
                                <c:forEach items="${zzjg }" var="be3">
                                    <tr>
                                        <td class="text-c" rowspan="1">${be3.parName != null ? be3.parName : "暂无数据" }</td>
                                        <td class="text-c">${be3.name != null ? be3.name : "暂无数据" }</td>
                                        <td class="text-c">${fn:replace(be3.danger_point, '!@#', ",") }</td>
                                        <td class="text-c" style="width:90px">
                                            <a class="btn-cz" onclick="redit(${be3.id}, ${type })" href="javascript:;"
                                               title="选择">选择</a>
                                        </td>
                                            <%-- <td class="text-c">${be3.danger_point != null ? be3.danger_point : "暂无风险因素" }</td>--%>
                                            <%-- <td class="text-c">${be4.danger_point != null ? be4.danger_point : "暂无数据" }</td>--%>
                                            <%-- <c:if test="${type eq 1}">
                                                 <td>${fn:replace(be3.hxys, '!@#', ",") }</td>
                                             </c:if>
                                             <c:if test="${type eq 2}">
                                                 <td>${fn:replace(be3.material, '!@#', ",") }</td>
                                             </c:if>
                                             <c:if test="${type eq 3}">
                                                 <td>${fn:replace(be3.gy, '!@#', ",") }</td>
                                             </c:if>
                                             <c:if test="${type eq 4}">
                                                 <td>${fn:replace(be3.wlbs, '!@#', ",") }</td>
                                             </c:if>
                                             <c:if test="${type eq 5}">
                                                 <td>${fn:replace(be3.gwzy, '!@#', ",") }</td>
                                             </c:if>--%>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <c:if test="${fn:length(zzjg) == 0}" >
                                <tr>
                                    <td class="text-c" rowspan="4" colspan="4" >暂无车间职位信息</td>
                                </tr>
                            </c:if>


                            <%--</c:if>--%>

                            <%-- <c:if test="${flags == 2}">
                                 <c:forEach items="${zzjg }" var="be3">

                                     <tr>
                                         <td class="text-c" rowspan="1">${be3.parName != null ? be3.parName : "暂无数据" }</td>
                                         <td class="text-c">${be3.name != null ? be3.name : "暂无数据" }</td>
                                         <td class="text-c">${fn:replace(be3.danger_point, '!@#', ",") }</td>
                                         &lt;%&ndash;<td class="text-c" style="width:90px">
                                             <a class="btn-cz" onclick="redit(${be3.id}, ${type })" href="javascript:;"
                                                title="选择">选择</a>
                                         </td>&ndash;%&gt;
                                             &lt;%&ndash; <td class="text-c">${be3.danger_point != null ? be3.danger_point : "暂无风险因素" }</td>&ndash;%&gt;
                                             &lt;%&ndash; <td class="text-c">${be4.danger_point != null ? be4.danger_point : "暂无数据" }</td>&ndash;%&gt;
                                             &lt;%&ndash; <c:if test="${type eq 1}">
                                                  <td>${fn:replace(be3.hxys, '!@#', ",") }</td>
                                              </c:if>
                                              <c:if test="${type eq 2}">
                                                  <td>${fn:replace(be3.material, '!@#', ",") }</td>
                                              </c:if>
                                              <c:if test="${type eq 3}">
                                                  <td>${fn:replace(be3.gy, '!@#', ",") }</td>
                                              </c:if>
                                              <c:if test="${type eq 4}">
                                                  <td>${fn:replace(be3.wlbs, '!@#', ",") }</td>
                                              </c:if>
                                              <c:if test="${type eq 5}">
                                                  <td>${fn:replace(be3.gwzy, '!@#', ",") }</td>
                                              </c:if>&ndash;%&gt;
                                     </tr>

                                 </c:forEach>
                             </c:if>--%>

                        </c:if>
                        </tbody>
                    </table>
                </c:if>
                <%-- 如果是设置--%>
                <c:if test="${number == 3}">
                    <table class="table table-border table-bordered table-bg table-hover table-sort tab-ndan"
                           style="width:100%">
                        <thead>
                        <tr class="text-c">
                            <th>车间/场所</th>
                                <%--<th>现场</th>--%>
                                <%--<th>基础</th>--%>
                            <th>安全风险</th>
                            <th>职业病物理因素</th>
                            <th>职业病化学因素</th>
                            <c:if test="${indus==1 }">
                                <c:choose>
                                    <c:when test="${fn:contains(company.name, '油') }">
                                    </c:when>
                                    <c:otherwise>
                                        <th>高危工艺</th>
                                    </c:otherwise>
                                </c:choose>
                                <th>物料</th>
                            </c:if>

                            <th>高危作业</th>
                                <%--<th >操作</th>--%>
                        </tr>

                        </thead>
                        <tbody>
                            <c:if test="${fn:length(zzjgDep) != 0}" >
                                <c:forEach items="${zzjgDep }" var="be">
                                    <tr>
                                        <td><p style="text-align:center;">${be.name }</p></td>
                                        <c:set value="${fn:split(be.dangerId,',')}" var="idx">

                                        </c:set>
                                            <%--<td style="text-align: center">--%>

                                            <%--<c:if test="${idx[0]==1||idx[1]==1||idx[2]==1||idx[3]==1||idx[4]==1||idx[5]==1||idx[6]==1||idx[7]==1||idx[8]==1}">--%>
                                            <%--<a onclick="amend('${be.id}',1)">查看</a>--%>
                                            <%--</c:if>--%>
                                            <%--<c:if test="${idx[0]!=1&&idx[1]!=1&&idx[2]!=1&&idx[3]!=1&&idx[4]!=1&&idx[5]!=1&&idx[6]!=1&&idx[7]!=1&&idx[8]!=1}">--%>
                                            <%--<input type="checkbox" name="xc" class="hid" onchange="checkShowType(this,1,'${be.id}')" style="margin-right: 3px"/>--%>
                                            <%--</c:if>--%>
                                            <%--<c:if test="${be.dangerId==null||be.dangerId!=1}">--%>
                                            <%--<input type="checkbox" name="xc" onchange="checkShowType(this,1,'${be.id}')" style="margin-right: 3px"/>${be.dangerId}--%>
                                            <%--</c:if>--%>
                                            <%--<c:if test="${be.dangerId==null||be.dangerId==4}">--%>
                                            <%--<input type="checkbox" name="xc"--%>
                                            <%--onchange="checkShowType(this,'${be.id}')" style="margin-right: 3px"/>现场--%>
                                            <%--<input type="checkbox" name="jc"--%>
                                            <%--onchange="checkShowType(this,'${be.id}')"--%>
                                            <%--style="margin-left: 20px;margin-right: 3px"/>基础--%>
                                            <%--</c:if>--%>
                                            <%--<c:if test="${be.dangerId==1}">--%>
                                            <%--<input type="checkbox" checked name="xc"--%>
                                            <%--onchange="checkShowType(this,'${be.id}')" style="margin-right: 3px"/>现场--%>
                                            <%--<input type="checkbox" name="jc"--%>
                                            <%--onchange="checkShowType(this,'${be.id}')"--%>
                                            <%--style="margin-left: 20px;margin-right: 3px"/>基础--%>
                                            <%--</c:if>--%>
                                            <%--<c:if test="${be.dangerId==2}">--%>
                                            <%--<input type="checkbox" name="xc"--%>
                                            <%--onchange="checkShowType(this,'${be.id}')" style="margin-right: 3px"/>现场--%>
                                            <%--<input type="checkbox" checked name="jc"--%>
                                            <%--onchange="checkShowType(this,'${be.id}')"--%>
                                            <%--style="margin-left: 20px;margin-right: 3px"/>基础--%>
                                            <%--</c:if>--%>
                                            <%--<c:if test="${be.dangerId==3}">--%>
                                            <%--<input type="checkbox" checked name="xc"--%>
                                            <%--onchange="checkShowType(this,'${be.id}')" style="margin-right: 3px"/>现场--%>
                                            <%--<input type="checkbox" checked name="jc"--%>
                                            <%--onchange="checkShowType(this,'${be.id}')"--%>
                                            <%--style="margin-left: 20px;margin-right: 3px"/>基础--%>
                                            <%--</c:if>--%>

                                            <%--</td>--%>
                                            <%--<td style="text-align: center">--%>

                                            <%--<c:if test="${idx[0]==2||idx[1]==2||idx[2]==2||idx[3]==2||idx[4]==2||idx[5]==2||idx[6]==2||idx[7]==2||idx[8]==2}">--%>
                                            <%--&lt;%&ndash;<input type="checkbox" name="xc" checked onchange="checkShowType(this,2,'${be.id}')" style="margin-right: 3px"/>&ndash;%&gt;--%>
                                            <%--<a onclick="amend('${be.id}',2)">查看</a>--%>
                                            <%--</c:if>--%>
                                            <%--<c:if test="${idx[0]!=2&&idx[1]!=2&&idx[2]!=2&&idx[3]!=2&&idx[4]!=2&&idx[5]!=2&&idx[6]!=2&&idx[7]!=2&&idx[8]!=2}">--%>
                                            <%--<input type="checkbox" name="xc" class="hid" onchange="checkShowType(this,2,'${be.id}')" style="margin-right: 3px"/>--%>
                                            <%--</c:if>--%>
                                            <%--</td>--%>
                                        <td style="text-align: center">
                                                <%--<c:if test="${idx[0]==3||idx[1]==3||idx[2]==3||idx[3]==3||idx[4]==3||idx[5]==3||idx[6]==3||idx[7]==3||idx[8]==3}">--%>
                                                <%--<a onclick="amend('${be.id}',3)">查看</a>--%>
                                                <%--</c:if>--%>

                                            <c:if test="${idx[0]==3||idx[1]==3||idx[2]==3||idx[3]==3||idx[4]==3||idx[5]==3||idx[6]==3||idx[7]==3||idx[8]==3}">
                                                <c:if test="${buttons==1}">
                                                    <input type="checkbox" name="xc" checked onchange="checkShowType(this,3,'${be.id}')" style="margin-right: 3px"/>
                                                </c:if>
                                                <c:if test="${buttons==2}">
                                                    <c:if test="${be.count == 0}">
                                                        <a onclick="amend('${be.id}',3,${be.count1})">辨识</a>
                                                    </c:if>

                                                    <c:if test="${be.count != 0}">
                                                        <a onclick="amend('${be.id}',3,${be.count1})">查看</a>
                                                    </c:if>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${idx[0]!=3&&idx[1]!=3&&idx[2]!=3&&idx[3]!=3&&idx[4]!=3&&idx[5]!=3&&idx[6]!=3&&idx[7]!=3&&idx[8]!=3}">
                                                <input type="checkbox" name="xc${be.id}" class="hid" onchange="checkShowType(this,3,'${be.id}')" style="margin-right: 3px"/>
                                            </c:if>
                                        </td>
                                        <td style="text-align: center">
                                            <c:if test="${idx[0]==4||idx[1]==4||idx[2]==4||idx[3]==4||idx[4]==4||idx[5]==4||idx[6]==4||idx[7]==4||idx[8]==4}">
                                                <c:if test="${buttons==1}">
                                                    <input type="checkbox" name="xc" checked onchange="checkShowType(this,4,'${be.id}')" style="margin-right: 3px"/>
                                                </c:if>
                                                <c:if test="${buttons==2}">
                                                    <c:if test="${be.count1 == 0}" >
                                                        <a onclick="amend('${be.id}',4,${be.count1})">辨识</a>
                                                    </c:if>
                                                    <c:if test="${be.count1 != 0}" >
                                                        <a onclick="amend('${be.id}',4,${be.count1})">查看</a>
                                                    </c:if>

                                                </c:if>
                                            </c:if>
                                            <c:if test="${idx[0]!=4&&idx[1]!=4&&idx[2]!=4&&idx[3]!=4&&idx[4]!=4&&idx[5]!=4&&idx[6]!=4&&idx[7]!=4&&idx[8]!=4}">
                                                <input type="checkbox" name="xc${be.id}" class="hid" onchange="checkShowType(this,4,'${be.id}')" style="margin-right: 3px"/>
                                            </c:if>
                                        </td>
                                        <td style="text-align: center">
                                            <c:if test="${idx[0]==5||idx[1]==5||idx[2]==5||idx[3]==5||idx[4]==5||idx[5]==5||idx[6]==5||idx[7]==5||idx[8]==5}">
                                                <c:if test="${buttons==1}">
                                                    <input type="checkbox" name="xc" checked onchange="checkShowType(this,5,'${be.id}')" style="margin-right: 3px"/>
                                                </c:if>
                                                <c:if test="${buttons==2}">
                                                    <c:if test="${be.count2 == 0}">
                                                        <a onclick="amend('${be.id}',5,${be.count1})">辨识</a>
                                                    </c:if>
                                                    <c:if test="${be.count2 != 0}">
                                                        <a onclick="amend('${be.id}',5,${be.count1})">查看</a>
                                                    </c:if>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${idx[0]!=5&&idx[1]!=5&&idx[2]!=5&&idx[3]!=5&&idx[4]!=5&&idx[5]!=5&&idx[6]!=5&&idx[7]!=5&&idx[8]!=5}">
                                                <input type="checkbox" name="xc${be.id}" class="hid" onchange="checkShowType(this,5,'${be.id}')" style="margin-right: 3px"/>
                                            </c:if>
                                        </td>
                                        <c:if test="${indus==1 }">
                                            <c:choose>
                                                <c:when test="${fn:contains(company.name, '油') }">
                                                </c:when>
                                                <c:otherwise>
                                                    <td style="text-align: center">
                                                        <c:if test="${idx[0]==6||idx[1]==6||idx[2]==6||idx[3]==6||idx[4]==6||idx[5]==6||idx[6]==6||idx[7]==6||idx[8]==6}">
                                                            <c:if test="${buttons==1}">
                                                                <input type="checkbox" name="xc" checked onchange="checkShowType(this,6,'${be.id}')" style="margin-right: 3px"/>
                                                            </c:if>
                                                            <c:if test="${buttons==2}">
                                                                <c:if test="${be.count3 == 0}">
                                                                    <a onclick="amend('${be.id}',6,${be.count1})">辨识</a>
                                                                </c:if>
                                                                <c:if test="${be.count3 != 0}">
                                                                    <a onclick="amend('${be.id}',6,${be.count1})">查看</a>
                                                                </c:if>
                                                            </c:if>
                                                        </c:if>
                                                        <c:if test="${idx[0]!=6&&idx[1]!=6&&idx[2]!=6&&idx[3]!=6&&idx[4]!=6&&idx[5]!=6&&idx[6]!=6&&idx[7]!=6&&idx[8]!=6}">
                                                            <input type="checkbox" name="xc${be.id}" class="hid" onchange="checkShowType(this,6,'${be.id}')" style="margin-right: 3px"/>
                                                        </c:if>
                                                    </td>
                                                </c:otherwise>
                                            </c:choose>
                                            <td style="text-align: center">
                                                <c:if test="${idx[0]==7||idx[1]==7||idx[2]==7||idx[3]==7||idx[4]==7||idx[5]==7||idx[6]==7||idx[7]==7||idx[8]==7}">
                                                    <c:if test="${buttons==1}">
                                                        <input type="checkbox" name="xc" checked onchange="checkShowType(this,7,'${be.id}')" style="margin-right: 3px"/>
                                                    </c:if>
                                                    <c:if test="${buttons==2}">
                                                        <c:if test="${be.count4 == 0}">
                                                            <a onclick="amend('${be.id}',7,${be.count1})">辨识</a>
                                                        </c:if>
                                                        <c:if test="${be.count4 != 0}">
                                                            <a onclick="amend('${be.id}',7,${be.count1})">查看</a>
                                                        </c:if>
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${idx[0]!=7&&idx[1]!=7&&idx[2]!=7&&idx[3]!=7&&idx[4]!=7&&idx[5]!=7&&idx[6]!=7&&idx[7]!=7&&idx[8]!=7}">
                                                    <input type="checkbox" name="xc${be.id}" class="hid" onchange="checkShowType(this,7,'${be.id}')" style="margin-right: 3px"/>
                                                </c:if>
                                            </td>
                                        </c:if>
                                        <td style="text-align: center">
                                            <c:if test="${idx[0]==8||idx[1]==8||idx[2]==8||idx[3]==8||idx[4]==8||idx[5]==8||idx[6]==8||idx[7]==8||idx[8]==8}">
                                                <c:if test="${buttons==1}">
                                                    <input type="checkbox" name="xc" checked onchange="checkShowType(this,8,'${be.id}')" style="margin-right: 3px"/>
                                                </c:if>
                                                <c:if test="${buttons==2}">
                                                    <c:if test="${be.count5 == 0}">
                                                        <a onclick="amend('${be.id}',8,${be.count1})">辨识</a>
                                                    </c:if>
                                                    <c:if test="${be.count5 != 0}">
                                                        <a onclick="amend('${be.id}',8,${be.count1})">查看</a>
                                                    </c:if>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${idx[0]!=8&&idx[1]!=8&&idx[2]!=8&&idx[3]!=8&&idx[4]!=8&&idx[5]!=8&&idx[6]!=8&&idx[7]!=8&&idx[8]!=8}">
                                                <input type="checkbox" name="xc${be.id}" class="hid" onchange="checkShowType(this,8,'${be.id}')" style="margin-right: 3px"/>
                                            </c:if>
                                        </td>

                                            <%--<td rowspan="${fn:length(zzjgDep) }"><button>修改</button></td>--%>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <c:if test="${fn:length(zzjgDep) == 0}" >
                                <tr>
                                    <td class="text-c" rowspan="7" colspan="7" >该公司暂无车间信息</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                    <c:if test="${buttons==1}">
                        <div  style="padding-left: 745px;padding-top: 10px" >
                            <button class="btn btn-primary radius" onClick="saveAll()">确定</button>
                        </div>
                    </c:if>
                </c:if>
            </div>
        </div>

        <div id="win-add2" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" style="width: 760px">
                <div class="modal-content radius">
                    <div class="modal-header">
                        <h3 class="modal-title">编辑</h3>
                        <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
                    </div>
                    <div class="modal-body">
                        <div class="row cl">
                            <label class="form-label col-xs-4 col-sm-2"
                                   style="width: 20%; text-align: right;">系统/车间：</label>
                            <div class="formControls col-xs-8 col-sm-9" style="width: 80%;">
                                <input type="text" id="level1" value="" style="width: 557px"
                                       class="input-text required">
                            </div>
                        </div>
                        <div class="row cl mt-15">
                            <label class="form-label col-xs-4 col-sm-2"
                                   style="width: 20%; text-align: right;">工段/班组：</label>
                            <div class="formControls col-xs-8 col-sm-9" style="width: 80%;">
                                <input type="text" id="level2" value="" style="width: 557px"
                                       class="input-text required">
                            </div>
                        </div>
                        <div class="row cl mt-15">
                            <label class="form-label col-xs-4 col-sm-2"
                                   style="width: 20%; text-align: right;">岗位/部位：</label>
                            <div class="formControls col-xs-8 col-sm-9" style="width: 80%;">
                                <input type="text" id="level3" value="" style="width: 557px"
                                       class="input-text required">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary" onclick="save()">确定</button>
                        <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
                    </div>
                </div>
            </div>
        </div>


    </div>
</div>
</body>
<script type="text/javascript" src="/js/fxgk/tbl-rowspan-reset.js"></script>
<script type="text/javascript">
    //alert("${point}");


    var id = '';

    var my_id =0;

    function edit(id, obj) {
        var p = $(obj).parent();
        window.id = id;
        $("#level1").val(p.find("textarea[name='level1']").val());
        $("#level2").val(p.find("textarea[name='level2']").val());
        $("#level3").val(p.find("textarea[name='level3']").val());
        $("#win-add2").modal("show");
    }

    function reload_() {
        layer.closeAll();
        location.reload();
    }

    function save() {
        var obj = {
            "level3": $("#level3").val(),
            "level1": $("#level1").val(),
            "level2": $("#level2").val(),
            "id": id
        }
        if (obj.level1 == '') {
            $("#level1").focus();
            return false;
        }
        if (obj.level2 == '') {
            $("#level2").focus();
            return false;
        }
        if (obj.level3 == '') {
            $("#level3").focus();
            return false;
        }
        if (obj.id == '') {
            obj.issys = 1;
        } else {
            obj.issys = 0;
        }
        var i = layer.load();
        $.post(getRootPath() + "/company/safety-system/aCompanyManual-save", obj, function (result) {
            layer.close(i);
            location.reload();
        })
    }

    function del(id) {
        layer.confirm("确定删除该记录?", function (r) {
            $.post(getRootPath() + "/company/safety-system/aCompanyManual-save", {
                id: id,
                del: 1
            }, function (result) {
                layer.close(r);
                location.reload();
            })
        })
    }
    /*function amend(id,idx){
        $.post(getRootPath() + "/company/safety-system/risk-list", {
            id: id,
            types: idx

        }, function (result) {
            location.reload();
        })
    }*/
    function deleteAll() {

        window.location.href = '${ly}/company/safety-system/risk-list?buttons=1'
    }

    function saveAll() {

        window.location.href = '${ly}/company/safety-system/risk-list?buttons=2'
    }


    function amend(id,idx,c) {
        sessionStorage.setItem('jcId',id);
        window.location.href = '${ly}/company/safety-system/risk-list?id='+id+"&types="+idx+"&buttons=3"+"&count1="+c
    }



    function selectNow() {
        <%--window.location.href = '${ly}/company/safety-system/risk-list?number=1'--%>
        window.location.href = '${ly}/company/safety-system/risk-list?types=3&id='+sessionStorage.getItem('jcId')
    }

    function selectBase() {
        <%--window.location.href = '${ly}/company/safety-system/risk-list?number=2'--%>
        window.location.href = '${ly}/company/safety-system/risk-list?types=2&id='+sessionStorage.getItem('jcId')
    }

    function BaseSet() {
        window.location.href = '${ly}/company/safety-system/risk-list?number=3'
    }


    function Partname() {
        var vs = $('select  option:selected').val();
        window.location.href = '${ly}/company/safety-system/risk-list?dmid='+vs+"&number=${number}"

    }

    function Partnames(that) {
        var vs = $('select  option:selected').val();
        window.location.href = '${ly}/company/safety-system/risk-list?dmid='+vs+"&type=${type}"

    }

    function checkShowType(that,str, id) {
        var isT = $(that).is(':checked');
        <%--var parent = $(obj).parent();--%>
        <%--var xc = parent.children('input[name="xc"]');  //现场checkbox--%>
        <%--var jc = parent.children('input[name="jc"]');  //基础checkbox--%>
        <%--var xcVal = xc.is(':checked');   //现场checkbox是否选中--%>
        <%--var jcVal = jc.is(':checked');   //基础checkbox是否选中--%>
        <%--postData.id = parseInt(id);--%>
        <%--postData.xc = xcVal ? 1 : 0;--%>
        <%--postData.jc = jcVal ? 1 : 0;--%>
        $.post("${ly}/company/safety-system/risk-set", isP(str,isT,id), function (result) {
            if (result.status == 0) {
                <%--layer.msg('设置成功');--%>
            } else {
                layer.msg('设置失败');
            }

        })
        function isP(v,isT,id){
            var postData = {}
            postData.dpId = id
            postData.flag = isT
            if(v==1){
                postData.number1 = 1
            }else if(v==2){
                postData.number2 = 2
            }else if(v==3){
                postData.number3 = 3
            }else if(v==4){
                postData.number4 = 4
            }else if(v==5){
                postData.number5 = 5
            }else if(v==6){
                postData.number6 = 6
            }else if(v==7){
                postData.number7 = 7
            }else if(v==8){
                postData.number8 = 8
            }
            return postData
        }
    }


    function editDanger(id){
        var idd=parseInt(id);
        show_dialog('编辑','${ly}/company/safety-system/save-danger_type?id='+idd);

    }


</script>
</html>