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
<style>
body{  
	text-align:center;  
}


.jOrgChart .line {
  height                : 20px;
  width                 : 4px;
}

.jOrgChart .down {
 background-color 		: green;
  margin 				      : 0px auto;
  border-down          : 3px solid green;
}

.jOrgChart .top {
  border-top          : 3px solid green;
}

.jOrgChart .left {
  border-right          : 2px solid green;
}

.jOrgChart .right {
  border-left           : 2px solid green;
}

/* node cell */
.jOrgChart td {
  text-align            : center;
  vertical-align        : top;
  padding               : 0;
}

.jOrgChart .node {
  /*background-color 		: #35363B;*/
  background-color 		  : deepskyblue;
  display               : inline-block;
  width                 : 200px;
  height                : 100px;
  z-index 							: 10;
  margin              	: 0 0px;
}

/* jQuery drag 'n drop */

.drag-active {
  border-style			: dotted !important;
}

.drop-hover {
  border-style			: solid !important;
  border-color 			: #E05E00 !important;
}

a {
            text-decoration: none;
            color: #000000;
            font-size: 12px;
}

.jOrgChart .node {
            width: 120px;
            height: 50px;
            line-height: 15px;
            border-radius: 4px;
            margin: 0 8px;
        }


</style>
</head>
<body>
<div style="margin-left: 5%;" id='jOrgChart'></div>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script src="/js/jquery.jOrgChart.js"></script>
    <script>
    	
      var data = {
          "data": [{
              "id": 1,
              "name": "企业主体信用<br>1111111111111111<br>11111111<br>111111",
              "pid": null,
              "childrens": [{
                      "id": 2,
                      "name": "企业素质",
                      "pid": 1,
                      "childrens": [{
                              "id": 5,
                              "name": "基本信息",
                              "pid": 2,
                              "childrens": [{
                                      "id": 10,
                                      "name": "企业主体信息识别",
                                      "pid": 5,
                                      "childrens": []
                                  },
                                  {
                                      "id": 11,
                                      "name": "企业持续注册时间",
                                      "pid": 5,
                                      "childrens": []
                                  },
                                  {
                                      "id": 12,
                                      "name": "注册资本",
                                      "pid": 5,
                                      "childrens": []
                                  }
                              ]
                          },
                          {
                              "id": 6,
                              "name": "管理认证",
                              "pid": 2,
                              "childrens": [{
                                  "id": 13,
                                  "name": "国际性管理认证",
                                  "pid": 6,
                                  "childrens": []
                              }]
                          }
                      ]
                  },
                  {
                      "id": 3,
                      "name": "履约记录",
                      "pid": 1,
                      "childrens": [{
                              "id": 7,
                              "name": "税务执行情况",
                              "pid": 3,
                              "childrens": [{
                                  "id": 14,
                                  "name": "是否按时缴纳税款",
                                  "pid": 7,
                                  "childrens": []
                              }]
                          },
                          {
                              "id": 8,
                              "name": "网贷情况",
                              "pid": 3,
                              "childrens": [{
                                  "id": 15,
                                  "name": "网贷逾期",
                                  "pid": 8,
                                  "childrens": []
                              }]
                          }
                      ]
                  },
                  {
                      "id": 4,
                      "name": "公共监督",
                      "pid": 1,
                      "childrens": [{
                          "id": 9,
                          "name": "行政处罚",
                          "pid": 4,
                          "childrens": [{
                              "id": 16,
                              "name": "处罚信息",
                              "pid": 9,
                              "childrens": []
                          }]
                      }]
                  }
              ]
          }]
      }

      function showall(menu_list, parent) {
          $.each(menu_list, function (index, val) {
              console.log(val);
              if (val.childrens.length > 0) {
                  var li = $("<li></li>");
                  li.append("<a href='javascript:void(0)' onclick=getOrgId(" + val.id + ");>" + val.name +
                      "</a>").append("<ul></ul>").appendTo(parent);
                  //递归显示
                  showall(val.childrens, $(li).children().eq(1));
              } else {
                  $("<li></li>").append("<a href='javascript:void(0)' onclick=getOrgId(" + val.id + ");>" +
                      val.name + "</a>").appendTo(parent);
              }
          });

      }
      var showlist = $("<ul id='org' style='display:none'></ul>");
      showall(data.data, showlist);
      $("#jOrgChart").append(showlist);
      $("#org").jOrgChart({
          chartElement: '#jOrgChart', //指定在某个dom生成jorgchart
          dragAndDrop: false //设置是否可拖动
      });

      function getOrgId(val) {
          console.log(val);
      }

        
    </script>
    
</body>
</html>