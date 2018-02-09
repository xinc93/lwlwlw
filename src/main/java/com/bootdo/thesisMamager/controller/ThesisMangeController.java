package com.bootdo.thesisMamager.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.thesisMamager.domain.ThesisMangeDO;
import com.bootdo.thesisMamager.service.ThesisMangeService;
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
 * @date 2018-02-06 15:40:40
 */
 
@Controller
@RequestMapping("/thesisMamager/thesisMange")
public class ThesisMangeController {

	@Autowired
	private ThesisMangeService thesisMangeService;
	
	@GetMapping("/thesisMange")
	@RequiresPermissions("thesisMamager:thesisMange:thesisMange")
	String Mange(){
	    return "thesisMamager/thesisMange/mange";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("thesisMamager:thesisMange:thesisMange")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ThesisMangeDO> mangeList = thesisMangeService.list(query);
		int total = thesisMangeService.count(query);
		PageUtils pageUtils = new PageUtils(mangeList, total);
		return pageUtils;
	}
	
	@GetMapping("thesisMamager:thesisMange:add")
	@RequiresPermissions("system:mange:add")
	String add(){
	    return "system/mange/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("thesisMamager:thesisMange:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ThesisMangeDO mange = thesisMangeService.get(id);
		model.addAttribute("mange", mange);
	    return "system/mange/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("thesisMamager:thesisMange:add")
	public R save( ThesisMangeDO mange){
		if(thesisMangeService.save(mange)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("thesisMamager:thesisMange:edit")
	public R update( ThesisMangeDO mange){
		thesisMangeService.update(mange);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("thesisMamager:thesisMange:remove")
	public R remove( Integer id){
		if(thesisMangeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("thesisMamager:thesisMange:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		thesisMangeService.batchRemove(ids);
		return R.ok();
	}
	
}
