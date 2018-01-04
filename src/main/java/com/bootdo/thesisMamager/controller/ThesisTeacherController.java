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

import com.bootdo.thesisMamager.domain.ThesisTeacherDO;
import com.bootdo.thesisMamager.service.ThesisTeacherService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author xc
 * @email 1992lcg@163.com
 * @date 2018-01-04 16:03:43
 */
 
@Controller
@RequestMapping("/thesisMamager/thesisTeacher")
public class ThesisTeacherController {
	@Autowired
	private ThesisTeacherService thesisTeacherService;
	
	@GetMapping()
	@RequiresPermissions("thesisMamager:thesisTeacher:thesisTeacher")
	String ThesisTeacher(){
	    return "thesisMamager/thesisTeacher/thesisTeacher";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("thesisMamager:thesisTeacher:thesisTeacher")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ThesisTeacherDO> thesisTeacherList = thesisTeacherService.list(query);
		int total = thesisTeacherService.count(query);
		PageUtils pageUtils = new PageUtils(thesisTeacherList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("thesisMamager:thesisTeacher:add")
	String add(){
	    return "thesisMamager/thesisTeacher/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("thesisMamager:thesisTeacher:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ThesisTeacherDO thesisTeacher = thesisTeacherService.get(id);
		model.addAttribute("thesisTeacher", thesisTeacher);
	    return "thesisMamager/thesisTeacher/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("thesisMamager:thesisTeacher:add")
	public R save( ThesisTeacherDO thesisTeacher){
		if(thesisTeacherService.save(thesisTeacher)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("thesisMamager:thesisTeacher:edit")
	public R update( ThesisTeacherDO thesisTeacher){
		thesisTeacherService.update(thesisTeacher);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("thesisMamager:thesisTeacher:remove")
	public R remove( Long id){
		if(thesisTeacherService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("thesisMamager:thesisTeacher:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		thesisTeacherService.batchRemove(ids);
		return R.ok();
	}
	
}
