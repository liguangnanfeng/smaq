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

        .btn-upload .input-text {
        width: auto
        }

        .form-group .upload-btn {
        margin-left: -1px
        }

        .my_flex {
        display: -webkit-box; /* Chrome 4+, Safari 3.1, iOS Safari 3.2+ */
        display: -moz-box; /* Firefox 17- */
        display: -webkit-flex; /* Chrome 21+, Safari 6.1+, iOS Safari 7+, Opera 15/16 */
        display: -moz-flex; /* Firefox 18+ */
        display: -ms-flexbox; /* IE 10 */
        display: flex; /* Chrome 29+, Firefox 22+, IE 11+, Opera 12.1/17/18, Android 4.4+ */
        }

        .f_c {
        -webkit-box-orient: vertical;
        -ms-flex-direction: column;
        -webkit-flex-direction: column;
        flex-direction: column;
        }

        .f_r {
        -webkit-box-orient: horizontal;
        -ms-flex-direction: row;
        -webkit-flex-direction: row;
        flex-direction: row;
        }

        .f_z_c {
        -webkit-box-pack: center;
        -ms-flex-pack: center;
        -webkit-justify-content: center;
        justify-content: center;
        }

        .f_j_c {
        -webkit-box-align: center;
        -ms-flex-align: center;
        -webkit-align-items: center;
        align-items: center;
        }

        .f_wrap {
        flex-wrap: wrap;
        }

        .item_container {
        width: 20%;
        height: 7.5vw;
        padding: 10px;
        box-sizing: border-box;
        position: relative;
        }

        .item_content {
        -moz-box-shadow: 0px 0px 9px rgba(95,145,230,0.5);
        box-shadow: 0px 0px 9px rgba(95,145,230,0.5);
        width: 100%;
        height: 100%;
        padding: 10px;
        cursor: pointer;
        overflow: hidden;
        text-overflow: ellipsis;
        /*background-color: #0B9EFB;*/
        box-sizing: border-box;
        position: relative;
        background:#5A98DE;
        }

        .item_container > span {
        position: absolute;
        right: 12px;
        top: 17px;
        padding-right: 5px;
        display: none;
        }

        .item_container > span a {
        color: #fff;
        background: #429842;
        border-radius: 6px;
        padding: 4px 15px;
        text-decoration: none;
        }

        .item_container > span a:first-child {
        margin-right: 5px;
        }
        .item_content:hover{
        background:#3275BF;
        color:rgba(95,152,222,1)
        }
        .item_container:hover .my_span {
        display: block;
        }
        h4{
        color:#fff;
        }
        .table tbody tr td a{
        border:none;
        padding: 3px 9px;
        }
        </style>
        <script type="text/javascript">
        console.log('${list}')


        var id;
        function openzl(Id) {
        id = Id
        $("#upload-zl").modal("show")
        }
        </script>
        </head>
        <body>
        <nav class="breadcrumb">
        <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
        <span class="c-gray en">&gt;</span> <span>安全生产标准化</span>
        <!--  <span class="c-gray en">&gt;</span><span>安全生产管理档案</span> -->
        <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px"
        href="javascript:location.replace(location.href);" title="刷新">
        <i class="Hui-iconfont">&#xe68f;</i>
        </a>
        </nav>
        <div class="page-container">
        <div class="text-c">
        <h3>${companyName}企业安全标准化操作系统</h3>
        </div>

        <%--<div class="item_container">--%>
          <%--<div class="item_content"></div>--%>
        <%--</div>--%>


        <%--<c:if test="${userType==9}">--%>
        <!--地级市-->
        <div class="">
        <%--<i class="Hui-iconfont top-iconfont">&#xe6d5;</i>--%>
        <ul>
        <!-- 循环开始 -->
        <c:forEach items="${list }" varStatus="index" var="list">
        <div class="item_container"><a class="item_content" data-href="${ly }/global/wel?districtId=${list.code }" data-title="${list.name}" href="javascript:void(0)">${list.name}</a></div>
        </c:forEach>
        <!-- 循环结束 -->
        </ul>
        </div>
        <%--</c:if>--%>
        <c:if test="${userType==6}">
        <!--县,地址市-->
        <li><a data-href="${ly }/global/company/company-list?key2=1&sk=0" data-title="重点企业" href="javascript:void(0)">重点企业</a></li>
        <div class="div_omenu">
        <div  class="div_otop">各地企业<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>
        <ul>
        <!-- 循环开始 -->
        <c:if test="${moveD != 1 }">
        <c:forEach items="${list }" varStatus="index" var="list">
        <div class=""><a data-href=" " data-title=" " href="${ly }/global/move/next?uid=${list.userId }" target="_parent">${list.name}</a></div>
        </c:forEach>
        </c:if>
        <c:if test="${moveD == 1 }">
        <c:forEach items="${list }" varStatus="index" var="list">
        <li><a data-href="${ly }/global/wel?townId=${list.userId }" data-title="${list.name}" href="javascript:void(0)">${list.name}</a></li>
        </c:forEach>
        </c:if>
        <!-- 循环结束 -->
        </ul>
        </div>
        </c:if>
        <c:if test="${userType==3}">
        <!--镇级-->
        <li><a data-href="${ly }/global/company/company-list?key1=1&sk=0" data-title="重点企业" href="javascript:void(0)">重点企业</a></li>
        <div class="div_omenu">
        <div  class="div_otop">各地企业<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>
        <ul>
        <!-- 循环开始 -->
        <c:forEach items="${list }" varStatus="index" var="list">
        <li><a data-href="${ly }/global/wel?villageId=${list.userId }" data-title="${list.name}" href="javascript:void(0)">${list.name}</a></li>
        </c:forEach>
        <!-- 循环结束 -->
        </ul>
        </div>
        </c:if>
        <c:if test="${userType==4}">
        <!--乡,村级-->
        <li><a data-href="${ly }/global/company/company-list?isKey=1&sk=0" data-title="重点企业" href="javascript:void(0)">重点企业</a></li>
        </c:if>
        <c:if test="${userType==10}">
        <!--行业级-->
        <c:if test="${session_user.id != 788}">
        <li><a data-href="${ly }/global/company/company-list?isKey=1&sk=0" data-title="重点企业" href="javascript:void(0)">重点企业</a></li>
        </c:if>
        </c:if>




        </div>




        </body>
        </html>