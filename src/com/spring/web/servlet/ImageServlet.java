package com.spring.web.servlet;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Title: ImageServlet
 * @Description: 图像服务
 * @author FL
 * @date 2016年1月26日 上午11:51:57
 * @version V1.0
 */
@WebServlet("/imageServlet")
public class ImageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private static Integer height = 45;
    
    private static Integer width = 136;
    
    /**
     * 放到session中的key
     */
    public static final String VALIDATE_CODE = "validateCode";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/png");
        // 设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");
        // 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        try {
            // 创建内存图象并获得其图形上下文
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.getGraphics();
            // 产生随机的认证码并画图
            char[] rands = this.generateCheckCode();
            this.drawBackground(g);
            this.drawRands(g, rands);
            // 结束图像的绘制过程，完成图像
            g.dispose();
            // 将当前验证码存入到Session中
            // session.setAttribute("validateNum", new String(rands));
            // 将图像输出到客户端
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(response.getOutputStream());
            encoder.encode(image);
            request.getSession().setAttribute(VALIDATE_CODE, new String(rands));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 产生随机数。
     * 
     * @return
     */
    private char[] generateCheckCode() {
        // 定义验证码的字符表
        String chars = "0123456789";
        char[] rands = new char[4];
        for (int i = 0; i < 4; i++) {
            int rand = (int) (Math.random() * 10);
            rands[i] = chars.charAt(rand);
        }
        return rands;
    }

    /**
     * 画随机数。
     * 
     * @param g
     * @param rands
     */
    private void drawRands(Graphics g, char[] rands) {
        g.setColor(Color.BLACK);
        g.setFont(new Font(null, Font.PLAIN | Font.BOLD, 25));
        // 在不同的高度上输出验证码的每个字符
        Integer x = height / 2;
        Integer y = width / 4;
        g.drawString("" + rands[0], y - 25, x + 10);
        g.drawString("" + rands[1], y * 2 - 25, x + 8);
        g.drawString("" + rands[2], y * 3 - 25, x + 10);
        g.drawString("" + rands[3], y * 4 - 25, x + 8);
        //System.out.println(rands);
    }

    /**
     * 画图片背景。
     * 
     * @param g
     */
    private void drawBackground(Graphics g) {
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, width, height);
        // 随机产生120个干扰点
        for (int i = 0; i < 120; i++) {
            int x = (int) (Math.random() * width);
            int y = (int) (Math.random() * height);
            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            g.setColor(new Color(red, green, blue));
            g.drawOval(x, y, 1, 0);
        }
    }
}
