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
    <%@ include file="/WEB-INF/inc/kindeditor.inc" %>
    <title>风险分级管控 隐患排查治理智能化平台</title>
    <meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
    <meta name="description" content="风险分级管控   隐患排查治理智能化平台">
</head>
<script type="text/javascript">
    var id = '${p.id}';
    var userType = ${session_user.userType};
    var x = (userType == 3 || userType == 4) ? '检查' : '执法';

    function save() {
        if ($("#depart").val() == '') {
            layer.alert(x + "部门不能为空");
            return false;
        }
        if ($("#name").val() == '') {
            layer.alert(x + "人员不能为空");
            return false;
        }
        if ($("#code").val() == '') {
            layer.alert(x + "人员编号不能为空");
            return false;
        }
        if ($("#password").val() == '') {
            layer.alert(x + "人员密码不能为空");
            return false;
        }
        var i = layer.load();
        if(${not empty p.id}){

            if($("#addpw").val() != ''){
                $.post(getRootPath() + "/district/off-save", {
                    id: id,
                    depart: $("#depart").val(),
                    name: $("#name").val(),
                    code: $("#code").val(),
                    password: $("#addpw").val()
                }, function (result) {
                    layer.close(i);
                    if (result.status == '') {
                        layer.alert(result.map.message);
                    } else {
                        show_tab(x + "人员信息库", '/district/off-list');
                    }
                })
            }else{
                $.post(getRootPath() + "/district/off-save", {
                    id: id,
                    depart: $("#depart").val(),
                    name: $("#name").val(),
                    code: $("#code").val(),
                    password: $("#password").val()
                }, function (result) {
                    layer.close(i);
                    if (result.status == '') {
                        layer.alert(result.map.message);
                    } else {
                        show_tab(x + "人员信息库", '/district/off-list');
                    }
                })
            }

        }else {
            $.post(getRootPath() + "/district/off-save", {
                id: id,
                depart: $("#depart").val(),
                name: $("#name").val(),
                code: $("#code").val(),
                password: $("#password").val()
            }, function (result) {
                layer.close(i);
                if (result.status == '') {
                    layer.alert(result.map.message);
                } else {
                    show_tab(x + "人员信息库", '/district/off-list');
                }
            })
        }

    }

    // TODO  修改密码的方法
    function addpwd() {

        var titles = "新密码 : ";

        $("#label_").text(titles);
        $("#type").val(1);
        $("#win-add").modal("show");
        $("#pwd").focus();
        ;

    }

    // 调用这个方法的话,进行赋值
    function save_btn() {
        console.log($("#addpw").val())
        $("#win-add").modal("hide");
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
<div class="page-container">
    <form class="form form-horizontal" id="form">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">${(session_user.userType == 3 || session_user.userType == 4) ? '检查' : '执法'}部门：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" id="depart" value="${p.depart }" style="width:350px" class="input-text required">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">${(session_user.userType == 3 || session_user.userType == 4) ? '检查' : '执法'}人员名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" id="name" value="${p.name }" style="width:350px" class="input-text required">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">${(session_user.userType == 3 || session_user.userType == 4) ? '检查' : '执法'}编号：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" id="code" value="${p.code }" style="width:350px" class="input-text required">
            </div>
        </div>

        <c:if test="${ not empty p.password }">
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>密码：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <button class="btn btn-primary radius" type="button" onclick="addpwd()"
                            style="padding: 0 70px;">是否重置密码
                    </button>
                </div>
            </div>
        </c:if>
        <c:if test="${ empty p.password }">
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">${(session_user.userType == 3 || session_user.userType == 4) ? '检查' : '执法'}密码：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" id="password" value="${p.password }" style="width:350px"
                           class="input-text required">
                </div>
            </div>
        </c:if>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <button onClick="save()" class="btn btn-primary radius" type="button" style="padding: 0 70px;"><i
                        class="Hui-iconfont">&#xe632;</i> 保存
                </button>
            </div>
        </div>
    </form>
</div>

<!-- TODO  弹窗添加 进行添加数据 -->
<div id="win-add" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header">
                <h3 class="modal-title">修改新密码</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body">
                <div class="row cl row1">
                    <label class="form-label col-xs-4 col-sm-2" style="width:120px;" id="label_">新密码 ：</label>
                    <div class="formControls col-xs-8 col-sm-9" style="text-align:left;width:320px;">
                        <input type="text" value="" name="password" id="addpw" class="input-text">
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <button class="btn btn-primary" onclick="save_btn()">确定</button>
                <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>