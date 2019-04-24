package imgsrv;

import java.io.*;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * 文件上传
 * FTP服务
 */
public class SynImgByFTP {
    //ftp服务器地址
    public String hostname = "58.215.112.118";
    //ftp服务器端口号默认为21
    public Integer port = 21 ;
    //ftp登录账号
    public String username = "Administrator";
    //ftp登录密码
    public String password = "xdcplus.com@2018";
    
    public FTPClient ftpClient = null;
    
    public String videoimg = "/usr/local/tomcat7/webapps/smaq/images/videoimg";
    //public String videoimg = "D:/Program Files/apache-tomcat-7.0.88/webapps/smaq/images/videoimg";
    
    public static String runat = "07:40:00";
    
    /**
     * 初始化ftp服务器
     */
    public void initFtpClient() {
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");
        try {
            //System.out.println("connecting...ftp服务器:"+this.hostname+":"+this.port); 
            ftpClient.connect(hostname, port); //连接ftp服务器
            ftpClient.login(username, password); //登录ftp服务器
            int replyCode = ftpClient.getReplyCode(); //是否成功登录服务器
            if(!FTPReply.isPositiveCompletion(replyCode)){
                System.out.println("connect failed...ftp服务器:"+this.hostname+":"+this.port); 
            }
            System.out.println("connect successfull...ftp服务器:"+this.hostname+":"+this.port); 
            ftpClient.enterLocalPassiveMode();//进入被动模式
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        }catch (MalformedURLException e) { 
        	System.out.println(1);
           e.printStackTrace(); 
        }catch (Exception e) { 
        	System.out.println(2);
           e.printStackTrace(); 
        } 
    }

    /**
    * 上传文件
    * @param pathname ftp服务保存地址
    * @param fileName 上传到ftp的文件名
    *  @param originfilename 待上传文件的名称（绝对地址） * 
    * @return
    */
    public boolean uploadFile( String pathname, String fileName,String originfilename){
        boolean flag = false;
        InputStream inputStream = null;
        try{
            System.out.println("开始上传文件");
            inputStream = new FileInputStream(new File(originfilename));
            initFtpClient();
            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
            CreateDirecroty(pathname);
            ftpClient.makeDirectory(pathname);
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            flag = true;
            System.out.println("上传文件成功");
        }catch (Exception e) {
            System.out.println("上传文件失败");
            e.printStackTrace();
        }finally{
            if(ftpClient.isConnected()){ 
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            } 
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            } 
        }
        return true;
    }
    /**
     * 上传文件
     * @param pathname ftp服务保存地址
     * @param fileName 上传到ftp的文件名
     * @param inputStream 输入文件流 
     * @return
     */
    public boolean uploadFile( String pathname, String fileName,InputStream inputStream){
        boolean flag = false;
        try{
            System.out.println("开始上传文件");
            initFtpClient();
            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
            CreateDirecroty(pathname);
            ftpClient.makeDirectory(pathname);
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            flag = true;
            System.out.println("上传文件成功");
        }catch (Exception e) {
            System.out.println("上传文件失败");
            e.printStackTrace();
        }finally{
            if(ftpClient.isConnected()){ 
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            } 
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            } 
        }
        return true;
    }
    //改变目录路径
     public boolean changeWorkingDirectory(String directory) {
            boolean flag = true;
            try {
                flag = ftpClient.changeWorkingDirectory(directory);
                if (flag) {
                  System.out.println("进入文件夹" + directory + " 成功！");

                } else {
                    System.out.println("进入文件夹" + directory + " 失败！开始创建文件夹");
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return flag;
        }

    //创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
    public boolean CreateDirecroty(String remote) throws IOException {
        boolean success = true;
        String directory = remote + "/";
        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(new String(directory))) {
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            String path = "";
            String paths = "";
            while (true) {
                String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
                path = path + "/" + subDirectory;
                if (!existFile(path)) {
                    if (makeDirectory(subDirectory)) {
                        changeWorkingDirectory(subDirectory);
                    } else {
                        System.out.println("创建目录[" + subDirectory + "]失败");
                        changeWorkingDirectory(subDirectory);
                    }
                } else {
                    changeWorkingDirectory(subDirectory);
                }

                paths = paths + "/" + subDirectory;
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return success;
    }

  //判断ftp服务器文件是否存在    
    public boolean existFile(String path) throws IOException {
            boolean flag = false;
            FTPFile[] ftpFileArr = ftpClient.listFiles(path);
            if (ftpFileArr.length > 0) {
                flag = true;
            }
            return flag;
        }
    //创建目录
    public boolean makeDirectory(String dir) {
        boolean flag = true;
        try {
            flag = ftpClient.makeDirectory(dir);
            if (flag) {
                System.out.println("创建文件夹" + dir + " 成功！");

            } else {
                System.out.println("创建文件夹" + dir + " 失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    
    /** * 下载文件 * 
    * @param pathname FTP服务器文件目录 * 
    * @param filename 文件名称 * 
    * @param localpath 下载后的文件路径 * 
    * @return */
    public  boolean downloadFile(String pathname, String filename, String localpath){ 
        boolean flag = false; 
        OutputStream os=null;
        try { 
            System.out.println("开始下载文件");
            initFtpClient();
            //切换FTP目录 
            ftpClient.changeWorkingDirectory(pathname); 
            FTPFile[] ftpFiles = ftpClient.listFiles(); 
            for(FTPFile file : ftpFiles){ 
                if(filename.equalsIgnoreCase(file.getName())){ 
                    File localFile = new File(localpath + "/" + file.getName()); 
                    os = new FileOutputStream(localFile); 
                    ftpClient.retrieveFile(file.getName(), os); 
                    os.close(); 
                } 
            } 
            ftpClient.logout(); 
            flag = true; 
            System.out.println("下载文件成功");
        } catch (Exception e) { 
            System.out.println("下载文件失败");
            e.printStackTrace(); 
        } finally{ 
            if(ftpClient.isConnected()){ 
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            } 
            if(null != os){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            } 
        } 
        return flag; 
    }
    
    /** * 删除文件 * 
    * @param pathname FTP服务器保存目录 * 
    * @param filename 要删除的文件名称 * 
    * @return */ 
    public boolean deleteFile(String pathname, String filename){ 
        boolean flag = false; 
        try { 
            System.out.println("开始删除文件");
            initFtpClient();
            //切换FTP目录 
            ftpClient.changeWorkingDirectory(pathname); 
            ftpClient.dele(filename); 
            //ftpClient.removeDirectory(filename);
            ftpClient.logout();
            flag = true; 
            System.out.println("删除文件成功");
        } catch (Exception e) { 
            System.out.println("删除文件失败");
            e.printStackTrace(); 
        } finally {
            if(ftpClient.isConnected()){ 
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            } 
        }
        return flag; 
    }
    
    /** * 删除目录 * 
     * @param pathname FTP服务器保存目录 * 
     * @param filename 要删除的目录名称 * 
     * @return */ 
     public boolean deleteDirectory(String pathname, String directory){ 
         boolean flag = false; 
         try { 
             System.out.println("开始删除文件夹");
             initFtpClient();
             //切换FTP目录 
             ftpClient.changeWorkingDirectory(pathname+"/"+directory); 
             FTPFile[] files = ftpClient.listFiles();
             for(FTPFile f : files){
            	 //System.out.println(f.getName());
            	 if(f.isFile()){
            		 ftpClient.dele(f.getName()); 
            	 }
             }
             ftpClient.changeWorkingDirectory(pathname); 
             ftpClient.removeDirectory(directory);
             ftpClient.logout();
             flag = true; 
             System.out.println("删除文件夹成功");
         } catch (Exception e) { 
             System.out.println("删除文件夹失败");
             e.printStackTrace(); 
         } finally {
             if(ftpClient.isConnected()){ 
                 try{
                     ftpClient.disconnect();
                 }catch(IOException e){
                     e.printStackTrace();
                 }
             } 
         }
         return flag; 
     }
     
     
     
     
     /**  同步视频服务器上的图片到本地 
      * @param del 同步并删除* 
      * @return 
     * @throws IOException */
      public  boolean doSynchronous(int del) throws Exception{ 
    	  boolean ret = false;
          OutputStream os=null;
          
          //System.out.println("开始同步");
          initFtpClient();
          //切换到远程图片目录
          ret = ftpClient.changeWorkingDirectory("/SNAPSHOT"); 
          if(!ret){
        	  System.out.println("切换到SNAPSHOT目录失败！");
        	  return false;
          }
          //遍历设备号目录
	      FTPFile[] ids = ftpClient.listFiles(); 
	      for(FTPFile file_id : ids){
	    	  if(!file_id.isDirectory()) continue;
	    	  String id = file_id.getName();
	    	  //System.out.println(file.getName());
	    	  File localfile = new File(videoimg+"/"+id);
	    	  if(!localfile.exists()){
	    		  ret = localfile.mkdir();
	    		  if(!ret) {
	    			  System.out.println("创建本地目录失败："+id);
	    			  continue;
	    		  }
	    	  }
	          ret = ftpClient.changeWorkingDirectory("/SNAPSHOT"+"/"+id); 
	          if(!ret){
	        	  System.out.println("切换到"+"/SNAPSHOT"+"/"+id+"目录失败！");
	        	  continue;
	          }
	          //遍历设备号目录
		      FTPFile[] cids = ftpClient.listFiles(); 
		      for(FTPFile file_cid : cids){
		    	  if(!file_id.isDirectory()) continue;
		    	  String cid = file_cid.getName();
		    	  SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
		          String date = df.format(new Date());// new Date()为获取当前系统时间
		          //date = "20190128";
		          ret = ftpClient.changeWorkingDirectory("/SNAPSHOT"+"/"+id+"/"+cid+"/"+date); 
		          if(!ret){
		        	  System.out.println("切换到"+"/SNAPSHOT"+"/"+id+"/"+cid+"/"+date+"目录失败,或当前的视频截图还未生成！");
		        	  continue;
		          }
		          FTPFile[] files = ftpClient.listFiles(); 
		          if(files.length==0){
		        	  System.out.println("目录为空："+"/SNAPSHOT"+"/"+id+"/"+cid+"/"+date);
		        	  if(del==1){
		        		  ftpClient.changeWorkingDirectory("/SNAPSHOT"+"/"+id+"/"+cid); 
		 	              ftpClient.removeDirectory(date);
		        	  }
		        	  continue;
		          }
			      File localFile_tmp = new File(videoimg + "/" + id+"/"+cid+"_tmp.jpg"); 
			      File localFile = new File(videoimg + "/" + id+"/"+cid+".jpg"); 
	              os = new FileOutputStream(localFile_tmp); 
	              ret = ftpClient.retrieveFile(files[0].getName(), os);//下载本日截图中的一张 
	              if(!ret){
	            	  System.out.println("下载"+"/SNAPSHOT"+"/"+id+"/"+cid+"/"+date+"/"+files[0].getName()+"失败！");
	            	  os.close();
	            	  if(localFile_tmp.exists()) localFile_tmp.delete();
		        	  continue; 
	              }
	              os.close();
	              if(localFile.exists()) localFile.delete();
	              ret = new File(videoimg + "/" + id+"/"+cid+"_tmp.jpg").renameTo(new File(videoimg + "/" + id+"/"+cid+".jpg"));
	              //System.out.println(ret);
	              if(del==1){
	            	  for(FTPFile f : files){
	 	             	 //System.out.println(f.getName());
	 	             	 if(f.isFile()){
	 	             		 ftpClient.dele(f.getName()); 
	 	             	 }
	 	              }
	 	              ftpClient.changeWorkingDirectory("/SNAPSHOT"+"/"+id+"/"+cid); 
	 	              ftpClient.removeDirectory(date);
	              }
		      }
	      }
	      ftpClient.logout(); 
	      //System.out.println("下载文件成功");
          if(ftpClient.isConnected()){    
        	  ftpClient.disconnect();   
          } 
          if(null != os){
        	  os.close();   
          } 
          return true; 
      }
      
      public static void timerRun() {
          // 一天的毫秒数
          long daySpan = 24 * 60 * 60 * 1000;
          // 规定的每天时间15:33:30运行
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd "+runat);
          // 首次运行时间
          try {
              Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sdf.format(new Date()));
              // 如果今天的已经过了 首次运行时间就改为明天
              if (System.currentTimeMillis() > startTime.getTime()){
                  startTime = new Date(startTime.getTime() + daySpan);
              }
              Timer t = new Timer();
              TimerTask task = new TimerTask() {
                  @Override
                  public void run() { 
                    try {
                    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                        System.out.println(df.format(new Date())+" : 定时器执行");// new Date()为获取当前系统时间
                        System.out.println("开始同步。。。");
                        SynImgByFTP ftp =new SynImgByFTP(); 
						ftp.doSynchronous(1);
						System.out.println("同步结束！");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println(e);
					}
                  }
              };
              // 以每24小时执行一次
              t.schedule(task, startTime, daySpan);
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
    
    public static void main(String[] args) throws IOException {
    	System.out.println("启动。。。");
    	System.out.println("开始同步。。。");
        SynImgByFTP ftp =new SynImgByFTP(); 
		try {
			ftp.doSynchronous(0);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println("同步结束！");
		System.out.println("启动每日定时任务，时间设置为："+runat);
    	timerRun();
    }
}