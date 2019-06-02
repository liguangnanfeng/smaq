<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/WEB-INF/inc/header.inc"%>
<script type="text/javascript">
  top.location.href = getRootPath() + "/";
  function isIFrameSelf() {
    try {
      if (window.top == window) {
        return false;
      } else {
        return true;
      }
    } catch (e) {
      return true;
    }
  }
   /* function toHome() {
    var timer;
    if (isIFrameSelf()) {
      $.dialog({
        max : false,
        min : false,
        lock : true,
        content : '登录超时',
        init : function() {
          var that = this, i = 3;
          var fn = function() {
            that.title(i + '秒钟后自动跳转');
            if (!i) {
              that.close();
            }
            i--;
          };
          timer = setInterval(fn, 1000);
          fn();
        },
        close : function() {
          clearInterval(timer);
          top.location.href = getRootPath() + "/";
        }
      });
      //window.location.href=getRootPath() + "/index.jsp";
    } else {
      $.dialog({
        lock : true,
        content : '登录超时',
        init : function() {
          var that = this, i = 3;
          var fn = function() {
            that.title(i + '秒钟后自动跳转');
            if (!i) {
              that.close();
            }
            i--;
          };
          timer = setInterval(fn, 1000);
          fn();
        },
        close : function() {
          clearInterval(timer);
          top.location.href = getRootPath() + "/";
        }
      });
    }
  }  */
</script>
</head>
<!-- <body onload="toHome()"> -->
<body>
</body>
</html>
