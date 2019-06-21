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
body h4{text-align: center;font-weight:bold;}
body .input-dan{float:none;height:22px;line-height:22px;width:90%;display:inline-block;border:1px solid #DDD}
.tab-fx th,.tab-fx td{padding:4px;}
</style>
<script type="text/javascript">
$(function() {
});

/* 判断评价结果和评定页面显示 */
function show(){
  $(".div-bzs").hide();
  $(".showcp").show();
}
</script> 
</head>
<body>
  <%-- <nav class="breadcrumb text-c">
    <a class="btn-page"  data-href="${ly }/company/safety-system/assess2"  data-title="作业条件风险程度评价" onclick="Hui_admin_tab(this)" href="javascript:;">作业条件风险程度评价</a>
    <a class="btn-page pagexz" href="javascript:;">风险矩阵法</a>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="parent.close_tab(function(){})">返回</a>
  </nav> --%>
  <div class="page-container">
    <%-- <div class="div-bzs">
      <div class="bt-fx2 text-c">
        <c:choose>
          <c:when test="${not empty bean }">
            <div class="bt-dj" style="font-size:40px">${bean.lev }</div>
            <font class="bt-fx" style="font-size:15px">事故发生的可能性（L）：${bean.l }&emsp;&emsp;事故后果的严重性（S）：${bean.s }</font>
            <div class="btn radius btn-cxcp" onclick="show()" >重新风险测评</div>
          </c:when>
          <c:otherwise>
            <div class="bt-dj" style="font-size:70px">?</div>
            <font class="bt-fx">当前还未进行风险评估</font>
            <div class="btn btn-primary radius btn-cxcp" onclick="show()" >前往风险测评</div>
          </c:otherwise>
        </c:choose>
      </div>
      <h4 class="mt-40" style="font-weight: normal;">企业安全风险矩阵与风险等级判别标准</h4>
      <table class="table table-border table-bordered table-bg mt-10 tab-fx" style="margin-left:10%;width:80%">
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
    </div> --%>
    <div class=" mb-40">
    <div class="mt-20 f-l" style="width:100%">
      <h4>发生事故的可能性（L）</h4>
      <table class="table table-border table-bordered table-bg table-hover table-sort tab-ndan" id="table_l">
        <thead>
          <tr class="text-c">
            <th rowspan="2" style="width:40px">序号</th>
            <th rowspan="2" style="width:15%">项目</th>
            <th colspan="4">取值标准</th>
            <th rowspan="2" style="width:40px">Ln</th>
            <th rowspan="2" style="width:60px">L</th>
          </tr>
          <tr class="text-c">
            <th>1</th>
            <th>2</th>
            <th>3</th>
            <th>4</th>
          </tr>
        </thead>
        <tr class="text-c">
          <td>1</td>
          <td><p>生产装置火灾危险性类别</p></td>
          <td>丙类（不含丙类）以下</td>
          <td>丙类</td>
          <td>乙类</td>
          <td>甲类</td>
          <td>L1</td>
          <td><input type="text" value="" class="input-dan ll-numberbox" data-options="min:1,max:4"/></td>
        </tr>
        <tr class="text-c">
          <td>2</td>
          <td><p>储罐区或储存场所火灾危险性类别</p></td>
          <td>丙类（不含丙类）以下</td>
          <td>丙类</td>
          <td>乙类</td>
          <td>甲类</td>
          <td>L2</td>
          <td><input type="text" value="" class="input-dan ll-numberbox" data-options="min:1,max:4"/></td>
        </tr>
        <tr class="text-c">
          <td>3</td>
          <td><p>化学品急性毒性危害类别</p></td>
          <td>类别4、类别5</td>
          <td>类别3</td>
          <td>类别2</td>
          <td>类别1</td>
          <td>L3</td>
          <td><input type="text" value="" class="input-dan ll-numberbox" data-options="min:1,max:4"/></td>
        </tr>
        <tr>
          <td class="text-c">4</td>
          <td><p>危险工艺和重点监管危险化学品</p></td>
          <td>
            <p>（1）不涉及重点监管危险化学品且不涉及危险工艺。</p>
            <p>（2）精细化工反应工艺安全风险已经安全评估。</p>
            <p>（3）国内首次采用的化工工艺已经安全可靠性论证。</p>
            <p>（4）除上述外的其他工艺。</p>
          </td>
          <td><p>涉及重点监管危险化学品但不涉及危险工艺。</p></td>
          <td><p>不涉及重点监管危险化学品但涉及危险工艺。</p></td>
          <td>
            <p>（1）涉及重点监管危险化学品且涉及危险工艺。</p>
            <p>（2）精细化工反应工艺安全风险未经安全评估。</p>
            <p>（3）国内首次采用的化工工艺未经安全可靠性论证。</p>
          </td>
          <td class="text-c">L4</td>
          <td class="text-c"><input type="text" value="" class="input-dan ll-numberbox" data-options="min:1,max:4"/></td>
        </tr>
        <tr>
          <td class="text-c">5</td>
          <td><p>反应压力(p)</p></td>
          <td>p≤0.1MPa</td>
          <td>0.1MPa＜p＜1.6MPa</td>
          <td>1.6MPa≤p＜10.0MPa</td>
          <td>p≥10.0Mpa</td>
          <td class="text-c">L5</td>
          <td class="text-c"><input type="text" value="" class="input-dan ll-numberbox" data-options="min:1,max:4"/></td>
        </tr>
        <tr>
          <td class="text-c">6</td>
          <td><p>反应温度（t）</p></td>
          <td>t≤20℃</td>
          <td>20℃＜t＜150℃</td>
          <td>150℃≤t＜450℃</td>
          <td>t≥450℃</td>
          <td class="text-c">L6</td>
          <td class="text-c"><input type="text" value="" class="input-dan ll-numberbox" data-options="min:1,max:4"/></td>
        </tr>
        <tr>
          <td class="text-c">7</td>
          <td><p>动火作业</p></td>
          <td><p>丙类（不含丙类）以下区域有动火作业。</p></td>
          <td><p>丙类区域有动火作业。</p></td>
          <td><p>乙类区域有动火作业。</p></td>
          <td><p>甲类区域有动火作业。</p></td>
          <td class="text-c">L7</td>
          <td class="text-c"><input type="text" value="" class="input-dan ll-numberbox" data-options="min:1,max:4"/></td>
        </tr>
        <tr>
          <td class="text-c">8</td>
          <td><p>自动化控制系统、安全仪表控制系统</p></td>
          <td><p>完好率和投用率100%。</p></td>
          <td><p>完好率或投用率低于100%。</p></td>
          <td><p>部分无自动化控制系统或安全仪表控制系统。</p></td>
          <td><p>全部无自动化控制系统或安全仪表控制系统。</p></td>
          <td class="text-c">L8</td>
          <td class="text-c"><input type="text" value="" class="input-dan ll-numberbox" data-options="min:1,max:4"/></td>
        </tr>
        <tr>
          <td class="text-c">9</td>
          <td><p>隐患排查治理</p></td>
          <td><p>定期开展隐患排查治理，所有隐患全部整改完成，形成闭环。</p></td>
          <td><p>存在未整改的一般隐患。</p></td>
          <td><p>存在限期内未整改的重大隐患。</p></td>
          <td><p>重大隐患未落实“五定”要求。</p></td>
          <td class="text-c">L9</td>
          <td class="text-c"><input type="text" value="" class="input-dan ll-numberbox" data-options="min:1,max:4"/></td>
        </tr>
        <tr>
          <td class="text-c">10</td>
          <td><p>安全管理制度与操作规程</p></td>
          <td><p>有健全的安全管理制度和操作规程。</p></td>
          <td><p>安全管理制度或操作规程不完善或有缺项。</p></td>
          <td><p>安全管理制度或操作规程未及时修订。</p></td>
          <td><p>未履行工艺、设备、管理等变更手续。</p></td>
          <td class="text-c">L10</td>
          <td class="text-c"><input type="text" value="" class="input-dan ll-numberbox" data-options="min:1,max:4"/></td>
        </tr>
      </table>
    </div>
    <div class="mt-20 f-l" style="width:100%">
      <h4>事故后果的严重性（S）</h4>
      <table class="table table-border table-bordered table-bg table-hover table-sort tab-ndan" id="table_s">
        <thead>
          <tr class="text-c">
            <th rowspan="2" style="width:40px">序号</th>
            <th rowspan="2" style="width:15%">项目</th>
            <th colspan="4">取值标准</th>
            <th rowspan="2" style="width:40px">Sn</th>
            <th rowspan="2" style="width:60px">S</th>
          </tr>
          <tr class="text-c">
            <th>1</th>
            <th>2</th>
            <th>3</th>
            <th>4</th>
          </tr>
        </thead>
        <tr class="text-c">
          <td>1</td>
          <td><p>企业内部不满足现行规范有关防火间距规定的装置、设施内的现场人数</p></td>
          <td>0-2人</td>
          <td>3-9人</td>
          <td>10-29人</td>
          <td>30人及以上</td>
          <td>S1</td>
          <td><input type="text" value="" class="input-dan ll-numberbox" data-options="min:1,max:4"/></td>
        </tr>
        <tr class="text-c">
          <td>2</td>
          <td><p>一座厂房、仓库或一个生产装置、罐区内的现场人数</p></td>
          <td>0-2人</td>
          <td>3-9人</td>
          <td>10-29人</td>
          <td>30人及以上</td>
          <td>S2</td>
          <td><input type="text" value="" class="input-dan ll-numberbox" data-options="min:1,max:4"/></td>
        </tr>
        <tr class="text-c">
          <td>3</td>
          <td><p>同一企业或相邻企业满足防火间距规定的相邻装置、设施内的现场人数</p></td>
          <td>0-2人</td>
          <td>3-9人</td>
          <td>10-29人</td>
          <td>30人及以上</td>
          <td>S3</td>
          <td><input type="text" value="" class="input-dan ll-numberbox" data-options="min:1,max:4"/></td>
        </tr>
        <tr class="text-c">
          <td>4</td>
          <td><p>重大危险源</p></td>
          <td>非重大危险源</td>
          <td>三、四级</td>
          <td>二级</td>
          <td>一级</td>
          <td>S4</td>
          <td><input type="text" value="" class="input-dan ll-numberbox" data-options="min:1,max:4"/></td>
        </tr>
        <tr class="text-c">
          <td>5</td>
          <td><p>企业边界外500米范围内</p></td>
          <td><p>无或有1个低密度人员场所</p></td>
          <td><p>有居住类高密度场所或公众聚集类高密度场所</p></td>
          <td><p>有1个高敏感场所、重要目标或特殊高密度场所</p></td>
          <td><p>有2个及以上高敏感场所、重要目标或特殊高密度场所</p></td>
          <td>S5</td>
          <td><input type="text" value="" class="input-dan ll-numberbox" data-options="min:1,max:4"/></td>
        </tr>
      </table>
    </div>
    <div class="mt-20 f-l mb-40" style="width:100%;text-align:center;">
      <button onClick="save()" class="btn btn-primary radius mt-20" type="button" style="padding: 0 70px;">
        <i class="Hui-iconfont mr-10">&#xe632;</i>提交评估
      </button>
    </div>
  </div>
  </div>
</body>
<script type="text/javascript">
/* var id = ${id};
var ls = '${bean.ls}', ss = '${bean.ss}';
if(ls != '') {
  ls = ls.split(",");
  ss = ss.split(",");
  $("#table_l :text").each(function(i, item) {
    $(this).val(ls[i]);
  })
  $("#table_s :text").each(function(i, item) {
    $(this).val(ss[i]);
  })
} */
function save() {
  var xx = 0;
  $(":text").each(function() {
    if($(this).val() == '') {
      layer.alert("请输入全部分值");
      xx = 1;
      return false;
    }
  })
  if(xx == 1) {
    return false;
  }
  var ls = [];
  var ss = [];
  var lev = '红色';
  var l = 0, s = 0, score = 0, danger = '重大风险企业';
  $("#table_l :text").each(function() {
    var v = parseInt($(this).val());
    l += v;
    ls.push(v);
  })
   $("#table_s :text").each(function() {
    var v = parseInt($(this).val());
    if( s < v) {
      s = v;
    }
    ss.push(v);
  })
  
  if(l % 10 == 0) {
    l = l / 10;
  } else {
    l = (l - l % 10) / 10 + 1;
  }
  score = l * s;
  if(score <= 3) {
    lev = '蓝色'; danger = "低风险企业";
  } else if(score <= 6) {
    lev = '黄色'; danger = "一般风险企业";
  } else if(score <= 9) {
    lev = '橙色'; danger = "较大风险企业";
  }
  layer.alert("本次测评结果为‘" + lev + "’风险。");
  /* $.post(getRootPath() + "/company/safety-system/assess3-save", {
    uid : id,
    ls : ls.join(","),
    ss : ss.join(","),
    l : l,
    s : s,
    score : score,
    lev : lev,
    danger : danger
  },function(result) {
    layer.alert("本次测评结果为‘" + lev + "’风险。",{},function(){
      parent.location.reload();
    })
  }) */
}
</script>
</html>