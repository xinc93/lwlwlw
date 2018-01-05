package com.bootdo.thesisMamager.dao;

import com.bootdo.thesisMamager.domain.ThesisCollegeDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


/**
 * 
 * @author xc
 * @email 1992lcg@163.com
 * @date 2018-01-04 16:03:33
 */
@Mapper
public interface ThesisCollegeDao {

	ThesisCollegeDO get(Long id);
	
	List<ThesisCollegeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ThesisCollegeDO thesisCollege);
	
	int update(ThesisCollegeDO thesisCollege);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
