var welcome = (function(){
  var rootPath = getRootPath();
  var info = {
      init : function(){
        $("#toOpenDanger").unbind("click").bind("click",function(){
          info.openChooseDanger();
        });
      },
      
      openChooseDanger : function(){
        layer.confirm('请选择您所要查看的类型？', {
          btn: ['重大污染源','高危作业'], //按钮,
          title : '提示'
        }, function(){
          $("#openZdwry").click();
          layer.closeAll();
        }, function(){
          $("#openGwzy").click();
          layer.closeAll();
        });
      }
  }
  
  return {
    info : info
  }
})();

$(function(){
  welcome.info.init();
});