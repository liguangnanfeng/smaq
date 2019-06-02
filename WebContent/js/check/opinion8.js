var mod = $('<p class="MsoNormal" style="text-indent:21.0000pt;" >' +
  '<span class="num_dan" style="">1</span><span style="">．</span><u><span style="text-decoration:underline;text-indent: 0;" class="yh_span" data-status="1"></span></u>' +
  '<i class="Hui-iconfont mr-10 dustbin" style="cursor:pointer;font-size:20px" onclick="removeDan(this)">&#xe609;</i>' +
'</p>');

var this_dan = '';
function pr_(e) {
  layer.alert("打印之前，请先保存！");
}

//初始化检查结果下标
function init_index() {
  $("#div_dangerm .num_dan").each(function(i,e){
    $(this).text(i+1);
  });
}

//移除一项检查结果
function removeDan(e) {
  $(e).parent().remove();
  init_index();
}

function addDan() {
  $("#modal-demo").modal("show");
}

$(function() {
  $('#modal-demo').on('shown.bs.modal', function () {
    $("#memo_text").focus();
  })
  
  //非工具入口
  if(checkId == '') {
    addDan();
  }
  
  //工具入口，没有隐患的
  if($("#div_dangerm .MsoNormal").length == 0 && checkId != '') {
    var m = mod.clone();
    m.find(".yh_span").text("本次检查内容全部合格。");
    m.find("i").hide();
    $("#div_dangerm").append(m);
  }
  
  //添加检查结果
  $("#check_item_btn").click(function() {
    if($("#memo_text").val() == '') {
      layer.alert("检查结果不能为空", function(i) {
        layer.close(i);
        $("#memo_text").focus();
      })
      return false;
    }
    
    if(this_dan == '') {//添加
      var m = mod.clone();
      var a = m.find(".yh_span")
      a.text($("#memo_text").val()).attr("data-status", $(":radio[name='radio-status']:checked").val()).attr("data-file", $("#pic").attr("url"));
      $("#div_dangerm").append(m);
      init_index();
      layer.confirm("添加成功，是否继续添加检查结果？",function(i) {
        $(":radio[name='radio-status'][value='2']").click();
        layer.close(i);
        $("#memo_text").val('').focus();
        $("#pic").attr("url",'').focus();
        $("#pic").attr("src","/images/zwtp.jpg").focus();
      },function(i) {
        layer.close(i);
        $("#modal-demo").modal("hide");
      })
    } else {//编辑
      $("#modal-demo").modal("hide");
      this_dan.text($("#memo_text").val()).attr("data-status", $(":radio[name='radio-status']:checked").val()).attr("data-file", $("#pic").attr("url"));
      this_dan = '';
    }
  })
  
  //编辑检查结果
  $("#div_dangerm").on("click", ".yh_span", function() {
    this_dan = $(this);
    $("#memo_text").val($(this).text());
    $(":radio[name='radio-status'][value='"+this_dan.attr("data-status")+"']").click();
    $("#pic").attr($(this).attr("data-file"));
    $("#modal-demo").modal("show");
  })
})

//保存
function save_() {
  var list = new Array();
  var c3 = 0;//不合格数目
  $("#div_dangerm .MsoNormal").each(function() {
    var x = $(this).find(".yh_span").text().trim();
    var s = $(this).find(".yh_span").attr("data-status");
    var y = $(this).find(".yh_span").attr("data-file");
    if(x != '') {
      var o = {memo : x, status : s, files : y, levels : '现场管理'};
      if(s == 2) c3++;
      list.push(o);
    }
  })
  if(list.length == 0) {
    layer.alert("请添加检查结果", function(i) {
      layer.close(i);
      addDan();
    });
    return false;
  }
  var itms = "";
  if(checkId == '') {
    itms = JSON.stringify(list);
  }
  $(".dustbin").hide();
  /*alert($("#sjcompany").text());
  return*/
  $.post(getRootPath() + "/village/check-document-save8", {
    checkId : checkId,
    userId : userId,
    flag : flag,
    flag2 : flag2,
    copy: $("#container").html().trim(),
    itms : itms,
    checkCompany : $("#sjcompany").text()
  }, function(reuslt) {
    if(flag2 == 3 || c3 == 0) {//委托检查或者没有隐患
      top.show_tab("文书详情", "/village/check-document?flag=8&checkId=" + reuslt.map.checkId);
    } else {
      layer.confirm('保存成功，是否继续添加责令限期整改意见书', {
        btn: ['确认', '取消']
      }, function(index, layero){
        window.location.href = "/village/check-document?flag=1&checkId=" + reuslt.map.checkId;
      }, function(index){
        top.show_tab("文书详情", "/village/check-document?flag=8&checkId=" + reuslt.map.checkId);
      });
    }
    
  })
}