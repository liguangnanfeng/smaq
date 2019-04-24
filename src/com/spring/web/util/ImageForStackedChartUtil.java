package com.spring.web.util;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StackedBarRenderer3D;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.TextAnchor;
import ChartDirector.BarLayer;
import ChartDirector.Chart;
import ChartDirector.XYChart;

/**
 * @Title: ImageForStackedChartUtil
 * @Description: 堆积图工具类
 * @author FL
 * @date 2016年1月13日 下午3:19:10
 * @version V1.0
 */
public class ImageForStackedChartUtil {
    @SuppressWarnings("deprecation")
    public static String execute(CategoryDataset dataset, String titleName, String xName, String yName,
            HttpServletRequest request) throws IOException {
        // 设置数据集
        /*
         * double[][] data = new double[][] {{1310, 1220, 1110, 1000},{720, 700, 680, 640},{1130, 1020, 980, 800}, {440, 400, 360, 300}}; String[] rowKeys = {"科目一", "科目二","科目三",
         * "科目四"}; String[] columnKeys = {"2013-03", "2013-06", "2013-09", "2013-12"}; CategoryDataset dataset = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
         */
        JFreeChart chart = ChartFactory.createBarChart3D("", "" + xName, "" + yName, dataset, PlotOrientation.VERTICAL,
                true, true, false);
        CategoryPlot plot = chart.getCategoryPlot();
        // 标题
        Font font = new Font("宋体", Font.BOLD, 16);
        TextTitle title = new TextTitle(titleName, font);
        chart.setTitle(title);
        // 设置网格背景颜色
        plot.setBackgroundPaint(Color.white);
        // 设置网格竖线颜色
        plot.setDomainGridlinePaint(Color.pink);
        CategoryAxis domainAxis = plot.getDomainAxis();// (柱状图的x轴)
        domainAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 12));// 设置x轴坐标上的字体
        domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));// 设置x轴上的标题的字体
        ValueAxis valueAxis = plot.getRangeAxis();// (柱状图的y轴)
        valueAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 12));// 设置y轴坐标上的字体
        valueAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));// 设置y轴坐标上的标题的字体
        /*------设置底部的文字(防止乱码)-----------*/
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
        // 设置网格横线颜色
        plot.setRangeGridlinePaint(Color.pink);
        // 显示每个柱的数值，并修改该数值的字体属性
        // BarRenderer3D renderer = new BarRenderer3D();
        // 堆积图
        StackedBarRenderer3D renderer = new StackedBarRenderer3D();
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        // 默认的数字显示在柱子中，通过如下两句可调整数字的显示
        // 注意：此句很关键，若无此句，那数字的显示会被覆盖，给人数字没有显示出来的问题
        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.CENTER));
        // 设置负值柱图数值的显示位置
        renderer.setNegativeItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.CENTER));
        renderer.setItemLabelAnchorOffset(10D);
        // 设置每个地区所包含的平行柱的之间距离
        renderer.setItemMargin(0.3);
        plot.setRenderer(renderer);
        // 设置地区、销量的显示位置
        // 将下方的“肉类”放到上方
        // plot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
        // 将默认放在左边的“销量”放到右方
        // plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        String filename = ServletUtilities.saveChartAsPNG(chart, 960, 400, null, request.getSession());
        // String url1=request.getContextPath()+"/servlet/DisplayChart?filename="+filename;
        // model.addAttribute("url1", url1);
        // model.addAttribute("filename", filename);
        return filename;
    }

    /**
     * @param data 数据
     * @param labels x轴栏目
     * @param lbName 科目
     * @param yName y轴文案
     * @param charName 图表title
     * @param imgName 表名
     * @param request
     * @return
     * @throws IOException
     */
    public static String[] stackedChart(List<double[]> data, String[] labels, String[] lbName, String yName,
            String charName, String imgName, HttpServletRequest request) throws IOException {
        // The data for the bar chart
        // double[] data0 = {100, 125, 245, 147, 67};
        // double[] data1 = {85, 156, 179, 211, 123};
        // double[] data2 = {97, 87, 56, 267, 157};
        // String[] labels = {"Mon", "Tue", "Wed", "Thur", "Fri"};
        // Create a XYChart object of size 540 x 375 pixels
        XYChart c = new XYChart(1200, 400);
        c.setDefaultFonts("SIMSUN.TTC", "simhei.ttf");
        // Set the plotarea at (100, 40) and of size 280 x 240 pixels
        c.setPlotArea(100, 40, 950, 300);
        // Add a legend box at (400, 100)
        c.addLegend(1060, 90);
        // Add a title to the chart using 18 pts Times Bold Italic font
        c.addTitle(charName, "宋体", 18);
        c.yAxis().setTitle(yName).setFontAngle(0);
        // Set the labels on the x axis
        c.xAxis().setLabels(labels);
        // Add a stacked bar layer and set the layer 3D depth to 8 pixels
        BarLayer layer = c.addBarLayer2(Chart.Stack, 8);
        for (int i = 0; i < data.size(); i++) {
            if (i > 11) {
                layer.addDataSet(data.get(i), clolr(), lbName[i]);
            } else {
                layer.addDataSet(data.get(i), color[i], lbName[i]);
            }
        }
        // Enable bar label for the whole bar
        layer.setAggregateLabelStyle();
        // Enable bar label for each segment of the stacked bar
        layer.setDataLabelStyle();
        // Output the chart
        String chart1URL = request.getContextPath() + "/getchart.jsp?" + c.makeSession(request, imgName);
        // Include tool tip for the chart
        String imageMap1 = c.getHTMLImageMap("", "", "title='{dataSetName} on {xLabel}: {value}'");
        String[] xx = new String[3];
        xx[0] = chart1URL;
        xx[1] = imageMap1;
        return xx;
    }

    private static int[] color = {0xff0000, 0x00ff00, 0xff6600, 0x0000ff, 0x00ff66, 0x6600ff, 0x66ff00, 0xff0066,
            0x6666ff, 0xff6666, 0x66ff66, 0xff66ff};

    private static int clolr() {
        String r, g, b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();
        r = r.length() == 1 ? "0" + r : r;
        g = g.length() == 1 ? "0" + g : g;
        b = b.length() == 1 ? "0" + b : b;
        return Integer.valueOf(r + g + b, 16);
    }
}
