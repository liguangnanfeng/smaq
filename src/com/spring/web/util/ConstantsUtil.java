package com.spring.web.util;

/**
 * @Title: ConstantsUtil
 * @Description: 常量工具类
 * @author FL
 * @date 2016年1月12日 下午4:22:33
 * @version V1.0
 */
public class ConstantsUtil {
    
    //与industry.js 中industry2 对应
    public final static String[][] industry2_ = new String[][] {
        {"纺织业", "纺织行业"},
        {"纺织服装、服饰业", "纺织行业"},
        {"化学原料和化学制品制造业", "化工企业（危险化学品生产、经营、使用）、加油站"},
        {"医药制造业", "化工企业（危险化学品生产、经营、使用）、加油站"},
        {"化学纤维制造业", "化工企业（危险化学品生产、经营、使用）、加油站"},
        {"橡胶和塑料制品业", "轻工行业"},
        {"非金属矿物制品业", "其他行业"},
        {"黑色金属冶炼和压延加工业", "冶金行业"},
        {"有色金属冶炼和压延加工业", "有色行业"},
        {"金属制品业", "机械行业"},
        {"通用设备制造业", "机械行业"},
        {"专用设备制造业", "机械行业"},
        {"汽车制造业", "机械行业"},
        {"铁路、船舶、航空航天和其他运输设备制造业", "机械行业"},
        {"电气机械和器材制造业", "机械行业"},
        {"计算机、通信和其他电子设备制造业", "机械行业"},
        {"仪器仪表制造业", "其他行业"},
        {"其他制造业", "其他行业"},
        {"废弃资源综合利用业", "其他行业"},
        {"电力、热力生产和供应业", "其他行业"},
        {"燃气生产和供应业", "其他行业"},
        {"水的生产和供应业", "其他行业"},
        {"农副食品加工业", "轻工行业"},
        {"食品制造业", "轻工行业"},
        {"酒、饮料和精制茶制造业", "轻工行业"},
        {"烟草制品业", "烟草行业"},
        {"皮革、毛皮、羽毛及其制品和制鞋业", "轻工行业"},
        {"木材加工和木、竹、藤、棕、草制品业", "轻工行业"},
        {"家具制造业", "轻工行业"},
        {"造纸和纸制品业", "轻工行业"},
        {"印刷和记录媒介复制业", "轻工行业"},
        {"文教、工美、体育和娱乐用品制造业", "轻工行业"},
        {"石油加工、炼焦和核燃料加工业", "轻工行业"},
        {"批发业", "商贸行业"},
        {"零售业", "商贸行业"},
        {"租赁业", "商贸行业"},
        {"商务服务业", "商贸行业"},
        {"居民服务业", "商贸行业"},
        {"机动车、电子产品和日用产品修理业", "商贸行业"},
        {"其他服务业", "商贸行业"}
    };
    
    public static String getIndustryByIndustry2(String industry2) {
        String[][] industry2_ = ConstantsUtil.industry2_;
        for(String[] in : industry2_) {
            if(industry2.indexOf(in[0]) > -1) {
                return in[1];
            }
        }
        return "";
    }
    
    public final static Integer SELECT_COUNT = 10;
    
    public final static String PROJECT_CONTENT = "http://www.wxelife.com";
    /**
     * @Fields LOGIN_VALIDATE_CODE : TODO(手机验证码找回密码 存的验证码)
     */
    public static final String FIND_PASSWORD_VALIDATE_CODE = "find_password_validateCode";
    /**
     * @Fields LOGIN_VALIDATE_CODE : TODO(手机验证码注册 存的验证码)
     */
    public static final String REGISTAR_VALIDATE_CODE = "registar_validateCode";
    /**
     * @Fields LOGIN_VALIDATE_CODE : TODO(手机验证码登录 存的验证码)
     */
    public static final String LOGIN_VALIDATE_CODE = "login_validateCode";
    /**
     * @Fields COOKIE_USERNAEM : TODO(cookie保存登录用户名)
     */
    public static final String COOKIE_USERNAEM = "thshop_username";
    /**
     * @Fields COOKIE_VALIDATE : TODO(自动登录 cookie存的token)
     */
    public static final String COOKIE_VALIDATE = "thshop_validate";
    /**
     * @Fields SESSION_SHOP : TODO(商户登录时 将商户存到session)
     */
    public static final String SESSION_SHOP = "session_shop";
    /**
     * 日期格式
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 日期时间格式
     */
    public final static String DATE_TIME_PATTER = "yyyy-MM-dd HH:mm:ss";
    /**
     * 邮件服务器
     */
    public final static String EMAIL_HOST = "smtp.163.com";
    /**
     * email源地址
     */
    public final static String EMAIL_FROM = "zhang.da.hai@163.com";
    /**
     * emai用户名
     */
    public final static String EMAIL_USERNAME = "zhang.da.hai@163.com";
    /**
     * email密码
     */
    public final static String EMAIL_PASSWORD = "zdh05574270053";
    /**
     * 加密后123456
     */
    public final static String ENCRYPT_PASSWORD_123456 = "e5c64652252ba27a80cc0698937e5ec622b67a8654b0d8cd20168cc3312bb0bb26792431082a4749";
    /**
     * bigdecimal 默认值为零
     */
    public final static Integer BIG_DECIMAL_DEFAULT_VALUE = 0;
    /**
     * 文本 默认值为""
     */
    public final static String TEXT_DEFAULT_VALUE = "";
    /**
     * 初始化行记录数
     */
    public final static Integer MAX_ROW = 10;
    /**
     * 0 int型
     */
    public final static int ZERO_INT = 0;
    /**
     * 1 int型
     */
    public final static int ONE_INT = 1;
    /**
     * 2 int型
     */
    public final static int TWO_INT = 2;
    /**
     * 3 int型
     */
    public final static int THREE_INT = 3;
    /**
     * 4 int型
     */
    public final static int FOUR_INT = 4;
    /**
     * 5 int型
     */
    public final static int FIVE_INT = 5;
    /**
     * 6 int型
     */
    public final static int SIX_INT = 6;
    /**
     * 7 int型
     */
    public final static int SEVEN_INT = 7;
    /**
     * 8 int型
     */
    public final static int EIGHT_INT = 8;
    /**
     * 9 int型
     */
    public final static int NINE_INT = 9;
    /**
     * 0 String型
     */
    public final static String ZERO_STRING = "0";
    /**
     * 1 String型
     */
    public final static String ONE_STRING = "1";
    /**
     * 2 String型
     */
    public final static String TWO_STRING = "2";
    /**
     * 3 String型
     */
    public final static String THREE_STRING = "3";
    /**
     * 4 String型
     */
    public final static String FOUR_STRING = "4";
    /**
     * 5 String型
     */
    public final static String FIVE_STRING = "5";
    /**
     * 6 String型
     */
    public final static String SIX_STRING = "6";
    /**
     * 7 String型
     */
    public final static String SEVEN_STRING = "7";
    /**
     * 8 String型
     */
    public final static String EIGHT_STRING = "8";
    /**
     * 9 String型
     */
    public final static String NINE_STRING = "9";
    /**
     * false Boolean型
     */
    public final static Boolean FALSE_BOOLEAN = false;
    /**
     * true Boolean型
     */
    public final static Boolean TRUE_BOOLEAN = true;
    /**
     * 用户类型2无密码登录用
     */
    public static final String NO_PASSWORD_LOGIN2 = "noPassword2";
    /**
     * 用户类型3无密码登录用
     */
    public static final String NO_PASSWORD_LOGIN3 = "noPassword3";
    /**
     * 用户类型4无密码登录用
     */
    public static final String NO_PASSWORD_LOGIN4 = "noPassword4";
    /**
     * 用户类型5无密码登录用
     */
    public static final String NO_PASSWORD_LOGIN5 = "noPassword5";
    /**
     * 无密码登录用
     */
    public static final String NO_PASSWORD_LOGIN_P = "123456";
    /**
     * 样式禁用
     */
    public final static String ZERO_TAG = "0";
    /**
     * 样式启用
     */
    public final static String ONE_TAG = "1";
    /***
     * 分页标签(1)
     */
    public final static Integer ONE_PAGE = 1;
    /**
     * 分页标签(3)
     */
    public final static Integer THREE_PAGE = 3;
    /**
     * 分页标签(4)
     */
    public final static Integer FOUR_PAGE = 4;
    /**
     * 分页标签(8)
     */
    public final static Integer EIGHT_PAGE = 8;
    /**
     * 分页标签(9)
     */
    public final static Integer NINE_PAGE = 9;
    /**
     * 分页标签(10)
     */
    public final static Integer TEN_PAGE = 10;
    /**
     * 分页标签(13)
     */
    public final static Integer THIRTEEN_PAGE = 13;
    /**
     * 分页标签(13)
     */
    public final static Integer FOURTEEN_PAGE = 14;
    /**
     * 分页标签(11)
     */
    public final static Integer ELEVEN_PAGE = 11;
    /**
     * 分页标签(6)
     */
    public final static Integer SIX_PAGE = 6;
    /**
     * 分页标签(7)
     */
    public final static Integer SEVEN_PAGE = 7;
}
