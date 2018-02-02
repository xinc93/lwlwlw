package com.bootdo.thesisMamager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.thesisMamager.dao.ThesisTemplateAttrDao;
import com.bootdo.thesisMamager.domain.ThesisTemplateAttrDO;
import com.bootdo.thesisMamager.service.ThesisTemplateAttrService;



@Service
public class ThesisTemplateAttrServiceImpl implements ThesisTemplateAttrService {
	@Autowired
	private ThesisTemplateAttrDao thesisTemplateAttrDao;
	
	@Override
	public ThesisTemplateAttrDO get(Long attributeid){
		return thesisTemplateAttrDao.get(attributeid);
	}
	
	@Override
	public List<ThesisTemplateAttrDO> list(Map<String, Object> map){
		return thesisTemplateAttrDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return thesisTemplateAttrDao.count(map);
	}
	
	@Override
	public int save(ThesisTemplateAttrDO thesisTemplateAttr){
		return thesisTemplateAttrDao.save(thesisTemplateAttr);
	}
	
	@Override
	public int update(ThesisTemplateAttrDO thesisTemplateAttr){
		return thesisTemplateAttrDao.update(thesisTemplateAttr);
	}
	
	@Override
	public int remove(Long attributeid){
		return thesisTemplateAttrDao.remove(attributeid);
	}
	
	@Override
	public int batchRemove(Long[] attributeids){
		return thesisTemplateAttrDao.batchRemove(attributeids);
	}
	
}
