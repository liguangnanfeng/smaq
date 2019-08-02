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
<link href="${ly }/js/plugins/laypage/skin/laypage.css" rel="stylesheet"/>
<style type="text/css">
body .dis-ib{margin-right:15px;}
.page-container {
    padding: 20px;
    padding-top: 0px;
}
.caption {
    bottom: 0px;
    padding: 10px 0px 0px 0px;
    box-sizing: border-box;
    height: 45px;
    line-height: 36px;
}
.caption .text-box {
    width: 34px;
    height: 28px;
    margin-top: 5px;
    float: right;
    line-height: 28px;
    background-color:#000;
}
.laypage_main a, .laypage_main span {
    margin-left: 5px;
    background-color: #fff;
    width: auto;
    line-height: 33px;
    padding: 4px 2px;
    border: 1px solid #d2d2d2;
    border-radius: 2px;
    text-align: center;
    min-width: 33px;
    line-height:20px !important;
    height:20px !important;
}
	body .layui-layer-setwin .layui-layer-close1{
		background: url('${ly}/images/return.png') !important;
		background-position: -11px 0px !important;
	}
</style>
</head>
<body id="monitorBody" style="min-width:1000px;">
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>实时监控</span> 
    <span class="c-gray en">&gt;</span> <span>${companyName }实时监控</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="mt-20">
      	<c:forEach items="${list }" var="m" varStatus="mv">
      		<div style="float:left;" core-i="${m.id }" core-thumbnail="${m.thumbnail }"  core-url="${m.code }" core-id="video_${mv.index }" core-name="${m.name }" core-desc="${m.descr }" core-level="${m.dangerLevel }" class="video" id="video_${mv.index }">
      		 </div>
      	</c:forEach>
    </div>
  </div>
  <div id="demo11" style="text-align:center;padding-top: 8px;"></div>
  
  <script type="text/javascript" src="${ly }/js/editckplayer/ckplayer/ckplayer.js"></script>
  <script type="text/javascript" src="${ly }/js/plugins/laypage/laypage.js"></script>
<script type="text/javascript">
var total = "${total}";
var page = "${page}";
var currLimit = parseInt("${currLimit}");

function calculateSize(){
  var bodyHeight = document.getElementById("monitorBody").clientHeight-40-20;//$("#monitorBody").height();
  var bodyWidth = document.getElementById("monitorBody").clientWidth-40;//$("#monitorBody").width();
  if(currLimit <= 2 ){
    if(bodyHeight > bodyWidth){
      if(currLimit == 1){
        var perWidth = bodyWidth;
        var perHeight = parseInt(perWidth/1.21);
        $(".video").css("width",perWidth);
        $(".video").css("height",perHeight);
      }else if(currLimit == 2){
        var perWidth = bodyWidth/2;
        var perHeight = parseInt(perWidth/1.21);
        $(".video").css("width",perWidth);
        $(".video").css("height",perHeight);
      }
    }else{
      var perHeight = bodyHeight;
      var perWidth = parseInt(perHeight*1.21);
      $(".video").css("width",perWidth);
      $(".video").css("height",perHeight);
      var totalWidth = perWidth;
      if(currLimit == 2){
        totalWidth = perWidth*2;
      }
      var spaceWidth = (bodyWidth - totalWidth) /2;
      $("#video_0").css("margin-left",spaceWidth);
    }
   
  }else if(currLimit > 2 && currLimit <= 4 ){
	if(bodyHeight > bodyWidth){
	    var perWidth = bodyWidth/2;
	    var perHeight = parseInt(perWidth/1.21);
	    $(".video").css("width",perWidth);
	    $(".video").css("height",perHeight);
    }else{
      var perHeight = bodyHeight/2;
      var perWidth = parseInt(perHeight*1.21);
      $(".video").css("width",perWidth);
      $(".video").css("height",perHeight);
      var totalWidth = perWidth*2;
      var spaceWidth = (bodyWidth - totalWidth) /2;
      $("#video_0").css("margin-left",spaceWidth);
      $("#video_2").css("margin-left",spaceWidth);
    }
    
  }else if(currLimit > 4 && currLimit <=6 ){
	if(bodyHeight > bodyWidth){
	    var perWidth = bodyWidth/3;
	    var perHeight = parseInt(perWidth/1.21);
	    $(".video").css("width",perWidth);
	    $(".video").css("height",perHeight);
    }else{
      var perHeight = bodyHeight/2;
      var perWidth = parseInt(perHeight*1.21);
      $(".video").css("width",perWidth);
      $(".video").css("height",perHeight);
      var totalWidth = perWidth*3;
      var spaceWidth = (bodyWidth - totalWidth) /2;
      $("#video_0").css("margin-left",spaceWidth);
      $("#video_3").css("margin-left",spaceWidth);
    }
    
  }else if(currLimit > 6){
	if(bodyHeight > bodyWidth){
	    var perWidth = bodyWidth/3;
	    var perHeight = parseInt(perWidth/1.21);
	    $(".video").css("width",perWidth);
	    $(".video").css("height",perHeight);
    }else{
      var perHeight = bodyHeight/3;
      var perWidth = parseInt(perHeight*1.21);
      $(".video").css("width",perWidth);
      $(".video").css("height",perHeight);
      var totalWidth = perWidth*3;
      var spaceWidth = (bodyWidth - totalWidth) /2;
      $("#video_0").css("margin-left",spaceWidth);
      $("#video_3").css("margin-left",spaceWidth);
      $("#video_6").css("margin-left",spaceWidth);
    }
    
  }
}
 
  calculateSize();
  $(window).on("resize",function(){
    calculateSize();
  })
  
 var firstUrl = "";
$(".video").each(function(index,ev){
  var id = $(ev).attr("core-id");
  var url = $(ev).attr("core-url");
  var i = $(ev).attr("core-i");
  var name = $(ev).attr("core-name");
  var thumbnail = $(ev).attr("core-thumbnail");
  //var width = $(".video").width();
 // var height = width/4*3;
  //$(".video").css("height",height);
  /* if(undefined != thumbnail && "" != thumbnail){
    $(this).html('<div><img src="'+thumbnail+'" width="100%" height="100%" /></div>');
    setTimeout(function(){
			var html = '';
			html += '<span class=" cf caption ckidclass" style="position:relative;display:none;float: left;bottom: 44px;" id="ck_'+id+'">';
			html += '	<a href="javascript:;" style="color:#fff;" core-name="'+name+'" core-id="'+i+'" class="text-box fr ">全屏</a>';
			html += '</span>';
			$("#"+id).children("div").last().append(html);
			
			$(".text-box").unbind("click").bind("click",function(){
			  var cname = $(this).attr("core-name");
			  var cid = $(this).attr("core-id");
			  show_dialog(cname, '/company/monitor-show?id='+cid);
			});
  	});
  }else{ */
    var videoObject = {
    		container: '#'+id,//“#”代表容器的ID，“.”或“”代表容器的class
    		variable: 'player',//该属性必需设置，值等于下面的new chplayer()的对象
    		autoplay:true,//自动播放
    		poster:thumbnail,
    		live:true,
    		video:url//视频地址
    	};
    	var player=new ckplayer(videoObject);
    	
    	setTimeout(function(){
  			var html = '';
  			html += '<div class="caption text-box" core-name="'+name+'" title="'+name+'" core-id="'+i+'" style="position:absolute;width:100%;height:100%;cursor:pointer;" >';
  			//html += '<div class=" cf caption ckidclass" style="position:absolute;display:none;" id="ck_'+id+'">';
  			//html += '	<a href="javascript:;" style="color:#fff;" core-name="'+name+'" core-id="'+i+'" class="text-box fr ">全屏</a>';
  			html += '</div>';
  			$("#"+id).children("div").last().append(html);
  			
  			$(".text-box").unbind("click").bind("click",function(){
  			  var cname = $(this).attr("core-name");
  			  var cid = $(this).attr("core-id");
  			  show_dialog(cname, '/company/monitor-show?id='+cid);
  			});
    	});
  //}
 
  });
  
  $(".video").on("mouseover",function(){
    $(".ckidclass").hide();
    var curr_id = $(this).attr("core-id");
    $("#ck_"+curr_id).show();
  })
  
  $(".video").on("mouseout",function(){
    var curr_id = $(this).attr("core-id");
    $("#ck_"+curr_id).hide();
  })

  laypage({
    cont : 'demo11',
    pages : Math.ceil(parseInt(total) / 9),
    curr : page || 1,
    first : '首页',
    last : '尾页',
    jump : function(obj, first) {
      if (!first) {
        location.href = "${ly }/village/to_monitor-list?page=" + obj.curr+"&userId=${userId}&companyName=${companyName}";
      }
    }
  });
  
</script> 
</body>
</html>