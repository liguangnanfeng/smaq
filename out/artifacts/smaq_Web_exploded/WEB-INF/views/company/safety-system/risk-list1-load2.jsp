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
body .dis-ib{margin-right:15px;}
.wid1{width:30%}
.wid2{width:30%}
.wid3{width:30%}
.wid4{width:10%}
.wid5{width:70%}
.btn-cz{margin:auto;background:#eee;border:1px solid #ddd;padding:0 5px;height:20px;line-height:20px;border-radius:5px;margin-left:15px}
.btn-cz:hover{border:1px solid #06c;color:#06c;text-decoration: none;}
body .check-box label{margin-left:5px;}
.one_danger .list_odan{margin-left:20px}
.two_danger .list_twdan{margin-left:45px;}
.one_danger .Hui-iconfont{margin-top:2px}
.one_danger,.list_danger{display:inline-block}
.one_danger,.one_danger .list_odan,.two_danger,.two_danger .list_twdan,.list_danger{float:none;}
.check-box{width:30%;margin-bottom:15px;}
.btn-group .btn{height: 34px;line-height: 34px;padding: 0 25px;}
</style>
<script type="text/javascript">
//type 1 物理因素 2 化学因素 3 工艺 4 物料辨识
function redit(id, type) {
  var titles;
  titles = ['',"职业危害物理因素选择",'职业危害化学因素选择','职业危害高危工艺选择','物料辨识选择'];
  show_dialog(titles[type], '/company/safety-system/set-choose?id=' + id + "&type=" + type);
}
</script>
</head>
<body>
<div class="page-container">
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort tab-ndan">
        <thead>
          <tr class="text-c">
            <th>车间</th>
            <th>岗位/部位</th>
            <c:if test="${type eq 1}">
                <th>职业危害物理因素</th>
            </c:if>
            <c:if test="${type eq 2}">
                <th>职业危害化学因素</th>
            </c:if>
            <c:if test="${type eq 3}">
                <th>高危工艺</th>
            </c:if>
            <c:if test="${type eq 4}">
                <th>物料辨识</th>
            </c:if>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- 循环 -->
          <c:forEach items="${dL }" var="be3">
            <tr>
              <td class="text-c" rowspan="1">${be3.level1 }</td>
              <td class="text-c">${be3.level2 }</td>
              <c:if test="${type eq 1}">
                  <td>${fn:replace(be3.hxys, '!@#', ",") }</td>
              </c:if>
              <c:if test="${type eq 2}">
                  <td>${fn:replace(be3.material, '!@#', ",") }</td>
              </c:if>
              <c:if test="${type eq 3}">
                  <td>${fn:replace(be3.gy, '!@#', ",") }</td>
              </c:if>
              <c:if test="${type eq 4}">
                  <td>${fn:replace(be3.wlbs, '!@#', ",") }</td>
              </c:if>
              
              <td class="text-c" style="width:90px">
                <a class="btn-cz" onclick="redit(${be3.id}, ${type })" href="javascript:;" title="选择">选择</a>
              </td>
            </tr>
          </c:forEach>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
  </div>
</body>
<script type="text/javascript" src="/js/fxgk/tbl-rowspan-reset.js"></script>
</html>