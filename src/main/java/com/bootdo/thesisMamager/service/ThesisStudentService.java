package com.bootdo.thesisMamager.service;

import com.bootdo.thesisMamager.domain.ThesisStudentDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-01-05 09:29:29
 */
public interface ThesisStudentService {
	
	ThesisStudentDO get(Long id);

	ThesisStudentDO queryone(Map map);
	
	List<ThesisStudentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ThesisStudentDO thesisStudent);
	
	int update(ThesisStudentDO thesisStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
