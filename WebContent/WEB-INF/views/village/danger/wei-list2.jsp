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
</head>
<body>
  <div class="page-container">
    <div class="mt-20" style="position: relative;">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="10%">序号</th>
            <th width="30%">类别</th>
            <th width="40%">危险化学品名称和说明</th>
            <th width="20%">临界量（T）</th>
          </tr>
        </thead>
        <tbody>
        <tr class="text-c">
          <td>1</td>
          <td  rowspan="8">爆炸品</td>
          <td>叠氮化钡</td>
          <td>0.5</td>
        </tr>
        <tr class="text-c">
          <td>2</td>
          <td>叠氮化铅</td>
          <td>0.5</td>
        </tr>
        <tr class="text-c">
          <td>3</td>
          <td>雷酸汞</td>
          <td>0.5</td>
        </tr>
        <tr class="text-c">
          <td>4</td>
          <td>三硝基苯甲醚</td>
          <td>5</td>
        </tr>
        <tr class="text-c">
          <td>5</td>
          <td>三硝基甲苯</td>
          <td>5</td>
        </tr>
        <tr class="text-c">
          <td>6</td>
          <td>硝化甘油</td>
          <td>1</td>
        </tr>
        <tr class="text-c">
          <td>7</td>
          <td>硝化纤维素</td>
          <td>10</td>
        </tr>
        <tr class="text-c">
          <td>8</td>
          <td>硝酸铵（含可燃物＞０．２％）</td>
          <td>5</td>
        </tr>
        <tr class="text-c">
          <td>9</td>
          <td  rowspan="9">易燃气体</td>
          <td>丁二烯</td>
          <td>5</td>
        </tr>
        <tr class="text-c">
          <td>10</td>
          <td>二甲醚</td>
          <td>50</td>
        </tr>
        <tr class="text-c">
          <td>11</td>
          <td>甲烷，天然气</td>
          <td>50</td>
        </tr>
        <tr class="text-c">
          <td>12</td>
          <td>氯乙烯</td>
          <td>50</td>
        </tr>
        <tr class="text-c">
          <td>13</td>
          <td>氢</td>
          <td>5</td>
        </tr>
        <tr class="text-c">
          <td>14</td>
          <td>液化石油气（含丙烷、丁烷及其混合物）</td>
          <td>50</td>
        </tr>
        <tr class="text-c">
          <td>15</td>
          <td>一甲胺</td>
          <td>5</td>
        </tr>
        <tr class="text-c">
          <td>16</td>
          <td>乙炔</td>
          <td>1</td>
        </tr>
        <tr class="text-c">
          <td>17</td>
          <td>乙烯</td>
          <td>50</td>
        </tr>
        <tr class="text-c">
          <td>18</td>
          <td  rowspan="17">毒性气体</td>
          <td>氨</td>
          <td>10</td>
        </tr>
        <tr class="text-c">
          <td>19</td>
          <td>二氟化氧</td>
          <td>1</td>
        </tr>
        <tr class="text-c">
          <td>20</td>
          <td>二氧化氮</td>
          <td>1</td>
        </tr>
        <tr class="text-c">
          <td>21</td>
          <td>二氧化硫</td>
          <td>20</td>
        </tr>
        <tr class="text-c">
          <td>22</td>
          <td>氟</td>
          <td>1</td>
        </tr>
        <tr class="text-c">
          <td>23</td>
          <td>光气</td>
          <td>0.3</td>
        </tr>
        <tr class="text-c">
          <td>24</td>
          <td>环氧乙烷</td>
          <td>10</td>
        </tr>
        <tr class="text-c">
          <td>25</td>
          <td>甲醛（含量＞９０％）</td>
          <td>5</td>
        </tr>
        <tr class="text-c">
          <td>26</td>
          <td>磷化氢</td>
          <td>1</td>
        </tr>
        <tr class="text-c">
          <td>27</td>
          <td>硫化氢</td>
          <td>5</td>
        </tr>
        <tr class="text-c">
          <td>28</td>
          <td>氯化氢</td>
          <td>20</td>
        </tr>
        <tr class="text-c">
          <td>29</td>
          <td>氯</td>
          <td>5</td>
        </tr>
        <tr class="text-c">
          <td>30</td>
          <td>煤气（ＣＯ，ＣＯ和Ｈ２、ＣＨ４的混合物等）</td>
          <td>20</td>
        </tr>
        <tr class="text-c">
          <td>31</td>
          <td>砷化三氢（胂）</td>
          <td>1</td>
        </tr>
        <tr class="text-c">
          <td>32</td>
          <td>锑化氢</td>
          <td>1</td>
        </tr>
        <tr class="text-c">
          <td>33</td>
          <td>硒化氢</td>
          <td>1</td>
        </tr>
        <tr class="text-c">
          <td>34</td>
          <td>溴甲烷</td>
          <td>10</td>
        </tr>
        <tr class="text-c">
          <td>35</td>
          <td  rowspan="14">易燃液体</td>
          <td>苯</td>
          <td>50</td>
        </tr>
        <tr class="text-c">
          <td>36</td>
          <td>苯乙烯</td>
          <td>500</td>
        </tr>
        <tr class="text-c">
          <td>37</td>
          <td>丙酮</td>
          <td>500</td>
        </tr>
        <tr class="text-c">
          <td>38</td>
          <td>丙烯腈</td>
          <td>50</td>
        </tr>
        <tr class="text-c">
          <td>39</td>
          <td>二硫化碳</td>
          <td>50</td>
        </tr>
        <tr class="text-c">
          <td>40</td>
          <td>环己烷</td>
          <td>500</td>
        </tr>
        <tr class="text-c">
          <td>41</td>
          <td>环氧丙烷</td>
          <td>10</td>
        </tr>
        <tr class="text-c">
          <td>42</td>
          <td>甲苯</td>
          <td>500</td>
        </tr>
        <tr class="text-c">
          <td>43</td>
          <td>甲醇</td>
          <td>500</td>
        </tr>
        <tr class="text-c">
          <td>44</td>
          <td>汽油</td>
          <td>200</td>
        </tr>
        <tr class="text-c">
          <td>45</td>
          <td>乙醇</td>
          <td>500</td>
        </tr>
        <tr class="text-c">
          <td>46</td>
          <td>乙醚</td>
          <td>10</td>
        </tr>
        <tr class="text-c">
          <td>47</td>
          <td>乙酸乙酯</td>
          <td>500</td>
        </tr>
        <tr class="text-c">
          <td>48</td>
          <td>正己烷</td>
          <td>500</td>
        </tr>
        <tr class="text-c">
          <td>49</td>
          <td  rowspan="3">易于自燃的物质</td>
          <td>黄磷</td>
          <td>50</td>
        </tr>
        <tr class="text-c">
          <td>50</td>
          <td>烷基铝</td>
          <td>1</td>
        </tr>
        <tr class="text-c">
          <td>51</td>
          <td>戊硼烷</td>
          <td>1</td>
        </tr>
        <tr class="text-c">
          <td>52</td>
          <td  rowspan="3">遇水放出易燃气体的物质</td>
          <td>电石</td>
          <td>100</td>
        </tr>
        <tr class="text-c">
          <td>53</td>
          <td>钾</td>
          <td>1</td>
        </tr>
        <tr class="text-c">
          <td>54</td>
          <td>钠</td>
          <td>10</td>
        </tr>
        <tr class="text-c">
          <td>55</td>
          <td  rowspan="9">氧化性物质</td>
          <td>发烟硫酸</td>
          <td>100</td>
        </tr>
        <tr class="text-c">
          <td>56</td>
          <td>过氧化钾</td>
          <td>20</td>
        </tr>
        <tr class="text-c">
          <td>57</td>
          <td>过氧化钠</td>
          <td>20</td>
        </tr>
        <tr class="text-c">
          <td>58</td>
          <td>氯酸钾</td>
          <td>100</td>
        </tr>
        <tr class="text-c">
          <td>59</td>
          <td>氯酸钠</td>
          <td>100</td>
        </tr>
        <tr class="text-c">
          <td>60</td>
          <td>硝酸（发红烟的）</td>
          <td>20</td>
        </tr>
        <tr class="text-c">
          <td>61</td>
          <td>硝酸（发红烟的除外，含硝酸＞７０％）</td>
          <td>100</td>
        </tr>
        <tr class="text-c">
          <td>62</td>
          <td>硝酸铵（含可燃物≤０．２％）</td>
          <td>300</td>
        </tr>
        <tr class="text-c">
          <td>63</td>
          <td>硝酸铵基化肥</td>
          <td>1000</td>
        </tr>
        <tr class="text-c">
          <td>64</td>
          <td>有机过氧化物</td>
          <td>过氧乙酸（含量≥６０％）</td>
          <td>10</td>
        </tr>
        <tr class="text-c">
          <td>65</td>
          <td> </td>
          <td>过氧化甲乙酮（含量≥６０％）</td>
          <td>10</td>
        </tr>
        <tr class="text-c">
          <td>66</td>
          <td  rowspan="13">毒性物质</td>
          <td>丙酮合氰化氢</td>
          <td>20</td>
        </tr>
        <tr class="text-c">
          <td>67</td>
          <td>丙烯醛</td>
          <td>20</td>
        </tr>
        <tr class="text-c">
          <td>68</td>
          <td>氟化氢</td>
          <td>1</td>
        </tr>
        <tr class="text-c">
          <td>69</td>
          <td>环氧氯丙烷（３氯１，２环氧丙烷）</td>
          <td>20</td>
        </tr>
        <tr class="text-c">
          <td>70</td>
          <td>环氧溴丙烷（表溴醇）</td>
          <td>20</td>
        </tr>
        <tr class="text-c">
          <td>71</td>
          <td>甲苯二异氰酸酯</td>
          <td>100</td>
        </tr>
        <tr class="text-c">
          <td>72</td>
          <td>氯化硫</td>
          <td>1</td>
        </tr>
        <tr class="text-c">
          <td>73</td>
          <td>氰化氢</td>
          <td>1</td>
        </tr>
        <tr class="text-c">
          <td>74</td>
          <td>三氧化硫</td>
          <td>75</td>
        </tr>
        <tr class="text-c">
          <td>75</td>
          <td>烯丙胺</td>
          <td>20</td>
        </tr>
        <tr class="text-c">
          <td>76</td>
          <td>溴</td>
          <td>20</td>
        </tr>
        <tr class="text-c">
          <td>77</td>
          <td>乙撑亚胺</td>
          <td>20</td>
        </tr>
        <tr class="text-c">
          <td>78</td>
          <td>异氰酸甲酯</td>
          <td>0.75</td>
        </tr>
        </tbody>
      </table>
      <br />
      <br />
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
          <tr class="text-c">
            <th width="35%">类别</th>
            <th width="45%">危险性分类及说明</th>
            <th width="20%">临界量(T)</th>
          </tr>
        </thead>
        <tbody>
        <tr class="text-c">
          <td  rowspan="3">爆炸品</td>
          <td>１．１Ａ项爆炸品</td>
          <td>1</td>
        </tr>
        <tr class="text-c">
          <td>除１．１Ａ项外的其他１．１项爆炸品</td>
          <td>10</td>
        </tr>
        <tr class="text-c">
          <td>除１．１项外的其他爆炸品</td>
          <td>50</td>
        </tr>
        <tr class="text-c">
          <td  rowspan="4">气体</td>
          <td>易燃气体：危险性属于２．１项的气体</td>
          <td>10</td>
        </tr>
        <tr class="text-c">
          <td>氧化性气体：危险性属于２．２项非易燃无毒气体且次要危险性为５类的气体</td>
          <td>200</td>
        </tr>
        <tr class="text-c">
          <td>剧毒气体：危险性属于２．３项且急性毒性为类别１的毒性气体</td>
          <td>5</td>
        </tr>
        <tr class="text-c">
          <td>有毒气体：危险性属于２．３项的其他毒性气体</td>
          <td>50</td>
        </tr>
        <tr class="text-c">
          <td  rowspan="3">易燃液体</td>
          <td>极易燃液体：沸点≤３５℃且闪点＜０℃的液体；或保存温度一直在其沸点以上的易燃液体</td>
          <td>10</td>
        </tr>
        <tr class="text-c">
          <td>高度易燃液体：闪点＜２３℃的液体（不包括极易燃液体）；液态退敏爆炸品</td>
          <td>1000</td>
        </tr>
        <tr class="text-c">
          <td>易燃液体：２３℃≤闪点＜６１℃的液体</td>
          <td>5000</td>
        </tr>
        <tr class="text-c">
          <td>易燃固体</td>
          <td>危险性属于４．１项且包装为Ⅰ类的物质</td>
          <td>200</td>
        </tr>
        <tr class="text-c">
          <td>易于自燃的物质</td>
          <td>危险性属于４．２项且包装为Ⅰ或Ⅱ类的物质</td>
          <td>200</td>
        </tr>
        <tr class="text-c">
          <td>遇水放出易燃气体的物质</td>
          <td>危险性属于４．３项且包装为Ⅰ或Ⅱ的物质</td>
          <td>200</td>
        </tr>
        <tr class="text-c">
          <td  rowspan="2">氧化性物质</td>
          <td>危险性属于５．１项且包装为Ⅰ类的物质</td>
          <td>50</td>
        </tr>
        <tr class="text-c">
          <td>危险性属于５．１项且包装为Ⅱ或Ⅲ类的物质</td>
          <td>200</td>
        </tr>
        <tr class="text-c">
          <td>有机过氧化物</td>
          <td>危险性属于５．２项的物质</td>
          <td>50</td>
        </tr>
        <tr class="text-c">
          <td  rowspan="2">毒性物质</td>
          <td>危险性属于６．１项且急性毒性为类别１的物质</td>
          <td>50</td>
        </tr>
        <tr class="text-c">
          <td>危险性属于６．１项且急性毒性为类别２的物质</td>
          <td>500</td>
        </tr>
        </tbody>
      </table>
    </div>
    <div class="mt-40">
    <div class="Huialert Huialert-success"><i class="icon-remove"></i>注：以上危险化学品危险性类别及包装类别依据ＧＢ１２２６８确定，急性毒性类别依据ＧＢ２０５９２确定。</div>
    </div>
  </div>
</body>
</html>