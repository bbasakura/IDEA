package cn.itcast.b2c.gciantispider.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * NhRule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nh_rule")
public class NhRule implements java.io.Serializable {

    // Fields

    private String id;

    private NhProcessInfo nhProcessInfo;

    private Integer ruleType;

    private Integer crawlerType;

    private Integer ruleName;

    private Double arg0;

    private Double arg1;

    private Integer score;

    private Integer status;

    // Constructors

    /** default constructor */
    public NhRule() {
    }

    /** minimal constructor */
    public NhRule(String id) {
        this.id = id;
    }

    /** full constructor */
    public NhRule(String id, NhProcessInfo nhProcessInfo, Integer ruleType, Integer crawlerType, Integer ruleName, Double arg0, Double arg1, Integer score, Integer status) {
        this.id = id;
        this.nhProcessInfo = nhProcessInfo;
        this.ruleType = ruleType;
        this.crawlerType = crawlerType;
        this.ruleName = ruleName;
        this.arg0 = arg0;
        this.arg1 = arg1;
        this.score = score;
        this.status = status;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "process_id")
    @JsonBackReference
    public NhProcessInfo getNhProcessInfo() {
        return this.nhProcessInfo;
    }

    public void setNhProcessInfo(NhProcessInfo nhProcessInfo) {
        this.nhProcessInfo = nhProcessInfo;
    }

    @Column(name = "rule_type")
    public Integer getRuleType() {
        return this.ruleType;
    }

    public void setRuleType(Integer ruleType) {
        this.ruleType = ruleType;
    }

    @Column(name = "crawler_type")
    public Integer getCrawlerType() {
        return this.crawlerType;
    }

    public void setCrawlerType(Integer crawlerType) {
        this.crawlerType = crawlerType;
    }

    @Column(name = "rule_name")
    public Integer getRuleName() {
        return this.ruleName;
    }

    public void setRuleName(Integer ruleName) {
        this.ruleName = ruleName;
    }

    @Column(name = "arg0", precision = 22, scale = 0)
    public Double getArg0() {
        return this.arg0;
    }

    public void setArg0(Double arg0) {
        this.arg0 = arg0;
    }

    @Column(name = "arg1", precision = 22, scale = 0)
    public Double getArg1() {
        return this.arg1;
    }

    public void setArg1(Double arg1) {
        this.arg1 = arg1;
    }

    @Column(name = "score")
    public Integer getScore() {
        return this.score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}