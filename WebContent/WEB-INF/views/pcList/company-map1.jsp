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
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css" />
<script src="https://cdn.bootcss.com/angular.js/1.3.2/angular.js"></script>
<script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
<script src="http://webapi.amap.com/maps?v=1.4.2&key=441462b46b736497a5f2a377aafb5b69&plugin=AMap.PlaceSearch"></script>
<script src="http://cache.amap.com/lbs/static/PlaceSearchRender.js"></script>
<script src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
</head>
<style>
	.main_map .left_map .selects select{width: 100%}
	.switch-left{
    	height: 26px;
    	vertical-align: top;
	}
	.switch-right{
	    height: 26px;
	    vertical-align: top;
	}
</style>
<body ng-app="app" ng-controller="appCtrl">
	<nav class="breadcrumb">
		<!-- <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
		<span class="c-gray en">&gt;</span> <span>企业信息库</span> 
		<span class="c-gray en">&gt;</span> <span>企业分布图</span>  -->
		<a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
			<i class="Hui-iconfont">&#xe68f;</i>
		</a>
	</nav>
	
    <div class="main_map clearfix">
        <div class="left_map">
            <div class="search">
                <input type="text" class="input-text" ng-model="companyName" placeholder="请输入企业名称..." /><img src="${ly}/images/searcher.png" alt="">
            </div>
            <div class="detail" ng-hide="detail">
                <div class="back" ng-click="back()">
                    <img src="${ly}/images/back.png" alt=""> 返回列表页面
                </div>
                <div class="detail_box">
                    <div class="b_top">
                        <h1>
                        	<span ng-bind="detail_company.companyName"></span>
                       	</h1>
                        <p>
                        	风险等级：
                        	<span ng-bind="detail_company.level"></span>
                       	</p>
                        <p>
                        	企业类型：
                        	<span ng-bind="detail_company.regType"></span>
                       	</p>
                    </div>
                    <div class="b_down">
                        <p><i class="Hui-iconfont">&#xe671;</i> 地址：<span ng-bind="detail_company.address"></span></p>
                        <p><i class="Hui-iconfont">&#xe6a3;</i> 安全员手机号：<span ng-bind="detail_company.safetyContact"></span></p>
                    </div>
                </div>
            </div>
            <div class="selects">
                <select ng-model="level" class="input-text">
                    <option value="">--请选择等级--</option>
                    <option ng-repeat="x in levels" value="{{x.id}}">{{x.name}}</option>
                </select>
                <!-- <select ng-model="townId" ng-change="townChange()">
                    <option value="">全部</option>
                    <option ng-repeat="x in townList" value="{{x.townId}}">{{x.name}}</option>
                </select>
                <select ng-model="villageId">
                    <option value="">全部</option>
                    <option ng-repeat="x in villageList" value="{{x.villageId}}">{{x.name}}</option>
                </select> -->
            </div>
            <ul class="list">
                <li ng-click="showItemId(x)" ng-repeat="x in companyList | filter:{companyName: companyName,level: level,townId: townId,villageId: villageId} as companyListFilter">
                    <div>
                        <span class="db" ng-class="x.dicolorCss" ng-bind="$index + 1"></span>
                        <span class="name" ng-bind="x.companyName"></span>
                    </div>
                    <div class="adress">地址：{{x.address}}</div>
                </li>
                <div ng-show="!(companyListFilter.length)">
                    <div class="empty_list">
                    	查询暂无数据...
                        <a href="javascript:;" ng-click="reset()">点击重置</a>
                    </div>
                </div>
            </ul>
        </div>
        <div class="right_map">
        	<div style="padding: 10px 10px 10px;text-align: center;position: fixed;top: 62px;right: 160px;z-index: 999;background-color: #fcf9f2;border-radius: 5px;box-shadow: 2px 2px 5px #333333;">
		    	<span style="vertical-align: middle;">普通图</span>
		    	<div style="vertical-align: middle;" id="mySwitch" class="switch"  data-on-label="<i class='icon-ok icon-white'></i>" data-off-label="<i class='icon-remove'></i>">
					<input type="checkbox" checked />
				</div>
				<span style="vertical-align: middle;">卫星图</span>
		    </div>
            <div id="container"></div>
        </div>
    </div>
<script>
var map = new AMap.Map('container', {
  resizeEnable: true,
  zoom: 13,
  <%--viewMode:'3D',--%>
  pitch:50,
  center: [120.299, 31.568]
});

var infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(0, -30)});

function markerClick(e){
  infoWindow.setContent(e.target.content);
  infoWindow.open(map, e.target.getPosition());
}

var placeSearchOptions = { //构造地点查询类
    pageSize: 1,
    pageIndex: 1
};
var placeSearch = new AMap.PlaceSearch();

var app = angular.module('app', []);
app.controller('appCtrl', function($scope, $filter, $compile, $http) {
  $scope.detail = true;
  $scope.back = function() {
      $scope.detail = true;
      map.setLayers([new AMap.TileLayer()]);
      var mapList = $scope.markerList;
      for (var i = 0; i < mapList.length; i++) {
          var data = mapList[i].getExtData();
          var datastr = JSON.stringify(data);
          //mapList[i].setContent("<div class='mark' data='" + datastr + "'>" + data.id + "</div>");
      }
      map.setCenter([120.299, 31.568]);
      map.setZoom(13);
  };
  $scope.reset = function() {
      $scope.companyName = "";
      $scope.level = "";
  };
  $scope.showItemId = function(item) {
    if (typeof item == "string") {
        item = JSON.parse(item);
    }
    $scope.detail = false;
    $scope.detail_company = item;
    map.setLayers([new AMap.TileLayer.Satellite()]);
    var mapList = $scope.markerList;
    for (var i = 0; i < mapList.length; i++) {
        var data = mapList[i].getExtData();
        if (item.id === data.id) {
            //$scope.saveLocation(data.userId, data.companyName);
            if(data.longitude == 0.000001 || data.longitude == ""){
              $scope.saveLocation(data.userId, data.companyName);
            }else{
              map.setCenter([data.longitude, data.latitude]);
              map.setZoom(18);
              infoWindow.setContent("("+ data.id +")" + data.companyName + "<br/>" + "风险等级：" + data.level + "<br/>" + "安全员手机号：" + data.safetyContact);
              infoWindow.open(map, [data.longitude,data.latitude]);
            }
        }
    }
};
$scope.saveLocation = function(userId, cname){

  layer.msg('企业自动定位中。。。');
  setTimeout(function() {
    //关键字查询
    placeSearch.search(cname, function(status, result) {
      console.log(userId);
      console.log(cname);
      //layer.closeAll();
      if (status === 'complete' && result.info === 'OK') {
        
        var tips = result.poiList.pois[0];
        var lnglatXY = tips.location.lng + "," + tips.location.lat;
        
        $.post(getRootPath() + "/company/company-edit-do",{
          userId : userId,
          address : tips.address + "(" + tips.name + ")",
          jw : lnglatXY
        },function(result){
          var data = result.map.data;
          companyList = $scope.companyList;
          for (var i = 0; i < companyList.length; i++) {
            if(companyList[i].userId === data.id){
              companyList[i].longitude = data.longitude;
              companyList[i].latitude = data.latitude;
              var mapList = $scope.markerList;
              for (var m = 0; m < mapList.length; m++) {
                  if (mapList[m].getExtData().userId === data.id) {
                      mapList[m].setPosition([data.longitude, data.latitude]);
                      map.setCenter([data.longitude, data.latitude]);
                      map.setZoom(18);
                      infoWindow.setContent("("+ data.id +")" + "<span onClick='markerClick(" + data.userId + ")'>" + companyList[i].companyName + "<br/>" + "风险等级：" + companyList[i].level + "<br/>" + "安全员手机号：" + companyList[i].safetyContact) + "</span>";
                      infoWindow.open(map, [data.longitude,data.latitude]);
                      break;
                  }
              }
              break;
            }
          }
        })
      } else {
        layer.alert("企业定位失败");
      }
    });
  }, 2000);
};
  $scope.$watch("companyListFilter", function(newValue, oldValue) {
      if (newValue == null || oldValue == null) return;

      var mapList = $scope.markerList;
      var count = 0;
      for (var m = 0; m < mapList.length; m++) {
          var flag = false;
          for (var n = 0; n < newValue.length; n++) {
              if (mapList[m].getExtData().id === newValue[n].id) {
                  count++;
                  flag = true;
                  break;
              }
          }
          var dicolorCss = mapList[m].getExtData().dicolorCss;
          mapList[m].setContent("<div id='mark" + mapList[m].getExtData().id + "' class='mark  "+ dicolorCss +"'>" + (count) + "</div>");
          var data = JSON.stringify(mapList[m].getExtData());
          var html = "<div class='mkbt' ng-click='showItemId(" + data + ")'></a>";
          var template = angular.element(html);
          var mobileDialogElement = $compile(template)($scope);
          $("#mark" + mapList[m].getExtData().id).append(mobileDialogElement);
          if (flag) {
              mapList[m].show();
          } else {
              mapList[m].hide();
          }
      }
  });

  $scope.levels = [{
          id: "红色",
          name: "红色"
      },
      {
          id: "橙色",
          name: "橙色"
      },
      {
          id: "黄色",
          name: "黄色"
      },
      {
       	  id: "蓝色",
          name: "蓝色"
   	  }
  ];
  $scope.companyList = ${companyList };
  for (var i = 0; i < $scope.companyList.length; i++) {
    var dicolor = $scope.companyList[i].level;
	if(dicolor == "红色"){
	  $scope.companyList[i].dicolorCss = "dicolor1";
    } else if (dicolor == "橙色") {
      $scope.companyList[i].dicolorCss = "dicolor2";
    } else if (dicolor == "黄色") {
      $scope.companyList[i].dicolorCss = "dicolor3";
    } else if (dicolor == "蓝色") {
      $scope.companyList[i].dicolorCss = "dicolor4";
    } else {
      $scope.companyList[i].dicolorCss = "dicolor4";
    }
  }
  $scope.markerList = new Array();
  var mapList = $scope.companyList;
  for (var i = 0; i < mapList.length; i++) {
      var data = JSON.stringify(mapList[i]);
      
      var marker = new AMap.Marker({
          content: "<div class='mark "+ mapList[i].dicolorCss +"' data='" + data + "'>" + mapList[i].id + "</div>",
          position: [mapList[i].longitude, mapList[i].latitude],
          extData: mapList[i],
          map: map
      });
      marker.content = "("+ mapList[i].id +")" + mapList[i].companyName + "<br/>" + "风险等级：" + mapList[i].level + "<br/>" + "安全员手机号：" + mapList[i].safetyContact;
      marker.on('click', markerClick);
      $scope.markerList.push(marker);
  }
  
  // 页面加载完成后调用
  $scope.$watch('$viewContentLoaded', function() {
      var time = setInterval(function() {
          if (document.querySelector(".mark") != null) {
              $(".mark").each(function(index, element) {
                  var data = JSON.stringify($(".mark").eq(index).attr("data"));
                  var html = "<div class='mkbt' ng-click='showItemId(" + data + ")'></a>";
                  var template = angular.element(html);
                  var mobileDialogElement = $compile(template)($scope);
                  $(".mark").eq(index).append(mobileDialogElement);
              });
              clearInterval(time);
          }
      }, 1000);
  });
});
</script>
</body>
</html>