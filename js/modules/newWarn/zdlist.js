var zdlist = (function(){
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
          url : rootPath + "/village/company/getZdList",
          type : 'post',
          data : {
            page : page,
            limit : limit,
            companyId : companyId,
            sensorType : sensorType
          },
          success : function(data){
        	  var type = $("#typeId").val();
	          	var colSpan = 7;
	          	if(5 == type){
	          		colSpan = 6;
	          	}
            if(undefined != data.list && data.list.length > 0){
            	if(colSpan == 6){
              	  $.each(data.list,function(index,m){
              		  m.isCompany = 1;
              	  })
                }
              var tpl = $("#listTemplate").html();
              juicer.register("subname",info.subname);
              var html = juicer(tpl,data);
              $("#showListBody").html(html);
              $("#showTotal").html(data.total);
            }else{
              $("#showTotal").html(0);
              $("#showListBody").html('<tr class="text-c"><td colspan="'+colSpan+'">还未接入报警设备</td></tr>');
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
      },
      
      subname :function(name){
     	 return name.substring(0,name.length -3);
   	 }
  }
  return {
    info : info
  }
})();

$(function(){
  zdlist.info.init();
})