package cn.itcast.b2c.gciantispider.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * NhSysmonitorSpiderdistinguish entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nh_sysmonitor_spiderdistinguish")
public class NhSysmonitorSpiderdistinguish implements java.io.Serializable {

    // Fields

    private Integer id;

    private Date date;

    private Integer numspider;

    // Constructors

    /** default constructor */
    public NhSysmonitorSpiderdistinguish() {
    }

    /** full constructor */
    public NhSysmonitorSpiderdistinguish(Date date, Integer numspider) {
        this.date = date;
        this.numspider = numspider;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "date", length = 10)
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "numspider")
    public Integer getNumspider() {
        return this.numspider;
    }

    public void setNumspider(Integer numspider) {
        this.numspider = numspider;
    }

}