/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.service.export;

import com.spring.web.dao.*;
import com.spring.web.model.*;
import com.spring.web.model.request.ImportPhoto;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.util.ConstantsUtil;
import com.spring.web.util.DateConvertUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Title: ExportServiceImpl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author Z
 * @date 2017年7月25日 上午10:23:32
 * @version V1.0
 */
@Service("exportService")
public class ExportServiceImpl implements ExportService {
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 6564L;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private RegulationMapper regulationMapper;
    @Autowired
    private MequipmentMapper mequipmentMapper;
    @Autowired
    private MaterialMapper materialMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private SequipmentMapper sequipmentMapper;
    @Autowired
    private SpersonMapper spersonMapper;
    @Autowired
    private EvaluationMapper evaluationMapper;
    @Autowired
    private DetectionMapper detectionMapper;
    @Autowired
    private ExaminationMapper examinationMapper;
    @Autowired
    private FacilitiesMapper facilitiesMapper;
    @Autowired
    private PequipmentMapper pequipmentMapper;
    @Autowired
    private GlobalRegionMapper globalRegionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ImportRecordMapper importRecordMapper;
    @Autowired
    private Table2Mapper table2mapper;
    @Autowired
    private Table3Mapper table3mapper;
    @Autowired
    private PingMapper pingMapper;
    @Autowired
    private TCheckMapper tCheckMapper;
    @Autowired
    private TCheckItemMapper tCheckItemMapper;
    @Autowired
    private TCheckPartMapper tCheckPartMapper;
    @Autowired
    private ImportPhotoMapper importPhotoMapper;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Result companyImport(@RequestParam MultipartFile file, Integer villageId, HttpServletRequest request)
            throws Exception {
        Result result = new ResultImpl();
        if (null != file) {
            String fileName = file.getOriginalFilename();
            // 检查扩展名
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            if (!"xlsx".equals(fileExt)) {
                result.setStatus("1");
                result.setMap("message", "上传文件扩展名是不允许的扩展名。只允许xlsx格式。");
                return result;
            }
        } else {
            result.setStatus("1");
            result.setMap("message", "请选择excel文件。");
            return result;
        }
        Workbook wb = null;
        try {
            wb = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = wb.getSheetAt(0);
            String title = tos(sheet, 0, 0);
            if (!"企业信息".equals(title)) {
                result.setStatus("1");
                result.setMap("message", "此表不是企业信息排查表，请重新选择");
                return result;
            }
            String name = tos(sheet, 1, 1);
            if (StringUtils.isBlank(name)) {
                result.setStatus("1");
                result.setMap("message", "企业名称不能为空");
                return result;
            }
            if (null != userMapper.selectUserByUserName(name)) {
                result.setStatus("1");
                result.setMap("message", "该企业已存在");
                return result;
            }
            
            Map<String, Integer> m = new HashMap<String, Integer>();
            String[] x = "主要原辅材料,主要生产产品,主要设备清单,（—）安全管理人员,（二）危险化学品安全管理人员,（三）特种作业人员,特种设备 ,劳动防护用品配备,（一）职业卫生检测,（二）职业健康体检,消防设备设施,评价评估系统,委托排查".split(",");
            for(int i=0;i<x.length;i++) {
                m.put(x[i].trim(), i + 1);
            }
            
            List<Material> list1 = new ArrayList<Material>();//主要原辅材料
            List<Product> list2 = new ArrayList<Product>();//主要生产产品
            List<Mequipment> list3 = new ArrayList<Mequipment>();//主要设备清单
            List<Sperson> list4 = new ArrayList<Sperson>();//（—）安全管理人员
            List<Sperson> list5 = new ArrayList<Sperson>();//危险化学品安全管理人员
            List<Sperson> list6 = new ArrayList<Sperson>();//特种作业人员
            List<Sequipment> list7 = new ArrayList<Sequipment>();//特种设备
            List<Pequipment> list8 = new ArrayList<Pequipment>();//劳动防护用品配备
            List<Detection> list9 = new ArrayList<Detection>();//（一）职业卫生检测
            List<Examination> list10 = new ArrayList<Examination>();//（二）职业健康体检
            List<Facilities> list11 = new ArrayList<Facilities>();//消防设备设施
            List<Evaluation> list12 = new ArrayList<Evaluation>();//评价评估系统
            TCheck check = new TCheck();
            TCheckPart part = new TCheckPart();
            List<TCheckItem> itemL = new ArrayList<TCheckItem>();
            
            Integer type = 0;
            Integer length = sheet.getLastRowNum();//总行数
            for(int i = 18; i <= length; i++) {
                String t = tos(sheet, i, 0);
                if(StringUtils.isEmpty(t)) {
                    continue;
                }
                if(t.equals("持证上岗") || t.equals("职卫检测、体检")) {
                    continue;
                }
                Integer t0 = m.get(t);
                if(null != t0) {
                    type = t0;
                    if(type.compareTo(13) == 0) {
                        i++;
                        String checkCompany = tos(sheet, i, 1);
                        String checker = tos(sheet, i, 3);
                        String time = tos(sheet, i, 5);
                        i = i + 2;
                        check.setCheckCompany(checkCompany);
                        check.setCheker(checker);
                        if(StringUtils.isNotBlank(time)) {
                            try {
                                Date d = DateConvertUtil.formateDate(time, "yyyy-MM-dd");
                                check.setRealTime(d);
                            } catch (Exception e) {
                            }
                        }
                    } else {
                        i++;
                        continue;
                    }
                }
                
                Row row = sheet.getRow(i);
                switch(type) {
                    case 1 : 
                        if(StringUtils.isEmpty(tos(row, 0)) || StringUtils.isEmpty(tos(row, 1))) {
                            continue;
                        }
                        list1.add(new Material(null, null, tos(row, 1), tos(row, 2), tos(row, 3),
                                tos(row, 4), tos(row, 5), tos(row, 6), tos(row, 7)));
                        break;
                    case 2 : 
                        if(StringUtils.isEmpty(tos(row, 0)) || StringUtils.isEmpty(tos(row, 1))) {
                            continue;
                        }
                        list2.add(new Product(null, null, tos(row, 1), tos(row, 2), tos(row, 3),null, tos(row, 4), tos(row, 5)));
                        break;
                    case 3 : 
                        if(StringUtils.isEmpty(tos(row, 0)) || StringUtils.isEmpty(tos(row, 1))) {
                            continue;
                        }
                        list3.add(new Mequipment(null, null, tos(row, 1), tos(row, 2), tos(row, 3), toi(row, 4), tos(row, 5)));
                        break;
                    case 4 : 
                        if(StringUtils.isEmpty(tos(row, 0)) || StringUtils.isEmpty(tos(row, 1))) {
                            continue;
                        }
                        list4.add(new Sperson(null, null, tos(row, 1), 1, tos(row, 2), tos(row, 3),
                                tos(row, 4),  tos(row, 5),  tos(row, 6), tos(row, 7)));
                        break;
                    case 5 : 
                        if(StringUtils.isEmpty(tos(row, 0)) || StringUtils.isEmpty(tos(row, 1))) {
                            continue;
                        }
                        list5.add(new Sperson(null, null, tos(row, 1), 2, tos(row, 2), tos(row, 3),
                                tos(row, 4),  tos(row, 5),  tos(row, 6), tos(row, 7)));
                        break;
                    case 6 : 
                        if(StringUtils.isEmpty(tos(row, 0)) || StringUtils.isEmpty(tos(row, 1))) {
                            continue;
                        }
                        list6.add(new Sperson(null, null, tos(row, 1), 3, tos(row, 2), tos(row, 3),
                                tos(row, 4),  tos(row, 5),  tos(row, 6), tos(row, 7)));
                        break;
                    case 7 : 
                        if(StringUtils.isEmpty(tos(row, 0)) || StringUtils.isEmpty(tos(row, 1))) {
                            continue;
                        }
                        list7.add(new Sequipment(null, null, tos(row, 1), tos(row, 2), tos(row, 3),
                                null, tos(row, 4), tos(row, 5), tos(row, 6)));
                        break;
                    case 8 : 
                        if(StringUtils.isEmpty(tos(row, 0)) || StringUtils.isEmpty(tos(row, 1))) {
                            continue;
                        }
                        String o = tos(row, 1);
                        Integer libId = null;
                        if("一般劳动防护用品".equals(o)) {
                            libId = 1;
                        }
                        if("特种劳动防护用品".equals(o)) {
                            libId = 2;
                        }
                        if("其他".equals(o)) {
                            libId = 3;
                        }
                        list8.add(new Pequipment(null, null, tos(row, 2), libId, tos(row, 3), tos(row, 4), tos(row, 5), tos(row, 6), null));
                        break;
                    case 9 : 
                        if(StringUtils.isEmpty(tos(row, 0)) || StringUtils.isEmpty(tos(row, 1))) {
                            continue;
                        }
                        list9.add(new Detection(null, null, tos(row, 1), tos(row, 2), tos(row, 3), tos(row, 4), null, null, null, null, null, null));
                        break;
                    case 10 : 
                        if(StringUtils.isEmpty(tos(row, 0))) {
                            continue;
                        }
                        list10.add(new Examination(null, null, tos(row, 1), null, null, tos(row, 3), tos(row, 2), tos(row, 4), tos(row, 5), null, null, null));
                        break;
                    case 11 : 
                        if(StringUtils.isEmpty(tos(row, 0)) || StringUtils.isEmpty(tos(row, 1))) {
                            continue;
                        }
                        list11.add(new Facilities(null, null, 1, tos(row, 1), tos(row, 2), tos(row, 3), tos(row, 4), tos(row, 5), tos(row, 6)));
                        break;
                    case 12 : 
                        if(StringUtils.isEmpty(tos(row, 0)) || StringUtils.isEmpty(tos(row, 1))) {
                            continue;
                        }
                        String ty = tos(row, 2);
                        Integer flag = null;
                        if(ty.startsWith("安全生产类")) {
                            flag = 1;
                            ty = ty.split("/")[1];
                        }
                        if(ty.startsWith("职业健康类")) {
                            flag = 2;
                            ty = ty.split("/")[1];
                        }
                        list12.add(new Evaluation(null, null, flag, ty, tos(row, 1), tos(row, 5), tos(row, 6), tos(row, 7), tos(row, 2), null, null));
                        break;
                    case 13 : 
                        if(StringUtils.isEmpty(tos(row, 0)) || StringUtils.isEmpty(tos(row, 1))) {
                            continue;
                        }
                        itemL.add(new TCheckItem(null, null, null, tos(row, 1), null, null, null, 2, tos(row, 2), null, null, null, null, null, null, null,null,null));
                        break;
                }
                
            }
            
            Date date = new Date();
            User user = new User(null, name, ConstantsUtil.ENCRYPT_PASSWORD_123456, 5, "0", date, "0", null, null);
            userMapper.insertSelective(user);
            
            Regulation rg = new Regulation();
            rg.setUserId(user.getId());
            regulationMapper.insertSelective(rg);
            
            Company c = parseCompanyInfo(sheet);
            c.setName(name);
            c.setUserId(user.getId());
            c.setVillageId(villageId);
            companyMapper.insertSelective(c);
            
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", user.getId());
            if(list1.size() > 0) {
                map.put("list", list1);
                materialMapper.insertBatch(map);
            }
            if(list2.size() > 0) {
                map.put("list", list2);
                productMapper.insertBatch(map);
            }
            if(list3.size() > 0) {
                map.put("list", list3);
                mequipmentMapper.insertBatch(map);
            }
            if(list4.size() > 0) {
                map.put("list", list4);
                spersonMapper.insertBatch(map);
            }
            if(list5.size() > 0) {
                map.put("list", list5);
                spersonMapper.insertBatch(map);
            }
            if(list6.size() > 0) {
                map.put("list", list6);
                spersonMapper.insertBatch(map);
            }
            if(list7.size() > 0) {
                map.put("list", list7);
                sequipmentMapper.insertBatch(map);
            }
            if(list8.size() > 0) {
                map.put("list", list8);
                pequipmentMapper.insertBatch(map);
            }
            if(list9.size() > 0) {
                map.put("list", list9);
                detectionMapper.insertBatch(map);
            }
            if(list10.size() > 0) {
                map.put("list", list10);
                examinationMapper.insertBatch(map);
            }
            if(list11.size() > 0) {
                map.put("list", list11);
                facilitiesMapper.insertBatch(map);
            }
            if(list12.size() > 0) {
                map.put("list", list12);
                evaluationMapper.insertBatch(map);
            }
            
            if(itemL.size() > 0) {
                check.setFlag(3);
                check.setTitle("委托排查");
                check.setUserId(user.getId());
                check.setCreateUser(villageId);
                check.setType(1);
                check.setCreateTime(date);
                check.setStatus(2);
                check.setDel(0);
                
                tCheckMapper.insertSelective(check);
                part.setName("");
                part.setCheckId(check.getId());
                tCheckPartMapper.insertSelective(part);
                
                tCheckItemMapper.insertThreeBath(itemL, check.getId(), part.getId());
            }
            
            // 文件保存目录路径
            String savePath = request.getServletContext().getRealPath("/") + "upload/";
            // 文件保存目录URL
            String saveUrl = request.getContextPath() + "/upload/";
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + ".xlsx";
            file.transferTo(new File(savePath + newFileName));
            ImportRecord i = new ImportRecord(null, file.getOriginalFilename(), saveUrl + newFileName, date, villageId);
            importRecordMapper.insertSelective(i);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.setStatus("1");
            result.setMap("message", "excel解析异常");
        } finally {
            if (null != wb) {
                wb.close();
            }
        }
        return result;
    }

    Integer youwu(String v) {
        if("有".equals(v)) {
            return 1;
        }
        if("无".equals(v)) {
            return 0;
        }
        return null;
    }
    
    /** 
    * @param sheet
    * @return 企业信息
    */
    Company parseCompanyInfo(Sheet sheet) {
        String regType = tos(sheet, 1, 3);//注册类型
        String regType2 = "其他类型".equals(regType) ? tos(sheet, 2, 3) : null;//其他类型
        String address = tos(sheet, 2, 1);//详细地址
        String legal = tos(sheet, 3, 1);//法定代表人
        String legalContact = tos(sheet, 3, 3);//法定代表人联系方式
        String capital = tos(sheet, 4, 1);//注册资本
        String establish = tos(sheet, 4, 3);//成立日期
        String business = tos(sheet, 5, 1);//营业期限
        String scope = tos(sheet, 5, 3);//经营范围
        String license = tos(sheet, 6, 1);//营业执照
        Integer regRegion = 222;//登记机关省市 默认无锡
        String authority = tos(sheet, 6, 3);//登记机关
        String fixed = tos(sheet, 7, 1);//固定资产
        Integer staff = toi(sheet, 9, 1);//员工人数
        String income = tos(sheet, 9, 3);//主营业务收入
        String industry = tos(sheet, 10, 1);//所属行业
        Integer danger = null;//是否高危作业 1是
        String c = tos(sheet, 8, 1);
        if("是".equals(c)) {danger = 1;}
        if("否".equals(c)) {danger = 0;}
        Integer hazard = null;//重大危险源 1是
        c = tos(sheet, 10, 3);
        if("是".equals(c)) {hazard = 1;}
        if("否".equals(c)) {hazard = 0;}
        String dlevel = tos(sheet, 11, 1);
        String duty = tos(sheet, 11, 3);
        String postage = tos(sheet, 12, 1);
        String email = tos(sheet, 12, 3);
        String charge = tos(sheet, 13, 1);
        String chargeContact = tos(sheet, 13, 3);
        String safety = tos(sheet, 14, 1);
        String safetyContact = tos(sheet, 14, 3);
        String safetyM = tos(sheet, 15, 1);
        String safetyMContact = tos(sheet, 15, 3);
        String area_range = tos(sheet,16,1);//add by zhangcl 2018.10.18
        return new Company(null, null, null, regType, regType2, null, address, legal,
                legalContact, capital, establish, business, scope, license, regRegion, authority, 
                null, fixed, staff, income, industry, danger, null, hazard, dlevel, charge, 
                chargeContact, safety, safetyContact, safetyM, safetyMContact, duty, postage, email, null, null, 0, null, null,area_range);//modify by zhangcl 2018.10.18
    }
    
    /** 
     * cell转integer
     */
    private Integer toi(Sheet sheet, int row, int col) {
        Row rowx = sheet.getRow(row);
        if(null == rowx) {
            return null;
        }
        Cell cell = rowx.getCell(col);
        if (null == cell || "".equals(cell.toString())) {
            return null;
        }
        return toi(cell);
    }

    /** 
     * cell转integer
     */
    private Integer toi(Row row, int col) {
        Cell cell = row.getCell(col);
        if (null == cell || "".equals(cell.toString())) {
            return null;
        }
        return toi(cell);
    }

    DecimalFormat df = new DecimalFormat("#");

    /** 
     * cell转integer
     */
    private Integer toi(Cell cell) {
        if(null == cell) {
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
     * cell转string
     */
    private String tos(Row row, int col) {
        return tos(row.getCell(col));
    }

    /** 
     * cell转string
     */
    private String tos(Cell cell) {
        if (null == cell) {
            return null;
        }
        CellType type = cell.getCellTypeEnum();
        if (type == CellType.NUMERIC) {
            return df.format(cell.getNumericCellValue());
        } else if (type == CellType.STRING) {
            return cell.getStringCellValue().trim();
        }
        return cell.toString().trim();
    }

    /** 
    * cell转string
    */
    private String tos(Sheet sheet, int row, int col) {
        Row rowx = sheet.getRow(row);
        if(null == rowx) {
            return null;
        }
        Cell cell = rowx.getCell(col);
        return tos(cell);
    }

    /**
     * (非 Javadoc)
     * 
     * @param file
     * @param id
     * @param request
     * @return
     * @throws Exception
     * @see com.spring.web.service.export.ExportService tableImport(org.springframework.web.multipart.MultipartFile, Integer, HttpServletRequest)
     */
    @Override
    public Result tableImport(MultipartFile file, Integer userId, Integer id, Integer isType, HttpServletRequest request)
            throws Exception {
        Result result = new ResultImpl();
        if (null != file) {
//            String fileName = file.getOriginalFilename();
            // 检查扩展名
//            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
//            if (!"docx".equals(fileExt)) {
//                result.setStatus("1");
//                result.setMap("message", "上传文件扩展名是不允许的扩展名。只允许docx格式。");
//                return result;
//            }
        } else {
            result.setStatus("1");
            result.setMap("message", "请选择文件。");
            return result;
        }
        try {
            // 文件保存目录路径
            String savePath = request.getServletContext().getRealPath("/") + "upload/txt/";
            // 文件保存目录URL
            String saveUrl = request.getContextPath() + "/upload/txt/";
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String oldname = file.getOriginalFilename();
            String fileExt = oldname.substring(oldname.lastIndexOf(".") + 1).toLowerCase();
            String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
            Date date = new Date();
            file.transferTo(new File(savePath + newFileName));
            Table2 i = new Table2(null, userId, file.getOriginalFilename(), isType, saveUrl + newFileName, 1, date);
            if (null != id && id < 0) {
                table2mapper.insertSelective(i);
            } else {
                i.setId(id);
                table2mapper.updateByPrimaryKeySelective(i);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            result.setStatus("1");
            result.setMap("message", "文件上传异常，请重试");
        }
        return result;
    }

    /** (非 Javadoc)
    *
    *
    * @param file
    * @param id
    * @param
    * @param isType
    * @param request
    * @return
    * @throws Exception
    * @see com.spring.web.service.export.ExportService#hImport(org.springframework.web.multipart.MultipartFile, Integer, Integer, Integer, HttpServletRequest)
    */
    @Override
    public Result hImport(MultipartFile file, Integer userId, Integer id, Integer isType, HttpServletRequest request)
            throws Exception {
        Result result = new ResultImpl();
        if (null != file) {
//            String fileName = file.getOriginalFilename();
//            // 检查扩展名
//            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
//            if (!"docx".equals(fileExt)) {
//                result.setStatus("1");
//                result.setMap("message", "上传文件扩展名是不允许的扩展名。只允许docx格式。");
//                return result;
//            }
        } else {
            result.setStatus("1");
            result.setMap("message", "请选择文件。");
            return result;
        }
        try {
            // 文件保存目录路径
            String savePath = request.getServletContext().getRealPath("/") + "upload/txt/";
            // 文件保存目录URL
            String saveUrl = request.getContextPath() + "/upload/txt/";
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String oldname = file.getOriginalFilename();
            String fileExt = oldname.substring(oldname.lastIndexOf(".") + 1).toLowerCase();
            String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
            Date date = new Date();
            file.transferTo(new File(savePath + newFileName));
            Table3 i = new Table3(null, userId, file.getOriginalFilename(), isType, saveUrl + newFileName, 1, date);
            if (null != id && id < 0) {
                table3mapper.insertSelective(i);
            } else {
                i.setId(id);
                table3mapper.updateByPrimaryKeySelective(i);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            result.setStatus("1");
            result.setMap("message", "文件上传异常，请重试");
        }
        return result;
    }

    /** (非 Javadoc)
    *
    *
    * @param file
    * @param userId
    * @param request
    * @return
    * @throws Exception
    * @see com.spring.web.service.export.ExportService#pImport(org.springframework.web.multipart.MultipartFile, Integer, HttpServletRequest)
    */
    @Override
    public Result pImport(MultipartFile file, Integer userId, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        if (null != file) {
//            String fileName = file.getOriginalFilename();
//            // 检查扩展名
//            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
//            if (!"docx".equals(fileExt)) {
//                result.setStatus("1");
//                result.setMap("message", "上传文件扩展名是不允许的扩展名。只允许docx格式。");
//                return result;
//            }
        } else {
            result.setStatus("1");
            result.setMap("message", "请选择文件。");
            return result;
        }
        try {
            // 文件保存目录路径
            String savePath = request.getServletContext().getRealPath("/") + "upload/txt/";
            // 文件保存目录URL
            String saveUrl = request.getContextPath() + "/upload/txt/";
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String oldname = file.getOriginalFilename();
            String fileExt = oldname.substring(oldname.lastIndexOf(".") + 1).toLowerCase();
            String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
            Date date = new Date();
            file.transferTo(new File(savePath + newFileName));
            Ping p = new Ping(null,userId, file.getOriginalFilename(),saveUrl + newFileName, date);
            pingMapper.insertSelective(p);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.setStatus("1");
            result.setMap("message", "文件上传异常，请重试");
        }
        return result;
    }


    /*
    * 岗位部说图片上传
    * */
    @Override
    public Result photoImport(MultipartFile file, Integer userId, HttpServletRequest request) throws Exception {

        Result result = new ResultImpl();
        if (null != file) {
            String fileName = file.getOriginalFilename();
           // 检查扩展名
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            if (!"jpeg".equals(fileExt) && !"jpg".equals(fileExt) && !"png".equals(fileExt)) {
                result.setStatus("1");
                result.setMap("message", "上传文件扩展名是不允许的扩展名。只允许jpeg，jpeg，png格式。");
                return result;
            }
        } else {
            result.setStatus("1");
            result.setMap("message", "请选择文件。");
            return result;
        }
        try {
            // 文件保存目录路径
            String savePath = request.getServletContext().getRealPath("/") + "upload/txt/";
            // 文件保存目录URL
            String saveUrl = request.getContextPath() + "/upload/txt/";
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String oldname = file.getOriginalFilename();
            String fileExt = oldname.substring(oldname.lastIndexOf(".") + 1).toLowerCase();
            String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
            Date date = new Date();
            file.transferTo(new File(savePath + newFileName));
            ImportPhoto importPhoto = new ImportPhoto();
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/upload/txt/"+newFileName;

            importPhoto.setUrl(url);
            importPhoto.setUrl1(url);
            importPhoto.setUser_id(userId);
            importPhoto.setName(oldname);
            importPhotoMapper.savePhoto(importPhoto);

        } catch (Exception ex) {
            ex.printStackTrace();
            result.setStatus("1");
            result.setMap("message", "文件上传异常，请重试");
        }
        return result;
    }
}
