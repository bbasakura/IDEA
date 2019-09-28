package cn.itcast.b2c.gciantispider.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * NhCrawlerQueryRoutesRank entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nh_crawler_query_routes_rank", catalog = "gciantispider")
public class NhCrawlerQueryRoutesRank implements java.io.Serializable {

    // Fields

    private String id;

    private String depairport;

    private String arrairport;

    private Date departureDate;

    private Integer grapFrequency;

    private Date recordTime;

    // Constructors

    /** default constructor */
    public NhCrawlerQueryRoutesRank() {
    }

    /** minimal constructor */
    public NhCrawlerQueryRoutesRank(String id) {
        this.id = id;
    }

    /** full constructor */
    public NhCrawlerQueryRoutesRank(String id, String depairport, String arrairport, Date departureDate, Integer grapFrequency, Date recordTime) {
        this.id = id;
        this.depairport = depairport;
        this.arrairport = arrairport;
        this.departureDate = departureDate;
        this.grapFrequency = grapFrequency;
        this.recordTime = recordTime;
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

    @Column(name = "depairport", length = 64)
    public String getDepairport() {
        return this.depairport;
    }

    public void setDepairport(String depairport) {
        this.depairport = depairport;
    }

    @Column(name = "arrairport", length = 64)
    public String getArrairport() {
        return this.arrairport;
    }

    public void setArrairport(String arrairport) {
        this.arrairport = arrairport;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "departureDate", length = 10)
    public Date getDepartureDate() {
        return this.departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    @Column(name = "grap_frequency")
    public Integer getGrapFrequency() {
        return this.grapFrequency;
    }

    public void setGrapFrequency(Integer grapFrequency) {
        this.grapFrequency = grapFrequency;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "record_time", length = 10)
    public Date getRecordTime() {
        return this.recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

}