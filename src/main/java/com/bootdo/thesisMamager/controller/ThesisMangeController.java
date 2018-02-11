package com.bootdo.thesisMamager.controller;

import java.io.*;
import java.util.List;
import java.util.Map;

import com.bootdo.thesisMamager.domain.ThesisMangeDO;
import com.bootdo.thesisMamager.service.ThesisMangeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

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
    @Value("${bootdo.uploadPath}")
    private String uploadPath;

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

	@GetMapping("/downloade")
	@RequiresPermissions("thesisMamager:thesisMange:downloade")
    void downloade(String id,String thesisName,String thesisStuid,HttpServletResponse response) throws IOException {
       String path= uploadPath+"/"+thesisStuid+"/"+id+"/"+thesisName+".doc";
       /* try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            if (!file.exists()) {
                response.sendError(404, "File not found!");
                return;
            }
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", filename));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/x-msdownload");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }*/
        File file = new File(path);
        long size = file.length();
        //为了解决中文名称乱码问题 这里是设置文件下载后的名称
        String fileName = new String(file.getName().getBytes("UTF-8"), "iso-8859-1");
        response.reset();
        response.setHeader("Accept-Ranges", "bytes");
        //设置文件下载是以附件的形式下载
        response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName));
        response.addHeader("Content-Length", String.valueOf(size));
        response.setContentType("application/octet-stream; charset=UTF-8");

        ServletOutputStream sos = response.getOutputStream();
        FileInputStream in = new FileInputStream(file);
        BufferedOutputStream outputStream = new BufferedOutputStream(sos);
        byte[] b = new byte[1024];
        int i = 0;
        while ((i = in.read(b)) > 0) {
            outputStream.write(b, 0, i);
        }
        outputStream.flush();
        sos.close();
        outputStream.close();
        in.close();
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
