package com.spring.web.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class FcTargetAssessment {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fc_target_assessment_t.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fc_target_assessment_t.uid
     *
     * @mbggenerated
     */
    private Integer uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fc_target_assessment_t.bkhgw
     *
     * @mbggenerated
     */
    private String bkhgw;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fc_target_assessment_t.khsj
     *
     * @mbggenerated
     */
    private Date khsj;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fc_target_assessment_t.khd1
     *
     * @mbggenerated
     */
    private String khd1;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fc_target_assessment_t.khd2
     *
     * @mbggenerated
     */
    private String khd2;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fc_target_assessment_t.khd3
     *
     * @mbggenerated
     */
    private String khd3;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fc_target_assessment_t.khd4
     *
     * @mbggenerated
     */
    private String khd4;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fc_target_assessment_t.khd5
     *
     * @mbggenerated
     */
    private String khd5;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fc_target_assessment_t.khd6
     *
     * @mbggenerated
     */
    private String khd6;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fc_target_assessment_t.khd7
     *
     * @mbggenerated
     */
    private String khd7;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fc_target_assessment_t.khbm
     *
     * @mbggenerated
     */
    private String khbm;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fc_target_assessment_t.bkhbm
     *
     * @mbggenerated
     */
    private String bkhbm;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fc_target_assessment_t.khrkhbm
     *
     * @mbggenerated
     */
    private String khrkhbm;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fc_target_assessment_t.fzrbkhbm
     *
     * @mbggenerated
     */
    private String fzrbkhbm;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fc_target_assessment_t.cjzrkhbm
     *
     * @mbggenerated
     */
    private String cjzrkhbm;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fc_target_assessment_t.cjzrbkhbm
     *
     * @mbggenerated
     */
    private String cjzrbkhbm;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fc_target_assessment_t.bmjlkhbm
     *
     * @mbggenerated
     */
    private String bmjlkhbm;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fc_target_assessment_t.bmjlbkhbm
     *
     * @mbggenerated
     */
    private String bmjlbkhbm;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fc_target_assessment_t.del
     *
     * @mbggenerated
     */
    private Integer del;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fc_target_assessment_t.ctime
     *
     * @mbggenerated
     */
    private Date ctime;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fc_target_assessment_t
     *
     * @mbggenerated
     */
    public FcTargetAssessment(Integer id, Integer uid, String bkhgw, Date khsj, String khd1, String khd2, String khd3, String khd4, String khd5, String khd6, String khd7, String khbm, String bkhbm, String khrkhbm, String fzrbkhbm, String cjzrkhbm, String cjzrbkhbm, String bmjlkhbm, String bmjlbkhbm, Integer del, Date ctime) {
        this.id = id;
        this.uid = uid;
        this.bkhgw = bkhgw;
        this.khsj = khsj;
        this.khd1 = khd1;
        this.khd2 = khd2;
        this.khd3 = khd3;
        this.khd4 = khd4;
        this.khd5 = khd5;
        this.khd6 = khd6;
        this.khd7 = khd7;
        this.khbm = khbm;
        this.bkhbm = bkhbm;
        this.khrkhbm = khrkhbm;
        this.fzrbkhbm = fzrbkhbm;
        this.cjzrkhbm = cjzrkhbm;
        this.cjzrbkhbm = cjzrbkhbm;
        this.bmjlkhbm = bmjlkhbm;
        this.bmjlbkhbm = bmjlbkhbm;
        this.del = del;
        this.ctime = ctime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fc_target_assessment_t
     *
     * @mbggenerated
     */
    public FcTargetAssessment() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fc_target_assessment_t.id
     *
     * @return the value of fc_target_assessment_t.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fc_target_assessment_t.id
     *
     * @param id the value for fc_target_assessment_t.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fc_target_assessment_t.uid
     *
     * @return the value of fc_target_assessment_t.uid
     *
     * @mbggenerated
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fc_target_assessment_t.uid
     *
     * @param uid the value for fc_target_assessment_t.uid
     *
     * @mbggenerated
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fc_target_assessment_t.bkhgw
     *
     * @return the value of fc_target_assessment_t.bkhgw
     *
     * @mbggenerated
     */
    public String getBkhgw() {
        return bkhgw;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fc_target_assessment_t.bkhgw
     *
     * @param bkhgw the value for fc_target_assessment_t.bkhgw
     *
     * @mbggenerated
     */
    public void setBkhgw(String bkhgw) {
        this.bkhgw = bkhgw == null ? null : bkhgw.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fc_target_assessment_t.khsj
     *
     * @return the value of fc_target_assessment_t.khsj
     *
     * @mbggenerated
     */
    public Date getKhsj() {
        return khsj;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fc_target_assessment_t.khsj
     *
     * @param khsj the value for fc_target_assessment_t.khsj
     *
     * @mbggenerated
     */
    public void setKhsj(Date khsj) {
        this.khsj = khsj;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fc_target_assessment_t.khd1
     *
     * @return the value of fc_target_assessment_t.khd1
     *
     * @mbggenerated
     */
    public String getKhd1() {
        return khd1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fc_target_assessment_t.khd1
     *
     * @param khd1 the value for fc_target_assessment_t.khd1
     *
     * @mbggenerated
     */
    public void setKhd1(String khd1) {
        this.khd1 = khd1 == null ? null : khd1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fc_target_assessment_t.khd2
     *
     * @return the value of fc_target_assessment_t.khd2
     *
     * @mbggenerated
     */
    public String getKhd2() {
        return khd2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fc_target_assessment_t.khd2
     *
     * @param khd2 the value for fc_target_assessment_t.khd2
     *
     * @mbggenerated
     */
    public void setKhd2(String khd2) {
        this.khd2 = khd2 == null ? null : khd2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fc_target_assessment_t.khd3
     *
     * @return the value of fc_target_assessment_t.khd3
     *
     * @mbggenerated
     */
    public String getKhd3() {
        return khd3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fc_target_assessment_t.khd3
     *
     * @param khd3 the value for fc_target_assessment_t.khd3
     *
     * @mbggenerated
     */
    public void setKhd3(String khd3) {
        this.khd3 = khd3 == null ? null : khd3.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fc_target_assessment_t.khd4
     *
     * @return the value of fc_target_assessment_t.khd4
     *
     * @mbggenerated
     */
    public String getKhd4() {
        return khd4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fc_target_assessment_t.khd4
     *
     * @param khd4 the value for fc_target_assessment_t.khd4
     *
     * @mbggenerated
     */
    public void setKhd4(String khd4) {
        this.khd4 = khd4 == null ? null : khd4.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fc_target_assessment_t.khd5
     *
     * @return the value of fc_target_assessment_t.khd5
     *
     * @mbggenerated
     */
    public String getKhd5() {
        return khd5;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fc_target_assessment_t.khd5
     *
     * @param khd5 the value for fc_target_assessment_t.khd5
     *
     * @mbggenerated
     */
    public void setKhd5(String khd5) {
        this.khd5 = khd5 == null ? null : khd5.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fc_target_assessment_t.khd6
     *
     * @return the value of fc_target_assessment_t.khd6
     *
     * @mbggenerated
     */
    public String getKhd6() {
        return khd6;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fc_target_assessment_t.khd6
     *
     * @param khd6 the value for fc_target_assessment_t.khd6
     *
     * @mbggenerated
     */
    public void setKhd6(String khd6) {
        this.khd6 = khd6 == null ? null : khd6.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fc_target_assessment_t.khd7
     *
     * @return the value of fc_target_assessment_t.khd7
     *
     * @mbggenerated
     */
    public String getKhd7() {
        return khd7;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fc_target_assessment_t.khd7
     *
     * @param khd7 the value for fc_target_assessment_t.khd7
     *
     * @mbggenerated
     */
    public void setKhd7(String khd7) {
        this.khd7 = khd7 == null ? null : khd7.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fc_target_assessment_t.khbm
     *
     * @return the value of fc_target_assessment_t.khbm
     *
     * @mbggenerated
     */
    public String getKhbm() {
        return khbm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fc_target_assessment_t.khbm
     *
     * @param khbm the value for fc_target_assessment_t.khbm
     *
     * @mbggenerated
     */
    public void setKhbm(String khbm) {
        this.khbm = khbm == null ? null : khbm.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fc_target_assessment_t.bkhbm
     *
     * @return the value of fc_target_assessment_t.bkhbm
     *
     * @mbggenerated
     */
    public String getBkhbm() {
        return bkhbm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fc_target_assessment_t.bkhbm
     *
     * @param bkhbm the value for fc_target_assessment_t.bkhbm
     *
     * @mbggenerated
     */
    public void setBkhbm(String bkhbm) {
        this.bkhbm = bkhbm == null ? null : bkhbm.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fc_target_assessment_t.khrkhbm
     *
     * @return the value of fc_target_assessment_t.khrkhbm
     *
     * @mbggenerated
     */
    public String getKhrkhbm() {
        return khrkhbm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fc_target_assessment_t.khrkhbm
     *
     * @param khrkhbm the value for fc_target_assessment_t.khrkhbm
     *
     * @mbggenerated
     */
    public void setKhrkhbm(String khrkhbm) {
        this.khrkhbm = khrkhbm == null ? null : khrkhbm.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fc_target_assessment_t.fzrbkhbm
     *
     * @return the value of fc_target_assessment_t.fzrbkhbm
     *
     * @mbggenerated
     */
    public String getFzrbkhbm() {
        return fzrbkhbm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fc_target_assessment_t.fzrbkhbm
     *
     * @param fzrbkhbm the value for fc_target_assessment_t.fzrbkhbm
     *
     * @mbggenerated
     */
    public void setFzrbkhbm(String fzrbkhbm) {
        this.fzrbkhbm = fzrbkhbm == null ? null : fzrbkhbm.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fc_target_assessment_t.cjzrkhbm
     *
     * @return the value of fc_target_assessment_t.cjzrkhbm
     *
     * @mbggenerated
     */
    public String getCjzrkhbm() {
        return cjzrkhbm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fc_target_assessment_t.cjzrkhbm
     *
     * @param cjzrkhbm the value for fc_target_assessment_t.cjzrkhbm
     *
     * @mbggenerated
     */
    public void setCjzrkhbm(String cjzrkhbm) {
        this.cjzrkhbm = cjzrkhbm == null ? null : cjzrkhbm.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fc_target_assessment_t.cjzrbkhbm
     *
     * @return the value of fc_target_assessment_t.cjzrbkhbm
     *
     * @mbggenerated
     */
    public String getCjzrbkhbm() {
        return cjzrbkhbm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fc_target_assessment_t.cjzrbkhbm
     *
     * @param cjzrbkhbm the value for fc_target_assessment_t.cjzrbkhbm
     *
     * @mbggenerated
     */
    public void setCjzrbkhbm(String cjzrbkhbm) {
        this.cjzrbkhbm = cjzrbkhbm == null ? null : cjzrbkhbm.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fc_target_assessment_t.bmjlkhbm
     *
     * @return the value of fc_target_assessment_t.bmjlkhbm
     *
     * @mbggenerated
     */
    public String getBmjlkhbm() {
        return bmjlkhbm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fc_target_assessment_t.bmjlkhbm
     *
     * @param bmjlkhbm the value for fc_target_assessment_t.bmjlkhbm
     *
     * @mbggenerated
     */
    public void setBmjlkhbm(String bmjlkhbm) {
        this.bmjlkhbm = bmjlkhbm == null ? null : bmjlkhbm.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fc_target_assessment_t.bmjlbkhbm
     *
     * @return the value of fc_target_assessment_t.bmjlbkhbm
     *
     * @mbggenerated
     */
    public String getBmjlbkhbm() {
        return bmjlbkhbm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fc_target_assessment_t.bmjlbkhbm
     *
     * @param bmjlbkhbm the value for fc_target_assessment_t.bmjlbkhbm
     *
     * @mbggenerated
     */
    public void setBmjlbkhbm(String bmjlbkhbm) {
        this.bmjlbkhbm = bmjlbkhbm == null ? null : bmjlbkhbm.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fc_target_assessment_t.del
     *
     * @return the value of fc_target_assessment_t.del
     *
     * @mbggenerated
     */
    public Integer getDel() {
        return del;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fc_target_assessment_t.del
     *
     * @param del the value for fc_target_assessment_t.del
     *
     * @mbggenerated
     */
    public void setDel(Integer del) {
        this.del = del;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fc_target_assessment_t.ctime
     *
     * @return the value of fc_target_assessment_t.ctime
     *
     * @mbggenerated
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fc_target_assessment_t.ctime
     *
     * @param ctime the value for fc_target_assessment_t.ctime
     *
     * @mbggenerated
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fc_target_assessment_t
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
        FcTargetAssessment other = (FcTargetAssessment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getBkhgw() == null ? other.getBkhgw() == null : this.getBkhgw().equals(other.getBkhgw()))
            && (this.getKhsj() == null ? other.getKhsj() == null : this.getKhsj().equals(other.getKhsj()))
            && (this.getKhd1() == null ? other.getKhd1() == null : this.getKhd1().equals(other.getKhd1()))
            && (this.getKhd2() == null ? other.getKhd2() == null : this.getKhd2().equals(other.getKhd2()))
            && (this.getKhd3() == null ? other.getKhd3() == null : this.getKhd3().equals(other.getKhd3()))
            && (this.getKhd4() == null ? other.getKhd4() == null : this.getKhd4().equals(other.getKhd4()))
            && (this.getKhd5() == null ? other.getKhd5() == null : this.getKhd5().equals(other.getKhd5()))
            && (this.getKhd6() == null ? other.getKhd6() == null : this.getKhd6().equals(other.getKhd6()))
            && (this.getKhd7() == null ? other.getKhd7() == null : this.getKhd7().equals(other.getKhd7()))
            && (this.getKhbm() == null ? other.getKhbm() == null : this.getKhbm().equals(other.getKhbm()))
            && (this.getBkhbm() == null ? other.getBkhbm() == null : this.getBkhbm().equals(other.getBkhbm()))
            && (this.getKhrkhbm() == null ? other.getKhrkhbm() == null : this.getKhrkhbm().equals(other.getKhrkhbm()))
            && (this.getFzrbkhbm() == null ? other.getFzrbkhbm() == null : this.getFzrbkhbm().equals(other.getFzrbkhbm()))
            && (this.getCjzrkhbm() == null ? other.getCjzrkhbm() == null : this.getCjzrkhbm().equals(other.getCjzrkhbm()))
            && (this.getCjzrbkhbm() == null ? other.getCjzrbkhbm() == null : this.getCjzrbkhbm().equals(other.getCjzrbkhbm()))
            && (this.getBmjlkhbm() == null ? other.getBmjlkhbm() == null : this.getBmjlkhbm().equals(other.getBmjlkhbm()))
            && (this.getBmjlbkhbm() == null ? other.getBmjlbkhbm() == null : this.getBmjlbkhbm().equals(other.getBmjlbkhbm()))
            && (this.getDel() == null ? other.getDel() == null : this.getDel().equals(other.getDel()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fc_target_assessment_t
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getBkhgw() == null) ? 0 : getBkhgw().hashCode());
        result = prime * result + ((getKhsj() == null) ? 0 : getKhsj().hashCode());
        result = prime * result + ((getKhd1() == null) ? 0 : getKhd1().hashCode());
        result = prime * result + ((getKhd2() == null) ? 0 : getKhd2().hashCode());
        result = prime * result + ((getKhd3() == null) ? 0 : getKhd3().hashCode());
        result = prime * result + ((getKhd4() == null) ? 0 : getKhd4().hashCode());
        result = prime * result + ((getKhd5() == null) ? 0 : getKhd5().hashCode());
        result = prime * result + ((getKhd6() == null) ? 0 : getKhd6().hashCode());
        result = prime * result + ((getKhd7() == null) ? 0 : getKhd7().hashCode());
        result = prime * result + ((getKhbm() == null) ? 0 : getKhbm().hashCode());
        result = prime * result + ((getBkhbm() == null) ? 0 : getBkhbm().hashCode());
        result = prime * result + ((getKhrkhbm() == null) ? 0 : getKhrkhbm().hashCode());
        result = prime * result + ((getFzrbkhbm() == null) ? 0 : getFzrbkhbm().hashCode());
        result = prime * result + ((getCjzrkhbm() == null) ? 0 : getCjzrkhbm().hashCode());
        result = prime * result + ((getCjzrbkhbm() == null) ? 0 : getCjzrbkhbm().hashCode());
        result = prime * result + ((getBmjlkhbm() == null) ? 0 : getBmjlkhbm().hashCode());
        result = prime * result + ((getBmjlbkhbm() == null) ? 0 : getBmjlbkhbm().hashCode());
        result = prime * result + ((getDel() == null) ? 0 : getDel().hashCode());
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fc_target_assessment_t
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
        sb.append(", bkhgw=").append(bkhgw);
        sb.append(", khsj=").append(khsj);
        sb.append(", khd1=").append(khd1);
        sb.append(", khd2=").append(khd2);
        sb.append(", khd3=").append(khd3);
        sb.append(", khd4=").append(khd4);
        sb.append(", khd5=").append(khd5);
        sb.append(", khd6=").append(khd6);
        sb.append(", khd7=").append(khd7);
        sb.append(", khbm=").append(khbm);
        sb.append(", bkhbm=").append(bkhbm);
        sb.append(", khrkhbm=").append(khrkhbm);
        sb.append(", fzrbkhbm=").append(fzrbkhbm);
        sb.append(", cjzrkhbm=").append(cjzrkhbm);
        sb.append(", cjzrbkhbm=").append(cjzrbkhbm);
        sb.append(", bmjlkhbm=").append(bmjlkhbm);
        sb.append(", bmjlbkhbm=").append(bmjlbkhbm);
        sb.append(", del=").append(del);
        sb.append(", ctime=").append(ctime);
        sb.append("]");
        return sb.toString();
    }
}