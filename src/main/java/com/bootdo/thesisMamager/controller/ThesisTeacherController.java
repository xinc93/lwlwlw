package com.bootdo.thesisMamager.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.FileUtil;
import com.bootdo.thesisMamager.service.ThesisCollegeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bootdo.thesisMamager.domain.ThesisTeacherDO;
import com.bootdo.thesisMamager.service.ThesisTeacherService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	@Autowired
	private ThesisCollegeService thesisCollegeService;
	@Value("${bootdo.uploadPath}")
	private String uploadPath;
	
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
	String add(Model model){
		Map map=new HashMap();
		//选择学校
		Map College=new HashMap();
		College.put("state","0");
		College.put("pid","0");
		model.addAttribute("mylist",thesisCollegeService.list(College));
	    return "thesisMamager/thesisTeacher/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("thesisMamager:thesisTeacher:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ThesisTeacherDO thesisTeacher = thesisTeacherService.get(id);
		model.addAttribute("thesisTeacher", thesisTeacher);
		//选择学校
		Map College=new HashMap();
		College.put("state","0");
		College.put("pid","0");
		model.addAttribute("mylist",thesisCollegeService.list(College));

		//院系
		Map faculty=new HashMap();
		faculty.put("state","0");
		faculty.put("pid",thesisTeacher.getSchoolId());
		model.addAttribute("faculty",thesisCollegeService.list(faculty));
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

	@RequestMapping(value = "/upload")
	@ResponseBody
	public Map upload(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {//@RequestParam("file") MultipartFile file,,HttpServletRequest request
		String filePathdata="";
		request.setCharacterEncoding("UTF-8");

		Map<String, Object> json = new HashMap<String, Object>();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		/** 页面控件的文件流* */
		MultipartFile multipartFile = null;
		Map map =multipartRequest.getFileMap();
		for (Iterator i = map.keySet().iterator(); i.hasNext();) {
			Object obj = i.next();
			multipartFile=(MultipartFile) map.get(obj);

		}
		/** 获取文件的后缀* */
		String filename = multipartFile.getOriginalFilename();
		String filePath = uploadPath;//"src/main/resources/static/imgupload/";
		System.out.println("fileName-->" + filePath+filename);
		try {
			FileUtil.uploadFile(multipartFile.getBytes(), filePath, filename);
			filePathdata="/files/"+filename;//"/imgupload/"+
		} catch (Exception e) {
			e.getMessage();
		}



		json.put("message", "应用上传成功");
		json.put("status", true);
		json.put("filepath",filePathdata);
		return json;
		/*if (!file.isEmpty()) {
			String contentType = file.getContentType();
			String fileName = file.getOriginalFilename();
			System.out.println("fileName-->" + fileName);
			System.out.println("getContentType-->" + contentType);
			filePath = uploadPath+"/imgupload/";
				System.out.println("fileName-->" + filePath+fileName);
			try {
				FileUtil.uploadFile(file.getBytes(), filePath, fileName);
			} catch (Exception e) {
				e.getMessage();
			}
			//返回json
			return filePath+fileName;
		}*/
	}

	@RequestMapping(value = "edit/upload")
	@ResponseBody
	public Map editUpload(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {//@RequestParam("file") MultipartFile file,,HttpServletRequest request
		String filePathdata="";
		request.setCharacterEncoding("UTF-8");

		Map<String, Object> json = new HashMap<String, Object>();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		/** 页面控件的文件流* */
		MultipartFile multipartFile = null;
		Map map =multipartRequest.getFileMap();
		for (Iterator i = map.keySet().iterator(); i.hasNext();) {
			Object obj = i.next();
			multipartFile=(MultipartFile) map.get(obj);

		}
		/** 获取文件的后缀* */
		String filename = multipartFile.getOriginalFilename();
		String filePath = uploadPath;//"src/main/resources/static/imgupload/";
		System.out.println("fileName-->" + filePath+filename);
		try {
			FileUtil.uploadFile(multipartFile.getBytes(), filePath, filename);
			filePathdata="/files/"+filename;//"/imgupload/"+
		} catch (Exception e) {
			e.getMessage();
		}



		json.put("message", "应用上传成功");
		json.put("status", true);
		json.put("filepath",filePathdata);
		return json;
		/*if (!file.isEmpty()) {
			String contentType = file.getContentType();
			String fileName = file.getOriginalFilename();
			System.out.println("fileName-->" + fileName);
			System.out.println("getContentType-->" + contentType);
			filePath = uploadPath+"/imgupload/";
				System.out.println("fileName-->" + filePath+fileName);
			try {
				FileUtil.uploadFile(file.getBytes(), filePath, fileName);
			} catch (Exception e) {
				e.getMessage();
			}
			//返回json
			return filePath+fileName;
		}*/
	}
}
