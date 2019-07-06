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
<link rel="stylesheet" type="text/css" href="${ly}/styles/print.css" />
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style type="text/css">
.oneyhms{margin:0;padding:0;display:inline-block;line-height:17px;font-size:13px;word-wrap:break-word;word-break:break-all;white-space:normal;overflow:hidden;}
.check-box{margin-top:15px;}
</style>
</head>
<body>
    <div class="page-container" style="width:150mm;margin:auto;padding:10px 0 0 0;">
        <div class="row cl row_ac" >
            <script type="text/javascript">
            function llo(src) {
              $("#zhang_img").html('<img src="' + src + '" style="height:180px;"/>')
            }
            </script>
            <button onClick="img_upload(null, null, llo)" class="btn radius" type="button">
                <i class="Hui-iconfont mr-10">&#xe600;</i>上传检查单位盖章
            </button>
            <button onClick="save_()" class="btn radius" type="button">
                <i class="Hui-iconfont mr-10">&#xe632;</i>保存
            </button>
            <button onClick="pr_()" class="btn radius" type="button">
                <i class="Hui-iconfont mr-10">&#xe652;</i>打印
            </button>
            <button onClick="top.show_tab('检查文书', '${ly }/village/check-list?flag=2')" class="btn radius" type="button" >
                <i class="Hui-iconfont mr-10">&#xe66b;</i>返回列表
            </button>
            <%-- <button onClick="top.show_tab('检查文书', '${ly }/village/opinion-show?flag2=2')" class="btn radius" type="button" >
                <i class="Hui-iconfont mr-10">&#xe66b;</i>返回文书
            </button> --%>
        </div>
        <div class="row cl mb-20">
          <div class="tabBar clearfix">
            <a href="/village/check-document?checkId=${check.id}&flag=8">现场检查记录</a>
            <a class="current" href="JavaScript:void(0)">责令限期整改意见书</a>
              <c:if test="${is_fu==1}">
                  <a  href="/village/check-document?checkId=${check.id}&flag=2">整改复查意见书</a>
              </c:if>
              <c:if test="${is_fu==0}">
                  <a href="javascript:void(0)" onclick="layer.msg('请先进行复查');setTimeout(function(){window.location.href='${ly}/company/recheck-add?checkId=${check.id}'},1000)">整改复查意见书</a>
<%--                  <a href="javascript:void(0)" onclick="layer.alert('请先进行复查')">整改复查意见书</a>--%>
              </c:if>

          </div>
        </div>
        <div id="container" style="width:150mm;margin:auto;padding:50px 0 0px 0;">
        <p class="dant1  mt-20" style="text-align:center;border-bottom:1px solid #000;margin-bottom:3px;" align="center">
            <b><span style="font-family:华文中宋;font-weight:bold;font-size:18pt;"><span>安全生产行政执法文书</span></span></b>
        </p>
        <p class="dant1" style="text-align:center;border-top:1px solid #000;margin-bottom:3px;" align="center">
            <span></span><b><span style="font-family:华文中宋;font-weight:bold;font-size:18pt;"><span>责令限期整改指令书</span></span></b><b><span style="font-family:华文中宋;font-weight:bold;font-size:18.0000pt;"></span></b>
        </p>
        <p class="MsoNormal" style="text-align:center;margin-top:10px;" align="center">
            <span style="font-size:14.7pt;"><span contenteditable="true">（&nbsp;&nbsp;&nbsp;）</span><span>安监责改</span><span contenteditable="true">〔&nbsp;&nbsp;&nbsp;〕</span> <span>号</span></span>
        </p>
        <p class="MsoNormal" style="margin-top:30px;">
            <u><span style="float:left;text-decoration:underline;" contenteditable="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${company.name }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u><span style=""><span>：</span></span><span style=""></span>
        </p>
        <p class="MsoNormal" style="text-indent:27.7500pt;">
            <span style=""><span>经查，你单位存在下列问题：</span></span><span style=""></span>
        </p>
        <div id="div_dangerm">
        <c:set value="" var="xx"/>
        <c:set value="" var="yy"/>
        <c:forEach items="${itemL }" var="be" varStatus="index">
            <c:if test="${lanlin:constains(rectification.item2,be.id,',') }">
              <c:choose>
              <c:when test="${xx == ''}">
                <c:set value="${index.index + 1}" var="xx"/>
              </c:when>
              <c:otherwise>
                <c:set value="${xx },${index.index + 1}" var="xx"/>
              </c:otherwise>
              </c:choose>
            </c:if>
            <c:if test="${lanlin:constains(rectification.item3,be.id,',') }">
              <c:choose>
              <c:when test="${yy == ''}">
                <c:set value="${index.index + 1}" var="yy"/>
              </c:when>
              <c:otherwise>
                <c:set value="${yy },${index.index + 1}" var="yy"/>
              </c:otherwise>
              </c:choose>
            </c:if>
            <p class="MsoNormal" style="text-indent:21.0000pt;">
                <span>${index.index + 1}．</span><u><span style="text-decoration:underline;text-indent: 0;" contenteditable="true">&nbsp;${be.memo }</span></u>
            </p>
        </c:forEach>
        </div>
        <p class="MsoNormal" style="text-indent:27.7500pt;">
            <span style="vertical-align: bottom;"><span>其中事故隐患                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     第</span></span>
            <u><span style="border-bottom:1px solid #333;width:30%;display:inline-block;vertical-align: text-bottom;text-indent: 0;">&nbsp;&nbsp;${yy }</span></u>
            <span style="vertical-align: bottom;"><span>项属于重大隐患。</span></span>
        </p>
        <p class="MsoNormal" style="text-indent:23.0000pt;text-align:left;line-height:28px;">
            <span style=""><span>现责令</span></span><span style=""><span>你单位</span></span><span style=""><span>对上述第</span></span>
            <u><span style="text-decoration:underline;text-indent: 0;">&nbsp;${empty xx ? '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' : xx}</span></u>
            <span style=""><span>项问题</span></span>
            <span style=""><span>于</span></span>
            <u><span style="text-decoration:underline;text-indent: 0;">&nbsp;<fmt:formatDate value="${!empty rectification ? rectification.deadline : timenow }" pattern="yyyy"/></span></u>
            <span style=""><span>年</span></span>
            <u><span style="text-decoration:underline;text-indent: 0;">&nbsp;<fmt:formatDate value="${rectification.deadline}" pattern="MM"/></span></u>
            <span style=""><span>月</span></span>
            <u><span style="text-decoration:underline;text-indent: 0;">&nbsp;<fmt:formatDate value="${rectification.deadline}" pattern="dd"/></span></u>
            <span style=""><span>日前整改完毕，达到有关法律法规规章和标准规定的要求。由此造成事故的，依法追究有关人员的责任。</span></span>
            <span style=""></span>
        </p>
        <p class="MsoNormal" style="text-indent:21.0000pt;line-height:28px;">
            <span style=""><span>整改期间，你单位应当采取措施，确保安全生产。对安全生产违法行为，将依法予以行政处罚。</span></span><span style=""></span>
        </p>
        <p class="MsoNormal" style="text-indent:21.0000pt;line-height:28px;">
            <span style=""><span>如果不服本指令，可以依法在</span>60日内向 </span>
            <u><span style="text-decoration:underline;text-indent: 0;" contenteditable="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u>
            <span style="">&nbsp;<span>人民政府或者</span></span>
            <u><span style="text-decoration:underline;text-indent: 0;word-wrap:break-word;word-break:break-all;white-space:normal;overflow:hidden;" contenteditable="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u>
            <span style=""><span>申请行政复议，或者在</span>6个月内依法向</span>
            <u><span style="text-decoration:underline;text-indent: 0;" contenteditable="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u>
            <span style=""><span>人民法院提起行政诉讼，但本指令不停止执行，法律另有规定的除外。</span></span>
        </p>
        
        <p class="MsoNormal" style="margin-top:15px;">
            <span style=""><span>安全生产监管行政执法人员（签名）：</span> </span>
            <u><span style="text-decoration:underline;" contenteditable="true">&nbsp;&nbsp;${check.cheker}&nbsp;&nbsp;&nbsp;</span></u>
            <span style=""><span>证号：</span></span>
            <u><span style="text-decoration:underline;" contenteditable="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u><span style="">&nbsp;</span><span style=""></span>
        </p>
        <p class="MsoNormal" >
            <span style="visibility:hidden"><span>安全生产监管行政执法人员（签名）：</span> </span>
            <u><span style="text-decoration:underline;text-indent: 0;" contenteditable="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u>
            <span style=""><span>证号：</span></span>
            <u><span style="text-decoration:underline;text-indent: 0;" contenteditable="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u>
            <span style="">&nbsp;</span>
            <span style=""></span>
        </p>
        <p class="MsoNormal" style="margin-top:15px;">
            <span style=""><span>被检查单位负责人（签名）：</span></span>
            <u><span style="text-decoration:underline;" contenteditable="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${company.charge}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u>
            <u><span style="text-decoration:underline;"></span></u>
        </p>
        
        <p class="MsoNormal" style="text-align:right;margin-top:50px">
            <span style=""><span>安全生产监督管理部门（印章）</span></span><span style=""></span>
            <span style="" id="zhang_img">
              <%--  <c:if test="${check.checkCompany eq '无锡市安泰安全技术服务有限公司' }">
                <img src="${ly }/images/zhang.png" style="height:180px;"/>
                </c:if>--%>
            </span>
        </p>
        <p class="MsoNormal" style="text-align:right">
            <span style=""><span contenteditable="true">&nbsp;<fmt:formatDate value="${timenow }" pattern="yyyy"/>&nbsp;</span>年<span contenteditable="true">&nbsp;&nbsp;<fmt:formatDate value="${timenow }" pattern="MM"/>&nbsp;</span>月<span contenteditable="true">&nbsp;&nbsp;<fmt:formatDate value="${timenow }" pattern="dd"/>&nbsp;</span>日</span><span style=""></span>
        </p>
        <p class="MsoNormal" style="border-top:1px solid #000;">
            <span style=""><span>本文书一式两份：一份由安全生产监督管理部门备案，一份交被检查单位。</span> </span><span style=""></span>
        </p>
        <p class="MsoNormal" style="text-align:right;" align="right">
            <span style=""><span></span><span></span></span><span style=""></span>
        </p>
        <p class="MsoNormal" style="text-align:right">
            <span style="">共<span contenteditable="true">&nbsp;&nbsp;</span>页&nbsp;&nbsp; 第<span contenteditable="true">&nbsp;&nbsp;</span>页
        </p>
        <br />
        </div>
    </div>
         
         <!-- 弹窗隐患 -->    
         <div id="modal-demo" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard=false>
        <div class="modal-dialog">
            <div class="modal-content radius">
                <div class="modal-header">
                    <h3 class="modal-title">隐患意见</h3>
                    <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
                </div>
                <div class="modal-body">
                  <div class="row cl">
                    <label class="form-label col-sm-2" style="width:20%">隐患描述：</label>
                    <div class="formControls col-sm-8" style="width:60%">
                      <!-- 循环 -->
                      <c:forEach items="${itemL }" var="be" varStatus="index">
                        <p class="oneyhms">${index.index + 1}.<span data-lid="${be.levelId }" data-name="item-memo">${be.memo }</span></p><br>
                      </c:forEach>
                    </div> 
                  </div>
                  <div class="row cl" style="margin-top:15px;">
                    <label class="form-label col-sm-2" style="width:80%">针对上述隐患，根据有关规定，现提出如下整改要求：</label>
                  </div>
                  <div class="row cl" style="margin-top:15px;">
                    <label class="form-label col-sm-2" style="width:80%">1、对上述隐患进行限期整改</label>
                  </div>
                  <div class="row cl" style="display: none;">
                    <div class="formControls col-sm-2" style="width:80%">
                     <!--  循环 -->
                      <c:forEach items="${itemL }" var="be" varStatus="index">
                        <div class="check-box">
                          <input type="checkbox" name="isxian" data-i="${be.id }" checked="checked"/>
                          <label>${index.index + 1}</label>
                        </div>
                       </c:forEach>
                      <!-- 循环结束 -->
                    </div> 
                  </div>
                  <div class="row cl" style="margin-top:15px;">
                    <div class="form-label col-sm-2" style="width:80%">期限：
                      <input type="text" id="deadline" value=""  class="input-text Wdate" onfocus="WdatePicker({minDate:'<fmt:formatDate value="${check.realTime }" pattern="yyyy-MM-dd"/>',dateFmt:'yyyy-MM-dd'})" style="width:250px;">
                    </div>
                  </div>
                  <div class="row cl" style="margin-top:15px;">
                    <div class="form-label col-sm-2" style="width:80%">2、对下列隐患属于重大隐患
                      <div class="check-box">
                        <input type="checkbox" name="yz_qx"/>
                        <label>全选</label>
                      </div>
                    </div>
                  </div>
                  <div class="row cl">
                    <div class="formControls col-sm-2" style="width:80%">
                      <!--  循环 -->
                      <c:forEach items="${itemL }" var="be" varStatus="index">
                        <div class="check-box">
                          <input type="checkbox" name="isyan" data-i="${be.id }"/>
                          <label>${index.index + 1}</label>
                        </div>
                      </c:forEach>
                      <!-- 循环结束 -->
                    </div> 
                  </div>
                  <div class="row cl" style="margin-top:15px;">
                    <div class="form-label col-sm-2" style="width:80%">3、整改复查时间：
                      <input type="text" id="planTime" value=""  class="input-text Wdate" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'deadline\')||$dp.$DV(\'<fmt:formatDate value="${check.realTime }" pattern="yyyy-MM-dd"/>\');}',dateFmt:'yyyy-MM-dd'})" style="width:250px;">
                    </div>
                  </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" onclick="savex_()" type="button">确定</button>
                    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
                </div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
var checkId = ${checkId};
var userId = ${company.userId };
var rectificationId = '${rectification.id}';
function save_() {
	if(rectificationId == '') {
    $("#modal-demo").modal("show");
    return;
  	}
  $(".dustbin").hide();
  $.post(getRootPath() + "/village/danger/opinion-save", {
      userId: userId,
      checkId : checkId,
      copy: $("#container").html(),
      flag: 1,
      flag2 : 2
    }, function(reuslt) {
      location.reload();
    })
}
var serious_i = ${serList};
</script>
<script type="text/javascript" src="${ly }/js/check/opinion1.js"></script>
</html>