Array.prototype.contains = function ( needle ) {
  for (i in this) {
    if (this[i] == needle) return true;
  }
  return false;
}
String.prototype.endWith = function(str) {
  if (str == null || str == "" || this.length == 0 || str.length > this.length)
    return false;
  if (this.substring(this.length - str.length) == str)
    return true;
  else
    return false;
  return true;
}
String.prototype.startWith = function(str) {
  if (str == null || str == "" || this.length == 0 || str.length > this.length)
    return false;
  if (this.substr(0, str.length) == str)
    return true;
  else
    return false;
  return true;
}
String.prototype.trim = function() {
  return this.replace(/(^\s*)|(\s*$)/g, '');
};

function getRootPath() {
  return "";
  var thisTHost = top.location.hostname;
  if (thisTHost.indexOf(".com") != -1 || thisTHost.indexOf(".cn") != -1) {
    return "http://" + thisTHost;
  } else {
    // 获取当前网址
    var curWwwPath = window.document.location.href;
    // 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    // 获取主机地址，如： http://localhost:8083
    var localhostPaht = curWwwPath.substring(0, pos);
    // 获取带"/"的项目名，如：/uimcardprj
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPaht + projectName);
  }
}

$.fn.serializeObject = function() {
  var o = {};
  var a = this.serializeArray();
  $.each(a, function() {
    if (o[this.name]) {
      if (!o[this.name].push) {
        o[this.name] = [o[this.name]];
      }
      o[this.name].push(this.value || '');
    } else {
      o[this.name] = this.value || '';
    }
  });
  return o;
}

/**
 * 弹窗
 * 
 * @param title
 *          标题
 * @param url
 *          加载路径
 */
function show_dialog(title, url) {
  var index = layer.open({
    type : 2,
    title : title,
    content : url,
  });
  layer.full(index);
}

function show_fulldialog(title, url) {
  var index = layer.open({
    type : 2,
    title : title,
    content : url,
    area:['100%','100%']
  });
  layer.full(index);
}

/**
 * 导航栏新增页面
 * 
 * @param title
 * @param href
 */
function show_tab(title, href) {
  if(self != top) {
    top.show_tab(title, href);
  } else {
    $("#min_title_list li i").click();
    var bStop = false,
      bStopIndex = 0,
      topWindow = $(window.parent.document),
      show_navLi = topWindow.find("#min_title_list li"),
      iframe_box = topWindow.find("#iframe_box");
    if(!href||href==""){
      alert("data-href不存在，v2.5版本之前用_href属性，升级后请改为data-href属性");
      return false;
    }if(!title){
      alert("v2.5版本之后使用data-title属性");
      return false;
    }
    if(title==""){
      alert("data-title属性不能为空");
      return false;
    }
    show_navLi.each(function() {
      if($(this).find('span').attr("data-href")==href){
        bStop=true;
        bStopIndex=show_navLi.index($(this));
        return false;
      }
    });
    if(!bStop){
      creatIframe(href,title);
      min_titleList();
    }else{
      show_navLi.removeClass("active").eq(bStopIndex).addClass("active");     
      iframe_box.find(".show_iframe").hide().eq(bStopIndex).show().find("iframe").attr("src",href);
    } 
    
    show_navLi.each(function(){
      if($(this).find("span").text() == title) {
        $(this).click();
      } else {
        if($(this).find("i")){
          $(this).find("i").click();
        }
      }
    });
  }
}

function ref_ifm() {
 $("iframe").eq(0).attr('src', $("iframe").eq(0).attr("src"));
}

/**
 * 关闭弹窗
 */
function close_dialog() {
  parent.layer.closeAll();
 // callback();
}
function back(){
  parent.layer.closeAll();
}
// function back(){
//   let index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
//   parent.layer.close(index); //再执行关闭
//   console.log('index:',index)
// }
/**
 * 导航栏关闭页面
 */
function close_tab(callback) {
  var i = $("#min_title_list .active i");
  callback();
  i.click();
}

var lanlin_call_back = function(r) {
  return;
};
var closehdtc = function() {
  $('#lanlinAlert').remove();
  $('#lanlinAlert_').remove();
  lanlin_call_back(true);
};
var esc_closehdtc = function() {
  $('#lanlinAlert').remove();
  $('#lanlinAlert_').remove();
  lanlin_call_back(false);
}
// 回调函数 用单引号 type : 1 提示 2成功提示
function lanlinAlert(information, type, callback) {
  if (callback != null) {
    lanlin_call_back = callback;
  } else {
    lanlin_call_back = function() {
      return;
    };
  }
  $('#lanlinAlert').remove();
  $('#lanlinAlert_').remove();
  var cla = "tcbjsb";// 提示信息
  if (type == '2')
    cla = "tcbjcg";// 成功信息
  $('body').append(
      '<div class="win4" id="lanlinAlert">' + 
        '<div class="bjwins"></div>' + 
        '<div class="tcalls" >' + 
          '<div class="closes">' + 
            '<img src="' + getRootPath() + '/images/fore/gb.jpg" onclick="closehdtc()" />' + 
          '</div>' + 
          '<div class="detjalls">' + 
            '<div class="' + cla + '"></div>' + information + 
          '</div>' + 
          '<div class="detjallcz" >' + 
            '<input class="btn_tqd" onclick="closehdtc();" value="确定" type="button"/>' + 
          '</div>' + 
        '</div>' + 
      '</div>' + 
      '<div class="win5" id="lanlinAlert_"></div>');
  $(".win5").height($(document).height()).show();
  /*
   * if(null == callback){ 1.5秒后关闭提示框，如有需要去掉注释
   *  } else { setTimeout("closehdtc(call_back)",1500); }
   */
}
// 微信端弹窗
function llWeAlert(information, type, callback) {
  if (type == '1' || type == null) {
    $('body').append('<div class="wxtcts" id="lanlinAlert"><font>' + information + '</font></div>');
    setTimeout("$('#lanlinAlert').remove();", 3000);
    if (null == callback) {
    } else {
      setTimeout(callback, 3000);
    }
  } else if (type == '2') { // confirm 询问框等
    $(".hdtcbj").height($(document).height());
    $(".hdtcbj").width($(document).width());
    var div = '<div class="hdtc" id="lanlinAlert">' + 
                '<div class="alltc">' + 
                  '<span>' + information + '</span>' + 
                  '<div class="all_btn">';
    for (var i = 0; i < callback.length; i++) {
      div = div + '<input type="button" class="' + callback[i][1] + '" value="' + callback[i][0] + '" onclick="javascript:' + callback[i][2] + '"/>';
    }
    div = div + '</div></div></div><div class="hdtcbj" id="lanlinAlert_"></div>';
    $('body').append(div);
    openhdtc();
  }
}
// 微信端询问框
function llWeConfirm(information, callback) {
  if (callback != null) {
    lanlin_call_back = callback;
  } else {
    lanlin_call_back = function() {
      return;
    };
  }
  $(".hdtcbj").height($(document).height());
  $(".hdtcbj").width($(document).width());
  var div = '<div class="hdtc" id="lanlinAlert">' + 
              '<div class="alltc">' + 
                '<span>' + information + '</span>' + 
                '<div class="all_btn">' +
                  '<input type="button" class="btn_tcgz1" onclick="closehdtc()" value="确定"/>' + 
                  '<input type="button" class="btn_tcqx" onclick="esc_closehdtc()" value="取消"/>' + 
                '</div>' + 
              '</div>' + 
            '</div>' + 
            '<div class="hdtcbj" id="lanlinAlert_"></div>';
  $('body').append(div);
  openhdtc();
}

function openhdtc() {
  $(".hdtc").show();
  $(".hdtcbj").show();
};

// 回调函数 用单引号
function lanlinConfirm(information, callback) {
  if (callback != null) {
    lanlin_call_back = callback;
  } else {
    lanlin_call_back = function() {
      return;
    };
  }
  $('#lanlinAlert').remove();
  $('#lanlinAlert_').remove();
  var cla = "tcbjxw";// 提示信息
  $('body').append(
      '<div class="win4" id="lanlinAlert">' + 
        '<div class="bjwins"></div>' + 
        '<div class="tcalls" >' + 
          '<div class="closes">' + 
            '<img src="' + getRootPath() + '/images/fore/gb.jpg" onclick="esc_closehdtc();" />' + 
          '</div>' + 
          '<div class="detjalls">' + 
            '<div class="' + cla + '">' + '</div>' + information + 
          '</div>' + 
          '<div class="detjallcz" >' + 
            '<input class="btn_tqx" onclick="esc_closehdtc();" value="取消" type="button"/>' + 
            '<input class="btn_tqd" onclick="closehdtc();" value="确定" type="button"/>' + 
          '</div>' + 
        '</div>' + 
      '</div>' + 
      '<div class="win5" id="lanlinAlert_"></div>');
  $(".win5").height($(document).height()).show();
}
// 回调函数 用单引号
function lanlinConfirmYN(information, callback) {
  if (callback != null) {
    lanlin_call_back = callback;
  } else {
    lanlin_call_back = function(r) {
      return;
    };
  }
  $('#lanlinAlert').remove();
  $('#lanlinAlert_').remove();
  var cla = "tcbjxw";// 提示信息
  $('body').append(
      '<div class="win4" id="lanlinAlert">' + 
        '<div class="bjwins"></div>' + 
        '<div class="tcalls" >' + 
          '<div class="detjalls">' + 
            '<div class="' + cla + '">' + '</div>' + information + 
          '</div>' + 
          '<div class="detjallcz" >' + 
            '<input class="btn_tqx" onclick="esc_closehdtc();" value="否" type="button"/>' + 
            '<input class="btn_tqd" onclick="closehdtc();" value="是" type="button"/>' + 
          '</div>' + 
        '</div>' + 
      '</div>' + 
      '<div class="win5" id="lanlinAlert_"></div>');
  $(".win5").height($(document).height()).show();
}

function setCookie(name, value) {
  var Days = 30; // 此 cookie 将被保存 30 天
  var exp = new Date(); // new Date("December 31, 9998");
  exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
  document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}

// 读取cookies函数
function getCookie(name) {
  var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
  if (arr != null)
    return unescape(arr[2]);
  return null;
}


/**
 * pc后台管理端 上传图片
 * 
 * @param img_id
 *          无回调函数 则将id为img_id的img赋值
 * @param width_
 * @param callBack
 *          回调函数 该函数存在优先函数 例： img_upload('imgid',400);
 *          img_upload(null,400,function(res){alert("图片路径"+src)});
 */
function img_upload(img_id, width_, callBack) {
  console.log('1 img_upload');
  create_img_file_();
  $('#img_upload_file').change(function() {
    // this.files[0] 是用户选择的文件
    var index = layer.load();
    var img_upload_callback = function(result) {
      $(".cd-popup").remove();
      $('#img_upload_file').remove();
      layer.close(index);
      var status = result.status;
      var map = result.map;
      if (status == '1') {
        layer.alert(map.message);
        return false;
      } else {
        if (callBack) {
          callBack(map.fileName);
        } else {
          $("#" + img_id).attr("src", map.fileName).attr("url", map.fileName);
        }
      }
    }
    if((!!window.ActiveXObject || "ActiveXObject" in window)) {
      ajaxFileUpload(img_upload_callback);
    } else {
      lrzImgUpload(this, width_, img_upload_callback);
    }
  })
}

function create_img_file_() {
  console.log('2 create_img_file_');
  /*if((!!window.ActiveXObject || "ActiveXObject" in window) && !(navigator.userAgent.search(/Trident/i) > 0)) {
  alert("浏览器版本过低，请升级浏览器或使用其他浏览器进行操作");
  return false;
  }*/
  if ($("#img_upload_file").length > 0) {
    $("#img_upload_file").remove();
  }
  if((!!window.ActiveXObject || "ActiveXObject" in window)) {
    var str = '<div class="cd-popup"><div class="cd-popup1"><div class="cd-popup-container1"><p style="margin-top:0;">请选择文件</p><div class="allsq">'+
    '<input type="file" class=" txt_sq" value="" id="img_upload_file" name="img_upload_file" accept="image/*" style="width:170px;margin-top: 70px"/>'+
    '</div><a href="javascript:void(0)" onclick="$(this).closest(\'.cd-popup\').remove();"><img src="'+getRootPath()+'/images/fore/tcgb.jpg" class="cd-popup-close"/></a></div></div></div>';
    $("body").append(str);
  } else {
    $("body").append('<input type="file" id="img_upload_file" style="display:none;" name="img_upload_file" accept="image/*"/>')
    $("#img_upload_file").click();
  }
}

function lrzImgUpload(obj,width_, callback) {
  console.log('3 lrzImgUpload');
  lrz(obj.files[0], {
    width : width_
  }).then(function(rst) {
    var submitData = {
        data : rst.base64.split("base64,")[1]
    };
    $.ajax({
      type : "POST",
      url : getRootPath() + '/ajaxUpload',
      data : submitData,
      async: false,
      dataType : "text",
      success : function(result_) {
        var result = JSON.parse(result_);
        if(result.status != '0') {
          ajaxFileUpload(callback);
        } else {
          callback(result);
        }
      },
      complete : function(XMLHttpRequest, textStatus) {
      },
      error : function(XMLHttpRequest, textStatus, errorThrown) { // 上传失败
        var result = new Object();
        var map = new Object();
        map.message = "图片上传失败";
        result.status = "1";
        result.map = map;
      }
    });
  }).catch(function(res){
    ajaxFileUpload(callback);
  });
}

function ajaxFileUpload(callback) {
  console.log('4 ajaxFileUpload');
  $.ajaxFileUpload({
      url: getRootPath() + '/ajaxUpload', //用于文件上传的服务器端请求地址
      secureuri: false, //一般设置为false
      fileElementId: 'img_upload_file', //文件上传空间的id属性  <input type="file" id="file" name="file" />
      dataType: 'json', //返回值类型 一般设置为json
      async: false,
      success: function (result) { //服务器成功响应处理函数
        callback(result);
      },
      error: function (data, status, e) {//服务器响应失败处理函数
        var result = new Object();
        var map = new Object();
        map.message = "图片上传失败";
        result.status = "1";
        result.map = map;
        callback(result);
      }
  })
}

/**
 * pc 前端 上传图片
 * 
 * @param img_id
 *          无回调函数 则将id为img_id的img赋值
 * @param width_
 * @param callBack
 *          回调函数 该函数存在优先函数 例： img_upload('imgid',400);
 *          img_upload1(null,400,function(res){alert("图片路径"+src)});
 */
function img_upload1(img_id, width_, callBack) {
  console.log('img_upload1');
  create_img_file_();
  $('#img_upload_file').change(function() {
    $("html").mask("处理中。。。");
    // this.files[0] 是用户选择的文件
    var img_upload_callback = function(result) {
      $(".cd-popup").remove();
      $("html").unmask();
      $('#img_upload_file').remove();
      var status = result.status;
      var map = result.map;
      if (status == '1') {
        lanlinAlert(map.message);
        return false;
      } else {
        if (callBack) {
          callBack(map.fileName)
        } else {
          $("#" + img_id).attr("src", map.fileName).attr("url", map.fileName);
        }
      }
    }
    if((!!window.ActiveXObject || "ActiveXObject" in window)) {
      ajaxFileUpload(img_upload_callback);
    } else {
      lrzImgUpload(this, width_, img_upload_callback);
    }
  })
}

/**
 * 微信端上传图片，后期逻辑自己处理 
 * 例：img_upload2(null,400,function(src){alert('图片路径：' + src)});
 * img_upload2('img_id',400);
 * 
 * @param width_
 */
function img_upload2(img_id, width_, callBack) {
  console.log('img_upload2');
  create_img_file_();
  $('#img_upload_file').change(function() {
    $("html").mask("处理中。。。");
    // this.files[0] 是用户选择的文件
    var img_upload_callback = function(result) {
      $(".cd-popup").remove();
      $("html").unmask();
      $('#img_upload_file').remove();
      var status = result.status;
      var map = result.map;
      if (status == '1') {
        llWeAlert(map.message);
        return false;
      } else {
        if (callBack) {
          callBack(map.fileName)
        } else {
          $("#" + img_id).attr("src", map.fileName).attr("url", map.fileName);
        }
      }
    }
    if((!!window.ActiveXObject || "ActiveXObject" in window)) {
      ajaxFileUpload(img_upload_callback);
    } else {
      lrzImgUpload(this, width_, img_upload_callback);
    }
  })
}