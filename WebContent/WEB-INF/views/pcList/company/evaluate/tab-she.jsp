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

<link type="text/css" rel="stylesheet" href="${ly }/js/kindeditor/themes/default/default.css" />
    <script type="text/javascript" src="${ly }/js/kindeditor/kindeditor-min.js"></script>
    <script type="text/javascript" src="${ly }/js/kindeditor/lang/zh_CN.js"></script>
<style type="text/css">
body .dis-ib{margin-right:15px;}
.div_btzl{width:100%;font-size:16px;font-weight:bold;margin-bottom:10px;}
.p_zl{width:100%;text-indent:2em;margin:0;padding:0;margin-bottom:8px;}
</style>
<script type="text/javascript">
function showzl(){
  $("#modal-zl").modal("show")}

</script>
</head>
<body>
  <nav class="breadcrumb">
        <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
        <span class="c-gray en">&gt;</span> <span>评价评估系统</span>
        <span class="c-gray en">&gt;</span> <span>评价评估档案</span>
        <span class="c-gray en">&gt;</span> <span>安全设施“三同时”</span>
        <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
    </nav>
    <div class="page-container">
        <div class="cl pd-5 bg-1 bk-gray mt-20">
            <div class="l">
                <!-- <button class="btn btn-primary radius ml-10" type="button" onclick="showzl()"><i class="Hui-iconfont" style="font-size:15px;">&#xe695;</i> 查看资料</button> -->
                <button id="insertfile" class="btn btn-warning radius ml-10" type="button"><i class="Hui-iconfont" style="font-size:15px;">&#xe642;</i> 上传资料</button>
            </div>
            <span class="r">共有数据：<strong>${fn:length(list) }</strong> 条</span>
        </div>
        <div class="mt-20">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
                <thead>
                    <tr class="text-c">
                        <th width="5%">序号</th>
                        <th width="55%">资料名称</th>
                        <th width="20%">上传时间</th>
                        <th width="20%">操作</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- 循环-->
                       <c:forEach items="${list }" varStatus="index" var="list">
                    <tr class="text-c">
                        <td>${index.index+1 }</td>
                        <td>${list.name}</td>
                        <td><fmt:formatDate value="${list.createTime}" pattern="yyyy-MM-dd"/></td>
                        <td>
                            <c:if test="${!empty list.url}">
                            <a style="text-decoration:none" href="${ly}/common/download?filename=${list.name}&fileurl=${list.url}" title="查看资料" target="_blank">查看资料</a>
                            </c:if>
                            <a style="text-decoration:none" onclick="del(${list.id})" href="javascript:;" title="删除">删除</a>
                        </td>
                    </tr>
                    </c:forEach>
                    <!-- 循环结束 -->
                </tbody>
            </table>
        </div>
        <div class="mt-40">
            <img alt="" src="${ly }/images/yan.jpg" />
        </div>
    
    <!-- <div id="modal-zl" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
          <div class="modal-content radius">
              <div class="modal-header">
                  <h3 class="modal-title">资料详情</h3>
                  <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
              </div>
              <div class="modal-body" style="height:450px;overflow: auto;">
                <div class="div_btzl">一、安全预评价</div>
                <p class="p_zl">下列建设项目在进行可行性研究时，生产经营单位应当按照国家规定，进行安全预评价：</p>
                <p class="p_zl">（一）非煤矿矿山建设项目；</p>
                <p class="p_zl">（二）生产、储存危险化学品（包括使用长输管道输送危险化学品，下同）的建设项目；</p>
                <p class="p_zl">（三）生产、储存烟花爆竹的建设项目；</p>
                <p class="p_zl">（四）金属冶炼建设项目；</p>
                <p class="p_zl">（五）使用危险化学品从事生产并且使用量达到规定数量的化工建设项目（属于危险化学品生产的除外，下同）；</p>
                <p class="p_zl">（六）法律、行政法规和国务院规定的其他建设项目。</p>
                <p class="p_zl">上列规定以外的其他建设项目，生产经营单位应当对其安全生产条件和设施进行综合分析，形成书面报告备查。</p>
                <div class="div_btzl">二、设计审查</div>
                <p class="p_zl">生产经营单位在建设项目初步设计时，应当委托有相应资质的设计单位对建设项目安全设施同时进行设计，编制安全设施设计。</p>
                <p class="p_zl">生产经营单位应当向安监部门提出审查申请，得到批准后方可开建设。</p>
                <div class="div_btzl">三、试生产</div>
                <p class="p_zl">建设项目竣工后，根据规定建设项目需要试运行（包括生产、使用，下同）的，应当在正式投入生产或者使用前进行试运行。</p>
                <p class="p_zl">生产、储存危险化学品的建设项目和化工建设项目安全验收评价报告除符合本条第二款的规定外，还应当符合有关危险化学品建设项目的规定。</p>
                <div class="div_btzl">四、安全验收评价报告</div>
                <p class="p_zl">建设项目安全设施竣工或者试运行完成后，生产经营单位应当委托具有相应资质的安全评价机构对安全设施进行验收评价，并编制建设项目安全验收评价报告。</p>
                <p class="p_zl">建设项目安全验收评价报告应当符合国家标准或者行业标准的规定。</p>
                <p class="p_zl">生产、储存危险化学品的建设项目和化工建设项目安全验收评价报告除符合本条第二款的规定外，还应当符合有关危险化学品建设项目的规定。</p>
                <div class="div_btzl">五、竣工验收</div>
                <p class="p_zl">建设项目竣工投入生产或者使用前，生产经营单位应当组织对安全设施进行竣工验收，并形成书面报告备查。安全设施竣工验收合格后，方可投入生产和使用。</p>
                <p class="p_zl">安全监管部门应当按照上列建设项目竣工验收活动和验收结果的监督核查。</p>
              </div>
          </div>
      </div>
    </div> -->
    
    
  </div>
  <script type="text/javascript">
    $(function() {
        $('.table-sort').dataTable({
            "aaSorting": [
                [0, "asc"]
            ], //默认第几个排序
            "bStateSave": false, //状态保存
            searching: false,
            ordering: false,
            "aoColumnDefs": []
        });
    });

    /*删除*/
    function del(id) {
        $.post(getRootPath() + "/company/tables/h1-del", {
            id: id,
        }, function(reuslt) {
            location.reload()
        })
    }
    
    KindEditor.ready(function(K) {

      var editor = K.editor({
          uploadJson: getRootPath() + '/UploadServlet'
      });

    K('#insertfile').click(function() {
            editor.loadPlugin('insertfile', function() {
                        editor.plugin.fileDialog({
                            fileUrl: K('#url').val(),
                            clickFn: function(url, title) {
                              $.post(getRootPath() + "/company/evaluate/tab-she-add", {
                            name: title,
                            isType: 50,
                            url: url,
                            type: 1
                        }, function(result) {
                            location.reload();
                        })
                    }
                });
            });
        });
    })
    </script>
</body>
</html>