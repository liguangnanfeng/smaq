<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!-- add by zhangcl 2018.12.10 -->
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
    <span class="c-gray en">&gt;</span> <span>重大危险源源长制</span>
    <span class="c-gray en">&gt;</span> <span>管控措施</span>
    <span class="c-gray en">&gt;</span> <span>${company.name}风险分布图</span>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
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
            <th style="min-width:100px">构成重大危险源的主要物质</th>
            <th style="min-width:100px">现场理论最大存量</th>
            <th style="min-width:100px">地图标示</th>
          </tr>
        </thead>
        <tbody>
        	
        	
        	
        	<c:forEach items="${fn:split(regulation.dangerMaterial, ',') }" var="be" varStatus="index">
          		<tr id="line_${index.index + 1}" class="text-c" >
            		<td>${index.index + 1}</td>
            		<td>${fn:split(be, '/')[0]}</td>
            		<td>${fn:split(be, '/')[1]}</td>
            		<td class="text-c">
               			<c:choose>
                			<c:when test="${! empty fn:split(be, '/')[2]}"><font style="color:green">是</font></c:when>
                			<c:when test="${empty fn:split(be, '/')[2]}"><font style="color:red">否</font></c:when>
               			</c:choose>
            		</td>
          		</tr>
          	</c:forEach>
          
          <!--<c:forEach items="${list }" var="be" varStatus="index">
          <tr id="line_${index.index + 1}">
            <td class="text-c">${index.index + 1}</td>
            <td class="text-c">${fn:split(be, '/')[0]}</td>
            <td class="text-c">${fn:split(be, '/')[1]}</td>
            <td class="text-c">
               <c:choose>
                <c:when test="${fn:split(be, '/')[2]!=''}"><font style="color:green">是</font></c:when>
                <c:when test="${fn:split(be, '/')[2]==''}"><font style="color:red">否</font></c:when>
               </c:choose>
            </td>
          </tr>
          </c:forEach>-->
        </tbody>
      </table>
  </div>
</body>

<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.1&key=441462b46b736497a5f2a377aafb5b69&plugin=AMap.PolyEditor"></script>
<script type="text/javascript">
var jw = '${user.longitude},${user.latitude}';

var lnglat;

var wzarray = new Array();
var slarray = new Array();
var jwarray = new Array();
var tokens;

function show_info(xuhao) {
	var infowin = new AMap.InfoWindow({offset: new AMap.Pixel(0, -30)});
	var wz = wzarray[xuhao];
	var sl = slarray[xuhao];
	
 	infowin.setContent("<b>序号: </b>" + (xuhao*1+1) + "<br>"
  						+"<b>构成重大危险源的主要物质: </b>" + wz + "<br>"
  						+"<b>现场理论最大存量: </b>" + sl + "<br>"
  						);
  	infowin.open(map, new AMap.LngLat(jwarray[xuhao].split('_')[0], jwarray[xuhao].split('_')[1]));
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

	//设置地图中心
	if(jw != ',') {
    	map.setCenter(new AMap.LngLat(jw.split(',')[0], jw.split(',')[1]));
	} else {
    	var cjw = "${v.longitude},${v.latitude}";//村经纬度
    	if(cjw != ',') {
      		map.setCenter(new AMap.LngLat(cjw.split(',')[0], cjw.split(',')[1]));
    	}
	}
  

    var dangerMaterial = "${regulation.dangerMaterial}";
    //alert(dangerMaterial);
   	tokens = dangerMaterial.split(',').length;
   	for(var i=0;i<tokens;i++){
   		var token = dangerMaterial.split(',')[i];
   		//alert(token);
   		wzarray.push(token.split('/')[0]);
   		slarray.push(token.split('/')[1]);
   		jwarray.push(token.split('/')[2]);
   	}
	for(var i=0;i<tokens;i++){  
		if(jwarray[i]&&jwarray[i]!=""){
			//alert(i+","+jwarray[i]);
			var xuhao = i;
   			var dicolorCss = "dicolor1";
			var marker = new AMap.Marker({
      			content: "<div class='mark "+ dicolorCss +"'>  </div>",
      			position: jwarray[i].split("_"),
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
		
    	lnglat = e.lnglat.getLng() + '_' + e.lnglat.getLat();
    	//alert(e.lnglat.getLng() + '_' + e.lnglat.getLat());
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
			var index = parent.children[0].textContent;
			jwarray[index-1] = lnglat;
			var dangerMaterial='';
			for(var i=0;i<tokens;i++){
				dangerMaterial += wzarray[i]+'/'+slarray[i]+'/'+jwarray[i];
				if(i!=tokens-1) dangerMaterial+=',';
			}
			//alert(dangerMaterial);
			//window.location.href = "/company/source/control-list3?dangerMaterial="+dangerMaterial;
			$.post(getRootPath() + "/company/source/control-list3",{
				id:${regulation.id},
    			dangerMaterial : dangerMaterial
  			},function(result) {
    			//layer.close(i);
    			if(result.status == '1') {
      				layer.alert(result.map.message);
    			} else {
        			window.location.href = '/company/source/distribution';
    			}
  			})
		}

	},false );

</script>
</html>