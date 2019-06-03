package com.spring.web.util;

/**
 * @Title: TableEditUtil
 * @Description: 表格编辑工具类
 * @author FL
 * @date 2016年1月13日 下午3:24:56
 * @version V1.0
 */
public class TableEditUtil {
    /**
     * 表格名称
     */
    public String table_name;
    /**
     * 表格Id
     */
    public String table_id;
    /**
     * 宽度
     */
    public int width;
    /**
     * 高度
     */
    public int hight;
    /**
     * 表格样式
     */
    public String table_class;
    /**
     * tr的主键
     */
    public String tr_id;
    /**
     * 序号
     */
    public int sortNum = -1;
    /**
     * 开始表格
     */
    public String start_table = "";
    /**
     * 结束表格
     */
    public String end_table = "";
    /**
     * 表格主体
     */
    public String table_body = "";

    public TableEditUtil(String table_id) {
        this.table_id = table_id;
    }

    public TableEditUtil(String table_id, String table_name) {
        this.table_id = table_id;
        this.table_name = table_name;
    }

    public TableEditUtil(String table_id, String table_name, String table_class) {
        this.table_id = table_id;
        this.table_name = table_name;
        this.table_class = table_class;
    }

    public TableEditUtil(String table_id, String table_name, String table_class, int width, int hight) {
        this.table_id = table_id;
        this.table_name = table_name;
        this.table_class = table_class;
        this.width = width;
        this.hight = hight;
    }

    /**
     * 添加表；
     */
    public void addTable() {
        StringBuffer sbf = new StringBuffer();
        sbf.append("<table width='" + width + "px'");
        sbf.append(" min-hight='" + hight + "px' cellspacing='0' ");
        sbf.append(" cellpadding='0' border='0' class='tableStyle' id='" + table_id + "'>");
        sbf.append(" <tbody>");
        this.start_table = sbf.toString();
    }

    /**
     * 结束表
     */
    public void endTable() {
        StringBuffer sbf = new StringBuffer();
        sbf.append(" </tbody></table>");
        this.end_table = sbf.toString();
    }

    /**
     * 添加行
     * 
     * @param tr_id
     */
    public void addTr(String tr_id) {
        StringBuffer sbf = new StringBuffer();
        sbf.append(" <tr id=" + tr_id + " >");
        sortNum++;
        this.table_body += sbf.toString();
    }

    /**
     * 添加行
     * 
     * @param tr_id trId
     * @param tr_class cssStyle
     */
    public void addTr(String tr_id, String tr_class) {
        StringBuffer sbf = new StringBuffer();
        sbf.append(" <tr id=" + tr_id + " class=" + tr_class + ">");
        sortNum++;
        this.table_body += sbf.toString();
    }

    /**
     * 结束行
     */
    public void endTr() {
        StringBuffer sbf = new StringBuffer();
        sbf.append(" </tr>");
        this.table_body += sbf.toString();
    }

    /**
     * 添加列头
     * 
     * @param width
     */
    public void addTitleColumn(int width, String titleName) {
        StringBuffer sbf = new StringBuffer();
        sbf.append(" <th width=" + width + "px" + ">");
        sbf.append("<div>" + titleName);
        sbf.append("</div></th>");
        this.table_body += sbf.toString();
    }

    /**
     * 添加列头
     * 
     * @param width
     * @param td_class
     */
    public void addTitleColumn(int width, String td_class, String titleName) {
        StringBuffer sbf = new StringBuffer();
        sbf.append(" <th width=" + width + "px" + " class=" + td_class + " >");
        sbf.append("<div>" + titleName);
        sbf.append("</div></th>");
        this.table_body += sbf.toString();
    }

    /**
     * 添加序号列
     * 
     * @param width
     */
    public void addSortTd(int width) {
        StringBuffer sbf = new StringBuffer();
        sbf.append(" <td width='" + width + "'px>" + sortNum);
        sbf.append(" </td>");
        this.table_body += sbf.toString();
    }

    /**
     * 添加列
     * 
     * @param width
     */
    public void addTd(int width, String content) {
        StringBuffer sbf = new StringBuffer();
        sbf.append(" <td width=" + width + "px" + ">");
        sbf.append(content);
        sbf.append("</td>");
        this.table_body += sbf.toString();
    }

    /**
     * 添加列
     * 
     * @param width
     * @param td_class
     */
    public void addTd(int width, String td_class, String content) {
        StringBuffer sbf = new StringBuffer();
        sbf.append(" <td width=" + width + "px" + " class=" + td_class + " >");
        sbf.append(content);
        sbf.append("</td>");
        this.table_body += sbf.toString();
    }

    /**
     * 获取表
     * 
     * @return
     */
    public String getTableHtml() {
        String table_html = this.start_table + this.table_body + this.end_table;
        return table_html;
    }
}
