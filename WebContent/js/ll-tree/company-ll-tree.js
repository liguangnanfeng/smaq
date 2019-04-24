$(function(){
  var list1 = new Array();
  var list2 = new Array();
  for(var i=0;i<levelL.length;i++) {
    var l = levelL[i].level1;
    var l2 = levelL[i].level2;
    var l3 = levelL[i].level3;
    var id = levelL[i].id;
    //处理1级树
    if(!list1.contains(l)) {
      list1.push(l);
      $(".list_danger").append(
        $('<div class="one_danger" data-t="'+l+'"><div class="one_dtitle"><i class="Hui-iconfont">&#xe604;</i>' +
            '<div class="check-box">' +
              '<input type="checkbox" name="check" data-level="1">' +
                '<label>'+l+'</label>' +
            '</div>' +
            '</div></div>')
      )
    }
    
    //处理二级树
    var par = $(".one_danger[data-t='"+l+"']");
    if(par.find(".list_odan").length == 0) {
      par.append($('<div class="list_odan"></div>'));
    }
    
    var t2 = l + "###" + l2;
    if(!list2.contains(t2)) {
      list2.push(t2);
      par.find(".list_odan").append(
          $('<div class="two_danger" data-t="'+t2+'" '+ (l3 == '' ? 'data-id="'+id+'"' : "") +'><div class="two_dtitle">' +
              (l3 == '' ? '' : '<i class="Hui-iconfont">&#xe604;</i>') + 
            '<div class="check-box">' +
              '<input type="checkbox" name="check" data-level="2"' + (l3 == '' ? 'data-id="'+id+'"' : "") +'>' +
                '<label>'+l2+'</label>' +
            '</div>' +
            '</div></div>')
      )
    }
    
    //处理三级目录
    if(l3 != '') {
      var par2 = $(".two_danger[data-t='"+t2+"']");
      if(par2.find(".list_twdan").length == 0) {
        par2.append($('<div class="list_twdan"></div>'));
      }
      var t3 = t2 + "###" + l3;
      par2.find(".list_twdan").append(
          $('<div class="three_danger" data-t="'+t3+'" data-id="'+id+'"><div class="three_dtitle">' +
            '<div class="check-box">' +
              '<input type="checkbox" name="check" data-level="3" data-id="'+id+'")>' +
                '<label>'+l3+'</label>' +
            '</div>' +
            '</div></div>')
      )
    }
  }
  
  //一级树点击事件
  $(".list_danger").on("click", ".one_dtitle > .Hui-iconfont", function(){
    var par = $(this).closest(".one_danger");
    if(par.hasClass("one_dangerxz")){//关闭
      par.removeClass("one_dangerxz");
      par.find(".Hui-iconfont").html("&#xe604;");
      par.find(".three_danger").removeClass("three_dangerxz");
      par.find(".two_danger").removeClass("two_dangerxz");
    } else {//开启
      par.addClass("one_dangerxz");
      par.find(".one_dtitle .Hui-iconfont").html("&#xe631;");
    }
  })
  
  //二级树点击事件
  $(".list_danger").on("click", ".two_dtitle > .Hui-iconfont", function(){
    var par = $(this).closest(".two_danger");
    if(par.hasClass("two_dangerxz")){//关闭
      par.removeClass("two_dangerxz");
      par.find(".Hui-iconfont").html("&#xe604;");
      par.find(".three_danger").removeClass("three_dangerxz");
    }else{//开启
      par.addClass("two_dangerxz");
      par.find(".two_dtitle .Hui-iconfont").html("&#xe631;");
    }
  })
  
  //checkbox 选择效果
  $(".list_danger").on("click", ":checkbox", function() {
    var level = $(this).attr("data-level");
    if(level == '1') {//1级
      var p = $(this).closest(".one_danger");
      if($(this).is(":checked")) {
        p.find(":checkbox").prop("checked", true);
      } else {//取消选择
        p.find(":checkbox").prop("checked", false);
      }
    } else if(level == '2') {//二级
      var p = $(this).closest(".one_danger");
      var tp = $(this).closest(".two_danger");
      if($(this).is(":checked")) {
        tp.find(":checkbox").prop("checked", true);
      } else {//取消选择
        tp.find(":checkbox").prop("checked", false);
      }
      if(p.find(":checkbox[data-level='2']:checked").length > 0) {
        p.find(":checkbox[data-level='1']").prop("checked", true);
      } else {
        p.find(":checkbox[data-level='1']").prop("checked", false);
      }
    } else {//三级
      var p = $(this).closest(".one_danger");
      var tp = $(this).closest(".two_danger");
      
      if(tp.find(":checkbox[data-level='3']:checked").length > 0) {
        tp.find(":checkbox[data-level='2']").prop("checked", true);
      } else {
        tp.find(":checkbox[data-level='2']").prop("checked", false);
      }
      if(p.find(":checkbox[data-level='2']:checked").length > 0) {
        p.find(":checkbox[data-level='1']").prop("checked", true);
      } else {
        p.find(":checkbox[data-level='1']").prop("checked", false);
      }
    }
  })
})