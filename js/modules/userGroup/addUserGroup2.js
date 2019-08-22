var addUserGroup = (function(){
  var rootPath = getRootPath();
  var userType = $("#utp").val();
  Array.prototype.remove=function(dx)
  {
    if(isNaN(dx)||dx>this.length){return false;}
    for(var i=0,n=0;i<this.length;i++)
    {
      if(this[i]!=this[dx])
      {
        this[n++]=this[i]
      }
    }
    this.length-=1
  }
  var hasChooseCompany = new Array();
  var info = {
    init : function(){
      juicerUtils.init();
      var pagesType = $("#pagesType").val();
      if(pagesType == 0){
        info.initList();
      }else if(pagesType == 1){
        info.initAdd();
      }

    },

    initAdd: function(){
      if(userType == 7){
        $("#selectDistrict").show();
        $("#selectTown").show();
        $("#selectVillage").show();
        info.getVillageDataList("",7);
      }else if(userType == 6){
        $("#selectTown").show();
        $("#selectVillage").show();
        info.getVillageDataList("",6);
      }else if(userType == 3){
        $("#selectVillage").show();
        info.getVillageDataList("",3);
      }

      $("#toSearch").unbind("click").bind("click",function(){
          $("#currpage").val(0);
        info.getCompanyListByCondition();
      });

      $("#savethis").unbind("click").bind("click",function(){
        info.saveAddCompany();
      });
      $("#btn1").unbind("click").bind("click",function() {
          var page = $("#currpage").val();
          page = parseInt(page);
          if (page > 0)
              page--;
          else
              page = 0;
          info.getCompanyListByCondition();
          $("#currpage").val(page);
      });


      $("#btn2").unbind("click").bind("click",function(){
          var page = $("#currpage").val();
          page = parseInt(page);
          page++;
          info.getCompanyListByCondition();
          $("#currpage").val(page);
      });

      var groupId = $("#groupId").val();
      if("" != groupId){
        info.editGetList(groupId);
      }else{
        info.getCompanyListByCondition();

      }
    },

    initList : function(){
      $("#toAddGroupUser").unbind("click").bind("click",function(){
        location.href = rootPath + "/global/tpAddGroup";
      });

      $("#toSearchGroupName").unbind("click").bind("click",function(){
        var searchGroupName = $.trim($("#searchGroupNameInput").val());
        if("" != searchGroupName){
          searchGroupName = encodeURI(encodeURI(searchGroupName));
        }
        location.href = rootPath+"/village/groupIndex?groupName="+searchGroupName;
      });

      $(".todetail").unbind("click").bind("click",function(){
        var groupId = $(this).attr("core-id");
        var groupName = $(this).attr("core-name");
        info.getGroupDetail(groupId,groupName);
      });

      $(".toedit").unbind("click").bind("click",function(){
        var groupId = $(this).attr("core-id");
        location.href=rootPath+"/village/tpAddGroup?groupId="+groupId;
      });

    },

    editGetList : function(groupId){
      $.ajax({
        url : rootPath + "/village/getGroupDetail",
        type : 'post',
        data : {
          groupId : groupId
        },
        success : function(data){
          if(undefined != data.list && data.list.length > 0){
            hasChooseCompany = data.list;
            info.getCompanyListByCondition();
            if(hasChooseCompany.length > 0){
              var html = '';
              $.each(hasChooseCompany,function(zindexs,zz){
                html += '<em><span style="margin-left:3px;">'+zz.companyName+'</span></em>';
              });
              $("#choosedList").html(html);
            }else{
              $("#choosedList").html('<div style="margin-left:50px;margin-top:30px;">暂无选择的企业</div>');
            }
          }
        }
      });
    },

    getGroupDetail : function(groupId,groupName){
      $.ajax({
        url : rootPath + "/village/getGroupDetail",
        type : 'post',
        data : {
          groupId : groupId
        },
        success : function(data){
          if(undefined != data.list && data.list.length > 0){
            var html = '<div class="listem">';
            $.each(data.list,function(index,dd){
              html += '<em><span style="margin-left:5px;">'+dd.companyName+'</span><em>';
            });
            html += '</div>';
            layer.open({
              type: 1,
              title : groupName+":企业列表",
              skin: 'layui-layer-rim', //加上边框
              area: ['420px', '400px'], //宽高
              content: html
            });
          }
        }
      });
    },

    saveAddCompany : function(){
      var groupName = $.trim($("#groupName").val());
      if("" == groupName){
        layer.msg("请输入群组名称");
        return;
      }
      if(hasChooseCompany.length == 0){
        layer.msg("请先查询并选择企业");
        return;
      }
      $.ajax({
        url : rootPath + "/village/doAddGroup",
        type : 'post',
        data : {
          groupName : encodeURI(encodeURI(groupName)),
          companyList : JSON.stringify(hasChooseCompany),
          groupId : $("#groupId").val()
        },
        success : function(data){
          if(data.status){
            layer.msg("添加成功");
            setTimeout(function(){
              location.href=rootPath + "/village/groupIndex";
            },1500)
          }
        }
      });
    },

    getCompanyListByCondition : function(){
      if($('input[name="allSlected"]').is(':checked')){
        $('input[name="allSlected"]').prop("checked",false);
      }
      var searchValue = $.trim($("#companyName").val());
      if("" != searchValue){
        searchValue = encodeURI(encodeURI(searchValue));
      }
      var districtId = $("#selectDistrict").children("option:selected").val();
      var villageId = $("#selectVillage").children("option:selected").val();
      var townId = $("#selectTown").children("option:selected").val();
      var page = $("#currpage").val();
      $.ajax({
        url : rootPath + '/global/getCompanyByConditionPage',
        type : 'post',
        data : {
          companyName : searchValue,
          districtId : districtId,
          townId : townId,
          villageId : villageId,
          page : page,
        },
        success : function(data){
          $(".toSelectAll").unbind("click").bind("click",function(){
            $(this).parent("em").children("input").click();
          });
          if(undefined != data.list && data.list.length > 0){
            var html = '';
            $("#searchResultInput").val("");
            $.each(data.list,function(index,c){
              $("#searchResultInput").val(JSON.stringify(data.list));
              html += '<em style="cursor:pointer;"><input name="company" core-name="'+c.companyName+'" type="checkbox"';
              if(null != hasChooseCompany && hasChooseCompany.length > 0){
                $.each(hasChooseCompany,function(jndex,hc){
                  if(undefined != hc && hc.userId == c.userId){
                    html += ' checked ';
                  }
                })
              }
              html += 'value="'+c.userId+'"/><span class="namespan">'+c.companyName+"</span></em>";
            });
            $("#hasChooseList").html(html);
            $(".namespan").unbind("click").bind("click",function(){
              $(this).parent("em").children("input").click();
            });
            $('input[name="company"]').unbind("click").bind("click",function(){
              var cuserId = $(this).val();
              if($(this).is(':checked')){
                var obj = {};
                obj.userId = cuserId;
                obj.companyName = $(this).attr("core-name");
                hasChooseCompany.push(obj);
              }else{
                if(hasChooseCompany.length > 0){
                  $.each(hasChooseCompany,function(kndex,hk){
                    if(undefined != hk && hk.userId == cuserId){
                      hasChooseCompany.remove(kndex);
                    }
                  })

                }
              }

              if(hasChooseCompany.length > 0){
                var html = '';
                $.each(hasChooseCompany,function(zindexs,zz){
                  html += '<em><span style="margin-left:3px;">'+zz.companyName+'</span></em>';
                });
                $("#choosedList").html(html);
              }else{
                $("#choosedList").html('<div style="margin-left:50px;margin-top:30px;">暂无选择的企业</div>');
              }
            });

            $('input[name="allSlected"]').unbind("click").bind("click",function(){
              var searchResultVal = $("#searchResultInput").val();
              var searchResultList = new Array();
              if("" != searchResultVal){
                searchResultList = JSON.parse(searchResultVal);
              }
              if($(this).is(':checked')){
                if(hasChooseCompany.length > 0){
                  $.each(searchResultList,function(index,c){
                    var hasInChoose = false;
                    $.each(hasChooseCompany,function(hindex,hc){
                      if(undefined != hc && hc.userId == c.userId){
                        hasInChoose = true;
                      }
                    });
                    if(!hasInChoose){
                      hasChooseCompany.push(c);
                    }
                  });
                }else{
                  hasChooseCompany = searchResultList;
                }

                $('input[name="company"]').each(function(idx,ci){
                  if(!$(ci).is(':checked')){
                    $(ci).prop("checked",true);
                  }
                });

                if(hasChooseCompany.length > 0){
                  var html = '';
                  $.each(hasChooseCompany,function(zindexs,zz){
                    html += '<em><span style="margin-left:3px;">'+zz.companyName+'</span></em>';
                  });
                  $("#choosedList").html(html);
                }
              }else{
                $('input[name="company"]').each(function(idx,ci){
                  if($(ci).is(':checked')){
                    $(ci).prop("checked",false);
                  }
                });
                if(searchResultList.length > 0){
                  if(hasChooseCompany.length > 0){
                    $.each(searchResultList,function(is,srl){
                      if(undefined != srl){
                        var hasInChooseFalse = false;
                        var hasChooseIndex = 0;
                        $.each(hasChooseCompany,function(hs,hhc){
                          if(undefined != hhc && hhc.userId == srl.userId){
                            hasInChooseFalse = true;
                            hasChooseIndex = hs;
                          }
                        });
                        if(hasInChooseFalse){
                          hasChooseCompany.remove(hasChooseIndex);
                        }
                      }
                    });

                  }
                }
              }
              if(hasChooseCompany.length > 0){
                var html = '';
                $.each(hasChooseCompany,function(zindexs,zz){
                  html += '<em><span style="margin-left:3px;">'+zz.companyName+'</span></em>';
                });
                $("#choosedList").html(html);
              }else{
                $("#choosedList").html('<div style="margin-left:50px;margin-top:30px;">暂无选择的企业</div>');
              }
            });
          }else{
            $("#hasChooseList").html('<div style="margin-left:50px;margin-top:30px;">暂未查询到相关企业</div>');
          }
        }
      });
    },

    getVillageDataList : function(userId,userType){
      $.ajax({
        url : rootPath + '/village/getGroupUserList',
        type : 'post',
        data : {
          userId : userId,
          userType : userType
        },
        success : function(data){
          if(undefined != data.list && data.list.length > 0){
            var html = '';
            if(userType == 7){
              html += '<option value="">不限区县</option>';
              $.each(data.list,function(i,dl){
                html += '<option value="'+dl.districtId+'">'+dl.districtName+'</option>';
              });
              $("#selectDistrict").html(html);

              $("#selectDistrict").on("change",function(){
                var currCheckDis = $("#selectDistrict").children("option:selected").val();
                if("" != currCheckDis){
                  info.getVillageDataList(currCheckDis,6);
                }else{
                  var html2 = '<option value="">不限镇</option>';
                  var html3 = '<option value="">不限乡村</option>';
                  $("#selectTown").html(html2);
                  $("#selectVillage").html(html3);
                }
                  $("#currpage").val(0);
                info.getCompanyListByCondition();
              });
            }else if(userType == 6){
              html += '<option value="">不限镇</option>';
              $.each(data.list,function(i,dl){
                html += '<option value="'+dl.townId+'">'+dl.townName+'</option>';
              });
              $("#selectTown").html(html);
              $("#selectTown").on("change",function(){
                var currCheckTow = $("#selectTown").children("option:selected").val();
                if("" != currCheckTow){
                  info.getVillageDataList(currCheckTow,3);
                }else{
                  var html3 = '<option value="">不限乡村</option>';
                  $("#selectVillage").html(html3);
                }
                  $("#currpage").val(0);
                info.getCompanyListByCondition();
              });
            }else if(userType == 3){
              html += '<option value="">不限乡村</option>';
              $.each(data.list,function(i,dl){
                html += '<option value="'+dl.villageId+'">'+dl.villageName+'</option>';
              });
              $("#selectVillage").html(html);
              $("#selectVillage").on("change",function(){
                  $("#currpage").val(0);
                info.getCompanyListByCondition();
              });
            }
          }
        }
      });
    },

  }

  return {
    info : info
  }
})();
$(document).ready(function(){
  addUserGroup.info.init();
})
