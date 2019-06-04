//数值输入框格式化
$(function() {
  $("body").on("keyup", ".ll-numberbox", function(e) {
//    if ((e.which >= 48 && e.which <= 57 && e.ctrlKey == false && e.shiftKey == false) || e.which == 0 || e.which == 8) {
//    } else {
//      if (e.ctrlKey == true && (e.which == 99 || e.which == 118)) {
//      } else {
//      }
//    }
    if(e.which == 8 || e.which == 0 || (e.which >= 48 && e.which <= 57 && e.ctrlKey == false && e.shiftKey == false)) {
      return false;
    }
    //var options = $(this).attr("data-options").split(",");
    var t_v = $(this).val().trim().replace(/[^\d\.\-]/g, '');
    $(this).val(t_v);
    /*if (t_v == '') {
      return false;
    }
    
    if(t_v.startWith(".")) {
      t_v = "0" + t_v;
    }

    var a1, a2;
    var sp = t_v.split(".");
    if (sp.length >= 2) {
      a1 = sp[0];
      a2 = sp[1];
    } else {
      a1 = sp[0];
      a2 = '00';
    }
    t_v = a1 + "." + a2;

    var min, max, precision = 0;// 最小值，最大值，小数位

    for (var i = 0; i < options.length; i++) {
      if (options[i].startWith("min:"))
        min = options[i].replace("min:", "");
      if (options[i].startWith("max:"))
        max = options[i].replace("max:", "");
      if (options[i].startWith("precision:"))
        precision = parseInt(options[i].replace("precision:", ""));
    }
    if (parseFloat(t_v) - parseFloat(min) < 0) {
      t_v = min;
    }
    if (parseFloat(t_v) - parseFloat(max) > 0) {
      t_v = max;
    }

    t_v = parseFloat(t_v).toFixed(precision)
    $(this).val(t_v);*/
  })
  
  $("body").on("blur", ".ll-numberbox", function(e) {
    var options = $(this).attr("data-options").split(",");
    var t_v = $(this).val().trim().replace(/[^\d\.\-]/g, '');
    if (t_v == '') {
      $(this).val(t_v);
      return false;
    }
    
    if(t_v.startWith(".")) {
      t_v = "0" + t_v;
    }

    var a1, a2;
    var sp = t_v.split(".");
    if (sp.length >= 2) {
      a1 = sp[0];
      a2 = sp[1];
    } else {
      a1 = sp[0];
      a2 = '00';
    }
    t_v = a1 + "." + a2;

    var min, max, precision = 0;// 最小值，最大值，小数位

    for (var i = 0; i < options.length; i++) {
      if (options[i].startWith("min:"))
        min = options[i].replace("min:", "");
      if (options[i].startWith("max:"))
        max = options[i].replace("max:", "");
      if (options[i].startWith("precision:"))
        precision = parseInt(options[i].replace("precision:", ""));
    }
    if (parseFloat(t_v) - parseFloat(min) < 0) {
      t_v = min;
    }
    if (parseFloat(t_v) - parseFloat(max) > 0) {
      t_v = max;
    }

    t_v = parseFloat(t_v).toFixed(precision)
    $(this).val(t_v);
  })
})
