/*package com.spring.web.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.jmesa.view.html.component.HtmlColumn;
import org.jmesa.worksheet.editor.RowNumberWorksheetEditor;
import com.spring.web.exception.LlApplicationException;
import com.spring.web.exception.LlSystemException;
import com.spring.web.ibatis.DynamicDBValues;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.Subject;
import com.spring.web.model.User;

 *//**
 * @Title: XMLOperateUtil
 * @Description: XML操作工具类
 * @author FL
 * @date 2016年1月13日 下午3:38:27
 * @version V1.0
 */
/*
public class XmlOperateUtil {
 *//**
 * 生成临时处理XML文件名称
 */
/*
private static String XMLNAME = "XMLNAME.xml";
 *//**
 * 当前操作用户信息
 */
/*
private User user;
private List<String> subjectNums = new ArrayList<String>();
 *//**
 * 是否有排位
 */
/*
private String flag_pw;
 *//**
 * 当前数据索引
 */
/*
private Integer index;
 *//**
 * 当前页码
 */
/*
private Integer pageNum;
 *//**
 * 信息总条数
 */
/*
private Integer totalNum;
 *//**
 * 最大显示条数
 */
/*
private Integer maxRows;
private String comName;
 *//**
 * 操作
 */
/*
private Document document;
 *//**
 * 公司企业查询信息
 */
/*
public static Map<String, Document> COMPANYINFOLIST = new HashMap<String, Document>();
private XMLOutputter xmlOutputter = new XMLOutputter(XMLFormatSet());

 *//** 
 *  
 *   
 */
/*
public XmlOperateUtil() {
  super();
}

 *//**
 * 初始化科目值
 * 
 * @param subjects
 */
/*
public XMLOperate(List<Subject> subjects) {
 for (Subject subject : subjects) {
     subjectNums.add(subject.getSubjectNum());
 }
}

 *//**
 * 读取数据到内存
 * 
 * @param sourcePath
 * @throws LlApplicationException
 * @throws LlSystemException
 */
/*
public void writeXMLToMap(String sourcePath) throws LlApplicationException {
 SAXBuilder saxBuilder = new SAXBuilder();
 File file = new File(ConstantsUtil.SUBJECTVALUE_PUTOUT_TEMP + sourcePath);
 if (!file.exists()) {
     throw new LlApplicationException(MessageConstantsUtil.XML_NOT_EXIST_EXCEPTION);
 }
 try {
     document = saxBuilder.build(file);
     // XMLOperate.COMPANYINFOLIST.put(user.getUsername()+"_xml", document);
 } catch (Exception e) {
     // TODO: handle exception
 }
}

 *//**
 * 读取数据到内存
 * 
 * @param dbValues
 * @param list
 * @return
 */
/*
public void writeXMLToMap(List<DynamicDBValues> dbValues) {
 this.setTotalNum(dbValues.size()); // 设置数据总数
 if (user != null) {
     XMLNAME = "putOut_" + System.currentTimeMillis() + "_" + user.getUsername() + ".xml";
 }
 Element rootElement = new Element("data");
 document = new Document(rootElement);
 Element childrenElement = null;
 int temp = 0;
 for (DynamicDBValues values : dbValues) {
     Element companyData = new Element("companyInfo");
     companyData.setAttribute("id", values.getString("ID"));
     childrenElement = new Element("dataID");
     childrenElement.setText(String.valueOf(temp++));
     companyData.addContent(childrenElement);
     childrenElement = new Element("name");
     childrenElement.setText(values.getString("NAME"));
     companyData.addContent(childrenElement);
     for (String str : subjectNums) {
         childrenElement = new Element(str);
         if (!"null".equals(values.getString(str))) {
             childrenElement.setText(values.getString(str));
         } else {
             childrenElement.setText("");
         }
         companyData.addContent(childrenElement);
     }
     if (flag_pw != null && !"null".equals(values.getString("PW"))) {
         childrenElement = new Element("排号");
         childrenElement.setText(values.getString("PW"));
         companyData.addContent(childrenElement);
     }
     rootElement.addContent(companyData);
 }
 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
 LoggerUtil.cLog.info("wirte to String start ...");
 try {
     xmlOutputter.output(document, byteArrayOutputStream);
 } catch (FileNotFoundException e) {
     e.printStackTrace();
 } catch (IOException e) {
     e.printStackTrace();
 }
 LoggerUtil.cLog.info("wirte to String end ...");
 COMPANYINFOLIST.put(user.getUsername() + "_xml", document);
 document = null;
}

 *//**
 * XML格式设置
 * 
 * @return
 */
/*
private Format XMLFormatSet() {
 Format format = Format.getPrettyFormat();
 format.setEncoding("GB2312");
 return format;
}

 *//**
 * xml分页查询
 * 
 * @throws LlApplicationException
 */
/*
@SuppressWarnings("rawtypes")
public List<DynamicDBValues> queryFromXMLPage() throws LlApplicationException {
 List<DynamicDBValues> list = new ArrayList<DynamicDBValues>();
 int begin = this.getIndex() == 0 ? this.getIndex() : this.getIndex() + 1;
 int end = this.getIndex() == 0 ? this.getIndex() + this.getMaxRows() : this.getIndex() + this.getMaxRows() + 1;
 String queryStr = "/data/companyInfo[dataID>=" + begin + "][dataID<" + end + "]";
 if (comName != null && !"".equals(comName)) {
     queryStr = "/data//companyInfo[contains(name,'" + comName + "')]";
     // queryStr = "/data/companyInfo[name="+comName+"]";
 }
 // String map = COMPANYINFOLIST.get(XMLNAME);
 List<Element> elements = xPathExpOperate(queryStr);
 DynamicDBValues dbValues;
 for (Element element : elements) {
     dbValues = new DynamicParameter();
     dbValues.put("ID", element.getAttributeValue("id"));
     dbValues.put("NAME", element.getChildText("name"));
     for (String str : subjectNums) {
         dbValues.put(str, element.getChildText(str));
     }
     if (flag_pw != null) {
         dbValues.put("PW", element.getChildText("排号"));
     }
     list.add(dbValues);
 }
 return list;
}

 *//**
 * 获取记录总数
 * 
 * @return
 * @throws LlApplicationException
 */
/*
@SuppressWarnings("rawtypes")
public Integer queryResultCount() throws LlApplicationException {
 List<DynamicDBValues> list = new ArrayList<DynamicDBValues>();
 String queryStr = "/data/companyInfo";
 if (comName != null && !"".equals(comName)) {
     queryStr = "/data//companyInfo[contains(name,'" + comName + "')]";
     // queryStr = "/data/companyInfo[name="+comName+"]";
 }
 // String map = COMPANYINFOLIST.get(XMLNAME);
 List<Element> elements = xPathExpOperate(queryStr);
 DynamicDBValues dbValues;
 for (Element element : elements) {
     dbValues = new DynamicParameter();
     dbValues.put("ID", element.getAttributeValue("id"));
     dbValues.put("NAME", element.getChildText("name"));
     for (String str : subjectNums) {
         dbValues.put(str, element.getChildText(str));
     }
     list.add(dbValues);
 }
 return elements.size();
}

public List<HtmlColumn> getHtmlColumn() throws LlApplicationException {
 List<HtmlColumn> list = new ArrayList<HtmlColumn>();
 HtmlColumn htmlColumn_X = new HtmlColumn("rowNumber");
 htmlColumn_X.setCellEditor(new RowNumberWorksheetEditor());
 htmlColumn_X.setTitle("序号");
 list.add(htmlColumn_X);
 HtmlColumn htmlColumn_C = new HtmlColumn("NAME");
 htmlColumn_C.setTitle("企业名称");
 htmlColumn_C.setWidth("260px");
 list.add(htmlColumn_C);
 String queryStr = "/data/companyInfo[1]";
 List<Element> elements = xPathExpOperate(queryStr);
 HtmlColumn column;
 for (Element element : elements) {
     List<Element> childrenEle = element.getChildren();
     for (int i = 2; i < childrenEle.size(); i++) {
         subjectNums.add(childrenEle.get(i).getName()); // 添加科目
         column = new HtmlColumn(childrenEle.get(i).getName());
         column.setProperty(childrenEle.get(i).getName());
         column.setTitle(childrenEle.get(i).getName());
         list.add(column);
     }
     break;
 }
 if (flag_pw != null) {
     HtmlColumn htmlColumn_PW = new HtmlColumn("PW");
     htmlColumn_C.setTitle("排位");
     htmlColumn_C.setWidth("40px");
     list.add(htmlColumn_C);
 }
 return list;
}

 *//**
 * 获取公司名称
 * 
 * @return
 * @throws LlApplicationException
 */
/*
public List<String> getCompanyName() throws LlApplicationException {
 List<String> list = new ArrayList<String>();
 String queryStr = "/data/companyInfo/name";
 List<Element> elements = xPathExpOperate(queryStr);
 for (Element element : elements) {
     list.add(element.getText());
 }
 this.setTotalNum(list.size());
 return list;
}

 *//**
 * 修改数据
 * 
 * @param id
 * @param subjectNum
 * @param text
 * @throws LlApplicationException
 */
/*
public void modifyXMLById(String id, String subjectNum, String text) throws LlApplicationException {
 String queryStr = "/data/companyInfo[@id=" + id + "]";
 List<Element> list = xPathExpOperate(queryStr);
 if (list.size() > 0) {
     Element element = list.get(0);
     list = element.getChildren();
     for (Element el : list) {
         if (subjectNum.equals(el.getName())) {
             el.setText(text);
         }
     }
 }
}

 *//**
 * 获取指定条件XML数据
 * 
 * @param queryStr 查询XML字符串
 * @return
 * @throws LlApplicationException
 */
/*
private List<Element> xPathExpOperate(String queryStr) throws LlApplicationException {
 XPathExpression<Element> xPathExpression = XPathFactory.instance().compile(queryStr, Filters.element());
 if (document == null || !document.hasRootElement()) {
     if (user != null) {
         document = COMPANYINFOLIST.get(user.getUsername() + "_xml");
     } else {
         throw new LlApplicationException(MessageConstantsUtil.MESSAGE_XPATHEXPOPERATE_USER);
     }
     List<Element> elements = xPathExpression.evaluate(document);
     document = null;
     return elements;
 }
 return xPathExpression.evaluate(document);
}

 *//**
 * 创建XML数据文件
 * 
 * @return 创建是否成功
 * @throws LlApplicationException
 */
/*
public boolean buildXMLFile() throws LlApplicationException {
 Boolean flag = true;
 try {
     File file = new File(ConstantsUtil.SUBJECTVALUE_PUTOUT_TEMP);
     if (!file.exists()) {
         file.mkdirs();
     }
     if (user != null) {
         document = COMPANYINFOLIST.get(user.getUsername() + "_xml");
     } else {
         throw new LlApplicationException(MessageConstantsUtil.MESSAGE_XPATHEXPOPERATE_USER);
     }
     xmlOutputter.output(document, new FileOutputStream(ConstantsUtil.SUBJECTVALUE_PUTOUT_TEMP + XMLNAME));
 } catch (FileNotFoundException e) {
     flag = false;
     e.printStackTrace();
 } catch (IOException e) {
     flag = false;
     e.printStackTrace();
 }
 return flag;
}

 *//**
 * @return index
 */
/*
public Integer getIndex() {
 // if(maxRows != null && pageNum != null) {
 // index = maxRows * (pageNum - 1);
 // }
 return index;
}

 *//**
 * @param index 要设置的 index
 */
/*
public void setIndex(Integer index) {
 this.index = index;
}

 *//**
 * @return pageNum
 */
/*
public Integer getPageNum() {
 return pageNum;
}

 *//**
 * @param pageNum 要设置的 pageNum
 */
/*
public void setPageNum(Integer pageNum) {
 this.pageNum = pageNum;
}

 *//**
 * @return maxRows
 */
/*
public Integer getMaxRows() {
 if (maxRows == null) {
     maxRows = ConstantsUtil.MAX_ROW;
 }
 return maxRows;
}

 *//**
 * @param maxRows 要设置的 maxRows
 */
/*
public void setMaxRows(Integer maxRows) {
 this.maxRows = maxRows;
}

 *//**
 * @param totalNum 要设置的 totalNum
 */
/*
public void setTotalNum(Integer totalNum) {
 this.totalNum = totalNum;
}

 *//**
 * @param user 要设置的 user
 */
/*
public void setUser(User user) {
 this.user = user;
}

 *//**
 * 移除xml内存内容
 */
/*
public void removeXMLString() {
 COMPANYINFOLIST.clear();
 document = null;
 XMLNAME = "XMLNAME.xml";
}

 *//**
 * @return totalNum
 */
/*
public Integer getTotalNum() {
 return totalNum;
}

 *//**
 * @return xMLNAME
 */
/*
public static String getXMLNAME() {
 return XMLNAME;
}

 *//**
 * @param comName 要设置的 comName
 */
/*
public void setComName(String comName) {
 this.comName = comName;
}

 *//**
 * @param flag_pw 要设置的 flag_pw
 */
/*
 * public void setFlag_pw(String flag_pw) { this.flag_pw = flag_pw; } }
 */