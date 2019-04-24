package com.spring.web.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.spring.web.exception.LlApplicationException;
import com.spring.web.exception.LlSystemException;

/**
 * @Title: ExcelUtil
 * @Description: Excel工具类
 * @author FL
 * @date 2016年1月12日 下午4:52:03
 * @version V1.0
 */
public class ExcelUtil {
    /**
     * 日志记录器
     */
    private Logger log = Logger.getLogger(ExcelUtil.class);
    private static final int version2003 = 2003;
    private static final int version2007 = 2007;
    @SuppressWarnings("unused")
    private static int version = version2003;
    /**
     * excel work
     */
    private Workbook workbook = null;
    /**
     * 系统限制行数
     */
    private int h_row_count = 0;
    /**
     * 限制列数
     */
    private int h_column_count = 0;
    /**
     * 系统限制行数
     */
    private int v_row_count = 0;
    /**
     * 限制列数
     */
    private int v_column_count = 0;
    /**
     * 报表类型,默认横排
     */
    private int table_type = 0;
    /**
     * 系统属性文件路径
     */
    private final String SYSTEM_PROPERTIES = "system.properties";

    public ExcelUtil(String path) throws Exception {
        try {
            // 获取系统限制行数
            Properties prop = new Properties();
            prop.load(this.getClass().getClassLoader().getResourceAsStream(SYSTEM_PROPERTIES));
            // 横排
            h_row_count = Integer.parseInt(prop.getProperty("horizontal_import_y"));
            h_column_count = Integer.parseInt(prop.getProperty("horizontal_import_x"));
            // 竖排
            v_row_count = Integer.parseInt(prop.getProperty("vertical_import_y"));
            v_column_count = Integer.parseInt(prop.getProperty("vertical_import_x"));
            if (path.endsWith(".xls")) {
                version = version2003;
                workbook = WorkbookFactory.create(new File(path));
            } else if (path.endsWith(".xlsx")) {
                version = version2007;
                OPCPackage pkg = OPCPackage.open(path);
                workbook = new XSSFWorkbook(pkg);
            }
            if (workbook == null) {
                throw new LlSystemException(MessageConstantsUtil.EXCEL_EX);
            }
        } catch (FileNotFoundException e) {
            log.error(StackTraceUtil.getStackTrace(e));
            throw new LlApplicationException(MessageConstantsUtil.EXCEL_FILE_NOT_FOUND);
        } catch (InvalidFormatException e) {
            log.error(StackTraceUtil.getStackTrace(e));
            throw new LlApplicationException(MessageConstantsUtil.EXCEL_IVALID_FORMAT);
        } catch (IOException e) {
            log.error(StackTraceUtil.getStackTrace(e));
            throw new LlApplicationException(MessageConstantsUtil.EXCEL_FILE_BAD);
        }
    }

    /**
     * @param path
     *            excel路径
     * @param tableType
     *            报表类型
     * @throws Exception
     */
    public ExcelUtil(String path, int tableType) throws Exception {
        try {
            // 获取系统限制行数
            Properties prop = new Properties();
            prop.load(this.getClass().getClassLoader().getResourceAsStream(SYSTEM_PROPERTIES));
            // 横排
            h_row_count = Integer.parseInt(prop.getProperty("horizontal_import_y"));
            h_column_count = Integer.parseInt(prop.getProperty("horizontal_import_x"));
            // 竖排
            v_row_count = Integer.parseInt(prop.getProperty("vertical_import_y"));
            v_column_count = Integer.parseInt(prop.getProperty("vertical_import_x"));
            table_type = tableType;
            if (path.endsWith(".xls")) {
                version = version2003;
                workbook = WorkbookFactory.create(new File(path));
            } else if (path.endsWith(".xlsx")) {
                version = version2007;
                OPCPackage pkg = OPCPackage.open(path);
                workbook = new XSSFWorkbook(pkg);
            }
            if (workbook == null) {
                throw new LlSystemException(MessageConstantsUtil.EXCEL_EX);
            }
        } catch (FileNotFoundException e) {
            log.error(StackTraceUtil.getStackTrace(e));
            throw new LlApplicationException(MessageConstantsUtil.EXCEL_FILE_NOT_FOUND);
        } catch (InvalidFormatException e) {
            log.error(StackTraceUtil.getStackTrace(e));
            throw new LlApplicationException(MessageConstantsUtil.EXCEL_IVALID_FORMAT);
        } catch (IOException e) {
            log.error(StackTraceUtil.getStackTrace(e));
            throw new LlApplicationException(MessageConstantsUtil.EXCEL_FILE_BAD);
        }
    }

    /**
     * 获取当前行数据
     * 
     * @param sheetNumber
     * @param rowNumber
     * @return 行数据
     */
    public List<String> getRowData(int sheetNumber, int rowNumber) {
        System.out.println("get row data start, sheet:" + sheetNumber + " col:" + rowNumber);
        List<String> array = new ArrayList<String>();
        Sheet sheet = workbook.getSheetAt(sheetNumber);
        Row row = sheet.getRow(rowNumber);
        if (row != null) {
            String data = null;
            int count = this.getColCount(sheetNumber);
            for (int i = 0; i < count; i++) {
                Cell cell = row.getCell(i);
                if (cell != null) {
                    CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                    data = this.getCellData(cell);
                    System.out.println(cellRef.formatAsString());
                } else {
                    data = "";
                }
                array.add(data);
            }
        }
        System.out.println("get row data end");
        return array;
    }

    /**
     * 得到所有sheet名
     * 
     * @return sheet name list
     */
    public List<String> getSheetName() {
        System.out.println("get sheet name list,start");
        List<String> list = new ArrayList<String>();
        int count = this.getSheetCount();
        for (int i = 0; i < count; i++) {
            System.out.println(workbook.getSheetName(i));
            list.add(workbook.getSheetName(i));
        }
        System.out.println("get sheet name list ,end");
        return list;
    }

    /**
     * 得到当前sheet总数
     * 
     * @return int
     */
    public int getSheetCount() {
        return workbook.getNumberOfSheets();
    }

    /**
     * 得到当前sheet总行数
     * 
     * @param sheetNumber
     * @return count
     */
    public int getRowCount(int sheetNumber) {
        System.out.println("get row count,start");
        Sheet sheet = workbook.getSheetAt(sheetNumber);
        int count = sheet.getLastRowNum();
        count = count + 1;
        if (table_type == 0) {
            count = count > this.h_row_count ? this.h_row_count : count;
        } else {
            count = count > this.v_row_count ? this.v_row_count : count;
        }
        System.out.println(count);
        System.out.println("get row count ,end");
        return count;
    }

    /**
     * 得到当前单元格数据
     * 
     * @param sheetNumber
     * @param rowNumber
     * @param colNumber
     * @return string
     */
    public String getCellContent(int sheetNumber, int rowNumber, int colNumber) {
        System.out.println("get cell data, start");
        Sheet sheet = workbook.getSheetAt(sheetNumber);
        Row row = sheet.getRow(rowNumber);
        Cell cell = row.getCell(colNumber);
        String data = this.getCellData(cell);
        System.out.println("get cell data ,end");
        return data;
    }

    /**
     * 得到总列数
     * 
     * @param sheetNumber
     * @return 总列数
     */
    public int getColCount(int sheetNumber) {
        System.out.println("get col count, start");
        Sheet sheet = workbook.getSheetAt(sheetNumber);
        int count = sheet.getRow(0).getPhysicalNumberOfCells();
        if (table_type == 0) {
            count = count > this.h_column_count ? this.h_column_count : count;
        } else {
            count = count > this.v_column_count ? this.v_column_count : count;
        }
        System.out.println(count);
        System.out.println("get col count ,end");
        return count;
    }

    /**
     * 得到列数据
     * 
     * @param sheetNumber
     * @param colNumber
     * @return 列数据
     */
    public List<String> getColData(int sheetNumber, int colNumber) {
        System.out.println("get col data start, sheet:" + sheetNumber + " col:" + colNumber);
        List<String> colList = new ArrayList<String>();
        Sheet sheet = workbook.getSheetAt(sheetNumber);
        String data = null;
        int count = this.getRowCount(sheetNumber);
        for (int i = 0; i < count; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            Cell cell = row.getCell(colNumber);
            data = this.getCellData(cell);
            colList.add(data);
        }
        /* for (Row row : sheet) { Cell cell = row.getCell(colNumber) ; data = this.getCellData(cell); colList.add(data); } */
        System.out.println("get col data end");
        return colList;
    }

    /**
     * 得到列标题
     * 
     * @return map
     */
    public Map<String, String> getColTitle(int sheetNumber) {
        System.out.println("get col title start, sheetNumber:" + sheetNumber);
        Sheet sheet = workbook.getSheetAt(sheetNumber);
        Map<String, String> map = new HashMap<String, String>();
        Row row = sheet.getRow(0);
        for (Cell cell : row) {
            map.put(String.valueOf(cell.getColumnIndex()), this.getCellData(cell));
        }
        System.out.println("get col title end, sheetNumber:" + sheetNumber);
        return map;
    }

    /**
     * 得到行标题
     * 
     * @param sheetNumber
     * @return map
     */
    public Map<String, String> getRowTitle(int sheetNumber) {
        System.out.println("get row title start, sheetNumber:" + sheetNumber);
        Sheet sheet = workbook.getSheetAt(sheetNumber);
        Map<String, String> map = new HashMap<String, String>();
        for (Row row : sheet) {
            Cell cell = row.getCell(0);
            map.put(String.valueOf(row.getRowNum()), this.getCellData(cell));
        }
        System.out.println("get row title end, sheetNumber:" + sheetNumber);
        return map;
    }

    /**
     * 得到单元格数据
     * 
     * @param cell
     * @return string
     */
    public static String getCellData(Cell cell) {
        System.out.println("get cell data start...");
        String data = null;
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    System.out.println("String:"+cell.getRichStringCellValue().getString());
                    data = cell.getRichStringCellValue().getString().trim();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        System.out.println("date:"+cell.getDateCellValue());
                        String sdf = null;
                        if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                            sdf = "HH:mm";
                        } else {// 日期
                            sdf = "yyyy-MM-dd";
                        }
                        Date date = cell.getDateCellValue();
                        data = DateFormatUtils.format(date, sdf);
                    } else {
                        System.out.println("numeric:"+cell.getNumericCellValue());
                        data = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    System.out.println("boolean:"+cell.getBooleanCellValue());
                    data = String.valueOf(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    System.out.println("formula:"+cell.getCellFormula());
                    data = String.valueOf(cell.getCellFormula());
                    break;
                default:
                    data = cell.getStringCellValue();
                    System.out.println(cell.getStringCellValue());
            }
            data = StringUtils.trim(data);
        }
        System.out.println("get cell data end");
        return data;
    }

    /**
     * 获取当前单元格编号
     * 
     * @param rowNum
     * @param columnIndex
     * @return
     */
    public static String getCellNumber(int rowNum, int columnIndex) {
        CellReference cellRef = new CellReference(rowNum, columnIndex);
        System.out.println(cellRef.formatAsString());
        return cellRef.formatAsString();
    }

    /**
     * cell转Integer
     * 
     * @param cell
     * @return Integer
     */
    public static Integer cellValueInteger(Cell cell) {
        if (null == cell) {
            return null;
        }
        CellType type = cell.getCellTypeEnum();
        if (type == CellType.NUMERIC) {
            return ((Double) cell.getNumericCellValue()).intValue();
        } else if (type == CellType.STRING) {
            return Integer.valueOf(cell.getStringCellValue());
        }
        return Integer.valueOf(cell.toString());
    }

    /**
     * cell转BigDecimal
     * 
     * @param cell
     * @return BigDecimal
     */
    public static BigDecimal cellValueBigDecimal(Cell cell) {
        if (null == cell) {
            return null;
        }
        CellType type = cell.getCellTypeEnum();
        if (type == CellType.NUMERIC) {
            return BigDecimal.valueOf(((Double) cell.getNumericCellValue()));
        } else if (type == CellType.STRING) {
            return BigDecimal.valueOf(Double.valueOf(cell.getStringCellValue()));
        }
        return BigDecimal.valueOf(Double.valueOf(cell.toString()));
    }

    public static void main(String[] args) throws Exception {
        ExcelUtil excelUtil = new ExcelUtil("e:/sel.xls");
        excelUtil.getRowTitle(0);
        excelUtil.getColTitle(0);
        excelUtil.getSheetCount();
        excelUtil.getSheetName();
        excelUtil.getRowData(0, 2);
        List<String> list = excelUtil.getColData(0, 1);
        for (String s : list) {
            if (StringUtils.isNotBlank(s)) {
                System.out.println(s);
            } else {
                System.out.println("I am null");
            }
        }
        excelUtil.getRowCount(0);
        excelUtil.getColCount(0);
    }
}
