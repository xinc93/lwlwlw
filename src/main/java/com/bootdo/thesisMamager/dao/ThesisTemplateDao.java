package com.bootdo.thesisMamager.dao;

import com.bootdo.thesisMamager.domain.ThesisTemplateDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-01-31 15:30:00
 */
@Mapper
public interface ThesisTemplateDao {

	ThesisTemplateDO get(Long templateid);
	
	List<ThesisTemplateDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ThesisTemplateDO thesisTemplate);
	
	int update(ThesisTemplateDO thesisTemplate);
	
	int remove(Long templateId);
	
	int batchRemove(Long[] templateids);
}
