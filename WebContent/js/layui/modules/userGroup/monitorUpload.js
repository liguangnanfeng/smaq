//var filesArr = new Array();
var monUploader = (function(){
   var rootPath = getRootPath();
  
   var uploader = WebUploader.create({

    swf: rootPath + '/js/plugins/webuploader/Uploader.swf',

    // 文件接收服务端。
    server: rootPath + '/village/new/ueditorUpload',

    pick: '#to_uploader',
    
    fileSingleSizeLimit : 10 * 1024 * 1024,
    
    auto : true,
    
    accept: {
      title: 'Images',
      extensions: 'jpg,jpeg,png',
      mimeTypes: 'image/jpg,image/jpeg,image/png'
    },
    
    resize: false
  });
   
   uploader.on( 'fileQueued', function( file ) {
     layer.load(2, {
       shade: [0.1,'#fff'] //0.1透明度的白色背景
     });
 });
  
   uploader.on( 'uploadProgress', function( file, percentage ) {
     
     
   });
   
   uploader.on( 'uploadSuccess', function(file,res ) {
     layer.closeAll();
       $("#thumbnail_val").val(res.url);
     
   });
   
   uploader.on( 'uploadError', function( file,reason ) {
     layer.closeAll();
     //alert(reason);
     layer.msg("上传出错，请重试！");
     uploader.removeFile(file);
     return;
   });
  
   uploader.onError = function (code, maxNum, file) {
     layer.closeAll();
       if (code == "F_EXCEED_SIZE") {
           layer.alert("文件过大，不能超过10MB");
       }else if(type == 'Q_TYPE_DENIED')
       {
         var err_tips2 = layer.msg('仅支持上传gif,jpg,jpeg,png格式的图片文件！');
   }else if(type == 'F_DUPLICATE'){
     var err_tips3 = layer.msg('上传的图片重复！');
   }
   };
  
   uploader.on( 'uploadComplete', function( file ) {
       $( '#'+file.id ).find('.progress').fadeOut();
   });
   
  
})();

