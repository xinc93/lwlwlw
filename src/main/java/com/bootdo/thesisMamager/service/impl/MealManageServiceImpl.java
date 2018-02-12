package com.bootdo.thesisMamager.service.impl;

import com.bootdo.thesisMamager.dao.MealManageDao;
import com.bootdo.thesisMamager.domain.MealManageDO;
import com.bootdo.thesisMamager.service.MealManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;




@Service
public class MealManageServiceImpl implements MealManageService {

	@Autowired
	private MealManageDao mealManageDao;

	@Override
	public MealManageDO get(Long id) {
		return mealManageDao.get(id);
	}

	@Override
	public List<MealManageDO> list(Map<String, Object> map) {
		return mealManageDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return mealManageDao.count(map);
	}

	@Override
	public int save(MealManageDO manage) {
		return mealManageDao.save(manage);
	}

	@Override
	public int update(MealManageDO manage) {
		return mealManageDao.update(manage);
	}

	@Override
	public int remove(Long id){
		return mealManageDao.remove(id);
	}

	@Override
	public int batchRemove(Long[] ids){
		return mealManageDao.batchRemove(ids);
	}

}
