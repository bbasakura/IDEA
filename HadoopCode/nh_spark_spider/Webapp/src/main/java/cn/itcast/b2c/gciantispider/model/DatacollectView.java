package cn.itcast.b2c.gciantispider.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Datacollect entity. @author MyEclipse Persistence Tools
 */
public class DatacollectView implements java.io.Serializable {


    private String id;

    private String serverName;

    private Integer beforeYesterdayNum;
    private Integer activeNum;

    private Integer yesterdayNum;

    private Integer lastThreeDaysNum;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServerName() {
        return this.serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Integer getBeforeYesterdayNum() {
        return this.beforeYesterdayNum;
    }

    public void setBeforeYesterdayNum(Integer beforeYesterdayNum) {
        this.beforeYesterdayNum = beforeYesterdayNum;
    }

    public Integer getYesterdayNum() {
        return this.yesterdayNum;
    }

    public void setYesterdayNum(Integer yesterdayNum) {
        this.yesterdayNum = yesterdayNum;
    }

    public Integer getLastThreeDaysNum() {
        return this.lastThreeDaysNum;
    }

    public void setLastThreeDaysNum(Integer lastThreeDaysNum) {
        this.lastThreeDaysNum = lastThreeDaysNum;
    }

	public Integer getActiveNum() {
		return activeNum;
	}

	public void setActiveNum(Integer activeNum) {
		this.activeNum = activeNum;
	}

}