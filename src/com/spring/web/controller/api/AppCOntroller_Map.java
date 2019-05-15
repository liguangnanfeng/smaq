package com.spring.web.controller.api;

import com.spring.web.BaseController;
import com.spring.web.dao.TMapMapper;
import com.spring.web.model.User;
import com.spring.web.model.request.TMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * @Author: 桃红梨白
 * @Date: 2019/05/15 10:11
 * 添加 地图功能
 */
@Controller
@RequestMapping("api/map")
public class AppCOntroller_Map extends BaseController {

    /**map*/
    @Autowired
    private TMapMapper tMapMapper;

    /**
     * TODO 保存图片并保存到数据库
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("B001")
    public String saveMap(Model model, HttpServletRequest request){

        User user = getLoginUser(request);

        String image = request.getParameter("images");

        if(null == image){
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            String images  = image.replaceAll("", "+");
            byte[] bytes = decoder.decodeBuffer(images.replace("data:image/jpg;base64", ""));
            image= image.replace("base64", "");
            for (int i = 0; i < bytes.length; i++) {
                if(bytes[i]<0){
                    //调整异常数据
                    bytes[i]+= 256;
                }
            }

            String realPath = request.getSession().getServletContext().getRealPath("/");

            String path= "/images/upload/";
            String s = UUID.randomUUID().toString();
            // 生成jpeg图片
            String imgFilePath =realPath+path+s+".jpg";
            // 数据库图片路径
            String filePath = path+s+".jpg";
            OutputStream ops = new FileOutputStream(imgFilePath);
            ops.write(bytes);
            ops.flush();
            ops.close();

            // 根据id进行查询, 是否为保存还是修改
            TMap tMap = tMapMapper.selectByUserId(user.getId());
            if(tMap==null){
                // 表示是新增
                TMap tMap1 = new TMap();
                tMap1.setUserId(user.getId());
                tMap1.setFiles(filePath);

                tMapMapper.insertTMap(tMap1);

            }else{
                //表示数据库里面有数据 那就是修改

                tMap.setFiles(filePath);
                tMapMapper.updateMap(tMap);

            }

            return filePath;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * TODO 删除图片
     */
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
    @RequestMapping("B003")
    public String findMap(HttpServletRequest request){

        User user = getLoginUser(request);

        if(user==null){
            return null;
        }
        TMap tMap = tMapMapper.selectByUserId(user.getId());
        if(tMap==null){
            return null;
        }

        return tMap.getFiles();
    }


}
