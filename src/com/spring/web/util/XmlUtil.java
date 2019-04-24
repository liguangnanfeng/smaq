/*package com.spring.web.util;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import com.spring.web.exception.LlSystemException;
import com.spring.web.model.Subject;

 *//**
 * @Title: XMLUtil
 * @Description: xml操作类
 * @author SEA
 * @date Jun 10, 2013 10:32:39 AM
 * @version V1.0
 */
/*
public class XmlUtil {
 *//**
 * 日志记录器
 */
/*
private Logger log = Logger.getLogger(XmlUtil.class);
 *//**
 * xml document
 */
/*
private Document document = null;
private XMLOutputter xmlOutputter = null;
private PropertyUtilsBean propertyUtils;
private ConvertUtilsBean convertUtilsBean;
 *//**
 * 读写路径
 */
/*
private String path = null;
private final static String FILENAME = "/subject.xml";

public XmlUtil() {
 xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
};

public XmlUtil(String path) throws Exception {
 this.path = path;
 propertyUtils = new PropertyUtilsBean();
 convertUtilsBean = new ConvertUtilsBean();
 SAXBuilder builder = new SAXBuilder();
 File file = new File(path + FILENAME);
 if (!file.exists()) {
     throw new LlSystemException(MessageConstantsUtil.XML_NOT_EXIST_EXCEPTION);
 }
 try {
     document = builder.build(file);
     xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
 } catch (JDOMException e) {
     // TODO Auto-generated catch block
     log.error(StackTraceUtil.getStackTrace(e));
     throw new LlSystemException(MessageConstantsUtil.XML_NOT_EXIST_EXCEPTION);
 } catch (IOException e) {
     // TODO Auto-generated catch block
     log.error(StackTraceUtil.getStackTrace(e));
     throw new LlSystemException(MessageConstantsUtil.XML_ANALYSIS_EXCEPTION);
 }
}

 *//**
 * xml字符串转换成doc
 * 
 * @param xmlStr
 * @param xml
 * @throws Exception
 */
/*
public XmlUtil(String xmlStr, String xml) throws Exception {
 propertyUtils = new PropertyUtilsBean();
 convertUtilsBean = new ConvertUtilsBean();
 SAXBuilder builder = new SAXBuilder();
 try {
     Reader in = new StringReader(xmlStr);
     document = builder.build(in);
     xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
 } catch (JDOMException e) {
     // TODO Auto-generated catch block
     log.error(StackTraceUtil.getStackTrace(e));
     throw new LlSystemException(MessageConstantsUtil.XML_NOT_EXIST_EXCEPTION);
 } catch (IOException e) {
     // TODO Auto-generated catch block
     log.error(StackTraceUtil.getStackTrace(e));
     throw new LlSystemException(MessageConstantsUtil.XML_ANALYSIS_EXCEPTION);
 }
}

 *//**
 * 添加新的节点
 * 
 * @param object
 * @throws Exception
 */
/*
synchronized public void addElement(Object object) throws Exception {
 try {
     log.info("add element start...");
     Element rootElement = document.getRootElement();
     Element element = new Element("field");
     rootElement.addContent(element);
     PropertyDescriptor[] descriptors = propertyUtils.getPropertyDescriptors(object);
     for (int i = 0; i < descriptors.length; i++) {
         String name = descriptors[i].getName();
         if ("id".equals(name)) {
             element.setAttribute("id", convertUtilsBean.convert(propertyUtils.getNestedProperty(object, name)));
             continue;
         }
         if (!"class".equals(name) && propertyUtils.getReadMethod(descriptors[i]) != null) {
             log.info("add element:" + name);
             element.addContent(new Element(name).setText(convertUtilsBean.convert(propertyUtils
                     .getNestedProperty(object, name))));
         }
     }
     log.info("add element end");
     this.writeXML();
 } catch (IllegalAccessException e) {
     // TODO Auto-generated catch block
     log.info(StackTraceUtil.getStackTrace(e));
     throw new LlSystemException(MessageConstantsUtil.CONVERT_EXCEPTION);
 } catch (InvocationTargetException e) {
     // TODO Auto-generated catch block
     log.info(StackTraceUtil.getStackTrace(e));
     throw new LlSystemException(MessageConstantsUtil.CONVERT_EXCEPTION);
 } catch (NoSuchMethodException e) {
     // TODO Auto-generated catch block
     log.info(StackTraceUtil.getStackTrace(e));
     throw new LlSystemException(MessageConstantsUtil.CONVERT_EXCEPTION);
 }
}

 *//**
 * 添加新的节点
 * 
 * @param object
 * @throws Exception
 */
/*
synchronized public void addElementFromMap(Map<String, Object> map) throws Exception {
 log.info("add element start...");
 Element rootElement = document.getRootElement();
 Element element = new Element("field");
 rootElement.addContent(element);
 for (Iterator<Entry<String, Object>> iter = map.entrySet().iterator(); iter.hasNext();) {
     Entry<String, Object> entry = iter.next();
     if ("id".equals(entry.getKey())) {
         element.setAttribute("id", String.valueOf(entry.getValue()));
     } else {
         element.addContent(new Element(entry.getKey()).setText(String.valueOf(entry.getValue())));
         log.info("add element:" + entry.getKey());
     }
 }
 log.info("add element end");
 this.writeXML();
}

 *//**
 * 修改指定元素
 * 
 * @param map
 * @throws Exception
 */
/*
synchronized public void modifyElment(Map<String, Object> map) throws Exception {
 log.info("modify start...");
 Element element = this.getById(String.valueOf(map.get("id")));
 // 获得所有子元素
 List<Element> list = element.getChildren();
 for (Element e : list) {
     if (map.containsKey(e.getName())) {
         e.setText(String.valueOf(map.get(e.getName())));
     }
 }
 this.writeXML();
 log.info("modify end");
}

 *//**
 * 删除指定元素
 * 
 * @param subject
 * @throws Exception
 */
/*
synchronized public void removeElement(Integer id) throws Exception {
 log.info("delete start");
 Element rootElement = document.getRootElement();
 Element element = this.getById(String.valueOf(id));
 rootElement.removeContent(element);
 this.writeXML();
 log.info("delete end");
}

 *//**
 * 写入xml
 * 
 * @throws Exception
 */
/*
synchronized private void writeXML() throws Exception {
 try {
     xmlOutputter.output(document, new FileOutputStream(new File(path + FILENAME)));
 } catch (FileNotFoundException e) {
     // TODO Auto-generated catch block
     throw new LlSystemException(MessageConstantsUtil.XML_NOT_EXIST_EXCEPTION);
 } catch (IOException e) {
     // TODO Auto-generated catch block
     throw new LlSystemException(MessageConstantsUtil.XML_NOT_EXIST_EXCEPTION);
 }
}

 *//**
 * 获取指定节点
 * 
 * @param id 对象id
 * @return 查询元素
 */
/*
private Element getById(String id) {
 XPathExpression<Element> xpath = XPathFactory.instance().compile("/subject/field[@id=" + id + "]",
         Filters.element());
 Element emt = xpath.evaluateFirst(document);
 return emt;
}

 *//**
 * 得到一实体对象
 * 
 * @param id
 * @return subject
 */
/*
public Subject getSubject(String id) {
 Subject subject = new Subject();
 Element el = this.getById(id);
 subject.setId(Integer.parseInt(el.getAttributeValue("id")));
 subject.setCategory(el.getChildText("category"));
 subject.setName(el.getChildText("name"));
 subject.setIsDelete(el.getChildText("isDelete"));
 subject.setSubjectNum(el.getChildText("subjectNum"));
 subject.setType(el.getChildText("type"));
 return subject;
}

 *//**
 * 初始化subject.xml
 * 
 * @param list
 * @throws Exception
 */
/*
public void initSubject(List<Subject> list) throws Exception {
 try {
     log.info("init subject.xml start...");
     Element rootElement = document.getRootElement();
     // 先删除
     rootElement.removeContent();
     for (Subject object : list) {
         log.info("write field start..." + object.getName());
         Element element = new Element("field");
         rootElement.addContent(element);
         PropertyDescriptor[] descriptors = propertyUtils.getPropertyDescriptors(object);
         for (int i = 0; i < descriptors.length; i++) {
             String name = descriptors[i].getName();
             if ("id".equals(name)) {
                 element.setAttribute("id",
                         convertUtilsBean.convert(propertyUtils.getNestedProperty(object, name)));
                 continue;
             }
             if (!"class".equals(name) && propertyUtils.getReadMethod(descriptors[i]) != null) {
                 log.info("add element:" + name);
                 element.addContent(new Element(name).setText(convertUtilsBean.convert(propertyUtils
                         .getNestedProperty(object, name))));
             }
         }
         log.info("write field end:" + object.getName());
     }
     log.info("init subject.xml end");
     this.writeXML();
 } catch (IllegalAccessException e) {
     // TODO Auto-generated catch block
     log.info(StackTraceUtil.getStackTrace(e));
     throw new LlSystemException(MessageConstantsUtil.CONVERT_EXCEPTION);
 } catch (InvocationTargetException e) {
     // TODO Auto-generated catch block
     log.info(StackTraceUtil.getStackTrace(e));
     throw new LlSystemException(MessageConstantsUtil.CONVERT_EXCEPTION);
 } catch (NoSuchMethodException e) {
     // TODO Auto-generated catch block
     log.info(StackTraceUtil.getStackTrace(e));
     throw new LlSystemException(MessageConstantsUtil.CONVERT_EXCEPTION);
 }
}

 *//**
 * 获取所有科目名称
 * 
 * @return array
 */
/*
public String[] getAllSubject() {
 XPathExpression<Element> xpath = XPathFactory.instance().compile("/subject/field[isDelete=0]",
         Filters.element());
 List<Element> list = xpath.evaluate(document);
 String[] subjectArray = new String[list.size()];
 int i = 0;
 for (Element el : list) {
     subjectArray[i] = el.getChildText("name");
     i++;
 }
 return subjectArray;
}

 *//**
 * 获取所有科目名称list
 * 
 * @return
 */
/*
public List<String> getAllSubjectArray() {
 List<String> subjectNameList = new ArrayList<String>();
 XPathExpression<Element> xpath = XPathFactory.instance().compile("/subject/field[isDelete=0]",
         Filters.element());
 List<Element> list = xpath.evaluate(document);
 int i = 0;
 for (Element el : list) {
     subjectNameList.add(el.getChildText("name"));
     i++;
 }
 return subjectNameList;
}

 *//**
 * 获取所有科目Map
 * 
 * @return
 */
/*
public Map<String, Subject> getAllSubjectMap() {
 Map<String, Subject> subjectMap = new HashMap<String, Subject>();
 XPathExpression<Element> xpath = XPathFactory.instance().compile("/subject/field[isDelete=0]",
         Filters.element());
 List<Element> list = xpath.evaluate(document);
 int i = 0;
 Subject subject = null;
 for (Element el : list) {
     subject = new Subject();
     subject.setId(Integer.parseInt(el.getAttributeValue("id")));
     subject.setCategory(el.getChildText("category"));
     subject.setName(el.getChildText("name"));
     subject.setSubjectNum(el.getChildText("subjectNum"));
     subject.setIsDelete(el.getChildText("isDelete"));
     subject.setType(el.getChildText("type"));
     subject.setDefaultValue(el.getChildText("defaultValue"));
     subjectMap.put(el.getChildText("name"), subject);
     i++;
 }
 return subjectMap;
}

 *//**
 * 获取所有科目Map,key->subjectNum,value->Subject
 * 
 * @return
 */
/*
public Map<String, Subject> getMapFromXml() {
 Map<String, Subject> subjectMap = new HashMap<String, Subject>();
 XPathExpression<Element> xpath = XPathFactory.instance().compile("/subject/field[isDelete=0]",
         Filters.element());
 List<Element> list = xpath.evaluate(document);
 int i = 0;
 Subject subject = null;
 for (Element el : list) {
     subject = new Subject();
     subject.setId(Integer.parseInt(el.getAttributeValue("id")));
     subject.setCategory(el.getChildText("category"));
     subject.setName(el.getChildText("name"));
     subject.setSubjectNum(el.getChildText("subjectNum"));
     subject.setType(el.getChildText("type"));
     subject.setDefaultValue(el.getChildText("defaultValue"));
     subjectMap.put(el.getChildText("subjectNum"), subject);
     i++;
 }
 return subjectMap;
}

 *//**
 * 通过节点增加节点
 * 
 * @param element
 * @throws Exception
 */
/*
public void addElementByElement(Element element) throws Exception {
 Element rootElement = document.getRootElement();
 rootElement.addContent(element);
 this.writeXML();
}

 *//**
 * 获取指定类别的科目
 * 
 * @param type 对象
 * @return 查询元素
 */
/*
public List<Subject> getListByType() {
 XPathExpression<Element> xpath = XPathFactory.instance().compile(
         "/subject/field[category=10]|/subject/field[category=09]|/subject/field[category=11]",
         Filters.element());
 List<Element> emt = xpath.evaluate(document);
 Subject s = new Subject();
 List<Subject> list = new ArrayList<Subject>();
 for (Element e : emt) {
     if (StringUtils.equals(e.getChildText("isDelete"), "0")) {
         s = new Subject();
         s.setId(Integer.parseInt(e.getAttributeValue("id")));
         s.setName(e.getChildText("name"));
         s.setCategory(e.getChildText("category"));
         s.setIsDelete(e.getChildText("isDelete"));
         s.setDefaultValue(e.getChildText("defaultValue"));
         s.setSubjectNum(e.getChildText("subjectNum"));
         s.setType(e.getChildText("type"));
         s.setAbc(e.getChildText("abc"));
         list.add(s);
     }
 }
 return list;
}

 *//**
 * 通过IDS获取list subject
 * 
 * @param ids
 * @return
 */
/*
public List<Subject> getListByIds(String[] ids) {
 List<Subject> list = new ArrayList<Subject>();
 for (String id : ids) {
     list.add(this.getSubject(id));
 }
 return list;
}

@SuppressWarnings("rawtypes")
public Queue<String> getSubjectIdById(String id, Queue<String> queue) {
 if (id != null && id.length() != 0 && !queue.contains("-1")) {
     if (queue.contains(id) || queue.contains("-1")) {
         queue.clear();
         queue.add("-1");
         log.info("数据重复，队列变为-1");
         return queue;
     } else {
         queue.add(id);
         log.info("队列中加入了数值：" + id);
         ArrayList eArray = getParentId(id);
         if (eArray != null && eArray.size() != 0) {
             for (int i = 0; i < eArray.size(); i++) {
                 queue = getSubjectIdById(eArray.get(i).toString(), queue);
             }
         }
     }
 }
 return queue;
}

@SuppressWarnings({"rawtypes", "unchecked"})
public ArrayList getParentId(String id) {
 ArrayList parentsId = new ArrayList();
 ;
 XPathExpression<Element> xpath = XPathFactory.instance().compile("/subject/field[isDelete=0]",
         Filters.element());
 List<Element> list = xpath.evaluate(document);
 for (Element e : list) {
     String[] eArray = e.getChildText("subjectId").split("@");
     if (eArray != null && eArray.length != 0) {
         for (String subjectId : eArray) {
             if (id.equals(subjectId)) {
                 parentsId.add(e.getAttributeValue("id"));
             }
         }
     }
 }
 return parentsId;
}

 *//**
 * xml转成字符串
 * 
 * @return string
 */
/*
public String xmlToString() {
 ByteArrayOutputStream byteRsp = new ByteArrayOutputStream();
 try {
     xmlOutputter.output(document, byteRsp);
 } catch (Exception e) {
     log.info(StackTraceUtil.getStackTrace(e));
 }
 return byteRsp.toString();
}

 *//**
 * 创建xml
 * 
 * @return xml
 */
/*
 * public String createXml() { Element root = new Element("table"); document = new Document(root); String s = this.xmlToString(); log.info(s); return s; } public static void
 * main(String[] args) throws Exception { XmlUtil xmlUtil = new XmlUtil(); xmlUtil.createXml(); XmlUtil xmlUtil = new XmlUtil("D:"); Queue<String> queue = new LinkedList<String>();
 * queue = xmlUtil.getSubjectIdById("67", queue); for (String string : queue) { System.out.println(string); } } }
 */