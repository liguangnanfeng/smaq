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

<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/AMap.PlaceSearchRender1120.css"/>
<link rel="stylesheet" type="text/css" href="http://webapi.amap.com/css/v1.4.1/style1509024629605.css">
    
<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.3&key=441462b46b736497a5f2a377aafb5b69"></script>
<style type="text/css">
body .amap-sug-result{z-index:9999} 
.input-text{position:relative}
.txtarea_sq{height:100px;width:350px;}
.inhide{display:none;}
.modal-dialog{margin:0 auto;}
</style>
<script type="text/javascript">
$(function(){
  $(".modal-dialog").width($('.page-container').width());
  $(".div_map").height($(window).height()-180);
})
</script>
</head>
<body>
<div class="page-container">
  <form class="form form-horizontal" id="form">
    <div class="Huialert Huialert-info" style="width:88%;margin-left:6%;margin-top:20px;"><i class="icon-remove"></i>一、基础信息</div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">企业名称：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="name" value="${TradeCompanyName }" style="width:350px" class="input-text required" maxlength="50" disabled="disabled">
        <a class="btn btn-primary radius" href="javascript:selectQccData()">获取基本信息</a>
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">企业类型：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <select class="sel_area" id="regType">
          <option value="">请选择</option>
          <option>内资企业</option>
          <option>港澳台投资企业</option>
          <option>外资企业</option>
          <option>其他特殊类型</option>
          <option>国有企业</option>
          <option>集体企业</option>
          <option>股份合作企业</option>
          <option>国有联营企业</option>
          <option>集体联营企业</option>
          <option>国有与集体联营企业</option>
          <option>其他联营企业</option>
          <option>有限责任公司</option>
          <option>股份有限公司</option>
          <option>私营独资企业</option>
          <option>私营合资企业</option>
          <option>私营有限责任公司</option>
          <option>私营股份有限公司</option>
          <option>其他企业</option>
          <option>合资经营企业（港或澳台资）</option>
          <option>合作经营企业（港或澳台资）</option>
          <option>港澳台商独资经营企业</option>
          <option>港澳台商投资股份有限公司</option>
          <option>其他港澳台商投资企业</option>
          <option>中外合资经营企业</option>
          <option>中外合作经营企业</option>
          <option>外资企业</option>
          <option>外商投资股份有限公司</option>
          <option>其他外商投资企业</option>
          <option>个体工商户</option>
          <option>分公司</option>
          <option>办事机构</option>
          <option>百货商店</option>
          <option>其他类型</option>
        </select>
        <input type="text" id="regType2" value="${c.regType2 }" style="width:350px" class="input-text required inhide" >
        <script type="text/javascript">
          var regType = '${c.regType}';$("#regType").val(regType);
            if(regType=="其他类型"){
              $(".inhide").show();
            }
            $(function(){
              $("#regType").change(function(){
                if($("#regType").val()=="其他类型"){
                  $(".inhide").show();
                }else{
                  $(".inhide").hide();
                }
              });
            });
        </script>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">企业地址：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <select id="province" class="sel_area"></select>
        <select id="city" class="sel_area"></select>
        <select id="region" class="sel_area"></select>
        <input type="hidden" id="globalRegionId" value="${empty c.regionId ? 1874 : c.regionId}">
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">详细地址：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="hidden" id="jw" value="<c:if test="${!empty u.longitude}">${u.longitude },${u.latitude }</c:if>">
        <textarea id="address" class="textarea txtarea_sq" readonly="readonly" onclick="openmap()">${c.address }</textarea>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">法定代表人：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="legal" value="${c.legal }" style="width:350px" class="input-text required" >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">法定代表人联系手机：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="legalContact" value="${c.legalContact }" style="width:350px" class="input-text required" >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">注册资本：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="capital" value="${c.capital }" style="width:350px" class="input-text required" >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">成立时间：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="establish" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${c.establish }" class="input-text " style="width:350px;">
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">营业期限：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="business" value="${c.business }" style="width:350px" class="input-text required" >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">经营范围：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <textarea id="scope" class="textarea txtarea_sq" maxlength="500">${c.scope }</textarea>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">统一社会信用代码：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="license" value="${c.license }" style="width:350px" class="input-text required" >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">登记机关：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <select class="sel_area" id="province1"></select>
        <select class="sel_area" id="city1"></select>
        <select class="sel_area" id="authority"></select>
        <input type="hidden" id="reg_region" value="${empty c.regRegion ? 222 : c.regRegion}">
      </div> 
    </div>
    <!-- <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">经济类型：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <select class="sel_area" id="economic">
          <option value="">请选择</option>
          <option>国有经济</option>
          <option>集体经济</option>
          <option>私营经济</option>
          <option>有限责任公司</option>
          <option>联营经济</option>
          <option>股份合作</option>
          <option>外商投资</option>
          <option>港澳台投资</option>
          <option>其他经济</option>
        </select>
        <script type="text/javascript">var economic = '${c.economic}';$("#economic").val(economic);</script>
      </div> 
    </div> -->
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">固定资产：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="fixed" value="${c.fixed }" style="width:350px" class="input-text required" >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">所属行业：</label>
      <div class="formControls col-xs-8 col-sm-9">
          <select class="sel_area" id="industry2_">
            <option value="">请选择</option>
          </select>
          <select class="sel_area" id="industry2_2">
            <option value="">请选择</option>
          </select>
      </div> 
    </div>
    <script type="text/javascript" src="${ly }/js/fxgk/industry.js?v=180920"></script>
    <script type="text/javascript">
    var industry2 = '${c.industry2}';
    $(function() {
      $("#industry2_").change(function() {
        $("#industry2_2").empty().append('<option value="">请选择</option>');
        var v = $(this).val();
        if(v != '') {
          $.each(industry2List, function(i, item) {
            if(item.name == v) {
              $.each(item.list, function(j, jtem) {
                $("#industry2_2").append("<option>" + jtem + "</option>");
              })
            }
          })
          
          if(industry2 != '') {
            $("#industry2_2").val(industry2.split(" > ")[1]);
            industry2 = '';
          }
        } else {
          $("#industry").removeAttr("disabled").css("background-color","white");
        }
      })
      
      $("#industry2_2").change(function() {
        var v = $(this).val();
        $("#industry").val('').removeAttr("disabled").css("background-color","white");
        if(v == '') {
        } else {
          $.each(industry2_to_industry,function(i, item) {
            if(item.industry2 == v) {
              $("#industry").val(item.industry).attr("disabled", "disabled").css("background-color","#EEEEEE");
              return false;
            }
          })
        }
      })
      
      $.each(industry2List, function(i, item) {
        $("#industry2_").append("<option>" + item.name + "</option>");
      })
      if(industry2 != '') {
        $("#industry2_").val(industry2.split(" > ")[0]).change();
      }
    })
    </script>
    <div class="Huialert Huialert-info" style="width:88%;margin-left:6%;margin-top:20px;"><i class="icon-remove"></i>二、管理信息</div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">员工人数：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="staff" value="${c.staff }" style="width:350px" class="input-text required ll-numberbox " >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">上年销售收入：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="income" value="${c.income }" style="width:350px" class="input-text required" >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">监管行业：</label>
      <div class="formControls col-xs-8 col-sm-9">
          <select class="sel_area" id="industry">
            <option value="">请选择</option>
           <c:forEach items="${list1 }" var="be">
            <option>${be.name }</option>  
           </c:forEach> 
           </select>
           <script type="text/javascript">$("#industry").val('${c.industry}');</script>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">规模类型：</label>
      <div class="formControls col-xs-8 col-sm-9">
          <select class="sel_area" id="scale">
            <option value="">请选择</option>
            <option value="规上">规上</option>
            <option value="中小">中小</option>
            <option value="小微">小微</option>
          </select>
          <script type="text/javascript">$("#scale").val('${c.scale}');</script>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">是否高危作业：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <div class="one_yxuan">
          <input class="check_gao" type="radio" name="danger" value="1" <c:if test="${c.danger == 1}">checked</c:if>/>
          <font>是</font>
        </div>
        <div class="one_yxuan">
          <input class="check_gao" type="radio" name="danger" value="0" <c:if test="${c.danger == 0}">checked</c:if>/>
          <font>否</font>
        </div>
      </div> 
    </div>
    <div class="row cl" style="display: none;" id="dangers_">
      <label class="form-label col-xs-4 col-sm-2">高危行业：</label>
      <div class="formControls col-xs-8 col-sm-9">
      <!-- 循环 -->
        <c:forEach items="${ind3L }" var="be">
        <div class="one_yxuan">
          <input class="check_gao" type="checkbox" name="dangers" value="${be.name }"/>
          <font>${be.name }</font>
        </div>
        </c:forEach>
        <!-- 循环结束 -->
      </div>
      <script type="text/javascript">
      var dangers = '${c.dangers}',  danger = '${c.danger}';
      if(danger == '1') {
        $("#dangers_").show();
      }
      if('' != dangers) {
        $.each(dangers.split(","),function(i, item) {
          $(":checkbox[name='dangers'][value='"+item+"']").attr("checked", "checked");
        })
      }
      $(":radio[name='danger']").click(function(){
        if($(this).val() == '1') {
          $("#dangers_").show();
        } else {
          $("#dangers_").hide();
        }
      })
      </script> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">是否构成重大危险源：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <div class="one_yxuan">
          <input class="check_gao" type="radio" name="hazard" value="1" <c:if test="${c.hazard == 1}">checked</c:if>/>
          <font>是</font>
        </div>
        <div class="one_yxuan">
          <input class="check_gao" type="radio" name="hazard" value="0" <c:if test="${c.hazard == 0}">checked</c:if>/>
          <font>否</font>
        </div>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">风险等级：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <select class="sel_area" id="dlevel">
          <option value="">请选择</option>
          <c:forEach items="${list2 }" var="be">
              <option>${be.name }</option>  
             </c:forEach> 
          </select>
           <script type="text/javascript">$("#dlevel").val('${c.dlevel}');</script>
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">主要负责人：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="charge" value="${c.charge }" style="width:350px" class="input-text required " >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">主要负责人联系手机：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="chargeContact" value="${c.chargeContact }" style="width:350px" class="input-text required ll-numberbox " >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">专职安全员：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="safety" value="${c.safety }" style="width:350px" class="input-text required " >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">专职安全员联系手机：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="safetyContact" value="${c.safetyContact }" style="width:350px" class="input-text required  ll-numberbox " >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">安全管理部门负责人：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="safetyM" value="${c.safetyM }" style="width:350px" class="input-text required " >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2"><!-- 安全管理 -->部门负责人联系手机：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="safetyMContact" value="${c.safetyMContact }" style="width:350px" class="input-text required  ll-numberbox " >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">企业值班电话：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="duty" value="${c.duty }" style="width:350px" class="input-text required  ll-numberbox " >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">企业邮政编码：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="postage" value="${c.postage }" style="width:350px" class="input-text required  ll-numberbox " >
      </div> 
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-2">企业邮箱：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <input type="text" id="email" value="${c.email }" style="width:350px" class="input-text required " >
      </div> 
    </div>
    <div class="row cl">
      <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
        <button onClick="save_submit()" class="btn btn-primary radius" type="button" style="padding: 0 70px;">
          <i class="Hui-iconfont">&#xe632;</i>保存
        </button>
      </div>
    </div>
  </form>
  <!-- 地图弹窗 -->
  <div id="win-mmap" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header">
                <h3 class="modal-title">选择地址</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body" style="display: inline-block;background:#fff;width:100%;box-sizing: border-box;">
              <div class="allsq" style="width:450px;margin-left:15px;">
                <span style="float:left;line-height:29px;width: 77px;text-align:right;">详细地址：</span>
                <input type="text" class="txt_sq" id="search" name="keyword" value="${c.address }" style="width:340px;"/>
              </div>
              <div class="allsq" style="width:340px;">
                <span style="float:left;line-height:29px;width: 65px;text-align:right;">经纬度：</span>
                <input type="text" class="txt_sq" id="addressMap1" placeholder="请在地图上标记您的详细地址" value="<c:if test="${!empty u.longitude}">${u.longitude },${u.latitude }</c:if>" style="width:180px;" readonly="readonly"/>
                <input type="button" class="btn_dzx" onclick="jw_()" value="确定"/>
              </div>
              <div class="div_map" id="map_div1"  style="width: 96%;margin-left:2%;"></div>
            </div>
            
        </div>
    </div>
</div>
</div>
</body>
<script type="text/javascript" src="${ly }/js/util/globalRegion.js"></script>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.1&key=441462b46b736497a5f2a377aafb5b69&plugin=AMap.PlaceSearch"></script>
<script type="text/javascript" src="http://cache.amap.com/lbs/static/PlaceSearchRender.js"></script>
<script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
<script type="text/javascript" src="${ly }/js/map.js"></script>
<script type="text/javascript">
function selectQccData() {
  if ($("#name").val()=='') {
    layer.alert("企业名称不能为空", function(i) {
      layer.close(i);
      $("#name").focus();
    })
    return false;
  }
  var companyName = $("#name").val();
  $.post(getRootPath() + "/company/getBasicInformation-qcc", {
    companyName: companyName
  }, function (result) {
      if (result.status == '0') {
          var item = JSON.parse(result.map.item);
          var data = item.Result;
          var status = item.Status;
          if(status == 200){
	  		//console.log(item);
            //console.log(data.Result);
  	        //alert("开始赋值");
  	        $("#address").val(data.Address);
  	        $("#legal").val(data.OperName);
  	        $("#capital").val(data.RegistCapi);
  	        $("#establish").val(data.StartDate.substring(0, 10));
  	        $("#scope").val(data.Scope);
  	        $("#license").val(data.CreditCode);
  	        
  	      	//console.log(data.EconKind);
  	        //console.log(data.BelongOrg);
  	        if(data.EconKind){
  	          	$("#regType option").each(function(){  
            		if($(this).text() == data.EconKind){
               		$(this).prop("selected", true);
             		}  
     	      	}); 
  	        }
  	        if(data.EconKind){
  	          	$("#authority option").each(function(){  
  	         		if($(this).text() == data.BelongOrg){
  	            		$(this).prop("selected", true);
  	          		}  
  	  	      	}); 
  	        }
  	        
  	        if(data.ContactInfo.Email){
	          	$("#email").val(data.ContactInfo.Email);
	        }
	        
	        if(data.ContactInfo.PhoneNumber){
	          	$("#duty").val(data.ContactInfo.PhoneNumber);
	        }
	        
	        $("#charge").val(data.OperName);
	        
          } else {
            layer.alert(item.Message);
          }
          
	  } else {
	      //console.log(result.map.message);
	      layer.alert("获取数据失败！");
	  }
  });
}

function jw_() {
  $("#jw").val($("#addressMap1").val());
  $("#address").val($("#search").val());
  closemap();
}
function closemap(){
  $("#win-mmap").modal("hide");
}
var userId = '${c.userId}';
var authority = '${c.authority}';
var path = '/company/';
var cname = '${c.name}';
var address = '${c.address}';
var placeSearchOptions = { //构造地点查询类
    pageSize: 1,
    pageIndex: 1
};
var placeSearch = new AMap.PlaceSearch(placeSearchOptions);

function openmap(){
  $("#win-mmap").modal("show");
  if($("#addressMap1").val() == '') {
    layer.msg('详细地址与经纬度自动检索中。。。');
    
    setTimeout(function() {
      //关键字查询
      placeSearch.search(address == '' ? cname : address, function(status, result) {
        layer.closeAll();
        if (status === 'complete' && result.info === 'OK') {
          
          var tips = result.poiList.pois[0];
          var lnglatXY = tips.location.lng + "," + tips.location.lat;
          
          $("#search").val(tips.address + "(" + tips.name + ")");
          $("#addressMap1").val(lnglatXY);
          
          if(null == marker1) {
            marker1 = new AMap.Marker({
              icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
              position: lnglatXY.split(","),
            });
            marker1.setMap(map);
          } else {
            marker1.setPosition(lnglatXY.split(","));
          }
          
          map.setCenter(new AMap.LngLat(lnglatXY.split(',')[0], lnglatXY.split(',')[1]));
        } else {
          layer.alert("自动检索失败，请输入详细地址手动查询");
        }
      });
      
    }, 2000);
  }
}
</script>
<script type="text/javascript" src="${ly }/js/regRegionClique.js?v=0.2"></script>
</html>