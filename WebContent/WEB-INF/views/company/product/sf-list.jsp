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
    <script src="${ly }/js/jquery-migrate-1.2.1.min.js"></script>
    <script src="${ly }/js/jquery.jqprint-0.3.js"></script>
    <script type="text/javascript" src="${ly}/js/H-ui.admin_v3.0/lib/layer/2.4/layer.js"></script>
    <meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
    <meta name="description" content="风险分级管控   隐患排查治理智能化平台">
    <style type="text/css">
        body .dis-ib {
            margin-right: 15px;
        }
    </style>
</head>
<script>
    /*打印功能*/
    function pr_() {

        $("#container").jqprint();

    }
</script>
<body>
<nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
    <span class="c-gray en">&gt;</span> <span>安全生产标准化</span>
    <span class="c-gray en">&gt;</span><span>设备设施管理</span>
    <span class="c-gray en">&gt;</span> <span>安全设施登记台账</span>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px;margin-right: 10px;"
       href="javascript:history.back(-1)" title="返回">返回</a>
</nav>
<div class="page-container">
    <div class="text-c">
        <form action="" method="post">
            <div class="dis-ib">
                <span>名称：</span>
                <input type="text" value="${name }" name="name" class="input-text mb-5 mt-5" style="width:150px;">
            </div>
            <button class="btn btn-success" type="submit">
                <i class="Hui-iconfont">&#xe665;</i> 查询
            </button>
        </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l">
        <a class="btn btn-primary radius" data-title="添加安全设施登记台账" data-href="${ly }/company/sf-add"
           onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont"
                                                                style="font-size:15px;">&#xe600;</i> 添加安全设施登记台账</a>
             <a class="btn btn-primary radius" data-title="打印" data-href="${ly }/company/product/lightning-edit"
                onclick="pr_()" href="javascript:;"><i class="Hui-iconfont"
                                                       style="font-size:15px;">&#xe616;</i> 打印</a>

         <a class="btn btn-primary radius" data-title="批量导入防雷防静电" data-href="${ly }/company/product/lightning-edit"
            onclick="sequipmentImportBoxShow()" href="javascript:;"><i class="Hui-iconfont"
                                                                       style="font-size:15px;">&#xe642;</i>批量导入安全设施登记台账</a>

         <a class="btn btn-primary radius" data-title="防雷防静电模板下载"
            href="${ly}/company/download?filename=安全设施登记台账模板.xlsx&fileurl=${ly}/upload/newsf.xlsx"><i
                 class="Hui-iconfont"
                 style="font-size:15px;">&#xe641;</i>安全设施登记台账模板下载</a>
      </span>
        <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span>
    </div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-bg table-hover table-sort" id="container">
            <thead>
            <tr class="text-c">
                <th width="5%">序号</th>
                <th width="20%">名称</th>
                <th width="15%">型号规格</th>
                <th width="15%">工艺参数</th>
                <th width="15%">数量</th>
                <th width="20%">备注</th>
                <th width="10%">操作</th>
            </tr>
            </thead>
            <tbody>
            <!-- 循环-->
            <c:forEach items="${list }" varStatus="index" var="equip">
                <tr class="text-c">
                    <td>${index.index + 1 }</td>
                    <td>${equip.name }</td>
                    <td>${equip.size }</td>
                    <td>${equip.process_parameters }</td>
                    <td>${equip.amount }</td>
                    <td>${equip.remark }</td>
                    <td>
                        <a style="text-decoration:none"
                           onClick="show_tab('编辑设施登记台账', '${ly }/company/sf-add?id=${equip.id}')" href="javascript:;"
                           title="编辑">查看/编辑</a>
                        <a style="text-decoration:none" onClick="del(${equip.id})" href="javascript:;" title="删除">删除</a>
                    </td>
                </tr>
            </c:forEach>
            <!-- 循环结束 -->
            </tbody>
        </table>
    </div>
</div>

<%--弹窗--%>
<div id="seqipumentImportBox" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">

            <div class="modal-header">
                <h3 class="modal-title">导入设施登记台账</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>

            <div class="modal-body" id="importBox">
                <form id="fm1">
                    <div class="one_txt cl">
                        <div class="text-left" style="float: left;width: 70px;">导入文件：</div>
                        <div class="text-right formControls">
                           <span class="btn-upload form-group">
                               <input type="file" id="file" name="file">
                           </span>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" onclick="sequipmentImportSave()">保存</button>
                <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $('.table-sort').dataTable({
            "aaSorting": [[0, "asc"]],//默认第几个排序
            "bStateSave": false,//状态保存
            "aoColumnDefs": []
        });
    });

    /*删除*/
    function del(id) {
        $.post(getRootPath() + "/company/sf-del", {
            id: id,
        }, function (reuslt) {
            location.reload()
        })
    }


    /*弹窗*/
    function sequipmentImportBoxShow() {
        $("#seqipumentImportBox").modal("show");
    }

    /*上传文件*/
    function sequipmentImportSave() {
        var index = layer.load();

        // 获取文件
        var form = document.querySelector("#fm1");
        var formdata = new FormData(form);
        $.ajax({
            url: getRootPath() + '/company/uoloadSafetyfacilities',   //请求的url地址
            secureuri: false, //一般设置为false
            data: formdata,
            async: false,
            type: "POST",   //请求方式
            processData: false,
            contentType: false,
            dataType: 'json',
            success: function (data) {
                layer.close(index);
                var status = data.status;
                if (status != 1) {
                    layer.alert("导入成功", {}, function (ind) {
                        layer.close(ind);
                        window.location.reload();
                    });
                } else {
                    layer.alert(data.map.message, {}, function (ind) {
                        layer.close(ind);
                        window.location.reload();
                    });
                }
            },
            error: function (data, status, e) {
                layer.close(index);
                alert("导入失败！请检查数据");
            }
        })
    }

</script>
</body>
</html>