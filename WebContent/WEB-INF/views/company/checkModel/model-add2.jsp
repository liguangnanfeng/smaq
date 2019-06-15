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

    <%@ include file="/WEB-INF/inc/kindeditor.inc" %>
    <%@ include file="/WEB-INF/inc/back-header.inc" %>
    <title>风险分级管控 隐患排查治理智能化平台</title>
    <meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
    <meta name="description" content="风险分级管控   隐患排查治理智能化平台">
</head>
<script type="text/javascript">

    window.onload = function () {

        //选择检查方式
        $("#dptitle").change(function () {
            //就是市区焦点的时候
            var dptitle = $("#dptitle option:selected").val();
            if (dptitle != null && dptitle == 2) {
                console.log(dptitle);
                $('#days').css('display', 'block');
            } else {
                $('#days').css('display', 'none');
            }
        });

        //选择基础还是现场检查
        $("#type").change(function () {
            var type = $("#type option:selected").val(); // 检查类型
            // 去数据库查询数据
            $.ajax({
                type: "POST",
                url: getRootPath() + '/village/findItemAll',
                data: {checkType: type},
                success: function (list) {
                    if (list != null && list.length>=1&&list!=[null]) {
                        console.log(list);
                        var disId = 0;
                        var disName = "请选择车间";
                        var opt ='';
                        $("#dpid1").append(opt).empty();
                        opt = "<option value='" +disId + "'>" + disName + "</option>";

                        for(var i = 0; i < list.length; i++) {
                             disId = list[i].dmid;
                             disName = list[i].level1;
                             opt += "<option value='" +disName + "'>" + disName + "</option> ";
                        }
                        $("#dpid1").append(opt);
                        $('#JiChubm').css('display', 'block');
                    } else {
                        alert('查无此数据');
                    }
                },
            });

        });

        // 调用这个方法的话,进行赋值
        function save_btn() {
            console.log($("#addpw").val())
            $("#win-add").modal("hide");
        }
    }

</script>
<body>
<nav class="breadcrumb">
    <%-- <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
    <span class="c-gray en">&gt;</span> <span>${(session_user.userType == 3 || session_user.userType == 4) ? '隐患排查治理系统' : '、行政执法系统'}</span>
    <span class="c-gray en">&gt;</span> <span>${(session_user.userType == 3 || session_user.userType == 4) ? '检查人员信息库' : '执法人员管理'}</span> --%>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
</nav>

<form class="form form-horizontal" id="form">
    <div class="page-container">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span
                    class="c-red">*</span>检查方式：</label>
            <div class="formControls col-xs-8 col-sm-9">
             <span class="select-box inline">
                <select id="dptitle" name="dpid" class="select" value="" style="width:300px">
                    <option value="1">日常</option>
                    <option value="2">定时</option>
                    <option value="3">季节</option>
                    <option value="4">其他</option>
                    <option value="5">综合</option>
                </select>
              </span>
            </div>
        </div>

        <%--显示一个选择天数的框--%>
        <div class="row cl dq" style="display: none" id="days">
            <label class="form-label col-xs-4 col-sm-2"><span
                    class="c-red">*</span>请填写定期时间 :
            </label>
            <div class="formControls col-xs-8 col-sm-9">
                <input id="times" class="input-text" type="number" min="0" max="7" style="width: 150px"/>
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span
                    class="c-red">*</span>检查类型：</label>
            <div class="formControls col-xs-8 col-sm-9">
             <span class="select-box inline">
                <select id="type" name="type" class="select" value="" style="width:300px">
                    <option value="0">请选择</option>
                    <option value="-1">基础</option>
                    <option value="-2">现场</option>
                </select>
              </span>
            </div>
        </div>
        <%--    <div className="row cl">--%>
        <%--        <label className="form-label col-xs-4 col-sm-2"><span className="c-red">*</span>检查部门 :</label>--%>
        <%--        <span className="select-box inline">--%>
        <%--       --%>
        <%--        </span>--%>
        <%--    </div>--%>

        <div class="row cl" style="display: none" id="JiChubm">
            <label class="form-label col-xs-4 col-sm-2"><span
                    class="c-red">*</span>检查部门 :</label>
            <div class="formControls col-xs-8 col-sm-9">
                <span class="select-box inline">
                    <select id="dpid1" name="dpid" class="select" value="" style="width:300px">
                    </select>
                </span>
            </div>
        </div>

        <div class="row cl" style="display: none" id="bm">
            <label class="form-label col-xs-4 col-sm-2"><span
                    class="c-red">*</span>检查部门 :</label>
            <div class="formControls col-xs-8 col-sm-9">
                <span class="select-box inline">
                    <select id="dpid" name="dpid" class="select" value="" style="width:300px">
                        <c:if test="${not empty map}">
                            <c:forEach items="${map}" var="entry">
                                <option value="${entry.key}">${entry.key}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                </span>
            </div>
        </div>

        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <button onClick="save()" class="btn btn-primary radius" type="button" style="padding: 0 70px;"><i
                        class="Hui-iconfont">&#xe632;</i> 生成模版
                </button>
            </div>
        </div>


</form>
</div>
</form>
</body>
<script>
    // 保存数据到数据库
    function save() {
        var dmid = $("#dpid1 option:selected").val(); // 部门名称
        console.log(dmid);
        var type = $("#type option:selected").val(); // 检查类型
        var dptitle = $("#dptitle option:selected").val(); // 检查方式
        var times   = $("#times").val();                   // 检查天数
        var flag = '${flag}';
         if (type == null || type == 0) {
            alert("请选择检查类型");
            return;
        }
        if (dptitle == null || dptitle == 0) {
            alert("请选择检查方式");
            return;
        }
        if (dmid == null) {
            alert("请选择部门");
            return;
        } else {
            // 已选择车间
            $.ajax({
                type: "POST",
                url: getRootPath() + '/village/saveCheckMenu3',
                data: {dmid: dmid,checkType:type,dptitle:dptitle,times:times,flag:flag},
                success: function (result) {
                    if (result.status == 0) {
                        alert('保存成功');
                        var arr=[2,3,4,5,6];
                        var tem = arr[dptitle-1];
                        window.parent.location.href = '${ly}/company/model-list-cx?flag='+flag+'&type=1&template=2';
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    } else {
                        alert('保存失败');
                    }
                },
            })
        }
    }
</script>

</html>