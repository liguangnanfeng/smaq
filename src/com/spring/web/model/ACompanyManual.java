package com.spring.web.model;

import java.util.Date;

public class ACompanyManual {

    private Integer id;

    private Integer uid;

    private Integer dmid;

    private String level1;

    private String level2;

    private String level3;

    private String factors;

    private String type;

    private String measures;

    private String reference;

    private String flag;

    private String level;

    private Integer issys;

    private Integer del;

    private Date ctime;

    private String gkcs;

    private String gkzt;

    private String fjgkfzr;

    private String material;

    private String gy;

    private String hxys;

    private String wlbs;

    private String flag2;

    private String gwzy;

    private String lnglat;

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

    public ACompanyManual() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getDmid() {
        return dmid;
    }

    public void setDmid(Integer dmid) {
        this.dmid = dmid;
    }

    public String getLevel1() {
        return level1;
    }

    public void setLevel1(String level1) {
        this.level1 = level1 == null ? null : level1.trim();
    }

    public String getLevel2() {
        return level2;
    }

    public void setLevel2(String level2) {
        this.level2 = level2 == null ? null : level2.trim();
    }

    public String getLevel3() {
        return level3;
    }

    public void setLevel3(String level3) {
        this.level3 = level3 == null ? null : level3.trim();
    }

    public String getFactors() {
        return factors;
    }

    public void setFactors(String factors) {
        this.factors = factors == null ? null : factors.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getMeasures() {
        return measures;
    }

    public void setMeasures(String measures) {
        this.measures = measures == null ? null : measures.trim();
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference == null ? null : reference.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Integer getIssys() {
        return issys;
    }

    public void setIssys(Integer issys) {
        this.issys = issys;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getGkcs() {
        return gkcs;
    }

    public void setGkcs(String gkcs) {
        this.gkcs = gkcs == null ? null : gkcs.trim();
    }

    public String getGkzt() {
        return gkzt;
    }

    public void setGkzt(String gkzt) {
        this.gkzt = gkzt == null ? null : gkzt.trim();
    }

    public String getFjgkfzr() {
        return fjgkfzr;
    }

    public void setFjgkfzr(String fjgkfzr) {
        this.fjgkfzr = fjgkfzr == null ? null : fjgkfzr.trim();
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material == null ? null : material.trim();
    }

    public String getGy() {
        return gy;
    }

    public void setGy(String gy) {
        this.gy = gy == null ? null : gy.trim();
    }

    public String getHxys() {
        return hxys;
    }

    public void setHxys(String hxys) {
        this.hxys = hxys == null ? null : hxys.trim();
    }

    public String getWlbs() {
        return wlbs;
    }

    public void setWlbs(String wlbs) {
        this.wlbs = wlbs == null ? null : wlbs.trim();
    }

    public String getFlag2() {
        return flag2;
    }

    public void setFlag2(String flag2) {
        this.flag2 = flag2 == null ? null : flag2.trim();
    }

    public String getGwzy() {
        return gwzy;
    }

    public void setGwzy(String gwzy) {
        this.gwzy = gwzy == null ? null : gwzy.trim();
    }

    public String getLnglat() {
        return lnglat;
    }

    public void setLnglat(String lnglat) {
        this.lnglat = lnglat == null ? null : lnglat.trim();
    }

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
