package com.bootdo.thesisMamager.service;

import com.bootdo.thesisMamager.domain.MealManageDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-12 10:00:10
 */
public interface MealManageService {
	
	MealManageDO get(Long id);
	
	List<MealManageDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MealManageDO manage);
	
	int update(MealManageDO manage);

	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
