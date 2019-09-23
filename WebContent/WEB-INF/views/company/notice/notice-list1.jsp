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
.tabBar {border-bottom: 0px}
.tabBar font{float:left;line-height:30px;}
.tabBar span {background-color: #fff;font-size:14px;font-weight:normal;color:#666;cursor: pointer;display: inline-block;float: left;height: 30px;line-height: 30px;padding: 0 15px;margin-right:15px;}
.tabBar span.current{background-color: #fff;color: #2472D4;border:0px;}
.tabCon {display: none;border:0px;}
.text-overflow{display: inline-block;width:70%;line-height:25px;color:#7C7C7C;margin-right:0px;}
.text-overflow i{height:3px;width:3px;background:#7C7C7C;border-radius:50%;display:inline-block;vertical-align: middle;margin-right:5px;}
.text-overflow:hover a{color:#5889D8;text-decoration: underline;}
.text-overflow:hover i{background:#5889D8;}
.text-riqi{float:right;color:#AEAEAE;line-height:25px;}
body .iconxw{transform: rotate(-90deg);float:right;margin-right:-5px;margin-top: -3px;}
</style>
<script type="text/javascript" src="${ly }/js/H-ui.admin_v3.0/lib/laypage/1.2/laypage.js"></script>
<link type="text/css" href="${ly }/js/H-ui.admin_v3.0/lib/laypage/1.2/skin/laypage.css" rel="stylesheet" />
<script type="text/javascript">
$(function(){
  $.Huitab("#tab_fa .tabBar span","#tab_fa .tabCon","current","click","0");
  $.Huitab("#tab_fa2 .tabBar span","#tab_fa2 .tabCon","current","click","0");
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
        <div class="div_tifa"><font>/ 法律</font><bb>&nbsp;</bb></div>
        <c:forEach items="${list }" varStatus="index" var="no">
          <c:if test="${no.type2 == 10 }">
            <div class="div_znotice">
              <p><b>&nbsp;</b><a onClick="admin_show(${no.id},1)"  href="javascript:;"><lanlin:htmlToText length="200" value="${no.title }"/></a></p>
              <font>${no.time }</font>
            </div>
            </c:if>
        </c:forEach>
        <div data-t="layer_page_div" class="f-l mt-20"></div>
      </div>
      
      <div class="div_fal">
        <div class="div_tifa"><font>/ 法规</font><bb>&nbsp;</bb></div>
        <div id="tab_fa" class="HuiTab">
          <div class="tabBar clearfix"><span>危化</span><span>工贸</span><span>消防</span><span>港口</span><span>应急</span><span>职卫</span></div>
            <div class="tabCon">
              <c:forEach items="${list }" varStatus="index" var="no">
                <c:if test="${no.type2 == 21}">
                  <div class="div_znotice">
                    <p><b>&nbsp;</b><a onClick="admin_show(${no.id},2)"  href="javascript:;">
                    <lanlin:htmlToText length="200" value="${no.title }"/></a></p>
                    <font>${no.time }</font>
                  </div>
                </c:if>
              </c:forEach>
              <div data-t="layer_page_div" class="f-l mt-20"></div>
            </div>
            <div class="tabCon">
              <c:forEach items="${list }" varStatus="index" var="no">
                <c:if test="${no.type2 == 22}">
                  <div class="div_znotice">
                    <p><b>&nbsp;</b><a onClick="admin_show(${no.id},2)"  href="javascript:;">
                    <lanlin:htmlToText length="200" value="${no.title }"/></a></p>
                    <font>${no.time }</font>
                  </div>
                </c:if>
              </c:forEach>
              <div data-t="layer_page_div" class="f-l mt-20"></div>
            </div>
            <div class="tabCon">
              <c:forEach items="${list }" varStatus="index" var="no">
                <c:if test="${no.type2 == 24}">
                  <div class="div_znotice">
                    <p><b>&nbsp;</b><a onClick="admin_show(${no.id},2)"  href="javascript:;">
                    <lanlin:htmlToText length="200" value="${no.title }"/></a></p>
                    <font>${no.time }</font>
                  </div>
                </c:if>
              </c:forEach>
              <div data-t="layer_page_div" class="f-l mt-20"></div>
            </div>
            <div class="tabCon">
              <c:forEach items="${list }" varStatus="index" var="no">
                <c:if test="${no.type2 == 25}">
                  <div class="div_znotice">
                    <p><b>&nbsp;</b><a onClick="admin_show(${no.id},2)"  href="javascript:;">
                    <lanlin:htmlToText length="200" value="${no.title }"/></a></p>
                    <font>${no.time }</font>
                  </div>
                </c:if>
              </c:forEach>
              <div data-t="layer_page_div" class="f-l mt-20"></div>
            </div>
            <div class="tabCon">
              <c:forEach items="${list }" varStatus="index" var="no">
                <c:if test="${no.type2 == 26}">
                  <div class="div_znotice">
                    <p><b>&nbsp;</b><a onClick="admin_show(${no.id},2)"  href="javascript:;">
                    <lanlin:htmlToText length="200" value="${no.title }"/></a></p>
                    <font>${no.time }</font>
                  </div>
                </c:if>
              </c:forEach>
              <div data-t="layer_page_div" class="f-l mt-20"></div>
            </div>
            <div class="tabCon">
              <c:forEach items="${list }" varStatus="index" var="no">
                <c:if test="${no.type2 == 27}">
                  <div class="div_znotice">
                    <p><b>&nbsp;</b><a onClick="admin_show(${no.id},2)"  href="javascript:;">
                    <lanlin:htmlToText length="200" value="${no.title }"/></a></p>
                    <font>${no.time }</font>
                  </div>
                </c:if>
              </c:forEach>
              <div data-t="layer_page_div" class="f-l mt-20"></div>
            </div>
        </div>
      </div>
      <div class="div_fal">
        <div class="div_tifa"><font>/ 标准规范</font><bb>&nbsp;</bb></div>
        <div id="tab_fa2" class="HuiTab">
          <div class="tabBar clearfix"><span>危化</span><span>工贸</span><!-- <span>煤矿</span> --><span>消防</span><span>港口</span><span>应急</span><span>职卫</span></div>
            <div class="tabCon">
              <c:forEach items="${list }" varStatus="index" var="no">
                <c:if test="${no.type2 == 31}">
                  <div class="div_znotice">
                    <p><b>&nbsp;</b><a onClick="admin_show(${no.id},3)"  href="javascript:;">
                    <lanlin:htmlToText length="200" value="${no.title }"/></a></p>
                    <font>${no.time }</font>
                  </div>
                </c:if>
              </c:forEach>
              <div data-t="layer_page_div" class="f-l mt-20"></div>
            </div>
            <div class="tabCon">
              <c:forEach items="${list }" varStatus="index" var="no">
                <c:if test="${no.type2 == 32}">
                  <div class="div_znotice">
                    <p><b>&nbsp;</b><a onClick="admin_show(${no.id},3)"  href="javascript:;">
                    <lanlin:htmlToText length="200" value="${no.title }"/></a></p>
                    <font>${no.time }</font>
                  </div>
                </c:if>
              </c:forEach>
              <div data-t="layer_page_div" class="f-l mt-20"></div>
            </div>
            <div class="tabCon">
              <c:forEach items="${list }" varStatus="index" var="no">
                <c:if test="${no.type2 == 34}">
                  <div class="div_znotice">
                    <p><b>&nbsp;</b><a onClick="admin_show(${no.id},3)"  href="javascript:;">
                    <lanlin:htmlToText length="200" value="${no.title }"/></a></p>
                    <font>${no.time }</font>
                  </div>
                </c:if>
              </c:forEach>
              <div data-t="layer_page_div" class="f-l mt-20"></div>
            </div>
            <div class="tabCon">
              <c:forEach items="${list }" varStatus="index" var="no">
                <c:if test="${no.type2 == 35}">
                  <div class="div_znotice">
                    <p><b>&nbsp;</b><a onClick="admin_show(${no.id},3)"  href="javascript:;">
                    <lanlin:htmlToText length="200" value="${no.title }"/></a></p>
                    <font>${no.time }</font>
                  </div>
                </c:if>
              </c:forEach>
              <div data-t="layer_page_div" class="f-l mt-20"></div>
            </div>
            <div class="tabCon">
              <c:forEach items="${list }" varStatus="index" var="no">
                <c:if test="${no.type2 == 36}">
                  <div class="div_znotice">
                    <p><b>&nbsp;</b><a onClick="admin_show(${no.id},3)"  href="javascript:;">
                    <lanlin:htmlToText length="200" value="${no.title }"/></a></p>
                    <font>${no.time }</font>
                  </div>
                </c:if>
              </c:forEach>
              <div data-t="layer_page_div" class="f-l mt-20"></div>
            </div>
            <div class="tabCon">
              <c:forEach items="${list }" varStatus="index" var="no">
                <c:if test="${no.type2 == 37}">
                  <div class="div_znotice">
                    <p><b>&nbsp;</b><a onClick="admin_show(${no.id},3)"  href="javascript:;">
                    <lanlin:htmlToText length="200" value="${no.title }"/></a></p>
                    <font>${no.time }</font>
                  </div>
                </c:if>
              </c:forEach>
              <div data-t="layer_page_div" class="f-l mt-20"></div>
            </div>
        </div>
      </div>
    </div>
    <div style="width:15%;position: fixed;right:20px;top:20px;">
    <c:if test="${session_user.userType!=6}">
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
      <a class="title_xw div_xinw" data-href="${ly }/fore/notice-list2"  data-title="安全员之家" onclick="Hui_admin_tab(this)" href="javascript:;">
        <font>安全员之家</font>
        <i class="Hui-iconfont iconxw">&#xe6d5;</i>
      </a>
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
<%--  <div class="panel panel-default" style="width:100%;margin-top:15px;">--%>
<%--    <div class="panel-body" style="padding:8px">--%>
<%--      <a class="title_xw div_xinw" data-href="http://www.hupinwang.com/info" data-title="护品网" onclick="Hui_admin_tab(this)" href="javascript:;">--%>
<%--        <font>获取更多信息</font>--%>
<%--        <i class="Hui-iconfont iconxw">&#xe6d5;</i>--%>
<%--      </a>--%>
<%--    </div>--%>
<%--  </div>--%>
  </c:if>
  <c:if test="${session_user.userType==6}">
    <div class="panel panel-default" style="width:100%;">
      <div class="panel-body" style="padding:8px">
        <a class="title_xw div_xinw" data-href="http://safety.wuxi.gov.cn/" data-title="上级数据分享"  onclick="Hui_admin_tab(this)" href="javascript:;">
          <font>上级数据分享</font>
          <i class="Hui-iconfont iconxw">&#xe6d5;</i>
        </a>
      </div>
    </div>
    <div class="panel panel-default" style="width:100%;margin-top:15px;">
    <div class="panel-body" style="padding:8px">
      <a class="title_xw div_xinw" data-href="http://safety365.com.cn/html/person.html" data-title="安委会成员数据分享"  onclick="Hui_admin_tab(this)" href="javascript:;">
        <font>安委会成员数据分享</font>
        <i class="Hui-iconfont iconxw">&#xe6d5;</i>
      </a>
    </div>
  </div>
  </c:if>
    </div>
  </div>

<script type="text/javascript">
/*公告详情*/
function admin_show(id,code){
  if(code==1){
  show_tab("法律", getRootPath() + "/fore/notice-show?id=" + id + "&type=3");
  }
  if(code==2){
  show_tab("法规", getRootPath() + "/fore/notice-show?id=" + id + "&type=3");
  }
  if(code==3){
  show_tab("标准规范", getRootPath() + "/fore/notice-show?id=" + id + "&type=3");
  }
}

</script> 
</body>
</html>