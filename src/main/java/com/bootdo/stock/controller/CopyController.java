package com.bootdo.stock.controller;

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

import com.bootdo.stock.domain.CopyDO;
import com.bootdo.stock.service.CopyService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-10 17:14:31
 */
 
@Controller
@RequestMapping("/system/copy")
public class CopyController {
	@Autowired
	private CopyService copyService;

	@GetMapping()
	String Copy(){
	    return "system/copy/copy";
	}

	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CopyDO> copyList = copyService.list(query);
		int total = copyService.count(query);
		PageUtils pageUtils = new PageUtils(copyList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("system:copy:add")
	String add(){
	    return "system/copy/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:copy:edit")
	String edit(@PathVariable("id") String id,Model model){
		CopyDO copy = copyService.get(id);
		model.addAttribute("copy", copy);
	    return "system/copy/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:copy:add")
	public R save( CopyDO copy){
		if(copyService.save(copy)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:copy:edit")
	public R update( CopyDO copy){
		copyService.update(copy);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:copy:remove")
	public R remove( String id){
		if(copyService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:copy:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		copyService.batchRemove(ids);
		return R.ok();
	}
	
}
