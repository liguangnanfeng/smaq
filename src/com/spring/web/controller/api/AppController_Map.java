package com.spring.web.controller.api;

import com.spring.web.BaseController;
import com.spring.web.dao.TMapMapper;
import com.spring.web.model.User;
import com.spring.web.model.request.TMap;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

=======
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
>>>>>>> bb9d2e59f9102daf143f984963e2dbfe16b7792f
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: 桃红梨白
 * @Date: 2019/05/15 10:11
 * 添加 地图功能
 * 添加ip地址,反向代理 获取的是nginx的地址
 */
@Controller
@RequestMapping("api/map")
@PropertySource("classpath:resources/resources.properties")
public class AppController_Map extends BaseController {

    /**map对象*/
    @Autowired
    private TMapMapper tMapMapper;

    private String pathStr;

    /**
     * TODO 保存图片并保存到数据库
     * @param request 请求
     * @return 返回到地址
     */
    @ResponseBody
    @RequestMapping("B001")
    public String saveMap(HttpServletRequest request) throws UnknownHostException {
        // 获取服务器的地址

        InetAddress address = InetAddress.getLocalHost();
        User user = getLoginUser(request);
        String images = request.getParameter("images");
        if(null == images){
            return null;
        }

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            images  = images.replaceAll(" ", "+");

            byte[] bytes = decoder.decodeBuffer(images.substring(images.indexOf(",") + 1));
            images = images.replace("base64", "");
            for (int i = 0; i < bytes.length; i++) {
                if(bytes[i]<0){
                    //调整异常数据
                    bytes[i]+= 256;
                }
            }

            String realPath = request.getSession().getServletContext().getRealPath("/");

            String path= "/images/upload/";
            String s = UUID.randomUUID().toString().replaceAll("-", "");

            // 判断是否存在
            File file =new File(realPath+path);
            if  (!file .exists()  && !file .isDirectory())
            {
                System.out.println("//不存在");
                file .mkdir();
            }

            // 生成jpeg图片
            String imgFilePath =realPath+path+s+".jpg";
            // 数据库图片路径
            //String filePath =  InetAddress.getLocalHost().getHostAddress()+":"+ request.getLocalPort()+path+s+".jpg";
            String filePath =  "https://sec.dicarl.com"+path+s+".jpg";
            //String filePath =  path+s+".jpg";

            OutputStream ops = new FileOutputStream(imgFilePath);
            ops.write(bytes);
            ops.flush();
            ops.close();

            // 根据id进行查询, 是否为保存还是修改
              TMap tMap = tMapMapper.selectByUserId(user.getId());
            //TMap tMap = tMapMapper.selectByUserId(6);
            if(tMap==null){
                // 表示是新增
                TMap tMap1 = new TMap();
                tMap1.setUserId(user.getId());
               // tMap1.setUserId(6);
                tMap1.setFiles(filePath);
                tMapMapper.insertTMap(tMap1);

            }else{
                tMap.setFiles(filePath);
                tMapMapper.updateMap(tMap);
            }
            return tMapMapper.selectByUserId(user.getId()).getFiles();
           //return tMapMapper.selectByUserId(6).getFiles();

        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        }

    }

    /**
     * TODO 删除图片
     */
    @ResponseBody
    @RequestMapping("B002")
    public String dropMap( HttpServletRequest request){
        User user = getLoginUser(request);
        if(user==null){
            return null;
        }
        try {
            tMapMapper.dropByUserId(user.getId());
            return "删除成功";
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /**
     * TODO 获取图片的路径
     */
    @ResponseBody
    @RequestMapping("B003")
    public String findMap(HttpServletRequest request){
        User user = getLoginUser(request);
      if(user==null){
            return null;
        }
        TMap tMap = tMapMapper.selectByUserId(user.getId());
        //TMap tMap = tMapMapper.selectByUserId(6);
        if(tMap==null){
            return null;
        }
        return tMap.getFiles();
    }

<<<<<<< HEAD

=======
>>>>>>> bb9d2e59f9102daf143f984963e2dbfe16b7792f
    /**
     * 跳转图片的路径
     */
    @RequestMapping("jietu")
    public String jietu(Model model, HttpServletRequest request) throws Exception {
        return "company/safety-system/jietu";
    }

<<<<<<< HEAD
=======

>>>>>>> bb9d2e59f9102daf143f984963e2dbfe16b7792f
}
