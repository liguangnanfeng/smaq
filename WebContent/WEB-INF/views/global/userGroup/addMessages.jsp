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
<title>安全分级管控   隐患排查治理智能化信息平台</title>
<meta name="keywords" content="安全分级管控   隐患排查治理智能化信息平台">
<meta name="description" content="安全分级管控   隐患排查治理智能化信息平台">
<link rel="stylesheet" type="text/css" href="${ly }/js/plugins/webuploader/webuploader.css">
<style type="text/css">
body .dis-ib{margin-right:15px;}
.view{
	width : 400px;
}
.edui-default .edui-editor-bottomContainer {
    overflow: hidden;
    display: none;
}
.listem>* {
    float: left;
}
.listem em {
    font-size: 12px;
    padding: 0 5px;
    margin: 0 5px 5px 0;
    border-radius: 1px;
    background-color: #f7f7f7;
    height: 22px;
    line-height: 21px;
    box-sizing: border-box;
    font-style: unset;
}
.webuploader-pick {
    position: relative;
    display: inline-block;
    cursor: pointer;
    background: #5a98de;
    padding: 10px 15px;
    color: #fff;
    text-align: center;
    border-radius: 3px;
    overflow: hidden;
    height: 13px;
    line-height: 13px;
}
</style>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>新建信息</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="" style="margin-left:20px;">
      <!-- <form action="" method="post"> -->
        <div class="dis-ib">
          <span>输入标题：</span>
          <input type="text" id="titleInput" value="" class="input-text mb-5 mt-5" style="width:300px;">
        </div>
        
        <div class="dis-ib">
          <span>输入简介：</span>
          <input type="text" id="descInput" value="" class="input-text mb-5 mt-5" style="width:300px;">
        </div>
        
      <!-- </form> -->
    </div>
  <div class="page-container">
  <div style="width:65%;float:left;">
    <script id="editor" type="text/plain" style="width:auto;height:330px;"></script>
    <div style="margin-top:6px;">
    	<a href="javascript:void(0)" style="" id="toUploadFiles">上传附件</a>
    </div>
    
    <span id="showFilesResult"></span>
      <!-- 推送环境（测试专用）：<input type="radio" name="envrionment"  value="1">测试环境
 <input type="radio" checked name="envrionment" value="">生产环境 -->
  </div>
	<div style="width:33%;float:left;margin-left:10px;">
	
<c:if test="${propelcompany != 1}">
	   <div class="dis-ib">
          <span>请选择推送范围：</span>
           <span style="cursor:pointer;"><input type="radio" name="sendType" checked value="0" ><span class="toCheck">企业</span></span>
           <span style="cursor:pointer;margin-left:5px;"><input type="radio" name="sendType" value="1" ><span class="toCheck">群组</span></span>
           <c:if test="${userType != 4 && userType != 10}">
           	<span style="cursor:pointer;margin-left:5px;"><input type="radio" name="sendType" value="2" ><span class="toCheck">部门</span></span>
           </c:if>
        </div>
</c:if>

<c:if test="${propelcompany == 1}">
		<div class="dis-ib">
          <span>推送范围：</span>
           <span style="cursor:pointer;"><input type="radio" name="sendType" checked value="0" ><span class="toCheck">上级部门</span></span>
        </div>
</c:if>

<c:if test="${propelcompany != 1}">
        <div class="dis-ib" id="userGroupDiv" style="display:none;width:100%;height: 200px;">
          <span>请选择群组：</span>
          <div id="canChooseGroup" class="listem">
          
          </div>
         </div>
         
         <div class="dis-ib" id="departListDiv" style="display:none;width:100%;height: 200px;">
          <span>请选择部门：</span>
          <div id="canChooseDepart" class="listem">
          
          </div>
         </div>
           
         <div class="dis-ib" id="companyListDiv" style="height: 200px;">
         <div class="cl">
         	<label class="">推送范围选择：</label>
         </div>
</c:if>        
         
<c:if test="${propelcompany != 1}">
          <div class="cl">
	      <div class="formControls col-xs-8 col-sm-9">
	        	<select id="selectDistrict" class="input-text" style="display:none;width:90px;">
	        		<option value="">不限区县</option>
	        		<%-- <c:forEach items="${list }" var="dl">
	        			<option value="${dl.districtId }">${dl.districtName }</option>
	        		</c:forEach> --%>
	        	</select>
	        	<select id="selectTown" class="input-text" style="display:none;width:90px;">
	        		<option value="">不限镇</option>
	        		<%-- <c:forEach items="${list }" var="dl">
	        			<option value="${dl.townId }">${dl.townName }</option>
	        		</c:forEach> --%>
	        	</select>
	        	<select id="selectVillage" class="input-text" style="display:none;width:90px;">
	        		<option value="">不限乡村</option>
	        		<%-- <c:forEach items="${list }" var="dl">
	        			<option value="${dl.villageId }">${dl.villageName }</option>
	        		</c:forEach> --%>
	        	</select>
	        	<br/>
	        	<input type="text" id="companyName" value="" style="width:180px" placeHolder="请输入企业名称" class="input-text required"  >
	        	
	        	<a href="javascript:void(0);" class="btn btn-primary radius" style="background-color: #ddd;border-color: #ddd;" id="toSearch" >查询</a>
	      </div>
	      <div class="formControls col-xs-8 col-sm-9" >
	      	<div style="float:left;">可选企业列表：<em style="font-style: unset;"><input type="checkbox" name="allSlected"/><span style="cursor:pointer;" class="toSelectAll">全选</span></em>
	      		<div id="hasChooseList" class="listem" style="height:100px;overflow:auto;"></div>
	      	</div> 
	       </div> 
	    </div>
</c:if>    
<c:if test="${propelcompany == 1}">
	      <div class="formControls col-xs-8 col-sm-9" style="display:none">
	        	<input type="text" id="companyName" value="" style="width:180px" placeHolder="请输入名称" class="input-text required"  >
	        	<a href="javascript:void(0);" class="btn btn-primary radius" style="background-color: #ddd;border-color: #ddd;" id="toSearch" >查询</a>
	      </div>
	      <div class="formControls col-xs-8 col-sm-9" >
	      	<div style="float:left;">可选列表：<em style="font-style: unset;"><input type="checkbox" name="allSlected"/><span style="cursor:pointer;" class="toSelectAll">全选</span></em>
	      		<div id="hasChooseList" class="listem" style="height:100px;overflow:auto;"></div>
	      	</div> 
	       </div> 
</c:if> 
         </div>
        
        <div class="dis-ib" style="height: 200px;">
          <span>已选择推送列表：</span>
          <div id="hasSelectCompanyList" style="height:150px;overflow:auto;" class="listem">
          	<div style="margin-left:50px;margin-top:30px;">暂未选择部门或企业</div>
          </div>       
        </div>

		<!-- <div class="dis-ib">
          <span>请选择推送类型：</span> 
        </div> -->
        	
		<div style="text-align:center;">
  			<a href="javascript:void(0);" class="btn btn-primary radius" style="width:160px;" id="tosubmit">推送</a>
  		</div>
	</div>
	
  </div>

<input type="hidden" id="utp" value="${userType }"/>
<input type="hidden" id="searchResultInput" />
<script type="text/javascript" src="${ly }/js/modules/common/common.js"></script>
<script type="text/javascript" charset="utf-8" src="${ly }/js/plugins/uedit/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ly }/js/plugins/uedit/ueditor.all.js"></script>
<script type="text/javascript" charset="utf-8" src="${ly }/js/plugins/uedit/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="${ly }/js/plugins/webuploader/webuploader.js"></script>
<script type="text/javascript" src="${ly }/js/modules/userGroup/upload.js"></script>
<script type="text/javascript" src="${ly }/js/plugins/websocket/swfobject.js"></script>
<script type="text/javascript" src="${ly }/js/plugins/websocket/web_socket.js"></script>
<script type="text/javascript" src="${ly }/js/plugins/juicer/juicer.js"></script>
<script type="text/javascript" src="${ly }/js/modules/userGroup/messages2.js"></script>
<script type="text/javascript">
    var websocket = null;
    var userno="${loginUserId}";
    //判断当前浏览器是否支持WebSocket
    WEB_SOCKET_SWF_LOCATION = "${ly }/js/plugins/websocket/WebSocketMain.swf";
    if(window.WebSocket){  
        websocket = new WebSocket("ws://"+location.hostname+":"+location.port+"/village/websocket/"+userno);
	}
    else {
    	//alert("当前版本不支持");
    }


    //连接发生错误的回调方法
    websocket.onerror = function () {
       //alert("error");
        //setMessageInnerHTML("WebSocket连接发生错误");
    };


    //连接成功建立的回调方法
    websocket.onopen = function () {
    	//alert("lianjie");
        //setMessageInnerHTML("WebSocket连接成功");
    }


    //接收到消息的回调方法
    websocket.onmessage = function (event) {
    	//tips_pop();
        //setMessageInnerHTML(event.data);
        //alert(event.data);
    }


    //连接关闭的回调方法
    websocket.onclose = function (e) {
        //setMessageInnerHTML("WebSocket连接关闭");
        //console.log(e);
        //alert("close");
    }


    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        //closeWebSocket();
    }
    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }
    
</script>
<script type="text/javascript">
var ue = UE.getEditor('editor',{
  imageActionName : "<c:url value='/village/new/ueditorUpload'/>",
  imageFieldName : "file",
  imageAllowFiles : ['.png','.jpg','.jpeg','.gif','.bmp']
});
</script> 
</body>
</html>