    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <%@ include file="/WEB-INF/views/taglibs.jsp" %>
    <!doctype html>
        <html>
        <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
        <link rel="stylesheet" href="https://a.amap.com/jsapi_demos/static/demo-center/css/demo-center.css" />
        <title>地图显示</title>
        <style>
        html,
        body,
        #container {
        width: 100%;
        height: 100%;
        }
        .font{
        font-size: 15px;
        padding-left: 10px;
        padding-top: 3px;
        padding-right: 2px;
        font-weight: 400;
        }
        </style>
        </head>
        <body>
        <div id="container"></div>
        <!-- 加载地图JSAPI脚本 -->
        <script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.14&key=da3f11570a7a5d170923792f72357543&plugin=AMap.PolyEditor"></script>
        <script>
        var mapCenter = [];
        console.log('${list}');
        console.log('默认坐标:');
        console.log('${longitude}','${latitude}');
        let x='';let y='';
        <c:forEach items="${list1}" varStatus="index" var="be">
            x='${be.longitude}'; y='${be.latitude}';
        </c:forEach>
        if(x && y){
        console.log("${list1}")
        console.log("hello!");
        mapCenter[0] = x;
        mapCenter[1] = y;
        }
        console.log(x,y)
        var map = new AMap.Map('container', {
        resizeEnable: true, //是否监控地图容器尺寸变化
        zoom: 116, //初始化地图层级
        center:mapCenter //初始化地图中心点
        });
        // 创建两个点标记

        <c:forEach items="${list1}" varStatus="index" var="be">
            let xx${index.index}='${be.longitude}';let yy${index.index}='${be.latitude}';
            if(xx${index.index}&&yy${index.index}){
            let m${index.index} = new AMap.Marker({
            position: ['${be.longitude}', '${be.latitude}'],
            icon: "/images/dw/re_b2.png"
            });
            map.add(m${index.index});
            }
            //添加marker标记
            function addMarker() {
            map.clearMap();
            marker${index.index} = new AMap.Marker({
            map: map,
            position: ['${be.longitude}', '${be.latitude}']
            });
            //鼠标点击marker弹出自定义的信息窗体
            AMap.event.addListener(marker${index.index}, 'click', function () {
            infoWindow${index.index}.open(map, marker${index.index}.getPosition());
            });
            }
            addMarker();
            //实例化信息窗体
            infoWindow${index.index} = new AMap.InfoWindow({
            isCustom: false,  //使用自定义窗体
            content: '<div class="font">${be.user_name}</div>',
            offset: new AMap.Pixel(10, -35)
            });
            infoWindow${index.index}.open(map, marker${index.index}.getPosition());

        </c:forEach>






        </script>
        </body>
        </html>