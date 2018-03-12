package com.bootdo.thesisMamager.controller.api;

import com.bootdo.common.domain.DictDO;
import com.bootdo.common.page.AjaxResponse;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.*;
import com.bootdo.thesisMamager.domain.MealManageDO;
import com.bootdo.thesisMamager.domain.ThesisCollegeDO;
import com.bootdo.thesisMamager.domain.ThesisStudentDO;
import com.bootdo.thesisMamager.service.MealManageService;
import com.bootdo.thesisMamager.service.ThesisCollegeService;
import com.bootdo.thesisMamager.service.ThesisStudentService;
import com.bootdo.thesisMamager.service.ThesisTeacherService;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(value="Student",tags={"Student"})
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


	@ResponseBody
	@ApiOperation(value = "学生注册", notes = "学生注册", position = 1)
	@RequestMapping(value = "/register", method = {RequestMethod.POST,RequestMethod.GET})
	public AjaxResponse register(@ApiParam(value = "学生姓名", required = false) @RequestParam(required = false) String name,
							  @ApiParam(value = "密码", required = false) @RequestParam(required = false) String passWord,
							  @ApiParam(value = "学校id", required = false) @RequestParam(required = false) Long schoolId,
							  @ApiParam(value = "院系id", required = false) @RequestParam(required = false) Long depId,
							  @ApiParam(value = "导师id", required = false) @RequestParam(required = false) Long teacherId,
							  @ApiParam(value = "学习层次", required = false) @RequestParam(required = false) String eduClass,
							  @ApiParam(value = "学习形式", required = false) @RequestParam(required = false) String learnClass,
							  @ApiParam(value = "专业", required = false) @RequestParam(required = false) String major,
							  @ApiParam(value = "专业方向", required = false) @RequestParam(required = false) String professionalDirection,
							  @ApiParam(value = "学籍号", required = false) @RequestParam(required = false) String schoolNo,
							  @ApiParam(value = "年级", required = false) @RequestParam(required = false) String schoolGrade,
							  @ApiParam(value = "手机号", required = false) @RequestParam(required = false) String mobile){
			AjaxResponse ajaxResponse=new AjaxResponse();
			ThesisStudentDO thesisStudentDO=new ThesisStudentDO();
			thesisStudentDO.setName(name);
			thesisStudentDO.setPassWord(MD5Utils.encrypt(mobile, passWord));
			thesisStudentDO.setSchoolId(schoolId);
			thesisStudentDO.setDepId(depId);
			thesisStudentDO.setTeacherId(teacherId);
			thesisStudentDO.setEduClass(eduClass);
			thesisStudentDO.setLearnClass(learnClass);
			thesisStudentDO.setMajor(major);
			thesisStudentDO.setProfessionalDirection(professionalDirection);
			thesisStudentDO.setSchoolNo(schoolNo);
			thesisStudentDO.setSchoolGrade(schoolGrade);
			thesisStudentDO.setMobile(mobile);
			thesisStudentDO.setIsJoin("1");
			thesisStudentDO.setState("0");
			thesisStudentDO.setAccountType("1");
			MealManageDO mealManageDO=new MealManageDO();
			if(thesisStudentService.save(thesisStudentDO)<=0){
				ajaxResponse.setSuccess(false);
				ajaxResponse.setErrorCode("失败");
				return ajaxResponse;
			}else{
				mealManageDO.setUserId(thesisStudentDO.getId());
				mealManageDO.setType("0");
				mealManageService.save(mealManageDO);
			}
		return ajaxResponse;
	}

	@ResponseBody
	@ApiOperation(value = "学生信息", notes = "学生信息", position = 1)
	@RequestMapping(value = "/getone", method = {RequestMethod.POST,RequestMethod.GET})
	public AjaxResponse login(@ApiParam(value = "id", required = false) @RequestParam(required = false) Long id){
		AjaxResponse ajaxResponse=new AjaxResponse();
		Map map=new HashMap();
		map.put("id",id);
		ThesisStudentDO thesisStudentDO=thesisStudentService.queryone(map);
		ThesisCollegeDO thesisCollegeDO=thesisCollegeService.get(thesisStudentDO.getDepId());
		thesisStudentDO.setDepName(thesisCollegeDO.getName());
		if(thesisStudentDO ==null){
			ajaxResponse.setSuccess(false);
			ajaxResponse.setMsg("操作失败");
			return ajaxResponse;
		}else{
			ajaxResponse.setResult(thesisStudentDO);
		}
		return ajaxResponse;
	}

	@ResponseBody
	@ApiOperation(value = "学生登陆", notes = "学生登陆", position = 1)
	@RequestMapping(value = "/login", method = {RequestMethod.POST,RequestMethod.GET})
	public AjaxResponse login(@ApiParam(value = "密码", required = false) @RequestParam(required = false) String passWord,
							  @ApiParam(value = "手机号", required = false) @RequestParam(required = false) String mobile){
		AjaxResponse ajaxResponse=new AjaxResponse();
		Map map=new HashMap();
		map.put("mobile",mobile);
		map.put("passWord",MD5Utils.encrypt(mobile, passWord));
		ThesisStudentDO thesisStudentDO=thesisStudentService.queryone(map);
		if(thesisStudentDO ==null){
			ajaxResponse.setSuccess(false);
			ajaxResponse.setMsg("操作失败");
			return ajaxResponse;
		}else{
			ajaxResponse.setResult(thesisStudentDO);
		}
		return ajaxResponse;
	}

	@ResponseBody
	@ApiOperation(value = "学生手机唯一性", notes = "学生手机唯一性", position = 1)
	@RequestMapping(value = "/checkmobile", method = {RequestMethod.POST,RequestMethod.GET})
	public AjaxResponse checkmobile(@ApiParam(value = "手机号", required = false) @RequestParam(required = false) String mobile){
		AjaxResponse ajaxResponse=new AjaxResponse();
		Map map=new HashMap();
		map.put("mobile",mobile);
		ThesisStudentDO thesisStudentDO=thesisStudentService.queryone(map);
		if(thesisStudentDO ==null){
			ajaxResponse.setSuccess(false);
			ajaxResponse.setMsg("操作失败");
			return ajaxResponse;
		}else{
			ajaxResponse.setResult(thesisStudentDO);
		}
		return ajaxResponse;
	}


	@ResponseBody
	@ApiOperation(value = "查询学校-院系", notes = "查询学校-院系", position = 1)
	@RequestMapping(value = "/querySchool", method = {RequestMethod.GET})
	public AjaxResponse querySchool(@ApiParam(value = "父id", required = false) @RequestParam(required = false) Integer pid){
		AjaxResponse ajaxResponse=new AjaxResponse();
		Map map=new HashMap();
		if(pid!=null){
			map.put("pid",pid);
			map.put("state","0");
			List<ThesisCollegeDO> li= thesisCollegeService.queryall(map);
			if(li.size()<=0){
				ajaxResponse.setSuccess(false);
				ajaxResponse.setMsg("操作失败");
				return ajaxResponse;
			}else{
				ajaxResponse.setResult(li);
			}
		}else{
			map.put("pid","0");
			map.put("state","0");
			List<ThesisCollegeDO> li= thesisCollegeService.queryall(map);
			if(li.size()<=0){
				ajaxResponse.setSuccess(false);
				ajaxResponse.setMsg("操作失败");
				return ajaxResponse;
			}else{
				ajaxResponse.setResult(li);
			}
		}

		return ajaxResponse;
	}


	@ResponseBody
	@ApiOperation(value = "查询老师", notes = "查询老师", position = 1)
	@RequestMapping(value = "/queryteacher", method = {RequestMethod.GET})
	public AjaxResponse queryteacher(@ApiParam(value = "院系id", required = false) @RequestParam(required = false) Long depId){
		AjaxResponse ajaxResponse=new AjaxResponse();
		Map map=new HashMap();

			map.put("depId",depId);
			map.put("state","0");
			List<ThesisCollegeDO> li=thesisTeacherService.list(map);
			if(li.size()<=0){
				ajaxResponse.setSuccess(false);
				ajaxResponse.setMsg("操作失败");
				return ajaxResponse;
			}else{
				ajaxResponse.setResult(li);
			}

		return ajaxResponse;
	}

	@ResponseBody
	@ApiOperation(value = "查询学习层次,学习形式", notes = "type:study_type学习层次,study_way学习形式", position = 1)
	@RequestMapping(value = "/studytype", method = {RequestMethod.GET})
	public AjaxResponse studytype(@ApiParam(value = "院系id", required = false) @RequestParam(required = false) String type){
		AjaxResponse ajaxResponse=new AjaxResponse();
		Map map=new HashMap();
		map.put("type",type);
		List<DictDO> li=sysDictService.list(map);
		if(li.size()<=0){
			ajaxResponse.setSuccess(false);
			ajaxResponse.setMsg("操作失败");
			return ajaxResponse;
		}else{
			ajaxResponse.setResult(li);
		}

		return ajaxResponse;
	}

	@ResponseBody
	@ApiOperation(value = "学生信息列表", notes = "学生信息列表", position = 1)
	@RequestMapping(value = "/getStudentMessage", method = {RequestMethod.POST,RequestMethod.GET})
	public AjaxResponse getStudentMessage(@ApiParam(value = "id", required = false) @RequestParam(required = false) Long id){
		AjaxResponse ajaxResponse=new AjaxResponse();
		Map map=new HashMap();
		map.put("id",id);
		ThesisStudentDO thesisStudentDO=thesisStudentService.queryone(map);
		ThesisCollegeDO thesisCollegeDO=thesisCollegeService.get(thesisStudentDO.getDepId());
		thesisStudentDO.setDepName(thesisCollegeDO.getName());
		if(thesisStudentDO ==null){
			ajaxResponse.setSuccess(false);
			ajaxResponse.setMsg("操作失败");
			return ajaxResponse;
		}else{
			ajaxResponse.setResult(thesisStudentDO);
		}
		return ajaxResponse;
	}



	@ResponseBody
	@GetMapping("/Studentlist")
	public AjaxResponse list(@ApiParam(value = "页码", required = false) @RequestParam(required = false) Integer page,
							 @ApiParam(value = "每页条数", required = false) @RequestParam(required = false) Integer limit){
		PageHelper.startPage(page, limit);
		//查询列表数据
        //Query query = new Query(params);
		Map query=new HashMap();
		query.put("state",1);
		List<ThesisStudentDO> thesisStudentList = thesisStudentService.list(query);
		for (ThesisStudentDO li:thesisStudentList) {
			ThesisCollegeDO thesisCollegeDO=thesisCollegeService.get(li.getDepId());
			li.setDepName(thesisCollegeDO.getName());
		}
		return new AjaxResponse(thesisStudentList);
	}


}
