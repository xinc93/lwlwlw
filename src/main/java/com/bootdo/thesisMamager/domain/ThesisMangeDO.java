package com.bootdo.thesisMamager.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-06 15:40:40
 */
public class ThesisMangeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//论文名字
	private String thesisName;
	//学校id
	private Long thesisSchool;
	//院系id
	private Long thesisDep;
	//所属专业
	private String thesisDirection;
	//所属学生
	private Long thesisStuid;
	//所属导师
	private Long thesisTeachid;
	//论文状态(0.未完成,1已完成)
	private Long thesisStutes;
	//论文类型(0.硕士,1本科)
	private Long thesisType;
	//
	private String updateTime;
	//
	private Long updateCount;
	//是否批阅(0.批阅,1未批)
	private Long isRead;

	private  String schoolname;

	private String teachername;

	private String studentname;

	private String depname;

	/**
	 * 设置：id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：论文名字
	 */
	public void setThesisName(String thesisName) {
		this.thesisName = thesisName;
	}
	/**
	 * 获取：论文名字
	 */
	public String getThesisName() {
		return thesisName;
	}
	/**
	 * 设置：学校id
	 */
	public void setThesisSchool(Long thesisSchool) {
		this.thesisSchool = thesisSchool;
	}
	/**
	 * 获取：学校id
	 */
	public Long getThesisSchool() {
		return thesisSchool;
	}
	/**
	 * 设置：院系id
	 */
	public void setThesisDep(Long thesisDep) {
		this.thesisDep = thesisDep;
	}
	/**
	 * 获取：院系id
	 */
	public Long getThesisDep() {
		return thesisDep;
	}
	/**
	 * 设置：所属专业
	 */
	public void setThesisDirection(String thesisDirection) {
		this.thesisDirection = thesisDirection;
	}
	/**
	 * 获取：所属专业
	 */
	public String getThesisDirection() {
		return thesisDirection;
	}
	/**
	 * 设置：所属学生
	 */
	public void setThesisStuid(Long thesisStuid) {
		this.thesisStuid = thesisStuid;
	}
	/**
	 * 获取：所属学生
	 */
	public Long getThesisStuid() {
		return thesisStuid;
	}
	/**
	 * 设置：所属导师
	 */
	public void setThesisTeachid(Long thesisTeachid) {
		this.thesisTeachid = thesisTeachid;
	}
	/**
	 * 获取：所属导师
	 */
	public Long getThesisTeachid() {
		return thesisTeachid;
	}
	/**
	 * 设置：论文状态(0.未完成,1已完成)
	 */
	public void setThesisStutes(Long thesisStutes) {
		this.thesisStutes = thesisStutes;
	}
	/**
	 * 获取：论文状态(0.未完成,1已完成)
	 */
	public Long getThesisStutes() {
		return thesisStutes;
	}
	/**
	 * 设置：论文类型(0.硕士,1本科)
	 */
	public void setThesisType(Long thesisType) {
		this.thesisType = thesisType;
	}
	/**
	 * 获取：论文类型(0.硕士,1本科)
	 */
	public Long getThesisType() {
		return thesisType;
	}
	/**
	 * 设置：
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public String getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：
	 */
	public void setUpdateCount(Long updateCount) {
		this.updateCount = updateCount;
	}
	/**
	 * 获取：
	 */
	public Long getUpdateCount() {
		return updateCount;
	}
	/**
	 * 设置：是否批阅(0.批阅,1未批)
	 */
	public void setIsRead(Long isRead) {
		this.isRead = isRead;
	}
	/**
	 * 获取：是否批阅(0.批阅,1未批)
	 */
	public Long getIsRead() {
		return isRead;
	}

	public String getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}

	public String getTeachername() {
		return teachername;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public String getDepname() {
		return depname;
	}

	public void setDepname(String depname) {
		this.depname = depname;
	}
}
