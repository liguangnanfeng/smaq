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
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
    <meta http-equiv="expires" content="0">
    <%@ include file="/WEB-INF/inc/back-header.inc"%>
    <title>安全分级管控   隐患排查治理智能化信息平台</title>
    <meta name="keywords" content="安全分级管控   隐患排查治理智能化信息平台">
    <meta name="description" content="安全分级管控   隐患排查治理智能化信息平台">
    <style type="text/css">
        body .dis-ib{margin-right:15px;}
        .listem>* {
            float: left;
        }
        .listem em {
            font-size: 12px;
            padding: 0 5px;
            margin: 0 5px 5px 0;
            border-radius: 1px;
            background-color: #f7f7f7;
            height: 22px;
            line-height: 21px;
            box-sizing: border-box;
            font-style: unset;
        }
    </style>
</head>
<body>
<nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
    <span class="c-gray en">&gt;</span> <span>群组</span>
    <span class="c-gray en">&gt;</span> <span>新增群组</span>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
</nav>

<div class="page-container">
    <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2"><em style="color:#FF0000;margin-right: 5px;">*</em>群组名称：</label>
        <div class="formControls col-xs-8 col-sm-9">
            <input type="text" id="groupName" placeHolder="请输入群组名称"  maxLength="20" value="${groupName }" style="width:350px" class="input-text required"  >
        </div>

    </div>
    <br/>
    <div class="row cl">
        <label class="form-label col-xs-4 col-sm-2"><em style="color:#FF0000;margin-right: 5px;">*</em>企业范围选择：</label>
        <div class="formControls col-xs-8 col-sm-9">
            <select id="selectDistrict" class="input-text" style="display:none;width:100px;">
                <option value="">不限区县</option>
                <%-- <c:forEach items="${list }" var="dl">
                    <option value="${dl.districtId }">${dl.districtName }</option>
                </c:forEach> --%>
            </select>
            <select id="selectTown" class="input-text" style="display:none;width:100px;">
                <option value="">不限镇</option>
                <%-- <c:forEach items="${list }" var="dl">
                    <option value="${dl.townId }">${dl.townName }</option>
                </c:forEach> --%>
            </select>
            <select id="selectVillage" class="input-text" style="display:none;width:100px;">
                <option value="">不限乡村</option>
                <%-- <c:forEach items="${list }" var="dl">
                    <option value="${dl.villageId }">${dl.villageName }</option>
                </c:forEach> --%>
            </select>
            <input type="text" id="companyName" placeHolder="请输入查询企业名称" value="" style="width:200px" class="input-text required"  >
            <a href="javascript:void(0);" class="btn btn-primary radius" id="toSearch" style="background-color: #ddd;border-color: #ddd;" onclick="search()">查询</a>
        </div>
        <input type="hidden" id="currpage" value="1">
        <div class="formControls col-xs-8 col-sm-9" >
            <div style="width:49%;float:left;">
                <span>企业列表：<em style="font-style: unset;"><input type="checkbox" name="allSlected"/><span style="cursor:pointer;" class="toSelectAll">全选</span></em></span>
                <div id="hasChooseList" class="listem" style="height:300px;overflow:auto;"></div>
            </div>
            <div style="width:49%;float:left;margin-left:10px;">
                <span>已选择企业列表：</span>
                <div style="height:300px;overflow:auto;" class="listem" id="choosedList">
                    <div style="margin-left:50px;margin-top:30px;">暂无选择的企业</div>
                </div>
            </div>
            <input type="button" value="上一頁"  id="btn1">&nbsp;&nbsp;&nbsp;<input type="button" value="下一頁" id="btn2">
        </div>
        <div class="formControls col-xs-8 col-sm-9" style="text-align:center;">
            <a href="javascript:void(0)" id="savethis" class="btn btn-primary radius" style="width:80px;">保存</a>
            <a href="<c:url value='/global/groupIndex' /> " class="btn btn-primary radius" style="background-color: #ddd;border-color:#ddd;width:80px;margin-left:20px;">取消</a>
        </div>
    </div>
</div>
<input type="hidden" id="utp" value="${utp }"/>
<input type="hidden" id="pagesType" value="1"/>
<input type="hidden" id="groupId" value="${groupId }"/>
<input type="hidden" id="searchResultInput" />
<script type="text/javascript" src="${ly}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${ly}/js/util/utils.js"></script>
<script type="text/javascript" src="${ly }/js/plugins/juicer/juicer.js"></script>
<script type="text/javascript" src="${ly }/js/modules/common/common.js"></script>
<script type="text/javascript" src="${ly }/js/modules/userGroup/addUserGroup2.js"></script>
</body>
</html>