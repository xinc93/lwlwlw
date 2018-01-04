package com.bootdo.thesisMamager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.thesisMamager.dao.ThesisTeacherDao;
import com.bootdo.thesisMamager.domain.ThesisTeacherDO;
import com.bootdo.thesisMamager.service.ThesisTeacherService;



@Service
public class ThesisTeacherServiceImpl implements ThesisTeacherService {
	@Autowired
	private ThesisTeacherDao thesisTeacherDao;
	
	@Override
	public ThesisTeacherDO get(Long id){
		return thesisTeacherDao.get(id);
	}
	
	@Override
	public List<ThesisTeacherDO> list(Map<String, Object> map){
		return thesisTeacherDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return thesisTeacherDao.count(map);
	}
	
	@Override
	public int save(ThesisTeacherDO thesisTeacher){
		return thesisTeacherDao.save(thesisTeacher);
	}
	
	@Override
	public int update(ThesisTeacherDO thesisTeacher){
		return thesisTeacherDao.update(thesisTeacher);
	}
	
	@Override
	public int remove(Long id){
		return thesisTeacherDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return thesisTeacherDao.batchRemove(ids);
	}
	
}
