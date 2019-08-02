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
<script type="text/javascript">
  $(function(){
    var loginType = '${loginType}';
    /* if(loginType == '1'){
      layer.alert("登录状态失效，请重新登录！",function(ind){
        layer.close(ind);
        top.location.href = getRootPath() + "/backLogout";
      });
    } */
    
    
  });
</script>
</head>
<body>
  <%@ include file="top.jsp"%>
  <%@ include file="left.jsp"%>
  <!-- 中间主页 -->
  <div class="dislpayArrow hidden-xs">
    <a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a>
  </div>
  <section class="Hui-article-box">
    <div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
      <div class="Hui-tabNav-wp">
        <ul id="min_title_list" class="acrossTab cl">
          <li class="active">
            <span title="首页" data-href="${ly }/back/welcome">首页</span>
            <em></em>
          </li>
        </ul>
      </div>
      <div class="Hui-tabNav-more btn-group">
        <a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;">
          <i class="Hui-iconfont">&#xe6d4;</i>
        </a>
        <a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;">
          <i class="Hui-iconfont">&#xe6d7;</i>
        </a>
      </div>
    </div>
    <div id="iframe_box" class="Hui-article">
      <div class="show_iframe">
        <div style="display:none" class="loading"></div>
        <iframe scrolling="yes" frameborder="0" src="welcome"></iframe>
      </div>
    </div>
  </section>
  <div class="contextMenu" id="Huiadminmenu">
    <ul>
      <li id="closethis">关闭当前</li>
      <li id="closeothers">关闭其他</li>
      <li id="closeall">关闭全部</li>
    </ul>
  </div>
</body>
</html>