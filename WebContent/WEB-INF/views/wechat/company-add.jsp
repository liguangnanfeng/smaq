<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script type="text/javascript" src="${ly }/js/H-ui.admin_v3.0/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="${ly}/js/H-ui.admin_v3.0/lib/layer/2.4/layer.js"></script>
    <script type="text/javascript" src="${ly }/js/util/utils.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>添加企业</title>
</head>

<body>
    <div class="container">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">添加企业</h3>
            </div>
            <div class="panel-body">
                <div>
                    <div class="form-group">
                        <label for="userName">企业名称</label>
                        <input type="text" id="userName" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>所在地</label>
                        <select id="area" class="form-control">
                            <option value="">请选择区</option>
							<c:forEach items="${list }" var="list">
				            	<option value="${list.code }">${list.name }</option>
				            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <select id="town" class="form-control">
                            <option>请选择镇</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <select id="village" class="form-control">
                            <option>请选择村</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="password">密码</label>
                        <input type="password" class="form-control" id="password">
                    </div>
                    <div class="form-group">
                        <label for="repassword">确认密码</label>
                        <input type="password" class="form-control" id="repassword">
                    </div>
                    <div class="form-group submitbox">
                        <div class="btn btn-default" onClick="save()">确认</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
    $(function () {
        $("#area").change(function () {
            $("#village").empty().append('<option value="">请选择村</option>');
            $("#town").empty().append('<option value="">请选择镇</option>');
            if ($(this).val() != '') {
                $.post(getRootPath() + "/common/getTownByArea", {
                    id: $("#area").val()
                }, function (result) {
                    var list = result.map.list;
                    $.each(list, function (i, item) {
                        $("#town").append('<option value="' + item.userId + '">' + item.name + '</option>');
                    })
                })
            }
        });
        $("#town").change(function () {
            $("#village").empty().append('<option value="">请选择村</option>');
            if ($(this).val() != '') {
                $.post(getRootPath() + "/common/getVillageByTown", {
                    id: $("#town").val()
                }, function (result) {
                    var list = result.map.list;
                    $.each(list, function (i, item) {
                        $("#village").append('<option value="' + item.userId + '">' + item.name + '</option>');
                    })
                })
            }
        })
    })

    function save() {
      	var duserId = {};
      	if ("${user.userType}" == 10) {
      	  	duserId = "${user.id}";
      	}
        if ($("#userName").val() == '') {
            layer.alert("企业名称不能为空");
            return false;
        }
        if ($("#village").val() == '') {
          	layer.alert("请选择所在地");
            return false;
        }
        if ($("#password").val() == '') {
          	layer.alert("密码不能为空");
            return false;
        }
        if ($("#repassword").val() == '') {
          	layer.alert("确认密码不能为空");
            return false;
        }
        if ($("#repassword").val() != $("#password").val()) {
          	layer.alert("密码不一致");
            return false;
        }
        $.post(getRootPath() + "/api/village/xlb/company-save", {
            userName: $("#userName").val(),
            psw: $("#password").val(),
            villageId: $("#village").val(),
            duserId: duserId
        }, function (result) {
          	//layer.alert(result.status);
            if (result.status == '1') {
              	layer.alert(result.map.message);
                return false;
            }else{
             	if(window.androidandiosJS){
	              	window.androidandiosJS.complete();
	            }else{
	              	layer.alert("添加完成！");
	            }
            }
        })
    }
</script>
<style>
    body {
        font-family: "Microsoft YaHei";
    }

    .container {
        padding: 0;
    }

    .submitbox {
        text-align: center;
    }
</style>

</html>