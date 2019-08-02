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
</head>
<body>
  <div class="text-c mt-20">
    <button onClick="pr_()" class="btn btn-primary radius" type="button">
        <i class="Hui-iconfont">&#xe652;</i>打印
    </button>
  </div>
  <div class="page-container" id="div_container">
    <div style="width:180mm;margin:auto;padding:30px 0 0 0;">
    <h3 class="text-c" style="padding-top:0">${be.name }主要物质理化、危险、有害特性表</h3>
    <div class="mt-20">
      <table class="table table-border table-bordered table-sort tab-ndan">
        <tbody>
          <tr>
            <td style="width:20px;" rowspan="3"></td>
            <td><p>名称：${be.name }</p></td>
            <td><p>英文名：${be.nameEn }</p></td>
            <td><p>分子式：${be.fzs }</p></td>
          </tr>
          <tr>
            <td><p>CSA号：${be.cas }</p></td>
            <td><p>UN编号：${be.un }</p></td>
            <td><p>分子量：${be.fzl }</p></td>
          </tr>
          <tr>
            <td colspan="3"><p>危化品序号：${be.serialNumber }</p></td>
          </tr>
          
        </tbody>
      </table>
      <table class="table table-border table-bordered table-sort tab-ndan" style="border-top:0;">
        <tbody>
          <tr>
            <td style="width:20px;" rowspan="4" class="text-c">理化特性</td>
            <td colspan="2"><p>外观与性状：${be.whWg }</p></td>
          </tr>
          <tr>
            <td><p>熔点（℃）：${be.whRd }</p></td>
            <td><p>沸点（℃）：${be.whFd }</p></td>
          </tr>
          <tr>
            <td><p>相对密度（水=1）：${be.whMd }</p></td>
            <td><p>饱和蒸汽压[Kpa]：${be.whBhzqy }</p></td>
          </tr>
          <tr>
            <td colspan="2"><p>溶解性：${be.whRjx }</p></td>
          </tr>
          <tr>
            <td style="width:20px;" class="text-c">危险特性</td>
            <td colspan="2"><p>${be.wx }</p></td>
          </tr>
          <tr>
            <td style="width:20px;" rowspan="6" class="text-c">燃烧爆炸危险性</td>
            <td><p>燃烧性：${be.rsRsx }</p></td>
            <td><p>建规火险分级：${be.rsHxfj }</p></td>
          </tr>
          <tr>
            <td><p>闪点（℃）：${be.rsSd }</p></td>
            <td><p>自燃点（℃）：${be.rsZrd }</p></td>
          </tr>
          <tr>
            <td><p>爆炸下限（v%）：${be.rsBzMin }</p></td>
            <td><p>爆炸上限（v%）：${be.rsBzMax }</p></td>
          </tr>
          <tr>
            <td><p>类别：${be.rsLb }</p></td>
            <td><p>稳定性：${be.rsWdx }</p></td>
          </tr>
          <tr>
            <td><p>聚合危害：${be.rsJhwh }</p></td>
            <td><p>禁忌物：${be.rsJjw }</p></td>
          </tr>
          <tr>
            <td colspan="2"><p>灭火方法：${be.rsMhff }</p></td>
          </tr>
        </tbody>
      </table>
      <table class="table table-border table-bordered table-sort tab-ndan" style="border-top:0;">
        <tbody>
          <tr>
            <td style="width:20px;" rowspan="5" class="text-c">毒性及危害</td>
            <td style="width:150px" rowspan="2">接触极限</td>
            <td><p>中国MAC：${be.dxJcjxZg }</p></td>
            <td><p>美国TWA：${be.dxJcjxMg }</p></td>
          </tr>
          <tr>
            <td><p>前苏联MAC：${be.dxJcjxQsl }</p></td>
            <td><p>美国STEL：${be.dxJcjxMg2 }</p></td>
          </tr>
          <tr>
            <td style="width:150px">毒性：${be.dxDx }</td>
            <td><p>LD50：${be.dxDx2 }</p></td>
            <td><p>LC50：${be.dxDx3 }</p></td>
          </tr>
          <tr>
            <td style="width:150px">侵入途径</td>
            <td colspan="2"><p>${be.dxQr }</p></td>
          </tr>
          <tr>
            <td style="width:150px">健康危害</td>
            <td colspan="2"><p>${be.dxWh }</p></td>
          </tr>
          <tr>
            <td style="width:20px;" class="text-c">储运注意事项</td>
            <td colspan="3"><p>${be.cy }</p></td>
          </tr>
          <tr>
            <td style="width:20px;" class="text-c">泄漏处置</td>
            <td colspan="3"><p>${be.xlcz }</p></td>
          </tr>
        </tbody>
      </table>
    </div>
    </div>
  </div>
</body>
</html>