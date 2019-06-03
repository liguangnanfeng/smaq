package com.spring.web.util;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;

public class ListToExcelUtil {
    /***************************************************************************
     * @param fileName 文件名
     * @param Title 标题
     * @param listContent 数据集合
     * @return
     */
    public final static String exportExcel(String fileName, String[] Title, List<Object> listContent,
            HttpServletResponse response) {
        String result = "系统提示：Excel文件导出成功！";
        // 以下开始输出到EXCEL
        try {
            // 定义输出流，以便打开保存对话框______________________begin
            OutputStream os = response.getOutputStream();// 取得输出流
            response.reset();// 清空输出流
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(fileName.getBytes("GB2312"), "ISO8859-1"));
            // 设定输出文件头
            response.setContentType("application/msexcel");// 定义输出类型
            // 定义输出流，以便打开保存对话框_______________________end
            /** **********创建工作簿************ */
            WritableWorkbook workbook = Workbook.createWorkbook(os);
            /** **********创建工作表************ */
            WritableSheet sheet = workbook.createSheet("Sheet1", 0);
            /** **********设置纵横打印（默认为纵打）、打印纸***************** */
            jxl.SheetSettings sheetset = sheet.getSettings();
            sheetset.setProtected(false);
            /** ************设置单元格字体************** */
            WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
            WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            /** ************以下设置三种单元格样式，灵活备用************ */
            // 用于标题居中
            WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
            wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
            wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
            wcf_center.setWrap(false); // 文字是否换行
            // 用于正文居左
            WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
            wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
            wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
            wcf_left.setWrap(false); // 文字是否换行
            /** ***************以下是EXCEL开头大标题，暂时省略********************* */
            // sheet.mergeCells(0, 0, colWidth, 0);
            // sheet.addCell(new Label(0, 0, "XX报表", wcf_center));
            /** ***************以下是EXCEL第一行列标题********************* */
            for (int i = 0; i < Title.length; i++) {
                sheet.addCell(new Label(i, 0, Title[i], wcf_center));
            }
            /** ***************以下是EXCEL正文数据********************* */
            Field[] fields = null;
            int i = 1;
            for (Object obj : listContent) {
                fields = obj.getClass().getDeclaredFields();
                int j = 0;
                for (Field v : fields) {
                    v.setAccessible(true);
                    if ("serialVersionUID".equals(v.getName())) { // 序列化属性不要
                        continue;
                    }
                    Object va = v.get(obj);
                    if (va == null) {
                        va = "";
                    }
                    sheet.addCell(new Label(j, i, va.toString(), wcf_left));
                    j++;
                }
                i++;
            }
            /** **********将以上缓存中的内容写到EXCEL文件中******** */
            workbook.write();
            /** *********关闭文件************* */
            workbook.close();
        } catch (Exception e) {
            result = "系统提示：Excel文件导出失败，原因：" + e.toString();
            System.out.println(result);
            e.printStackTrace();
        }
        return result;
    }

    // 输出TXT
    public static void writeToTxt(HttpServletResponse response, String title, String content) throws Exception {
        // 设定输出文件头
        response.addHeader("Content-Disposition", "attachment;filename=" + title + ".txt");// filename指定默认的名字
        response.setContentType("text/plain");// 一下两行关键的设置
        BufferedOutputStream buff = null;
        ServletOutputStream outSTr = null;
        try {
            outSTr = response.getOutputStream();// 建立
            buff = new BufferedOutputStream(outSTr);
            buff.write(content.getBytes("UTF-8"));
            buff.flush();
            buff.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                buff.close();
                outSTr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
