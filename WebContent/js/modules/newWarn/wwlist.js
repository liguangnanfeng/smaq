var wwlist = (function(){
var rootPath = getRootPath();
var companyId = $("#companyId").val();
  var info = {
      init : function(){
        juicerUtils.init();
        info.getCompanyList(1,10,"");
        /*$("#toSearchCompany").unbind("click").bind("click",function(){
          var companyName = $.trim($("#searchCompany").val());
          if("" != companyName){
            companyName = encodeURI(encodeURI(companyName));
          }
          info.getCompanyList(1,10,companyName);
        });*/
        
        $("#typeSelect").on("change",function(){
          var currType = $(this).children("option:selected").val();
          info.getCompanyList(1,10,currType);
        })
      },
      
      getCompanyList : function(page,limit,sensorType){
        $.ajax({
          url : rootPath + "/village/company/getWwList",
          type : 'post',
          data : {
            page : page,
            limit : limit,
            companyId : companyId,
            sensorType : sensorType
          },
          success : function(data){
            if(undefined != data.list && data.list.length > 0){
              var tpl = $("#listTemplate").html();
              var html = juicer(tpl,data);
              $("#showListBody").html(html);
              $("#showTotal").html(data.total);
            }else{
              $("#showTotal").html(0);
              $("#showListBody").html('<tr class="text-c"><td colspan="6">暂未查询到河道污染数据</td></tr>');
            }
            
            var total = data.total;
            laypage({
              cont : 'demo11',
              pages : Math.ceil(parseInt(total) / limit),
              curr : page || 1,
              first : '首页',
              last : '尾页',
              jump : function(obj, first) {
                if (!first) {
                  info.getCompanyList(obj.curr,limit,sensorType);
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
  wwlist.info.init();
})