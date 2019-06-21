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
<link rel="stylesheet" href="https://cache.amap.com/lbs/static/main.css"/>
<title>后台首页</title>
<meta name="keywords" content="后台首页">
<meta name="description" content="后台首页">
<style type="text/css">
body .amap-sug-result{z-index:9999} 
body .check-box label{line-height:26px;}
</style>
</head>
<body ng-app="app" ng-controller="appCtrl">
    <nav class="breadcrumb">
        <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
        <span class="c-gray en">&gt;</span> <span>行业管理</span>
        <span class="c-gray en">&gt;</span> <span>编辑行业</span>
        <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
    </nav>
    <div class="page-container">
        <form class="form form-horizontal">
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">登录账号：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="hidden" id="userId" value="${user.id }">
                    <input type="text" id="userName" value="${user.userName }" style="width:400px" class="input-text">
                </div>
            </div>
            <c:if test="${empty user.id}">
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">登录密码：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="password" id="userPsw" style="width:400px" class="input-text" maxlength="50">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">确认密码：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="password" id="userPswRe" style="width:400px" class="input-text" maxlength="50">
                </div>
            </div>
            </c:if>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">行业名称：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" id="tradeName" value="${trade.name }" style="width:400px" class="input-text" maxlength="50">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">所含企业：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <div id="companyArea" ng-click="companySelect()" style="width:400px;border: solid 1px #ddd;min-height:200px;padding:5px;box-sizing:border-box">
                    	<ul class="cl">
			                <li ng-repeat="x in tradeCompany" style="float:left;padding: 0 10px;">
			                    <span class="label label-primary radius" data-option="{{x.uid}}" ng-bind="x.name"></span>
			                </li>
			            </ul>
                    </div>
                </div>
            </div>
            <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                    <button id="saveSubmit" ng-click="saveSubmit()" class="btn btn-primary radius" type="button" style="padding: 0 70px;"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
                </div>
            </div>
        </form>
        <!-- 企业弹窗 -->
        <div id="win" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">选择企业</h4>
                    </div>
                    <div class="modal-body">
                        <div class="cl" style="margin: 20px 0;">
                            <div style="float:left;width:15%;text-align:right;line-height: 31px;">企业名称：</div>
                            <div style="float:right;width:85%;text-align:left;">
                                <input class="input-text radius size-M" type="text" ng-model="companyName" placeholder="请输入企业名称">
                            </div>
                        </div>
                        <div class="cl" style="margin: 20px 0;">
                            <div style="float:left;width:15%;text-align:right;line-height: 31px;">行业类别：</div>
                            <div style="float:right;width:85%;text-align:left;">
                                <span class="select-box radius">
									<select class="select" ng-model="type">
										<option value="">--请选择类别--</option>
					                    <option ng-repeat="x in tradeType" value="{{x.name}}">{{x.name}}</option>
									</select>
								</span>
                            </div>
                        </div>
                        <div class="cl" style="margin: 20px 0;">
                            <div style="float:left;width:15%;text-align:right;line-height: 31px;">地区选择：</div>
                            <div style="float:right;width:85%;text-align:left;">
                                <span class="select-box radius" style="width:200px;">
									<select class="select" ng-model="district" id="area" ng-change="districtChange()">
										<option value="">请选择区</option>
										<c:forEach items="${arealist }" var="list">
							            	<option value="${list.code }">${list.name }</option>
							            </c:forEach>
									</select>
								</span>
			               		<span class="select-box radius" style="width:200px;">
									<select class="select" ng-model="town" id="town" ng-change="townChange()">
										<option value="">请选择镇</option>
										<option ng-repeat="x in townlist" value="{{x.userId}}">{{x.name}}</option>
									</select>
								</span>
			               		<span class="select-box radius" style="width:200px;">
									<select class="select" ng-model="village" id="village">
										<option value="">请选择村</option>
										<option ng-repeat="x in villagelist" value="{{x.userId}}">{{x.name}}</option>
									</select>
								</span>
                            </div>
                        </div>
                        <div class="cl" style="margin: 20px 0;">
                            <div style="float:left;width:15%;text-align:right;">企业列表：</div>
                            <div style="float:right;width:85%;text-align:left;border: solid 1px #ddd;box-sizing: border-box;padding: 4px 5px;max-height:200px;overflow-y: scroll;" class="radius">
                                <div ng-repeat="x in companylist | filter:{name: companyName, industry: type, districtId: district, townId: town, villageId: village} as companyListFilter" style="float:left;width:50%">
                                    <input type="checkbox" id="checkbox-{{x.userId}}" ng-checked="x.select" ng-click="updateSelection($event,x)">
                                    <label for="checkbox-{{x.userId}}">{{x.name}}</label>
                                </div>
                                <div ng-show="!(companyListFilter.length)">
				                    <div class="empty_list" style="text-align: center;margin: 10px 0;">
				                    	查询暂无数据...
				                    </div>
				                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <!-- <button type="button" class="btn btn-default" >关闭</button> -->
                        <button type="button" class="btn btn-primary" data-dismiss="modal">保存</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
$('#companyArea').click(function() {
  setTimeout(function() {
      $("#win").modal("show");
  }, 0);
});

$('#saveSubmit').click(function() {
  //userName
  //userPsw
  //userPswRe
  //tradeName
  //companyArea
  if($("#userName").val() == '') {
    layer.alert("请输入登录账号",'',function(index){
      layer.close(index);
      $("#userName").focus();
    })
    return false;
  }
  if($("#tradeName").val() == '') {
    layer.alert("请输入行业名称",'',function(index){
      layer.close(index);
      $("#tradeName").focus();
    })
    return false;
  }
  
  if($("#userPsw").val() == '') {
    layer.alert("请输入登录密码",'',function(index){
      layer.close(index);
      $("#userPsw").focus();
    })
    return false;
  }
  if($("#userPswRe").val() == '') {
    layer.alert("请确认登录密码",'',function(index){
      layer.close(index);
      $("#userPswRe").focus();
    })
    return false;
  }
  if($("#userPswRe").val() !=$("#userPsw").val()) {
    layer.alert("登录密码不一致",'',function(index){
      layer.close(index);
      $("#userPswRe").focus();
    })
    return false;
  }
  
  var companyArea = [];
  var companyNode = $('#companyArea').find('span');
  for(var i = 0;i<companyNode.length;i++){
    companyArea.push(companyNode.eq(i).attr('data-option'));
  }
  console.log(companyArea);
  
  $.post(getRootPath() + "/back/trade-save",{
    userId : $("#userId").val(),
    userName : $("#userName").val(),
    tradeName : $("#tradeName").val(),
    userPsw : $("#userPsw").val(),
    companyArea : companyArea + ''
  },function(result) {
    if(result.status == '1') {
      layer.alert(result.map.message);
      return false;
    }
    layer.alert("保存成功","",function(index){
      location.href = getRootPath() + '/back/trade-list';
    })
  })
});

var app = angular.module('app', []);
app.controller('appCtrl', function($scope, $filter, $compile, $http) {
  $scope.companylist = ${ companylist };
  $scope.tradeCompany = ${ tradeCompany };
  $scope.tradeType = ${ tradeType };
  
  $scope.townlist = [];
  $scope.villagelist = [];
  
  $scope.districtChange = function() {
	    if($scope.district == ""){
	      $scope.townlist = [];
	      $scope.villagelist = [];
	    }
	    $http({
	      method: 'GET',
	      url: '${ly }/common/getTownByArea?id=' + $scope.district
	  }).then(function successCallback(response) {
	          //$scope.townlist = JSON.stringify(response.data.map.list);
	          $scope.townlist = response.data.map.list;
	      }, function errorCallback(response) {
	  });
	}
	
	$scope.townChange = function() {
	  if($scope.town == ""){
      $scope.villagelist = [];
    }
	  $http({
	      method: 'GET',
	      url: '${ly }/common/getVillageByTown?id=' + $scope.town
	  }).then(function successCallback(response) {
	        $scope.villagelist = response.data.map.list;
	    }, function errorCallback(response) {
	  });
	}

  $scope.companySelect = function() {
      for (var m = 0; m < $scope.companylist.length; m++) {
          $scope.companylist[m].select = false;
          for (var n = 0; n < $scope.tradeCompany.length; n++) {
              if ($scope.companylist[m].userId == $scope.tradeCompany[n].uid) {
                  $scope.companylist[m].select = true;
              }
          }
      }

  };

  $scope.updateSelection = function($event, x) {
      var checkbox = $event.target;
      if (checkbox.checked) {
          var tradeCompany = {};
          tradeCompany.name = x.name;
          tradeCompany.uid = x.userId;
          $scope.tradeCompany.push(tradeCompany);
      } else {
          for (var n = 0; n < $scope.tradeCompany.length; n++) {
              if ($scope.tradeCompany[n].uid == x.userId) {
                  $scope.tradeCompany.splice(n, 1);
                  break;
              }
          }
      }
  };
  
})
</script>
</html>