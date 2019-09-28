package cn.itcast.b2c.gciantispider.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ClimbMonitor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "climb_monitor")
public class ClimbMonitor implements java.io.Serializable {

    // Fields

    private String id;

    private Integer calculateType;

    private Integer climbType;

    private String processName;

    private Float accuracy;

    // Constructors

    /** default constructor */
    public ClimbMonitor() {
    }

    /** minimal constructor */
    public ClimbMonitor(String id) {
        this.id = id;
    }

    /** full constructor */
    public ClimbMonitor(String id, Integer calculateType, Integer climbType, String processName, Float accuracy) {
        this.id = id;
        this.calculateType = calculateType;
        this.climbType = climbType;
        this.processName = processName;
        this.accuracy = accuracy;
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

    @Column(name = "calculate_type")
    public Integer getCalculateType() {
        return this.calculateType;
    }

    public void setCalculateType(Integer calculateType) {
        this.calculateType = calculateType;
    }

    @Column(name = "climb_type")
    public Integer getClimbType() {
        return this.climbType;
    }

    public void setClimbType(Integer climbType) {
        this.climbType = climbType;
    }

    @Column(name = "process_name", length = 64)
    public String getProcessName() {
        return this.processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    @Column(name = "accuracy", precision = 12, scale = 0)
    public Float getAccuracy() {
        return this.accuracy;
    }

    public void setAccuracy(Float accuracy) {
        this.accuracy = accuracy;
    }

}