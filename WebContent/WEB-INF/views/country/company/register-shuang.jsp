<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width; initial-scale=0; maximum-scale=1.0; user-scalable=yes" name="viewport" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<%@ include file="/WEB-INF/inc/back-header.inc"%>
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<link rel="stylesheet" type="text/css" href="${ly }/js/layui/css/layui.css" />
<script type="text/javascript" src="${ly }/js/layui/layui.all.js"></script>
<style type="text/css">
body .footer {
    background: none;
    border-top: 0px;
    padding:0px;
}

body .header {
    background: none;
    border: 0;
    text-align: center;
    top: 30%;
    margin-top: -170px;
    color: #fff;
    font-size: 35px;
}

body .loginBox .input-text {
    border: 0px;
    border-bottom: 1px solid #637283;
    width: 280px;
    color: #333;
  height:36px;padding:0 8px;
}
.input-text::-webkit-input-placeholder { color:#333;}
.input-text:-moz-placeholder {color:#333;}
.input-text::-moz-placeholder {color:#333;}
.input-text:-ms-input-placeholder {color:#333;}
body .loginBox .input-text:focus {
    border: 0px;
    border-bottom: 1px solid #206AC0
}

body .loginBox .row {
    width: 80%;
    margin-left: 10%;
    margin-top: 15px;
}

.btn_zhuc {
    float: right;
    margin: 10px;
    color: #2472D4;
    font-size: 15px;
}
.layui-form-item .layui-input-inline {
    float: left;
    width: 113px;
    margin-right: 10px;
}
.layui-input, .layui-select, .layui-textarea{height:45px;}
.layui-form-select .layui-input{background: #E5E5E5;box-shadow: inset 8px 10px 12px #959497;border:0px}
.layui-form-select .layui-input::-webkit-input-placeholder { color:#333;}
.layui-form-select .layui-input:-moz-placeholder {color:#333;}
.layui-form-select .layui-input::-moz-placeholder {color:#333;}
.layui-form-select .layui-input:-ms-input-placeholder {color:#333;}
</style>

</head>
<body style="background:#1A69DF">
<table style="float:left;width:100%;height:100%">
<tr>
<td valign="middle">
    <div class="div_width" style="position: relative;min-height:200px;">
        <img alt="" src="${ly }/images/lbjz.jpg" style="float:left;margin-top:1%;margin-left:3%;max-width:66%"/>
        <img alt="" src="${ly }/images/lbj.gif" class="img_dldt"/>
        <div id="loginform" class="" style="float:left;width:100%"> 
          <div class="div_lomain">
            <form class="form form-horizontal" method="post">
              <div class="toplogin">
                 <a href="" class="a_login2" style="width: 400px;margin-left: 117px;">企业端</a>
               </div>
               <div class="div_row">
                 <div class="row_left">&nbsp;</div>
                 <div class="row_right">
                   <input id="userName" type="text" placeholder="请输入企业名称" class="input-text size-L" required>
                 </div>
               </div>
               <div class="div_row">
                 <div class="row_left">&nbsp;</div>
                     <div class="row_right">
                       <div class="layui-form">
                      <!-- 提示：如果你不想用form，你可以换成div等任何一个普通元素 -->
                      <div class="layui-form-item">
                          <div class="layui-input-inline">
                              <select id="province" name="province" lay-filter="province">
                                  <option value="">请选择省/市</option>
                              </select>
                          </div>
                          <div class="layui-input-inline">
                              <select id="city" name="city" lay-filter="city" disabled>
                                  <option value="">请选择镇</option>
                              </select>
                          </div>
                          <div class="layui-input-inline" style="margin-right:0px;">
                              <select id="area" name="area" lay-filter="area" disabled>
                                  <option value="">请选择村</option>
                              </select>
                          </div>
                      </div>
                  </div>
                 </div>
               </div>
               
               <div class="div_row">
                 <div class="row_left">&nbsp;</div>
                 <div class="row_right">
                   <input id="password" type="password" placeholder="请输入密码" class="input-text size-L" required>
                 </div>
               </div>
               <div class="div_row">
                 <div class="row_left">&nbsp;</div>
                 <div class="row_right">
                   <input id="repassword" type="password" placeholder="确认密码"   class="input-text size-L" required>
                 </div>
               </div>
               <div class="div_row">
                 <div class="row_left">&nbsp;</div>
                 <div class="row_right">
                   <input class="input-text size-L" id="validateCode" type="text" placeholder="验证码" style="width:165px;" required> 
                   <a id="kanbuq" href="javascript:void(0)" onclick="refreshcode()"><img src="${ly }/imageServlet" id="code_img" style="height:45px;margin:0 8px">&nbsp;<img alt="" src="${ly }/images/back/sxb.png"></a>
                 </div>
               </div>
               <div class="div_row">
                 <div class="row_left">&nbsp;</div>
                 <div class="row_right">
                   <input id="register_btn" type="button"class="btn btn-success radius size-L"style="width:100%;background:#092B5B;border:0px;height:50px;font-size:20px" value="立即注册">
                 </div>
               </div>
               <div class="div_row">
                 <div class="row_left">&nbsp;</div>
                 <div class="row_right">
                  <a href="${ly }/fore/company-smaq" class="a_ruk">企业登录</a>
                 </div>
               </div>
            </form>
          </div>
             <%-- <form class="form form-horizontal" method="post">
                <div class="row cl" style="margin-top: 0px;">
                    <div class="formControls col-xs-8">
                        <input id="userName" type="text" placeholder="请输入企业名称"
                            class="input-text size-L" required>
                    </div>
                </div>
		         <div class="row cl" style="padding-left: 15px;">
                    <div class="formControls">
                        <div class="layui-form">
				        <!-- 提示：如果你不想用form，你可以换成div等任何一个普通元素 -->
				        <div class="layui-form-item">
				            <div class="layui-input-inline">
				                <select id="province" name="province" lay-filter="province">
				                    <option value="">请选择省/市</option>
				                </select>
				            </div>
				            <div class="layui-input-inline">
				                <select id="city" name="city" lay-filter="city" disabled>
				                    <option value="">请选择镇</option>
				                </select>
				            </div>
				            <div class="layui-input-inline">
				                <select id="area" name="area" lay-filter="area" disabled>
				                    <option value="">请选择村</option>
				                </select>
				            </div>
				        </div>
				    </div>
                    </div>
                </div>
                <div class="row cl">
                    <div class="formControls col-xs-8">
                        <input id="password" type="password" placeholder="密码"
                            class="input-text size-L" required>
                    </div>
                </div>
                <div class="row cl">
                    <div class="formControls col-xs-8">
                        <input id="repassword" type="password" placeholder="确认密码"
                            class="input-text size-L" required>
                    </div>
                </div>
                <div class="row cl">
                    <div class="formControls col-xs-8 col-xs-offset-3"
                        style="margin-left: 0px; width: 100%">
                        <input class="input-text size-L" id="validateCode" type="text"
                            placeholder="验证码" style="width: 130px;" required> <a
                            id="kanbuq" href="javascript:void(0)" onclick="refreshcode()"><img
                            src="${ly }/imageServlet" id="code_img"
                            style="height: 30px; margin: 0 8px">&nbsp;<img alt=""
                            src="${ly }/images/back/sx.png"></a>
                    </div>
                </div>
                <div class="row cl">
                    <div class="formControls col-xs-8 col-xs-offset-3"
                        style="margin-left: 0px; width: 100%; margin-top: 8px;">
                        <input id="register_btn" type="button"
                            class="btn btn-success radius size-L"
                            style="width: 100%; background: #2472D4; border: 0px; height: 40px;"
                            value="立即注册">
                    </div>
                    <a href="${ly }/fore/company-smaq" data-title="企业登录"  class="btn_zhuc">企业登录</a>
                </div>
            </form> --%>
        </div>
    </div>
    </td></tr></table>
</body>
<script type="text/javascript">
  $("#register_btn").click(function() {
    if ($("#userName").val() == '') {
      layer.alert("请输入企业名称", function(i) {
        layer.close(i);
        $("#userName").focus();
      })
      return false;
    }
    if ($("#password").val() == '') {
      layer.alert("账号密码不能为空", function(i) {
        layer.close(i);
        $("#password").focus();
      })
      return false;
    }
    if ($("#area").val()=='') {
      layer.alert("请输入所在村", function(i) {
        layer.close(i);
        $("#area").focus();
      })
      return false;
    }
    if ($("#repassword").val() == '') {
      layer.alert("请再次输入密码", function(i) {
        layer.close(i);
        $("#repassword").focus();
      })
      return false;
    }
    if ($("#repassword").val() != $("#password").val()) {
      layer.alert("两次输入密码不匹配", function(i) {
        layer.close(i);
        $("#repassword").focus();
      })
      return false;
    }
    if ($("#validateCode").val()=='') {
      layer.alert("请输入验证码", function(i) {
        layer.close(i);
        $("#validateCode").focus();
      })
      return false;
    }

    $("#register_btn").attr("disabled", "disabled");
    var index = layer.load();
    $.post(getRootPath() + "/fore/register", {
      userName : $("#userName").val(),
      psw : $("#password").val(),
      village : $("#area").val(),
      validateCode : $("#validateCode").val()
    }, function(result) {
      layer.close(index);
      if (result.status == '1') {
        $("#register_btn").removeAttr("disabled");
        layer.alert(result.map.message);
        return false;
      } else {
        layer.alert("注册成功，请等待管理员审核");
      }
    });
  });

  function refreshcode() {
    $('#code_img').attr('src', getRootPath() + '/imageServlet?time=' + new Date().getTime());
  }
  
  layui.config({
    base : "../js/"
  }).extend({
	    "address" : "address"
  })
  
  layui.use(['jquery', "address"], function() {
      var address = layui.address(),
          $ = layui.jquery;
  })
</script>
</html>