function pr_(e) {
  layer.alert("打印之前，请先保存！");
}
$(function() {
  if(rectificationId == '') {
    $("#modal-demo").modal("show");
  }
  
  $("div[data-name='item-memo").each(function(i,item) {
    var lid = $(this).attr("data-lid");
    var memo = $(this).text();
    if(lid != '') {
      $.each(serious_i, function(j, it) {
        if(it.levelid == lid && memo.indexOf(it.keywords) > -1) {
          cL.eq(i).click();
          if(ids == '') {
            ids = (i + 1);
          } else {
            ids = ids + "," + (i + 1);
          }
          return false;
        }
      })
    }
  })

  var cL = $(":checkbox[name='isyan']");
  $(":checkbox").click(function(){
    var name = $(this).attr("name");
    if(name != 'isyan') {
      var i = $(this).attr("data-i");
      if($(this).is(":checked")) {//选中
        $(":checkbox[data-i='"+i+"'][name='"+(name=='isxian'?'isli':'isxian')+"']").removeAttr("checked");
      } else {
        $(":checkbox[data-i='"+i+"'][name='"+(name=='isxian'?'isli':'isxian')+"']").prop("checked", true);
      }
    }
    init_all_box();
  })
  
  $(":checkbox[name='yz_qx']").click(function(){
    if(!$(this).prop("checked")) {
      $(":checkbox[name='isyan']").prop("checked", true);
    } else {
      $(":checkbox[name='isyan']").prop("checked", false);
    }
    init_all_box();
  })
})

function init_all_box() {
  if($(":checkbox[name='isli']").length == $(":checkbox[name='isli']:checked").length) {
    $(":checkbox[name='lj_qx']").prop("checked", true);
  } else {
    $(":checkbox[name='lj_qx']").prop("checked", false);
  }
  if($(":checkbox[name='isxian']").length == $(":checkbox[name='isxian']:checked").length) {
    $(":checkbox[name='xq_qx']").prop("checked", true);
  } else {
    $(":checkbox[name='xq_qx']").prop("checked", false);
  }
  if($(":checkbox[name='isyan']").length == $(":checkbox[name='isyan']:checked").length) {
    $(":checkbox[name='yz_qx']").prop("checked", true);
  } else {
    $(":checkbox[name='yz_qx']").prop("checked", false);
  }
}

function savex_() {
  if($(":checkbox[name='isxian']:checked").length > 0 && $("#deadline").val() == '') {
    layer.alert("请选择限期整改期限");
    return false;
  }
  if($("#planTime").val() == '') {
    layer.alert("请选择整改复查时间");
    return false;
  }
  var item1 = '', item2 = '', item3 = '';
  $(":checkbox[name='isxian']:checked").each(function(){
    var it = $(this).attr("data-i");
    item2 = item2 == '' ? it : item2 + "," + it;
  })
  $(":checkbox[name='isli']:checked").each(function(){
    var it = $(this).attr("data-i");
    item1 = item1 == '' ? it : item1 + "," + it;
  })
  $(":checkbox[name='isyan']:checked").each(function(){
    var it = $(this).attr("data-i");
    item3 = item3 == '' ? it : item3 + "," + it;
  })
  var i = layer.load();
  $.post(getRootPath() + "/company/rectification-save", {
    checkId : checkId,
    item1 : item1,
    item2 : item2,
    item3 : item3,
    deadline : $("#deadline").val(),
    planTime : $("#planTime").val()
  },function(result) {
    layer.close(i);
    if(result.status == '1') {
      layer.alert(result.map.message);
    } else {
      top.ref_ifm();
      location.reload();
    }
  })
}