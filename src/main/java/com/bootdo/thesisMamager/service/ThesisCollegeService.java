package com.bootdo.thesisMamager.service;

import com.bootdo.thesisMamager.domain.ThesisCollegeDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xc
 * @email 1992lcg@163.com
 * @date 2018-01-04 16:03:33
 */
public interface ThesisCollegeService {
	
	ThesisCollegeDO get(Long id);

	ThesisCollegeDO getByPid(Long id);
	
	List<ThesisCollegeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ThesisCollegeDO thesisCollege);
	
	int update(ThesisCollegeDO thesisCollege);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
