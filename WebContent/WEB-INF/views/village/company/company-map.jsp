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
<link rel="stylesheet" href="https://cache.amap.com/lbs/static/main.css"/>
<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.3&key=441462b46b736497a5f2a377aafb5b69"></script>
<style>
.tag{
	display:block;
	word-break:keep-all;
	white-space:nowrap;
    padding: 5px;
    border: 2px solid #09F;
    position:relative;
    border-radius: 5px;
    background-color: #fff;
}
.tag:before,.tag:after{
    content:"";
    display:block;
    border-width:12px;
    position:absolute;
    bottom:-22px;
    left:17px;
    border-style:solid dashed dashed;
    border-color:#09F transparent transparent;
    font-size:0;
    line-height:0;
}
.tag:after{
    bottom:-21px;
    border-color:#FFF transparent transparent;
}
.btn_fenx{width:auto;padding:0px 20px;color:#fff;height:30px;line-height:30px;margin-left:3px;margin-right:15px;border-radius:5px;}
</style>
<script type="text/javascript">
$(function(){
  $('body').click(function(e) {
    var target = $(e.target);
    if(!target.is('.div_coms *')){
      $(".div_coms").removeClass("div_comsxz");
    }
 });
  $(".div_coms").click(function(){
    if($(this).hasClass("div_comsxz")){
      $(this).removeClass("div_comsxz");
      $(this).find(".Hui-iconfont").html("&#xe6d5;");
    }else{
      $(".div_coms").removeClass("div_comsxz");
      $(this).addClass("div_comsxz");
      $(this).find(".Hui-iconfont").html("&#xe6d6;");
    }
  });
  
  $(".one_ocoms").click(function(){
    $(this).closest(".div_coms").find(".one_ocoms").removeClass("one_ocomsxz");
    $(this).addClass("one_ocomsxz");
    if($(this).html()=="全部"){
      $(this).closest(".div_coms").find("label").html("风险等级");
    }else{
      $(this).closest(".div_coms").find("label").html($(this).html());
    }
  });
});
  function showdetail(){
    $(".shan_companys").hide();
    $(".one_scompany").show();
  }
  function showlist(){
    $(".one_scompany").hide();
    $(".shan_companys").show();
  }
</script>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
		<span class="c-gray en">&gt;</span> <span>企业信息库</span> 
		<span class="c-gray en">&gt;</span> <span>企业分布图</span> 
		<a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
			<i class="Hui-iconfont">&#xe68f;</i>
		</a>
	</nav>
	<div class="page-container">
       <div class="div_mapleft">
         <!-- 查询 -->
         <div class="search_map">
           <input type="text" id=""  placeholder="请输入企业名称" class="input-text txt_maps" value="" />
           <button onClick="" class="btn btn_maps" type="button"><i class="Hui-iconfont">&#xe665;</i></button>
         </div>
         <div class="shan_companys">
           <!-- 筛选条件 -->
           <div class="search_coms">
             <div class="div_coms">
               <div class="bt_coms"><label>风险等级</label><i class="Hui-iconfont">&#xe6d5;</i></div>
               <div class="list_ocoms">
                 <div class="one_ocoms">全部</div>
                 <div class="one_ocoms">A级</div>
                 <div class="one_ocoms">B级</div>
                 <div class="one_ocoms">C级</div>
               </div>
             </div>
           </div>
           <!-- 企业列表 -->
           <div class="list_companys">
             <!-- 循环企业 -->
             <div class="one_companym" onclick="showdetail()">
               <p class="name_comp">
                 <bb class="bt_comp">1</bb>
                 <font title="企业名称xx">企业名称xx</font>
               </p>
               <p class="p_comname">企业地址xx</p>
             </div>
           </div>
           <!-- 循环结束 -->
           
         </div>
         <!-- 企业详情 -->
         <div class="one_scompany">
           <!-- 判断搜索结果时显示 -->
           <div class="div_mapfan" onclick="showlist()">
             <i class="Hui-iconfont">&#xe6d4;</i>返回搜索结果
           </div>
           <!-- 判断结束 -->
           <div class="top_company">
             <p class="p_allname">企业名称xx</p>
             <div class="company_cs">
               <font>风险等级：A</font>
               <font>企业类型：xx</font>
             </div>
           </div>
           <div class="com_adres">
             <p class="p_xiangx"><i class="Hui-iconfont">&#xe671;</i>地址xx</p>
             <p class="p_xiangx" style="padding:0px;"><i class="Hui-iconfont">&#xe6a3;</i>法人手机xx</p>
           </div>
         </div>
       </div>
       <div id="map_com"></div>
  
  
  
  
		<!-- <input name="Fruit" type="checkbox" checked onchange="checkA(this)" value="A级" />
		<button class="btn_fenx" style="background:#FF0066" type="button" onclick="">A级</button>
		<input name="Fruit" type="checkbox"  checked onchange="checkB(this)" value="B级" />
		<button class="btn_fenx" style="background:#F7A35C" type="button" onclick="">B级</button>
		<input name="Fruit" type="checkbox" checked onchange="checkC(this)" value="C级" />
		<button class="btn_fenx" style="background:blue" type="button" onclick="">C级</button> -->
	</div>
<script type="text/javascript">
	var mapList = $.parseJSON('${list }');
	var map = new AMap.Map("map_com", {
	    resizeEnable: true,
	    center: [120.148965, 31.66404], //获取村的地址
	    zoom: 10 //地图显示的缩放级别
	});
	var Alist = new Array();
	var Blist = new Array();
	var Clist = new Array();
	for (var i = 0; i < mapList.length; i++) {
	    var colorList = ["225,0,0", "0,191,255", "0,0,0"];
	    var color;
	    if (mapList[i].dlevel == "红色") {
	        color = colorList[0];
	    } else if (mapList[i].dlevel == "橙色") {
	        color = colorList[1];
	    } else if (mapList[i].dlevel == "蓝色") {
	        color = colorList[2];
	    } else {
	        color = colorList[2];
	    }
	    var marker;
	    if(mapList[i].longitude != null && mapList[i].longitude != ''){
		      marker = new AMap.Marker({
		        content: "<p class='tag' style='color:rgb(" + color + ")'>" + mapList[i].companyName + "[" + mapList[i].dlevel + "]" + "</p>",
		        position: [mapList[i].longitude, mapList[i].latitude],
		        title: mapList[i].companyName + "[" + mapList[i].dlevel + "]",
		        offset: new AMap.Pixel(-10, 0),
		        map: map
		    });
		      
		      if (mapList[i].dlevel == "A") {
		        Alist.push(marker);
		    } else if (mapList[i].dlevel == "B") {
		        Blist.push(marker);
		    } else if (mapList[i].dlevel == "C") {
		        Clist.push(marker);
		    } else {
		        Clist.push(marker);
		    }
	    }
	    
	    
	}
	
	function checkA(target) {
	  if (target.checked) {
	      Alist.forEach(function(marker) {
          	marker.show();
	      })
	  } else {
	    Alist.forEach(function(marker) {
	        marker.hide();
	    })
	  }
	}
	function checkB(target) {
	  if (target.checked) {
	      Blist.forEach(function(marker) {
          	marker.show();
	      })
	  } else {
	    Blist.forEach(function(marker) {
	        marker.hide();
	    })
	  }
	}
	function checkC(target) {
	  if (target.checked) {
	      Clist.forEach(function(marker) {
          	marker.show();
	      })
	  } else {
	    Clist.forEach(function(marker) {
	        marker.hide();
	    })
	  }
	}
</script>
<script type="text/javascript" src="https://webapi.amap.com/demos/js/liteToolbar.js"></script>
</body>
</html>