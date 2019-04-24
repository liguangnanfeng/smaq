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
        $('<div class="one_danger" data-t="'+l+'"><div class="one_dtitle"><i class="Hui-iconfont">&#xe604;</i><p>'+l+'</p></div></div>')
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
          $('<div class="two_danger" data-t="'+t2+'" '+ (l3 == '' ? 'data-id="'+id+'"' : "") +'><div class="two_dtitle"><i class="Hui-iconfont">&#xe604;</i><p class="p_suo">'+l2+'</p></div></div>')
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
          $('<div class="three_danger" data-t="'+t3+'" data-id="'+id+'"><div class="three_dtitle"><i class="Hui-iconfont">&#xe604;</i><p>'+l3+'</p></div></div>')
      )
    }
  }
  
  //检查项目展示
  $.each(itemL, function(i, item) {
    var p = $("div[data-id='"+item.levelId+"']");
    var c = item.content;
    if(item.reference != '') {
      c = c + "（" + item.reference + " ）";
    }
    if(p.hasClass("three_danger") != '') {//用四级样式
      if(p.find(".list_thdan").length == 0) {
        p.append($('<div class="list_thdan"></div>'));
      }
      p.find(".list_thdan").append(
        $('<div class="four_danger" data-end="1" data-itemid="'+item.id+'"><div class="four_dtitle"><bb>&nbsp;</bb><p>'+c+'</p></div></div>')    
      )
    } else {
      if(p.find(".list_twdan").length == 0) {
        p.append($('<div class="list_twdan"></div>'));
      }
      p.find(".list_twdan").append(
        $('<div class="three_danger" data-end="1" data-itemid="'+item.id+'"><div class="three_dtitle"><bb>&nbsp;</bb><p>'+c+'</p></div></div>')    
      )
    }
  })
  
  //一级树点击事件
  $(".list_danger").on("click", ".one_dtitle", function(){
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
  $(".list_danger").on("click", ".two_dtitle", function(){
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
  
  //三级树点击事件
  $(".list_danger").on("click", ".three_dtitle", function(){
    var par = $(this).closest(".three_danger");
    if(par.hasClass("three_dangerxz")){//关闭
      par.removeClass("three_dangerxz");
      par.find(".Hui-iconfont").html("&#xe604;");
    }else{//开启
      par.addClass("three_dangerxz");
      par.find(".Hui-iconfont").html("&#xe631;");
    }
  })
  
})