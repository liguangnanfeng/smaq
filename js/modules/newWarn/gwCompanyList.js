var gwCompanyList = (function(){
var rootPath = getRootPath();
  
  var info = {
      init : function(){
        juicerUtils.init();
        info.getCompanyList(1,10,"");
        $("#toSearchCompany").unbind("click").bind("click",function(){
          var companyName = $.trim($("#searchCompany").val());
          if("" != companyName){
            companyName = encodeURI(encodeURI(companyName));
          }
          info.getCompanyList(1,10,companyName);
        });
      },
      
      getCompanyList : function(page,limit,companyName){
        $.ajax({
          url : rootPath + "/village/company/getGwCompanyList",
          type : 'post',
          data : {
            page : page,
            limit : limit,
            companyName : companyName,
            districtId : $("#district_id").val(),
            townId : $("#town_id").val(),
            villageId : $("#village_id").val()
          },
          success : function(data){
            if(undefined != data.list && data.list.length > 0){
              var tpl = $("#listTemplate").html();
              var html = juicer(tpl,data);
              $("#showListBody").html(html);
              $("#showTotal").html(data.total);
            }else{
              $("#showTotal").html(0);
              $("#showListBody").html('<tr class="text-c"><td colspan="4">暂无高危作业企业</td></tr>');
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
                  info.getCompanyList(obj.curr,limit,companyName);
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
  gwCompanyList.info.init();
})