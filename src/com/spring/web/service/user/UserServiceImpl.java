package com.spring.web.service.user;

import com.spring.web.dao.*;
import com.spring.web.ibatis.BaseDao;
import com.spring.web.ibatis.LlHashMap;
import com.spring.web.model.*;
import com.spring.web.result.Result;
import com.spring.web.service.cgf.CgfService;
import com.spring.web.tobject.cgf.CompanyListReqDTO;
import com.spring.web.util.ConstantsUtil;
import com.spring.web.util.DateConvertUtil;
import com.spring.web.util.EncryptUtil;
import com.spring.web.util.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.DecimalFormat;
import java.util.*;

/**
 * @Title: UserServiceImpl
 * @Description: 用户服务实现类
 * @author FL
 * @date 2016年1月21日 上午11:18:33
 * @version V1.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 8852198365919835115L;
    @Autowired
    private BaseDao baseDao;
    @Autowired
    private ExaminationMapper examinationMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private SpersonMapper spersonMapper;
    @Autowired
    private RegulationMapper regulationMapper;
    @Autowired
    private TradeCompanyMapper tradeCompanyMapper;
    @Autowired
    private VillageMapper villageMapper;
    @Autowired
    private TCleanWarnSettingMapper tcleanWarnSettingMapper;
    @Autowired
    private SequipmentMapper sequipmentMapper;
    @Autowired
    private ZzjgPersonnelMapper zzjgPersonnelMapper;
    @Autowired
    private MequipmentMapper mequipmentMapper;
    @Autowired
    private TradeMapper tradeMapper;

    @Autowired
    private CgfService cgfService;
    /** (非 Javadoc) 
    *  注册 或 村保存企业
    */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void registar(User user, Result result, User user_d, Integer villageId) throws Exception {

        user.setUserType(5);
        user.setPsw(EncryptUtil.encrypt(user.getPsw()));
        user.setCreateTime(new Date());
        if(StringUtils.isEmpty(user.getIsFreeze())) {
            user.setIsFreeze("0");
        }
        user.setIsDel("0");
        userMapper.insertSelective(user);
        
        Company co = new Company();
        co.setName(user.getUserName());
        co.setUserId(user.getId());
        co.setVillageId(villageId);
        co.setIsKey(0);
        companyMapper.insertSelective(co);
        
        Integer id = user.getId();
        Regulation rg = new Regulation();
        rg.setUserId(id);
        regulationMapper.insertSelective(rg);
        
        
        if (null != user_d && user_d.getUserType() == 10) {
            TradeCompany tradeCompany = new TradeCompany();
            tradeCompany.setTid(user_d.getId());
            tradeCompany.setUid(user.getId());
            tradeCompany.setIskey(0);
            tradeCompanyMapper.insertSelective(tradeCompany);
        }
    }

    /**
     * (非 Javadoc) 批量导入企业会员
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void companyImportSave(Result result, MultipartFile file, Integer villageId) throws Exception {
        if (null != file) {
            String fileName = file.getOriginalFilename();
            // 检查扩展名
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            if (!"xlsx".equalsIgnoreCase(fileExt)) {
                result.setStatus("1");
                result.setMap("message", "上传文件扩展名是不允许的扩展名。只允许xlsx格式。");
                return;
            }
        } else {
            result.setStatus("1");
            result.setMap("message", "请选择excel文件。");
            return;
        }
        Workbook wb = null;

        String messages = "";
        
        List<Company> companyInfoList = new ArrayList<Company>();
        try {
            wb = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = wb.getSheetAt(0);
            String title = tos(sheet, 0, 0);
            if (!"企业全称".equals(title)) {
                result.setStatus("1");
                result.setMap("message", "此表不是企业信息表，请重新选择");
                return;
            }
            int totalRows = sheet.getPhysicalNumberOfRows();
            List<User> d_userList = userMapper.selectAllUser();
            List<Company> d_company = companyMapper.selectAllCompany();
            List<String> nameList = new ArrayList<String>();
            
            for (int i = 1; i < totalRows; i++) {
                Row row = sheet.getRow(i);
                String userName = row.getCell(0).toString().trim();
                if (null != userName && !"".equals(userName)) {
                    for (Company companyInfo : d_company) {
                        if (companyInfo.getName().equalsIgnoreCase(userName)) {
                            
                            if(ExcelUtil.getCellData(row.getCell(2)) != null){
                                companyInfo.setEmail(ExcelUtil.getCellData(row.getCell(2)).split("、")[0]);
                            }
                            if(ExcelUtil.getCellData(row.getCell(1)) != null){
                                companyInfo.setLegalContact(ExcelUtil.getCellData(row.getCell(1)).split("、")[0]);
                            }
                            companyInfo.setLegal(ExcelUtil.getCellData(row.getCell(3)));
                            companyInfo.setLicense(ExcelUtil.getCellData(row.getCell(4)));
                            companyInfo.setEstablish(ExcelUtil.getCellData(row.getCell(5)));
                            companyInfo.setCapital(ExcelUtil.getCellData(row.getCell(6)));
                            companyInfo.setAddress(ExcelUtil.getCellData(row.getCell(7)));
                            companyInfo.setBusiness(ExcelUtil.getCellData(row.getCell(8)));
                            companyInfo.setIndustry2(ExcelUtil.getCellData(row.getCell(9)));
                            if(ExcelUtil.getCellData(row.getCell(9)) != null){
                                companyInfo.setIndustry(ConstantsUtil.getIndustryByIndustry2(ExcelUtil.getCellData(row.getCell(9))));
                            }
                            companyInfo.setScope(ExcelUtil.getCellData(row.getCell(10)));
                            companyMapper.updateByPrimaryKeySelective(companyInfo);
                            continue;
                        }
                    }
                    
                    boolean d_flag = false;
                    for (User user : d_userList) {
                        if (user.getUserName().equalsIgnoreCase(userName)) {
                            d_flag = true;
                            break;
                        }
                    }
                    
                    boolean n_flag = false;
                    for (String name : nameList) {
                        if (name.equalsIgnoreCase(userName)) {
                            n_flag = true;
                            break;
                        }
                    }
                    if (d_flag || n_flag) {
                        messages += "第" + i + "行\t" + "企业全称已存在、" + "\r\n";
                    } else {
                        User user = new User();
                        user.setUserName(userName);
                        user.setPsw(ConstantsUtil.ENCRYPT_PASSWORD_123456);
                        user.setUserType(5);
                        user.setIsFreeze("0");
                        user.setCreateTime(new Date());
                        user.setIsDel("0");
                        userMapper.insertSelective(user);
                        
                        Company companyInfo = new Company();
                        companyInfo.setUserId(user.getId());
                        companyInfo.setName(user.getUserName());
                        if(ExcelUtil.getCellData(row.getCell(2)) != null){
                            companyInfo.setEmail(ExcelUtil.getCellData(row.getCell(2)).split("、")[0]);
                        }
                        if(ExcelUtil.getCellData(row.getCell(1)) != null){
                            companyInfo.setLegalContact(ExcelUtil.getCellData(row.getCell(1)).split("、")[0]);
                        }
                        companyInfo.setLegal(ExcelUtil.getCellData(row.getCell(3)));
                        companyInfo.setLicense(ExcelUtil.getCellData(row.getCell(4)));
                        companyInfo.setEstablish(ExcelUtil.getCellData(row.getCell(5)));
                        companyInfo.setCapital(ExcelUtil.getCellData(row.getCell(6)));
                        companyInfo.setAddress(ExcelUtil.getCellData(row.getCell(7)));
                        companyInfo.setBusiness(ExcelUtil.getCellData(row.getCell(8)));
                        companyInfo.setIndustry2(ExcelUtil.getCellData(row.getCell(9)));
                        if(ExcelUtil.getCellData(row.getCell(9)) != null){
                            companyInfo.setIndustry(ConstantsUtil.getIndustryByIndustry2(ExcelUtil.getCellData(row.getCell(9))));
                        }
                        companyInfo.setScope(ExcelUtil.getCellData(row.getCell(10)));
                        companyInfo.setVillageId(villageId);
                        
                        companyInfoList.add(companyInfo);
                        
                        nameList.add(userName);
                    }
                }
            }
            
            if(companyInfoList.size() > 0) {
                companyMapper.insertBatch(companyInfoList);
            }
        } catch (Exception e) {
            
        }
        if(messages.length() > 3){
            messages = messages.substring(0,messages.length() - 3);
        }
        result.setStatus("0");
        result.setMap("count", companyInfoList.size());
        result.setMess(messages);
    }
    
    /**
     * (非 Javadoc) 批量导入持证上岗人员名单
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void spersonImportSave(Result result, Integer flag, MultipartFile file) throws Exception {
        if (null != file) {
            String fileName = file.getOriginalFilename();
            // 检查扩展名
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            if (!"xlsx".equalsIgnoreCase(fileExt)) {
                result.setStatus("1");
                result.setMap("message", "上传文件扩展名是不允许的扩展名。只允许xlsx格式。");
                return;
            }
        } else {
            result.setStatus("1");
            result.setMap("message", "请选择excel文件。");
            return;
        }
        Workbook wb = null;
        String messages = "";
        try {
            wb = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = wb.getSheetAt(0);
            String title = tos(sheet, 0, 0);
            if (!"姓名".equals(title)) {
                result.setStatus("1");
                result.setMap("message", "此表不是人员名单表，请重新选择");
                return;
            }
            int totalRows = sheet.getPhysicalNumberOfRows();
            
            List<Company> d_company = companyMapper.selectAllCompany();
            
            List<Sperson> list = new ArrayList<Sperson>();
            List<Sperson> spersonInfoList = new ArrayList<Sperson>();
            for (int i = 1; i < totalRows; i++) {
                Row row = sheet.getRow(i);
                String userName = row.getCell(1).toString().trim();
                String name = row.getCell(0).toString().trim();
                Map<String, Object> map = new HashMap<String, Object>();
                if (null != userName && !"".equals(userName)) {
                    Integer userId = null;
                    for (Company user : d_company) {
                        if (user.getName().equalsIgnoreCase(userName)) {
                            userId = user.getUserId();
                        }
                    }
                    if (userId != null) {
                        map.put("userId", userId);
                        list = spersonMapper.selectAllUser(map);
                    }
                    
                    if (null == userId) {
                        messages += "第" + i + "行\t" + "单位不存在、" + "\r\n";
                    } else {
                        boolean h = false;
                        if(list.size() != 0){
                            for (Sperson sperson : list) {
                                if(sperson.getName().equalsIgnoreCase(name) 
                                		&& sperson.getFlag().intValue() == flag 
                                		&& sperson.getUserId().intValue() == userId){
                                	sperson.setType(ExcelUtil.getCellData(row.getCell(4)));
                                	sperson.setReviewTime(ExcelUtil.getCellData(row.getCell(2)));
                                	sperson.setForensicTime(ExcelUtil.getCellData(row.getCell(3)));
                                	sperson.setCertificateNumber(ExcelUtil.getCellData(row.getCell(5)));
                                	sperson.setRemark(ExcelUtil.getCellData(row.getCell(6)));
                                    spersonMapper.updateByPrimaryKeySelective(sperson);
                                    h = true;
                                    break;
                                }
                            }
                        }
                        
                        if(!h) {
                            Sperson spersonInfo = new Sperson();
                            spersonInfo.setUserId(userId);
                            spersonInfo.setFlag(flag);
                            spersonInfo.setName(ExcelUtil.getCellData(row.getCell(0)));
                            spersonInfo.setType(ExcelUtil.getCellData(row.getCell(4)));
                            spersonInfo.setReviewTime(ExcelUtil.getCellData(row.getCell(2)));
                            spersonInfo.setForensicTime(ExcelUtil.getCellData(row.getCell(3)));
                            spersonInfo.setCertificateNumber(ExcelUtil.getCellData(row.getCell(5)));
                            spersonInfo.setRemark(ExcelUtil.getCellData(row.getCell(6)));
                            spersonInfoList.add(spersonInfo);
                            //nameList.add(userName);
                        }
                    }
                }
            }
            spersonMapper.insertSpersonBatch(spersonInfoList);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        if(messages.length() > 3){
            messages = messages.substring(0,messages.length() - 3);
        }
        result.setStatus("0");
        result.setMess(messages);
    }
    
    /**
     * (非 Javadoc) 集团企业批量导入持证上岗人员名单
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void spersonImportSaveForClique(Result result, Integer flag, MultipartFile file) throws Exception {
        if (null != file) {
            String fileName = file.getOriginalFilename();
            // 检查扩展名
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            if (!"xlsx".equalsIgnoreCase(fileExt)) {
                result.setStatus("1");
                result.setMap("message", "上传文件扩展名是不允许的扩展名。只允许xlsx格式。");
                return;
            }
        } else {
            result.setStatus("1");
            result.setMap("message", "请选择excel文件。");
            return;
        }
        Workbook wb = null;
        String messages = "";
        try {
            wb = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = wb.getSheetAt(0);
            String title = tos(sheet, 0, 0);
            if (!"姓名".equals(title)) {
                result.setStatus("1");
                result.setMap("message", "此表不是人员名单表，请重新选择");
                return;
            }
            int totalRows = sheet.getPhysicalNumberOfRows();
            
            List<Company> d_company = companyMapper.selectAllCompany();
            List<Trade> d_clique = tradeMapper.selectAllClique();
            
            List<Sperson> list = new ArrayList<Sperson>();
            List<Sperson> spersonInfoList = new ArrayList<Sperson>();
            for (int i = 1; i < totalRows; i++) {
                Row row = sheet.getRow(i);
                String userName = row.getCell(1).toString().trim();
                String name = row.getCell(0).toString().trim();
                Map<String, Object> map = new HashMap<String, Object>();
                if (null != userName && !"".equals(userName)) {
                    Integer userId = null;
                    for (Company user : d_company) {
                        if (user.getName().equalsIgnoreCase(userName)) {
                            userId = user.getUserId();
                            break;
                        }
                    }
                    if(userId == null){
                    	for (Trade user : d_clique) {
                            if (user.getName().equalsIgnoreCase(userName)) {
                                userId = user.getUserId();
                                break;
                            }
                        }
                    }
                    if (userId != null) {
                        map.put("userId", userId);
                        list = spersonMapper.selectAllUser(map);
                    }
                    
                    if (null == userId) {
                        messages += "第" + i + "行\t" + "单位不存在、" + "\r\n";
                    } else {
                        boolean h = false;
                        if(list.size() != 0){
                            for (Sperson sperson : list) {
                                if(sperson.getName().equalsIgnoreCase(name) 
                                		&& sperson.getFlag().intValue() == flag 
                                		&& sperson.getUserId().intValue() == userId){
                                	sperson.setType(ExcelUtil.getCellData(row.getCell(4)));
                                	sperson.setReviewTime(ExcelUtil.getCellData(row.getCell(2)));
                                	sperson.setForensicTime(ExcelUtil.getCellData(row.getCell(3)));
                                	sperson.setCertificateNumber(ExcelUtil.getCellData(row.getCell(5)));
                                	sperson.setRemark(ExcelUtil.getCellData(row.getCell(6)));
                                    spersonMapper.updateByPrimaryKeySelective(sperson);
                                    h = true;
                                    break;
                                }
                            }
                        }
                        
                        if(!h) {
                            Sperson spersonInfo = new Sperson();
                            spersonInfo.setUserId(userId);
                            spersonInfo.setFlag(flag);
                            spersonInfo.setName(ExcelUtil.getCellData(row.getCell(0)));
                            spersonInfo.setType(ExcelUtil.getCellData(row.getCell(4)));
                            spersonInfo.setReviewTime(ExcelUtil.getCellData(row.getCell(2)));
                            spersonInfo.setForensicTime(ExcelUtil.getCellData(row.getCell(3)));
                            spersonInfo.setCertificateNumber(ExcelUtil.getCellData(row.getCell(5)));
                            spersonInfo.setRemark(ExcelUtil.getCellData(row.getCell(6)));
                            spersonInfoList.add(spersonInfo);
                            //nameList.add(userName);
                        }
                    }
                }
            }
            spersonMapper.insertSpersonBatch(spersonInfoList);
        } catch (Exception e) {
        }
        if(messages.length() > 3){
            messages = messages.substring(0,messages.length() - 3);
        }
        result.setStatus("0");
        result.setMess(messages);
    }
    
    /**
     * (非 Javadoc) 批量导入特种设备
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void sequipmentImportSave(Result result, MultipartFile file) throws Exception {
        if (null != file) {
            String fileName = file.getOriginalFilename();
            // 检查扩展名
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            if (!"xlsx".equalsIgnoreCase(fileExt)) {
                result.setStatus("1");
                result.setMap("message", "上传文件扩展名是不允许的扩展名。只允许xlsx格式。");
                return;
            }
        } else {
            result.setStatus("1");
            result.setMap("message", "请选择excel文件。");
            return;
        }
        
        Workbook wb = null;
        Set<String> set = new LinkedHashSet<String>();
        try {
            wb = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = wb.getSheetAt(0);
            String title = tos(sheet, 0, 0);
            if (!"设备档案号".equals(title)) {
                result.setStatus("1");
                result.setMap("message", "此表不是特种设备表，请重新选择");
                return;
            }
            
            int totalRows = sheet.getPhysicalNumberOfRows();
            Map<String, Object> m = new HashMap<String, Object>();
            List<LlHashMap<String, Object>> list = sequipmentMapper.selectSequipmentList(m);
            
            Map<String, Integer> companyMap = new HashMap<String, Integer>();
            
            List<Sequipment> l = new LinkedList<Sequipment>();
            for (int i = 1; i < totalRows; i++) {
                Row row = sheet.getRow(i);
                String fileNumber = tos(row, 0);//设备档案号
                if(StringUtils.isEmpty(fileNumber)) {
                    continue;
                }
                String se_name = tos(row, 1);//设备名称
                String type = tos(row, 2);//设备类型，型号
                String companyName = tos(row, 3);
                String expirationTime = tos(row, 4);
                Integer userId = getUserIdByCompanyName(companyMap, companyName);
                if(null != userId) {
                    Sequipment s = new Sequipment(null, userId, se_name, type, fileNumber, null, "", expirationTime, "");
                    
                    Integer c = checkSequipment(list, s);
                    if(c.intValue() == 0) {//新增
                        l.add(s);
                    } else if(c.intValue() != -1) {//编辑
                        s.setId(c);
                        sequipmentMapper.updateByPrimaryKeySelective(s);
                    }
                } else {
                    set.add("企业" + companyName + "不存在");
                }
            }
            
            if(l.size() > 0) {
                m.put("list", l);
                sequipmentMapper.insertBatch2(m);
            }
            if(set.size() > 0) {
                result.setStatus("2");
            }
            result.setMap("message", StringUtils.join(set, "<br>"));
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMap("message", "系统异常");
        }
    }
    
    /**
     * (非 Javadoc) 集团批量导入特种设备
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void sequipmentImportSaveForClique(Result result, MultipartFile file) throws Exception {
        if (null != file) {
            String fileName = file.getOriginalFilename();
            // 检查扩展名
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            if (!"xlsx".equalsIgnoreCase(fileExt)) {
                result.setStatus("1");
                result.setMap("message", "上传文件扩展名是不允许的扩展名。只允许xlsx格式。");
                return;
            }
        } else {
            result.setStatus("1");
            result.setMap("message", "请选择excel文件。");
            return;
        }
        
        Workbook wb = null;
        Set<String> set = new LinkedHashSet<String>();
        try {
            wb = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = wb.getSheetAt(0);
            String title = tos(sheet, 0, 0);
            if (!"设备档案号".equals(title)) {
                result.setStatus("1");
                result.setMap("message", "此表不是特种设备表，请重新选择");
                return;
            }
            
            int totalRows = sheet.getPhysicalNumberOfRows();
            Map<String, Object> m = new HashMap<String, Object>();
            List<LlHashMap<String, Object>> list = sequipmentMapper.selectSequipmentList(m);
            
            Map<String, Integer> companyMap = new HashMap<String, Integer>();
            
            List<Sequipment> l = new LinkedList<Sequipment>();
            for (int i = 1; i < totalRows; i++) {
                Row row = sheet.getRow(i);
                String fileNumber = tos(row, 0);//设备档案号
                if(StringUtils.isEmpty(fileNumber)) {
                    continue;
                }
                String se_name = tos(row, 1);//设备名称
                String type = tos(row, 2);//设备类型，型号
                String companyName = tos(row, 3);
                String expirationTime = tos(row, 4);
                Integer userId = getUserIdByCompanyName(companyMap, companyName);
                if(null != userId) {
                    Sequipment s = new Sequipment(null, userId, se_name, type, fileNumber, null, "", expirationTime, "");
                    
                    Integer c = checkSequipment(list, s);
                    if(c.intValue() == 0) {//新增
                        l.add(s);
                    } else if(c.intValue() != -1) {//编辑
                        s.setId(c);
                        sequipmentMapper.updateByPrimaryKeySelective(s);
                    }
                } else {
                    set.add("企业" + companyName + "不存在");
                }
            }
            
            if(l.size() > 0) {
                m.put("list", l);
                sequipmentMapper.insertBatch2(m);
            }
            if(set.size() > 0) {
                result.setStatus("2");
            }
            result.setMap("message", StringUtils.join(set, "<br>"));
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMap("message", "系统异常");
        }
    }
    
    /** (非 Javadoc) 
     *  企业更改名称
     */
     @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
     public void editName(User user, Result result, User user2, Integer id , String userName) throws Exception {

         if(null == user){
             result.setStatus("1");
             result.setMap("message", "企业不存在！");
         }else {
             if(user.getUserName() == userName){
                 result.setStatus("1");
                 result.setMap("message", "更改名称不能与原名称相同！");
             }else{
                 if (null == user2) {
                     user.setId(id);
                     user.setUserName(userName);
                     userMapper.updateByPrimaryKeySelective(user);
                     Company co = companyMapper.selectByPrimaryKey(id);
                     co.setName(userName);
                     companyMapper.updateByPrimaryKeySelective(co);
                 }else{
                     result.setStatus("1");
                     result.setMap("message", "该更改企业名称已存在！");
                 }
             }
         }
     }
    
    /** 
    * @param l
    * @param se
    * @return 0 新增 -1 不操作 2跟新 传id
    */
    Integer checkSequipment(List<LlHashMap<String, Object>> l, Sequipment se) {
        for(LlHashMap<String, Object> ll : l) {
            Integer userId = ll.getInteger("userId");
            String seName = ll.getString("seName");
            String type = ll.getString("type");
            String fileNumber = ll.getString("fileNumber");
            String expirationTime = ll.getString("expirationTime");
            
            if(se.getUserId().compareTo(userId) == 0 && seName.equals(se.getSeName()) && type.equals(se.getType())
                    && fileNumber.equals(se.getFileNumber())) {
                if(expirationTime.equals(se.getExpirationTime())) {
                    return -1;
                } else {
                    return ll.getInteger("id");
                }
            }
        }
        return 0;
    }
    
    Integer getUserIdByCompanyName(Map<String, Integer> companyMap, String companyName) {
        Integer id = companyMap.get("companyName");
        if(null == id) {
            id = userMapper.selectAllUser5(companyName);
            companyMap.put(companyName, null == id ? -1 : id);
        } else if(id.intValue() == -1) {
            return null;
        }
        return id;
    }
    
    Integer getUserIdForClique(Map<String, Integer> companyMap, String companyName) {
        Integer id = companyMap.get("companyName");
        if(null == id) {
            id = userMapper.selectAllUser5(companyName);
            if(id == null){
            	id = tradeMapper.selectByNameForClique(companyName);
            }
            companyMap.put(companyName, null == id ? -1 : id);
        } else if(id.intValue() == -1) {
            return null;
        }
        return id;
    }
    
    Integer youwu(String v) {
        if ("有".equals(v)) {
            return 1;
        }
        if ("无".equals(v)) {
            return 0;
        }
        return null;
    }

    DecimalFormat df = new DecimalFormat("#");

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
        if (null == rowx) {
            return null;
        }
        Cell cell = rowx.getCell(col);
        return tos(cell);
    }
    
    public List<Integer> selectCount(CompanyListReqDTO dto, User user) {
        Map<String, Object> m = new HashMap<String, Object>();
        if(StringUtils.isBlank(dto.getUserIds())) {
            //查询所含企业ids
            List<Integer> list = cgfService.getCompanyUserIds(dto, user);
            if(list.isEmpty()){
                m.put("userIds",-1);
            }else{
                m.put("userIds", StringUtils.join(list, ","));
            }
        } else {
            m.put("userIds", dto.getUserIds());
        }
        
        Date n = new Date();
        Date th = DateConvertUtil.addMonths(n, 3);
        Date six = DateConvertUtil.addMonths(n, 6);
        m.put("time", DateFormatUtils.format(th, "yyyy-MM"));
        m.put("time1", DateFormatUtils.format(th, "yyyy-MM-dd"));
        m.put("time2", DateFormatUtils.format(six, "yyyy-MM"));
        m.put("time3", DateFormatUtils.format(six, "yyyy-MM-dd"));
        m.put("now", DateFormatUtils.format(n, "yyyy-MM"));
        m.put("now1", DateFormatUtils.format(n, "yyyy-MM-dd"));
        
//        if(userType.intValue() == 9) {//全国
//            return companyMapper.selectYuCountAll(m);
//        }
//        if(userType.intValue() == 5) {//企业
//            return companyMapper.selectYuCount(m);
//        }
        return companyMapper.selectYuCountAll(m);
    }
    
    @Override
    public TCleanWarnSetting geTCleanWarnSetting(Integer creator) {
        List<TCleanWarnSetting> settings = tcleanWarnSettingMapper.getCleanSettings(creator);
        if(null != settings && settings.size() > 0){
            return settings.get(0);
        }
        return null;
    }

    @Override
    public boolean updateCleanWarnSetting(TCleanWarnSetting setting) {
        List<TCleanWarnSetting> settings = tcleanWarnSettingMapper.getCleanSettings(setting.getCreator());
        if(null != settings && settings.size() > 0){
            TCleanWarnSetting oldSetting = settings.get(0);
            oldSetting.setMaxTimes(setting.getMaxTimes());
            oldSetting.setUpdateName(setting.getUpdateName());
            oldSetting.setUpdateTime(new Date());
            return tcleanWarnSettingMapper.updateByPrimaryKeySelective(oldSetting)>0?true:false;
        }else{
            return tcleanWarnSettingMapper.insertSelective(setting)>0?true:false;
        }
    }
    
    /**
     * (非 Javadoc) 批量导入特种设备
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void importSequipmentExcel(Result result, Integer userId, MultipartFile file) throws Exception {
        if (null != file) {
            String fileName = file.getOriginalFilename();
            // 检查扩展名
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            if (!"xlsx".equalsIgnoreCase(fileExt)) {
                result.setStatus("1");
                result.setMap("message", "上传文件扩展名是不允许的扩展名。只允许xlsx格式。");
                return;
            }
        } else {
            result.setStatus("1");
            result.setMap("message", "请选择excel文件。");
            return;
        }
        
        Workbook wb = null;
        Set<String> set = new LinkedHashSet<String>();
        try {
            wb = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = wb.getSheetAt(0);
            String title = tos(sheet, 0, 0);
            if (!"特种设备名称".equals(title)) {
                result.setStatus("1");
                result.setMap("message", "此表不是特种设备表，请重新选择");
                return;
            }
            
            int totalRows = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < totalRows; i++) {
                Row row = sheet.getRow(i);
                String seName = tos(row, 0);//设备名称
                String type = tos(row, 1);//设备类型，型号
                String fileNumber = tos(row, 2);//设备档案号
                String detectionTime = tos(row, 3);
                String expirationTime = tos(row, 4);
                String remark = tos(row, 5);
                
                Sequipment item = new Sequipment();
                item.setUserId(userId);
                item.setSeName(seName);
                item.setType(type);
                item.setFileNumber(fileNumber);
                item.setAmount(1);
                item.setDetectionTime(detectionTime);
                item.setExpirationTime(expirationTime);
                item.setRemark(remark);
                sequipmentMapper.insertSelective(item);
            }
            
            result.setMap("message", StringUtils.join(set, "<br>"));
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMap("message", "系统异常");
        }
    }
    
    /**
     * (非 Javadoc) 批量导入主要设备
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void importMajorequipmentExcel(Result result, Integer userId, MultipartFile file) throws Exception {
        if (null != file) {
            String fileName = file.getOriginalFilename();
            // 检查扩展名
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            if (!"xlsx".equalsIgnoreCase(fileExt)) {
                result.setStatus("1");
                result.setMap("message", "上传文件扩展名是不允许的扩展名。只允许xlsx格式。");
                return;
            }
        } else {
            result.setStatus("1");
            result.setMap("message", "请选择excel文件。");
            return;
        }
        
        Workbook wb = null;
        Set<String> set = new LinkedHashSet<String>();
        try {
            wb = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = wb.getSheetAt(0);
            String title = tos(sheet, 0, 0);
            if (!"主要设备名称".equals(title)) {
                result.setStatus("1");
                result.setMap("message", "此表不是主要设备表，请重新选择");
                return;
            }
            
            int totalRows = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < totalRows; i++) {
                Row row = sheet.getRow(i);
                String equipmentName = tos(row, 0);//设备名称
                String size = tos(row, 1);//设备类型，型号
                String processParameters = tos(row, 2);//设备档案号
                String amount = tos(row, 3);
                String remark = tos(row, 4);
                
                Mequipment item = new Mequipment();
                item.setUserId(userId);
                item.setEquipmentName(equipmentName);
                item.setSize(size);
                item.setProcessParameters(processParameters);
                if(null != amount && !amount.equals("") ){
                    item.setAmount(Integer.valueOf(amount));
                }
                item.setRemark(remark);
                mequipmentMapper.insertSelective(item);
            }
            
            result.setMap("message", StringUtils.join(set, "<br>"));
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMap("message", "系统异常");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void importPersonExcel(Result result, Integer userId, MultipartFile file) throws Exception {
        if (null != file) {
            String fileName = file.getOriginalFilename();
            // 检查扩展名
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            if (!"xlsx".equalsIgnoreCase(fileExt)) {
                result.setStatus("1");
                result.setMap("message", "上传文件扩展名是不允许的扩展名。只允许xlsx格式。");
                return;
            }
        } else {
            result.setStatus("1");
            result.setMap("message", "请选择excel文件。");
            return;
        }

        Workbook wb = null;
        Set<String> set = new LinkedHashSet<String>();
        try {
            wb = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = wb.getSheetAt(0);
            String title = tos(sheet, 0, 0);
            if (!"人员名称".equals(title)) {
                result.setStatus("1");
                result.setMap("message", "此表不是人员表，请重新选择");
                return;
            }

            int totalRows = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < totalRows; i++) {
                Row row = sheet.getRow(i);
                String name = tos(row, 0);//人员姓名
                String mobile = tos(row, 1);//人员电话
                String cid = tos(row, 2);//所属公司编号
                String dpid = tos(row, 3);//所属部门编号
                String did = tos(row, 4);//所在班组
                String uid = tos(row, 5);//所属公司ID
                String position = tos(row, 6);//所属权限
                String status = tos(row, 7);//所属状态

                ZzjgPersonnel item = new ZzjgPersonnel();
                item.setCtime(new Date());
                item.setUtime(new Date());
                item.setDel(0);
                item.setName(name);
                item.setMobile(mobile);
                item.setCid(Integer.parseInt(cid));
                item.setDpid(Integer.parseInt(dpid));
                item.setDid(Integer.parseInt(did));
                item.setUid(Integer.parseInt(uid));
                item.setPosition(position);
                item.setStatus(status);

                zzjgPersonnelMapper.save(item);
            }

            result.setMap("message", StringUtils.join(set, "<br>"));
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMap("message", "系统异常");
        }
    }

}
