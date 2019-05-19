
package com.spring.web.utils;

import com.spring.web.controller.WebSocketDangerController;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

@ClientEndpoint
public class SocketClient {

    private Session session;
    @OnOpen
    public void onOpen(Session session) throws IOException {
        this.session = session;
    }

    @OnMessage
    public void onMessage(String message) {
    }

    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }
    /**
     * 连接关闭调用的方法
     * @throws Exception 
     */
    @OnClose
    public void onClose() throws Exception{
    }

    /**
     * 关闭链接方法
     * @param message
     * @throws IOException
     */
    public void closeSocket() throws IOException{
        this.session.close();
    }

    /**
     * 发送消息方法。
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException{
        String sendUserno = message.split("[|]")[1];
        // String sendMessage = message.split("[|]")[0];
         String messageId = message.split("[|]")[2];
         try {
             if (WebSocketDangerController.webSocketSet.get(sendUserno) != null) {
                 WebSocketDangerController.webSocketSet.get(sendUserno).sendMessage(messageId);
             } else {
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
       // this.session.getBasicRemote().sendText(message);
    }
    //启动客户端并建立链接
    public void start(String uri) {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
            this.session = container.connectToServer(SocketClient.class, URI.create(uri));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}