<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="format-detection" content="telephone=no" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/inc/header.inc"%>
<link type="text/css" href="${ly }/styles/all.css" rel="stylesheet" />
<title>风险分级管控   隐患排查治理智能化平台</title>
<meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
<meta name="description" content="风险分级管控   隐患排查治理智能化平台">
<style type="text/css">
.top_all,.top_main{height:45px;}
.page2{display:none;}
.container{padding:0;}
</style>
<script type="text/javascript">
  $(function(){
  });
  /* 切换页面 */
  function openpage(e){
    $(".page").hide();
    $(""+e+"").show();
  }
  /* 弹窗显示 */
  function openwin(e){
    $(".winbj").height($(document).height());
    $(".winbj").show();
    $(""+e+"").show();
  }
  /* 弹窗关闭 */
  function closewin(){
    $(".winbj").hide();
    $(".winapply").hide();
  }
  /* 单选行业、基础、现场、检查类型 并且赋值*/
  function chosedan(e,f){
    $(e).closest(".winapply").find(".dan_apply").removeClass("dan_applyxz");
    $(e).addClass("dan_applyxz");
    var i=$(e).find("font").html();
    $(""+f+"").html(i);
    closewin();
  }
  
  /* 高危行业、执法人员*/
  function choseduo(e,f){
    if($(e).hasClass("dan_applyxz")){
      $(e).removeClass("dan_applyxz");
    }else{
      $(e).addClass("dan_applyxz");
    }
    var i="";
    $(e).closest(".winapply").find(".dan_applyxz").each(function(){
      i += $(this).find("font").html()+"、";
    });
    $(""+f+"").html(i);
  }
  
  
</script>
</head>
<body>
<div class="container">
  <!-- 页面1：选择行业 -->
  <div class="page page1">
    <div class="top_all">
      <div class="top_main">
        <a href="" class="back_top"><img alt="" src="${ly }/images/weixin/fanhuib.png"/></a>
        <font class="top_title">添加检查表</font>
      </div>
    </div>
    <div class="allh1"></div>
      <div class="memmain">
        <div class="ts-wz">注：企业对其所在行业、涉及的高危作业等选项进行设置，系统会自动为其确定相应的安全检查标准。</div>
        <div class="mk_in2" onclick="openwin('.a1')">
          <font>行业选择</font>
          <img alt="" src="${ly }/images/weixin/hyzxjt.png" class="img_jth2"/>
          <label class="font-a1">请选择</label>
        </div> 
        <div class="mk_in2" onclick="openwin('.a2')">
          <font>基础管理</font>
          <img alt="" src="${ly }/images/weixin/hyzxjt.png" class="img_jth2"/>
          <label class="font-a2">请选择</label>
        </div> 
        <div class="mk_in2" onclick="openwin('.a3')">
          <font>现场管理</font>
          <img alt="" src="${ly }/images/weixin/hyzxjt.png" class="img_jth2"/>
          <label class="font-a3">请选择</label>
        </div> 
        <div class="mk_in2" onclick="openwin('.a4')">
          <font>高危作业选择</font>
          <img alt="" src="${ly }/images/weixin/hyzxjt.png" class="img_jth2"/>
          <label class="font-a4">请选择</label>
        </div> 
      </div>   
     <!-- 底部操作 -->
    <div class="allh1"></div>
    <div class="sjfoot">
      <a href="javascript:void(0)" class="btn_tjyh" onClick="openpage('.page2')" style="width:100%">下一步</a>
    </div>
    <!-- 弹窗选择行业 -->
    <div class="winapply a1">
      <font class="bt_apply">行业选择</font>
      <div class="list_apply">
        <div class="dan_apply" onclick="chosedan(this,'.font-a1')">
          <font>化工企业（危险化学品生产、经营、使用）、加油站</font>
          <img alt="" src="${ly }/images/weixin/cxxz.png" class="wxz"/>
          <img alt="" src="${ly }/images/weixin/cxxzg.png" class="xz"/>
        </div>
        <div class="dan_apply" onclick="chosedan(this,'.font-a1')">
          <font>冶金行业</font>
          <img alt="" src="${ly }/images/weixin/cxxz.png" class="wxz"/>
          <img alt="" src="${ly }/images/weixin/cxxzg.png" class="xz"/>
        </div>
        <div class="dan_apply" onclick="chosedan(this,'.font-a1')">
          <font>有色行业</font>
          <img alt="" src="${ly }/images/weixin/cxxz.png" class="wxz"/>
          <img alt="" src="${ly }/images/weixin/cxxzg.png" class="xz"/>
        </div>
        <div class="dan_apply" onclick="chosedan(this,'.font-a1')">
          <font>建材行业</font>
          <img alt="" src="${ly }/images/weixin/cxxz.png" class="wxz"/>
          <img alt="" src="${ly }/images/weixin/cxxzg.png" class="xz"/>
        </div>
        <div class="dan_apply" onclick="chosedan(this,'.font-a1')">
          <font>机械行业</font>
          <img alt="" src="${ly }/images/weixin/cxxz.png" class="wxz"/>
          <img alt="" src="${ly }/images/weixin/cxxzg.png" class="xz"/>
        </div>
        <div class="dan_apply" onclick="chosedan(this,'.font-a1')">
          <font>轻工行业</font>
          <img alt="" src="${ly }/images/weixin/cxxz.png" class="wxz"/>
          <img alt="" src="${ly }/images/weixin/cxxzg.png" class="xz"/>
        </div>
        <div class="dan_apply" onclick="chosedan(this,'.font-a1')">
          <font>纺织行业</font>
          <img alt="" src="${ly }/images/weixin/cxxz.png" class="wxz"/>
          <img alt="" src="${ly }/images/weixin/cxxzg.png" class="xz"/>
        </div>
        <div class="dan_apply" onclick="chosedan(this,'.font-a1')">
          <font>烟草行业</font>
          <img alt="" src="${ly }/images/weixin/cxxz.png" class="wxz"/>
          <img alt="" src="${ly }/images/weixin/cxxzg.png" class="xz"/>
        </div>
        <div class="dan_apply" onclick="chosedan(this,'.font-a1')">
          <font>商贸行业</font>
          <img alt="" src="${ly }/images/weixin/cxxz.png" class="wxz"/>
          <img alt="" src="${ly }/images/weixin/cxxzg.png" class="xz"/>
        </div>
        <div class="dan_apply" onclick="chosedan(this,'.font-a1')">
          <font>非煤矿山行业</font>
          <img alt="" src="${ly }/images/weixin/cxxz.png" class="wxz"/>
          <img alt="" src="${ly }/images/weixin/cxxzg.png" class="xz"/>
        </div>
        <div class="dan_apply" onclick="chosedan(this,'.font-a1')">
          <font>其他行业</font>
          <img alt="" src="${ly }/images/weixin/cxxz.png" class="wxz"/>
          <img alt="" src="${ly }/images/weixin/cxxzg.png" class="xz"/>
        </div>
      </div>
      <input type="button" class="btn_apply" value="关闭" onclick="closewin()"/>
    </div>
    
    <!-- 弹窗基础管理-->
    <div class="winapply a2">
      <font class="bt_apply">基础管理</font>
      <div class="list_apply">
        <!-- 循环基础管理 -->
        <div class="dan_apply" onclick="chosedan(this,'.font-a2')">
          <font>基础管理</font>
          <img alt="" src="${ly }/images/weixin/cxxz.png" class="wxz"/>
          <img alt="" src="${ly }/images/weixin/cxxzg.png" class="xz"/>
        </div>
        <!-- 循环结束 -->
      </div>
      <input type="button" class="btn_apply" value="关闭" onclick="closewin()"/>
    </div>
    
    <!-- 弹窗现场管理-->
    <div class="winapply a3">
      <font class="bt_apply">现场管理</font>
      <div class="list_apply">
        <!-- 循环现场管理 -->
        <div class="dan_apply" onclick="chosedan(this,'.font-a3')">
          <font>现场管理</font>
          <img alt="" src="${ly }/images/weixin/cxxz.png" class="wxz"/>
          <img alt="" src="${ly }/images/weixin/cxxzg.png" class="xz"/>
        </div>
        <!-- 循环结束 -->
      </div>
      <input type="button" class="btn_apply" value="关闭" onclick="closewin()"/>
    </div>
    
    <!-- 弹窗高危作业选择(多选)-->
    <div class="winapply a4">
      <font class="bt_apply">高危作业选择</font>
      <div class="list_apply">
        <!-- 循环高危作业选择 -->
        <div class="dan_apply" onclick="choseduo(this,'.font-a4')">
          <font>高危作业选择</font>
          <img alt="" src="${ly }/images/weixin/cxxz.png" class="wxz"/>
          <img alt="" src="${ly }/images/weixin/cxxzg.png" class="xz"/>
        </div>
        <!-- 循环结束 -->
      </div>
      <input type="button" class="btn_apply" value="确定" onclick="closewin()"/>
    </div>
    <!-- 弹窗结束 -->
  </div>
  <!-- 页面1结束 -->
  <!-- 页面2：添加检查项 -->
  <div class="page page2">
    <div class="top_all">
      <div class="top_main">
        <div class="back_top" onclick="openpage('.page1')"><img alt="" src="${ly }/images/weixin/fanhuib.png"/></div>
        <font class="top_title">添加检查表</font>
      </div>
    </div>
    <div class="allh1"></div>
    <div class="memmain">
      <div class="ts-wz">1、选择具体检查类型；2、填写检查表名称、受检部门；3、添加部位/装置、检查项；4、生成检查表</div>
      <div class="mk_in2" onclick="openwin('.a5')">
        <font>检查类型</font>
        <img alt="" src="${ly }/images/weixin/hyzxjt.png" class="img_jth2"/>
        <label class="font-a5">请选择</label>
      </div> 
      <div class="mk_in2" onclick="openwin('.a6')">
        <font>检查人</font>
        <img alt="" src="${ly }/images/weixin/hyzxjt.png" class="img_jth2"/>
        <label class="font-a6">请选择</label>
      </div>
      <div class="mk_in2">
        <input type="text" class="txt_real" id="" value="" placeholder="请输入检查表名称"/>
      </div>
      <div class="mk_in2">
        <input type="text" class="txt_real" id="" value="" placeholder="请输入受检单位"/>
      </div>
      <div class="mk_in2">
        <input type="text" class="txt_real" id="" value="" placeholder="请输入受检单位负责人"/>
      </div>
      <div class="mk_in2">
        <input type="text" class="txt_real" id="" value="" placeholder="请输入检查部门/单位"/>
      </div>
    </div>
    <div class="lj-memin">
      <font class="f-mbt">添加检查类别</font>
      <img alt="" src="${ly }/images/weixin/hyzxjt.png" class="img-mjt"/>
    </div>
    <div class="allh1"></div>
    <div class="sjfoot">
      <a href="javascript:void(0)" class="btn_tjyh" onClick="" style="width:100%">保存</a>
    </div>
    
    <!-- 弹窗选择检查类型-->
    <div class="winapply a5">
      <font class="bt_apply">检查类型</font>
      <div class="list_apply">
        <!-- 循环检查类型 -->
        <div class="dan_apply" onclick="chosedan(this,'.font-a5')">
          <font>检查类型</font>
          <img alt="" src="${ly }/images/weixin/cxxz.png" class="wxz"/>
          <img alt="" src="${ly }/images/weixin/cxxzg.png" class="xz"/>
        </div>
        <!-- 循环结束 -->
      </div>
      <input type="button" class="btn_apply" value="关闭" onclick="closewin()"/>
    </div>
    
    <!-- 弹窗选择检查人(多选)-->
    <div class="winapply a6">
      <font class="bt_apply">选择检查人</font>
      <div class="list_apply">
        <!-- 循环执法人员 -->
        <div class="dan_apply" onclick="choseduo(this,'.font-a6')">
          <font>执法人员</font>
          <img alt="" src="${ly }/images/weixin/cxxz.png" class="wxz"/>
          <img alt="" src="${ly }/images/weixin/cxxzg.png" class="xz"/>
        </div>
        <div class="dan_apply" onclick="choseduo(this,'.font-a6')">
          <font>2执法人员</font>
          <img alt="" src="${ly }/images/weixin/cxxz.png" class="wxz"/>
          <img alt="" src="${ly }/images/weixin/cxxzg.png" class="xz"/>
        </div>
        <!-- 循环结束 -->
      </div>
      <input type="button" class="btn_apply" value="确定" onclick="closewin()"/>
    </div>
    <!-- 弹窗结束 -->
    
  </div>
</div>
<div class="winbj" onclick="closewin()"></div>
</body>
</html>
