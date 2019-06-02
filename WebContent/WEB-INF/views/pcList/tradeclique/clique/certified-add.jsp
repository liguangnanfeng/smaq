<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<%@ include file="/WEB-INF/inc/back-header.inc"%>
<title>风险分级管控   隐患排查治理智能化平台</title>
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
</style>
</head>
<body>
    <nav class="breadcrumb">
        <i class="Hui-iconfont">&#xe67f;</i>
        <span>首页</span>
        <span class="c-gray en">&gt;</span>
        <span>安全生产标准化</span>
        <span class="c-gray en">&gt;</span>
        <span>安全生产教育培训</span>
        <span class="c-gray en">&gt;</span>
        <span id="specialAddName"></span>
        <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
    <i class="Hui-iconfont">&#xe68f;</i>
  </a>
    </nav>
    <div class="page-container">
        <form class="form form-horizontal" id="form">
        	<div class="row cl">
		      <label class="form-label col-xs-4 col-sm-2">所属企业：</label>
		      <div class="formControls col-xs-8 col-sm-9">
		        <select id="uid" name="uid"  style="width:350px" class="input-text required">
		          <option value="">请选择</option>
		          <option value="${clique.userId }"<c:if test="${sp.userId == clique.userId}">selected</c:if>>${clique.name }</option>
		          <c:forEach items="${branches }" varStatus="index" var="br">
		          	<option value="${br.userId }"<c:if test="${sp.userId == br.userId}">selected</c:if>>${br.name }</option>
		          </c:forEach>
		        </select>
		      </div> 
		    </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">姓名：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" id="a" name="" value="${sp.name }" style="width:350px" class="input-text required">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">工种：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <select id="type" class="sel_area">
                        <option value="">请选择</option>
                        <c:forEach items="${lib }" varStatus="index" var="wt">
                            <option value="${wt.name }"<c:if test="${sp.type == wt.name}">selected</c:if>>${wt.name }</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">证书编号：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" id="b" name="" value="${sp.certificateNumber }" style="width:350px" class="input-text required">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">取证时间：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" id="c" value="${sp.forensicTime }" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" class="input-text Wdate mb-5 mt-5" style="width:350px;">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">下次复审时间：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" id="e" value="${sp.reviewTime }" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" class="input-text Wdate mb-5 mt-5" style="width:350px;">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">备注：</label>
                <div class="formControls col-exs-8 col-sm-9">
                    <textarea id="f" class="textarea txtarea_sq">${sp.remark }</textarea>
                </div>
            </div>
            <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                    <button onClick="article_save_submit()" class="btn btn-primary radius" type="button" style="padding: 0 70px;">
                        <i class="Hui-iconfont">&#xe632;</i>保存
                    </button>
                </div>
            </div>
        </form>
        <script type="text/javascript">
        function article_save_submit() {
        	if ($("#uid").val() == '') {
                layer.alert("所属企业不能为空", function(i) {
                    layer.close(i);
                    $("#uid").focus();
                })
                return false;
            }
            if ($("#a").val() == '') {
                layer.alert("姓名不能为空", function(i) {
                    layer.close(i);
                    $("#a").focus();
                })
                return false;
            }
            if ($("#type").val() == '') {
                layer.alert("类别不能为空", function(i) {
                    layer.close(i);
                    $("#type").focus();
                })
                return false;
            }
            if ($("#b").val() == '') {
                layer.alert("证书编号不能为空", function(i) {
                    layer.close(i);
                    $("#b").focus();
                })
                return false;
            }
            if ($("#c").val() == '') {
                layer.alert("取证时间不能为空", function(i) {
                    layer.close(i);
                    $("#c").focus();
                })
                return false;
            }
            /* if ($("#d").val() == '') {
                layer.alert("审核时间不能为空", function(i) {
                    layer.close(i);
                    $("#d").focus();
                })
                return false;
            } */
            if ($("#e").val() == '') {
                layer.alert("复审时间不能为空", function(i) {
                    layer.close(i);
                    $("#e").focus();
                })
                return false;
            }
            
            var i = '${spFlag}';
            var j = 1;
   			if (i != 0) {
   			    j = i;
   			}

            $.post(getRootPath() + "/tradeclique/certified-save", {
            	id : "${sp.id}",
            	userId:$("#uid").val(),
                name: $("#a").val(),
                flag: j,
                type: $("#type").val(),
                certificateNumber: $("#b").val(),
                forensicTime: $("#c").val(),
                reviewTime: $("#e").val(),
                remark: $("#f").val(),
            }, function(result) {
                    show_tab("持证上岗", getRootPath() + '/tradeclique/certified?spFlag='+i);
                /* parent.close_tab(function() {
                    show_tab("持证上岗", getRootPath() + '/company/train/special-list?spFlag='+i);
                }); */
            })
        }
        /* function getUrlParam(name) {
	      var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	      var r = window.location.search.substr(1).match(reg); //匹配目标参数
	      if (r != null) return unescape(r[2]);
	      return null; //返回参数值
	  	} */
		
		$(function() {
			var title = ['安全管理人员', '危险化学品安全管理人员', '特种作业人员'];
			/* var i = getUrlParam('spFlag');
			if (i == undefined || i == null) {
			    i = '1';
			} */
			var i = '${spFlag}';
			var j = 1;
			if (i != 0) {
			    j = i;
			}
			$("#specialAddName").text('添加' + title[parseInt(j - 1)]);
		})
		
        </script>
    </div>
</body>
</html>