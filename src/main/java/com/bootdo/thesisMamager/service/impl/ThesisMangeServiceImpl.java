package com.bootdo.thesisMamager.service.impl;

import com.bootdo.thesisMamager.dao.ThesisMangeDao;
import com.bootdo.thesisMamager.domain.ThesisMangeDO;
import com.bootdo.thesisMamager.service.ThesisMangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class ThesisMangeServiceImpl implements ThesisMangeService {
	@Autowired
	private ThesisMangeDao thesisMangeDao;

	@Override
	public ThesisMangeDO get(Integer id){
		return thesisMangeDao.get(id);
	}

	@Override
	public List<ThesisMangeDO> list(Map<String, Object> map){
		return thesisMangeDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return thesisMangeDao.count(map);
	}

	@Override
	public int save(ThesisMangeDO mange){
		return thesisMangeDao.save(mange);
	}

	@Override
	public int update(ThesisMangeDO mange){
		return thesisMangeDao.update(mange);
	}

	@Override
	public int remove(Integer id){
		return thesisMangeDao.remove(id);
	}

	@Override
	public int batchRemove(Integer[] ids){
		return thesisMangeDao.batchRemove(ids);
	}

}
