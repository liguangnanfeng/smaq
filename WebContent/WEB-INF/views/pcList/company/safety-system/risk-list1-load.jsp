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
    .radio-lx{display: inline-block;cursor: pointer;height: 50px;font-size: 14px;line-height: 50px;margin-bottom: 10px;padding: 0 35px;margin-right: 30px;background: #f5f5f5;border-radius: 5px;}
    .radio-lxxz {background: #D9EDF7;color: #31708F;}
    .l2{display:none;}
    .bxt{margin-right:5px;}
  </style>
  <script type="text/javascript">
      $(function() {
          $(".three_danger").each(function(){
              var s=$(this).find("label").html();
              var h=$(this).prev().find("label").html();
              if (s==h){
                  $(this).find(".three_dtitle").hide();
                  $(this).find(".bxt").show();
                  $(this).prev().find(".bxt").show();
              }
          })
          /* 弹窗树 */
          /* 弹窗树二级选中 */
          $(".two_dtitle .Hui-iconfont").on("click",function(){
              if($(this).parent().parent().hasClass("two_dangerxz")){
                  $(this).parent().parent().removeClass("two_dangerxz");
                  $(this).parent().parent().find(".Hui-iconfont").html("&#xe604;");
                  $(this).closest(".two_danger").find(".three_danger").removeClass("three_dangerxz");
              }else{
                  $(this).parent().parent().addClass("two_dangerxz");
                  $(this).html("&#xe631;");
              }
          });
          /* 弹窗树一级选中 */
          $(".one_dtitle .Hui-iconfont").on("click",function(){
              if($(this).parent().parent().hasClass("one_dangerxz")){
                  $(this).parent().parent().removeClass("one_dangerxz");
                  $(this).parent().parent().find(".Hui-iconfont").html("&#xe604;");
                  $(this).closest(".one_danger").find(".three_danger").removeClass("three_dangerxz");
                  $(this).closest(".one_danger").find(".two_danger").removeClass("two_dangerxz");
              }else{
                  $(this).parent().parent().addClass("one_dangerxz");
                  $(this).html("&#xe631;");
              }
          });

          //选择事件
          $(":checkbox").click(function() {
              var l = $(this).data("l");
              if(l == 1) {
                  $(this).closest(".one_danger").find(":checkbox").prop("checked", $(this).is(":checked"));
              } else if(l == 2) {
                  $(this).closest(".two_danger").find(":checkbox").prop("checked", $(this).is(":checked"));
                  var t = $(this).closest(".one_danger");
                  t.find(":checkbox[data-l='1']").prop("checked",false).prop("checked", t.find(":checkbox:checked").length > 0);
              } else if(l == 3) {
                  $(this).closest(".three_danger").find(":checkbox").prop("checked", $(this).is(":checked"));
                  var t = $(this).closest(".one_danger");
                  var t2 = $(this).closest(".two_danger");
                  t2.find(":checkbox[data-l='2']").prop("checked",false).prop("checked", t2.find(":checkbox:checked").length > 0);
                  t.find(":checkbox[data-l='1']").prop("checked",false).prop("checked", t.find(":checkbox:checked").length > 0);
              } else if(l == 4) {
                  var t = $(this).closest(".one_danger");
                  var t2 = $(this).closest(".two_danger");
                  var t3 = $(this).closest(".three_danger");
                  t3.find(":checkbox[data-l='3']").prop("checked",false).prop("checked", t3.find(":checkbox:checked").length > 0);
                  t2.find(":checkbox[data-l='2']").prop("checked",false).prop("checked", t2.find(":checkbox:checked").length > 0);
                  t.find(":checkbox[data-l='1']").prop("checked",false).prop("checked", t.find(":checkbox:checked").length > 0);
              }
          })
      });

  </script>
</head>
<body>
<div class="page-container">
  <div class="modal-body">
    <!-- 判断化工企业显示 -->
    <%-- <c:if test="${company.industry eq '化工企业（危化生产、使用）'}">
      <div class="radio-lx <c:if test="${industry eq '化工企业（危险化学品生产、经营、使用）'}"> radio-lxxz</c:if>" onclick="location.href='/company/safety-system/risk-list-load?industry=化工企业（危险化学品生产、经营、使用）'" style="margin-left:5%">化工企业</div>
      <div class="radio-lx <c:if test="${industry eq '加油站'}"> radio-lxxz</c:if>" onclick="location.href='/company/safety-system/risk-list-load?industry=加油站'">加油站</div>
    </c:if> --%>
    <!-- 判断结束 -->
    <div class="list_danger l1">
      <!-- 循环一级 -->
      <c:forEach items="${list }" var="be">
        <div class="one_danger one_dangerxz">
          <div class="one_dtitle">
            <i class="Hui-iconfont">&#xe631;</i>
            <div class="check-box">
              <input type="checkbox" data-l="1" name="check">
              <label>${be.key }</label>
            </div>
          </div>
          <div class="list_odan">
            <!-- 循环二级 -->
            <c:forEach items="${be.value }" var="be2">
              <div class="two_danger">
                <div class="two_dtitle">
                  <i class="Hui-iconfont">&#xe604;</i>
                  <div class="check-box">
                    <input type="checkbox" data-l="2" name="check">
                    <label>${be2 }</label>
                  </div>
                </div>
                <div class="list_twdan">
                  <!-- 循环三级 -->
                  <c:set var="l3" value=""/>
                  <c:set var="l3_" value="1"/>
                  <c:forEach items="${dL }" var="be3">
                    <c:set var="key_">${be3.level1}_${be3.level2}_${be3.level3}</c:set>
                    <c:if test="${be3.level1 eq be.key and be3.level2 eq be2 and !lanlin:constains(l3,key_,',')}">
                      <c:set var="l3">${l3 },${key_ }</c:set>
                      <div class="three_danger">
                        <div class="three_dtitle">
                          <div class="check-box">
                            <input type="checkbox" data-l="3" name="check" value="${be3.id }" class="xt">
                            <label>${be3.level3 }</label>
                          </div>
                        </div>
                        <!-- 判断三级名称是否相同，循环 -->
                        <c:forEach items="${dL }" var="be4">
                          <c:if test="${be4.level1 eq be.key and be4.level2 eq be2 and be4.level3 eq be3.level3}">
                            <p class="p-three">
                              <!-- 判断三级相同，复选框显示 -->
                              <input type="checkbox" data-l="4" name="check" value="${be4.id }" class="bxt">${be4.measures }
                            </p>
                          </c:if>
                        </c:forEach>
                        <!-- 循环结束 -->
                      </div>
                    </c:if>
                  </c:forEach>
                  <!-- 三级结束 -->
                </div>
              </div>
            </c:forEach>
            <!-- 二级结束 -->
          </div>
        </div>
      </c:forEach>
      <!-- 一级结束  -->
    </div>
  </div>

  <div class="mt-20 f-l mb-40" style="width:100%;text-align:center;">
    <button onClick="save_1()" class="btn btn-primary radius mt-20" type="button" style="padding: 0 70px;">
      <i class="Hui-iconfont mr-10">&#xe632;</i>保存
    </button>
  </div>
</div>
</body>
<script type="text/javascript">
    var depId = '${depId}';
    function save_1() {
        var l = $(":checkbox:checked[data-l='4']");
        if(l.length == 0) {
            layer.alert("请至少选择一个风险。");
            return false;
        }
        var ids = new Array();
        $.each(l, function(i, item) {
            ids.push($(this).val());
        })
        ids = ids.join(",");
        var i = layer.load();
        $.post("/company/safety-system/aCompanyManual-save1", {
            ids : ids,
            depId : depId
        },function(result) {
            layer.close(i);
            layer.alert("保存成功", {}, function(i) {
                layer.close(i);
                //parent.close_dialog(function() {
                //parent.show_dialog('添加检查表','/company/model-add?type=3&flag=1&ids='+ids+'&depid='+depId);
                //})
                //alert("depId:"+depId);
                //parent.window.location.href = '/company/model-adds?type=9&flag=1&ids='+ids+'&depId='+depId;//1:综合检查表；2：定期检查表；3：整改复查 ；9：检查表 zhangcl 2018.10.27

                parent.reload_();
                //alert("11");
            })
        })
    }
</script>
</html>