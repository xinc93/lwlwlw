package com.bootdo.thesisMamager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.*;
import com.bootdo.thesisMamager.domain.ThesisCollegeDO;
import com.bootdo.thesisMamager.service.ThesisCollegeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.thesisMamager.domain.LicenseDO;
import com.bootdo.thesisMamager.service.LicenseService;

/**
 *
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-26 15:29:02
 */

@Controller
@RequestMapping("/thesis/license")
public class LicenseController {
	@Autowired
	private LicenseService licenseService;

	@Autowired
	private ThesisCollegeService thesisCollegeService;

	@GetMapping()
	@RequiresPermissions("thesis:license:license")
	String License(){
	    return "thesisMamager/license/license";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("thesis:license:license")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<LicenseDO> licenseList = licenseService.list(query);
		licenseList.stream().forEach(i->{
			i.setSchoolName(thesisCollegeService.get(Long.parseLong(i.getLicenseSchool())).getName());
		});
		int total = licenseService.count(query);
		PageUtils pageUtils = new PageUtils(licenseList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("thesis:license:add")
	String add(Model model){
		model.addAttribute("licenseKey",MD5Utils.encrypt(IdGen.nextS()));
		Map College=new HashMap();
		College.put("state","0");
		College.put("pid","0");
		College.put("isLicense","1");
		model.addAttribute("mylist",thesisCollegeService.list(College));
	    return "thesisMamager/license/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("thesis:license:edit")
	String edit(@PathVariable("id") Long id,Model model){
		LicenseDO license = licenseService.get(id);
		license.setSchoolName(thesisCollegeService.get(Long.parseLong(license.getLicenseSchool())).getName());
		model.addAttribute("license", license);
	    return "thesisMamager/license/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("thesis:license:add")
	public R save( LicenseDO license){
		List<Long> list = thesisCollegeService.getByPidList(Long.parseLong(license.getLicenseSchool()));
		list.add(Long.parseLong(license.getLicenseSchool()));
		list.stream().forEach(i->{
			ThesisCollegeDO thesisCollegeDO = thesisCollegeService.get(i);
			thesisCollegeDO.setLicenseKey(license.getLicenseKey());
			thesisCollegeService.update(thesisCollegeDO);
		});
		license.setId(IdGen.nextS());
		license.setState("1");
		license.setAmountUnused(license.getLicenseCount());
		license.setAmountUsed("0");
		if(licenseService.save(license)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("thesis:license:edit")
	public R update( LicenseDO license){
		licenseService.update(license);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("thesis:license:remove")
	public R remove( Long id){
		LicenseDO  licenseDO= licenseService.get(id);
		if("0".equals(licenseDO.getState())){
			licenseDO.setState("1");
		}else {
			licenseDO.setState("0");
		}
		licenseService.update(licenseDO);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("thesis:license:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		licenseService.batchRemove(ids);
		return R.ok();
	}

}
