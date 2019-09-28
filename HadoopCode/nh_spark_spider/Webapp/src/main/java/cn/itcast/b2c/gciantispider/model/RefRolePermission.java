package cn.itcast.b2c.gciantispider.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * RefRolePermission entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ref_role_permission")
public class RefRolePermission implements java.io.Serializable {

    // Fields

    private String rrId;

    private Permission permission;

    private SystemRole systemRole;

    // Constructors

    /** default constructor */
    public RefRolePermission() {
    }

    /** minimal constructor */
    public RefRolePermission(String rrId) {
        this.rrId = rrId;
    }

    /** full constructor */
    public RefRolePermission(String rrId, Permission permission, SystemRole systemRole) {
        this.rrId = rrId;
        this.permission = permission;
        this.systemRole = systemRole;
    }

    // Property accessors
    @Id
    @Column(name = "RR_ID", unique = true, nullable = false, length = 64)
    public String getRrId() {
        return this.rrId;
    }

    public void setRrId(String rrId) {
        this.rrId = rrId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERMISSION_ID")
    public Permission getPermission() {
        return this.permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID")
    public SystemRole getSystemRole() {
        return this.systemRole;
    }

    public void setSystemRole(SystemRole systemRole) {
        this.systemRole = systemRole;
    }

}