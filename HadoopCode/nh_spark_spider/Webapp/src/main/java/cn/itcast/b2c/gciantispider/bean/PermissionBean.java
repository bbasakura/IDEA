package cn.itcast.b2c.gciantispider.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PermissionBean implements Serializable{
    /**
     * ID
     */
    private String id;
    /**
     * 权限点名
     */
    private String perName;
    /**
     * 权限编号
     */
    private String perNum;
    /**
     * 权限点父ID
     */
    private String perParentId;
    /**
     * 权限描述
     */
    private String perDesr;
    /**
     * 权限URL
     */
    private String perUrl;
    
    public String getPerName() {
        return perName;
    }
    public void setPerName(String perName) {
        this.perName = perName;
    }
    public String getPerNum() {
        return perNum;
    }
    public void setPerNum(String perNum) {
        this.perNum = perNum;
    }
    public String getPerParentId() {
        return perParentId;
    }
    public void setPerParentId(String perParentId) {
        this.perParentId = perParentId;
    }
    public String getPerDesr() {
        return perDesr;
    }
    public void setPerDesr(String perDesr) {
        this.perDesr = perDesr;
    }
    public String getPerUrl() {
        return perUrl;
    }
    public void setPerUrl(String perUrl) {
        this.perUrl = perUrl;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    
}
