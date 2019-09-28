package cn.itcast.b2c.gciantispider.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_info")
public class User implements java.io.Serializable {

    // Fields

    private String id;

    private Account account;

    private String name;

    private String mobile;

    private String email;

    // Constructors

    /** default constructor */
    public User() {
    }

    /** minimal constructor */
    public User(String id, Account account) {
        this.id = id;
        this.account = account;
    }

    /** full constructor */
    public User(String id, Account account, String name, String mobile, String email) {
        this.id = id;
        this.account = account;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
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

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Column(name = "NAME", length = 128)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "MOBILE", length = 64)
    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "EMAIL", length = 64)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}