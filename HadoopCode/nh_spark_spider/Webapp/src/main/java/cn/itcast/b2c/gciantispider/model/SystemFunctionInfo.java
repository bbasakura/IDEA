package cn.itcast.b2c.gciantispider.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SystemFunctionInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "system_function_info", catalog = "gciantispider")
public class SystemFunctionInfo implements java.io.Serializable {

    // Fields

    private String id;

    private Integer state;

    private String modelName;

    private Timestamp recordTime;

    // Constructors

    /** default constructor */
    public SystemFunctionInfo() {
    }

    /** minimal constructor */
    public SystemFunctionInfo(String id) {
        this.id = id;
    }

    /** full constructor */
    public SystemFunctionInfo(String id, Integer state, String modelName, Timestamp recordTime) {
        this.id = id;
        this.state = state;
        this.modelName = modelName;
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

    @Column(name = "state")
    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Column(name = "model_name", length = 64)
    public String getModelName() {
        return this.modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Column(name = "record_time", length = 19)
    public Timestamp getRecordTime() {
        return this.recordTime;
    }

    public void setRecordTime(Timestamp recordTime) {
        this.recordTime = recordTime;
    }

}