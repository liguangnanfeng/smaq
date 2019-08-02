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
<link type="text/css" rel="stylesheet" href="${ly }/js/kindeditor/themes/default/default.css" />
<script type="text/javascript" src="${ly }/js/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" src="${ly }/js/kindeditor/lang/zh_CN.js"></script>
<style type="text/css">
.input-text {
  position: relative
}

.txtarea_sq {
  height: 100px;
  width: 350px;
}
</style>
<script type="text/javascript">
  
</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>评价评估系统</span>
    <span class="c-gray en">&gt;</span> <span>评价评估档案</span> 
    <span class="c-gray en">&gt;</span> <span>编辑各类评价评估</span>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <form class="form form-horizontal" id="form">
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">评价评估分类：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <c:if test="${e.flag == '1' }">
            <label class="form-label" style="text-align: left;">安全生产类</label>
          </c:if>
          <c:if test="${e.flag == '2' }">
            <label class="form-label" style="text-align: left;">职业健康类</label>
          </c:if>
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">评价报告名称：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="text" id="a" name="" value="${e.reportName }" style="width: 350px" class="input-text required">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">评价类型：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <select id="b" class="sel_area"></select>
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">编制单位：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="text" id="c" name="" value="${e.unit }" style="width: 350px" class="input-text">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">报告文档：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="text" id="d" style="width: 200px" value="${e.docName }" class="input-text" readonly="readonly" />
          <button id="insertfile" class="btn btn-warning radius ml-10" type="button">
            <i class="Hui-iconfont" style="font-size: 15px;">&#xe642;</i> 上传资料
          </button>
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">编制日期：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="text" id="e" value="${e.reportTime }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="input-text Wdate mb-5 mt-5"
            style="width: 350px;">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">有效期：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="text" id="f" value="${e.expirationTime }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="input-text Wdate mb-5 mt-5"
            style="width: 350px;">
        </div>

      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">备注：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <textarea id="g" class="textarea txtarea_sq">${e.remark }</textarea>
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
          var docUrl = '${e.docUrl}';
          var id = '${e.id}';
          var eFlag = '${e.flag}';
          var flag = 1;
          if('${flag}' == 0) {
            if(eFlag == 2) {
              flag = 2;
            }
          }
          
          function article_save_submit() {
            if ($("#a").val() == '') {
              layer.alert("评价报告名称不能为空", function(i) {
                layer.close(i);
                $("#a").focus();
              })
              return false;
            }
            if ($("#b").val() == '') {
              layer.alert("评价类型不能为空", function(i) {
                layer.close(i);
                $("#b").focus();
              })
              return false;
            }
            if ($("#e").val() == '') {
              layer.alert("上次编制时间不能为空", function(i) {
                layer.close(i);
                $("#e").focus();
              })
              return false;
            }
            /* if ($("#f").val() == '') {
              layer.alert("下次编制时间不能为空", function(i) {
                layer.close(i);
                $("#f").focus();
              })
              return false;
            } */

            $.post(getRootPath() + "/company/evaluate/evaluation-save?flag="+flag, {
              id : id,
              reportName : $("#a").val(),
              type : $("#b").val(),
              unit : $("#c").val(),
              docUrl : docUrl,
              docName : $("#d").val(),
              reportTime : $("#e").val(),
              expirationTime : $("#f").val(),
              remark : $("#g").val()
            }, function(result) {
                show_tab("各类评价评估", getRootPath() + '/company/evaluate/evaluation-list?flag=${flag}');
            })
          }

          function getUrlParam(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
            var r = window.location.search.substr(1).match(reg); //匹配目标参数
            if (r != null)
              return unescape(r[2]);
            return null; //返回参数值
          }

          $(function() {
            if (eFlag == '1') {
              $("#b").append("<option value=''>" + "请选择" + "</option>");
              $("#b").append("<option value='" + 1 + "'>" + "预评价" + "</option>");
              $("#b").append("<option value='" + 2 + "'>" + "验收评价" + "</option>");
              $("#b").append("<option value='" + 3 + "'>" + "现状评价" + "</option>");
              $("#b").append("<option value='" + 4 + "'>" + "危化品经营证评价" + "</option>");
              $("#b").append("<option value='" + 5 + "'>" + "危化品生产证评价" + "</option>");
              $("#b").append("<option value='" + 6 + "'>" + "危化品使用证评价" + "</option>");
              $("#b").append("<option value='" + 7 + "'>" + "重大危险源评估" + "</option>");
              $("#b").append("<option value='" + 8 + "'>" + "安全条件论证报告" + "</option>");
              $("#b").append("<option value='" + 9 + "'>" + "安全条件综合分析报告" + "</option>");
            } else {
              $("#b").append("<option value=''>" + "请选择" + "</option>");
              $("#b").append("<option value='" + 1 + "'>" + "预评价" + "</option>");
              $("#b").append("<option value='" + 2 + "'>" + "控效评价" + "</option>");
              $("#b").append("<option value='" + 3 + "'>" + "现状评价" + "</option>");
            }
            $("#b").val('${e.type}');
          })

          KindEditor.ready(function(K) {
            var editor = K.editor({
              uploadJson : getRootPath() + '/UploadServlet'
            });
            K('#insertfile').click(function() {
              editor.loadPlugin('insertfile', function() {
                editor.plugin.fileDialog({
                  fileUrl : K('#url').val(),
                  clickFn : function(url, title) {
                    docUrl = url;
                    K('#d').val(title);
                    editor.hideDialog();
                  }
                });
              });
            });
          });
        </script>

  </div>
</body>
</html>