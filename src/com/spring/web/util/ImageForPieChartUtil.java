package com.spring.web.util;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;
import ChartDirector.Chart;
import ChartDirector.PieChart;
import ChartDirector.TextBox;

/**
 * @Title: ImageForPieChartUtil
 * @Description: 饼形图工具类
 * @author FL
 * @date 2016年1月13日 下午2:47:44
 * @version V1.0
 */
public class ImageForPieChartUtil {
    @SuppressWarnings("unused")
    public static String execute(DefaultPieDataset piedata, String titleName, HttpServletRequest request)
            throws IOException {
        // 设置数据集
        // DefaultPieDataset piedata=new DefaultPieDataset();
        // piedata.setValue("2013-01:", 10000);
        // piedata.setValue("2013-02:", 8000);
        // piedata.setValue("2013-03:", 11000);
        // piedata.setValue("2013-04:", 7000);
        // piedata.setValue("2013-05:", 9000);
        // 通过工厂类生成JFreeChart对象
        JFreeChart piechar = ChartFactory.createPieChart3D("", piedata, true, true, false);
        // 获取3D对象
        PiePlot3D pieplot3d = (PiePlot3D) piechar.getPlot();
        pieplot3d.setLabelFont(new Font("宋体", 0, 14));
        // 标题
        Font font = new Font("宋体", Font.BOLD, 16);
        TextTitle title = new TextTitle(titleName, font);
        piechar.setTitle(title);
        /*------设置底部的文字(防止乱码)-----------*/
        piechar.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
        // pieplot3d.setExplodePercent("其他",6000);
        pieplot3d.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}{1}({2})", java.text.NumberFormat
                .getNumberInstance(), new java.text.DecimalFormat("0.00%")));
        // 设置背景
        pieplot3d.setBackgroundPaint(Color.white);
        // 设置开始角度
        pieplot3d.setStartAngle(0);
        // 设置方向为”顺时针方向“
        pieplot3d.setDirection(Rotation.CLOCKWISE);
        // 设置透明度，0.5F为半透明，1为不透明，0为全透明
        pieplot3d.setForegroundAlpha(1F);
        pieplot3d.setNoDataMessage("无数据显示");
        String filename = ServletUtilities.saveChartAsJPEG(piechar, 800, 360, request.getSession());
        String url1 = request.getContextPath() + "/servlet/DisplayChart?filename=" + filename;
        return filename;
    }

    /**
     * @param titleName 图图表名
     * @param labels 各项label
     * @param depths1 三位深度
     * @param unit 单位
     * @param imgName 图片名
     * @param request
     * @return
     * @throws IOException
     */
    public static String[] pieChart(String titleName, String[] labels, double[] data, double[] depths1, String unit,
            String imgName, HttpServletRequest request) throws IOException {
        // 设置Chart大小、背景色
        PieChart pc = new PieChart(1200, 400, 0xddddff, -1, 1);
        pc.setPieSize(600, 215, 150);
        // 设置图表标题
        // 只要将文字转成UTF-8编码就可以解决中文乱码问题
        // 另外如果你将程序部署到Linux下就需要copy相应的字体文件，比如simsun.ttc文件到/usr/share/font下
        TextBox t = null;
        pc.setDefaultFonts("SIMSUN.TTC", "simhei.ttf");
        t = pc.addTitle(titleName, "宋体", 14);
        t.setBackground(0xaaaaff); // 字体也要有背景的
        t.setBackground(0xaaaaff, 0xaaffaa); // 需要加边框吗？加上第二个参数就可以了
        t.setHeight(30);
        StringBuffer sb = new StringBuffer("");
        TextBox total = pc.addText(10, 35, sb.toString()); // 加个文字吧，随便显示什么都行了
        total.setFontColor(0x000000);
        total.setFontSize(10);
        pc.setData(data, labels); // 放数据
        pc.set3D2(depths1);
        pc.setLabelLayout(Chart.SideLayout);
        // 每一块饼都得给个说明
        TextBox t1 = pc.setLabelStyle();
        t1.setBackground(Chart.SameAsMainColor, Chart.Transparent, Chart.glassEffect());
        t1.setRoundedCorners(5);
        pc.setLineColor(Chart.SameAsMainColor, 0x000000);
        pc.setStartAngle(225);
        // 最终的img.src
        String url = request.getContextPath() + "/getchart.jsp?" + pc.makeSession(request, imgName);
        // 链接的href，其中变量{dataSetName}和{value}分别对应了data和lables中的值
        String imageMap = pc.getHTMLImageMap("", "", "title='{value}'" + unit);
        String[] xx = new String[3];
        xx[0] = url;
        xx[1] = imageMap;
        return xx;
    }
}
