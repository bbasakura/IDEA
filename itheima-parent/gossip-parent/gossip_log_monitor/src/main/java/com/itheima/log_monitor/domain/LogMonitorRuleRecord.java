package com.itheima.log_monitor.domain;

import java.io.Serializable;
import java.util.Date;

public class LogMonitorRuleRecord implements Serializable{

	private Long recordId;
	private Integer appId;
	private Integer ruleId;
	private Integer isEmail;
	private Integer isPhone;
	private Integer isClose;
	private String noticeInfo;
	private Date createDate;
	private Date updateDate;

	
	
	public Long getRecordId() {
		return recordId;
	}
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	public Integer getRuleId() {
		return ruleId;
	}
	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}
	public Integer getIsEmail() {
		return isEmail;
	}
	public void setIsEmail(Integer isEmail) {
		this.isEmail = isEmail;
	}
	public Integer getIsPhone() {
		return isPhone;
	}
	public void setIsPhone(Integer isPhone) {
		this.isPhone = isPhone;
	}
	public Integer getIsClose() {
		return isClose;
	}
	public void setIsClose(Integer isClose) {
		this.isClose = isClose;
	}
	public String getNoticeInfo() {
		return noticeInfo;
	}
	public void setNoticeInfo(String noticeInfo) {
		this.noticeInfo = noticeInfo;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	

}
