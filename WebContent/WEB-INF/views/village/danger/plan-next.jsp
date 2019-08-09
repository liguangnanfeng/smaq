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
            width: 380px;
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
            height: 32px;
            line-height: 32px;
        }

        body .radio-box {
            float: Left;
        }
    </style>
    <script type="text/javascript">
        function uploadpicture(obj) {
            window.itemId = $(obj).attr("data-item");
            //var memo = $(this).closest("td").find("div[name='item-memo']").html();
            var memo = $(obj).closest("td").prev().text();
            var file = $(obj).attr("data-file");
            $("#pic3").attr("src", file == "" ? "/images/zwtp.jpg" : file).attr("url", file);
            $("#memo").val(memo);
            $("#modal-plan").modal("show");
        }

        function uploadimg(obj) {
            var partimg = $(obj).closest("td").attr("data-partimg");
            window.partId = $(obj).closest("td").attr("data-partid");
            $("#pic4").attr("src", partimg == "" ? "/images/zwtp.jpg" : partimg).attr("url", partimg);
            $("#modal-img").modal("show");
        }
    </script>
</head>
<body>
<div class="page-container">
    <%-- <div class="div_pdetail">
     <button onClick="show_dialog('检查表打印', getRootPath()+'/company/plan-next-print?id=${check.id}')" class="btn btn-success radius" type="button" style="padding: 0 70px;">
          <i class="Hui-iconfont mr-10">&#xe652;</i>打印
        </button>
    </div> --%>
    <h2 class="text-c mt-10">${check.title}</h2>
    <div class="f-l div_pdetail mt-10">
        <%--<font>受检${check.flag == 1 ? '部门' : '单位'}：${check.depart }</font>
        <label>检查日期：<fmt:formatDate value="${now }" pattern="yyyy年MM月dd日"/></label>--%>
        <c:if test="${check.flag==1}">
            <c:if test="${check.depart=='全公司'}">
                <font>受检单位：${company.name }</font>
            </c:if>
            <c:if test="${check.depart!='全公司'}">
                <font>受检单位：${check.depart }</font>
            </c:if>
        </c:if>
        <c:if test="${check.flag!=1}">
            <font>受检单位：${company.name }</font>
        </c:if>

    </div>
    <script>
      console.log("1111");
    </script>
    <div class="f-l mt-20" style="width:100%">
        <table class="f-l table table-border table-bordered table-bg table-hover table-sort" id="table"
               style="display: none">
            <thead>
            <tr class="text-c">
                <%--          <th width="3%">序号</th>--%>
                <%--<th width="15%">检查部位</th>--%>
                <th width="10%">检查部位</th>
                <th width="10%">检查类型</th>
                <th width="10%">检查方式</th>
                <th width="10%">检查项目</th>
                <th width="30%">检查内容</th>
                <th width="12%">检查结果</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${partL }" var="part" varStatus="pi">
                <c:set var="x" value="0"/>
                <c:forEach items="${itemL }" var="ch">
                    <c:if test="${ch.partId == part.id}">
                        <c:set var="x" value="${x + 1}"/>
                    </c:if>
                </c:forEach>
                <tr>
                <%--<td class="text-c" rowspan="${x }">${pi.index + 1}</td>--%>
                <%-- <td class="text-c" rowspan="${x }" data-partid="${part.id }" data-partimg="${part.partImg }">${part.name }
                   <br><input class="btn btn-primary size-M radius mt-10" type="button" onclick="uploadimg(this)" value="上传图片"/>
                 </td>--%>
                <c:set var="y" value="0"/>
                <c:forEach items="${itemL}" var="ch">
                    <c:if test="${ch.partId == part.id}">
                        <c:if test="${y > 0}">
                            <tr>${fn:split(ch.levels,'/')}
                        </c:if>
                        <c:set var="item" value="${fn:split(ch.levels,'/') }"/>
                        <td class="text-l">${item[1]} &nbsp;</td>
                        <%--检查类型--%>
                        <td class="text-l">
                            <c:if test="${check.industryType==1}">基础检查</c:if>
                            <c:if test="${check.industryType==2}">现场检查</c:if>
                            <c:if test="${check.industryType==3}">高危检查</c:if>
                        </td>

                        <%--检查方式--%>
                        <td class="text-l">
                            <c:if test="${check.type==1}">日常检查</c:if>
                            <c:if test="${check.type==2}">定期检查</c:if>
                            <c:if test="${check.type==3}">季节检查</c:if>
                            <c:if test="${check.type==4}">其他检查</c:if>
                            <c:if test="${check.type==5}">综合检查</c:if>
                        </td>
                        <td class="text-l">
                              ${item[2]}
		</td>
                        <td class="text-l">${ch.content }</td>
                        <td class="text-c" data-base="${ch.status }">&nbsp;
                            <div class="radio-box">
                                <input type="radio" name="plan-radio-${ch.id }" data-part="${part.id }"
                                       data-item="${ch.id }" value="1" <c:if test="${ch.status == 1}">checked</c:if>>
                                <label>合格</label>
                            </div>
                            <div class="radio-box">
                                <input type="radio" value="2" data-file="" data-part="${part.id }"
                                       name="plan-radio-${ch.id }" data-item="${ch.id }" onClick="uploadpicture(this)"
                                       <c:if test="${ch.status == 2}">checked</c:if>>
                                <label>不合格</label>
                            </div>
                            <div name="item-memo" style="display: none;">${ch.memo }</div>
                        </td>
                        <c:if test="${ch.partId == part.id}">
                            </tr>
                        </c:if>
                        <c:set var="y" value="1"/>
                    </c:if>
                </c:forEach>
                </tr>

            </c:forEach>

            <tr>
                <td colspan="3">
                    <div style="float:left;width:100%;position: relative;min-height:150px;">
                        <%-- <c:if test="${check.flag == 3 && check.checkCompany eq '无锡市安泰安全技术服务有限公司'}">
                             <img alt="" src="${ly }/images/zhang.png"
                                  style="position: absolute;bottom:10px;right:10px;height:130px;"/>
                         </c:if>--%>
                        <c:if test="${check.flag > 1}">
                            <div class="div_pleft  mt-10 mb-10">检查部门/单位：</div>
                            <div class="div_pright  mt-10 mb-10">
                                <input type="text" style="width:150px" id="checkCompany" value="${check.checkCompany }"
                                       class="input-text" maxlength="50"/>
                            </div>
                            <br/>
                        </c:if>
                        <div class="div_pleft  mt-10 mb-10">检查人员签字：</div>
                        <div class="div_pright  mt-10 mb-10">
                            <input type="text" style="width:150px" id="cheker" value="${check.cheker }"
                                   class="input-text" maxlength="50" oninput="checkerChange(this)"/>
                            <c:if test="${!empty jcL && (check.flag == '2' || check.flag == '4')}">
                                <button type="button" onclick='$("#jfry").modal("show");'
                                        class="btn btn-primary radius">从执法人员库中选择
                                </button>
                                <script type="text/javascript">
                                    function save_zfry() {
                                        var s = '';
                                        $(":checkbox:checked").each(function (i, item) {
                                            var v = $(this).val();
                                            if (s != '') {
                                                s += ",";
                                            }
                                            s += v;
                                        })
                                        if (s == '') {
                                            alert("请选择执法人员");
                                            return false;
                                        }
                                        $("#cheker").val(s);
                                        $('#jfry').modal("hide");
                                    }
                                </script>
                            </c:if>
                        </div>
                    </div>
                </td>
                <td colspan="3">
                    <div style="float:left;width:100%;min-height:150px;">
                        <div class="div_pleft  mt-10 mb-10">受检部门负责人签字：</div>
                        <div class="div_pright  mt-10 mb-10">
                            <input type="text" style="width:150px" id="dapartContact" value="${check.dapartContact }"
                                   class="input-text" maxlength="50"/>
                        </div>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="row cl" id="baocun" style="display: none">
        <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2  mt-20">
            <button onClick="article_save_submit()" class="btn btn-primary radius" type="button"
                    style="padding: 0 70px;">
                <i class="Hui-iconfont">&#xe632;</i>保存
            </button>
        </div>
    </div>
</div>

<!-- 弹窗隐患描述 -->
<div id="modal-plan" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header">
                <h3 class="modal-title">隐患信息</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body">
                <div class="row cl">
                    <label class="form-label col-xs-4 col-sm-2" style="width:20%;text-align:right;">隐患描述：</label>
                    <div class="formControls col-xs-8 col-sm-9" style="width:70%;">
                        <textarea id="memo" class="textarea txtarea_sq" style="width:350px;"></textarea>
                    </div>
                </div>
                <div class="row cl mt-15">
                    <label class="form-label col-xs-4 col-sm-2" style="width:20%;text-align:right;">隐患图片：</label>
                    <div class="formControls col-xs-8 col-sm-9" style="width:70%;">
                        <img id="pic3" src="${ly }/images/zwtp.jpg" style="width:200px;min-height:200px;" url=""/>
                        <a href="javascript:void(0);" onclick="img_upload('pic3', null)"
                           class="btn btn-primary radius upload-btn mt-10"><i class="Hui-iconfont">&#xe642;</i> 上传图片</a>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" onclick="upload()">确定</button>
                <button class="btn" data-dismiss="modal" onclick="esc_()">关闭</button>
            </div>
        </div>
    </div>
</div>

<!-- 弹窗图片 -->
<div id="modal-img" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header">
                <h3 class="modal-title">上传图片</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body">
                <div class="row cl mt-15">
                    <label class="form-label col-xs-4 col-sm-2" style="width:20%;text-align:right;">上传图片：</label>
                    <div class="formControls col-xs-8 col-sm-9" style="width:70%;">
                        <img id="pic4" src="${ly }/images/zwtp.jpg" style="width:300px;min-height:300px;" url=""/>
                        <a href="javascript:void(0);" onclick="img_upload('pic4', null)"
                           class="btn btn-primary radius upload-btn mt-10"><i class="Hui-iconfont">&#xe642;</i> 上传图片</a>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" onclick="upload_part()">确定</button>
                <button class="btn" data-dismiss="modal" onclick="esc_()">关闭</button>
            </div>
        </div>
    </div>
</div>

<c:if test="${!empty jcL && (check.flag == '2' || check.flag == '4')}">
    <div id="jfry" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content radius">
                <div class="modal-header">
                    <h3 class="modal-title">选择检查人</h3>
                    <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
                </div>
                <div class="modal-body">
                    <div class="row cl">
                        <c:forEach items="${jcL }" var="be">
                            <div class="check-box">
                                <input type="checkbox" name="jfry" value="${be.name }">
                                <label>${be.name }</label>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" onclick="save_zfry();">确定</button>
                    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
                </div>
            </div>
        </div>
    </div>
</c:if>

</body>
<script type="text/javascript">
    $(function () {
        var b = null, l1 = '', c = 1;
        var b2 = null, l2 = '', c2 = 1;
        var b3 = null, l3 = '', c3 = 1;
        $("tbody tr").each(function () {
            var td = $(this).children("td").eq(0);
            var td2 = $(this).children("td").eq(1);
            var td3 = $(this).children("td").eq(2);
            var l1_ = td.text();
            var l2_ = td2.text();
            var l3_ = td3.text();
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
                    } else {
                        l3 = l3_;
                        if (b3 != null) {
                            b3.attr("rowspan", c3);
                            c3 = 1;
                        }
                        b3 = td3;

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
        $('#table').css('display', 'block');
        $('#baocun').css('display', 'block');
    })


</script>

<script type="text/javascript">

    function checkerChange(obj) {
        var val = $(obj).val();
        var text = '检查人员 : ' + val;
        $('#my_checker').text(text);
    }

    var checkId = ${check.id};
    var flag = ${check.flag};

    function upload() {
        if ($("#memo").val() == '') {
            layer.alert("隐患描述不能为空", function (i) {
                layer.close(i);
                $("#memo").focus();
            });
            return false;
        }
        $(":radio[name='plan-radio-" + itemId + "'][value='2']").attr("data-file", $("#pic3").attr("url"));
        $("#modal-plan").modal("hide");
        $.post(getRootPath() + "/company/check-item-save", {
            id: itemId,
            files: $("#pic3").attr("url"),
            memo: $("#memo").val()
        }, function (result) {

        })
    }

    function upload_part() {
        $("td[data-partid='" + partId + "']").attr("data-partimg", $("#pic4").attr("url"));
        $("#modal-img").modal("hide");
        $.post(getRootPath() + "/company/check-part-save", {
            id: partId,
            partImg: $("#pic4").attr("url")
        }, function (result) {

        })
    }

    $(function () {
        $(":radio").click(function () {
            var this_ = $(this);
            var base = this_.closest("td").attr("data-base");
            var item_ = this_.attr("data-item");
            var status = this_.val();
            if (status == base) {
                return false;
            }
            $.post(getRootPath() + "/company/check-item-save", {
                id: item_,
                status: status
            }, function (result) {
                this_.closest("td").attr("data-base", status);
            })
        })
    })
    /*保存检查记录*/
    function article_save_submit() {
        // 判断合格不合格记录是否为空失敗,只能用来判断在数据库中数据中是否有数据
        var count11 =0;
        $('input[type=radio]').each(function(){//获取所有name=choice2的值
                if(this.checked){
                    count11++;
                }

        });

        if(count11 != ${fn:length(itemL)}){
            layer.alert("选项不能为空");
            return false;
        }

        if ($("#cheker").val() == '') {
            layer.alert("检查人不能为空");
            return false;
        }
        if ($("#checkCompany").length > 0) {
            if ($("#checkCompany").val() == '') {
                layer.alert("检查部门/单位不能为空");
                return false;
            }
        }
        if ($("#dapartContact").val() == '') {
            layer.alert("被检查负责人不能为空");
            return false;
        }
        var inx = layer.load();
        $.post(getRootPath() + "/company/check-save2", {
            id: checkId,
            cheker: $("#cheker").val(),
            checkCompany: $("#checkCompany").val(),
            dapartContact: $("#dapartContact").val(),
            status: 2
        }, function (result) {
            layer.close(inx);
            if (result.status == '1') {
                layer.alert(result.map.message);
            } else {
                top.ref_ifm();


                var x = "企业自查";
                if (flag == 2) {
                    x = "行政检查";
                }
                if (flag == 3) {
                    x = "部门抽查";
                }
                if (flag == 1) {//自查
                    var l_flag = parseInt(sessionStorage.getItem('flag'));
                    var l_dmname =sessionStorage.getItem('dmname').replace(/\s*/g,"");
                    var l_industryType = parseInt(sessionStorage.getItem('industryType'));
                    if ($(":radio[value='2']:checked").length > 0) {
                        top.show_tab("治理意见表_" + checkId, '/village/check-rectification?flag=' + flag + '&id=' + checkId+'&departName=${departName}');
                    } else {
                        //var url = '${ly}/company/check-list-szss?dmName='+l_dmname+'&flag='+l_flag+'&industryType='+l_industryType;
                        var url ="/company/check-detail?flag=1&id=${check.id}&number=0";
                        top.show_tab(x, url);
                        }
                } else {
                    //新版跳转
                    if ($(":radio[value='2']:checked").length > 0) {
                        // top.show_tab("治理意见表_" + checkId, '/village/check-rectification?flag=' + flag + '&id=' + checkId);
                        top.show_tab("检查详情" + checkId, '/company/check-detail?flag=' + flag + '&id=' + checkId);
                    } else {
                        var url = '${ly }/company/model-list-cx2?flag='+flag+'&type=1&template=2';
                        top.show_tab(x, url);
                    }

                }
            }
        })
    }
</script>
</html>