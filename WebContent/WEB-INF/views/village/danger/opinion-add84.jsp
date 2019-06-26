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
<title>安全分级管控 隐患排查治理智能化信息平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style type="text/css">
</style>
<script type="text/javascript">
function opente(){
  $("#win_te").modal("show")
  }
</script>
</head>
<body>
  <div class="page-container" style="width: 150mm; margin: auto; padding: 10px 0 0 0;">
    <div class="row cl row_ac">
      <c:if test="${empty check}">
        <button onClick="addDan()" class="btn radius" type="button">
          <i class="Hui-iconfont mr-10">&#xe600;</i>添加检查情况
        </button>
      </c:if>
      <button onClick="save_()" class="btn radius" type="button">
        <i class="Hui-iconfont mr-10">&#xe632;</i>保存
      </button>
      <button onClick="pr_()" class="btn radius" type="button">
        <i class="Hui-iconfont mr-10">&#xe652;</i>打印
      </button>
      <button onClick="top.show_tab('检查文书', '${ly }/village/check-list?flag=4')" class="btn radius" type="button">
        <i class="Hui-iconfont mr-10">&#xe66b;</i>返回列表
      </button>
      <%-- <button onClick="top.show_tab('检查文书', '${ly }/village/danger/opinion-list?type=1&flag2=4')" class="btn radius" type="button" >
                <i class="Hui-iconfont mr-10">&#xe66b;</i>返回文书
            </button> --%>
    </div>
    <div class="row cl mb-20">
      <div class="tabBar clearfix">
        <a class="current" href="javascript:void(0)">现场检查记录</a>
        <c:if test="${empty check}">
          <a href="javascript:void(0)" onclick="layer.alert('请先保存现场检查记录')">责令限期整改意见书</a>
          <a href="javascript:void(0)" onclick="layer.alert('请先保存现场检查记录')">整改复查意见书</a>
        </c:if>
        <c:if test="${!empty check}">
          <!-- 从工具入口，没有隐患的提示 -->
          <c:choose>
            <c:when test="${empty itemL}">
              <a href="javascript:void(0)" onclick="layer.alert('本次检查没有隐患，无需整改')">责令限期整改意见书</a>
              <a href="javascript:void(0)" onclick="layer.alert('本次检查没有隐患，无需整改')">整改复查意见书</a>
            </c:when>
            <c:otherwise>
              <a href="/village/check-document?checkId=${check.id}&flag=1">责令限期整改意见书</a>
              <a href="/village/check-document?checkId=${check.id}&flag=2">整改复查意见书</a>
            </c:otherwise>
          </c:choose>
        </c:if>
      </div>
    </div>
    <div id="container" style="width: 150mm; margin: auto; padding: 50px 0 0px 0;">
      <p class="dant1  mt-20" style="text-align: center; border-bottom: 1px solid #000; margin-bottom: 3px;" align="center">
        <b><span style="font-family: 华文中宋; font-weight: bold; font-size: 18pt;"><span>安全生产监督检查文书</span></span></b>
      </p>
      <p class="dant1" style="text-align: center; border-top: 1px solid #000; margin-bottom: 3px;" align="center">
        <span></span><b><span style="font-family: 华文中宋; font-weight: bold; font-size: 18pt;"><span>现场检查记录</span></span></b><b><span
          style="font-family: 华文中宋; font-weight: bold; font-size: 18.0000pt;"></span></b>
      </p>
      <p class="MsoNormal" style="text-align: center; margin-top: 10px;" align="center">
        <span style="font-size: 14.7pt;"><span contenteditable="true">（&nbsp;&nbsp;&nbsp;）</span><span>安监现记〔督〕</span><span
          contenteditable="true">&nbsp;&nbsp;&nbsp;</span> <span>号</span></span>
      </p>
      <p class="MsoNormal" style="text-align: left; margin-top: 30px">
        <span style="vertical-align: bottom;"><span>被检查单位：</span></span> <u><span
          style="border-bottom: 1px solid #333; width: 81%; display: inline-block; vertical-align: text-bottom;" contenteditable="true">&nbsp;${company.name }</span></u>
      </p>
      <p class="MsoNormal" style="text-align: left;">
        <span style="vertical-align: bottom;"><span>地&emsp;&emsp;&emsp;址：</span></span> <u><span
          style="border-bottom: 1px solid #333; vertical-align: text-bottom;; width: 81%; display: inline-block;" contenteditable="true">&nbsp;${company.address }</span></u>
      </p>
      <p class="MsoNormal" style="text-align: left;">
        <span style="vertical-align: bottom;"><span>法定代表人：</span></span> <u><span
          style="border-bottom: 1px solid #333; vertical-align: text-bottom;; width: 18%; display: inline-block;" contenteditable="true">&nbsp;${company.legal }</span></u>
        <span style="vertical-align: bottom;"><span>职务：</span></span> <u><span
          style="border-bottom: 1px solid #333; vertical-align: text-bottom;; width: 18%; display: inline-block;" contenteditable="true">&nbsp;</span></u>
        <span style="vertical-align: bottom;"><span>联系电话：</span></span> <u><span
          style="border-bottom: 1px solid #333; vertical-align: text-bottom;; width: 18%; display: inline-block;" contenteditable="true">&nbsp;${company.legalContact }</span></u>
      </p>
      <p class="MsoNormal" style="text-align: left;">
        <span style="vertical-align: bottom;"><span>检查场所及内容：</span></span> <u><span
          style="border-bottom: 1px solid #333; vertical-align: text-bottom;; width: 76%; display: inline-block;" contenteditable="true">&nbsp;</span></u>
      </p>
      <p class="MsoNormal" style="text-align: left;">
        <span style="vertical-align: bottom;"><span>检查时间</span></span> <u><span
          style="border-bottom: 1px solid #333; vertical-align: text-bottom;; width: 8%; display: inline-block; text-align: center;"
          contenteditable="true">&nbsp;<fmt:formatDate value="${empty check ? timenow : check.realTime }" pattern="yyyy" /></span></u> <span
          style="vertical-align: bottom;"><span>年</span></span> <u><span
          style="border-bottom: 1px solid #333; vertical-align: text-bottom;; width: 5%; display: inline-block; text-align: center;"
          contenteditable="true">&nbsp;<fmt:formatDate value="${empty check ? timenow : check.realTime }" pattern="MM" /></span></u> <span
          style="vertical-align: bottom;"><span>月</span></span> <u><span
          style="border-bottom: 1px solid #333; vertical-align: text-bottom;; width: 5%; display: inline-block; text-align: center;"
          contenteditable="true">&nbsp;<fmt:formatDate value="${empty check ? timenow : check.realTime }" pattern="dd" /></span></u> <span
          style="vertical-align: bottom;"><span>日</span></span> <u><span
          style="border-bottom: 1px solid #333; vertical-align: text-bottom;; width: 5%; display: inline-block; text-align: center;"
          contenteditable="true">&nbsp;</span></u> <span style="vertical-align: bottom;"><span>时</span></span> <u><span
          style="border-bottom: 1px solid #333; vertical-align: text-bottom;; width: 5%; display: inline-block; text-align: center;"
          contenteditable="true">&nbsp;</span></u> <span style="vertical-align: bottom;"><span>分至</span></span> <u><span
          style="border-bottom: 1px solid #333; vertical-align: text-bottom;; width: 5%; display: inline-block; text-align: center;"
          contenteditable="true">&nbsp;<fmt:formatDate value="${empty check ? timenow : check.realTime }" pattern="dd" /></span></u> <span
          style="vertical-align: bottom;"><span>日</span></span> <u><span
          style="border-bottom: 1px solid #333; vertical-align: text-bottom;; width: 5%; display: inline-block; text-align: center;"
          contenteditable="true">&nbsp;</span></u> <span style="vertical-align: bottom;"><span>时</span></span> <u><span
          style="border-bottom: 1px solid #333; vertical-align: text-bottom;; width: 5%; display: inline-block; text-align: center;"
          contenteditable="true">&nbsp;</span></u> <span style="vertical-align: bottom;"><span>分</span></span>
      </p>
      <p class="MsoNormal" style="text-indent: 23.0000pt; text-align: left;">
        <span style="vertical-align: bottom;"><span>我们是</span></span> <u><span
          style="border-bottom: 1px solid #333; vertical-align: text-bottom; width: 18%; display: inline-block; text-indent: 0;"
          contenteditable="true">&nbsp;无锡市惠山区</span></u> <span style="vertical-align: bottom;"><span>安全生产监督管理局行政执法人员</span></span> <u><span
          style="border-bottom: 1px solid #333; vertical-align: text-bottom; width: 21%; display: inline-block; text-indent: 0;"
          contenteditable="true">&nbsp;</span></u> <span style="vertical-align: bottom;"><span>、</span></span>

      </p>
      <p class="MsoNormal" style="text-align: left;">
        <u><span style="border-bottom: 1px solid #333; vertical-align: text-bottom; width: 21%; display: inline-block; text-indent: 0;"
          contenteditable="true">&nbsp;</span></u> <span style="vertical-align: bottom;"><span>，</span></span> <span style="vertical-align: bottom;"><span>证件号码为</span></span>
        <u><span style="border-bottom: 1px solid #333; vertical-align: text-bottom; width: 25%; display: inline-block;" contenteditable="true">&nbsp;</span></u>
        <span style="vertical-align: bottom;"><span>、</span></span> <u><span
          style="border-bottom: 1px solid #333; vertical-align: text-bottom; width: 25%; display: inline-block;" contenteditable="true">&nbsp;</span></u>
        <span style="vertical-align: bottom;"><span>，</span></span>
      </p>
      <p class="MsoNormal" style="text-align: left;">
        <span style=""><span>这是我们的证件（出示证件）。现依法对你单位进行现场检查，请予以配合。</span></span>
      </p>
      <p class="MsoNormal" style="text-indent: 23.0000pt; text-align: left;">
        <span style=""><span>检查情况如下：</span></span>
      </p>
      <div id="div_dangerm">
        <c:set var="x" value="0" />
        <c:set var="y" value="0" />
        <c:forEach items="${itemL }" var="be" varStatus="index">
          <c:if test="${be.status == 2 }">
            <c:set var="x" value="1" />
          </c:if>
        </c:forEach>
        <c:forEach items="${itemL1 }" var="bi" varStatus="index">
          <c:if test="${bi.status == 1 }">
            <c:set var="y" value="1" />
          </c:if>
        </c:forEach>
        <c:if test="${x > 0 }">
          <p class="MsoNormal">
            <span>经检查以下检查项存在隐患：</span>
          </p>
          <c:forEach items="${itemL }" var="be" varStatus="index">
            <p class="MsoNormal" style="text-indent: 21.0000pt;">
              <span class="num_dan" style="">${index.index + 1}</span><span style="">．</span><u><span class="yh_span"
                style="text-decoration: underline; text-indent: 0;" contenteditable="true">&nbsp;${be.memo }&nbsp;&nbsp;</span></u>
              <!-- <i class="Hui-iconfont mr-10 dustbin" style="cursor:pointer;font-size:20px" onclick="removeDan(this)">&#xe609;</i> -->
            </p>
          </c:forEach>
        </c:if>
        <%-- <c:if test="${y > 0 }">
          <p class="MsoNormal">
            <span>以下检查项合格：</span>
          </p>
          <c:forEach items="${itemL1 }" var="bi" varStatus="index">
            <p class="MsoNormal" style="text-indent: 21.0000pt;">
              <span class="num_dan" style="">${index.index + 1}</span><span style="">．</span><u><span class="yh_span"
                style="text-decoration: underline; text-indent: 0;" contenteditable="true">&nbsp;${bi.content }&nbsp;&nbsp;</span></u>
            </p>
          </c:forEach>
        </c:if> --%>
      </div>

      <%-- <div id="div_dangerm">
            <c:set var="x" value="0"/>
            <c:set var="y" value="0"/>
            <c:forEach items="${itemL }" var="be" varStatus="index">
            <c:if test="${be.status == 1 }"><c:set var="x" value="1"/></c:if>
            <c:if test="${be.status == 2 }"><c:set var="y" value="1"/></c:if>
            </c:forEach>
            <c:if test="${x > 0 }"><p class="MsoNormal"><span>以下隐患已整改合格,符合安全要求：</span></p>
            <c:forEach items="${itemL }" var="be" varStatus="index">
                <p class="MsoNormal" style="text-indent:21.0000pt;">
                <c:if test="${be.status == 3 }">
                    <span style="">${index.index + 1}．</span><u><span style="font-family:'\4EFF\5B8B_GB2312';text-decoration:underline;text-indent: 0;" contenteditable="true">&nbsp;
                    ${be.memo }
                    </span></u><u><span style="font-family:'\4EFF\5B8B_GB2312';text-decoration:underline;text-indent: 0;"></span></u>
                    <!-- <i class="Hui-iconfont mr-10 dustbin" style="cursor:pointer;font-size:20px" onclick="removeDan(this)">&#xe609;</i>  -->
                 </c:if>
                </p>
            </c:forEach>
            </c:if>
            <c:if test="${y > 0 }"><p class="MsoNormal"><span>以下隐患未整改合格：</span></p>
            <c:forEach items="${itemL }" var="be" varStatus="index">
                <p class="MsoNormal" style="text-indent:21.0000pt;">
                  <c:if test="${be.status == 2 }">
                     <span style="">${index.index + 1}．</span><u><span style="font-family:'\4EFF\5B8B_GB2312';text-decoration:underline;text-indent: 0;" contenteditable="true">&nbsp;
                     ${be.memo }
                     </span></u><u><span style="font-family:'\4EFF\5B8B_GB2312';text-decoration:underline;text-indent: 0;"></span></u>
                     <!-- <i class="Hui-iconfont mr-10 dustbin" style="cursor:pointer;font-size:20px" onclick="removeDan(this)">&#xe609;</i>  -->
                   </c:if>
                </p>
            </c:forEach>
            </c:if>
        </div> --%>
      
      <p class="MsoNormal" style="text-indent: 23.0000pt; text-align: left; margin-top: 10px;">
        <span style=""><span style="line-height: 28px;">本现场检查记录，仅限针对本次检查过程中已发现的安全生产情况和问题。企业（单位）应举一反三，主要负责人应当严格履职，对在生产经营活动中存在的其他安全生产问题，对照安全生产相关法律法规、规章和标准组织自查自纠。</span></span>
      </p>

      <p class="MsoNormal" style="text-align: left;">
        <span style="vertical-align: bottom;"><span>检查人员（签名）：</span></span>
        <%-- <u><span style="border-bottom:1px solid #333;vertical-align: text-bottom; width:20%;display:inline-block;" contenteditable="true">&nbsp;&nbsp;&nbsp;&nbsp;${x[0] }&nbsp;&nbsp;&nbsp;&nbsp;</span></u> --%>
        <u><span id="id1"
          style="border-bottom: 1px solid #333; vertical-align: text-bottom; width: 15%; text-align: center; display: inline-block;height: 23px;"
          contenteditable="true"></span></u> &nbsp;&nbsp;${check.cheker}&nbsp;<u><span id="id2"
          style="border-bottom: 1px solid #333; vertical-align: text-bottom; width: 15%; text-align: center; display: inline-block;height: 23px;"
          contenteditable="true"></span></u>
      </p>
      <p class="MsoNormal" style="text-align: left; margin-top: 15px">
        <span style="vertical-align: bottom;"><span>被检查单位现场负责人（签名）：</span></span> <u><span
          style="border-bottom: 1px solid #333; vertical-align: text-bottom;; width: 20%; display: inline-block;" contenteditable="true">&nbsp;${check.dapartContact }</span></u>
      </p>
      <p class="MsoNormal" style="text-align: right; margin-top: 15px">
        <span style=""><span contenteditable="true">&nbsp;<fmt:formatDate value="${empty check ? timenow : check.realTime }"
              pattern="yyyy" />&nbsp;
        </span>年<span contenteditable="true">&nbsp;&nbsp;<fmt:formatDate value="${empty check ? timenow : check.realTime }" pattern="MM" />&nbsp;
        </span>月<span contenteditable="true">&nbsp;&nbsp;<fmt:formatDate value="${empty check ? timenow : check.realTime }" pattern="dd" />&nbsp;
        </span>日</span><span style=""></span>
      </p>
      <p class="MsoNormal" style="border-top: 1px solid #000; text-align: right;">
        <span style="">共<span contenteditable="true">&nbsp;&nbsp;</span>页&nbsp;&nbsp; 第<span contenteditable="true">&nbsp;&nbsp;</span>页
        </span>
      </p>
    </div>
  </div>

  <!-- 弹窗人员 -->
  <div id="win_te" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content radius">
        <div class="modal-header">
          <h3 class="modal-title">选择执法人员</h3>
          <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
        </div>
        <div class="modal-body">
          <table class="table table-border table-bordered table-bg table-hover table-sort">
            <thead>
              <tr class="text-c">
                <th><input type="checkbox" class="checkall" style="margin-right: 15px;">${(session_user.userType == 3 || session_user.userType == 4) ? '检查' : '执法'}部门</th>
                <th>${(session_user.userType == 3 || session_user.userType == 4) ? '检查' : '执法'}人员</th>
                <th>人员编号</th>
              </tr>
            </thead>
            <tbody>
              <!-- 循环 -->
              <c:forEach items="${list }" varStatus="index" var="be">
                <tr class="text-c">
                  <td><input type="checkbox" name="checkcode" class="check_code" style="margin-right: 15px;" />${be.depart }</td>
                  <td>${be.name }</td>
                  <td>${be.code }</td>
                </tr>
              </c:forEach>
              <!-- 循环结束 -->
            </tbody>
          </table>
        </div>
        <div class="modal-footer">
          <button class="btn btn-primary">确定</button>
          <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
        </div>
      </div>
    </div>
  </div>


  <!-- 弹窗隐患 -->
  <div id="modal-demo" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content radius">
        <div class="modal-header">
          <h3 class="modal-title">添加检查情况</h3>
          <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
        </div>
        <div class="modal-body">
          <div class="row cl">
            <label class="form-label col-sm-2" style="width: 20%">检查情况：</label>
            <div class="formControls col-sm-8" style="width: 60%">
              <textarea class="textarea" resize="none" style="height: 50px" id="memo_text"></textarea>
            </div>
          </div>
          <div class="row cl" style="margin-top: 15px;">
            <label class="form-label col-sm-2" style="width: 20%">检查结果：</label>
            <div class="formControls col-sm-8" style="width: 60%">
              <div class="radio-box" style="padding-left: 0px;">
                <input id="x2" type="radio" name="radio-status" value="1"> <label for="x2">合格</label>
              </div>
              <div class="radio-box">
                <input id="x3" type="radio" name="radio-status" value="2" checked="checked"> <label for="x3">不合格</label>
              </div>
            </div>
          </div>

          <div class="row cl" style="margin-top: 15px;">
            <label class="form-label col-sm-2" style="width: 20%;">上传图片：</label>
            <div class="formControls col-sm-8" style="width: 60%;">
              <img id="pic" src="${ly }/images/zwtp.jpg" style="width: 300px; min-height: 300px;" url="" /> <a href="javascript:void(0);"
                onclick="img_upload('pic', null)" class="btn btn-primary radius upload-btn mt-10"><i class="Hui-iconfont">&#xe642;</i> 上传图片</a>
            </div>
          </div>

        </div>
        <div class="modal-footer">
          <button class="btn btn-primary" id="check_item_btn">确定</button>
          <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
        </div>
      </div>
    </div>
  </div>
</body>
<script type="text/javascript">
    var checkId = '${check.id}';
    var userId = ${userId};
    var flag = 8, flag2 = 4;
    $(function(){
    var str='${check.cheker }';
    var x = str.replace(/、/g, ',').split(",");
    $("#id1").html(x[0]);
    $("#id2").html(x[1]);
    }
    )
</script>
<script type="text/javascript" src="${ly }/js/check/opinion8.js"></script>
</html>