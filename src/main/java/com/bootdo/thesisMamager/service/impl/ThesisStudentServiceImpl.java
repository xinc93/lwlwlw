package com.bootdo.thesisMamager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.thesisMamager.dao.ThesisStudentDao;
import com.bootdo.thesisMamager.domain.ThesisStudentDO;
import com.bootdo.thesisMamager.service.ThesisStudentService;



@Service
public class ThesisStudentServiceImpl implements ThesisStudentService {
	@Autowired
	private ThesisStudentDao thesisStudentDao;
	
	@Override
	public ThesisStudentDO get(Long id){
		return thesisStudentDao.get(id);
	}
	
	@Override
	public List<ThesisStudentDO> list(Map<String, Object> map){
		return thesisStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return thesisStudentDao.count(map);
	}
	
	@Override
	public int save(ThesisStudentDO thesisStudent){
		return thesisStudentDao.save(thesisStudent);
	}
	
	@Override
	public int update(ThesisStudentDO thesisStudent){
		return thesisStudentDao.update(thesisStudent);
	}
	
	@Override
	public int remove(Long id){
		return thesisStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return thesisStudentDao.batchRemove(ids);
	}
	
}
