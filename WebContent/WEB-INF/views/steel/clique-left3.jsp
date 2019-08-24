  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!DOCTYPE HTML>
    <html>
    <%@ include file="/WEB-INF/views/taglibs.jsp"%>
    <style type="text/css">
    #winpop {
    width: 275px;
    height: 120px;
    position: absolute;
    right: 0;
    bottom: 0;
    border: 1px solid #666;
    margin: 0;
    padding: 1px;
    overflow: hidden;
    z-index: 3000;
    background: #fff;
    }
    #winpop .title{
    width:100%;
    height:22px;
    line-height:20px;
    background:#FFCC00;
    font-weight:bold;
    text-align:center;
    font-size:12px;
    }
    #winpop .con{
    width:100%;
    height:90px;
    line-height:80px;
    font-weight:bold;
    font-size:12px;
    color:#FF0000;
    text-decoration:underline;
    text-align:center
    }
    #silu{
    font-size:12px;
    color:#666;
    position:absolute;
    right:0;
    text-decoration:underline;
    line-height:22px;
    }
    .close{
    position:absolute;
    right:4px;
    top:-1px;
    color:#FFF;
    cursor:pointer
    }
    </style>
    <script type="text/javascript">
    $(document).ready(function() {
    $("body").on("click",".Hui-aside .menu_dropdown li a", function() {
    $(".Hui-aside .menu_dropdown li a").removeClass("l_active");
    $(this).addClass("l_active");
    });
    $(".div_otop2").on("click",function(){
    if($(this).closest(".div_omenu2").hasClass("div_omenu2xz")){
    $(this).closest(".div_omenu2").removeClass("div_omenu2xz");
    $(this).parent().find(".div_omenu").removeClass("div_omenuxz");
    }else{
    $(".div_omenu2").removeClass("div_omenuxz");
    $(this).closest(".div_omenu2").addClass("div_omenu2xz");
    }
    });
    $(".div_otop").on("click",function(){
    if($(this).closest(".div_omenu").hasClass("div_omenuxz")){
    $(this).closest(".div_omenu").removeClass("div_omenuxz");
    }else{
    $(".div_omenu").removeClass("div_omenuxz");
    $(this).closest(".div_omenu").addClass("div_omenuxz");
    }
    });
    $(".Hui-aside .menu_dropdown dt").on("click",function(){
    if(!$(this).hasClass("selected")){
    $(this).parent().find(".div_omenu").removeClass("div_omenuxz");
    $(this).parent().find(".div_omenu2").removeClass("div_omenu2xz");
    }
    });


    });
    </script>
    <body>
    <!-- 左侧导航 -->
    <aside class="Hui-aside">
    <input runat="server" id="divScrollValue" type="hidden" value="" />
    <div class="menu_dropdown bk_2">
      <!---------------------企业信息数据---------------------------------->
      <dl>
         <dt>
             <i class="Hui-iconfont">&#xe643;</i>企业信息数据
             <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
         </dt>
          <dd>
              <ul>
                  <li><a data-href="${ly }/steel/clique/basic-information" data-title="基本信息" href="javascript:void(0)">基本信息</a></li>
                  <li><a data-href="${ly }/steel/threeLeft?leftBasic=1" data-title="组织架构" href="javascript:void(0)">组织架构</a></li>
                  <li><a data-href="${ly }/steel/product/material-list" data-title="原辅材料" href="javascript:void(0)">原辅材料</a></li>
                  <li><a data-href="${ly }/steel/product/product-list" data-title="主要产品" href="javascript:void(0)">主要产品</a></li>
                  <li><a data-href="${ly }/steel/process/process-list" data-title="生产工艺" href="javascript:void(0)">生产工艺</a></li>
              </ul>
          </dd>
      </dl>
        <!----------------------风险分级管控----------------------->
      <dl>
          <dt>
              <i class="Hui-iconfont">&#xe63c;</i>风险分级管控
              <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
         </dt>
          <dd>
              <ul>
                  <li><a data-href="${ly }/html/editor/plan-list.html" data-title="管理手册" href="javascript:void(0)">管理手册</a></li>
                  <li><a data-href="${ly }/steel/risk-list" data-title="风险辨识" href="javascript:void(0)">风险辨识</a></li>
                  <li><a data-href="${ly }/company/threeLeft?leftBasic=2" data-title="风险评估" href="javascript:void(0)">风险评估</a></li>
                  <li><a data-href="${ly }/company/safety-system/control-list" data-title="分级管控" href="javascript:void(0)">分级管控</a></li>
                  <li><a data-href="${ly }/company/threeLeft?leftBasic=3" data-title="公告警示" href="javascript:void(0)">公告警示</a></li>
              </ul>
          </dd>
        </dl>
        <!-------------------------------------------隐患排查治理-------------------------------------------------->
        <dl>
            <dt>
                <i class="Hui-iconfont">&#xe687;</i>隐患排查治理
                <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
           </dt>
           <dd>
                <ul>
                    <li><a data-href="${ly }/company/model-list-main?flag=1" data-title="检查设置实施" href="javascript:void(0)">检查设置实施</a></li>
                    <li><a data-href="${ly }/village/check-list?flag=1&status=2" data-title="隐患排查记录" href="javascript:void(0)">隐患排查记录</a></li>
                    <li><a data-href="${ly }/village/hidden-danger-list?flag=1&status=1" data-title="隐患治理记录" href="javascript:void(0)">隐患治理记录</a></li>
                    <li><a data-href="${ly }/company/jx-analysis" data-title="排查数据分析" href="javascript:void(0)">排查数据分析</a></li>
                    <li><a data-href="${ly }/company/yh-analysis" data-title="隐患数据分析" href="javascript:void(0)">隐患数据分析</a></li>
                    <li><a data-href="${ly }/company/zl-analysis" data-title="治理数据分析" href="javascript:void(0)">治理数据分析</a></li>
               </ul>
         </dd>
      </dl>
        <!-----------------------------------------风险研判公告------------------------------------------------------------>
        <dl>
        <dt>
            <i class="Hui-iconfont">&#xe687;</i>风险研判公告
            <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
            <ul>
			    <li><a data-href="${ly }/company/threeLeft?leftBasic=6" data-title="流程管理" href="javascript:void(0)">流程管理</a></li>
				<li><a data-href="${ly }/company/threeLeft?leftBasic=7" data-title="运行管理" href="javascript:void(0)">运行管理</a></li>
				<li><a data-href="${ly }/company/study/targetassessment" data-title="履职考核" href="javascript:void(0)">履职考核</a></li>
				<li><a data-href="${ly }/company/study/promise" data-title="承诺公示" href="javascript:void(0)">承诺公示</a></li>
            </ul>
        </dd>
        </dl>
		 <!---------------------------------------------------------------------安全培训中心------------------------------------------------->
        <dl>
            <dt>
                <a style="color:#fff" data-href="/company/tables/dajiangtang" href="javascript:void(0)" data-title="安全培训中心"><i class="Hui-iconfont">&#xe687;</i>安全培训中心</a>
            </dt>
        </dl>
		  <!---------------------------------------------------------------------应急管理中心---------------------------------------------------------->
        <dl>
            <dt>
                <a style="color:#fff;"  data-href="${ly }/company/tables/yjmanage_center"  data-title="应急管理中心" href="javascript:void(0)"><i class="Hui-iconfont">&#xe6c1;</i>应急管理中心</a>
            </dt>
        </dl>
		 <!------------------------------------------------------------------------------------------------------------------------------------------->
        <dl>
            <dt>
                <a style="color:#fff;"  data-href="${ly }/api/safety_Standard/findAll?parendId=0&flag=1" data-title="运行操作系统"
                   href="javascript:void(0)"><i class="Hui-iconfont">&#xe639;</i>安全生产标准化</a>
            </dt>
        </dl>
		    <dl>
            <dt>
             <i class="Hui-iconfont">&#xe687;</i>重大危险源长制
             <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
           </dt>
       <dd>
       <ul>
           <li><a data-href="${ly }/company/threeLeft?leftBasic=13" data-title="管控机制" href="javascript:void(0)">管控机制</a></li>
           <li><a data-href="${ly }/company/threeLeft?leftBasic=14" data-title="管控措施" href="javascript:void(0)">管控措施</a></li>
           <li><a data-href="${ly }/company/source/model-list-cx" data-title="隐患排查" href="javascript:void(0)">排查治理</a></li>
           <li><a data-href="${ly }/company/threeLeft?leftBasic=15" data-title="特殊作业" href="javascript:void(0)">特殊作业</a></li>
           <li><a data-href="${ly }/company/threeLeft?leftBasic=16" data-title="应急救援" href="javascript:void(0)">应急救援</a></li>
     </dd>
    </dl>
        <!---------------------------------------职业卫生健康--------------------------------------------------------------------------->
        <dl>
            <dt>
                <i class="Hui-iconfont">&#xe687;</i>职业卫生健康
                <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
            </dt>
            <dd>
                <ul>
                    <li><a data-href="${ly }/company/labor/labor-list" data-title="劳保用品配备" href="javascript:void(0)">劳保用品配备</a></li>
                    <li><a data-href="${ly }/company/threeLeft?leftBasic=10" data-title="管理档案台账" href="javascript:void(0)">管理档案台账</a></li>
                </ul>
            </dd>
        </dl>
        <!---------------------------------------行政执法检查--------------------------------------------------------------------------->
        <dl>
            <dt>
                <i class="Hui-iconfont">&#xe687;</i>行政执法检查
                <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
            </dt>
            <dd>
                <ul>
                    <li><a data-href="${ly }/village/opinion-show?flag2=2" data-title="执法档案" href="javascript:void(0)">执法档案</a></li>
                </ul>
            </dd>
        </dl>
        <!------------------------------------------信息推送互动------------------------------------------------------------------------------------>
        <dl>
            <dt>
                <i class="Hui-iconfont">&#xe687;</i>信息推送互动
                <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
            </dt>
            <dd>
                <ul>
				    <li><a data-href="${ly }/village/groupIndex" data-title="群组设置" href="javascript:void(0)">群组设置</a></li>
					<li><a data-href="${ly }/village/addMessages" data-title="推送编辑" href="javascript:void(0)">推送编辑</a></li>
					<li><a data-href="${ly }/village/sendMessageList" data-title="发送设置" href="javascript:void(0)">发送列表</a></li>
                    <li><a data-href="${ly }/village/reviceMessageList" data-title="接收列表" href="javascript:void(0)">接收列表</a></li>
                </ul>
            </dd>
        </dl>
        <!------------------------------------------系统设置管理------------------------------------------------------------------------------------->
        <dl>
            <dt>
                <i class="Hui-iconfont">&#xe687;</i>系统设置管理
                <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
            </dt>
            <dd>
                <ul>
                    <li><a data-href="${ly }/company/threeLeft?leftBasic=11" data-title="在线检测" href="javascript:void(0)">在线检测</a></li>
                    <li><a data-href="${ly }/village/to_monitor-list?flag=1" data-title="实时监控" href="javascript:void(0)">实时监控</a></li>
                    <li><a data-href="${ly }/company/system/change-psw" data-title="修改密码" href="javascript:void(0)">修改密码</a></li>
                    <li><a data-href="${ly }/company/system/change-userName" data-title="修改登录" href="javascript:void(0)">修改登录</a></li>
                </ul>
            </dd>
        </dl>
        <dl>
      <dt>
      <i class="Hui-iconfont">&#xe653;</i>领导关注信息
      <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
       </dt>
            <dd>
           <ul>
      <li><a data-href="${ly }/global/notice-list" data-title="一周监管动态" href="javascript:void(0)">一周监管动态</a></li>
      <li><a data-href="${ly }/village/danger-collect-guanzhu?isZhongDa=1" data-title="隐患排查治理数据" href="javascript:void(0)">排查治理数据</a></li>
      <li><a data-href="${ly }/village/danger-collect-guanzhu?isZhongDa=2" data-title="重大事故隐患数据" href="javascript:void(0)">重大隐患数据</a></li>
      <li><a data-href="${ly }/village/danger-collect-guanzhu?isZhongDa=3" data-title="行政执法数据" href="javascript:void(0)">行政执法数据</a></li>
      </ul>
      </dd>
      </dl>
       
    
    </div>
    </aside>
    <div id="winpop" style="display:none;" >
    <div class="title">
    您有新的信息！<span class="close" title="关闭" id="toclose">X</span>
    </div>
    <div class="con" id="shwomsgTips" style="cursor:pointer;height:60px;line-height: 60px;"></div>
    <div class="con" id="shwomsg" style="cursor:pointer;height:30px;line-height:30px;">1条未读信息</div>
    </div>
    <script type="text/javascript" src="${ly }/js/plugins/websocket/swfobject.js"></script>
    <script type="text/javascript" src="${ly }/js/plugins/websocket/web_socket.js"></script>
    <script type="text/javascript">

    getUnreadMsg();
    var intervalIndex2 = setInterval(function() {
    getUnreadMsg();
    }, 1000 * 60*5);
    function getUnreadMsg() {
    $.ajax({
    url : "<c:url value='/village/getUnReadMessage'/>",
    type : 'post',
    success : function(data) {
    if (undefined != data.message) {
    tips_pop(data.message.id, data.message.title, data.message.createName, data.message.createTime)
    }
    }
    });
    }
    var websocket = null;
    var userno="${loginUserId}";
    //判断当前浏览器是否支持WebSocket
    WEB_SOCKET_SWF_LOCATION = "${ly }/js/plugins/websocket/WebSocketMain.swf";
    if(window.WebSocket){
    websocket = new WebSocket("ws://"+location.hostname+":"+location.port+"/village/websocket/"+userno);
    }
    else {
    //alert("当前版本不支持");
    }


    //连接发生错误的回调方法
    websocket.onerror = function () {
    //alert("error");
    //setMessageInnerHTML("WebSocket连接发生错误");
    };


    //连接成功建立的回调方法
    websocket.onopen = function () {
    clearInterval(intervalIndex2);
    }


    //接收到消息的回调方法
    websocket.onmessage = function (event) {
    //tips_pop();
    //setMessageInnerHTML(event.data);
    //alert(event.data);
    //tips_pop(event.data);
    if("" != $.trim(event.data)){
    $.ajax({
    url : "<c:url value='/village/getMessageById'/>",
    type : 'post',
    data : {
    messageId : event.data,
    from : 1
    },
    success : function(data){
    var title = "";
    if(undefined != data.title ){
    title = data.title;
    }
    tips_pop(event.data,title,data.createName,data.createTime);
    }
    });
    }else{

    }

    }


    //连接关闭的回调方法
    websocket.onclose = function () {
    //setMessageInnerHTML("WebSocket连接关闭");
    //alert("close");
    }


    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
    //closeWebSocket();
    }
    //关闭WebSocket连接
    function closeWebSocket() {
    websocket.close();
    }

    </script>

    <script type="text/javascript">
    $.ajax({
    url : "<c:url value='/village/getUnReadMessage'/>",
    type : 'post',
    success : function(data){
    if(undefined != data.message){
    tips_pop(data.message.id,data.message.title,data.message.createName,data.message.createTime)
    }
    }
    });
    </script>
    <script type="text/javascript">
    function tips_pop(messageId,title,createName,createTime){
    $("#winpop").show();
    $("#shwomsg").attr("core-msgid",messageId);
    if("" != title){
    $("#shwomsg").html(title);
    }
    $("#shwomsgTips").html(createName+"在"+createTime+"发来信息：");
    $("#toclose").unbind("click").bind("click",function(){
    $("#winpop").hide();

    $.ajax({
    url : "<c:url value='/village/getUnReadMessage'/>",
    type : 'post',
    success : function(data){
    if(undefined != data.message){
    tips_pop(data.message.id,data.message.title,data.message.createName,data.message.createTime)
    }
    }
    });
    });

    $("#shwomsg").unbind("click").bind("click",function(){
    var messageId = $(this).attr("core-msgid");

    window.open("<c:url value='/village/messageDetail?messageId="+messageId+"'/>");
    });

    }

    </script>
    </body>
    </html>