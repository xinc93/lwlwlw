package com.bootdo.thesisMamager.domain;

import com.bootdo.common.utils.LongJsonDeserializer;
import com.bootdo.common.utils.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-01-05 09:29:29
 */
public class ThesisStudentDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//学生ID
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long id;
	//学生姓名
	private String name;
	//手机号
	private String mobile;
	//学历层次
	private String eduClass;
	//学习形式
	private String learnClass;
	//专业
	private String major;
	//专业方向
	private String professionalDirection;
	//年级
	private String schoolGrade;
	//学籍号
	private String schoolNo;
	//学校ID
	private Long schoolId;
	//院系ID
	private Long depId;
	//导师id
	private Long teacherId;
	//账号类型   1 个人账号  2 学校授权账号
	private String accountType;
	//账号有效期
	private String accountTm;
	//chuangjianshijian 
	private String createTm;
	//最后一次登录时间
	private String lastloginTm;
	//头像
	private String headImg;

	/**
	 * 设置：学生ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：学生ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：学生姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：学生姓名
	 */
	public String getName() {
		return name;
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
	 * 设置：学历层次
	 */
	public void setEduClass(String eduClass) {
		this.eduClass = eduClass;
	}
	/**
	 * 获取：学历层次
	 */
	public String getEduClass() {
		return eduClass;
	}
	/**
	 * 设置：学习形式
	 */
	public void setLearnClass(String learnClass) {
		this.learnClass = learnClass;
	}
	/**
	 * 获取：学习形式
	 */
	public String getLearnClass() {
		return learnClass;
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
	 * 设置：专业方向
	 */
	public String getProfessionalDirection() {
		return professionalDirection;
	}

	public void setProfessionalDirection(String professionalDirection) {
		this.professionalDirection = professionalDirection;
	}

	/**
	 * 设置：年级
	 */
	public void setSchoolGrade(String schoolGrade) {
		this.schoolGrade = schoolGrade;
	}
	/**
	 * 获取：年级
	 */
	public String getSchoolGrade() {
		return schoolGrade;
	}
	/**
	 * 设置：学籍号
	 */
	public void setSchoolNo(String schoolNo) {
		this.schoolNo = schoolNo;
	}
	/**
	 * 获取：学籍号
	 */
	public String getSchoolNo() {
		return schoolNo;
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
	 * 设置：导师id
	 */
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	/**
	 * 获取：导师id
	 */
	public Long getTeacherId() {
		return teacherId;
	}
	/**
	 * 设置：账号类型   1 个人账号  2 学校授权账号
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	/**
	 * 获取：账号类型   1 个人账号  2 学校授权账号
	 */
	public String getAccountType() {
		return accountType;
	}
	/**
	 * 设置：账号有效期
	 */
	public void setAccountTm(String accountTm) {
		this.accountTm = accountTm;
	}
	/**
	 * 获取：账号有效期
	 */
	public String getAccountTm() {
		return accountTm;
	}
	/**
	 * 设置：chuangjianshijian 
	 */
	public void setCreateTm(String createTm) {
		this.createTm = createTm;
	}
	/**
	 * 获取：chuangjianshijian 
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
