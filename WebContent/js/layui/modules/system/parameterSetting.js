var parameterSetting = (function(){
  var rootPath = getRootPath();
  var info = {
      init : function(){
        $(".toEditParam").unbind("click").bind("click",function(){
          var name = $(this).attr("core-name");
          var id = $(this).attr("core-id");
          var max = $(this).attr("core-max");
          var min = $(this).attr("core-min");
          info.toUpdateParam(name,id,max,min,this);
        });
      },
      
      toUpdateParam : function(name,id,max,min,_this){
        var html = '';
        html += '<div style="width:300px;height:200px;">';
        html += '<div style="margin-top:10px;">名称:'+name+'</div>';
        html += '<div style="margin-top:10px;">最大值:<input type="text" maxLength="20" value="'+max+'" id="maxId"/></div>';
        html += '<div style="margin-top:10px;">最小值:<input type="text" maxLength="20" value="'+min+'" id="minId"/></div>';
        html += '<div style="margin-left: 30%;margin-top: 90px;"><a class="label label-primary radius" href="javascript:void(0)" id="toSubmit">确定</a>';
        html += '<a href="javascript:void(0)" id="toCancel" class="label  radius">取消</a></div>';
        html += '</div>';
        layer.open({
          title : '提示',
          type: 1,
          skin: 'layui-layer-rim', //加上边框
          area: 'auto !important', //宽高
          content: html
        });
        
        $("#toCancel").on("click",function(){
          layer.closeAll();
        });
        
        $("#toSubmit").unbind("click").bind("click",function(){
          var maxV = $.trim($("#maxId").val());
          var minV = $.trim($("#minId").val());
          var reg = /^(-?\d+)(\.\d+)?$/;
          if(!reg.test(maxV)){
            $("#maxId").focus();
            layer.msg("请输入正确的浮点数！");
            return;
          }
          if(!reg.test(minV)){
            $("#minId").focus();
            layer.msg("请输入正确的浮点数！");
            return;
          }
          
          if(parseFloat(minV) > parseFloat(maxV)){
            layer.msg("所输入的最小值不能比最大值大！");
            return;
          }
          $.ajax({
            url : rootPath + "/village/system/doUpdateWaterParams",
            type : 'post',
            data : {
              id : id,
              maxv : maxV,
              minv : minV
            },success : function(data){
              if(data.status){
                layer.msg("修改成功");
                $(_this).attr("core-max",maxV);
                $(_this).attr("core-min",minV);
                var index = $(_this).attr("core-index");
                $("#max_"+index).html(maxV);
                $("#min_"+index).html(minV);
                layer.closeAll();
              }else{
                layer.msg("修改失败，请重试");
              }
            },error : function(data){
              layer.msg("修改失败，请重试");
            }
          });
        });
      }
      
  }
  
  return {
    info : info
  }
})();

$(function(){
  parameterSetting.info.init();
})