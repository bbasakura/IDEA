package cn.itcast.b2c.gciantispider.pageUtil;

import java.sql.Timestamp;
/**
 * 服务器状态封装类
 * 
 */
public class ServerStatusVO {

    private String name;

    private Integer cpuRate;

    private Integer memoryRate;

    private String memoryInfo;

    private Integer diskRate;

    private String diskInfo;

    private String remarke;

    private Timestamp createTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCpuRate() {
        return cpuRate;
    }

    public void setCpuRate(Integer cpuRate) {
        this.cpuRate = cpuRate;
    }

    public Integer getMemoryRate() {
        return memoryRate;
    }

    public void setMemoryRate(Integer memoryRate) {
        this.memoryRate = memoryRate;
    }

    public String getMemoryInfo() {
        return memoryInfo;
    }

    public void setMemoryInfo(String memoryInfo) {
        this.memoryInfo = memoryInfo;
    }

    public Integer getDiskRate() {
        return diskRate;
    }

    public void setDiskRate(Integer diskRate) {
        this.diskRate = diskRate;
    }

    public String getDiskInfo() {
        return diskInfo;
    }

    public void setDiskInfo(String diskInfo) {
        this.diskInfo = diskInfo;
    }

    public String getRemarke() {
        return remarke;
    }

    public void setRemarke(String remarke) {
        this.remarke = remarke;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    
}
