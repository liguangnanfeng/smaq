  var marker1 = null;
  var map = new AMap.Map("map_div1", {
      resizeEnable: true,
      zoom: 13,//地图显示的缩放级别
      keyboardEnable: false
  });
  
  AMap.plugin(['AMap.Autocomplete','AMap.PlaceSearch'],function(){
    var autoOptions = {
      city: "", //城市，默认全国
      input: "search"//使用联想输入的input的id
    };
    autocomplete= new AMap.Autocomplete(autoOptions);
    var placeSearch = new AMap.PlaceSearch({
          city:'',
          map:map
    })
    
    AMap.event.addListener(autocomplete, "select", function(e){
      placeSearch.search(e.poi.name);
    });
    
    AMap.event.addListener(placeSearch, "markerClick", function(e){
      console.log(e.data.location);//当前marker的经纬度信息
       $("#addressMap1").val(e.data.location.lng + ',' + e.data.location.lat);
    })
  });
  
  //设置中心点
  function setMark(lnglatXY) {
      if(null == marker1) {
        marker1 = new AMap.Marker({
          icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
          position: lnglatXY.split(","),
        });
        marker1.setMap(map);
      } else {
        marker1.setPosition(lnglatXY.split(","));
      }
  }
  
  $(function() {
    //为地图注册click事件获取鼠标点击出的经纬度坐标1
    map.on('click', function(e) {
       var lnglatXY = e.lnglat.getLng() + ',' + e.lnglat.getLat(); //已知点坐标
       $("#addressMap1").val(lnglatXY);
       setMark(lnglatXY,1);
    });
    
    if($("#addressMap1").val() == '') {
    } else {//自动定位经纬度
      var poiArr = $("#addressMap1").val();
      setMark(poiArr,1);
      map.setCenter(new AMap.LngLat(poiArr.split(',')[0], poiArr.split(',')[1]));
    }
  })
  
//  placeSearch.search(e.poi.name, function(status, result) {
//        if (status === 'complete' && result.info === 'OK') {                    
//            for(var h=0;h<result.poiList.pois.length;h++) {//返回搜索列表循环绑定marker
//                var jy = result.poiList.pois[h]['location'];//经纬度
//                var address = result.poiList.pois[h]['address'];//地址
//                var marker=new AMap.Marker({  //加点
//                    map: map,
//                    position: jy
//                });
//                marker.extData = {'getLng':jy['lng'],'getLat':jy['lat'],'address':address};//自定义想传入的参数
//                marker.content = '123123123';                       
//                marker.on("click",function(e) {
//                   var hs = e.target.extData;
//                   $("#addressMap1").val(hs['getLng'] + "," + hs[getLat]);
//                   
//                   infoWindow.open(map, e.target.getPosition());
//                   infoWindow.setContent(hs['address']);//点击以后窗口展示的内容
//                });
//                marker.emit('click', {target: marker});
//              }
//
//            //alert(result.poiList.pois[0].location);
//            //geocoder_CallBack(result);
//        }
//    });  //关键字查询查询