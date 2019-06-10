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
<link rel="stylesheet" href="https://cache.amap.com/lbs/static/main1119.css" />
<script src="https://cache.amap.com/lbs/static/es5.min.js"></script>
<script src="http://webapi.amap.com/maps?v=1.4.2&key=441462b46b736497a5f2a377aafb5b69&plugin=AMap.PlaceSearch"></script>
<script src="https://cache.amap.com/lbs/static/PlaceSearchRender.js"></script>

<script src="${ly}/js/angular.js"></script>
<script src="${ly}/js/angular-animate.js"></script>
<script src="${ly}/js/angular-sanitize.js"></script>
<script src="${ly}/js/ui-bootstrap-tpls-2.5.0.js"></script>
</head>
<style>
	.main_map .left_map .selects select{width: 50%}
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
		<i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
		<span class="c-gray en">&gt;</span> <span>企业信息库</span> 
		<span class="c-gray en">&gt;</span> <span>企业分布图</span> 
		<div class="back" ng-click="back()" ng-hide="backBtn" style="display: inline-block;z-index: 99;position: absolute;right: 100px;cursor: pointer;">
        	<img src="${ly}/images/back.png" alt="" style="width: 20px;height: 20px;line-height: 20px;"> 返回列表页面
        </div>
		<a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
			<i class="Hui-iconfont">&#xe68f;</i>
		</a>
	</nav>
	
    <div class="main_map clearfix">
        <div class="left_map">
            <div class="search" ng-hide="search">
                <input type="text" class="input-text" ng-model="companyName" placeholder="请输入企业名称..." />
            </div>
            <div class="detail" ng-hide="detail">
                <div class="detail_box">
                    <div class="b_top" ng-class="detail_company.dicolorCss">
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
            
            <div class="mb-10">
	          <span>所属行业：</span>
	          <select class="sel_area" id="industry2_" name="industry2_">
	            <option value="">请选择</option>
	          </select>
	          <script type="text/javascript" src="${ly }/js/fxgk/industry.js?v=180920"></script>
	          <script type="text/javascript">
	          $(function() {
	            $.each(industry2List, function(i, item) {
	              $("#industry2_").append("<option>" + item.name + "</option>");
	            })
	          })
	          </script>
	        </div>
		        
            <div class="selects">
                <select ng-model="level" class="input-text">
                    <option value="">--请选择等级--</option>
                    <option ng-repeat="x in levels" value="{{x.id}}">{{x.name}}</option>
                </select>
                <!-- <select ng-model="townId" ng-change="townChange()">
                    <option value="">全部</option>
                    <option ng-repeat="x in townList" value="{{x.townId}}">{{x.name}}</option>
                </select> -->
                <select ng-model="villageId" class="input-text">
                    <option value="">--请选择村--</option>
                    <option ng-repeat="x in villageList" value="{{x.villageId}}">{{x.name}}</option>
                </select>
            </div>
            <input class="btn btn-primary radius btn-block" ng-click="searchBtn()" type="button" value="查询">
            <ul class="list">
                <li ng-click="showItemId(x)" ng-repeat="x in companyList">
                    <div>
                        <span class="db" ng-class="x.dicolorCss" ng-bind="$index + 1"></span>
                        <span class="name" ng-bind="x.companyName"></span>
                    </div>
                    <div class="adress">地址：{{x.address}}</div>
                </li>
            </ul>
            <!-- <ul previous-text="上一页" next-text="下一页" direction-links="false" uib-pagination total-items="totalItems" items-per-page="itemsPerPage" ng-model="currentPage" max-size="maxSize" class="pagination-sm" boundary-link-numbers="true" ng-change="pageChanged()" rotate="false"></ul> -->
        </div>
        <div class="right_map">
        	<div style="padding: 10px 10px 10px;text-align: center;position: fixed;top: 62px;right: 160px;z-index: 999;background-color: #fcf9f2;border-radius: 5px;box-shadow: 2px 2px 5px #333333;">
		    	<span style="vertical-align: middle;">普通图</span>
		    	<div style="vertical-align: middle;" id="mySwitch" class="switch"  data-on-label="<i class='icon-ok icon-white'></i>" data-off-label="<i class='icon-remove'></i>">
					<input type="checkbox" checked />
				</div>
				<span style="vertical-align: middle;">卫星图</span>
		    </div>
        	<div style="padding: 10px 10px 10px;text-align: center;position: fixed;top: 60px;right: 60px;z-index: 999;background-color: #fcf9f2;border-radius: 5px;box-shadow: 2px 2px 5px #333333;">
    			<input class="btn btn-primary radius" type="button" value="测距" onClick="javascript:startRuler()" />
    		</div>
            <div id="container"></div>
        </div>
    </div>
<script>
var user = JSON.parse('${user}');
var app = angular.module('app', ['ngAnimate', 'ngSanitize', 'ui.bootstrap']);
app.controller('appCtrl', function($scope, $filter, $compile, $http) {
  
  $scope.itemsPerPage = 20;
  $scope.maxSize = 5;
  $scope.totalItems = ${count};
  $scope.currentPage = 1;
  
  $scope.setPage = function (pageNo) {
    $scope.currentPage = pageNo;
  };

  $scope.pageChanged = function() {
		var dto = {
	   	    companyName: $scope.companyName,
	   	    townId: $scope.townId,
	   	    villageId: $scope.villageId,
	   	    dlevel: $scope.dlevel,
	   	    page: $scope.currentPage,
	   	    limit: $scope.itemsPerPage,
	   	}
	  $http({
	      method: 'POST',
	      url: '${ly }/common/getCompanyByMap',
	      data: JSON.stringify(dto)
	  }).then(function successCallback(response) {
	          // 请求成功执行代码
	          $scope.companyList = response.data.map.list;
	          $scope.totalItems = response.data.map.count;
	          //console.log($scope.companyList);
	          $scope.initCompanyList();
	      }, function errorCallback(response) {
	          // 请求失败执行代码
	          layer.alert("请求失败！");
	  });
  };
  

  $scope.searchBtn = function() {
    var industry = angular.element("#industry2_").val();
    $scope.currentPage = 1;
    var dto = {
   	    companyName: $scope.companyName,
   	   	industry: industry,
   	    townId: $scope.townId,
   	    villageId: $scope.villageId,
   	   	dlevel: $scope.level,
   	    page: $scope.currentPage,
   	    limit: $scope.itemsPerPage,
   	}
    console.log(dto);
    //return;
  $http({
      method: 'POST',
      url: '${ly }/common/getCompanyByMap',
      data: JSON.stringify(dto)
  }).then(function successCallback(response) {
          // 请求成功执行代码
          $scope.companyList = response.data.map.list;
          $scope.totalItems = response.data.map.count;
          //console.log($scope.companyList);
          $scope.initCompanyList();
      }, function errorCallback(response) {
          // 请求失败执行代码
          layer.alert("请求失败！");
  });
  }
  
  //企业列表初始化事件
  $scope.initCompanyList = function() {
    
  //关闭信息窗体
    map.clearInfoWindow();
    
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
    
    //清除所有mark
    var mapList = $scope.markerList;
    for (var i = 0; i < mapList.length; i++) {
      	map.remove(mapList[i]);
    }
    
    //return;
    
    $scope.markerList = new Array();
    mapList = $scope.companyList;
    for (var i = 0; i < mapList.length; i++) {
        var marker = new AMap.Marker({
          content: "<div class='mark "+ mapList[i].dicolorCss +"' data='" + data + "'>" + mapList[i].id + "</div>",
            position: [mapList[i].longitude, mapList[i].latitude],
            extData: mapList[i],
            map: map
        });
        marker.content = "("+ mapList[i].id +")" + mapList[i].companyName + "<br/>" + "风险等级：" + mapList[i].level + "<br/>" + "安全员手机号：" + mapList[i].safetyContact;
        // marker.on('click', markerClick);
        $scope.markerList.push(marker);
    }
    
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
  }, 100);
  };
  
  $scope.detail = true;
  $scope.backBtn = true;
  $scope.search = false;
  $scope.back = function() {
      $scope.detail = true;
      $scope.backBtn = true;
      $scope.search = false;
      map.setLayers([new AMap.TileLayer()]);
      var mapList = $scope.markerList;
      for (var i = 0; i < mapList.length; i++) {
          var data = mapList[i].getExtData();
          var datastr = JSON.stringify(data);
          //mapList[i].setContent("<div class='mark' data='" + datastr + "'>" + data.id + "</div>");
      }
      map.setCenter(mapCenter);
      map.setZoom(13);
  };
  $scope.reset = function() {
      $scope.companyName = "";
      $scope.level = "";
      $scope.villageId = "";
  };
  $scope.showItemId = function(item) {
    if (typeof item == "string") {
        item = JSON.parse(item);
    }
    $scope.detail = false;
    $scope.backBtn = false;
    $scope.search = true;
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
              infoWindow.setContent("("+ data.id +")" + "<span onClick='markerClick(" + data.userId + ")'>" + data.companyName + "<br/>" + "风险等级：" + data.level + "<br/>" + "安全员手机号：" + data.safetyContact + "</span>");
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
                      infoWindow.setContent("("+ data.id +")" + "<span onClick='markerClick(" + data.userId + ")'>" + companyList[i].companyName + "<br/>" + "风险等级：" + companyList[i].level + "<br/>" + "安全员手机号：" + companyList[i].safetyContact + "</span>");
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
  $scope.villageList = ${villageList };
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
      // marker.on('click', markerClick);
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
<script src="${ly}/js/myMap.js"></script>
</body>
</html>