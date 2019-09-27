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
</head>
<body style="text-align:center">
  <nav class="breadcrumb" style="text-align:left;">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>实时监控</span> 
    <span class="c-gray en">&gt;</span> <span>${companyName }实时监控-img</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
<%--    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:history.back(-1)" title="返回">返回</a>--%>
    <%--<a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px; margin-right:10px" href="javascript:void(0);" title="返回" onclick="show_tab('重点部位监控', '/village/monitor-list-supervise');">返回</a>--%>
  </nav>
<div>&nbsp;</div>

<div  class="easyui-panel" style="text-align:center;width:100%;height:730px;border:0px solid red;overflow:hidden;">
	<div id="play_window_container" style="padding-left:20%;width:60%;height:100%;border:0px solid red;overflow:hidden;"></div>
</div>

</body>
</html>
<script>

//创建视频窗口并初始化
function init_windows(num){
	var htmlArray = Array();
	for (var i = 0; i < num; i++) {
    htmlArray.push(
      "<div id=\"wnd" + i + "\" class=\"windowbox\">",
      "<div id=\"windowbox" + i + "\" class=\"wnd\">",
      "<img id=\"img" + i + "\" ondblclick=\"showinfo("+i+")\" style=\"width:100%;height:100%\" onerror=\"this.src='/images/zwtp.jpg;this.onerror=null'\"/>",
      "</div>",
      "</div>"
    );
  }
	
	$('#play_window_container').html(htmlArray.join(""));	
	setTimeout(function(){
		set_window_size(num);	
	},10);		
	//init_window_container();
	//active_wnd($("#play_window_container .wnd")[0].id);
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


function showinfo(i){
	var channelId = ids[i];
	var name = camobj[channelId];
	show_tab(name, encodeURI(encodeURI("/village/to_monitor-list?before=2&flag=3&userId=${company.userId}&name="+name)));
	
}

var cameras = new Array();
var ids = new Array();
var camobj = new Object();
var userId = '${userId}';
var puid = new Object();
var imgpath = new Object();

var videonum = 0;

<c:forEach items="${list}" var="t">  
　　cameras.push('${t.name}');
   ids.push('${t.channelId}');
   if('${t.channelId}'!=''){
   		camobj['${t.channelId}'] = '${t.name}';
   		puid['${t.channelId}'] = '${t.puid}';
   		imgpath['${t.channelId}'] = '${t.img}';
   		
   }
</c:forEach>

//ids = ids.sort();
videonum = cameras.length;
console.log("userId:"+userId+",videonum:"+videonum);
console.log(cameras);
console.log(ids);
console.log('{puid}');
console.log($("#play_window_container").width());

//根据视频源数量初始化显示窗口
init_windows(videonum);


for(var i=0;i<videonum;i++){
	if(ids[i]==''){
		$("#img"+i).attr('src','/images/zwtp.jpg');//默认图片
	}
	else{
//		var id = ids[i];
//		if(ids[i]<10){
//			id = '0'+ids[i];
//		}
		//$("#img"+i).attr('src','/images/videoimg/'+puid[ids[i]]+'/'+id+'.jpg');
		$("#img"+i).attr('src',imgpath[ids[i]]);
	}
}






//setTimeout(function(){
//	drawImage();
//},7000);
//drawImage = function(){
//	console.log('drawImage');
//	html2canvas(document.querySelector("#wnd0")).then(canvas => {
//  	document.body.appendChild(canvas);
//	});
//}

</script>