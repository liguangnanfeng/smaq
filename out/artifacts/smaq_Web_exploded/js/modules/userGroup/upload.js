var filesArr = new Array();
var doUploader = (function(){
   var rootPath = getRootPath();
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
   var $list = $("#showFilesResult");
  
   var uploader = WebUploader.create({

    swf: rootPath + '/js/plugins/webuploader/Uploader.swf',

    // 文件接收服务端。
    server: rootPath + '/village/new/upload',

    pick: '#toUploadFiles',
    
    fileSingleSizeLimit : 30 * 1024 * 1024,
    
    auto : true,
    
    resize: false
  });
   
   uploader.on( 'fileQueued', function( file ) {
     if(filesArr.length < 3){
       $list.append( '<div id="' + file.id + '" class="item">' +
           '<h4 class="info">' + file.name + '</h4>' +
           '<p class="state">等待上传...</p>' +
       '</div>' );
     }else{
       layer.msg("最多上传三个附件！");
       return;
     }
 });
  
   uploader.on( 'uploadProgress', function( file, percentage ) {
     var $li = $( '#'+file.id ),
         $percent = $li.find('.progress .progress-bar');

     // 避免重复创建
     if ( !$percent.length ) {
         $percent = $('<div class="progress progress-striped active">' +
           '<div class="progress-bar" role="progressbar" style="width: 0%">' +
           '</div>' +
         '</div>').appendTo( $li ).find('.progress-bar');
     }

     $li.find('p.state').text('上传中');

     $percent.css( 'width', percentage * 100 + '%' );
     
   });
   
   uploader.on( 'uploadSuccess', function(file,res ) {
     $( '#'+file.id ).find('p.state').text('已上传');
     if(filesArr.length < 3){
       var obj = {};
       obj.name = res.title;
       obj.url = res.url;
       obj.id = file.id;
       filesArr.push(obj);
       $( '#'+file.id ).find('p.state').append('<a class="del" href="javascript:void(0);" style="float:right;" core-id="'+file.id+'">删除</a>');
       $(".del").unbind("click").bind("click",function(){
         var coreid = $(this).attr("core-id");
         $( '#'+coreid ).remove();
         $.each(filesArr,function(i,fa){
           if(fa.id == coreid){
             filesArr.remove(i);
           }
         })
       });
     }
     
   });
  
   uploader.onError = function (code, maxNum, file) {
       if (code == "F_EXCEED_SIZE") {
           layer.alert("文件过大，不能超过30MB");
       }
   };
  
   uploader.on( 'uploadComplete', function( file ) {
       $( '#'+file.id ).find('.progress').fadeOut();
   });
   
  
})();

