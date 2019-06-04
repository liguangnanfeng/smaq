var timeSetting = (function(){
  var rootPath = getRootPath();
  var info = {
      init : function(){
        $("#timeCleanSelect").unbind("change").bind("change",function(){
          var currTime = $(this).children("option:selected").val();
          info.toUpdateSettingTime(currTime);
        });
      },
      
      toUpdateSettingTime : function(times){
        $.ajax({
          url : rootPath + "/back/doTtimesetting",
          type : 'post',
          data : {
            times : times
          },
          success : function(data){
            if(data.status){
              layer.msg("修改成功");
            }else{
              layer.msg("修改失败，请重试！");
            }
          },
          error : function(data){
            layer.msg("修改失败，请重试！");
          }
        });
      }
  }
  
  return {
    info : info
  }
})();

$(function(){
  timeSetting.info.init();
});