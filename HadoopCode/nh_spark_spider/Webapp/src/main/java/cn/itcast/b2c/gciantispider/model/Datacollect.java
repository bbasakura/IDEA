package cn.itcast.b2c.gciantispider.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Datacollect entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "datacollect")
public class Datacollect implements java.io.Serializable {

    // Fields

    private String id;

    private String serverName;

    private Integer beforeYesterdayNum;

    private Integer yesterdayNum;

    private Integer lastThreeDaysNum;

    // Constructors

    /** default constructor */
    public Datacollect() {
    }

    /** minimal constructor */
    public Datacollect(String id) {
        this.id = id;
    }

    /** full constructor */
    public Datacollect(String id, String serverName, Integer beforeYesterdayNum, Integer yesterdayNum, Integer lastThreeDaysNum) {
        this.id = id;
        this.serverName = serverName;
        this.beforeYesterdayNum = beforeYesterdayNum;
        this.yesterdayNum = yesterdayNum;
        this.lastThreeDaysNum = lastThreeDaysNum;
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

    @Column(name = "server_name", length = 128)
    public String getServerName() {
        return this.serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    @Column(name = "before_yesterday_num")
    public Integer getBeforeYesterdayNum() {
        return this.beforeYesterdayNum;
    }

    public void setBeforeYesterdayNum(Integer beforeYesterdayNum) {
        this.beforeYesterdayNum = beforeYesterdayNum;
    }

    @Column(name = "yesterday_num")
    public Integer getYesterdayNum() {
        return this.yesterdayNum;
    }

    public void setYesterdayNum(Integer yesterdayNum) {
        this.yesterdayNum = yesterdayNum;
    }

    @Column(name = "last_three_days_num")
    public Integer getLastThreeDaysNum() {
        return this.lastThreeDaysNum;
    }

    public void setLastThreeDaysNum(Integer lastThreeDaysNum) {
        this.lastThreeDaysNum = lastThreeDaysNum;
    }


}