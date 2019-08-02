var messages = (function(){
  
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
  var hasChooseGroup = new Array();
  var info = {
      init : function(){
        juicerUtils.init();
        
        $("#tosubmit").unbind("click").bind("click",function(){
          info.doSendMessage();
        });
        
        info.getUserCompanyInit();
        info.getCompanyListByCondition();
        
        $('input[name="sendType"]').unbind("click").bind("click",function(){
          var currType = $('input[name="sendType"]:checked').val();
          if(currType == 1){
            $("#companyListDiv").hide();
            $("#departListDiv").hide();
            $("#userGroupDiv").show();
            info.getUserGroupList("");
          }else if(currType == 0){
            $("#userGroupDiv").hide();
            $("#departListDiv").hide();
            $("#companyListDiv").show();
            info.getUserCompanyInit();
            info.getCompanyListByCondition();
          }else if(currType == 2){
            if(userType != 4){
              $("#userGroupDiv").hide();
              $("#companyListDiv").hide();
              $("#departListDiv").show();
              info.getDepartList();
            }
          }
        })
        
        $(".toCheck").unbind("click").bind("click",function(){
          $(this).parent("span").children("input").prop("checked",true);
          $(this).parent("span").children("input").click();
        });
      },
      
      getDepartList : function(){
        $.ajax({
          url : rootPath + '/village/getGroupUserList',
          type : 'post',
          data : {
            userType : userType
          },
          success : function(data){
            var searchListArr = new Array();
            if(undefined != data.list && data.list.length > 0){
              if(userType == 7){
                $.each(data.list,function(i,dl){
                  var obj = {};
                  obj.userId = dl.districtId;
                  obj.companyName = dl.districtName;
                  searchListArr.push(obj);
                });
              }else if(userType == 6){
                $.each(data.list,function(i,dl){
                  var obj = {};
                  obj.userId = dl.townId;
                  obj.companyName = dl.townName;
                  searchListArr.push(obj);
                });
              }else if(userType == 3){
                $.each(data.list,function(i,dl){
                  var obj = {};
                  obj.userId = dl.villageId;
                  obj.companyName = dl.villageName;
                  searchListArr.push(obj);
                });
              }
            }
            
            if(searchListArr.length > 0){
              var html = "";
              $.each(searchListArr,function(i,sl){
                html += '<em style="cursor:pointer;"><input name="depart" core-name="'+sl.companyName+'" type="checkbox"';
                if(null != hasChooseCompany && hasChooseCompany.length > 0){
                  $.each(hasChooseCompany,function(jndex,hc){
                    if(undefined != hc && hc.userId == sl.userId){
                      html += ' checked ';
                    }
                  })
                }
                html += 'value="'+sl.userId+'"/><span class="namespan4">'+sl.companyName+"</span></em>";
              });
              $("#canChooseDepart").html(html);
              $(".namespan4").unbind("click").bind("click",function(){
                $(this).parent("em").children("input").click();
              });
              
              $('input[name="depart"]').unbind("click").bind("click",function(){
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
                  $.each(hasChooseCompany,function(zindexs,c){
                    html += '<em><span class="" core-userid="'+c.userId+'" >'+c.companyName+'</span> </em>';
                  });
                  $("#hasSelectCompanyList").html(html);
                }else{
                  $("#hasSelectCompanyList").html('<div style="margin-left:50px;margin-top:30px;">暂无选择的企业或部门</div>');
                }
              });
            }else{
              $("#canChooseDepart").html('<div style="margin-left:50px;margin-top:30px;">暂无可选择的部门<div>');
            }
          }
        });
      },
      
      doSendMessage : function(){
        
        var title = $.trim($("#titleInput").val());
        var desc = $.trim($("#descInput").val());
        var content = UE.getEditor('editor').getContent();
        if("" == title){
          layer.msg("标题不能为空！");
          return;
        }
        if("" == desc){
          layer.msg("简介不能为空！");
          return;
        }
        if(hasChooseCompany.length == 0){
          layer.msg("请先选择要推送的企业");
          return;
        }
        var files = filesArr;
        var hasSend = false;
        var messageId;
        layer.load(0, {
          shade: [0.1,'#fff'] //0.1透明度的白色背景
        });
        $.ajax({
          url : rootPath + "/village/doAddMessage",
          type : 'post',
          async : false,
          data : {
            title : encodeURI(encodeURI(title)),
            desc : encodeURI(encodeURI(desc)),
            content : content,
            companyList : JSON.stringify(hasChooseCompany),
            files : JSON.stringify(files),
            envrionment : $('input[name="envrionment"]:checked').val()
          },
          success : function(data){
            if(data.status){
              hasSend = true;
              messageId = data.messageId;
            }else{
              layer.msg("推送失败，请重试！");
            }
          },error:function(data){
            layer.closeAll();
            layer.msg("推送失败，请重试！");
          }
        });
        
        if(hasSend){
          info.send(title,messageId);
        }
      },
      
      send :function(message,messageId) {
        
        try{
          $.each(hasChooseCompany,function(i,hc){
            var newmessage=message+"|"+hc.userId+"|"+messageId
            websocket.send(newmessage);
          })
        }catch(e){
          
        }
        
        layer.closeAll();
        layer.msg("发送成功");
        setTimeout(function(){
          location.href= rootPath+"/global/sendMessageList";
        },1000)
    },
      
      getUserCompanyInit : function(){
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
          info.getCompanyListByCondition();
        });
      },
      
      getUserGroupList : function(groupName){
        $.ajax({
          url : rootPath + "/village/getUserGroupUserList",
          type : 'post',
          data : {
            groupName : groupName
          },
          success : function(data){
            if(undefined != data.list && data.list.length > 0){
              var html = '';
              $.each(data.list,function(index,c){
                html += '<em><input name="group" core-name="'+c.groupName+'" type="checkbox"';
                if(null != hasChooseGroup && hasChooseGroup.length > 0){
                  $.each(hasChooseGroup,function(jndex,hc){
                    if(undefined != hc && hc.groupId == c.id){
                      html += ' checked ';
                    }
                  })
                }
                html += 'value="'+c.id+'"/><span class="namespan3">'+c.groupName+"</span></em>";
              });
              $("#canChooseGroup").html(html);
              $(".namespan3").unbind("click").bind("click",function(){
                $(this).parent("em").children("input").click();
              });
              $('input[name="group"]').unbind("click").bind("click",function(){
                var cugroupId = $(this).val();
                if($(this).is(':checked')){
                  var obj = {};
                  obj.groupId = cugroupId;
                  obj.groupName = $(this).attr("core-name");
                  hasChooseGroup.push(obj);
                  info.getGroupUserById(cugroupId,0);
                  
                }else{
                  if(hasChooseGroup.length > 0){
                    $.each(hasChooseGroup,function(kndex,hk){
                      if(undefined != hk && hk.groupId == cugroupId){
                        hasChooseGroup.remove(kndex);
                        info.getGroupUserById(cugroupId,1);
                      }
                    })
                    
                  }
                }
              });
            }else{
              $("#canChooseGroup").html('<div style="margin-left:50px;margin-top:30px;">暂未查询到企业群组</div>');
            }
          }
            
        });
      },
      
      getGroupUserById : function(groupId,type){
        $.ajax({
          url : rootPath + "/village/getGroupDetail",
          type : 'post',
          data : {
            groupId : groupId
          },
          success : function(data){
            if(undefined != data.list && data.list.length > 0){
              if(type == 0){//新增
                if(hasChooseCompany.length > 0){
                  $.each(data.list,function(i,dt){
                    var hasIn = false;
                    $.each(hasChooseCompany,function(j,hc){
                      if(undefined != hc && hc.userId == dt.userId){
                        hasIn = true;
                      }
                    });
                    if(!hasIn){
                      hasChooseCompany.push(dt);
                    }
                  });
                }else{
                  hasChooseCompany = data.list;
                }
              }else if(type == 1){//取消
                var hasSelectd = new Array();
                if(hasChooseGroup.length > 0){
                  $.ajax({
                    url : rootPath + "/village/getHasChooseCompanyList",
                    type : 'post',
                    async : false,
                    data : {
                      groupList : JSON.stringify(hasChooseGroup)
                    },
                    success : function(data){
                      if(undefined != data.list && data.list.length > 0){
                        hasSelectd = data.list;
                      }
                    }
                  });
                }
                
                
                if(hasChooseCompany.length > 0){
                  $.each(data.list,function(i,dt){
                    var hasIn = false;
                    var hasIndex = 0;
                    var otherGroupHas = false;
                    $.each(hasChooseCompany,function(j,hc){
                      if(undefined != hc && hc.userId == dt.userId){
                        if(hasSelectd.length > 0){
                          $.each(hasSelectd,function(kk,hsd){
                            if(undefined != hsd && hsd.userId == dt.userId){
                              otherGroupHas = true;
                            }
                          });
                        }
                        hasIn = true;
                        hasIndex = j;
                      }
                    });
                    if(hasIn && !otherGroupHas){
                      hasChooseCompany.remove(hasIndex);
                    }
                  });
                }
              }
              
              if(hasChooseCompany.length > 0){
                var html = '';
                $.each(hasChooseCompany,function(index,c){
                  html += '<em><span class="" core-userid="'+c.userId+'" >'+c.companyName+'</span> </em>';
                  $("#hasSelectCompanyList").html(html);
                });
              }else{
                $("#hasSelectCompanyList").html('<div style="margin-left:50px;margin-top:30px;">暂未选择部门或企业</div>');
              }
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
                  info.getCompanyListByCondition();
                });
              }else if(userType == 3){
                html += '<option value="">不限乡村</option>';
                $.each(data.list,function(i,dl){
                  html += '<option value="'+dl.villageId+'">'+dl.villageName+'</option>';
                });
                $("#selectVillage").html(html);
                $("#selectVillage").on("change",function(){
                  info.getCompanyListByCondition();
                });
              }
            }
          }
        });
      },
      
      getCompanyListByCondition : function(){
        var searchValue = $.trim($("#companyName").val());
        if("" != searchValue){
          searchValue = encodeURI(encodeURI(searchValue));
        }
        var districtId = $("#selectDistrict").children("option:selected").val();
        var villageId = $("#selectVillage").children("option:selected").val();
        var townId = $("#selectTown").children("option:selected").val();
        $.ajax({
          url : rootPath + '/village/getCompanyByCondition',
          type : 'post',
          data : {
            companyName : searchValue,
            districtId : districtId,
            townId : townId,
            villageId : villageId
          },
          success : function(data){
            if($('input[name="allSlected"]').is(':checked')){
              $('input[name="allSlected"]').prop("checked",false);
            }
            $(".toSelectAll").unbind("click").bind("click",function(){
              $(this).parent("em").children("input").click();
            });
            $("#searchResultInput").val("");
            if(undefined != data.list && data.list.length > 0){
              $("#searchResultInput").val(JSON.stringify(data.list));
              var html = '';
              $.each(data.list,function(index,c){
                html += '<em style="cursor:pointer;"><input name="company" core-name="'+c.companyName+'" type="checkbox"';
                if(null != hasChooseCompany && hasChooseCompany.length > 0){
                  $.each(hasChooseCompany,function(jndex,hc){
                    if(undefined != hc && hc.userId == c.userId){
                      html += ' checked ';
                    }
                  })
                }
                html += 'value="'+c.userId+'"/><span class="namespan2">'+c.companyName+"</spam></em>";
              });
              $("#hasChooseList").html(html);
              $(".namespan2").unbind("click").bind("click",function(){
                $(this).parent("em").children("input").click();
              });
              $('input[name="company"]').unbind("click").bind("click",function(){
                var cuserId = $(this).val();
                if($(this).is(':checked')){
                  var obj = {};
                  obj.userId = cuserId;
                  obj.companyName = $(this).attr("core-name");
                  if(hasChooseCompany.length > 0){
                    var hasSelectdStatus = false;
                    $.each(hasChooseCompany,function(jndex,hc){
                      if(undefined != hc && hc.userId == cuserId){
                        hasSelectdStatus = true;
                      }
                    })
                    if(!hasSelectdStatus){
                      hasChooseCompany.push(obj);
                    }
                  }else{
                    hasChooseCompany.push(obj);
                  }
                }else{
                  if(hasChooseCompany.length > 0){
                    var haseSelect2 = new Array();
                    if(hasChooseGroup.length > 0){
                      $.ajax({
                        url : rootPath + "/village/getHasChooseCompanyList",
                        type : 'post',
                        async : false,
                        data : {
                          groupList : JSON.stringify(hasChooseGroup)
                        },
                        success : function(data){
                          if(undefined != data.list && data.list.length > 0){
                            haseSelect2 = data.list;
                          }
                        }
                      });
                    }
                    $.each(hasChooseCompany,function(kndex,hk){
                      if(undefined != hk && hk.userId == cuserId){
                        var cinGroup = false;
                        if(haseSelect2.length > 0){
                          $.each(haseSelect2,function(zzz,hs2){
                            if(undefined != hs2 && hs2.userId == cuserId){
                              cinGroup = true;
                            }
                          })
                        }
                        if(!cinGroup){
                          hasChooseCompany.remove(kndex);
                        }else{
                          layer.msg("该企业在群组里，需取消群组才能删除！");
                        }
                      }
                    })
                    
                  }
                }
                
                if(hasChooseCompany.length > 0){
                  var html = '<span class="listem">';
                  $.each(hasChooseCompany,function(index,c){
                    html += '<em><span class="" core-userid="'+c.userId+'" >'+c.companyName+'</span></em> ';
                    $("#hasSelectCompanyList").html(html);
                  });
                  html += "</span>";
                }else{
                  $("#hasSelectCompanyList").html('<div style="margin-left:50px;margin-top:30px;">暂未选择部门或企业</div>');
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
                    $("#hasSelectCompanyList").html(html);
                  }
                }else{
                  if(hasChooseGroup.length == 0){
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
                    
                    if(hasChooseCompany.length > 0){
                      var html = '';
                      $.each(hasChooseCompany,function(zindexs,zz){
                        html += '<em><span style="margin-left:3px;">'+zz.companyName+'</span></em>';
                      });
                      $("#hasSelectCompanyList").html(html);
                    }else{
                      $("#hasSelectCompanyList").html('<div style="margin-left:50px;margin-top:30px;">暂未选择部门或企业</div>');
                    }
                  }else{
                    layer.msg("已选择群组的情况下，不支持取消全选功能！");
                    $('input[name="allSlected"]').prop("checked",true);
                  }
                }
               });
            
            }else{
              $("#hasChooseList").html('<div style="margin-left:50px;margin-top:30px;">暂未查询到相关企业</div>');
            }
          }
        });
      }
      
  }
  
  return {
    info : info
  }
  
})();

$(function(){
  messages.info.init();
})