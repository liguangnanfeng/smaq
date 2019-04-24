//package com.spring.web.websocket;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpSession;
//import javax.websocket.EndpointConfig;
//import javax.websocket.OnClose;
//import javax.websocket.OnError;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//import javax.websocket.server.ServerEndpoint;
//import org.springframework.web.context.ContextLoader;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.spring.web.bean.ChatMessage;
//import com.spring.web.dao.SocketMapper;
//import com.spring.web.dao.SocketMessageMapper;
//import com.spring.web.model.Socket;
//import com.spring.web.model.SocketMessage;
//
// TODO: 沒有使用
//@ServerEndpoint(value="/websocket", configurator=GetHttpSessionConfigurator.class)
//public class MyWebSocket extends HttpServlet{  
//  
//    private static final long serialVersionUID = -3492732124588476941L;
//    
//    private static SocketMapper socketMapper = null;
//
//    private static SocketMessageMapper socketMessageMapper = null;
//
//    private static Gson gson = new GsonBuilder().create();
//    
//    //记录客服登录信息    商户id 对应一个 客服ids数组
//    private static Map<Integer, List<Integer>> loginMap = new ConcurrentHashMap<Integer, List<Integer>>();
//    
//    //记录MyWebSocket，用于对游客人员发送信息，游客保存在此
//    private static Map<String, List<MyWebSocket>> map = new ConcurrentHashMap<String,List<MyWebSocket>>();
//    
//    //记录MyWebSocket，用于对客服人员发送信息，客服保存在此
//    private static Map<Integer, List<MyWebSocket>> map1 = new ConcurrentHashMap<Integer,List<MyWebSocket>>();
//     
//    private Session session;//与某个客户端的连接会话，需要通过它来给客户端发送数据
//    
//    private HttpSession httpSession;//正常session，用于控制客服登录在线信息（暂不用）
//    
//    private String t;//1 游客端  2客服端
//    
//    private Integer socketId;
//    
//    private Integer serverId;
//    
//    private Integer userId;
//    
//    /**
//     * 连接建立成功调用的方法
//     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
//     */
//    @OnOpen
//    public void onOpen(Session session, EndpointConfig config) throws Exception {
//        if(socketMapper == null) {
//            setSocket();
//        }
//        
//        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
//        this.session = session;
//    	this.t = session.getRequestParameterMap().get("t").get(0);
//    	this.socketId = Integer.valueOf(session.getRequestParameterMap().get("socketId").get(0));
//    	
//    	//游客端
//    	if("1".equals(t)) {
//    	    String sessionid = httpSession.getId();
//    	    List<MyWebSocket> mw = map.get(sessionid);
//    	    if(null == mw) {
//    	        mw = new ArrayList<MyWebSocket>();
//    	    }
//    	    mw.add(this);
//    	    map.put(sessionid, mw);
//    	    
//    	} else {//客服端
//    	    
//    	    Socket socket = socketMapper.selectByPrimaryKey(socketId);
//    	    this.serverId = socket.getServerId();
//    	    this.userId = socket.getUserId();
//    	    List<MyWebSocket> mw = map1.get(serverId);
//            if(null == mw) {
//                mw = new ArrayList<MyWebSocket>();
//            }
//            mw.add(this);
//            map1.put(serverId, mw);
//            
//            //在线处理
//            List<Integer> m = loginMap.get(socket.getUserId());
//            if(null == m) {
//                m = new ArrayList<Integer>();
//                m.add(serverId);
//            } else {
//                if(!m.contains(serverId)) {
//                    m.add(serverId);
//                }
//            }
//            loginMap.put(socket.getServerId(), m);
//            
//    	}
//    	
//    }
//     
//    @OnClose
//    public void onClose(){
//        if("1".equals(t)) {
//            List<MyWebSocket> mw = map.get(session.getId());
//            if(mw != null)
//                mw.remove(this);
//        } else {
//            List<MyWebSocket> mw = map1.get(session.getId());
//            if(mw != null)
//                mw.remove(this);
//            
//            //移除登录
//            List<Integer> m = loginMap.get(userId);
//            if(null != m && m.contains(serverId)) {
//                m.remove(serverId);
//            }
//        }
//    }
//     
//    @OnMessage
//    public void onMessage(String message, Session session) throws Exception {
//        //ChatMessage cm = gson.fromJson(message, ChatMessage.class);
//        if("1".equals(t)) {
//            Socket socket = socketMapper.selectByPrimaryKey(socketId);
//            if(null != socket.getServerId()) {
//                List<MyWebSocket> mw = map1.get(socket.getServerId());
//                for(MyWebSocket m : mw) {
//                    m.sendMessage(message);
//                }
//            }
//        }
//    }
//     
//    @OnError
//    public void onError(Session session, Throwable error){
//        error.printStackTrace();
//    }
//     
//    public void sendMessage(String message) throws IOException{
//        this.session.getBasicRemote().sendText(message);
//        //this.session.getAsyncRemote().sendText(message);
//    }
// 
//    public static void setSocket() {
//		MyWebSocket.socketMapper = (SocketMapper)ContextLoader.getCurrentWebApplicationContext().getBean("socketMapper");
//		MyWebSocket.socketMessageMapper = (SocketMessageMapper)ContextLoader.getCurrentWebApplicationContext().getBean("socketMessageMapper");
//    }
// 
//}  
