package cn.itcast.b2c.gciantispider.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * NhProcessInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nh_process_info")
public class NhProcessInfo implements java.io.Serializable {

    // Fields

    private String id;

    private String processName;

    private Timestamp createDate;

    private String createPerson;

    private Integer status;

    private NhStrategy nhStrategy;

    private Set<NhRule> nhRules = new HashSet<NhRule>(0);

    private NhModel nhModel;

    // Constructors

    /** default constructor */
    public NhProcessInfo() {
    }

    /** minimal constructor */
    public NhProcessInfo(String id, String processName) {
        this.id = id;
        this.processName = processName;
    }

    /** full constructor */
    public NhProcessInfo(String id, String processName, Timestamp createDate, String createPerson, Integer status, NhStrategy nhStrategy, Set<NhRule> nhRules, NhModel nhModel) {
        this.id = id;
        this.processName = processName;
        this.createDate = createDate;
        this.createPerson = createPerson;
        this.status = status;
        this.nhStrategy = nhStrategy;
        this.nhRules = nhRules;
        this.nhModel = nhModel;
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

    @Column(name = "process_name", nullable = false, length = 64)
    public String getProcessName() {
        return this.processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    @Column(name = "create_date", length = 19)
    public Timestamp getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Column(name = "create_person", length = 64)
    public String getCreatePerson() {
        return this.createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "nhProcessInfo")
    @JsonManagedReference
    public NhStrategy getNhStrategy() {
        return this.nhStrategy;
    }

    public void setNhStrategy(NhStrategy nhStrategy) {
        this.nhStrategy = nhStrategy;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "nhProcessInfo")
    @JsonManagedReference
    public Set<NhRule> getNhRules() {
        return this.nhRules;
    }

    public void setNhRules(Set<NhRule> nhRules) {
        this.nhRules = nhRules;
    }

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "nhProcessInfo")
    @JsonManagedReference
    public NhModel getNhModel() {
        return this.nhModel;
    }

    public void setNhModel(NhModel nhModel) {
        this.nhModel = nhModel;
    }

}