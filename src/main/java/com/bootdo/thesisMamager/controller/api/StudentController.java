package com.bootdo.thesisMamager.controller.api;

import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.thesisMamager.domain.MealManageDO;
import com.bootdo.thesisMamager.domain.ThesisCollegeDO;
import com.bootdo.thesisMamager.domain.ThesisStudentDO;
import com.bootdo.thesisMamager.service.MealManageService;
import com.bootdo.thesisMamager.service.ThesisCollegeService;
import com.bootdo.thesisMamager.service.ThesisStudentService;
import com.bootdo.thesisMamager.service.ThesisTeacherService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-01-05 09:29:29
 */
 
@Controller
@RequestMapping("/thesisfront/Student")
public class StudentController {

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

	@GetMapping("/Student")
	//@RequiresPermissions("thesisMamager:thesisStudent:thesisStudent")
	String ThesisStudent(){
	    return "thesisMamager/thesisStudent/thesisStudent";
	}

	@ResponseBody
	@GetMapping("/Studentlist")
	//@RequiresPermissions("thesisMamager:thesisStudent:thesisStudent")
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

}
