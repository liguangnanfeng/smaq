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
<%@ include file="/WEB-INF/inc/video.inc"%>
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style type="text/css">
body .dis-ib{margin-right:15px;}

.form-label{
	font-size : 19px;
}
.formControls{
	border: 2px solid #e5e5e5;
}
.desc_class{
	text-align:left;
	margin-top:10px;
}
</style>
<script type="text/javascript" src="/js/html2canvas.js"></script>
<script type="text/javascript" src="/js/dom-to-image.js"></script>

</head>
<body style="text-align:center">
  <!--<nav class="breadcrumb" style="text-align:left;">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>实时监控</span> 
    <span class="c-gray en">&gt;</span> <span>${companyName }实时监控</span> 
    <c:if test="${flag == 3}">
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="parent.close_dialog(function(){})">返回</a>
    </c:if>
    <c:if test="${flag == 1 || flag==2}">
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
    </c:if>
  </nav>-->
  <nav class="breadcrumb" style="text-align:left;">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>实时监控</span> 
    <span class="c-gray en">&gt;</span> <span>${companyName }实时监控</span> 
    <c:if test="${flag != 3}">
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
    </c:if>
    <c:if test="${flag == 3}">
    <c:if test="${before == 1}">
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="show_tab('实施监控设置', '/village/to_monitor-list?flag=1');">返回</a>
    </c:if>
    <c:if test="${before == 2}">
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="show_tab('重点部位关键装置实时监控', '/village/to_monitor-list?flag=2&userId=${company.userId}');">返回</a>
    </c:if>
    </c:if>
  </nav>
<div>&nbsp;</div>
<div style="float:left;display: none;"><!-- wz 18.12.21 -->
		<table>
			<tr>
				<td valign="top" >
					<div id="res_tree" class="easyui-tree" style="width:150px;height:360px;border:1px solid #bebebe;overflow:auto;"></div>
				</td>
			</tr>
			<tr>
				<td valign="top" align="center" style="line-height: 120%;">
				<a id="connect_btn" class="easyui-linkbutton" onclick="connect()" style="display:none;">连接平台</a>
				<a id="disconnect_btn" class="easyui-linkbutton" onclick="disconnect()" >断开平台</a><br /><br />
				<span id="playtoolbar" style="">
					<a id="close_btn" class="easyui-linkbutton" onclick="stop()">关闭视频</a><br /><br />
					<a id="palyaudio_btn" class="easyui-linkbutton" data-options="toggle:true" onclick="playaudio()">播放音频</a><br /><br />
					<a id="record_btn" class="easyui-linkbutton" data-options="toggle:true" onclick="record()">本地录像</a><br /><br />
					<a id="snap_btn" class="easyui-linkbutton" onclick="snap()">本地抓拍</a><br /><br />
					<a id="gps_btn" class="easyui-linkbutton" onclick="startGPS()">开启GPS</a>
					<a id="gps_btn1" class="easyui-linkbutton" onclick="stopGPS()">关闭GPS</a><br /><br />
					<a id="turnup_btn" class="easyui-linkbutton" data-options="toggle:true" onclick="ptzcontrol('turnup')">上</a>&nbsp;&nbsp;<a id="turndown_btn" class="easyui-linkbutton" data-options="toggle:true" onclick="ptzcontrol('turndown')">下</a><br /><br />
					<a id="turnleft_btn" class="easyui-linkbutton" data-options="toggle:true" onclick="ptzcontrol('turnleft')">左</a>&nbsp;&nbsp;<a id="turnright_btn" class="easyui-linkbutton" data-options="toggle:true" onclick="ptzcontrol('turnright')">右</a><br />
				</span>
				</td>
				<td width="15">&nbsp;</td>
			</tr>
		</table>
	</div>
	<c:if test="${flag == 1}"> <!-- col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2   config page-->
		
		<div class="mt-20" style="float:left;margin-left:20px;margin-bottom: 20px;">
        	<button onClick="update_resource()" class="btn btn-primary radius" type="button" style="padding: 0 70px;float: left;">
          		更新视频源
        	</button>
      	</div>
		
		
		<div class="page-container">
    <!--<div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l">
        <a class="btn btn-primary radius" data-title="添加摄像头" data-href="${ly }/company/monitor-add" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 添加摄像头</a>
      </span>
      <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span> 
    </div>-->
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="20%">重点部位/关键装置</th>
            <!-- <th width="50%">视频地址</th> -->
            <th width="15%">编号</th>
            <th width="10%">操作</th>
          </tr>
        </thead>
        <tbody id="camerainfo">

          <!--<c:forEach items="${list }" varStatus="index" var="be">
          <tr class="text-c">
            <td>${index.index+1 }</td>
            <td>${be.name }</td>
            
            <td>${be.memo }</td>
            <td>
              <a style="text-decoration:none" onClick="show_tab('编辑摄像头', '/company/monitor-add?id=${be.id}')" href="javascript:;" title="编辑">查看/编辑</a>
              <a style="text-decoration:none" onClick="del(${be.id})" href="javascript:;" title="删除">删除</a>
              <a style="text-decoration:none" onClick="show_dialog('${be.name}', '/company/monitor-show?id=${be.id }')" href="javascript:;" title="删除">查看监控</a>
            </td>
          </tr>
          </c:forEach>-->
   
        </tbody>
      </table>
    </div>
  </div>
	</c:if>

	<c:if test="${flag == 2}"> <!-- total video-->
	
	<!--<div id="iv_region_center" data-options="region:\'center\',border:true"  style="width:auto;float:left" ></div>-->

	<!--<div id="play_window_container" class="easyui-panel" style="width:100%;height:800px;auto;border:0px solid red;overflow:hidden;float:left;padding-left:15px"></div>-->
	<div  class="easyui-panel" style="text-align:center;width:100%;height:730px;border:0px solid red;overflow:hidden;">
		<div id="play_window_container" style="padding-left:20%;width:60%;height:100%;border:0px solid red;overflow:hidden;"></div>
	</div>
	</c:if>
	
	<c:if test="${flag == 3}"> <!-- one video with config page-->
	<div style="width: 60%;float: left;">
	<div class="easyui-panel" style="text-align:center;width:100%;height:730px;border:0px solid red;overflow:hidden;">
		<div id="play_window_container" style="width:100%;height:100%;border:0px solid red;overflow:hidden;"></div>
	</div>
	</div>
	<!--<div id="showdescId" style="width:30%;height:100%;border:0px solid red;overflow:hidden;float: left;">-->
		<div style="float:left;width:15%;margin-left:15px;">
	  	<div class="desc_class">
	      <label class="form-label">部位</label>
	      <div class="formControls">
	      	<c:choose>
	        	<c:when test="${empty name}">
	        		暂无
	        	</c:when>
	        	<c:otherwise>
	        		${name}
	        	</c:otherwise>
	      	</c:choose>
	        <!--<c:out value="${monitor.name }" default="暂无"/>-->
	      </div> 
	    </div>
	  	<div class="desc_class">
		  	<label class="form-label">风险等级</label>
	      <div class="formControls" 
	      
	      <c:choose>
	        	<c:when test="${empty monitor.dangerLevel }">
	        		
	        	</c:when>
	        	<c:when test ="${monitor.dangerLevel == '1' }">
	        		style="background-color:#ff0000;"
	        	</c:when>
	        	<c:when test ="${monitor.dangerLevel == '2' }">
	        		style="background-color:#fa800a;"
	        	</c:when>
	        	<c:when test ="${monitor.dangerLevel == '3' }">
	        		style="background-color:#ffff00;"
	        	</c:when>
	        	<c:when test ="${monitor.dangerLevel == '4' }">
	        		style="background-color:#0000ff;"
	        	</c:when>
	        </c:choose>
	      >
	        <%-- <c:out value="${monitor.dangerLevel }" default="暂无"/> --%>
	        <c:choose>
	        	<c:when test="${empty monitor.dangerLevel }">
	        		暂无
	        	</c:when>
	        	<c:when test ="${monitor.dangerLevel == '1' }">
	        		<span style="color:#ff0000;">重大风险</span>
	        	</c:when>
	        	<c:when test ="${monitor.dangerLevel == '2' }">
	        		<span style="color:#fa800a;">高风险</span>
	        	</c:when>
	        	<c:when test ="${monitor.dangerLevel == '3' }">
	        		<span style="color:#ffff00;">较大风险</span>
	        	</c:when>
	        	<c:when test ="${monitor.dangerLevel == '4' }">
	        		<span style="color:#0000ff;">一般风险</span>
	        	</c:when>
	        	<c:otherwise>
	        		${monitor.dangerLevel }
	        	</c:otherwise>
	        </c:choose>
	      </div>
	      </div>

	      <div class="desc_class">
	      <label class="form-label">事故类型</label>
	      <div class="formControls">
	      	<c:choose>
	        	<c:when test="${empty monitor.accidentType}">
	        		暂无
	        	</c:when>
	        	<c:otherwise>
	        		${monitor.accidentType}
	        	</c:otherwise>
	      	</c:choose>
	        <!--<c:out value="${monitor.accidentType}" default="暂无"/>-->
	      </div> 
	      </div>
	      <div class="desc_class">
	      <label class="form-label">应急&nbsp;&nbsp;处置方式</label>
	      <div class="formControls">
	      	<c:choose>
	        	<c:when test="${empty monitor.descr}">
	        		暂无
	        	</c:when>
	        	<c:otherwise>
	        		${monitor.descr}
	        	</c:otherwise>
	      	</c:choose>
	        <!--<c:out value="${monitor.descr }" default="暂无"/>-->
	      </div> 
	      </div>
	      <div class="desc_class">
	      <label class="form-label">责任人</label>
	      <div class="formControls">
	      	<c:choose>
	        	<c:when test="${empty monitor.dutyPeople}">
	        		暂无
	        	</c:when>
	        	<c:otherwise>
	        		${monitor.dutyPeople}
	        	</c:otherwise>
	      	</c:choose>
	        <!--<c:out value="${monitor.dutyPeople }" default="暂无"/>-->
	      </div> 
	      </div>
      </div>
      
      
      <div id="imgview"></div>
      
      
      
	</c:if>

</body>
</html>
<script>
var connectId = null;
var resOAIndex = null;
var ResOAChannels = new P_Utils.Hash();
var	ResGPS = new P_Utils.Hash();

// 初始化插件对象，必须初始化成功后才可以调用所有接口
function load(){	
		//初始化插件
		try{
			var rv = P_LY.Init(new P_LY.Struct.InitParamStruct(
				true, 
				function(msg)
				{
					alert(JSON.stringify(msg));
				}
			));
			if(rv.rv != P_Error.SUCCESS)
			{
				return false;
			}
			// 加载默认的连接参数			
	        return true;
		}
		catch(e){
		//	$("#msg_bar").html(e.name + ","+ e.message);
			return false;
		}
}

// 释放插件对象，网页退出必须释放插件，否则会出现浏览器异常的错误
function unload(){
	console.log("unload");
	P_LY.UnLoad();
}
	
function connect(){
	
	//创建连接,并记录在P_LY.Connections中
	var ip = _cf.connParams.ip;
	var port = _cf.connParams.port;
	var epId = _cf.connParams.epId;
	var uid = _cf.connParams.uid;
	var pwd = _cf.connParams.pwd; 
	var bfix = _cf.connParams.bfix;

	// 构造连接对象
	var param = new P_IF.Struct.ConnParamStruct(ip,port,uid,epId,pwd,bfix || 0);

			
	// 连接平台
	var conn = P_LY.Connect(param);
	
	if(conn.rv == P_Error.SUCCESS){
		connectId = conn.response;// 连接成功，返回connectId，此参数很重要，后面很多操作都需要
		$('#connect_btn').hide();
		$('#disconnect_btn').show();
	
		fetch_resource();
		
		// 开接收流状态事件
		P_LY.NCNotifyManager.Add(P_LY.Enum.NCObjectNotify.stream_status_notify,notify);
		
		//init_windows(1);
//		var html = load_windows(9);
//		$('#play_window_container').html(html);	
//		//setTimeout(function(){
//			set_window_size(9);	
//		//},10);		
//		init_window_container();
//		active_wnd($("#play_window_container .wnd")[0].id);

	}else{
		alert(P_Error.Detail(conn.rv));
	}
}


//创建视频窗口并初始化
function init_windows(num){
	var htmlArray = Array();
	for (var i = 0; i < num; i++) {
    htmlArray.push(
      "<div id=\"wnd" + i + "\" class=\"windowbox\">",
      "<div id=\"windowbox" + i + "\" class=\"wnd\">",
      "</div>",
      "</div>"
    );
  }
	
	$('#play_window_container').html(htmlArray.join(""));	
	setTimeout(function(){
		set_window_size(num);	
	},10);		
	init_window_container();
	active_wnd($("#play_window_container .wnd")[0].id);
}

function set_window_size(num)
{
	var s = {width:$("#play_window_container").width(),height:$("#play_window_container").height()};
	var wid;
	var num_line;
	switch(num)
	{
    case 1:
    case 2:
    case 3:
    	num_line = num;
    	break;
    case 4:
    	num_line = 2;
    	break;
    case 5:
    case 6:
    case 7:
    case 8:
    case 9:
   		num_line = 3;
    	break;
    case 10:
    case 11:
    case 12:
    case 13:
    case 14:
    case 15:
    case 16:
    	num_line = 4;
    	break;
    default://max 25
    	num_line = 5;
    	break;
	}
	
	wid = s.width*0.95/num_line;
           
  for (var i = 0; i < num; i++)
  {       
    var wnd = $("#play_window_container .windowbox")[i];
    if(wnd && wnd.id.search("wnd") > -1){
        $("#"+wnd.id)[0].style.width=wid+"px";
        $("#"+wnd.id)[0].style.height=wid*3/4+"px";
//    $("#"+wnd.id)[0].style.width=95/num_line+"%";
//    $("#"+wnd.id)[0].style.height="100%";

      $("#"+wnd.id+ " .wnd")[0].style.height=$("#"+wnd.id)[0].style.height;
    }
  }
}


var containerKeys = new Array();
var curActiveWndId = "";
// 初始化窗口容器
function init_window_container() {
    if ($("#play_window_container .wnd").length > 0) {
        var wndBoxs = $("#play_window_container .wnd");
        for (var i = 0; i < wndBoxs.length; i++) {
            var wndBox = wndBoxs[i];
            
            
//          var windowtitleId = wndBox.id.replace("windowbox", "windowtitle");
//          if($('#'+windowtitleId).length > 0){
//          	$('#'+windowtitleId).unbind().bind("click",function(){
//          		var id = this.id.replace("windowtitle","windowbox");
//          		active_wnd(id);
//          	});
//          }
            if (wndBox && wndBox.id.search("windowbox") != -1) {
            		console.log(wndBox.id);
                wndBox.onclick = function () {
                		//alert(this.id);
                    active_wnd(this.id);
                }
                containerKeys.push(wndBox.id);
                set_wnd_conatiner(wndBox.id, 'wnd');
            }
        }
    }
}
// 激活窗口
function active_wnd(wndId){
    var keys = containerKeys;
    var puid = "",idx = "";;
    for (var i = 0; i < keys.length; i++) {
        var k = keys[i];
        var node = get_wnd_container(k, 'wnd');
      
        if (node && node.container) {
            if (node.container == wndId) {
                node.active = true;
//              var windowtitle = k.replace("windowbox", "windowtitle");
//              if ($('#'+windowtitle)) {
//              	$('#'+windowtitle)[0].style.backgroundColor = "#6681E3";
//              }
                curActiveWndId = k;
              

                if(typeof node.window != "undefined" && node.window != null && typeof node.window.params != "undefined"){
                	                  
                	puid = node.window.params.puid;
                	idx = node.window.params.idx;
                
                }else{
                
                }
				
            }
            else {
          
                node.active = false;
//              var windowtitle = node.container.replace("windowbox", "windowtitle");
//              if ($('#'+windowtitle)) {
//              	$('#'+windowtitle)[0].style.backgroundColor = "#9DBDD8";
//              }
                $('#'+node.container)[0].style.borderColor = "#9CAABD"; //"#9bcaf3"
                $('#'+node.container)[0].style.color = "#000000";
            }
        }
    }
    
}

function set_wnd_conatiner(wndKey, type) 
{
	P_LY.WindowContainers.set(wndKey, new P_LY.Struct.WindowContainerStruct(wndKey, type));
}
   
function get_wnd_container(wndKey, type) 
{
	if(P_LY.WindowContainers.get(wndKey))
	{
		return P_LY.WindowContainers.get(wndKey);
	}
	else
	{
		return null;
	}
}
	   
    
function disconnect(){
	console.log("disconnect video source");
	stop();
	var rv = P_LY.DisConnection(connectId);
	if(rv.rv == P_Error.SUCCESS){
		alert("断开成功");		
		connectId = null;
		$('#connect_btn').show();
		$('#disconnect_btn').hide();
		$('#res_tree').tree('loadData',[]);
	}else{
		alert(P_Error.Detail(rv.rv));
	}
}



function fetch_resource(){
	var offset = 0;
	var cnt = 200;// 这里demo只查询100个资源，实际应用，可以根据需求，如果返回的节点数等于要查询的数，说明可能还有资源，可以继续获取	
	var pulist = new Array();
	var rv = P_LY. ForkResource(connectId,P_LY.Enum.ForkResourceLevel.nppForkPUInfo, offset,cnt, "");
	if(rv.rv == P_Error.SUCCESS){
		var pu = rv.response;
		if (pu && typeof pu == "object" ) {
			pulist = pulist.concat(pu);
		}
	}

		
	var domainRoads = P_LY.Connections.get(connectId).domainRoad;
    if(domainRoads && $.isArray(domainRoads)){
		for(var j = 0; j < domainRoads.length; j++){
			var dr = domainRoads[j].domain_road;
			var operator = P_LY.ForkResource(connectId, P_LY.Enum.ForkResourceLevel.nppForkPUInfo, 0, 200, dr);
			if(operator.rv == P_Error.SUCCESS){
				pulist = pulist.concat(operator.response);
			}
		}
    }

	var nodes = new Array();
	
	$('#res_tree').tree({
		onExpand:function(node){
			
			var childs = $(this).tree('getChildren', node.target);
			
			if (childs.length == 1) {
			    var l = node.children[0];
			    if (l.text == "正在查询资源……") {
					var rv = P_LY.ForkResource(connectId,P_LY.Enum.ForkResourceLevel.nppForkOnePUInfo, 0, 0 , null,{PUID:node.attributes.pu.puid,_HANDLE:node.id});
					if(rv.rv == P_Error.SUCCESS){
						var nodes = new Array();
						for(var j = 0;j < rv.response.childResource.length;j++){
							var pures = rv.response.childResource[j];
							
							// 这里只列出视频输入资源
							if(pures.type == P_LY.Enum.PuResourceType.VideoIn){
								nodes.push({id:pures._HANDLE,text:pures.name,attributes:{pu:node.attributes.pu,self:pures}});	
							}
						}
						
                        if (nodes.length > 0) {
                            // 删除那个load节点
                            for (var i = 0; i < childs.length; i++) {
                                $('#res_tree').tree('remove', childs[i].target);
                            }

                            $('#res_tree').tree('append', {
                                parent: node.target,
                                data: nodes
                            });
                        }
                        
					}else{
						l.text = "没有可用的资源";
					}
			    }
			}
		},
		onCollapse:function(node){
			//console.log(node)
		},
		onDblClick:function(node){
			
			var attr = node.attributes;
			if(attr.pu.online != "1"){
				log(pu.name+"不在线");
				return;
			}
			//alert(attr.pu.name);
			//alert(JSON.stringify(attr.self));
			play(attr.pu,attr.self);
			return true;
		}
	});
	if(pulist.length > 0){
		
		for(var i =0;i <pulist.length;i++){
			var pu = pulist[i];
		//	if(pu.modelType == "ENC" || pu.modelType == "WENC"){
				nodes.push({id:pu._HANDLE,text:pu.name,attributes:{pu:pu},state:"closed",children:[{text:'正在查询资源……'}]});
		//	}
		}
		if(nodes.length > 0){
			$('#res_tree').tree('loadData',nodes);
			
		}
	}else{
		log("没有获取资源,rv="+rv.rv);
	}
}

function play(pu,pures){

	// 窗口对象一定放到WindowContainers，这个请开发人员注意
	var wndId = curActiveWndId;
	var node = P_LY.WindowContainers.get(wndId);

		
	var play = true;		
	if (play) 
	{
		
			var create = true;
			if (node.window != null) 
			{
				create = false;
				if (node.window.status.playvideoing) 
				{
					stop(wndId);
				}
	
			}
			
			if (create) 
			{
					var menu_params = 	{		
					menu_command: {
						status:true,
						menu:[
								{key:"info",text:"查看详情"},
								{key:"stopvideo",text:"停止视频"},
								{key:"snapshot",text:"抓拍"},
								{key:"record",text:"本地录像"},
								{key:"playaudio",text:"播放音频"},
								{key:"playtalk",text:"开始对讲"}
						],
						callback:function(key){
							switch(key){
								case "info":
									info(wndId);
								break;
								case "stopvideo":
									stop(wndId);
								break;
								case "snapshot":
									snap(wndId);
								break;
								case "record":
									record(wndId);
								break;
								case "playaudio":
									playaudio(wndId);
								break;
								case "playtalk":
									play_talk(wndId);
									break;
							
							}
						}
					}
				}
				var wndEvent = new P_LY.Struct.WindowEventStruct();
				wndEvent.menu_command.status = menu_params.menu_command.status;
				wndEvent.menu_command.menu = menu_params.menu_command.menu;
				wndEvent.menu_command.callback = menu_params.menu_command.callback;
								
				var rv  = P_LY.CreateWindow(connectId, wndId, P_LY.Enum.WindowType.VIDEO,wndEvent);
						
				if(rv.rv != P_Error.SUCCESS)
				{
					alert("创建窗口失败,rv"+rv.response);
					return false;
				}
				var window = rv.response;
			
			
				node.window = window;
				P_LY.WindowContainers.get(wndId).window = node.window = window;
				P_LY.WindowContainers.get(wndId).description = pures;
				
				var operator = P_LY.PlayVideo(connectId, window, pu.puid, pures.idx);
				
				P_LY.ResizeWindowDimension(window,"100%","100%");
				if(operator.rv != P_Error.SUCCESS)
				{
					if(operator.rv == -1){//zhangcl 2018.12.19
						alert("该资源已在播放！请不要重复播放");
						return false;
					}
					P_LY.ResizeWindowDimension(window,0,0);
					//alert("播放视频失败,rv ="+operator.rv+",response="+operator.response);
					//alert(wndId);
					//$("#"+wndId).text("Hello world!");
					$("#"+wndId)[0].style["font-size"]=14+"px";
					$("#"+wndId)[0].style["vertical-align"] = "middle";
					$("#"+wndId)[0].style.color="red";
					$("#"+wndId).text("播放失败,请检查视频源！");
					return false;
				}
				
				$('#playtoolbar').show();
		
			}
		
	}
	else
	{
		if(_cf.debug) console.log("该资源已在播放！请不要重复播放");
        return false;
	}
	
	return true;
}

//查询GPS历史数据

function QueryHistoryGPSData(connectId,puid){
	
	var xmlObjSets = '<OSets><Res OType="151" OID="'+puid+'" Type="GNNS" Idx="0" /></OSets>';
	var param = '<Param Offset="1" Count="200" Begin="1462337586" End="1462355586"/>';

	var rv = P_LY.Control.CommonGet(
		connectId,
		{
			puid: puid,
			resType:"ST",
			resIdx:0,
			controlID:"C_GS_QueryHistoryGPSData",
			xmlObjSets:xmlObjSets,
			param:param
		}
	).response;
	var gpsdata = new Array();
	if(rv.M.C.Res && rv.M.C.Res.Error == "0"){
		var gpsPage = rv.M.C.Res.Param.Res.GPS;
		gpsdata = gpsdata.concat(gpsPage);
	}
	console.log(gpsdata)
}

function stop(wndId){
	if(!P_LY.WindowContainers.get(wndId)){
		return;
	}
//	log("关闭视频"+P_LY.WindowContainers.get('windowbox').window.customParams.cameraName);
	P_LY.StopVideo(P_LY.WindowContainers.get(wndId).window);
	P_LY.ResizeWindowDimension(P_LY.WindowContainers.get(wndId).window,0,0);
	$("#windowtitle").html("无视频");
	$('#playtoolbar').hide();
}

function info(wndId){
	if(!P_LY.WindowContainers.get(wndId)){
		return;
	}
	//alert(camjson[wndId]);
	if('${flag}' == 2)
		show_tab(camjson[wndId], encodeURI(encodeURI("/village/to_monitor-list?before=2&flag=3&userId=${company.userId}&name="+camjson[wndId])));
}

function playaudio(wndId){
	
	if(P_LY.WindowContainers.get(wndId)){
		var wnd = P_LY.WindowContainers.get(wndId).window;
		if(wnd.containerId == wndId){
			if(wnd.status.playvideoing){
				var paStop = P_LY.PlayAudio(wnd);
				if(wnd.status.playaudioing){
					P_LY.WindowAttachEvent.UpdateMenuCommand(wnd, [{key:"playaudio",text:"停止音频"}]);
				//	log("播放音频");
				}else{
					P_LY.WindowAttachEvent.UpdateMenuCommand(wnd, [{key:"playaudio",text:"播放音频"}]);
				//	log("停止音频")
				}
			}
		}
	}
}

function record(wndId){
	var path = "d://测试";
	var wnd = P_LY.WindowContainers.get(wndId).window;
	if(typeof wnd != "object"){
		return false;
	}
	
	if(wnd.containerId == wndId){
		if(wnd.status.playvideoing){
			var path = "测试.avi";
			var rv = P_LY.LocalRecord(wnd,"d:/",path );
			if(wnd.status.recording){
				P_LY.WindowAttachEvent.UpdateMenuCommand(wnd, [{key:"record",text:"停止录像"}]);
			//	log("开始录像,保存路径为:"+path);
			}
			else{
				P_LY.WindowAttachEvent.UpdateMenuCommand(wnd, [{key:"record",text:"本地录像"}]);
			//	log("停止录像");
			}
		}
	}
}

function snap(wndId){
	var path = "C:/";
	var wnd = P_LY.WindowContainers.get(wndId).window;
	if(typeof wnd != "object"){
		return false;
	}
	if(wnd.containerId == wndId){
		if(wnd.status.playvideoing){
			var rv = P_LY.Snapshot(wnd,path);
			console.log("抓拍成功,保存路径为:"+path+",rv:"+JSON.stringify(rv));
		}
	}
}

function check_talkenable(connectId,puid) {

	var rv = P_LY.ForkResource(connectId,P_LY.Enum.ForkResourceLevel.nppForkPUResourceInfo,0,200,null,{PUID:puid});
	var re = rv.response.childResource;
	
	for (var i = 0; i < re.length; i++) {
		var childRes = re[i];
		if(childRes.type == P_LY.Enum.PuResourceType.AudioOut){
			try{
				ResOAChannels.set(puid + "_" + childRes.idx, { puid: puid, index: childRes.idx, windowKey: "", flag: false });	
			}catch(e){
				console.log(e.message);
			}
		}
	}
	var found = false, activeResOAIdx = 0, flag = -1;

	ResOAChannels.each
		(
			function (ee) {
				var e = ee.value;	
				if (e.puid == puid) {
					flag = 0; // 存在OA资源
					if (found == false && e.flag != true) {
						activeResOAIdx = e.index;
						found = true;
					}
				}
			}
		);
	if (found == true){flag = 1}
	resOAIndex = activeResOAIdx;
	return [flag, activeResOAIdx];
}

function play_talk(wndId){

	var node = P_LY.WindowContainers.get(wndId);

	if(node){
			var puid = node.window.params.puid;
			try{
				var connectId =  node.window.connectId;
				var rv_arr = check_talkenable(connectId,puid) || [-1, -1];

			}catch(e){
				
			}
		
			switch (rv_arr[0]) {
				case -1:
				alert("设备不支持对讲！");
					return false;
					break;
				case 0:
				alert("资源已被占用，无法开启对讲！");
					return false;
					break;
				default: 
					break;
			}
			var resOAIdx = rv_arr[1];
			P_LY.WindowContainers.each(
				function(item){
					if(item.value.window != null){
						var connectId =  item.value.window.connectId;
						console.log(item.value.window)
						if(item.value.window.status.playvideoing && item.value.window.status.talking){
						
							var rv = P_LY.CallTalkControl.Stop(connectId,item.value.window.params.puid,resOAIndex);
							console.log(rv)
							if(rv.rv == P_Error.SUCCESS){
								item.value.window.status.talking = false;
								item.value.window.customParams.talk = "start";
								P_LY.WindowAttachEvent.UpdateMenuCommand(item.value.window, [{key:"playtalk",text:"开始对讲"}]);
							//	log("停止对讲！");
							}
						}else{
							var rv = P_LY.CallTalkControl.StartTalk(connectId,item.value.window.params.puid,resOAIndex);
							console.log(rv)
							if(rv.rv == P_Error.SUCCESS){
								node.window.status.talking = true;
								node.window.customParams.talk = "end";
								P_LY.WindowAttachEvent.UpdateMenuCommand(item.value.window, [{key:"playtalk",text:"停止对讲"}]);
							//	log("开始对讲！");
							}
						}
						return;
					}
				});
	}
}
  
    
function ptzcontrol(director){
	var wndId = curActiveWndId;
	var wnd = P_LY.WindowContainers.get(wndId).window;
	if(typeof wnd != "object"){
		return false;
	}
	
	var mode = P_LY.Enum.PTZDirection[director];
	
	if(typeof mode == "undefined"){
		mode = P_LY.Enum.PTZDirection.stopturn;
	}
	if($('#'+director+"_btn").length > 0){
		var opts = $('#'+director+"_btn").linkbutton('options');
		if(opts.selected == true){
			mode = P_LY.Enum.PTZDirection.stopturn;
		}
	}
	if(wnd.containerId == wndId){
		if(wnd.status.playvideoing){
			var puid = wnd.params.puid || "";
			var idx = wnd.params.idx || "";
			var mode = mode || "";
			var operator = P_LY.PTZ.Control(connectId, puid, idx, mode);
			if(operator.rv != P_Error.SUCCESS){
				alert("操作云台失败，错误码："+operator.rv);
			}
		}                                                                                                                                                                                                                                                                                                                          
	}
}

function reset(){
	$('#conn_form').form('reset');
}

function notify(args){

	if(typeof args == "object"){
		P_LY.WindowContainers.each(
		function(item){
			var node = item.value;
			if(node.window){
				var html1 = "",html2 = "",htmlstr = "";
				if(args.errorCode == P_Error.SUCCESS){
					switch(Number(args.status)){//-1表示流已断开，0表示正在缓冲，1表示正在播放,2表示正在下载，3表示下载完成
					case -1:
						html1 = _lp.frame.player.notes.streambreak;//"流已断开"
						break;
					case 0:
						html1 =  _lp.frame.player.notes.buffering;//"正在缓冲"
						break;
					case 1:
						html1 = _lp.frame.player.notes.playing;//"正在播放"
						break;
					case 2:
						html1 = _lp.frame.player.notes.downing;//"正在下载"
						break;
					case 3:
						html1 = _lp.frame.player.notes.downdone;//"下载完成"
						break;
					}
					var _bite = function(bite){
						return (bite / 1000).toFixed(0) + "KB/s";
					}
					var bitrate = args.keyData.bit_rate;
					var bite = _bite(bitrate)
					var framerate = args.keyData.frame_rate;
					var frame = framerate;
					
					var title2 = '<a class="snapshot"  title="'+_lp.frame.player.notes.snapshot+'" onclick="_pl.snapshot(\''+node.window.containerId+'\')" ></a>&nbsp;';
					var recStr = "";
					if(node.window.status.recording){
						recStr = _lp.frame.player.notes.recording;//"正在录像"
						title2 += '<a class="record"  title="'+_lp.frame.player.notes.recording+'"  onclick="_pl.record(\''+node.window.containerId+'\')"  ></a>&nbsp;';
					}else{
						recStr = "";
						title2 += '<a class="record"  title="'+_lp.frame.player.notes.record+'"  onclick="_pl.record(\''+node.window.containerId+'\')"  ></a>&nbsp;';
					}
					var audStr = "";
					if(node.window.status.playaudioing){
						audStr = _lp.frame.player.notes.audioing;//"正在播放声音"
						title2 += '<a class="audio"  title="'+_lp.frame.player.notes.audioing+'" onclick="_pl.play_audio(\''+node.window.containerId+'\')"></a>&nbsp;';
					}else{
						audStr = "";
						title2 += '<a class="audio"  title="'+_lp.frame.player.notes.playaudio+'" onclick="_pl.play_audio(\''+node.window.containerId+'\')"></a>&nbsp;';
					}
					html2 = _lp.frame.player.notes.frame+"："+frame+","+_lp.frame.player.notes.bite+"："+bite;
					if(args._HANDLE == node.window.params.ivStreamHandle){
						var windowtitlekey = $("#"+node.window.containerId+"").next().attr("id");
						//$("#"+windowtitlekey+" .title1")[0].innerHTML =  html1 +","+ html2+","+ recStr+","+audStr+","+upaudStr+","+talkStr;
						$("#"+windowtitlekey+" .title1")[0].innerHTML =  html1 +","+ html2;
						if($("#"+windowtitlekey+" .title2")[0].innerHTML == ""){
							$("#"+windowtitlekey+" .title2")[0].innerHTML =  title2;//'<input type=button class="audio_onmouseover"  title="'+audStr+'"/>&nbsp;<input type=button class="snapshot_onmouseover" title="'+_lp.frame.player.notes.snapshot+'" />&nbsp;&nbsp;';	
						}else{
							if($("#"+windowtitlekey+" .title2 .record").length > 0){
								var t = (node.window.status.recording ? _lp.frame.player.notes.recording :_lp.frame.player.notes.record );
								if($("#"+windowtitlekey+" .title2 .record").attr('title') != t){
									
									$("#"+windowtitlekey+" .title2 .record").attr('title',t);
								}
							}
							if($("#"+windowtitlekey+" .title2 .audio").length > 0){
								var t = (node.window.status.playaudioing ? _lp.frame.player.notes.audioing :_lp.frame.player.notes.playaudio );
								if($("#"+windowtitlekey+" .title2 .audio").attr('title') != t){
									
									$("#"+windowtitlekey+" .title2 .audio").attr('title',t);
								}
							}
						}
					}
				}
				else{
					html1 = _lp.frame.player.notes.playfailed;//"播放失败";
				}
			}	
		});
	}
}

function findmome(camera,dbname,dbmemo){
	for(var i=0;i<dbname.length;i++){
		if(camera==dbname[i]) return dbmemo[i];
	}
	return "";
}

if(window.attachEvent){
	window.attachEvent(
		"onload",
		function() {
			load();  
		} 
	);
	window.attachEvent(
		"onunload",
		function(){
			unload();
		}
	);
	window.attachEvent(
		"onbeforeunload",
		function(){
			unload();
		}
	);
}else{
	window.addEventListener (
        "load",
        function() {
			load();  
        },
        false
    );
	window.addEventListener (
        "unload",
        function() {
			unload();
        },
        false
    );
	window.addEventListener (
        "beforeunload",
        function() {
			unload();
        },
        false
    );
}

//tree 的用法
////某一节点下的所有子节点
//function getChildren(id/*节点ID*/){
//  var $tree = $('#tree');
//  var node = $tree.tree('find',id);
//  var childrenNodes = $tree.tree('getChildren',node.target);
//  return childrenNodes;
//}
////某节点的一级节点
//function getSubChildren(id){
//  var $tree = $('#tree');
//  var node = $tree.tree('find',id);
//  var subNodes = [];
//  $(node.target)
//  .next().children().children("div.tree-node").each(function(){   
//      subNodes.push($tree.tree('getNode',this));
//  });
// return subNodes;
//}
function update_resource(){
	var puids='';
	var ids='';
	var names='';
	var userId = '${userId}';
	if(camreso.length==0){
		
		//return;//不返回就删除所有摄像头
	}
	else{
		puids = camreso[0].attributes.pu.puid;
		ids = camreso[0].attributes.self.idx;
		names = camreso[0].attributes.self.name;
		for(var i=1;i<camreso.length;i++){
			puids += ","+camreso[i].attributes.pu.puid;
			ids += ","+camreso[i].attributes.self.idx;
			names += ","+camreso[i].attributes.self.name;
		}
	}
	
	var index = layer.load();
	//console.log("obj:"+JSON.stringify(obj));
	$.ajax({
	    type : "POST",
	    url : getRootPath() + encodeURI(encodeURI('/village/monitor-update?puids='+puids+'&ids='+ids+'&names='+names+'&userId='+userId)),
	    //data : JSON.stringify(obj),
	    async: false,
	    //dataType : "json",
	    //contentType : 'application/json',
	    success : function(result) {
		    if(result.status != '0') {
		        layer.alert(result.map.message);
		    }
	    },
	    complete : function(XMLHttpRequest, textStatus) {
	    	layer.close(index);
	    	show_tab('实时监控', '/village/to_monitor-list?flag=1');
	    },
	    error : function(XMLHttpRequest, textStatus, errorThrown) {
	    	alert("更新失败")
	    }
  	});
}

var flag = ${flag};
//alert(flag);
var cameras = new Array();
var camjson = new Object();
var camreso = new Array();
//自动连接平台,播放视频,zhangcl 2018.12.19
setTimeout(function(){
	connect();
	var roots = $('#res_tree').tree('getRoots');
	//alert(roots[0].target.innerHTML);
	var user = '${company.name}';
	var root;
	//if(user=='zyp02')user='无锡安泰';//测试用
	for(var i=0;i<roots.length;i++){
		if(roots[i].text == user){
			root = roots[i];
		}
	}
	if(!root){
		alert("本公司无视频接入系统！");
		return;
	}
	//alert(user);
	//alert(JSON.stringify(root));
	
	$('#res_tree').tree('expand',root.target);
	var children = $('#res_tree').tree('getChildren',root);
	
	var videonum = 0;
	var containerid = 0;

	//搜索有效视频源
	for(var i=0;i<=children.length;i++){
		if(children[i]&&children[i].attributes&&children[i].attributes.self&&children[i].attributes.self.name){
			//console.log(JSON.stringify(children[i].attributes));
			//console.log(children[i].attributes.self.name);
			cameras.push(children[i].attributes.self.name);
			camreso.push(children[i]);

			videonum++;
		}
	}
	//alert(cameras.length);
	
	if(flag==1){//配置页
		
		var dbname = new Array();
		var dbmemo = new Array();
		
		//alert(JSON.stringify(list));
		<c:forEach items="${list}" var="t">  
	     dbname.push("${t.name}"); 
	     //alert("${t.name}");
	     dbmemo.push("${t.memo}");
	  </c:forEach>
	  var html = "";
	  for(var i=0;i<cameras.length;i++){
	  	html += "<tr class='text-c'>"
	  						+"<td>"+(i+1)+"</td>"
	  						+"<td>"+cameras[i]+"</td>"
	  						+"<td>"+findmome(cameras[i],dbname,dbmemo)+"</td>"
	  						+"<td>"
    							+"<a style='text-decoration:none' onClick=\"show_tab('编辑摄像头', '"+  encodeURI(encodeURI("/village/monitor-add?userId="+'${userId}'+"&name="+cameras[i]))  +"'"+")\" href='javascript:;' title='编辑'>查看/编辑</a>"
              		+"<a style='text-decoration:none' onClick=\"show_tab('"+cameras[i]+"', '"+   encodeURI(encodeURI("/village/to_monitor-list?userId="+'${userId}'+"&before=1&flag=3&name="+cameras[i]))  +"')\" href='javascript:;' title='删除'>查看监控</a>"
            		+"</td>"
	  				+"</tr>";
	  }
	  //alert(html);
	  $("#camerainfo").html(html);
	  
	}
	else if(flag==2){//播放全部
	
	//根据视频源数量初始化显示窗口
	init_windows(videonum);
	//init_windows(5);
	
	//循环播放视频
	for(var i=0;i<=children.length;i++){
		if(children[i]&&children[i].attributes&&children[i].attributes.self&&children[i].attributes.self.name){
			console.log(JSON.stringify(children[i]));
			var attr = children[i].attributes;
			if(attr.pu.online != "1"){
				log(attr.pu.name+"不在线");
			}
			if(containerid>=containerKeys.length) break;
			curActiveWndId = containerKeys[containerid];
			camjson[curActiveWndId] = children[i].attributes.self.name;

			//全部播放
			var ret = play(attr.pu,attr.self);
			if(ret){
				console.log(children[i].attributes.self.name+" 播放成功！"); 
			}
			else{
				console.log(children[i].attributes.self.name+" 播放失败！"); 
			}
			containerid++;
		}
	}
	
	}
	else if(flag==3){//查看单个详情
		//根据视频源数量初始化显示窗口
	init_windows(1);
	//init_windows(5);
	//alert('${name}');
	//播放选定视频
	for(var i=0;i<=children.length;i++){
		if(children[i]&&children[i].attributes&&children[i].attributes.self&&children[i].attributes.self.name
			&&children[i].attributes.self.name=='${name}'){
			var attr = children[i].attributes;
			if(attr.pu.online != "1"){
				log(attr.pu.name+"不在线");
			}
			if(containerid>=containerKeys.length) break;
			curActiveWndId = containerKeys[containerid];

			//全部播放
			var ret = play(attr.pu,attr.self);
			if(ret){
				console.log(children[i].attributes.self.name+" 播放成功！"); 
			}
			else{
				console.log(children[i].attributes.self.name+" 播放失败！"); 
			}
			containerid++;
		}
	}
	}
	
},1);

//setTimeout(function(){
//	drawImage();
//},7000);
//
//drawImage = function(){
//	console.log('drawImage');
//	//var c=document.createElement()ElementById("myCanvas");
//	var c= document.createElement("canvas"); 
//	var ctx=c.getContext("2d");
//	var img=document.getElementById("windowbox0_wnd");
//	ctx.drawImage(img,10,10);
//	var src = c.toDataURL("image/jpeg",0.4);
//	$('#imgview').html('<img id="imgSmallView" src="' + src + '" alt="预览图" />');
//
//
//	html2canvas($("#windowbox0")).then(canvas => {//object不支持，目前无法实现
//	document.body.appendChild(canvas);
//	});
//}

</script>