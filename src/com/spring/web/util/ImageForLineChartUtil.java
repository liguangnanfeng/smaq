/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.util;

import ChartDirector.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

/**
 * @Title: ImageForLineChartUtil
 * @Description: 线形图工具类
 * @author FL
 * @date 2016年1月13日 下午2:51:53
 * @version V1.0
 */
public class ImageForLineChartUtil {
    @SuppressWarnings("deprecation")
    public static String execute(TimeSeriesCollection lineDataset, String titleName, String formatStyle,
                                 HttpServletRequest request) throws IOException {
        JFreeChart chart = ChartFactory.createTimeSeriesChart("", "", "", lineDataset, true, true, true);
        // 设置子标题
        TextTitle subtitle = new TextTitle(titleName, new Font("宋体", Font.BOLD, 16));
        chart.addSubtitle(subtitle);
        chart.setAntiAlias(true);
        /*------设置底部的文字(防止乱码)-----------*/
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) plot.getRenderer();
        // X轴为日期格式，这里是专门的处理日期的类，
        SimpleDateFormat format = new SimpleDateFormat(formatStyle);
        DateAxis dateaxis = (DateAxis) plot.getDomainAxis();
        dateaxis.setTickUnit(new DateTickUnit(DateTickUnit.MONTH, 1, format));
        // 设置网格背景颜色
        ValueAxis domainAxis = plot.getDomainAxis();
        domainAxis.setTickLabelFont(new Font("", Font.PLAIN, 12));// 设置x轴坐标上的字体
        domainAxis.setLabelFont(new Font("", Font.PLAIN, 12));// 设置x轴坐标上的标题的字体
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setTickLabelFont(new Font("", Font.PLAIN, 12));// 设置y轴坐标上的字体
        rangeAxis.setLabelFont(new Font("", Font.PLAIN, 12));// 设置y轴坐标上的标题的字体
        rangeAxis.setLowerMargin(0.06d);// 分类轴下（左）边距
        rangeAxis.setUpperMargin(0.14d);// 分类轴下（右）边距,防止最后边的一个数据靠近了坐标轴。
        plot.setBackgroundPaint(Color.white);
        // 设置网格竖线颜色
        plot.setDomainGridlinePaint(Color.pink);
        // 设置网格横线颜色
        plot.setRangeGridlinePaint(Color.pink);
        // 设置曲线图与xy轴的距离
        plot.setAxisOffset(new RectangleInsets(0D, 0D, 0D, 5D));
        // 设置曲线是否显示数据点
        xylineandshaperenderer.setBaseShapesVisible(true);
        // 设置曲线显示各数据点的值
        XYItemRenderer xyitem = plot.getRenderer();
        xyitem.setBaseItemLabelsVisible(true);
        xyitem.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12,
                TextAnchor.BASELINE_CENTER));
        xyitem.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
        xyitem.setBaseItemLabelPaint(new Color(102, 102, 102));// 显示折点数值字体的颜色
        plot.setRenderer(xyitem);
        String filename = ServletUtilities.saveChartAsPNG(chart, 900, 400, null, request.getSession());
        return filename;
    }

    /**
     * @param data 数据
     * @param labels x轴
     * @param yTitle y轴
     * @param chartName 图表名
     * @param imgName 多个图表的名
     * @param request
     * @return
     */
    public static String[] lineChart(List<double[]> data, String[] filter, String[] labels, String yTitle,
            String chartName, String imgName, HttpServletRequest request) {
        // double[] data0 = {42, 49, Chart.NoValue, 38, 64, 56, 29, 41, 44, 57};
        // double[] data1 = {65, 75, 47, 34, 42, 49, 73, Chart.NoValue, 90, 69, 66, 78};
        // double[] data2 = {Chart.NoValue, Chart.NoValue, 25, 28, 38, 20, 22, Chart.NoValue, 25, 33, 30, 24};
        // String[] labels = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep",
        // "Oct", "Nov", "Dec"};
        // Create a XYChart object of size 600 x 360 pixels. Set background color to brushed
        // silver, with a 2 pixel 3D border. Use rounded corners.
        XYChart c = new XYChart(1200, 400, Chart.brushedSilverColor(), Chart.Transparent, 2);
        c.setDefaultFonts("SIMSUN.TTC", "simhei.ttf");
        c.setRoundedFrame();
        // Add a title using 18 pts Times New Roman Bold Italic font. #Set top/bottom margins
        // to 6 pixels.
        TextBox title = c.addTitle(chartName, "宋体", 18);
        title.setMargin2(0, 0, 6, 6);
        // Add a separator line just under the title
        c.addLine(10, title.getHeight(), c.getWidth() - 11, title.getHeight(), Chart.LineColor);
        // Add a legend box where the top-center is anchored to the horizontal center of the
        // chart, just under the title. Use horizontal layout and 10 points Arial Bold font,
        // and transparent background and border.
        LegendBox legendBox = c.addLegend(c.getWidth() / 2, title.getHeight(), false, "宋体", 10);
        legendBox.setAlignment(Chart.TopCenter);
        legendBox.setBackground(Chart.Transparent, Chart.Transparent);
        // Tentatively set the plotarea at (70, 75) and of 460 x 240 pixels in size. Use
        // transparent border and black (000000) grid lines
        c.setPlotArea(70, 75, 460, 240, -1, -1, Chart.Transparent, 0x000000, -1);
        // Set the x axis labels
        c.xAxis().setLabels(labels);
        // Show the same scale on the left and right y-axes
        c.syncYAxis();
        // Set y-axis tick density to 30 pixels. ChartDirector auto-scaling will use this as
        // the guideline when putting ticks on the y-axis.
        c.yAxis().setTickDensity(30);
        // Set all axes to transparent
        c.xAxis().setColors(Chart.Transparent);
        c.yAxis().setColors(Chart.Transparent);
        c.yAxis2().setColors(Chart.Transparent);
        // Set the x-axis margins to 15 pixels, so that the horizontal grid lines can extend
        // beyond the leftmost and rightmost vertical grid lines
        c.xAxis().setMargin(15, 15);
        // Set axis label style to 8pts Arial Bold
        c.xAxis().setLabelStyle("宋体", 8);
        c.yAxis().setLabelStyle("宋体", 8);
        c.yAxis2().setLabelStyle("宋体", 8);
        // Add axis title using 10pts Arial Bold Italic font
        c.yAxis().setTitle(yTitle, "宋体", 10);
        c.yAxis2().setTitle(yTitle, "宋体", 10);
        // Add the first line. The missing data will be represented as gaps in the line (the
        // default behaviour)
        for (int i = 0; i < data.size(); i++) {
            LineLayer layer0 = c.addLineLayer2();
            if (i > 11) {
                layer0.addDataSet(data.get(i), clolr(), filter[i]).setDataSymbol(Chart.GlassSphere2Shape, 11);
            } else {
                layer0.addDataSet(data.get(i), color[i], filter[i]).setDataSymbol(Chart.GlassSphere2Shape, 11);
            }
            layer0.setLineWidth(3);
            layer0.setGapColor(Chart.SameAsMainColor);
        }
        // Add the second line. The missing data will be represented by using dash lines to
        // bridge the gap
        // LineLayer layer1 = c.addLineLayer2();
        // layer1.addDataSet(data1, 0x00ff00, "Atom Synthesizer").setDataSymbol(Chart.GlassSphere2Shape, 11);
        // layer1.setLineWidth(3);
        // layer1.setGapColor(c.dashLineColor(0x00ff00));
        // Add the third line. The missing data will be ignored - just join the gap with the
        // original line style.
        // LineLayer layer2 = c.addLineLayer2();
        // layer2.addDataSet(data2, 0xff6600, "Proton Cannon").setDataSymbol(Chart.GlassSphere2Shape, 11);
        // layer2.setLineWidth(3);
        // layer2.setGapColor(Chart.SameAsMainColor);
        // layout the legend so we can get the height of the legend box
        c.layoutLegend();
        // Adjust the plot area size, such that the bounding box (inclusive of axes) is 15
        // pixels from the left edge, just under the legend box, 16 pixels from the right
        // edge, and 25 pixels from the bottom edge.
        c.packPlotArea(15, legendBox.getTopY() + legendBox.getHeight(), c.getWidth() - 16, c.getHeight() - 25);
        String[] xx = new String[3];
        // Output the chart
        String chart1URL = request.getContextPath() + "/getchart.jsp?" + c.makeSession(request, imgName, Chart.JPG);
        xx[0] = chart1URL;
        // Include tool tip for the chart
        String imageMap1 = c.getHTMLImageMap("clickable", "", "title='Revenue of {dataSetName} in {xLabel}: {value}'");
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

    public static void main(String[] args) {
        System.out.println(clolr());
    }
}
