package com.spring.web.utils;

import com.spring.web.utils.quartz.WarnMainScheduler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class Sersocket843 implements InitializingBean
{
    protected static final Logger log = Logger.getLogger("R");
    public static Object locker = new Object();
    private ServerSocket s = null;
    
    
    /**
     * 线程池,JDK5 新增
     */
    private static ExecutorService executorService = null;// 线程池

    
    @Override
    public  void afterPropertiesSet() throws Exception {
        try {
            WarnMainScheduler scheduler = new WarnMainScheduler();
            scheduler.schedulerJob();
        } catch (Exception e1) {
            log.error(e1.getMessage(),e1);
        }
       
        /*try {
            if (executorService == null) {
                executorService = Executors.newFixedThreadPool(Runtime
                        .getRuntime().availableProcessors() * 20);
            } else {
                return;
            }

            s = new ServerSocket(843);
            Runnable r = new Server();
            Thread t = new Thread(r);
            t.start();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                    try {
                        ServerSocket serverSocket=new ServerSocket(843);
                        String xml = "<?xml version=\"1.0\"?><cross-domain-policy><site-control permitted-cross-domain-policies=\"all\"/><allow-access-from domain=\"*\" to-ports=\"*\"/></cross-domain-policy>\0";
                        Socket socket=serverSocket.accept();
                        BufferedReader br = new BufferedReader(new InputStreamReader(socket
                                .getInputStream(), "UTF-8"));
                        PrintWriter pw = new PrintWriter(socket.getOutputStream());
                        char[] by = new char[22];
                        br.read(by, 0, 22);
                        String s = new String(by);
                        if (s.equals("<policy-file-request/>")) {
                            System.out.println("接收policy-file-request");
                            pw.print(xml);
                            pw.flush();
                            br.close();
                            pw.close();
                            socket.close();
                        }
                    } catch (Exception e) {
                        log.error(e.getMessage(),e);
                    } 
                }
        }).start();
        
    }
    
    /*public void closeServer() {
        locker = null;
        if (s != null && !s.isClosed()) {
            try {
                s.close();
                System.out.println("socket 停止成功");
            } catch (IOException e) {
                 
            } finally {
                System.out.println("socket 停止....");
            }
        }
    }
    
    class Server implements Runnable {

        @Override
        public void run() {
            try {

                System.out.println("启动ServerSocket..");
                while (locker != null) {
                    if (s == null || s.isClosed()) {
                     
                        continue;
                    }
                    Socket incoming = s.accept();
                    Runnable r = new ThreadClient(incoming);
                    executorService.execute(r);
                     
                }
            } catch (Exception e) {
                 
            }

        }

    }
    
    class ThreadClient implements Runnable {
        private Socket incoming;

        public ThreadClient(Socket s) {
            incoming = s;
        }

        public void run() {
            try {
                System.out.println("处理线程启动");
                InputStream i = incoming.getInputStream();
                OutputStream o = incoming.getOutputStream();
                Scanner s = new Scanner(i);
                PrintWriter p = new PrintWriter(o);
                //这段话就是flash 的 socket 安全策略.做过flex和web交互的同学应该知道
                p.println("<cross-domain-policy> <site-control permitted-cross-domain-policies=\"all\"/> <allow-access-from domain=\"*\" to-ports=\"*\" /></cross-domain-policy> \0");
                p.flush();
 
            } catch (Exception e) {
                 
            } finally {
                try {
                    incoming.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }*/
}
