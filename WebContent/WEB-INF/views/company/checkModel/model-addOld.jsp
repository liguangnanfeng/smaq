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
    <meta name="keywords" content="风险分级管控 隐患排查治理智能化平台">
    <meta name="description" content="风险分级管控 隐患排查治理智能化平台">
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

        function nature(obj) {
            var cType = $(obj);
            //未选择
            if ('0' == cType.val()) {
                return null;
            } else if ('-1' == cType.val()) { // 基础检查 与高危类似
                $('#addContainer').html('');
                $(".addCh3").hide();
                $(".addCh1").css("display", 'block');






            }
            //现场检查 选择部门岗位
            else if ('-2' == cType.val()) {

                $('#addContainer').empty();
                $(".addCh3").hide();
                $(".addCh1").css("display", 'block');
                // 两套html页面，这一套不变


            }
            // 高危检查
            else {
                $('#addContainer').empty();
                $(".addCh1").hide();
                $(".addCh3").css("display", 'block');

            }
        }


        //请求现场数据 进行渲染
        function findPerson(obj) {
            var level1 = $(obj);
            var dom = $(obj).parent().parent().parent('.level1').next().find('.department2Id');
            dom.empty();
            dom.append('<option value="0">请选择岗位</option>')
            // var doc = document.getElementById("departmentId");
            params = {
                "depId": level1.val()
            }
            $.ajax({
                type: "POST",
                url: getRootPath() + '/village/selectDep',
                data: params,
                async: false,
                dataType: "json",
                success: function (result) {
                    $.each(result, function (index, item) {
                        var station = item.name;//岗位
                        var id = item.id;//岗位id
                        dom.append("<option value='" + station + "'>" + station + "</option>")
                    })
                },
                complete: function (XMLHttpRequest, textStatus) {
                    // layer.close(index);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("查询失败");
                }
            });
            $.ajax()
        }

        function findCheck(obj) {
            var level2 = $(obj);
            var dom2 = $(obj).parent().parent().parent('.level2').prev().find('.departmentId');

            var inspection = $(obj).parent().parent().parent('.level2').next().find('.project1Id');
            params2 = {
                "depId": dom2.val(),
                "sName": level2.val()
            }
            console.log(params)
            $.ajax({
                type: "POST",
                url: getRootPath() + '/village/findInspection',
                data: params2,
                async: false,
                dataType: "json",
                success: function (result) {
                    if (result.length > 0) {
                        console.log(result);
                        $(obj).parent().parent().parent('.level2').next().find('.project1Id').empty();
                        inspection.append('<option value="0">选择检查项目</option>')
                        $.each(result, function (index, item) {
                            var isp = item.level3;
                            var manuId = item.id;
                            <%--                            inspection.append("<p class='checkBoxOuter'><input type='checkbox' class='checkBox' name='checkBox' value='" + isp + "'/>" + isp + "</p>")--%>
                            inspection.append("<option value='" + isp + "'>" + isp + "</option>")
                        })
                    }

                },
                complete: function (XMLHttpRequest, textStatus) {
                    // layer.close(index);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("查询失败")
                }
            });

        }

        function findCheck1(obj) {
            var project1Id = $(obj);
            var depId = $(obj).parent().parent().parent().prev().prev().find('.departmentId').val();
            var gawei = $(obj).parent().parent().parent().prev().find('.department2Id').val();
            var inspection = $(obj).parent().parent().parent('.level3').next().find('.content1Id');
            inspection.empty();
            inspection.append('<option value="0">请选择检查内容</option>')
            params2 = {
                "level1": depId,
                "level2": gawei,
                "level3": project1Id.val(),
                "id": "",
                "uid": "",
                "type": "",
                "level4": "",
                "reference": "",
                "factors": "",
                "types": "",
                "flag": "",
                "gkcs": "",
                "gkzt": "",
                "files": "",
                "checkType": "",
                "industryId": "",
                "access_token": "",
                "sessionId": "",
            }
            $.ajax({
                type: "POST",
                url: getRootPath() + '/api/custom/check/A2022',
                data: JSON.stringify(params2),
                async: false,
                dataType: "json",
                contentType: "application/json",
                success: function (result) {
                    $.each(result.data, function (index, item) {
                        if (item.level4 != null) {
                            var isp = item.level4;
                            inspection.append("<option value='" + isp + "'>" + isp + "</option>")
                        }
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

        function saveCheckMenu() {
            alert("hello");
            alert("进入saveCheckMenu");
            var obj = document.getElementsByName("checkBox");
            check_val = [];
            for (c in obj) {
                if (obj[c].checked) {
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
                    window.location.href = "${ly }/company/model-list-cx?flag=1&type=1&template=1"
                },
                complete: function (XMLHttpRequest, textStatus) {
                    // layer.close(index);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("添加失败")
                }
            });

        }

        function saveCheckMenu() {
            var title = document.getElementById("title");
            var checkType = document.getElementById("checkType");
            var checkNature = document.getElementById("checkNature");

            var checkItemList = [];
            var cusCheckItemList = [];//自定义检查项
            for (let j = 1; j <= i; j++) {
                <%--                    var checkList = $('.checkedList' + j + '').find(".checkBox");--%>
                <%--                    console.log(checkList.length)--%>
                <%--                    var check_val = [];--%>
                <%--                    for (c in checkList) {--%>
                <%--                        if (checkList[c].checked) {--%>
                <%--                            check_val.push(checkList[c].value)--%>
                <%--                        }--%>
                <%--                    }--%>
                var dx = $('.project1Id').eq(j - 1).val();    //dingwei
                var gawei = $('.content1Id').eq(j - 1).val();
                var jcx = {
                    'bm': $('.addItem' + j + ' .department' + j + '').children('.departmentId').val(),
                    'gw': $('.addItem' + j + ' .post' + j + '').children('.department2Id').val(),
                    'dx': dx,
                    'cn': gawei,
                }
                if (checkNature.value == -2) {
                    if (jcx.bm && dx && dx != "0" && jcx.bm != "0") {
                        checkItemList.push(jcx);
                    }

                } else {
                    if (jcx.bm && jcx.gw) {
                        checkItemList.push(jcx);
                    }
                }


                var cusCheck = {
                    'bm': $('.cusAddItem' + j + ' .department' + j + '').children('.departmentId').val(),
                    'gw': $('.cusAddItem' + j + ' .post' + j + '').children('.department2Id').val(),
                    'project': $('#project' + j).val(),
                    'content': $('#content' + j).val(),
                }
                if (cusCheck.project) {
                    cusCheckItemList.push(cusCheck);
                }
            }
            var str = $('#checkType').find("option:selected").text(), time = '';
            if (str == '定期') {
                var tt = $('#dataTime').val()
                if (tt != '' && tt <= 7) {
                    time = tt
                } else {
                    alert('请填写定期时间，最大7天')
                }
            }
            var params3 = {
                "title": title.value,//检查表名称
                "checkType": checkType.value,//检查方式
                "checkNature": checkNature.value,//检查性质
                "checkItemList": checkItemList,//检查项 数组 单个对象 bm 部门 gw 项目 dx 检查项目
                "cusCheckItemList": cusCheckItemList,//自定义检查项 数组 单个对象 bm 部门 gw 项目 project 自定义检查项目 content自定义检查内容
                'time': time
            }
            // console.log(params3);
            // console.log('请求前');
            $.ajax({
                type: "POST",
                url: getRootPath() + '/village/saveCheckMenu2',
                data: JSON.stringify(params3),
                async: false,
                contentType: "application/json",
                dataType: "json",
                success: function (result) {
                    if (result.status == 0) {
                        alert('保存成功');
                    } else {
                        alert('保存失败');
                    }

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
        var type = '${type}';
        var flag = '${flag}';
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

        function time(v) {
            var str = $(v).find("option:selected").text()
            if (str == '定期') {
                $(".dq").css("display", "block");
            } else {
                $(".dq").css("display", "none");
            }
        }




    </script>
     <%--type==1挪出来的第四个检查项    --%>
    <%--<div class="col-xs-4 cl level4">--%>
        <%--<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>检查内容</label>--%>
        <%--<div class="formControls col-xs-8 col-sm-9">--%>
        <%--<span class="checkedList` + i + ` select-box inline">--%>
        <%--<select name="content1Id" class="content1Id" >--%>
        <%--<option value="0">选择检查内容</option>--%>
        <%--</select>--%>
        <%--</span>--%>
        <%--</div>--%>
    <%--</div>--%>

    <script>
        //        查找第二个选择的选择项

        function findSelect2(obj,index){
          console.log(index);
          var current = $(obj);
          var dom = $('.selectOne'+i);

        }
        //        查找第三个选择的选择项
        function findSelect3(obj,index){
            var current = $(obj);
            var dom = $('.selectOne'+i);
        }


        var i = 0;

        function addItem(type) {
            i++;
            if (1 == type) {      //2019/6/15  by qy

        var add1 = `<div class="addItem` + i + ` row" style="height: 50px" >
        <div class="col-xs-4 cl level1">
        <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>选择一级分类</label>
        <div class="department formControls col-xs-8 col-sm-9">

        <select name="departmentId" style="width:300px;height: 31px" class="selectOne`+i+` departmentId "   onChange="findSelect2(this,i)">
        <option value="0" >请选择一级分类</option>
        <option value="1" >请选择一级分类</option>
        <option value="2" >请选择一级分类</option>
        </select>

        </div>
        </div>

        <div class="col-xs-4 cl level2">
        <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>选择二级分类</label>
        <div class="post formControls col-xs-8 col-sm-9">


        <select name="department2Id" style="width:300px;height: 31px" class="selectTwo`+i+` department2Id" onchange="findSelect3(this,i)">
        <option value="0">请选择二级分类</option>
        </select>

        </div>
        </div>
        <div class="col-xs-4 cl level3">
        <label class="form-label col-xs-4 col-sm-4"><span class="c-red">*</span>检查项目</label>
        <div class="formControls col-xs-8 col-sm-8">

        <select name="project1Id"  style="width:300px;height: 31px" class="selectThree`+i+` project1Id" onchange="findCheck1(this,i)">
        <option value="0">选择检查项目</option>
        </select>

        </div>
        </div>

        </div>`;
                $('#addContainer').append(add1);

            } else if (2 == type) {

                var add1 = `<div class="cusAddItem` + i + ` row" >
        <div class="col-xs-3 cl level1">
        <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>请选择一级分类</label>
        <div class="department formControls col-xs-8 col-sm-9">
        <span class="department` + i + ` select-box inline">
        <select name="departmentId" class="departmentId" onChange="findPerson(this)">
        <option value="0">请选择一级分类</option>
        <c:forEach items="${map}" var="entry">
            <option value="${entry.value}">${entry.key}</option>
        </c:forEach>
        </select>
        </span>
        </div>
        </div>
        <div class="col-xs-3 cl level2">
        <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>请选择二级分类</label>
        <div class="post formControls col-xs-8 col-sm-9">
        <span class="post` + i + ` select-box inline">

        <select name="department2Id" class="department2Id" onchange="findCheck(this)">
        <option value="0">请选择二级分类</option>
        </select>
        </span>
        </div>
        </div>
        <div class="col-xs-3 cl level2">
        <div>
        <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>检查项目</label>
        <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="project` + i + `" class="input-text">
        </div>
        </div>
        </div>
        <div class="col-xs-3">
        <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>检查内容</label>
        <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="content` + i + `" class="input-text">
        </div>
        </div>
        </div>`;
                $('#addContainer').append(add1);
            } else if (3 == type) {
                console.log('ceshi');
                var add3 = `<div class="addItem` + i + ` row" >
        <div class="col-xs-6 cl level1">
        <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>检查项目 :</label>
        <div class="department formControls col-xs-8 col-sm-9">
        <span class="department` + i + ` select-box inline">
        <select name="departmentId" class="departmentId selectitem` + i + `" >
        <option value="0">检查项目 </option>

        </select>
        </span>
        </div>
        </div>

        <div class="col-xs-6 cl level2">
        <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>检查内容 :</label>
        <div class="post formControls col-xs-8 col-sm-9">
        <span class="post` + i + ` select-box inline">

        <select name="department2Id" class="department2Id zdyselectitem` + i + `" >
        <option value="0">检查内容</option>
        </select>
        </span>
        </div>
        </div>

        </div>`;
                $('#addContainer').append(add3);
                console.log("====================",getRootPath());

                //在这里渲染数据!!!
                var csval = $("#checkNature option:selected").val();
                console.log("csval:",csval);
                //第一个下拉框
                var select1 = document.querySelector('.selectitem' + i);
                //第二个下拉框
                var select2 = document.querySelector(".zdyselectitem" + i);
//                select1.options.length = 1;
//                select2.options.length = 1;
                //1.基础
                if (csval == -1) {
                    $.ajax({
                        type: "POST",
                        url: getRootPath() + '/village/select-all-level1',
                        data: {checkType:csval},
                        async: false,
                        contentType: "application/json",
                        dataType: "json",
                        success: function (result) {
                            console.log("基础数据调用成功");
                            var data = result.data;

                            for (var key in data) {
                                //这里是动态添加option到select里面
                                var opt = document.createElement("option");
                                opt.value = key;
                                opt.innerText = key;
                                select1.appendChild(opt);

                            }


//                            $('.selectitem' + i).on("change", function () {
//                                console.log("data", data);
//                                var valbase = select1.value;
//                                console.log('valbase', valbase);
//                                var data2 = data[valbase];
//                                //data2为第一个下拉框选中的数组长度
//                                console.log('data2', data2);
//                                //在这里需要做一次清
//                                console.log("我要开始清空了");
//                                var w = 0;
//                                // for (var n=1;n<=data2.length;n++){
//                                // w++;
//                                // console.log("第"+w+"次删除");
//                                // console.log( $(".zdyselectitem1").children().eq(n+1).remove());
//                                // $(".zdyselectitem1").children().eq(n+1).remove()
//                                // }
//                                select2.options.length = 1;
//                                for (var m = 0; m < data2.length; m++) {
//                                    var opt2 = document.createElement("option");
//                                    console.log(opt2);
//                                    opt2.innerText = data2[m].level2;
//                                    select2.appendChild(opt2);
//                                }
//                            });


                        },
                        complete: function (XMLHttpRequest, textStatus) {
                            // layer.close(index);
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            console.log("添加失败")
                        }
                    });

                }

                //3.高危
                if (csval != 0 && csval != -1 && csval != -2) {
                    var params3 = {
                        "id": csval
                    };

                    $.ajax({
                        type: "POST",
                        url: getRootPath() + '/api/custom/check/A2133',
                        data: JSON.stringify(params3),
                        async: false,
                        contentType: "application/json",
                        dataType: "json",
                        success: function (data) {
                            console.log("高危数据调用成功");
                            console.log('l am here', data);
                            data.map(function (item, index) {
                                var opt = document.createElement("option");
                                opt.value = item.industry_id;
                                opt.innerText = item.level1;
                                select1.appendChild(opt);

                            })


                        },
                        error: function (res) {

                            console.log("高危数据请求出错1");
                            console.log(res);
                        }
                    });
                    //监听高危第一次下拉框
                    $(".selectitem1").change(function () {
                        var csval2 = $(".selectitem1 option:selected").val();
                        console.log("我是change事件");
                        console.log(csval2);
                        var aaa = {
                            "industryId": csval2
                        };
                        //第二次请求高危数据
                        $.ajax({
                            type: "POST",
                            url: getRootPath() + '/api/custom/check/A214',
                            data: JSON.stringify(aaa),
                            async: false,
                            contentType: "application/json",
                            dataType: "json",
                            success: function (result) {
                                console.log("高危数据2调用成功");
                                var data = result.data;
                                console.log('data', data);
                                select2.options.length = 1;
                                for (var key in data) {//遍历对象
                                    console.log("key", data[key]);
                                    data[key].map(function (item, index) {//遍历数组
                                        console.log('level2:', item.level2);

                                        var opt2 = document.createElement("option");
                                        opt2.innerText = item.level2;
                                        select2.appendChild(opt2);
                                    })
                                }
                            },
                            error: function (res) {
                                console.log("高危数据请求出错2");
                            }
                        });


                    });


                }


            } else if (4 == type) {
                var add1 = `<div class="row">
        <div class="col-xs-6 cl level1">
        <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>检查项目 :</label>
        <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="project` + i + `" class="input-text">
        </div>
        </div>
        <div class="col-xs-6 cl level1">
        <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>检查内容 :</label>
        <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="content` + i + `"class="input-text">
        </div>
        </div>
        </div>`;
                $('#addContainer').append(add1);
            }

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
                <input type="text" id="title" style="width:350px" class="input-text"
                       maxlength="50" placeholder="根据检查需要填写或空缺"/>
            </div>
        </div>

        <%--        检查方式--%>
        <%--<div class="row cl">--%>
            <%--<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>请选择检查方式 :</label>--%>
            <%--<div class="formControls col-xs-8 col-sm-9">--%>
        <%--<span class="select-box inline">--%>

        <%--<select name="checkType" class="select" id="checkType" onchange="time(this)">--%>
        <%--<option value="1">日常</option>--%>
        <%--<option value="2">定期</option>--%>
        <%--<option value="3">临时</option>--%>
        <%--</select>--%>
        <%--</span>--%>

            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="row cl dq" style="display:none">--%>
            <%--<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>请填写定期时间 :</label>--%>
            <%--<div class="formControls col-xs-8 col-sm-9">--%>
                <%--<input type="number" min="0" max="7" name="user_date" id='dataTime'/>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--        检查性质--%>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>请选择检查性质 :</label>
            <div class="formControls col-xs-8 col-sm-9">
        <span class="select-box inline">

        <select name="checkNature" class="select" id="checkNature" onchange="nature(this)">
        <option value="0">请选择检查类别</option>
        <option value="-1">基础</option>
        <option value="-2">现场</option>
        <%--<c:if test="${danger!=null}">--%>
            <%--<c:forEach items="${danger}" var="entry">--%>
                <%--<option value="${entry.id}">${entry.name}</option>--%>
            <%--</c:forEach>--%>
        <%--</c:if>--%>
        <%--                       <script>--%>
        <%--                           window.onload=function () {--%>
        <%--                               var checknature=document.querySelector('#checkNature');--%>
        <%--                               --%>
        <%--                               console.log(checknature.value[enter.id]);--%>
        <%--                               console.log(${entry.id});--%>
        <%--                           }--%>
        <%--                       </script>--%>
        </select>
        </span>

            </div>
        </div>


        <%--部门--%>

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

        <%--<div class="row cl">--%>
        <%--<label class="form-label col-xs-4 col-sm-2">检查周期(天)：</label>--%>
        <%--<div class="formControls col-xs-8 col-sm-9">--%>
        <%--<input type="text" id="cycle" value="" style="width:350px" class="input-text required ll-numberbox"--%>
        <%--data-options="min:1,max:365"/>--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--<div class="row cl">--%>
        <%--<label class="form-label col-xs-4 col-sm-2">检查开始日期：</label>--%>
        <%--<div class="formControls col-xs-8 col-sm-9">--%>
        <%--<input type="text" id="nextTime" value=""--%>
        <%--onfocus="WdatePicker({dateFmt:'yyyy-MM-dd', minDate:'%y-%M-{%d+1}'})"--%>
        <%--class="input-text Wdate mb-5 mt-5" style="width:350px;"/>--%>
        <%--</div>--%>
        <%--</div>--%>
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

            <%--添加项的容器--%>


            <div id="addContainer" class="row"></div>


            <div class="addCh1 row cl" style="display: none">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2 mt-20">
                    <button onClick="addItem(1)" class="btn btn-primary radius" type="button"
                            style="padding: 0 70px;">
                        <i class="Hui-iconfont">&#xe632;</i>新增检查项
                    </button>
                    <button onClick="addItem(2)" class="btn btn-primary radius" type="button"
                            style="padding: 0 70px;">
                        <i class="Hui-iconfont">&#xe632;</i>新增自定义检查项
                    </button>
                </div>
            </div>


            <div class="addCh3 row cl" style="display: none">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2 mt-20">
                    <button onClick="addItem(1)" class="btn btn-primary radius" type="button"
                            style="padding: 0 70px;">
                        <i class="Hui-iconfont">&#xe632;</i>新增检查项
                    </button>
                    <button onClick="addItem(4)" class="btn btn-primary radius" type="button"
                            style="padding: 0 70px;">
                        <i class="Hui-iconfont">&#xe632;</i>新增自定义检查项
                    </button>
                </div>
            </div>


            <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2 mt-20">
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


<%--隐藏域--%>

<%--现场--%>
<div class="addItem" style="display:none">
    <div class="row cl level1">
        <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>请选择部门 :</label>
        <div class="formControls col-xs-8 col-sm-9">
        <span class="select-box inline">

        <select name="departmentId" class="select departmentId" onchange="findPerson(this)">
        <option value="0">请选择车间</option>
        <c:forEach items="${map}" var="entry">
            <option value="${entry.value}">${entry.key}</option>
        </c:forEach>

        </select>
        </span>
            <script>

            </script>
        </div>
    </div>

    <div class="addItem" style="display:none">
        <div class="row cl level1">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>请选择部门 :</label>
            <div class="formControls col-xs-8 col-sm-9">
        <span class="select-box inline">

        <select name="departmentId" class="select departmentId" onchange="findPerson2(this)">
        <option value="0">请选择车间</option>
        <c:forEach items="${map}" var="entry">
            <option value="${entry.value}">${entry.key}</option>
        </c:forEach>

        </select>
        </span>
                <script>

                </script>
            </div>
        </div>


        <div class="row cl level2">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>请选择岗位 :</label>
            <div class="formControls col-xs-8 col-sm-9">
        <span class="select-box inline">

        <select name="department2Id" class="select department2Id" onchange="findCheck(this)">
        <option value="0">请选择岗位</option>
        </select>
        </span>

            </div>
        </div>

        <div class="row cl level3">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>请选择检查项目 :</label>
            <div class="formControls col-xs-8 col-sm-9">
                <p class="inspection"></p>
                <%--<p id="xiangmu"><input type="checkbox" name="category" value="今日话题" />今日话题 </p>--%>
            </div>
        </div>

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
        industryId = '${ind1.id }';//基础管理';给默认值，隐藏
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