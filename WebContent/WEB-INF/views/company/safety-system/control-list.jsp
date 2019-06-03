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
  <%-- <%@ include file="/WEB-INF/inc/back-header.inc"%> --%>
  <%@ include file="/WEB-INF/inc/print.inc"%>
  <title>风险分级管控   隐患排查治理智能化平台</title>
  <script src="${ly }/js/jquery-migrate-1.2.1.min.js"></script>
  <script src="${ly }/js/jquery.jqprint-0.3.js"></script>
  <script type="text/javascript" src="${ly}/js/H-ui.admin_v3.0/lib/layer/2.4/layer.js"></script>
  <meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
  <meta name="description" content="风险分级管控   隐患排查治理智能化平台">
  <style type="text/css">
    body .dis-ib{margin-right:15px;}
    .col-b,.col-a,.col-c,.col-d{margin:0;padding:0 15px;height:25px;line-height:25px;}
  </style>
  <script type="text/javascript">
    function copy_(fjgkfzr,gkcs,gkzt,level2,level,factors,flag,uid) {


    var data = {
    "level":level,
    "factors":factors,
    "id" : null,
    "ids": '',
    "flag":flag,
    "uid":uid
    }

    console.log(data);
    $.post(getRootPath() + "/company/safety-system/aCompanyManual-save", data, function(result) {
    location.reload();
    })
    }
      /* 弹窗管控信息添加 */
      function edit(id, obj){
          window.id = id;
          var p = $(obj).parent();
          $("#gkzt").val(p.find("input[name='gkzt']").val());
          $("#gkcs").val(p.find("input[name='gkcs']").val());
          $("#fjgkfzr").val(p.find("input[name='fjgkfzr']").val());
          $("#buwei").val(p.find("input[name='buwei']").val());
          $("#win-add").modal("show");
      }

      function chosem(){
          $("#win-add2").modal("show");
      }

      function chosem2(){
          $("#win-add3").modal("show");
      }

      function chosem3() {

          $("#win-add4").modal("show");
          var name = $("#gkzt").val();
          var id = $("#gkztIds").val();

          $.ajax({ //post也可
              type: "POST",
              url: getRootPath() + "/company/safety-system/control-list-one",
              data: {name: name, pid:id},
              dataType: 'json',
              success: function (result) {
                  var dataObj = result,
                      con = "";
                  $.each(dataObj, function (index, item) {
                      con += "<tr class=\"text-c\">";
                      con += "<td style=\"width:30px;\" align=\"center\" valign=\"middle\"><input  type=\"radio\" name=\"radio-1\" value='" + item.name + "'></td>";
                      con += "<td align=\"center\" valign=\"middle\">"+item.name+"</td>";
                      con += "</tr>";
                  });
                  $("#test").html(con);
              }
          });
      }


    /* 弹窗管控信息添加 */
    function edits(id, obj){
        window.id = id;
        var p = $(obj).parent();
        $("#gkzt").val(p.find("input[name='gkzt']").val());
        $("#gkcs").val(p.find("input[name='gkcs']").val());
        $("#fjgkfzr").val(p.find("input[name='fjgkfzr']").val().split("-")[0]);
        $("#buwei").val(p.find("input[name='buwei']").val());
        $("#win-add").modal("show");
    }

    function chosem(){
        $("#win-add2").modal("show");
    }

    function chosem2(){
        $("#win-add3").modal("show");
    }

    function chosem3() {

        $("#win-add4").modal("show");
        var name = $("#gkzt").val();
        var id = $("#gkztIds").val();

        $.ajax({ //post也可
            type: "POST",
            url: getRootPath() + "/company/safety-system/control-list-one",
            data: {name: name, pid:id},
            dataType: 'json',
            success: function (result) {
                var dataObj = result,
                    con = "";
                $.each(dataObj, function (index, item) {
                    con += "<tr class=\"text-c\">";
                    con += "<td style=\"width:30px;\" align=\"center\" valign=\"middle\"><input  type=\"radio\" name=\"radio-1\" value='" + item.name + "'></td>";
                    con += "<td align=\"center\" valign=\"middle\">"+item.name+"</td>";
                    con += "</tr>";
                });
                $("#test").html(con);
            }
        });
    }



      function per_chooses() {

          var list = $("#win-add4 :checked");
          var v = [];
          list.each(function() {
              v.push($(this).val());
          })
          $("#buwei").val(v.join(","));
          $("#win-add4").modal("hide");

      }

      function save_() {
          var obj = {
              "fjgkfzr" : $("#fjgkfzr").val(),
              "gkcs" : $("#gkcs").val(),
              "gkzt" : $("#gkzt").val(),
              "level2" : $("#buwei").val(),
              "id" : id,
              "ids": $("#gkztIds").val(),
              "dianhuas" : $("#dianhuas").val(),
          }
          if(obj.gkzt == '') {
              $("#gkzt").focus();
              return false;
          }
          if(obj.fjgkfzr == '') {
              $("#fjgkfzr").focus();
              return false;
          }
          if(obj.gkcs == '') {
              $("#gkcs").focus();
              return false;
          }
          if(obj.level2 == '') {
              $("#level2").focus();
              return false;
          }
          if(obj.dianhuas == '') {
              $("#dianhuas").focus();
              return false;
          }
          var i = layer.load();
          $.post(getRootPath() + "/company/safety-system/aCompanyManual-save", obj, function(result) {
              layer.close(i);
              location.reload();
          })
      }
      function pr_() {
          $(".div-pcz").hide();
          $("#container").jqprint();
          $(".div-pcz").show();
      }
  </script>
</head>
<body>
<%-- <nav class="breadcrumb text-c">
  <a class="btn-page <c:if test="${type == 1}"> pagexz</c:if>" data-href="${ly }/company/safety-system/control-list?type=1" data-title="重大风险管控" onclick="Hui_admin_tab(this)">重大风险管控</a>
  <a class="btn-page <c:if test="${type == 2}"> pagexz</c:if>" data-href="${ly }/company/safety-system/control-list?type=2" data-title="较大风险管控" onclick="Hui_admin_tab(this)" href="javascript:;">较大风险管控</a>
  <a class="btn-page <c:if test="${type == 3}"> pagexz</c:if>" data-href="${ly }/company/safety-system/control-list?type=3" data-title="一般和较小风险管控" onclick="Hui_admin_tab(this)" href="javascript:;">一般和较小风险管控</a>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="parent.close_tab(function(){})">返回</a>
</nav> --%>
<nav class="breadcrumb">
  <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
  <span class="c-gray en">&gt;</span> <span>风险分级管控</span>
  <span class="c-gray en">&gt;</span> <span>分级管控</span>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
    <i class="Hui-iconfont">&#xe68f;</i>
  </a>
</nav>
<div class="page-container">
  <div class="row cl" style="margin-top:20px;text-align:center;">
    <button onClick="pr_()" class="btn btn-success radius " type="button" style="padding: 0 70px;">
      <i class="Hui-iconfont mr-10">&#xe652;</i>打印
    </button>
  </div>
  <div class="mt-20" id="container" style="margin:auto;">
    <h3 class="text-c">${company.name}分级管控表</h3>
    <table id="xxx" class="table table-border table-bordered table-bg table-hover table-sort">
      <thead>
      <tr class="text-c">
        <th style="min-width:80px">辨识类型</th>
        <th style="min-width:80px">车间/场所</th>
        <th style="min-width:80px">系统</th>
        <th style="min-width:80px">岗位/部位</th>
        <th style="min-width:50px">风险等级</th>
        <th style="min-width:150px">风险类型</th>
        <th style="min-width:200px">风险因素</th>
        <th style="min-width:70px">管控主体</th>
        <th style="min-width:150px">责任人</th>
        <th style="min-width:150px">管控措施</th>
        <th style="min-width:100px" class="div-pcz">操作</th>
      </tr>
      </thead>

      <tbody>
    <c:forEach items="${list }" var="be">
      <c:forEach items="${treeMap }" var="be1" varStatus="index">
        <c:forEach items="${be1.value }" var="be2">
            <c:if test="${(be.level1 eq be1.key && be.level2 eq be2) || (empty be1.key && empty be.level1)}">
              <tr>
                <c:set value="${fn:split(be.level3,'/')}" var="ls"></c:set>

                <td class="text-c">${ls[0] != null ? ls[0] : "无数据" }</td>

                <c:if test="${empty be.gkzt}">
                    <td class="text-c">暂无数据</td>
                </c:if>

                <c:if test="${not empty be.gkzt}">
                  <td class="text-c">${be.gkzt}</td>
                </c:if>

                <td class="text-c">${ls[1] != null ? ls[1] : "无数据" }</td>


                <c:if test="${empty be.level2}">
                    <td class="text-c">暂无数据</td>
                </c:if>

                <c:if test="${not empty be.level2}">
                  <td class="text-c">${be.level2}</td>
                </c:if>


                <td class="text-c">
                  <c:choose>
                    <c:when test="${be.level eq '红色'}"><font class="col-a">${be.level}</font></c:when>
                    <c:when test="${be.level eq '橙色'}"><font class="col-b">${be.level}</font></c:when>
                    <c:when test="${be.level eq '黄色'}"><font class="col-c">${be.level}</font></c:when>
                    <c:when test="${be.level eq '蓝色'}"><font class="col-d">${be.level}</font></c:when>
                  </c:choose>
                </td>
                <td class="text-c">${be.type }</td>
                <td class="text-c">${be.factors }</td>
                <td class="text-c">${be.gkzt }</td>
                <td class="text-c">${be.fjgkfzr }</td>
                <td><p>${empty be.gkcs ? be.measures : be.gkcs}</p></td>
                <td class="text-c div-pcz">
                  <input type="hidden" name="gkcs" value="${empty be.gkcs ? be.measures : be.gkcs }"/>
                  <input type="hidden" name="gkzt" value="${be.gkzt }"/>
                  <input type="hidden" name="fjgkfzr" value="${be.fjgkfzr }"/>
                  <input type="hidden" name="buwei" value="${be.level2}"/>
                <c:if test="${empty be.gkzt}">
                    <a style="text-decoration:none" onClick="edit(${be.id}, this)" href="javascript:;" title="编辑">编辑管控信息</a>
                </c:if>
                <c:if test="${not empty be.gkzt}">
                    <a style="text-decoration:none" onClick="edits(${be.id}, this)" href="javascript:;" title="编辑">编辑</a>
<%--                     <input type="button" style="text-decoration:none;" onClick='copy_('"${be}") title="复制" value="复制"></input>--%>

                  <a style="text-decoration:none;" onClick="copy_('${be.fjgkfzr}','${be.gkcs}','${be.gkzt}','${be.level2}','${be.level}','${be.factors}','${be.flag}','${be.uid}' )" href="javascript:;" title="复制" >复制</a>
                </c:if>
                </td>
              </tr>
            </c:if>
          </c:forEach>
        </c:forEach>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>

<!--  编辑管控信息 弹窗 -->
<div id="win-add" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="width: 760px">
    <div class="modal-content radius">
      <div class="modal-header">
        <h3 class="modal-title">管控信息</h3>
        <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
      </div>
      <div class="modal-body">
        <div class="row cl">
          <label class="form-label col-xs-4 col-sm-2" style="width: 20%; text-align: right;">管控主体：</label>
          <div class="formControls col-xs-8 col-sm-9" style="width: 80%;">
            <input type="text" id="gkzt" value="" style="width: 357px" class="input-text required" readonly="readonly">

            <input id = "gkztIds"  type="hidden" >

            <c:if test="${not empty departL}">
              <button class="btn btn-primary radius" type="button" onclick="chosem()">
                <i class="Hui-iconfont">&#xe611;</i>选择管控主体
              </button>
            </c:if>
          </div>
        </div>


        <div class="row cl mt-15">
          <label class="form-label col-xs-4 col-sm-2" style="width: 20%; text-align: right;">部位：</label>
          <div class="formControls col-xs-8 col-sm-9" style="width: 80%;">
            <input type="text" id="buwei" value="" style="width: 357px" class="input-text required" readonly="readonly">
            <c:if test="${not empty perL}">
              <button class="btn btn-primary radius" id = "gangwei" type="button" onclick="chosem3()" >

                <i class="Hui-iconfont" >&#xe611;</i>选择部位

              </button>
            </c:if>
          </div>
        </div>


        <div class="row cl mt-15">
          <label class="form-label col-xs-4 col-sm-2" style="width: 20%; text-align: right;">管控措施：</label>
          <div class="formControls col-xs-8 col-sm-9" style="width: 80%;">
            <textarea id="gkcs" class="textarea txtarea_sq" style="width: 557px;" ></textarea>
          </div>
        </div>
        <div class="row cl mt-15">
          <label class="form-label col-xs-4 col-sm-2" style="width: 20%; text-align: right;">责任人：</label>
          <div class="formControls col-xs-8 col-sm-9" style="width: 80%;" readonly="readonly">
            <input type="text" id="fjgkfzr" value="" style="width: 357px" class="input-text required">
            <c:if test="${not empty perL}">
              <button class="btn btn-primary radius" type="button" onclick="chosem2()">
                <i class="Hui-iconfont">&#xe611;</i>选择责任人
              </button>
            </c:if>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" onclick="save_()">确定</button>
        <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
      </div>
    </div>
  </div>
</div>

<div id="win-add2" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="width: 900px">
    <div class="modal-content radius">
      <div class="modal-header">
        <h3 class="modal-title">选择管控主体</h3>
        <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
      </div>
      <div class="modal-body">
        <table class="table table-border table-bordered table-bg table-hover table-sort" style="float:none;">
          <thead>
          <tr class="text-c">
            <th style="width:50px;">&nbsp;</th>
            <th>公司</th>
            <th>车间/部门</th>
            <!-- <th>班组</th> -->
          </tr>
          </thead>
          <c:forEach items="${departL }" var="be">
            <tr class="text-c">
              <td><input type="radio" name="radio-1" value="${be.name }" onclick="dep_choose2s(${be.id})"></td>
              <input type="hidden" value="${be.id }">
              <td>${be.companyName }</td>
              <td>${be.name }</td>
                <%-- <td>${be.level == 1 ? be.name : be.parName}</td>
                <td>${be.level == 1 ? '' : be.name}</td> --%>
            </tr>
          </c:forEach>
        </table>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" onclick="dep_choose()">确定</button>
        <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
    function  dep_choose2s(a) {
        $("#gkztIds").val(a);
    }
    function dep_choose() {
        var list = $("#win-add2 :checked");
        var v = [];
        list.each(function() {
            v.push($(this).val());
        });
        $("#gkzt").val(v.join(","));
        $("#win-add2").modal("hide");
    }
</script>

<div id="win-add3" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="width: 900px">
    <div class="modal-content radius">
      <div class="modal-header">
        <h3 class="modal-title">选择责任人</h3>
        <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
      </div>
      <div class="modal-body">
        <table class="table table-border table-bordered table-bg table-hover table-sort" style="float:none;">
          <thead>
          <tr class="text-c">
            <th style="width:50px;">&nbsp;</th>
            <th>姓名</th>
            <th>公司</th>
            <th>部门</th>
            <th>班组</th>
            <th>职务</th>
          </tr>
          </thead>
          <c:forEach items="${perL }" var="be">
            <tr class="text-c">
              <td><input type="radio" name="radio-2" value="${be.name }"></td>
              <td>${be.name }</td>
              <td>${be.companyName }</td>
              <td>${be.level == 1 ? be.dname : be.dpname}</td>
              <td>${be.level == 1 ? '' : be.dname}</td>
              <td>${be.position }</td>

              <input id = "dianhuas" type="hidden" value = "${be.mobile }">
            </tr>
          </c:forEach>
        </table>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" onclick="per_choose()">确定</button>
        <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
      </div>
    </div>
  </div>
</div>



<%-- 部位窗口信息 --%>
<div id="win-add4" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="width: 400px">
    <div class="modal-content radius">
      <div class="modal-header">
        <h3 class="modal-title">选择部位</h3>
        <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
      </div>
      <div id = "test" class="modal-body">
        <table class="table table-border table-bordered table-bg table-hover table-sort" style="float:none">
          <thead>
          <tr class="text-c">
            <th style="width:50px;">&nbsp;</th>
            <th>部位</th>"
          </thead>
        </table>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" onclick="per_chooses()">确定</button>
        <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">

    function per_choose() {
        var list = $("#win-add3 :checked");
        var v = [];
        list.each(function() {
            v.push($(this).val());
        })
        $("#fjgkfzr").val(v.join(","));

        $("#win-add3").modal("hide");
    }
</script>
</body>
<script type="text/javascript">
    $(function(){
        var b = null, l1 = '', c = 1;
        var b2 = null, l2 = '', c2 = 1;
        $("#xxx tbody tr").each(function() {
            var td = $(this).children("td").eq(0);
            var td2 = $(this).children("td").eq(1);
            var l1_ = td.text();
            var l2_ = td2.text();

            //与上一层一样
            if(l1 == l1_) {
                td.remove();
                c = c + 1;

                if(l2 == l2_) {
                    td2.remove();
                    c2 = c2 + 1;
                } else {
                    l2 = l2_;
                    if(b2 != null) {
                        b2.attr("rowspan", c2);
                        c2 = 1;
                    }
                    b2 = td2;
                }

            } else {//与上一层不一样
                l1 = l1_;
                if(b != null) {
                    b.attr("rowspan", c);
                    c = 1;
                }
                b = td;

                l2 = l2_;
                if(b2 != null) {
                    b2.attr("rowspan", c2);
                    c2 = 1;
                }
                b2 = td2;
            }
        })
        if(b != null) {
            b.attr("rowspan", c);
        }
        if(b2 != null) {
            b2.attr("rowspan", c2);
        }
    })
</script>
</html>