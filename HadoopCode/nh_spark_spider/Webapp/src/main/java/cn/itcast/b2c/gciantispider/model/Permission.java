package cn.itcast.b2c.gciantispider.model;

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
 * Permission entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "permission")
public class Permission implements java.io.Serializable {

    // Fields

    private String id;

    private String name;

    private String num;

    private String parentid;

    private String url;

    private String description;

    private String perlevel;

    private Set<RefRolePermission> refRolePermissions = new HashSet<RefRolePermission>(0);

    // Constructors

    /** default constructor */
    public Permission() {
    }

    /** minimal constructor */
    public Permission(String id) {
        this.id = id;
    }

    /** full constructor */
    public Permission(String id, String name, String num, String parentid, String url, String description, String perlevel, Set<RefRolePermission> refRolePermissions) {
        this.id = id;
        this.name = name;
        this.num = num;
        this.parentid = parentid;
        this.url = url;
        this.description = description;
        this.perlevel = perlevel;
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

    @Column(name = "NUM", length = 64)
    public String getNum() {
        return this.num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Column(name = "PARENTID", length = 64)
    public String getParentid() {
        return this.parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    @Column(name = "URL")
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "PERLEVEL", length = 10)
    public String getPerlevel() {
        return this.perlevel;
    }

    public void setPerlevel(String perlevel) {
        this.perlevel = perlevel;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "permission")
    public Set<RefRolePermission> getRefRolePermissions() {
        return this.refRolePermissions;
    }

    public void setRefRolePermissions(Set<RefRolePermission> refRolePermissions) {
        this.refRolePermissions = refRolePermissions;
    }

}