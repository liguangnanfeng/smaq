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
.panel-default{display: inline-block;border:0px;}
body,html{background:#EBEEF3}
.title_xw{width:100%;font-weight:bold;font-size:15px;}
.clearfix:after{content:"\20";display:block;height:0;clear:both;visibility:hidden}
.clearfix{zoom:1}
.tabBar {border-bottom: 2px solid #eee}
.tabBar font{float:left;line-height:30px;}
.tabBar span {background-color: #fff;font-size:14px;cursor: pointer;display: inline-block;float: left;font-weight: bold;height: 30px;line-height: 30px;padding: 0 15px}
.tabBar span.current{background-color: #fff;color: #2472D4;border:0px;}
.tabCon {display: none;border:0px;}
.text-overflow{display: inline-block;width:70%;line-height:25px;color:#7C7C7C;margin-right:0px;}
.text-overflow i{height:3px;width:3px;background:#7C7C7C;border-radius:50%;display:inline-block;vertical-align: middle;margin-right:5px;}
.text-overflow:hover a{color:#5889D8;text-decoration: underline;}
.text-overflow:hover i{background:#5889D8;}
.text-riqi{float:right;color:#AEAEAE;line-height:25px;}
body .div_tifa font{width:16%;}
body .div_tifa bb{width:81%}
body .iconxw{transform: rotate(-90deg);float:right;margin-right:-5px;margin-top: -3px;}
</style>
<script type="text/javascript">
$(function(){
  $.Huitab("#tab_fa .tabBar span","#tab_fa .tabCon","current","click","0")
  $(".title_danger").on("click",function(){
    if($(this).parent().hasClass("listdxz")){
      $(this).parent().removeClass("listdxz");
      $(this).find("i").html("&#xe6d5;");
    }else{
      $(this).parent().addClass("listdxz");
      $(this).find("i").html("&#xe6d7;");
    }
  });
  });
</script>
<script type="text/javascript" src="${ly }/js/H-ui.admin_v3.0/lib/laypage/1.2/laypage.js"></script>
<link type="text/css" href="${ly }/js/H-ui.admin_v3.0/lib/laypage/1.2/skin/laypage.css" rel="stylesheet" />
<script type="text/javascript">
var rows = 10;
$(function(){
  $("div[data-t='layer_page_div']").each(function() {
    var this_ = $(this);
    var p = this_.parent();
    var list = p.find(".div_znotice");
    var a_p;
    if(list.length % rows == 0) {
      a_p = list.length / rows;
    } else {
      a_p = (list.length - list.length % rows) / rows + 1;
    }
    if(a_p > 1) {
      laypage({
        cont : this_, //容器。值支持id名、原生dom对象，jquery对象,
        pages : a_p, //总页数
        //skin : '#AF0000', //加载内置皮肤，也可以直接赋值16进制颜色值，如:#c00
        groups : 8, //连续显示分页数
        jump: function(obj){ //触发分页后的回调
          var page = obj.curr;
          var i = (page - 1) * rows;
          p.find(".div_znotice:visible").hide();
          var end = rows + i;
          for(;i<list.length&&i<end;i++) {
            list.eq(i).show();
          }
        }
      });
    }
  })
})
</script>
</head>
<body>
  <div class="page-container">
    <div class="panel panel-default" style="width:80%;margin-left:10px;float:left;background:#EBEEF3">
      <div class="div_fal">
        <div class="div_tifa"><font>/ 怎样当好安全员</font><bb>&nbsp;</bb></div>
        <c:forEach items="${list }" varStatus="index" var="no">
          <c:if test="${no.type2 == 1}">
            <div class="div_znotice">
              <p><b>&nbsp;</b><a onClick="admin_show(${no.id})"  href="javascript:;"><lanlin:htmlToText length="200" value="${no.title }"/></a></p>
              <font>${no.time }</font>
            </div>
            </c:if>
          </c:forEach>
           <div data-t="layer_page_div" class="f-l mt-20"></div>
      </div>
      <div class="div_fal">
        <div class="div_tifa"><font>/ 一周事故警示</font><bb>&nbsp;</bb></div>
        <c:forEach items="${list }" varStatus="index" var="no">
          <c:if test="${no.type2 == 3}">
            <div class="div_znotice">
              <p><b>&nbsp;</b><a onClick="admin_show(${no.id})"  href="javascript:;"><lanlin:htmlToText length="200" value="${no.title }"/></a></p>
              <font>${no.time }</font>
            </div>
            </c:if>
          </c:forEach>
        <div data-t="layer_page_div" class="f-l mt-20"></div>
      </div>      
      <div class="div_fal">
        <div class="div_tifa"><font>/ 安全常识</font><bb>&nbsp;</bb></div>
        <c:forEach items="${list }" varStatus="index" var="no">
          <c:if test="${no.type2 == 5}">
            <div class="div_znotice">
              <p><b>&nbsp;</b><a onClick="admin_show(${no.id})"  href="javascript:;"><lanlin:htmlToText length="200" value="${no.title }"/></a></p>
              <font>${no.time }</font>
            </div>
            </c:if>
          </c:forEach>
        <div data-t="layer_page_div" class="f-l mt-20"></div>
      </div>
      
    </div>
    <div style="width:15%;position: fixed;right:20px;top:20px;">
    <div class="panel panel-default" style="width:100%;">
    <div class="panel-body" style="padding:8px">
      <div class="title_xw div_xinw" data-href="${ly }/fore/notice-list"  data-title="监管动态" onclick="Hui_admin_tab(this)" href="javascript:;">
        <font>监管动态</font>
        <i class="Hui-iconfont iconxw">&#xe6d5;</i>
      </div>
    </div>
  </div>
  <div class="panel panel-default" style="width:100%;margin-top:15px;">
    <div class="panel-body" style="padding:8px">
      <div class="title_xw div_xinw" data-href="${ly }/fore/notice-list1"  data-title="法律法规标准规范" onclick="Hui_admin_tab(this)" href="javascript:;">
        <font>法律法规标准规范</font>
        <i class="Hui-iconfont iconxw">&#xe6d5;</i>
      </div>
    </div>
  </div>
  <div class="panel panel-default" style="width:100%;margin-top:15px;">
    <div class="panel-body listd" style="padding:8px">
      <div class="title_xw div_xinw title_danger">
        <font>风险因素检索</font>
        <i class="Hui-iconfont iconxw">&#xe6d5;</i>
      </div>
      <div class="list_gg">
        <div class="one_notice">
          <div class="text-overflow">
            <i class="Hui-iconfont">&nbsp;</i>
            <a data-href="${ly }/html/danger.html" data-title="重大事故隐患判定标准" onclick="Hui_admin_tab(this)" href="javascript:;">重大事故隐患判定标准</a>
          </div>
        </div>
        <div class="one_notice">
          <div class="text-overflow">
            <i class="Hui-iconfont">&nbsp;</i>
            <a data-href="${ly }/fore/notice-show?id=543" data-title="重大火灾隐患判定方法" onclick="Hui_admin_tab(this)" href="javascript:;">重大火灾隐患判定方法</a>
          </div>
        </div>
        <div class="one_notice">
          <div class="text-overflow">
            <i class="Hui-iconfont">&nbsp;</i>
            <a data-href="${ly }/fore/danger/wei-list" data-title="重大危险源辨识目录" onclick="Hui_admin_tab(this)" href="javascript:;">重大危险源辨识目录</a>
          </div>
        </div>
        <div class="one_notice">
          <div class="text-overflow">
            <i class="Hui-iconfont">&nbsp;</i>
            <a data-href="${ly }/fore/danger/danger-list" data-title="危化品目录" onclick="Hui_admin_tab(this)" href="javascript:;">危化品目录</a>
          </div>
        </div>
        <div class="one_notice">
          <div class="text-overflow">
            <i class="Hui-iconfont">&nbsp;</i>
            <a data-href="${ly }/fore/danger/bao-list" data-title="易制爆危险化学品" onclick="Hui_admin_tab(this)" href="javascript:;">易制爆危险化学品</a>
          </div>
        </div>
        <div class="one_notice">
          <div class="text-overflow">
            <i class="Hui-iconfont">&nbsp;</i>
            <a data-href="${ly }/fore/danger/du-list" data-title="易制毒化学品" onclick="Hui_admin_tab(this)" href="javascript:;">易制毒化学品</a>
          </div>
        </div>
        <div class="one_notice">
          <div class="text-overflow">
            <i class="Hui-iconfont">&nbsp;</i>
            <a data-href="${ly }/fore/danger/zhi-list" data-title="职业病有害因素" onclick="Hui_admin_tab(this)" href="javascript:;">职业病有害因素</a>
          </div>
        </div>
        <div class="one_notice">
          <div class="text-overflow">
            <i class="Hui-iconfont">&nbsp;</i>
            <a data-href="${ly }/company/trouble/trouble-information" data-title="隐患排查治理标准信息库" onclick="Hui_admin_tab(this)" href="javascript:;">隐患排查治理标准</a>
          </div>
        </div>
        <div class="one_notice">
          <div class="text-overflow">
            <i class="Hui-iconfont">&nbsp;</i>
            <a data-href="${ly }/company/trouble/trouble-information-list" data-title="较大危险因素辨识与防范指导手册" onclick="Hui_admin_tab(this)" href="javascript:;">较大危险因素辨识与防范指导手册</a>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="panel panel-default" style="width:100%;margin-top:15px;">
    <div class="panel-body" style="padding:8px">
      <div class="title_xw div_xinw" data-href="http://www.hupinwang.com/info" data-title="护品网" onclick="Hui_admin_tab(this)" href="javascript:;">
        <font>获取更多信息</font>
        <i class="Hui-iconfont iconxw">&#xe6d5;</i>
      </div>
    </div>
  </div>
    </div>
  </div>

<script type="text/javascript">
/*公告详情*/
function admin_show(id){
  show_tab("公告详情", getRootPath() + "/fore/notice-show?id=" + id + "&type=2");
}

</script> 
</body>
</html>