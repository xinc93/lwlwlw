package com.bootdo.thesisMamager.controller;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.utils.*;
import com.bootdo.thesisMamager.domain.ThesisTemplateAttrDO;
import com.bootdo.thesisMamager.domain.ThesisTemplateDO;
import com.bootdo.thesisMamager.service.ThesisCollegeService;
import com.bootdo.thesisMamager.service.ThesisTemplateAttrService;
import com.bootdo.thesisMamager.service.ThesisTemplateService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 *
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-01-31 15:30:00
 */

@Controller
@RequestMapping("/thesisMamager/thesisTemplate")
public class ThesisTemplateController {
    @Autowired
    private ThesisTemplateService thesisTemplateService;
    @Autowired
    private ThesisTemplateAttrService thesisTemplateAttrService;
    @Autowired
    private BootdoConfig bootdoConfig;
    @Autowired
    private ThesisCollegeService thesisCollegeService;
    @GetMapping()
    @RequiresPermissions("thesisMamager:thesisTemplate:thesisTemplate")
    String ThesisTemplate(){
        return "thesisMamager/thesisTemplate/thesisTemplate";
    }

    @ResponseBody
    @GetMapping("/list")

    public PageUtils list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<ThesisTemplateDO> thesisTemplateList = thesisTemplateService.list(query);
        int total = thesisTemplateService.count(query);
        PageUtils pageUtils = new PageUtils(thesisTemplateList, total);
        return pageUtils;
    }

    @GetMapping("/add")

    String add(Model model){
        Map College=new HashMap();
        College.put("state","0");
        College.put("pid","0");
        model.addAttribute("mylist",thesisCollegeService.list(College));
        return "thesisMamager/thesisTemplate/add";
    }

    @GetMapping("/edit/{templateid}")
    @RequiresPermissions("thesisMamager:thesisTemplate:edit")
    String edit(@PathVariable("templateid") Long templateid,Model model){
        ThesisTemplateDO thesisTemplate = thesisTemplateService.get(templateid);
        model.addAttribute("thesisTemplate", thesisTemplate);
        return "thesisMamager/thesisTemplate/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public R save(ThesisTemplateDO thesisTemplate,@RequestParam("myfile") MultipartFile file) throws IOException, DocumentException {
        Long id= IdGen.next();
        SAXReader saxReader=new SAXReader();
        Document document=saxReader.read(file.getInputStream());
        thesisTemplate.setTemplateid(id);
        List<Node>list=document.selectNodes("//aml:annotation");
        for(int i=0;i<list.size();i++) {
            Node node = list.get(i);
            String type = node.valueOf("@w:type");
            if (type.equals("Word.Bookmark.Start")) {
                ThesisTemplateAttrDO thesisTemplateAttrDO=new ThesisTemplateAttrDO();
                thesisTemplateAttrDO.setTemplateid(id);
                thesisTemplateAttrDO.setAttributename(node.valueOf("@w:name"));
                thesisTemplateAttrDO.setAttributeid(IdGen.next());
                System.out.println(node.valueOf("@w:name"));
                thesisTemplateAttrService.save(thesisTemplateAttrDO);
            }
        }
        String fileName = file.getOriginalFilename();
        fileName = id.toString()+".ftl";
        FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
        try {
            FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath()+"template/"+id+"/", fileName);
            if(thesisTemplateService.save(thesisTemplate)>0){
                return R.ok();
            }
            return R.error();


        } catch (Exception e) {
            return R.error();
        }
		/*Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("userName", "哈哈哈哈哈");

		dataMap.put("title", "关于这个插件WORD的测试工作进展");

		dataMap.put("text", "这个插件WORD的测试工作正在积极测试当中！！！！！！！！！！！！！！！！！！");

		//写入doc创建文件

		DocumentHandler doc=new DocumentHandler();
		doc.createDoc(dataMap,bootdoConfig.getUploadPath()+"template/"+thesisTemplate.getTemplateid()+"/",thesisTemplate.getTemplateid());*/

/*

		*//*SAXReader saxReader=new SAXReader();
		File file=new File(bootdoConfig.getUploadPath()+"template/"+thesisTemplate.getTemplateid()+"/");
		Document document=saxReader.read(file.getInputStream());
		List<Node>list=document.selectNodes("//aml:annotation");*//*
		for(int i=0;i<list.size();i++) {
			Node node=list.get(i);
			String type = node.valueOf( "@w:type" );
			if(type.equals("Word.Bookmark.Start"))
			{
				System.out.println(node.valueOf( "@w:name" ));
			}
		}*/

        //System.out.println("doc创建成功！");


    }
    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")

    public R update(ThesisTemplateDO thesisTemplate){
        thesisTemplateService.update(thesisTemplate);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping( "/remove")
    @ResponseBody

    public R remove(Long templateid){
        if(thesisTemplateService.remove(templateid)>0){
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping( "/batchRemove")
    @ResponseBody

    public R remove(@RequestParam("ids[]") Long[] templateids){
        thesisTemplateService.batchRemove(templateids);
        return R.ok();
    }



    @ResponseBody
    @PostMapping("/upload")
    R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException, DocumentException {



/*		SAXReader saxReader=new SAXReader();
		Document document=saxReader.read(file.getInputStream());
		List<Node>list=document.selectNodes("//aml:annotation");
		for(int i=0;i<list.size();i++) {
			Node node=list.get(i);
			String type = node.valueOf( "@w:type" );
			if(type.equals("Word.Bookmark.Start"))
			{
				System.out.println(node.valueOf( "@w:name" ));
			}
		}*/

        Long id= IdGen.next();
        String fileName = file.getOriginalFilename();
        fileName = id.toString()+".xml";
        FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
        try {
            FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath()+"template/"+id+"/", fileName);

            return R.ok(id.toString());
        } catch (Exception e) {
            return R.error();
        }



    }


/*	public Map parserXml(MultipartFile file, String xmlPath) throws IOException, DocumentException {
		Document document;
		SAXReader reader = new SAXReader();
		Map map = new HashMap();
		try {
			document=reader.read(file.getInputStream());
			List list = document.selectNodes(xmlPath);
			for (int i = 0; i < list.size(); i++) {
				Element timer = (Element) list.get(i);
				for(Iterator j = timer.elementIterator();j.hasNext();){
					Element node = (Element) j.next();
					//System.out.println(node.getName() + ":" + node.getText());
					map.put(node.getName(), node.getText());
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return map;
	}*/

    private void getData(Map<String, Object> dataMap) {
        dataMap.put("title", "标题");
        dataMap.put("year", "2017");
        dataMap.put("month", "09");
        dataMap.put("day", "02");
        //dataMap.put("id", "SA15226241");
        //dataMap.put("context", "潘凯特在测试JavaWord");
        dataMap.put("reviewer", "张津津");

        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        for(int i = 0; i < 10; i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", "SA1522624" + i);
            map.put("context", "潘凯特在测试JavaWord" + i);
            list.add(map);
        }

        dataMap.put("list", list);
    }

}
