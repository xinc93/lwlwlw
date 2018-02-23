package com.bootdo.thesisMamager.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.*;
import com.bootdo.thesisMamager.domain.MealManageDO;
import com.bootdo.thesisMamager.domain.ThesisCollegeDO;
import com.bootdo.thesisMamager.service.MealManageService;
import com.bootdo.thesisMamager.service.ThesisCollegeService;
import com.bootdo.thesisMamager.service.ThesisTeacherService;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bootdo.thesisMamager.domain.ThesisStudentDO;
import com.bootdo.thesisMamager.service.ThesisStudentService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	@Autowired
	private DictService sysDictService;
	@Autowired
	private ThesisCollegeService thesisCollegeService;
	@Autowired
	private ThesisTeacherService thesisTeacherService;
	@Autowired
	private MealManageService mealManageService;
	@Value("${bootdo.uploadPath}")
	private String uploadPath;

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
		for (ThesisStudentDO li:thesisStudentList) {
			ThesisCollegeDO thesisCollegeDO=thesisCollegeService.get(li.getDepId());
			li.setDepName(thesisCollegeDO.getName());
		}
		int total = thesisStudentService.count(query);
		PageUtils pageUtils = new PageUtils(thesisStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("thesisMamager:thesisStudent:add")
	String add(Model model){
		Map map=new HashMap();
		//学习层次
		map.put("type","study_type");
		model.addAttribute("typelist",sysDictService.list(map));
		//学习形式
		map.put("type","study_way");
		model.addAttribute("waylist",sysDictService.list(map));
		//选择学校
		Map College=new HashMap();
		College.put("state","0");
		College.put("pid","0");
		model.addAttribute("mylist",thesisCollegeService.list(College));
	    return "thesisMamager/thesisStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("thesisMamager:thesisStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ThesisStudentDO thesisStudent = thesisStudentService.get(id);
		model.addAttribute("thesisStudent", thesisStudent);
		Map map=new HashMap();
		//学习层次
		map.put("type","study_type");
		model.addAttribute("typelist",sysDictService.list(map));
		//学习形式
		map.put("type","study_way");
		model.addAttribute("waylist",sysDictService.list(map));
		//选择学校
		Map College=new HashMap();
		College.put("state","0");
		College.put("pid","0");
		model.addAttribute("mylist",thesisCollegeService.list(College));

		//院系
		Map faculty=new HashMap();
		faculty.put("state","0");
		faculty.put("pid",thesisStudent.getSchoolId());
		model.addAttribute("faculty",thesisCollegeService.list(faculty));
		//导师
		Map teachers=new HashMap();
		//faculty.put("state","0");
		teachers.put("schoolId",thesisStudent.getSchoolId());
		teachers.put("depId",thesisStudent.getDepId());
		model.addAttribute("teachers",thesisTeacherService.list(teachers));


	    return "thesisMamager/thesisStudent/edit";
	}

	/**
	 * 查询院系
	 */
	@ResponseBody
	@PostMapping("/faculty")
	/*@RequiresPermissions("thesisMamager:thesisStudent:add")*/
	public List faculty(String pid){
		Map map=new HashMap();
		map.put("pid",pid);
		List li=thesisCollegeService.list(map);
		return li;
	}

	/**
	 * 查询老师
	 */
	@ResponseBody
	@PostMapping("/teacher")
	/*@RequiresPermissions("thesisMamager:thesisStudent:add")*/
	public List teacher(String dep_id){
		Map map=new HashMap();
		map.put("depId",dep_id);
		List li=thesisTeacherService.list(map);
		return li;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
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

	@RequestMapping(value = "/edit/upload", method = RequestMethod.POST)
	@ResponseBody
	public Map editupload(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {//@RequestParam("file") MultipartFile file,,HttpServletRequest request
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
		String filePath = "src/main/resources/static/imgupload/";
		System.out.println("fileName-->" + filePath+filename);
		try {
			FileUtil.uploadFile(multipartFile.getBytes(), filePath, filename);
			filePathdata="/imgupload/"+filename;
		} catch (Exception e) {
			e.getMessage();
		}



		json.put("message", "应用上传成功");
		json.put("status", true);
		json.put("filepath",filePathdata);
		return json;
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("thesisMamager:thesisStudent:add")
	public R save( ThesisStudentDO thesisStudent){
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		thesisStudent.setState("0");
		thesisStudent.setCreateTm(sdf.format(new Date()));
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
	public R remove(Long id){
		/*if(thesisStudentService.remove(id)>0){
			return R.ok();
		}*/
		ThesisStudentDO thesisStudent=thesisStudentService.get(id);
		ThesisStudentDO thesisStudentDO=new ThesisStudentDO();
		if(thesisStudent.getState().equals("0")){
			thesisStudentDO.setId(id);
			thesisStudentDO.setState("1");
			if(thesisStudentService.update(thesisStudentDO)>0){
				return R.ok();
			}
		}else{
			thesisStudentDO.setId(id);
			thesisStudentDO.setState("0");
			if(thesisStudentService.update(thesisStudentDO)>0){
				return R.ok();
			}
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
