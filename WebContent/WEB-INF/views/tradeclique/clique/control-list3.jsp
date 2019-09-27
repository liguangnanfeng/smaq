<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!-- add by zhangcl 2018.10.13 -->
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

<link rel="stylesheet" href="https://cache.amap.com/lbs/static/main1119.css"/>
<link rel="stylesheet" type="text/css" href="https://webapi.amap.com/css/v1.4.1/style1509024629605.css">
<style type="text/css">
.ter1{width:15%}
.tab_tmr1{width:35%}
.switch-left{
    height: 26px;
    vertical-align: top;
}
.switch-right{
    height: 26px;
    vertical-align: top;
}
#menu{
	padding: 10px 10px 10px;
	text-align: center;
	position: fixed;
	z-index: 998;
	background-color: #fcf9f2;
	display:none;
	border-radius: 5px;
	box-shadow: 2px 2px 5px #333333;
	height:300px;
	overflow-y:auto;
}
</style>

<%@ include file="/WEB-INF/inc/print.inc"%>
<link rel="stylesheet" type="text/css" media="print"  href="${ly}/js/H-ui.admin_v3.0/static/h-ui/css/H-ui.css" />
<link rel="stylesheet" type="text/css" href="${ly}/styles/common.css" media="print"  />
<style type="text/css" media="print">
body .dis-ib{margin-right:15px;}
.col-b,.col-a,.col-c,.col-d{margin:0;padding:0 15px;height:25px;line-height:25px;}
.div-print{width:90%;margin:auto;padding:20px 0 0 0;}
h3{display:block;width:100%;margin-bottom:15px;}
</style>
<style type="text/css">
body .dis-ib{margin-right:15px;}
.col-b,.col-a,.col-c,.col-d{margin:0;padding:0 15px;height:25px;line-height:25px;}
.div-print{width:90%;margin:auto;padding:20px 0 0 0;}
/* h3{display:none;} */
</style>
<script type="text/javascript">

</script>
</head>
<body>
	<nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
    <span class="c-gray en">&gt;</span> <span>风险分级管控</span>
    <span class="c-gray en">&gt;</span> <span>风险评估</span>
    <span class="c-gray en">&gt;</span> <span>${company.name}风险分布图</span>
   	
	<!--</div>-->
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
    
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:endEditArea();" title="保存企业范围">
    	保存企业范围
    </a>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="javascript:startEditArea();" title="修改企业范围">
   		修改企业范围
    </a>
	</nav>

  	<div class="div_cmap" id="map" style="position: absolute;top: 0px;bottom: 50px;"></div>
  	
  	
  	<!--<div class="input-card" style="padding: 10px 10px 10px;text-align: center;position:fixed;top: 60px;right: 140px;z-index: 999;background-color: #fcf9f2;border-radius: 5px;box-shadow: 2px 2px 5px #333333;">
   		<table>
   			<tr><td><button class="btn" onclick="startEditArea()" style="margin-bottom: 5px">修改企业范围</button></td></tr>
   			<tr><td><button class="btn" onclick="endEditArea()">保存企业范围</button></td></tr>
   		</table>
	</div>-->

    <!-- 右击菜单 -->
    <div id="menu">
      <table id="menutable" class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th style="width:40px">序号</th>
            <th style="min-width:100px">岗位/部位</th>
            <th style="min-width:100px">风险等级</th>
            <th style="min-width:100px">地图标示</th>
            <th style="min-width:150px">风险类型</th>
            <th style="min-width:200px">风险因素</th>
            <th style="min-width:200px">防范措施</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${list }" var="be" varStatus="index">
          <tr id="line_${index.index + 1}">
            <td class="text-c">${index.index + 1}</td>
            <td><c:if test="${not empty be.level1}">${be.level1 } > ${be.level2 }</c:if></td>
            <td class="text-c">
               <c:choose>
                <c:when test="${be.level eq '红色'}"><font class="col-a">${be.level}</font></c:when>
                <c:when test="${be.level eq '橙色'}"><font class="col-b">${be.level}</font></c:when>
                <c:when test="${be.level eq '黄色'}"><font class="col-c">${be.level}</font></c:when>
                <c:when test="${be.level eq '蓝色'}"><font class="col-d">${be.level}</font></c:when>
               </c:choose>
            </td>
            <td class="text-c">
               <c:choose>
                <c:when test="${not empty be.lnglat}"><font style="color:green">是</font></c:when>
                <c:when test="${empty be.lnglat}"><font style="color:red">否</font></c:when>
               </c:choose>
            </td>
            <td>${be.type }</td>
            <td>${be.factors }</td>
            <td style="display:none">${be.lnglat }</td>
            <td style="display:none">${be.id }</td>
            <td>${empty be.gkcs ? be.measures :be.gkcs }</td>
          </tr>
          </c:forEach>
          <c:forEach items="${branches }" var="be" varStatus="index">
          <tr id="line_${fn:length(list) + index.index + 1}">
            <td class="text-c">${fn:length(list) + index.index + 1}</td>
            <td><c:if test="${not empty be.level1}">${be.level1 } > ${be.level2 }</c:if></td>
            <td class="text-c">
               <c:choose>
                <c:when test="${be.level eq '红色'}"><font class="col-a">${be.level}</font></c:when>
                <c:when test="${be.level eq '橙色'}"><font class="col-b">${be.level}</font></c:when>
                <c:when test="${be.level eq '黄色'}"><font class="col-c">${be.level}</font></c:when>
                <c:when test="${be.level eq '蓝色'}"><font class="col-d">${be.level}</font></c:when>
               </c:choose>
            </td>
            <td class="text-c">
               <c:choose>
                <c:when test="${not empty be.lnglat}"><font style="color:green">是</font></c:when>
                <c:when test="${empty be.lnglat}"><font style="color:red">否</font></c:when>
               </c:choose>
            </td>
            <td>${be.type }</td>
            <td>${be.factors }</td>
            <td style="display:none">${be.lnglat }</td>
            <td style="display:none">${be.id }</td>
            <td>${empty be.gkcs ? be.measures :be.gkcs }</td>
          </tr>
          </c:forEach>
        </tbody>
      </table>
  </div>

</body>

<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.1&key=441462b46b736497a5f2a377aafb5b69&plugin=AMap.PolyEditor"></script>
<script type="text/javascript">
var jw = '${user.longitude},${user.latitude}';
var userId = ${user.id};
//var address = '${company.address}';
//var cname = '${user.userName}';
//var safety_contact = '${company.safetyContact}';
var lnglat;
//var markerarray = new Array();
//var marker_size = 0;
//style="display:none"

function show_info(xuhao) {
	var infowin = new AMap.InfoWindow({offset: new AMap.Pixel(0, -30)});
	var gwbw = document.getElementById("line_"+xuhao).children[1].textContent;
	var fxlx = document.getElementById("line_"+xuhao).children[4].textContent;
	var fxys = document.getElementById("line_"+xuhao).children[5].textContent;
	var jwzb = document.getElementById("line_"+xuhao).children[6].textContent;
	var gkcs = document.getElementById("line_"+xuhao).children[8].textContent;
	
 	infowin.setContent("<b>序号: </b>" + xuhao + "<br>"
  						+"<b>岗位/部位：</b>" + gwbw + "<br>"
  						+"<b>风险类型：</b>" + fxlx + "<br>"
  						+"<b>风险因素：</b>" + fxys + "<br>"
  						+"<b>管控措施：</b>" + gkcs + "<br>"
  						);
  	infowin.open(map, new AMap.LngLat(jwzb.split(',')[0], jwzb.split(',')[1]));
}

var map = new AMap.Map("map", {
    resizeEnable: true,
    zoom:17,
    zooms:[3,20],
    expandZoomRange:true,
    //viewMode:'3D', //3d就不能显示多边形
    pitch:50,
    layers: [new AMap.TileLayer.Satellite()],
    keyboardEnable: false,
});

  map.plugin(["AMap.RangingTool"], function() {
    ruler1 = new AMap.RangingTool(map);
    AMap.event.addListener(ruler1, "end", function(e) {
        ruler1.turnOff();
    });
    var sMarker = {
        icon: new AMap.Icon({
            size: new AMap.Size(19, 31),//图标大小
            image: "https://webapi.amap.com/theme/v1.3/markers/n/mark_b1.png"
        })
    };
    var eMarker = {
        icon: new AMap.Icon({
            size: new AMap.Size(19, 31),//图标大小
            image: "https://webapi.amap.com/theme/v1.3/markers/n/mark_b2.png"
        }),
        offset: new AMap.Pixel(-9, -31)
    };
    var lOptions = {
        strokeStyle: "solid",
        strokeColor: "#FF33FF",
        strokeOpacity: 1,
        strokeWeight: 2
    };
    var rulerOptions = {startMarkerOptions: sMarker, endMarkerOptions: eMarker, lineOptions: lOptions};
    ruler2 = new AMap.RangingTool(map, rulerOptions);
  });

	//设置地图中心
//	if(jw != ',') {
//  		map.setCenter(new AMap.LngLat(jw.split(',')[0], jw.split(',')[1]));
//	} else {
//  	var cjw = "${v.longitude},${v.latitude}";//村经纬度
//  	if(cjw != ',') {
//		map.setCenter(new AMap.LngLat(cjw.split(',')[0], cjw.split(',')[1]));
//  	}
//	}
  
  	//显示图标
	var jwarray = new Array();
	var xuhaoarray = new Array(); 
	var levlearray = new Array();
	<c:forEach items="${list}" var="be" varStatus="index">  
		xuhaoarray.push(${index.index + 1});
　　		jwarray.push("${be.lnglat}"); //js中可以使用此标签，将EL表达式中的值push到数组中  
		levlearray.push("${be.level}");
	</c:forEach>  
	<c:forEach items="${branches}" var="be" varStatus="index">  
		xuhaoarray.push(${fn:length(list) + index.index + 1});
		jwarray.push("${be.lnglat}"); //js中可以使用此标签，将EL表达式中的值push到数组中  
		levlearray.push("${be.level}");
	</c:forEach>  
	//alert(jwarray.length);
	for(var i=0;i<jwarray.length;i++){  
		if(jwarray[i]!=""){
			//alert(jwarray[i]);
			
			var xuhao = xuhaoarray[i];
			var dlevel = levlearray[i];
			//alert(parent.children[2].children[0].textContent);
   			var dicolorCss = "";
   			if(dlevel == "红色"){
   			  dicolorCss = "dicolor1";
   			} else if (dlevel == "橙色") {
   			  dicolorCss = "dicolor2";
   			} else if (dlevel == "黄色") {
   			  dicolorCss = "dicolor3";
   			} else if (dlevel == "蓝色") {
   			  dicolorCss = "dicolor4";
   			} else {
   			  dicolorCss = "dicolor4";
   			}
			var marker = new AMap.Marker({
      			content: "<div class='mark "+ dicolorCss +"'>  </div>",
      			position: jwarray[i].split(","),
      			clickable : true
    		});
    		marker.content = 'marker_' + xuhao;
    		marker.setMap(map);	
    		AMap.event.addListener(marker, 'click', function(e) {
    			var xuhao = e.target.content.split("_")[1];
     			show_info(xuhao);
    		});
		}
 	}
	
	//显示多边形
	var range = '${company.areaRange}';
	var path = new Array();
	var area_editing = 0;
	if(range){
		//alert("range not null:"+range);
		var array_range = range.split(",");
		var len_path = array_range.length/2;
		for(var i=0;i<len_path;i++){
			path.push([array_range[2*i],array_range[2*i+1]]);
		}
		//alert("range:"+range+" len_path:"+len_path+" path:"+path.join(","));
	}
	else{
		var jindu = jw.split(",")[0];
		var weidu = jw.split(",")[1];
		path = [
        	[parseFloat(jindu)+0.001, parseFloat(weidu)+0.001],
        	[parseFloat(jindu)-0.002, parseFloat(weidu)+0.002],
        	[parseFloat(jindu)-0.004, parseFloat(weidu)],
        	[parseFloat(jindu)-0.0014, parseFloat(weidu)-0.0036]
    	];
	}
    path.push(path[0]);//首尾相连的折线，使中间地图可以被点击
    var path1 = path.slice(0);//拷贝
    var polyline = new AMap.Polyline({
        		path: path1,
        		strokeColor: "#ff00ff",
        		strokeOpacity: 1,
        		strokeWeight: 6,
        		zIndex: 50,
        		//strokeDasharray: [10, 10] //补充线样式
    });		
	polyline.setMap(map);
	
	//设置地图显示范围为多边形范围,zhangcl 2018.12.17
	path.sort(function (a, b) {
  		return a[0]-b[0];
	});
	var lngmin = path[0][0];
	var lngmax = path[path.length-1][0];
	path.sort(function (a, b) {
  		return a[1]-b[1];
	});
	var latmin = path[0][1];
	var latmax = path[path.length-1][1];
	//alert(lngmin+","+latmin+","+lngmax+","+latmax);
	var bounds = new AMap.Bounds([lngmin,latmin], [lngmax,latmax]);
	map.setBounds(bounds);
	
 
    // 缩放地图到合适的视野级别
    //map.setFitView([ polygon ]);

	var startEditArea = function(){
		if(area_editing==1){
			return;
		}
		area_editing = 1;
		map.remove(polyline);
		path.pop();
		var polygon = new AMap.Polygon({
        	path: path,
        	isOutline: true,
        	borderWeight: 10,
        	//strokeColor: "#9aff02",//亮绿 
        	strokeColor: "#ff00ff", //玫红
        	strokeWeight: 6,
        	strokeOpacity: 1,
        	fillOpacity: 0,
        	// 线样式还支持 'dashed'
        	//fillColor: '#1791fc',
        	zIndex: 50,
    	});
    	polygon.setMap(map);
    	polyEditor = new AMap.PolyEditor(map, polygon);
    		polyEditor.on('addnode', function(event) {
			});
			polyEditor.on('adjust', function(event) {
			});
			polyEditor.on('removenode', function(event) {
			});
			polyEditor.on('end', function(event) {	
			window.location.href = "/tradeclique/area-range-save?arearange="+event.target.getPath().join(',');
		});
    	polyEditor.open();
	}
	
	var endEditArea = function(){
		if(area_editing==0){
			return;
		}
		area_editing = 0;
		polyEditor.close();
	}

$('#mySwitch').on('switch-change', function (e, data) {
    var value = data.value;
    //console.log(e, $el, value);
    if(value){
    	map.setLayers([new AMap.TileLayer.Satellite()]);
    } else {
    	map.setLayers([new AMap.TileLayer()]);
    }
});

//添加右击菜单
var clickEventListener = map.on('contextmenu', function(e) {
		
    	lnglat = e.lnglat.getLng() + ',' + e.lnglat.getLat();
    	//alert(e.lnglat.getLng() + ',' + e.lnglat.getLat());
    	var menu=document.getElementById("menu");
		//alert(ev.clientY+" "+document.body.clientHeight);
    	if(event.clientY<(document.body.clientHeight/2)){
    		menu.setAttribute("style", "left: 60px;right: 60px;bottom:60px");
    	}
    	else{
    		menu.setAttribute("style", "left: 60px;right: 60px;top:60px");
    	}
		menu.style.display="block";
		return false;
});
//左键取消菜单显示
map.on('click', function(e) {
    	var menu=document.getElementById("menu");
		menu.style.display="none";
		return false;
});

//为表格添加点击事件
document.getElementById("menutable").addEventListener( "click",function(e){
		var event = event||window.event;
		var element = event.target||event.srcElement;
		var parent = element.parentNode;
		if(parent.getAttribute("id").startsWith("line")){
			var id = parent.children[7].textContent;
			window.location.href = "/tradeclique/control-list3?id="+id+"&lnglat="+lnglat;
		}

	},false );

</script>
</html>