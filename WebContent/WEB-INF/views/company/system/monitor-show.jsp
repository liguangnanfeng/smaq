<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="/WEB-INF/views/taglibs.jsp" %>
    <!DOCTYPE HTML>
    <html>
    <head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
    content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>安全分级管控 隐患排查治理智能化信息平台</title>
    <meta name="keywords" content="安全分级管控 隐患排查治理智能化信息平台">
    <meta name="description" content="安全分级管控 隐患排查治理智能化信息平台">
    <style type="text/css">
    .form-label{
    font-size : 19px;
    }
    .formControls{
    border: 2px solid #e5e5e5;
    }
    .desc_class{
    margin-top:10px;
    }
    </style>
    </head>
    <body id="monitorDetailBody">
    <!-- <script src="https://open.ys7.com/sdk/js/1.3/ezuikit.js"></script> -->
    <div class="page-container">
    <div style="display:inline;">
    <%--  <video id="myPlayer" style="float:left;height:480px;width:900px;" poster="" controls playsInline webkit-playsinline autoplay>
         <source src="${code }" type="application/x-mpegURL" />
     </video> --%>
    <video id="videoShowId" src=""  style="width:67%;float:left;"></video>
    <div id="showdescId" style="float:left;width:29%;margin-left:15px;">
    <div class="desc_class">
    <label class="form-label col-xs-4 col-sm-2">部位</label>
    <div class="formControls col-xs-8 col-sm-9">
    <c:out value="${monitor.name }" default="暂无"/>
    </div>
    </div>
    <div class="desc_class">
    <label class="form-label col-xs-4 col-sm-2">风险等级</label>
    <div class="formControls col-xs-8 col-sm-9"

    <c:choose>
        <c:when test="${empty monitor.dangerLevel }">

        </c:when>
        <c:when test="${monitor.dangerLevel == '1' }">
            style="background-color:#ff0000;"
        </c:when>
        <c:when test="${monitor.dangerLevel == '2' }">
            style="background-color:#fa800a;"
        </c:when>
        <c:when test="${monitor.dangerLevel == '3' }">
            style="background-color:#ffff00;"
        </c:when>
        <c:when test="${monitor.dangerLevel == '4' }">
            style="background-color:#0000ff;"
        </c:when>
    </c:choose>

    >
    <%-- <c:out value="${monitor.dangerLevel }" default="暂无"/> --%>
    <c:choose>
        <c:when test="${empty monitor.dangerLevel }">
            暂无
        </c:when>
        <c:when test="${monitor.dangerLevel == '1' }">
            <span style="color:#ff0000;">重大风险</span>
        </c:when>
        <c:when test="${monitor.dangerLevel == '2' }">
            <span style="color:#fa800a;">高风险</span>
        </c:when>
        <c:when test="${monitor.dangerLevel == '3' }">
            <span style="color:#ffff00;">较大风险</span>
        </c:when>
        <c:when test="${monitor.dangerLevel == '4' }">
            <span style="color:#0000ff;">一般风险</span>
        </c:when>
        <c:otherwise>
            ${monitor.dangerLevel }
        </c:otherwise>
    </c:choose>
    </div>
    </div>
    <div class="desc_class">
    <label class="form-label col-xs-4 col-sm-2">事故类型</label>
    <div class="formControls col-xs-8 col-sm-9">
    <c:out value="${monitor.accidentType }" default="暂无"/>
    </div>
    </div>
    <div class="desc_class">
    <label class="form-label col-xs-4 col-sm-2">应急&nbsp;&nbsp;处置方式</label>
    <div class="formControls col-xs-8 col-sm-9">
    <c:out value="${monitor.descr }" default="暂无"/>
    </div>
    </div>
    <div class="desc_class">
    <label class="form-label col-xs-4 col-sm-2">责任人</label>
    <div class="formControls col-xs-8 col-sm-9">
    <c:out value="${monitor.dutyPeople }" default="暂无"/>
    </div>
    </div>
    </div>
    </div>
    <script type="text/javascript" src="${ly}/js/H-ui.admin_v3.0/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="${ly }/js/editckplayer/ckplayer/ckplayer.js"></script>
    <script type="text/javascript" src="/js/H-ui.admin_v3.0/lib/layer/2.4/layer.js" ></script>
	<script type="text/javascript">
    console.log('111${monitor}');
	console.log('111-${puid}');
	var puid='${puid}';
	var searchURL = window.location.search;
	searchURL = searchURL.substring(1, searchURL.length);
	var idx= searchURL.split("&")[1].split("=")[1];
	console.log('idx:',idx);
    var data={
    id: 1,
    name: "清新",
    ip: "58.215.121.38",
    port: 9988,
    hport: 9580,
    uid: "admin",
    pwd: "",
    epid: "system",
    bfix: "1",
    memo: null
    }
    function getToken(info){
    const postData = {
    user: data.uid,
    password: data.pwd,
    address: data.ip,
    port: data.port,
    epid: data.epid
    } ;
    $.ajax({
        url:"https://sec.dicarl.com/icvs2/login",    //请求的url地址
        header: {
		'content-type': 'application/x-www-form-urlencoded',
		},  //返回格式为json
        async:false,//请求是否异步，默认为异步，这也是ajax重要特性
        data:postData,    //参数值
        type:"POST",   //请求方式
        beforeSend:function(){
            //请求前的处理
        },
        success:function(res){
            //请求成功时处理
			console.log(res);
			if(res.msg==='OK') {
			let token=res.token;
			console.log('token:',token);
			//layer.msg('请求成功！');
			wjj(token)
			}
        },
        complete:function(){
            //请求完成的处理
        },
        error:function(){
            //请求出错处理
             layer.msg('请求失败！');
        }
    });
    };
    getToken();
	function wjj(token){
	var src = 'https://sec.dicarl.com/icvs2/stream.flv?idx=' + idx + '&puid='+puid+'&token=' + token
	$("#videoShowId").attr("src",src);
	console.log('src:',src)
	let v=document.querySelector("#videoShowId");
	console.log(v.networkState)
	let vn=v.networkState;
	if(vn===0){

	}else if(vn===1){

	}else if(vn===2){
	layer.msg('视频加载中...')
	}else if(vn===3){
		setTimeout(()=>{
	layer.msg('未找到视频来源！')
	},2000)
	}else{
	setTimeout(()=>{
	   layer.msg('未知错误！')
	},2000)
	}
	//0 = NETWORK_EMPTY - 视频尚未初始化
	//1 = NETWORK_IDLE - 视频是活动的且已选取资源，但并未使用网络
	//2 = NETWORK_LOADING - 浏览器正在下载数据
	//3 = NETWORK_NO_SOURCE - 未找到视频来源
	}


    var videoObject2 = {
    container: '#videoShowId',//“#”代表容器的ID，“.”或“”代表容器的class
    variable: 'player',//该属性必需设置，值等于下面的new chplayer()的对象
    autoplay:true,//自动播放
    live:true,
    video:"${code}"//视频地址
    };
    var player2=new ckplayer(videoObject2);

    //var bodyWidth2 = document.getElementById("monitorDetailBody").clientWidth-40;//$("#monitorBody").width();
    // $("#videoShowId").css("width",800);
    setTimeout(function(){





    $("#videoShowId").css("height",$("#videoShowId").width()/1.2);
    },100)
    /* if(bodyWidth2-900 <= 0){
    $("#showdescId").css("width",200);
    }else{
    $("#showdescId").css("width",bodyWidth2-900);
    } */
    /* setTimeout(function(){
    $.ajax({
    url : "${ly}/village/toUpdateMonitorThumbnail",
    type : 'post',
    data : {
    id : "${monitor.id}",
    success : function(data){}
    }
    });
    },1000) */

    </script>
    <!-- <script>
    var player = new EZUIPlayer('myPlayer');
    player.on('error', function(){
    });
    player.on('play', function(){
    });
    player.on('pause', function(){
    });
    </script> -->
    </div>
    </body>
    </html>