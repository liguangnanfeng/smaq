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
    <%@ include file="/WEB-INF/inc/kindeditor.inc" %>
    <title>风险分级管控 隐患排查治理智能化平台</title>
    <meta name="keywords" content="风险分级管控   隐患排查治理智能化平台">
    <meta name="description" content="风险分级管控   隐患排查治理智能化平台">
</head>
<script type="text/javascript">
    var id = '${p.id}';
    var userType = ${session_user.userType};
    var x = (userType == 3 || userType == 4) ? '检查' : '执法';

    function save() {

      var dmid= $("#dpid option:selected").val();
        console.log(dmid);
        if(dmid==null){
            alert("请选择部门");
            return;
        }else{
            // 已选择车间
            $.ajax({
                type: "POST",
                url: getRootPath() + '/village/saveCheckMenu3',
                data:{dmid:dmid},
                success: function (result) {
                    if (result.status == 0) {
                        alert('保存成功');
                        window.location.href='${ly }/company/model-list-cx?flag=1&type=1&template=1';
                    } else {
                        alert('保存失败');
                    }
                },

            })

        }

    }


    // 调用这个方法的话,进行赋值
    function save_btn() {
        console.log($("#addpw").val())
        $("#win-add").modal("hide");
    }


</script>
<body>
<nav class="breadcrumb">
    <%-- <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
    <span class="c-gray en">&gt;</span> <span>${(session_user.userType == 3 || session_user.userType == 4) ? '隐患排查治理系统' : '、行政执法系统'}</span>
    <span class="c-gray en">&gt;</span> <span>${(session_user.userType == 3 || session_user.userType == 4) ? '检查人员信息库' : '执法人员管理'}</span> --%>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
</nav>
<div class="page-container">


    <div className="row cl">
        <label className="form-label col-xs-4 col-sm-2"><span className="c-red">*</span>检查部门 :</label>

                                    <span className="select-box inline">
                                       <select id="dpid" name="dpid" class="select" id="statusId" value="">
                                        <c:if test="${not empty map}">
                                            <c:forEach items="${map}" var="entry"  >
                                                <option  value="${entry.value}">${entry.key}</option>
                                            </c:forEach>

                                        </c:if>

                                      </select>
                                    </span>

    </div>
    <div class="row cl">
        <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
            <button onClick="save()" class="btn btn-primary radius" type="button" style="padding: 0 70px;"><i
                    class="Hui-iconfont">&#xe632;</i> 生成检查模版
            </button>
        </div>
    </div>
    </form>
</div>

</body>
</html>