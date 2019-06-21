$(function() {
  $(".one_gnmc").click(function() {
    if ($(this).parent().hasClass("one_gnxz")) {
      $(this).parent().removeClass("one_gnxz");
    } else {
      $(this).parent().addClass("one_gnxz");
    }
  });
  $(".one_zgn1").click(function() {
    if ($(this).hasClass("one_zgn1xz")) {
      $(this).removeClass("one_zgn1xz");
    } else {
      $(this).addClass("one_zgn1xz");
    }
  });
});
