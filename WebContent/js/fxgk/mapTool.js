function mapTool(container,options) {
    var _this=this,map,mouseTool,overlays = [];
    var opt=$.extend({},{
        center:[117.000923,36.675807],
        resizeEnable: true,
        zoom:17,
        zooms:[3,20],
        expandZoomRange:true,
        pitch:50,
        layers: [new AMap.TileLayer.Satellite()],
        keyboardEnable: false,
        //viewMode:'3D', //3d就不能显示多边形
    },options);

    var init=function () {
        _this.map = map = new AMap.Map(container,opt);
        _this.mouseTool = mouseTool = new AMap.MouseTool(map)
            .on('draw',function(e){
                overlays.push(e.obj);
                _this.graphs[_this.drawingName]=e.obj;
                mouseTool.close();//关闭，并清除覆盖物
                if(opt.drawCallback){
                    opt.drawCallback(e.obj);
                }
            });
        _this.graphs={};
        _this.drawingName='';
    }

    _this.draw=function(type,name,opt){
        if(_this.graphs[name]==undefined){
            switch(type){
                case 'marker':{
                    mouseTool.marker($.extend({
                        //同Marker的Option设置
                    },opt));
                    break;
                }
                case 'polyline':{
                    mouseTool.polyline($.extend({
                        strokeColor:'#80d8ff',
                        zIndex: 51,
                        //同Polyline的Option设置
                    },opt));
                    break;
                }
                case 'rectangle':{
                    mouseTool.rectangle($.extend({
                        fillColor:'#00b0ff',
                        strokeColor:'#80d8ff',
                        zIndex: 51,
                        //同Polygon的Option设置
                    },opt));
                    break;
                }
                case 'circle':{
                    mouseTool.circle($.extend({
                        fillColor:'#00b0ff',
                        strokeColor:'#80d8ff',
                        zIndex: 51,
                        //同Circle的Option设置
                    },opt));
                    break;
                }
                case 'polygon':{
                    var polygon = mouseTool.polygon($.extend({
                        fillColor:'#00b0ff',
                        strokeColor:'#80d8ff',
                        zIndex: 51,
                        //同Polygon的Option设置
                    },opt));
                    _this.drawingName=name;
                    return polygon;
                    break;
                }
            }
        }else {
            return _this.edit(type,name);
        }
    }
    _this.add=function(type,name,opt){
        if(_this.graphs[name]==undefined){
            switch(type){
                case 'marker':{
                    var marker =new AMap.Marker($.extend({
                        //同Marker的Option设置
                        icon: "https://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
                        position: [116.405467, 39.907761],
                        offset: new AMap.Pixel(-13, -30),
                        // 设置是否可以拖拽
                        draggable: true,
                        cursor: 'move',
                        // 设置拖拽效果
                        raiseOnDrag: true
                    },opt));
                    map.add(marker);
                    map.setFitView();
                    _this.graphs.push({name:marker});
                    return marker;
                    break;
                }
                case 'polyline':{
                    var polyline = new AMap.Polyline($.extend({
                        strokeColor:'#80d8ff',
                        path: [
                            [116.368904,39.913423],
                            [116.382122,39.901176],
                            [116.387271,39.912501],
                            [116.398258,39.904600]
                        ],
                        borderWeight: 2, // 线条宽度，默认为 1
                        strokeColor: 'red', // 线条颜色
                        lineJoin: 'round' // 折线拐点连接处样式
                        //同Polyline的Option设置
                    },opt));
                    map.add(polyline);
                    map.setFitView();
                    _this.graphs.push({name:polyline});
                    return polyline;
                    break;
                }
                case 'rectangle':{
                    var southWest = new AMap.LngLat(116.356449, 39.859008);
                    var northEast = new AMap.LngLat(116.417901, 39.893797);
                    var bounds = new AMap.Bounds(southWest, northEast);
                    var rectangle = new AMap.Rectangle($.extend({
                        bounds: bounds,
                        strokeColor:'red',
                        strokeWeight: 6,
                        strokeOpacity:0.5,
                        strokeDasharray: [30,10],
                        // strokeStyle还支持 solid
                        strokeStyle: 'dashed',
                        fillColor:'blue',
                        fillOpacity:0.5,
                        cursor:'pointer',
                        zIndex:50,
                        //同Polygon的Option设置
                    },opt));
                    map.add(rectangle);
                    map.setFitView();
                    _this.graphs.push({name:rectangle});
                    return rectangle;
                    break;
                }
                case 'circle':{
                    var circle = new AMap.Circle($.extend({
                        center: ["116.403322", "39.920255"], // 圆心位置
                        radius: 1000,  //半径
                        strokeColor: "#F33",  //线颜色
                        strokeOpacity: 1,  //线透明度
                        strokeWeight: 3,  //线粗细度
                        fillColor: "#ee2200",  //填充颜色
                        fillOpacity: 0.35 //填充透明度
                        //同Circle的Option设置
                    },opt));
                    map.add(circle);
                    map.setFitView();
                    _this.graphs.push({name:circle});
                    return circle;
                    break;
                }
                case 'polygon':{
                    var polygon = new AMap.Polygon($.extend({
                        path: [
                            [116.403322, 39.920255],
                            [116.410703, 39.897555],
                            [116.402292, 39.892353],
                            [116.389846, 39.891365]
                        ],
                        strokeColor: "#FF33FF",
                        strokeWeight: 3,
                        strokeOpacity: 1,
                        fillOpacity: 0.4,
                        fillColor: '#1791fc',
                        zIndex: 50,
                        //同Polygon的Option设置
                    },opt));
                    map.add(polygon);
                    map.setFitView();
                    _this.graphs[name]=polygon;
                    return polygon;
                    break;
                }
            }
        }else {
            map.setFitView();
        }
    }
    _this.edit=function(type,name){
        if(_this.graphs[name]){
            switch(type){
                case 'marker':{
                    map.setFitView();
                    var polyEditor = new AMap.PolyEditor(map, _this.graphs[name]);
                    return polyEditor;
                    break;
                }
                case 'polyline':{
                    map.setFitView();
                    var polyEditor = new AMap.PolyEditor(map, _this.graphs[name]);
                    return polyEditor;
                    break;
                }
                case 'rectangle':{
                    map.setFitView();
                    var rectangleEditor = new AMap.RectangleEditor(map, _this.graphs[name]);
                    return rectangleEditor;
                    break;
                }
                case 'circle':{
                    map.setFitView();
                    var circleEditor = new AMap.CircleEditor(map, _this.graphs[name]);
                    return circleEditor;
                    break;
                }
                case 'polygon':{
                    map.setFitView();
                    var polyEditor = new AMap.PolyEditor(map, _this.graphs[name]);
                    return polyEditor;
                    break;
                }
            }
        }else {
            map.setFitView();
        }
    }
    _this.remove=function(name){
        if(_this.graphs[name]){
            map.remove(_this.graphs[name]);
            delete _this.graphs[name];
        }else {
            map.setFitView();
        }
    }
    _this.clear=function(){
        map.remove(overlays);
        overlays = [];
    }
    _this.close=function(isAll){
        isAll=isAll?isAll:false;
        mouseTool.close(isAll)//关闭，并清除覆盖物
    }

    _this.getOverlays=function (type) {
        return map.getAllOverlays(type);
    }

    init.call(this);
}
