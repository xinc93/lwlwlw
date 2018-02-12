package com.bootdo.thesisMamager.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.thesisMamager.domain.MealManageDO;
import com.bootdo.thesisMamager.service.MealManageService;
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
		int total = mealManageService.count(query);
		PageUtils pageUtils = new PageUtils(manageList, total);
		return pageUtils;
	}
	
}
