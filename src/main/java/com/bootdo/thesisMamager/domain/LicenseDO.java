package com.bootdo.thesisMamager.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-26 15:29:02
 */
public class LicenseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//授权码
	private String licenseKey;
	//授权院校
	private String licenseSchool;
	//授权时间
	private String createTm;
	//授权终止时间
	private String endTm;
	//授权使用数量
	private String amountUsed;
	//授权未使用数量
	private String amountUnused;
	//授权数量
	private String licenseCount;
	//授权使用状态
	private String state;
	private String schoolName;

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：授权码
	 */
	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}
	/**
	 * 获取：授权码
	 */
	public String getLicenseKey() {
		return licenseKey;
	}
	/**
	 * 设置：授权院校
	 */
	public void setLicenseSchool(String licenseSchool) {
		this.licenseSchool = licenseSchool;
	}
	/**
	 * 获取：授权院校
	 */
	public String getLicenseSchool() {
		return licenseSchool;
	}
	/**
	 * 设置：授权时间
	 */
	public void setCreateTm(String createTm) {
		this.createTm = createTm;
	}
	/**
	 * 获取：授权时间
	 */
	public String getCreateTm() {
		return createTm;
	}
	/**
	 * 设置：授权终止时间
	 */
	public void setEndTm(String endTm) {
		this.endTm = endTm;
	}
	/**
	 * 获取：授权终止时间
	 */
	public String getEndTm() {
		return endTm;
	}
	/**
	 * 设置：授权使用数量
	 */
	public void setAmountUsed(String amountUsed) {
		this.amountUsed = amountUsed;
	}
	/**
	 * 获取：授权使用数量
	 */
	public String getAmountUsed() {
		return amountUsed;
	}
	/**
	 * 设置：授权未使用数量
	 */
	public void setAmountUnused(String amountUnused) {
		this.amountUnused = amountUnused;
	}
	/**
	 * 获取：授权未使用数量
	 */
	public String getAmountUnused() {
		return amountUnused;
	}
	/**
	 * 设置：授权数量
	 */
	public void setLicenseCount(String licenseCount) {
		this.licenseCount = licenseCount;
	}
	/**
	 * 获取：授权数量
	 */
	public String getLicenseCount() {
		return licenseCount;
	}
	/**
	 * 设置：授权使用状态
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：授权使用状态
	 */
	public String getState() {
		return state;
	}
}
