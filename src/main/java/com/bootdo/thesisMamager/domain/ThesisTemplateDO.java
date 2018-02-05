package com.bootdo.thesisMamager.domain;

import java.io.Serializable;


/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-01-31 15:30:00
 */
public class ThesisTemplateDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long templateid;
	//
	private String templatename;
	//
	private Long shoolid;
	//
	private Long depid;

	private  String schoolName;
	private  String depName;

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	/**
	 * 设置：
	 */
	public void setTemplateid(Long templateid) {
		this.templateid = templateid;
	}
	/**
	 * 获取：
	 */
	public Long getTemplateid() {
		return templateid;
	}
	/**
	 * 设置：
	 */
	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}
	/**
	 * 获取：
	 */
	public String getTemplatename() {
		return templatename;
	}
	/**
	 * 设置：
	 */
	public void setShoolid(Long shoolid) {
		this.shoolid = shoolid;
	}
	/**
	 * 获取：
	 */
	public Long getShoolid() {
		return shoolid;
	}
	/**
	 * 设置：
	 */
	public void setDepid(Long depid) {
		this.depid = depid;
	}
	/**
	 * 获取：
	 */
	public Long getDepid() {
		return depid;
	}
}
