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

        .btn-group .btn {
            height: 34px;
            line-height: 34px;
            padding: 0 25px;
        }
    </style>
</head>
<script>
    // 根据部门名称进行查询
    function queryButton() {
        var dmName = $("[name='dmName']").val();
        alert(dmName);
        $.ajax({

    url:"${ly }/village/check-list",

    }
    )


    }

    /*删除*/
    function del_(id) {
    layer.confirm("要删除该记录？", function (i) {
    layer.close(i);
    $.post(getRootPath() + "/company/check-del", {
    id: id
    }, function (reuslt) {
    location.reload();
    })
    })
    }

</script>
<body>
<nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
    <span class="c-gray en">&gt;</span> <span>${flag!=2 ? '隐患排查治理系统' : '行政执法系统'}</span>
    <c:if test="${flag != 2}">
        <span class="c-gray en">&gt;</span> <span>隐患排查记录</span>
    </c:if>
    <c:if test="${flag == 2}">
        <span class="c-gray en">&gt;</span> <span>执法记录</span>
    </c:if>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
</nav>
<div class="page-container">

    <div id="spTab" class="btn-group" style="text-align: center;margin-bottom: 20px;">
        <a class="btn default ${flag == 1 ? 'btn-primary' : 'radius'}" href="${ly }/village/check-list?flag=1&status=2">企业自查</a>
        <a class="btn default ${flag == 2 ? 'btn-primary' : 'radius'}" href="${ly }/village/check-list?flag=2&status=2">行政检查</a>
        <a class="btn default ${flag == 3 ? 'btn-primary' : 'radius'}" href="${ly }/village/check-list?flag=3&status=2">第三方检查</a>
        <%-- <a class="btn default ${flag == 2 ? 'btn-primary' : 'radius'}" href="${ly }/village/check-list?flag=2">执法检查</a> --%>
    </div>

    <div class="text-c">
        <form action="${ly }/village/check-list?flag=${flag}" method="post">
            <div class="dis-ib">
                <span>检查表名称：</span>
                <input type="text" value="${title }" name="title" class="input-text mb-5 mt-5" style="width:150px;">
            </div>
            <button class="btn btn-success" type="submit">
                <i class="Hui-iconfont">&#xe665;</i> 查询
            </button>
        </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l">
      <!--   <c:if test="${flag == 2}">
          <a class="btn btn-primary radius" data-title="添加行政检查表" data-href="${ly }/village/check-add?flag=2"
             onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont"
                                                                  style="font-size:15px;">&#xe600;</i> 添加行政检查表</a>
              </c:if>
               <c:if test="${flag == 3}">
           <a class="btn btn-primary radius" data-title="添加线下检查记录" data-href="${ly }/village/check-add3?flag=3"
              onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont"
                                                                   style="font-size:15px;">&#xe600;</i> 添加线下检查记录</a>
               </c:if> 
        <c:if test="${flag == 4}">
            <a class="btn btn-primary radius" data-title="添加线下检查记录" data-href="${ly }/village/check-add3?flag=4"
               onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 添加线下检查记录</a>
        </c:if>  -->
      </span>
        <c:if test="${flag == 1}">
             <span class="l">


      <form action="${ly }/village/check-list?flag=${flag}" method="post">
          <select class="sel_area isShow" id="partNamme"  name="dmName"  style="position:relative;top:3px">
            <option value="">全部</option>
            <c:forEach items="${set }" var="be">
                <option value="${be}">${be}</option>
            </c:forEach>
     </select>
            <button class="btn btn-success" type="submit">
                <i class="Hui-iconfont">&#xe665;</i> 查询
            </button>
        </form>

        </span>
        </c:if>
        <span class="r">检查总数：<strong>${fn:length(list) }</strong> 条；隐患数量：<strong>${sum}</strong> 条</span>
    </div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-bg table-hover table-sort">
            <thead>
            <tr class="text-c">
                <th width="5%">序号</th>
                <th width="20%">检查表名称</th>
                <th width="10%">${flag == 1 ? '检查方式' : '检查日期'}</th>
                <th width="10%">${flag == 1 ? '检查日期' : '检查部门'}</th>
                <%--<th width="10%">${flag == 1 ? '实际检查' : '录入'}时间</th>--%>
                <th width="10%">检查人员</th>
                <th width="10%">隐患数量</th>
                <%-- <th width="15%">${flag == 1 ? '受检部门' : '检查单位'}</th>--%>
                <th width="10%">操作</th>
            </tr>
            </thead>
            <tbody>
            <!-- 循环-->
            <c:forEach items="${list }" varStatus="index" var="be">
                <tr class="text-c">
                    <td>${index.index + 1 }</td>
                    <td><c:if test="${be.status == 1 and (!empty be.expectTime and be.expectTime.time < t)}"><font
                            color="red">【过期】</font></c:if>${be.title }</td>
                    <c:if test="${flag == 1}">
                        <td>
                            <c:choose>
                                <c:when test="${be.type == 1 }">日常</c:when>
                                <c:when test="${be.type == 2 }">定期</c:when>
                                <c:when test="${be.type == 3 }">季节</c:when>
                                <c:when test="${be.type == 4 }">其它</c:when>
                                <c:when test="${be.type == 5 }">综合</c:when>
                            </c:choose>
                        </td>
                        <td>
                            <fmt:formatDate value="${be.realTime }" pattern="yyyy-MM-dd"/>
                        </td>
                    </c:if>


                    <c:if test="${flag != 1}">
                        <td>
                            <fmt:formatDate value="${be.realTime }" pattern="yyyy-MM-dd"/>
                        </td>

                        <td>
                                ${be.depart}
                        </td>
                    </c:if>
                    <td>
                            <%--${be.status == 1 ? '未检查' : '已检查'}--%>
                            ${be.cheker}
                    </td>
                    <td>${be.c }</td>
                        <%--<td>${flag == 1 ? be.depart : be.checkCompany}</td>--%>
                    <td>
                            <%--              <c:if test="${flag != 4}">--%>
                            <%--              <c:if test="${be.status == 1}">--%>
                            <%--                &lt;%&ndash; <c:if test="${be.flag == 1 || be.flag == 3}">--%>
                            <%--                <c:if test="${empty be.expectTime or be.expectTime.time >= t}">--%>
                            <%--                <a style="text-decoration:none" onClick="show_tab('修改检查表_${be.id }', '${ly}/company/plan-edit?id=${be.id }')" href="javascript:;">修改检查表</a><br>--%>
                            <%--                </c:if>--%>
                            <%--                </c:if>--%>
                            <%--                <c:if test="${be.flag == 2 }">--%>
                            <%--                <a style="text-decoration:none" onClick="show_tab('修改检查表_${be.id }', '${ly}/company/check-edit?id=${be.id }')" href="javascript:;">修改检查表</a><br>--%>
                            <%--                </c:if> &ndash;%&gt;--%>
                            <%--              </c:if>--%>
                            <%--              --%>
                            <%--              <!-- 判断能否录入 -->--%>
                            <%--              <c:choose>--%>
                            <%--                <c:when test="${be.status == 1 && (empty be.expectTime or be.expectTime.time >= t) && be.flag != 2}">--%>
                            <%--                &lt;%&ndash; <c:when test="${be.status == 1 && (be.flag == 1 || be.flag == 3) && (empty be.expectTime or be.expectTime.time >= t) }"> &ndash;%&gt;--%>
                            <%--                     <a style="text-decoration:none" onClick="show_dialog('实施检查_${be.id }', '${ly}/village/plan-next?flag=${flag }&id=${be.id }')" href="javascript:;">实施检查</a><br>--%>
                            <%--                </c:when>--%>
                            <%--                <c:otherwise>--%>
                            <%--                <c:if test="${be.status == 2 && be.flag != 1}">--%>
                            <%--                <a style="text-decoration:none" onClick="show_dialog('现场检查记录_${be.id }', '${ly}/village/check-document?checkId=${be.id }&flag=8')" href="javascript:;">现场检查记录</a><br>--%>
                            <%--                </c:if>--%>
                            <%--                <c:if test="${be.status != 2 || be.flag == 1}">--%>
                            <%--                     <a style="text-decoration:none" onClick="show_dialog('检查详情_${be.id }', '${ly}/company/check-detail?flag=${flag }&id=${be.id }')" href="javascript:;">检查详情</a><br>--%>
                            <%--                     </c:if>--%>
                            <%--                     &lt;%&ndash; <c:if test="${flag == 1 }">--%>
                            <%--                     <a style="text-decoration:none" onClick="show_dialog('检查详情_${be.id }', '${ly}/company/check-detail?flag=${flag }&id=${be.id }')" href="javascript:;">检查详情</a><br>--%>
                            <%--                     </c:if>--%>
                            <%--                     <c:if test="${flag != 1 }">--%>
                            <%--                     <a style="text-decoration:none" onClick="show_dialog('检查详情_${be.id }', '/village/check-document?checkId=${be.id}&flag=8')" href="javascript:;">检查详情</a><br>--%>
                            <%--                     </c:if> &ndash;%&gt;--%>
                            <%--                </c:otherwise>--%>
                            <%--              </c:choose>--%>
                            <%--              --%>
                            <%--              <!-- 判断是否有治理意见表 -->--%>
                            <%--              <c:if test="${be.c > 0 && be.status == 2 }">&lt;%&ndash; 已检查过且有隐患的需要治理意见表&ndash;%&gt;--%>
                            <%--                  <c:choose>--%>
                            <%--                    <c:when test="${be.t > 0}">&lt;%&ndash; 已设置整改意见 &ndash;%&gt;--%>
                            <%--                    <c:if test="${be.status == 2 && be.flag != 1}">--%>
                            <%--                    <c:if test="${flag == 4 || flag == 2}">--%>
                            <%--                       <a style="text-decoration:none" onClick="show_dialog('责令限期整改意见书_${be.id }', '${ly}/village/check-document?checkId=${be.id }&flag=1')" href="javascript:;">整改意见书</a><br>--%>
                            <%--                       </c:if>--%>
                            <%--                       <c:if test="${flag == 3}">--%>
                            <%--                       <a style="text-decoration:none" onClick="show_dialog('责令限期整改意见书_${be.id }', '${ly}/village/check-document?checkId=${be.id }&flag=8')" href="javascript:;">整改意见书</a><br>--%>
                            <%--                       </c:if>--%>
                            <%--                </c:if>--%>
                            <%--                <c:if test="${be.status != 2 || be.flag == 1}">--%>
                            <%--                       <a style="text-decoration:none" onClick="show_dialog('整改意见_${be.id }', '${ly}/village/check-rectification?flag=${flag }&id=${be.id }')" href="javascript:;">整改详情</a><br>--%>
                            <%--                       </c:if>--%>
                            <%--                       &lt;%&ndash; <c:if test="${flag == 1 }">--%>
                            <%--                       <a style="text-decoration:none" onClick="show_dialog('整改意见_${be.id }', '${ly}/village/check-rectification?flag=${flag }&id=${be.id }')" href="javascript:;">整改意见</a><br>--%>
                            <%--                       </c:if>--%>
                            <%--                       <c:if test="${flag != 1 }">--%>
                            <%--                       <a style="text-decoration:none" onClick="show_dialog('整改意见_${be.id }', '/village/check-document?checkId=${be.id}&flag=${flag == 3 ? 8 : 1}')" href="javascript:;">整改意见</a><br>--%>
                            <%--                       </c:if> &ndash;%&gt;--%>
                            <%--                    </c:when>--%>
                            <%--                    &lt;%&ndash; <c:otherwise>未设置整改意见--%>
                            <%--                      <a style="text-decoration:none" onClick="show_dialog('整改意见_${be.id }', '${ly}/village/check-rectification?flag=${flag }&id=${be.id }')" href="javascript:;">整改详情</a><br>--%>
                            <%--                    </c:otherwise> &ndash;%&gt;--%>
                            <%--                  </c:choose>--%>
                            <%--              --%>
                            <%--              --%>
                            <%--                  <!-- 已经有复查 -->--%>
                            <%--                  <c:if test="${be.c3 > 0}">--%>
                            <%--                  <c:if test="${be.status == 2 && be.flag != 1}">--%>
                            <%--                  <a style="text-decoration:none" onClick="show_dialog('整改复查意见书_${be.id }', '${ly}/village/check-document?checkId=${be.id }&flag=2')" href="javascript:;">整改复查意见书</a><br>--%>
                            <%--                </c:if>--%>
                            <%--                <c:if test="${be.status != 2 || be.flag == 1}">--%>
                            <%--                    <a style="text-decoration:none" onClick="show_dialog('复查详情_${be.id}', '${ly }/company/recheck-detail?checkId=${be.id }')" href="javascript:;">复查详情</a><br>--%>
                            <%--                 </c:if>--%>
                            <%--                  </c:if>--%>
                            <%--                  &lt;%&ndash; <c:if test="${be.c3 == 0}">--%>
                            <%--                    <a style="text-decoration:none" onClick="layer.alert('还没有复查记录哦。。。')" href="javascript:;">复查详情</a><br>--%>
                            <%--                  </c:if> &ndash;%&gt;--%>
                            <%--              &lt;%&ndash; <c:choose>--%>
                            <%--                <c:when test="${be.flag == 1 || be.flag == 3}">自查与委托可编辑可查看--%>
                            <%--                  <a style="text-decoration:none" onClick="show_dialog('整改意见_${be.id }', '${ly}/village/check-rectification?flag=${flag }&id=${be.id }')" href="javascript:;">整改意见</a><br>--%>
                            <%--                </c:when>--%>
                            <%--                <c:when test="${be.t > 0}">已设置治理意见--%>
                            <%--                   <a style="text-decoration:none" onClick="show_dialog('整改意见_${be.id }', '${ly}/village/check-rectification?flag=${flag }&id=${be.id }')" href="javascript:;">整改意见</a><br>--%>
                            <%--                </c:when>--%>
                            <%--                <c:otherwise>未设置治理意见--%>
                            <%--                   <a style="text-decoration:none" onClick="layer.alert('还未设置整改意见')" href="javascript:;">整改意见</a><br>--%>
                            <%--                </c:otherwise>--%>
                            <%--              </c:choose> &ndash;%&gt;--%>
                            <%--                &lt;%&ndash; <a style="text-decoration:none" onClick="show_tab('隐患汇总表_${be.id }', '${ly}/company/check-danger?id=${be.id }')" href="javascript:;">隐患汇总表</a><br> &ndash;%&gt;--%>
                            <%--              </c:if>--%>
                            <%--              --%>
                            <%--             &lt;%&ndash; <c:if test="${be.status == 2 && be.flag != 1}">--%>
                            <%--                  <a style="text-decoration:none" onClick="show_dialog('检查文书-现场检查记录_${be.id }', '${ly}/village/check-document?checkId=${be.id }&flag=8')" href="javascript:;">查看检查文书</a><br>--%>
                            <%--                  <a style="text-decoration:none" onClick="show_dialog('现场检查记录_${be.id }', '${ly}/village/check-document?checkId=${be.id }&flag=8')" href="javascript:;">现场检查记录</a><br>--%>
                            <%--                  <a style="text-decoration:none" onClick="show_dialog('责令限期整改意见书_${be.id }', '${ly}/village/check-document?checkId=${be.id }&flag=1')" href="javascript:;">整改意见书</a><br>--%>
                            <%--                  <a style="text-decoration:none" onClick="show_dialog('整改复查意见书_${be.id }', '${ly}/village/check-document?checkId=${be.id }&flag=2')" href="javascript:;">整改复查意见书</a><br>--%>
                            <%--              </c:if> &ndash;%&gt;--%>
                            <%--               </c:if>--%>
                            <%--               <c:if test="${flag == 4}">--%>
                            <%--               <a style="text-decoration:none" onClick="show_dialog('现场检查记录_${be.id }', '${ly}/village/check-document?checkId=${be.id }&flag=8')" href="javascript:;">现场检查记录</a><br>--%>
                            <%--               <c:if test="${be.c > 0}">--%>
                            <%--               <a style="text-decoration:none" onClick="show_dialog('责令限期整改意见书_${be.id }', '${ly}/village/check-document?checkId=${be.id }&flag=1')" href="javascript:;">整改意见书</a><br>--%>
                            <%--               <a style="text-decoration:none" onClick="show_dialog('整改复查意见书_${be.id }', '${ly}/village/check-document?checkId=${be.id }&flag=2')" href="javascript:;">整改复查意见书</a><br>--%>
                            <%--               --%>
                            <%--               </c:if>--%>
                            <%--               </c:if>--%>
                            <%--              &lt;%&ndash; <c:if test="${be.flag == 1 || be.flag == 3}">--%>
                            <%--                <a style="text-decoration:none" onClick="del_(${be.id})" href="javascript:;">删除检查记录</a>--%>
                            <%--              </c:if> &ndash;%&gt;--%>
                            <%--              <c:if test="${flag != 1 && be.c > 0 }">--%>
                            <%--              <a style="text-decoration:none" onClick="show_dialog('隐患描述与图片_${be.id }', '${ly}/company/danger-detail?flag=${flag }&id=${be.id }')" href="javascript:;">隐患与图片</a><br>--%>
                            <%--              </c:if>--%>
                            <%--              &lt;%&ndash; <a style="text-decoration:none" onClick="del_(${be.id})" href="javascript:;">删除检查记录</a> &ndash;%&gt;--%>
                        <a style="text-decoration:none"
                           onClick="show_dialog('检查详情_${be.id }', '${ly}/company/check-detail?flag=${flag }&id=${be.id }&number=${be.c}')"
                           href="javascript:;">检查详情</a><br>
                        <c:if test="${be.listM.c3 > 0}">
                            <a style="text-decoration:none"
                               onClick="location.href = '/company/recheck-detail?checkId=${be.id}&flag=${flag}&number=${be.c }'"
                               href="javascript:;">复查详情</a><br>
                        </c:if>

                        <c:if test="${flag!=1}">
                            <a style="text-decoration:none"
                               onClick="location.href = '/village/check-document?checkId=${be.id}'" href="javascript:;">文书详情</a><br>
                        </c:if>
                        <a style="text-decoration:none"  onClick="del_(${be.id})" href="javascript:;">删除记录</a>
                    </td>
                </tr>
            </c:forEach>
            <!-- 循环结束 -->
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript">

    $(function () {
        sessionStorage.clear();
        $('.table-sort').dataTable({
            "aaSorting": [[0, "asc"]],//默认第几个排序
            "bStateSave": false,//状态保存
            "aoColumnDefs": []
        }),

        var type = '${type}';

        function copy_(id) {
            show_tab('添加自查记录', getRootPath() + '/company/check-copy?id=' + id);
        }

    });


</script>
</body>
</html>