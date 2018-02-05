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

import com.bootdo.thesisMamager.domain.ThesisTemplateAttrDO;
import com.bootdo.thesisMamager.service.ThesisTemplateAttrService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-02 13:55:55
 */
 
@Controller
@RequestMapping("/thesisMamager/thesisTemplateAttr")
public class ThesisTemplateAttrController {
	@Autowired
	private ThesisTemplateAttrService thesisTemplateAttrService;
	
	@GetMapping()

	String ThesisTemplateAttr(){
	    return "thesisMamager/thesisTemplateAttr/thesisTemplateAttr";
	}
	
	@ResponseBody
	@GetMapping("/list")

	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		/*params.put("offset",10000);
		params.put("limit",1);*/

		Query query = new Query(params);
		List<ThesisTemplateAttrDO> thesisTemplateAttrList = thesisTemplateAttrService.list(query);
		int total = thesisTemplateAttrService.count(query);
		PageUtils pageUtils = new PageUtils(thesisTemplateAttrList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")

	String add(){
	    return "thesisMamager/thesisTemplateAttr/add";
	}

	@GetMapping("/edit/{attributeid}")

	String edit(@PathVariable("attributeid") Long attributeid,Model model){
		ThesisTemplateAttrDO thesisTemplateAttr = thesisTemplateAttrService.get(attributeid);
		model.addAttribute("thesisTemplateAttr", thesisTemplateAttr);
	    return "thesisMamager/thesisTemplateAttr/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")

	public R save( ThesisTemplateAttrDO thesisTemplateAttr){
		if(thesisTemplateAttrService.save(thesisTemplateAttr)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")

	public R update( ThesisTemplateAttrDO thesisTemplateAttr){
		thesisTemplateAttrService.update(thesisTemplateAttr);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody

	public R remove( Long attributeid){
		if(thesisTemplateAttrService.remove(attributeid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("thesisMamager:thesisTemplateAttr:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] attributeids){
		thesisTemplateAttrService.batchRemove(attributeids);
		return R.ok();
	}
	
}
