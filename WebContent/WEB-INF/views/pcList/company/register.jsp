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

.input-text::-webkit-input-placeholder { color:#333;}
.input-text:-moz-placeholder {color:#333;}
.input-text::-moz-placeholder {color:#333;}
.input-text:-ms-input-placeholder {color:#333;}

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
<body>
<table style="float:left;width:100%;height:100%">
<tr>
<td valign="middle">
<div style="float:left;width:100%;">
 <div style="width:1366px;margin:auto;">
<div class="div_login">
    <img alt="" src="${ly }/images/llogo.jpg" class="img_dlogo"/>
    <img alt="" src="${ly }/images/dit.png" class="img_dldt"/>
  </div>
  </div>
  </div>
  <div style="float:left;width:100%;background:#1A69DF;margin-top:35px;">
 <div style="width:1366px;margin:auto;">
  <div class="div_login" >
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
            <a href="${ly }/village" class="a_ruk">政府端入口</a>
            <a href="${ly }/fore/company" class="a_ruk" style="margin-right:0">企业端入口</a>
           </div>
         </div>
      </form>
    </div>
    <img alt="" src="${ly }/images/ewm.png" class="img_dewm">
  </div>
  </div>
</div>
<div style="float:left;width:100%;">
 <div style="width:1366px;margin:auto;">
  <div class="div_login">
    <div class="div_lomain2">
      <div class="one-f" style="margin-top:12px;">
        <a href="${ly }/html/fore.html?id=1" target="_blank">关于我们</a>
        <font>|</font>
        <a href="${ly }/html/fore.html?id=2" target="_blank">网站声明</a>
        <font>|</font>
        <a href="${ly }/html/fore.html?id=3" target="_blank">联系我们</a>
        <font>|</font>
        <%-- <a href="${ly }/html/tui.html" target="_blank">平台推广</a>
        <font>|</font> --%>
        <a href="http://new.cnzz.com/v1/login.php?siteid=1255702121" target="_blank">站长统计</a>
      </div>
      <div class="one-f">
        <font>主办方：江苏安泰信息技术发展有限公司</font>
        <font>联系电话：0510-83556999</font>
        <a href="http://www.miitbeian.gov.cn/" target="_blank">苏ICP备17056652号-1</a>
        <font>增值电信业务经营许可证：苏B1-20150079</font>
        <a href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=32020602000446" target="_blank">苏公网安备：32020602000446号</a>
      </div>
      <div class="one-f"><font>技术支撑方：无锡兰霖网络有限公司</font><font>法律顾问：江苏欣林律师事务所</font></div>
    </div>
  </div>
</div>
</div>
</td></tr></table>
<script src="https://s19.cnzz.com/z_stat.php?id=1273938480&web_id=1273938480" language="JavaScript"></script>
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
  
  /* var list1 = new Array();
  var x = new Object();
  x.id = 1;
  x.name = "1111";
  var x1 = new Object();
  x1.id = 2;
  x1.name = "2222";
  var x2 = new Object();
  x2.id = 3;
  x2.name = "3333";
  list1.push(x);
  list1.push(x1);
  list1.push(x2);
  
  var list2 = new Array();
  var x3 = new Object();
  x3.id = 4;
  x3.name = "1111";
  x3.pid = 1;
  var x4 = new Object();
  x4.id = 4;
  x4.name = "2222";
  x4.pid = 2;
  var x5 = new Object();
  x5.id = 5;
  x5.name = "3333";
  x5.pid = 3;
  list2.push(x3);
  list2.push(x4);
  list2.push(x5);
  
  $("#province").empty().append("<option value=''>请选择</option>");
  $.each(list1,function(i, item) {
    $("#province").append("<option value='"+item.id+"'>"+item.name+"</option>");
  })
  
  $("#province").change(function(){
    $("#city").empty().append("<option value=''>请选择</option>");
    var pid = $(this).val();
    if(pid == '') {
    	return false;
    }
    
    $.each(list2,function(i, item) {
      if(item.pid == pid) {
	      $("#city").append("<option value='"+item.id+"'>"+item.name+"</option>");
      }
    })
  }) */
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