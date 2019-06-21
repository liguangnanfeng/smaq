function chat(id) {
  $(".ltframe").remove();
  $("body").append('<div class="ltframe"><iframe src="' + getRootPath() + '/common/websocket?id=' + id + '" scrolling="no" ></iframe></div>')
  var l = $(document).width() - 735;
  $(".ltframe").css("left", l / 2);
}

function wechat(id) {
  window.location.href = getRootPath() + "/common/websocketWe?id=" + id;
}

function closeChat() {
  $(".ltframe").remove();
}

function bigWin() {

}
$(function() {
  $(".one_riyc").hover(function() {
    $(this).addClass("one_riycxz");
  }, function() {
    $(this).removeClass("one_riycxz");
  });
  $("#div_yjfk").click(function() {
    $(".win2").height($(document).height());
    $(".win2").show();
    $(".div_fan").show();
  });
  $(".fangb").live("click", function() {
    $(".div_fan").hide();
    $(".win2").hide();
  });

});