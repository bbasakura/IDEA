package cn.itcast.b2c.gciantispider.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NhCrawlerCurdayInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nh_crawler_curday_info")
public class NhCrawlerCurdayInfo implements java.io.Serializable {

    // Fields

    private String id;

    //private Date time;

    private Double flowNum;

    private Timestamp identifyTime;

    // Constructors

    /** default constructor */
    public NhCrawlerCurdayInfo() {
    }

    /** minimal constructor */
    public NhCrawlerCurdayInfo(String id) {
        this.id = id;
    }

    /** full constructor */
    public NhCrawlerCurdayInfo(String id, Double flowNum, Timestamp identifyTime) {
        this.id = id;
       // this.time = time;
        this.flowNum = flowNum;
        this.identifyTime = identifyTime;
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

  /*  @Temporal(TemporalType.DATE)
    @Column(name = "time", length = 10)
    public Date getTime() {
        return this.time;
    }

    public void setTime(Date time) {
        this.time = time;
    }*/

    @Column(name = "flow_num", precision = 22, scale = 0)
    public Double getFlowNum() {
        return this.flowNum;
    }

    public void setFlowNum(Double flowNum) {
        this.flowNum = flowNum;
    }

    @Column(name = "identify_time", length = 19)
    public Timestamp getIdentifyTime() {
        return this.identifyTime;
    }

    public void setIdentifyTime(Timestamp identifyTime) {
        this.identifyTime = identifyTime;
    }

}