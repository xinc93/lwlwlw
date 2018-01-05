package com.bootdo.thesisMamager.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.IdGen;
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

import com.bootdo.thesisMamager.domain.ThesisStudentDO;
import com.bootdo.thesisMamager.service.ThesisStudentService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-01-05 09:29:29
 */
 
@Controller
@RequestMapping("/thesisMamager/thesisStudent")
public class ThesisStudentController {
	@Autowired
	private ThesisStudentService thesisStudentService;
	
	@GetMapping("/thesisStudent")
	@RequiresPermissions("thesisMamager:thesisStudent:thesisStudent")
	String ThesisStudent(){
	    return "thesisMamager/thesisStudent/thesisStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("thesisMamager:thesisStudent:thesisStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ThesisStudentDO> thesisStudentList = thesisStudentService.list(query);
		int total = thesisStudentService.count(query);
		PageUtils pageUtils = new PageUtils(thesisStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("thesisMamager:thesisStudent:add")
	String add(){
	    return "thesisMamager/thesisStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("thesisMamager:thesisStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ThesisStudentDO thesisStudent = thesisStudentService.get(id);
		model.addAttribute("thesisStudent", thesisStudent);
	    return "thesisMamager/thesisStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("thesisMamager:thesisStudent:add")
	public R save( ThesisStudentDO thesisStudent){
		thesisStudent.setId(IdGen.next());
		if(thesisStudentService.save(thesisStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("thesisMamager:thesisStudent:edit")
	public R update( ThesisStudentDO thesisStudent){
		thesisStudentService.update(thesisStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("thesisMamager:thesisStudent:remove")
	public R remove( Long id){
		if(thesisStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("thesisMamager:thesisStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		thesisStudentService.batchRemove(ids);
		return R.ok();
	}
	
}
