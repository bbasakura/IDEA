package cn.itcast.b2c.gciantispider.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * NhFlightQueryConversionRate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nh_flight_query_conversion_rate", catalog = "gciantispider")
public class NhFlightQueryConversionRate implements java.io.Serializable {

    // Fields

    private String id;

    private Integer stepType;

    private Integer querySpiderType;

    private Float conversionValue;

    private Date recordTime;

    // Constructors

    /** default constructor */
    public NhFlightQueryConversionRate() {
    }

    /** minimal constructor */
    public NhFlightQueryConversionRate(String id) {
        this.id = id;
    }

    /** full constructor */
    public NhFlightQueryConversionRate(String id, Integer stepType, Integer querySpiderType, Float conversionValue, Date recordTime) {
        this.id = id;
        this.stepType = stepType;
        this.querySpiderType = querySpiderType;
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

    @Column(name = "query_spider_type")
    public Integer getQuerySpiderType() {
        return this.querySpiderType;
    }

    public void setQuerySpiderType(Integer querySpiderType) {
        this.querySpiderType = querySpiderType;
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