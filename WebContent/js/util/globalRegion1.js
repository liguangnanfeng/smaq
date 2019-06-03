$(function() {
  var globalRegionId1 = '';

  if ($("#globalRegionId1").length > 0)
    globalRegionId1 = $("#globalRegionId1").val();

  //新增初始化
  if (globalRegionId1 == '') {
    childGlobal1(1);
  } else { //编辑初始化
    parentGlobal1(globalRegionId1);
  }

  //省-》市联动
  $("#province1").change(function() {
    if ($("#city1").length == 0) {
      return false;
    }
    $("#city1").empty();
    $("#region1").empty();
    $("#city1").append("<option value=''>请选择</option>");
    $("#region1").append("<option value=''>请选择</option>");
    if ($(this).val() != '') {
      childGlobal1($(this).val());
    }
  });

  //市 -》区
  $("#city1").change(function() {
    if ($("#region1").length == 0) {
      return false;
    }
    $("#region1").empty();
    $("#region1").append("<option value=''>请选择</option>");
    if ($(this).val() != '') {
      childGlobal1($(this).val());
    }
  });
});

function childGlobal1(id) {
  $.post(getRootPath() + '/common/childGlobal', {
    id : id
  }, function(result) {
    var list = result.map.list;
    var type = result.map.type;
    if (type == '0') {//国->省
      $("#province1").empty();
      $("#city1").empty();
      $("#region1").empty();
      $("#province1").append("<option value=''>请选择</option>");
      $("#city1").append("<option value=''>请选择</option>");
      $("#region1").append("<option value=''>请选择</option>");

      $.each(list, function() {
        $("#province1").append("<option value='" + this.id + "'>" + this.regionName + "</option>");
      });
    } else if (type == '1') { //省-》市
      $.each(list, function() {
        $("#city1").append("<option value='" + this.id + "' cityCode='" + this.cityCode + "'>" + this.regionName + "</option>");
      });
    } else { //市 -》区
      if (list == null || list.length == 0) {
        $("#region1").hide();
      } else {
        $("#region1").show();
        $.each(list, function() {
          $("#region1").append("<option value='" + this.id + "'>" + this.regionName + "</option>");
        });
      }
    }
  }, 'json');
}

function parentGlobal1(id) {
  $.post(getRootPath() + '/common/parentGlobal', {
    id : id
  }, function(result) {
    var type = result.map.type;

    if (type == '3') {// 区-》市-》省
      var regionId = result.map.regionId;
      var regionList = result.map.regionList;
      $("#region1").empty();
      $("#region1").append("<option value=''>请选择</option>");

      $.each(regionList, function() {
        if (this.id == regionId) {
          $("#region1").append("<option value='" + this.id + "' selected='selected'>" + this.regionName + "</option>");
        } else {
          $("#region1").append("<option value='" + this.id + "'>" + this.regionName + "</option>");
        }
      });
    }
    var cityId = result.map.cityId;
    var cityList = result.map.cityList;
    var provinceId = result.map.provinceId;
    var provinceList = result.map.provinceList;

    if ($("#region1 option").length <= 1) {
      $("#region1").hide();
    }
    $("#province1").empty();
    $("#city1").empty();
    $("#province1").append("<option value=''>请选择</option>");
    $("#city1").append("<option value=''>请选择</option>");

    $.each(cityList, function() {
      if (this.id == cityId) {
        $("#city1").append("<option value='" + this.id + "' selected='selected' cityCode='" + this.cityCode + "'>" + this.regionName + "</option>");
      } else {
        $("#city1").append("<option value='" + this.id + "' cityCode='" + this.cityCode + "'>" + this.regionName + "</option>");
      }
    });

    $.each(provinceList, function() {
      if (this.id == provinceId) {
        $("#province1").append("<option value='" + this.id + "' selected='selected'>" + this.regionName + "</option>");
      } else {
        $("#province1").append("<option value='" + this.id + "'>" + this.regionName + "</option>");
      }
    });
  }, 'json');
}