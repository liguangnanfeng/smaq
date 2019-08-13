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
body .check-box, .radio-box{line-height:30px;}
.div_pdetail{width:100%;font-size:15px;}
.div_pdetail font{float:left;font-size:15px;margin-left:15px;font-weight:bold;}
.div_pdetail label{float:right;font-size:15px;margin-right:15px;}
.div_imgp{float:left;width:90%;margin-left:5%;text-align: center;}
.div_imgp img{max-width:100%}
.div_pleft{width:150px;display: inline-block;line-height:32px;font-size:14px;}
.div_pright{width:300px;display: inline-block;}
.div_pri{width:100%;min-height: 32px}
.div_pri font{float:right;line-height:32px;font-size:14px;margin-right:15px;}
.div_pri .Wdate{float:right;}
body .select{height:32px;line-height:32px;}
body .radio-box{float:Left;}
</style>
<script type="text/javascript">
  console.log("我是c/check-fuadd");
function showpicture(src, obj){

  $("#modal-plan2 .div_imgp").html('');
  $.each(src.split(","), function(i, item) {
    $(".div_imgp").append('<div class="div_imgp">' + 
        '<img src="'+item+'" class="img-responsive mt-20" >' +
        '</div>');
  })
  $("#modal-plan2").modal("show")}
</script>
  <script src="/js/jquery.jqprint-0.3.js"></script>
  <script type="text/javascript">
  function pr_() {
  $('.btn-primary').css('display','none')
  $(".page-container").jqprint();
  setTimeout(function () {
  $('.btn-primary').css('display','inline-block')
  },2000)
  }
  </script>
<script type="text/javascript">
function uploadpicture(obj){
  var td = $(obj).closest("td");
  window.itemId = td.attr("data-i");
  var file = td.attr("data-file");
  $("#pic3").attr("src", file==""?"/images/zwtp.jpg":file).attr("url", file);
  $("#modal-plan").modal("show");
}
</script>
</head>
<body>
<div class="page-container">
  <div class="div_pdetail">
  
   <c:choose>
    <c:when test="${check.flag == 1}">
      <button onClick="pr_()" class="btn btn-primary radius" type="button">
      <i class="Hui-iconfont">&#xe652;</i>打印
      </button>
       <%--<button onClick="top.show_tab('复查意见表', '/company/recheck-add-print?checkId=${check.id}')" class="btn btn-success radius" type="button" style="padding: 0 70px;">--%>
        <%--<i class="Hui-iconfont mr-10">&#xe652;</i><!-- 打印 -->打印预览--%>
      <%--</button>--%>
    </c:when>
    <c:otherwise>
      <button onClick="pr_()" class="btn btn-primary radius" type="button">
      <i class="Hui-iconfont">&#xe652;</i>打印
      </button>
      <%--<button onClick="parent.show_dialog('复查意见书', '/village/check-document?checkId=${check.id}&flag=2')" class="btn btn-success radius" type="button" style="padding: 0 70px;">--%>
        <%--<i class="Hui-iconfont mr-10">&#xe652;</i><!-- 打印文书 -->打印预览--%>
      <%--</button>--%>
    </c:otherwise>
    </c:choose>
  </div>
  <h2 class="text-c mb-10">隐患整改复查意见表</h2>
  <div class="div_pdetail">
    <font>受检${check.flag == 1 ? '部门' : '单位'}：${check.companyName}</font>
    <label>复检日期：<fmt:formatDate value="${now }" pattern="yyyy年MM月dd日"/></label>
  </div>
  <div class="f-l mt-20" style="width:100%">
    <table class="f-l table table-border table-bordered table-bg table-hover table-sort">
      <thead>
        <tr class="text-c">
          <th width="3%">序号</th>
          <%--<th width="15%">部门/装置</th>--%>
          <th width="10%">检查部位</th>
          <th>检查类型</th>
          <th>检查方式</th>
          <th>检查项目</th>
          <th width="30%">检查内容</th>
          <th width="15%">整改期限</th>
          <th width="12%">检查结果</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${itemL }" var="be" varStatus="index">
        <tr>
          <td class="text-c">${index.index + 1}</td>
          <td class="text-c"><c:if test="${!empty be.partImg}">
            <img src="${be.partImg }" class="mr-10" style="height:100px;"/>
            </c:if>${be.partName }</td>
            <td class="text-c">
          <c:if test="${be.industry_type==1}">基础检查</c:if>
          <c:if test="${be.industry_type==2}">现场检查</c:if>
          <c:if test="${be.industry_type==3}">高危检查</c:if>
          </td>
            <td class="text-c">
          <c:if test="${be.type==1}">日常检查</c:if>
          <c:if test="${be.type==2}">定期检查</c:if>
          <c:if test="${be.type==3}">季节检查</c:if>
          <c:if test="${be.type==4}">其他检查</c:if>
          <c:if test="${be.type==5}">综合检查</c:if>
          </td>
            <td class="text-c">${be.levels }</td>

          
          <!--<td class="text-c">zhangcl ${be.id }</td>-->
          
          
          <td class="text-c"><%-- ${be.content } --%>
          <c:if test="${!empty be.memo }">${be.memo }</c:if>
          <c:if test="${!empty be.files }"><img alt="" src="${be.files }" style="max-height:100px;cursor:pointer;" onClick="showpicture('${be.files}')"/></c:if>
          </td>
          </td>
          <td class="text-c"><fmt:formatDate value="${be.deadline }"/></td>
          <td data-n="xxx" class="text-c" data-i="${be.id }" data-file="" data-status="2" data-dead="<fmt:formatDate value="${be.deadline }" pattern="yyyy-MM-dd"/>">&nbsp;
            <input type="hidden" name="memo">
            <div class="radio-box">
              <input type="radio" value="3" name="plan-radio_${be.id }" onClick="uploadpicture(this)">
              <label>合格</label>
            </div>
            <div class="radio-box">
              <%-- <input type="radio" value="2" name="plan-radio_${be.id }" onClick="uploadpicture(this)"> --%>
              <input type="radio" value="2" name="plan-radio_${be.id }" checked="checked">
              <label>不合格</label>
            </div>
          </td>
        </tr>
        </c:forEach>
        <tr>
          <td colspan="8">
            <div class="div_pri  mt-10">针对上次隐患整改要求，经复查，提出如下复查意见：</div>
            <div class="div_pri  mt-10">1、已整改项：基本符合安全要求</div>
            <div class="div_pri">
             <!-- 循环 -->
             <c:forEach items="${itemL }" var="be" varStatus="index">
              <div class="check-box">
                <input type="checkbox" name="isli" data-i="${be.id }" disabled="disabled"/>
                <label>${index.index + 1}</label>
              </div>
             </c:forEach>
              <!-- 循环结束 -->
            </div>
            <div class="div_pri  mt-10">2、未整改项：</div>
            <div class="div_pri">
             <!-- 循环 -->
              <c:forEach items="${itemL }" var="be" varStatus="index">
              <div class="check-box">
                <input type="checkbox" name="isxian" data-i="${be.id }" disabled="disabled" checked="checked"/>
                <label>${index.index + 1}</label>
              </div>
              </c:forEach>
              <!-- 循环结束 -->
            </div>
            <div class="div_pri  mb-10  mt-10">
              <font style="float:Left;">3、对未整改项要求于</font>
              <input type="text" id="nextTime" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})" class="input-text Wdate" style="float:left;width:250px;">
              <font style="float:Left;">整改完毕</font>
            </div>
          </td>
        </tr>
        <tr>
          <td colspan="5">
            <c:if test="${check.flag > 1}">
            <div class="div_pleft  mt-10 mb-10">检查部门/单位：</div>
            <div class="div_pright  mt-10 mb-10">
              <input type="text" style="width:150px" id="checkCompany" value="${check.checkCompany }" class="input-text" maxlength="50"/>
            </div><br/>
            </c:if>
            <div class="div_pleft  mt-10 mb-10">检查人员签字：</div>
            <div class="div_pright  mt-10 mb-10">
              <input type="text" id="checker" style="width:150px" value="${check.cheker }" class="input-text" maxlength="50"/>
            </div>
            <div class="div_pri mt-10">
              <font><fmt:formatDate value="${now }" pattern="yyyy年MM月dd日"/></font>
            </div>
          </td>
          <td colspan="3">
            <div class="div_pleft  mt-10 mb-10">受检负责人签字：</div>
            <div class="div_pright  mt-10 mb-10">
              <input type="text" id="dapartContact" style="width:150px" value="${check.dapartContact }" class="input-text" maxlength="50"/>
            </div>
            <div class="div_pri mt-10">
              <font><fmt:formatDate value="${now }" pattern="yyyy年MM月dd日"/></font>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
  <div class="row cl">
    <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2  mt-20" style="text-align:center;margin-left:0">
      <button onClick="article_save_submit()" class="btn btn-primary radius" type="button" style="padding: 0 70px;">
          <i class="Hui-iconfont">&#xe632;</i>保存
      </button>
    </div>
  </div>
</div>

  <!-- 弹窗图片 -->
  <div id="modal-plan" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
          <div class="modal-content radius">
              <div class="modal-header">
                  <h3 class="modal-title">复查信息</h3>
                  <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
              </div>
              <div class="modal-body">
                <div class="row cl">
                  <label class="form-label col-xs-4 col-sm-2" style="width:20%;text-align:right;">复查描述：</label>
                  <div class="formControls col-xs-8 col-sm-9" style="width:70%;">
                    <textarea id="memo" class="textarea txtarea_sq" style="width:350px;" maxlength="500"></textarea>
                  </div> 
                </div>
                <div class="row cl mt-15">
                <label class="form-label col-xs-4 col-sm-2" style="width:20%;text-align:right;">复查图片：</label>
                <div class="formControls col-xs-8 col-sm-9" style="width:70%;">
                  <img id="pic3" src="${ly }/images/zwtp.jpg" style="width:200px;min-height:200px;" url=""/>
                  <a href="javascript:void(0);" onclick="img_upload('pic3', null)" class="btn btn-primary radius upload-btn mt-10"><i class="Hui-iconfont">&#xe642;</i> 上传图片</a>
                </div> 
              </div>
              </div>
              <div class="modal-footer">
                <button class="btn btn-primary" onclick="upload()" >确定</button>
                <button class="btn" data-dismiss="modal" onclick="esc_()">关闭</button>
            </div>
          </div>
      </div>
  </div>
  
  <!-- 弹窗图片 -->
  <div id="modal-plan2" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
          <div class="modal-content radius">
              <div class="modal-header">
                  <h3 class="modal-title">查看详情</h3>
                  <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
              </div>
              <div class="modal-body" style="height:400px;overflow-y:auto">
                <!-- 循环图片 -->
                <div class="div_imgp">
                  <img src="${ly }/images/zwtp.jpg" class="img-responsive mt-20" >
                </div>
                <!-- 循环结束 -->
              </div>
          </div>
      </div>
  </div>
</body>
<script type="text/javascript">
var checkId = ${check.id};
function upload() {
  $("td[data-i='"+itemId+"']").attr("data-file", $("#pic3").attr("url"));
  $("td[data-i='"+itemId+"']").find("input[name='memo']").val($("#memo").val());
  $("#modal-plan").modal("hide");
  $("#memo").val('');
  $("#pic3").attr("url", "").attr("src", "/images/zwtp.jpg");
}

$(function(){
  $(":radio").click(function() {
    var this_ = $(this);
    var td = this_.closest("td");
    var s = this_.val();
    if(s) {
      td.attr("data-file", "");
    }
    td.attr("data-status", s);
    $(":checkbox[data-i='"+td.attr("data-i")+"'][name='"+(s=='2'?'isxian':'isli')+"']").prop("checked", true);
    $(":checkbox[data-i='"+td.attr("data-i")+"'][name='"+(s=='2'?'isli':'isxian')+"']").prop("checked", false);
    
    check_time();
  })
})

//全部整改了 不用填写时间
function check_time() {
  if($(":checkbox[name='isxian']:checked").length > 0) {
    $("#nextTime").removeAttr("readonly").removeAttr("disabled");
  } else {
    $("#nextTime").val('').attr("readonly", "readonly").attr("disabled","disabled");
  }
}

function article_save_submit() {
  if($("td[data-status='']").length > 0) {
    layer.alert("请复查所有项目再提交");
    return false;
  }
  if($(":checkbox[name='isxian']:checked").length > 0 && $("#nextTime").val() == '') {
    layer.alert("请选择未整改项期限");
    return false;
  }
  
  if($("#cheker").val() == '') {
    layer.alert("检查人不能为空");
    return false;
  }
  if($("#checkCompany").length > 0) {
    if($("#checkCompany").val() == '') {
      layer.alert("检查部门/单位不能为空");
      return false;
    }
  }
  if($("#dapartContact").val() == '') {
    layer.alert("被检查部门负责人不能为空");
    return false;
  }
  
  var ind = layer.load();
  var obj = new Object();
  var recheck = new Object();
  recheck.checkId = checkId;
  recheck.status = $(":checkbox[name='isxian']:checked").length > 0 ? 1 : 2;
  recheck.nextTime = $("#nextTime").val();
  recheck.checker = $("#checker").val();
  recheck.checkCompany = $("#checkCompany").val();
  recheck.dapartContact = $("#dapartContact").val();
  
  obj.recheck = recheck;
  obj.nextTime= $("#nextTime").val();
  var list = new Array();
  $("td[data-n='xxx']").each(function() {
    var itemid = $(this).attr("data-i");
    var file = $(this).attr("data-file");
    var status = $(this).attr("data-status");
    var it = new Object();
    it.checkItemId = itemid;
    it.status = status;
    it.file = file;
    it.deadline = $(this).attr("data-dead");
    it.memo = $(this).find("input[name='memo']").val();
    list.push(it);
  })
  obj.list = list;
  
  //alert(JSON.stringify(obj));
  
  $.ajax({
    type : "POST",
    url : getRootPath() + '/company/recheck-save',
    //url : getRootPath() + '/api/tongx/A005',//debug api zhangcl 2018.11.14
    data : JSON.stringify(obj),
    async: false,
    dataType : "json",
    contentType : 'application/json',
    success : function(result) {
    	//alert(JSON.stringify(result));
      layer.close(ind);
      if(result.status == '0') {
  <%--location.replace(location.href);--%>
        top.ref_ifm();
  let flag=1;
  <%--//新版跳转--%>
  <%--console.log("wjjjjj")--%>
  console.log($(":radio[value='2']:checked").length);
  if ($(":radio[value='2']:checked").length > 0) {
  console.log('不合格')
  <%--top.show_tab("治理意见表_" + checkId, '/village/check-rectification?flag=' + flag + '&id=' + checkId);--%>
  top.show_tab("治理意见表_" + checkId, '/village/check-rectification?flag=' + flag + '&id=' + checkId);
  } else {
  console.log('合格')
  var url = '/company/recheck-detail?checkId=' + checkId+'&flag=' + flag + '&number=${be.c }';
  <%--var url = '/company/recheck-detail?checkId=${be.id}&flag=${flag}&number=${be.c }';--%>
  <%--top.show_tab(url);--%>
  window.location.href = url
  }

  <%--parent.location.reload();--%>
      } else {
        layer.alert(result.map.message);
      }
    },
    complete : function(XMLHttpRequest, textStatus) {
      layer.close(ind);
    },
    error : function(XMLHttpRequest, textStatus, errorThrown) {
      alert("保存失败")
    }
  });
}
</script>
</html>