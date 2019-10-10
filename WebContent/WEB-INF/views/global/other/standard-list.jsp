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
<script src="js/jquery-3.2.1.min.js"></script>
</head>
<body>
  <nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> <span>首页</span>
    <span class="c-gray en">&gt;</span> <span>安全生产化标准</span>
    <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
      <i class="Hui-iconfont">&#xe68f;</i>
    </a>
  </nav>
   <input type="text"  id="name"><input type="button" value="搜索" id="search">
  <input type="hidden" id="currentpage" value="0"><!--放置是当前的页码, 默认为0-->
  <input type="hidden" id="totalpage" value="0"><!-----存放的是总页码------>
  <div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
      <span class="l">
      </span>
      <span class="r">共有数据：<strong><span id="total"></span></strong> 条</span>
    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort" id="dataTables">
      </table>
      <div style="width:auto;float: right;    margin: 10px 0;">
        <input type="button" value="上一页" id="lastPage" class="btn btn-primary">
        <input type="button" value="下一页" id="nextPage" class="btn btn-primary">
      </div>
    </div>
  </div>
<script type="text/javascript">
$(function() {
  // $('.table-sort').dataTable({
  //   "aaSorting": [[0, "asc"]],//默认第几个排序
  //   "bStateSave": false,//状态保存
  //   searching: false,
  //   ordering: false,
  //   "aoColumnDefs": []
  // });
  //查询共有多少页
  $.ajax({
    type:"POST",
     url:"${ly}/global/getTotalPage",
     success:function(data){
       $("#totalpage").val(data.map.totalPage);//隐藏域
       $("#total").html(data.map.totals);//显示有多少页
       $("#currentpage").val(0);//设置当前页为0
     },
    error:function () {
      alert("发生错误");
    }
  });
  //请求起始数据
    function dj(i){
    var dj=$('.dj'+i).text();
    console.log(i)
    console.log(dj)
    if(dj==="红色"){
    $('.dj'+i).text('1级');
    }
    else if(dj==='黄色'){
    $('.dj'+i).text('2级');
    }
    else if(dj==='橙色'|| dj==='蓝色'){
    $('.dj'+i).text('3级');
    }

    }
  var name = $("#name").val();
  $.ajax({
     type:"POST",
     url:"${ly}/global/getData?page=0&companyName="+name,
     success:function (data) {
          var list = data.map.list; //获取数据元素
           var html='<thead>\n' +
                   '          <tr class="text-c">\n' +
                   '            <th width="5%">序号</th>\n' +
                   '            <th width="20%">企业名称</th>\n' +
                   '            <th width="10%">行业</th>\n' +
                   '            <th width="10%">标准化等级</th>\n' +
                   '            <th width="10%">最近操作时间</th>\n' +
                   '            <th width="15%">查看详情</th>\n' +
                   '          </tr>\n' +
                   '        </thead>\n' +
                   '        <tbody>';
          //alert(data.map.list[0]["name"])
          for(var i=0; i<list.length; i++){
            html += ' <tr class="text-c">';
            html += '<td>'+(i+1)+'</td>';//序号
            html += "<td><a href='/global/move/company?uid="+list[i]['userId']+"' target='_parent'>"+list[i]['name']+"</a></td>";//企业名称
            html += "<td>"+list[i]['industry']+"</td>";//行业
            html += "<td class=dj"+i+">"+list[i]['dlevel']+"</td>";//标准化等级
            html += "<td>"+list[i]['time']+"</td>"; //操作时间
            html += "<td>"+"<a style= 'text-decoration:none' href='${ly }/global/findAll?parentId=0&flag=1&userId="+list[i]['userId']+"' title='查看详情'>查看详情</a></td>";//查看详情
            html += '</tr>';


          }
          html += ' </tbody>\n' +
                  '      </table>';
          $("#dataTables").html(html);
          $("#currentpage").val(0);
            for(let j=0;j<=i-1;j++){
                dj(j)
            }
     },
    error:function () {
      alert("发生错误");
    }
  })
  $("#search").click(function () {
     var name = $("#name").val();
     //查询总页数
    $.ajax({
      type:"POST",
      url:"${ly}/global/getTotalPage?companyName="+name,
      success:function(data){
        $("#totalpage").val(data.map.totalPage);//隐藏域
        $("#total").html(data.map.totals);//显示有多少页
        $("#currentpage").val(0);//设置当前页为0
      },
      error:function () {
        alert("发生错误");
      }
    });
    //加载第一页数据
    $.ajax({
      type:"POST",
      url:"${ly}/global/getData?page=0&companyName="+name,
      success:function (data) {
        var list = data.map.list; //获取数据元素
        var html='<thead>\n' +
                '          <tr class="text-c">\n' +
                '            <th width="5%">序号</th>\n' +
                '            <th width="20%">企业名称</th>\n' +
                '            <th width="10%">行业</th>\n' +
                '            <th width="10%">标准化等级</th>\n' +
                '            <th width="10%">最近操作时间</th>\n' +
                '            <th width="15%">查看详情</th>\n' +
                '          </tr>\n' +
                '        </thead>\n' +
                '        <tbody>';
        //alert(data.map.list[0]["name"])
        for(var i=0; i<list.length; i++){
          html += ' <tr class="text-c">';
          html += '<td>'+(i+1)+'</td>';//序号
          html += "<td><a href='/global/move/company?uid="+list[i]['userId']+"' target='_parent'>"+list[i]['name']+"</a></td>";//企业名称
          html += "<td>"+list[i]['industry']+"</td>";//行业
          html += "<td class=dj"+i+">"+list[i]['dlevel']+"</td>";//标准化等级
          html += "<td>"+list[i]['time']+"</td>"; //操作时间
          html += "<td>"+"<a style= 'text-decoration:none' href='${ly }/global/findAll?parentId=0&flag=1&userId="+list[i]['userId']+"' title='查看详情'>查看详情</a></td>";//查看详情
          html += '</tr>';


        }
        html += ' </tbody>\n' +
                '      </table>';
        $("#dataTables").html(html);
        $("#currentpage").val(0);
        for(let j=0;j<=i-1;j++){
          dj(j)
        }
      },
      error:function () {
        alert("发生错误");
      }
    })
  });
  //上一页
  $("#lastPage").click(function () {
      //获取当前的页码
      var currentPage = parseInt($("#currentpage").val());
      var name = $("#name").val();
      if(currentPage>0){
        currentPage--;
        $.ajax({
          type:"POST",
          url:"${ly}/global/getData?page="+currentPage+"&companyName="+name,
          success:function (data) {
            var list = data.map.list; //获取数据元素
            var html='<thead>\n' +
                    '          <tr class="text-c">\n' +
                    '            <th width="5%">序号</th>\n' +
                    '            <th width="20%">企业名称</th>\n' +
                    '            <th width="10%">行业</th>\n' +
                    '            <th width="10%">标准化等级</th>\n' +
                    '            <th width="10%">最近操作时间</th>\n' +
                    '            <th width="15%">查看详情</th>\n' +
                    '          </tr>\n' +
                    '        </thead>\n' +
                    '        <tbody>';
            for(var i=0; i<list.length; i++){
              html += ' <tr class="text-c">';
              html += '<td>'+parseInt(10*currentPage+i+1)+'</td>';//序号
              html += "<td><a href='/global/move/company?uid="+list[i]['userId']+"' target='_parent'>"+list[i]['name']+"</a></td>";//企业名称
              html += "<td>"+list[i]['industry']+"</td>";//行业
              html += "<td class=dj"+i+">"+list[i]['dlevel']+"</td>";//标准化等级
              html += "<td>"+list[i]['time']+"</td>"; //操作时间
              html += "<td>"+"<a style= 'text-decoration:none' href='${ly }/global/findAll?parentId=0&flag=1&userId="+list[i]['userId']+"' title='查看详情'>查看详情</a></td>";//查看详情
              html += '</tr>';
            }



            html += ' </tbody>\n' +
                    '      </table>';
            $("#dataTables").html(html);
            $("#currentpage").val(currentPage);
    for(let j=0;j<=i-1;j++){
    dj(j)
    }
          },
          error:function () {
            alert("发生错误");
          }
        })
      }
  });
  //下一页
  $("#nextPage").click(function () {
    var currentPage = parseInt($("#currentpage").val());
    var totalPage = parseInt($("#totalpage").val());
    if(currentPage<totalPage-1){
      currentPage = currentPage + 1;
      $.ajax({
        type:"POST",
        url:"${ly}/global/getData?page="+currentPage+"&companyName="+name,
        success:function (data) {
          var list = data.map.list; //获取数据元素
          var html='<thead>\n' +
                  '          <tr class="text-c">\n' +
                  '            <th width="5%">序号</th>\n' +
                  '            <th width="20%">企业名称</th>\n' +
                  '            <th width="10%">行业</th>\n' +
                  '            <th width="10%">标准化等级</th>\n' +
                  '            <th width="10%">最近操作时间</th>\n' +
                  '            <th width="15%">查看详情</th>\n' +
                  '          </tr>\n' +
                  '        </thead>\n' +
                  '        <tbody>';
          for(var i=0; i<list.length; i++){
            html += ' <tr class="text-c">';
            html += '<td>'+parseInt(10*currentPage+i+1)+'</td>';//序号
            html += "<td><a href='/global/move/company?uid="+list[i]['userId']+"' target='_parent'>"+list[i]['name']+"</a></td>";//企业名称
            html += "<td>"+list[i]['industry']+"</td>";//行业
            html += "<td class=dj"+i+">"+list[i]['dlevel']+"</td>";//标准化等级
            html += "<td>"+list[i]['time']+"</td>"; //操作时间
            html += "<td>"+"<a style= 'text-decoration:none' href='${ly }/global/findAll?parentId=0&flag=1&userId="+list[i]['userId']+"' title='查看详情'>查看详情</a></td>";//查看详情
            html += '</tr>';
          }
          html += ' </tbody>\n' +
                  '      </table>';
          $("#dataTables").html(html);
          $("#currentpage").val(currentPage);
    for(let j=0;j<=i-1;j++){
    dj(j)
    }
        },
        error:function () {
          alert("发生错误");
        }
      })
    }
  });
});

</script>
</body>
</html>