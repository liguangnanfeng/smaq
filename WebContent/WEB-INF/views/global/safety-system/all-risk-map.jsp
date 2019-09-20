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
        </style>
        </head>
        <body>
        <div id="container"></div>
        <!-- 加载地图JSAPI脚本 -->
        <script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.14&key=da3f11570a7a5d170923792f72357543&plugin=AMap.PolyEditor"></script>
        <script>
        var mapCenter = [120.299, 31.568];
        // if(user.latitude && user.longitude){
        // mapCenter[1] = user.latitude;
        // mapCenter[0] = user.longitude;
        // }
        console.log('123')
        console.log(${longitude},${latitude})

        var map = new AMap.Map('container', {
        resizeEnable: true, //是否监控地图容器尺寸变化
        zoom:11, //初始化地图层级
        center:mapCenter //初始化地图中心点
        });

        // 创建两个点标记
        var m1 = new AMap.Marker({
        position: [116.49, 39.9]
        });
        var m2 = new AMap.Marker({
        position: [116.29, 39.9]
        });
        var m3 = new AMap.Marker({
        position: [116.69, 39.9],
        icon: "https://webapi.amap.com/theme/v1.3/markers/n/mark_r.png"
        });
        map.add(m1);
        map.add(m2);
        map.add(m3);
        </script>
        </body>
        </html>