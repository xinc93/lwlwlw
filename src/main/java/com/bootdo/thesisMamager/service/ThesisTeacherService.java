package com.bootdo.thesisMamager.service;

import com.bootdo.thesisMamager.domain.ThesisTeacherDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xc
 * @email 1992lcg@163.com
 * @date 2018-01-04 16:03:43
 */
public interface ThesisTeacherService {
	
	ThesisTeacherDO get(Long id);
	
	List<ThesisTeacherDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ThesisTeacherDO thesisTeacher);
	
	int update(ThesisTeacherDO thesisTeacher);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
