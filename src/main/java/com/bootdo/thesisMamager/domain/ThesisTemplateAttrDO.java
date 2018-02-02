package com.bootdo.thesisMamager.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-02 13:55:55
 */
public class ThesisTemplateAttrDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long attributeid;
	//
	private String attributename;
	//
	private Long templateid;
	//预留字段
	private String attrbuteccode;

	/**
	 * 设置：
	 */
	public void setAttributeid(Long attributeid) {
		this.attributeid = attributeid;
	}
	/**
	 * 获取：
	 */
	public Long getAttributeid() {
		return attributeid;
	}
	/**
	 * 设置：
	 */
	public void setAttributename(String attributename) {
		this.attributename = attributename;
	}
	/**
	 * 获取：
	 */
	public String getAttributename() {
		return attributename;
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
	 * 设置：预留字段
	 */
	public void setAttrbuteccode(String attrbuteccode) {
		this.attrbuteccode = attrbuteccode;
	}
	/**
	 * 获取：预留字段
	 */
	public String getAttrbuteccode() {
		return attrbuteccode;
	}
}
