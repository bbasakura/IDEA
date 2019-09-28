package cn.itcast.b2c.gciantispider.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NhFlowInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nh_flow_info", catalog = "gciantispider")
public class NhFlowInfo implements java.io.Serializable {

    // Fields

    private String id;

    private Integer flowType;

    private Float flowValue;

    private Timestamp recordTime;

    // Constructors

    /** default constructor */
    public NhFlowInfo() {
    }

    /** minimal constructor */
    public NhFlowInfo(String id) {
        this.id = id;
    }

    /** full constructor */
    public NhFlowInfo(String id, Integer flowType, Float flowValue, Timestamp recordTime) {
        this.id = id;
        this.flowType = flowType;
        this.flowValue = flowValue;
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

    @Column(name = "flow_type")
    public Integer getFlowType() {
        return this.flowType;
    }

    public void setFlowType(Integer flowType) {
        this.flowType = flowType;
    }

    @Column(name = "flow_value", precision = 12, scale = 0)
    public Float getFlowValue() {
        return this.flowValue;
    }

    public void setFlowValue(Float flowValue) {
        this.flowValue = flowValue;
    }

    @Column(name = "record_time", length = 19)
    public Timestamp getRecordTime() {
        return this.recordTime;
    }

    public void setRecordTime(Timestamp recordTime) {
        this.recordTime = recordTime;
    }

}