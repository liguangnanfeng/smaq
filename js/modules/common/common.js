var juicerUtils = {
      /**
       * @description : 模板初始化
       */
      init : function() {
        // 避免去jstl标签冲突
        juicer.set({
          'tag::operationOpen' : '{@',
          'tag::operationClose' : '}',
          'tag::interpolateOpen' : '$<{',
          'tag::interpolateClose' : '}>',
          'tag::noneencodeOpen' : '$$<{',
          'tag::noneencodeClose' : '}>',
          'tag::commentOpen' : '{#',
          'tag::commentClose' : '}'
        });
      },
      /**
       * @description : juicer 内方法绑定，支持{key:value,key:value},key,value
       */
      bingEvent : function() {
        if (!!!arguments || arguments.length <= 0) {
          return;
        }
        if (arguments.length == 1) {
          var eventObj = arguments[0];
          for ( var key in eventObj) {
            var func = eventObj[key];
            if (typeof func !== "function") {
              continue;
            }
            juicer.register(key, eventObj[func]);
          }
        } else if (arguments.length == 2) {
          var key = arguments[0];
          var func = arguments[1];
          if (typeof func !== "function") {
            return;
          }
          juicer.register(key, func);
        }
      }
    };