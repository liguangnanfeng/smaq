$(function() {
  var globalRegionId = '';

  if ($("#globalRegionId").length > 0)
    globalRegionId = $("#globalRegionId").val();

  // 新增初始化
  if (globalRegionId == '') {
    childGlobal(1);
  } else { // 编辑初始化
    parentGlobal(globalRegionId);
  }

  // 省→市联动
  $("#province").change(function() {
    if ($("#city").length == 0) {
      return false;
    }
    $("#city").empty();
    $("#region").empty();
    $("#city").append("<option value=''>请选择</option>");
    $("#region").append("<option value=''>请选择</option>");
    if ($(this).val() != '') {
      childGlobal($(this).val());
    }
  });

  // 市→区
  $("#city").change(function() {
    if ($("#region").length == 0) {
      return false;
    }
    $("#region").empty();
    $("#region").append("<option value=''>请选择</option>");
    if ($(this).val() != '') {
      childGlobal($(this).val());
    }
  });
});
// 新增初始化
function childGlobal(id) {
  $.post(getRootPath() + '/common/childGlobal', {
    id : id
  }, function(result) {
    var list = result.map.list;
    var type = result.map.type;
    if (type == '0') {// 国→省
      $("#province").empty();
      $("#city").empty();
      $("#region").empty();
      $("#province").append("<option value=''>请选择</option>");
      $("#city").append("<option value=''>请选择</option>");
      $("#region").append("<option value=''>请选择</option>");

      $.each(list, function() {
        $("#province").append("<option value='" + this.id + "'>" + this.regionName + "</option>");
      });

      if ($("#globalRegionId1") && $("#globalRegionId1").val() != '') {
        $("#province").val($("#globalRegionId1").val()).change();
        $("#globalRegionId1").val("");
      }
    } else if (type == '1') { // 省-》市
      $.each(list, function() {
        $("#city").append("<option value='" + this.id + "' cityCode='" + this.cityCode + "'>" + this.regionName + "</option>");
      });

      if ($("#globalRegionId2") && $("#globalRegionId2").val() != '') {
        $("#city").val($("#globalRegionId2").val()).change();
        $("#globalRegionId2").val("");
      }
    } else { // 市→区
      if (list == null || list.length == 0) {
        $("#region").hide();
      } else {
        $("#region").show();
        $.each(list, function() {
          $("#region").append("<option value='" + this.id + "'>" + this.regionName + "</option>");
        });
      }
    }
  }, 'json');
}
// 编辑初始化
function parentGlobal(id) {
  $.post(getRootPath() + '/common/parentGlobal', {
    id : id
  }, function(result) {
    var type = result.map.type;

    if (type == '3') {// 区→市→省
      var regionId = result.map.regionId;
      var regionList = result.map.regionList;
      $("#region").empty();
      $("#region").append("<option value=''>请选择</option>");

      $.each(regionList, function() {
        if (this.id == regionId) {
          $("#region").append("<option value='" + this.id + "' selected='selected'>" + this.regionName + "</option>");
        } else {
          $("#region").append("<option value='" + this.id + "'>" + this.regionName + "</option>");
        }
      });
    }
    var cityId = result.map.cityId;
    var cityList = result.map.cityList;
    var provinceId = result.map.provinceId;
    var provinceList = result.map.provinceList;

    if ($("#region option").length <= 1) {
      $("#region").hide();
    }
    $("#province").empty();
    $("#city").empty();
    $("#province").append("<option value=''>请选择</option>");
    $("#city").append("<option value=''>请选择</option>");

    $.each(cityList, function() {
      if (this.id == cityId) {
        $("#city").append("<option value='" + this.id + "' selected='selected' cityCode='" + this.cityCode + "'>" + this.regionName + "</option>");
      } else {
        $("#city").append("<option value='" + this.id + "' cityCode='" + this.cityCode + "'>" + this.regionName + "</option>");
      }
    });

    $.each(provinceList, function() {
      if (this.id == provinceId) {
        $("#province").append("<option value='" + this.id + "' selected='selected'>" + this.regionName + "</option>");
      } else {
        $("#province").append("<option value='" + this.id + "'>" + this.regionName + "</option>");
      }
    });
  }, 'json');
}