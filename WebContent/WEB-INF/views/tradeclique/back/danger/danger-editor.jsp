<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<%@ include file="/WEB-INF/inc/back-header.inc"%>
<title>后台首页</title>
<meta name="keywords" content="后台首页">
<meta name="description" content="后台首页">
<style type="text/css">
.one_jcx,.one_gjc{width:100%;margin-top:20px;min-height:150px;}
.sel_area{width:250px}
</style>
<script type="text/javascript">
</script>
</head>
<body>
  <!-- <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>企业自查</span> 
    <span class="c-gray en">&gt;</span> <span>编辑检查内容/重大隐患</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav> -->
  <div class="page-container">
    <c:set var="x" value="${fn:split(' /基础管理-/现场管理-/高危作业-','/') }"/>
    <form class="form form-horizontal">
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">检查标准：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <select id="hy" class="sel_area">
           <option value="">请选择检查标准</option>
           <c:forEach items="${TIndustry }" var="be">
            <option value="${be.id }">${x[be.type]}${be.name }</option>
          </c:forEach>
          </select>
        </div> 
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2">检查分类：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <select id="level1" class="sel_area"></select>
          <select id="level2" class="sel_area"></select>
          <select id="level3" class="sel_area"></select>
        </div> 
      </div>
      
      <div id="load_div">
        <div class="row cl">
          <label class="form-label col-xs-4 col-sm-2">检查项：</label>
          <div class="formControls col-xs-8 col-sm-9">
            <div>请选择行业及检查分类</div>
          </div> 
        </div>
        
        <div class="row cl">
          <label class="form-label col-xs-4 col-sm-2">重大隐患关键词：</label>
          <div class="formControls col-xs-8 col-sm-9">
            <div>请选择行业及检查分类</div>
          </div> 
        </div>
      </div>
      
    </form>
  </div>
  
  <div id="module_div">
  <div class="one_jcx" data-id="">
      <textarea class="textarea txtarea_sq" ></textarea>
      <textarea class="textarea txtarea_sq" style="margin-left: 10px;"></textarea>
      <input class="btn btn-primary-outline radius ml-10 edit-jcx" type="button" value="编辑" style="display: none;"/>
      <input class="btn btn-success-outline radius ml-10 save-jcx" type="button" value="保存" />
      <input class="btn btn-danger-outline radius ml-10 del-jcx" type="button" value="删除" />
  </div>
  
  <div class="one_jcx" data-id="">
    <textarea class="textarea txtarea_sq"></textarea>
    <input class="btn btn-primary-outline radius ml-10 edit-zd" type="button" value="编辑" style="display: none;"/>
    <input class="btn btn-success-outline radius ml-10 save-zd" type="button" value="保存" />
    <input class="btn btn-danger-outline radius ml-10 del-zd" type="button" value="删除" />
  </div>
  </div>
  
</body>
<script>
var m1 = $("#module_div .one_jcx").eq(0).clone();
var m2 = $("#module_div .one_jcx").eq(1).clone();
$("#module_div").remove();
$(function() {
    //添加一列检查项
    $("#load_div").on("click", "#add_jcx", function() {
        var x = m1.clone();
        $(this).parent().append(x);
        x.find("textarea").eq(0).focus();
    })
    //检查项编辑按钮点击
    $("#load_div").on("click", ".edit-jcx", function() {
        $(this).parent().find("textarea").removeAttr("disabled");
        $(this).parent().find(":button").eq(0).hide();
        $(this).parent().find(":button").eq(1).show();
    })
    //检查项保存按钮点击
    $("#load_div").on("click", ".save-jcx", function() {
        var $this =  $(this);
        if($this.parent().find("textarea").eq(0).val() == '') {
            layer.alert("请输入检查内容");
            return false;
        }
        $.post(getRootPath() + "/back/titem-save",{
            id : $this.parent().attr("data-id"),
            content : $this.parent().find("textarea").eq(0).val(),
            reference : $this.parent().find("textarea").eq(1).val(),
            levelId : levelid
        },function(result) {
            layer.alert("保存成功");
            $this.parent().attr("data-id", result.map.id);
            $this.parent().find("textarea").attr("disabled", "disabled");
            $this.parent().find(":button").eq(1).hide();
            $this.parent().find(":button").eq(0).show();
        })
    })
    
    //检查项删除按钮点击
    $("#load_div").on("click", ".del-jcx", function() {
        var $this =  $(this);
        layer.confirm("确定删除该检查内容，删除后将无法恢复?", function(i) {
            layer.close(i);
            var id = $this.parent().attr("data-id");
            if(id == '') {
                $this.parent().remove();
            } else {
                $.post(getRootPath() + "/back/titem-del",{
                    id : id,
                },function(result) {
                    $this.parent().remove();
                })
            }
        })
        
    })
    
    //添加一行关键词
    $("#load_div").on("click", "#add_zd", function() {
        var x = m2.clone();
        $(this).parent().append(x);
        x.find("textarea").eq(0).focus();
    })
    //关键词编辑按钮点击
    $("#load_div").on("click", ".edit-zd", function() {
        $(this).parent().find("textarea").removeAttr("disabled");
        $(this).parent().find("input[type='button']").eq(0).hide();
        $(this).parent().find("input[type='button']").eq(1).show();
    })
    //关键词保存按钮点击
    $("#load_div").on("click", ".save-zd", function() {
        var $this =  $(this);
        if($this.parent().find("textarea").eq(0).val() == '') {
            layer.alert("请输入关键词");
            return false;
        }
        $.post(getRootPath() + "/back/titems-save",{
            id : $this.parent().attr("data-id"),
            keywords : $this.parent().find("textarea").eq(0).val(),
            levelid : levelid
        },function(result) {
            layer.alert("保存成功");
            $this.parent().attr("data-id", result.map.id);
            $this.parent().find("textarea").attr("disabled", "disabled");
            $this.parent().find("input[type='button']").eq(1).hide();
            $this.parent().find("input[type='button']").eq(0).show();
        })
    })
    
    //关键词删除按钮点击
    $("#load_div").on("click", ".del-zd", function() {
        var $this =  $(this);
        layer.confirm("确定删除该检查内容，删除后将无法恢复?", function(i) {
            layer.close(i);
            var id = $this.parent().attr("data-id");
            if(id == '') {
                $this.parent().remove();
            } else {
                $.post(getRootPath() + "/back/titems-del",{
                    id : id,
                },function(result) {
                    $this.parent().remove();
                })
            }
        })
        
    })
})

var levList;
$("#level1").empty().append('<option value="">请选择检查分类1</option>');
$("#level2").empty().append('<option value="">请选择检查分类2</option>');
$("#level3").empty().append('<option value="">请选择检查分类3</option>');
 $(function(){
  $("#hy").change(function() {
    $("#level1").empty().append('<option value="">请选择检查分类1</option>');
    $("#level2").empty().append('<option value="">请选择检查分类2</option>');
    $("#level3").empty().append('<option value="">请选择检查分类3</option>');
     if($(this).val() != '') {
      $.post(getRootPath() + "/back/tlevel-get", {
        industryId : $(this).val()
      },function(result) {
        levList = result.map.list;
        init();
      })
    } 
  }); 
  
  $("#level1").change(function() {
    $("#level2").empty().append('<option value="">请选择检查分类2</option>');
    $("#level3").empty().append('<option value="">请选择检查分类3</option>');
    if($(this).val() != '') {
      var v1 = $(this).val();
      var l2 = new Array();
      $.each(levList, function(i, item) {
        var level1 = item.level1;
        var level2 = item.level2;
        if(!l2.contains(level2) && level1 == v1) {
          $("#level2").append('<option data-v="' + item.id + '">'+level2+'</option>');
          l2.push(level2);
        }
      })
    } 
  })
  
  $("#level3").change(function() {
    /* if(levList[0].level3 == '') { */
        load($(this).find("option:selected").attr("data-v"));
    /* } */
  })
  
  $("#level2").change(function() {
    /* if(levList[0].level3 == '') {//只有两级分类
        load($(this).find("option:selected").attr("data-v"));
    } else { */
      $("#level3").empty().append('<option value="">请选择检查分类3</option>');
      if($(this).val() != '') {
        var v1 = $("#level1").val();
        var v2 = $(this).val();
        var l3 = new Array();
        $.each(levList, function(i, item) {
          var level1 = item.level1;
          var level2 = item.level2;
          var level3 = item.level3;
          if(level3 == '') {
              level3 = level2;
          }
          if(!l3.contains(level3) && level1 == v1 && level2 == v2) {
            $("#level3").append('<option data-v="' + item.id + '">'+level3+'</option>');
            l3.push(level3);
          }
        })
      } 
    /* } */
  })
 })
 
 function load(levelid_) {
     window.levelid = levelid_
     $("#load_div").load(getRootPath() + "/back/tlevelitem #load_",{
         levelid : levelid
     },function(result) {
         
     })
 }
 
 function init() {
   var l1 = new Array();
   /* if(levList[0].level3 == '') {
     $("#level3").hide();
   } else {
     $("#level3").show();
   } */
   $.each(levList, function(i, item) {
     var level1 = item.level1;
     if(!l1.contains(level1)) {
       $("#level1").append('<option data-v="' + item.id + '">'+level1+'</option>');
       l1.push(level1);
     }
   })
 }
</script>
</html>