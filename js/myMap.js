var mapCenter = [120.299, 31.568];
if(user.latitude && user.longitude){
  	mapCenter[1] = user.latitude;
  	mapCenter[0] = user.longitude;
}
var map = new AMap.Map('container', {
  resizeEnable: true,
  zoom: 13,
  zooms: [3,20],
  viewMode: '3D',
  pitch: 50,
  expandZoomRange: true,
  center: mapCenter
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

var infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(0, -30)});

function markerClick(e){
//  infoWindow.setContent(e.target.content);
//  infoWindow.open(map, e.target.getPosition());
  
  show_dialog("企业详细信息", "/village/company/company-show?userId=" + e)
}

//关闭信息窗体
function closeInfoWindow() {
    map.clearInfoWindow();
}

var placeSearchOptions = { //构造地点查询类
    pageSize: 1,
    pageIndex: 1
};
var placeSearch = new AMap.PlaceSearch();

$('#mySwitch').on('switch-change', function (e, data) {
  var value = data.value;
  //console.log(e, $el, value);
  if(value){
    map.setLayers([new AMap.TileLayer()]);
  } else {
    
    
    map.setLayers([new AMap.TileLayer.Satellite()]);
  }
});