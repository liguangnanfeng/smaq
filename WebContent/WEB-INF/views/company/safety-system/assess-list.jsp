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
    .col-b,.col-a,.col-c,.col-d{margin:0;padding:0 15px;height:25px;line-height:25px;}
    .btn-huang{color:#ffd900;border-color:#ffd900;}
    .btn-huang:hover{background-color:#ffd900;color:#fff;border-color:#FFFF00}
    body .col-c{background:#ffd900}
    .check-fxlx{float:left;margin-right:15px;}
    .check-fxlx input{margin-right:5px;}
  </style>
  <script type="text/javascript">
      function add(){
          $("#win-add").modal("show");
      }
      function show1(){
          $("#win-add2").modal("show");
      }
      function show2(){
          $("#win-add3").modal("show");
      }
      function show3(id_, level){
          window.id_ = id_;
          $("#zpLevel").val(level);
          $("#win-add4").modal("show");
      }

      //自评保存
      function zpLevel_save_(id,e) {
          $.post(getRootPath() + "/company/safety-system/zp-save", {
              id : id,
              level : $(e).attr("value")
          },function(result) {
              location.reload();
          })
      }

      //保存
      function save_() {
//  if($("#l1_l2").val() == '') {
//    layer.alert("请选择车间/岗位/部位");
//   return false;
//  }
          var type = new Array();
          $("#win-add :checkbox:checked").each(function() {
              type.push($(this).val());
          })
          if($("#factors").val() == '') {
              layer.alert("请输入风险因素");
              return false;
          }
          $.post("/company/safety-system/aCompanyManual-save",{
//    level1 : $("#l1_l2 option:selected").attr("data-p"),
//    level2 : $("#l1_l2 option:selected").attr("data-n"),
              factors : $("#factors").val(),
              type : type.join(","),
              flag : '3',
              flag2 : 1,
              flag3 : 1
          },function(i, item) {
              location.reload();
          })
      }

      function del_(id) {
          layer.confirm("确定删除该记录?", function(r) {
            $.post(getRootPath() + "/company/safety-system/aCompanyManual-del", {
                id : id
            }, function(result) {
                location.reload();
            })
          })
      }

      function del_bath() {
          var ids = new Array();
          $("tbody :checkbox:checked").each(function() {
              ids.push($(this).val());
          })
          ids = ids.join(",");
          if(ids == '') {
              layer.alert("请选择需要删除的数据");
              return false;
          }
          $.post(getRootPath() + "/company/safety-system/aCompanyManual-deletes", {
              ids : ids
          }, function(result) {
              location.reload();
          })
      }
        $(function(){
            $('.table-sort').dataTable({
                "searching": false,
                "ordering": false,
                "bStateSave": true,//状态保存
                "pagingType":"full_numbers",
                "aoColumnDefs": [
                ]
            });
            /* 全选和反选 */
            $(".check-all").on('click', function() {
                $(".modal-body tbody input:checkbox").prop("checked", $(this).prop('checked'));
            })
            $(".tab-ass tbody input:checkbox").on('click', function() {
                if($(".tab-ass tbody input:checkbox").length == $(".tab-ass tbody input:checked").length) {
                    $(".check-all").prop("checked", true);
                } else {
                    $(".check-all").prop("checked", false);
                }
            })
        })
  </script>
</head>
<body>
<nav class="breadcrumb text-c">
  <a class="btn-page"  data-href="${ly }/company/safety-system/assess5"  data-title="重大风险判定" onclick="Hui_admin_tab(this)" href="javascript:;">重大风险判定</a>
  <a class="btn-page pagexz" href="javascript:;">较大风险判定</a>
  <a class="btn-page"  data-href="${ly }/company/safety-system/assess6"  data-title="一般和较小风险判定" onclick="Hui_admin_tab(this)" href="javascript:;">一般和较小风险判定</a>
  <%--<a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="parent.close_tab(function(){})">返回</a>--%>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;" href="${ly }/company/threeLeft?leftBasic=2" title="返回">返回</a>
</nav>
<div class="page-container">
  <%--div class="cl pd-5 bg-1 bk-gray mt-20">
      &lt;%&ndash;<span class="l">
        &lt;%&ndash;<a class="btn btn-primary radius" href="javascript:void(0)" onClick="add()"><i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 添加风险点</a>&ndash;%&gt;
       &lt;%&ndash;
        <a class="btn btn-success-outline radius" href="javascript:void(0)" onClick="show_dialog('作业条件风险程度评价','/company/safety-system/assess2?id=${be.id}')"><i class="Hui-iconfont" style="font-size:15px;">&#xe695;</i> LEC评测</a>
        <a class="btn btn-success-outline radius" href="javascript:void(0)" onClick="show_dialog('风险矩阵法评价','/company/safety-system/assess3?id=${be.id}')"><i class="Hui-iconfont" style="font-size:15px;">&#xe695;</i> LS评测</a>
        <!-- <a class="btn btn-success-outline radius" href="javascript:void(0)" onClick="show1()"><i class="Hui-iconfont" style="font-size:15px;">&#xe695;</i> 风险等级界限值</a>&ndash;%&gt;
&lt;%&ndash;
        <a class="btn btn-success-outline radius ml-10" href="javascript:void(0)" onClick="show2()"><i class="Hui-iconfont" style="font-size:15px;">&#xe695;</i> 企业安全风险矩阵与风险等级判别标准</a> -->
&ndash;%&gt;
      </span>&ndash;%&gt;--%>
  <div class="cl pd-5 bg-1 bk-gray mt-20">
    <a class="btn btn-danger radius" href="javascript:void(0)" onClick="del_bath()"><i class="Hui-iconfont" style="font-size:15px;">&#xe609;</i> 删除</a>
    <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span>
  </div>
  <div class="mt-20">
    <table class="table table-border table-bordered table-bg table-hover table-sort tab-ass">
      <thead>
      <tr class="text-c">
        <th style="width:40px"><input type="checkbox" value="${be.id }" class="check-all"/></th>
        <th style="min-width:120px">辨识类型</th>
        <th style="min-width:140px">系统</th>
        <th style="min-width:120px">环节/部位</th>
        <th style="min-width:100px">风险因素</th>
        <th style="min-width:100px">风险等级</th>
        <!-- <th style="min-width:100px">作业条件风险程度评价(LEC)</th>
        <th style="min-width:100px">风险矩阵法评价(LS)</th> -->
        <th style="min-width:265px">评估</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${list }" var="be" varStatus="index">
        <tr class="text-c">
          <td><input type="checkbox" value="${be.id }"/></td>
            <%--<td>${be.level1 }</td>
            <td>${be.level2 }</td>
             <td>
                <c:set value="${fn:split(be.level3,'/')}" var="ls"></c:set>
              	<c:set value="0" var="y"/>
              	<c:forEach items="${ls}" var="l">
              		<c:set value="${y+1}" var="y"/>
                  <c:if test="${y==3}">
                  	<c:set value="${l}" var="point"/>
                  </c:if>
               </c:forEach>
               <p>${point}</p>
            </td> --%>
          <c:set value="${fn:split(be.level3,'/')}" var="ls"></c:set>
          <c:set value="0" var="y"/>
          <c:forEach items="${ls}" var="l">
            <c:set value="${y+1}" var="y"/>
            <c:if test="${y==1}">
              <c:set value="${l}" var="point1"/>
            </c:if>
            <c:if test="${y==2}">
              <c:set value="${l}" var="point2"/>
            </c:if>
            <c:if test="${y==3}">
              <c:set value="${l}" var="point3"/>
            </c:if>
          </c:forEach>
          <td><p>${point1}</p></td>
          <td><p>${point2}</p></td>
          <td><p>${point3}</p></td>
          <td>${be.factors }</td>
          <td>
            <input class="btn btn-warning size-S radius ml-5" type="button" value="橙色" />

          </td>


        <%--  <td>
             &lt;%&ndash;  <c:choose>
              <c:when test="${be.zpLevel eq '红色'}"><font class="col-a">${be.zpLevel}</font></c:when>

              <c:when test="${be.zpLevel eq '黄色'}"><font class="col-c">${be.zpLevel}</font></c:when>
              <c:when test="${be.zpLevel eq '蓝色'}"><font class="col-d">${be.zpLevel}</font></c:when>
             </c:choose>&ndash;%&gt;
            &lt;%&ndash;<c:choose>
              <c:when test="${be.level eq '红色'}"><font class="col-a">${be.level}</font></c:when>
              <c:when test="${be.level eq '橙色'}"><font class="col-b">${be.level}</font></c:when>
              <c:when test="${be.level eq '黄色'}"><font class="col-c">${be.level}</font></c:when>
              <c:when test="${be.level eq '蓝色'}"><font class="col-d">${be.level}</font></c:when>
            </c:choose>&ndash;%&gt;
          </td>--%>
             <%--<td>
                <c:choose>
                <c:when test="${be.lev2 eq '红色'}"><font class="col-a">${be.lev2}</font></c:when>
                <c:when test="${be.lev2 eq '橙色'}"><font class="col-b">${be.lev2}</font></c:when>
                <c:when test="${be.lev2 eq '黄色'}"><font class="col-c">${be.lev2}</font></c:when>
                <c:when test="${be.lev2 eq '蓝色'}"><font class="col-d">${be.lev2}</font></c:when>
               </c:choose>
            </td>--%>
           <%-- <td>
                <c:choose>
                <c:when test="${be.level eq '红色'}"><font class="col-a">${be.lev1}</font></c:when>
                <c:when test="${be.level eq '橙色'}"><font class="col-b">${be.lev1}</font></c:when>
                <c:when test="${be.level eq '黄色'}"><font class="col-c">${be.lev1}</font></c:when>
                <c:when test="${be.level eq '蓝色'}"><font class="col-d">${be.lev1}</font></c:when>
               </c:choose>
            </td> --%>
          <td>
            <input class="btn btn-danger-outline size-S radius ml-5" type="button" value="红色" onclick="zpLevel_save_(${be.id},this)"/>
            <input class="btn btn-warning-outline size-S radius ml-5" type="button" value="橙色" onclick="zpLevel_save_(${be.id},this)"/>
            <input class="btn btn-warning-outline size-S radius btn-huang ml-5" type="button" value="黄色" onclick="zpLevel_save_(${be.id},this)"/>
            <input class="btn btn-secondary-outline size-S radius ml-5 mr-5" type="button" value="蓝色" onclick="zpLevel_save_(${be.id},this)"/>
            <a class="btn size-S radius" style="text-decoration:none" onClick="del_(${be.id})" href="javascript:;" title="删除">删除</a>
               <%--<a style="text-decoration:none" onClick="show3(${be.id}, '${be.level}')" href="javascript:;" title="评估">评估</a>
               <a style="text-decoration:none" onClick="show_dialog('作业条件风险程度评价','/company/safety-system/assess2?id=${be.id}')" href="javascript:;" title="立即评测">LEC评测</a>
              <a style="text-decoration:none" onClick="show_dialog('风险矩阵法评价','/company/safety-system/assess3?id=${be.id}')" href="javascript:;" title="立即评测">LS评测</a>--%>

          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>

<%--<!-- 弹窗评估 -->
<div id="win-add4" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="width: 760px">
    <div class="modal-content radius">
      <div class="modal-header">
        <h3 class="modal-title">评估</h3>
        <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
      </div>
      <div class="modal-body">
        <div class="row cl">
          <label class="form-label col-xs-4 col-sm-2" style="width: 20%; text-align: right;">评估：</label>
          <div class="formControls col-xs-8 col-sm-9" style="width: 80%;">
            <select id="zpLevel" class="sel_area">
              <option value="">请选择</option>
              <option >红色</option>
              <option >橙色</option>
              <option >黄色</option>
              <option >蓝色</option>
            </select>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" onclick="zpLevel_save_()">确定</button>
        <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
      </div>
    </div>
  </div>
</div>--%>

<%--<!-- 弹窗添加 -->
<div id="win-add" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="width: 760px">
    <div class="modal-content radius">
      <div class="modal-header">
        <h3 class="modal-title">添加风险点</h3>
        <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
      </div>
      <div class="modal-body">
        &lt;%&ndash;<div class="row cl">
          <label class="form-label col-xs-4 col-sm-2" style="width: 20%; text-align: right;">车间/部位：</label>
          <div class="formControls col-xs-8 col-sm-9" style="width: 80%;">
            <select id="l1_l2" class="sel_area">
              <option value="">请选择车间/岗位/部位</option>
              <c:forEach items="${depL }" var="be">
              <option data-p="${be.parName }" data-n="${be.name }">${be.parName }/${be.name }</option>
              </c:forEach>
            </select>
            <a class="btn btn-primary radius" href="${ly }/company/system/department-list"><i class="Hui-iconfont">&#xe600;</i>添加车间/岗位/部位</a>
          </div>
        </div> &ndash;%&gt;
        <div class="row cl mt-15">
          <label class="form-label col-xs-4 col-sm-2" style="width: 20%; text-align: right;">风险类型：</label>
          <div class="formControls col-xs-8 col-sm-9" style="width: 80%;">
            <!-- 循环 -->
            <c:forEach items="${shL }" var="be">
              <div class="check-fxlx">
                <input type="checkbox" value="${be.name }"/>${be.name }
              </div>
            </c:forEach>
            <!-- 循环结束 -->
          </div>
        </div>
        <div class="row cl mt-15">
          <label class="form-label col-xs-4 col-sm-2" style="width: 20%; text-align: right;">风险因素：</label>
          <div class="formControls col-xs-8 col-sm-9" style="width: 80%;">
            <textarea id="factors" class="textarea txtarea_sq" style="width: 557px;height:100px"></textarea>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" onclick="save_()">确定</button>
        <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
      </div>
    </div>
  </div>
</div>--%>

<!-- 弹窗查看风险值 -->
<div id="win-add2" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="width:760px">
    <div class="modal-content radius">
      <div class="modal-header">
        <h3 class="modal-title">风险等级界限值</h3>
        <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
      </div>
      <div class="modal-body">
        <table class="table table-border table-bordered table-bg table-hover table-sort" style="float:none;">
          <thead>
          <tr class="text-c">
            <th>D值</th>
            <th>危险程度</th>
            <th>风险等级</th>
          </tr>
          </thead>
          <tr class="text-c">
            <td>&gt;320</td>
            <td>不可容许的危险</td>
            <td>红</td>
          </tr>
          <tr class="text-c">
            <td>160～320</td>
            <td>高度危险</td>
            <td>橙</td>
          </tr>
          <tr class="text-c">
            <td>70～160</td>
            <td>中度危险</td>
            <td>黄</td>
          </tr>
          <tr class="text-c">
            <td>&lt;70</td>
            <td>轻度和可容许的危险</td>
            <td>蓝</td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</div>

<!-- 弹窗企业安全风险矩阵与风险等级判别标准 -->
<div id="win-add3" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="width:760px">
    <div class="modal-content radius">
      <div class="modal-header">
        <h3 class="modal-title">企业安全风险矩阵与风险等级判别标准</h3>
        <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
      </div>
      <div class="modal-body">
        <font>R=1，2，3，蓝色低风险企业；R=4，6，黄色一般风险企业；R=8，9，橙色较大风险企业；R=12，16，红色重大风险企业</font>
        <table class="table table-border table-bordered table-bg mt-10 tab-fx mt-10" style="float:none">
          <thead>
          <tr class="text-c">
            <th rowspan="2" colspan="2" width="20%">风险矩阵（R）</th>
            <th colspan="4" width="80%" >事故后果的严重性（S）</th>
          </tr>
          <tr class="text-c">
            <th>1</th>
            <th>2</th>
            <th>3</th>
            <th>4</th>
          </tr>
          </thead>
          <tr class="text-c">
            <td rowspan="4">事故发生的可能性（L）</td>
            <td style="width:50px">1</td>
            <td style="background:#3B9BFF"></td>
            <td style="background:#3B9BFF"></td>
            <td style="background:#3B9BFF"></td>
            <td style="background:#FFFF00"></td>
          </tr>
          <tr class="text-c">
            <td>2</td>
            <td style="background:#3B9BFF"></td>
            <td style="background:#FFFF00"></td>
            <td style="background:#FFFF00"></td>
            <td style="background:#FFA500"></td>
          </tr>
          <tr class="text-c">
            <td>3</td>
            <td style="background:#3B9BFF"></td>
            <td style="background:#FFFF00"></td>
            <td style="background:#FFA500"></td>
            <td style="background:#FF0000"></td>
          </tr>
          <tr class="text-c">
            <td>4</td>
            <td style="background:#FFFF00"></td>
            <td style="background:#FFA500"></td>
            <td style="background:#FF0000"></td>
            <td style="background:#FF0000"></td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</div>


</body>
</html>
