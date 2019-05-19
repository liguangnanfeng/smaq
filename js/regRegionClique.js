$(function() {
  var reg_region_id = '';

  if ($("#reg_region").length > 0)
    reg_region_id = $("#reg_region").val();

  // 新增初始化
  if (reg_region_id == '') {
    childGlobal_(1);
  } else { // 编辑初始化
    parentGlobal_(reg_region_id);
  }

  // 省→市联动
  $("#province1").change(function() {
    if ($("#city1").length == 0) {
      return false;
    }
    $("#city1").empty();
    $("#city1").append("<option value=''>请选择</option>");
    if ($(this).val() != '') {
      childGlobal_($(this).val());
    }
  });

  // 市→区
  $("#city1").change(function() {
    $("#authority").empty().append("<option value=''>请选择</option>");
    if($("#city1").val() == '') {
      return false;
    }
    $.post(getRootPath() + "/fore/regL", {
      regionId : $("#city1").val()
    },function(result) {
      var list = result.map.list;
      $.each(list, function() {
        $("#authority").append("<option value='" + this.name + "'>" + this.name + "</option>");
      })
      
      if(authority != '') {
        $("#authority").val(authority);
        authority = '';
      }
    })
  });
});

// 新增初始化
function childGlobal_(id) {
  $.post(getRootPath() + '/common/childGlobal', {
    id : id
  }, function(result) {
    var list = result.map.list;
    var type = result.map.type;
    if (type == '0') {// 国→省
      $("#province1").empty().append("<option value=''>请选择</option>");
      $("#city1").empty().append("<option value=''>请选择</option>");
      $("#authority").empty().append("<option value=''>请选择</option>");

      $.each(list, function() {
        $("#province1").append("<option value='" + this.id + "'>" + this.regionName + "</option>");
      });
    } else if (type == '1') { // 省-》市
      $.each(list, function() {
        $("#city1").append("<option value='" + this.id + "' cityCode='" + this.cityCode + "'>" + this.regionName + "</option>");
      });
    }
  }, 'json');
}

// 编辑初始化
function parentGlobal_(id) {
  $.post(getRootPath() + '/common/parentGlobal', {
    id : id
  }, function(result) {
    var cityId = result.map.cityId;
    var cityList = result.map.cityList;
    var provinceId = result.map.provinceId;
    var provinceList = result.map.provinceList;

    $("#province1").empty().append("<option value=''>请选择</option>");
    $("#city1").empty().append("<option value=''>请选择</option>");
    $("#authority").empty().append("<option value=''>请选择</option>");

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
    
    $("#city1").change();
  }, 'json');
}

function save_submit() {
  var name = $("#name").val();
  if(name.trim()==''){
    layer.alert("请填企业名称！")
    return false;
  }
  var dangers = '';
  $(":checkbox[name='dangers']:checked").each(function(){
    var v = $(this).val();
    dangers = dangers == '' ? v : dangers + "," + v;
  })
  var industry2 = '';
  if($("#industry2_2").val() != '') {
    industry2 = $("#industry2_").val() + " > " + $("#industry2_2").val();
  }
  $.post("/tradeclique/clique/basic-information-save",{
    userId : userId,
    regType : $("#regType").val(),
    regType2 : $("#regType").val()=="其他类型"?$("#regType2").val():"",
    regionId : $("#region").val(),
    address : $("#address").val(),
    legal : $("#legal").val(),
    legalContact : $("#legalContact").val(),
    capital : $("#capital").val(),
    establish : $("#establish").val(),
    business : $("#business").val(),
    scope : $("#scope").val(),
    license : $("#license").val(),
    regRegion : $("#city1").val(),
    authority : $("#authority").val(),
    economic : $("#economic").val(),
    fixed : $("#fixed").val(),
    staff : $("#staff").val(),
    income : $("#income").val(),
    industry : $("#industry").val(),
    danger : $(":radio[name='danger']:checked").val(),
    dangers : dangers,
    hazard : $(":radio[name='hazard']:checked").val(),
    dlevel : $("#dlevel").val(),
    charge : $("#charge").val(),
    chargeContact : $("#chargeContact").val(),
    safety : $("#safety").val(),
    safetyContact : $("#safetyContact").val(),
    safetyM : $("#safetyM").val(),
    safetyMContact : $("#safetyMContact").val(),
    duty : $("#duty").val(),
    postage : $("#postage").val(),
    email : $("#email").val(),
    jw : $("#jw").val(),
    scale : $("#scale").val(),
    industry2 : industry2
  },function(result){
    if(path == '/company/') {
      layer.alert("保存成功", null, function(i) {
        layer.close(i);
        window.parent.location.reload();
        var index=parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
      })
    } else {
      layer.alert("保存成功", null, function(i) {
        layer.close(i);
        window.parent.location.reload();
        var index=parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
      })
    }
  })
}