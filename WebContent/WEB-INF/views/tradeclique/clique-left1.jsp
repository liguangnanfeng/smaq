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
        <!-- ��ർ�� -->
        <aside class="Hui-aside">
        <input runat="server" id="divScrollValue" type="hidden" value="" />
        <div class="menu_dropdown bk_2">
        <dl>
        <dt>
        <i class="Hui-iconfont">&#xe643;</i>��ҵ�������ݿ�
        <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
        <ul>
        <li><a data-href="${ly }/tradeclique/clique/basic-information" data-title="������Ϣ" href="javascript:void(0)">������Ϣ</a></li>
        <%--  	 <div class="div_omenu">
                 <div  class="div_otop">������ҵ<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>
                 <ul>
                     <!-- ѭ����ʼ -->
                     <c:forEach items="${list }" varStatus="index" var="list">
                         <li><a data-href="${ly }/tradeclique/welcomecompany?companyId=${list.uid }" data-title="${list.companyname}" href="javascript:void(0)">${list.companyname}</a></li>
                     </c:forEach>
                     <!-- ѭ������ -->
                 </ul>
             </div>
        --%>
        </ul>
        <div class="div_omenu">
        <div  class="div_otop">��֯�ܹ�<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>
        <ul>
        <li><a data-href="${ly }/company/system/department-list" data-title="��֯�ܹ�" href="javascript:void(0)">��֯�ܹ�</a></li>
        <li><a data-href="${ly }/tradeclique/information/user-list" data-title="������Ա" href="javascript:void(0)">������Ա</a></li>
        </ul>
        </div>
        <ul>
        <li><a data-href="${ly }/tradeclique/product/mequipment-list" data-title="��Ҫ�豸��ʩ" href="javascript:void(0)">��Ҫ�豸��ʩ</a></li>
        <li><a data-href="${ly }/tradeclique/product/material-list" data-title="��Ҫԭ������" href="javascript:void(0)">��Ҫԭ������</a></li>
        <li><a data-href="${ly }/tradeclique/product/product-list" data-title="��Ҫ������Ʒ" href="javascript:void(0)">��Ҫ������Ʒ</a></li>
        <li><a data-href="${ly }/tradeclique/process/process-list" data-title="������������ͼ" href="javascript:void(0)">������������ͼ</a></li>
        <li><a data-href="${ly }/tradeclique/company/company-list" data-title="������ҵ" href="javascript:void(0)">������ҵ</a></li>
        </ul>
        <%--
            <ul>
              <c:if test="${session_user.id != 788}">
              <li><a data-href="${ly }/village/company/company-list" data-title="ȫ����ҵ" href="javascript:void(0)">ȫ����ҵ</a></li>
              <li><a data-href="${ly }/village/company/company-tab" data-title="��ҵ����" href="javascript:void(0)">��ҵ����</a></li>
              <li><a data-href="${ly }/village/company/company-list?isKey=1&sk=0" data-title="�ص���ҵ" href="javascript:void(0)">�ص���ҵ</a></li>
              </c:if>
              <c:if test="${session_user.id == 788}">
              <li><a data-href="${ly }/village/company/company-tab" data-title="��ҵ����" href="javascript:void(0)">��ҵ����</a></li>
              <li><a data-href="${ly }/village/company/company-list" data-title="������ҵ" href="javascript:void(0)">������ҵ</a></li>
              </c:if>
            </ul>
           --%>
        <%-- <div class="div_omenu">
          <div  class="div_otop">��Ϣ����<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>
          <ul>
            <li><a data-href="${ly }/village/warning/sperson-list" data-title="��֤�ϸ�" href="javascript:void(0)">��֤�ϸ�</a></li>
            <li><a data-href="${ly }/village/warning/sequipment-list" data-title="�����豸���" href="javascript:void(0)">�����豸���</a></li>
            <li><a data-href="${ly }/village/warning/detection-list" data-title="ְҵ�������" href="javascript:void(0)">ְҵ�������</a></li>
            <li><a data-href="${ly }/village/warning/lightning-list" data-title="���׷�����" href="javascript:void(0)">���׷�����</a></li>
            <li><a data-href="${ly }/village/evaluate/evaluation-list" data-title="��������" href="javascript:void(0)">��������</a></li>
            <li><a data-href="${ly }/village/evaluate/zheng-list" data-title="�������֤" href="javascript:void(0)">�������֤</a></li>
            <li><a data-href="${ly }/company/tables/tab-yjlist" data-title="Ӧ��Ԥ��" href="javascript:void(0)">Ӧ��Ԥ��</a></li>
          </ul>
        </div> --%>
        </dd>
        </dl>
        <dl>
        <dt>
        <i class="Hui-iconfont">&#xe63c;</i>���շּ���ܿ�
        <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
        <ul>
        <li><a data-href="${ly }/village/safety-system/control-listmenu?level=��ɫ" data-title="�ش���ջ���" href="javascript:void(0)">�ش���ջ���</a></li>
        <li><a data-href="${ly }/village/safety-system/control-listmenu?level=��ɫ" data-title="�ϴ���ջ���" href="javascript:void(0)">�ϴ���ջ���</a></li>
        <li><a data-href="${ly }/village/safety-system/statistics-list" data-title="���ݷ���" href="javascript:void(0)">���ݷ���</a></li>
        </ul>
        </dd>
        </dl>
        <dl>
        <dt>
        <i class="Hui-iconfont">&#xe687;</i>�����Ų�������
        <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
        <div class="div_omenu">
        <div  class="div_otop">���������ʵʩ<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>
        <ul>
        <%-- <li><a data-href="${ly }/tradeclique/model-list-cx?flag=1&type=1" data-title="��ҵ�Բ�" href="javascript:void(0)">��ҵ�Բ�</a></li> --%>
        <li><a data-href="${ly }/village/check-list-cx?flag=4" data-title="�������" href="javascript:void(0)">�������</a></li>
        <li><a data-href="${ly }/village/check-list-cx?flag=3" data-title="���ų��" href="javascript:void(0)">���ų��</a></li>
        </ul>
        </div>
        <ul>
        <li><a data-href="${ly }/village/check-list?flag=4" data-title="�Ų������¼" href="javascript:void(0)">�Ų������¼</a></li>
        <%--  <li><a data-href="${ly }/village/check-add-cho" data-title="��������" href="javascript:void(0)">��������</a></li>
         <li><a data-href="${ly }/village/rectification-list?flag=1" data-title="����������" href="javascript:void(0)">����������</a></li>
         <li><a data-href="${ly }/village/check-list?flag=1" data-title="�Ų������¼" href="javascript:void(0)">�Ų������¼</a></li>
         <li><a data-href="${ly }/village/evaluate/lawDoc-list?flag=4" data-title="�ϴ�ͼƬ��Ƶ����" href="javascript:void(0)">�ϴ�ͼƬ��Ƶ����</a></li>
         <li><a data-href="${ly }/village/danger/opinion-list?type=1&flag2=4" data-title="�������" href="javascript:void(0)">�������</a></li> --%>
        <li><a data-href="${ly }/village/danger-collect" data-title="����ͳ�ƻ���" href="javascript:void(0)">����ͳ�ƻ���</a></li>
        <li><a data-href="${ly }/village/danger-chart" data-title="�������ݷ���" href="javascript:void(0)">�������ݷ���</a></li>
        <%-- <li><a data-href="${ly }/html/about.html" data-title="����ָ��" href="javascript:void(0)">����ָ��</a></li> --%>
        </ul>
        </dd>
        </dl>
        <dl>
        <dt>
        <i class="Hui-iconfont">&#xe687;</i>�����������ŵ
        <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
        <ul>
        <li><a data-href="${ly }/villageAdd/study/promise-collect" data-title="��ȫ��ŵ����" href="javascript:void(0)">��ŵ���湫ʾ����</a></li>
        </ul>
        </dd>
        </dl>
        <dl>
        <dt>
        <i class="Hui-iconfont">&#xe687;</i>�ش�Σ��Դ����
        <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
        <ul>
        <li><a data-href="${ly }/villageAdd/source/company-list" data-title="ȫ����ҵ" href="javascript:void(0)">ȫ����ҵ</a></li>
        <li><a data-href="${ly }/villageAdd/source/video" data-title="���߼��" href="javascript:void(0)">���߼��</a></li>
        <li><a data-href="${ly }/villageAdd/source/company-map" data-title="��ҵ�ֲ�" href="javascript:void(0)">��ҵ�ֲ�</a></li>
        <li><a data-href="${ly }/villageAdd/source/danger-collect" data-title="�����Ų�����" href="javascript:void(0)">�����Ų�����</a></li>
        <li><a data-href="${ly }/villageAdd/source/danger-chart" data-title="����ͳ��" href="javascript:void(0)">����ͳ��</a></li>
        </ul>
        </dd>
        </dl>

        <dl class="list_an">
        <dt>
        <i class="Hui-iconfont">&#xe639;</i>��ȫ������׼��
        <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
        <ul>
        <%--<li><a data-href="${ly }/company/threeLeft?leftBasic=8" data-title="��ȫ����"--%>
        <%--href="javascript:void(0)">��ȫ����</a>--%>
        <%--</li>--%>

        <%--<li><a data-href="${ly }/company/threeLeft?leftBasic=9" data-title="��ǫ̈̄��"--%>
        <%--href="javascript:void(0)">��ǫ̈̄��</a>--%>
        <%--</li>--%>

        <!-- �ɰ�(����) <li><a data-href="${ly }/company/tables/tab-biaozhun" data-title="��ȫ��׼��"
        href="javascript:void(0)">��ȫ��׼��</a></li> -->
        <li><a data-href="${ly }/api/safety_Standard/findAll?parendId=0&flag=1" data-title="���в���ϵͳ"
        href="javascript:void(0)">���в���ϵͳ</a></li>

        <%--<li><a data-href="${ly }/company/threeLeft?leftBasic=18" data-title="�豸��ʩ����"--%>
        <%--href="javascript:void(0)">�豸��ʩ����</a></li>--%>
        <%--<li><a data-href="${ly }/company/threeLeft?leftBasic=17&userName=${userName}" data-title="������ѵ"--%>
        <%--href="javascript:void(0)">������ѵ</a></li>--%>
        <%--<li><a target="_blank" data-href="http://exam.dicarl.com/index.php?user-app-login&mobile=${userName }" data-title="��ȫ��ѵ"  >��ȫ��ѵ</a></li>--%>


        <%--<li><a data-href="${ly }/company/threeLeft?leftBasic=8" data-title="������"
               href="javascript:void(0)">��ǫ̈̄��</a></li>
        <li><a data-href="${ly }/company/threeLeft?leftBasic=9" data-title="������"
               href="javascript:void(0)">��ȫ��׼��</a></li>--%>


        <%--<li><a data-href="${ly }/company/tables/tab-list?isType=1" data-title="��ȫ���������ƶ�"
        href="javascript:void(0)">�����ƶ�</a></li>
        <li><a data-href="${ly }/company/threeLeft?leftBasic=8" data-title="������"
        href="javascript:void(0)">������</a></li>
        <li><a data-href="${ly }/company/threeLeft?leftBasic=9" data-title="������"
        href="javascript:void(0)">������</a></li>--%>
        </ul>


        <%--<div class="div_omenu">--%>
        <%--<div class="div_otop">������<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>--%>
        <%--<ul>--%>
        <%--<li><a data-href="${ly }/company/tables/tab-list?isType=0" data-title="��ȫ����������"--%>
        <%--href="javascript:void(0)">������</a></li>--%>
        <%--<li><a data-href="${ly }/company/information/information6" data-title="��ȫ������������ͼ"--%>
        <%--href="javascript:void(0)">��������ͼ</a></li>--%>
        <%--</ul>--%>
        <%--</div>--%>
        <%--<div class="div_omenu2">--%>
        <%--<div class="div_otop2">������<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>--%>
        <%--<div class="div_omenu">--%>
        <%--<div class="div_otop">������ѵ<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>--%>
        <%--<ul>--%>
        <%--<li><a data-href="${ly }/company/train/special-list?spFlag=0" style="padding-left:55px;"--%>
        <%--data-title="��֤�ϸ�" href="javascript:void(0)">��֤�ϸ�</a></li>--%>
        <%--<li><a data-href="${ly }/company/tables/tab-anlist" style="padding-left:55px;"--%>
        <%--data-title="��ȫ��������̨��" href="javascript:void(0)">����̨��</a></li>--%>
        <%--&lt;%&ndash; <li><a data-href="${ly }/company/tables/tab-list?isType=2" data-title="��ѵ��������" href="javascript:void(0)">��ѵ��������</a></li>--%>
        <%--<li><a data-href="${ly }/company/tables/tab-list?isType=3" data-title="�����ѵ�ƻ�" href="javascript:void(0)">�����ѵ�ƻ�</a></li>--%>
        <%--<li><a data-href="${ly }/company/tables/tab-list?isType=4" data-title="�任����/���°�ȫ������" href="javascript:void(0)">�任���ְ�ȫ������</a></li>--%>
        <%--<li><a data-href="${ly }/company/tables/tab-list?isType=5" data-title="������ȫ������" href="javascript:void(0)">������ȫ������</a></li>--%>
        <%--<li><a data-href="${ly }/company/tables/tab-list?isType=6" data-title="������ȫ������" href="javascript:void(0)">������ȫ������</a></li>--%>
        <%--<li><a data-href="${ly }/company/tables/tab-list?isType=7" data-title="��ѵ��¼��" href="javascript:void(0)">��ѵ��¼��</a></li> &ndash;%&gt;--%>
        <%--</ul>--%>
        <%--</div>--%>
        <%--<div class="div_omenu">--%>
        <%--<div class="div_otop">�豸��ʩ����<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>--%>
        <%--<ul>--%>
        <%--<li><a data-href="${ly }/company/product/mequipment-list" style="padding-left:55px;"--%>
        <%--data-title="��Ҫ�豸�嵥" href="javascript:void(0)">��Ҫ�豸�嵥</a></li>--%>
        <%--<li><a data-href="${ly }/company/sequipment/sequipment-list" style="padding-left:55px;"--%>
        <%--data-title="�����豸���" href="javascript:void(0)">�����豸���</a></li>--%>
        <%--<li><a data-href="${ly }/company/product/lightning-list" style="padding-left:55px;"--%>
        <%--data-title="���׷�����" href="javascript:void(0)">���׷�����</a></li>--%>
        <%--<li><a data-href="${ly }/company/safety-facilities" style="padding-left:55px;"--%>
        <%--data-title="��ȫ��ʩ�Ǽ�̨��" href="javascript:void(0)">��ȫ��ʩ�Ǽ�̨��</a></li>--%>
        <%--&lt;%&ndash; <li><a data-href="${ly }/company/tables/tab-list?isType=9" style="padding-left:55px;" data-title="����ۺϼ�ά�޼ƻ�" href="javascript:void(0)">����ۺϼ�ά�޼ƻ�</a></li>--%>
        <%--<li><a data-href="${ly }/company/tables/tab-list?isType=10" style="padding-left:55px;" data-title="�ֳֵ綯���߾�Ե����ֵ������¼��" href="javascript:void(0)">�ֳֹ��߾�Ե������</a></li>--%>
        <%--<li><a data-href="${ly }/company/tables/tab-list?isType=11" style="padding-left:55px;" data-title="�ƶ������豸��Ե������Ա�" href="javascript:void(0)">�ƶ��豸��Ե���Ա�</a></li> &ndash;%&gt;--%>
        <%--</ul>--%>
        <%--</div>--%>
        <%--<ul class="oul">--%>
        <%--<li><a data-href="${ly }/company/evaluate/evaluation-list?flag=0" data-title="������������"--%>
        <%--href="javascript:void(0)">������������</a></li>--%>
        <%--<li><a data-href="${ly }/company/evaluate/zheng-list" data-title="�������֤����"--%>
        <%--href="javascript:void(0)">�������֤����</a></li>--%>
        <%--<li><a data-href="${ly }/company/information/information3" data-title="���ص�һ�ش�"--%>
        <%--href="javascript:void(0)">���ص�һ�ش�</a></li>--%>
        <%--&lt;%&ndash; <li><a data-href="${ly }/company/tables/tab-yjlist" data-title="Ӧ��Ԥ��" href="javascript:void(0)">Ӧ��Ԥ��</a></li> &ndash;%&gt;--%>
        <%--<li><a data-href="${ly }/company/tables/tab-weilist" data-title="Σ����ҵ֤"--%>
        <%--href="javascript:void(0)">Σ����ҵ֤</a></li>--%>
        <%--<li><a data-href="${ly }/company/tables/tab-jianglist" data-title="��ȫ����"--%>
        <%--href="javascript:void(0)">��ȫ����</a></li>--%>
        <%--<li><a data-href="${ly }/company/tables/tab-shilist" data-title="�¹ʱ���ʹ���"--%>
        <%--href="javascript:void(0)">�¹ʱ���ʹ���</a></li>--%>
        <%--</ul>--%>
        <%--</div>--%>


        <%-- <div class="div_omenu">
            <div  class="div_otop">Ӧ����Ԯ<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>
            <ul>
              <li><a data-href="${ly }/company/tables/tab-list?isType=" data-title="�¹�Ӧ����ԮԤ�������ƻ�" href="javascript:void(0)">Ӧ����ԮԤ�������ƻ�</a></li>
              <li><a data-href="${ly }/company/tables/tab-list?isType=" data-title="�¹�Ӧ����ԮԤ��������¼" href="javascript:void(0)">Ӧ����ԮԤ��������¼</a></li>
              <li><a data-href="${ly }/company/tables/tab-list?isType=" data-title="�¹�Ӧ����ԮԤ��������������" href="javascript:void(0)">Ԥ��������������</a></li>
              <li><a data-href="${ly }/company/tables/tab-list?isType=27" data-title="�������ġ�����������̨��" href="javascript:void(0)">�������ġ�����������̨��</a></li>
              <li><a data-href="${ly }/company/tables/tab-list?isType=28" data-title="Ӧ���������ʼ���¼��" href="javascript:void(0)">Ӧ���������ʼ���</a></li>
            </ul>
          </div> --%>

        <%--
        <div class="div_omenu">
          <div  class="div_otop">Σ����ҵ֤<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>
          <ul>
            <li><a data-href="${ly }/company/tables/tab-list?isType=12" data-title="������ҵ֤" href="javascript:void(0)">������ҵ֤</a></li>
            <li><a data-href="${ly }/company/tables/tab-list?isType=13" data-title="���޿ռ䰲ȫ��ҵ֤" href="javascript:void(0)">���޿ռ䰲ȫ��ҵ֤</a></li>
            <li><a data-href="${ly }/company/tables/tab-list?isType=14" data-title="ä���°�ȫ��ҵ֤" href="javascript:void(0)">ä���°�ȫ��ҵ֤</a></li>
            <li><a data-href="${ly }/company/tables/tab-list?isType=15" data-title="�ߴ���ȫ��ҵ֤" href="javascript:void(0)">�ߴ���ȫ��ҵ֤</a></li>
            <li><a data-href="${ly }/company/tables/tab-list?isType=16" data-title="��װ��ȫ��ҵ֤" href="javascript:void(0)">��װ��ȫ��ҵ֤</a></li>
            <li><a data-href="${ly }/company/tables/tab-list?isType=17" data-title="��ʱ�õ簲ȫ��ҵ֤" href="javascript:void(0)">��ʱ�õ簲ȫ��ҵ֤</a></li>
            <li><a data-href="${ly }/company/tables/tab-list?isType=18" data-title="������ȫ��ҵ֤" href="javascript:void(0)">������ȫ��ҵ֤</a></li>
            <li><a data-href="${ly }/company/tables/tab-list?isType=19" data-title="��·��ȫ��ҵ֤" href="javascript:void(0)">��·��ȫ��ҵ֤</a></li>
          </ul>
        </div> --%>

        <%-- <div class="div_omenu">
          <div  class="div_otop">��ȫ����<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>
          <ul>
            <li><a data-href="${ly }/company/tables/tab-list?isType=22" data-title="��ȫ�����ƿ��˺ͽ���̨��" href="javascript:void(0)">�����ƿ��˺ͽ���̨��</a></li>
            <li><a data-href="${ly }/company/tables/tab-list?isType=23" data-title="��ȫ�������˺ͽ���̨��" href="javascript:void(0)">�������˺ͽ���̨��</a></li>
          </ul>
        </div> --%>
        <%-- <div class="div_omenu">
          <div  class="div_otop">�¹ʱ���ʹ���<i class="Hui-iconfont top-iconfont">&#xe6d5;</i></div>
          <ul>
            <li><a data-href="${ly }/company/tables/tab-list?isType=24" data-title="�����¹ʵ���" href="javascript:void(0)">�����¹ʵ���</a></li>
            <li><a data-href="${ly }/company/tables/tab-list?isType=25" data-title="�����¹ʵ��鼰������" href="javascript:void(0)">�����¹ʵ��鼰����</a></li>
            <li><a data-href="${ly }/company/tables/tab-list?isType=26" data-title="�����¹�̨��" href="javascript:void(0)">�����¹�̨��</a></li>
          </ul>
        </div> --%>


        </dd>
        </dl>
        <%--<dl>--%>
        <%--<dt>--%>
        <%--<i class="Hui-iconfont">&#xe70c;</i>��ȫ�������--%>
        <%--<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>--%>
        <%--</dt>--%>
        <%--<dd>--%>
        <%--<ul>--%>
        <%--<li><a data-href="${ly }/village/company/company-list-zg?cisDanger=1" data-title="�ش�Σ��Դ" onclick="javascript:void(0)">�ش�Σ��Դ</a></li>--%>
        <%--<li><a data-href="${ly }/tradeclique/dangerouswork-list" data-title="��Σ��ҵ" onclick="javascript:void(0)">��Σ��ҵ</a></li>--%>
        <%--<li><a data-href="${ly }/tradeclique/certified?spFlag=0" data-title="��֤�ϸ�" href="javascript:void(0)">��֤�ϸ�</a></li>--%>
        <%--<li><a data-href="${ly }/tradeclique/sequipment/sequipment-list" data-title="�����豸���" href="javascript:void(0)">�����豸���</a></li>--%>
        <%--<li><a data-href="${ly }/tradeclique/detection-list" data-title="ְҵ�������" href="javascript:void(0)">ְҵ�������</a></li>--%>
        <%--<li><a data-href="${ly }/tradeclique/clique/lightning-list" data-title="���׷�����" href="javascript:void(0)">���׷�����</a></li>--%>
        <%--<li><a data-href="${ly }/tradeclique/clique/evaluation-list" data-title="��������" href="javascript:void(0)">��������</a></li>--%>
        <%--<li><a data-href="${ly }/tradeclique/clique/license-list" data-title="�������֤" href="javascript:void(0)">�������֤</a></li>--%>
        <%--<li><a data-href="${ly }/tradeclique/clique/emergency-plan" data-title="Ӧ��Ԥ��" href="javascript:void(0)">Ӧ��Ԥ��</a></li>--%>
        <%--</ul>--%>
        <%--</dd>--%>
        <%--</dl>--%>


        <dl>
        <dt>
        <i class="Hui-iconfont">&#xe70c;</i>����ִ�����
        <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
        <ul>
        <%--<li><a data-href="${ly }/village/check-list-cx?flag=2" data-title="ִ�����" href="javascript:void(0)">ִ�����</a></li>
         <li><a data-href="${ly }/village/rectification-list?flag=2" data-title="ִ������" href="javascript:void(0)">ִ������</a></li>
        <li><a data-href="${ly }/village/check-list?flag=2" data-title="ִ����¼" href="javascript:void(0)">ִ����¼</a></li>
        <li><a data-href="${ly }/village/danger/opinion-list?type=3&flag2=2" data-title="��������" href="javascript:void(0)">��������</a></li>
        <li><a data-href="${ly }/village/opinion-show?flag2=2" data-title="ִ������" href="javascript:void(0)">ִ������</a></li>
        <li><a data-href="${ly }/village/evaluate/lawDoc-list?flag=2" data-title="�ϴ�ͼƬ��Ƶ����" href="javascript:void(0)">�ϴ�ͼƬ��Ƶ����</a></li>
        <li><a data-href="${ly }/district/off-list" data-title="ִ����Ա��Ϣ��" href="javascript:void(0)">ִ����Ա��Ϣ��</a></li>--%>
        <li><a data-href="${ly }/village/opinion-show?flag2=2" data-title="ִ������" href="javascript:void(0)">ִ������</a></li>
        <li><a data-href="${ly }/village/evaluate/lawDoc-list?flag=2" data-title="ִ��ͼƬ��Ƶ����" href="javascript:void(0)">ִ��ͼƬ��Ƶ����</a></li>
        </ul>
        </dd>
        </dl>
        <dl>
        <dt>
        <i class="Hui-iconfont">&#xe653;</i>�쵼��ע�����
        <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
        <ul>
        <li><a data-href="javascript:void(0)" data-title="һ�ܼ�ܶ�̬" href="javascript:void(0)">һ�ܼ�ܶ�̬</a></li>
        <li><a data-href="${ly }/village/danger-collect-guanzhu?isZhongDa=1" data-title="�����Ų���������" href="javascript:void(0)">�����Ų���������</a></li>
        <li><a data-href="${ly }/village/danger-collect-guanzhu?isZhongDa=2" data-title="�ش��¹���������" href="javascript:void(0)">�ش��¹���������</a></li>
        <li><a data-href="${ly }/village/danger-collect-guanzhu?isZhongDa=3" data-title="����ִ������" href="javascript:void(0)">����ִ������</a></li>
        </ul>
        </dd>
        </dl>
        <%-- <dl>
          <dt>
            <i class="Hui-iconfont">&#xe70c;</i>����ͳ�Ʒ���ϵͳ
            <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
          </dt>
          <dd>
            <ul>
              <li><a data-href="${ly }/village/danger-collect" data-title="����ͳ�ƻ���" href="javascript:void(0)">����ͳ�ƻ���</a></li>
              <li><a data-href="${ly }/village/danger-chart" data-title="�������ݷ���" href="javascript:void(0)" style="color:#fff;">�������ݷ���</a></li>
            </ul>
          </dd>
        </dl> --%>

        <%-- <dl>
          <dt>
            <i class="Hui-iconfont">&#xe6cd;</i>Σ���к����ؼ���
            <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
          </dt>
          <dd>
            <ul>
              <li><a data-href="${ly }/html/danger.html" data-title="�ش��¹������ж���׼" href="javascript:void(0)">�ش��¹������ж���׼</a></li>
              <li><a data-href="${ly }/fore/notice-show?id=543" data-title="�ش���������ж�����" href="javascript:void(0)">�ش���������ж�����</a></li>
              <li><a data-href="${ly }/fore/danger/wei-list" data-title="�ش�Σ��Դ��ʶĿ¼" href="javascript:void(0)">�ش�Σ��Դ��ʶĿ¼</a></li>
              <li><a data-href="${ly }/fore/danger/danger-list" data-title="Σ��ƷĿ¼" href="javascript:void(0)">Σ��ƷĿ¼</a></li>
              <li><a data-href="${ly }/fore/danger/bao-list" data-title="���Ʊ�Σ�ջ�ѧƷ" href="javascript:void(0)">���Ʊ�Σ�ջ�ѧƷ</a></li>
              <li><a data-href="${ly }/fore/danger/du-list" data-title="���ƶ���ѧƷ" href="javascript:void(0)">���ƶ���ѧƷ</a></li>
              <li><a data-href="${ly }/fore/danger/zhi-list" data-title="ְҵ���к�����" href="javascript:void(0)">ְҵ���к�����</a></li>
              <li><a data-href="${ly }/company/trouble/trouble-information" data-title="�����Ų������׼��Ϣ��" href="javascript:void(0)">�����Ų������׼</a></li>
            </ul>
          </dd>
        </dl> --%>
        <dl>
        <dt>
        <i class="Hui-iconfont">&#xe61d;</i>��Ϣ���ͻ���
        <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
        <ul>
        <li><a data-href="${ly }/village/sendMessageList" data-title="��Ϣ�����б�" href="javascript:void(0)">��Ϣ�����б�</a></li>
        <li><a data-href="${ly }/village/reviceMessageList" data-title="��Ϣ�����б�" href="javascript:void(0)">��Ϣ�����б�</a></li>
        <li><a data-href="${ly }/village/addMessages" data-title="��Ϣ����" href="javascript:void(0)">��Ϣ����</a></li>
        <li><a data-href="${ly }/village/groupIndex" data-title="Ⱥ������" href="javascript:void(0)">��ҵȺ������</a></li>
        </ul>
        </dd>
        </dl>

        <dl>
        <dt>
        <i class="Hui-iconfont">&#xe61d;</i>ϵͳ���ù���
        <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
        </dt>
        <dd>
        <ul>
        <li><a data-href="${ly }/village/system/change-psw" data-title="�޸�����" href="javascript:void(0)">�޸�����</a></li>
        <li><a data-href="${ly }/company/system/change-userName" data-title="�޸ĵ�¼��" href="javascript:void(0)">�޸ĵ�¼��</a></li>
        </ul>
        </dd>
        </dl>
        </div>
        </aside>
        <div id="winpop" style="display:none;" >
        <div class="title">
        �����µ���Ϣ��<span class="close" title="�ر�" id="toclose">X</span>
        </div>
        <div class="con" id="shwomsgTips" style="cursor:pointer;height:60px;line-height: 60px;"></div>
        <div class="con" id="shwomsg" style="cursor:pointer;height:30px;line-height:30px;">1��δ����Ϣ</div>
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
        //�жϵ�ǰ������Ƿ�֧��WebSocket
        WEB_SOCKET_SWF_LOCATION = "${ly }/js/plugins/websocket/WebSocketMain.swf";
        if(window.WebSocket){
        websocket = new WebSocket("ws://"+location.hostname+":"+location.port+"/village/websocket/"+userno);
        }
        else {
        //alert("��ǰ�汾��֧��");
        }


        //���ӷ�������Ļص�����
        websocket.onerror = function () {
        //alert("error");
        //setMessageInnerHTML("WebSocket���ӷ�������");
        };


        //���ӳɹ������Ļص�����
        websocket.onopen = function () {
        clearInterval(intervalIndex2);
        }


        //���յ���Ϣ�Ļص�����
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


        //���ӹرյĻص�����
        websocket.onclose = function () {
        //setMessageInnerHTML("WebSocket���ӹر�");
        //alert("close");
        }


        //�������ڹر��¼��������ڹر�ʱ������ȥ�ر�websocket���ӣ���ֹ���ӻ�û�Ͽ��͹رմ��ڣ�server�˻����쳣��
        window.onbeforeunload = function () {
        //closeWebSocket();
        }
        //�ر�WebSocket����
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
        $("#shwomsgTips").html(createName+"��"+createTime+"������Ϣ��");
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