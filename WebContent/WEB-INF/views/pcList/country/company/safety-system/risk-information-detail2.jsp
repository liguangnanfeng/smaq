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
<%@ include file="/WEB-INF/inc/print.inc"%>
<link rel="stylesheet" type="text/css" href="${ly}/styles/print-tab.css"/>
<link rel="stylesheet" type="text/css" href="${ly}/styles/print-tab.css" media="print" />
<link rel="stylesheet" type="text/css" media="print"  href="${ly}/js/H-ui.admin_v3.0/static/h-ui/css/H-ui.css" />
<script type="text/javascript">
function pr_() {
  $("#div_container").jqprint();
}
</script>
<style type="text/css" media="print">
.div-print{width:90%;margin:auto;padding:30px 0 0 0;}
.tab-harm p{color:#000}
</style>
<style type="text/css">
.div-print{width:60%;margin:auto;padding:30px 0 0 0;}
.tab-harm p{color:#000}
</style>
</head>
<body>
  <div class="text-c mt-20">
    <button onClick="pr_()" class="btn btn-primary radius" type="button">
        <i class="Hui-iconfont">&#xe652;</i>打印
    </button>
  </div>
  <div class="page-container" id="div_container">
    <div class="div-print">
    <h3 class="text-c" style="padding-top:0">危化品物质告知牌</h3>
    <div class="mt-20">
      <table class="table table-border table-bordered table-sort tab-harm">
        <tbody>
          <tr>
            <td class="text-c bj-blue" style="width:25%">危险性提示词</td>
            <td class="k-tab no-bo"></td>
            <td class="text-c bj-blue" colspan="3">品名、英文名称、分子式及CAS码</td>
            <td class="k-tab no-bo"></td>
            <td class="text-c bj-blue" style="width:25%">危险性标志 </td>
          </tr>
          <tr>
            <td class="text-c">
              <h4>${be.rsRsx }${be.dxDx }</h4>
            </td>
            <td class="k-tab nobo"></td>
            <td class="text-c" colspan="3">
              <p>${be.name }</p>
              <p>${be.nameEn }</p>
              <p>${be.fzs }</p>
              <p>CAS号：${be.cas }</p>
            </td>
            <td class="k-tab nobo"></td>
            <td class="text-c">
              <img alt="" src="${ly }/images/harm/hh0.jpg">
             </td>
          </tr>
          <tr><td colspan="7" class="kh-tab nobo"></td></tr>
          <tr>
            <td class="text-c bj-blue" colspan="3">危险性理化数据</td>
            <td class="k-tab nobo"></td>
            <td class="text-c bj-blue" colspan="3">危险特性</td>
          </tr>
          <tr>
            <td colspan="3">
              <p>闪点：${be.rsSd }</p>
              <p>沸点：${be.whFd }</p>
              <p>相对密度：${be.whMd }</p>
              <p>饱和蒸汽压：${be.whBhzqy }</p>
            </td>
            <td class="k-tab nobo"></td>
            <td colspan="3">
              <p>${be.wx }</p>
            </td>
          </tr>
          <tr><td colspan="7" class="kh-tab nobo"></td></tr>
          <tr>
            <td colspan="7" class="text-c bj-blue">接触后表现</td>
          </tr>
          <tr>
            <td colspan="7">
              <p>${be.dxWh }</p>
            </td>
          </tr>
          <tr><td colspan="7" class="kh-tab nobo"></td></tr>
          <tr>
            <td colspan="7" class="text-c bj-blue">现场急救措施</td>
          </tr>
          <tr>
            <td colspan="7">
              <!-- <p>皮肤接触：xx</p>
              <p>眼睛接触：xx</p>
              <p>吸&emsp;&emsp;入：xx</p>
              <p>食&emsp;&emsp;入：xx</p> -->
              <p>${be.xlcz }</p>
            </td>
          </tr>
          <tr><td colspan="7" class="kh-tab nobo"></td></tr>
          <tr>
            <td class="text-c bj-blue" colspan="3">个体防护措施</td>
            <td class="k-tab nobo"></td>
            <td class="text-c bj-blue" colspan="3">泄漏处理及防火防爆措施</td>
          </tr>
          <tr>
            <td class="text-c" colspan="3">
              <img alt="" src="${ly }/images/harm/hh1.jpg" style="height:100px">
              <img alt="" src="${ly }/images/harm/hh3.jpg" style="height:100px;margin-left:10px">
            </td>
            <td class="k-tab nobo"></td>
            <td class="text-c" colspan="3">
              <img alt="" src="${ly }/images/harm/hh2.jpg" style="height:100px">
              <img alt="" src="${ly }/images/harm/hh4.jpg" style="height:100px;margin-left:10px">
              <img alt="" src="${ly }/images/harm/hh5.jpg" style="height:100px;margin-left:10px">
            </td>
          </tr>
          <tr><td colspan="7" class="kh-tab nobo"></td></tr>
          <tr>
            <td class="text-c bj-blue">最高容许浓度</td>
            <td class="k-tab no-bo"></td>
            <td class="text-c bj-blue" colspan="3">当地应急救援单位名称</td>
            <td class="k-tab no-bo"></td>
            <td class="text-c bj-blue">当地应急救援电话 </td>
          </tr>
          <tr>
            <td class="text-c">${be.dxJcjxZg }</td>
            <td class="k-tab no-to"></td>
            <td class="text-c" colspan="3">
              <p></p>
            </td>
            <td class="k-tab no-to"></td>
            <td class="text-c">
              <p>火&emsp;&emsp;警：119</p>
              <p>急救电话：120</p>
             </td>
          </tr>
        </tbody>
      </table>
    </div>
    </div>
  </div>
</body>
</html>