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
.input-text{position:relative}
.txtarea_sq{height:100px;width:350px;}
body .check-box{padding-left:0px;width:19%;margin-top:10px;padding-right:0px;}
</style>
<script type="text/javascript">
var id_ = '${r.id}';
function adddan(){
  $("#add-dan").modal("show")
}
</script>
</head>
<body>
<nav class="breadcrumb">
  <i class="Hui-iconfont">&#xe67f;</i>
  <span>首页</span>
  <span class="c-gray en">&gt;</span>
  <span>安全生产标准化</span>
  <span class="c-gray en">&gt;</span>
  <span>安全检查和隐患排查</span>
  <span class="c-gray en">&gt;</span>
  <span>修改两重点一重大</span>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
    <i class="Hui-iconfont">&#xe68f;</i>
  </a>
</nav>
<div class="page-container">
  <form class="form form-horizontal" id="form">
    <div class="Huialert Huialert-info" style="width:88%;margin-left:6%;margin-top:20px;"><i class="icon-remove"></i>重点监管危险化学品</div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-3">是否有重点监管危险化学品：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <select class="sel_area sel01" id="chemic">
          <option value="">请选择</option>
          <option value="1" <c:if test="${r.chemic==1 }">selected="selected"</c:if>>是</option>
          <option value="0" <c:if test="${r.chemic==0 }">selected="selected"</c:if>>否</option>
        </select>
      </div>
    </div>
    <div class="divd1">
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-3">物质名称：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="text" id="chemicName" value="${r.chemicName }" style="width:350px" class="input-text required"  >
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-3">是否满足自控要求：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <select class="sel_area" id="chemicControl" >
            <option value="">请选择</option>
            <option value="1" <c:if test="${r.chemicControl==1 }">selected="selected"</c:if>>是</option>
            <option value="0" <c:if test="${r.chemicControl==0 }">selected="selected"</c:if>>否</option>
          </select>
        </div>
      </div>
    </div>
    <div class="Huialert Huialert-info" style="width:88%;margin-left:6%;margin-top:20px;"><i class="icon-remove"></i>重点监管危险化工工艺</div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-3">是否有重点监管危险化工工艺：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <select class="sel_area sel02" id="process">
          <option value="">请选择</option>
          <option value="1" <c:if test="${r.process==1 }">selected="selected"</c:if>>是</option>
          <option value="0" <c:if test="${r.process==0 }">selected="selected"</c:if>>否</option>
        </select>
      </div>
    </div>
    <div class="divd2">
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-3">工艺名称：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <div class="check-box">
            <input type="checkbox" name="processName"/>
            <label>光气及光气化工艺</label>
          </div>
          <div class="check-box">
            <input type="checkbox" name="processName"/>
            <label>电解工艺（氯碱）</label>
          </div>
          <div class="check-box">
            <input type="checkbox" name="processName"/>
            <label>氯化工艺</label>
          </div>
          <div class="check-box">
            <input type="checkbox" name="processName"/>
            <label>硝化工艺</label>
          </div>
          <div class="check-box">
            <input type="checkbox" name="processName"/>
            <label>合成氨工艺</label>
          </div>
          <div class="check-box">
            <input type="checkbox" name="processName"/>
            <label>裂解（裂化）工艺</label>
          </div>
          <div class="check-box">
            <input type="checkbox" name="processName"/>
            <label>氟化工艺</label>
          </div>
          <div class="check-box">
            <input type="checkbox" name="processName"/>
            <label>加氢工艺</label>
          </div>
          <div class="check-box">
            <input type="checkbox" name="processName"/>
            <label>重氮化工艺</label>
          </div>
          <div class="check-box">
            <input type="checkbox" name="processName"/>
            <label>氧化工艺</label>
          </div>
          <div class="check-box">
            <input type="checkbox" name="processName"/>
            <label>过氧化工艺</label>
          </div>
          <div class="check-box">
            <input type="checkbox" name="processName"/>
            <label>胺基化工艺</label>
          </div>
          <div class="check-box">
            <input type="checkbox" name="processName"/>
            <label>磺化工艺</label>
          </div>
          <div class="check-box">
            <input type="checkbox" name="processName"/>
            <label>聚合工艺</label>
          </div>
          <div class="check-box">
            <input type="checkbox" name="processName"/>
            <label>烷基化工艺</label>
          </div>
          <div class="check-box">
            <input type="checkbox" name="processName"/>
            <label>新型煤化工工艺</label>
          </div>
          <div class="check-box">
            <input type="checkbox" name="processName"/>
            <label>电石生产工艺</label>
          </div>
          <div class="check-box">
            <input type="checkbox" name="processName"/>
            <label>偶氮化工艺</label>
          </div>
          <script type="text/javascript">
          $(":checkbox[name='processName']").each(function(){
            $(this).attr("value", $(this).parent().find("label").text());
          })
          
          var processName = '${r.processName}';
          if(processName != '') {
            $.each(processName.split(","), function(i, item) {
              $(":checkbox[value='"+item+"']").prop("checked", true);
            })
          }
          </script>
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-3">是否满足自控要求：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <select class="sel_area" id="processControl" >
            <option value="">请选择</option>
            <option value="1" <c:if test="${r.processControl==1 }">selected="selected"</c:if>>是</option>
            <option value="0" <c:if test="${r.processControl==1 }">selected="selected"</c:if>>否</option>
          </select>
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-3">备案日期：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="text" value="${r.processRecordDate }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="processRecordDate" class="input-text Wdate mb-5 mt-5" style="width:350px;">
        </div>
      </div>
    </div>
    <div class="Huialert Huialert-info" style="width:88%;margin-left:6%;margin-top:20px;"><i class="icon-remove"></i>重大危险源</div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-3">是否构成重大危险源：</label>
      <div class="formControls col-xs-8 col-sm-9">
        <select class="sel_area sel03" id="cisDanger" >
          <option value="">请选择</option>
          <option value="1" onclick="javascript:show_dialog('两重点一重大','/company/information/information-edit3')" <c:if test="${r.cisDanger==1 }">selected="selected"</c:if>>是</option>
          <option value="0" <c:if test="${r.cisDanger==0 }">selected="selected"</c:if>>否</option>
        </select>
      </div>
    </div>
    <div class="divd3">
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-3">重大危险源等级：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <select class="sel_area" id="dangerLvl" >
            <option value="">请选择</option>
            <option <c:if test="${r.dangerLvl=='一级' }">selected="selected"</c:if>>一级</option>
            <option <c:if test="${r.dangerLvl=='二级' }">selected="selected"</c:if>>二级</option>
            <option <c:if test="${r.dangerLvl=='三级' }">selected="selected"</c:if>>三级</option>
            <option <c:if test="${r.dangerLvl=='四级' }">selected="selected"</c:if>>四级</option>
          </select>
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-3">备案日期：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="text" value="${r.dangerRecordDate }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="dangerRecordDate" class="input-text Wdate mb-5 mt-5" style="width:350px;">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-3">有效期：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <input type="text" value="${r.dangerEndDate }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="dangerEndDate" class="input-text Wdate mb-5 mt-5" style="width:350px;">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-4 col-sm-3">物质及存量：</label>
        <div class="formControls col-xs-8 col-sm-9">
          <button class="btn radius btn-warning size-M" type="button" onClick="adddan()"><i class="Hui-iconfont">&#xe600;</i>添加物质、存量及储存装置</button>
        </div>
      </div>
      
      <script type="text/javascript">
      $(".sel01").change(function(){
        $(this).val() == '1' ? $(".divd1").show() : $(".divd1").hide();
      })
      $(".sel02").change(function(){
        $(this).val() == '1' ? $(".divd2").show() : $(".divd2").hide();
      })
      $(".sel03").change(function(){
        $(this).val() == '1' ? $(".divd3").show() : $(".divd3").hide();
      })
      $("#chemic").val('${r.chemic}').change();
      $("#process").val('${r.process}').change();
      $("#cisDanger").val('${r.cisDanger}').change();
      </script>
      <!-- 判断添加物质及存量后显示 -->
      <table class="table table-border table-bordered table-bg table-hover table-sort mt-20" style="width:88%;margin-left:6%;">
        <thead>
          <tr class="text-c">
            <th width="5%">序号</th>
            <th width="30%">构成重大危险源的主要物质</th>
            <th width="30%">现场理论最大存量</th>
            <th width="30%">使用/储存装置</th>
            <th width="5%">操作</th>
            <th style="display: none;">经纬</th>
          </tr>
        </thead>
        <tbody id="xxx">
          <!-- 循环检查项目-->
          <c:if test="${!empty r.dangerMaterial}">
          <c:forEach items="${fn:split(r.dangerMaterial, ',') }" var="be" varStatus="index">
          <tr class="text-c" >
            <td>${index.index + 1}</td>
            <td>${fn:split(be, '/')[0] }</td>
            <td>${fn:split(be, '/')[1] }</td>
            <td>${fn:split(be, '/')[2] }</td>
            <!--<c:choose>
              <c:when test="${! empty fn:split(be, '/')[2]}"><td id = "1" jw="1">${fn:split(be, '/')[1] }</td></c:when>
              <c:when test="${empty fn:split(be, '/')[2]}"><td id="2"　jw="2">${fn:split(be, '/')[1] }</td></c:when>
            </c:choose>-->
            <!--<td id="${index.index}_jw" style="display: none;">${fn:split(be, '/')[2] }</td> <!--经纬坐标 add by zhangcl 2018.12.10-->
            <td>
              <a style="text-decoration:none" onclick="del(this)" href="javascript:;" title="删除">删除</a>
            </td>
            <td style="display: none;">${fn:split(be, '/')[3] }</td>
          </tr>
          </c:forEach>
          </c:if>
          <!-- 循环结束 -->
        </tbody>
      </table>
    </div>
    <script type="text/javascript">
    function del(obj) {
      $(obj).closest("tr").remove();
      index_load();
    }
    
    function index_load() {
      $("#xxx tr").each(function(i, item) {
        $(this).find("td").eq(0).text(i + 1);
      })
    }
    </script>
    
    
    <div class="row cl" align="center">
        <button onClick="save_submit()" class="btn btn-primary radius" type="button" style="padding: 0 70px;">
          <i class="Hui-iconfont">&#xe632;</i>保存
        </button>
    </div>
  </form>
  
  <!-- 弹窗添加 -->
  <div id="add-dan" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header">
                <h3 class="modal-title">添加物质及存量</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body">
              <div class="row cl mt-15">
                <label class="form-label col-xs-4 col-sm-2" style="width:36%;text-align:right;">构成重大危险源的主要物质：</label>
                <div class="formControls col-xs-8 col-sm-9" style="width:60%;">
                  <input type="text" id="x" value="" style="width:300px" class="input-text required">
                </div> 
              </div>
              <div class="row cl mt-15">
                <label class="form-label col-xs-4 col-sm-2" style="width:36%;text-align:right;">现场理论最大存量：</label>
                <div class="formControls col-xs-8 col-sm-9" style="width:60%;">
                  <input type="text" id="y" value="" style="width:300px" class="input-text required">
                </div> 
              </div>
              <div class="row cl mt-15">
                <label class="form-label col-xs-4 col-sm-2" style="width:36%;text-align:right;">储存装置：</label>
                <div class="formControls col-xs-8 col-sm-9" style="width:60%;">
                  <input type="text" id="z" value="" style="width:300px" class="input-text required">
                </div> 
              </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" onclick="upload()" >确定</button>
                <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
  </div>
</div>
</body>
<script type="text/javascript">
function upload() {
  if($("#x").val() == '') {
    layer.alert("请输入构成重大危险源的主要物质");
    return false;
  }
  if($("#y").val() == '') {
    layer.alert("请输入现场理论最大存量");
    return false;
  }
  $("#xxx").append('<tr class="text-c" ><td></td>' +
      '<td>' + $("#x").val() + '</td>' +
      '<td>' + $("#y").val() + '</td>' +
      '<td>' + $("#z").val() + '</td>' +
      '<td><a style="text-decoration:none" onclick="del(this)" href="javascript:;" title="删除">删除</a></td></tr>');
  index_load();
  $("#x").val('');
  $("#y").val('');
  $("#z").val('');
  $("#add-dan").modal("hide");//${fn:split(be, '/')[3]}
}


function save_submit() {
  var i = layer.load();
  var process_name = "";
  $(":checkbox[name='processName']:checked").each(function(){
    var v = $(this).val();
    process_name = process_name == '' ? v : process_name + "," + v;
  })
  var danger_material = "";
  $("#xxx tr").each(function(i, item) {
  	//var v = $(this).children("td").eq(1).text() + '/' + $(this).children("td").eq(2).text();
  	//alert($(this).children("td").eq(4).text());
    var v = $(this).children("td").eq(1).text() + '/' + $(this).children("td").eq(2).text()+ '/' + $(this).children("td").eq(3).text()+ '/' + $(this).children("td").eq(5).text();//hejy 2019.1.10
    danger_material = danger_material == '' ? v : danger_material + "," + v;
  })
  $.post(getRootPath() + "/company/information/information3-save",{
    id : id_,
    chemic : $("#chemic").val(),
    chemicName : $("#chemic").val() == '1' ? $("#chemicName").val() : '',
    chemicControl : $("#chemic").val() == '1' ? $("#chemicControl").val() : '',
    process : $("#process").val(),
    processName : $("#process").val() == '1' ? process_name : '',
    processControl : $("#process").val() == '1' ? $("#processControl").val() : '',
    processRecordDate : $("#process").val() == '1' ? $("#processRecordDate").val() : '',
    cisDanger : $("#cisDanger").val(),
    dangerLvl : $("#cisDanger").val() == '1' ? $("#dangerLvl").val() : '',
    dangerRecordDate : $("#cisDanger").val() == '1' ? $("#dangerRecordDate").val() : '',
    dangerEndDate : $("#cisDanger").val() == '1' ? $("#dangerEndDate").val() : '',
    dangerMaterial : $("#cisDanger").val() == '1' ? danger_material : ''
  },function(result) {
    layer.close(i);
    if(result.status == '1') {
      layer.alert(result.map.message);
    } else {
        show_tab('两重点一重大', '/company/information/information3');
    }
  })
}
</script>
</html>