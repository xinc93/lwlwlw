package com.bootdo.thesisMamager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.thesisMamager.dao.LicenseDao;
import com.bootdo.thesisMamager.domain.LicenseDO;
import com.bootdo.thesisMamager.service.LicenseService;



@Service
public class LicenseServiceImpl implements LicenseService {
	@Autowired
	private LicenseDao licenseDao;
	
	@Override
	public LicenseDO get(Long id){
		return licenseDao.get(id);
	}
	
	@Override
	public List<LicenseDO> list(Map<String, Object> map){
		return licenseDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return licenseDao.count(map);
	}
	
	@Override
	public int save(LicenseDO license){
		return licenseDao.save(license);
	}
	
	@Override
	public int update(LicenseDO license){
		return licenseDao.update(license);
	}
	
	@Override
	public int remove(Long id){
		return licenseDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return licenseDao.batchRemove(ids);
	}
	
}
