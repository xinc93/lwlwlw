package com.bootdo.thesisMamager.controller;

import java.util.List;
import java.util.Map;

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

import com.bootdo.thesisMamager.domain.ThesisCollegeDO;
import com.bootdo.thesisMamager.service.ThesisCollegeService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author xc
 * @email 1992lcg@163.com
 * @date 2018-01-04 16:03:33
 */
 
@Controller
@RequestMapping("/thesisMamager/thesisCollege")
public class ThesisCollegeController {
	@Autowired
	private ThesisCollegeService thesisCollegeService;
	
	@GetMapping()
	@RequiresPermissions("thesisMamager:thesisCollege:thesisCollege")
	String ThesisCollege(){
	    return "thesisMamager/thesisCollege/thesisCollege";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("thesisMamager:thesisCollege:thesisCollege")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ThesisCollegeDO> thesisCollegeList = thesisCollegeService.list(query);
		int total = thesisCollegeService.count(query);
		PageUtils pageUtils = new PageUtils(thesisCollegeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("thesisMamager:thesisCollege:add")
	String add(){
	    return "thesisMamager/thesisCollege/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("thesisMamager:thesisCollege:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ThesisCollegeDO thesisCollege = thesisCollegeService.get(id);
		model.addAttribute("thesisCollege", thesisCollege);
	    return "thesisMamager/thesisCollege/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("thesisMamager:thesisCollege:add")
	public R save( ThesisCollegeDO thesisCollege){
		if(thesisCollegeService.save(thesisCollege)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("thesisMamager:thesisCollege:edit")
	public R update( ThesisCollegeDO thesisCollege){
		thesisCollegeService.update(thesisCollege);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("thesisMamager:thesisCollege:remove")
	public R remove( Long id){
		if(thesisCollegeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("thesisMamager:thesisCollege:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		thesisCollegeService.batchRemove(ids);
		return R.ok();
	}
	
}
