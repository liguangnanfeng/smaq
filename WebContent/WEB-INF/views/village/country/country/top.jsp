<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<body>
  <!-- 头部 -->
  <header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
      <div class="container-fluid cl">
        <a class="logo navbar-logo f-l mr-10 hidden-xs" href="javascript:void(0)">${name_ }风险分级管控   隐患排查治理智能化平台</a>
        <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:void(0);">&#xe667;</a>
        <nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
          <ul class="cl">
            <li><a data-href="${ly }/village/company/company-list" onclick="Hui_admin_tab(this)" data-title="全部企业" href="javascript:void(0)">企业搜索</a></li>
            <li class="dropDown dropDown_hover">
              <a href="javascript:void(0)" class="dropDown_A">${session_user.userName }<i class="Hui-iconfont">&#xe6d5;</i>
              </a>
              <ul class="dropDown-menu menu radius box-shadow">
                <li><a href="${ly }/villageLogout">退出</a></li>
              </ul>
            </li>
          </ul>
        </nav>
      </div>
    </div>
  </header>
</body>
</html>