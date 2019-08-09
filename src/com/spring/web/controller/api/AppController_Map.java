package com.spring.web.controller.api;

import com.spring.web.BaseController;
import com.spring.web.dao.ImportPhotoMapper;
import com.spring.web.dao.TMapMapper;
import com.spring.web.model.TCheckItem;
import com.spring.web.model.User;
import com.spring.web.model.request.ImportPhoto;
import com.spring.web.model.request.TMap;
import com.spring.web.result.AppResult;
import com.spring.web.result.AppResultImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
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

    /*map对象*/
    @Autowired
    private TMapMapper tMapMapper;
    @Autowired
    private ImportPhotoMapper importPhotoMapper;

    /*保存图片的路径*/
    private String pathStr;


    /**
     * TODO 保存图片并保存到数据库
     *
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
        if (null == images) {
            return null;
        }

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            images = images.replaceAll(" ", "+");

            byte[] bytes = decoder.decodeBuffer(images.substring(images.indexOf(",") + 1));
            images = images.replace("base64", "");
            for (int i = 0; i < bytes.length; i++) {
                if (bytes[i] < 0) {
                    //调整异常数据
                    bytes[i] += 256;
                }
            }

            String realPath = request.getSession().getServletContext().getRealPath("/");

            String path = "/images/upload/";
            String s = UUID.randomUUID().toString().replaceAll("-", "");

            // 判断是否存在
            File file = new File(realPath + path);
            if (!file.exists() && !file.isDirectory()) {
                System.out.println("不存在");
                file.mkdir();
            }

            // 生成jpeg图片
            String imgFilePath = realPath + path + s + ".jpg";
            // 数据库图片路径
            String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()  + path + s + ".jpg";
            //String filePath = "https://sec.dicarl.com" + path + s + ".jpg";
            //String filePath =  path+s+".jpg";

            OutputStream ops = new FileOutputStream(imgFilePath);
            ops.write(bytes);
            ops.flush();
            ops.close();

            // 根据id进行查询, 是否为保存还是修改
            TMap tMap = tMapMapper.selectByUserId(user.getId());
            //TMap tMap = tMapMapper.selectByUserId(6);
            if (tMap == null) {
                // 表示是新增
                TMap tMap1 = new TMap();
                tMap1.setUserId(user.getId());
                // tMap1.setUserId(6);
                tMap1.setFiles(filePath);
                tMapMapper.insertTMap(tMap1);

            } else {
                tMap.setFiles(filePath);
                tMapMapper.updateMap(tMap);
            }

            ImportPhoto importPhoto = new ImportPhoto();

            importPhoto.setUrl(filePath);
            importPhoto.setUrl1(filePath);
            importPhoto.setUser_id(user.getId());
            importPhoto.setName(user.getUserName());
            importPhoto.setFlag(2);

            if (null == importPhoto){

            }else {
                importPhotoMapper.savePhoto(importPhoto);
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
    public String dropMap(HttpServletRequest request) {
        User user = getLoginUser(request);
        if (user == null) {
            return null;
        }
        try {
            tMapMapper.dropByUserId(user.getId());
            return "删除成功";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * TODO 获取图片的路径
     */
    @ResponseBody
    @RequestMapping("B003")
    public String findMap(HttpServletRequest request) {
        User user = getLoginUser(request);
        if (user == null) {
            return null;
        }
        TMap tMap = tMapMapper.selectByUserId(user.getId());

        if (tMap != null){
            return tMap.getFiles();
        }else {
            return "";
        }
    }

    /**
     * 跳转截图的路径
     */
    @RequestMapping("jietu")
    public String jietu(HttpServletRequest request) throws Exception {
        return "company/safety-system/jietu";
    }




    /**
     * 跳转到地图的截图页面
     */
    @RequestMapping(value = "control-list4")
    public String jumpHtml() {
        return "company/safety-system/control-list4";
    }

    /**
     * 治理方案文件上传的接口
     */
    @RequestMapping("B004")
    public @ResponseBody
    AppResult uploadFile(@RequestParam(value = "file", required = false) MultipartFile file, Integer itemId, HttpServletRequest request) throws IOException {
        AppResult result = new AppResultImpl();
        System.out.println("执行文件上传");
        request.setCharacterEncoding("UTF-8");

        String realPath1 = "/images/upload/";
        String path = null;
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();

            String type = null;
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            if (type != null) {
                if ("TXT".equals(type.toUpperCase()) || "DOCX".equals(type.toUpperCase()) || "DOC".equals(type.toUpperCase()) || "DOTX".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    String realPath = request.getSession().getServletContext().getRealPath("/");
                    String realPath2 = realPath.replaceAll("\\\\", "/");

                    String replace = UUID.randomUUID().toString().replace("-", "");

                    // 自定义的文件名称
                    String trueFileName = /*String.valueOf(System.currentTimeMillis()) +*/ replace + fileName;
                    // 设置存放图片文件的路径
                    path = realPath2 + "images/upload/" + trueFileName;

                    // 判断是否存在
                    File file1 = new File(realPath2 + "images/upload/");
                    if (!file1.exists() && !file1.isDirectory()) {
                        file1.mkdir();
                    }

                    realPath1 += trueFileName;

                    file.transferTo(new File(path));
                    TCheckItem tCheckItem = new TCheckItem();
                    tCheckItem.setId(itemId);
                    tCheckItem.setFileAddress(realPath1);
                    tCheckItemMapper.updateByPrimaryKeySelective(tCheckItem);
                } else {
                    result.setStatus("1");
                    result.setMessage("不是我们想要的文件类型,请按要求重新上传");
                    return result;

                }
            } else {
                result.setStatus("1");
                result.setMessage("文件类型为空");
                return result;

            }
        } else {

            result.setStatus("1");
            result.setMessage("没有找到相对应的文件");

            return result;

        }
        result.setStatus("0");
        result.setMessage("保存成功");
        realPath1.replace("\\", "/");
        result.setData(realPath1);
        return result;

    }


    /**
     * 治理方案文件上传的接口
     */
    @RequestMapping("B005")
    public @ResponseBody
    AppResult uploadFiles(@RequestParam(value = "file", required = false) MultipartFile file, Integer itemId, HttpServletRequest request) throws IOException {
        AppResult result = new AppResultImpl();
        System.out.println("执行文件上传");
        request.setCharacterEncoding("UTF-8");

        String realPath1 = "/images/upload/";
        String path = null;
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();

            String type = null;
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            if (type != null) {
                if ("PDF".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    String realPath = request.getSession().getServletContext().getRealPath("/");
                    String realPath2 = realPath.replaceAll("\\\\", "/");

                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                    String oldname = file.getOriginalFilename();
                    String fileExt = oldname.substring(oldname.lastIndexOf(".") + 1).toLowerCase();
                    String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
                    // 设置存放图片文件的路径
                    path = realPath2 + "images/upload/" + newFileName;
                    // 判断是否存在
                    File file1 = new File(realPath2 + "images/upload/");
                    if (!file1.exists() && !file1.isDirectory()) {
                        file1.mkdir();
                    }
                    realPath1 += newFileName;
                    file.transferTo(new File(path));
                    TCheckItem tCheckItem = new TCheckItem();
                    tCheckItem.setId(itemId);
                    tCheckItem.setFileAddress(realPath1);
                    tCheckItemMapper.updateByPrimaryKeySelective(tCheckItem);
                } else {
                    result.setStatus("1");
                    result.setMessage("请上传pdf格式的文件。");
                    return result;
                }
            } else {
                result.setStatus("1");
                result.setMessage("文件类型为空");
                return result;

            }
        } else {

            result.setStatus("1");
            result.setMessage("没有找到相对应的文件");

            return result;

        }
        result.setStatus("0");
        result.setMessage("保存成功");
        realPath1.replace("\\", "/");
        result.setData(realPath1);
        return result;

    }
}
