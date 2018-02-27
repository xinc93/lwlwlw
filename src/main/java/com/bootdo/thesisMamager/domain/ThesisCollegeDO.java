package com.bootdo.thesisMamager.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xc
 * @email 1992lcg@163.com
 * @date 2018-01-04 16:03:33
 */
public class ThesisCollegeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//学校 部门ID
	private Long id;
	//父ID   根节点父ID   默认 0
	private Long pid;
	//层级
	private Integer level;
	//名称
	private String name;
	//0 正常使用  1 停止使用
	private Integer state;
	//授权码   ( 记录 ：授权码可对单独院系  也可以整个学校所有院系共用  授权码有时限限制  和前端用户使用人数限制)
	private String licenseKey;
	//创建时间
	private String createTm;

	private String isLicense;

	public String getIsLicense() {
		return isLicense;
	}

	public void setIsLicense(String isLicense) {
		this.isLicense = isLicense;
	}

	/**
	 * 设置：学校 部门ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：学校 部门ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：父ID   根节点父ID   默认 0
	 */
	public void setPid(Long pid) {
		this.pid = pid;
	}
	/**
	 * 获取：父ID   根节点父ID   默认 0
	 */
	public Long getPid() {
		return pid;
	}
	/**
	 * 设置：层级
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	/**
	 * 获取：层级
	 */
	public Integer getLevel() {
		return level;
	}
	/**
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：0 正常使用  1 停止使用
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：0 正常使用  1 停止使用
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * 设置：授权码   ( 记录 ：授权码可对单独院系  也可以整个学校所有院系共用  授权码有时限限制  和前端用户使用人数限制)
	 */
	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}
	/**
	 * 获取：授权码   ( 记录 ：授权码可对单独院系  也可以整个学校所有院系共用  授权码有时限限制  和前端用户使用人数限制)
	 */
	public String getLicenseKey() {
		return licenseKey;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTm(String createTm) {
		this.createTm = createTm;
	}
	/**
	 * 获取：创建时间
	 */
	public String getCreateTm() {
		return createTm;
	}
}
