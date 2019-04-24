/*package com.spring.web.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.jmesa.limit.LimitActionFactory;
import org.jmesa.model.TableModel;
import org.jmesa.util.SupportUtils;
import org.jmesa.view.component.Column;
import org.jmesa.view.component.Row;
import org.jmesa.view.component.Table;
import org.jmesa.view.editor.CellEditor;
import com.google.gson.Gson;
import com.spring.web.model.ExportModel;

 *//**
 * 
 * @Title: ExportUtil 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author FL   
 * @date 2016年1月13日 下午1:15:29 
 * @version V1.0
 */
/*
public class ExportUtil {
 private static String EXPORT_MODEL = "exportModel";
 private static String DEFAULT_EDITOR = "org.jmesa.view.editor.BasicCellEditor";
 private static String CELL_EDITOR = "cellEditor";
 private static String DEFAULT_TITLE = "title";
 private static String DEFAULT_PATTERN = "pattern";
 private HttpServletRequest request;
 private HttpServletResponse response;

 public ExportUtil(HttpServletRequest request, HttpServletResponse response) {
     this.request = request;
     this.response = response;
 }

 *//**
 * 直接跳转导出。
 */
/*
public void export() {
 ExportModel exportModel = (ExportModel) request.getAttribute(EXPORT_MODEL);
 if (exportModel != null) {
     TableModel tableModel = new TableModel(exportModel.getTag(), request, response);
     tableModel.setItems(exportModel.getItems());
     if (TableModel.CSV.equals(exportModel.getExportType())) {
         tableModel.setExportTypes(TableModel.CSV);
     } else if (TableModel.EXCEL.equals(exportModel.getExportType())) {
         tableModel.setExportTypes(TableModel.EXCEL);
     }
     // 导出数据
     LoggerUtil.cLog.info("export data start...");
     export(tableModel, exportModel.getCaption(), exportModel.getTag());
     tableModel.render();
     LoggerUtil.cLog.info("export data  end。");
 }
}

 *//**
 * 页面重定向导出。
 * 
 * @param exportModel
 */
/*
public void export(ExportModel exportModel) {
 if (exportModel != null) {
     TableModel tableModel = new TableModel(exportModel.getTag(), request, response);
     tableModel.setItems(exportModel.getItems());
     if (TableModel.CSV.equals(exportModel.getExportType())) {
         tableModel.setExportTypes(TableModel.CSV);
     } else if (TableModel.EXCEL.equals(exportModel.getExportType())) {
         tableModel.setExportTypes(TableModel.EXCEL);
     }
     // 导出数据
     LoggerUtil.cLog.info("export data start...");
     this.export(tableModel, exportModel.getCaption(), exportModel.getTag());
     tableModel.render();
     LoggerUtil.cLog.info("export data  end。");
 }
}

 *//**
 * 导出数据。
 * 
 * @return
 */
/*
private void export(TableModel tableModel, String caption, String tag) {
 Table table = new Table().caption(caption);
 tableModel.setTable(table);
 Row row = new Row();
 table.setRow(row);
 Gson g = new Gson();
 @SuppressWarnings("unchecked")
 Map<String, Map<String, String>> exportFields = g.fromJson(this.getExportFields(tag), Map.class);
 for (Iterator<Entry<String, Map<String, String>>> iter = exportFields.entrySet().iterator(); iter.hasNext();) {
     Entry<String, Map<String, String>> entry = iter.next();
     Map<String, String> innerMap = entry.getValue();
     Column column = new Column(entry.getKey()).title(innerMap.get(DEFAULT_TITLE));
     String editor = innerMap.get(CELL_EDITOR);
     String pattern = innerMap.get(DEFAULT_PATTERN);
     if (StringUtils.isNotBlank(editor) && !editor.equals(DEFAULT_EDITOR)) {
         CellEditor result = (CellEditor) createInstance(editor);
         if (StringUtils.isNotBlank(pattern)) {
             SupportUtils.setPattern(result, pattern);
         }
         column.setCellEditor(result);
     }
     row.addColumn(column);
 }
}

 *//**
 * 直接跳转导出。
 */
/*
public void exportByTable() {
 ExportModel exportModel = (ExportModel) request.getAttribute(EXPORT_MODEL);
 if (exportModel != null) {
     TableModel tableModel = new TableModel(exportModel.getTag(), request, response);
     tableModel.setTable(exportModel.getTable());
     tableModel.setItems(exportModel.getItems());
     if (TableModel.CSV.equals(exportModel.getExportType())) {
         tableModel.setExportTypes(TableModel.CSV);
     } else if (TableModel.EXCEL.equals(exportModel.getExportType())) {
         tableModel.setExportTypes(TableModel.EXCEL);
     }
     // 导出数据
     LoggerUtil.cLog.info("export data start...");
     // export(tableModel, exportModel.getCaption(), exportModel.getTag());
     tableModel.render();
     LoggerUtil.cLog.info("export data  end。");
 }
}

 *//**
 * 得到导出字段。
 * 
 * @param tag
 * @return
 */
/*
public String getExportFields(String tag) {
 LimitActionFactory actionFactory = new LimitActionFactory(tag, request.getParameterMap());
 return actionFactory.getExportFields();
}

 *//**
 * 类名实例化。
 * 
 * @param className
 * @return
 */
/*
 * private static Object createInstance(String className) { if (StringUtils.isEmpty(className)) { return null; } try { return Class.forName(className).newInstance(); } catch
 * (Exception e) { LoggerUtil.cLog.error("Could not create the class [" + className + "]", e); throw new RuntimeException("Could not create the class [" + className + "]", e); } }
 * }
 */