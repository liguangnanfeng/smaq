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
  </style>
  <script type="text/javascript" src="${ly }/js/fxgk/zdsg.js?v=123"></script>
  <script type="text/javascript">
      var zdfx = ${list};
      var industry = '${company.industry}';
      $(function() {
          var $yb_4 = $("#yb_4");
          $.each(yb_4, function(j, t) {
              if(t.industry == industry) {
                  $.each(t.list, function(i, item) {
                      $yb_4.append('<tr><td><p>'+(i+1)+". "+item+'</p></td><td class="text-c" style="width:50px"><input type="checkbox" value="'+item+'"></td></tr>');
                  })
                  return false;
              }
          })

          $.each(zdfx,function(i, item) {
              $(":checkbox[value='" + item.factors + "']").prop("checked", true);
          })
      })

      function save() {
          var z = [];
          $(":checkbox:checked").each(function() {
              z.push($(this).val());
          })
          $.post("/company/safety-system/a-zdfxys-save", {
              zdfx : z.join("!@#")
          },function(result) {
              if(z.length > 0) {
                  layer.alert("评估结果为‘重大风险企业’。");
              } else {
                  layer.alert("保存成功。");
              }
          })
      }
  </script>
</head>
<body>
<nav class="breadcrumb text-c">
  <a class="btn-page pagexz" href="javascript:;">重大风险判定</a>
  <a class="btn-page" data-href="${ly }/company/safety-system/assess-list"  data-title="较大风险判定" onclick="Hui_admin_tab(this)" href="javascript:;">较大风险判定</a>
  <a class="btn-page"  data-href="${ly }/company/safety-system/assess6"  data-title="一般和较小风险判定" onclick="Hui_admin_tab(this)" href="javascript:;">一般和较小风险判定</a>
  <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="parent.close_tab(function(){})">返回</a>
</nav>
<div class="page-container">
  <div class="mt-20">
    <table class="table table-border table-bordered table-bg table-hover table-sort tab-ndan">
      <thead>
      <tr class="text-c">
        <th colspan="3">专项类重大风险判定</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <td rowspan="10" class="text-c">存在粉尘爆炸危险的行业领域</td>
        <td>粉尘爆炸危险场所设置在非框架结构的多层建构筑物内，或与居民区、员工宿舍、会议室等人员密集场所安全距离不足。</td>
        <td class="text-c" style="width:50px"><input type="checkbox" value="粉尘爆炸危险场所设置在非框架结构的多层建构筑物内，或与居民区、员工宿舍、会议室等人员密集场所安全距离不足。"></td>
      </tr>
      <tr>
        <td>可燃性粉尘与可燃气体等易加剧爆炸危险的介质共用一套除尘系统，不同防火分区的除尘系统互联互通。</td>
        <td class="text-c"><input type="checkbox" value="可燃性粉尘与可燃气体等易加剧爆炸危险的介质共用一套除尘系统，不同防火分区的除尘系统互联互通。"></td>
      </tr>
      <tr>
        <td>干式除尘系统未规范采用泄爆、隔爆、惰化、抑爆等任一种控爆措施。</td>
        <td class="text-c"><input type="checkbox" value="干式除尘系统未规范采用泄爆、隔爆、惰化、抑爆等任一种控爆措施。"></td>
      </tr>
      <tr>
        <td>除尘系统采用正压吹送粉尘，且未采取可靠的防范点燃源的措施。</td>
        <td class="text-c"><input type="checkbox" value="除尘系统采用正压吹送粉尘，且未采取可靠的防范点燃源的措施。"></td>
      </tr>
      <tr>
        <td>除尘系统采用粉尘沉降室除尘，或者采用干式巷道式构筑物作为除尘风道。</td>
        <td class="text-c"><input type="checkbox" value="除尘系统采用粉尘沉降室除尘，或者采用干式巷道式构筑物作为除尘风道。"></td>
      </tr>
      <tr>
        <td>铝镁等金属粉尘及木质粉尘的干式除尘系统未规范设置锁气卸灰装置。</td>
        <td class="text-c"><input type="checkbox" value="铝镁等金属粉尘及木质粉尘的干式除尘系统未规范设置锁气卸灰装置。"></td>
      </tr>
      <tr>
        <td>粉尘爆炸危险场所的20区未使用防爆电气设备设施。</td>
        <td class="text-c"><input type="checkbox" value="粉尘爆炸危险场所的20区未使用防爆电气设备设施。"></td>
      </tr>
      <tr>
        <td>在粉碎、研磨、造粒等易于产生机械点火源的工艺设备前，未按规范设置去除铁、石等异物的装置。</td>
        <td class="text-c"><input type="checkbox" value="在粉碎、研磨、造粒等易于产生机械点火源的工艺设备前，未按规范设置去除铁、石等异物的装置。"></td>
      </tr>
      <tr>
        <td>木制品加工企业，与砂光机连接的风管未规范设置火花探测报警装置。</td>
        <td class="text-c"><input type="checkbox" value="木制品加工企业，与砂光机连接的风管未规范设置火花探测报警装置。"></td>
      </tr>
      <tr>
        <td>未制定粉尘清扫制度，作业现场积尘未及时规范清理。</td>
        <td class="text-c"><input type="checkbox" value="未制定粉尘清扫制度，作业现场积尘未及时规范清理。"></td>
      </tr>
      <tr>
        <td class="text-c" rowspan="2">使用液氨制冷的行业领域</td>
        <td>包装间、分割间、产品整理间等人员较多生产场所的空调系统采用氨直接蒸发制冷系统。</td>
        <td class="text-c"><input type="checkbox" value="包装间、分割间、产品整理间等人员较多生产场所的空调系统采用氨直接蒸发制冷系统。"></td>
      </tr>
      <tr>
        <td>快速冻结装置未设置在单独的作业间内，且作业间内作业人员数量超过9人。</td>
        <td class="text-c"><input type="checkbox" value="快速冻结装置未设置在单独的作业间内，且作业间内作业人员数量超过9人。"></td>
      </tr>
      <tr>
        <td class="text-c" rowspan="2">有限空间作业相关的行业领域</td>
        <td>未对有限空间作业场所进行辨识，并设置明显安全警示标志。</td>
        <td class="text-c"><input type="checkbox" value="未对有限空间作业场所进行辨识，并设置明显安全警示标志。"></td>
      </tr>
      <tr>
        <td>未落实作业审批制度，擅自进入有限空间作业。</td>
        <td class="text-c"><input type="checkbox" value="未落实作业审批制度，擅自进入有限空间作业。"></td>
      </tr>
      </tbody>
    </table>
    <table class="table table-border table-bordered table-bg table-hover table-sort tab-ndan" id="hy">
      <thead>
      <tr class="text-c">
        <th colspan="2">行业类重大风险判定</th>
      </tr>
      </thead>
      <tbody id="yb_4">

      </tbody>
    </table>
    <div class="mt-20 text-c f-l mb-20" style="width:100%">
      <button onClick="save()" class="btn btn-primary radius" type="button" style="padding: 0 70px;">
        <i class="Hui-iconfont mr-10">&#xe632;</i>提交评估
      </button>
    </div>
  </div>
</div>
</body>
</html>
