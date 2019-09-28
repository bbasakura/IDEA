package cn.itcast.b2c.gciantispider.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SystemRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "system_role")
public class SystemRole implements java.io.Serializable {

    // Fields

    private String id;

    private String name;

    private String cnname;

    private String description;

    private Date createtime;

    private Set<RefUserRole> refUserRoles = new HashSet<RefUserRole>(0);

    private Set<RefRolePermission> refRolePermissions = new HashSet<RefRolePermission>(0);

    // Constructors

    /** default constructor */
    public SystemRole() {
    }

    /** minimal constructor */
    public SystemRole(String id) {
        this.id = id;
    }

    /** full constructor */
    public SystemRole(String id, String name, String cnname, String description, Timestamp createtime, Set<RefUserRole> refUserRoles, Set<RefRolePermission> refRolePermissions) {
        this.id = id;
        this.name = name;
        this.cnname = cnname;
        this.description = description;
        this.createtime = createtime;
        this.refUserRoles = refUserRoles;
        this.refRolePermissions = refRolePermissions;
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

    @Column(name = "NAME", length = 128)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "CNNAME", length = 128)
    public String getCnname() {
        return this.cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "CREATETIME", length = 19)
    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "systemRole")
    public Set<RefUserRole> getRefUserRoles() {
        return this.refUserRoles;
    }

    public void setRefUserRoles(Set<RefUserRole> refUserRoles) {
        this.refUserRoles = refUserRoles;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "systemRole")
    public Set<RefRolePermission> getRefRolePermissions() {
        return this.refRolePermissions;
    }

    public void setRefRolePermissions(Set<RefRolePermission> refRolePermissions) {
        this.refRolePermissions = refRolePermissions;
    }

}