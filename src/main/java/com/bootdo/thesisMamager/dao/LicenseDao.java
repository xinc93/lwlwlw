package com.bootdo.thesisMamager.dao;

import com.bootdo.thesisMamager.domain.LicenseDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-26 15:29:02
 */
@Mapper
public interface LicenseDao {

	LicenseDO get(Long id);
	
	List<LicenseDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LicenseDO license);
	
	int update(LicenseDO license);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
