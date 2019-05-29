function addDan() {
  var html = $("#div_model").html();
  $("#div_dangerm").append(html);
  init_index();
}

if ($("#div_dangerm .MsoNormal").length == 0) {
  addDan();
  addDan();
  addDan();
}

function init_index() {
  $("#div_dangerm .num_dan").each(function(i, e) {
    $(this).text(i + 1);
  });
}

function removeDan(e) {
  $(e).parent().remove();
  init_index();
}

function pr_(e) {
  layer.alert("打印之前，请先保存！");
}

$(function() {
  //没有设置整改意见的，先设置整改意见
  if (rectificationId == '') {
    layer.alert("复查之前,请先整改！", {
      icon : 0,
      closeBtn : 0
    }, function(i) {
      layer.close(i);
      window.location.href = "/village/check-document?checkId="+checkId+"&flag=1"
    });
    return false;
  }

  //没有设置复查意见的弹窗设置复查意见
  if (count == 0) {
    $("#modal-demo").modal("show");
  }

})

function article_save_submit() {
  var ind = layer.load();
  var obj = new Object();
  var recheck = new Object();
  recheck.checkId = checkId;
  recheck.status = $(":checkbox[name='isxian']:checked").length > 0 ? 1 : 2;
  recheck.nextTime = $("#nextTime").val();
  recheck.checker = $("#checker").val();
  recheck.checkCompany = $("#checkCompany").val();
  recheck.dapartContact = $("#dapartContact").val();
  obj.recheck = recheck;

  var list = new Array();
  $(":checkbox[name='isxian']").each(function() {
    var itemid = $(this).attr("data-i");
    var file = $(this).attr("data-file");
    var status = $(this).prop("checked") ? 2 : 3;
    var it = new Object();
    it.checkItemId = itemid;
    it.status = status;
    it.file = file;
    it.deadline = $(this).attr("data-dead");
    it.memo = $(this).find("input[name='memo']").val();
    list.push(it);
  })
  obj.list = list;
  $.ajax({
    type : "POST",
    url : getRootPath() + '/company/recheck-save',
    data : JSON.stringify(obj),
    async : false,
    dataType : "json",
    contentType : 'application/json',
    success : function(result) {
      layer.close(ind);
      if (result.status == '0') {
        top.ref_ifm();
        location.reload();
      } else {
        layer.alert(result.map.message);
      }
    },
    complete : function(XMLHttpRequest, textStatus) {
      layer.close(ind);
    },
    error : function(XMLHttpRequest, textStatus, errorThrown) {
      alert("保存失败")
    }
  });
}