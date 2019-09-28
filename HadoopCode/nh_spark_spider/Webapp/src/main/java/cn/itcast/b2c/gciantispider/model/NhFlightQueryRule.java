package cn.itcast.b2c.gciantispider.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * NhFlightQueryRule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nh_flight_query_rule", catalog = "gciantispider")
public class NhFlightQueryRule implements java.io.Serializable {

    // Fields

    private String id;

    private Integer flghtType;

    private Integer wayType;

    private Integer value;

    private Date recordTime;

    // Constructors

    /** default constructor */
    public NhFlightQueryRule() {
    }

    /** minimal constructor */
    public NhFlightQueryRule(String id) {
        this.id = id;
    }

    /** full constructor */
    public NhFlightQueryRule(String id, Integer flghtType, Integer wayType, Integer value, Date recordTime) {
        this.id = id;
        this.flghtType = flghtType;
        this.wayType = wayType;
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

    @Column(name = "flght_type")
    public Integer getFlghtType() {
        return this.flghtType;
    }

    public void setFlghtType(Integer flghtType) {
        this.flghtType = flghtType;
    }

    @Column(name = "way_type")
    public Integer getWayType() {
        return this.wayType;
    }

    public void setWayType(Integer wayType) {
        this.wayType = wayType;
    }

    @Column(name = "value")
    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
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