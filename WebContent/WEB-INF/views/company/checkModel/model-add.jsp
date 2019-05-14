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

        .div_model {
            float: left;
            width: 80%;
            margin-left: 10%
        }

        body .form .row {
            margin-top: 20px;
        }

        .radio-lx {
            display: inline-block;
            cursor: pointer;
            height: 50px;
            font-size: 14px;
            line-height: 50px;
            margin-bottom: 10px;
            padding: 0 35px;
            margin-right: 30px;
            background: #f5f5f5;
            border-radius: 5px;
        }

        .radio-lxxz {
            background: #D9EDF7;
            color: #31708F
        }
    </style>
    <script>
        function findPerson() {
            $("#department2Id option:gt(0)").remove();
            var doc = document.getElementById("departmentId");
            obj = {
                "depId": doc.value
            }
            $.ajax({
                type: "POST",
                url: getRootPath() + '/village/selectDep',
                data: obj,
                async: false,
                dataType: "json",
                success: function (result) {
                    $.each(result, function (index, item) {
                        var station = item.name;//岗位
                        var id = item.id;//岗位id
                        $("#department2Id").append("<option value='"+station+"'>" + station + "</option>")
                    })
                },
                complete: function (XMLHttpRequest, textStatus) {
                    // layer.close(index);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("查询失败")
                }
            });
        }

        function findCheck() {
            var doc = document.getElementById("departmentId");
            var doc2 = document.getElementById("department2Id");
            obj = {
                "depId": doc.value,
                "sName": doc2.value
            }
            $.ajax({
                type: "POST",
                url: getRootPath() + '/village/findInspection',
                data: obj,
                async: false,
                dataType: "json",
                success: function (result) {
                    $.each(result, function (index, item) {
                        var isp = item.level3;
                        var manuId = item.id;
                        $("#inspection").append("<p><input type='checkbox' id='checkBox' name='checkBox' value='"+manuId+"'/>"+isp+"</p>")
                    })
                },
                complete: function (XMLHttpRequest, textStatus) {
                    // layer.close(index);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("查询失败")
                }
            });

        }

        function saveCheckMenu(){
            alert("进入saveCheckMenu");
            var obj = document.getElementsByName("checkBox");
            check_val = [];
            for(c in obj){
                if(obj[c].checked){
                    check_val.push(obj[c].value)
                }
            }
            console.log(obj)
            console.log(check_val)
            var title = document.getElementById("title");
            var doc = document.getElementById("departmentId");
            var doc2 = document.getElementById("department2Id");
            var cycle = document.getElementById("cycle");
            var nextTime = document.getElementById("nextTime");
            // var checkType = document.getElementById("checkType");
            // var checkNature = document.getElementById("checkNature");
            var params = {
                "title": title.value,
                "depId": doc.value,
                "sName": doc2.value,
                "checkVal": JSON.stringify(check_val),
                "cycle": cycle.value,
                "nextTime": nextTime.value,
                // "checkType": checkType.value,
                // "checkNature":checkNature.value
            }
            $.ajax({
                type: "POST",
                url: getRootPath() + '/village/saveCheckMenu',
                data: params,
                async: false,
                dataType: "json",
                success: function () {
                    alert("添加成功");
                },
                complete: function (XMLHttpRequest, textStatus) {
                    // layer.close(index);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("添加失败")
                }
            });

        }
    </script>
    <script type="text/javascript">
        var modelId = '${model.id}';
        //alert("modelId="+modelId);//都是null
        var type = ${type};
        var flag = ${flag};
        var industryId = '${model.industryId}', industryType = '${model.industryType}';
        $(function () {
            if (industryId != "" && industryType != "") {
                $(".radio-lx[type='" + industryType + "']").addClass("radio-lxxz");
            }
            $(".div_fold").on("click", ".item h4", function () {
                var info = $(this).closest(".item").find(".info");
                if (info.is(":hidden")) {
                    info.show();
                    $(this).find("b").html("-");
                } else {
                    info.hide();
                    $(this).find("b").html("+");
                }
            });
        });

        //设置检查类型
        function indu_(i, t) {
            industryId = i;
            industryType = t;
            $(".radio-lx").removeClass("radio-lxxz");
            $(".radio-lx[type='" + t + "']").addClass("radio-lxxz");
        }


    </script>
</head>
<body>
<nav class="breadcrumb">
    <span>添加检查表</span>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
</nav>
<div class="page-container">
    <form class="form form-horizontal" id="form">
        <%-- <div class="row cl">
          <img alt="" src="${ly }/images/danger.jpg" style="margin-left:10%;height:120px;"/>
        </div> --%>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">检查表名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" id="title"  style="width:350px" class="input-text"
                       maxlength="50" placeholder="根据检查需要填写或空缺"/>
            </div>
        </div>



            <%--检查方式--%>
            <%--<div class="row cl">--%>
                <%--<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>请选择检查方式 :</label>--%>
                <%--<div class="formControls col-xs-8 col-sm-9">--%>
            <%--<span class="select-box inline">--%>

                   <%--<select name="checkType" class="select" id="checkType" >--%>
                       <%--<option value="1">日常</option>--%>
                       <%--<option value="2">定期</option>--%>
                       <%--<option value="3">临时</option>--%>
                  <%--</select>--%>
            <%--</span>--%>

                <%--</div>--%>
            <%--</div>--%>

            <%--检查性质--%>
            <%--<div class="row cl">--%>
                <%--<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>请选择检查性质 :</label>--%>
                <%--<div class="formControls col-xs-8 col-sm-9">--%>
            <%--<span class="select-box inline">--%>

                   <%--<select name="checkNature" class="select" id="checkNature">--%>
                       <%--<option value="1">基础</option>--%>
                       <%--<option value="2">现场</option>--%>
                       <%--<option value="3">高危行业</option>--%>
                  <%--</select>--%>
            <%--</span>--%>

                <%--</div>--%>
            <%--</div>--%>


        <%--部门--%>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>请选择部门 :</label>
            <div class="formControls col-xs-8 col-sm-9">
            <span class="select-box inline">

                   <select name="departmentId" class="select" id="departmentId" onChange="findPerson()">
                            <option value="0">请选择车间</option>
                        <c:forEach items="${map}" var="entry">
                            <option value="${entry.value}">${entry.key}</option>
                        </c:forEach>

                  </select>
            </span>

            </div>
        </div>
        <%--岗位--%>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>请选择岗位 :</label>
            <div class="formControls col-xs-8 col-sm-9">
            <span class="select-box inline">

                   <select name="department2Id" class="select" id="department2Id" onchange="findCheck()">
                             <option value="0">请选择岗位</option>
                  </select>
            </span>

            </div>
        </div>




            <!-- 检查项目 -->

            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>请选择检查项目 :</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <p id="inspection"></p>
                    <%--<p id="xiangmu"><input type="checkbox" name="category" value="今日话题" />今日话题 </p>--%>
                </div>
            </div>

        <div class="row cl" style="display: none;">
            <label class="form-label col-xs-4 col-sm-2" style="line-height:50px;">检查类型：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <div class="radio-lx" onclick="indu_(${ind1.id },1)" type="1">基础管理</div>
                <c:forEach items="${ind2L }" var="be">
                    <div class="radio-lx" onclick="indu_(${be.id },2)" type="2">现场管理-${be.name }</div>
                </c:forEach>
                <c:forEach items="${ind3L }" var="be">
                    <div class="radio-lx" onclick="indu_(${be.id },3)" type="3">高危作业-${be.name }</div>
                </c:forEach>
            </div>
        </div>

        <%--<div class="row cl">--%>
            <%--<label class="form-label col-xs-4 col-sm-2">自动预警设置：</label>--%>
            <%--<div class="formControls col-xs-8 col-sm-9">--%>
                <%--<input type="radio" name="open" value="1" <c:if test="${model.open != 0}">checked="checked"</c:if>/>开启--%>
                <%--<input type="radio" name="open" value="0" class="ml-20"--%>
                       <%--<c:if test="${model.open == 0}">checked="checked"</c:if>/>关闭--%>
                <%--<span class="ml-20">开启后，逾期不填写检查记录，系统自动预警！</span>--%>
            <%--</div>--%>
        <%--</div>--%>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">检查周期(天)：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" id="cycle" value="" style="width:350px" class="input-text required ll-numberbox"
                       data-options="min:1,max:365"/>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">检查开始日期：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" id="nextTime" value=""
                       onfocus="WdatePicker({dateFmt:'yyyy-MM-dd', minDate:'%y-%M-{%d+1}'})"
                       class="input-text Wdate mb-5 mt-5" style="width:350px;"/>
            </div>
        </div>
        <%-- <div class="row cl">
          <label class="form-label col-xs-4 col-sm-2">备注：</label>
          <div class="formControls col-xs-8 col-sm-9">
            <input type="text" id="memo" value="${model.memo }" style="width:350px" class="input-text" maxlength="500"/>
          </div>
        </div> --%>
        <div class="row cl">
            <%--<label class="form-label col-xs-4 col-sm-2">岗位/部位：</label>--%>
            <%--<div class="formControls col-xs-8 col-sm-9">--%>
                <%--<input type="text" id="jcbw" value="${name}" style="width:350px" class="input-text"/>--%>
            <%--</div>--%>
            <div class="div_model mt-20">
                <ul class="div_fold">
                    <li class="item" data-n="">
                        <%--<div class="title_item">--%>
                            <%--<h4><b>+</b></h4>--%>
                            <%--<!----%>
                            <%--<a class="btn_ejc" style="text-decoration:none" onclick="part_edit(this)" href="javascript:;" title="编辑检查项">编辑检查项</a>--%>
                            <%--<a class="btn_djc" style="text-decoration:none" onclick="part_del(this)" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe609;</i></a>--%>
                              <%---->--%>
                        <%--</div>--%>
                        <div class="info">
                            <table class="table table-border table-bordered table-bg table-hover table-sort">
                                <thead>
                                <tr class="text-c">
                                    <th width="5%">序号</th>
                                    <th width="80%">检查项目</th>
                                    <th width="15%">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <!-- 循环检查项目-->
                                <c:set value="0" var="x"/>
                                <c:forEach items="${items}" var="l">

                                    <c:set value="${x + 1}" var="x"/>
                                    <tr class="text-c" data-lid="${l.dmid }">
                                        <!--fix a bug id->dmid zhangcl 2018.12.13-->
                                        <td>${x}</td>
                                        <td>${l.level1 }/${l.level2 }<c:if
                                                test="${!empty l.level3}">/${l.level3 }</c:if></td>
                                        <td>
                                            <a style="text-decoration:none" onclick="del(this)" href="javascript:;"
                                               title="删除">删除</a>
                                        </td>
                                    </tr>

                                </c:forEach>
                                <!-- 循环结束 -->
                                </tbody>
                            </table>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2  mt-20">
                    <button onClick="saveCheckMenu()" class="btn btn-primary radius" type="button"
                            style="padding: 0 70px;">
                        <i class="Hui-iconfont">&#xe632;</i>保存检查表
                    </button>
                    <%--<button onClick="giveup_save_submit()" class="btn btn-primary radius" type="button"--%>
                            <%--style="padding: 0 70px;">--%>
                        <%--放弃添加--%>
                    <%--</button>--%>
                </div>
            </div>
        </div>
    </form>
</div>

</body>
<script type="text/javascript">

    // 获取名称
    var departmentName = $("#departmentId").val();


    $("#cycle").val(1);
    var day = new Date();
    day.setTime(day.getTime() + 24 * 60 * 60 * 1000);
    var s = day.getFullYear() + "-" + (day.getMonth() + 1) + "-" + day.getDate();
    $("#nextTime").val(s);//默认明天

    function article_save_submit_1() {
        //alert("model-add3.jsp");
        industryId = ${ind1.id };//'基础管理';给默认值，隐藏
        industryType = 1;
        if (industryId == '') {
            layer.alert("请选择检查类型");
            return false;
        }

        if (!$("#jcbw").val()) {
            layer.alert("请添加检查部位");
            return false;
        }

        var obj = new Object();

        var model = new Object();
        model.id = modelId;
        model.title = $("#title").val();//检查表名称
        model.type = type;
        model.flag = flag;

        model.open = $(":radio:checked").val();
        model.cycle = $("#cycle").val();
        model.nextTime = $("#nextTime").val();
        //alert(model.open);

        model.part = $("#part").val();//受检部门单位
        model.industryId = industryId;
        model.industryType = industryType;
        model.memo = $("#memo").val();//备注
        model.del = 0;
        if (model.open == 1) {
            if (model.cycle == '') {
                layer.alert("请输入报表生成周期")
                return false;
            }
            if (model.nextTime == '') {
                layer.alert("请选择下次报表自动创建时间")
                return false;
            }
        }
        obj.model = model;
        var index = layer.load();
        var partList = new Array();
        $(".div_fold li").each(function () {
            var levels = '';
            $(this).find("tbody").children("tr").each(function (i, item) {
                var i = $(item).attr("data-lid");
                levels = levels == '' ? i : levels + "," + i;
            })

            var part = new Object();
            part.name = $("#jcbw").val();
            part.levels = levels;
            //alert("levels:"+levels);
            partList.push(part);
        })

        obj.partList = partList;
        //alert(JSON.stringify(obj))
        $.ajax({
            type: "POST",
            url: getRootPath() + '/company/model-save',
            data: JSON.stringify(obj),
            async: false,
            dataType: "json",
            contentType: 'application/json',
            success: function (result) {
                if (result.status == '0') {
//show_tab("检查表", "/company/model-list-cx?flag=" + flag + (flag == 1 ? "&type=" + type : ""));
                    window.location.href = '${ly }/company/safety-system/risk-list';

                } else {
                    layer.alert(result.map.message);
                }
            },
            complete: function (XMLHttpRequest, textStatus) {
                layer.close(index);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("保存失败")
            }
        });
    }

    function giveup_save_submit() {
        window.location.href = "/company/safety-system/risk-list";
    }

    /* //部门单个检查项删除
     function del(obj) {
         var tbody = $(obj).closest("tbody");
         $(obj).closest("tr").remove();
         var trList = tbody.children("tr");
         $.each(trList, function(i, item) {
             $(this).children("td").first().text(i + 1);
         })

         if(trList.length == 0) {
             tbody.closest("li").remove();
         }
     }*/




</script>
</html>