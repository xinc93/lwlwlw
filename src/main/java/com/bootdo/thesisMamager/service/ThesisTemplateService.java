package com.bootdo.thesisMamager.service;

import com.bootdo.thesisMamager.domain.ThesisTemplateDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-01-31 15:30:00
 */
public interface ThesisTemplateService {
	
	ThesisTemplateDO get(Long templateid);
	
	List<ThesisTemplateDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ThesisTemplateDO thesisTemplate);
	
	int update(ThesisTemplateDO thesisTemplate);
	
	int remove(Long templateid);
	
	int batchRemove(Long[] templateids);
}
