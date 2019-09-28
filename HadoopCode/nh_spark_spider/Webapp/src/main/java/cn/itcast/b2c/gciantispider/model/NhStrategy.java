package cn.itcast.b2c.gciantispider.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * NhStrategy entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nh_strategy")
public class NhStrategy implements java.io.Serializable {

    // Fields

    private String id;

    private NhProcessInfo nhProcessInfo;

    private Double crawlerBlacklistThresholds;

    private Double occBlacklistThresholds;

    // Constructors

    /** default constructor */
    public NhStrategy() {
    }

    /** minimal constructor */
    public NhStrategy(String id, NhProcessInfo nhProcessInfo) {
        this.id = id;
        this.nhProcessInfo = nhProcessInfo;
    }

    /** full constructor */
    public NhStrategy(String id, NhProcessInfo nhProcessInfo, Double crawlerBlacklistThresholds, Double occBlacklistThresholds) {
        this.id = id;
        this.nhProcessInfo = nhProcessInfo;
        this.crawlerBlacklistThresholds = crawlerBlacklistThresholds;
        this.occBlacklistThresholds = occBlacklistThresholds;
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

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @JsonBackReference
    public NhProcessInfo getNhProcessInfo() {
        return this.nhProcessInfo;
    }

    public void setNhProcessInfo(NhProcessInfo nhProcessInfo) {
        this.nhProcessInfo = nhProcessInfo;
    }

    @Column(name = "crawler_blacklist_thresholds", precision = 22, scale = 0)
    public Double getCrawlerBlacklistThresholds() {
        return this.crawlerBlacklistThresholds;
    }

    public void setCrawlerBlacklistThresholds(Double crawlerBlacklistThresholds) {
        this.crawlerBlacklistThresholds = crawlerBlacklistThresholds;
    }

    @Column(name = "occ_blacklist_thresholds", precision = 22, scale = 0)
    public Double getOccBlacklistThresholds() {
        return this.occBlacklistThresholds;
    }

    public void setOccBlacklistThresholds(Double occBlacklistThresholds) {
        this.occBlacklistThresholds = occBlacklistThresholds;
    }

}