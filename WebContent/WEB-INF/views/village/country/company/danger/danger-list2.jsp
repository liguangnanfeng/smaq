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
  .danger_ {display: none;}
</style>
<script type="text/javascript">
var levelL = ${levelL};
var itemL = ${itemL};
var checkId = ${tCheck.id};
var type = ${tCheck.type};
//打开检查项 不合格弹窗
function openzl(obj){
  var itemId = $(obj).closest("div[data-end='1']").attr("data-itemid");
  
  window.itemId = itemId;
  $.post(getRootPath() + "/company/check-item-get",{
    id : itemId
  },function(result) {
      $(".list_zl").empty();
      $("#memo").val(result.map.item.memo);
      $("#suggest").val(result.map.item.suggest);
      $("#endTime").val(result.map.item.endTime);
      if(null != result.map.item.files && '' != result.map.item.files) {
        $.each(result.map.item.files.split(","),function(i, item) {
          $(".list_zl").append(
            	'<div class="one_yhzl mt-5">'+
              	'<a href="'+item+'" target="_blank">'+item+'</a>'+
              	'<i class="Hui-iconfont" onclick="$(this).parent().remove();">&#xe6e2;</i>'+
            	'</div>'
            	)
        })
      }
	$("#upload-zl").modal("show");
  })  
}
</script> 
<script type="text/javascript" src="${ly }/js/ll-tree/company-next-ll-tree.js"></script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>隐患排查治理系统</span> 
    <span class="c-gray en">&gt;</span> <span>隐患排查治理标准</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="list_danger">
      <!-- 包含两级 -->
      <%-- <div class="one_danger">
        <div class="one_dtitle">
          <i class="Hui-iconfont">&#xe604;</i>
          <p>一级分类</p>
        </div>
        <div class="list_odan">
          <div class="two_danger">
            <!-- 判断添加不合格的显示图片 -->
            <div class="two_dtitle">
              <bb>&nbsp;</bb>
              <p><img alt="" src="${ly }/images/fore/zd.jpg"/> 二级分类 
                <button class="btn btn-danger ml-10 radius" type="button" onclick="openzl(1)">不合格</button>
              </p>
            </div>
          </div>
          <div class="two_danger">
            <div class="two_dtitle">
              <bb>&nbsp;</bb>
              <p><img alt="" src="${ly }/images/fore/zd.jpg"/> 二级分类 
                <button class="btn btn-danger ml-10 radius" type="button" onclick="openzl(1)">不合格</button>
              </p>
            </div>
          </div>
        </div>
      </div>
      <!-- 包含三级 -->
      <div class="one_danger">
        <div class="one_dtitle">
          <i class="Hui-iconfont">&#xe604;</i>
          <p>一级分类</p>
        </div>
        <div class="list_odan">
          <div class="two_danger">
            <div class="two_dtitle">
              <i class="Hui-iconfont">&#xe604;</i>
              <p>二级分类</p>
            </div>
            <div class="list_twdan">
              <div class="three_danger">
                <div class="three_dtitle">
                  <bb>&nbsp;</bb>
                  <p><img alt="" src="${ly }/images/fore/zd.jpg"/> 三级分类
                    <button class="btn btn-danger ml-10 radius" type="button" onclick="openzl(1)">不合格</button>
                  </p>
                </div>
              </div>
              <div class="three_danger">
                <div class="three_dtitle">
                  <bb>&nbsp;</bb>
                  <p><img alt="" src="${ly }/images/fore/zd.jpg"/> 三级分类 
                    <button class="btn btn-danger ml-10 radius" type="button" onclick="openzl(1)">不合格</button>
                  </p>
                </div>
              </div>
            </div>
          </div>
          <div class="two_danger">
            <div class="two_dtitle">
              <bb>&nbsp;</bb>
              <p><img alt="" src="${ly }/images/fore/zd.jpg"/> 二级分类 
                <button class="btn btn-danger ml-10 radius" type="button" onclick="openzl(1)">不合格</button>
              </p>
            </div>
          </div>
        </div>
      </div>
      <!-- 包含四级 -->
      <div class="one_danger">
        <div class="one_dtitle">
          <i class="Hui-iconfont">&#xe604;</i>
          <p>一级分类</p>
        </div>
        <div class="list_odan">
          <div class="two_danger">
            <div class="two_dtitle">
              <i class="Hui-iconfont">&#xe604;</i>
              <p>二级分类</p>
            </div>
            <div class="list_twdan">
              <div class="three_danger">
                <div class="three_dtitle">
                  <i class="Hui-iconfont">&#xe604;</i>
                  <p>三级分类</p>
                </div>
                <div class="list_thdan">
                  <div class="four_dtitle">
                    <bb>&nbsp;</bb>
                    <p><img alt="" src="${ly }/images/fore/zd.jpg"/> 四级分类 
                      <button class="btn btn-danger ml-10 radius" type="button" onclick="openzl(1)">不合格</button>
                    </p>
                  </div>
                  <div class="four_dtitle">
                    <bb>&nbsp;</bb>
                    <p><img alt="" src="${ly }/images/fore/zd.jpg"/> 四级分类 
                      <button class="btn btn-danger ml-10 radius" type="button" onclick="openzl(1)">不合格</button>
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="two_danger">
            <div class="two_dtitle">
              <i class="Hui-iconfont">&#xe604;</i>
              <p>二级分类</p>
            </div>
            <div class="list_twdan">
              <div class="three_danger">
                <div class="three_dtitle">
                  <bb>&nbsp;</bb>
                  <p><img alt="" src="${ly }/images/fore/zd.jpg"/> 三级分类 
                    <button class="btn btn-danger ml-10 radius" type="button" onclick="openzl(1)">不合格</button>
                  </p>
                </div>
              </div>
            </div>
          </div>
          <div class="two_danger">
            <div class="two_dtitle">
              <bb>&nbsp;</bb>
              <p>二级分类 
                <button class="btn btn-danger ml-10 radius" type="button" onclick="openzl(1)">不合格</button>
              </p>
            </div>
          </div>
        </div>
      </div> --%>
    </div>
    <div class="row cl mt-15">
      <label class="form-label col-xs-4 col-sm-2">上传照片：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <!-- 判断添加图片后循环显示 -->
        <c:if test="${!empty tCheck.files}">
        <c:forEach items="${fn:split(tCheck.files, ',') }" var="be">
        <div class="one_himg">
           <img alt="" src="${be }"/>
           <a href="javascript:void();" class="btn_sctp" onclick="$(this).parent().remove();">删除</a>
         </div>
        </c:forEach>
        </c:if>
        <!-- 判断循环结束 -->
        <a id="addImg" class="btn_addtp" href="javascript:void();" onclick="uploadImg1x();">+ 添加图片</a>
      </div>  
    </div>
    <script type="text/javascript">
    function uploadImg1x() {
      img_upload(null, null, function(src) {
        $('<div class="one_himg">' +
            '<img alt="" src="'+src+'"/>' +
            '<a href="javascript:void();" class="btn_sctp" onclick="$(this).parent().remove();">删除</a>' + 
          '</div>').insertBefore($("#addImg"))
      })
    }
    </script>
    <div class="row cl mt-15">
      <label class="form-label col-xs-4 col-sm-2">检查人：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="cheker" style="width:350px" class="input-text required" value="${tCheck.cheker }">
      </div> 
    </div>
    <div class="row cl mt-20" style="float:left;">
      <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
        <button onClick="article_save_submit();" class="btn btn-primary radius" type="button" style="padding: 0 70px;"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
      </div>
    </div>
  </div>
<!-- 弹窗不合格资料 -->
  <div id="upload-zl" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header">
                <h3 class="modal-title">上传不合格资料</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body">
              <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2" style="width:20%;text-align:right;">隐患描述：</label>
                <div class="formControls col-xs-8 col-sm-9" style="width:70%;">
                  <textarea class="textarea txtarea_sq" id="memo"></textarea>
                </div> 
              </div>
              <div class="row cl mt-15">
                <label class="form-label col-xs-4 col-sm-2" style="width:20%;text-align:right;">整改意见：</label>
                <div class="formControls col-xs-8 col-sm-9" style="width:70%;">
                  <textarea class="textarea txtarea_sq" id="suggest"></textarea>
                </div> 
              </div>
              <div class="row cl mt-15">
                <label class="form-label col-xs-4 col-sm-2" style="width:20%;text-align:right;">整改截止时间：</label>
                <div class="formControls col-xs-8 col-sm-9" style="width:70%;">
                  <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="endTime" class="input-text Wdate mb-5 mt-5" style="width:120px;">
                </div> 
              </div>
              <div class="row cl mt-15">
                <label class="form-label col-xs-4 col-sm-2" style="width:20%;text-align:right;">资料上传：</label>
                <div class="formControls col-xs-8 col-sm-9" style="width:70%;">
                  <span class="btn-upload">
                    <a href="javascript:void();" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe642;</i> 浏览文件</a>
                    <input type="file" multiple name="file1" id="file1" onchange="a_load()" class="input-file">
                  </span>
                  <div class="list_zl mb-10">
                    <!-- 文件s -->
                  </div>
                </div> 
              </div>
            </div>
            <script type="text/javascript">
            function a_load() {
              var i = layer.load();
              $.ajaxFileUpload({
                url: getRootPath() + '/ajaxUploadFile', //用于文件上传的服务器端请求地址
                secureuri: false, //一般设置为false
                fileElementId: 'file1', //文件上传空间的id属性  <input type="file" id="file" name="file" />
                dataType: 'json', //返回值类型 一般设置为json
                async: false,
                success: function (result) { //服务器成功响应处理函数
                  layer.close(i);
                  if (status == '1') {
                    layer.alert(map.message);
                    return false;
                  } else {
                  	var fileName = result.map.fileName;
                  	$(".list_zl").append(
                  	'<div class="one_yhzl mt-5">'+
                    	'<a href="'+fileName+'" target="_blank">'+fileName+'</a>'+
                    	'<i class="Hui-iconfont" onclick="$(this).parent().remove();">&#xe6e2;</i>'+
                  	'</div>'
                  	)
                  }
                },
                error: function (data, status, e) {//服务器响应失败处理函数
                  var result = new Object();
                  var map = new Object();
                  map.message = "图片上传失败";
                  result.status = "1";
                  result.map = map;
                }
            })
            }
            </script>
            <div class="modal-footer">
                <button class="btn btn-danger" onclick="esc_()">取消不合格</button>
                <button class="btn btn-primary" onclick="sur()">确定</button>
                <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
//取消不合格
function esc_() {
  var files = '';
  $(".list_zl .one_yhzl").each(function(){
    var f = $(this).find("a").attr("href");
    files = files==''? f : files + "," + f;
  })
  $.post(getRootPath() + "/company/check-item-save",{
    id : itemId,
    memo : $("#memo").val(),
    suggest : $("#suggest").val(),
    endTime : $("#endTime").val(),
    status : 1,
    files : files
  },function(result) {
    var div = $("div[data-itemid='"+itemId+"']");
    div.find(".danger_").show();
    $("#upload-zl").modal("hide");
    layer.alert("信息保存成功");
  })
}

//保存不合格信息
function sur() {
  /* if($("#memo").val() == '') {
    layer.alert("请输入隐患描述",'',function(i){
      layer.close(i);
      $("#memo").focus();
    })
    return false;
  }
  if($("#suggest").val() == '') {
    layer.alert("请输入整改意见",'',function(i){
      layer.close(i);
      $("#suggest").focus();
    })
    return false;
  }
  if($("#endTime").val() == '') {
    layer.alert("请选择整改截止时间",'',function(i){
      layer.close(i);
      $("#endTime").focus();
    })
    return false;
  } */
  var files = '';
  $(".list_zl .one_yhzl").each(function(){
    var f = $(this).find("a").attr("href");
    files = files==''? f : files + "," + f;
  })
  $.post(getRootPath() + "/company/check-item-save",{
    id : itemId,
    memo : $("#memo").val(),
    suggest : $("#suggest").val(),
    endTime : $("#endTime").val(),
    status : 2,
    files : files
  },function(result) {
    var div = $("div[data-itemid='"+itemId+"']");
    div.find(".danger_").show();
    $("#upload-zl").modal("hide");
    layer.alert("不合格信息保存成功");
  })
}

//整体提交
function article_save_submit() {
  layer.confirm("确定提交该检查记录么？",function(i){
    var files = '';
    $(".one_himg img").each(function(){
      var f = $(this).attr("src");
      files = files==''? f : files + "," + f;
    })
    layer.close(i);
    $.post(getRootPath() + "/company/check-save2", {
      id : checkId,
      files : files,
      cheker : $("#cheker").val()
    }, function(result) {
      if(result.status == '1') {
        layer.alert(result.map.message)
      } else {
        if(type == 1) {
            show_tab('基础管理', '/village/check-list/1');
        }
        if(type == 2) {
            show_tab('现场管理', '/village/check-list/2');
        }
        if(type == 3) {
            show_tab('高危作业', '/village/check-list/3');
        }
      }
    })
  })
}
</script>
</body>
</html>