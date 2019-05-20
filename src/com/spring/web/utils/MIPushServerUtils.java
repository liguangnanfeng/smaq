
package com.spring.web.utils;

import com.alibaba.fastjson.JSONObject;
import com.spring.web.model.MessageToUser;
import com.spring.web.model.Messages;
import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/** 
* @Title: MIPushServerUtils 
* @Description: TODO(小米推送服务) 
* @version V1.0   
*/
public class MIPushServerUtils {
    
    public static final String ANDROID_PACKAGE_NAME = "g.lanling.shuangmiaovillage";
    
    public static final String ANDROID_APP_SECRET = "FwhyEgITQdZvbKBEAel1JA==";
    
    public static final String IOS_APP_SECRET = "CaEsIjXTSGxzxjLq8LqgAg==";
    
    
    public  void sendMessages(List<MessageToUser> toUsers, String createName, Integer type, Messages msg, Integer environment) throws Exception {
        Sender sender = null;
        //安卓
        if(type.equals(0)){
            Constants.useOfficial();
            sender = new Sender(ANDROID_APP_SECRET);
        }else{
            if(null == environment){
                Constants.useOfficial();
            }else{
                Constants.useSandbox();
            }
            sender = new Sender(IOS_APP_SECRET);
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<String> aliasList = new ArrayList<String>();
        if(null != toUsers && toUsers.size() > 0){
            //安卓设备
            if(type.equals(0)){
                JSONObject object = new JSONObject();
                object.put("messageId", msg.getId());
                object.put("createTime", format.format(msg.getCreateTime()));
                object.put("createName", createName);
                String messagePayload1= object.toJSONString();
                String title1 = msg.getTitle();
                String description1 = msg.getDescr();
                Message message = new Message.Builder()
                        .title(title1)
                        .description(description1).payload(messagePayload1)
                        .restrictedPackageName(ANDROID_PACKAGE_NAME)
                        .notifyType(1)     // 使用默认提示音提示
                        .build();
                for(MessageToUser user : toUsers){
                    aliasList.add(user.getUserId().toString());
                }
                Result result = sender.sendToAlias(message, aliasList, 1); //根据alias，发送消息到指定设备上，不重试。
            }else{
                //ios设备
                Message message = new Message.IOSBuilder()
                        .description(msg.getDescr())
                        .soundURL("default")    // 消息铃声
                        .badge(1)               // 数字角标
                        .category("action")     // 快速回复类别
                        .extra("messageId", msg.getId())  // 自定义键值对
                        .extra("createTime", format.format(msg.getCreateTime()))  // 自定义键值对
                        .extra("createName", createName)  // 自定义键值对
                        .extra("flow_control", "4000")   // 设置平滑推送, 推送速度4000每秒(qps=4000)
                        .build();
                for(MessageToUser user : toUsers){
                    aliasList.add(user.getUserId().toString());
                }
                Result result = sender.sendToAlias(message, aliasList, 1); //根据alias，发送消息到指定设备上，不重试。
            }
        }
   }

}
