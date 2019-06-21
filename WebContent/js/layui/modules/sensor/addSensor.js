var addSensor = (function(){
  
  var rootPath = getRootPath();
  var info = {
      
      init : function(){
        juicerUtils.init();
        
        $("#addDeviceButton").unbind("click").bind("click",function(){
          info.addSensor();
        });
        
        $("#deviceSelect").on("change",function(){
           var deviceSn = $("#deviceSelect").children("option:selected").val();
           var sensorName = $.trim($("#searchSensorInput").val());
           if("" != sensorName){
             sensorName = encodeURI(encodeURI(sensorName));
           }
           info.getSensorList(1,10,deviceSn,sensorName);
        });
        var isList = $("#listPageFlag").val();
        if("" != isList && isList == 1){
          info.getSensorList(1,10,"","");
        }
        $("#toSearchSensorName").unbind("click").bind("click",function(){
          var deviceSn = $("#deviceSelect").children("option:selected").val();
          var sensorName = $.trim($("#searchSensorInput").val());
          if("" != sensorName){
            sensorName = encodeURI(encodeURI(sensorName));
          }
          info.getSensorList(1,10,deviceSn,sensorName);
        });
        
        $("#sensorSn").unbind("blur").bind("blur",function(){
          var sensorId = $("#sensorId").val();
          var sensorSn = $.trim($("#sensorSn").val());
            $.ajax({
              url : rootPath + "/village/checkSensorExsits",
              type : 'post',
              async : false,
              data : {
                sensorSn : sensorSn
              },success : function(data){
                if(undefined != data.id && "" != data.id){
                  if(data.id != sensorId){
                    layer.msg("传感器编号已存在，请重新输入");
                    return;
                  }
                }
              }
            });
        });
      },
      
      getSensorList : function(page,limit,deviceSn,sensorName){
        $.ajax({
          url : rootPath + "/village/getSensorList",
          type : 'post',
          data : {
            page : page,
            limit : limit,
            deviceSn : deviceSn,
            sensorName : sensorName
          },
          success : function(data){
            if(undefined != data.list && data.list.length > 0){
              var tpl = $("#reviceListTemplate").html();
              var html = juicer(tpl,data);
              $("#showListBody").html(html);
              $("#showTotal").html(data.total);
            }else{
              $("#showTotal").html(0);
              $("#showListBody").html('<tr class="text-c"><td colspan="7">暂无传感器</td></tr>');
            }
            
            $(".toDel").unbind("click").bind("click",function(){
              var id = $(this).attr("core-id");
              layer.confirm('确认删除吗？', {
                btn: ['确定','取消'] //按钮
              }, function(){
                $.ajax({
                  url : rootPath + "/village/toDeleteSensor",
                  type : 'post',
                  data : {
                    id : id
                  },
                  success : function(data){
                    if(data.status){
                      layer.msg("删除成功");
                      info.getSensorList(1,limit,deviceSn,sensorName);
                    }else{
                      layer.msg("删除失败，请重试");
                    }
                  }
                });
              }, function(){
                layer.closeAll();
              });
            });
            
            var total = data.total;
            laypage({
              cont : 'demo11',
              pages : Math.ceil(parseInt(total) / limit),
              curr : page || 1,
              first : '首页',
              last : '尾页',
              jump : function(obj, first) {
                if (!first) {
                  info.getSensorList(obj.curr,limit,deviceSn,sensorName);
                }
              }
            });
          }
        });
      },
      
      addSensor : function(){
        var hasCheckSn = false;
        var sensorId = $("#sensorId").val();
        var sensorName = $.trim($("#sensorname").val());
        if("" == sensorName){
          layer.msg("传感器名称不能为空");
          return;
        }
        var sensorSn = $.trim($("#sensorSn").val());
        if("" == sensorSn){
          layer.msg("传感器通道号不能为空");
          return;
        }else{
          $.ajax({
            url : rootPath + "/village/checkSensorExsits",
            type : 'post',
            async : false,
            data : {
              sensorSn : sensorSn
            },success : function(data){
              if(undefined != data.id && "" != data.id){
                if(data.id == sensorId){
                  hasCheckSn = true;
                }else{
                  hasCheckSn = false;
                }
              }else{
                hasCheckSn = true;
              }
            }
          });
        }
        if(!hasCheckSn){
          layer.msg("传感器通道号已存在，请重新输入");
          return;
        }
        
        var deviceSn = $("#deviceSn").children("option:selected").val();
        if("" == deviceSn){
          layer.msg("请选择设备序列号");
          return;
        }
        
        var sensorType = $("#sensorType").children("option:selected").val();
        if("" == sensorType){
          layer.msg("请选择传感器类型");
          return;
        }
        var site = $.trim($("#site").val());
        if("" == site){
          layer.msg("放置场所不能为空");
          return;
        }
        $.ajax({
          url : rootPath + "/village/addSensor",
          type : 'post',
          data : {
            sensorId : sensorId,
            sensorName : sensorName,
            sensorSn : sensorSn,
            deviceSn : deviceSn,
            site : site,
            sensorType : sensorType
          },success : function(data){
            if(data.status){
              if("" != sensorId){
                layer.msg("保存成功");
              }else{
                layer.msg("添加成功");
              }
              setTimeout(function(){
                location.href= rootPath+"/village/toSensorList"
              },1500);
            }else{
              layer.msg("添加失败，请重试！");
            }
          }
        });
        
      }
      
  }
  
  return {
    info : info
  }
  
})()

$(function(){
  addSensor.info.init();
})