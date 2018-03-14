package com.bootdo.thesisMamager.controller.api;

import com.bootdo.common.domain.DictDO;
import com.bootdo.common.page.AjaxResponse;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.thesisMamager.domain.MealManageDO;
import com.bootdo.thesisMamager.domain.ThesisCollegeDO;
import com.bootdo.thesisMamager.domain.ThesisStudentDO;
import com.bootdo.thesisMamager.domain.ThesisTeacherDO;
import com.bootdo.thesisMamager.service.MealManageService;
import com.bootdo.thesisMamager.service.ThesisCollegeService;
import com.bootdo.thesisMamager.service.ThesisStudentService;
import com.bootdo.thesisMamager.service.ThesisTeacherService;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-01-05 09:29:29
 */
 
@Controller
@Api(value="Teacher",tags={"Teacher"})
@RequestMapping("/thesisfront/Student")
public class TeacherController {

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


	@ApiOperation(value = "老师登录", notes = "老师登录", position = 1)
	@RequestMapping(value = "/teacherLogin", method = {RequestMethod.POST,RequestMethod.GET})
	public AjaxResponse teacherLogin(@ApiParam(value = "手机号", required = false) @RequestParam(required = false) String mobile,
							   @ApiParam(value = "密码", required = false) @RequestParam(required = false) String passWord){
		AjaxResponse ajaxResponse=new AjaxResponse();
		ThesisTeacherDO thesisTeacherDO = thesisTeacherService.getLogin(mobile,MD5Utils.encrypt(mobile,passWord));
		if(thesisTeacherDO != null){
			ajaxResponse.setResult(thesisTeacherDO);
		}else {
			ajaxResponse.setErrorCode("400");
			ajaxResponse.setSuccess(false);
		}
		return ajaxResponse;
	}

	@ResponseBody
	@ApiOperation(value = "注册导师", notes = "注册导师", position = 1)
	@RequestMapping(value = "/register", method = {RequestMethod.POST,RequestMethod.GET})
	public AjaxResponse register(@ApiParam(value = "注册导师", required = false) @RequestParam(required = false) String name,
							  @ApiParam(value = "密码", required = false) @RequestParam(required = false) String passWord,
							  @ApiParam(value = "学校id", required = false) @RequestParam(required = false) Long schoolId,
							  @ApiParam(value = "院系id", required = false) @RequestParam(required = false) Long depId,
							  @ApiParam(value = "专业", required = false) @RequestParam(required = false) String major,
								 @ApiParam(value = "职称", required = false) @RequestParam(required = false) String teacherTitle,
								 @ApiParam(value = "邀请码", required = false) @RequestParam(required = false) String inviteCode,
								 @ApiParam(value = "手机号", required = false) @RequestParam(required = false) String mobile){
			AjaxResponse ajaxResponse=new AjaxResponse();
			ThesisTeacherDO thesisTeacherDO=new ThesisTeacherDO();
			thesisTeacherDO.setName(name);
			thesisTeacherDO.setPassWord(MD5Utils.encrypt(mobile, passWord));
			thesisTeacherDO.setSchoolId(schoolId);
			thesisTeacherDO.setDepId(depId);
			thesisTeacherDO.setMajor(major);
			thesisTeacherDO.setTeacherTitle(teacherTitle);
			thesisTeacherDO.setMobile(mobile);
			thesisTeacherDO.setState("0");
			if(thesisTeacherService.save(thesisTeacherDO)<=0){
				ajaxResponse.setSuccess(false);
				ajaxResponse.setMsg("注册失败");
				return ajaxResponse;
			}
		return ajaxResponse;
	}

}
