package com.bootdo.thesisMamager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.thesisMamager.domain.MealManageDO;
import com.bootdo.thesisMamager.domain.ThesisCollegeDO;
import com.bootdo.thesisMamager.service.MealManageService;
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

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-12 10:00:10
 */
 
@Controller
@RequestMapping("/MealManage/MealManage")
public class MealManageController {
	@Autowired
	private MealManageService mealManageService;
	@Autowired
	private ThesisCollegeService thesisCollegeService;

	@GetMapping("/mealmanage")
	@RequiresPermissions("MealManage:MealManage:mealmanage")
	String mealmanage(){
		return "thesisMamager/mealmanage/mealmanage";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("MealManage:MealManage:mealmanage")
	public PageUtils mealmanagelist(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<MealManageDO> manageList = mealManageService.list(query);

		for (MealManageDO li:manageList) {
			ThesisCollegeDO thesisCollegeDO=thesisCollegeService.get(li.getDepId());
			li.setDepname(thesisCollegeDO.getName());
		}
		int total = mealManageService.count(query);
		PageUtils pageUtils = new PageUtils(manageList, total);
		return pageUtils;
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("MealManage:MealManage:edit")
	String edit(@PathVariable("id") Long id,Model model){
		MealManageDO mealManageDO = mealManageService.get(id);
		model.addAttribute("mealManageDO", mealManageDO);
		/*Map map=new HashMap();
		//学习层次
		map.put("type","study_type");*/
		//model.addAttribute("typelist",sysDictService.list(map));
		return "thesisMamager/mealmanage/edit";
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@PostMapping("/update")
	@RequiresPermissions("MealManage:MealManage:edit")
	public R update(MealManageDO mealManageDO){
		mealManageService.update(mealManageDO);
		return R.ok();
	}

}
