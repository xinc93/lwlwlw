package com.bootdo.thesisMamager.dao;
import java.util.List;
import java.util.Map;

import com.bootdo.thesisMamager.domain.MealManageDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-12 10:00:10
 */
@Mapper
public interface MealManageDao {

	MealManageDO get(Long id);
	
	List<MealManageDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MealManageDO manage);
	
	int update(MealManageDO manage);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
