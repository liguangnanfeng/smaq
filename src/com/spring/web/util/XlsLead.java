/**  
* Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
* All right reserved. 
*/ 

package com.spring.web.util;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.spring.web.model.Company;
import com.spring.web.model.Regulation;

/** 
 * @Title: XlsLead 
 * @Description: 导入Excel文件 
 * @author FL   
 * @date 2017年7月25日 上午8:58:59 
 * @version V1.0   
 */
public class XlsLead {
    /** 
     * @param args 
     */
    public static void main(String[] args) {
        try {
            Workbook wb = new XSSFWorkbook(OPCPackage.open(new File("E:/111222.xlsx")));
            Sheet sheet = wb.getSheetAt(0);
//            Company com=new Company();
//            Regulation reg=new Regulation();
//            com.setName(sheet.getRow(2).getCell(1).toString());
//            com.setLicense(sheet.getRow(2).getCell(3).toString());
//            com.setAddress(sheet.getRow(3).getCell(5).toString());
//            com.setShAdministration(sheet.getRow(4).getCell(1).toString());
//            com.setStaff(Integer.parseInt(sheet.getRow(6).getCell(1).toString()));
//            com.setSafetyPersonnel(Integer.parseInt(sheet.getRow(6).getCell(3).toString()));
//            com.setFixedAssets(Integer.parseInt(sheet.getRow(7).getCell(1).toString()));
//            com.setIncome(Integer.parseInt(sheet.getRow(7).getCell(3).toString()));
//            com.setShifts(Integer.parseInt(sheet.getRow(8).getCell(1).toString()));
//            com.setShiftsType(sheet.getRow(8).getCell(3).toString());
//            com.setCharge((sheet.getRow(9).getCell(1).toString()));
//            com.setChargePhone(sheet.getRow(9).getCell(3).toString());
//            com.setChargeFax(sheet.getRow(9).getCell(5).toString());
//            com.setChargeMobile(sheet.getRow(10).getCell(3).toString());
//            com.setChargeEmail(sheet.getRow(10).getCell(5).toString());
//            com.setContact(sheet.getRow(11).getCell(1).toString());
//            com.setContactPhone(sheet.getRow(11).getCell(3).toString());
//            com.setContactFax(sheet.getRow(11).getCell(5).toString());
//            com.setContactMobile(sheet.getRow(12).getCell(3).toString());
//            com.setContactEmail(sheet.getRow(12).getCell(5).toString());
//            com.setIndustry(sheet.getRow(13).getCell(1).toString());
//            com.setScale(sheet.getRow(13).getCell(3).toString());
//            
//            
//            reg.setManagementSystem(Integer.parseInt(sheet.getRow(14).getCell(1).toString()));
//            reg.setMisInforce(Boolean.parseBoolean((sheet.getRow(14).getCell(3).toString())));
//            reg.setRegulations(Integer.parseInt(sheet.getRow(14).getCell(5).toString()));
//            reg.setRisInforce(Boolean.parseBoolean((sheet.getRow(14).getCell(7).toString())));
//            reg.setSafety(Integer.parseInt((sheet.getRow(15).getCell(1).toString())));
//            reg.setSisInforce(Boolean.parseBoolean((sheet.getRow(15).getCell(3).toString())));
//            reg.setImplementation(Integer.parseInt((sheet.getRow(15).getCell(5).toString())));
//            reg.setContingency(Integer.parseInt((sheet.getRow(16).getCell(1).toString())));
//            reg.setCisInforce(Boolean.parseBoolean((sheet.getRow(16).getCell(3).toString())));
//            reg.setCisDanger(Boolean.parseBoolean((sheet.getRow(16).getCell(5).toString())));
//            reg.setProcess(Integer.parseInt((sheet.getRow(17).getCell(1).toString())));
//            reg.setProcessName((sheet.getRow(17).getCell(3).toString()));
//            reg.setChemic(Integer.parseInt((sheet.getRow(17).getCell(5).toString())));
//            reg.setChemicName((sheet.getRow(17).getCell(7).toString()));
//            
            List<Map<String, Object>> sbqdL = new LinkedList<Map<String,Object>>();
            List<Map<String, Object>> yfclL = new LinkedList<Map<String,Object>>();
            List<Map<String, Object>> zycpL = new LinkedList<Map<String,Object>>();
            List<Map<String, Object>> aqglL = new LinkedList<Map<String,Object>>();
            List<Map<String, Object>> tzzyL = new LinkedList<Map<String,Object>>();
            List<Map<String, Object>> tzsbL = new LinkedList<Map<String,Object>>();
            List<Map<String, Object>> aqpjL = new LinkedList<Map<String,Object>>();
            List<Map<String, Object>> jcL = new LinkedList<Map<String,Object>>();
            List<Map<String, Object>> zyjkL = new LinkedList<Map<String,Object>>();
            List<Map<String, Object>> xfyjL = new LinkedList<Map<String,Object>>();
            
            int t = 0;//1 主要设备清单 2 主要原辅材料 3 主要产品 4安全管理人员 5特种作业人员 6特种设备 7安全评价 8检测 9职业健康检查 10 消防应急设施
            for(int rowIndex = 19; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {
                String xx = sheet.getRow(rowIndex).getCell(0).toString();
                if(xx.equals("主要设备清单")) {
                    t = 1;
                }
                if(xx.equals("主要原辅材料")) {
                    t = 2;
                }
                if(xx.equals("主要产品")) {
                    t = 3;
                }
                if(xx.equals("安全管理人员")) {
                    t = 4;
                }
                if(xx.equals("特种作业人员")) {
                    t = 5;
                }
                if(xx.equals("特种设备")) {
                    t = 6;
                }
                if(xx.equals("安全评价")) {
                    t = 7;
                }
                if(xx.equals("检测")) {
                    t = 8;
                }
                if(xx.equals("职业健康检查")) {
                    t = 9;
                }
                if(xx.equals("消防应急设备配备")) {
                    t = 10;
                }
                if(1 == t) {
                    sbqdL.add(new HashMap<String, Object>());
                }
                if(2 == t) {
                    yfclL.add(new HashMap<String, Object>());
                }
                if(3 == t) {
                    zycpL.add(new HashMap<String, Object>());
                }
                if(4 == t) {
                    aqglL.add(new HashMap<String, Object>());
                }
                if(5 == t) {
                    tzzyL.add(new HashMap<String, Object>());
                }
                if(6 == t) {
                    tzsbL.add(new HashMap<String, Object>());
                }
                if(7 == t) {
                    aqpjL.add(new HashMap<String, Object>());
                }
                if(8 == t) {
                    jcL.add(new HashMap<String, Object>());
                }
                if(9 == t) {
                    zyjkL.add(new HashMap<String, Object>());
                }
                if(10 == t) {
                    xfyjL.add(new HashMap<String, Object>());
                }
            }
            
            Object company;
            Object regulation;
            List<Object> sbqd;
            List<Object> yfcl;
            List<Object> zycp;
            List<Object> aqgl;
            List<Object> tzzy;
            List<Object> tzsb;
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
}
