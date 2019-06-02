<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<body>
  <!-- 头部 -->
  <header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
      <div class="container-fluid cl">
      	<c:if test="${userId == 35346 }">
      	<a class="logo navbar-logo f-l mr-10 hidden-xs" href="javascript:void(0)">无锡新三洲特钢集团风险分级管控   隐患排查治理智能化平台</a>
      	</c:if>
      	<c:if test="${userId != 35346 }">
        <a class="logo navbar-logo f-l mr-10 hidden-xs" href="javascript:void(0)">${name_ }风险分级管控   隐患排查治理智能化平台</a>
        </c:if>
        <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:void(0);">&#xe667;</a>
        <nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
          <ul class="cl">
            <li class="dropDown dropDown_hover">
              <a href="javascript:void(0)" class="dropDown_A">政府管理员<i class="Hui-iconfont">&#xe6d5;</i>
              </a>
              <ul class="dropDown-menu menu radius box-shadow">
                <li><a href="${ly }/villageLogout">退出</a></li>
              </ul>
            </li>
            <!-- <li id="Hui-msg">
              <a href="javascript:void(0)" title="消息">
                <span class="badge badge-danger">1</span>
                <i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i>
              </a>
            </li> -->
          </ul>
        </nav>
      </div>
    </div>
  </header>
</body>
</html>