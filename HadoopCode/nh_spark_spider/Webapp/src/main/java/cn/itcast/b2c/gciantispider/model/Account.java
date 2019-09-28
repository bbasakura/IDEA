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

/**
 * Account entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "account")
public class Account implements java.io.Serializable {

    // Fields

    private String id;

    private String name;

    private String password;

    private Timestamp lastLogTime;

    private Timestamp thisLogTime;

    private User user;

    private Set<RefUserRole> refUserRoles = new HashSet<RefUserRole>(0);

    // Constructors

    /** default constructor */
    public Account() {
    }

    /** minimal constructor */
    public Account(String id) {
        this.id = id;
    }

    /** full constructor */
    public Account(String id, String name, String password, Timestamp lastLogTime, Timestamp thisLogTime, User userInfo, Set<RefUserRole> refUserRoles) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.lastLogTime = lastLogTime;
        this.thisLogTime = thisLogTime;
        this.user = userInfo;
        this.refUserRoles = refUserRoles;
    }

    // Property accessors
    @Id
    @Column(name = "ID", unique = true, nullable = false, length = 64)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "NAME", length = 64)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "LAST_LOG_TIME", length = 19)
    public Timestamp getLastLogTime() {
        return this.lastLogTime;
    }

    public void setLastLogTime(Timestamp lastLogTime) {
        this.lastLogTime = lastLogTime;
    }
    
    @Column(name = "THIS_LOG_TIME", length = 19)
    public Timestamp getThisLogTime() {
        return thisLogTime;
    }

    public void setThisLogTime(Timestamp thisLogTime) {
        this.thisLogTime = thisLogTime;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
    public User getUser() {
        return this.user;
    }

    public void setUser(User userInfo) {
        this.user = userInfo;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
    public Set<RefUserRole> getRefUserRoles() {
        return this.refUserRoles;
    }

    public void setRefUserRoles(Set<RefUserRole> refUserRoles) {
        this.refUserRoles = refUserRoles;
    }

}