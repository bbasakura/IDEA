package cn.itcast.b2c.gciantispider.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * RefUserRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ref_user_role")
public class RefUserRole implements java.io.Serializable {

    // Fields

    private String arId;

    private SystemRole systemRole;

    private Account account;

    // Constructors

    /** default constructor */
    public RefUserRole() {
    }

    /** minimal constructor */
    public RefUserRole(String arId) {
        this.arId = arId;
    }

    /** full constructor */
    public RefUserRole(String arId, SystemRole systemRole, Account account) {
        this.arId = arId;
        this.systemRole = systemRole;
        this.account = account;
    }

    // Property accessors
    @Id
    @Column(name = "AR_ID", unique = true, nullable = false, length = 64)
    public String getArId() {
        return this.arId;
    }

    public void setArId(String arId) {
        this.arId = arId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID")
    public SystemRole getSystemRole() {
        return this.systemRole;
    }

    public void setSystemRole(SystemRole systemRole) {
        this.systemRole = systemRole;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}