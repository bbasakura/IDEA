package cn.itcast.b2c.gciantispider.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ServerStatusInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "server_status_info", catalog = "gciantispider")
public class ServerStatusInfo implements java.io.Serializable {

    // Fields

    private String id;

    private String name;

    private Float cpuRate;

    private Float memoryRate;

    private String memoryInfo;

    private Float diskRate;

    private String diskInfo;

    private Timestamp stateTime;

    // Constructors

    /** default constructor */
    public ServerStatusInfo() {
    }

    /** minimal constructor */
    public ServerStatusInfo(String id) {
        this.id = id;
    }

    /** full constructor */
    public ServerStatusInfo(String id, String name, Float cpuRate, Float memoryRate, String memoryInfo, Float diskRate, String diskInfo, Timestamp stateTime) {
        this.id = id;
        this.name = name;
        this.cpuRate = cpuRate;
        this.memoryRate = memoryRate;
        this.memoryInfo = memoryInfo;
        this.diskRate = diskRate;
        this.diskInfo = diskInfo;
        this.stateTime = stateTime;
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

    @Column(name = "name", length = 64)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "cpu_rate")
    public Float getCpuRate() {
        return this.cpuRate;
    }

    public void setCpuRate(Float cpuRate) {
        this.cpuRate = cpuRate;
    }

    @Column(name = "memory_rate")
    public Float getMemoryRate() {
        return this.memoryRate;
    }

    public void setMemoryRate(Float memoryRate) {
        this.memoryRate = memoryRate;
    }

    @Column(name = "memory_info", length = 64)
    public String getMemoryInfo() {
        return this.memoryInfo;
    }

    public void setMemoryInfo(String memoryInfo) {
        this.memoryInfo = memoryInfo;
    }

    @Column(name = "disk_rate")
    public Float getDiskRate() {
        return this.diskRate;
    }

    public void setDiskRate(Float diskRate) {
        this.diskRate = diskRate;
    }

    @Column(name = "disk_info", length = 64)
    public String getDiskInfo() {
        return this.diskInfo;
    }

    public void setDiskInfo(String diskInfo) {
        this.diskInfo = diskInfo;
    }

    @Column(name = "state_time", length = 19)
    public Timestamp getStateTime() {
        return this.stateTime;
    }

    public void setStateTime(Timestamp stateTime) {
        this.stateTime = stateTime;
    }

}