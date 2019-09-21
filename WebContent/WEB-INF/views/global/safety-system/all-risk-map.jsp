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
        var a1='${longitude}',a2='${latitude}';
        if(a1 && a2){
         mapCenter[0] = '${longitude}';
         mapCenter[1] = '${latitude}';
        }
        console.log('默认坐标:');
        console.log('${longitude}','${latitude}');

        var map = new AMap.Map('container', {
        resizeEnable: true, //是否监控地图容器尺寸变化
        zoom:11, //初始化地图层级
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
        </c:forEach>
       <c:forEach items="${list2}" varStatus="index2" var="be2">
           let mm${index2.index}='${be2.longitude}';let nn${index2.index}='${be2.latitude}';
           if(mm${index2.index}&&nn${index2.index}){
            let n${index2.index} = new AMap.Marker({
            position: [${be2.longitude}, ${be2.latitude}],
           icon: "/images/dw/or_b2.png"
            });

            map.add(n${index2.index});
           }
       </c:forEach>

        <c:forEach items="${list3}" varStatus="index3" var="be3">
            let pp${index3.index}='${be3.longitude}';let oo${index3.index}='${be3.latitude}';
            if(pp${index3.index}&&oo${index3.index}){
            let p${index3.index} = new AMap.Marker({
            position: [${be3.longitude}, ${be3.latitude}],
            icon: "/images/dw/ye_b2.png"
            });
            map.add(p${index3.index});
            }
        </c:forEach>

        <c:forEach items="${list4}" varStatus="index4" var="bee">
            let qq${index4.index}='${bee.longitude}';let rr${index4.index}='${bee.latitude}';
            if(qq${index4.index}&&rr${index4.index}){
            let q${index4.index} = new AMap.Marker({
            position: [${bee.longitude}, ${bee.latitude}],
            icon: "/images/dw/bl_b2.png"
            });
            map.add(q${index4.index});
            }
       </c:forEach>

        </script>
        </body>
        </html>