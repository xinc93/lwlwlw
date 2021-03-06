package com.bootdo.thesisMamager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.*;
import com.bootdo.thesisMamager.service.ThesisStudentService;
import com.bootdo.thesisMamager.service.ThesisTeacherService;
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

/**
 *
 *
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
	@Autowired
	private ThesisTeacherService teacherService;
	@Autowired
	private ThesisStudentService thesisStudentService;


	@GetMapping()
	@RequiresPermissions("thesisMamager:thesisCollege:thesisCollege")
	String ThesisCollege(){
	    return "thesisMamager/thesisCollege/thesisCollege";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("thesisMamager:thesisCollege:thesisCollege")
	public List<ThesisCollegeDO> list(@RequestParam Map<String, Object> params){
		//查询列表数据

		Map<String, Object> query = new HashMap<>(params);
		List<ThesisCollegeDO> thesisCollegeList = thesisCollegeService.list(query);

		return thesisCollegeList;
	}

	@GetMapping("/add/{pId}")
	@RequiresPermissions("thesisMamager:thesisCollege:add")
	String add(@PathVariable("pId") String pId, Model model) {
		Long p = Long.parseLong(pId);
		model.addAttribute("pid", pId);
		if (p == 0) {
			model.addAttribute("pName", "总部门");
		} else {
			model.addAttribute("pName", thesisCollegeService.get(p).getName());
		}
		return  "thesisMamager/thesisCollege/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("thesisMamager:thesisCollege:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ThesisCollegeDO thesisCollege = thesisCollegeService.get(id);
		Long p = thesisCollege.getPid();
		model.addAttribute("pid", p);
		if (p == 0) {
			model.addAttribute("pName", "总部门");
		} else {
			model.addAttribute("pName", thesisCollegeService.get(p).getName());
		}
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
		ThesisCollegeDO thesisCollege1 = thesisCollegeService.get(thesisCollege.getPid());
		if(thesisCollege1 != null && (thesisCollege1 != null && thesisCollege1.getLevel() != 1)){
			return R.error(1,"只能在学校下添加院系");
		}
		thesisCollege.setCreateTm(DateUtil.getCurDateTime());
		if(thesisCollege.getPid() !=0){
			thesisCollege.setLevel(2);
		}else {
			thesisCollege.setLevel(1);
		}
		thesisCollege.setState(0);
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
	public R remove( Long id) {
		Map map = new HashMap();
		map.put("schoolId", id);
		Map map2 = new HashMap();
		map2.put("depId", id);
		if (thesisCollegeService.getByPid(id) != null) {
			return R.error("删除失败,下级还有院系数据");
		}
		if (teacherService.list(map).size() > 0 || thesisStudentService.list(map).size() > 0) {
			return R.error("删除失败,下级还有导师和学生的数据");
		}
		if (teacherService.list(map2).size() > 0 || thesisStudentService.list(map2).size() > 0) {
			return R.error("删除失败,下级还有导师和学生的数据");
		}
		if (thesisCollegeService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * stop
	 */
	@PostMapping( "/stop")
	@ResponseBody
	@RequiresPermissions("thesisMamager:thesisCollege:stop")
	public R stop( Long id) {
		ThesisCollegeDO thesisCollegeDO = thesisCollegeService.get(id);
		if(thesisCollegeDO.getState() == 0){
			thesisCollegeDO.setState(1);
		}else{
			thesisCollegeDO.setState(0);
		}
		thesisCollegeService.update(thesisCollegeDO);
		return R.ok();
	}

	/**
	 * 删除
	 */
	/*@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("thesisMamager:thesisCollege:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		thesisCollegeService.batchRemove(ids);
		return R.ok();
	}*/

}
