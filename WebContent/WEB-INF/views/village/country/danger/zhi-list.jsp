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
.clearfix:after{content:"\20";display:block;height:0;clear:both;visibility:hidden}
.clearfix{zoom:1}
.tabBar {border-bottom: 2px solid #337AB7}
.tabBar span {background-color: #e8e8e8;font-size:14px;cursor: pointer;display: inline-block;float: left;font-weight: bold;height: 38px;line-height: 38px;padding: 0 45px}
.tabBar span.current{background-color: #337AB7;color: #fff}
.tabCon {display: none;border:0px;}
</style>
<script type="text/javascript">
$(function(){
  $.Huitab("#tab_demo .tabBar span","#tab_demo .tabCon","current","click","0")});
</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>危险有害因素检索</span> 
    <span class="c-gray en">&gt;</span> <span>职业病有害因素</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div id="tab_demo" class="HuiTab">
      <div class="tabBar clearfix"><span>粉尘</span><span>化学因素</span><span>物理因素</span><span>放射性因素</span><span>生物因素</span><span>其他因素</span></div>
      <div class="tabCon">
        <div class="mt-20">
          <table class="table table-border table-bordered table-bg table-hover table-sort">
            <thead>
              <tr class="text-c">
                <th width="5%">序号</th>
                <th width="60%">名称</th>
                <th width="35%">CAS号</th>
              </tr>
            </thead>
            <tbody>
             <!-- 循环 -->
             <c:forEach items="${list }" varStatus="index" var="feng">
              <tr>
                <td>${index.index + 1 }</td>
                <td>${feng.name }</td>
                <td>${feng.cas }</td>
              </tr>
              </c:forEach>
              <!-- 循环结束 -->
            </tbody>
          </table>
        </div>
        <div class="Huialert Huialert-success mt-50"><i class="icon-remove"></i>注：还有以上未提及的可导致职业病的其他粉尘。</div>
      </div>
      <div class="tabCon">
        <div class="mt-20">
          <table class="table table-border table-bordered table-bg table-hover table-sort">
            <thead>
              <tr class="text-c">
                <th width="5%">序号</th>
                <th width="60%">名称</th>
                <th width="35%">CAS号</th>
              </tr>
            </thead>
            <tbody>
             <!-- 循环 -->
             <c:forEach items="${list2 }" varStatus="index" var="h">
              <tr>
                <td>${index.index + 1 }</td>
                <td>${h.name }</td>
                <td>${h.cas }</td>
              </tr>
              </c:forEach>
              <!-- 循环结束 -->
            </tbody>
          </table>
        </div>
        <div class="Huialert Huialert-success mt-50"><i class="icon-remove"></i>注：还有以上未提及的可导致职业病的其他化学因素。</div>
      </div>
      <div class="tabCon">
        <div class="mt-20">
          <table class="table table-border table-bordered table-bg table-hover">
            <thead>
              <tr class="text-c">
                <th width="5%">序号</th>
                <th width="95%">名称</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>1</td>
                <td>噪声</td>
              </tr>
              <tr>
                <td>2</td>
                <td>高温</td>
              </tr>
              <tr>
                <td>3</td>
                <td>低气压</td>
              </tr>
              <tr>
                <td>4</td>
                <td>高企业</td>
              </tr>
              <tr>
                <td>5</td>
                <td>高原低氧</td>
              </tr>
              <tr>
                <td>6</td>
                <td>振动</td>
              </tr>
              <tr>
                <td>7</td>
                <td>激光</td>
              </tr>
              <tr>
                <td>8</td>
                <td>低温</td>
              </tr>
              <tr>
                <td>9</td>
                <td>微波</td>
              </tr>
              <tr>
                <td>10</td>
                <td>紫外线</td>
              </tr>
              <tr>
                <td>11</td>
                <td>红外线</td>
              </tr>
              <tr>
                <td>12</td>
                <td>工频电磁场</td>
              </tr>
              <tr>
                <td>13</td>
                <td>高频电磁场</td>
              </tr>
              <tr>
                <td>14</td>
                <td>超高频电磁场</td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="Huialert Huialert-success mt-20"><i class="icon-remove"></i>注：还有以上未提及的可导致职业病的其他物理因素。</div>
      </div>
      <div class="tabCon">
        <div class="mt-20">
          <table class="table table-border table-bordered table-bg table-hover">
            <thead>
              <tr class="text-c">
                <th width="5%">序号</th>
                <th width="60%">名称</th>
                <th width="35%">备注</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>1</td>
                <td>密封放射源产生的电离辐射</td>
                <td>主要产生γ、中子等射线</td>
              </tr>
              <tr>
                <td>2</td>
                <td>非密封放射性物质</td>
                <td>可产生α、β、γ射线或中子</td>
              </tr>
              <tr>
                <td>3</td>
                <td>X射线装置（含CT机）产生的电离辐射</td>
                <td>X射线</td>
              </tr>
              <tr>
                <td>4</td>
                <td>加速器产生的电离辐射</td>
                <td>可产生电子射线、X射线、质子、重离子、中子以及感生放射性等</td>
              </tr>
              <tr>
                <td>5</td>
                <td>中子发生器产生的电离辐射</td>
                <td>主要是中子、γ射线等</td>
              </tr>
              <tr>
                <td>6</td>
                <td>氡及其短寿命子体</td>
                <td>限于矿工高氡暴露</td>
              </tr>
              <tr>
                <td>7</td>
                <td>铀及其化合物</td>
                <td>&nbsp;</td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="Huialert Huialert-success mt-20"><i class="icon-remove"></i>注：还有以上未提及的可导致职业病的其他放射性因素。</div>
      </div>
      <div class="tabCon">
        <div class="mt-20">
          <table class="table table-border table-bordered table-bg table-hover">
            <thead>
              <tr class="text-c">
                <th width="5%">序号</th>
                <th width="60%">名称</th>
                <th width="35%">备注</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>1</td>
                <td>艾滋病病毒</td>
                <td>限于医疗卫生人员及人民警察</td>
              </tr>
              <tr>
                <td>2</td>
                <td>布鲁氏菌</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>3</td>
                <td>伯氏疏螺旋体</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>4</td>
                <td>森林脑炎病毒</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>5</td>
                <td>炭疽芽孢杆菌</td>
                <td>&nbsp;</td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="Huialert Huialert-success mt-20"><i class="icon-remove"></i>注：还有以上未提及的可导致职业病的其他生物因素。</div>
      </div>
      <div class="tabCon">
        <div class="mt-20">
          <table class="table table-border table-bordered table-bg table-hover">
            <thead>
              <tr class="text-c">
                <th width="5%">序号</th>
                <th width="60%">名称</th>
                <th width="35%">备注</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>1</td>
                <td>金属烟</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>2</td>
                <td>井下不良作业条件</td>
                <td>限于井下工人</td>
              </tr>
              <tr>
                <td>3</td>
                <td>刮研作业</td>
                <td>限于手工刮研作业人员</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
    
    
  </div>
<script type="text/javascript">
$(function() {
  $('.table-sort').dataTable({
    "aaSorting": [[0, "asc"]],//默认第几个排序
    "bStateSave": false,//状态保存
    searching: false,
    ordering:  false,
    "aoColumnDefs": [
    ]
    });
});
</script> 
</body>
</html>