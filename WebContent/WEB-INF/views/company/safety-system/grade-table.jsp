<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <%@ include file="/WEB-INF/inc/back-header.inc" %>
    <title>风险分级管控 隐患排查治理智能化平台</title>
    <meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
    <meta name="description" content="风险分级管控   隐患排查治理智能化平台">
    <style type="text/css">
        body .dis-ib {
            margin-right: 15px;
        }

        .wid1 {
            width: 30%
        }

        .wid2 {
            width: 30%
        }

        .wid3 {
            width: 30%
        }

        .wid4 {
            width: 10%
        }

        .wid5 {
            width: 70%
        }

        .btn-cz {
            margin: auto;
            background: #eee;
            border: 1px solid #ddd;
            padding: 0 5px;
            height: 20px;
            line-height: 20px;
            border-radius: 5px;
            margin-left: 15px
        }

        .btn-cz:hover {
            border: 1px solid #06c;
            color: #06c;
            text-decoration: none;
        }

        body .check-box label {
            margin-left: 5px;
        }

        .one_danger .list_odan {
            margin-left: 20px
        }

        .two_danger .list_twdan {
            margin-left: 45px;
        }

        .one_danger .Hui-iconfont {
            margin-top: 2px
        }

        .one_danger, .list_danger {
            display: inline-block
        }

        .one_danger, .one_danger .list_odan, .two_danger, .two_danger .list_twdan, .list_danger {
            float: none;
        }

        .check-box {
            width: 30%;
            margin-bottom: 15px;
        }

        .btn-group .btn {
            height: 34px;
            line-height: 34px;
            padding: 0 25px;
        }

        .one-danrisk {
            float: left;
            width: 100%;
            border-bottom: 1px solid #ddd;
            height: auto;
        }

        .one-danrisk p {
            float: left;
            width: 80%;
            padding: 8px;
            height: auto;
        }

        .table tbody tr td .one-danrisk a {
            float: right;
            margin-top: 8px;
            margin-right: 8px;
        }

        .my_text {
            text-align: center!important;
            font-size: 25px;
            font-weight: bold;
        }

        .my_red {
            color: red;
        }

        .my_green {
            color: #00B83F;
        }
    </style>
    <script src="/js/jquery.jqprint-0.3.js"></script>
</head>
<body>
<%--<nav class="breadcrumb">--%>
<%--    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>--%>
<%--    <span class="c-gray en">&gt;</span> <span>系统设置</span>--%>
<%--    <span class="c-gray en">&gt;</span> <span>风险辨识</span>--%>
<%--    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="parent.close_tab(function(){})">返回</a>--%>
<%--</nav>--%>
<div class="page-container">

    <div class="page-container" id="div_container">
        <div class="div-print">

            <h3 class="text-c">危险化学品生产储存企业安全风险评估诊断分级指南</h3>


            <div class="mt-20">
                <table class="table table-border table-bordered table-bg table-hover table-sort tab-ndan">
                    <thead>
                    <tr class="text-c">
                        <th style="width:20%">类别</th>
                        <th style="width:20%">项目（分值）</th>
                        <th style="width:50%">评估内容</th>
                        <th style="width:10%">扣分值</th>

                    </thead>
                    <tbody id="tableaaa">
                    <tr>
                        <td>1.固有危险性</td>
                        <td>重大危险源（10分）</td>
                        <td>存在一级危险化学品重大危险源的，扣10分;</td>
                        <td rowspan="5" onclick="dafen('danger1',10,this)" class="my_text"></td>
                    </tr>
                    <tr>
                        <td>1.固有危险性</td>
                        <td>重大危险源（10分）</td>
                        <td>存在二级危险化学品重大危险源的，扣8分;</td>

                    </tr>

                    <tr>
                        <td>1.固有危险性</td>
                        <td>重大危险源（10分）</td>
                        <td>存在三级危险化学品重大危险源的，扣6分;</td>

                    </tr>

                    <tr>
                        <td>1.固有危险性</td>
                        <td>重大危险源（10分）</td>
                        <td>存在三级危险化学品重大危险源的，扣6分;</td>

                    </tr>

                    <tr>
                        <td>1.固有危险性</td>
                        <td>重大危险源（10分）</td>
                        <td>存在四级危险化学品重大危险源的，扣4分;</td>
                    </tr>

                    <tr>
                        <td>1.固有危险性</td>
                        <td>物质危险性（5分）</td>
                        <td>生产、储存爆炸品的（实验室化学试剂除外），每一种扣2分；</td>
                        <td rowspan="3" onclick="dafen('danger2',5,this)" class="my_text"></td>
                    </tr>

                    <tr>
                        <td>1.固有危险性</td>
                        <td>物质危险性（5分）</td>
                        <td>生产、储存（含管道输送）氯气、光气等吸入性剧毒化学品的（实验室化学试剂除外），每一种扣2分；</td>

                    </tr>

                    <tr>
                        <td>1.固有危险性</td>
                        <td>物质危险性（5分）</td>
                        <td>生产、储存其他重点监管危险化学品的（实验室化学试剂除外），每一种扣0.1分。</td>

                    </tr>

                    <tr>
                        <td>1.固有危险性</td>
                        <td>危险化工工艺种类（10分）</td>
                        <td>涉及18种危险化工工艺的，每一种扣2分。</td>
                        <td onclick="dafen('danger3',10,this)" class="my_text"></td>
                    </tr>

                    <tr>
                        <td>1.固有危险性</td>
                        <td>火灾爆炸危险性（5分）</td>
                        <td>涉及甲类/乙类火灾危险性类别厂房、库房或者罐区的，每涉及一处扣1/0.5分；</td>
                        <td rowspan="2" onclick="dafen('danger4',5,this)" class="my_text"></td>
                    </tr>

                    <tr>
                        <td>1.固有危险性</td>
                        <td>火灾爆炸危险性（5分）</td>
                        <td>涉及甲类、乙类火灾危险性罐区、气柜与加热炉等与产生明火的设施、装置比邻布置的，扣5分。</td>

                    </tr>

                    <tr>
                        <td>2.周边环境</td>
                        <td>周边环境（10分）</td>
                        <td>涉及甲类、乙类火灾危险性罐区、气柜与加热炉等与产生明火的设施、装置比邻布置的，扣5分。</td>
                        <td rowspan="2" onclick="dafen('danger5',10,this)" class="my_text"></td>
                    </tr>

                    <tr>
                        <td>2.周边环境</td>
                        <td>周边环境（10分）</td>
                        <td>涉及甲类、乙类火灾危险性罐区、气柜与加热炉等与产生明火的设施、装置比邻布置的，扣5分。</td>
                    </tr>


                    <tr>
                        <td>3.设计与评估</td>
                        <td>设计与评估（10分）</td>
                        <td>国内首次使用的化工工艺未经过省级人民政府有关部门组织安全可靠性论证的，扣5分；</td>
                        <td rowspan="3" onclick="dafen('danger6',10,this)" class="my_text"></td>
                    </tr>

                    <tr>
                        <td>3.设计与评估</td>
                        <td>设计与评估（10分）</td>
                        <td>精细化工企业未按规范性文件要求开展反应安全风险评估的，扣10分；</td>

                    </tr>

                    <tr>
                        <td>3.设计与评估</td>
                        <td>设计与评估（10分）</td>
                        <td>企业危险化学品生产储存装置均由甲级资质设计单位进行全面设计的，加2分。</td>

                    </tr>


                    <tr>
                        <td>4.设备</td>
                        <td>设备（5分）</td>
                        <td>使用淘汰落后安全技术工艺、设备目录列出的工艺及设备的，每一项扣2分；</td>
                        <td rowspan="3" onclick="dafen('danger7',5,this)" class="my_text"></td>
                    </tr>

                    <tr>
                        <td>4.设备</td>
                        <td>设备（5分）</td>
                        <td>特种设备没有办理使用登记证书的，或者未按要求定期检验的，扣2分；</td>

                    </tr>

                    <tr>
                        <td>4.设备</td>
                        <td>设备（5分）</td>
                        <td>化工生产装置未按国家标准要求设置双电源或者双回路供电的，扣5分。</td>

                    </tr>

                    <tr>
                        <td>5.自控与安全设施</td>
                        <td>自控与安全设施（10分）</td>
                        <td>涉及重点监管危险化工工艺的装置未按要求实现自动化控制，系统未实现紧急停车功能，装备的自动化控制系统、紧急停车系统未投入使用的，扣10分；</td>
                        <td rowspan="7" onclick="dafen('danger8',10,this)" class="my_text"></td>
                    </tr>

                    <tr>
                        <td>5.自控与安全设施</td>
                        <td>自控与安全设施（10分）</td>
                        <td>涉及毒性气体、液化气体、剧毒液体的一级、二级重大危险源的危险化学品罐区未配备独立的安全仪表系统的，扣10分；</td>

                    </tr>


                    <tr>
                        <td>5.自控与安全设施</td>
                        <td>自控与安全设施（10分）</td>
                        <td>构成一级、二级重大危险源的危险化学品罐区未实现紧急切断功能的，扣5分；</td>

                    </tr>

                    <tr>
                        <td>5.自控与安全设施</td>
                        <td>自控与安全设施（10分）</td>
                        <td>危险化学品重大危险源未设置压力、液位、温度远传监控和超限位报警装置的，每涉及一项扣1分；</td>

                    </tr>


                    <tr>
                        <td>5.自控与安全设施</td>
                        <td>自控与安全设施（10分）</td>
                        <td>涉及可燃和有毒有害气体泄漏的场所未按国家标准设置检测声光报警设施的，每一处扣1分；</td>

                    </tr>

                    <tr>
                        <td>5.自控与安全设施</td>
                        <td>自控与安全设施（10分）</td>
                        <td>防爆区域未按国家标准安装使用防爆电气设备的，每一处扣1分；</td>

                    </tr>

                    <tr>
                        <td>5.自控与安全设施</td>
                        <td>自控与安全设施（10分）</td>
                        <td>甲类、乙类火灾危险性生产装置内设有办公室、操作室、固定操作岗位或休息室的，每涉及一处扣5分。</td>

                    </tr>


                    <tr>
                        <td>6.人员资质</td>
                        <td>人员资质（15分）</td>
                        <td>企业主要负责人和安全生产管理人员未依法经考核合格的，每一人次扣5分；</td>
                        <td rowspan="5" onclick="dafen('danger9',15,this)" class="my_text"></td>
                    </tr>

                    <tr>
                        <td>6.人员资质</td>
                        <td>人员资质（15分）</td>
                        <td>企业专职安全生产管理人员不具备国民教育化工化学类（或安全工程）中等职业教育以上学历或者化工化学类中级以上专业技术职称的，每一人次扣5分；</td>

                    </tr>

                    <tr>
                        <td>6.人员资质</td>
                        <td>人员资质（15分）</td>
                        <td>涉及“两重点一重大”装置的生产、设备及工艺专业管理人员不具有相应专业大专以上学历的，每一人次扣5分；</td>

                    </tr>

                    <tr>
                        <td>6.人员资质</td>
                        <td>人员资质（15分）</td>
                        <td>企业未按有关要求配备注册安全工程师的，扣3分；</td>

                    </tr>


                    <tr>
                        <td>6.人员资质</td>
                        <td>人员资质（15分）</td>
                        <td>企业主要负责人、分管安全生产工作负责人、安全管理部门主要负责人为化学化工类专业毕业的，每一人次加2分。</td>

                    </tr>


                    <tr>
                        <td>7.安全管理制度</td>
                        <td>管理制度（10分）</td>
                        <td>未制定操作规程和工艺控制指标或者制定的操作规程和工艺控制指标不完善的，扣5分；</td>
                        <td rowspan="3" onclick="dafen('danger10',10,this)" class="my_text"></td>
                    </tr>


                    <tr>
                        <td>7.安全管理制度</td>
                        <td>管理制度（10分）</td>
                        <td>动火、进入受限空间等特殊作业管理制度不符合国家标准或未有效执行的，扣10分；</td>

                    </tr>

                    <tr>
                        <td>7.安全管理制度</td>
                        <td>管理制度（10分）</td>
                        <td>未建立与岗位相匹配的全员安全生产责任制的，每涉及一个岗位扣2分。</td>

                    </tr>

                    <tr>
                        <td>8.应急管理</td>
                        <td>应急配备</td>
                        <td>企业自设专职消防应急队伍的，加3分。</td>
                        <td onclick="dafen('danger11',10,this)" class="my_text"></td>
                    </tr>

                    <tr>
                        <td>9.安全管理绩效</td>
                        <td>安全生产标准化达标</td>
                        <td>安全生产标准化为一级的，加15分；</td>
                        <td rowspan="3" onclick="dafen('danger12',15,this)" class="my_text"></td>
                    </tr>

                    <tr>
                        <td>9.安全管理绩效</td>
                        <td>安全生产标准化达标</td>
                        <td>安全生产标准化为二级的，加5分；</td>

                    </tr>

                    <tr>
                        <td>9.安全管理绩效</td>
                        <td>安全生产标准化达标</td>
                        <td>安全生产标准化为三级的，加2分。</td>

                    </tr>

                    <tr>
                        <td>9.安全管理绩效</td>
                        <td>安全事故情况（10分）</td>
                        <td>三年内发生过1起较大安全事故的，扣10分;</td>
                        <td rowspan="4" onclick="dafen('danger13',10,this)" class="my_text"></td>
                    </tr>

                    <tr>
                        <td>9.安全管理绩效</td>
                        <td>安全事故情况（10分）</td>
                        <td>三年内发生过1起安全事故造成1-2人死亡的，扣8分;</td>

                    </tr>


                    <tr>
                        <td>9.安全管理绩效</td>
                        <td>安全事故情况（10分）</td>
                        <td>三年内发生过爆炸、着火、中毒等具有社会影响的安全事故，但未造成人员伤亡的，扣5分;</td>

                    </tr>


                    <tr>
                        <td>9.安全管理绩效</td>
                        <td>安全事故情况（10分）</td>
                        <td>五年内未发生安全事故的，加5分。</td>

                    </tr>


                    </tbody>
                </table>


                <table class="table table-border table-bordered table-bg table-hover table-sort tab-ndan">

                    <tbody>
                    <tr>
                        <td colspan="2">存在下列情况之一的企业直接判定为红色（最高风险等级）</td>
                    </tr>
                    <tr>
                        <td>新开发的危险化学品生产工艺未经小试、中试和工业化试验直接进行工业化生产的；</td>
                        <td width="10%"></td>
                    </tr>

                    <tr>
                        <td>在役化工装置未经正规设计且未进行安全设计诊断的；</td>
                        <td width="10%"></td>
                    </tr>
                    <tr>
                        <td>危险化学品特种作业人员未持有效证件上岗或者未达到高中以上文化程度的;</td>
                        <td width="10%"></td>
                    </tr>


                    <tr>
                        <td>三年内发生过重大以上安全事故的，或者三年内发生2起较大安全事故，或者近一年内发生2起以上亡人一般安全事故的。</td>
                        <td width="10%"></td>
                    </tr>


                    <tr>
                        <td colspan="2">备注：
                            1.安全风险从高到低依次对应为红色、橙色、黄色、蓝色。总分在90分以上（含90分）的为蓝色；75分（含75分）至90分的为黄色；60分（含60分）至75分的为橙色；60分以下的为红色。
                            2.每个项目分值扣完为止，最低为0分。
                            3.储存企业指带储存的经营企业。
                        </td>

                    </tr>

                    </tbody>

                </table>

            </div>
        </div>
        <!-- 弹窗输入 -->
        <div id="modal-plan2" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" style="margin-top: 150px">
                <div class="modal-content radius">
                    <div class="modal-header">
                        <h3 class="modal-title">输入评分(只能输入数字)</h3>
                        <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
                    </div>
                    <div class="modal-body" style="height: 200px; overflow-y: auto">
                        <div class="form form-horizontal">
                            <div class="row cl dq">
                                <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>输入评分
                                    :</label>
                                <div class="formControls col-xs-5 col-sm-5">
                                    <input class="input-text" type="text" name="" id="trInput"
                                           oninput="if(value.length==1){value=value.replace(/[^(\-?)\d+]/ig,'')}else{value=value.substring(0,1)+value.substring(1,value.length).replace(/[^\d+]/ig,'');}"
                                           style="width:150px">
                                </div>
                                <div class="col-xs-3 col-sm-3">
                                    <button class="btn radius btn-primary size-S" onclick="queren()">
                                        确认
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>


    </div>
</div>
</body>
<script>
    var postData = {
        danger1: 0,
        danger2: 0,
        danger3: 0,
        danger4: 0,
        danger5: 0,
        danger6: 0,
        danger7: 0,
        danger8: 0,
        danger9: 0,
        danger10: 0,
        danger11: 0,
        danger12: 0,
        danger13: 0,
        count: 100
    }
    var current = '';  //当前要复制的postData的key
    var currentDom = ''  // 当前要操作的dom
    var max = 0    //当前能输入的最大值
    function dafen(zd, xz, dom) {  //zd 要赋值的变量名    xz 表示输入最大的范围
        current = zd;
        currentDom = $(dom);
        max = xz;
        $("#modal-plan2").modal("show");
    }

    function queren() {
        var val = parseInt($('#trInput').val());  //为了保险还是先转化成数字
        if (Math.abs(val) > max) {   // 如果输入的数字大于限制  则提示
            layer.msg('输入的值不能超过最大分值');
            return
        } else {
            currentDom.removeClass("my_green")
            currentDom.removeClass("my_red")
            if (val >= 0) {
                currentDom.addClass("my_green")
            } else {
                currentDom.addClass("my_red")
            }
            postData[current] = val;
            if(val>0){
                val = '+'+val;
            }
            currentDom.text(val);
            $("#modal-plan2").modal("hide");
        }

    }
</script>
<script type="text/javascript">
    $(function () {
        var b = null, l1 = '', c = 1;
        var b2 = null, l2 = '', c2 = 1;
        $("#tableaaa tr").each(function () {
            var td = $(this).children("td").eq(0);
            var td2 = $(this).children("td").eq(1);
            var l1_ = td.text();
            var l2_ = td2.text();
            //Same to top level
            if (l1 == l1_) {
                td.remove();
                c = c + 1;
                if (l2 == l2_) {
                    td2.remove();
                    c2 = c2 + 1;

                } else {
                    l2 = l2_;
                    if (b2 != null) {
                        b2.attr("rowspan", c2);
                        c2 = 1;
                    }
                    b2 = td2;
                }

            } else {//Diffrent to top level
                l1 = l1_;
                if (b != null) {
                    b.attr("rowspan", c);
                    c = 1;
                }
                b = td;
                l2 = l2_;
                if (b2 != null) {
                    b2.attr("rowspan", c2);
                    c2 = 1;
                }
                b2 = td2;

            }
        })
        if (b != null) {
            b.attr("rowspan", c);
        }
        if (b2 != null) {
            b2.attr("rowspan", c2);
        }
    })

</script>
</html>