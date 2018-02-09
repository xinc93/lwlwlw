package com.bootdo.thesisMamager.dao;

import java.util.List;
import java.util.Map;

import com.bootdo.thesisMamager.domain.ThesisMangeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-06 15:40:40
 */
@Mapper
public interface ThesisMangeDao {

	ThesisMangeDO get(Integer id);
	
	List<ThesisMangeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ThesisMangeDO mange);
	
	int update(ThesisMangeDO mange);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
