var reviceList = (function(){
  
  var rootPath = getRootPath();
  
  var info = {
      init : function(){
        juicerUtils.init();
        info.getList(1,10,"");
        $("#toSearchGroupName").unbind("click").bind("click",function(){
          var searchTitle = $.trim($("#searchGroupNameInput").val());
          if("" != searchTitle){
            searchTitle = encodeURI(encodeURI(searchTitle));
          }
          info.getList(1,10,searchTitle);
        });
      },
      
      getList : function(page,limit,searchTitle){
        $.ajax({
          url : rootPath + "/village/getReviceList",
          type : 'post',
          data : {
            page : page,
            limit : limit,
            title : searchTitle
          },
          success : function(data){
            if(undefined != data.list && data.list.length > 0){
              var tpl = $("#reviceListTemplate").html();
              var html = juicer(tpl,data);
              $("#showListBody").html(html);
              $("#showTotal").html(data.total);
            }else{
              $("#showTotal").html(0);
              $("#showListBody").html('<tr class="text-c"><td colspan="5">暂无接收信息</td></tr>');
            }
            
            $(".toDel").unbind("click").bind("click",function(){
              var messageId = $(this).attr("core-id");
              layer.confirm('确认删除吗？', {
                btn: ['确定','取消'] //按钮
              }, function(){
                $.ajax({
                  url : rootPath + "/village/toDeleteReviceMsg",
                  type : 'post',
                  data : {
                    messageId : messageId
                  },
                  success : function(data){
                    if(data.status){
                      layer.msg("删除成功");
                      info.getList(1,limit,searchTitle)
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
                  info.getList(obj.curr,limit,searchTitle);
                }
              }
            });
            
          }
        });
      }
  }
  
  return {
    info : info
  }
  
})();

$(function(){
  reviceList.info.init();
})