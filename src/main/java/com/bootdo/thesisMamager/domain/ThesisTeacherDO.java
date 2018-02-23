package com.bootdo.thesisMamager.domain;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xc
 * @email 1992lcg@163.com
 * @date 2018-01-04 16:03:43
 */
public class ThesisTeacherDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//导师ID
	private Long id;
	//手机号
	private String mobile;
	//学校ID
	private Long schoolId;
	//院系ID
	private Long depId;
	//
	private String teacherTitle;
	//专业
	private String major;
	//学生数量
	private Integer studentCount;
	//创建时间
	private String createTm;
	//最后一次登录时间
	private String lastloginTm;
	//头像
	private String headImg;

	private String schoolName;
	private String depName;

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
	 * 设置：导师ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：导师ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机号
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：学校ID
	 */
	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}
	/**
	 * 获取：学校ID
	 */
	public Long getSchoolId() {
		return schoolId;
	}
	/**
	 * 设置：院系ID
	 */
	public void setDepId(Long depId) {
		this.depId = depId;
	}
	/**
	 * 获取：院系ID
	 */
	public Long getDepId() {
		return depId;
	}
	/**
	 * 设置：
	 */
	public void setTeacherTitle(String teacherTitle) {
		this.teacherTitle = teacherTitle;
	}
	/**
	 * 获取：
	 */
	public String getTeacherTitle() {
		return teacherTitle;
	}
	/**
	 * 设置：专业
	 */
	public void setMajor(String major) {
		this.major = major;
	}
	/**
	 * 获取：专业
	 */
	public String getMajor() {
		return major;
	}
	/**
	 * 设置：学生数量
	 */
	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}
	/**
	 * 获取：学生数量
	 */
	public Integer getStudentCount() {
		return studentCount;
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
	/**
	 * 设置：最后一次登录时间
	 */
	public void setLastloginTm(String lastloginTm) {
		this.lastloginTm = lastloginTm;
	}
	/**
	 * 获取：最后一次登录时间
	 */
	public String getLastloginTm() {
		return lastloginTm;
	}
	/**
	 * 设置：头像
	 */
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	/**
	 * 获取：头像
	 */
	public String getHeadImg() {
		return headImg;
	}
}
