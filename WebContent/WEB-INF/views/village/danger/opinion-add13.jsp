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
</style>
</head>
<body>
    <div class="page-container" style="width:250mm;margin:auto;padding:10px 0 0 0;">
        <div class="row cl row_ac">
            <c:if test="${empty check}">
            <button onClick="addDan()" class="btn radius" type="button">
                <i class="Hui-iconfont mr-10">&#xe600;</i>添加检查情况
            </button>
            </c:if>
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
            <button onClick="top.show_tab('检查文书', '${ly }/village/check-list?flag=3')" class="btn radius" type="button">
                <i class="Hui-iconfont mr-10">&#xe66b;</i>返回列表
            </button>
            <%-- <button onClick="top.show_tab('检查文书', '${ly }/village/danger/opinion-list?type=1&flag2=3')" class="btn radius" type="button" >
                <i class="Hui-iconfont mr-10">&#xe66b;</i>返回文书
            </button> --%>
        </div>
        <div class="row cl mb-20">
          <div class="tabBar clearfix">
            <a href="/village/check-document?checkId=${check.id}&flag=8">现场检查记录</a>
            <a class="current" href="javascript:void(0)">隐患整改意见书</a>
            <c:if test="${empty check}">
            <a href="javascript:void(0)" onclick="layer.alert('请先保存隐患整改意见书')">整改复查意见书</a>
            </c:if>
            <c:if test="${!empty check}">
                <!-- 从工具入口，没有隐患的提示 -->
                <c:choose>
                    <c:when test="${empty itemL}">
                    <a href="javascript:void(0)" onclick="layer.alert('本次检查没有隐患，无需整改')">整改复查意见书</a>
                    </c:when>
                    <c:otherwise>
                    <a href="/village/check-document?checkId=${check.id}&flag=2">整改复查意见书</a>
                    </c:otherwise>
                </c:choose>
            </c:if>
          </div>
        </div>
        <div id="container" style="width:150mm;margin:auto;padding:50px 0 0px 0;">
        <p class="dant1  mt-20" style="text-align:center;border-bottom:1px solid #000;margin-bottom:3px;" align="center">
            <b><span style="font-family: 'KaiTi_GB2312';font-weight:bold;font-size:18pt;"><span>隐 患 整 改 意 见 书</span></span></b>
        </p>
        <p class="MsoNormal" style="text-align:right;margin-bottom:15px;border-top:1px solid #000;padding-top:10px;" align="right">
            <span style=""><span contenteditable="true">&nbsp;<fmt:formatDate value="${timenow}" pattern="yyyy"/>&nbsp;</span>年<span contenteditable="true">&nbsp;<fmt:formatDate value="${timenow}" pattern="MM"/>&nbsp;</span>月<span contenteditable="true">&nbsp;<fmt:formatDate value="${timenow}" pattern="dd"/>&nbsp;</span>日</span><span style=""></span>
        </p>
        <p class="MsoNormal" style="margin-top:30px;">
            <u><span style="float:left;vertical-align: bottom;">受检单位</span>
            <span style="float:left;vertical-align: text-bottom;text-decoration:underline;" contenteditable="true">&nbsp;&nbsp;&nbsp;${company.name }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u><span style=""><span>：</span></span><span style=""></span>
        </p>
        <p class="MsoNormal" style="text-indent:27.5pt;text-align:left;line-height:30px;">
            <span style="vertical-align: bottom;"><span>我们受</span></span></span>
            <u><span style="border-bottom:1px solid #333;width:20%;display:inline-block;vertical-align: text-bottom;text-indent: 0;" contenteditable="true">&nbsp;</span></u>
            <span style="vertical-align: bottom;"><span>委托，对你单位进行安全检查。经检查，你单位存在下列事故隐患：</span></span>
            <span style=""></span>
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
            <c:if test="${lanlin:constains(rectification.item1,be.id,',') }">
              <c:choose>
              <c:when test="${zz == ''}">
                <c:set value="${index.index + 1}" var="zz"/>
              </c:when>
              <c:otherwise>
                <c:set value="${zz },${index.index + 1}" var="zz"/>
              </c:otherwise>
              </c:choose>
            </c:if>
            <p class="MsoNormal" style="text-indent:21.0000pt;" >
                <span class="num_dan" style="">${index.index + 1}</span><span style="">．</span><u><span class="yh_span" style="text-decoration:underline;text-indent: 0;"contenteditable="true">${be.memo }&nbsp;&nbsp;</span></u>
                <!-- <i class="Hui-iconfont mr-10 dustbin" style="cursor:pointer;font-size:20px" onclick="removeDan(this)">&#xe609;</i> -->
            </p>
          </c:forEach>
        </div>
        <p class="MsoNormal" style="text-indent:27.5pt;text-align:left;">
            <span style=""><span>针对上列事故隐患，根据国家有关规定，现提出如下整改要求：</span></span>
        </p>
        <p class="MsoNormal" style="text-align:left;">
            <span style="vertical-align: bottom;"><span>一、对下列事故隐患第</span></span>
            <u><span style="border-bottom:1px solid #333;min-width:20%;max-width:100%;display:inline-block;vertical-align: text-bottom;text-indent: 0;" contenteditable="true">&nbsp;${zz }</span></u>
            <span style="vertical-align: bottom;"><span>项进行立即整改：</span></span>
        </p>
        <p class="MsoNormal" style="text-align:left;">
            <span style="vertical-align: bottom;"><span>二、下列事故隐患</span></span>
            <u><span style="border-bottom:1px solid #333;min-width:10%;max-width:100%;display:inline-block;vertical-align: text-bottom;text-indent: 0;" contenteditable="true">&nbsp;${empty xx ? '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' : xx}</span></u>
            <span style="vertical-align: bottom;"><span>应于</span></span>
            <u><span style="border-bottom:1px solid #333;width:10%;display:inline-block;vertical-align: text-bottom;text-indent: 0;" contenteditable="true">&nbsp;<fmt:formatDate value="${!empty rectification ? rectification.deadline : timenow }" pattern="yyyy"/></span></u>
            <span style="vertical-align: bottom;"><span>年</span></span>
            <u><span style="border-bottom:1px solid #333;width:5%;display:inline-block;vertical-align: text-bottom;text-indent: 0;" contenteditable="true">&nbsp;<fmt:formatDate value="${rectification.deadline}" pattern="MM"/></span></u>
            <span style="vertical-align: bottom;"><span>月</span></span>
            <u><span style="border-bottom:1px solid #333;width:5%;display:inline-block;vertical-align: text-bottom;text-indent: 0;" contenteditable="true">&nbsp;<fmt:formatDate value="${rectification.deadline}" pattern="dd"/></span></u>
            <span style="vertical-align: bottom;"><span>日前整改完毕。</span></span>
        </p>
        <p class="MsoNormal" style="text-align:left;">
            <span style="vertical-align: bottom;"><span>三、其中事故隐患第</span></span>
            <u><span style="border-bottom:1px solid #333;min-width:35%;max-width:100%;display:inline-block;vertical-align: text-bottom;text-indent: 0;" contenteditable="true">&nbsp;&nbsp;${yy }</span></u>
            <span style="vertical-align: bottom;"><span>项属于重大隐患。</span></span>
        </p>
        <p class="MsoNormal" style="text-align:left;">
            <span style="vertical-align: bottom;"><span>四、我们将于</span></span>
            <u><span style="border-bottom:1px solid #333;width:15%;display:inline-block;vertical-align: text-bottom;text-indent: 0;" contenteditable="true">&nbsp;<fmt:formatDate value="${!empty rectification ? rectification.planTime : timenow }" pattern="yyyy"/>&nbsp;</span></u>
            <span style="vertical-align: bottom;"><span>年</span></span>
            <u><span style="border-bottom:1px solid #333;width:8%;display:inline-block;vertical-align: text-bottom;text-indent: 0;" contenteditable="true">&nbsp;<fmt:formatDate value="${rectification.planTime}" pattern="MM"/>&nbsp;</span></u>
            <span style="vertical-align: bottom;"><span>月</span></span>
            <u><span style="border-bottom:1px solid #333;width:8%;display:inline-block;vertical-align: text-bottom;text-indent: 0;" contenteditable="true">&nbsp;<fmt:formatDate value="${rectification.planTime}" pattern="dd"/>&nbsp;</span></u>
            <span style="vertical-align: bottom;"><span>日对整改情况进行复查。</span></span>
        </p>
        
        <p class="MsoNormal">
            <span style="vertical-align: bottom;"><span>检查人员（签名）：</span></span>
            <%-- <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom; width:20%;display:inline-block;" contenteditable="true">&nbsp;&nbsp;&nbsp;&nbsp;${check.cheker }&nbsp;&nbsp;&nbsp;&nbsp;</span></u> --%>
            <u><span id="id1" style="border-bottom:1px solid #333;vertical-align: text-bottom; width:15%;text-align: center;display:inline-block;" contenteditable="true">${check.cheker}</span></u>
            &nbsp;&nbsp;&nbsp;<u><span id="id2" style="border-bottom:1px solid #333;vertical-align: text-bottom; width:15%;text-align: center;display:inline-block;" contenteditable="true"></span></u>
        </p>
        <p class="MsoNormal" style="text-align:left;margin-top:10px;height:180px;">
            <span style="float:left;margin: 0 8px 0 0;">检查单位（盖章）:</span>
            
            <span style="float:left;">
            	<span contenteditable="true" style="border-bottom:1px solid #333;float:left;padding: 0 20px;line-height: 22px;height: 22px;">${check.checkCompany}</span>
            </span>
            
            <span style="" id="zhang_img">
                <c:if test="${check.checkCompany eq '无锡市安泰安全技术服务有限公司' }">
                <img src="${ly }/images/zhang.png" style="height:180px;"/>
                </c:if>
            </span>
        </p>
        <p class="MsoNormal" style="text-align:left;margin-top:30px">
            <span style="vertical-align: bottom;"><span>受检单位负责人（签名）：</span></span>
            <u><span id="sjcompany" style="border-bottom:1px solid #333;width:20%;display:inline-block;vertical-align: text-bottom;" contenteditable="true">&nbsp;${check.dapartContact }</span></u>
        </p>
        <p class="MsoNormal">
            <span style="">&nbsp;</span>
        </p>
        <p class="MsoNormal" style="text-align:right">
            <span style=""><span contenteditable="true">&nbsp;<fmt:formatDate value="${timenow}" pattern="yyyy"/>&nbsp;</span>年<span contenteditable="true">&nbsp;<fmt:formatDate value="${timenow}" pattern="MM"/>&nbsp;</span>月<span contenteditable="true">&nbsp;<fmt:formatDate value="${timenow}" pattern="dd"/>&nbsp;</span>日</span><span style=""></span>
        </p>
        <p class="MsoNormal" style="border-top:1px solid #000;">
            <span style=""><span>本文书一式三份：一份交受委托单位，一份交受检单位，一份由检查单位备查。</span> </span><span style=""></span>
        </p>
        </div>
    </div>
    
    <!-- 弹窗隐患 -->    
     <!-- <div id="modal-demo" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content radius">
                <div class="modal-header">
                    <h3 class="modal-title">添加检查情况</h3>
                    <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
                </div>
                <div class="modal-body">
                   <div class="row cl">
                    <label class="form-label col-sm-2" style="width:20%">检查情况：</label>
                    <div class="formControls col-sm-8" style="width:60%">
                      <textarea class="textarea" resize="none" style="height:50px" id="memo_text"></textarea>
                    </div> 
                  </div>
                  <div class="row cl" style="margin-top:15px;">
                    <label class="form-label col-sm-2" style="width:20%">检查结果：</label>
                    <div class="formControls col-sm-8" style="width:60%">
                      <div class="radio-box" style="padding-left:0px;">
                        <input id="x2" type="radio" name="radio-status" value="1">
                        <label for="x2">合格</label>
                      </div>
                      <div class="radio-box" >
                        <input id="x3" type="radio" name="radio-status" value="2" checked="checked">
                        <label for="x3">不合格</label>
                      </div>
                    </div> 
                  </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" id="check_item_btn">确定</button>
                    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
                </div>
            </div>
        </div>
    </div>  --> 
    
     <div id="modal-demo" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard=false>
        <div class="modal-dialog">
            <div class="modal-content radius">
                <div class="modal-header">
                    <h3 class="modal-title">设置限期整改意见</h3>
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
      flag2 : 3
    }, function(reuslt) {
      location.reload();
    })
}
</script>
<script type="text/javascript" src="${ly }/js/check/opinion1.js"></script>
</html>