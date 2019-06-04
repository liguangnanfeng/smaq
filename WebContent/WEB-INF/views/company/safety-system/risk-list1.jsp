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
        .one-danrisk{float:left;width:100%;border-bottom:1px solid #ddd;height:auto;}
        .one-danrisk p{float:left;width:80%;padding: 8px;height:auto;}
        .table tbody tr td .one-danrisk a{float:right;margin-top:8px;margin-right:8px;}
    </style>
    <script type="text/javascript">
        /* 弹窗工具添加 */
        function addgj(depid) {
            show_dialog(" ", "/company/safety-system/risk-list-load?depId=" + depid);
        }

        /* 弹窗工具添加 */
        function addgjs(depid) {
            show_dialog(" ", "/company/safety-system/risk-list-loads?depId=" + depid);
        }



        function redit(id, type) {
            var titles;
            titles = ['',"职业危害物理因素选择",'职业危害化学因素选择','职业危害高危工艺选择','物料辨识选择','高危作业选择'];
            show_dialog(titles[type], '/company/safety-system/set-choose?id=' + id + "&type=" + type);
        }

        function show_ys(type) {
            var t = ['确定职业危害物理因素', '确定职业危害化学因素', '高危工艺', '物料辨识','高危作业'];
            var index = layer.open({
                type : 2,
                title : t[type-1],
                content : "/company/safety-system/risk-list-load2?type=" + type,
                scrollbar: false,
                end: function(){
                    // 右上角关闭回调
                    location.reload();
                }
            });
            layer.full(index);
        }
    </script>
<script type="text/javascript">
function pr_() {
  $("#div_container").jqprint();
}
</script>
<script src="/js/jquery.jqprint-0.3.js"></script>
</head>
<body>
<nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
    <span class="c-gray en">&gt;</span> <span>系统设置</span>
    <span class="c-gray en">&gt;</span> <span>风险辨识</span>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:void(0);" title="返回" onclick="parent.close_tab(function(){})">返回</a>
</nav>
<div class="page-container">
    <div id="spTab" class="btn-group" style="text-align: center;margin-bottom: 20px;">
        <a class="btn default ${empty type ? 'btn-primary' : 'radius'}" href="${ly }/company/safety-system/risk-list">风险点分布表</a>
        <a class="btn default ${1 eq type ? 'btn-primary' : 'radius'}" href="${ly }/company/safety-system/risk-list?type=1">职业病风险物理因素分布表</a>
        <a class="btn default ${2 eq  type ? 'btn-primary' : 'radius'}" href="${ly }/company/safety-system/risk-list?type=2">职业病风险化学因素分布表</a>
        <c:if test="${company.industry eq '化工企业（危化生产、使用）'}">
            <c:choose>
                <c:when test="${fn:contains(company.name, '油') }">
                </c:when>
                <c:otherwise>
                    <a class="btn default ${3 eq  type ? 'btn-primary' : 'radius'}" href="${ly }/company/safety-system/risk-list?type=3">高危工艺分布表</a>
                </c:otherwise>
            </c:choose>
            <a class="btn default ${4 eq  type ? 'btn-primary' : 'radius'}" href="${ly }/company/safety-system/risk-list?type=4">物料风险分布表</a>
        </c:if>
        <a class="btn default ${5 eq  type ? 'btn-primary' : 'radius'}" href="${ly }/company/safety-system/risk-list?type=5">高危作业分布表</a>
    </div>

  <div class="text-c mt-20">
    <button onClick="pr_()" class="btn btn-primary radius" type="button">
        <i class="Hui-iconfont">&#xe652;</i>打印
    </button>
  </div>
  <div class="page-container" id="div_container" >
    <div class="div-print">
	
    <c:if test="${empty type}">
        <h3 class="text-c">${company.name}风险因素辨识表</h3>
    </c:if>
    <c:if test="${type eq 1}">
        <h3 class="text-c">${company.name}职业病风险物理因素分布表</h3>
    </c:if>
    <c:if test="${type eq 2}">
        <h3 class="text-c">${company.name}职业病风险化学因素分布表</h3>
    </c:if>
    <c:if test="${type eq 3}">
        <h3 class="text-c">${company.name}高危工艺分布表</h3>
    </c:if>
    <c:if test="${type eq 4}">
        <h3 class="text-c">${company.name}物料风险分布表</h3>
    </c:if>
    <c:if test="${type eq 5}">
        <h3 class="text-c">${company.name}高危作业分布表</h3>
    </c:if>
    <div class="mt-20">
        <table class="table table-border table-bordered table-bg table-hover table-sort tab-ndan"  >
            <thead>
            <tr class="text-c">
                <c:if test="${empty type}">
                <th style="width:8%">辨识类型</th>
                <th style="width:8%">车间/场所</th>
                <th style="width:8%">系统</th>
                <th style="width:8%">环节/部位</th> 
                <th style="width:8%">风险类型</th>  
                <th style="width:50%">风险因素</th>
                </c:if>
                <c:if test="${!empty type}">
                <th style="padding:0;width:20%">车间/场所</th>
                <th style="width:10%" >岗位/部位</th>
                </c:if>
                <c:if test="${type eq 1}">
                    <th>职业危害物理因素</th>
                </c:if>
                <c:if test="${type eq 2}">
                    <th>职业危害化学因素</th>
                </c:if>
                <c:if test="${type eq 3}">
                    <th>高危工艺</th>
                </c:if>
                <c:if test="${type eq 4}">
                    <th>物料辨识</th>
                </c:if>
                <c:if test="${type eq 5}">
                    <th>高危作业</th>
                </c:if>
            <th style="width:10%" >操作</th>
            </tr>
            </thead>
            <tbody style="display: none" id="myTable" >
            <c:if test="${empty type}">
                <c:forEach items="${zzjgDep }" var="be">
                                <c:set value="0" var="x"/>
                                <c:forEach items="${acL }" var="ac">
                                    <c:if test="${be.name eq ac.level1}">
 						<script>
						str="${ac.level3}";
						arr=str.split("/");
						</script>
                                        <tr>
						<td style="width:10%;height:80px;" class="text-c">
						<script>
						document.write(arr[0]);
						</script></td>
                        <td style="width:10%;border-bottom: 0px;" class="text-c">${be.name }</td>
                            <c:if test="${x==0}"><td style="width:10%;" class="text-c"></c:if>
                            <c:if test="${x==1}"><td style="width:10%;" class="text-c"></c:if>
                            <p style="text-align:center;"><script>document.write(arr[1]);</script></p>
                            </td>
                            <c:if test="${x==0}"><td style="width:10%;" class="text-c"></c:if>
                            <c:if test="${x==1}"><td style="width:10%;" class="text-c"></c:if>
                            <p style="text-align:center;"><script>document.write(arr[2]);</script></td>
                            <c:if test="${x==0}"><td style="width:10%;" class="text-c"></c:if>
                            <c:if test="${x==1}"><td style="width:10%;" class="text-c"></c:if>
                            <c:if test="${empty  ac.type}" >
                                <p style="text-align:center;">未识别</p>
                            </c:if>
                            <c:if test="${not empty  ac.type}" >
                                <p style="text-align:center;">${ac.type}</p>
                            </c:if>
                            </td>
                            <c:if test="${x==0}"><td style="width:30%;" class="text-c"></c:if>
                            <c:if test="${x==1}"><td style="width:30%;" class="text-c"></c:if>
                            <p style="float:left;width:80%;">${ac.factors}</p>
                            </td>
                            <td class="text-c">
                            <c:if test="${empty type}">
                                <button class="btn btn-primary radius" onClick="addgj('${be.id}')">现场风险辨识</button>
                            </c:if>
                            <c:if test="${empty type}">
                                <button class="btn btn-primary radius" style="margin-top:10px;" onClick="addgjs('${be.id}')">基础风险辨识</button>
                            </c:if>
										    </td>
                                        </tr>
                                        <c:set value="1" var="x"/>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${x == 0}">
                                    <tr>
                                        <td style="width:10%;height:80px;"><p style="text-align:center;">未辨识</p><p style="display:none">${be.id}</p></td>
                                        <td style="width:10%;"><p style="text-align:center;">${be.name }</p></td>
                                        <td style="width:10%;"><p style="text-align:center;">未关联</p></td>
                                        <td style="width:10%;"><p style="text-align:center;">未关联</p></td>
                                        <td style="width:10%;"><p style="text-align:center;">未识别</p></td>
                                        <td style="width:40%;"><p style="text-align:center;">未辨识风险因素</p></td>
										<td style="width:10%;">
                            <c:if test="${empty type}">
                                <button class="btn btn-primary radius" onClick="addgj('${be.id}')">现场风险辨识</button>
                            </c:if>
                            <c:if test="${empty type}">
                                <button class="btn btn-primary radius" style="margin-top:10px;" onClick="addgjs('${be.id}')">基础风险辨识</button>
                            </c:if>
										</td>
                                    </tr>
                                </c:if>
                </c:forEach>
            </c:if>

            <c:if test="${not empty type }">
                <c:forEach items="${dL }" var="be3">
                    <tr>
                        <td class="text-c" rowspan="1">${be3.level1 }</td>
                        <td class="text-c">${be3.level2 }</td>
                        <c:if test="${type eq 1}">
                            <td>${fn:replace(be3.hxys, '!@#', ",") }</td>
                        </c:if>
                        <c:if test="${type eq 2}">
                            <td>${fn:replace(be3.material, '!@#', ",") }</td>
                        </c:if>
                        <c:if test="${type eq 3}">
                            <td>${fn:replace(be3.gy, '!@#', ",") }</td>
                        </c:if>
                        <c:if test="${type eq 4}">
                            <td>${fn:replace(be3.wlbs, '!@#', ",") }</td>
                        </c:if>
                        <c:if test="${type eq 5}">
                            <td>${fn:replace(be3.gwzy, '!@#', ",") }</td>
                        </c:if>
                        <td class="text-c" style="width:90px">
                            <a class="btn-cz" onclick="redit(${be3.id}, ${type })" href="javascript:;" title="选择">选择</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>
</div>

<div id="win-add2" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 760px">
        <div class="modal-content radius">
            <div class="modal-header">
                <h3 class="modal-title">编辑</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body">
                <div class="row cl">
                    <label class="form-label col-xs-4 col-sm-2" style="width: 20%; text-align: right;">系统/车间：</label>
                    <div class="formControls col-xs-8 col-sm-9" style="width: 80%;">
                        <input type="text" id="level1" value="" style="width: 557px" class="input-text required">
                    </div>
                </div>
                <div class="row cl mt-15">
                    <label class="form-label col-xs-4 col-sm-2" style="width: 20%; text-align: right;">工段/班组：</label>
                    <div class="formControls col-xs-8 col-sm-9" style="width: 80%;">
                        <input type="text" id="level2" value="" style="width: 557px" class="input-text required">
                    </div>
                </div>
                <div class="row cl mt-15">
                    <label class="form-label col-xs-4 col-sm-2" style="width: 20%; text-align: right;">岗位/部位：</label>
                    <div class="formControls col-xs-8 col-sm-9" style="width: 80%;">
                        <input type="text" id="level3" value="" style="width: 557px" class="input-text required">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" onclick="save()">确定</button>
                <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>
    </div>
  </div>
</body>
<script type="text/javascript" src="/js/fxgk/tbl-rowspan-reset.js"></script>
<script type="text/javascript">
    //alert("${point}");
    var id = '';
    function edit(id, obj) {
        var p = $(obj).parent();
        window.id = id;
        $("#level1").val(p.find("textarea[name='level1']").val());
        $("#level2").val(p.find("textarea[name='level2']").val());
        $("#level3").val(p.find("textarea[name='level3']").val());
        $("#win-add2").modal("show");
    }

    function reload_() {
        layer.closeAll();
        location.reload();
    }

    function save() {
        var obj = {
            "level3" : $("#level3").val(),
            "level1" : $("#level1").val(),
            "level2" : $("#level2").val(),
            "id" : id
        }
        if(obj.level1 == '') {
            $("#level1").focus();
            return false;
        }
        if(obj.level2 == '') {
            $("#level2").focus();
            return false;
        }
        if(obj.level3 == '') {
            $("#level3").focus();
            return false;
        }
        if(obj.id == '') {
            obj.issys = 1;
        } else {
            obj.issys = 0;
        }
        var i = layer.load();
        $.post(getRootPath() + "/company/safety-system/aCompanyManual-save", obj, function(result) {
            layer.close(i);
            location.reload();
        })
    }

    /*function del(id) {
        layer.confirm("确定删除该记录?", function(r) {
            $.post(getRootPath() + "/company/safety-system/aCompanyManual-save", {
                id : id,
                del : 1
            }, function(result) {
                layer.close(r);
                location.reload();
            })
        })
    }*/
</script>
</html>