package cn.itcast.b2c.gciantispider.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NhAgencyCustomerAnalysis entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nh_agency_customer_analysis", catalog = "gciantispider")
public class NhAgencyCustomerAnalysis implements java.io.Serializable {

    // Fields

    private String id;

    private Integer customerType;

    private Float value;

    private Timestamp recordTime;

    // Constructors

    /** default constructor */
    public NhAgencyCustomerAnalysis() {
    }

    /** minimal constructor */
    public NhAgencyCustomerAnalysis(String id) {
        this.id = id;
    }

    /** full constructor */
    public NhAgencyCustomerAnalysis(String id, Integer customerType, Float value, Timestamp recordTime) {
        this.id = id;
        this.customerType = customerType;
        this.value = value;
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

    @Column(name = "customer_type")
    public Integer getCustomerType() {
        return this.customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    @Column(name = "value", precision = 12, scale = 0)
    public Float getValue() {
        return this.value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    @Column(name = "record_time", length = 19)
    public Timestamp getRecordTime() {
        return this.recordTime;
    }

    public void setRecordTime(Timestamp recordTime) {
        this.recordTime = recordTime;
    }

}