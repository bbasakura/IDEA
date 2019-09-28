package cn.itcast.b2c.gciantispider.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NhFourFlowNum entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nh_four_flow_num", catalog = "gciantispider")
public class NhFourFlowNum implements java.io.Serializable {

    // Fields

    private String id;

    private Integer type;

    private Integer value;

    private Timestamp recordTime;

    // Constructors

    /** default constructor */
    public NhFourFlowNum() {
    }

    /** minimal constructor */
    public NhFourFlowNum(String id) {
        this.id = id;
    }

    /** full constructor */
    public NhFourFlowNum(String id, Integer type, Integer value, Timestamp recordTime) {
        this.id = id;
        this.type = type;
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

    @Column(name = "type")
    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "value")
    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
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