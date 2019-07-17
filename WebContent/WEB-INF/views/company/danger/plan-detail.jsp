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
    <title>安全分级管控 隐患排查治理智能化信息平台</title>
    <meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
    <meta name="description" content="风险分级管控   隐患排查治理智能化平台">
    <style type="text/css">
        .input-text {
            position: relative
        }

        .txtarea_sq {
            height: 100px;
            width: 350px;
        }

        body .check-box, .radio-box {
            line-height: 30px;
        }

        .div_pdetail {
            width: 100%;
            font-size: 15px;
        }

        .div_pdetail font {
            float: left;
            font-size: 15px;
            margin-left: 15px;
            font-weight: bold;
        }

        .div_pdetail label {
            float: right;
            font-size: 15px;
            margin-right: 15px;
        }

        .div_imgp {
            float: left;
            width: 90%;
            margin-left: 5%;
            text-align: center;
        }

        .div_imgp img {
            max-width: 100%
        }

        .div_pleft {
            width: 150px;
            display: inline-block;
            line-height: 32px;
            font-size: 14px;
        }

        .div_pright {
            width: 300px;
            display: inline-block;
        }

        .div_pri {
            width: 100%;
            min-height: 32px
        }

        .div_pri font {
            float: right;
            line-height: 32px;
            font-size: 14px;
            margin-right: 15px;
        }

        .div_pri .Wdate {
            float: right;
        }

        body .select {
            min-height: 32px;
            line-height: 32px;
        }
    </style>
    <script src="${ly }/js/jquery-migrate-1.2.1.min.js"></script>
    <script src="${ly }/js/jquery.jqprint-0.3.js"></script>
    <script type="text/javascript">
        function showpicture(src) {
            $(".div_imgp img").attr("src", src);
            $("#modal-plan").modal("show")
        }

        function pr_() {
            $("#div_container").jqprint();
        }
    </script>
</head>
<body>
<div class="page-container">
    <div class="div_pdetail">
        <c:choose>
            <c:when test="${check.flag == 1}">
                <button onClick="pr_()" class="btn btn-success radius ml-20" type="button" style="padding: 0 70px;">
                    <i class="Hui-iconfont mr-10">&#xe652;</i>打印预览
                </button>
                <%--          <button onClick="parent.show_dialog('检查表打印', getRootPath()+'/company/plan-next-print?id=${check.id}')" class="btn btn-success radius"--%>
                <%--            type="button" style="padding: 0 60px;margin-right: 20px">--%>
                <%--            <i class="Hui-iconfont mr-10">&#xe652;</i>--%>
                <%--            <!-- 打印 -->--%>
                <%--            打印预览33--%>
                <%--          </button>--%>
            </c:when>
            <c:otherwise>
                <button onClick="pr_()" class="btn btn-success radius ml-20" type="button" style="padding: 0 70px;">
                    <i class="Hui-iconfont mr-10">&#xe652;</i>打印预览
                </button>
                <%--          <button onClick="parent.show_dialog('检查表打印', getRootPath()+'/village/check-document?checkId=${check.id}&flag=8')"--%>
                <%--            class="btn btn-success radius" type="button" style="padding: 0 60px;margin-right: 20px">--%>
                <%--            <i class="Hui-iconfont mr-10">&#xe652;</i>--%>
                <%--            <!-- 打印文书 -->--%>
                <%--            打印预览44--%>
                <%--          </button>--%>
            </c:otherwise>
        </c:choose>
        <%-- <button onClick="parent.show_tab('排查治理记录', '${ly }/village/check-list?flag=${flag }')" class="btn btn-success radius" type="button" style="padding: 0 15px;margin-right: 20px">
          <i class="Hui-iconfont mr-10">&#xe652;</i>返回列表
        </button> --%>
        <!-- 判断是否有治理意见表 -->
        <c:if test="${listM.c > 0 && listM.status == 2 }">
            <!-- 已检查过且有隐患的需要治理意见表 -->
            <!-- <c:choose>
            <c:when test="${listM.t > 0}"> -->
                <!-- 已设置整改意见 -->
                <%--<button onClick="location.href = '/village/check-rectification?flag=${flag}&id=${listM.id}'"
                  class="btn btn-success radius" type="button" style="padding: 0 70px;margin-right: 20px">整改详情</button>--%>
                <!-- </c:when>
        </c:choose> -->

            <c:if test="${number > 0}">
                <button onClick="show_dialog('整改详情','/village/check-rectification?flag=${flag}&id=${listM.id}&number=${number}')"
                        class="btn btn-success radius" type="button" style="padding: 0 70px;margin-right: 20px">整改意见书
                </button>

                <%--                <button onClick="location.href = '/company/recheck-add?checkId=${check.id}'"--%>
                <%--                        class="btn btn-success radius" type="button" style="padding: 0 70px;">实施复查--%>
                <%--                </button>--%>
            </c:if>
            <c:if test="${dangerInteger>0}">
                <button onClick="show_dialog('实施复查','/company/recheck-add?checkId=${check.id}')"
                        class="btn btn-success radius" type="button" style="padding: 0 70px;">实施复查
                </button>
            </c:if>

            <!-- 已经有复查 -->
            <c:if test="${listM.c3 > 0}">
                <button onClick="show_dialog('复查详情','/company/recheck-detail?checkId=${listM.id }&flag=${flag}&number=${number}')"
                        class="btn btn-success radius" type="button" style="padding: 0 70px;">复查详情
                </button>
                <%--                <button onClick="location.href = '/company/recheck-detail?checkId=${listM.id }&flag=${flag}&number=${number}'"--%>
                <%--                        class="btn btn-success radius" type="button" style="padding: 0 70px;">复查详情--%>
                <%--                </button>--%>
            </c:if>


            <%--显示行政检查文书--%>
            <c:if test="${flag==2  }">

                <button onClick="show_dialog('检查文书','/village/check-document?checkId=${check.id }')"
                        class="btn btn-success radius" type="button" style="padding: 0 70px;">检查文书
                </button>
                <%--                <button onClick="location.href = '/village/check-document?checkId=${check.id }'"--%>
                <%--                        class="btn btn-success radius" type="button" style="padding: 0 70px;">检查文书--%>
                <%--                </button>--%>
            </c:if>

            <%--显示部门抽查检查文书--%>
            <c:if test="${flag==3 }">
                <c:if test="${is_re==1}">
                    <button onClick="show_dialog('检查文书','/village/check-document?checkId=${check.id }')"
                            class="btn btn-success radius" type="button" style="padding: 0 70px;">检查文书
                    </button>
                    <%--                <button onClick="location.href = '/village/check-document?checkId=${check.id }'"--%>
                    <%--                        class="btn btn-success radius" type="button" style="padding: 0 70px;">检查文书--%>
                    <%--                </button>--%>
                </c:if>
            </c:if>

        </c:if>

        <c:if test="${latitude!=null&&longitude!=null}">
            <button onClick="location.href = '/village/loadMap?latitude=${latitude}&longitude=${longitude}&checkId=${check.id }'"
                    class="btn btn-success radius" type="button" style="padding: 0 70px;">查看检查定位
            </button>
        </c:if>

    </div>
</div>
<div id="div_container">
    <h2 class="text-c mb-10">检查详情</h2>
    <div class="f-l div_pdetail mt-10">
        <c:if test="${flag==2 || flag== 3 }">
            <font>受检公司：${user.userName }</font>
        </c:if>
        <c:if test="${flag==1}">
            <font>受检部门：${check.depart }</font>
        </c:if>
        <label>检查日期：<fmt:formatDate value="${check.realTime }" pattern="yyyy年MM月dd日"/> <c:if
                test="${empty check.realTime}">&nbsp;&nbsp;年&nbsp;月&nbsp;日</c:if>
        </label>
    </div>
    <div class="f-l mt-20" style="width: 100%">
        <table class="f-l table table-border table-bordered table-bg table-hover table-sort">
            <thead>
            <tr class="text-c">
                <th width="5%">检查类型</th>
                <th width="7%">车间/场所</th>
                <c:if test="${flag == 1}">
                    <th width="10%">系统</th>
                </c:if>
                <th width="3%">环节/部位</th>
                <th width="5%">检查方式</th>
                <th width="3%">检查形式</th>
                <th width="12%">${!empty check.industryId ? '检查内容' : '隐患描述'}</th>
                <th width="4%">检查结果</th>
                <%--                <th width="15%">隐患内容</th>--%>
                <c:if test="${flag == 1}">
                    <th width="7%">隐患等级</th>
                </c:if>
                <th width="9%">查看</th>
            </tr>
            </thead>
            <tbody>
            <%--TODO 这里进行合并的话,会没有检查合格的图片--%>
            <c:forEach items="${itemL }" var="ch">
                <tr class="text-c">

                    <td>
                        <c:choose>
                            <c:when test="${ch.industry_type == 1}">基础</c:when>
                            <c:when test="${ch.industry_type == 2}">现场</c:when>
                            <c:when test="${ch.industry_type == 3}">高危</c:when>
                        </c:choose>
                    </td>
                    <td class="text-c">${ch.part == "" ? "暂无数据" : ch.part}</td>
                    <td class="text-c">${ch.level2 == "" ? "暂无数据" : ch.level2}</td>
                    <c:if test="${flag == 1}">
                        <c:set value="${fn:split(ch.sys,'/')}" var="ls"></c:set>
                        <td class="text-c">${ls[1] != null ? ls[1] : "暂无数据" }</td>
                    </c:if>

                    <td>
                        <c:choose>
                            <c:when test="${ch.type == 1}">日常</c:when>
                            <c:when test="${ch.type == 2}">定期</c:when>
                            <c:when test="${ch.type == 3}">季节</c:when>
                            <c:when test="${ch.type == 4}">其他</c:when>
                            <c:when test="${ch.type == 5}">综合</c:when>
                        </c:choose>

                    </td>

                    <td>

                        <c:choose>
                            <c:when test="${ch.flag == 1}">企业自查</c:when>
                            <c:when test="${ch.flag == 2}">行政执法</c:when>
                            <c:when test="${ch.flag == 3}">第三方排查</c:when>
                        </c:choose>

                    </td>

                        <%--                    <td class="text-c">${ch.measures == "" ? "暂无数据" : ch.measures}</td>--%>
                    <td class="text-c">${ch.measures == "" ? "暂无数据" : ch.measures}</td>
                    <td>
                        <c:if test="${ empty ch.status}">没有数据</c:if>
                        <c:if test="${ not empty ch.status}">
                            <c:choose>
                                <c:when test="${ch.status == 1}">合格</c:when>
                                <c:when test="${ch.status == 2}">不合格</c:when>
                                <c:when test="${ch.status == 3}">已复查</c:when>
                            </c:choose>
                        </c:if>
                    </td>
                        <%--                    <td>--%>
                        <%--                        <c:if test="${ empty ch.factors}">--%>
                        <%--                            暂无数据--%>
                        <%--                        </c:if>--%>
                        <%--                        <c:if test="${ not empty ch.factors}">--%>
                        <%--                            ${ch.factors}--%>
                        <%--                        </c:if>--%>
                        <%--                    </td>--%>

                    <c:if test="${flag == 1}">
                        <td>
                            <c:if test="${ch.status != 1}">
                                <c:choose>
                                    <c:when test="${ch.level eq '红色'}"><font class="col-a">${ch.level}</font></c:when>
                                    <c:when test="${ch.level eq '橙色'}"><font class="col-b">${ch.level}</font></c:when>
                                    <c:when test="${ch.level eq '黄色'}"><font class="col-c">${ch.level}</font></c:when>
                                    <c:when test="${ch.level eq '蓝色'}"><font class="col-d">${ch.level}</font></c:when>
                                </c:choose>
                            </c:if>
                            <c:if test="${ch.status == 1}">
                                无
                            </c:if>
                        </td>
                    </c:if>

                    <td class="text-c">
                        <button class="btn radius btn-danger size-S ml-20" onClick="showpicture('${ch.files}')">
                            <i class="Hui-iconfont" style="font-size: 15px;">&#xe613;</i> 查看图片
                        </button>
                    </td>
                </tr>
            </c:forEach>

            <%--<c:forEach items="${partL }" var="part" varStatus="pi">
              <c:set var="x" value="0" />
              <c:forEach items="${itemL }" var="ch">
                <c:if test="${ch.partId == part.id}">
                  <c:set var="x" value="${x + 1}" />
                </c:if>
              </c:forEach>
              <tr>
                &lt;%&ndash;<td class="text-c" rowspan="${x }">${pi.index + 1}</td>
                <td class="text-c" rowspan="${x }">&ndash;%&gt;
               &lt;%&ndash; <c:if test="${!empty part.partImg}">
                    <img src="${part.partImg }" class="mr-10" style="height:100px;"/>
                </c:if>&ndash;%&gt;
  &lt;%&ndash;
                  <c:forTokens items=" ${part.name}" delims="!@#" var="splitSubname">             
                                   <c:if test="${!fn:contains(tempSubname,splitSubname)}">                
                                                 ${splitSubname}

                                      <c:set var="tempSubname" value="${tempSubname}${splitSubname}"/>
                                     </c:if>

                  </c:forTokens>&ndash;%&gt;

                ${part.name}

                <button class="btn radius btn-danger size-S ml-20" onClick="showpicture('${part.partImg }')">
                  <i class="Hui-iconfont" style="font-size: 15px;">&#xe613;</i> 查看大图
                </button>
                </td>
                <c:set var="y" value="0" />
                <c:forEach items="${itemL }" var="ch">
                  <c:if test="${ch.partId == part.id}">
                    <c:if test="${y > 0}">
                      <tr>
                    </c:if>
                    <td class="text-l">${ch.levels }</td>
                    <td class="text-l">${!empty check.industryId ? ch.content : ch.memo }</td>
                    <td class="text-c">&nbsp; <!-- 判断不合格显示 --> <c:if test="${ch.status == 1}">
                        <font>合格</font>
                      </c:if> <c:if test="${ch.status > 1}">
                        <font>不合格</font>
                      </c:if> <c:if test="${! empty ch.files}">
                        <button class="btn radius btn-danger size-S ml-20" onClick="showpicture('${ch.files}')">
                          <i class="Hui-iconfont" style="font-size: 15px;">&#xe613;</i> 查看图片
                        </button>
                      </c:if>
                    </td>
                    <c:if test="${y > 0}">
              </tr>
              </c:if>
              <c:set var="y" value="1" />
              </c:if>
            </c:forEach>
            </tr>
            </c:forEach>--%>
            <!--  <tr>
            <td colspan="5">
              <div class="div_pleft  mt-10">整改要求：</div>
              <div class="div_pright  mt-10">
                <select name="isZheng" class="select">
                  <option value="1">立即整改</option>
                </select>
              </div>
              <div class="div_pri mb-10 mt-10">
                <input type="text" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'"  name="zhengTime" class="input-text Wdate" style="width:250px;">
                <font>整改日期：</font>
              </div>
            </td>
          </tr> -->

            <%-- <tr>
            <td rowspan="${fn:length(partL)}"></td>
            <td rowspan="${fn:length(partL)}"><div class="div_pleft  mt-10">整改要求：</div></td>
            <td colspan="3">${partL[0].name } 整改意见:
                <select data-partname="${partL[0].name }" name="suggest" data-part="${partL[0].id }" class="select" style="width: 200px;">
                  <option value=""></option>
                  <option value="1" <c:if test="${partL[0].suggest == 1}"> selected</c:if>>立即整改</option>
                  <option value="2" <c:if test="${partL[0].suggest == 2}"> selected</c:if>>限期整改</option>
                  <option value="3" <c:if test="${partL[0].suggest == 3}"> selected</c:if>>整改计划</option>
                </select>
                                       整改期限 :
              <input type="text" value="<fmt:formatDate value="${partL[0].dangerTime }" pattern="yyyy-MM-dd"/>" data-part="${partL[0].id }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})" name="dangerTime" class="input-text Wdate" style="width:250px;">
            </td>
          </tr>
          <c:forEach items="${partL }" var="part" varStatus="pi" begin="1">
            <tr>
            <td colspan="3">${part.name } 整改意见:
                <select data-partname="${part.name }" name="suggest" data-part="${part.id }" class="select" style="width: 200px;">
                  <option value="0"></option>
                  <option value="1" <c:if test="${part.suggest == 1}"> selected</c:if>>立即整改</option>
                  <option value="2" <c:if test="${part.suggest == 2}"> selected</c:if>>限期整改</option>
                  <option value="3" <c:if test="${part.suggest == 3}"> selected</c:if>>整改计划</option>
                </select>
                                       整改期限 :
              <input type="text" value="<fmt:formatDate value="${part.dangerTime }" pattern="yyyy-MM-dd"/>" data-part="${part.id }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})" name="dangerTime" class="input-text Wdate" style="width:250px;">
            </td>
            </tr>
          </c:forEach> --%>

            <tr>
                <td colspan="5">
                    <div style="float: left; width: 100%; position: relative; min-height: 150px;">
                        <c:if test="${check.flag == 3 && check.checkCompany eq '无锡市安泰安全技术服务有限公司'}">
                            <img alt="" src="${ly }/images/zhang.png"
                                 style="position: absolute; bottom: 10px; right: 10px; height: 130px;"/>
                        </c:if>
                        <c:if test="${check.flag > 1}">
                            <div class="div_pleft  mt-10 mb-10">检查部门/单位：</div>
                            <div class="div_pright  mt-10 mb-10">
                                <input type="text" style="width: 200px" id="checkCompany" value="${check.checkCompany }"
                                       class="input-text" maxlength="50"
                                       disabled="disabled"/>
                            </div>
                            <br/>
                        </c:if>
                        <div class="div_pleft  mt-10 mb-10">检查人员签字：</div>
                        <div class="div_pright  mt-10 mb-10">
                            <input type="text" style="width: 200px" id="cheker" value="${check.cheker }"
                                   class="input-text" maxlength="50" disabled="disabled"/>
                        </div>
                    </div>
                </td>
                <td colspan="5">
                    <div style="float: left; width: 100%; min-height: 150px;">
                        <div class="div_pleft  mt-10 mb-10">受检负责人签字：</div>
                        <div class="div_pright  mt-10 mb-10">
                            <input type="text" style="width: 200px" value="${name}" class="input-text" maxlength="50"
                                   disabled="disabled"/>
                        </div>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<!-- 弹窗图片 -->
<div id="modal-plan" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header">
                <h3 class="modal-title">查看图片</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body" style="height: 400px; overflow-y: auto">
                <!-- 循环图片 -->
                <div class="div_imgp">
                    <img src="${ly }/images/zwtp.jpg" class="img-responsive mt-20">
                </div>
                <!-- 循环结束 -->
            </div>
        </div>
    </div>
</div>

</body>

<script type="text/javascript">
    $(function () {
        var b = null, l1 = '', c = 1;
        var b2 = null, l2 = '', c2 = 1;
        var b3 = null, l3 = '', c3 = 1;
        var b4 = null, l4 = '', c4 = 1;
        $("tbody tr").each(function () {
            var td = $(this).children("td").eq(0);
            var td2 = $(this).children("td").eq(1);
            var td3 = $(this).children("td").eq(2);
            var td4 = $(this).children("td").eq(3);
            var l1_ = td.text();
            var l2_ = td2.text();
            var l3_ = td3.text();
            var l4_ = td4.text();

            //Same to top level
            if (l1 == l1_) {
                td.remove();
                c = c + 1;
                if (l2 == l2_) {
                    td2.remove();
                    c2 = c2 + 1;
                    if (l3 == l3_) {
                        td3.remove();
                        c3 = c3 + 1;
                        if (l4 == l4_) {
                            td4.remove();
                            c4 = c4 + 1;
                        } else {
                            l4 = l4_;
                            if (b4 != null) {
                                b4.attr("rowspan", c4);
                                c4 = 1;
                            }
                            b4 = td4;
                        }
                    } else {
                        l3 = l3_;
                        if (b3 != null) {
                            b3.attr("rowspan", c3);
                            c3 = 1;
                        }
                        b3 = td3;
                        l4 = l4_;
                        if (b4 != null) {
                            b4.attr("rowspan", c4);
                            c4 = 1;
                        }
                        b4 = td4;
                    }
                } else {
                    l2 = l2_;
                    if (b2 != null) {
                        b2.attr("rowspan", c2);
                        c2 = 1;
                    }
                    b2 = td2;
                    l3 = l3_;
                    if (b3 != null) {
                        b3.attr("rowspan", c3);
                        c3 = 1;
                    }
                    b3 = td3;
                    l4 = l4_;
                    if (b4 != null) {
                        b4.attr("rowspan", c4);
                        c4 = 1;
                    }
                    b4 = td4;
                }

            } else {//Diffrent to top level
                l1 = l1_;
                if (b != null) {
                    b.attr("rowspan", c);
                    c = 1;
                }
                b = td;
                l2 = l2_;
                if (b2 != null) {
                    b2.attr("rowspan", c2);
                    c2 = 1;
                }
                b2 = td2;
                l3 = l3_;
                if (b3 != null) {
                    b3.attr("rowspan", c3);
                    c3 = 1;
                }
                b3 = td3;
                l4 = l4_;
                if (b4 != null) {
                    b4.attr("rowspan", c4);
                    c4 = 1;
                }
                b4 = td4;
            }
        })
        if (b != null) {
            b.attr("rowspan", c);
        }
        if (b2 != null) {
            b2.attr("rowspan", c2);
        }
        if (b3 != null) {
            b3.attr("rowspan", c3);
        }
        if (b4 != null) {
            b4.attr("rowspan", c4);
        }
    })
</script>
</html>