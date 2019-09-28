package cn.itcast.b2c.gciantispider.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NhFlowQueryRate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nh_flow_query_rate", catalog = "gciantispider")
public class NhFlowQueryRate implements java.io.Serializable {

    // Fields

    private String id;

    private Integer userType;

    private Float conversionRate;

    private Timestamp recordTime;

    // Constructors

    /** default constructor */
    public NhFlowQueryRate() {
    }

    /** minimal constructor */
    public NhFlowQueryRate(String id) {
        this.id = id;
    }

    /** full constructor */
    public NhFlowQueryRate(String id, Integer userType, Float conversionRate, Timestamp recordTime) {
        this.id = id;
        this.userType = userType;
        this.conversionRate = conversionRate;
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

    @Column(name = "user_type")
    public Integer getUserType() {
        return this.userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Column(name = "conversion_rate", precision = 12, scale = 0)
    public Float getConversionRate() {
        return this.conversionRate;
    }

    public void setConversionRate(Float conversionRate) {
        this.conversionRate = conversionRate;
    }

    @Column(name = "record_time", length = 19)
    public Timestamp getRecordTime() {
        return this.recordTime;
    }

    public void setRecordTime(Timestamp recordTime) {
        this.recordTime = recordTime;
    }

}