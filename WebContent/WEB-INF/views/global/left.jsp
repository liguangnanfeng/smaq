    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <!DOCTYPE HTML>
        <html>
        <script type="text/javascript">
        $(document).ready(function() {
        $("body").on("click",".Hui-aside .menu_dropdown li a", function() {
        $(".Hui-aside .menu_dropdown li a").removeClass("l_active");
        $(this).addClass("l_active");
        });
        $(".div_otop2").on("click",function(){
        if($(this).closest(".div_omenu2").hasClass("div_omenu2xz")){
        $(this).closest(".div_omenu2").removeClass("div_omenu2xz");
        $(this).parent().find(".div_omenu").removeClass("div_omenuxz");
        }else{
        $(".div_omenu2").removeClass("div_omenuxz");
        $(this).closest(".div_omenu2").addClass("div_omenu2xz");
        }
        });
        $(".div_otop").on("click",function(){
        if($(this).closest(".div_omenu").hasClass("div_omenuxz")){
        $(this).closest(".div_omenu").removeClass("div_omenuxz");
        }else{
        $(".div_omenu").removeClass("div_omenuxz");
        $(this).closest(".div_omenu").addClass("div_omenuxz");
        }
        });
        $(".Hui-aside .menu_dropdown dt").on("click",function(){
        if(!$(this).hasClass("selected")){
        $(this).parent().find(".div_omenu").removeClass("div_omenuxz");
        $(this).parent().find(".div_omenu2").removeClass("div_omenu2xz");
        }
        });
        });
        </script>
        <body>
        <!-- 左侧导航 -->
        <aside class="Hui-aside">
        <input runat="server" id="divScrollValue" type="hidden" value="" />
        <div class="menu_dropdown bk_2">
        <dl>
        <dt>
        <i class="Hui-iconfont">&#xe643;</i>企业信息数据
        <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
        <ul>
        <script>
         console.log("${list}");
        console.log("${userType}");
        </script>
        <li><a data-href="${ly }/global/company/company-list" data-title="全部企业" href="javascript:void(0)">全部企业</a></li>
        <li><a data-href="${ly }/global/company/company-tab" data-title="企业分类" href="javascript:void(0)">企业分类</a></li>
        <c:if test="${userType==9}">
            <!--地级市-->
            <div class="div_omenu">
            <div  class="div_otop" data-href="${ly }/global/company-allcompany">各地企业</div>
            <i class="Hui-iconfont top-iconfont">&#xe6d5;</i>
            <ul>
            <!-- 循环开始 -->
            <c:forEach items="${list }" varStatus="index" var="list">
                <li><a data-href="${ly }/global/wel?districtId=${list.code }" data-title="${list.name}" href="javascript:void(0)">${list.name}</a></li>
            </c:forEach>
            <!-- 循环结束 -->
            </ul>
            </div>
            <div class="div_omenu">
            <div  class="div_otop">部门、人员管理<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>
            <ul>
            <!-- 循环开始 -->
            <%--<c:forEach items="${list1 }" varStatus="index" var="list1">--%>
                <%--<li><a data-href="${ly }/global/welcometrade?tradeId=${list1.code }" data-title="${list1.name}" href="javascript:void(0)">${list1.name}</a></li>--%>
            <%--</c:forEach>--%>
            <!-- 循环结束 -->
            </ul>
            </div>
        </c:if>
        <c:if test="${userType==6}">
            <!--县,地址市-->
            <li><a data-href="${ly }/global/company/company-list?key2=1&sk=0" data-title="重点企业" href="javascript:void(0)">重点企业</a></li>
            <div class="div_omenu">
            <div  class="div_otop">各地企业<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>
            <ul>
            <!-- 循环开始 -->
            <c:if test="${moveD != 1 }">
                <c:forEach items="${list }" varStatus="index" var="list">
                    <li><a data-href=" " data-title=" " href="${ly }/global/move/next?uid=${list.userId }" target="_parent">${list.name}</a></li>
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
        </ul>
        <%-- <div class="div_omenu">
          <div  class="div_otop">信息汇总<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>
          <ul>
            <li><a data-href="${ly }/village/warning/sperson-list" data-title="持证上岗" href="javascript:void(0)">持证上岗</a></li>
            <li><a data-href="${ly }/village/warning/sequipment-list" data-title="特种设备检测" href="javascript:void(0)">特种设备检测</a></li>
            <li><a data-href="${ly }/village/warning/detection-list" data-title="职业卫生检测" href="javascript:void(0)">职业卫生检测</a></li>
            <li><a data-href="${ly }/village/warning/lightning-list" data-title="防雷防静电" href="javascript:void(0)">防雷防静电</a></li>
            <li><a data-href="${ly }/village/evaluate/evaluation-list" data-title="评价评估" href="javascript:void(0)">评价评估</a></li>
            <li><a data-href="${ly }/village/evaluate/zheng-list" data-title="各类许可证" href="javascript:void(0)">各类许可证</a></li>
            <li><a data-href="${ly }/company/tables/tab-yjlist" data-title="应急预案" href="javascript:void(0)">应急预案</a></li>
          </ul>
        </div> --%>
        </dd>
        </dl>
        <dl>
        <dt>
        <i class="Hui-iconfont">&#xe63c;</i>风险分级管控
        <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
        <ul>
            <li><a data-href="${ly }/global/safety-system/statistics-list" data-title="风险数据分析" href="javascript:void(0)">风险数据分析</a></li>
            <li><a data-href="${ly }/global/safety-system/control-operation" data-title="分级管控运行" href="javascript:void(0)">分级管控运行</a></li>
<%--            <li><a data-href="${ly }/global/safety-system/all-risk-map" data-title="各类风险分布" href="javascript:void(0)">各类风险分布</a></li>--%>
            <li><a data-href="${ly }/global/company/company-map" data-title="各类风险分布" href="javascript:void(0)">各类风险分布</a></li>

            <%--<li><a data-href="${ly }/global/safety-system/control-listmenu?level=红色" data-title="重大风险汇总" href="javascript:void(0)">重大风险汇总</a></li>
            <li><a data-href="${ly }/global/safety-system/control-listmenu?level=橙色" data-title="较大风险汇总" href="javascript:void(0)">较大风险汇总</a></li>
            <li><a data-href="${ly }/global/safety-system/statistics-list" data-title="数据分析" href="javascript:void(0)">数据分析</a></li>--%>
        </ul>
        </dd>
        </dl>
        <dl>
        <dt>
        <i class="Hui-iconfont">&#xe687;</i>隐患排查治理
        <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
        <div class="div_omenu">
        <c:if test="${userType!=10&&userType!=9}">
             <li><a id="div_otop1"   data-href="${ly }/global/check-list-cx?flag=4" data-title="行政检查">检查设置实施</a></li>
        </c:if>
        <c:if test="${userType==10||userType==9}">
            <li><a id="div_otop1"   data-href="${ly }/global/check-list-cx?flag=3" data-title="行政检查">检查设置实施</a></li>
        </c:if>
            </div>
        <%--<li><a data-href="${ly }/global/check-list-cx?flag=4" data-title="行政检查" href="javascript:void(0)">行政检查</a></li>--%>
        <%--<c:if test="${userType==10||userType==9}">--%>
            <%--<!--行业级-->--%>
            <%--<li><a data-href="${ly }/global/check-list-cx?flag=3" data-title="部门抽查" href="javascript:void(0)">部门抽查</a></li>--%>
        <%--</c:if>--%>
        <%--<c:when test="${userType==6||userType==9||userType==10}">--%>
            <%--<li><a data-href="${ly }/global/check-list?flag=4" data-title="排查治理记录" href="javascript:void(0)">排查治理记录</a></li>--%>
        <%--</c:when>--%>
        <%--<c:when test="${userType==4}">--%>
            <%--<li><a data-href="${ly }/global/check-list3?flag=4" data-title="隐患排查记录" href="javascript:void(0)">隐患排查记录</a></li>--%>
            <%--<li><a data-href="${ly }/global/check-list4?flag=4" data-title="隐患治理记录" href="javascript:void(0)">隐患治理记录</a></li>--%>
            <%--<li><a data-href="${ly }/global/off-list" data-title="检查人员信息库" href="javascript:void(0)">检查人员信息库</a></li>--%>
        <%--</c:when>--%>
        <%--<c:when test="${userType==3}">--%>
            <%--<li><a data-href="${ly }/global/check-list?flag=4" data-title="排查治理记录" href="javascript:void(0)">排查治理记录</a></li>--%>
            <%--<li><a data-href="${ly }/global/off-list" data-title="检查人员信息库" href="javascript:void(0)">检查人员信息库</a></li>--%>
        <%--</c:when>--%>

        <ul>
        <%--data-href="${ly }/global/check-list?flag=1&status=2" data-title="排查治理记录"--%>
        <%--<c:choose>--%>
            <%--<c:when test="${userType==6||userType==9||userType==10}">--%>
                <%--<li><a data-href="${ly }/village/check-list?flag=1&status=2" data-title="隐患排查记录" href="javascript:void(0)">隐患排查记录</a></li>--%>
                <%--<li><a data-href="${ly }/global/check-list4?flag=4" data-title="隐患治理记录" href="javascript:void(0)">隐患治理记录</a></li>--%>
            <%--</c:when>--%>
            <%--<c:when test="${userType==4}">--%>
                <%--<li><a data-href="${ly }/village/check-list?flag=1&status=2" data-title="隐患排查记录" href="javascript:void(0)">隐患排查记录</a></li>--%>
                <%--<li><a data-href="${ly }/global/check-list4?flag=4" data-title="隐患治理记录" href="javascript:void(0)">隐患治理记录</a></li>--%>
                <%--<li><a data-href="${ly }/global/off-list" data-title="检查人员信息库" href="javascript:void(0)">检查人员信息库</a></li>--%>
            <%--</c:when>--%>
            <%--<c:when test="${userType==3}">--%>
                <%--<li><a data-href="${ly }/village/check-list?flag=1&status=2" data-title="隐患排查记录" href="javascript:void(0)">隐患排查记录</a></li>--%>
                <%--<li><a data-href="${ly} /global/check-list4?flag=4" data-title="隐患治理记录" href="javascript:void(0)">隐患治理记录</a></li>--%>
                <%--<li><a data-href="${ly }/global/off-list" data-title="检查人员信息库" href="javascript:void(0)">检查人员信息库</a></li>--%>
            <%--</c:when>--%>
        <%--</c:choose>--%>
        <c:choose>
            <c:when test="${userType==6||userType==9||userType==10}">
                <li><a data-href="${ly }/global/check-company?flag=1&status=2" data-title="隐患排查记录" href="javascript:void(0)">隐患排查记录</a></li>
                <li><a data-href="${ly }/global/hidden-company-list?flag=1&status=1" data-title="隐患治理记录" href="javascript:void(0)">隐患治理记录</a></li>
            </c:when>
            <c:when test="${userType==4}">
                <li><a data-href="${ly }/global/check-company?flag=1&status=2" data-title="隐患排查记录" href="javascript:void(0)">隐患排查记录</a></li>
                <li><a data-href="${ly }/global/hidden-company-list?flag=1&status=1" data-title="隐患治理记录" href="javascript:void(0)">隐患治理记录</a></li>
                <li><a data-href="${ly }/global/off-list" data-title="检查人员信息库" href="javascript:void(0)">检查人员信息库</a></li>
            </c:when>
            <c:when test="${userType==3}">
                <li><a data-href="${ly }/global/check-company?flag=1&status=2" data-title="隐患排查记录" href="javascript:void(0)">隐患排查记录</a></li>
                <li><a data-href="${ly }/global/hidden-company-list?flag=1&status=1" data-title="隐患治理记录" href="javascript:void(0)">隐患治理记录</a></li>
                <li><a data-href="${ly }/global/off-list" data-title="检查人员信息库" href="javascript:void(0)">检查人员信息库</a></li>
            </c:when>
        </c:choose>
        <li><a data-href="${ly }/global/pc-analysis?flag=1" data-title="排查数据分析" href="javascript:void(0)">排查数据分析</a></li>
        <li><a data-href="${ly }/global/yh-all-analysis?flag=1" data-title="隐患数据分析" href="javascript:void(0)">隐患数据分析</a></li>
        <li><a data-href="${ly }/global/zl-all-analysis" data-title="治理数据分析" href="javascript:void(0)">治理数据分析</a></li>


        </ul>
        </dd>
        </dl>
        <dl>
        <dt>
        <i class="Hui-iconfont">&#xe687;</i>风险研判公告
        <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>

        <ul>
        <li><a data-href="${ly }/global/study/promise-collect" data-title="承诺公告汇总" href="###">承诺公告汇总</a></li>
        </ul>
        </dd>
        </dl>
        <dl>
        <dt>
        <i class="Hui-iconfont">&#xe70c;</i>行政执法检查
        <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
        <ul>
        <c:choose>
            <c:when test="${userType==9}">
                <!--地级-->
                <li><a data-href="${ly }/global/check-list-cx?flag=2" data-title="执法检查" href="javascript:void(0)">执法检查</a></li>
                <%-- <li><a data-href="${ly }/village/check-add-cho?flag=2" data-title="执法检查" href="javascript:void(0)">执法检查</a></li> --%>
                <%-- <li><a data-href="${ly }/village/rectification-list?flag=2" data-title="执法复查" href="javascript:void(0)">执法复查</a></li> --%>
                <li><a data-href="${ly }/global/check-list?flag=2" data-title="执法记录" href="javascript:void(0)">执法记录</a></li>
                <li><a data-href="${ly }/global/danger/opinion-list?type=3&flag2=2" data-title="行政处罚" href="javascript:void(0)">行政处罚</a></li>
                <li><a data-href="${ly }/global/evaluate/lawDoc-list?flag=2" data-title="上传图片视频资料" href="javascript:void(0)">上传图片视频资料</a></li>
                <li><a data-href="${ly }/global/off-list" data-title="人员信息" href="javascript:void(0)">人员信息</a></li>

            </c:when>
            <c:when test="${userType==6}">
                <!--县市级,县级-->
                <li><a data-href="${ly }/global/check-list-cx?flag=2" data-title="执法检查" href="javascript:void(0)">执法检查</a></li>
                <%-- <li><a data-href="${ly }/village/rectification-list?flag=2" data-title="执法复查" href="javascript:void(0)">执法复查</a></li> --%>
                <li><a data-href="${ly }/global/check-list?flag=2" data-title="执法记录" href="javascript:void(0)">执法记录</a></li>
                <li><a data-href="${ly }/global/evaluate/lawDoc-list?flag=2" data-title="执法终端" href="javascript:void(0)">执法终端</a></li>
                <li><a data-href="${ly }/global/danger/opinion-list?type=3&flag2=2" data-title="行政处罚" href="javascript:void(0)">行政处罚</a></li>
                <li><a data-href="${ly }/global/off-list" data-title="人员信息" href="javascript:void(0)">人员信息</a></li>

            </c:when>
            <c:when test="${userType==3}">
                <!--镇级-->
                <li><a data-href="${ly }/global/check-list-cx?flag=2" data-title="执法检查" href="javascript:void(0)">执法检查</a></li>
                <%-- <li><a data-href="${ly }/village/rectification-list?flag=2" data-title="执法复查" href="javascript:void(0)">执法复查</a></li> --%>
                <li><a data-href="${ly }/global/check-list?flag=2" data-title="执法记录" href="javascript:void(0)">执法记录</a></li>
                <li><a data-href="${ly }/global/danger/opinion-list?type=3&flag2=2" data-title="行政处罚" href="javascript:void(0)">行政处罚</a></li>
                <li><a data-href="${ly }/global/evaluate/lawDoc-list?flag=2" data-title="上传图片视频资料" href="javascript:void(0)">上传图片视频资料</a></li>
                <li><a data-href="${ly }/global/off-list" data-title="人员信息" href="javascript:void(0)">人员信息</a></li>
            </c:when>
            <c:when test="${userType==4}">
                <!--村级-->
                <li><a data-href="${ly }/global/evaluate/lawDoc-list?flag=2" data-title="执法图片视频资料" href="javascript:void(0)">执法图片视频资料</a></li>





                <%--<li><a data-href="${ly }/global/zf-performance-industry?flag=1" data-title="chart1" href="javascript:void(0)">chart1</a></li>--%>
                <%--<li><a data-href="${ly }/global/zf-performance-industry?flag=2" data-title="chart1-2" href="javascript:void(0)">chart1-2</a></li>--%>
                <%--<li><a data-href="${ly }/global/zf-performance-industry?flag=3" data-title="chart1-3" href="javascript:void(0)">chart1-3</a></li>--%>
                <%--<li><a data-href="${ly }/global/zf-check-hidden-trouble?flag=1" data-title="chart2" href="javascript:void(0)">chart2-1</a></li>--%>
                <%--<li><a data-href="${ly }/global/zf-check-hidden-trouble?flag=2" data-title="chart2" href="javascript:void(0)">chart2-2</a></li>--%>
                <%--<li><a data-href="${ly }/global/zf-check-hidden-trouble?flag=3" data-title="chart2" href="javascript:void(0)">chart2-3</a></li>--%>
                <%--<li><a data-href="${ly }/global/zf-hidden-industry?flag=1" data-title="charts3-1-1" href="javascript:void(0)">chart3-1-1</a></li>--%>
                <%--<li><a data-href="${ly }/global/zf-hidden-industry?flag=2" data-title="charts3-1-2" href="javascript:void(0)">chart3-1-2</a></li>--%>
                <%--<li><a data-href="${ly }/global/zf-hidden-industry?flag=3" data-title="charts3-1-3" href="javascript:void(0)">chart3-1-3</a></li>--%>
                <%--<li><a data-href="${ly }/global/zf-hidden-trouble?flag=1" data-title="charts3-2-1" href="javascript:void(0)">chart3-2-1</a></li>--%>
                <%--<li><a data-href="${ly }/global/zf-hidden-trouble?flag=2" data-title="charts3-2-2" href="javascript:void(0)">chart3-2-2</a></li>--%>
                <%--<li><a data-href="${ly }/global/zf-hidden-trouble?flag=3" data-title="charts3-2-3" href="javascript:void(0)">chart3-2-3</a></li>--%>
                <%--<li><a data-href="${ly }/global/manage-hidden-company" data-title="charts4-1" href="javascript:void(0)">chart4-1</a></li>--%>
                <%--<li><a data-href="${ly }/global/manage-hidden-danger" data-title="charts4-2" href="javascript:void(0)">chart4-2</a></li>--%>
                <%--<li><a data-href="${ly }/global/manage-hidden-industry" data-title="charts4-3" href="javascript:void(0)">chart4-3</a></li>--%>

            </c:when>
            <c:when test="${userType==10}">
                <!--行业级-->
                <li><a data-href="${ly }/global/check-list-cx?flag=2" data-title="执法检查" href="javascript:void(0)">执法检查</a></li>
                <%-- <li><a data-href="${ly }/village/rectification-list?flag=2" data-title="执法复查" href="javascript:void(0)">执法复查</a></li> --%>
                <li><a data-href="${ly }/global/check-list?flag=2" data-title="执法记录" href="javascript:void(0)">执法记录</a></li>
                <li><a data-href="${ly }/global/danger/opinion-list?type=3&flag2=2" data-title="行政处罚" href="javascript:void(0)">行政处罚</a></li>
                <li><a data-href="${ly }/global/evaluate/lawDoc-list?flag=2" data-title="上传图片视频资料" href="javascript:void(0)">上传图片视频资料</a></li>
                <li><a data-href="${ly }/global/off-list" data-title="执法人员信息库" href="javascript:void(0)">执法人员信息库</a></li>
            </c:when></c:choose>
        <li><a data-href="${ly }/global/opinion-show?flag2=2" data-title="执法档案" href="javascript:void(0)">执法档案</a></li>
        </ul>
        </dd>
        </dl>
        <!-------------信息推送及接受------------------------->
        <dl>
        <dt>
        <i class="Hui-iconfont">&#xe61d;</i>信息推送互动
        <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
        <ul>
        <li><a data-href="${ly }/global/reviceMessageList" data-title="信息接收列表" href="javascript:void(0)">接收列表</a></li>
        <li><a data-href="${ly }/global/sendMessageList" data-title="信息发送列表" href="javascript:void(0)">发送列表</a></li>
        <li><a data-href="${ly }/global/addMessages" data-title="信息推送" href="javascript:void(0)">推送编辑</a></li>
        <li><a data-href="${ly }/global/groupIndex" data-title="群组设置" href="javascript:void(0)">群组设置</a></li>
        </ul>
        </dd>
        </dl>
        <dl>
        <dt>
        <a style="color:#fff;"  data-href="${ly }/global/tables/yjmanage_center"  data-title="应急救援中心" href="javascript:void(0)"><i class="Hui-iconfont">&#xe6c1;</i>应急救援中心</a>
        </dt>
        </dl>





        <%--<dl>--%>
        <%--<dt>--%>
        <%--<i class="Hui-iconfont">&#xe70c;</i>安全监察与监管--%>
        <%--<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>--%>
        <%--</dt>--%>
        <%--<dd>--%>
        <%--<ul>--%>
        <%--<li><a data-href="${ly }/global/company/company-list-zg?cisDanger=1" data-title="重大危险源" href="javascript:void(0)">重大危险源</a></li>--%>
        <%--<li><a data-href="${ly }/global/company/company-list-zg?danger=1" data-title="高危作业" onclick="javascript:void(0)">高危作业</a></li>--%>
        <%--<li><a data-href="${ly }/global/warning/sperson-list" data-title="持证上岗" href="javascript:void(0)">持证上岗</a></li>--%>
        <%--<li><a data-href="${ly }/global/warning/sequipment-list" data-title="特种设备检测" href="javascript:void(0)">特种设备检测</a></li>--%>
        <%--<li><a data-href="${ly }/global/warning/detection-list" data-title="职业卫生检测" href="javascript:void(0)">职业卫生检测</a></li>--%>
        <%--<li><a data-href="${ly }/global/warning/lightning-list" data-title="防雷防静电" href="javascript:void(0)">防雷防静电</a></li>--%>
        <%--<li><a data-href="${ly }/global/evaluate/evaluation-list" data-title="评价评估" href="javascript:void(0)">评价评估</a></li>--%>
        <%--<li><a data-href="${ly }/global/evaluate/zheng-list" data-title="各类许可证" href="javascript:void(0)">各类许可证</a></li>--%>
        <%--<li><a data-href="${ly }/company/tables/tab-yjlist" data-title="应急预案" href="javascript:void(0)">应急预案</a></li>--%>
        <%--</ul>--%>
        <%--</dd>--%>
        <%--</dl>--%>


        <dl>
        <%--<dt>--%>
        <%--<i class="Hui-iconfont">&#xe653;</i>领导关注点汇总--%>
        <%--<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>--%>
        <%--</dt>--%>
        <dd>
        <ul>
        <li><a data-href="${ly }/global/notice-list" data-title="一周监管动态" href="javascript:void(0)">一周监管动态</a></li>
        <li><a data-href="${ly }/global/danger-collect-guanzhu?isZhongDa=1" data-title="隐患排查治理数据" href="javascript:void(0)">隐患排查治理数据</a></li>
        <li><a data-href="${ly }/global/danger-collect-guanzhu?isZhongDa=2" data-title="重大事故隐患数据" href="javascript:void(0)">重大事故隐患数据</a></li>
        <li><a data-href="${ly }/global/danger-collect-guanzhu?isZhongDa=3" data-title="行政执法数据" href="javascript:void(0)">行政执法数据</a></li>
        </ul>
        </dd>
        </dl>








        <dl>
        <dt>
        <i class="Hui-iconfont">&#xe61d;</i>账号设置管理
        <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>

        <%--<c:if test="${userType==4||userType==10}">--%>
            <%--<div class="div_omenu">--%>
            <%--<div  class="div_otop">信息推送<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>--%>
            <%--<ul>--%>
            <%--<li><a data-href="${ly }/global/sendMessageList" data-title="信息发送列表" href="javascript:void(0)">发送列表</a></li>--%>
            <%--<li><a data-href="${ly }/global/reviceMessageList" data-title="信息接收列表" href="javascript:void(0)">接收列表</a></li>--%>
            <%--<li><a data-href="${ly }/global/addMessages" data-title="信息推送" href="javascript:void(0)">推送编辑</a></li>--%>
            <%--<li><a data-href="${ly }/global/groupIndex" data-title="群组设置" href="javascript:void(0)">群组设置</a></li>--%>
            <%--</ul>--%>
            <%--</div>--%>
        <%--</c:if>--%>
        <c:if test="${userType==6||userType==4||userType==3}">
            <div class="div_omenu">
            <div  class="div_otop">在线监测<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>
            <ul>
            <li><a data-href="${ly }/global/system/toUpdateWaterParams" data-title="水质五参数设置" href="javascript:void(0)">水质五参数设置</a></li>
            <li><a data-href="${ly }/village/to_monitor-list?flag=1" data-title="实时监控"
            href="javascript:void(0)">实时监控</a></li>
            </ul>
            </div>
        </c:if>
        <c:if test="${userType==3}">
            <div class="div_omenu">
            <div  class="div_otop">组织架构<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>
            <ul>
            <li><a data-href="${ly }/company/system/department-list" data-title="组织架构" href="javascript:void(0)">组织架构</a></li>
            <li><a data-href="${ly }/company/system/user-list" data-title="部门人员" href="javascript:void(0)">部门人员</a></li>
            </ul>
            </div>
        </c:if>
        <ul>
        <li><a data-href="${ly }/global/system/change-psw" data-title="修改密码" href="javascript:void(0)">修改密码</a></li>
        <li><a data-href="${ly }/company/system/change-userName" data-title="修改登录名" href="javascript:void(0)">修改登录名</a></li>
        <c:if test="${userType==4}">
            <li><a data-href="${ly }/back/test?url=/global/system/tuiSet" data-title="推送设置" href="javascript:void(0)">推送设置</a></li>
        </c:if>
        </ul>
        </dd>
        </dl>

        <dl>
        <dt>
        <a style="color:#fff" data-href="/global/Standard" href="javascript:void(0)" data-title="安全生产标准化"><i class="Hui-iconfont">&#xe687;</i>安全生产标准化</a>
        </dt>
        </dl>


        <dl>
        <dt>
        <i class="Hui-iconfont">&#xe687;</i>重大危险源长制
        <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
        <ul>
        <li><a data-href="${ly }/global/source/company-list" data-title="全部企业" onclick="javascript:void(0)">全部企业</a></li>
        <li><a data-href="${ly }/village/company/zdlist?hazard=1" data-title="在线监测" href="javascript:void(0)">在线检测</a></li>
        <li><a data-href="${ly }/global/source/company-map" data-title="企业分布" href="javascript:void(0)">企业分布</a></li>
        <%--        <li><a data-href="${ly }/global/greate-danger-collect" data-title="隐患排查治理" href="javascript:void(0)">隐患排查</a></li>--%>
        <li><a data-href="${ly }/global/source/danger-collect" data-title="隐患排查" href="javascript:void(0)">隐患排查</a></li>
            <li><a data-href="${ly }/village/to_monitor-list?flag=1" data-title="实时监控" href="javascript:void(0)">实时监控</a></li>
        <li><a data-href="${ly }/global/source/danger-chart" data-title="分类统计" href="javascript:void(0)">分类统计</a></li>
        </ul>
        </dd>
        </dl>

        </div>
        </aside>
        <script type="text/javascript" src="${ly }/js/plugins/websocket/swfobject.js"></script>
        <script type="text/javascript" src="${ly }/js/plugins/websocket/web_socket.js"></script>
        <script type="text/javascript">
        var websocket = null;
        var userno="${loginUserId}";
        //判断当前浏览器是否支持WebSocket
        WEB_SOCKET_SWF_LOCATION = "${ly }/js/plugins/websocket/WebSocketMain.swf";
        if(window.WebSocket){
        websocket = new WebSocket("ws://"+location.hostname+":"+location.port+"/village/websocket/"+userno);
        }
        else {
        //alert("当前版本不支持");
        }


        //连接发生错误的回调方法
        websocket.onerror = function () {
        //alert("error");
        //setMessageInnerHTML("WebSocket连接发生错误");
        };


        //连接成功建立的回调方法
        websocket.onopen = function () {
        //alert("lianjie");
        //setMessageInnerHTML("WebSocket连接成功");
        }


        //接收到消息的回调方法
        websocket.onmessage = function (event) {
        /*  $.ajax({
        url : "village/getDangerCount"
        type : 'post',
        success : function(data){
        if(undefined != data.zdTotal){
        if(data.zdTotal > 0){
        if(!$("#zd_font").hasClass("blink")){
        $("#zd_font").addClass("blink");
        }
        $("#zd_font").html(data.zdTotal);
        }else{
        $("#zd_font").removeClass("blink");
        $("#zd_font").html(0);
        }
        }
        if(undefined != data.gwTotal){
        if(data.gwTotal > 0){
        if(!$("#gw_font").hasClass("blink")){
        $("#gw_font").addClass("blink");
        }
        $("#gw_font").html(data.gwTotal);
        }else{
        $("#gw_font").removeClass("blink");
        $("#gw_font").html(0);
        }
        }
        }
        }); */
        }


        //连接关闭的回调方法
        websocket.onclose = function () {
        //setMessageInnerHTML("WebSocket连接关闭");
        //alert("close");
        }


        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function () {
        //closeWebSocket();
        }
        //关闭WebSocket连接
        function closeWebSocket() {
        websocket.close();
        }

        </script>
        </body>
        </html>