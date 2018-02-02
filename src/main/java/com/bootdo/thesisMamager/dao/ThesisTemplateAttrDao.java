package com.bootdo.thesisMamager.dao;

import com.bootdo.thesisMamager.domain.ThesisTemplateAttrDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-02 13:55:55
 */
@Mapper
public interface ThesisTemplateAttrDao {

	ThesisTemplateAttrDO get(Long attributeid);
	
	List<ThesisTemplateAttrDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ThesisTemplateAttrDO thesisTemplateAttr);
	
	int update(ThesisTemplateAttrDO thesisTemplateAttr);
	
	int remove(Long attributeId);
	
	int batchRemove(Long[] attributeids);
}
