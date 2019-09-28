package cn.itcast.b2c.gciantispider.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * NhUserConversionRate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nh_user_conversion_rate", catalog = "gciantispider")
public class NhUserConversionRate implements java.io.Serializable {

    // Fields

    private String id;

    private Integer stepType;

    private Integer userType;

    private Float conversionValue;

    private Date recordTime;

    // Constructors

    /** default constructor */
    public NhUserConversionRate() {
    }

    /** minimal constructor */
    public NhUserConversionRate(String id) {
        this.id = id;
    }

    /** full constructor */
    public NhUserConversionRate(String id, Integer stepType, Integer userType, Float conversionValue, Date recordTime) {
        this.id = id;
        this.stepType = stepType;
        this.userType = userType;
        this.conversionValue = conversionValue;
        this.recordTime = recordTime;
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

    @Column(name = "step_type")
    public Integer getStepType() {
        return this.stepType;
    }

    public void setStepType(Integer stepType) {
        this.stepType = stepType;
    }

    @Column(name = "user_type")
    public Integer getUserType() {
        return this.userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Column(name = "conversion_rate", precision = 12, scale = 0)
    public Float getConversionValue() {
        return this.conversionValue;
    }

    public void setConversionValue(Float conversionValue) {
        this.conversionValue = conversionValue;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "record_time", length = 10)
    public Date getRecordTime() {
        return this.recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

}