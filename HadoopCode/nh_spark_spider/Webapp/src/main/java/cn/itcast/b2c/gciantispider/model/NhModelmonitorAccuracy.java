package cn.itcast.b2c.gciantispider.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * NhModelmonitorAccuracy entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nh_modelmonitor_accuracy")
public class NhModelmonitorAccuracy implements java.io.Serializable {

    // Fields

    private Integer id;

    private Date date;

    private Integer timetype;

    private Integer flowtype;

    private Integer modeltype;

    private Integer accuracy;

    // Constructors

    /** default constructor */
    public NhModelmonitorAccuracy() {
    }

    /** minimal constructor */
    public NhModelmonitorAccuracy(Integer id) {
        this.id = id;
    }

    /** full constructor */
    public NhModelmonitorAccuracy(Integer id, Date date, Integer timetype, Integer flowtype, Integer modeltype, Integer accuracy) {
        this.id = id;
        this.date = date;
        this.timetype = timetype;
        this.flowtype = flowtype;
        this.modeltype = modeltype;
        this.accuracy = accuracy;
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

    @Column(name = "timetype")
    public Integer getTimetype() {
        return this.timetype;
    }

    public void setTimetype(Integer timetype) {
        this.timetype = timetype;
    }

    @Column(name = "flowtype")
    public Integer getFlowtype() {
        return this.flowtype;
    }

    public void setFlowtype(Integer flowtype) {
        this.flowtype = flowtype;
    }

    @Column(name = "modeltype")
    public Integer getModeltype() {
        return this.modeltype;
    }

    public void setModeltype(Integer modeltype) {
        this.modeltype = modeltype;
    }

    @Column(name = "accuracy")
    public Integer getAccuracy() {
        return this.accuracy;
    }

    public void setAccuracy(Integer accuracy) {
        this.accuracy = accuracy;
    }

}