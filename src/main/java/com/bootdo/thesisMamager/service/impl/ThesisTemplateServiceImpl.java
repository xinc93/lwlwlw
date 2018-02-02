package com.bootdo.thesisMamager.service.impl;

import com.bootdo.thesisMamager.dao.ThesisTemplateDao;
import com.bootdo.thesisMamager.domain.ThesisTemplateDO;
import com.bootdo.thesisMamager.service.ThesisTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ThesisTemplateServiceImpl implements ThesisTemplateService {
	@Autowired
	private ThesisTemplateDao thesisTemplateDao;
	
	@Override
	public ThesisTemplateDO get(Long templateid){
		return thesisTemplateDao.get(templateid);
	}
	
	@Override
	public List<ThesisTemplateDO> list(Map<String, Object> map){
		return thesisTemplateDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return thesisTemplateDao.count(map);
	}
	
	@Override
	public int save(ThesisTemplateDO thesisTemplate){
		return thesisTemplateDao.save(thesisTemplate);
	}
	
	@Override
	public int update(ThesisTemplateDO thesisTemplate){
		return thesisTemplateDao.update(thesisTemplate);
	}
	
	@Override
	public int remove(Long templateid){
		return thesisTemplateDao.remove(templateid);
	}
	
	@Override
	public int batchRemove(Long[] templateids){
		return thesisTemplateDao.batchRemove(templateids);
	}
	
}
