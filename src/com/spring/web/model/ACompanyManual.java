package com.spring.web.model;

import java.util.Date;

public class ACompanyManual {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_company_manual_tbl.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_company_manual_tbl.uid
     *
     * @mbggenerated
     */
    private Integer uid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_company_manual_tbl.dmid
     *
     * @mbggenerated
     */
    private Integer dmid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_company_manual_tbl.level1
     *
     * @mbggenerated
     */
    private String level1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_company_manual_tbl.level2
     *
     * @mbggenerated
     */
    private String level2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_company_manual_tbl.level3
     *
     * @mbggenerated
     */
    private String level3;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_company_manual_tbl.factors
     *
     * @mbggenerated
     */
    private String factors;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_company_manual_tbl.type
     *
     * @mbggenerated
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_company_manual_tbl.measures
     *
     * @mbggenerated
     */
    private String measures;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_company_manual_tbl.reference
     *
     * @mbggenerated
     */
    private String reference;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_company_manual_tbl.flag
     *
     * @mbggenerated
     */
    private String flag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_company_manual_tbl.level
     *
     * @mbggenerated
     */
    private String level;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_company_manual_tbl.issys
     *
     * @mbggenerated
     */
    private Integer issys;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_company_manual_tbl.del
     *
     * @mbggenerated
     */
    private Integer del;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_company_manual_tbl.ctime
     *
     * @mbggenerated
     */
    private Date ctime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_company_manual_tbl.gkcs
     *
     * @mbggenerated
     */
    private String gkcs;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_company_manual_tbl.gkzt
     *
     * @mbggenerated
     */
    private String gkzt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_company_manual_tbl.fjgkfzr
     *
     * @mbggenerated
     */
    private String fjgkfzr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_company_manual_tbl.material
     *
     * @mbggenerated
     */
    private String material;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_company_manual_tbl.gy
     *
     * @mbggenerated
     */
    private String gy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_company_manual_tbl.hxys
     *
     * @mbggenerated
     */
    private String hxys;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_company_manual_tbl.wlbs
     *
     * @mbggenerated
     */
    private String wlbs;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_company_manual_tbl.flag2
     *
     * @mbggenerated
     */
    private String flag2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_company_manual_tbl.gwzy
     *
     * @mbggenerated
     */
    private String gwzy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_company_manual_tbl.lnglat
     *
     * @mbggenerated
     */
    private String lnglat;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_company_manual_tbl
     *
     * @mbggenerated
     */

    public ACompanyManual(Integer id, Integer uid, Integer dmid, String level1, String level2, String level3, String factors, String type, String measures, String reference, String flag, String level, Integer issys, Integer del, Date ctime, String gkcs, String gkzt, String fjgkfzr, String material, String gy, String hxys, String wlbs, String flag2, String gwzy, String lnglat) {

        this.id = id;
        this.uid = uid;
        this.dmid = dmid;
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
        this.factors = factors;
        this.type = type;
        this.measures = measures;
        this.reference = reference;
        this.flag = flag;
        this.level = level;
        this.issys = issys;
        this.del = del;
        this.ctime = ctime;
        this.gkcs = gkcs;
        this.gkzt = gkzt;
        this.fjgkfzr = fjgkfzr;
        this.material = material;
        this.gy = gy;
        this.hxys = hxys;
        this.wlbs = wlbs;
        this.flag2 = flag2;
        this.gwzy = gwzy;
        this.lnglat = lnglat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_company_manual_tbl
     *
     * @mbggenerated
     */
    public ACompanyManual() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_company_manual_tbl.id
     *
     * @return the value of a_company_manual_tbl.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_company_manual_tbl.id
     *
     * @param id the value for a_company_manual_tbl.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_company_manual_tbl.uid
     *
     * @return the value of a_company_manual_tbl.uid
     *
     * @mbggenerated
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_company_manual_tbl.uid
     *
     * @param uid the value for a_company_manual_tbl.uid
     *
     * @mbggenerated
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_company_manual_tbl.dmid
     *
     * @return the value of a_company_manual_tbl.dmid
     *
     * @mbggenerated
     */
    public Integer getDmid() {
        return dmid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_company_manual_tbl.dmid
     *
     * @param dmid the value for a_company_manual_tbl.dmid
     *
     * @mbggenerated
     */
    public void setDmid(Integer dmid) {
        this.dmid = dmid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_company_manual_tbl.level1
     *
     * @return the value of a_company_manual_tbl.level1
     *
     * @mbggenerated
     */
    public String getLevel1() {
        return level1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_company_manual_tbl.level1
     *
     * @param level1 the value for a_company_manual_tbl.level1
     *
     * @mbggenerated
     */
    public void setLevel1(String level1) {
        this.level1 = level1 == null ? null : level1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_company_manual_tbl.level2
     *
     * @return the value of a_company_manual_tbl.level2
     *
     * @mbggenerated
     */
    public String getLevel2() {
        return level2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_company_manual_tbl.level2
     *
     * @param level2 the value for a_company_manual_tbl.level2
     *
     * @mbggenerated
     */
    public void setLevel2(String level2) {
        this.level2 = level2 == null ? null : level2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_company_manual_tbl.level3
     *
     * @return the value of a_company_manual_tbl.level3
     *
     * @mbggenerated
     */
    public String getLevel3() {
        return level3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_company_manual_tbl.level3
     *
     * @param level3 the value for a_company_manual_tbl.level3
     *
     * @mbggenerated
     */
    public void setLevel3(String level3) {
        this.level3 = level3 == null ? null : level3.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_company_manual_tbl.factors
     *
     * @return the value of a_company_manual_tbl.factors
     *
     * @mbggenerated
     */
    public String getFactors() {
        return factors;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_company_manual_tbl.factors
     *
     * @param factors the value for a_company_manual_tbl.factors
     *
     * @mbggenerated
     */
    public void setFactors(String factors) {
        this.factors = factors == null ? null : factors.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_company_manual_tbl.type
     *
     * @return the value of a_company_manual_tbl.type
     *
     * @mbggenerated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_company_manual_tbl.type
     *
     * @param type the value for a_company_manual_tbl.type
     *
     * @mbggenerated
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_company_manual_tbl.measures
     *
     * @return the value of a_company_manual_tbl.measures
     *
     * @mbggenerated
     */
    public String getMeasures() {
        return measures;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_company_manual_tbl.measures
     *
     * @param measures the value for a_company_manual_tbl.measures
     *
     * @mbggenerated
     */
    public void setMeasures(String measures) {
        this.measures = measures == null ? null : measures.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_company_manual_tbl.reference
     *
     * @return the value of a_company_manual_tbl.reference
     *
     * @mbggenerated
     */
    public String getReference() {
        return reference;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_company_manual_tbl.reference
     *
     * @param reference the value for a_company_manual_tbl.reference
     *
     * @mbggenerated
     */
    public void setReference(String reference) {
        this.reference = reference == null ? null : reference.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_company_manual_tbl.flag
     *
     * @return the value of a_company_manual_tbl.flag
     *
     * @mbggenerated
     */
    public String getFlag() {
        return flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_company_manual_tbl.flag
     *
     * @param flag the value for a_company_manual_tbl.flag
     *
     * @mbggenerated
     */
    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_company_manual_tbl.level
     *
     * @return the value of a_company_manual_tbl.level
     *
     * @mbggenerated
     */
    public String getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_company_manual_tbl.level
     *
     * @param level the value for a_company_manual_tbl.level
     *
     * @mbggenerated
     */
    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_company_manual_tbl.issys
     *
     * @return the value of a_company_manual_tbl.issys
     *
     * @mbggenerated
     */
    public Integer getIssys() {
        return issys;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_company_manual_tbl.issys
     *
     * @param issys the value for a_company_manual_tbl.issys
     *
     * @mbggenerated
     */
    public void setIssys(Integer issys) {
        this.issys = issys;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_company_manual_tbl.del
     *
     * @return the value of a_company_manual_tbl.del
     *
     * @mbggenerated
     */
    public Integer getDel() {
        return del;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_company_manual_tbl.del
     *
     * @param del the value for a_company_manual_tbl.del
     *
     * @mbggenerated
     */
    public void setDel(Integer del) {
        this.del = del;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_company_manual_tbl.ctime
     *
     * @return the value of a_company_manual_tbl.ctime
     *
     * @mbggenerated
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_company_manual_tbl.ctime
     *
     * @param ctime the value for a_company_manual_tbl.ctime
     *
     * @mbggenerated
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_company_manual_tbl.gkcs
     *
     * @return the value of a_company_manual_tbl.gkcs
     *
     * @mbggenerated
     */
    public String getGkcs() {
        return gkcs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_company_manual_tbl.gkcs
     *
     * @param gkcs the value for a_company_manual_tbl.gkcs
     *
     * @mbggenerated
     */
    public void setGkcs(String gkcs) {
        this.gkcs = gkcs == null ? null : gkcs.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_company_manual_tbl.gkzt
     *
     * @return the value of a_company_manual_tbl.gkzt
     *
     * @mbggenerated
     */
    public String getGkzt() {
        return gkzt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_company_manual_tbl.gkzt
     *
     * @param gkzt the value for a_company_manual_tbl.gkzt
     *
     * @mbggenerated
     */
    public void setGkzt(String gkzt) {
        this.gkzt = gkzt == null ? null : gkzt.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_company_manual_tbl.fjgkfzr
     *
     * @return the value of a_company_manual_tbl.fjgkfzr
     *
     * @mbggenerated
     */
    public String getFjgkfzr() {
        return fjgkfzr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_company_manual_tbl.fjgkfzr
     *
     * @param fjgkfzr the value for a_company_manual_tbl.fjgkfzr
     *
     * @mbggenerated
     */
    public void setFjgkfzr(String fjgkfzr) {
        this.fjgkfzr = fjgkfzr == null ? null : fjgkfzr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_company_manual_tbl.material
     *
     * @return the value of a_company_manual_tbl.material
     *
     * @mbggenerated
     */
    public String getMaterial() {
        return material;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_company_manual_tbl.material
     *
     * @param material the value for a_company_manual_tbl.material
     *
     * @mbggenerated
     */
    public void setMaterial(String material) {
        this.material = material == null ? null : material.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_company_manual_tbl.gy
     *
     * @return the value of a_company_manual_tbl.gy
     *
     * @mbggenerated
     */
    public String getGy() {
        return gy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_company_manual_tbl.gy
     *
     * @param gy the value for a_company_manual_tbl.gy
     *
     * @mbggenerated
     */
    public void setGy(String gy) {
        this.gy = gy == null ? null : gy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_company_manual_tbl.hxys
     *
     * @return the value of a_company_manual_tbl.hxys
     *
     * @mbggenerated
     */
    public String getHxys() {
        return hxys;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_company_manual_tbl.hxys
     *
     * @param hxys the value for a_company_manual_tbl.hxys
     *
     * @mbggenerated
     */
    public void setHxys(String hxys) {
        this.hxys = hxys == null ? null : hxys.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_company_manual_tbl.wlbs
     *
     * @return the value of a_company_manual_tbl.wlbs
     *
     * @mbggenerated
     */
    public String getWlbs() {
        return wlbs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_company_manual_tbl.wlbs
     *
     * @param wlbs the value for a_company_manual_tbl.wlbs
     *
     * @mbggenerated
     */
    public void setWlbs(String wlbs) {
        this.wlbs = wlbs == null ? null : wlbs.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_company_manual_tbl.flag2
     *
     * @return the value of a_company_manual_tbl.flag2
     *
     * @mbggenerated
     */
    public String getFlag2() {
        return flag2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_company_manual_tbl.flag2
     *
     * @param flag2 the value for a_company_manual_tbl.flag2
     *
     * @mbggenerated
     */
    public void setFlag2(String flag2) {
        this.flag2 = flag2 == null ? null : flag2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_company_manual_tbl.gwzy
     *
     * @return the value of a_company_manual_tbl.gwzy
     *
     * @mbggenerated
     */
    public String getGwzy() {
        return gwzy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_company_manual_tbl.gwzy
     *
     * @param gwzy the value for a_company_manual_tbl.gwzy
     *
     * @mbggenerated
     */
    public void setGwzy(String gwzy) {
        this.gwzy = gwzy == null ? null : gwzy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_company_manual_tbl.lnglat
     *
     * @return the value of a_company_manual_tbl.lnglat
     *
     * @mbggenerated
     */
    public String getLnglat() {
        return lnglat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_company_manual_tbl.lnglat
     *
     * @param lnglat the value for a_company_manual_tbl.lnglat
     *
     * @mbggenerated
     */
    public void setLnglat(String lnglat) {
        this.lnglat = lnglat == null ? null : lnglat.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_company_manual_tbl
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ACompanyManual other = (ACompanyManual) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
                && (this.getDmid() == null ? other.getDmid() == null : this.getDmid().equals(other.getDmid()))
                && (this.getLevel1() == null ? other.getLevel1() == null : this.getLevel1().equals(other.getLevel1()))
                && (this.getLevel2() == null ? other.getLevel2() == null : this.getLevel2().equals(other.getLevel2()))
                && (this.getLevel3() == null ? other.getLevel3() == null : this.getLevel3().equals(other.getLevel3()))
                && (this.getFactors() == null ? other.getFactors() == null : this.getFactors().equals(other.getFactors()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getMeasures() == null ? other.getMeasures() == null : this.getMeasures().equals(other.getMeasures()))
                && (this.getReference() == null ? other.getReference() == null : this.getReference().equals(other.getReference()))
                && (this.getFlag() == null ? other.getFlag() == null : this.getFlag().equals(other.getFlag()))
                && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
                && (this.getIssys() == null ? other.getIssys() == null : this.getIssys().equals(other.getIssys()))
                && (this.getDel() == null ? other.getDel() == null : this.getDel().equals(other.getDel()))
                && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
                && (this.getGkcs() == null ? other.getGkcs() == null : this.getGkcs().equals(other.getGkcs()))
                && (this.getGkzt() == null ? other.getGkzt() == null : this.getGkzt().equals(other.getGkzt()))
                && (this.getFjgkfzr() == null ? other.getFjgkfzr() == null : this.getFjgkfzr().equals(other.getFjgkfzr()))
                && (this.getMaterial() == null ? other.getMaterial() == null : this.getMaterial().equals(other.getMaterial()))
                && (this.getGy() == null ? other.getGy() == null : this.getGy().equals(other.getGy()))
                && (this.getHxys() == null ? other.getHxys() == null : this.getHxys().equals(other.getHxys()))
                && (this.getWlbs() == null ? other.getWlbs() == null : this.getWlbs().equals(other.getWlbs()))
                && (this.getFlag2() == null ? other.getFlag2() == null : this.getFlag2().equals(other.getFlag2()))
                && (this.getGwzy() == null ? other.getGwzy() == null : this.getGwzy().equals(other.getGwzy()))
                && (this.getLnglat() == null ? other.getLnglat() == null : this.getLnglat().equals(other.getLnglat()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_company_manual_tbl
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getDmid() == null) ? 0 : getDmid().hashCode());
        result = prime * result + ((getLevel1() == null) ? 0 : getLevel1().hashCode());
        result = prime * result + ((getLevel2() == null) ? 0 : getLevel2().hashCode());
        result = prime * result + ((getLevel3() == null) ? 0 : getLevel3().hashCode());
        result = prime * result + ((getFactors() == null) ? 0 : getFactors().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getMeasures() == null) ? 0 : getMeasures().hashCode());
        result = prime * result + ((getReference() == null) ? 0 : getReference().hashCode());
        result = prime * result + ((getFlag() == null) ? 0 : getFlag().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getIssys() == null) ? 0 : getIssys().hashCode());
        result = prime * result + ((getDel() == null) ? 0 : getDel().hashCode());
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
        result = prime * result + ((getGkcs() == null) ? 0 : getGkcs().hashCode());
        result = prime * result + ((getGkzt() == null) ? 0 : getGkzt().hashCode());
        result = prime * result + ((getFjgkfzr() == null) ? 0 : getFjgkfzr().hashCode());
        result = prime * result + ((getMaterial() == null) ? 0 : getMaterial().hashCode());
        result = prime * result + ((getGy() == null) ? 0 : getGy().hashCode());
        result = prime * result + ((getHxys() == null) ? 0 : getHxys().hashCode());
        result = prime * result + ((getWlbs() == null) ? 0 : getWlbs().hashCode());
        result = prime * result + ((getFlag2() == null) ? 0 : getFlag2().hashCode());
        result = prime * result + ((getGwzy() == null) ? 0 : getGwzy().hashCode());
        result = prime * result + ((getLnglat() == null) ? 0 : getLnglat().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_company_manual_tbl
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uid=").append(uid);
        sb.append(", dmid=").append(dmid);
        sb.append(", level1=").append(level1);
        sb.append(", level2=").append(level2);
        sb.append(", level3=").append(level3);
        sb.append(", factors=").append(factors);
        sb.append(", type=").append(type);
        sb.append(", measures=").append(measures);
        sb.append(", reference=").append(reference);
        sb.append(", flag=").append(flag);
        sb.append(", level=").append(level);
        sb.append(", issys=").append(issys);
        sb.append(", del=").append(del);
        sb.append(", ctime=").append(ctime);
        sb.append(", gkcs=").append(gkcs);
        sb.append(", gkzt=").append(gkzt);
        sb.append(", fjgkfzr=").append(fjgkfzr);
        sb.append(", material=").append(material);
        sb.append(", gy=").append(gy);
        sb.append(", hxys=").append(hxys);
        sb.append(", wlbs=").append(wlbs);
        sb.append(", flag2=").append(flag2);
        sb.append(", gwzy=").append(gwzy);
        sb.append(", lnglat=").append(lnglat);
        sb.append("]");
        return sb.toString();
    }
}
