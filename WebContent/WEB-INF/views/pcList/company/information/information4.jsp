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

<link rel="stylesheet" href="https://cache.amap.com/lbs/static/main1119.css"/>
<link rel="stylesheet" href="https://cache.amap.com/lbs/static/AMap.PlaceSearchRender1120.css"/>
<link rel="stylesheet" type="text/css" href="http://webapi.amap.com/css/v1.4.1/style1509024629605.css">

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
</style>
</head>
<body>
  <!-- <div class="page-container">
    <div class="mt-20" style="width:100%"> -->
    <div style="padding: 10px 10px 10px;text-align: center;position: fixed;top: 60px;right: 140px;z-index: 999;background-color: #fcf9f2;border-radius: 5px;box-shadow: 2px 2px 5px #333333;">
    	<span style="vertical-align: middle;">普通图</span>
    	<div style="vertical-align: middle;" id="mySwitch" class="switch"  data-on-label="<i class='icon-ok icon-white'></i>" data-off-label="<i class='icon-remove'></i>">
			<input type="checkbox" checked />
		</div>
		<span style="vertical-align: middle;">卫星图</span>
    </div>
    	<div style="padding: 10px 10px 10px;text-align: center;position: fixed;top: 60px;right: 60px;z-index: 999;background-color: #fcf9f2;border-radius: 5px;box-shadow: 2px 2px 5px #333333;">
  			<input class="btn btn-primary radius" type="button" value="测距" onClick="javascript:startRuler()" />
  		</div>
      <div class="div_cmap" id="map">
      </div>
    <!-- </div>
  </div> -->
</body>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.1&key=441462b46b736497a5f2a377aafb5b69&plugin=AMap.PlaceSearch"></script>
<script type="text/javascript" src="https://cache.amap.com/lbs/static/PlaceSearchRender.js"></script>
<script type="text/javascript">
var jw = '${user.longitude},${user.latitude}';
var userId = ${user.id};
var address = '${company.address}';
var cname = '${user.userName}';
var safety_contact = '${company.safetyContact}';

var placeSearchOptions = { //构造地点查询类
    pageSize: 1,
    pageIndex: 1
};
var infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(0, -30)});
function open_info() {
  infoWindow.setContent("<b>" + cname + "</b><br>详细地址：" + address + "<br>" + "安全员手机号：" + (safety_contact == '' ? '暂无信息' : safety_contact));
  infoWindow.open(map, new AMap.LngLat(jw.split(',')[0], jw.split(',')[1]));
}
var placeSearch = new AMap.PlaceSearch(placeSearchOptions);

  var map = new AMap.Map("map", {
    resizeEnable: true,
    zoom:17,
    zooms:[3,20],
    expandZoomRange:true,
    viewMode:'3D',
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

  //启用默认样式测距
  function startRuler() {
    ruler2.turnOff();
    ruler1.turnOn();
  }
  
  if(jw != ',') {
    map.setCenter(new AMap.LngLat(jw.split(',')[0], jw.split(',')[1]));
    open_info();
  } else {
    var cjw = "${v.longitude},${v.latitude}";//村经纬度
    if(cjw != ',') {
      map.setCenter(new AMap.LngLat(cjw.split(',')[0], cjw.split(',')[1]));
    }
  } 
  
  var marker = null;
  if(jw != ',') {
    var dlevel = "${company.dlevel}";
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
    
    marker = new AMap.Marker({
      content: "<div class='mark "+ dicolorCss +"'>  </div>",
      position: jw.split(","),
      clickable : true
    });
    
    marker.setMap(map);
    bind_click();
  } else {
    layer.msg('企业自动定位中。。。');
    
    setTimeout(function() {
      //关键字查询
      placeSearch.search(address == '' ? cname : address, function(status, result) {
        layer.closeAll();
        if (status === 'complete' && result.info === 'OK') {
          
          var tips = result.poiList.pois[0];
          var lnglatXY = tips.location.lng + "," + tips.location.lat;
          
          marker = new AMap.Marker({
            icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
            position: lnglatXY.split(","),
            clickable : true
          });
          
          marker.setMap(map);
          
          address = tips.address + "(" + tips.name + ")";
          
          open_info();
          bind_click();
          $.post(getRootPath() + "/company/company-edit-do",{
            userId : userId,
            address : tips.address + "(" + tips.name + ")",
            jw : lnglatXY
          },function(result){
          })
        } else {
          layer.alert("企业定位失败");
        }
      });
      
    }, 2000);
  }
  
  function bind_click() {
    AMap.event.addListener(marker, 'click', function() {
      open_info();
    });
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
  
</script>
</html>