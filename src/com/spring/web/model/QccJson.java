/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.model;

import java.util.List;

/**
 * @Title: QccJson
 * @Description: TODO(用一句话描述该文件做什么)
 * @author FL
 * @date 2018年5月2日 下午5:51:58
 * @version V1.0
 */
public class QccJson {
    /**
     * Partners :
     * [{"StockName":"邵伟","StockType":"境内中国公民","StockPercent":"","ShouldCapi":"1.500000","ShoudDate":"2013年03月06日","InvestType":"货币","InvestName":"货币","RealCapi":"174.36万元人民币"
     * ,"CapiDate"
     * :"2013年03月06日"},{"StockName":"殷忠华","StockType":"境内中国公民","StockPercent":"","ShouldCapi":"72.462000","ShoudDate":null,"InvestType":null,"InvestName":null,"RealCapi":
     * "","CapiDate"
     * :null},{"StockName":"周建洪","StockType":"境内中国公民","StockPercent":"","ShouldCapi":"32.238000","ShoudDate":null,"InvestType":null,"InvestName":null,"RealCapi":"","CapiDate"
     * :null},
     * {"StockName":"姜亚南","StockType":"境内中国公民","StockPercent":"","ShouldCapi":"30.000000","ShoudDate":null,"InvestType":null,"InvestName":null,"RealCapi":"","CapiDate":null},
     * {"StockName":"金雯懿","StockType":"境内中国公民","StockPercent":"","ShouldCapi":"8.910000","ShoudDate":null,"InvestType":null,"InvestName":null,"RealCapi":"","CapiDate":null}]
     * Employees : [{"Name":"邵伟","Job":"执行董事"},{"Name":"邵伟","Job":"总经理"},{"Name":"殷忠华","Job":"监事"}] Branches : [] ChangeRecords :
     * [{"ProjectName":"企业住所变更","BeforeContent":"无锡新区新华路5号创新创意产业园D幢C区209室"
     * ,"AfterContent":"无锡新吴区新华路5号创新创意产业园D幢C区209室","ChangeDate":"2017-02-04T00:00:00+08:00"},{"ProjectName":"股东变更"
     * ,"BeforeContent":"邵伟,无锡微田园网络科技有限公司","AfterContent":"邵伟,殷忠华,周建洪,姜亚南,金雯懿","ChangeDate":"2017-02-04T00:00:00+08:00"},{"ProjectName":"经营范围","BeforeContent":
     * "电子产品、通信器材的设计和销售；计算机系统集成；计算机软硬件、农产品的销售；机械设备出租；互联网上从事各类商品的销售（不含许可项目）；网络平台、网络技术的开发；软件开发；技术开发、技术转让、技术咨询、技术服务；互联网信息服务。（依法须经批准的项目，经相关部门批准后方可开展经营活动）"
     * ,"AfterContent":"电子产品、通信器材的设计和销售；计算机系统集成；计算机软硬件、农产品、食品的销售；机械设备出租；互联网上从事各类商品的销售（不含许可项目）；网络平台、网络技术的开发；软件开发；技术开发、技术转让、技术咨询、技术服务；互联网信息服务。（依法须经批准的项目，经相关部门批准后方可开展经营活动）"
     * ,"ChangeDate"
     * :"2016-08-29T00:00:00+08:00"},{"ProjectName":"股东变更","BeforeContent":"邵伟,殷忠华,金雯懿,陈超,周建洪,姜亚南","AfterContent":"邵伟,无锡微田园网络科技有限公司","ChangeDate":"2015-12-18T00:00:00+08:00"
     * },{"ProjectName"
     * :"经营范围","BeforeContent":"电子产品、通信器材的设计和销售；计算机系统集成；计算机软硬件的销售；机械设备出租；互联网上从事各类商品的销售（不含许可项目）；网络平台、网络技术的开发；软件开发；技术开发、技术转让、技术咨询、技术服务；互联网信息服务。（依法须经批准的项目，经相关部门批准后方可开展经营活动）"
     * ,"AfterContent":"电子产品、通信器材的设计和销售；计算机系统集成；计算机软硬件、农产品的销售；机械设备出租；互联网上从事各类商品的销售（不含许可项目）；网络平台、网络技术的开发；软件开发；技术开发、技术转让、技术咨询、技术服务；互联网信息服务。（依法须经批准的项目，经相关部门批准后方可开展经营活动）","ChangeDate":
     * "2015-07-20T00:00:00+08:00"
     * },{"ProjectName":"股东变更","BeforeContent":"邵伟,殷忠华,金雯懿,陈超,周建洪","AfterContent":"邵伟,殷忠华,金雯懿,陈超,周建洪,姜亚南","ChangeDate":"2015-06-26T00:00:00+08:00"},{"ProjectName"
     * :"经营范围","BeforeContent":"电子产品、通信器材的设计和销售；计算机系统集成；计算机软硬件的销售；机械设备出租；互联网上从事各类商品的销售（不含许可项目）；网络平台、网络技术的开发；软件开发；技术开发、技术转让、技术咨询、技术服务。（依法须经批准的项目，经相关部门批准后方可开展经营活动）","AfterContent":
     * "电子产品、通信器材的设计和销售；计算机系统集成；计算机软硬件的销售；机械设备出租；互联网上从事各类商品的销售（不含许可项目）；网络平台、网络技术的开发；软件开发；技术开发、技术转让、技术咨询、技术服务；互联网信息服务。（依法须经批准的项目，经相关部门批准后方可开展经营活动）"
     * ,"ChangeDate":"2015-06-26T00:00:00+08:00"}] ContactInfo : {"WebSite":[{"Name":null,"Url":"www.lanlinweb.cn"}],"PhoneNumber":"0510-81022267","Email":"sw@lanlinweb.cn"}
     * Industry : {"Industry":"科学研究和技术服务业","SubIndustry":"研究和试验发展"} KeyNo : b1ffd5766786940c704a654c2b3c1fe9 Name : 无锡兰霖网络科技有限公司 No : 320213000112743 BelongOrg : 无锡市新吴区市场监督管理局
     * OperName : 邵伟 StartDate : 2009-07-02T00:00:00+08:00 EndDate : null Status : 在业 Province : JS UpdatedDate : 2018-05-02T11:50:50.798+08:00 CreditCode : 91320213691345656N
     * RegistCapi : 300万元人民币 EconKind : 有限责任公司 Address : 无锡新吴区新华路5号创新创意产业园D幢C区209室 Scope :
     * 电子产品、通信器材的设计和销售;计算机系统集成;计算机软硬件、农产品、食品的销售;机械设备出租;互联网上从事各类商品的销售(不含许可项目);网络平台、网络技术的开发;软件开发;技术开发、技术转让、技术咨询、技术服务;互联网信息服务。(依法须经批准的项目,经相关部门批准后方可开展经营活动) TermStart :
     * 2009-07-02T00:00:00+08:00 TeamEnd : null CheckDate : 2017-02-04T00:00:00+08:00 OrgNo : 69134565-6 IsOnStock : 0 StockNumber : null StockType : null OriginalName : null
     */
    private ContactInfoBean ContactInfo;
    private IndustryBean Industry;
    private String KeyNo;
    private String Name;
    private String No;
    private String BelongOrg;
    private String OperName;
    private String StartDate;
    private Object EndDate;
    private String Status;
    private String Province;
    private String UpdatedDate;
    private String CreditCode;
    private String RegistCapi;
    private String EconKind;
    private String Address;
    private String Scope;
    private String TermStart;
    private Object TeamEnd;
    private String CheckDate;
    private String OrgNo;
    private String IsOnStock;
    private Object StockNumber;
    private Object StockType;
    private Object OriginalName;
    private List<PartnersBean> Partners;
    private List<EmployeesBean> Employees;
    private List<?> Branches;
    private List<ChangeRecordsBean> ChangeRecords;

    public ContactInfoBean getContactInfo() {
        return ContactInfo;
    }

    public void setContactInfo(ContactInfoBean ContactInfo) {
        this.ContactInfo = ContactInfo;
    }

    public IndustryBean getIndustry() {
        return Industry;
    }

    public void setIndustry(IndustryBean Industry) {
        this.Industry = Industry;
    }

    public String getKeyNo() {
        return KeyNo;
    }

    public void setKeyNo(String KeyNo) {
        this.KeyNo = KeyNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getNo() {
        return No;
    }

    public void setNo(String No) {
        this.No = No;
    }

    public String getBelongOrg() {
        return BelongOrg;
    }

    public void setBelongOrg(String BelongOrg) {
        this.BelongOrg = BelongOrg;
    }

    public String getOperName() {
        return OperName;
    }

    public void setOperName(String OperName) {
        this.OperName = OperName;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    public Object getEndDate() {
        return EndDate;
    }

    public void setEndDate(Object EndDate) {
        this.EndDate = EndDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String Province) {
        this.Province = Province;
    }

    public String getUpdatedDate() {
        return UpdatedDate;
    }

    public void setUpdatedDate(String UpdatedDate) {
        this.UpdatedDate = UpdatedDate;
    }

    public String getCreditCode() {
        return CreditCode;
    }

    public void setCreditCode(String CreditCode) {
        this.CreditCode = CreditCode;
    }

    public String getRegistCapi() {
        return RegistCapi;
    }

    public void setRegistCapi(String RegistCapi) {
        this.RegistCapi = RegistCapi;
    }

    public String getEconKind() {
        return EconKind;
    }

    public void setEconKind(String EconKind) {
        this.EconKind = EconKind;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getScope() {
        return Scope;
    }

    public void setScope(String Scope) {
        this.Scope = Scope;
    }

    public String getTermStart() {
        return TermStart;
    }

    public void setTermStart(String TermStart) {
        this.TermStart = TermStart;
    }

    public Object getTeamEnd() {
        return TeamEnd;
    }

    public void setTeamEnd(Object TeamEnd) {
        this.TeamEnd = TeamEnd;
    }

    public String getCheckDate() {
        return CheckDate;
    }

    public void setCheckDate(String CheckDate) {
        this.CheckDate = CheckDate;
    }

    public String getOrgNo() {
        return OrgNo;
    }

    public void setOrgNo(String OrgNo) {
        this.OrgNo = OrgNo;
    }

    public String getIsOnStock() {
        return IsOnStock;
    }

    public void setIsOnStock(String IsOnStock) {
        this.IsOnStock = IsOnStock;
    }

    public Object getStockNumber() {
        return StockNumber;
    }

    public void setStockNumber(Object StockNumber) {
        this.StockNumber = StockNumber;
    }

    public Object getStockType() {
        return StockType;
    }

    public void setStockType(Object StockType) {
        this.StockType = StockType;
    }

    public Object getOriginalName() {
        return OriginalName;
    }

    public void setOriginalName(Object OriginalName) {
        this.OriginalName = OriginalName;
    }

    public List<PartnersBean> getPartners() {
        return Partners;
    }

    public void setPartners(List<PartnersBean> Partners) {
        this.Partners = Partners;
    }

    public List<EmployeesBean> getEmployees() {
        return Employees;
    }

    public void setEmployees(List<EmployeesBean> Employees) {
        this.Employees = Employees;
    }

    public List<?> getBranches() {
        return Branches;
    }

    public void setBranches(List<?> Branches) {
        this.Branches = Branches;
    }

    public List<ChangeRecordsBean> getChangeRecords() {
        return ChangeRecords;
    }

    public void setChangeRecords(List<ChangeRecordsBean> ChangeRecords) {
        this.ChangeRecords = ChangeRecords;
    }

    public static class ContactInfoBean {
        /**
         * WebSite : [{"Name":null,"Url":"www.lanlinweb.cn"}] PhoneNumber : 0510-81022267 Email : sw@lanlinweb.cn
         */
        private String PhoneNumber;
        private String Email;
        private List<WebSiteBean> WebSite;

        public String getPhoneNumber() {
            return PhoneNumber;
        }

        public void setPhoneNumber(String PhoneNumber) {
            this.PhoneNumber = PhoneNumber;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public List<WebSiteBean> getWebSite() {
            return WebSite;
        }

        public void setWebSite(List<WebSiteBean> WebSite) {
            this.WebSite = WebSite;
        }

        public static class WebSiteBean {
            /**
             * Name : null Url : www.lanlinweb.cn
             */
            private Object Name;
            private String Url;

            public Object getName() {
                return Name;
            }

            public void setName(Object Name) {
                this.Name = Name;
            }

            public String getUrl() {
                return Url;
            }

            public void setUrl(String Url) {
                this.Url = Url;
            }
        }
    }

    public static class IndustryBean {
        /**
         * Industry : 科学研究和技术服务业 SubIndustry : 研究和试验发展
         */
        private String Industry;
        private String SubIndustry;

        public String getIndustry() {
            return Industry;
        }

        public void setIndustry(String Industry) {
            this.Industry = Industry;
        }

        public String getSubIndustry() {
            return SubIndustry;
        }

        public void setSubIndustry(String SubIndustry) {
            this.SubIndustry = SubIndustry;
        }
    }

    public static class PartnersBean {
        /**
         * StockName : 邵伟 StockType : 境内中国公民 StockPercent : ShouldCapi : 1.500000 ShoudDate : 2013年03月06日 InvestType : 货币 InvestName : 货币 RealCapi : 174.36万元人民币 CapiDate :
         * 2013年03月06日
         */
        private String StockName;
        private String StockType;
        private String StockPercent;
        private String ShouldCapi;
        private String ShoudDate;
        private String InvestType;
        private String InvestName;
        private String RealCapi;
        private String CapiDate;

        public String getStockName() {
            return StockName;
        }

        public void setStockName(String StockName) {
            this.StockName = StockName;
        }

        public String getStockType() {
            return StockType;
        }

        public void setStockType(String StockType) {
            this.StockType = StockType;
        }

        public String getStockPercent() {
            return StockPercent;
        }

        public void setStockPercent(String StockPercent) {
            this.StockPercent = StockPercent;
        }

        public String getShouldCapi() {
            return ShouldCapi;
        }

        public void setShouldCapi(String ShouldCapi) {
            this.ShouldCapi = ShouldCapi;
        }

        public String getShoudDate() {
            return ShoudDate;
        }

        public void setShoudDate(String ShoudDate) {
            this.ShoudDate = ShoudDate;
        }

        public String getInvestType() {
            return InvestType;
        }

        public void setInvestType(String InvestType) {
            this.InvestType = InvestType;
        }

        public String getInvestName() {
            return InvestName;
        }

        public void setInvestName(String InvestName) {
            this.InvestName = InvestName;
        }

        public String getRealCapi() {
            return RealCapi;
        }

        public void setRealCapi(String RealCapi) {
            this.RealCapi = RealCapi;
        }

        public String getCapiDate() {
            return CapiDate;
        }

        public void setCapiDate(String CapiDate) {
            this.CapiDate = CapiDate;
        }
    }

    public static class EmployeesBean {
        /**
         * Name : 邵伟 Job : 执行董事
         */
        private String Name;
        private String Job;

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getJob() {
            return Job;
        }

        public void setJob(String Job) {
            this.Job = Job;
        }
    }

    public static class ChangeRecordsBean {
        /**
         * ProjectName : 企业住所变更 BeforeContent : 无锡新区新华路5号创新创意产业园D幢C区209室 AfterContent : 无锡新吴区新华路5号创新创意产业园D幢C区209室 ChangeDate : 2017-02-04T00:00:00+08:00
         */
        private String ProjectName;
        private String BeforeContent;
        private String AfterContent;
        private String ChangeDate;

        public String getProjectName() {
            return ProjectName;
        }

        public void setProjectName(String ProjectName) {
            this.ProjectName = ProjectName;
        }

        public String getBeforeContent() {
            return BeforeContent;
        }

        public void setBeforeContent(String BeforeContent) {
            this.BeforeContent = BeforeContent;
        }

        public String getAfterContent() {
            return AfterContent;
        }

        public void setAfterContent(String AfterContent) {
            this.AfterContent = AfterContent;
        }

        public String getChangeDate() {
            return ChangeDate;
        }

        public void setChangeDate(String ChangeDate) {
            this.ChangeDate = ChangeDate;
        }
    }
}
