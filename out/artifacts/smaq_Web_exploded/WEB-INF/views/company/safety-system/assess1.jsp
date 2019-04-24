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
body .input-dan{float:none;height:22px;line-height:22px;width:90%;display:inline-block;border:1px solid #DDD}
</style>
<script type="text/javascript">
$(function() {
  /* 设置表格的宽度 */
  var w2=$(".wid2").width();
  var w3=$(".wid3").width()+1+"px";
  var w4=$(".wid4").width();
  var w6=$(".wid3").width()+$(".wid4").width()+2+"px";
  $(".two-lb").width(w2);
  $(".list-danf").width(w3);
  $(".four-lb").width(w4);
  $(".wid6").width(w6);
  /* 设置表格的宽度 */
  /* 每行类别高度 */
  $(".first-lb").each(function(){
    $(this).height($(this).closest(".tr-danger").height()-1+"px");
  });
  /* 每行项目高度 */
  $(".two-lb").each(function(){
    $(this).height($(this).closest(".wid5").height()-1+"px");
  });
  /* 每行扣分值高度 */
  $(".four-lb").each(function(){
    $(this).height($(this).closest(".wid6").height()-1+"px");
  });
  /* 最高风险等级每行扣分值高度 */
  $(".five-lb").each(function(){
    $(this).height($(this).closest(".tr-danger").height()-1+"px");
  });
  
  
  
});
/* 判断评价结果和评定页面显示 */
function show(){
  $(".div-bzs").hide();
  $(".showcp").show();
}
</script> 
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>风险分级管控</span> 
    <span class="c-gray en">&gt;</span> <span>危化品</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="div-bzs">
      <div class="bt-fx2 text-c">
        <c:choose>
          <c:when test="${not empty bean }">
            <div class="bt-dj" style="font-size:40px">${bean.lev }</div>
            <font class="bt-fx" style="font-size:15px">分值：${bean.score }</font>
            <div class="btn radius btn-cxcp" onclick="show()" >重新风险测评</div>
          </c:when>
          <c:otherwise>
            <div class="bt-dj" style="font-size:70px">?</div>
            <font class="bt-fx">当前还未进行风险评估</font>
            <div class="btn btn-primary radius btn-cxcp" onclick="show()" >前往风险测评</div>
          </c:otherwise>
        </c:choose>
      </div>
      <h4 class="mt-40 text-c">企业安全风险评估值判定</h4>
      <table class="table table-border table-bordered table-bg table-hover table-sort mb-40">
        <tr>
          <td>
             <div class="list-dbz">
              <p>1.安全风险从高到低依次对应为红色、橙色、黄色、蓝色。总分在90分以上（含90分）的为蓝色；75分（含75分）至90分的为黄色；60分（含60分）至75分的为橙色；60分以下的为红色。</p>
              <p>2.每个项目分值扣完为止，最低为0分。</p>
              <p>3.储存企业指带储存的经营企业。</p>
             </div>
          </td>
        </tr>
      </table>
    </div>
    <div class="showcp">
      <h3 class="text-c" style="padding:10px 0">危险化学品生产储存企业安全风险评估诊断分级指南（试行）</h3>
      <div class="mt-20 f-l" style="width:100%">
        <table class="table table-border table-bordered table-bg table-hover table-sort tab-ndan">
          <thead>
            <tr class="text-c">
              <th style="width:10%">类别</th>
              <th style="width:20%">项目（分值）</th>
              <th style="width:60%">评估内容</th>
              <th style="width:10%">扣分值</th>
            </tr>
          </thead>
          <tr>
             <td rowspan="10" class="text-c">1.固有危险性</td>
             <td rowspan="4" class="text-c">重大危险源（10分）</td>
             <td><p>存在一级危险化学品重大危险源的，扣10分；</p></td>
             <td rowspan="4"><input type="text" value="" data-idx="0" class="input-dan ll-numberbox" data-options="min:0,max:10" data-type="1"/></td>
          </tr>
          <tr>
             <td><p>存在二级危险化学品重大危险源的，扣8分;</p></td>
          </tr>
          <tr>
             <td><p>存在三级危险化学品重大危险源的，扣6分；</p></td>
          </tr>
          <tr>
             <td><p>存在四级危险化学品重大危险源的，扣4分。</p></td>
          </tr>
          <tr>
             <td rowspan="3" class="text-c">物质危险性（5分）</td>
             <td><p>生产、储存爆炸品的（实验室化学试剂除外），每一种扣2分；</p></td>
             <!-- type 1 扣分 2加分 -->
             <td><input type="text" value="" data-idx="1" class="input-dan ll-numberbox" data-options="min:0,max:5" data-type="1"/></td>
          </tr>
          <tr>
             <td><p>生产、储存（含管道输送）氯气、光气等吸入性剧毒化学品的（实验室化学试剂除外），每一种扣2分；</p></td>
             <td><input type="text" value="" data-idx="1" class="input-dan ll-numberbox" data-options="min:0,max:5" data-type="1"/></td>
          </tr>
          <tr>
             <td><p>生产、储存其他重点监管危险化学品的（实验室化学试剂除外），每一种扣0.1分。</p></td>
             <td><input type="text" value="" data-idx="1" class="input-dan ll-numberbox" data-options="min:0.0,max:5,precision:1" data-type="1"/></td>
          </tr>
          <tr>
             <td class="text-c">危险化工工艺种类（10分）</td>
             <td><p>涉及18种危险化工工艺的，每一种扣2分。</p></td>
             <td><input type="text" value="" data-idx="2" class="input-dan ll-numberbox" data-options="min:0,max:10,precision:0" data-type="1"/></td>
          </tr>
          <tr>
             <td class="text-c" rowspan="2">火灾爆炸危险性（5分）</td>
             <td><p>涉及甲类/乙类火灾危险性类别厂房、库房或者罐区的，每涉及一处扣1/0.5分</p></td>
             <td><input type="text" value="" data-idx="3" class="input-dan ll-numberbox" data-options="min:0,max:5,precision:1" data-type="1"/></td>
          </tr>
          <tr>
             <td><p>涉及甲类、乙类火灾危险性罐区、气柜与加热炉等与产生明火的设施、装置比邻布置的，扣5分。</p></td>
             <td><input type="text" value="" data-idx="3" class="input-dan ll-numberbox" data-options="min:0,max:5,precision:0" data-type="1"/></td>
          </tr>
          <tr>
             <td rowspan="2" class="text-c">2.周边环境</td>
             <td rowspan="2" class="text-c">周边环境（10分）</td>
             <td><p>企业在化工园区（化工集中区）外的，扣3分；</p></td>
             <td><input type="text" value="" data-idx="4" class="input-dan ll-numberbox" data-options="min:0,max:10,precision:0" data-type="1"/></td>
          </tr>
          <tr>
             <td><p>企业外部安全防护距离不符合《危险化学品生产、储存装置个人可接受风险标准和社会可接受风险标准（试行）》的，扣10分。</p></td>
             <td><input type="text" value="" data-idx="4" class="input-dan ll-numberbox" data-options="min:0,max:10,precision:0" data-type="1"/></td>
          </tr>
          <tr>
             <td class="text-c" rowspan="3">3.设计与评估</td>
             <td class="text-c" rowspan="3">设计与评估（10分）</td>
             <td><p>国内首次使用的化工工艺未经过省级人民政府有关部门组织安全可靠性论证的，扣5分；</p></td>
             <td><input type="text" value="" data-idx="5" class="input-dan ll-numberbox" data-options="min:0,max:10,precision:0" data-type="1"/></td>
          </tr>
          <tr>
             <td><p>精细化工企业未按规范性文件要求开展反应安全风险评估的，扣10分；</p></td>
             <td><input type="text" value="" data-idx="5" class="input-dan ll-numberbox" data-options="min:0,max:10,precision:0" data-type="1"/></td>
          </tr>
          <tr>
             <td style="font-weight: bold"><p>企业危险化学品生产储存装置均由甲级资质设计单位进行全面设计的，加2分。</p></td>
             <td><input type="text" value="" data-idx="5" class="input-dan ll-numberbox" data-options="min:0,max:2,precision:0" data-type="2"/></td>
          </tr>
          <tr>
             <td class="text-c" rowspan="3">4.设备</td>
             <td class="text-c" rowspan="3">设备（5分）</td>
             <td><p>使用淘汰落后安全技术工艺、设备目录列出的工艺及设备的，每一项扣2分；</p></td>
             <td><input type="text" value="" data-idx="6" class="input-dan ll-numberbox" data-options="min:0,max:5,precision:0" data-type="1"/></td>
          </tr>
          <tr>
             <td><p>特种设备没有办理使用登记证书的，或者未按要求定期检验的，扣2分；</p></td>
             <td><input type="text" value="" data-idx="6" class="input-dan ll-numberbox" data-options="min:0,max:5,precision:0" data-type="1"/></td>
          </tr>
          <tr>
             <td><p>化工生产装置未按国家标准要求设置双电源或者双回路供电的，扣5分。</p></td>
             <td><input type="text" value="" data-idx="6" class="input-dan ll-numberbox" data-options="min:0,max:5,precision:0" data-type="1"/></td>
          </tr>
          <tr>
             <td class="text-c" rowspan="7">5.自控与安全设施</td>
             <td class="text-c" rowspan="7">自控与安全设施（10分）</td>
             <td><p>涉及重点监管危险化工工艺的装置未按要求实现自动化控制，系统未实现紧急停车功能，装备的自动化控制系统、紧急停车系统未投入使用的，扣10分；</p></td>
             <td><input type="text" value="" data-idx="7" class="input-dan ll-numberbox" data-options="min:0,max:10,precision:0" data-type="1"/></td>
          </tr>
          <tr>
             <td><p>涉及毒性气体、液化气体、剧毒液体的一级、二级重大危险源的危险化学品罐区未配备独立的安全仪表系统的，扣10分</p></td>
             <td><input type="text" value="" data-idx="7" class="input-dan ll-numberbox" data-options="min:0,max:10,precision:0" data-type="1"/></td>
          </tr>
          <tr>
             <td><p>构成一级、二级重大危险源的危险化学品罐区未实现紧急切断功能的，扣5分；</p></td>
             <td><input type="text" value="" data-idx="7" class="input-dan ll-numberbox" data-options="min:0,max:10,precision:0" data-type="1"/></td>
          </tr>
          <tr>
             <td><p>危险化学品重大危险源未设置压力、液位、温度远传监控和超限位报警装置的，每涉及一项扣1分；</p></td>
             <td><input type="text" value="" data-idx="7" class="input-dan ll-numberbox" data-options="min:0,max:10,precision:0" data-type="1"/></td>
          </tr>
          <tr>
             <td><p>涉及可燃和有毒有害气体泄漏的场所未按国家标准设置检测声光报警设施的，每一处扣1分；</p></td>
             <td><input type="text" value="" data-idx="7" class="input-dan ll-numberbox" data-options="min:0,max:10,precision:0" data-type="1"/></td>
          </tr>
          <tr>
             <td><p>防爆区域未按国家标准安装使用防爆电气设备的，每一处扣1分；</p></td>
             <td><input type="text" value="" data-idx="7" class="input-dan ll-numberbox" data-options="min:0,max:10,precision:0" data-type="1"/></td>
          </tr>
          <tr>
             <td><p>甲类、乙类火灾危险性生产装置内设有办公室、操作室、固定操作岗位或休息室的，每涉及一处扣5分。</p></td>
             <td><input type="text" value="" data-idx="7" class="input-dan ll-numberbox" data-options="min:0,max:10,precision:0" data-type="1"/></td>
          </tr>
          <tr>
             <td class="text-c" rowspan="5">6.人员资质</td>
             <td class="text-c" rowspan="5">人员资质（15分）</td>
             <td><p>企业主要负责人和安全生产管理人员未依法经考核合格的，每一人次扣5分；</p></td>
             <td><input type="text" value="" data-idx="8" class="input-dan ll-numberbox" data-options="min:0,max:15,precision:0" data-type="1"/></td>
          </tr>
          <tr>
             <td><p>企业专职安全生产管理人员不具备国民教育化工化学类（或安全工程）中等职业教育以上学历或者化工化学类中级以上专业技术职称的，每一人次扣5分；</p></td>
             <td><input type="text" value="" data-idx="8" class="input-dan ll-numberbox" data-options="min:0,max:15,precision:0" data-type="1"/></td>
          </tr>
          <tr>
             <td><p>涉及“两重点一重大”装置的生产、设备及工艺专业管理人员不具有相应专业大专以上学历的，每一人次扣5分；</p></td>
             <td><input type="text" value="" data-idx="8" class="input-dan ll-numberbox" data-options="min:0,max:15,precision:0" data-type="1"/></td>
          </tr>
          <tr>
             <td><p>企业未按有关要求配备注册安全工程师的，扣3分；</p></td>
             <td><input type="text" value="" data-idx="8" class="input-dan ll-numberbox" data-options="min:0,max:3,precision:0" data-type="1"/></td>
          </tr>
          <tr>
             <td style="font-weight:bold;"><p>企业主要负责人、分管安全生产工作负责人、安全管理部门主要负责人为化学化工类专业毕业的，每一人次加2分。</p></td>
             <td><input type="text" value="" data-idx="8" class="input-dan ll-numberbox" data-options="min:0,precision:0" data-type="2"/></td>
          </tr>
          <tr>
             <td class="text-c" rowspan="3">7.安全管理制度</td>
             <td class="text-c" rowspan="3">管理制度（10分）</td>
             <td><p>未制定操作规程和工艺控制指标或者制定的操作规程和工艺控制指标不完善的，扣5分；</p></td>
             <td><input type="text" value="" data-idx="9" class="input-dan ll-numberbox" data-options="min:0,max:5,precision:0" data-type="1"/></td>
          </tr>
          <tr>
             <td><p>动火、进入受限空间等特殊作业管理制度不符合国家标准或未有效执行的，扣10分；</p></td>
             <td><input type="text" value="" data-idx="9" class="input-dan ll-numberbox" data-options="min:0,max:10,precision:0" data-type="1"/></td>
          </tr>
          <tr>
             <td><p>未建立与岗位相匹配的全员安全生产责任制的，每涉及一个岗位扣2分。</p></td>
             <td><input type="text" value="" data-idx="9" class="input-dan ll-numberbox" data-options="min:0,max:5,precision:0" data-type="1"/></td>
          </tr>
          <tr>
             <td class="text-c">8.应急管理</td>
             <td class="text-c">应急配备</td>
             <td style="font-weight:bold;"><p>企业自设专职消防应急队伍的，加3分。</p></td>
             <td><input type="text" value="" data-idx="10" class="input-dan ll-numberbox" data-options="min:0,max:3,precision:0" data-type="2"/></td>
          </tr>
          <tr>
             <td class="text-c" rowspan="7">9.安全管理绩效</td>
             <td class="text-c" rowspan="7">安全生产标准化达标安全事故情况（10分）</td>
             <td style="font-weight:bold;"><p>安全生产标准化为一级的，加15分；</p></td>
             <td><input type="text" value="" data-idx="10" class="input-dan ll-numberbox" data-options="min:0,max:15,precision:0" data-type="2"/></td>
          </tr>
          <tr>
             <td style="font-weight:bold;"><p>安全生产标准化为二级的，加5分；</p></td>
             <td><input type="text" value="" data-idx="11" class="input-dan ll-numberbox" data-options="min:0,max:5,precision:0" data-type="2"/></td>
          </tr>
          <tr>
             <td style="font-weight:bold;"><p>安全生产标准化为三级的，加2分。</p></td>
             <td><input type="text" value="" data-idx="11" class="input-dan ll-numberbox" data-options="min:0,max:2,precision:0" data-type="2"/></td>
          </tr>
          <tr>
             <td><p>三年内发生过1起较大安全事故的，扣10分;</p></td>
             <td><input type="text" value="" data-idx="11" class="input-dan ll-numberbox" data-options="min:0,max:10,precision:0" data-type="1"/></td>
          </tr>
          <tr>
             <td><p>三年内发生过1起安全事故造成1-2人死亡的，扣8分;</p></td>
             <td><input type="text" value="" data-idx="11" class="input-dan ll-numberbox" data-options="min:0,max:8,precision:0" data-type="1"/></td>
          </tr>
          <tr>
             <td><p>三年内发生过爆炸、着火、中毒等具有社会影响的安全事故，但未造成人员伤亡的，扣5分;</p></td>
             <td><input type="text" value="" data-idx="11" class="input-dan ll-numberbox" data-options="min:0,max:5,precision:0" data-type="1"/></td>
          </tr>
          <tr>
             <td style="font-weight:bold;"><p>五年内未发生安全事故的，加5分。</p></td>
             <td><input type="text" value="" data-idx="11" class="input-dan ll-numberbox" data-options="min:0,max:5,precision:0" data-type="2"/></td>
          </tr>
          <tr class="text-c">
             <td colspan="4" style="height:40px"><font>存在下列情况之一的企业直接判定为红色（最高风险等级）</font></td>
          </tr>
          <tr>
             <td colspan="3" style="font-weight:bold;"><p>新开发的危险化学品生产工艺未经小试、中试和工业化试验直接进行工业化生产的；</p></td>
             <td class="text-c"><input type="checkbox" name="spe" value="1"/></td>
          </tr>
          <tr>
             <td colspan="3" style="font-weight:bold;"><p>在役化工装置未经正规设计且未进行安全设计诊断的；</p></td>
             <td class="text-c"><input type="checkbox" name="spe" value="2"/></td>
          </tr>
          <tr>
             <td colspan="3" style="font-weight:bold;"><p>危险化学品特种作业人员未持有效证件上岗或者未达到高中以上文化程度的; </p></td>
             <td class="text-c"><input type="checkbox" name="spe" value="3"/></td>
          </tr>
          <tr>
             <td colspan="3" style="font-weight:bold;"><p>三年内发生过重大以上安全事故的，或者三年内发生2起较大安全事故，或者近一年内发生2起以上亡人一般安全事故的。</p></td>
             <td class="text-c"><input type="checkbox" name="spe" value="4"/></td>
          </tr>
          <tr>
             <td colspan="4">
               <div class="bt-dbz">备注：</div>
               <div class="list-dbz">
                <p>1.安全风险从高到低依次对应为红色、橙色、黄色、蓝色。总分在90分以上（含90分）的为蓝色；75分（含75分）至90分的为黄色；60分（含60分）至75分的为橙色；60分以下的为红色。</p>
                <p>2.每个项目分值扣完为止，最低为0分。</p>
                <p>3.储存企业指带储存的经营企业。</p>
               </div>
             </td>
          </tr>
        </table>
      </div>
      <div class="mt-20 text-c f-l mb-20" style="width:100%">
        <button onClick="save()" class="btn btn-primary radius" type="button" style="padding: 0 70px;">
          <i class="Hui-iconfont mr-10">&#xe632;</i>提交评估
        </button>
      </div>
    </div>
  </div>
</body>
<script type="text/javascript">
var s = [-10, -5, -10, -5, -10, -10, -5, -10, -5, -15, 3, -10];

var cores = [], lev = '蓝色', spe = '', score = 100, danger = '';

function save() {
  var c = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
  $(":text").each(function() {
    var v = $(this).val();
    if($(this).val().trim() == '') {
      cores.push(' ');
      return true;
    } else {
      v = parseFloat(v);
      cores.push(v);
    }
    var tp = $(this).data("type");
    if(tp == '1') {
      v = 0 - v;
    }
    var idx = $(this).data("idx");
    c[idx] = c[idx] + v;
  });
  $.each(c, function(i, item) {
    if(Math.abs(item) > Math.abs(s[i])) {
      c[i] = s[i];
    }
    score = score + c[i];
  })
  if(score < 0) {
    score = 0;
  }
  
  if(score < 60) {
    lev = '红色';
  } else if(score < 75) {
    lev = '橙色';
  } else if(score < 90) {
    lev = '黄色';
  }
  
  $(":checkbox:checked").each(function() {
    var v = $(this).val();
    if(spe != '') {
      spe += ",";
    }
    spe += v;
  })
  if(spe != '') {
    lev = '红色';
  }
  
  $.post(getRootPath() + "/company/safety-system/assess1-save", {
    cores : cores.join(","),
    score : score,
    lev : lev,
    danger : danger,
    spe : spe
  },function(result) {
    location.reload();
  })
}

var cores_ = '${bean.cores}', spe_ = '${bean.spe}';
if(cores_.trim() != '') {
  cores_ = cores_.split(",");
  var txt = $(":text");
  $.each(cores_, function(i, item) {
    if(item != '') {
      txt.eq(i).val(item);
    }
  })
}
if(spe_ != '') {
  spe_ = spe_.split(",");
  $.each(spe_, function(i, item) {
    $(":checkbox[value='" + item + "']").prop("checked", true);
  })
}
</script>
</html>