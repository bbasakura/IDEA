package com.itheima.log_monitor.domain;

import java.io.Serializable;
import java.util.Date;

public class LogMonitorUser implements Serializable{

	private Long userId;
	private String name;
	private String mobile;
	private String email;
	private int isValid;
	private Date createDate;
	private Date updateDate;
	private String createUser;
	private String updateUser;
	private int chargeAppId;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIsValid() {
		return isValid;
	}
	public void setIsValid(int isValid) {
		this.isValid = isValid;
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
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public int getChargeAppId() {
		return chargeAppId;
	}
	public void setChargeAppId(int chargeAppId) {
		this.chargeAppId = chargeAppId;
	}
	
	

}
