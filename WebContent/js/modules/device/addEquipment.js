var addEquipment = (function(){
  
  var rootPath = getRootPath();
  
  var interValIndex;
  
  var info = {
      
      init : function(){
        juicerUtils.init();
        $("#toSearchCompany").unbind("click").bind("click",function(){
          info.openChooseCompany();
        });
        
        $("#addDeviceButton").unbind("click").bind("click",function(){
          info.addDevice();
        });
        
        
        $("#companySelect").on("change",function(){
           var companyId = $("#companySelect").children("option:selected").val();
           var deviceName = $.trim($("#searchDeviceInput").val());
           if("" != deviceName){
             deviceName = encodeURI(encodeURI(deviceName));
           }
           ////clearInterval(interValIndex);
           info.getDeviceList(1,10,companyId,deviceName);
        });
        var isList = $("#listPageFlag").val();
        if("" != isList && isList == 1){
          info.getDeviceList(1,10,"","");
          
          interValIndex = setInterval(function(){
            var companyId = $("#companySelect").children("option:selected").val();
            var deviceName = $.trim($("#searchDeviceInput").val());
            if("" != deviceName){
              deviceName = encodeURI(encodeURI(deviceName));
            }
            info.getDeviceList(1,10,companyId,deviceName);
          },1000*60*5);
        }
        $("#toSearchDeviceName").unbind("click").bind("click",function(){
          var companyId = $("#companySelect").children("option:selected").val();
          var deviceName = $.trim($("#searchDeviceInput").val());
          if("" != deviceName){
            deviceName = encodeURI(encodeURI(deviceName));
          }
          //clearInterval(interValIndex);
          info.getDeviceList(1,10,companyId,deviceName);
        });
        
        $("#deviceSn").unbind("blur").bind("blur",function(){
          var deviceId = $("#deviceId").val();
          var deviceSn = $.trim($("#deviceSn").val());
          if("" == deviceSn){
            layer.msg("设备序列号不能为空");
            return;
          }else{
            $.ajax({
              url : rootPath + "/back/checkDeviceSnExsist",
              type : 'post',
              async : false,
              data : {
                deviceSn : deviceSn
              },success : function(data){
                if(undefined != data.id && "" != data.id){
                  if("" == deviceId || deviceId != data.id){
                    layer.msg("设备序列号已存在，请重新输入");
                    return;
                  }
                }
              }
            });
          }
        });
      },
      
      getDeviceList : function(page,limit,companyId,deviceName){
        $.ajax({
          url : rootPath + "/back/getDeviceList",
          type : 'post',
          data : {
            page : page,
            limit : limit,
            companyId : companyId,
            deviceName : deviceName
          },
          success : function(data){
            if(undefined != data.list && data.list.length > 0){
              var tpl = $("#reviceListTemplate").html();
              var html = juicer(tpl,data);
              $("#showListBody").html(html);
              $("#showTotal").html(data.total);
              
            }else{
              //clearInterval(interValIndex);
              $("#showTotal").html(0);
              $("#showListBody").html('<tr class="text-c"><td colspan="8">暂无设备</td></tr>');
            }
            
            $(".toDel").unbind("click").bind("click",function(){
              var deviceSn = $(this).attr("core-snid");
              layer.confirm('删除后，该设备对应的传感器也会被删除，确认删除吗？', {
                btn: ['确定','取消'] //按钮
              }, function(){
                $.ajax({
                  url : rootPath + "/back/toDeleteDevice",
                  type : 'post',
                  data : {
                    deviceSn : deviceSn
                  },
                  success : function(data){
                    if(data.status){
                      layer.msg("删除成功");
                      //clearInterval(interValIndex);
                      info.getDeviceList(1,limit,companyId,deviceName);
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
                  //clearInterval(interValIndex);
                  info.getDeviceList(obj.curr,limit,companyId,deviceName);
                }
              }
            });
          }
        });
      },
      
      addDevice : function(){
        var hasCheckSn = false;
        var deviceId = $("#deviceId").val();
        var deviceName = $.trim($("#deviceName").val());
        if("" == deviceName){
          layer.msg("设备名称不能为空");
          return;
        }
        var deviceSn = $.trim($("#deviceSn").val());
        if("" == deviceSn){
          layer.msg("设备序列号不能为空");
          return;
        }else{
          $.ajax({
            url : rootPath + "/back/checkDeviceSnExsist",
            type : 'post',
            async : false,
            data : {
              deviceSn : deviceSn
            },success : function(data){
              if(undefined != data.id && "" != data.id){
                if(data.id == deviceId){
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
          layer.msg("设备序列号已存在，请重新输入");
          return;
        }
        var companyId = $("#companyIdVal").val();
        if("" == companyId){
          layer.msg("请选择放置单位");
          return;
        }
        var site = $.trim($("#site").val());
        if("" == site){
          layer.msg("设备放置场所不能为空");
          return;
        }
        var phonename = $.trim($("#phonename").val());
        $.ajax({
          url : rootPath + "/back/addDevice",
          type : 'post',
          data : {
            deviceId : deviceId,
            deviceName : deviceName,
            deviceSn : deviceSn,
            companyId : companyId,
            site : site,
            phonename : phonename
          },success : function(data){
            if(data.status){
              if("" != deviceId){
                layer.msg("保存成功");
              }else{
                layer.msg("添加成功");
              }
              setTimeout(function(){
                location.href= rootPath+"/back/toDeviceList"
              },1500);
            }else{
              layer.msg("添加失败，请重试！");
            }
          }
        });
        
      },
      
      openChooseCompany : function(){
        var html = '';
        html += '<div class="tcall" style="height: 380px;position:relative;overflow:hidden;">';
        html += '  <div class=""  style="border: 0px;">';
        html += '    <span>请输入要添加的企业名称:</span>';
        html += '    <input type="text" id="searchCompanyName" placeholder="请输入要搜索的企业名称" style="width:300px" class="input-text required" >';
        html += '    <a href="javascript:void(0);" class="btn btn-default radius" id="toSearchCompanyA" style="">搜索</a>';
        html += '  </div>';
        html += '  <div class=""  style="border: 0px;">';
        html += '    <span>已选择企业:</span>';
        html += '    <span id="hasSelectCompany"></span>';
        html += '  </div>';
        html += '  <div class="allsq listem" id="canChooseCompanyList" style="width:93%;height:295px;overflow-Y:auto;margin-left:7%;">';
            
        html += '  </div>';
        html += '  <div style="bottom:10px;left:0;width:100%;text-align:center">';
        html += '    <a  class="btn btn-primary radius" id="submitChooseCompany" type="button" style="width:50px;">保存</a>';
        html += '    <a  class="btn btn-default radius cancel_btn" type="button" style="width:50px;">取消</a>';
        html += '  </div>';
        html += '</div>';
        layer.open({
          type: 1,
          title : '更换企业单位',
          skin: 'layui-layer-rim', //加上边框
          area: ['420px', '500px'], //宽高
          content: html
        });
        
        $("#submitChooseCompany").unbind("click").bind("click",function(){
          var hasChoosed = $("#hasSelectCompany").attr("core-userid");
          var hasChoosedName = $("#hasSelectCompany").html();
          if(undefined == hasChoosed || "" == hasChoosed){
            layer.msg("请先选择放置的单位");
            return;
          }else{
            $("#companySpan").html(hasChoosedName);
            $("#companyIdVal").val(hasChoosed);
            $("#companyNameVal").val(hasChoosedName);
            layer.closeAll();
          }
        });
        
        $(".cancel_btn").unbind("click").bind("click",function(){
          layer.closeAll();
        });
        
        var oldCompanyName = $("#companyNameVal").val();
        if("" != oldCompanyName){
          $("#hasSelectCompany").html(oldCompanyName);
          $("#hasSelectCompany").attr("core-userid",$("#companyIdVal").val());
        }else{
          $("#hasSelectCompany").html('暂未选择企业');
        }
        
        $("#toSearchCompanyA").unbind("click").bind("click",function(){
          var companyName = $("#searchCompanyName").val();
          if("" != companyName){
            companyName = encodeURI(encodeURI(companyName));
          }
          $.ajax({
            url : rootPath + "/back/getCompanyByCondition",
            type : 'post',
            data : {
              companyName : companyName
            },
            success : function(data){
              if(undefined != data.list && data.list.length > 0){
                var html = '';
                $.each(data.list,function(index,c){
                  html += '<em style="cursor:pointer;"><input name="company" core-name="'+c.companyName+'" type="radio"';
                  html += 'value="'+c.userId+'"/><span class="namespan">'+c.companyName+"</span></em>";
                })
                $("#canChooseCompanyList").html(html);
                
                $(".namespan").unbind("click").bind("click",function(){
                  $(this).parent("em").children("input").click();
                });
                
                $("input[name=company]").unbind("click").bind("click",function(){
                  var currUserId = $(this).val();
                  var currName = $(this).attr("core-name");
                  $("#hasSelectCompany").html(currName);
                  $("#hasSelectCompany").attr("core-userid",currUserId);
                });
              }else{
                $("#canChooseCompanyList").html('暂未搜索到企业');
              }
            }
          });
        });
      }
  }
  
  return {
    info : info
  }
  
})()

$(function(){
  addEquipment.info.init();
})