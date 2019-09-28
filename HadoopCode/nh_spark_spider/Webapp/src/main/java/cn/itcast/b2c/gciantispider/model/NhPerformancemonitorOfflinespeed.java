package cn.itcast.b2c.gciantispider.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * NhPerformancemonitorOfflinespeed entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nh_performancemonitor_offlinespeed")
public class NhPerformancemonitorOfflinespeed implements java.io.Serializable {

    // Fields

    private Integer id;

    private Date date;

    private Integer speed;

    // Constructors

    /** default constructor */
    public NhPerformancemonitorOfflinespeed() {
    }

    /** minimal constructor */
    public NhPerformancemonitorOfflinespeed(Integer id) {
        this.id = id;
    }

    /** full constructor */
    public NhPerformancemonitorOfflinespeed(Integer id, Date date, Integer speed) {
        this.id = id;
        this.date = date;
        this.speed = speed;
    }

    // Property accessors
    @Id
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

    @Column(name = "speed")
    public Integer getSpeed() {
        return this.speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

}