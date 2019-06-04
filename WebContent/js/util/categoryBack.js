$(function(){
  var categoryBackId = '';
  
  if($("#categoryBackId").length > 0)
    categoryBackId = $("#categoryBackId").val();
  
  //新增初始化
  if(categoryBackId == ''){
    childCategoryBack(null);
  } else{ //编辑初始化
    parentCategoryBack(categoryBackId);
  }
  
  //省-》市联动
  $("#category_back_1").change(function(){
    if($("#category_back_2").length == 0){
      return false;
    }
    $("#category_back_2").empty();
    $("#category_back_3").empty();
    $("#category_back_2").append("<option value=''>请选择</option>");
    $("#category_back_3").append("<option value=''>请选择</option>");
    if($(this).val() != '') {
      childCategoryBack($(this).val());
    }
  });
  
  //市 -》区
  $("#category_back_2").change(function(){
    if($("#category_back_3").length == 0){
      return false;
    }
    $("#category_back_3").empty();
    $("#category_back_3").append("<option value=''>请选择</option>");
    if($(this).val() != '') {
      childCategoryBack($(this).val());
    }
  });
  
});

function childCategoryBack(id){
  $.post(getRootPath() + '/common/cgf/childCategoryBack', {
    id : id
  }, function(result) {
    var list = result.map.list;
    var type = result.map.type;
    if(type == '0'){//第一级
      $("#category_back_1").empty();
      $("#category_back_2").empty();
      $("#category_back_3").empty();
      $("#category_back_1").append("<option value=''>请选择</option>");
      $("#category_back_2").append("<option value=''>请选择</option>");
      $("#category_back_3").append("<option value=''>请选择</option>");
      
      $.each(list, function(){     
        $("#category_back_1").append("<option value='" + this.id + "'>" + this.categoryName + "</option>");    
      }); 
      
      if($("#categoryBackId1") && $("#categoryBackId1").val() != '') {
        $("#category_back_1").val($("#categoryBackId1").val()).change();
        $("#categoryBackId1").val("");
      }
    } else if(type == '1'){ //1-》2
      $.each(list, function(){     
        $("#category_back_2").append("<option value='" + this.id + "'>" + this.categoryName + "</option>");    
      }); 
      
      if($("#categoryBackId2") && $("#categoryBackId2").val() != '') {
        $("#category_back_2").val($("#categoryBackId2").val()).change();
        $("#categoryBackId2").val("");
      }
    } else { //2 -》3
      if(list == null || list.length == 0) {
        $("#category_back_3").hide();
      } else {
        $("#category_back_3").show();
        $.each(list, function(){     
          $("#category_back_3").append("<option value='" + this.id + "'>" + this.categoryName + "</option>");    
        });
      }
    }
    
  }, 'json');
}


function parentCategoryBack(id){
  $.post(getRootPath() + '/common/cgf/parentCategoryBack', {
    id : id
  }, function(result) {
    var type = result.map.type;
    
    if(type == '3'){// 区-》市-》省
      
      var id3 = result.map.id3;
      var list3 = result.map.list3;
      
      $("#category_back_3").empty();
      $("#category_back_3").append("<option value=''>请选择</option>");
      
      $.each(list3, function(){
        if(this.id == id3) {
          $("#category_back_3").append("<option value='" + this.id + "' selected='selected'>" + this.categoryName + "</option>");
        } else {
          $("#category_back_3").append("<option value='" + this.id + "'>" + this.categoryName + "</option>");
        }
      });
      
    } 
      var id2 = result.map.id2;
      var list2 = result.map.list2;
      var id1 = result.map.id1;
      var list1 = result.map.list1;
      
      if($("#category_back_3 option").length <=1){
        $("#category_back_3").hide();
      }
      $("#category_back_1").empty();
      $("#category_back_2").empty();
      $("#category_back_1").append("<option value=''>请选择</option>");
      $("#category_back_2").append("<option value=''>请选择</option>");
      
      $.each(list2, function(){
        if(this.id == id2) {
          $("#category_back_2").append("<option value='" + this.id + "' selected='selected'>" + this.categoryName + "</option>");
        } else {
          $("#category_back_2").append("<option value='" + this.id + "'>" + this.categoryName + "</option>");
        }
      });
      
      $.each(list1, function(){
        if(this.id == id1) {
          $("#category_back_1").append("<option value='" + this.id + "' selected='selected'>" + this.categoryName + "</option>");
        } else {
          $("#category_back_1").append("<option value='" + this.id + "'>" + this.categoryName + "</option>");
        }
      });
    
  }, 'json');
}