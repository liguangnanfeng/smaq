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
<script src="https://cdn.bootcss.com/angular.js/1.3.2/angular.js"></script>
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
</head>
<body ng-app="app" ng-controller="appCtrl">
    <nav class="breadcrumb">
        <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
        <span class="c-gray en">&gt;</span> <span>企业信息库</span>
        <span class="c-gray en">&gt;</span> <span>添加企业</span>
        <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
	      <i class="Hui-iconfont">&#xe68f;</i>
	    </a>
    </nav>
    <div class="page-container">
        <form class="form form-horizontal">
            <div class="cl" style="margin: 20px 0;">
                <div style="float:left;width:15%;text-align:right;line-height: 31px;">企业名称：</div>
                <div style="float:right;width:85%;text-align:left;">
                    <input class="input-text radius size-M" type="text" ng-model="companyName" placeholder="请输入企业名称" style="width:350px">
                    <button ng-click="searchSubmit()" class="btn btn-primary radius" type="button" style="padding: 0 15px;">搜索</button>
                </div>
            </div>
            <div class="cl" style="margin: 20px 0;">
                <div style="float:left;width:15%;text-align:right;">企业列表：</div>
                <div style="float:left;width:750px;border: solid 1px #ddd;box-sizing: border-box;padding: 4px 5px;max-height:300px;overflow-y: scroll;" class="radius">
                    <div ng-repeat="x in companylist as companyListFilter" style="float:left;width:50%">
                        <input type="checkbox" id="checkbox-{{x.userId}}" ng-checked="x.select" ng-click="updateSelection($event,x)">
                        <label for="checkbox-{{x.userId}}">{{x.companyName}}</label>
                    </div>
                    <div ng-show="!(companyListFilter.length)">
                        <div class="empty_list" style="text-align: center;">
                   			查询暂无数据...
                        </div>
                    </div>
                </div>
            </div>
            <!-- <div class="cl" style="margin: 20px 0;">
                <div style="float:left;width:15%;text-align:right;line-height: 31px;">所含企业：</div>
                <div style="float:right;width:85%;text-align:left;">
                    <div id="companyArea" ng-click="companySelect()" style="width:350px;border: solid 1px #ddd;min-height:200px;padding:5px;box-sizing:border-box;">
                        <ul class="cl">
                            <li ng-repeat="x in tradeCompany" style="float:left;padding: 0 10px;">
                                <span class="label label-primary radius" data-option="{{x.uid}}" ng-bind="x.name"></span>
                            </li>
                        </ul>
                    </div>
                </div>
            </div> -->
            <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                    <button id="saveSubmit" ng-click="saveSubmit()" class="btn btn-primary radius" type="button" style="padding: 0 70px;"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
                    <a href="${ly }/trade/company-add" style="text-decoration: underline;color: #0a6999;">添加新行业企业</a>
                </div>
            </div>
        </form>
    </div>
</body>
<script>
$('#saveSubmit').click(function() {
  show_tab('全部企业', getRootPath() + '/village/company/company-list');
});

var app = angular.module('app', []);
app.controller('appCtrl', function($scope, $filter, $compile, $http) {
  $scope.companylist = [];
  $scope.tradeCompany = ${ tradeCompany };

  $scope.searchSubmit = function() {
    var companyName = $scope.companyName;
    $.post(getRootPath() + "/company/company-list-name",{
      companyName : companyName
    },function(result){
      //console.log(JSON.stringify(result.map.list));
      $scope.companylist = result.map.list;
      
      for (var m = 0; m < $scope.companylist.length; m++) {
      	$scope.companylist[m].select = false;
      	for (var n = 0; n < $scope.tradeCompany.length; n++) {
          if ($scope.companylist[m].userId == $scope.tradeCompany[n].uid) {
            	//console.log($scope.companylist[m].userId);
            	//console.log($scope.tradeCompany[n].uid);
              $scope.companylist[m].select = true;
          }
      	}
		}
      $scope.$apply();
    });
  };

  $scope.updateSelection = function($event, x) {
      var checkbox = $event.target;
      if (checkbox.checked) {
	        $http({
	  	      method: 'GET',
	  	      url: '${ly }/trade/trade-company-add?companyId=' + x.userId
	  	  }).then(function successCallback(response) {
		  	    var tradeCompany = response.data.map.tradeCompany;
		        tradeCompany.name = x.companyName;
		        $scope.tradeCompany.push(tradeCompany);
	  	    }, function errorCallback(response) {
	  	  });
      } else {
        var id = {};
        for (var n = 0; n < $scope.tradeCompany.length; n++) {
          if ($scope.tradeCompany[n].uid == x.userId) {
            id = $scope.tradeCompany[n].id;
              $scope.tradeCompany.splice(n, 1);
              break;
          }
      }
        $http({
  	      method: 'GET',
  	      url: '${ly }/trade/trade-company-del?id=' + id
  	  }).then(function successCallback(response) {
  	    
  	    }, function errorCallback(response) {
  	  });
      }
  };
  
});
</script>
</html>