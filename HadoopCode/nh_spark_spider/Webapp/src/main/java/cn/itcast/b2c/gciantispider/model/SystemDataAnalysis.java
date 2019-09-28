package cn.itcast.b2c.gciantispider.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SystemDataAnalysis entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "system_data_analysis", catalog = "gciantispider")
public class SystemDataAnalysis implements java.io.Serializable {

    // Fields

    private String id;

    private Integer type;

    private Float acctualValue;

    private Float standValue;

    private Date date;

    // Constructors

    /** default constructor */
    public SystemDataAnalysis() {
    }

    /** minimal constructor */
    public SystemDataAnalysis(String id) {
        this.id = id;
    }

    /** full constructor */
    public SystemDataAnalysis(String id, Integer type, Float acctualValue, Float standValue, Date date) {
        this.id = id;
        this.type = type;
        this.acctualValue = acctualValue;
        this.standValue = standValue;
        this.date = date;
    }

    // Property accessors
    @Id
    @Column(name = "id", unique = true, nullable = false, length = 64)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "type")
    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "acctual_value", precision = 12, scale = 0)
    public Float getAcctualValue() {
        return this.acctualValue;
    }

    public void setAcctualValue(Float acctualValue) {
        this.acctualValue = acctualValue;
    }

    @Column(name = "stand_value", precision = 12, scale = 0)
    public Float getStandValue() {
        return this.standValue;
    }

    public void setStandValue(Float standValue) {
        this.standValue = standValue;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "date", length = 10)
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}