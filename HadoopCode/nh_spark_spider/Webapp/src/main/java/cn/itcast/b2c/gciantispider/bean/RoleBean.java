package cn.itcast.b2c.gciantispider.bean;

import java.util.Date;

@SuppressWarnings("serial")
public class RoleBean implements java.io.Serializable{
    
    private String id;
    /**
     * 角色名
     */
    private String name;
    /**
     * 角色的中文名称
     */
    private String cnname;
    /**
     * 角色描述
     */
    private String description;
    /**
     * 角色状态
     */
    private String state;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 创建人
     */
    private String creater;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCnname() {
        return cnname;
    }
    
    public void setCnname(String cnname) {
        this.cnname = cnname;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public Date getCreatetime() {
        return createtime;
    }
    
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    
    public String getCreater() {
        return creater;
    }
    
    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
        
}
