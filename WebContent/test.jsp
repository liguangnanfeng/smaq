<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>验证demo</title>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<%@ include file="/WEB-INF/inc/header.inc"%>
<style type="text/css">
.main_css {
  margin: auto;
  width: 500px;
  margin-top: 100px;
}

.area_css {
  float: left;
  display: inline;
  width: 100%;
  height: 350px;
}

.one_css {
  float: left;
  display: inline;
  width: 100%;
  height: 50px;
  font-size: 15px;
}

.one_css label {
  float: float: left;
  display: inline;
  width: 150px;
  height: 100%;
}

.input_text_css {
  float: float: left;
  display: inline;
  width: 300px;
  height: 20px;
  border: 1px solid #CCCCCC;
}
</style>
</head>
<body>
  <div class="main_css">
    <div class="area_css">
      <form action="${ly}/back/test" method="post">
        <div class="one_css">
          <label>链接地址：</label><input type="text" id="url" name="url" class="input_text_css">
        </div>
        <div class="one_css">
          <label></label><input type="submit" value="立即访问">
        </div>
      </form>
    </div>
  </div>
</body>
</html>