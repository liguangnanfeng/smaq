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
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style type="text/css">
body .three_danger .list_thdan,body  .two_danger .list_twdan,body .one_danger .list_odan{width:94%;margin-left:50px;}
</style>
<script type="text/javascript">
var levelL = ${levelL};
</script> 
<script type="text/javascript" src="${ly }/js/ll-tree/company-ll-tree.js"></script>
<script type="text/javascript">
var levelIds = '${levelIds}';
$(function(){
  if(levelIds != '') {
    $.each(levelIds.split(","), function(i, item) {
      $(":checkbox[data-id='"+item+"']").click().attr("checked", "checked");
    })
  }
})
</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>隐患排查治理系统</span> 
    <span class="c-gray en">&gt;</span> <span>隐患排查治理标准</span> 
    <span class="c-gray en">&gt;</span> <span>${tIndustry.name }隐患排查治理</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="list_danger">
      <!-- 包含两级 -->
      <!-- <div class="one_danger">
        <div class="one_dtitle">
          <i class="Hui-iconfont">&#xe604;</i>
          <div class="check-box">
            <input type="checkbox" name="check">
            <label>一级分类</label>
          </div>
        </div>
        <div class="list_odan">
          <div class="two_danger">
            <div class="two_dtitle">
              <div class="check-box">
                <input type="checkbox" name="check">
                <label>二级分类</label>
              </div>
            </div>
          </div>
          <div class="two_danger">
            <div class="two_dtitle">
              <div class="check-box">
                <input type="checkbox" name="check">
                <label>二级分类</label>
              </div>
            </div>
          </div>
        </div>
      </div>
      包含三级
      <div class="one_danger">
        <div class="one_dtitle">
          <i class="Hui-iconfont">&#xe604;</i>
          <div class="check-box">
            <input type="checkbox" name="check">
            <label>一级分类</label>
          </div>
        </div>
        <div class="list_odan">
          <div class="two_danger">
            <div class="two_dtitle">
              <i class="Hui-iconfont">&#xe604;</i>
              <div class="check-box">
                <input type="checkbox" name="check">
                <label>二级分类</label>
              </div>
            </div>
            <div class="list_twdan">
              <div class="three_danger">
                <div class="three_dtitle">
                  <div class="check-box">
                    <input type="checkbox" name="check">
                    <label>三级分类</label>
                  </div>
                </div>
              </div>
              <div class="three_danger">
                <div class="three_dtitle">
                  <div class="check-box">
                    <input type="checkbox" name="check">
                    <label>三级分类</label>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="two_danger">
            <div class="two_dtitle">
              <div class="check-box">
                <input type="checkbox" name="check">
                <label>二级分类</label>
              </div>
            </div>
          </div>
        </div>
      </div> -->
    </div>
    <div class="row cl mt-20" style="float:left;">
      <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
        <button onClick="article_save_submit();" class="btn btn-primary radius" type="button" style="padding: 0 70px;"><i class="Hui-iconfont">&#xe632;</i> 下一步</button>
      </div>
    </div>
  </div>

</body>
<script type="text/javascript">
var type = '${tIndustry.type}';
var name = '${tIndustry.name}';
var tid = '${tIndustry.id}';
function article_save_submit() {
  var list;
  var ids = '';
  list= $(":checkbox[data-level='2']:checked");
  $.each(list, function(){
    if($(this).attr("data-id")) {
      var id = $(this).attr("data-id");
      ids = (ids == '' ? id : ids + "," + id);
    }
  })
  list= $(":checkbox[data-level='3']:checked");
  $.each(list, function(){
    var id = $(this).attr("data-id");
    ids = (ids == '' ? id : ids + "," + id);
  })
  if(ids == '') {
    layer.alert("请选择本次检查的项目");
    return false;
  }
  $.post(getRootPath() + "/company/t-check-save",{
    title : name + "隐患排查治理",
    type : type,
    tindustryId : tid,
    levelIds : ids
  },function(result) {
    if(result.status == '1') {
      layer.alert(result.map.message);
    } else {
        show_tab("隐患信息录入_" + result.map.id, getRootPath() + "/company/check-next?id=" + result.map.id);
      /* parent.close_tab(function(){
        show_tab("隐患信息录入_" + result.map.id, getRootPath() + "/company/check-next?id=" + result.map.id);
      }) */
    }
  })
}
</script>
</html>