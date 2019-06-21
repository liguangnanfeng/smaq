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
.Huifold .item{ position:relative}
.Huifold .item h4{margin:0;font-weight:bold;position:relative;border-top: 1px solid #fff;font-size:15px;line-height:22px;padding:7px 10px;background-color:#eee;cursor:pointer;padding-right:30px}
.Huifold .item h4 b{position:absolute;display: block; cursor:pointer;right:10px;top:7px;width:16px;height:16px; text-align:center; color:#666}
.Huifold .item .info{display:none;padding:10px}
.Huifold .item .selected{background:#2472D4;color:#fff}
.Huifold .item .selected b{color:#fff}
</style>
<script type="text/javascript">
$(function(){
  $.Huifold("#Huifold1 .item h4","#Huifold1 .item .info","fast",2,"click");
});
</script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span> 
    <span class="c-gray en">&gt;</span> <span>危险有害因素检索</span> 
    <span class="c-gray en">&gt;</span> <span>易制爆危险化学品</span> 
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
  <div class="page-container">
    <div class="mt-20">
      <ul id="Huifold1" class="Huifold">
        <li class="item">
          <h4 class="selected">1.酸类<b>+</b></h4>
          <div class="info">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
              <thead>
                <tr class="text-c">
                  <th width="5%">序号</th>
                  <th width="35%">品名</th>
                  <th width="30%">别名</th>
                  <th width="15%">CAS号</th>
                  <th width="15%">主要的燃爆危险性分类</th>
                </tr>
              </thead>
              <tbody>
              <tr class="text-c">
                <td>1.1</td>
                <td>硝酸</td>
                <td> </td>
                <td>7697-37-2</td>
                <td>氧化性液体，类别3</td>
              </tr>
              <tr class="text-c">
                <td>1.2</td>
                <td>发烟硝酸</td>
                <td> </td>
                <td>52583-42-3</td>
                <td>氧化性液体，类别1</td>
              </tr>
              <tr class="text-c">
                <td rowspan="3">1.3</td>
                <td>高氯酸[浓度＞72%]</td>
                <td>过氯酸</td>
                <td  rowspan="3">7601-90-3</td>
                <td>氧化性液体，类别1</td>
              </tr>
              <tr class="text-c">
                <td>高氯酸[浓度50%～72%]</td>
                <td></td>
                <td>氧化性液体，类别1</td>
              </tr>
              <tr class="text-c">
                <td>高氯酸[浓度≤50%]</td>
                <td></td>
                <td>氧化性液体，类别2</td>
              </tr>
              </tbody>
            </table>
          </div>
        </li>
        <li class="item">
          <h4>2.硝酸盐类<b>+</b></h4>
          <div class="info">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
              <thead>
                <tr class="text-c">
                  <th width="5%">序号</th>
                  <th width="35%">品名</th>
                  <th width="30%">别名</th>
                  <th width="15%">CAS号</th>
                  <th width="15%">主要的燃爆危险性分类</th>
                </tr>
              </thead>
              <tbody>
              <tr class="text-c">
                <td>2.1</td>
                <td>硝酸钠</td>
                <td> </td>
                <td>7631-99-4</td>
                <td>氧化性固体，类别3</td>
              </tr>
              <tr class="text-c">
                <td>2.2</td>
                <td>硝酸钾</td>
                <td> </td>
                <td>7757-79-1</td>
                <td>氧化性固体，类别3</td>
              </tr>
              <tr class="text-c">
                <td>2.3</td>
                <td>硝酸铯</td>
                <td> </td>
                <td>7789-18-6</td>
                <td>氧化性固体，类别3</td>
              </tr>
              <tr class="text-c">
                <td>2.4</td>
                <td>硝酸镁</td>
                <td> </td>
                <td>10377-60-3</td>
                <td>氧化性固体，类别3</td>
              </tr>
              <tr class="text-c">
                <td>2.5</td>
                <td>硝酸钙</td>
                <td> </td>
                <td>10124-37-5</td>
                <td>氧化性固体，类别3</td>
              </tr>
              <tr class="text-c">
                <td>2.6</td>
                <td>硝酸锶</td>
                <td> </td>
                <td>10042-76-9</td>
                <td>氧化性固体，类别3</td>
              </tr>
              <tr class="text-c">
                <td>2.7</td>
                <td>硝酸钡</td>
                <td> </td>
                <td>10022-31-8</td>
                <td>氧化性固体，类别2</td>
              </tr>
              <tr class="text-c">
                <td>2.8</td>
                <td>硝酸镍</td>
                <td>二硝酸镍</td>
                <td>13138-45-9</td>
                <td>氧化性固体，类别2</td>
              </tr>
              <tr class="text-c">
                <td>2.9</td>
                <td>硝酸银</td>
                <td> </td>
                <td>7761-88-8</td>
                <td>氧化性固体，类别2</td>
              </tr>
              <tr class="text-c">
                <td>2.10</td>
                <td>硝酸锌</td>
                <td> </td>
                <td>7779-88-6</td>
                <td>氧化性固体，类别2</td>
              </tr>
              <tr class="text-c">
                <td>2.11</td>
                <td>硝酸铅</td>
                <td> </td>
                <td>10099-74-8</td>
                <td>氧化性固体，类别2</td>
              </tr>
              </tbody>
            </table>
          </div>
        </li>
        <li class="item">
          <h4>3.氯酸盐类<b>+</b></h4>
          <div class="info">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
              <thead>
                <tr class="text-c">
                  <th width="5%">序号</th>
                  <th width="35%">品名</th>
                  <th width="30%">别名</th>
                  <th width="15%">CAS号</th>
                  <th width="15%">主要的燃爆危险性分类</th>
                </tr>
              </thead>
              <tbody>
              <tr class="text-c">
                <td  rowspan="2">3.1</td>
                <td>氯酸钠</td>
                <td> </td>
                <td  rowspan="2">7775-09-9</td>
                <td>氧化性固体，类别1</td>
              </tr>
              <tr class="text-c">
                <td>氯酸钠溶液</td>
                <td> </td>
                <td>氧化性液体，类别3*</td>
              </tr>
              <tr class="text-c">
                <td rowspan="2">3.2</td>
                <td>氯酸钾</td>
                <td> </td>
                <td  rowspan="2">3811-04-9</td>
                <td>氧化性固体，类别1</td>
              </tr>
              <tr class="text-c">
                <td>氯酸钾溶液</td>
                <td> </td>
                <td>氧化性液体，类别3*</td>
              </tr>
              <tr class="text-c">
                <td>3.3</td>
                <td>氯酸铵</td>
                <td> </td>
                <td>10192-29-7</td>
                <td>爆炸物，不稳定爆炸物</td>
              </tr>
              </tbody>
            </table>
          </div>
        </li>
        <li class="item">
          <h4>4.高氯酸盐类<b>+</b></h4>
          <div class="info">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
              <thead>
                <tr class="text-c">
                  <th width="5%">序号</th>
                  <th width="35%">品名</th>
                  <th width="30%">别名</th>
                  <th width="15%">CAS号</th>
                  <th width="15%">主要的燃爆危险性分类</th>
                </tr>
              </thead>
              <tbody>
              <tr class="text-c">
                <td>4.1</td>
                <td>高氯酸锂</td>
                <td>过氯酸锂</td>
                <td>7791-03-9</td>
                <td>氧化性固体，类别2</td>
              </tr>
              <tr class="text-c">
                <td>4.2</td>
                <td>高氯酸钠</td>
                <td>过氯酸钠</td>
                <td>7601-89-0</td>
                <td>氧化性固体，类别1</td>
              </tr>
              <tr class="text-c">
                <td>4.3</td>
                <td>高氯酸钾</td>
                <td>过氯酸钾</td>
                <td>7778-74-7</td>
                <td>氧化性固体，类别1</td>
              </tr>
              <tr class="text-c">
                <td>4.4</td>
                <td>高氯酸铵</td>
                <td>过氯酸铵</td>
                <td>7790-98-9</td>
                <td>爆炸物，1.1项<br>氧化性固体，类别1</td>
              </tr>
              </tbody>
            </table>
          </div>
        </li>
        <li class="item">
          <h4>5.重铬酸盐类<b>+</b></h4>
          <div class="info">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
              <thead>
                <tr class="text-c">
                  <th width="5%">序号</th>
                  <th width="35%">品名</th>
                  <th width="30%">别名</th>
                  <th width="15%">CAS号</th>
                  <th width="15%">主要的燃爆危险性分类</th>
                </tr>
              </thead>
              <tbody>
              <tr class="text-c">
                <td>5.1</td>
                <td>重铬酸锂</td>
                <td> </td>
                <td>13843-81-7</td>
                <td>氧化性固体，类别2</td>
              </tr>
              <tr class="text-c">
                <td>5.2</td>
                <td>重铬酸钠</td>
                <td>红矾钠</td>
                <td>10588-01-9</td>
                <td>氧化性固体，类别2</td>
              </tr>
              <tr class="text-c">
                <td>5.3</td>
                <td>重铬酸钾</td>
                <td>红矾钾</td>
                <td>7778-50-9</td>
                <td>氧化性固体，类别2</td>
              </tr>
              <tr class="text-c">
                <td>5.4</td>
                <td>重铬酸铵</td>
                <td>红矾铵</td>
                <td>7789-09-5</td>
                <td>氧化性固体，类别2</td>
              </tr>
              </tbody>
            </table>
          </div>
        </li>
        <li class="item">
          <h4>6.过氧化物和超氧化物类<b>+</b></h4>
          <div class="info">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
              <thead>
                <tr class="text-c">
                  <th width="5%">序号</th>
                  <th width="35%">品名</th>
                  <th width="30%">别名</th>
                  <th width="15%">CAS号</th>
                  <th width="15%">主要的燃爆危险性分类</th>
                </tr>
              </thead>
              <tbody>
              <tr class="text-c">
                <td>6.1</td>
                <td>过氧化氢溶液（含量＞8%）</td>
                <td>双氧水</td>
                <td>7722-84-1</td>
                <td></td>
              </tr>
              <tr class="text-c">
                <td>6.2</td>
                <td>过氧化锂</td>
                <td>二氧化锂</td>
                <td>12031-80-0</td>
                <td>氧化性固体，类别2</td>
              </tr>
              <tr class="text-c">
                <td>6.3</td>
                <td>过氧化钠</td>
                <td>双氧化钠；二氧化钠</td>
                <td>1313-60-6</td>
                <td>氧化性固体，类别1</td>
              </tr>
              <tr class="text-c">
                <td>6.4</td>
                <td>过氧化钾</td>
                <td>二氧化钾</td>
                <td>17014-71-0</td>
                <td>氧化性固体，类别1</td>
              </tr>
              <tr class="text-c">
                <td>6.5</td>
                <td>过氧化镁</td>
                <td>二氧化镁</td>
                <td>1335-26-8</td>
                <td>氧化性液体，类别2</td>
              </tr>
              <tr class="text-c">
                <td>6.6</td>
                <td>过氧化钙</td>
                <td>二氧化钙</td>
                <td>1305-79-9</td>
                <td>氧化性固体，类别2</td>
              </tr>
              <tr class="text-c">
                <td>6.7</td>
                <td>过氧化锶</td>
                <td>二氧化锶</td>
                <td>1314-18-7</td>
                <td>氧化性固体，类别2</td>
              </tr>
              <tr class="text-c">
                <td>6.8</td>
                <td>过氧化钡</td>
                <td>二氧化钡</td>
                <td>1304-29-6</td>
                <td>氧化性固体，类别2</td>
              </tr>
              <tr class="text-c">
                <td>6.9</td>
                <td>过氧化锌</td>
                <td>二氧化锌</td>
                <td>1314-22-3</td>
                <td>氧化性固体，类别2</td>
              </tr>
              <tr class="text-c">
                <td>6.10</td>
                <td>过氧化脲</td>
                <td>过氧化氢尿素；过氧化氢脲</td>
                <td>124-43-6</td>
                <td>氧化性固体，类别3</td>
              </tr>
              <tr class="text-c">
                <td  rowspan="2">6.11</td>
                <td>过乙酸[含量≤16%,含水≥39%,含乙酸≥15%,含过氧化氢≤24%,含有稳定剂]</td>
                <td>过醋酸；过氧乙酸；乙酰过氧化氢</td>
                <td  rowspan="2">79-21-0</td>
                <td>有机过氧化物F型</td>
              </tr>
              <tr class="text-c">
                <td>过乙酸[含量≤43%,含水≥5%,含乙酸≥35%,含过氧化氢≤6%,含有稳定剂]</td>
                <td> </td>
                <td>易燃液体，类别3<br>有机过氧化物，D型</td>
              </tr>
              <tr class="text-c">
                <td>6.12</td>
                <td>过氧化二异丙苯[52%＜含量≤100%]</td>
                <td>二枯基过氧化物；硫化剂DCP</td>
                <td>80-43-3</td>
                <td>有机过氧化物，F型</td>
              </tr>
              <tr class="text-c">
                <td>6.13</td>
                <td>过氧化氢苯甲酰</td>
                <td>过苯甲酸</td>
                <td>93-59-4</td>
                <td>有机过氧化物，C型</td>
              </tr>
              <tr class="text-c">
                <td>6.14</td>
                <td>超氧化钠</td>
                <td> </td>
                <td>12034-12-7</td>
                <td>氧化性固体，类别1</td>
              </tr>
              <tr class="text-c">
                <td>6.15</td>
                <td>超氧化钾</td>
                <td> </td>
                <td>12030-88-5</td>
                <td>氧化性固体，类别1</td>
              </tr>
              </tbody>
            </table>
          </div>
        </li>
        <li class="item">
          <h4>7.易燃物还原剂类<b>+</b></h4>
          <div class="info">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
              <thead>
                <tr class="text-c">
                  <th width="5%">序号</th>
                  <th width="35%">品名</th>
                  <th width="30%">别名</th>
                  <th width="15%">CAS号</th>
                  <th width="15%">主要的燃爆危险性分类</th>
                </tr>
              </thead>
              <tbody>
              <tr class="text-c">
                <td>7.1</td>
                <td>锂</td>
                <td>金属锂</td>
                <td>7439-93-2</td>
                <td>遇水放出易燃气体的物质和混合物，类别1</td>
              </tr>
              <tr class="text-c">
                <td>7.2</td>
                <td>钠</td>
                <td>金属钠</td>
                <td>7440-23-5</td>
                <td>遇水放出易燃气体的物质和混合物，类别1</td>
              </tr>
              <tr class="text-c">
                <td>7.3</td>
                <td>钾</td>
                <td>金属钾</td>
                <td>7440-09-7</td>
                <td>遇水放出易燃气体的物质和混合物，类别1</td>
              </tr>
              <tr class="text-c">
                <td>7.4</td>
                <td>镁</td>
                <td> </td>
                <td>7439-95-4</td>
                <td>1）粉末：自热物质和混合物，类别1<br>遇水放出易燃气体的物质和混合物，类别2<br>（2）丸状、旋屑或带状：易燃固体，类别2</td>
              </tr>
              <tr class="text-c">
                <td>7.5</td>
                <td>镁铝粉</td>
                <td>镁铝合金粉</td>
                <td> </td>
                <td>遇水放出易燃气体的物质和混合物，类别2<br>自热物质和混合物，类别1</td>
              </tr>
              <tr class="text-c">
                <td>7.6</td>
                <td>铝粉</td>
                <td> </td>
                <td>7429-90-5</td>
                <td>（1）有涂层：易燃固体，类别1<br>（2）无涂层：遇水放出易燃气体的物质和混合物，类别2</td>
              </tr>
              <tr class="text-c">
                <td  rowspan="2">7.7</td>
                <td>硅铝</td>
                <td  rowspan="2"> </td>
                <td  rowspan="2">57485-31-1</td>
                <td  rowspan="2">遇水放出易燃气体的物质和混合物，类别3</td>
              </tr>
              <tr class="text-c">
                <td>硅铝粉</td>
              </tr>
              <tr class="text-c">
                <td>7.8</td>
                <td>硫磺</td>
                <td>硫</td>
                <td>7704-34-9</td>
                <td>易燃固体，类别2</td>
              </tr>
              <tr class="text-c">
                <td  rowspan="3">7.9</td>
                <td>锌尘</td>
                <td> </td>
                <td  rowspan="3">7440-66-6</td>
                <td>自热物质和混合物，类别1；遇水放出易燃气体的物质和混合物，类别1</td>
              </tr>
              <tr class="text-c">
                <td>锌粉</td>
                <td> </td>
                <td>自热物质和混合物，类别1；遇水放出易燃气体的物质和混合物，类别1</td>
              </tr>
              <tr class="text-c">
                <td>锌灰</td>
                <td> </td>
                <td>遇水放出易燃气体的物质和混合物，类别3</td>
              </tr>
              <tr class="text-c">
                <td  rowspan="2">7.10</td>
                <td>金属锆</td>
                <td> </td>
                <td  rowspan="2">7440-67-7</td>
                <td>易燃固体，类别2</td>
              </tr>
              <tr class="text-c">
                <td>金属锆粉</td>
                <td>锆粉</td>
                <td>自燃固体，类别1，遇水放出易燃气体的物质和混合物，类别1</td>
              </tr>
              <tr class="text-c">
                <td>7.11</td>
                <td>六亚甲基四胺</td>
                <td>六甲撑四胺；乌洛托品</td>
                <td>100-97-0</td>
                <td>易燃固体，类别2</td>
              </tr>
              <tr class="text-c">
                <td>7.12</td>
                <td>1，2-乙二胺</td>
                <td>1，2-二氨基乙烷；乙撑二胺</td>
                <td>107-15-3</td>
                <td>易燃液体，类别3</td>
              </tr>
              <tr class="text-c">
                <td  rowspan="2">7.13</td>
                <td>一甲胺[无水]</td>
                <td>氨基甲烷；甲胺</td>
                <td  rowspan="2">74-89-5</td>
                <td>易燃气体，类别1</td>
              </tr>
              <tr class="text-c">
                <td>一甲胺溶液</td>
                <td>氨基甲烷溶液；甲胺溶液</td>
                <td>易燃液体，类别1</td>
              </tr>
              <tr class="text-c">
                <td>7.14</td>
                <td>硼氢化锂</td>
                <td>氢硼化锂</td>
                <td>16949-15-8</td>
                <td>遇水放出易燃气体的物质和混合物，类别1</td>
              </tr>
              <tr class="text-c">
                <td>7.15</td>
                <td>硼氢化钠</td>
                <td>氢硼化钠</td>
                <td>16940-66-2</td>
                <td>遇水放出易燃气体的物质和混合物，类别1</td>
              </tr>
              <tr class="text-c">
                <td>7.16</td>
                <td>硼氢化钾</td>
                <td>氢硼化钾</td>
                <td>13762-51-1</td>
                <td>遇水放出易燃气体的物质和混合物，类别1</td>
              </tr>
              </tbody>
            </table>
          </div>
        </li>
        <li class="item">
          <h4>8.硝基化合物类<b>+</b></h4>
          <div class="info">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
              <thead>
                <tr class="text-c">
                  <th width="5%">序号</th>
                  <th width="35%">品名</th>
                  <th width="30%">别名</th>
                  <th width="15%">CAS号</th>
                  <th width="15%">主要的燃爆危险性分类</th>
                </tr>
              </thead>
              <tbody>
              <tr class="text-c">
                <td>8.1</td>
                <td>硝基甲烷</td>
                <td> </td>
                <td>75-52-5</td>
                <td>易燃液体，类别3</td>
              </tr>
              <tr class="text-c">
                <td>8.2</td>
                <td>硝基乙烷</td>
                <td> </td>
                <td>79-24-3</td>
                <td>易燃液体，类别3</td>
              </tr>
              <tr class="text-c">
                <td>8.3</td>
                <td>2，4-二硝基甲苯</td>
                <td> </td>
                <td>121-14-2</td>
                <td> </td>
              </tr>
              <tr class="text-c">
                <td>8.4</td>
                <td>2，6-二硝基甲苯</td>
                <td> </td>
                <td>606-20-2</td>
                <td> </td>
              </tr>
              <tr class="text-c">
                <td>8.5</td>
                <td>1，5-二硝基萘</td>
                <td> </td>
                <td>605-71-0</td>
                <td>易燃固体，类别1</td>
              </tr>
              <tr class="text-c">
                <td>8.6</td>
                <td>1，8-二硝基萘</td>
                <td> </td>
                <td>602-38-0</td>
                <td>易燃固体，类别1</td>
              </tr>
              <tr class="text-c">
                <td  rowspan="2">8.7</td>
                <td>二硝基苯酚[干的或含水＜15%]</td>
                <td> </td>
                <td  rowspan="2">25550-58-7</td>
                <td>爆炸物，1.1项</td>
              </tr>
              <tr class="text-c">
                <td>二硝基苯酚溶液</td>
                <td> </td>
                <td> </td>
              </tr>
              <tr class="text-c">
                <td>8.8</td>
                <td>2，4-二硝基苯酚[含水≥15%]</td>
                <td>1-羟基-2，4-二硝基苯</td>
                <td>51-28-5</td>
                <td>易燃固体，类别1</td>
              </tr>
              <tr class="text-c">
                <td>8.9</td>
                <td>2，5-二硝基苯酚[含水≥15%]</td>
                <td> </td>
                <td>329-71-5</td>
                <td>易燃固体，类别1</td>
              </tr>
              <tr class="text-c">
                <td>8.10</td>
                <td>2，6-二硝基苯酚[含水≥15%]</td>
                <td> </td>
                <td>573-56-8</td>
                <td>易燃固体，类别1</td>
              </tr>
              <tr class="text-c">
                <td>8.11</td>
                <td>2，4-二硝基苯酚钠</td>
                <td> </td>
                <td>1011-73-0</td>
                <td>爆炸物，1.3项</td>
              </tr>
              </tbody>
            </table>
          </div>
        </li>
        <li class="item">
          <h4>9.其他<b>+</b></h4>
          <div class="info">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
              <thead>
                <tr class="text-c">
                  <th width="5%">序号</th>
                  <th width="35%">品名</th>
                  <th width="30%">别名</th>
                  <th width="15%">CAS号</th>
                  <th width="15%">主要的燃爆危险性分类</th>
                </tr>
              </thead>
              <tbody>
              <tr class="text-c">
                <td  rowspan="7">9.1</td>
                <td>硝化纤维素[干的或含水（或乙醇）＜25%]</td>
                <td  rowspan="6">硝化棉</td>
                <td  rowspan="7">9004-70-0</td>
                <td>爆炸物，1.1项</td>
              </tr>
              <tr class="text-c">
                <td>硝化纤维素[含氮≤12.6%，含乙醇≥25%]</td>
                <td>易燃固体，类别1</td>
              </tr>
              <tr class="text-c">
                <td>硝化纤维素[含氮≤12.6%]</td>
                <td>易燃固体，类别1</td>
              </tr>
              <tr class="text-c">
                <td>硝化纤维素[含水≥25%]</td>
                <td>易燃固体，类别1</td>
              </tr>
              <tr class="text-c">
                <td>硝化纤维素[含乙醇≥25%]</td>
                <td>爆炸物，1.3项</td>
              </tr>
              <tr class="text-c">
                <td>硝化纤维素[未改型的，或增塑的，含增塑剂＜18%]</td>
                <td>爆炸物，1.1项</td>
              </tr>
              <tr class="text-c">
                <td>硝化纤维素溶液[含氮量≤12.6%，含硝化纤维素≤55%]</td>
                <td>硝化棉溶液</td>
                <td>易燃液体，类别2</td>
              </tr>
              <tr class="text-c">
                <td>9.2</td>
                <td>4，6-二硝基-2-氨基苯酚钠</td>
                <td>苦氨酸钠</td>
                <td>831-52-7</td>
                <td>爆炸物，1.3项</td>
              </tr>
              <tr class="text-c">
                <td>9.3</td>
                <td>高锰酸钾</td>
                <td>过锰酸钾；灰锰氧</td>
                <td>7722-64-7</td>
                <td>氧化性固体，类别2</td>
              </tr>
              <tr class="text-c">
                <td>9.4</td>
                <td>高锰酸钠</td>
                <td>过锰酸钠</td>
                <td>10101-50-5</td>
                <td>氧化性固体，类别2</td>
              </tr>
              <tr class="text-c">
                <td>9.5</td>
                <td>硝酸胍</td>
                <td>硝酸亚氨脲</td>
                <td>506-93-4</td>
                <td>氧化性固体，类别3</td>
              </tr>
              <tr class="text-c">
                <td>9.6</td>
                <td>水合肼</td>
                <td>水合联氨</td>
                <td>10217-52-4</td>
                <td> </td>
              </tr>
              <tr class="text-c">
                <td>9.7</td>
                <td>2，2-双（羟甲基）1，3-丙二醇 </td>
                <td>季戊四醇、四羟甲基甲烷</td>
                <td>115-77-5</td>
                <td> </td>
              </tr>
              </tbody>
            </table>
          </div>
        </li>
      </ul>
      
    </div>
    <div class="mt-30">
    <img alt="" src="${ly }/images/d5.jpg"/>
    </div>
  </div>
</body>
</html>