$(function(){
  
  
  $(".img_check").click(function(){
    if($(this).hasClass("img_checkxz")){
      $(this).removeClass("img_checkxz");
    }else{
      $(this).addClass("img_checkxz");
    }
  });
  function print(){
    $("#print2").jqprint();
  }
})


