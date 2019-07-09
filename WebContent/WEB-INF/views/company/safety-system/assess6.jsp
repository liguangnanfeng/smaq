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
          if($("#l1_l2").val() == '') {
              layer.alert("请选择车间/岗位/部位");
              return false;
          }
          var type = new Array();
          $("#win-add :checkbox:checked").each(function() {
              type.push($(this).val());
          })
          if($("#factors").val() == '') {
              layer.alert("请输入风险因素");
              return false;
          }
          $.post("/company/safety-system/aCompanyManual-save",{
              level1 : $("#l1_l2 option:selected").attr("data-p"),
              level2 : $("#l1_l2 option:selected").attr("data-n"),
              factors : $("#factors").val(),
              type : type.join(","),
              flag : '3',
              flag2 : 1
          },function(i, item) {
              location.reload();
          })
      }

      function del_(id) {
          layer.confirm("确定删除该记录?", function(r) {
              $.post(getRootPath() + "/company/safety-system/aCompanyManual-save", {
                  id : id,
                  del : 1
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

      function update() {
          layer.confirm("确定进行风险点重置？", function(r) {
              $.post(getRootPath() + "/company/safety-system/risk-list-add2", {
              }, function(result) {
                  location.reload();
              })
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
</head>s
<body>
<nav class="breadcrumb text-c">
  <a class="btn-page"  data-href="${ly }/company/safety-system/assess7"  data-title="评估操作" onclick="Hui_admin_tab(this)" href="javascript:;">评估操作</a>
  <a class="btn-page"  data-href="${ly }/company/safety-system/assess5"  data-title="重大风险评估" onclick="Hui_admin_tab(this)" href="javascript:;">重大风险评估</a>
  <a class="btn-page" data-href="${ly }/company/safety-system/assess-list"  data-title="较大风险判定" onclick="Hui_admin_tab(this)" href="javascript:;">较大风险评估</a>
  <a class="btn-page pagexz" href="javascript:;">一般和较小风险评估</a>
  <%--<a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="parent.close_tab(function(){})">返回</a>--%>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;" href="${ly }/company/threeLeft?leftBasic=2" title="返回">返回</a>
</nav>
<div class="page-container">
  <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l">
        <%--<a class="btn btn-primary radius" href="javascript:void(0)" onClick="add()"><i class="Hui-iconfont" style="font-size:15px;">&#xe600;</i> 添加风险点</a> --%>
       <%-- <a class="btn btn-primary radius" href="javascript:void(0);" onclick="update()">风险因素辨识 </a>--%>
        <a class="btn btn-danger radius" href="javascript:void(0)" onClick="del_bath()"><i class="Hui-iconfont" style="font-size:15px;">&#xe609;</i> 删除</a>
		   <c:if test="${indus == 1}">
             <a class="btn btn-danger radius" href="javascript:void(0)" onClick="show_dialog('企业评分','${ly }/company/safety-system/grade-table')">企业评分</a>
           </c:if>

          <c:if test="${industry eq '冶金行业' || industry eq '有色行业' || industry eq '建材行业'|| industry eq '机械行业'|| industry eq '轻工行业'|| industry eq '纺织行业'|| industry eq '烟草行业'|| industry eq '商贸行业' }">
              <a class="btn btn-danger radius" href="javascript:void(0)" onClick="show_dialog('工贸企业','${ly }/company/safety-system/danger-table')">工贸评定</a>
          </c:if>
            <a class="btn btn-success-outline radius" href="javascript:void(0)" onclick="show_dialog('作业条件风险程度评价','/company/safety-system/assess2?id=')"><i class="Hui-iconfont" style="font-size:15px;"></i> LEC评测</a>
            <a class="btn btn-success-outline radius" href="javascript:void(0)" onclick="show_dialog('风险矩阵法评价','/company/safety-system/assess3?id=')"><i class="Hui-iconfont" style="font-size:15px;"></i> LS评测</a>

        <!-- <a class="btn btn-success-outline radius" href="javascript:void(0)" onClick="show_dialog('作业条件风险程度评价','/company/safety-system/assess2?id=${be.id}')"><i class="Hui-iconfont" style="font-size:15px;">&#xe695;</i> LEC评测</a>
        <a class="btn btn-success-outline radius" href="javascript:void(0)" onClick="show_dialog('风险矩阵法评价','/company/safety-system/assess3?id=${be.id}')"><i class="Hui-iconfont" style="font-size:15px;">&#xe695;</i> LS评测</a>
        <a class="btn btn-success-outline radius" href="javascript:void(0)" onClick="show1()"><i class="Hui-iconfont" style="font-size:15px;">&#xe695;</i> 风险等级界限值</a>
        <a class="btn btn-success-outline radius ml-10" href="javascript:void(0)" onClick="show2()"><i class="Hui-iconfont" style="font-size:15px;">&#xe695;</i> 企业安全风险矩阵与风险等级判别标准</a> -->
      </span>
    <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span>
  </div>
  <div class="mt-20">
    <table class="table table-border table-bordered table-bg table-hover table-sort tab-ass">
      <thead>
      <tr class="text-c">
        <th style="width:40px"><input type="checkbox" value="${be.id }" class="check-all"/></th>
        <th style="min-width:80px">辨识类型</th>
        <th style="min-width:80px">车间/场所</th>
        <th style="min-width:80px">系统</th>
        <th style="min-width:80px">环节/部位</th>
        <th style="min-width:70px">风险等级</th>
        <th style="min-width:150px">事故类型</th>
        <th style="min-width:200px">风险因素</th>
        <!-- <th style="min-width:100px">作业条件风险程度评价(LEC)</th>
        <th style="min-width:100px">风险矩阵法评价(LS)</th> -->
        <th style="min-width:265px">评估</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${list }" var="be" varStatus="index">
        <tr>
          <td class="text-c"><input type="checkbox" value="${be.id}"/></td>
            <%-- <td class="text-c">${be.level3 }</td> --%>
          <c:set value="${fn:split(be.level3,'/')}" var="ls"></c:set>

          <td class="text-c">${ls[0] != null ? ls[0] : "暂无数据" }</td>

          <td class="text-c">${be.level1}</td>

            <%--<c:if test="${be.flag == 2}">
              <td class="text-c">公司</td>
            </c:if>

            <c:if test="${be.flag != 2}">
              <td class="text-c">${be.gkzt != null ? be.gkzt : "暂无数据" }</td>
            </c:if>--%>

          <td class="text-c">${ls[1] != null ? ls[1] : "暂无数据" }</td>

          <td class="text-c">${be.level2 == null ? "暂无数据" : be.level2}</td>
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

          <td class="text-c">
            <input class="btn btn-danger-outline size-S radius ml-5" type="button" value="红色" onclick="zpLevel_save_(${be.id},this)"/>
            <input class="btn btn-warning-outline size-S radius ml-5" type="button" value="橙色" onclick="zpLevel_save_(${be.id},this)"/>
            <input class="btn btn-warning-outline size-S radius btn-huang ml-5" type="button" value="黄色" onclick="zpLevel_save_(${be.id},this)"/>
            <input class="btn btn-secondary-outline size-S radius ml-5 mr-5" type="button" value="蓝色" onclick="zpLevel_save_(${be.id},this)"/>
            <a class="btn-cz" style="text-decoration:none;" onClick="del_(${be.id})" href="javascript:;" title="删除">删除</a>
          </td>
            <%-- <c:set value="${fn:split(be.level3,'/')}" var="ls"></c:set>
             <c:set value="0" var="y"/>
             <c:forEach items="${ls}" var="l">
               <c:set value="${y+1}" var="y"/>
               <c:if test="${y==3}">
                 <c:set value="${l}" var="point"/>
               </c:if>
             </c:forEach>
             <p>${point}</p>--%>
            <%-- </td>
             <td class="text-c"><font class="col-a">红色</font></td>
             <td>${be.factors }</td>
             &lt;%&ndash;<td>${be.type }</td>&ndash;%&gt;
             <td class="text-c">
               <input class="btn btn-danger-outline size-S radius ml-5" type="button" value="红色" onclick="zpLevel_save_(${be.id},this)"/>
               <input class="btn btn-warning-outline size-S radius ml-5" type="button" value="橙色" onclick="zpLevel_save_(${be.id},this)"/>
               <input class="btn btn-warning-outline size-S radius btn-huang ml-5" type="button" value="黄色" onclick="zpLevel_save_(${be.id},this)"/>
               <input class="btn btn-secondary-outline size-S radius ml-5 mr-5" type="button" value="蓝色" onclick="zpLevel_save_(${be.id},this)"/>
               <a class="btn-cz" style="text-decoration:none;" onClick="del_(${be.id})" href="javascript:;" title="删除">删除</a>
             </td>--%>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>

<!-- 弹窗评估 -->
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
</div>

<!-- 弹窗添加 -->
<div id="win-add" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="width: 760px">
    <div class="modal-content radius">
      <div class="modal-header">
        <h3 class="modal-title">添加风险点</h3>
        <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
      </div>
      <div class="modal-body">
        <div class="row cl">
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
        </div>
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
</div>

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
