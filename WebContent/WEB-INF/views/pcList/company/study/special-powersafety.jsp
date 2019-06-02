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
.wid1{width:30%}
.wid2{width:30%}
.wid3{width:30%}
.wid4{width:10%}
.wid5{width:70%}
.btn-cz{margin:auto;background:#eee;border:1px solid #ddd;padding:0 5px;height:20px;line-height:20px;border-radius:5px;margin-left:15px}
.btn-cz:hover{border:1px solid #06c;color:#06c;text-decoration: none;}
body .check-box label{margin-left:5px;}
.one_danger .list_odan{margin-left:20px}
.two_danger .list_twdan{margin-left:45px;}
.one_danger .Hui-iconfont{margin-top:2px}
.one_danger,.list_danger{display:inline-block}
.one_danger,.one_danger .list_odan,.two_danger,.two_danger .list_twdan,.list_danger{float:none;}
.check-box{width:30%;margin-bottom:15px;}
.btn-group .btn{height: 34px;line-height: 34px;padding: 0 25px;}
.one-danrisk{float:left;width:100%;border-bottom:1px solid #ddd}
.one-danrisk p{float:left;width:80%;padding: 8px}
.table tbody tr td .one-danrisk a{float:right;margin-top:8px;margin-right:8px;}
</style>
<style type="text/css">
	table,th,td{
		border:1px solid #E0E0E0;
		font-size:15px;
		text-align:center;
		vertical-align:middle;
	}
	td{ 
		height:30px;
	}
	td input{
		font-size:15px;
		border:none;
		width:100%;
		text-align:center;
		vertical-align:middle;
		height:30px;
	}
</style>
<script type="text/javascript">
/* 弹窗工具添加 */
function edit(flag) {
	switch(flag){
		case 1:
			show_dialog(" ", "/company/study/firework");
			break;
		
		default:
			return;
	}
  
}

</script>
</head>
<body>
	<!--<nav class="breadcrumb">
	    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
	    <span class="c-gray en">&gt;</span> <span>风险研判与承诺公告</span> 
	    <span class="c-gray en">&gt;</span> <span>特殊流程作业</span> 
	    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="parent.close_tab(function(){})">返回</a>
	  </nav>-->
	
	
	<h3 class="text-c">《临时用电安全作业证》</h3>
    <div class="mt-20">
    	
    	<!--<table style="width:50%;" cellpadding="0" cellspacing="0"> table-sort tab-ndan table-bg   table   table-border table-bordered-->
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 15%;">吊装地点</td>
    	<td style="width: 15%;"><input id="dzdd" type="text"/></td>
    	<td style="width: 15%;">吊装工具名称</td>
    	<td style="width: 15%;"><input id="dzgjmc" type="text"/></td>
    	<td style="width: 15%;">作业证编号</td>
    	<td><input id="zyzbh" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">吊装人员及特殊工种作业证号</td>
    	<td style="width: 25%;"><input id="dzryzh" type="text"/></td>
    	<td style="width: 25%;">监护人</td>
    	<td><input id="jhr" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">吊装指挥及特殊工种作业证号</td>
    	<td style="width: 25%;"><input id="dzzhzh" type="text"/></td>
    	<td style="width: 25%;">起吊重物质量(t)</td>
    	<td><input id="qdzwzl" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">作业起始时间</td>
    	<td style="width: 25%;"><input id="zysj_s" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
    	<td style="width: 25%;">作业截止时间</td>
    	<td><input id="zysj_e" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" type="text"/></td>
    	</tr>
    </table>
       <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">吊装内容</td>
    	<td><input id="dznr" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">危害辨识</td>
    	<td><input id="whbs" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">序号</td>
    	<td style="width: 75%;">安全措施</td>
    	<td>确认人</td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">1</td>
    	<td style="width: 75%;">吊装质量大于等于40t的重物和土建工程主体结构；吊装物体虽不足40t，
    	但形状复杂、刚度小、长径比大、精密贵重，作业条件特殊，已编制吊装作业方案，且经作业主管部门和安全管理部门审查，报主管（副总经理/总工程师）批准</td>
    	<td><input id="qrr1" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">2</td>
    	<td style="width: 75%;">指派专人监护，并监守岗位，非作业人员禁止入内</td>
    	<td><input id="qrr2" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">3</td>
    	<td style="width: 75%;">作业人员已按规定佩戴防护器具和个体防护用品</td>
    	<td><input id="qrr3" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">4</td>
    	<td style="width: 75%;">已与分厂（车间）负责人取得联系，建立联系信号</td>
    	<td><input id="qrr4" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">5</td>
    	<td style="width: 75%;">已在吊装现场设置安全警戒标志，无关人员不许进入作业现场</td>
    	<td><input id="qrr5" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">6</td>
    	<td style="width: 75%;">夜间作业采用足够的照明</td>
    	<td><input id="qrr6" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">7</td>
    	<td style="width: 75%;">室外作业遇到（大雪/暴雨/大雾/六级以上大风），已停止作业</td>
    	<td><input id="qrr7" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">8</td>
    	<td style="width: 75%;">检查起重吊装设备、钢丝绳、缆风绳、链条、吊钩等各种机具，保证安全可靠</td>
    	<td><input id="qrr8" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">9</td>
    	<td style="width: 75%;">明确分工、坚守岗位，并按规定的联络信号，统一指挥</td>
    	<td><input id="qrr9" type="text"/></td>
    	</tr>
    </table>
        <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">10</td>
    	<td style="width: 75%;">将建筑物、构筑物作为锚点，需经工程处审查核算并批准</td>
    	<td><input id="qrr10" type="text"/></td>
    	</tr>
    </table>
        <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">11</td>
    	<td style="width: 75%;">吊装绳索、缆风绳、拖拉绳等避免同带电线路接触，并保持安全距离</td>
    	<td><input id="qrr11" type="text"/></td>
    	</tr>
    </table>
        <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">12</td>
    	<td style="width: 75%;">人员随同吊装重物或吊装机械升降，应采取可靠的安全措施，并经过现场指挥人员批准</td>
    	<td><input id="qrr12" type="text"/></td>
    	</tr>
    </table>
        <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">13</td>
    	<td style="width: 75%;">利用管道、管架、电杆、机电设备等作吊装锚点，不准吊装</td>
    	<td><input id="qrr13" type="text"/></td>
    	</tr>
    </table>
        <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">14</td>
    	<td style="width: 75%;">悬吊重物下方站人、通行和工作，不准吊装</td>
    	<td><input id="qrr14" type="text"/></td>
    	</tr>
    </table>
        <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">15</td>
    	<td style="width: 75%;">超负荷或重物质量不明，不准吊装</td>
    	<td><input id="qrr15" type="text"/></td>
    	</tr>
    </table>
        <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">16</td>
    	<td style="width: 75%;">斜拉重物、重物埋在地下或重物坚固不牢，绳打结、绳不齐，不准吊装</td>
    	<td><input id="qrr16" type="text"/></td>
    	</tr>
    </table>
        <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">17</td>
    	<td style="width: 75%;">棱角重物没有衬垫措施，不准吊装</td>
    	<td><input id="qrr17" type="text"/></td>
    	</tr>
    </table>
        <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">18</td>
    	<td style="width: 75%;">安全装置失灵，不准吊装</td>
    	<td><input id="qrr18" type="text"/></td>
    	</tr>
    </table>
        <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">19</td>
    	<td style="width: 75%;">用定型起重吊装机械（履带吊车/轮胎吊车/矫式吊车等）进行吊装作业，遵守该定型机械的操作规程</td>
    	<td><input id="qrr19" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">20</td>
    	<td style="width: 75%;">作业过程中应先用低高度、短行程试吊</td>
    	<td><input id="qrr20" type="text"/></td>
    	</tr>
    </table>
        <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">21</td>
    	<td style="width: 75%;">作业现场出现危险品泄漏，立即停止作业，撤离人员</td>
    	<td><input id="qrr21" type="text"/></td>
    	</tr>
    </table>
        <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">22</td>
    	<td style="width: 75%;">作业完成后现场杂物已清理</td>
    	<td><input id="qrr22" type="text"/></td>
    	</tr>
    </table>
        <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">23</td>
    	<td style="width: 75%;">吊装作业人员持有法定的有效的证件</td>
    	<td><input id="qrr23" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">24</td>
    	<td style="width: 75%;">地下通讯电（光）缆、局域网络电（光）缆、排水沟的盖板，承重吊装机械的负重量已确认，保护措施已落实</td>
    	<td><input id="qrr24" type="text"/></td>
    	</tr>
    </table>
        <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">25</td>
    	<td style="width: 75%;">起吊物的质量（<input id="qdwzl25" style="width:6%;" type="text" />）t，经确认，在吊装机械的承重范围</td>
    	<td><input id="qrr25" type="text"/></td>
    	</tr>
    </table>
        <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">26</td>
    	<td style="width: 75%;">在吊装高度的管线、电缆桥架已做好防护措施</td>
    	<td><input id="qrr26" type="text"/></td>
    	</tr>
    </table>
        <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">27</td>
    	<td style="width: 75%;">作业现场围栏、警戒线、警告牌、夜间警示灯已按要求设置</td>
    	<td><input id="qrr27" type="text"/></td>
    	</tr>
    </table>
        <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">28</td>
    	<td style="width: 75%;">作业高度和转臂范围内，无架空线路</td>
    	<td><input id="qrr28" type="text"/></td>
    	</tr>
    </table>
        <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">29</td>
    	<td style="width: 75%;">人员出入口和撤离安全措施已落实：A.指示牌；B.指示灯（<input id="xzab29" style="width:6%;" type="text" placeholder="A/B" />）</td>
    	<td><input id="qrr29" type="text"/></td>
    	</tr>
    </table>
        <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">30</td>
    	<td style="width: 75%;">在爆炸危险生产区域内作业，机动车排气管已装火星熄灭器</td>
    	<td><input id="qrr30" type="text"/></td>
    	</tr>
    </table>
        <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">31</td>
    	<td style="width: 75%;">现场夜间有充足照明：36V、24V、12V防水型灯；36V、24V、12V防爆型灯</td>
    	<td><input id="qrr31" type="text"/></td>
    	</tr>
    </table>    
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">32</td>
    	<td style="width: 75%;">作业人员已佩戴防护器具</td>
    	<td><input id="qrr32" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 10%;">33</td>
    	<td style="width: 75%;">其他安全措施：<input id="qtaqcs" style="width:70%;" type="text"/>编制人：<input id="bzr" style="width:10%;" type="text"/></td>
    	<td><input id="qrr33" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">实施安全教育人</td>
    	<td style="width: 25%;"><input id="ssaqjyr1" type="text"/></td>
    	<td style="width: 25%;"><input id="ssaqjyr2" type="text"/></td>
    	<td><input id="ssaqjyr3" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">生产单位安全部门负责人（签字）：</td>
    	<td style="width: 25%;"><input id="scdwaqbmfzr" type="text"/></td>
    	<td style="width: 25%;">生产单位负责人（签字）：</td>
    	<td><input id="scdwfzr" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td style="width: 25%;">作业单位安全部门负责人（签字）：</td>
    	<td style="width: 25%;"><input id="zydwaqbmfzr" type="text"/></td>
    	<td style="width: 25%;">作业单位负责人（签字）：</td>
    	<td><input id="zydwfzr" type="text"/></td>
    	</tr>
    </table>
    <table class="table-hover" cellspacing="0" cellpadding="0" style="width:80%;margin-left: 10%;">
    	<tr>
    	<td>审批部门意见：<input id="spbmyj" style="width:60%;" type="text"/>签字：<input id="spbmqz" style="width:10%;" type="text"/>日期：<input id="spbmqzsj" style="width:10%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/></td>
    	</tr>
    </table>


  </div>
  
  <div class="mt-20 f-l mb-40" style="width:100%;text-align:center;">
      <button onClick="save()" class="btn btn-primary radius mt-20" type="button" style="padding: 0 70px;">
        <i class="Hui-iconfont mr-10">&#xe632;</i>保存
      </button>
  </div>

  
</body>
<script type="text/javascript" src="/js/fxgk/tbl-rowspan-reset.js"></script>
<script type="text/javascript">

if("${action}" == "read"){
	
	$("input").attr("disabled","disabled");
	$("input").css("background-color","white");
	
    $("#dzdd").val("${hi.dzdd}");
    $("#dzgjmc").val("${hi.dzgjmc}");
    $("#zyzbh").val("${hi.zyzbh}");
    $("#dzryzh").val("${hi.dzryzh}");
    $("#jhr").val("${hi.jhr}");
    $("#dzzhzh").val("${hi.dzzhzh}");
    $("#qdzwzl").val("${hi.qdzwzl}"); 
//    $("#zysj_s").val("${hi.zysjS}");
	$("#zysj_s").val("<fmt:formatDate value='${hi.zysjS}' pattern='yyyy-MM-dd HH:mm'/>");
    $("#zysj_e").val("<fmt:formatDate value='${hi.zysjE}' pattern='yyyy-MM-dd HH:mm'/>");
    $("#dznr").val("${hi.dznr}");
    $("#whbs").val("${hi.whbs}");

    $("#qrr1").val("${hi.qrr1}");
    $("#qrr2").val("${hi.qrr2}");
    $("#qrr3").val("${hi.qrr3}");
    $("#qrr4").val("${hi.qrr4}");
    $("#qrr5").val("${hi.qrr5}");
    $("#qrr6").val("${hi.qrr6}");
    $("#qrr7").val("${hi.qrr7}");
    $("#qrr8").val("${hi.qrr8}");
    $("#qrr9").val("${hi.qrr9}");
    $("#qrr10").val("${hi.qrr10}");
    $("#qrr11").val("${hi.qrr11}");
    $("#qrr12").val("${hi.qrr12}");
    $("#qrr13").val("${hi.qrr13}");
    $("#qrr14").val("${hi.qrr14}");
    $("#qrr15").val("${hi.qrr15}");
    $("#qrr16").val("${hi.qrr16}");
    $("#qrr17").val("${hi.qrr17}");
    $("#qrr18").val("${hi.qrr18}");
    $("#qrr19").val("${hi.qrr19}");
    $("#qrr20").val("${hi.qrr20}");
    $("#qrr21").val("${hi.qrr21}");
    $("#qrr22").val("${hi.qrr22}");
    $("#qrr23").val("${hi.qrr23}");
    $("#qrr24").val("${hi.qrr24}");
    $("#qrr25").val("${hi.qrr25}");
    $("#qrr26").val("${hi.qrr26}");
    $("#qrr27").val("${hi.qrr27}");
    $("#qrr28").val("${hi.qrr28}");
    $("#qrr29").val("${hi.qrr29}");
    $("#qrr30").val("${hi.qrr30}");
    $("#qrr31").val("${hi.qrr31}");
    $("#qrr32").val("${hi.qrr32}");
    $("#qrr33").val("${hi.qrr33}");
    $("#qdwzl25").val("${hi.qdwzl25}");
    $("#xzab29").val("${hi.xzab29}");
    $("#qtaqcs").val("${hi.qtaqcs}");
    $("#bzr").val("${hi.bzr}");
    
    $("#ssaqjyr1").val("${hi.ssaqjyr1}");
    $("#ssaqjyr2").val("${hi.ssaqjyr2}");
    $("#ssaqjyr3").val("${hi.ssaqjyr3}");
    
    $("#scdwaqbmfzr").val("${hi.scdwaqbmfzr}");
    $("#scdwfzr").val("${hi.scdwfzr}");
    
    $("#zydwaqbmfzr").val("${hi.zydwaqbmfzr}");
    $("#zydwfzr").val("${hi.zydwfzr}");
    
    $("#spbmyj").val("${hi.spbmyj}");
    $("#spbmqz").val("${hi.spbmqz}");
    $("#spbmqzsj").val("<fmt:formatDate value='${hi.spbmqzsj}' pattern='yyyy-MM-dd'/>");
	
}

function save() {
  var obj = {
	  "dzdd" : $("#dzdd").val(),
	  "dzgjmc" : $("#dzgjmc").val(),
      "zyzbh" : $("#zyzbh").val(),
      "dhzyjb" : $("#dhzyjb").val(),
      "dzryzh" : $("#dzryzh").val(),
      "jhr" : $("#jhr").val(),
      "dzzhzh" : $("#dzzhzh").val(),
      "qdzwzl" : $("#qdzwzl").val(), 
      "zysjS" : $("#zysj_s").val(),
      "zysjE" : $("#zysj_e").val(),
      "dznr" : $("#dznr").val(),
      "whbs" : $("#whbs").val(),

      "qrr1" : $("#qrr1").val(),
      "qrr2" : $("#qrr2").val(),
      "qrr3" : $("#qrr3").val(),
      "qrr4" : $("#qrr4").val(),
      "qrr5" : $("#qrr5").val(),
      "qrr6" : $("#qrr6").val(),
      "qrr7" : $("#qrr7").val(),
      "qrr8" : $("#qrr8").val(),
      "qrr9" : $("#qrr9").val(),
      "qrr10" : $("#qrr10").val(),
      "qrr11" : $("#qrr11").val(),
      "qrr12" : $("#qrr12").val(),
      "qrr13" : $("#qrr13").val(),
      "qrr14" : $("#qrr14").val(),
      "qrr15" : $("#qrr15").val(),
      "qrr16" : $("#qrr16").val(),
      "qrr17" : $("#qrr17").val(),
      "qrr18" : $("#qrr18").val(),
      "qrr19" : $("#qrr19").val(),
      "qrr20" : $("#qrr20").val(),
      "qrr21" : $("#qrr21").val(),
      "qrr22" : $("#qrr22").val(),
      "qrr23" : $("#qrr23").val(),
      "qrr24" : $("#qrr24").val(),
      "qrr25" : $("#qrr25").val(),
      "qrr26" : $("#qrr26").val(),
      "qrr27" : $("#qrr27").val(),
      "qrr28" : $("#qrr28").val(),
      "qrr29" : $("#qrr29").val(),
      "qrr30" : $("#qrr30").val(),
      "qrr31" : $("#qrr31").val(),
      "qrr32" : $("#qrr32").val(),
      "qrr33" : $("#qrr33").val(),
      "qdwzl25" : $("#qdwzl25").val(),
      "xzab29" : $("#xzab29").val(),
      "qtaqcs" : $("#qtaqcs").val(),
      "bzr" : $("#bzr").val(),
      
      "ssaqjyr1" : $("#ssaqjyr1").val(),
      "ssaqjyr2" : $("#ssaqjyr2").val(),
      "ssaqjyr3" : $("#ssaqjyr3").val(),
      
      "scdwaqbmfzr" : $("#scdwaqbmfzr").val(),
      "scdwfzr" : $("#scdwfzr").val(),
      
      "zydwaqbmfzr" : $("#zydwaqbmfzr").val(),
      "zydwfzr" : $("#zydwfzr").val(),
      
      "spbmyj" : $("#spbmyj").val(),
      "spbmqz" : $("#spbmqz").val(),
      "spbmqzsj" : $("#spbmqzsj").val(),
  
  }
  if(obj.dzdd == '') {
    $("#dzdd").focus();
    alert("请填写吊装地点！");
    return false;
  }
  if(obj.dzgjmc == '') {
    $("#dzgjmc").focus();
    alert("请填写吊装工具名称！");
    return false;
  }
  //alert($("#sqdwqzsj").val());
  var i = layer.load();
  $.post(getRootPath() + "/company/study/powersafety-save", obj,function(result){
    layer.close(i);
    if(result.status == '1') {
    	layer.alert(result.map.message);
    } else {
    	layer.alert("保存成功！");
        parent.location.reload();
    }
  })
}

//function del(id) {
//layer.confirm("确定删除该记录?", function(r) {
//  $.post(getRootPath() + "/company/safety-system/aCompanyManual-save", {
//    id : id,
//    del : 1
//  }, function(result) {
//    layer.close(r);
//    location.reload();
//  })
//})
//}
</script>
</html>