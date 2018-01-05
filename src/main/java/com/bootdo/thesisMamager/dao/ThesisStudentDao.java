package com.bootdo.thesisMamager.dao;

import com.bootdo.thesisMamager.domain.ThesisStudentDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-01-05 09:29:29
 */
@Mapper
public interface ThesisStudentDao {

	ThesisStudentDO get(Long id);
	
	List<ThesisStudentDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ThesisStudentDO thesisStudent);
	
	int update(ThesisStudentDO thesisStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
