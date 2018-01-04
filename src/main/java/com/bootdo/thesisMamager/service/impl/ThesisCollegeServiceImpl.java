package com.bootdo.thesisMamager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.thesisMamager.dao.ThesisCollegeDao;
import com.bootdo.thesisMamager.domain.ThesisCollegeDO;
import com.bootdo.thesisMamager.service.ThesisCollegeService;



@Service
public class ThesisCollegeServiceImpl implements ThesisCollegeService {
	@Autowired
	private ThesisCollegeDao thesisCollegeDao;
	
	@Override
	public ThesisCollegeDO get(Long id){
		return thesisCollegeDao.get(id);
	}
	
	@Override
	public List<ThesisCollegeDO> list(Map<String, Object> map){
		return thesisCollegeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return thesisCollegeDao.count(map);
	}
	
	@Override
	public int save(ThesisCollegeDO thesisCollege){
		return thesisCollegeDao.save(thesisCollege);
	}
	
	@Override
	public int update(ThesisCollegeDO thesisCollege){
		return thesisCollegeDao.update(thesisCollege);
	}
	
	@Override
	public int remove(Long id){
		return thesisCollegeDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return thesisCollegeDao.batchRemove(ids);
	}
	
}
